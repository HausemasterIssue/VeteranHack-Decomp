//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.gui;

import com.veteran.hack.setting.*;
import net.minecraft.util.text.*;
import java.util.*;
import com.veteran.hack.util.*;
import net.minecraft.client.*;
import com.veteran.hack.module.*;
import com.veteran.hack.module.modules.combat.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import com.veteran.hack.command.*;
import net.minecraft.item.*;
import java.util.function.*;

@Module.Info(name = "InfoOverlay", category = Module.Category.GUI, description = "Configures the game information overlay", showOnArray = Module.ShowOnArray.OFF)
public class InfoOverlay extends Module
{
    private Setting<Page> page;
    private Setting<Boolean> version;
    private Setting<Boolean> username;
    private Setting<Boolean> tps;
    private Setting<Boolean> fps;
    private Setting<Boolean> ping;
    private Setting<Boolean> durability;
    private Setting<Boolean> memory;
    private Setting<Boolean> totems;
    private Setting<Boolean> endCrystals;
    private Setting<Boolean> caStatus;
    private Setting<Boolean> atStatus;
    private Setting<Boolean> inHole;
    private Setting<Boolean> expBottles;
    private Setting<Boolean> godApples;
    private Setting<Boolean> speed;
    private Setting<SpeedUnit> speedUnit;
    private Setting<Boolean> time;
    private Setting<TimeUtil.TimeType> timeTypeSetting;
    private Setting<TimeUtil.TimeUnit> timeUnitSetting;
    private Setting<Boolean> doLocale;
    public Setting<ColourTextFormatting.ColourCode> firstColour;
    public Setting<ColourTextFormatting.ColourCode> secondColour;
    
    public InfoOverlay() {
        this.page = (Setting<Page>)this.register((Setting)Settings.enumBuilder(Page.class).withName("Page").withValue(Page.ONE).build());
        this.version = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("Version").withValue(true).withVisibility(v -> this.page.getValue().equals(Page.ONE)).build());
        this.username = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("Username").withValue(true).withVisibility(v -> this.page.getValue().equals(Page.ONE)).build());
        this.tps = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("TPS").withValue(true).withVisibility(v -> this.page.getValue().equals(Page.ONE)).build());
        this.fps = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("FPS").withValue(true).withVisibility(v -> this.page.getValue().equals(Page.ONE)).build());
        this.ping = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("Ping").withValue(false).withVisibility(v -> this.page.getValue().equals(Page.ONE)).build());
        this.durability = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("Item Damage").withValue(false).withVisibility(v -> this.page.getValue().equals(Page.ONE)).build());
        this.memory = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("RAM Used").withValue(false).withVisibility(v -> this.page.getValue().equals(Page.ONE)).build());
        this.totems = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("Totems").withValue(false).withVisibility(v -> this.page.getValue().equals(Page.TWO)).build());
        this.endCrystals = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("End Crystals").withValue(false).withVisibility(v -> this.page.getValue().equals(Page.TWO)).build());
        this.caStatus = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("CA Status").withValue(false).withVisibility(v -> this.page.getValue().equals(Page.TWO)).build());
        this.atStatus = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("AT Status").withValue(false).withVisibility(v -> this.page.getValue().equals(Page.TWO)).build());
        this.inHole = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("HOLE Status").withValue(false).withVisibility(v -> this.page.getValue().equals(Page.TWO)).build());
        this.expBottles = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("EXP Bottles").withValue(false).withVisibility(v -> this.page.getValue().equals(Page.TWO)).build());
        this.godApples = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("God Apples").withValue(false).withVisibility(v -> this.page.getValue().equals(Page.TWO)).build());
        this.speed = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("Speed").withValue(true).withVisibility(v -> this.page.getValue().equals(Page.THREE)).build());
        this.speedUnit = (Setting<SpeedUnit>)this.register((Setting)Settings.enumBuilder(SpeedUnit.class).withName("Speed Unit").withValue(SpeedUnit.KMH).withVisibility(v -> this.page.getValue().equals(Page.THREE) && this.speed.getValue()).build());
        this.time = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("Time").withValue(true).withVisibility(v -> this.page.getValue().equals(Page.THREE)).build());
        this.timeTypeSetting = (Setting<TimeUtil.TimeType>)this.register((Setting)Settings.enumBuilder(TimeUtil.TimeType.class).withName("Time Format").withValue(TimeUtil.TimeType.HHMMSS).withVisibility(v -> this.page.getValue().equals(Page.THREE) && this.time.getValue()).build());
        this.timeUnitSetting = (Setting<TimeUtil.TimeUnit>)this.register((Setting)Settings.enumBuilder(TimeUtil.TimeUnit.class).withName("Time Unit").withValue(TimeUtil.TimeUnit.H12).withVisibility(v -> this.page.getValue().equals(Page.THREE) && this.time.getValue()).build());
        this.doLocale = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("Time Show AMPM").withValue(true).withVisibility(v -> this.page.getValue().equals(Page.THREE) && this.time.getValue()).build());
        this.firstColour = (Setting<ColourTextFormatting.ColourCode>)this.register((Setting)Settings.enumBuilder(ColourTextFormatting.ColourCode.class).withName("First Colour").withValue(ColourTextFormatting.ColourCode.WHITE).withVisibility(v -> this.page.getValue().equals(Page.THREE)).build());
        this.secondColour = (Setting<ColourTextFormatting.ColourCode>)this.register((Setting)Settings.enumBuilder(ColourTextFormatting.ColourCode.class).withName("Second Colour").withValue(ColourTextFormatting.ColourCode.BLUE).withVisibility(v -> this.page.getValue().equals(Page.THREE)).build());
    }
    
    public static String getStringColour(final TextFormatting c) {
        return c.toString();
    }
    
    private TextFormatting setToText(final ColourTextFormatting.ColourCode colourCode) {
        return ColourTextFormatting.toTextMap.get(colourCode);
    }
    
    public ArrayList<String> infoContents() {
        final ArrayList<String> infoContents = new ArrayList<String>();
        if (this.version.getValue()) {
            infoContents.add(getStringColour(this.setToText(this.firstColour.getValue())) + "\u1d20\u1d07\u1d1b\u029c\u1d00\u1d04\u1d0b" + getStringColour(this.setToText(this.secondColour.getValue())) + " " + "b2.3");
        }
        if (this.username.getValue()) {
            infoContents.add(getStringColour(this.setToText(this.firstColour.getValue())) + "thanks for using vethack " + InfoOverlay.mc.getSession().getUsername() + " :^)");
        }
        if (this.time.getValue()) {
            infoContents.add(getStringColour(this.setToText(this.firstColour.getValue())) + TimeUtil.getFinalTime(this.setToText(this.secondColour.getValue()), this.setToText(this.firstColour.getValue()), this.timeUnitSetting.getValue(), this.timeTypeSetting.getValue(), this.doLocale.getValue()));
        }
        if (this.tps.getValue()) {
            infoContents.add(getStringColour(this.setToText(this.firstColour.getValue())) + InfoCalculator.tps() + getStringColour(this.setToText(this.secondColour.getValue())) + " tps");
        }
        if (this.fps.getValue()) {
            infoContents.add(getStringColour(this.setToText(this.firstColour.getValue())) + Minecraft.debugFPS + getStringColour(this.setToText(this.secondColour.getValue())) + " fps");
        }
        if (this.speed.getValue()) {
            infoContents.add(getStringColour(this.setToText(this.firstColour.getValue())) + InfoCalculator.speed(this.useUnitKmH()) + getStringColour(this.setToText(this.secondColour.getValue())) + " " + this.unitType(this.speedUnit.getValue()));
        }
        if (this.ping.getValue()) {
            infoContents.add(getStringColour(this.setToText(this.firstColour.getValue())) + InfoCalculator.ping() + getStringColour(this.setToText(this.secondColour.getValue())) + " ms");
        }
        if (this.durability.getValue()) {
            infoContents.add(getStringColour(this.setToText(this.firstColour.getValue())) + InfoCalculator.dura() + getStringColour(this.setToText(this.secondColour.getValue())) + " dura");
        }
        if (this.atStatus.getValue()) {
            final AutoTrap at = (AutoTrap)ModuleManager.getModuleByName("AutoTrap");
            infoContents.add(getStringColour(this.setToText(this.firstColour.getValue())) + "AT: " + getStringColour(this.setToText(this.secondColour.getValue())) + (at.isEnabled() ? "TRUE" : "FALSE"));
        }
        if (this.caStatus.getValue()) {
            final Autocrystal ac = (Autocrystal)ModuleManager.getModuleByName("Autocrystal");
            infoContents.add(getStringColour(this.setToText(this.firstColour.getValue())) + "CA: " + getStringColour(this.setToText(this.secondColour.getValue())) + (ac.isEnabled() ? "TRUE" : "FALSE"));
        }
        if (this.inHole.getValue() && InfoOverlay.mc.world != null) {
            final Vec3d[] holeOffset = { InfoOverlay.mc.player.getPositionVector().add(1.0, 0.0, 0.0), InfoOverlay.mc.player.getPositionVector().add(-1.0, 0.0, 0.0), InfoOverlay.mc.player.getPositionVector().add(0.0, 0.0, 1.0), InfoOverlay.mc.player.getPositionVector().add(0.0, 0.0, -1.0), InfoOverlay.mc.player.getPositionVector().add(0.0, -1.0, 0.0) };
            int holeBlocks = 0;
            int safeHoleBlocks = 0;
            for (final Vec3d o : holeOffset) {
                if (InfoOverlay.mc.world.getBlockState(new BlockPos(o.x, o.y, o.z)).getBlock() == Blocks.BEDROCK) {
                    ++safeHoleBlocks;
                }
                if (InfoOverlay.mc.world.getBlockState(new BlockPos(o.x, o.y, o.z)).getBlock() == Blocks.OBSIDIAN) {
                    ++holeBlocks;
                }
            }
            if (holeBlocks + safeHoleBlocks != 5) {
                infoContents.add(getStringColour(this.setToText(this.firstColour.getValue())) + "HOLE: " + getStringColour(this.setToText(this.secondColour.getValue())) + "UNSAFE");
            }
            if (holeBlocks + safeHoleBlocks == 5 && safeHoleBlocks != 5) {
                infoContents.add(getStringColour(this.setToText(this.firstColour.getValue())) + "HOLE: " + getStringColour(this.setToText(this.secondColour.getValue())) + "OBBY");
            }
            if (safeHoleBlocks == 5) {
                infoContents.add(getStringColour(this.setToText(this.firstColour.getValue())) + "HOLE: " + getStringColour(this.setToText(this.secondColour.getValue())) + "BEDROCK");
            }
        }
        return infoContents;
    }
    
    public void onDisable() {
        Command.sendDisableMessage(this.getName());
    }
    
    public boolean useUnitKmH() {
        return this.speedUnit.getValue().equals(SpeedUnit.KMH);
    }
    
    public static int getItems(final Item i) {
        return InfoOverlay.mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == i).mapToInt(ItemStack::getCount).sum() + InfoOverlay.mc.player.inventory.offHandInventory.stream().filter(itemStack -> itemStack.getItem() == i).mapToInt(ItemStack::getCount).sum();
    }
    
    private String unitType(final SpeedUnit s) {
        switch (s) {
            case MPS: {
                return "m/s";
            }
            case KMH: {
                return "km/h";
            }
            default: {
                return "Invalid unit type (mps or kmh)";
            }
        }
    }
    
    private enum SpeedUnit
    {
        MPS, 
        KMH;
    }
    
    private enum Page
    {
        ONE, 
        TWO, 
        THREE;
    }
}
