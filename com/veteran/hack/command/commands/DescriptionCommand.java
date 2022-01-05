//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.command.commands;

import com.veteran.hack.command.*;
import com.veteran.hack.command.syntax.*;
import com.veteran.hack.module.*;

public class DescriptionCommand extends Command
{
    public DescriptionCommand() {
        super("description", new ChunkBuilder().append("module").build(), new String[] { "tooltip" });
        this.setDescription("Prints a module's description into the chat");
    }
    
    public void call(final String[] args) {
        for (final String s : args) {
            if (s != null) {
                final Module module = ModuleManager.getModuleByName(s);
                Command.sendChatMessage(module.getChatName() + "Description: &7" + module.getDescription());
            }
        }
    }
}
