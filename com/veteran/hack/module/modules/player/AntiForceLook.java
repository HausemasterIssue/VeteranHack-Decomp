//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.player;

import com.veteran.hack.module.*;
import com.veteran.hack.event.events.*;
import me.zero.alpine.listener.*;
import java.util.function.*;
import net.minecraft.network.play.server.*;

@Module.Info(name = "AntiForceLook", category = Module.Category.HIDDEN, description = "Stops server packets from turning your head")
public class AntiForceLook extends Module
{
    @EventHandler
    Listener<PacketEvent.Receive> receiveListener;
    
    public AntiForceLook() {
        this.receiveListener = (Listener<PacketEvent.Receive>)new Listener(event -> {
            if (AntiForceLook.mc.player == null) {
                return;
            }
            if (event.getPacket() instanceof SPacketPlayerPosLook) {
                final SPacketPlayerPosLook packet = (SPacketPlayerPosLook)event.getPacket();
                packet.yaw = AntiForceLook.mc.player.rotationYaw;
                packet.pitch = AntiForceLook.mc.player.rotationPitch;
            }
        }, new Predicate[0]);
    }
}
