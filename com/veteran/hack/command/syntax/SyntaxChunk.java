//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.command.syntax;

public class SyntaxChunk
{
    boolean headless;
    String head;
    String type;
    private boolean necessary;
    private SyntaxParser parser;
    public static final SyntaxChunk[] EMPTY;
    
    public SyntaxChunk(final String head, final String type, final boolean necessary) {
        this.headless = false;
        this.head = head;
        this.type = type;
        this.necessary = necessary;
        this.parser = ((chunks, thisChunk, values, chunkValue) -> {
            if (chunkValue != null) {
                return null;
            }
            else {
                return head + (this.isNecessary() ? "<" : "[") + type + (this.isNecessary() ? ">" : "]");
            }
        });
    }
    
    public SyntaxChunk(final String type, final boolean necessary) {
        this("", type, necessary);
        this.headless = true;
    }
    
    public String getHead() {
        return this.head;
    }
    
    public boolean isHeadless() {
        return this.headless;
    }
    
    public boolean isNecessary() {
        return this.necessary;
    }
    
    public String getChunk(final SyntaxChunk[] chunks, final SyntaxChunk thisChunk, final String[] args, final String chunkValue) {
        final String s = this.parser.getChunk(chunks, thisChunk, args, chunkValue);
        if (s == null) {
            return "";
        }
        return s;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setParser(final SyntaxParser parser) {
        this.parser = parser;
    }
    
    static {
        EMPTY = new SyntaxChunk[0];
    }
}
