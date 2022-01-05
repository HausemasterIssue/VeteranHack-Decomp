//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.hidden;

import com.veteran.hack.module.*;
import com.veteran.hack.setting.*;
import com.veteran.hack.util.*;

@Module.Info(name = "Hidden:FixGui", category = Module.Category.HIDDEN, showOnArray = Module.ShowOnArray.OFF, description = "Moves GUI elements back on screen")
public class FixGui extends Module
{
    public Setting<Boolean> shouldAutoEnable;
    
    public FixGui() {
        this.shouldAutoEnable = (Setting<Boolean>)this.register((Setting)Settings.b("Enable", true));
    }
    
    public void onUpdate() {
        GuiFrameUtil.fixFrames(FixGui.mc);
    }
}
