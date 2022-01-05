//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.mixin.client;

import org.spongepowered.asm.mixin.*;
import net.minecraft.entity.*;
import com.veteran.hack.event.events.*;
import com.veteran.hack.*;
import org.spongepowered.asm.mixin.injection.*;
import com.veteran.hack.module.modules.movement.*;
import com.veteran.hack.module.modules.player.*;

@Mixin({ Entity.class })
public class MixinEntity
{
    @Redirect(method = { "applyEntityCollision" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;addVelocity(DDD)V"))
    public void addVelocity(final Entity entity, final double x, final double y, final double z) {
        final EntityEvent.EntityCollision entityCollisionEvent = new EntityEvent.EntityCollision(entity, x, y, z);
        BaseMod.EVENT_BUS.post((Object)entityCollisionEvent);
        if (entityCollisionEvent.isCancelled()) {
            return;
        }
        entity.motionX += x;
        entity.motionY += y;
        entity.motionZ += z;
        entity.isAirBorne = true;
    }
    
    @Redirect(method = { "move" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;isSneaking()Z"))
    public boolean isSneaking(final Entity entity) {
        return SafeWalk.shouldSafewalk() || Scaffold.shouldScaffold() || entity.isSneaking();
    }
}
