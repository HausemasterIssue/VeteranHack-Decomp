//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.combat;

import net.minecraftforge.event.entity.living.*;
import me.zero.alpine.listener.*;
import net.minecraftforge.event.entity.*;
import com.veteran.hack.setting.*;
import java.util.function.*;
import net.minecraft.client.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.text.*;
import com.veteran.hack.module.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.*;

@Module.Info(name = "AutoLog", description = "Automatically log when in danger or on low health", category = Module.Category.HIDDEN)
public class AutoLog extends Module
{
    private Setting<Integer> health;
    private boolean shouldLog;
    long lastLog;
    @EventHandler
    private Listener<LivingDamageEvent> livingDamageEventListener;
    @EventHandler
    private Listener<EntityJoinWorldEvent> entityJoinWorldEventListener;
    
    public AutoLog() {
        this.health = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Health").withRange(0, 36).withValue(6).build());
        this.shouldLog = false;
        this.lastLog = System.currentTimeMillis();
        this.livingDamageEventListener = (Listener<LivingDamageEvent>)new Listener(event -> {
            if (AutoLog.mc.player == null) {
                return;
            }
            if (event.getEntity() == AutoLog.mc.player && AutoLog.mc.player.getHealth() - event.getAmount() < this.health.getValue()) {
                this.log();
            }
        }, new Predicate[0]);
        this.entityJoinWorldEventListener = (Listener<EntityJoinWorldEvent>)new Listener(event -> {
            if (AutoLog.mc.player == null) {
                return;
            }
            if (event.getEntity() instanceof EntityEnderCrystal && AutoLog.mc.player.getHealth() - Autocrystal.calculateDamage((EntityEnderCrystal)event.getEntity(), (Entity)AutoLog.mc.player) < this.health.getValue()) {
                this.log();
            }
        }, new Predicate[0]);
    }
    
    public void onUpdate() {
        if (this.shouldLog) {
            this.shouldLog = false;
            if (System.currentTimeMillis() - this.lastLog < 2000L) {
                return;
            }
            Minecraft.getMinecraft().getConnection().handleDisconnect(new SPacketDisconnect((ITextComponent)new TextComponentString("AutoLogged")));
        }
    }
    
    private void log() {
        ModuleManager.getModuleByName("AutoReconnect").disable();
        this.shouldLog = true;
        this.lastLog = System.currentTimeMillis();
    }
}
