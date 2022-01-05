//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.combat;

import com.veteran.hack.module.*;
import com.veteran.hack.setting.*;
import net.minecraft.util.math.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import net.minecraft.item.*;

@Module.Info(category = Module.Category.COMBAT, description = "Use items faster", name = "FastUse")
public class Fastuse extends Module
{
    private Setting<Integer> delay;
    private Setting<Boolean> all;
    private Setting<Boolean> bow;
    private Setting<Boolean> expBottles;
    private Setting<Boolean> endCrystals;
    private Setting<Boolean> fireworks;
    private static long time;
    
    public Fastuse() {
        this.delay = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Delay").withMinimum(0).withMaximum(20).withValue(0).build());
        this.all = (Setting<Boolean>)this.register((Setting)Settings.b("All", false));
        this.bow = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder().withName("Bow").withValue(true).withVisibility(v -> !this.all.getValue()).build());
        this.expBottles = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder().withName("Exp Bottles").withValue(true).withVisibility(v -> !this.all.getValue()).build());
        this.endCrystals = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder().withName("End Crystals").withValue(true).withVisibility(v -> !this.all.getValue()).build());
        this.fireworks = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder().withName("Fireworks").withValue(false).withVisibility(v -> !this.all.getValue()).build());
    }
    
    public void onDisable() {
        Fastuse.mc.rightClickDelayTimer = 4;
    }
    
    public void onUpdate() {
        if (Fastuse.mc.player == null) {
            return;
        }
        if (this.all.getValue() || (this.bow.getValue() && Fastuse.mc.player.getHeldItemMainhand().getItem() instanceof ItemBow && Fastuse.mc.player.isHandActive() && Fastuse.mc.player.getItemInUseMaxCount() >= 3)) {
            Fastuse.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, Fastuse.mc.player.getHorizontalFacing()));
            Fastuse.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(Fastuse.mc.player.getActiveHand()));
            Fastuse.mc.player.stopActiveHand();
        }
        if (this.delay.getValue() > 0) {
            if (Fastuse.time > 0L) {
                --Fastuse.time;
                Fastuse.mc.rightClickDelayTimer = 1;
                return;
            }
            Fastuse.time = Math.round((float)(2 * Math.round(this.delay.getValue() / 2.0f)));
        }
        if (this.passItemCheck(Fastuse.mc.player.getHeldItemMainhand().getItem()) || this.passItemCheck(Fastuse.mc.player.getHeldItemOffhand().getItem())) {
            Fastuse.mc.rightClickDelayTimer = 0;
        }
    }
    
    private boolean passItemCheck(final Item item) {
        return this.all.getValue() || (this.expBottles.getValue() && item instanceof ItemExpBottle) || (this.endCrystals.getValue() && item instanceof ItemEndCrystal) || (this.fireworks.getValue() && item instanceof ItemFirework);
    }
    
    static {
        Fastuse.time = 0L;
    }
}
