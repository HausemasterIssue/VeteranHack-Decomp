//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.movement;

import com.veteran.hack.module.*;
import com.veteran.hack.setting.*;
import net.minecraft.util.math.*;
import net.minecraft.client.entity.*;

@Module.Info(name = "Strafe", category = Module.Category.MOVEMENT, description = "2b2t strafe bypass ezpz // found and coded by polymer")
public class DVDStrafe extends Module
{
    private Setting<Boolean> jump;
    int waitCounter;
    int forward;
    
    public DVDStrafe() {
        this.jump = (Setting<Boolean>)this.register((Setting)Settings.b("AutoJump", true));
        this.forward = 1;
    }
    
    public void onUpdate() {
        final boolean boost = Math.abs(DVDStrafe.mc.player.rotationYawHead - DVDStrafe.mc.player.rotationYaw) < 90.0f;
        if (DVDStrafe.mc.player.moveForward != 0.0f) {
            if (!DVDStrafe.mc.player.isSprinting()) {
                DVDStrafe.mc.player.setSprinting(true);
            }
            float yaw = DVDStrafe.mc.player.rotationYaw;
            if (DVDStrafe.mc.player.moveForward > 0.0f) {
                if (DVDStrafe.mc.player.movementInput.moveStrafe != 0.0f) {
                    yaw += ((DVDStrafe.mc.player.movementInput.moveStrafe > 0.0f) ? -45.0f : 45.0f);
                }
                this.forward = 1;
                DVDStrafe.mc.player.moveForward = 1.0f;
                DVDStrafe.mc.player.moveStrafing = 0.0f;
            }
            else if (DVDStrafe.mc.player.moveForward < 0.0f) {
                if (DVDStrafe.mc.player.movementInput.moveStrafe != 0.0f) {
                    yaw += ((DVDStrafe.mc.player.movementInput.moveStrafe > 0.0f) ? 45.0f : -45.0f);
                }
                this.forward = -1;
                DVDStrafe.mc.player.moveForward = -1.0f;
                DVDStrafe.mc.player.moveStrafing = 0.0f;
            }
            if (DVDStrafe.mc.player.onGround) {
                DVDStrafe.mc.player.setJumping(false);
                if (this.waitCounter < 1) {
                    ++this.waitCounter;
                    return;
                }
                this.waitCounter = 0;
                final float f = (float)Math.toRadians(yaw);
                if (this.jump.getValue()) {
                    DVDStrafe.mc.player.motionY = 0.4;
                    final EntityPlayerSP player = DVDStrafe.mc.player;
                    player.motionX -= MathHelper.sin(f) * 0.195f * (double)this.forward;
                    final EntityPlayerSP player2 = DVDStrafe.mc.player;
                    player2.motionZ += MathHelper.cos(f) * 0.195f * (double)this.forward;
                }
                else if (DVDStrafe.mc.gameSettings.keyBindJump.isPressed()) {
                    DVDStrafe.mc.player.motionY = 0.4;
                    final EntityPlayerSP player3 = DVDStrafe.mc.player;
                    player3.motionX -= MathHelper.sin(f) * 0.195f * (double)this.forward;
                    final EntityPlayerSP player4 = DVDStrafe.mc.player;
                    player4.motionZ += MathHelper.cos(f) * 0.195f * (double)this.forward;
                }
            }
            else {
                if (this.waitCounter < 1) {
                    ++this.waitCounter;
                    return;
                }
                this.waitCounter = 0;
                final double currentSpeed = Math.sqrt(DVDStrafe.mc.player.motionX * DVDStrafe.mc.player.motionX + DVDStrafe.mc.player.motionZ * DVDStrafe.mc.player.motionZ);
                double speed = boost ? 1.0034 : 1.001;
                if (DVDStrafe.mc.player.motionY < 0.0) {
                    speed = 1.0;
                }
                final double direction = Math.toRadians(yaw);
                DVDStrafe.mc.player.motionX = -Math.sin(direction) * speed * currentSpeed * this.forward;
                DVDStrafe.mc.player.motionZ = Math.cos(direction) * speed * currentSpeed * this.forward;
            }
        }
    }
}
