//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.movement;

import com.veteran.hack.module.*;
import com.veteran.hack.event.events.*;
import me.zero.alpine.listener.*;
import java.util.function.*;
import net.minecraft.network.play.client.*;

@Module.Info(name = "AntiHunger", category = Module.Category.HIDDEN, description = "Reduces hunger lost when moving around")
public class AntiHunger extends Module
{
    @EventHandler
    public Listener<PacketEvent.Send> packetListener;
    
    public AntiHunger() {
        this.packetListener = (Listener<PacketEvent.Send>)new Listener(event -> {
            if (event.getPacket() instanceof CPacketPlayer) {
                ((CPacketPlayer)event.getPacket()).onGround = false;
            }
        }, new Predicate[0]);
    }
}
