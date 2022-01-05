//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.player;

import com.veteran.hack.module.*;
import net.minecraft.entity.*;
import me.zero.alpine.listener.*;
import net.minecraftforge.client.event.*;
import com.veteran.hack.event.events.*;
import com.veteran.hack.setting.*;
import java.util.function.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.entity.*;
import net.minecraft.network.play.client.*;

@Module.Info(name = "Freecam", category = Module.Category.PLAYER, description = "Leave your body and trascend into the realm of the gods")
public class Freecam extends Module
{
    private Setting<Integer> speed;
    private double posX;
    private double posY;
    private double posZ;
    private float pitch;
    private float yaw;
    private EntityOtherPlayerMP clonedPlayer;
    private boolean isRidingEntity;
    private Entity ridingEntity;
    @EventHandler
    private Listener<PlayerMoveEvent> moveListener;
    @EventHandler
    private Listener<PlayerSPPushOutOfBlocksEvent> pushListener;
    @EventHandler
    private Listener<PacketEvent.Send> sendListener;
    
    public Freecam() {
        this.speed = (Setting<Integer>)this.register((Setting)Settings.i("Speed", 5));
        this.moveListener = (Listener<PlayerMoveEvent>)new Listener(event -> Freecam.mc.player.noClip = true, new Predicate[0]);
        this.pushListener = (Listener<PlayerSPPushOutOfBlocksEvent>)new Listener(event -> event.setCanceled(true), new Predicate[0]);
        this.sendListener = (Listener<PacketEvent.Send>)new Listener(event -> {
            if (event.getPacket() instanceof CPacketPlayer || event.getPacket() instanceof CPacketInput) {
                event.cancel();
            }
        }, new Predicate[0]);
    }
    
    protected void onEnable() {
        if (Freecam.mc.player != null) {
            this.isRidingEntity = (Freecam.mc.player.getRidingEntity() != null);
            if (Freecam.mc.player.getRidingEntity() == null) {
                this.posX = Freecam.mc.player.posX;
                this.posY = Freecam.mc.player.posY;
                this.posZ = Freecam.mc.player.posZ;
            }
            else {
                this.ridingEntity = Freecam.mc.player.getRidingEntity();
                Freecam.mc.player.dismountRidingEntity();
            }
            this.pitch = Freecam.mc.player.rotationPitch;
            this.yaw = Freecam.mc.player.rotationYaw;
            (this.clonedPlayer = new EntityOtherPlayerMP((World)Freecam.mc.world, Freecam.mc.getSession().getProfile())).copyLocationAndAnglesFrom((Entity)Freecam.mc.player);
            this.clonedPlayer.rotationYawHead = Freecam.mc.player.rotationYawHead;
            Freecam.mc.world.addEntityToWorld(-100, (Entity)this.clonedPlayer);
            Freecam.mc.player.capabilities.isFlying = true;
            Freecam.mc.player.capabilities.setFlySpeed(this.speed.getValue() / 100.0f);
            Freecam.mc.player.noClip = true;
            Freecam.mc.renderChunksMany = false;
            Freecam.mc.renderGlobal.loadRenderers();
        }
    }
    
    protected void onDisable() {
        final EntityPlayer localPlayer = (EntityPlayer)Freecam.mc.player;
        if (localPlayer != null) {
            Freecam.mc.player.setPositionAndRotation(this.posX, this.posY, this.posZ, this.yaw, this.pitch);
            Freecam.mc.world.removeEntityFromWorld(-100);
            this.clonedPlayer = null;
            final double posX = 0.0;
            this.posZ = posX;
            this.posY = posX;
            this.posX = posX;
            final float n = 0.0f;
            this.yaw = n;
            this.pitch = n;
            Freecam.mc.player.capabilities.isFlying = false;
            Freecam.mc.player.capabilities.setFlySpeed(0.05f);
            Freecam.mc.player.noClip = false;
            final EntityPlayerSP player = Freecam.mc.player;
            final EntityPlayerSP player2 = Freecam.mc.player;
            final EntityPlayerSP player3 = Freecam.mc.player;
            final double motionX = 0.0;
            player3.motionZ = motionX;
            player2.motionY = motionX;
            player.motionX = motionX;
            if (this.isRidingEntity) {
                Freecam.mc.player.startRiding(this.ridingEntity, true);
            }
            Freecam.mc.renderChunksMany = true;
            Freecam.mc.renderGlobal.loadRenderers();
        }
    }
    
    public void onUpdate() {
        Freecam.mc.player.capabilities.isFlying = true;
        Freecam.mc.player.capabilities.setFlySpeed(this.speed.getValue() / 100.0f);
        Freecam.mc.player.noClip = true;
        Freecam.mc.player.onGround = false;
        Freecam.mc.player.fallDistance = 0.0f;
    }
}
