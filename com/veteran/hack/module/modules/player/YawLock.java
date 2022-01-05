//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.player;

import com.veteran.hack.module.*;
import com.veteran.hack.setting.*;
import net.minecraft.util.math.*;

@Module.Info(name = "YawLock", category = Module.Category.HIDDEN, description = "Locks your camera yaw")
public class YawLock extends Module
{
    private Setting<Boolean> auto;
    private Setting<Float> yaw;
    private Setting<Integer> slice;
    
    public YawLock() {
        this.auto = (Setting<Boolean>)this.register((Setting)Settings.b("Auto", true));
        this.yaw = (Setting<Float>)this.register((Setting)Settings.f("Yaw", 180.0f));
        this.slice = (Setting<Integer>)this.register((Setting)Settings.i("Slice", 8));
    }
    
    public void onUpdate() {
        if (this.slice.getValue() == 0) {
            return;
        }
        if (this.auto.getValue()) {
            final int angle = 360 / this.slice.getValue();
            float yaw = YawLock.mc.player.rotationYaw;
            yaw = (float)(Math.round(yaw / angle) * angle);
            YawLock.mc.player.rotationYaw = yaw;
            if (YawLock.mc.player.isRiding()) {
                YawLock.mc.player.getRidingEntity().rotationYaw = yaw;
            }
        }
        else {
            YawLock.mc.player.rotationYaw = MathHelper.clamp(this.yaw.getValue() - 180.0f, -180.0f, 180.0f);
        }
    }
}
