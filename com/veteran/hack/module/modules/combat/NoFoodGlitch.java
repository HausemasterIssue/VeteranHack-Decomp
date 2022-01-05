//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.combat;

import com.veteran.hack.module.*;
import com.veteran.hack.event.events.*;
import me.zero.alpine.listener.*;
import com.veteran.hack.setting.*;
import java.util.function.*;
import com.veteran.hack.util.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;

@Module.Info(name = "BetterGapple", description = "Attempts to stop gapple disease and makes eating easier in low tps", category = Module.Category.COMBAT)
public class NoFoodGlitch extends Module
{
    boolean timer;
    boolean cancelUseItem;
    double waitTicks;
    int lastSlot;
    private Setting<Boolean> fastEat;
    private Setting<Boolean> tpsCheck;
    private Setting<Double> startTPS;
    private Setting<Boolean> noGapGlitch;
    private Setting<Double> wait;
    @EventHandler
    private Listener<PacketEvent.Send> sendListener;
    
    public NoFoodGlitch() {
        this.timer = false;
        this.cancelUseItem = false;
        this.fastEat = (Setting<Boolean>)this.register((Setting)Settings.b("Fast Eat", true));
        this.tpsCheck = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("TPS Check").withValue(true).withVisibility(v -> this.fastEat.getValue().equals(true)).build());
        this.startTPS = (Setting<Double>)this.register((Setting)Settings.doubleBuilder("Enable TPS").withMinimum(0.0).withValue(13.0).withMaximum(20.0).withVisibility(v -> this.fastEat.getValue().equals(true)).build());
        this.noGapGlitch = (Setting<Boolean>)this.register((Setting)Settings.b("Anti Gap Disease", false));
        this.wait = (Setting<Double>)this.register((Setting)Settings.doubleBuilder("Switch Wait").withMinimum(0.0).withValue(5.0).withMaximum(20.0).withVisibility(v -> this.noGapGlitch.getValue().equals(true)).build());
        this.sendListener = (Listener<PacketEvent.Send>)new Listener(e -> {
            if (e.getPacket() instanceof CPacketPlayerTryUseItem) {
                this.timer = true;
                if (this.cancelUseItem) {
                    e.cancel();
                }
            }
        }, new Predicate[0]);
    }
    
    public void onUpdate() {
        if (!this.timer) {
            this.waitTicks = this.wait.getValue() * 20.0 + 50.0;
        }
        if (this.noGapGlitch.getValue()) {
            --this.waitTicks;
            if (this.waitTicks <= 14.0) {
                this.cancelUseItem = true;
                this.lastSlot = Wrapper.getPlayer().inventory.currentItem;
                NoFoodGlitch.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(6));
                Wrapper.getPlayer().inventory.currentItem = 1;
            }
            if (this.waitTicks <= 4.0) {
                this.timer = false;
                NoFoodGlitch.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.lastSlot));
                Wrapper.getPlayer().inventory.currentItem = this.lastSlot;
            }
        }
    }
}
