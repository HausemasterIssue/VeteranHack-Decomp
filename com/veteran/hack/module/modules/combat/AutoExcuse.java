//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.combat;

import com.veteran.hack.module.*;
import java.util.*;

@Module.Info(category = Module.Category.COMBAT, name = "Automatic Excuse", description = "Tells other players why they shouldnt have killed you")
public class AutoExcuse extends Module
{
    int diedTime;
    
    public AutoExcuse() {
        this.diedTime = 0;
    }
    
    public void onUpdate() {
        if (this.diedTime > 0) {
            --this.diedTime;
        }
        if (AutoExcuse.mc.player.isDead) {
            this.diedTime = 500;
        }
        if (!AutoExcuse.mc.player.isDead && this.diedTime > 0) {
            final Random rand = new Random();
            final int randomNum = rand.nextInt(6) + 1;
            if (randomNum == 1) {
                AutoExcuse.mc.player.sendChatMessage("your ping is so good :(((( why are you targeting me");
            }
            if (randomNum == 2) {
                AutoExcuse.mc.player.sendChatMessage("i was in my inventoryyyyyyy");
            }
            if (randomNum == 3) {
                AutoExcuse.mc.player.sendChatMessage("i was configuring my settings bro im not ez i promise");
            }
            if (randomNum == 4) {
                AutoExcuse.mc.player.sendChatMessage("I was tabbed out of minecraft dude");
            }
            if (randomNum == 5) {
                AutoExcuse.mc.player.sendChatMessage("i was was deSynced :(");
            }
            if (randomNum == 6) {
                AutoExcuse.mc.player.sendChatMessage("youre hacking bro not cool :/");
            }
            this.diedTime = 0;
        }
    }
}
