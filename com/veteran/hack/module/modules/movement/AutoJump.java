//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.movement;

import com.veteran.hack.module.*;

@Module.Info(name = "AutoJump", category = Module.Category.HIDDEN, description = "Automatically jumps if possible")
public class AutoJump extends Module
{
    public void onUpdate() {
        if (AutoJump.mc.player.isInWater() || AutoJump.mc.player.isInLava()) {
            AutoJump.mc.player.motionY = 0.1;
        }
        else if (AutoJump.mc.player.onGround) {
            AutoJump.mc.player.jump();
        }
    }
}
