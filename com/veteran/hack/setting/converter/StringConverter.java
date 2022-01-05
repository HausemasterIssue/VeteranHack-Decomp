//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.setting.converter;

import com.google.common.base.*;
import com.google.gson.*;

public class StringConverter extends Converter<String, JsonElement>
{
    protected JsonElement doForward(final String s) {
        return (JsonElement)new JsonPrimitive(s);
    }
    
    protected String doBackward(final JsonElement s) {
        return s.getAsString();
    }
}
