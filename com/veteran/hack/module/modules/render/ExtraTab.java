//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.render;

import com.veteran.hack.module.*;
import com.veteran.hack.setting.*;

@Module.Info(name = "ExtraTab", description = "Expands the player tab menu", category = Module.Category.RENDER)
public class ExtraTab extends Module
{
    public Setting<Integer> tabSize;
    public static ExtraTab INSTANCE;
    
    public ExtraTab() {
        this.tabSize = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Players").withMinimum(1).withValue(80).build());
        ExtraTab.INSTANCE = this;
    }
}
