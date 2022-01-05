//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.hidden;

import com.veteran.hack.module.*;
import com.veteran.hack.setting.*;
import com.veteran.hack.command.*;

@Module.Info(name = "AutoEncourage", category = Module.Category.HIDDEN, description = "Automatically Encourage player client side")
public class AutoEncourager extends Module
{
    private Setting<Double> delay;
    double delayTimer;
    
    public AutoEncourager() {
        this.delay = (Setting<Double>)this.register((Setting)Settings.d("Delay", 240.0));
        this.delayTimer = (int)(this.delay.getValue() * 20.0 * 2.0);
    }
    
    public void onUpdate() {
        --this.delayTimer;
        if (this.delayTimer == this.delay.getValue() / 2.0) {
            Command.sendChatMessage(AutoEncourager.mc.player.getName() + " remember to gap! Keep your head up king, go crystal those ping players");
        }
        if (this.delayTimer == 0.0) {
            Command.sendChatMessage(AutoEncourager.mc.player.getName() + " bro, you're literally the best at pvp.");
            this.delayTimer = this.delay.getValue() * 20.0 * 2.0;
        }
    }
}
