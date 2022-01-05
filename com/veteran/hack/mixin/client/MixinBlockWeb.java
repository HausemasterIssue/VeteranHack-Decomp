//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.mixin.client;

import org.spongepowered.asm.mixin.*;
import net.minecraft.block.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.veteran.hack.module.*;
import com.veteran.hack.module.modules.movement.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ BlockWeb.class })
public class MixinBlockWeb
{
    @Inject(method = { "onEntityCollision" }, at = { @At("HEAD") }, cancellable = true)
    public void onEntityCollidedWithBlock(final World worldIn, final BlockPos pos, final IBlockState state, final Entity entityIn, final CallbackInfo info) {
        if (ModuleManager.isModuleEnabled("NoSlowDown") && ((NoSlowDown)ModuleManager.getModuleByName("NoSlowDown")).cobweb.getValue()) {
            info.cancel();
        }
    }
}
