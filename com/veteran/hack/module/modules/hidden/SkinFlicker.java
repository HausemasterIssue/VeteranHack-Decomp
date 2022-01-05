//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.hidden;

import com.veteran.hack.module.*;
import net.minecraft.entity.player.*;
import java.util.*;
import com.veteran.hack.setting.*;

@Module.Info(name = "SkinFlicker", description = "Toggle your skin layers rapidly for a cool skin effect", category = Module.Category.HIDDEN)
public class SkinFlicker extends Module
{
    private Setting<FlickerMode> mode;
    private Setting<Integer> slowness;
    private static final EnumPlayerModelParts[] PARTS_HORIZONTAL;
    private static final EnumPlayerModelParts[] PARTS_VERTICAL;
    private Random r;
    private int len;
    
    public SkinFlicker() {
        this.mode = (Setting<FlickerMode>)this.register((Setting)Settings.e("Mode", FlickerMode.HORIZONTAL));
        this.slowness = (Setting<Integer>)this.register((Setting)Settings.integerBuilder().withName("Slowness").withValue(2).withMinimum(1).build());
        this.r = new Random();
        this.len = EnumPlayerModelParts.values().length;
    }
    
    public void onUpdate() {
        switch (this.mode.getValue()) {
            case RANDOM: {
                if (SkinFlicker.mc.player.ticksExisted % this.slowness.getValue() != 0) {
                    return;
                }
                SkinFlicker.mc.gameSettings.switchModelPartEnabled(EnumPlayerModelParts.values()[this.r.nextInt(this.len)]);
                break;
            }
            case VERTICAL:
            case HORIZONTAL: {
                int i = SkinFlicker.mc.player.ticksExisted / this.slowness.getValue() % (SkinFlicker.PARTS_HORIZONTAL.length * 2);
                boolean on = false;
                if (i >= SkinFlicker.PARTS_HORIZONTAL.length) {
                    on = true;
                    i -= SkinFlicker.PARTS_HORIZONTAL.length;
                }
                SkinFlicker.mc.gameSettings.setModelPartEnabled((this.mode.getValue() == FlickerMode.VERTICAL) ? SkinFlicker.PARTS_VERTICAL[i] : SkinFlicker.PARTS_HORIZONTAL[i], on);
                break;
            }
        }
    }
    
    static {
        PARTS_HORIZONTAL = new EnumPlayerModelParts[] { EnumPlayerModelParts.LEFT_SLEEVE, EnumPlayerModelParts.JACKET, EnumPlayerModelParts.HAT, EnumPlayerModelParts.LEFT_PANTS_LEG, EnumPlayerModelParts.RIGHT_PANTS_LEG, EnumPlayerModelParts.RIGHT_SLEEVE };
        PARTS_VERTICAL = new EnumPlayerModelParts[] { EnumPlayerModelParts.HAT, EnumPlayerModelParts.JACKET, EnumPlayerModelParts.LEFT_SLEEVE, EnumPlayerModelParts.RIGHT_SLEEVE, EnumPlayerModelParts.LEFT_PANTS_LEG, EnumPlayerModelParts.RIGHT_PANTS_LEG };
    }
    
    public enum FlickerMode
    {
        HORIZONTAL, 
        VERTICAL, 
        RANDOM;
    }
}
