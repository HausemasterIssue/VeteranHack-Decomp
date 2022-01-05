//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.render;

import com.veteran.hack.module.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import com.veteran.hack.util.*;
import com.veteran.hack.setting.*;

@Module.Info(name = "Chams", category = Module.Category.HIDDEN, description = "See entities through walls")
public class Chams extends Module
{
    private static Setting<Boolean> players;
    private static Setting<Boolean> animals;
    private static Setting<Boolean> mobs;
    
    public Chams() {
        this.registerAll(new Setting[] { Chams.players, Chams.animals, Chams.mobs });
    }
    
    public static boolean renderChams(final Entity entity) {
        return (entity instanceof EntityPlayer) ? Chams.players.getValue() : (EntityUtil.isPassive(entity) ? Chams.animals.getValue() : ((boolean)Chams.mobs.getValue()));
    }
    
    static {
        Chams.players = Settings.b("Players", true);
        Chams.animals = Settings.b("Animals", false);
        Chams.mobs = Settings.b("Mobs", false);
    }
}
