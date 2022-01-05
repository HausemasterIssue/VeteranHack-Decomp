//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.setting;

import com.veteran.hack.setting.converter.*;
import java.util.function.*;
import com.google.gson.*;

public abstract class Setting<T> implements ISettingUnknown, Convertable<T>
{
    String name;
    T value;
    private Predicate<T> restriction;
    private Predicate<T> visibilityPredicate;
    private BiConsumer<T, T> consumer;
    private final Class valueType;
    
    public Setting(final T value, final Predicate<T> restriction, final BiConsumer<T, T> consumer, final String name, final Predicate<T> visibilityPredicate) {
        this.value = value;
        this.valueType = value.getClass();
        this.restriction = restriction;
        this.consumer = consumer;
        this.name = name;
        this.visibilityPredicate = visibilityPredicate;
    }
    
    public String getName() {
        return this.name;
    }
    
    public T getValue() {
        return this.value;
    }
    
    public Class getValueClass() {
        return this.valueType;
    }
    
    public boolean setValue(final T value) {
        final T old = this.getValue();
        if (!this.restriction.test(value)) {
            return false;
        }
        this.value = value;
        this.consumer.accept(old, value);
        return true;
    }
    
    public boolean isVisible() {
        return this.visibilityPredicate.test(this.getValue());
    }
    
    public BiConsumer<T, T> changeListener() {
        return this.consumer;
    }
    
    public void setValueFromString(final String value) {
        final JsonParser jp = new JsonParser();
        this.setValue(this.converter().reverse().convert((Object)jp.parse(value)));
    }
    
    public String getValueAsString() {
        return ((JsonElement)this.converter().convert(this.getValue())).toString();
    }
}
