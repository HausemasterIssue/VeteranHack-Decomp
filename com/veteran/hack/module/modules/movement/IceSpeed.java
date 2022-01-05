//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.movement;

import com.veteran.hack.module.*;
import com.veteran.hack.setting.*;
import net.minecraft.init.*;

@Module.Info(name = "IceSpeed", description = "Changes how slippery ice is", category = Module.Category.HIDDEN)
public class IceSpeed extends Module
{
    private Setting<Float> slipperiness;
    
    public IceSpeed() {
        this.slipperiness = (Setting<Float>)this.register((Setting)Settings.floatBuilder("Slipperiness").withMinimum(0.2f).withValue(0.4f).withMaximum(1.0f).build());
    }
    
    public void onUpdate() {
        Blocks.ICE.slipperiness = this.slipperiness.getValue();
        Blocks.PACKED_ICE.slipperiness = this.slipperiness.getValue();
        Blocks.FROSTED_ICE.slipperiness = this.slipperiness.getValue();
    }
    
    public void onDisable() {
        Blocks.ICE.slipperiness = 0.98f;
        Blocks.PACKED_ICE.slipperiness = 0.98f;
        Blocks.FROSTED_ICE.slipperiness = 0.98f;
    }
}
