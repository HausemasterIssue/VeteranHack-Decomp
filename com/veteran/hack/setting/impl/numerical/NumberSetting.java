//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.setting.impl.numerical;

import com.veteran.hack.setting.*;
import java.util.function.*;
import com.veteran.hack.setting.converter.*;
import com.google.common.base.*;

public abstract class NumberSetting<T extends Number> extends Setting<T>
{
    private final T min;
    private final T max;
    
    public NumberSetting(final T value, final Predicate<T> restriction, final BiConsumer<T, T> consumer, final String name, final Predicate<T> visibilityPredicate, final T min, final T max) {
        super(value, restriction, consumer, name, visibilityPredicate);
        this.min = min;
        this.max = max;
    }
    
    public boolean isBound() {
        return this.min != null && this.max != null;
    }
    
    public abstract AbstractBoxedNumberConverter converter();
    
    @Override
    public T getValue() {
        return super.getValue();
    }
    
    public T getMax() {
        return this.max;
    }
    
    public T getMin() {
        return this.min;
    }
}
