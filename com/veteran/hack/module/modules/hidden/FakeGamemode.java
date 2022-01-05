//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.hidden;

import com.veteran.hack.module.*;
import net.minecraft.world.*;
import com.veteran.hack.setting.*;
import net.minecraft.client.*;
import com.veteran.hack.command.*;

@Module.Info(name = "FakeGamemode", description = "Fakes your current gamemode client side", category = Module.Category.HIDDEN)
public class FakeGamemode extends Module
{
    private Setting<GamemodeChanged> gamemode;
    private Setting<Boolean> disable2b;
    private GameType gameType;
    
    public FakeGamemode() {
        this.gamemode = (Setting<GamemodeChanged>)this.register((Setting)Settings.e("Mode", GamemodeChanged.CREATIVE));
        this.disable2b = (Setting<Boolean>)this.register((Setting)Settings.b("AntiKick 2b2t", true));
    }
    
    public void onUpdate() {
        if (FakeGamemode.mc.player == null) {
            return;
        }
        if (Minecraft.getMinecraft().getCurrentServerData() == null || (Minecraft.getMinecraft().getCurrentServerData() != null && Minecraft.getMinecraft().getCurrentServerData().serverIP.equalsIgnoreCase("2b2t.org"))) {
            if (FakeGamemode.mc.player.dimension == 1 && this.disable2b.getValue()) {
                Command.sendWarningMessage(this.getChatName() + " Using this on 2b2t queue might get you kicked, please disable the AntiKick option if you're sure");
                this.disable();
            }
            return;
        }
        if (this.gamemode.getValue().equals(GamemodeChanged.CREATIVE)) {
            FakeGamemode.mc.playerController.setGameType(this.gameType);
            FakeGamemode.mc.playerController.setGameType(GameType.CREATIVE);
        }
        else if (this.gamemode.getValue().equals(GamemodeChanged.SURVIVAL)) {
            FakeGamemode.mc.playerController.setGameType(this.gameType);
            FakeGamemode.mc.playerController.setGameType(GameType.SURVIVAL);
        }
        else if (this.gamemode.getValue().equals(GamemodeChanged.ADVENTURE)) {
            FakeGamemode.mc.playerController.setGameType(this.gameType);
            FakeGamemode.mc.playerController.setGameType(GameType.ADVENTURE);
        }
        else if (this.gamemode.getValue().equals(GamemodeChanged.SPECTATOR)) {
            FakeGamemode.mc.playerController.setGameType(this.gameType);
            FakeGamemode.mc.playerController.setGameType(GameType.SPECTATOR);
        }
    }
    
    public void onEnable() {
        if (FakeGamemode.mc.player == null) {
            this.disable();
        }
        else {
            this.gameType = FakeGamemode.mc.playerController.getCurrentGameType();
        }
    }
    
    public void onDisable() {
        if (FakeGamemode.mc.player == null) {
            return;
        }
        FakeGamemode.mc.playerController.setGameType(this.gameType);
    }
    
    private enum GamemodeChanged
    {
        SURVIVAL, 
        CREATIVE, 
        ADVENTURE, 
        SPECTATOR;
    }
}
