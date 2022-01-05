//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.util;

public class RichPresence
{
    public static RichPresence INSTANCE;
    public CustomUser[] customUsers;
    
    public RichPresence() {
        RichPresence.INSTANCE = this;
    }
    
    public static class CustomUser
    {
        public String uuid;
        public String type;
    }
}
