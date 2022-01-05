//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.mixin.client;

import org.spongepowered.asm.mixin.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.block.state.*;
import com.veteran.hack.module.*;
import com.veteran.hack.module.modules.render.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ ChunkCache.class })
public class MixinChunkCache
{
    @Inject(method = { "getBlockState" }, at = { @At("RETURN") }, cancellable = true)
    public void getState(final BlockPos pos, final CallbackInfoReturnable<IBlockState> info) {
        if (ModuleManager.isModuleEnabled("XRay")) {
            info.setReturnValue((Object)XRay.transform((IBlockState)info.getReturnValue()));
        }
    }
}
