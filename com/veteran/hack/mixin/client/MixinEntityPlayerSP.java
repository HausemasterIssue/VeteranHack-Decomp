//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.mixin.client;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.entity.*;
import com.veteran.hack.module.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.veteran.hack.event.events.*;
import com.veteran.hack.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ EntityPlayerSP.class })
public class MixinEntityPlayerSP
{
    @Redirect(method = { "onLivingUpdate" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;closeScreen()V"))
    public void closeScreen(final EntityPlayerSP entityPlayerSP) {
        if (ModuleManager.isModuleEnabled("PortalChat")) {
            return;
        }
    }
    
    @Redirect(method = { "onLivingUpdate" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;displayGuiScreen(Lnet/minecraft/client/gui/GuiScreen;)V"))
    public void closeScreen(final Minecraft minecraft, final GuiScreen screen) {
        if (ModuleManager.isModuleEnabled("PortalChat")) {
            return;
        }
    }
    
    @Inject(method = { "move" }, at = { @At("HEAD") }, cancellable = true)
    public void move(final MoverType type, final double x, final double y, final double z, final CallbackInfo info) {
        final PlayerMoveEvent event = new PlayerMoveEvent(type, x, y, z);
        BaseMod.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            info.cancel();
        }
    }
}
