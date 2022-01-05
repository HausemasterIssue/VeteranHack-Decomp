//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.combat;

import net.minecraft.entity.player.*;
import net.minecraftforge.event.entity.player.*;
import me.zero.alpine.listener.*;
import com.veteran.hack.event.events.*;
import com.veteran.hack.setting.*;
import java.util.function.*;
import com.veteran.hack.module.*;
import java.util.*;
import net.minecraft.client.gui.*;

@Module.Info(name = "AutoEZ", category = Module.Category.CHAT, description = "Sends an insult in chat after killing someone")
public class AutoEZ extends Module
{
    private Setting<Mode> mode;
    EntityPlayer focus;
    int hasBeenCombat;
    @EventHandler
    public Listener<AttackEntityEvent> livingDeathEventListener;
    @EventHandler
    public Listener<GuiScreenEvent.Displayed> listener;
    
    public AutoEZ() {
        this.mode = (Setting<Mode>)this.register((Setting)Settings.e("Mode", Mode.ONTOP));
        this.livingDeathEventListener = (Listener<AttackEntityEvent>)new Listener(event -> {
            if (event.getTarget() instanceof EntityPlayer) {
                this.focus = (EntityPlayer)event.getTarget();
                if (event.getEntityPlayer().getUniqueID() == AutoEZ.mc.player.getUniqueID()) {
                    if (this.focus.getHealth() <= 0.0 || this.focus.isDead || !AutoEZ.mc.world.playerEntities.contains(this.focus)) {
                        AutoEZ.mc.player.sendChatMessage(this.getText(this.mode.getValue()) + event.getTarget().getName());
                        return;
                    }
                    this.hasBeenCombat = 1000;
                }
            }
        }, new Predicate[0]);
        this.listener = (Listener<GuiScreenEvent.Displayed>)new Listener(event -> {
            if (!(event.getScreen() instanceof GuiGameOver)) {
                return;
            }
            if (AutoEZ.mc.player.getHealth() > 0.0f) {
                this.hasBeenCombat = 0;
            }
        }, new Predicate[0]);
    }
    
    private String getText(final Mode m) {
        return m.text;
    }
    
    public void onUpdate() {
        if (this.hasBeenCombat > 0 && (this.focus.getHealth() <= 0.0f || this.focus.isDead || !AutoEZ.mc.world.playerEntities.contains(this.focus))) {
            if (ModuleManager.getModuleByName("AutoEZ").isEnabled()) {
                final Random rand = new Random();
                final int randomNum = rand.nextInt(6) + 1;
                if (randomNum == 1) {
                    AutoEZ.mc.player.sendChatMessage("You just got Killed by a rusher," + this.focus.getName());
                }
                if (randomNum == 2) {
                    AutoEZ.mc.player.sendChatMessage("Team vet ontop " + this.focus.getName());
                }
                if (randomNum == 4) {
                    AutoEZ.mc.player.sendChatMessage("I just removed " + this.focus.getName() + " with VeteranHack!");
                }
                if (randomNum == 3) {
                    AutoEZ.mc.player.sendChatMessage("Everybody whip and naenae; I just killed " + this.focus.getName() + " With VeteranHack!!!");
                }
                if (randomNum == 5) {
                    AutoEZ.mc.player.sendChatMessage(this.focus.getName() + " Is a little rusher baby.");
                }
                if (randomNum == 6) {
                    AutoEZ.mc.player.sendChatMessage("Clown Down " + this.focus.getName() + ".");
                }
            }
            this.hasBeenCombat = 0;
        }
        --this.hasBeenCombat;
    }
    
    public enum Mode
    {
        FUCKED("You just got Killed by a rusher, "), 
        ONTOP("DVDGOD ONTOP FOREVER AND ALWAYS!, EZ, "), 
        REMOVED("I just removed a hole camping faggot thanks to VeteranHack. Don't even bother coming back, "), 
        NAENAE("Everybody whip and naenae, Using VeteranHack I just killed "), 
        CLIPPED("VeteranHack forever ontop! Thanks for the clip, ");
        
        private String text;
        
        private Mode(final String text) {
            this.text = text;
        }
    }
}
