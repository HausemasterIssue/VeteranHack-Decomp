//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.hidden;

import com.veteran.hack.module.*;
import com.veteran.hack.event.events.*;
import me.zero.alpine.listener.*;
import com.veteran.hack.setting.*;
import java.util.function.*;
import net.minecraft.network.play.client.*;
import io.netty.buffer.*;
import com.veteran.hack.util.*;
import net.minecraft.network.*;

@Module.Info(name = "BetterBeacons", category = Module.Category.HIDDEN, description = "Choose any of the 5 beacon effects regardless of beacon base height")
public class BetterBeacons extends Module
{
    private Setting<Effects> effects;
    private boolean doCancelPacket;
    @EventHandler
    public Listener<PacketEvent.Send> packetListener;
    
    public BetterBeacons() {
        this.effects = (Setting<Effects>)this.register((Setting)Settings.e("Effect", Effects.SPEED));
        this.doCancelPacket = true;
        this.packetListener = (Listener<PacketEvent.Send>)new Listener(event -> {
            if (event.getPacket() instanceof CPacketCustomPayload && ((CPacketCustomPayload)event.getPacket()).getChannelName().equals("MC|Beacon") && this.doCancelPacket) {
                this.doCancelPacket = false;
                final PacketBuffer data = ((CPacketCustomPayload)event.getPacket()).getBufferData();
                final int i1 = data.readInt();
                final int k1 = data.readInt();
                event.cancel();
                final PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
                buf.writeInt(this.getPotionID());
                buf.writeInt(k1);
                Wrapper.getPlayer().connection.sendPacket((Packet)new CPacketCustomPayload("MC|Beacon", buf));
                this.doCancelPacket = true;
            }
        }, new Predicate[0]);
    }
    
    private int getPotionID() {
        switch (this.effects.getValue()) {
            case SPEED: {
                return 1;
            }
            case HASTE: {
                return 3;
            }
            case RESISTANCE: {
                return 11;
            }
            case JUMP_BOOST: {
                return 8;
            }
            case STRENGTH: {
                return 5;
            }
            default: {
                return -1;
            }
        }
    }
    
    private enum Effects
    {
        SPEED, 
        HASTE, 
        RESISTANCE, 
        JUMP_BOOST, 
        STRENGTH;
    }
}
