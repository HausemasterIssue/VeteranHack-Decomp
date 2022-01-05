//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.combat;

import com.veteran.hack.module.*;
import me.zero.alpine.listener.*;
import com.veteran.hack.event.events.*;
import com.veteran.hack.setting.*;
import java.util.function.*;
import net.minecraft.entity.player.*;
import java.util.*;
import com.veteran.hack.util.*;
import net.minecraft.util.text.*;
import com.veteran.hack.command.*;
import net.minecraft.network.play.server.*;
import net.minecraft.world.*;
import com.veteran.hack.*;
import net.minecraft.entity.*;

@Module.Info(name = "TotemPopCounter", description = "Counts how many times players pop", category = Module.Category.COMBAT)
public class TotemPopCounter extends Module
{
    private Setting<Boolean> countFriends;
    private Setting<Boolean> countSelf;
    private Setting<Boolean> resetDeaths;
    private Setting<Boolean> resetSelfDeaths;
    private Setting<Announce> announceSetting;
    private Setting<Boolean> thanksTo;
    private Setting<ColourTextFormatting.ColourCode> colourCode;
    private Setting<ColourTextFormatting.ColourCode> colourCode1;
    private HashMap<String, Integer> playerList;
    private boolean isDead;
    @EventHandler
    public Listener<EntityUseTotem> listListener;
    @EventHandler
    public Listener<PacketEvent.Receive> popListener;
    
    public TotemPopCounter() {
        this.countFriends = (Setting<Boolean>)this.register((Setting)Settings.b("Count Friends", true));
        this.countSelf = (Setting<Boolean>)this.register((Setting)Settings.b("Count Self", false));
        this.resetDeaths = (Setting<Boolean>)this.register((Setting)Settings.b("Reset On Death", true));
        this.resetSelfDeaths = (Setting<Boolean>)this.register((Setting)Settings.b("Reset Self Death", true));
        this.announceSetting = (Setting<Announce>)this.register((Setting)Settings.e("Announce", Announce.CLIENT));
        this.thanksTo = (Setting<Boolean>)this.register((Setting)Settings.b("Thanks to", false));
        this.colourCode = (Setting<ColourTextFormatting.ColourCode>)this.register((Setting)Settings.e("Color Name", ColourTextFormatting.ColourCode.DARK_PURPLE));
        this.colourCode1 = (Setting<ColourTextFormatting.ColourCode>)this.register((Setting)Settings.e("Color Number", ColourTextFormatting.ColourCode.LIGHT_PURPLE));
        this.playerList = new HashMap<String, Integer>();
        this.isDead = false;
        this.listListener = (Listener<EntityUseTotem>)new Listener(event -> {
            if (this.playerList == null) {
                this.playerList = new HashMap<String, Integer>();
            }
            if (this.playerList.get(event.getEntity().getName()) == null) {
                this.playerList.put(event.getEntity().getName(), 1);
                this.sendMessage(this.formatName(event.getEntity().getName()) + " popped " + this.formatNumber(1) + " totem" + this.ending());
            }
            else if (this.playerList.get(event.getEntity().getName()) != null) {
                int popCounter = this.playerList.get(event.getEntity().getName());
                ++popCounter;
                this.playerList.put(event.getEntity().getName(), popCounter);
                this.sendMessage(this.formatName(event.getEntity().getName()) + " popped " + this.formatNumber(popCounter) + " totems" + this.ending());
            }
        }, new Predicate[0]);
        this.popListener = (Listener<PacketEvent.Receive>)new Listener(event -> {
            if (TotemPopCounter.mc.player == null) {
                return;
            }
            if (event.getPacket() instanceof SPacketEntityStatus) {
                final SPacketEntityStatus packet = (SPacketEntityStatus)event.getPacket();
                if (packet.getOpCode() == 35) {
                    final Entity entity = packet.getEntity((World)TotemPopCounter.mc.world);
                    if (this.friendCheck(entity.getName()) || this.selfCheck(entity.getName())) {
                        BaseMod.EVENT_BUS.post((Object)new EntityUseTotem(entity));
                    }
                }
            }
        }, new Predicate[0]);
    }
    
    public void onUpdate() {
        if (!this.isDead && this.resetSelfDeaths.getValue() && 0.0f >= TotemPopCounter.mc.player.getHealth()) {
            this.sendMessage(this.formatName(TotemPopCounter.mc.player.getName()) + " died and " + this.grammar(TotemPopCounter.mc.player.getName()) + " pop list was reset!");
            this.isDead = true;
            this.playerList.clear();
            return;
        }
        if (this.isDead && 0.0f < TotemPopCounter.mc.player.getHealth()) {
            this.isDead = false;
        }
        for (final EntityPlayer player : TotemPopCounter.mc.world.playerEntities) {
            if (this.resetDeaths.getValue() && 0.0f >= player.getHealth() && this.friendCheck(player.getName()) && this.selfCheck(player.getName()) && this.playerList.containsKey(player.getName())) {
                this.sendMessage(this.formatName(player.getName()) + " died after popping " + this.formatNumber(this.playerList.get(player.getName())) + " totems" + this.ending());
                this.playerList.remove(player.getName(), this.playerList.get(player.getName()));
            }
        }
    }
    
    private boolean friendCheck(final String name) {
        if (this.isDead) {
            return false;
        }
        for (final Friends.Friend names : Friends.friends.getValue()) {
            if (names.getUsername().equalsIgnoreCase(name)) {
                return this.countFriends.getValue();
            }
        }
        return true;
    }
    
    private boolean selfCheck(final String name) {
        return !this.isDead && ((this.countSelf.getValue() && name.equalsIgnoreCase(TotemPopCounter.mc.player.getName())) || this.countSelf.getValue() || !name.equalsIgnoreCase(TotemPopCounter.mc.player.getName()));
    }
    
    private boolean isSelf(final String name) {
        return name.equalsIgnoreCase(TotemPopCounter.mc.player.getName());
    }
    
    private boolean isFriend(final String name) {
        for (final Friends.Friend names : Friends.friends.getValue()) {
            if (names.getUsername().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
    
    private String formatName(String name) {
        String extraText = "";
        if (this.isFriend(name) && !this.isPublic()) {
            extraText = "Your friend, ";
        }
        else if (this.isFriend(name) && this.isPublic()) {
            extraText = "My friend, ";
        }
        if (this.isSelf(name)) {
            extraText = "";
            name = "I";
        }
        if (this.announceSetting.getValue().equals(Announce.EVERYONE)) {
            return extraText + name;
        }
        return extraText + this.setToText(this.colourCode.getValue()) + name + TextFormatting.RESET;
    }
    
    private String grammar(final String name) {
        if (this.isSelf(name)) {
            return "my";
        }
        return "their";
    }
    
    private String ending() {
        if (this.thanksTo.getValue()) {
            return " thanks to Veteran Hack!";
        }
        return "!";
    }
    
    private boolean isPublic() {
        return this.announceSetting.getValue().equals(Announce.EVERYONE);
    }
    
    private String formatNumber(final int message) {
        if (this.announceSetting.getValue().equals(Announce.EVERYONE)) {
            return "" + message;
        }
        return this.setToText(this.colourCode1.getValue()) + "" + message + TextFormatting.RESET;
    }
    
    private void sendMessage(final String message) {
        switch (this.announceSetting.getValue()) {
            case CLIENT: {
                Command.sendChatMessage(message);
            }
            case EVERYONE: {
                Command.sendServerMessage(message);
            }
            default: {}
        }
    }
    
    private TextFormatting setToText(final ColourTextFormatting.ColourCode colourCode) {
        return ColourTextFormatting.toTextMap.get(colourCode);
    }
    
    private enum Announce
    {
        CLIENT, 
        EVERYONE;
    }
}
