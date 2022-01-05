//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.setting.builder.numerical;

import com.veteran.hack.setting.builder.*;
import java.util.function.*;
import com.veteran.hack.setting.impl.numerical.*;
import com.veteran.hack.setting.*;

public abstract class NumericalSettingBuilder<T extends Number> extends SettingBuilder<T>
{
    protected T min;
    protected T max;
    
    public NumericalSettingBuilder<T> withMinimum(final T minimum) {
        this.predicateList.add(t -> t.doubleValue() >= minimum.doubleValue());
        if (this.min == null || minimum.doubleValue() > this.min.doubleValue()) {
            this.min = minimum;
        }
        return this;
    }
    
    public NumericalSettingBuilder<T> withMaximum(final T maximum) {
        this.predicateList.add(t -> t.doubleValue() <= maximum.doubleValue());
        if (this.max == null || maximum.doubleValue() < this.max.doubleValue()) {
            this.max = maximum;
        }
        return this;
    }
    
    public NumericalSettingBuilder<T> withRange(final T minimum, final T maximum) {
        final double doubleValue;
        this.predicateList.add(t -> {
            doubleValue = t.doubleValue();
            return doubleValue >= minimum.doubleValue() && doubleValue <= maximum.doubleValue();
        });
        if (this.min == null || minimum.doubleValue() > this.min.doubleValue()) {
            this.min = minimum;
        }
        if (this.max == null || maximum.doubleValue() < this.max.doubleValue()) {
            this.max = maximum;
        }
        return this;
    }
    
    public NumericalSettingBuilder<T> withListener(final BiConsumer<T, T> consumer) {
        this.consumer = consumer;
        return this;
    }
    
    @Override
    public NumericalSettingBuilder<T> withValue(final T value) {
        return (NumericalSettingBuilder)super.withValue(value);
    }
    
    @Override
    public NumericalSettingBuilder withName(final String name) {
        return (NumericalSettingBuilder)super.withName(name);
    }
    
    @Override
    public abstract NumberSetting build();
}
