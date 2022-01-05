//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules;

import com.veteran.hack.module.*;
import com.veteran.hack.gui.kami.*;
import net.minecraft.client.gui.*;

@Module.Info(name = "clickGUI", description = "Opens the Click GUI", category = Module.Category.HIDDEN)
public class ClickGUI extends Module
{
    public ClickGUI() {
        this.getBind().setKey(21);
    }
    
    protected void onEnable() {
        if (!(ClickGUI.mc.currentScreen instanceof DisplayGuiScreen)) {
            ClickGUI.mc.displayGuiScreen((GuiScreen)new DisplayGuiScreen(ClickGUI.mc.currentScreen));
        }
        this.disable();
    }
}
