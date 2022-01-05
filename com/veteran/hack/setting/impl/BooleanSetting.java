//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.setting.impl;

import com.veteran.hack.setting.*;
import com.veteran.hack.setting.converter.*;
import java.util.function.*;
import com.google.common.base.*;

public class BooleanSetting extends Setting<Boolean>
{
    private static final BooleanConverter converter;
    
    public BooleanSetting(final Boolean value, final Predicate<Boolean> restriction, final BiConsumer<Boolean, Boolean> consumer, final String name, final Predicate<Boolean> visibilityPredicate) {
        super(value, restriction, consumer, name, visibilityPredicate);
    }
    
    public BooleanConverter converter() {
        return BooleanSetting.converter;
    }
    
    static {
        converter = new BooleanConverter();
    }
}
