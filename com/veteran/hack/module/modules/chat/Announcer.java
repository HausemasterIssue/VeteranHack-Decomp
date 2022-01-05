//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.chat;

import com.veteran.hack.module.*;
import com.veteran.hack.setting.*;
import net.minecraft.init.*;

@Module.Info(name = "Announcer", description = "annoys chat lmaoo", category = Module.Category.CHAT)
public class Announcer extends Module
{
    private int delay;
    private Setting DelayChange;
    
    public Announcer() {
        this.delay = 0;
        this.DelayChange = this.register((Setting)Settings.integerBuilder("Delay").withRange(1, 100).withValue(10).build());
    }
    
    public void onUpdate() {
        ++this.delay;
        if (this.delay > this.DelayChange.getValue() * 40) {
            if (Announcer.mc.player.sleeping) {
                Announcer.mc.player.sendChatMessage("I'm Sleeping Thanks To VeteranHack!");
            }
            if (Announcer.mc.player.inWater) {
                Announcer.mc.player.sendChatMessage("I'm Swimming Thanks To VeteranHack!");
            }
            if (Announcer.mc.player.isDead) {
                Announcer.mc.player.sendChatMessage("I Died With VeteranHack Installed");
            }
            if (Announcer.mc.player.isInWeb) {
                Announcer.mc.player.sendChatMessage("I'm In Webs with VeteranHack!");
            }
            if (Announcer.mc.player.inPortal) {
                Announcer.mc.player.sendChatMessage("I'm Traveling Through A Portal Thanks To VeteranHack!");
            }
            if (Announcer.mc.player.isSneaking()) {
                Announcer.mc.player.sendChatMessage("I'm Sneaking Thanks To VeteranHack!");
            }
            if (Announcer.mc.player.isElytraFlying()) {
                Announcer.mc.player.sendChatMessage("I'm flying like an eagle with VeteranHack elytrafly ");
            }
            if (Announcer.mc.fpsCounter == 69) {
                Announcer.mc.player.sendChatMessage("I'm At 69 Fps with To VeteranHack!");
            }
            if (Announcer.mc.player.glowing) {
                Announcer.mc.player.sendChatMessage("I'm Glowing Thanks To VeteranHack!");
            }
            if (Announcer.mc.player.isInLava()) {
                Announcer.mc.player.sendChatMessage("I'm Swimming Thanks To VeteranHack");
            }
            if (Announcer.mc.player.isSwingInProgress && Announcer.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL) {
                Announcer.mc.player.sendChatMessage("You're getting crystalled with VeteranHack Autocrystal!");
            }
            if (Announcer.mc.world.isRaining()) {
                Announcer.mc.player.sendChatMessage("God Damn, Its Raining Cats and Dogs Out There!");
            }
            if (Announcer.mc.playerController.isHittingBlock && Announcer.mc.player.getHeldItemMainhand().getItem() == Items.DIAMOND_PICKAXE) {
                Announcer.mc.player.sendChatMessage("Im removing thanks to VeteranHack");
            }
            if (Announcer.mc.player.isSwingInProgress && Announcer.mc.player.getHeldItemMainhand().getItem() == Items.DIAMOND_SWORD) {
                Announcer.mc.player.sendChatMessage("I'm stabbing you to death thanks to VeteranHack!");
            }
            if (Announcer.mc.player.getHeldItemMainhand().getItem() == Items.GOLDEN_APPLE) {
                Announcer.mc.player.sendChatMessage("I'm Gapping thanks to VeteranHack!");
            }
            if (Announcer.mc.player.getHeldItemMainhand().getItem() == Items.EXPERIENCE_BOTTLE) {
                Announcer.mc.player.sendChatMessage("I'm mending my armor Thanks to VeteranHack!");
            }
            if (Announcer.mc.player.getHeldItemMainhand().getItem() == Items.CHORUS_FRUIT) {
                Announcer.mc.player.sendChatMessage("I'm Leaving like a Black Dad Thanks to VeteranHack!");
            }
            if (Announcer.mc.player.getHeldItemMainhand().getItem() == Items.POTIONITEM) {
                Announcer.mc.player.sendChatMessage("Bottoms up! I'm Potting thanks to VeteranHack!");
            }
            if (Announcer.mc.player.getHeldItemMainhand().getItem() == Items.BOW) {
                Announcer.mc.player.sendChatMessage("I'm a little larper bowspam faggot who deserves to get raped and then die!");
            }
            this.delay = 0;
        }
    }
}
