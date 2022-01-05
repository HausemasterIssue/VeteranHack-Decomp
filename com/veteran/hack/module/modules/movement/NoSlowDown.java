//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.movement;

import com.veteran.hack.module.*;
import net.minecraftforge.client.event.*;
import me.zero.alpine.listener.*;
import com.veteran.hack.setting.*;
import java.util.function.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

@Module.Info(name = "NoSlowDown", category = Module.Category.HIDDEN, description = "Prevents being slowed down when using an item or going through cobwebs")
public class NoSlowDown extends Module
{
    public Setting<Boolean> soulSand;
    public Setting<Boolean> cobweb;
    private Setting<Boolean> slime;
    private Setting<Boolean> allItems;
    private Setting<Boolean> food;
    private Setting<Boolean> bow;
    private Setting<Boolean> potion;
    private Setting<Boolean> shield;
    @EventHandler
    private Listener<InputUpdateEvent> eventListener;
    
    public NoSlowDown() {
        this.soulSand = (Setting<Boolean>)this.register((Setting)Settings.b("Soul Sand", true));
        this.cobweb = (Setting<Boolean>)this.register((Setting)Settings.b("Cobweb", true));
        this.slime = (Setting<Boolean>)this.register((Setting)Settings.b("Slime", true));
        this.allItems = (Setting<Boolean>)this.register((Setting)Settings.b("All Items", false));
        this.food = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder().withName("Food").withValue(true).withVisibility(v -> !this.allItems.getValue()).build());
        this.bow = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder().withName("Bows").withValue(true).withVisibility(v -> !this.allItems.getValue()).build());
        this.potion = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder().withName("Potions").withValue(true).withVisibility(v -> !this.allItems.getValue()).build());
        this.shield = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder().withName("Shield").withValue(true).withVisibility(v -> !this.allItems.getValue()).build());
        this.eventListener = (Listener<InputUpdateEvent>)new Listener(event -> {
            if (this.passItemCheck(NoSlowDown.mc.player.getActiveItemStack().getItem()) && NoSlowDown.mc.player.isHandActive() && !NoSlowDown.mc.player.isRiding()) {
                final MovementInput movementInput = event.getMovementInput();
                movementInput.moveStrafe *= 5.0f;
                final MovementInput movementInput2 = event.getMovementInput();
                movementInput2.moveForward *= 5.0f;
            }
        }, new Predicate[0]);
    }
    
    public void onUpdate() {
        if (this.slime.getValue()) {
            Blocks.SLIME_BLOCK.slipperiness = 0.4945f;
        }
        else {
            Blocks.SLIME_BLOCK.slipperiness = 0.8f;
        }
    }
    
    public void onDisable() {
        Blocks.SLIME_BLOCK.slipperiness = 0.8f;
    }
    
    private boolean passItemCheck(final Item item) {
        return this.allItems.getValue() || (this.food.getValue() && item instanceof ItemFood) || (this.bow.getValue() && item instanceof ItemBow) || (this.potion.getValue() && item instanceof ItemPotion) || (this.shield.getValue() && item instanceof ItemShield);
    }
}
