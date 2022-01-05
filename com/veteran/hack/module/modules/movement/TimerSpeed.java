//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.movement;

import com.veteran.hack.module.*;
import com.veteran.hack.setting.*;
import com.veteran.hack.util.*;

@Module.Info(name = "TimerSpeed", description = "Automatically change your timer to go fast", category = Module.Category.HIDDEN)
public class TimerSpeed extends Module
{
    private float tickDelay;
    private static float curSpeed;
    private Setting<Float> minimumSpeed;
    private Setting<Float> maxSpeed;
    private Setting<Float> attemptSpeed;
    private Setting<Float> fastSpeed;
    
    public TimerSpeed() {
        this.tickDelay = 0.0f;
        this.minimumSpeed = (Setting<Float>)this.register((Setting)Settings.floatBuilder("Minimum Speed").withMinimum(0.1f).withMaximum(10.0f).withValue(4.0f).build());
        this.maxSpeed = (Setting<Float>)this.register((Setting)Settings.floatBuilder("Max Speed").withMinimum(0.1f).withMaximum(10.0f).withValue(7.0f).build());
        this.attemptSpeed = (Setting<Float>)this.register((Setting)Settings.floatBuilder("Attempt Speed").withMinimum(1.0f).withMaximum(10.0f).withValue(4.2f).build());
        this.fastSpeed = (Setting<Float>)this.register((Setting)Settings.floatBuilder("Fast Speed").withMinimum(1.0f).withMaximum(10.0f).withValue(5.0f).build());
    }
    
    public static String returnGui() {
        return "" + InfoCalculator.round(TimerSpeed.curSpeed, 2);
    }
    
    public void onUpdate() {
        if (this.tickDelay == this.minimumSpeed.getValue()) {
            TimerSpeed.curSpeed = this.fastSpeed.getValue();
            TimerSpeed.mc.timer.tickLength = 50.0f / this.fastSpeed.getValue();
        }
        if (this.tickDelay >= this.maxSpeed.getValue()) {
            this.tickDelay = 0.0f;
            TimerSpeed.curSpeed = this.attemptSpeed.getValue();
            TimerSpeed.mc.timer.tickLength = 50.0f / this.attemptSpeed.getValue();
        }
        ++this.tickDelay;
    }
    
    public void onDisable() {
        TimerSpeed.mc.timer.tickLength = 50.0f;
    }
    
    static {
        TimerSpeed.curSpeed = 0.0f;
    }
}
