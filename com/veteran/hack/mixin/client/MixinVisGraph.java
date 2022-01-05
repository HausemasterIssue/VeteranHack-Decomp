//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.mixin.client;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.chunk.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.util.*;
import com.veteran.hack.module.*;
import java.util.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ VisGraph.class })
public class MixinVisGraph
{
    @Inject(method = { "getVisibleFacings" }, at = { @At("HEAD") }, cancellable = true)
    public void getVisibleFacings(final CallbackInfoReturnable<Set<EnumFacing>> callbackInfo) {
        if (ModuleManager.isModuleEnabled("Freecam")) {
            callbackInfo.setReturnValue((Object)EnumSet.allOf(EnumFacing.class));
        }
    }
}
