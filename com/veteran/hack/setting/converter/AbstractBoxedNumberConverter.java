//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.setting.converter;

import com.google.common.base.*;
import com.google.gson.*;

public abstract class AbstractBoxedNumberConverter<T extends Number> extends Converter<T, JsonElement>
{
    protected JsonElement doForward(final T t) {
        return (JsonElement)new JsonPrimitive((Number)t);
    }
}
