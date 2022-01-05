//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.render;

import com.veteran.hack.module.*;
import com.veteran.hack.event.events.*;
import me.zero.alpine.listener.*;
import net.minecraftforge.client.event.*;
import com.veteran.hack.setting.*;
import java.util.function.*;
import net.minecraft.network.play.server.*;
import net.minecraft.network.*;

@Module.Info(name = "NoRender", category = Module.Category.RENDER, description = "Ignore entity spawn packets")
public class NoRender extends Module
{
    private Setting<Boolean> mob;
    private Setting<Boolean> sand;
    private Setting<Boolean> gentity;
    private Setting<Boolean> object;
    private Setting<Boolean> xp;
    private Setting<Boolean> paint;
    private Setting<Boolean> fire;
    private Setting<Boolean> explosion;
    @EventHandler
    public Listener<PacketEvent.Receive> receiveListener;
    @EventHandler
    public Listener<RenderBlockOverlayEvent> blockOverlayEventListener;
    
    public NoRender() {
        this.mob = (Setting<Boolean>)this.register((Setting)Settings.b("Mob", false));
        this.sand = (Setting<Boolean>)this.register((Setting)Settings.b("Sand", false));
        this.gentity = (Setting<Boolean>)this.register((Setting)Settings.b("GEntity", false));
        this.object = (Setting<Boolean>)this.register((Setting)Settings.b("Object", false));
        this.xp = (Setting<Boolean>)this.register((Setting)Settings.b("XP", false));
        this.paint = (Setting<Boolean>)this.register((Setting)Settings.b("Paintings", false));
        this.fire = (Setting<Boolean>)this.register((Setting)Settings.b("Fire"));
        this.explosion = (Setting<Boolean>)this.register((Setting)Settings.b("Explosions"));
        this.receiveListener = (Listener<PacketEvent.Receive>)new Listener(event -> {
            final Packet packet = event.getPacket();
            if ((packet instanceof SPacketSpawnMob && this.mob.getValue()) || (packet instanceof SPacketSpawnGlobalEntity && this.gentity.getValue()) || (packet instanceof SPacketSpawnObject && this.object.getValue()) || (packet instanceof SPacketSpawnExperienceOrb && this.xp.getValue()) || (packet instanceof SPacketSpawnObject && this.sand.getValue()) || (packet instanceof SPacketExplosion && this.explosion.getValue()) || (packet instanceof SPacketSpawnPainting && this.paint.getValue())) {
                event.cancel();
            }
        }, new Predicate[0]);
        this.blockOverlayEventListener = (Listener<RenderBlockOverlayEvent>)new Listener(event -> {
            if (this.fire.getValue() && event.getOverlayType() == RenderBlockOverlayEvent.OverlayType.FIRE) {
                event.setCanceled(true);
            }
        }, new Predicate[0]);
    }
}
