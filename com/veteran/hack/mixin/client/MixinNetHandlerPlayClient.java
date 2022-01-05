//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.mixin.client;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.network.*;
import net.minecraft.network.play.server.*;
import net.minecraft.world.chunk.*;
import com.veteran.hack.*;
import com.veteran.hack.event.events.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin({ NetHandlerPlayClient.class })
public class MixinNetHandlerPlayClient
{
    @Inject(method = { "handleChunkData" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/Chunk;read(Lnet/minecraft/network/PacketBuffer;IZ)V") }, locals = LocalCapture.CAPTURE_FAILHARD)
    private void read(final SPacketChunkData data, final CallbackInfo info, final Chunk chunk) {
        BaseMod.EVENT_BUS.post((Object)new ChunkEvent(chunk, data));
    }
}
