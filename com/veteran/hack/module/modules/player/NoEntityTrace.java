//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.player;

import com.veteran.hack.module.*;
import com.veteran.hack.setting.*;

@Module.Info(name = "NoEntityTrace", category = Module.Category.PLAYER, description = "Blocks entities from stopping you from mining")
public class NoEntityTrace extends Module
{
    private Setting<TraceMode> mode;
    private static NoEntityTrace INSTANCE;
    
    public NoEntityTrace() {
        this.mode = (Setting<TraceMode>)this.register((Setting)Settings.e("Mode", TraceMode.DYNAMIC));
        NoEntityTrace.INSTANCE = this;
    }
    
    public static boolean shouldBlock() {
        return NoEntityTrace.INSTANCE.isEnabled() && (NoEntityTrace.INSTANCE.mode.getValue() == TraceMode.STATIC || NoEntityTrace.mc.playerController.isHittingBlock);
    }
    
    private enum TraceMode
    {
        STATIC, 
        DYNAMIC;
    }
}
