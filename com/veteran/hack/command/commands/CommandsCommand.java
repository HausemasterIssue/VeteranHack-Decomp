//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.command.commands;

import com.veteran.hack.command.*;
import com.veteran.hack.command.syntax.*;
import com.veteran.hack.*;
import java.util.*;

public class CommandsCommand extends Command
{
    public CommandsCommand() {
        super("commands", SyntaxChunk.EMPTY, new String[] { "cmds" });
        this.setDescription("Gives you this list of commands");
    }
    
    public void call(final String[] args) {
        BaseMod.getInstance().getCommandManager().getCommands().stream().sorted(Comparator.comparing(command -> command.getLabel())).forEach(command -> Command.sendChatMessage("&f" + Command.getCommandPrefix() + command.getLabel() + "&r ~ &7" + command.getDescription()));
    }
}
