//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.hidden;

import com.veteran.hack.module.*;
import com.veteran.hack.event.events.*;
import me.zero.alpine.listener.*;
import java.util.function.*;
import com.veteran.hack.command.*;
import com.veteran.hack.util.*;
import net.minecraft.tileentity.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.util.math.*;

@Module.Info(name = "ConsoleSpam", description = "Spams Spigot consoles by sending invalid UpdateSign packets", category = Module.Category.HIDDEN)
public class ConsoleSpam extends Module
{
    @EventHandler
    public Listener<PacketEvent.Send> sendListener;
    
    public ConsoleSpam() {
        this.sendListener = (Listener<PacketEvent.Send>)new Listener(event -> {
            if (event.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) {
                final BlockPos location = ((CPacketPlayerTryUseItemOnBlock)event.getPacket()).getPos();
                Wrapper.getPlayer().connection.sendPacket((Packet)new CPacketUpdateSign(location, new TileEntitySign().signText));
            }
        }, new Predicate[0]);
    }
    
    public void onEnable() {
        Command.sendChatMessage(this.getChatName() + " Every time you right click a sign, a warning will appear in console.");
        Command.sendChatMessage(this.getChatName() + " Use an autoclicker to automate this process.");
    }
}
