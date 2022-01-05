//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.hidden;

import com.veteran.hack.module.*;

@Module.Info(name = "AntiWeather", description = "Removes rain from your world", category = Module.Category.HIDDEN)
public class AntiWeather extends Module
{
    public void onUpdate() {
        if (this.isDisabled()) {
            return;
        }
        if (AntiWeather.mc.world.isRaining()) {
            AntiWeather.mc.world.setRainStrength(0.0f);
        }
    }
}
