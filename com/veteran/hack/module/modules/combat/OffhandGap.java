//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.combat;

import com.veteran.hack.event.events.*;
import me.zero.alpine.listener.*;
import com.veteran.hack.setting.*;
import java.util.function.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import com.veteran.hack.module.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import com.veteran.hack.module.modules.gui.*;
import net.minecraft.network.play.client.*;
import net.minecraft.item.*;

@Module.Info(name = "OffhandGap", category = Module.Category.COMBAT, description = "Holds a God apple when right clicking your sword!")
public class OffhandGap extends Module
{
    private Setting<Double> disableHealth;
    private Setting<Boolean> eatWhileAttacking;
    private Setting<Boolean> swordOrAxeOnly;
    private Setting<Boolean> preferBlocks;
    private Setting<Boolean> holeCheck;
    int gaps;
    boolean BetterOffhandWasEnabled;
    boolean cancelled;
    Item usedItem;
    Item toUseItem;
    int holeBlocks;
    Autocrystal crystalAura;
    @EventHandler
    private Listener<PacketEvent.Send> sendListener;
    
    public OffhandGap() {
        this.disableHealth = (Setting<Double>)this.register((Setting)Settings.doubleBuilder("Disable Health").withMinimum(0.0).withValue(4.0).withMaximum(20.0).build());
        this.eatWhileAttacking = (Setting<Boolean>)this.register((Setting)Settings.b("Eat While Attacking", false));
        this.swordOrAxeOnly = (Setting<Boolean>)this.register((Setting)Settings.b("Sword or Axe Only", true));
        this.preferBlocks = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("Prefer Placing Blocks").withValue(false).withVisibility(v -> !this.swordOrAxeOnly.getValue()).build());
        this.holeCheck = (Setting<Boolean>)this.register((Setting)Settings.b("Hole Check", false));
        this.gaps = -1;
        this.BetterOffhandWasEnabled = false;
        this.cancelled = false;
        this.sendListener = (Listener<PacketEvent.Send>)new Listener(e -> {
            if (e.getPacket() instanceof CPacketPlayerTryUseItem) {
                if (this.cancelled || (this.holeCheck.getValue() && this.holeBlocks != 5.0f)) {
                    this.disableGaps();
                    return;
                }
                if (OffhandGap.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword || OffhandGap.mc.player.getHeldItemMainhand().getItem() instanceof ItemAxe || this.passItemCheck()) {
                    if (ModuleManager.isModuleEnabled("BetterOffhand")) {
                        this.BetterOffhandWasEnabled = true;
                        ModuleManager.getModuleByName("BetterOffhand").disable();
                    }
                    if (!this.eatWhileAttacking.getValue()) {
                        this.usedItem = OffhandGap.mc.player.getHeldItemMainhand().getItem();
                    }
                    this.enableGaps(this.gaps);
                }
            }
            try {
                if (!OffhandGap.mc.gameSettings.keyBindUseItem.isKeyDown() && OffhandGap.mc.player.getHeldItemOffhand().getItem() == Items.GOLDEN_APPLE) {
                    this.disableGaps();
                }
                else if (this.usedItem != OffhandGap.mc.player.getHeldItemMainhand().getItem() && OffhandGap.mc.player.getHeldItemOffhand().getItem() == Items.GOLDEN_APPLE) {
                    if (!this.eatWhileAttacking.getValue()) {
                        this.usedItem = OffhandGap.mc.player.getHeldItemMainhand().getItem();
                        this.disableGaps();
                    }
                }
                else if (OffhandGap.mc.player.getHealth() + OffhandGap.mc.player.getAbsorptionAmount() <= this.disableHealth.getValue()) {
                    this.disableGaps();
                }
            }
            catch (NullPointerException ex) {}
        }, new Predicate[0]);
    }
    
    public void onUpdate() {
        this.holeBlocks = 0;
        if (OffhandGap.mc.world == null) {
            return;
        }
        if (this.holeCheck.getValue()) {
            final Vec3d[] array;
            final Vec3d[] holeOffset = array = new Vec3d[] { OffhandGap.mc.player.getPositionVector().add(1.0, 0.0, 0.0), OffhandGap.mc.player.getPositionVector().add(-1.0, 0.0, 0.0), OffhandGap.mc.player.getPositionVector().add(0.0, 0.0, 1.0), OffhandGap.mc.player.getPositionVector().add(0.0, 0.0, -1.0), OffhandGap.mc.player.getPositionVector().add(0.0, -1.0, 0.0) };
            for (final Vec3d v : array) {
                final BlockPos offset = new BlockPos(v.x, v.y, v.z);
                if (OffhandGap.mc.world.getBlockState(offset).getBlock() == Blocks.OBSIDIAN || OffhandGap.mc.world.getBlockState(offset).getBlock() == Blocks.BEDROCK) {
                    ++this.holeBlocks;
                }
            }
        }
        if (OffhandGap.mc.player == null) {
            return;
        }
        this.cancelled = (OffhandGap.mc.player.getHealth() + OffhandGap.mc.player.getAbsorptionAmount() <= this.disableHealth.getValue());
        if (this.cancelled) {
            this.disableGaps();
            return;
        }
        this.toUseItem = Items.GOLDEN_APPLE;
        if (OffhandGap.mc.player.getHeldItemOffhand().getItem() != Items.GOLDEN_APPLE) {
            for (int i = 0; i < 45; ++i) {
                if (OffhandGap.mc.player.inventory.getStackInSlot(i).getItem() == Items.GOLDEN_APPLE) {
                    this.gaps = i;
                    break;
                }
            }
        }
    }
    
    private boolean passItemCheck() {
        if (this.swordOrAxeOnly.getValue()) {
            return false;
        }
        final Item item = OffhandGap.mc.player.getHeldItemMainhand().getItem();
        return !(item instanceof ItemBow) && !(item instanceof ItemSnowball) && !(item instanceof ItemEgg) && !(item instanceof ItemPotion) && !(item instanceof ItemEnderEye) && !(item instanceof ItemEnderPearl) && !(item instanceof ItemFood) && !(item instanceof ItemShield) && !(item instanceof ItemFlintAndSteel) && !(item instanceof ItemFishingRod) && !(item instanceof ItemArmor) && !(item instanceof ItemExpBottle) && (!this.preferBlocks.getValue() || !(item instanceof ItemBlock));
    }
    
    private void disableGaps() {
        if (this.BetterOffhandWasEnabled != ModuleManager.isModuleEnabled("BetterOffhand")) {
            this.moveGapsToInventory(this.gaps);
            ModuleManager.getModuleByName("BetterOffhand").enable();
            this.BetterOffhandWasEnabled = false;
        }
    }
    
    private void enableGaps(final int slot) {
        if (OffhandGap.mc.player.getHeldItemOffhand().getItem() != Items.GOLDEN_APPLE) {
            OffhandGap.mc.playerController.windowClick(0, (slot < 9) ? (slot + 36) : slot, 0, ClickType.PICKUP, (EntityPlayer)OffhandGap.mc.player);
            OffhandGap.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer)OffhandGap.mc.player);
        }
    }
    
    private void moveGapsToInventory(final int slot) {
        if (OffhandGap.mc.player.getHeldItemOffhand().getItem() == Items.GOLDEN_APPLE) {
            OffhandGap.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer)OffhandGap.mc.player);
            OffhandGap.mc.playerController.windowClick(0, (slot < 9) ? (slot + 36) : slot, 0, ClickType.PICKUP, (EntityPlayer)OffhandGap.mc.player);
        }
    }
    
    public String getHudInfo() {
        return String.valueOf(InfoOverlay.getItems(Items.GOLDEN_APPLE));
    }
}
