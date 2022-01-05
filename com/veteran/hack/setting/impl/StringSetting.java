//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.setting.impl;

import com.veteran.hack.setting.*;
import com.veteran.hack.setting.converter.*;
import java.util.function.*;
import com.google.common.base.*;

public class StringSetting extends Setting<String>
{
    private static final StringConverter converter;
    
    public StringSetting(final String value, final Predicate<String> restriction, final BiConsumer<String, String> consumer, final String name, final Predicate<String> visibilityPredicate) {
        super(value, restriction, consumer, name, visibilityPredicate);
    }
    
    public StringConverter converter() {
        return StringSetting.converter;
    }
    
    @Override
    public String getValueAsString() {
        return this.getValue();
    }
    
    @Override
    public void setValueFromString(final String s) {
        this.setValue(s);
    }
    
    static {
        converter = new StringConverter();
    }
}
