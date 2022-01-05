//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.gui;

import com.veteran.hack.module.*;
import com.veteran.hack.setting.*;
import com.veteran.hack.command.*;

@Module.Info(name = "PrefixChat", category = Module.Category.GUI, description = "Opens chat with prefix inside when prefix is pressed.", showOnArray = Module.ShowOnArray.OFF)
public class PrefixChat extends Module
{
    public Setting<Boolean> startupGlobal;
    
    public PrefixChat() {
        this.startupGlobal = (Setting<Boolean>)this.register((Setting)Settings.b("Enable Automatically", true));
    }
    
    public void onDisable() {
        Command.sendAutoDisableMessage(this.getName(), (boolean)this.startupGlobal.getValue());
    }
}
