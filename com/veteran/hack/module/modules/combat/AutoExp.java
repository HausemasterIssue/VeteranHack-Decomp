//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.combat;

import com.veteran.hack.module.*;
import com.veteran.hack.event.events.*;
import me.zero.alpine.listener.*;
import com.veteran.hack.setting.*;
import java.util.function.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.init.*;
import com.veteran.hack.command.*;

@Module.Info(name = "AutoExp", category = Module.Category.COMBAT, description = "Automatically mends armour")
public class AutoExp extends Module
{
    private Setting<Boolean> autoThrow;
    private Setting<Boolean> autoSwitch;
    private Setting<Boolean> autoDisable;
    private Setting<Boolean> smartMend;
    private Setting<Double> mendDur;
    private int initHotbarSlot;
    @EventHandler
    private Listener<PacketEvent.Receive> receiveListener;
    boolean hasClicked;
    
    public AutoExp() {
        this.autoThrow = (Setting<Boolean>)this.register((Setting)Settings.b("Auto Throw", true));
        this.autoSwitch = (Setting<Boolean>)this.register((Setting)Settings.b("Auto Switch", true));
        this.autoDisable = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("Auto Disable").withValue(true).withVisibility(o -> this.autoSwitch.getValue()).build());
        this.smartMend = (Setting<Boolean>)this.register((Setting)Settings.b("Smart Mend", false));
        this.mendDur = (Setting<Double>)this.register((Setting)Settings.doubleBuilder("Required Durabilty").withValue(85.0).withVisibility(o -> this.smartMend.getValue()).build());
        this.initHotbarSlot = -1;
        this.receiveListener = (Listener<PacketEvent.Receive>)new Listener(event -> {
            if (AutoExp.mc.player != null && AutoExp.mc.player.getHeldItemMainhand().getItem() == Items.EXPERIENCE_BOTTLE) {
                AutoExp.mc.rightClickDelayTimer = 0;
            }
        }, new Predicate[0]);
        this.hasClicked = false;
    }
    
    protected void onEnable() {
        this.hasClicked = false;
        if (AutoExp.mc.player == null) {
            return;
        }
        if (this.autoSwitch.getValue()) {
            this.initHotbarSlot = AutoExp.mc.player.inventory.currentItem;
        }
        if (this.smartMend.getValue()) {
            for (int s = 1, i = 5; i <= 8; ++i, ++s) {
                AutoExp.mc.playerController.windowClick(0, i, 0, ClickType.PICKUP, (EntityPlayer)AutoExp.mc.player);
                AutoExp.mc.playerController.windowClick(0, s, 0, ClickType.PICKUP, (EntityPlayer)AutoExp.mc.player);
            }
        }
    }
    
    protected void onDisable() {
        if (AutoExp.mc.player == null) {
            return;
        }
        if (this.autoSwitch.getValue() && this.initHotbarSlot != -1 && this.initHotbarSlot != AutoExp.mc.player.inventory.currentItem) {
            AutoExp.mc.player.inventory.currentItem = this.initHotbarSlot;
        }
        if (this.smartMend.getValue()) {
            for (int s = 1, i = 5; i <= 8; ++i, ++s) {
                AutoExp.mc.playerController.windowClick(0, s, 0, ClickType.PICKUP, (EntityPlayer)AutoExp.mc.player);
                AutoExp.mc.playerController.windowClick(0, i, 0, ClickType.PICKUP, (EntityPlayer)AutoExp.mc.player);
            }
        }
    }
    
    public void onUpdate() {
        if (AutoExp.mc.player == null) {
            return;
        }
        if (this.autoSwitch.getValue() && AutoExp.mc.player.getHeldItemMainhand().getItem() != Items.EXPERIENCE_BOTTLE) {
            final int xpSlot = this.findXpPots();
            if (xpSlot == -1) {
                if (this.autoDisable.getValue()) {
                    Command.sendWarningMessage(this.getChatName() + " No XP in hotbar, disabling");
                    this.disable();
                }
                return;
            }
            AutoExp.mc.player.inventory.currentItem = xpSlot;
        }
        if (this.autoThrow.getValue() && AutoExp.mc.player.getHeldItemMainhand().getItem() == Items.EXPERIENCE_BOTTLE) {
            AutoExp.mc.rightClickMouse();
        }
    }
    
    private int findXpPots() {
        int slot = -1;
        for (int i = 0; i < 9; ++i) {
            if (AutoExp.mc.player.inventory.getStackInSlot(i).getItem() == Items.EXPERIENCE_BOTTLE) {
                slot = i;
                break;
            }
        }
        return slot;
    }
}
