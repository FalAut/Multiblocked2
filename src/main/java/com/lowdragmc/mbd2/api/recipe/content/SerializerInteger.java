package com.lowdragmc.mbd2.api.recipe.content;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import org.apache.commons.lang3.math.NumberUtils;

public class SerializerInteger implements IContentSerializer<Integer> {

    public static SerializerInteger INSTANCE = new SerializerInteger();

    private SerializerInteger() {}

    @Override
    public void toNetwork(FriendlyByteBuf buf, Integer content) {
        buf.writeInt(content);
    }

    @Override
    public Integer fromNetwork(FriendlyByteBuf buf) {
        return buf.readInt();
    }

    @Override
    public Tag toNBT(Integer content) {
        return IntTag.valueOf(content);
    }

    @Override
    public Integer fromNBT(Tag nbt) {
        if (nbt instanceof IntTag intTag) {
            return intTag.getAsInt();
        }
        return 0;
    }

    @Override
    public Integer fromJson(JsonElement json) {
        return json.getAsInt();
    }

    @Override
    public JsonElement toJson(Integer content) {
        return new JsonPrimitive(content);
    }

    @Override
    public Integer of(Object o) {
        if (o instanceof Integer) {
            return (Integer) o;
        } else if (o instanceof Number) {
            return ((Number) o).intValue();
        } else if (o instanceof CharSequence) {
            return NumberUtils.toInt(o.toString(), 1);
        }
        return 0;
    }

    @Override
    public Integer copyWithModifier(Integer content, ContentModifier modifier) {
        return modifier.apply(content).intValue();
    }

    @Override
    public Integer copyInner(Integer content) {
        return content;
    }
}
