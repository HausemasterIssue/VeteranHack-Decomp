//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.command.commands;

import com.veteran.hack.command.*;
import com.veteran.hack.command.syntax.*;

public class SayCommand extends Command
{
    public SayCommand() {
        super("say", new ChunkBuilder().append("message").build(), new String[0]);
        this.setDescription("Allows you to send any message, even with a prefix in it");
    }
    
    public void call(final String[] args) {
        final StringBuilder message = new StringBuilder();
        for (final String arg : args) {
            if (arg != null) {
                message.append(" ").append(arg);
            }
        }
        Command.sendServerMessage(message.toString());
    }
}
