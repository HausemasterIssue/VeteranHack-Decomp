//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.command.commands;

import com.veteran.hack.command.*;
import com.veteran.hack.command.syntax.*;

public class PrefixCommand extends Command
{
    public PrefixCommand() {
        super("prefix", new ChunkBuilder().append("character").build(), new String[0]);
        this.setDescription("Changes the prefix to your new key");
    }
    
    public void call(final String[] args) {
        if (args.length <= 0) {
            Command.sendChatMessage("Please specify a new prefix!");
            return;
        }
        if (args[0] != null) {
            Command.commandPrefix.setValue(args[0]);
            Command.sendChatMessage("Prefix set to &b" + Command.commandPrefix.getValue());
        }
        else if (args[0].equals("\\")) {
            Command.sendChatMessage("Error: \"\\\" is not a supported prefix");
        }
        else {
            Command.sendChatMessage("Please specify a new prefix!");
        }
    }
}
