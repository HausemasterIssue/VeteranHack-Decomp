//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.setting.converter;

import com.google.gson.*;

public class BoxedDoubleConverter extends AbstractBoxedNumberConverter<Double>
{
    protected Double doBackward(final JsonElement s) {
        return s.getAsDouble();
    }
}
