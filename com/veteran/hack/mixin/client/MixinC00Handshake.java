//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.mixin.client;

import net.minecraft.network.handshake.client.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.network.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.veteran.hack.module.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ C00Handshake.class })
public class MixinC00Handshake
{
    @Shadow
    int protocolVersion;
    @Shadow
    String ip;
    @Shadow
    int port;
    @Shadow
    EnumConnectionState requestedState;
    
    @Inject(method = { "writePacketData" }, at = { @At("HEAD") }, cancellable = true)
    public void writePacketData(final PacketBuffer buf, final CallbackInfo info) {
        if (ModuleManager.isModuleEnabled("FakeVanillaClient")) {
            info.cancel();
            buf.writeVarInt(this.protocolVersion);
            buf.writeString(this.ip);
            buf.writeShort(this.port);
            buf.writeVarInt(this.requestedState.getId());
        }
    }
}
