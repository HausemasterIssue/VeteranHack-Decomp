//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.hidden;

import com.veteran.hack.module.*;
import com.veteran.hack.event.events.*;
import me.zero.alpine.listener.*;
import java.util.function.*;
import net.minecraft.network.play.client.*;

@Module.Info(name = "XCarry", category = Module.Category.HIDDEN, description = "Store items in crafting slots", showOnArray = Module.ShowOnArray.OFF)
public class XCarry extends Module
{
    @EventHandler
    private Listener<PacketEvent.Send> l;
    
    public XCarry() {
        this.l = (Listener<PacketEvent.Send>)new Listener(event -> {
            if (event.getPacket() instanceof CPacketCloseWindow) {
                event.cancel();
            }
        }, new Predicate[0]);
    }
}
