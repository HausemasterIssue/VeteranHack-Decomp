//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.player;

import com.veteran.hack.module.*;
import com.veteran.hack.event.events.*;
import me.zero.alpine.listener.*;
import java.util.function.*;
import net.minecraft.network.play.client.*;

@Module.Info(name = "NoSwing", category = Module.Category.HIDDEN, description = "Cancels server and client swinging packets")
public class NoSwing extends Module
{
    @EventHandler
    private Listener<PacketEvent.Send> listener;
    
    public NoSwing() {
        this.listener = (Listener<PacketEvent.Send>)new Listener(event -> {
            if (event.getPacket() instanceof CPacketAnimation) {
                event.cancel();
            }
        }, new Predicate[0]);
    }
}
