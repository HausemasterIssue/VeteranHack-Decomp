//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.command;

import net.minecraft.client.*;
import com.veteran.hack.command.syntax.*;
import java.util.*;
import com.veteran.hack.module.*;
import com.veteran.hack.util.*;
import net.minecraft.launchwrapper.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import com.veteran.hack.setting.*;
import net.minecraft.util.text.*;
import java.util.regex.*;

public abstract class Command
{
    protected String label;
    protected String syntax;
    protected String description;
    protected List<String> aliases;
    public final Minecraft mc;
    protected SyntaxChunk[] syntaxChunks;
    public static Setting<String> commandPrefix;
    
    public Command(final String label, final SyntaxChunk[] syntaxChunks, final String... aliases) {
        this.mc = Minecraft.getMinecraft();
        this.label = label;
        this.syntaxChunks = syntaxChunks;
        this.description = "Descriptionless";
        this.aliases = Arrays.asList(aliases);
    }
    
    public static void sendChatMessage(final String message) {
        sendRawChatMessage("&7[&9\u1d20\u1d07\u1d1b\u029c\u1d00\u1d04\u1d0b&7] &r" + message);
    }
    
    public static void sendWarningMessage(final String message) {
        sendRawChatMessage("&7[&6\u1d20\u1d07\u1d1b\u029c\u1d00\u1d04\u1d0b&7] &r" + message);
    }
    
    public static void sendErrorMessage(final String message) {
        sendRawChatMessage("&7[&4\u1d20\u1d07\u1d1b\u029c\u1d00\u1d04\u1d0b&7] &r" + message);
    }
    
    public static void sendCustomMessage(final String message, final String colour) {
        sendRawChatMessage("&7[" + colour + "\u1d20\u1d07\u1d1b\u029c\u1d00\u1d04\u1d0b" + "&7] &r" + message);
    }
    
    public static void sendStringChatMessage(final String[] messages) {
        sendChatMessage("");
        for (final String s : messages) {
            sendRawChatMessage(s);
        }
    }
    
    public static void sendDisableMessage(final String moduleName) {
        sendErrorMessage("Error: The " + moduleName + " module is only for configuring the GUI element. In order to show the GUI element you need to hit the pin in the upper left of the GUI element");
        ModuleManager.getModuleByName(moduleName).enable();
    }
    
    public static void sendAutoDisableMessage(final String moduleName, final boolean startup) {
        if (startup) {
            sendWarningMessage("Note: The " + moduleName + " module has automatic startup enabled. If you want to keep it disabled, disable the automatic startup setting");
        }
    }
    
    public static void sendRawChatMessage(final String message) {
        if (Minecraft.getMinecraft().player != null) {
            Wrapper.getPlayer().sendMessage((ITextComponent)new ChatMessage(message));
        }
        else {
            LogWrapper.info(message, new Object[0]);
        }
    }
    
    public static void sendServerMessage(final String message) {
        if (Minecraft.getMinecraft().player != null) {
            Wrapper.getPlayer().connection.sendPacket((Packet)new CPacketChatMessage(message));
        }
        else {
            LogWrapper.warning("Could not send server message: \"" + message + "\"", new Object[0]);
        }
    }
    
    protected void setDescription(final String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public static String getCommandPrefix() {
        return Command.commandPrefix.getValue();
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public abstract void call(final String[] p0);
    
    public SyntaxChunk[] getSyntaxChunks() {
        return this.syntaxChunks;
    }
    
    protected SyntaxChunk getSyntaxChunk(final String name) {
        for (final SyntaxChunk c : this.syntaxChunks) {
            if (c.getType().equals(name)) {
                return c;
            }
        }
        return null;
    }
    
    public List<String> getAliases() {
        return this.aliases;
    }
    
    static {
        Command.commandPrefix = Settings.s("commandPrefix", ".");
    }
    
    public static class ChatMessage extends TextComponentBase
    {
        String text;
        
        public ChatMessage(final String text) {
            final Pattern p = Pattern.compile("&[0123456789abcdefrlosmk]");
            final Matcher m = p.matcher(text);
            final StringBuffer sb = new StringBuffer();
            while (m.find()) {
                final String replacement = "§" + m.group().substring(1);
                m.appendReplacement(sb, replacement);
            }
            m.appendTail(sb);
            this.text = sb.toString();
        }
        
        public String getUnformattedComponentText() {
            return this.text;
        }
        
        public ITextComponent createCopy() {
            return (ITextComponent)new ChatMessage(this.text);
        }
    }
}
