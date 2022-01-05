//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.gui;

import com.veteran.hack.module.*;
import com.veteran.hack.setting.*;
import com.veteran.hack.command.*;

@Module.Info(name = "CleanGUI", category = Module.Category.GUI, showOnArray = Module.ShowOnArray.OFF, description = "Modifies parts of the GUI to be transparent")
public class CleanGUI extends Module
{
    public Setting<Boolean> startupGlobal;
    public Setting<Boolean> inventoryGlobal;
    public static Setting<Boolean> chatGlobal;
    private static CleanGUI INSTANCE;
    
    public CleanGUI() {
        this.startupGlobal = (Setting<Boolean>)this.register((Setting)Settings.b("Enable Automatically", true));
        this.inventoryGlobal = (Setting<Boolean>)this.register((Setting)Settings.b("Inventory", false));
        (CleanGUI.INSTANCE = this).register((Setting)CleanGUI.chatGlobal);
    }
    
    public static boolean enabled() {
        return CleanGUI.INSTANCE.isEnabled();
    }
    
    public void onDisable() {
        Command.sendAutoDisableMessage(this.getName(), (boolean)this.startupGlobal.getValue());
    }
    
    static {
        CleanGUI.chatGlobal = Settings.b("Chat", true);
        CleanGUI.INSTANCE = new CleanGUI();
    }
}
