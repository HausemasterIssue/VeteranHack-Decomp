//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.player;

import com.veteran.hack.module.*;
import com.veteran.hack.event.events.*;
import me.zero.alpine.listener.*;
import com.veteran.hack.setting.*;
import java.util.function.*;
import com.veteran.hack.util.*;
import com.veteran.hack.command.*;
import net.minecraft.client.network.*;
import java.util.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.text.*;

@Module.Info(name = "EndTeleport", category = Module.Category.PLAYER, description = "Allows for teleportation when going through end portals")
public class EndTeleport extends Module
{
    private Setting<Boolean> confirmed;
    @EventHandler
    public Listener<PacketEvent.Receive> receiveListener;
    
    public EndTeleport() {
        this.confirmed = (Setting<Boolean>)this.register((Setting)Settings.b("Confirm", true));
        this.receiveListener = (Listener<PacketEvent.Receive>)new Listener(event -> {
            if (event.getPacket() instanceof SPacketRespawn && ((SPacketRespawn)event.getPacket()).getDimensionID() == 1 && this.confirmed.getValue()) {
                Objects.requireNonNull(Wrapper.getMinecraft().getConnection()).handleDisconnect(new SPacketDisconnect((ITextComponent)new TextComponentString("Attempting teleportation exploit")));
                this.disable();
            }
        }, new Predicate[0]);
    }
    
    public void onEnable() {
        if (Wrapper.getMinecraft().getCurrentServerData() == null) {
            Command.sendWarningMessage(this.getChatName() + "This module does not work in singleplayer");
            this.disable();
        }
        else if (!this.confirmed.getValue()) {
            Command.sendWarningMessage(this.getChatName() + "This module will kick you from the server! It is part of the exploit and cannot be avoided");
        }
    }
}
