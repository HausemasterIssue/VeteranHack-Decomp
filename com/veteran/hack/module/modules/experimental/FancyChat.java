//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.experimental;

import com.veteran.hack.module.*;
import java.util.*;
import com.veteran.hack.event.events.*;
import me.zero.alpine.listener.*;
import com.veteran.hack.setting.*;
import java.util.function.*;
import com.veteran.hack.util.*;
import com.veteran.hack.module.modules.chat.*;
import net.minecraft.network.play.client.*;

@Module.Info(name = "FancyChat", category = Module.Category.EXPERIMENTAL, description = "Makes messages you send fancy", showOnArray = Module.ShowOnArray.OFF)
public class FancyChat extends Module
{
    private Setting<Mode> modeSetting;
    private Setting<Boolean> randomSetting;
    private Setting<Boolean> commands;
    private static Random random;
    @EventHandler
    public Listener<PacketEvent.Send> listener;
    
    public FancyChat() {
        this.modeSetting = (Setting<Mode>)this.register((Setting)Settings.e("Mode", Mode.MOCKING));
        this.randomSetting = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("Random Case").withValue(true).withVisibility(v -> this.modeSetting.getValue().equals(Mode.MOCKING)).build());
        this.commands = (Setting<Boolean>)this.register((Setting)Settings.b("Commands", false));
        this.listener = (Listener<PacketEvent.Send>)new Listener(event -> {
            if (event.getPacket() instanceof CPacketChatMessage) {
                String s = ((CPacketChatMessage)event.getPacket()).getMessage();
                if (!this.commands.getValue() && this.isCommand(s)) {
                    return;
                }
                s = this.getText(this.modeSetting.getValue(), s);
                if (s.length() >= 256) {
                    s = s.substring(0, 256);
                }
                ((CPacketChatMessage)event.getPacket()).message = s;
            }
        }, new Predicate[0]);
    }
    
    private String getText(final Mode t, final String s) {
        switch (t) {
            case UWU: {
                return "Error: UWU mode is still experimental";
            }
            case LEET: {
                return this.leetConverter(s);
            }
            case MOCKING: {
                return this.mockingConverter(s);
            }
            default: {
                return "";
            }
        }
    }
    
    private String leetConverter(final String input) {
        final StringBuilder message = new StringBuilder();
        for (int i = 0; i < input.length(); ++i) {
            String inputChar = input.charAt(i) + "";
            inputChar = inputChar.toLowerCase();
            inputChar = this.leetSwitch(inputChar);
            message.append(inputChar);
        }
        return message.toString();
    }
    
    private String mockingConverter(final String input) {
        final StringBuilder message = new StringBuilder();
        for (int i = 0; i < input.length(); ++i) {
            String inputChar = input.charAt(i) + "";
            int rand = 0;
            if (this.randomSetting.getValue()) {
                rand = (FancyChat.random.nextBoolean() ? 1 : 0);
            }
            if (!InfoCalculator.isNumberEven(i + rand)) {
                inputChar = inputChar.toUpperCase();
            }
            else {
                inputChar = inputChar.toLowerCase();
            }
            message.append(inputChar);
        }
        return message.toString();
    }
    
    private boolean isCommand(final String s) {
        for (final String value : CustomChat.cmdCheck) {
            if (s.startsWith(value)) {
                return true;
            }
        }
        return false;
    }
    
    private String leetSwitch(final String i) {
        switch (i) {
            case "a": {
                return "4";
            }
            case "e": {
                return "3";
            }
            case "g": {
                return "6";
            }
            case "l":
            case "i": {
                return "1";
            }
            case "o": {
                return "0";
            }
            case "s": {
                return "$";
            }
            case "t": {
                return "7";
            }
            default: {
                return i;
            }
        }
    }
    
    static {
        FancyChat.random = new Random();
    }
    
    private enum Mode
    {
        UWU, 
        LEET, 
        MOCKING;
    }
}
