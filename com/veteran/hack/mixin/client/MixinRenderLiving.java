//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.mixin.client;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.entity.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.veteran.hack.module.*;
import com.veteran.hack.module.modules.render.*;
import org.lwjgl.opengl.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.entity.*;

@Mixin({ RenderLiving.class })
public class MixinRenderLiving
{
    @Inject(method = { "doRender" }, at = { @At("HEAD") })
    private void injectChamsPre(final EntityLiving entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks, final CallbackInfo info) {
        if (ModuleManager.isModuleEnabled("Chams") && Chams.renderChams((Entity)entity)) {
            GL11.glEnable(32823);
            GL11.glPolygonOffset(1.0f, -1000000.0f);
        }
    }
    
    @Inject(method = { "doRender" }, at = { @At("RETURN") })
    private <S extends EntityLivingBase> void injectChamsPost(final EntityLiving entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks, final CallbackInfo info) {
        if (ModuleManager.isModuleEnabled("Chams") && Chams.renderChams((Entity)entity)) {
            GL11.glPolygonOffset(1.0f, 1000000.0f);
            GL11.glDisable(32823);
        }
    }
}
