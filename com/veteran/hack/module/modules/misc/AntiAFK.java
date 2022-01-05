//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.misc;

import com.veteran.hack.module.*;
import java.util.*;
import com.veteran.hack.setting.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

@Module.Info(name = "AntiAFK", category = Module.Category.MISC, description = "Prevents being kicked for AFK")
public class AntiAFK extends Module
{
    private Setting<Boolean> swing;
    private Setting<Boolean> turn;
    private Random random;
    
    public AntiAFK() {
        this.swing = (Setting<Boolean>)this.register((Setting)Settings.b("Swing", true));
        this.turn = (Setting<Boolean>)this.register((Setting)Settings.b("Turn", true));
        this.random = new Random();
    }
    
    public void onUpdate() {
        if (AntiAFK.mc.playerController.getIsHittingBlock()) {
            return;
        }
        if (AntiAFK.mc.player.ticksExisted % 40 == 0 && this.swing.getValue()) {
            AntiAFK.mc.getConnection().sendPacket((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
        }
        if (AntiAFK.mc.player.ticksExisted % 15 == 0 && this.turn.getValue()) {
            AntiAFK.mc.player.rotationYaw = (float)(this.random.nextInt(360) - 180);
        }
        if (!this.swing.getValue() && !this.turn.getValue() && AntiAFK.mc.player.ticksExisted % 80 == 0) {
            AntiAFK.mc.player.jump();
        }
    }
}
