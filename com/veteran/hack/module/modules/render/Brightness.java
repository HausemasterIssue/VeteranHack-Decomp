//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.render;

import com.veteran.hack.module.*;
import java.util.*;
import com.veteran.hack.setting.*;
import java.util.function.*;

@Module.Info(name = "FullBright", description = "Makes everything brighter!", category = Module.Category.RENDER)
public class Brightness extends Module
{
    private Setting<Boolean> transition;
    private Setting<Float> seconds;
    private Setting<Transition> mode;
    private Stack<Float> transitionStack;
    private static float currentBrightness;
    private static boolean inTransition;
    
    public Brightness() {
        this.transition = (Setting<Boolean>)this.register((Setting)Settings.b("Transition", true));
        this.seconds = (Setting<Float>)this.register((Setting)Settings.floatBuilder("Seconds").withMinimum(0.0f).withMaximum(10.0f).withValue(5.0f).withVisibility(o -> this.transition.getValue()).build());
        this.mode = (Setting<Transition>)this.register((Setting)Settings.enumBuilder(Transition.class).withName("Mode").withValue(Transition.SINE).withVisibility(o -> this.transition.getValue()).build());
        this.transitionStack = new Stack<Float>();
    }
    
    private void addTransition(final boolean isUpwards) {
        if (this.transition.getValue()) {
            final int length = (int)(this.seconds.getValue() * 20.0f);
            float[] values = null;
            switch (this.mode.getValue()) {
                case LINEAR: {
                    values = this.linear(length, isUpwards);
                    break;
                }
                case SINE: {
                    values = this.sine(length, isUpwards);
                    break;
                }
                default: {
                    values = new float[] { 0.0f };
                    break;
                }
            }
            for (final float v : values) {
                this.transitionStack.add(v);
            }
            Brightness.inTransition = true;
        }
    }
    
    protected void onEnable() {
        super.onEnable();
        this.addTransition(true);
    }
    
    protected void onDisable() {
        this.setAlwaysListening(true);
        super.onDisable();
        this.addTransition(false);
    }
    
    public void onUpdate() {
        if (Brightness.inTransition) {
            if (this.transitionStack.isEmpty()) {
                this.setAlwaysListening(Brightness.inTransition = false);
                Brightness.currentBrightness = (this.isEnabled() ? 1.0f : 0.0f);
            }
            else {
                Brightness.currentBrightness = this.transitionStack.pop();
            }
        }
    }
    
    private float[] createTransition(final int length, final boolean upwards, final Function<Float, Float> function) {
        final float[] transition = new float[length];
        for (int i = 0; i < length; ++i) {
            float v = function.apply(i / (float)length);
            if (upwards) {
                v = 1.0f - v;
            }
            transition[i] = v;
        }
        return transition;
    }
    
    private float[] linear(final int length, final boolean polarity) {
        return this.createTransition(length, polarity, d -> d);
    }
    
    private float sine(final float x) {
        return ((float)Math.sin(3.141592653589793 * x - 1.5707963267948966) + 1.0f) / 2.0f;
    }
    
    private float[] sine(final int length, final boolean polarity) {
        return this.createTransition(length, polarity, this::sine);
    }
    
    public static float getCurrentBrightness() {
        return Brightness.currentBrightness;
    }
    
    public static boolean isInTransition() {
        return Brightness.inTransition;
    }
    
    public static boolean shouldBeActive() {
        return isInTransition() || Brightness.currentBrightness == 1.0f;
    }
    
    static {
        Brightness.currentBrightness = 0.0f;
        Brightness.inTransition = false;
    }
    
    public enum Transition
    {
        LINEAR, 
        SINE;
    }
}
