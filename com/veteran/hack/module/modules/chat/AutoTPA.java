//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.chat;

import com.veteran.hack.module.*;
import com.veteran.hack.event.events.*;
import me.zero.alpine.listener.*;
import com.veteran.hack.setting.*;
import java.util.function.*;
import net.minecraft.network.play.server.*;
import com.veteran.hack.util.*;

@Module.Info(name = "AutoTPA", description = "Automatically decline or accept TPA requests", category = Module.Category.HIDDEN)
public class AutoTPA extends Module
{
    private Setting<mode> mod;
    @EventHandler
    public Listener<PacketEvent.Receive> receiveListener;
    
    public AutoTPA() {
        this.mod = (Setting<mode>)this.register((Setting)Settings.e("Response", mode.DENY));
        this.receiveListener = (Listener<PacketEvent.Receive>)new Listener(event -> {
            final SPacketChat packet;
            if (event.getPacket() instanceof SPacketChat && (packet = (SPacketChat)event.getPacket()).getChatComponent().getUnformattedText().contains(" has requested to teleport to you.")) {
                switch (this.mod.getValue()) {
                    case ACCEPT: {
                        Wrapper.getPlayer().sendChatMessage("/tpaccept");
                        break;
                    }
                    case DENY: {
                        Wrapper.getPlayer().sendChatMessage("/tpdeny");
                        break;
                    }
                }
            }
        }, (Predicate[])new Predicate[0]);
    }
    
    public enum mode
    {
        ACCEPT, 
        DENY;
    }
}
