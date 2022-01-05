//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.chat;

import com.veteran.hack.module.*;
import com.veteran.hack.event.events.*;
import me.zero.alpine.listener.*;
import java.util.function.*;
import net.minecraft.client.*;
import com.veteran.hack.command.*;
import net.minecraft.network.play.client.*;
import com.veteran.hack.util.*;
import net.minecraft.network.*;

@Module.Info(name = "FormatChat", description = "Add colour and linebreak support to upstream chat packets", category = Module.Category.HIDDEN)
public class FormatChat extends Module
{
    @EventHandler
    public Listener<PacketEvent.Send> sendListener;
    
    public FormatChat() {
        this.sendListener = (Listener<PacketEvent.Send>)new Listener(event -> {
            if (event.getPacket() instanceof CPacketChatMessage) {
                String message = ((CPacketChatMessage)event.getPacket()).message;
                if (message.contains("&") || message.contains("#n")) {
                    message = message.replaceAll("&", "§");
                    message = message.replaceAll("#n", "\n");
                    Wrapper.getPlayer().connection.sendPacket((Packet)new CPacketChatMessage(message));
                    event.cancel();
                }
            }
        }, new Predicate[0]);
    }
    
    public void onEnable() {
        if (Minecraft.getMinecraft().getCurrentServerData() == null) {
            Command.sendWarningMessage(this.getChatName() + " &6&lWarning: &r&6This does not work in singleplayer");
            this.disable();
        }
        else {
            Command.sendWarningMessage(this.getChatName() + " &6&lWarning: &r&6This will kick you on most servers!");
        }
    }
}
