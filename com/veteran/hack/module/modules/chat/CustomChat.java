//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.chat;

import com.veteran.hack.module.*;
import com.veteran.hack.event.events.*;
import me.zero.alpine.listener.*;
import com.veteran.hack.setting.*;
import java.util.function.*;
import com.veteran.hack.command.*;
import net.minecraft.network.play.client.*;

@Module.Info(name = "CustomChat", category = Module.Category.CHAT, description = "Add a custom suffix to the end of your message!", showOnArray = Module.ShowOnArray.OFF)
public class CustomChat extends Module
{
    public Setting<Boolean> startupGlobal;
    public Setting<TextMode> textMode;
    private Setting<DecoMode> decoMode;
    private Setting<Boolean> commands;
    public Setting<String> customText;
    public static String[] cmdCheck;
    @EventHandler
    public Listener<PacketEvent.Send> listener;
    private static long startTime;
    
    public CustomChat() {
        this.startupGlobal = (Setting<Boolean>)this.register((Setting)Settings.b("Enable Automatically", true));
        this.textMode = (Setting<TextMode>)this.register((Setting)Settings.e("Message", TextMode.NAME_2B));
        this.decoMode = (Setting<DecoMode>)this.register((Setting)Settings.e("Separator", DecoMode.NONE));
        this.commands = (Setting<Boolean>)this.register((Setting)Settings.b("Commands", false));
        this.customText = (Setting<String>)this.register((Setting)Settings.stringBuilder("Custom Text").withValue("unchanged").withConsumer((old, value) -> {}).build());
        this.listener = (Listener<PacketEvent.Send>)new Listener(event -> {
            if (event.getPacket() instanceof CPacketChatMessage) {
                String s = ((CPacketChatMessage)event.getPacket()).getMessage();
                if (!this.commands.getValue() && this.isCommand(s)) {
                    return;
                }
                s += this.getFull(this.decoMode.getValue());
                if (s.length() >= 256) {
                    s = s.substring(0, 256);
                }
                ((CPacketChatMessage)event.getPacket()).message = s;
            }
        }, new Predicate[0]);
    }
    
    private String getText(final TextMode t) {
        switch (t) {
            case NAME_2B: {
                return "Veteran Hack";
            }
            case NAME: {
                return "\u1d20\u1d07\u1d1b\u029c\u1d00\u1d04\u1d0b";
            }
            case ONTOP_2B: {
                return "Veteran Hack On Top";
            }
            case WEBSITE: {
                return "hack.veteran.com :^)";
            }
            case LONGNAME: {
                return "\u1d20\u1d07\u1d1b\u1d07\u0280\u1d00\u0274 \u029c\u1d00\u1d04\u1d0b";
            }
            case CUSTOM: {
                return this.customText.getValue();
            }
            default: {
                return "";
            }
        }
    }
    
    private String getFull(final DecoMode d) {
        switch (d) {
            case NONE: {
                return " " + this.getText(this.textMode.getValue());
            }
            case CLASSIC: {
                return " « " + this.getText(this.textMode.getValue()) + " " + '»';
            }
            case SEPARATOR: {
                return " \u23d0 " + this.getText(this.textMode.getValue());
            }
            default: {
                return "";
            }
        }
    }
    
    private boolean isCommand(final String s) {
        for (final String value : CustomChat.cmdCheck) {
            if (s.startsWith(value)) {
                return true;
            }
        }
        return false;
    }
    
    public void onUpdate() {
        if (CustomChat.startTime == 0L) {
            CustomChat.startTime = System.currentTimeMillis();
        }
        if (CustomChat.startTime + 5000L <= System.currentTimeMillis()) {
            if (this.textMode.getValue().equals(TextMode.CUSTOM) && this.customText.getValue().equalsIgnoreCase("unchanged") && CustomChat.mc.player != null) {
                Command.sendWarningMessage(this.getChatName() + " Warning: In order to use the custom " + this.getName() + ", please run the &7" + Command.getCommandPrefix() + "customchat&r command to change it");
            }
            CustomChat.startTime = System.currentTimeMillis();
        }
    }
    
    public void onDisable() {
        Command.sendAutoDisableMessage(this.getName(), (boolean)this.startupGlobal.getValue());
    }
    
    static {
        CustomChat.cmdCheck = new String[] { "/", ",", ".", "-", ";", "?", "*", "^", "&", Command.getCommandPrefix() };
        CustomChat.startTime = 0L;
    }
    
    private enum DecoMode
    {
        SEPARATOR, 
        CLASSIC, 
        NONE;
    }
    
    public enum TextMode
    {
        NAME, 
        NAME_2B, 
        ONTOP_2B, 
        WEBSITE, 
        LONGNAME, 
        CUSTOM;
    }
}
