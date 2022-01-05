//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.setting.builder.numerical;

import com.veteran.hack.setting.impl.numerical.*;
import java.util.function.*;
import com.veteran.hack.setting.*;
import com.veteran.hack.setting.builder.*;

public class DoubleSettingBuilder extends NumericalSettingBuilder<Double>
{
    @Override
    public NumberSetting build() {
        return new DoubleSetting((Double)this.initialValue, this.predicate(), this.consumer(), this.name, this.visibilityPredicate(), (Double)this.min, (Double)this.max);
    }
    
    @Override
    public DoubleSettingBuilder withVisibility(final Predicate<Double> predicate) {
        return (DoubleSettingBuilder)super.withVisibility(predicate);
    }
    
    @Override
    public DoubleSettingBuilder withRestriction(final Predicate<Double> predicate) {
        return (DoubleSettingBuilder)super.withRestriction(predicate);
    }
    
    @Override
    public DoubleSettingBuilder withConsumer(final BiConsumer<Double, Double> consumer) {
        return (DoubleSettingBuilder)super.withConsumer(consumer);
    }
    
    @Override
    public DoubleSettingBuilder withValue(final Double value) {
        return (DoubleSettingBuilder)super.withValue(value);
    }
    
    @Override
    public DoubleSettingBuilder withRange(final Double minimum, final Double maximum) {
        return (DoubleSettingBuilder)super.withRange(minimum, maximum);
    }
    
    @Override
    public DoubleSettingBuilder withMaximum(final Double maximum) {
        return (DoubleSettingBuilder)super.withMaximum(maximum);
    }
    
    @Override
    public DoubleSettingBuilder withListener(final BiConsumer<Double, Double> consumer) {
        return (DoubleSettingBuilder)super.withListener(consumer);
    }
    
    @Override
    public DoubleSettingBuilder withName(final String name) {
        return (DoubleSettingBuilder)super.withName(name);
    }
    
    @Override
    public DoubleSettingBuilder withMinimum(final Double minimum) {
        return (DoubleSettingBuilder)super.withMinimum(minimum);
    }
}
