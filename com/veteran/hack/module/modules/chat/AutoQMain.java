//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.chat;

import com.veteran.hack.module.*;
import com.veteran.hack.setting.*;
import net.minecraft.client.*;
import com.veteran.hack.command.*;
import java.text.*;
import java.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

@Module.Info(name = "AutoQMain", description = "Automatically does /queue main on 2b2t.org", category = Module.Category.CHAT, showOnArray = Module.ShowOnArray.OFF)
public class AutoQMain extends Module
{
    private Setting<Boolean> debug;
    private Setting<Boolean> debugWarn;
    private Setting<Boolean> endDi;
    private Setting<Double> delay;
    private double delayTime;
    private double oldDelay;
    
    public AutoQMain() {
        this.debug = (Setting<Boolean>)this.register((Setting)Settings.b("Debug", true));
        this.debugWarn = (Setting<Boolean>)this.register((Setting)Settings.b("Connection Warning", true));
        this.endDi = (Setting<Boolean>)this.register((Setting)Settings.b("Dimension Warning", true));
        this.delay = (Setting<Double>)this.register((Setting)Settings.doubleBuilder("Wait time").withMinimum(0.2).withValue(7.1).withMaximum(10.0).build());
        this.oldDelay = 0.0;
    }
    
    public void onUpdate() {
        if (AutoQMain.mc.player == null) {
            return;
        }
        if (this.oldDelay == 0.0) {
            this.oldDelay = this.delay.getValue();
        }
        else if (this.oldDelay != this.delay.getValue()) {
            this.delayTime = this.delay.getValue();
            this.oldDelay = this.delay.getValue();
        }
        if (this.delayTime <= 0.0) {
            this.delayTime = (int)(this.delay.getValue() * 2400.0);
        }
        else if (this.delayTime > 0.0) {
            --this.delayTime;
            return;
        }
        if (Minecraft.getMinecraft().getCurrentServerData() == null) {
            Command.sendWarningMessage(this.getChatName() + "&l&6Warning: &r&6You are on singleplayer");
            return;
        }
        if (!Minecraft.getMinecraft().getCurrentServerData().serverIP.equalsIgnoreCase("2b2t.org") && this.debugWarn.getValue()) {
            Command.sendWarningMessage(this.getChatName() + "&l&6Warning: &r&6You are not connected to 2b2t.org");
        }
        if (AutoQMain.mc.player.dimension != 1 && this.endDi.getValue()) {
            Command.sendWarningMessage(this.getChatName() + "&l&6Warning: &r&6You are not in the end. Not running &b/queue main&7.");
            return;
        }
        if (this.debug.getValue()) {
            final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            final Date date = new Date(System.currentTimeMillis());
            System.out.println(formatter.format(date));
            Command.sendChatMessage("&7Run &b/queue main&7 at " + formatter.format(date));
        }
        Minecraft.getMinecraft().playerController.connection.sendPacket((Packet)new CPacketChatMessage("/queue main"));
    }
    
    public void onDisable() {
        this.delayTime = 0.0;
    }
}
