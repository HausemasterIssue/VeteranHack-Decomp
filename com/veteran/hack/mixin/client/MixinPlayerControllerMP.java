//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.mixin.client;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import com.veteran.hack.module.modules.player.*;
import com.veteran.hack.util.*;
import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.client.entity.*;
import com.veteran.hack.event.events.*;
import com.veteran.hack.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ PlayerControllerMP.class })
public class MixinPlayerControllerMP
{
    @Redirect(method = { "onPlayerDamageBlock" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/block/state/IBlockState;getPlayerRelativeBlockHardness(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)F"))
    float getPlayerRelativeBlockHardness(final IBlockState state, final EntityPlayer player, final World worldIn, final BlockPos pos) {
        return state.getPlayerRelativeBlockHardness(player, worldIn, pos) * (TpsSync.isSync() ? (LagCompensator.INSTANCE.getTickRate() / 20.0f) : 1.0f);
    }
    
    @Inject(method = { "attackEntity" }, at = { @At("HEAD") }, cancellable = true)
    public void attackEntity(final EntityPlayer playerIn, final Entity targetEntity, final CallbackInfo ci) {
        if (targetEntity == null) {
            return;
        }
        if (targetEntity instanceof EntityPlayerSP) {
            final ClientPlayerAttackEvent e = new ClientPlayerAttackEvent(targetEntity);
            BaseMod.EVENT_BUS.post((Object)e);
            if (e.isCancelled()) {
                ci.cancel();
            }
        }
    }
}
