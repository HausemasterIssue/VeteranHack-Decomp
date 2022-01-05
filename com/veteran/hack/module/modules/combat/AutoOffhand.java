//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.combat;

import com.veteran.hack.setting.*;
import com.veteran.hack.setting.builder.*;
import com.veteran.hack.module.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import java.util.function.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import net.minecraft.entity.item.*;
import java.util.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import java.util.stream.*;

@Module.Info(name = "BetterOffhand", category = Module.Category.COMBAT, description = "Refills your offhand with totems or other items")
public class AutoOffhand extends Module
{
    private Setting<Mode> modeSetting;
    private Setting<Boolean> smartOffhand;
    public Setting<Double> healthSetting;
    private Setting<CustomItem> smartItemSetting;
    private Setting<Boolean> holeCheck;
    private Setting<Boolean> crystalCheck;
    int holeBlocks;
    int totems;
    boolean moving;
    boolean returnI;
    Autocrystal ca;
    
    public AutoOffhand() {
        this.modeSetting = (Setting<Mode>)this.register((Setting)Settings.e("Mode", Mode.REPLACE_OFFHAND));
        this.smartOffhand = (Setting<Boolean>)this.register((SettingBuilder)Settings.booleanBuilder("Custom Item").withValue(true).withVisibility(v -> this.modeSetting.getValue().equals(Mode.REPLACE_OFFHAND)));
        this.healthSetting = (Setting<Double>)this.register((SettingBuilder)Settings.doubleBuilder("Custom Item Health").withValue(21.0).withVisibility(v -> this.smartOffhand.getValue() && this.modeSetting.getValue().equals(Mode.REPLACE_OFFHAND)));
        this.smartItemSetting = (Setting<CustomItem>)this.register((Setting)Settings.enumBuilder(CustomItem.class).withName("Item").withValue(CustomItem.CRYSTAL).withVisibility(v -> this.smartOffhand.getValue()).build());
        this.holeCheck = (Setting<Boolean>)this.register((SettingBuilder)Settings.booleanBuilder("HoleCheck").withValue(false).withVisibility(v -> this.modeSetting.getValue().equals(Mode.REPLACE_OFFHAND)));
        this.crystalCheck = (Setting<Boolean>)this.register((SettingBuilder)Settings.booleanBuilder("CrystalCheck").withValue(false).withVisibility(v -> this.modeSetting.getValue().equals(Mode.REPLACE_OFFHAND)));
        this.moving = false;
        this.returnI = false;
        this.ca = new Autocrystal();
    }
    
    public void onUpdate() {
        this.holeBlocks = 0;
        if (AutoOffhand.mc.world == null) {
            return;
        }
        this.ca = (Autocrystal)ModuleManager.getModuleByName("Autocrystal");
        if (this.holeCheck.getValue()) {
            final Vec3d[] array;
            final Vec3d[] holeOffset = array = new Vec3d[] { AutoOffhand.mc.player.getPositionVector().add(1.0, 0.0, 0.0), AutoOffhand.mc.player.getPositionVector().add(-1.0, 0.0, 0.0), AutoOffhand.mc.player.getPositionVector().add(0.0, 0.0, 1.0), AutoOffhand.mc.player.getPositionVector().add(0.0, 0.0, -1.0), AutoOffhand.mc.player.getPositionVector().add(0.0, -1.0, 0.0) };
            for (final Vec3d v : array) {
                final BlockPos offset = new BlockPos(v.x, v.y, v.z);
                if (AutoOffhand.mc.world.getBlockState(offset).getBlock() == Blocks.OBSIDIAN || AutoOffhand.mc.world.getBlockState(offset).getBlock() == Blocks.BEDROCK) {
                    ++this.holeBlocks;
                }
            }
        }
        if (!this.modeSetting.getValue().equals(Mode.INVENTORY) && AutoOffhand.mc.currentScreen instanceof GuiContainer) {
            return;
        }
        if (this.returnI) {
            int t = -1;
            for (int i = 0; i < 45; ++i) {
                if (AutoOffhand.mc.player.inventory.getStackInSlot(i).isEmpty) {
                    t = i;
                    break;
                }
            }
            if (t == -1) {
                return;
            }
            AutoOffhand.mc.playerController.windowClick(0, (t < 9) ? (t + 36) : t, 0, ClickType.PICKUP, (EntityPlayer)AutoOffhand.mc.player);
            this.returnI = false;
        }
        this.totems = AutoOffhand.mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == this.settingToItem()).mapToInt(ItemStack::getCount).sum();
        if (AutoOffhand.mc.player.getHeldItemOffhand().getItem() == this.settingToItem()) {
            ++this.totems;
        }
        else {
            if (!this.modeSetting.getValue().equals(Mode.REPLACE_OFFHAND) && !AutoOffhand.mc.player.getHeldItemOffhand().isEmpty) {
                return;
            }
            if (this.moving) {
                AutoOffhand.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer)AutoOffhand.mc.player);
                this.moving = false;
                if (!AutoOffhand.mc.player.inventory.itemStack.isEmpty()) {
                    this.returnI = true;
                }
                return;
            }
            if (AutoOffhand.mc.player.inventory.itemStack.isEmpty()) {
                if (this.totems == 0) {
                    return;
                }
                int t = -1;
                for (int i = 0; i < 45; ++i) {
                    if (AutoOffhand.mc.player.inventory.getStackInSlot(i).getItem() == this.settingToItem()) {
                        t = i;
                        break;
                    }
                }
                if (t == -1) {
                    return;
                }
                AutoOffhand.mc.playerController.windowClick(0, (t < 9) ? (t + 36) : t, 0, ClickType.PICKUP, (EntityPlayer)AutoOffhand.mc.player);
                this.moving = true;
            }
            else if (this.modeSetting.getValue().equals(Mode.REPLACE_OFFHAND)) {
                int t = -1;
                for (int i = 0; i < 45; ++i) {
                    if (AutoOffhand.mc.player.inventory.getStackInSlot(i).isEmpty) {
                        t = i;
                        break;
                    }
                }
                if (t == -1) {
                    return;
                }
                AutoOffhand.mc.playerController.windowClick(0, (t < 9) ? (t + 36) : t, 0, ClickType.PICKUP, (EntityPlayer)AutoOffhand.mc.player);
            }
        }
    }
    
    private Item settingToItem() {
        if (!this.smartOffhand.getValue() || this.passHealthCheck() || (this.holeCheck.getValue() && this.holeBlocks != 5)) {
            return Items.TOTEM_OF_UNDYING;
        }
        switch (this.smartItemSetting.getValue()) {
            case GAPPLE: {
                return Items.GOLDEN_APPLE;
            }
            case CRYSTAL: {
                return Items.END_CRYSTAL;
            }
            default: {
                return null;
            }
        }
    }
    
    private boolean passHealthCheck() {
        if (this.modeSetting.getValue().equals(Mode.REPLACE_OFFHAND)) {
            if (this.crystalCheck.getValue()) {
                final EntityEnderCrystal crystal = (EntityEnderCrystal)AutoOffhand.mc.world.loadedEntityList.stream().filter(entity -> entity instanceof EntityEnderCrystal).map(entity -> entity).min(Comparator.comparing(c -> AutoOffhand.mc.player.getDistance(c))).orElse(null);
                if (crystal == null || crystal.getPosition().distanceSq((double)AutoOffhand.mc.player.getPosition().x, (double)AutoOffhand.mc.player.getPosition().y, (double)AutoOffhand.mc.player.getPosition().z) <= 16.0) {}
            }
            return AutoOffhand.mc.player.getHealth() + AutoOffhand.mc.player.getAbsorptionAmount() <= this.healthSetting.getValue() || !this.isCrystalsAABBEmpty();
        }
        return true;
    }
    
    private boolean isEmpty(final BlockPos pos) {
        final List<Entity> crystalsInAABB = (List<Entity>)AutoOffhand.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(pos)).stream().filter(e -> e instanceof EntityEnderCrystal).collect(Collectors.toList());
        return crystalsInAABB.isEmpty();
    }
    
    private boolean isCrystalsAABBEmpty() {
        return this.isEmpty(AutoOffhand.mc.player.getPosition().add(1, 0, 0)) && this.isEmpty(AutoOffhand.mc.player.getPosition().add(-1, 0, 0)) && this.isEmpty(AutoOffhand.mc.player.getPosition().add(0, 0, 1)) && this.isEmpty(AutoOffhand.mc.player.getPosition().add(0, 0, -1)) && this.isEmpty(AutoOffhand.mc.player.getPosition());
    }
    
    public String getHudInfo() {
        return String.valueOf(this.totems);
    }
    
    private enum Mode
    {
        NEITHER, 
        REPLACE_OFFHAND, 
        INVENTORY;
    }
    
    private enum CustomItem
    {
        CRYSTAL, 
        GAPPLE;
    }
}
