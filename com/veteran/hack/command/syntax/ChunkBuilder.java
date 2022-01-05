//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.command.syntax;

import java.util.*;

public class ChunkBuilder
{
    private static final SyntaxChunk[] EXAMPLE;
    List<SyntaxChunk> chunks;
    
    public ChunkBuilder() {
        this.chunks = new ArrayList<SyntaxChunk>();
    }
    
    public ChunkBuilder append(final SyntaxChunk syntaxChunk) {
        this.chunks.add(syntaxChunk);
        return this;
    }
    
    public ChunkBuilder append(final String head, final boolean necessary) {
        this.append(new SyntaxChunk(head, necessary));
        return this;
    }
    
    public ChunkBuilder append(final String head, final boolean necessary, final SyntaxParser parser) {
        final SyntaxChunk chunk = new SyntaxChunk(head, necessary);
        chunk.setParser(parser);
        this.append(chunk);
        return this;
    }
    
    public SyntaxChunk[] build() {
        return this.chunks.toArray(ChunkBuilder.EXAMPLE);
    }
    
    public ChunkBuilder append(final String name) {
        return this.append(name, true);
    }
    
    static {
        EXAMPLE = new SyntaxChunk[0];
    }
}
