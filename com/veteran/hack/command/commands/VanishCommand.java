//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.command.commands;

import com.veteran.hack.command.*;
import net.minecraft.entity.*;
import net.minecraft.client.*;
import com.veteran.hack.command.syntax.*;

public class VanishCommand extends Command
{
    private static Entity vehicle;
    Minecraft mc;
    
    public VanishCommand() {
        super("vanish", (SyntaxChunk[])null, new String[0]);
        this.mc = Minecraft.getMinecraft();
        this.setDescription("Allows you to vanish using an entity");
    }
    
    public void call(final String[] args) {
        if (this.mc.player.getRidingEntity() != null && VanishCommand.vehicle == null) {
            VanishCommand.vehicle = this.mc.player.getRidingEntity();
            this.mc.player.dismountRidingEntity();
            this.mc.world.removeEntityFromWorld(VanishCommand.vehicle.getEntityId());
            Command.sendChatMessage("Vehicle " + VanishCommand.vehicle.getName() + " removed.");
        }
        else if (VanishCommand.vehicle != null) {
            VanishCommand.vehicle.isDead = false;
            this.mc.world.addEntityToWorld(VanishCommand.vehicle.getEntityId(), VanishCommand.vehicle);
            this.mc.player.startRiding(VanishCommand.vehicle, true);
            Command.sendChatMessage("Vehicle " + VanishCommand.vehicle.getName() + " created.");
            VanishCommand.vehicle = null;
        }
        else {
            Command.sendChatMessage("No Vehicle.");
        }
    }
}
