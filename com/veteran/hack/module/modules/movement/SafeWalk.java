//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.movement;

import com.veteran.hack.module.*;

@Module.Info(name = "SafeWalk", category = Module.Category.HIDDEN, description = "Keeps you from walking off edges")
public class SafeWalk extends Module
{
    private static SafeWalk INSTANCE;
    
    public SafeWalk() {
        SafeWalk.INSTANCE = this;
    }
    
    public static boolean shouldSafewalk() {
        return SafeWalk.INSTANCE.isEnabled();
    }
}
