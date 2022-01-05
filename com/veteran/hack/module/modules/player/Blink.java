//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.player;

import com.veteran.hack.module.*;
import net.minecraft.network.play.client.*;
import com.veteran.hack.event.events.*;
import me.zero.alpine.listener.*;
import net.minecraft.client.entity.*;
import java.util.*;
import java.util.function.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import net.minecraft.entity.player.*;

@Module.Info(name = "Blink", category = Module.Category.HIDDEN, description = "Cancels server side packets")
public class Blink extends Module
{
    Queue<CPacketPlayer> packets;
    @EventHandler
    public Listener<PacketEvent.Send> listener;
    private EntityOtherPlayerMP clonedPlayer;
    
    public Blink() {
        this.packets = new LinkedList<CPacketPlayer>();
        this.listener = (Listener<PacketEvent.Send>)new Listener(event -> {
            if (this.isEnabled() && event.getPacket() instanceof CPacketPlayer) {
                event.cancel();
                this.packets.add((CPacketPlayer)event.getPacket());
            }
        }, new Predicate[0]);
    }
    
    protected void onEnable() {
        if (Blink.mc.player != null) {
            (this.clonedPlayer = new EntityOtherPlayerMP((World)Blink.mc.world, Blink.mc.getSession().getProfile())).copyLocationAndAnglesFrom((Entity)Blink.mc.player);
            this.clonedPlayer.rotationYawHead = Blink.mc.player.rotationYawHead;
            Blink.mc.world.addEntityToWorld(-100, (Entity)this.clonedPlayer);
        }
    }
    
    protected void onDisable() {
        while (!this.packets.isEmpty()) {
            Blink.mc.player.connection.sendPacket((Packet)this.packets.poll());
        }
        final EntityPlayer localPlayer = (EntityPlayer)Blink.mc.player;
        if (localPlayer != null) {
            Blink.mc.world.removeEntityFromWorld(-100);
            this.clonedPlayer = null;
        }
    }
    
    public String getHudInfo() {
        return String.valueOf(this.packets.size());
    }
}
