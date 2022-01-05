//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.player;

import com.veteran.hack.module.*;
import com.veteran.hack.event.events.*;
import me.zero.alpine.listener.*;
import java.util.function.*;
import net.minecraft.network.play.client.*;

@Module.Info(name = "PortalGodMode", category = Module.Category.PLAYER, description = "Don't take damage in portals")
public class PortalGodMode extends Module
{
    @EventHandler
    public Listener<PacketEvent.Send> listener;
    
    public PortalGodMode() {
        this.listener = (Listener<PacketEvent.Send>)new Listener(event -> {
            if (this.isEnabled() && event.getPacket() instanceof CPacketConfirmTeleport) {
                event.cancel();
            }
        }, new Predicate[0]);
    }
}
