//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.chat;

import com.veteran.hack.module.*;
import com.veteran.hack.event.events.*;
import me.zero.alpine.listener.*;
import com.veteran.hack.setting.*;
import com.veteran.hack.command.*;
import java.util.function.*;
import net.minecraft.network.play.server.*;
import com.veteran.hack.util.*;

@Module.Info(name = "AutoReply", description = "Automatically replies to messages", category = Module.Category.CHAT)
public class AutoReply extends Module
{
    public Setting<Boolean> customMessage;
    public Setting<String> message;
    public Setting<Boolean> customListener;
    public Setting<String> listener;
    public Setting<Boolean> customReplyCommand;
    public Setting<String> replyCommand;
    private String listenerDefault;
    private String replyCommandDefault;
    @EventHandler
    public Listener<PacketEvent.Receive> receiveListener;
    private static long startTime;
    
    public AutoReply() {
        this.customMessage = (Setting<Boolean>)this.register((Setting)Settings.b("Custom Message", false));
        this.message = (Setting<String>)this.register((Setting)Settings.stringBuilder("Custom Text").withValue("Use &7" + Command.getCommandPrefix() + "autoreply&r to modify this").withConsumer((old, value) -> {}).withVisibility(v -> this.customMessage.getValue()).build());
        this.customListener = (Setting<Boolean>)this.register((Setting)Settings.b("Custom Listener", false));
        this.listener = (Setting<String>)this.register((Setting)Settings.stringBuilder("Custom Listener Name").withValue("unchanged").withConsumer((old, value) -> {}).withVisibility(v -> this.customListener.getValue()).build());
        this.customReplyCommand = (Setting<Boolean>)this.register((Setting)Settings.b("Custom Reply Command", false));
        this.replyCommand = (Setting<String>)this.register((Setting)Settings.stringBuilder("Custom Reply Command").withValue("unchanged").withConsumer((old, value) -> {}).withVisibility(v -> this.customReplyCommand.getValue()).build());
        this.listenerDefault = "whispers:";
        this.replyCommandDefault = "r";
        this.receiveListener = (Listener<PacketEvent.Receive>)new Listener(event -> {
            if (event.getPacket() instanceof SPacketChat && ((SPacketChat)event.getPacket()).getChatComponent().getUnformattedText().contains(this.listenerDefault) && !((SPacketChat)event.getPacket()).getChatComponent().getUnformattedText().contains(AutoReply.mc.player.getName())) {
                if (this.customMessage.getValue()) {
                    Wrapper.getPlayer().sendChatMessage("/" + this.replyCommandDefault + " " + this.message.getValue());
                }
                else {
                    Wrapper.getPlayer().sendChatMessage("/" + this.replyCommandDefault + " I am currently afk, thanks to KAMI Blue's AutoReply module!");
                }
            }
        }, new Predicate[0]);
    }
    
    public void onUpdate() {
        if (this.customListener.getValue()) {
            this.listenerDefault = this.listener.getValue();
        }
        else {
            this.listenerDefault = "whispers:";
        }
        if (this.customReplyCommand.getValue()) {
            this.replyCommandDefault = this.replyCommand.getName();
        }
        else {
            this.replyCommandDefault = "r";
        }
        if (AutoReply.startTime == 0L) {
            AutoReply.startTime = System.currentTimeMillis();
        }
        if (AutoReply.startTime + 5000L <= System.currentTimeMillis()) {
            if (this.customListener.getValue() && this.listener.getValue().equalsIgnoreCase("unchanged") && AutoReply.mc.player != null) {
                Command.sendWarningMessage(this.getChatName() + " Warning: In order to use the custom listener, please run the &7" + Command.getCommandPrefix() + "autoreply&r =LISTENERNAME command to change it");
            }
            if (this.customReplyCommand.getValue() && this.replyCommand.getValue().equalsIgnoreCase("unchanged") && AutoReply.mc.player != null) {
                Command.sendWarningMessage(this.getChatName() + " Warning: In order to use the custom reply command, please run the &7" + Command.getCommandPrefix() + "autoreply&r -REPLYCOMMAND command to change it");
            }
            AutoReply.startTime = System.currentTimeMillis();
        }
    }
    
    static {
        AutoReply.startTime = 0L;
    }
}
