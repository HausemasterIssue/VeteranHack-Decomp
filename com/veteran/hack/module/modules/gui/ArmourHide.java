//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.gui;

import com.veteran.hack.module.*;
import com.veteran.hack.setting.*;
import net.minecraft.inventory.*;

@Module.Info(name = "ArmourHide", category = Module.Category.GUI, description = "Hides the armour on selected entities", showOnArray = Module.ShowOnArray.OFF)
public class ArmourHide extends Module
{
    public Setting<Boolean> player;
    public Setting<Boolean> armourstand;
    public Setting<Boolean> mobs;
    public Setting<Boolean> helmet;
    public Setting<Boolean> chestplate;
    public Setting<Boolean> leggins;
    public Setting<Boolean> boots;
    public static ArmourHide INSTANCE;
    
    public ArmourHide() {
        this.player = (Setting<Boolean>)this.register((Setting)Settings.b("Players", false));
        this.armourstand = (Setting<Boolean>)this.register((Setting)Settings.b("Armour Stands", true));
        this.mobs = (Setting<Boolean>)this.register((Setting)Settings.b("Mobs", true));
        this.helmet = (Setting<Boolean>)this.register((Setting)Settings.b("Helmet", false));
        this.chestplate = (Setting<Boolean>)this.register((Setting)Settings.b("Chestplate", false));
        this.leggins = (Setting<Boolean>)this.register((Setting)Settings.b("Leggings", false));
        this.boots = (Setting<Boolean>)this.register((Setting)Settings.b("Boots", false));
        ArmourHide.INSTANCE = this;
    }
    
    public static boolean shouldRenderPiece(final EntityEquipmentSlot slotIn) {
        return (slotIn == EntityEquipmentSlot.HEAD && ArmourHide.INSTANCE.helmet.getValue()) || (slotIn == EntityEquipmentSlot.CHEST && ArmourHide.INSTANCE.chestplate.getValue()) || (slotIn == EntityEquipmentSlot.LEGS && ArmourHide.INSTANCE.leggins.getValue()) || (slotIn == EntityEquipmentSlot.FEET && ArmourHide.INSTANCE.boots.getValue());
    }
}
