//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.misc;

import com.veteran.hack.module.*;
import com.veteran.hack.setting.*;
import com.veteran.hack.*;
import com.veteran.hack.command.*;

@Module.Info(name = "DiscordSettings", category = Module.Category.MISC, description = "Discord Rich Presence")
public class DiscordSettings extends Module
{
    public Setting<Boolean> startupGlobal;
    public Setting<Boolean> coordsConfirm;
    public Setting<LineInfo> line1Setting;
    public Setting<LineInfo> line2Setting;
    private static long startTime;
    
    public DiscordSettings() {
        this.startupGlobal = (Setting<Boolean>)this.register((Setting)Settings.b("Enable Automatically", true));
        this.coordsConfirm = (Setting<Boolean>)this.register((Setting)Settings.b("Coords Confirm", false));
        this.line1Setting = (Setting<LineInfo>)this.register((Setting)Settings.e("Line 1 Left", LineInfo.VERSION));
        this.line2Setting = (Setting<LineInfo>)this.register((Setting)Settings.e("Line 2 Left", LineInfo.SERVER_IP));
    }
    
    public String getLine(final LineInfo line) {
        switch (line) {
            case VERSION: {
                return "Veteran Hack b2.3";
            }
            case WORLD: {
                if (DiscordSettings.mc.isIntegratedServerRunning()) {
                    return "Singleplayer";
                }
                if (DiscordSettings.mc.getCurrentServerData() != null) {
                    return "Multiplayer";
                }
                return "Main Menu";
            }
            case USERNAME: {
                if (DiscordSettings.mc.player != null) {
                    return DiscordSettings.mc.player.getName();
                }
                return DiscordSettings.mc.getSession().getUsername();
            }
            case HEALTH: {
                if (DiscordSettings.mc.player != null) {
                    return (int)DiscordSettings.mc.player.getHealth() + " hp";
                }
                return "null hp";
            }
            case SERVER_IP: {
                if (DiscordSettings.mc.getCurrentServerData() != null) {
                    return DiscordSettings.mc.getCurrentServerData().serverIP;
                }
                if (DiscordSettings.mc.isIntegratedServerRunning()) {
                    return "Offline";
                }
                return "Main Menu";
            }
            case COORDS: {
                if (DiscordSettings.mc.player != null && this.coordsConfirm.getValue()) {
                    return "(" + (int)DiscordSettings.mc.player.posX + " " + (int)DiscordSettings.mc.player.posY + " " + (int)DiscordSettings.mc.player.posZ + ")";
                }
                return "null coords";
            }
            default: {
                return "";
            }
        }
    }
    
    public void onEnable() {
        DiscordPresence.start();
    }
    
    public void onUpdate() {
        if (DiscordSettings.startTime == 0L) {
            DiscordSettings.startTime = System.currentTimeMillis();
        }
        if (DiscordSettings.startTime + 10000L <= System.currentTimeMillis()) {
            if ((this.line1Setting.getValue().equals(LineInfo.COORDS) || this.line2Setting.getValue().equals(LineInfo.COORDS)) && !this.coordsConfirm.getValue() && DiscordSettings.mc.player != null) {
                Command.sendWarningMessage(this.getChatName() + " Warning: In order to use the coords option please enable the coords confirmation option. This will display your coords on the discord rpc. Do NOT use this if you do not want your coords displayed");
            }
            DiscordSettings.startTime = System.currentTimeMillis();
        }
    }
    
    public void onDisable() {
        Command.sendAutoDisableMessage(this.getName(), (boolean)this.startupGlobal.getValue());
    }
    
    static {
        DiscordSettings.startTime = 0L;
    }
    
    public enum LineInfo
    {
        VERSION, 
        WORLD, 
        USERNAME, 
        HEALTH, 
        SERVER_IP, 
        COORDS, 
        NONE;
    }
}
