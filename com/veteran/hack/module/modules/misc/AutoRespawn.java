//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.misc;

import com.veteran.hack.module.*;
import com.veteran.hack.event.events.*;
import me.zero.alpine.listener.*;
import com.veteran.hack.setting.*;
import java.util.function.*;
import com.veteran.hack.command.*;
import net.minecraft.client.gui.*;

@Module.Info(name = "AutoRespawn", description = "Automatically respawn after dying", category = Module.Category.MISC)
public class AutoRespawn extends Module
{
    private Setting<Boolean> respawn;
    private Setting<Boolean> deathCoords;
    private Setting<Boolean> antiGlitchScreen;
    @EventHandler
    public Listener<GuiScreenEvent.Displayed> listener;
    
    public AutoRespawn() {
        this.respawn = (Setting<Boolean>)this.register((Setting)Settings.b("Respawn", true));
        this.deathCoords = (Setting<Boolean>)this.register((Setting)Settings.b("DeathCoords", true));
        this.antiGlitchScreen = (Setting<Boolean>)this.register((Setting)Settings.b("Anti Glitch Screen", true));
        this.listener = (Listener<GuiScreenEvent.Displayed>)new Listener(event -> {
            if (!(event.getScreen() instanceof GuiGameOver)) {
                return;
            }
            if (this.deathCoords.getValue() && AutoRespawn.mc.player.getHealth() <= 0.0f) {
                Command.sendChatMessage(String.format("You died at x %d y %d z %d", (int)AutoRespawn.mc.player.posX, (int)AutoRespawn.mc.player.posY, (int)AutoRespawn.mc.player.posZ));
            }
            if (this.respawn.getValue() || (this.antiGlitchScreen.getValue() && AutoRespawn.mc.player.getHealth() > 0.0f)) {
                AutoRespawn.mc.player.respawnPlayer();
                AutoRespawn.mc.displayGuiScreen((GuiScreen)null);
            }
        }, new Predicate[0]);
    }
}
