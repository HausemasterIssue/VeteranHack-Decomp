//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.setting.impl.numerical;

import java.util.function.*;
import com.veteran.hack.setting.converter.*;
import com.google.common.base.*;

public class FloatSetting extends NumberSetting<Float>
{
    private static final BoxedFloatConverter converter;
    
    public FloatSetting(final Float value, final Predicate<Float> restriction, final BiConsumer<Float, Float> consumer, final String name, final Predicate<Float> visibilityPredicate, final Float min, final Float max) {
        super(value, restriction, consumer, name, visibilityPredicate, min, max);
    }
    
    @Override
    public AbstractBoxedNumberConverter converter() {
        return (AbstractBoxedNumberConverter)FloatSetting.converter;
    }
    
    static {
        converter = new BoxedFloatConverter();
    }
}
