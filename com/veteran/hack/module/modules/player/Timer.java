//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.player;

import com.veteran.hack.module.*;
import com.veteran.hack.setting.*;

@Module.Info(name = "Timer", category = Module.Category.PLAYER, description = "Changes your client tick speed")
public class Timer extends Module
{
    private Setting<Boolean> slow;
    private Setting<Float> tickNormal;
    private Setting<Float> tickSlow;
    
    public Timer() {
        this.slow = (Setting<Boolean>)this.register((Setting)Settings.b("Slow Mode", false));
        this.tickNormal = (Setting<Float>)this.register((Setting)Settings.floatBuilder("Tick N").withMinimum(1.0f).withMaximum(10.0f).withValue(2.0f).withVisibility(v -> !this.slow.getValue()).build());
        this.tickSlow = (Setting<Float>)this.register((Setting)Settings.floatBuilder("Tick S").withMinimum(1.0f).withMaximum(10.0f).withValue(8.0f).withVisibility(v -> this.slow.getValue()).build());
    }
    
    public void onDisable() {
        Timer.mc.timer.tickLength = 50.0f;
    }
    
    public void onUpdate() {
        if (!this.slow.getValue()) {
            Timer.mc.timer.tickLength = 50.0f / this.tickNormal.getValue();
        }
        else {
            Timer.mc.timer.tickLength = 50.0f / (this.tickSlow.getValue() / 10.0f);
        }
    }
}
