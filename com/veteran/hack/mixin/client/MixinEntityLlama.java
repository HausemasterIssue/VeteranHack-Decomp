//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.mixin.client;

import org.spongepowered.asm.mixin.*;
import net.minecraft.entity.passive.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.veteran.hack.module.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ EntityLlama.class })
public class MixinEntityLlama
{
    @Inject(method = { "canBeSteered" }, at = { @At("RETURN") }, cancellable = true)
    public void canBeSteered(final CallbackInfoReturnable<Boolean> returnable) {
        if (ModuleManager.isModuleEnabled("EntitySpeed")) {
            returnable.setReturnValue((Object)true);
        }
    }
}
