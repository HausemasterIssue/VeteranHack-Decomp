//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.util;

import com.veteran.hack.module.*;
import java.text.*;
import net.minecraft.util.math.*;
import net.minecraft.item.*;

public class InfoCalculator extends Module
{
    private static DecimalFormat formatter;
    
    public static int ping() {
        if (InfoCalculator.mc.getConnection() == null) {
            return 1;
        }
        if (InfoCalculator.mc.player == null) {
            return -1;
        }
        try {
            return InfoCalculator.mc.getConnection().getPlayerInfo(InfoCalculator.mc.player.getUniqueID()).getResponseTime();
        }
        catch (NullPointerException ex) {
            return -1;
        }
    }
    
    public static String speed(final boolean useUnitKmH) {
        final float currentTps = InfoCalculator.mc.timer.tickLength / 1000.0f;
        double multiply = 1.0;
        if (useUnitKmH) {
            multiply = 3.6;
        }
        return InfoCalculator.formatter.format(MathHelper.sqrt(Math.pow(coordsDiff("x"), 2.0) + Math.pow(coordsDiff("z"), 2.0)) / currentTps * multiply);
    }
    
    private static double coordsDiff(final String s) {
        switch (s) {
            case "x": {
                return InfoCalculator.mc.player.posX - InfoCalculator.mc.player.prevPosX;
            }
            case "z": {
                return InfoCalculator.mc.player.posZ - InfoCalculator.mc.player.prevPosZ;
            }
            default: {
                return 0.0;
            }
        }
    }
    
    public static int dura() {
        final ItemStack itemStack = Wrapper.getMinecraft().player.getHeldItemMainhand();
        return itemStack.getMaxDamage() - itemStack.getItemDamage();
    }
    
    public static String memory() {
        return "" + Runtime.getRuntime().freeMemory() / 1000000L;
    }
    
    public static String tps() {
        return "" + Math.round(LagCompensator.INSTANCE.getTickRate());
    }
    
    public static double round(final double value, final int places) {
        final double scale = Math.pow(10.0, places);
        return Math.round(value * scale) / scale;
    }
    
    public static boolean isNumberEven(final int i) {
        return (i & 0x1) == 0x0;
    }
    
    public static int reverseNumber(final int num, final int min, final int max) {
        return max + min - num;
    }
    
    static {
        InfoCalculator.formatter = new DecimalFormat("#.#");
    }
}
