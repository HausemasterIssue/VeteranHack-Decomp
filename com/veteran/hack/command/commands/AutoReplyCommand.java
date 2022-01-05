//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.command.commands;

import com.veteran.hack.command.*;
import com.veteran.hack.command.syntax.*;
import com.veteran.hack.module.modules.chat.*;
import com.veteran.hack.module.*;

public class AutoReplyCommand extends Command
{
    public AutoReplyCommand() {
        super("autoreply", new ChunkBuilder().append("message").append("=listener").append("-replyCommand").build(), new String[] { "reply" });
        this.setDescription("Allows you to customize AutoReply's settings");
    }
    
    public void call(final String[] args) {
        final AutoReply autoReply = (AutoReply)ModuleManager.getModuleByName("AutoReply");
        if (autoReply == null) {
            Command.sendErrorMessage("&cThe AutoReply module is not available for some reason. Make sure the name you're calling is correct and that you have the module installed!!");
            return;
        }
        if (!autoReply.isEnabled()) {
            Command.sendWarningMessage("&6Warning: The AutoReply module is not enabled!");
            Command.sendWarningMessage("The command will still work, but will not visibly do anything.");
        }
        for (final String s : args) {
            if (s != null) {
                if (s.startsWith("=")) {
                    final String sT = s.replace("=", "");
                    autoReply.listener.setValue(sT);
                    Command.sendChatMessage("Set the AutoReply listener to <" + sT + ">");
                    if (!autoReply.customListener.getValue()) {
                        Command.sendWarningMessage("&6Warning: You don't have Custom Listener enabled in AutoReply!");
                        Command.sendWarningMessage("The command will still work, but will not visibly do anything.");
                    }
                }
                else if (s.startsWith("-")) {
                    final String sT = s.replace("-", "");
                    autoReply.replyCommand.setValue(sT);
                    Command.sendChatMessage("Set the AutoReply reply command to <" + sT + ">");
                    if (!autoReply.customReplyCommand.getValue()) {
                        Command.sendWarningMessage("&6Warning: You don't have Custom Reply Command enabled in AutoReply!");
                        Command.sendWarningMessage("The command will still work, but will not visibly do anything.");
                    }
                }
                else {
                    autoReply.message.setValue(s);
                    Command.sendChatMessage("Set the AutoReply message to <" + s + ">");
                    if (!autoReply.customMessage.getValue()) {
                        Command.sendWarningMessage("&6Warning: You don't have Custom Message enabled in AutoReply!");
                        Command.sendWarningMessage("The command will still work, but will not visibly do anything.");
                    }
                }
            }
        }
    }
}
