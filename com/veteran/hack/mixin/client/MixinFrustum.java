//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.mixin.client;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.culling.*;
import net.minecraft.util.math.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.veteran.hack.module.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ Frustum.class })
public abstract class MixinFrustum
{
    @Inject(method = { "Lnet/minecraft/client/renderer/culling/Frustum;isBoundingBoxInFrustum(Lnet/minecraft/util/math/AxisAlignedBB;)Z" }, at = { @At("HEAD") }, cancellable = true)
    public void isBoundingBoxEtc(final AxisAlignedBB ignore, final CallbackInfoReturnable<Boolean> info) {
        if (ModuleManager.isModuleEnabled("Freecam")) {
            info.setReturnValue((Object)true);
        }
    }
}
