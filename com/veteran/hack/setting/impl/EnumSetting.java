//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.setting.impl;

import com.veteran.hack.setting.*;
import com.veteran.hack.setting.converter.*;
import java.util.function.*;
import com.google.common.base.*;

public class EnumSetting<T extends Enum> extends Setting<T>
{
    private EnumConverter converter;
    public final Class<? extends Enum> clazz;
    
    public EnumSetting(final T value, final Predicate<T> restriction, final BiConsumer<T, T> consumer, final String name, final Predicate<T> visibilityPredicate, final Class<? extends Enum> clazz) {
        super(value, restriction, consumer, name, visibilityPredicate);
        this.converter = new EnumConverter((Class)clazz, (Enum)value);
        this.clazz = clazz;
    }
    
    public Converter converter() {
        return (Converter)this.converter;
    }
}
