//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.player;

import com.veteran.hack.module.*;

@Module.Info(name = "Fastbreak", category = Module.Category.HIDDEN, description = "Nullifies block hit delay")
public class Fastbreak extends Module
{
    public void onUpdate() {
        Fastbreak.mc.playerController.blockHitDelay = 0;
    }
}
