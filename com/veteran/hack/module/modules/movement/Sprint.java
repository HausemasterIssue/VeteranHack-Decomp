//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.movement;

import com.veteran.hack.module.*;

@Module.Info(name = "Sprint", description = "Automatically makes the player sprint", category = Module.Category.MOVEMENT, showOnArray = Module.ShowOnArray.OFF)
public class Sprint extends Module
{
    public void onUpdate() {
        if (Sprint.mc.player == null) {
            return;
        }
        if (ModuleManager.getModuleByName("ElytraFlight").isEnabled() && (Sprint.mc.player.isElytraFlying() || Sprint.mc.player.capabilities.isFlying)) {
            return;
        }
        try {
            if (!Sprint.mc.player.collidedHorizontally && Sprint.mc.player.moveForward > 0.0f) {
                Sprint.mc.player.setSprinting(true);
            }
            else {
                Sprint.mc.player.setSprinting(false);
            }
        }
        catch (Exception ex) {}
    }
}
