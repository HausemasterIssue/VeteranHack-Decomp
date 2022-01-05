//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.event.events;

import com.veteran.hack.event.*;
import net.minecraft.world.chunk.*;
import net.minecraft.network.play.server.*;

public class ChunkEvent extends KamiEvent
{
    private Chunk chunk;
    private SPacketChunkData packet;
    
    public ChunkEvent(final Chunk chunk, final SPacketChunkData packet) {
        this.chunk = chunk;
        this.packet = packet;
    }
    
    public Chunk getChunk() {
        return this.chunk;
    }
    
    public SPacketChunkData getPacket() {
        return this.packet;
    }
}
