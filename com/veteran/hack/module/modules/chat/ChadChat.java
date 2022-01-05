//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.chat;

import com.veteran.hack.module.*;
import com.veteran.hack.event.events.*;
import me.zero.alpine.listener.*;
import com.veteran.hack.setting.*;
import java.util.function.*;
import net.minecraft.network.play.client.*;

@Module.Info(name = "ChadChat", category = Module.Category.CHAT, description = "Modifies your chat messages")
public class ChadChat extends Module
{
    private Setting<Boolean> commands;
    private final String KAMI_SUFFIX = " \u1d0b\u1d00\u1d0d\u026a \u0299\u029f\u1d1c\u1d07 \u1d0f\u0274 \u1d1b\u1d0f\u1d18\u23d0 \u0493\u1d1c\u0280\u0280\u028f\u1d21\u1d00\u0280\u1d07 \u23d0 \u1d1b\u0280\u026a\u1d18\u029f\ua731\u02e2\u026a\u02e3 \u23d0 \u0274\u1d1c\u1d1b\u0262\u1d0f\u1d05.\u1d04\u1d04 \u0fc9 \u23d0 PENIS \u23d0 \u1d07\u029f\u1d07\u1d0d\u1d07\u0274\u1d1b\u1d00\ua731.\u1d04\u1d0f\u1d0d \u23d0 \u0262\u1d00\u028f \u23d0 \u0299\u1d00\u029f\u1d05\u029c\u1d00\u1d04\u1d0b \u23d0 027Hack \u23d0 \u1d00\u1d04\u1d07 \u029c\u1d00\u1d04\u1d0b \u2713\u1d00\u1d18\u0150\u029f¥\u028f\u1d0f\u0143.\u0493\u1d00\u0262 »\u0299\u1d00\u1d04\u1d0b\u1d05\u1d0f\u1d0f\u0280\u1d07\u1d05\u23d0 \u1d0b\u1d07\u1d07\u1d0d\u028f.\u1d04\u1d04\u30c4 \u23d0 \u166d\uff4f\u1587\uff0d\u1455\u14aa\uff49\u4e47\u144e\u3112 \u23d0 \u0e23\u0e4f\u0e22\u05e7\u0452\u0e04\u03c2\u043a  \u23d0  \u23d0\u1575\u157c\u15bb.\u1462\u1462";
    @EventHandler
    public Listener<PacketEvent.Send> listener;
    
    public ChadChat() {
        this.commands = (Setting<Boolean>)this.register((Setting)Settings.b("Commands", false));
        this.listener = (Listener<PacketEvent.Send>)new Listener(event -> {
            if (event.getPacket() instanceof CPacketChatMessage) {
                String s = ((CPacketChatMessage)event.getPacket()).getMessage();
                if (s.startsWith("/") && !this.commands.getValue()) {
                    return;
                }
                s += " \u1d0b\u1d00\u1d0d\u026a \u0299\u029f\u1d1c\u1d07 \u1d0f\u0274 \u1d1b\u1d0f\u1d18\u23d0 \u0493\u1d1c\u0280\u0280\u028f\u1d21\u1d00\u0280\u1d07 \u23d0 \u1d1b\u0280\u026a\u1d18\u029f\ua731\u02e2\u026a\u02e3 \u23d0 \u0274\u1d1c\u1d1b\u0262\u1d0f\u1d05.\u1d04\u1d04 \u0fc9 \u23d0 PENIS \u23d0 \u1d07\u029f\u1d07\u1d0d\u1d07\u0274\u1d1b\u1d00\ua731.\u1d04\u1d0f\u1d0d \u23d0 \u0262\u1d00\u028f \u23d0 \u0299\u1d00\u029f\u1d05\u029c\u1d00\u1d04\u1d0b \u23d0 027Hack \u23d0 \u1d00\u1d04\u1d07 \u029c\u1d00\u1d04\u1d0b \u2713\u1d00\u1d18\u0150\u029f¥\u028f\u1d0f\u0143.\u0493\u1d00\u0262 »\u0299\u1d00\u1d04\u1d0b\u1d05\u1d0f\u1d0f\u0280\u1d07\u1d05\u23d0 \u1d0b\u1d07\u1d07\u1d0d\u028f.\u1d04\u1d04\u30c4 \u23d0 \u166d\uff4f\u1587\uff0d\u1455\u14aa\uff49\u4e47\u144e\u3112 \u23d0 \u0e23\u0e4f\u0e22\u05e7\u0452\u0e04\u03c2\u043a  \u23d0  \u23d0\u1575\u157c\u15bb.\u1462\u1462";
                if (s.length() >= 256) {
                    s = s.substring(0, 256);
                }
                ((CPacketChatMessage)event.getPacket()).message = s;
            }
        }, new Predicate[0]);
    }
}
