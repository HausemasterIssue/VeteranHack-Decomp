//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.combat;

import com.veteran.hack.module.*;
import com.veteran.hack.setting.*;
import com.veteran.hack.util.*;
import com.veteran.hack.gui.rgui.render.font.*;
import net.minecraft.item.*;

@Module.Info(name = "Durability Warning", description = "displays a warning when your armor is below a threshold", category = Module.Category.COMBAT)
public class ArmorWarning extends Module
{
    private Setting<Integer> threshold;
    
    public ArmorWarning() {
        this.threshold = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Warning Threshold").withMinimum(5).withValue(25).withMaximum(100).build());
    }
    
    public void onRender() {
        if (this.shouldMend(0) || this.shouldMend(1) || this.shouldMend(2) || this.shouldMend(3)) {
            final String text = "Armor below " + String.valueOf(this.threshold.getValue() + "% !");
            final FontRenderer renderer = Wrapper.getFontRenderer();
            final int divider = getScale();
            renderer.drawStringWithShadow(ArmorWarning.mc.displayWidth / divider / 2 - renderer.getStringWidth(text) / 2, ArmorWarning.mc.displayHeight / divider / 2 - 16, 240, 87, 70, text);
        }
    }
    
    private boolean shouldMend(final int i) {
        return ((ItemStack)ArmorWarning.mc.player.inventory.armorInventory.get(i)).getMaxDamage() != 0 && 100 * ((ItemStack)ArmorWarning.mc.player.inventory.armorInventory.get(i)).getItemDamage() / ((ItemStack)ArmorWarning.mc.player.inventory.armorInventory.get(i)).getMaxDamage() > reverseNumber(this.threshold.getValue(), 1, 100);
    }
    
    public static int reverseNumber(final int num, final int min, final int max) {
        return max + min - num;
    }
    
    public static int getScale() {
        int scaleFactor = 0;
        int scale = Wrapper.getMinecraft().gameSettings.guiScale;
        if (scale == 0) {
            scale = 1000;
        }
        while (scaleFactor < scale && Wrapper.getMinecraft().displayWidth / (scaleFactor + 1) >= 320 && Wrapper.getMinecraft().displayHeight / (scaleFactor + 1) >= 240) {
            ++scaleFactor;
        }
        if (scaleFactor == 0) {
            scaleFactor = 1;
        }
        return scaleFactor;
    }
}
