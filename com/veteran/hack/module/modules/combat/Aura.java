//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.combat;

import com.veteran.hack.module.*;
import com.veteran.hack.setting.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import com.veteran.hack.util.*;
import java.util.*;
import net.minecraft.util.math.*;

@Module.Info(name = "Aura", category = Module.Category.COMBAT, description = "Hits entities around you")
public class Aura extends Module
{
    private Setting<WaitMode> delayMode;
    private Setting<Boolean> autoSpamDelay;
    private Setting<Double> waitTick;
    private Setting<Boolean> eat;
    private Setting<Boolean> multi;
    private Setting<Boolean> attackPlayers;
    private Setting<Boolean> attackMobs;
    private Setting<Boolean> attackAnimals;
    private Setting<Double> hitRange;
    private Setting<Boolean> ignoreWalls;
    private Setting<Boolean> sync;
    private int waitCounter;
    
    public Aura() {
        this.delayMode = (Setting<WaitMode>)this.register((Setting)Settings.e("Mode", WaitMode.DELAY));
        this.autoSpamDelay = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("Auto Spam Delay").withValue(true).withVisibility(v -> this.delayMode.getValue().equals(WaitMode.SPAM)).build());
        this.waitTick = (Setting<Double>)this.register((Setting)Settings.doubleBuilder("Spam Delay").withMinimum(0.1).withValue(2.0).withMaximum(20.0).withVisibility(v -> !this.autoSpamDelay.getValue() && this.delayMode.getValue().equals(WaitMode.SPAM)).build());
        this.eat = (Setting<Boolean>)this.register((Setting)Settings.b("While Eating", true));
        this.multi = (Setting<Boolean>)this.register((Setting)Settings.b("Multi", true));
        this.attackPlayers = (Setting<Boolean>)this.register((Setting)Settings.b("Players", true));
        this.attackMobs = (Setting<Boolean>)this.register((Setting)Settings.b("Mobs", false));
        this.attackAnimals = (Setting<Boolean>)this.register((Setting)Settings.b("Animals", false));
        this.hitRange = (Setting<Double>)this.register((Setting)Settings.d("Hit Range", 5.5));
        this.ignoreWalls = (Setting<Boolean>)this.register((Setting)Settings.b("Ignore Walls", true));
        this.sync = (Setting<Boolean>)this.register((Setting)Settings.b("TPS Sync", false));
    }
    
    public void onUpdate() {
        if (Aura.mc.player == null || Aura.mc.player.isDead) {
            return;
        }
        final float autoWaitTick = 20.0f - LagCompensator.INSTANCE.getTickRate();
        final boolean canAttack = Aura.mc.player.getCooledAttackStrength(((boolean)this.sync.getValue()) ? (-autoWaitTick) : 0.0f) >= 1.0f;
        if (!this.eat.getValue()) {
            final boolean shield = Aura.mc.player.getHeldItemOffhand().getItem().equals(Items.SHIELD) && Aura.mc.player.getActiveHand() == EnumHand.OFF_HAND;
            if (Aura.mc.player.isHandActive() && !shield) {
                return;
            }
        }
        if (this.delayMode.getValue().equals(WaitMode.DELAY)) {
            if (Aura.mc.player.getCooledAttackStrength(this.getLagComp()) < 1.0f) {
                return;
            }
            if (Aura.mc.player.ticksExisted % 2 != 0) {
                return;
            }
        }
        if (this.autoSpamDelay.getValue()) {
            if (this.delayMode.getValue().equals(WaitMode.SPAM) && autoWaitTick > 0.0f) {
                if (this.sync.getValue()) {
                    if (this.waitCounter < autoWaitTick) {
                        ++this.waitCounter;
                        return;
                    }
                    this.waitCounter = 0;
                }
                else if (!canAttack) {
                    return;
                }
            }
        }
        else if (this.delayMode.getValue().equals(WaitMode.SPAM) && this.waitTick.getValue() > 0.0) {
            if (this.waitCounter < this.waitTick.getValue()) {
                ++this.waitCounter;
                return;
            }
            this.waitCounter = 0;
        }
        for (final Entity target : Aura.mc.world.loadedEntityList) {
            if (!EntityUtil.isLiving(target)) {
                continue;
            }
            if (target == Aura.mc.player) {
                continue;
            }
            if (Aura.mc.player.getDistance(target) > this.hitRange.getValue()) {
                continue;
            }
            if (((EntityLivingBase)target).getHealth() <= 0.0f) {
                continue;
            }
            if (this.delayMode.getValue().equals(WaitMode.DELAY) && ((EntityLivingBase)target).hurtTime != 0) {
                continue;
            }
            if (!this.ignoreWalls.getValue() && !Aura.mc.player.canEntityBeSeen(target) && !this.canEntityFeetBeSeen(target)) {
                continue;
            }
            if (this.attackPlayers.getValue() && target instanceof EntityPlayer && !Friends.isFriend(target.getName())) {
                this.attack(target);
                if (!this.multi.getValue()) {
                    return;
                }
                continue;
            }
            else {
                if (EntityUtil.isPassive(target)) {
                    if (!this.attackAnimals.getValue()) {
                        continue;
                    }
                }
                else if (!EntityUtil.isMobAggressive(target) || !this.attackMobs.getValue()) {
                    continue;
                }
                this.attack(target);
                if (!this.multi.getValue()) {
                    return;
                }
                continue;
            }
        }
    }
    
    private void attack(final Entity e) {
        Aura.mc.playerController.attackEntity((EntityPlayer)Aura.mc.player, e);
        Aura.mc.player.swingArm(EnumHand.MAIN_HAND);
    }
    
    private float getLagComp() {
        if (this.delayMode.getValue().equals(WaitMode.DELAY)) {
            return -(20.0f - LagCompensator.INSTANCE.getTickRate());
        }
        return 0.0f;
    }
    
    private boolean canEntityFeetBeSeen(final Entity entityIn) {
        return Aura.mc.world.rayTraceBlocks(new Vec3d(Aura.mc.player.posX, Aura.mc.player.posY + Aura.mc.player.getEyeHeight(), Aura.mc.player.posZ), new Vec3d(entityIn.posX, entityIn.posY, entityIn.posZ), false, true, false) == null;
    }
    
    public enum HitMode
    {
        SWORD, 
        AXE, 
        NONE;
    }
    
    private enum WaitMode
    {
        DELAY, 
        SPAM;
    }
}
