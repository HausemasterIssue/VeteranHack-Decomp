//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.setting.builder.primitive;

import com.veteran.hack.setting.builder.*;
import com.veteran.hack.setting.impl.*;
import com.veteran.hack.setting.*;

public class StringSettingBuilder extends SettingBuilder<String>
{
    @Override
    public StringSetting build() {
        return new StringSetting((String)this.initialValue, this.predicate(), this.consumer(), this.name, this.visibilityPredicate());
    }
}
