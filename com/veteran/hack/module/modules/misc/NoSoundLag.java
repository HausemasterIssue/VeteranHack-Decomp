//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.misc;

import com.veteran.hack.module.*;
import com.veteran.hack.event.events.*;
import me.zero.alpine.listener.*;
import java.util.function.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.*;
import net.minecraft.init.*;

@Module.Info(name = "NoSoundLag", category = Module.Category.MISC, description = "Prevents lag caused by sound machines")
public class NoSoundLag extends Module
{
    @EventHandler
    Listener<PacketEvent.Receive> receiveListener;
    
    public NoSoundLag() {
        this.receiveListener = (Listener<PacketEvent.Receive>)new Listener(event -> {
            if (NoSoundLag.mc.player == null) {
                return;
            }
            if (event.getPacket() instanceof SPacketSoundEffect) {
                final SPacketSoundEffect soundPacket = (SPacketSoundEffect)event.getPacket();
                if (soundPacket.getCategory() == SoundCategory.PLAYERS && soundPacket.getSound() == SoundEvents.ITEM_ARMOR_EQUIP_GENERIC) {
                    event.cancel();
                }
            }
        }, new Predicate[0]);
    }
}
