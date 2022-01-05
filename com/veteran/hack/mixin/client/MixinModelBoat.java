//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.mixin.client;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.model.*;
import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.veteran.hack.util.*;
import com.veteran.hack.module.*;
import com.veteran.hack.module.modules.movement.*;
import net.minecraft.client.renderer.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ ModelBoat.class })
public class MixinModelBoat
{
    @Inject(method = { "render" }, at = { @At("HEAD") })
    public void render(final Entity entityIn, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scale, final CallbackInfo info) {
        if (Wrapper.getPlayer().getRidingEntity() == entityIn && ModuleManager.isModuleEnabled("EntitySpeed")) {
            GlStateManager.color(1.0f, 1.0f, 1.0f, EntitySpeed.getOpacity());
            GlStateManager.enableBlend();
        }
    }
}
