//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.command.commands;

import com.veteran.hack.command.*;
import com.veteran.hack.command.syntax.parsers.*;
import com.veteran.hack.command.syntax.*;
import com.veteran.hack.module.*;

public class ToggleCommand extends Command
{
    public ToggleCommand() {
        super("toggle", new ChunkBuilder().append("module", true, new ModuleParser()).build(), new String[] { "t" });
        this.setDescription("Quickly toggle a module on and off");
    }
    
    public void call(final String[] args) {
        if (args.length == 0) {
            Command.sendChatMessage("Please specify a module!");
            return;
        }
        final Module m = ModuleManager.getModuleByName(args[0]);
        if (m == null) {
            Command.sendChatMessage("Unknown module '" + args[0] + "'");
            return;
        }
        m.toggle();
        Command.sendChatMessage(m.getName() + (m.isEnabled() ? " &aenabled" : " &cdisabled"));
    }
}
