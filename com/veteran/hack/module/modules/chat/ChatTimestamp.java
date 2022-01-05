//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.chat;

import com.veteran.hack.module.*;
import com.veteran.hack.util.*;
import com.veteran.hack.event.events.*;
import me.zero.alpine.listener.*;
import com.veteran.hack.setting.*;
import java.util.function.*;
import net.minecraft.util.text.*;
import com.veteran.hack.command.*;
import net.minecraft.network.play.server.*;

@Module.Info(name = "ChatTimestamp", category = Module.Category.CHAT, description = "Shows the time a message was sent beside the message", showOnArray = Module.ShowOnArray.OFF)
public class ChatTimestamp extends Module
{
    private Setting<ColourTextFormatting.ColourCode> firstColour;
    private Setting<ColourTextFormatting.ColourCode> secondColour;
    private Setting<TimeUtil.TimeType> timeTypeSetting;
    private Setting<TimeUtil.TimeUnit> timeUnitSetting;
    private Setting<Boolean> doLocale;
    @EventHandler
    public Listener<PacketEvent.Receive> listener;
    
    public ChatTimestamp() {
        this.firstColour = (Setting<ColourTextFormatting.ColourCode>)this.register((Setting)Settings.e("First Colour", ColourTextFormatting.ColourCode.GRAY));
        this.secondColour = (Setting<ColourTextFormatting.ColourCode>)this.register((Setting)Settings.e("Second Colour", ColourTextFormatting.ColourCode.WHITE));
        this.timeTypeSetting = (Setting<TimeUtil.TimeType>)this.register((Setting)Settings.e("Time Format", TimeUtil.TimeType.HHMM));
        this.timeUnitSetting = (Setting<TimeUtil.TimeUnit>)this.register((Setting)Settings.e("Time Unit", TimeUtil.TimeUnit.H12));
        this.doLocale = (Setting<Boolean>)this.register((Setting)Settings.b("Show AMPM", true));
        this.listener = (Listener<PacketEvent.Receive>)new Listener(event -> {
            if (ChatTimestamp.mc.player == null || this.isDisabled()) {
                return;
            }
            if (!(event.getPacket() instanceof SPacketChat)) {
                return;
            }
            final SPacketChat sPacketChat = (SPacketChat)event.getPacket();
            if (this.addTime(sPacketChat.getChatComponent().getUnformattedText())) {
                event.cancel();
            }
        }, new Predicate[0]);
    }
    
    private boolean addTime(final String message) {
        Command.sendRawChatMessage("<" + TimeUtil.getFinalTime(this.setToText(this.secondColour.getValue()), this.setToText(this.firstColour.getValue()), this.timeUnitSetting.getValue(), this.timeTypeSetting.getValue(), this.doLocale.getValue()) + TextFormatting.RESET + "> " + message);
        return true;
    }
    
    private TextFormatting setToText(final ColourTextFormatting.ColourCode colourCode) {
        return ColourTextFormatting.toTextMap.get(colourCode);
    }
}
