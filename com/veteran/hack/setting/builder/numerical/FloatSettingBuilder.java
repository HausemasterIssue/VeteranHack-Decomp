//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.setting.builder.numerical;

import com.veteran.hack.setting.impl.numerical.*;
import java.util.function.*;
import com.veteran.hack.setting.*;
import com.veteran.hack.setting.builder.*;

public class FloatSettingBuilder extends NumericalSettingBuilder<Float>
{
    @Override
    public NumberSetting build() {
        return new FloatSetting((Float)this.initialValue, this.predicate(), this.consumer(), this.name, this.visibilityPredicate(), (Float)this.min, (Float)this.max);
    }
    
    @Override
    public FloatSettingBuilder withMinimum(final Float minimum) {
        return (FloatSettingBuilder)super.withMinimum(minimum);
    }
    
    @Override
    public FloatSettingBuilder withName(final String name) {
        return (FloatSettingBuilder)super.withName(name);
    }
    
    @Override
    public FloatSettingBuilder withListener(final BiConsumer<Float, Float> consumer) {
        return (FloatSettingBuilder)super.withListener(consumer);
    }
    
    @Override
    public FloatSettingBuilder withMaximum(final Float maximum) {
        return (FloatSettingBuilder)super.withMaximum(maximum);
    }
    
    @Override
    public FloatSettingBuilder withRange(final Float minimum, final Float maximum) {
        return (FloatSettingBuilder)super.withRange(minimum, maximum);
    }
    
    @Override
    public FloatSettingBuilder withConsumer(final BiConsumer<Float, Float> consumer) {
        return (FloatSettingBuilder)super.withConsumer(consumer);
    }
    
    @Override
    public FloatSettingBuilder withValue(final Float value) {
        return (FloatSettingBuilder)super.withValue(value);
    }
    
    @Override
    public FloatSettingBuilder withVisibility(final Predicate<Float> predicate) {
        return (FloatSettingBuilder)super.withVisibility(predicate);
    }
    
    @Override
    public FloatSettingBuilder withRestriction(final Predicate<Float> predicate) {
        return (FloatSettingBuilder)super.withRestriction(predicate);
    }
}
