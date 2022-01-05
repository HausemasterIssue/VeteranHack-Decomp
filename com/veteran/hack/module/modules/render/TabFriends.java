//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.render;

import com.veteran.hack.module.*;
import com.veteran.hack.setting.*;
import net.minecraft.client.network.*;
import net.minecraft.scoreboard.*;
import com.veteran.hack.util.*;
import com.veteran.hack.command.*;

@Module.Info(name = "TabFriends", description = "Highlights friends in the tab menu", category = Module.Category.GUI, showOnArray = Module.ShowOnArray.OFF)
public class TabFriends extends Module
{
    public Setting<Boolean> startupGlobal;
    public static TabFriends INSTANCE;
    
    public TabFriends() {
        this.startupGlobal = (Setting<Boolean>)this.register((Setting)Settings.b("Enable Automatically", true));
        TabFriends.INSTANCE = this;
    }
    
    public static String getPlayerName(final NetworkPlayerInfo networkPlayerInfoIn) {
        final String dname = (networkPlayerInfoIn.getDisplayName() != null) ? networkPlayerInfoIn.getDisplayName().getFormattedText() : ScorePlayerTeam.formatPlayerName((Team)networkPlayerInfoIn.getPlayerTeam(), networkPlayerInfoIn.getGameProfile().getName());
        if (Friends.isFriend(dname)) {
            return String.format("%sa%s", '§', dname);
        }
        return dname;
    }
    
    public void onDisable() {
        Command.sendAutoDisableMessage(this.getName(), (boolean)this.startupGlobal.getValue());
    }
}
