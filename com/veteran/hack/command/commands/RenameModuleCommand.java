//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.command.commands;

import com.veteran.hack.command.*;
import com.veteran.hack.command.syntax.parsers.*;
import com.veteran.hack.command.syntax.*;
import com.veteran.hack.module.*;

public class RenameModuleCommand extends Command
{
    public RenameModuleCommand() {
        super("renamemodule", new ChunkBuilder().append("module", true, new ModuleParser()).append("name").build(), new String[0]);
        this.setDescription("Rename a module to something else");
    }
    
    public void call(final String[] args) {
        if (args.length == 0) {
            sendChatMessage("Please specify a module!");
            return;
        }
        final Module module = ModuleManager.getModuleByName(args[0]);
        if (module == null) {
            sendChatMessage("Unknown module '" + args[0] + "'!");
            return;
        }
        final String name = (args.length == 1) ? module.getOriginalName() : args[1];
        if (!name.matches("[a-zA-Z]+")) {
            sendChatMessage("Name must be alphabetic!");
            return;
        }
        sendChatMessage("&b" + module.getName() + "&r renamed to &b" + name);
        module.setName(name);
    }
}
