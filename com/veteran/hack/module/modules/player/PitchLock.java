//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.player;

import com.veteran.hack.module.*;
import com.veteran.hack.setting.*;
import net.minecraft.util.math.*;

@Module.Info(name = "PitchLock", category = Module.Category.HIDDEN, description = "Locks your camera pitch")
public class PitchLock extends Module
{
    private Setting<Boolean> auto;
    private Setting<Float> pitch;
    private Setting<Integer> slice;
    
    public PitchLock() {
        this.auto = (Setting<Boolean>)this.register((Setting)Settings.b("Auto", true));
        this.pitch = (Setting<Float>)this.register((Setting)Settings.f("Pitch", 180.0f));
        this.slice = (Setting<Integer>)this.register((Setting)Settings.i("Slice", 8));
    }
    
    public void onUpdate() {
        if (this.slice.getValue() == 0) {
            return;
        }
        if (this.auto.getValue()) {
            final int angle = 360 / this.slice.getValue();
            float yaw = PitchLock.mc.player.rotationPitch;
            yaw = (float)(Math.round(yaw / angle) * angle);
            PitchLock.mc.player.rotationPitch = yaw;
            if (PitchLock.mc.player.isRiding()) {
                PitchLock.mc.player.getRidingEntity().rotationPitch = yaw;
            }
        }
        else {
            PitchLock.mc.player.rotationPitch = MathHelper.clamp(this.pitch.getValue() - 180.0f, -180.0f, 180.0f);
        }
    }
}
