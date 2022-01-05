//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.misc;

import com.veteran.hack.module.*;

@Module.Info(name = "NoPacketKick", category = Module.Category.MISC, description = "Prevent large packets from kicking you")
public class NoPacketKick
{
    private static NoPacketKick INSTANCE;
    
    public NoPacketKick() {
        NoPacketKick.INSTANCE = this;
    }
    
    public static boolean isEnabled() {
        final NoPacketKick instance = NoPacketKick.INSTANCE;
        return isEnabled();
    }
}
