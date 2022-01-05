//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.mixin.client;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.veteran.hack.util.*;
import com.veteran.hack.gui.mc.*;
import com.veteran.hack.command.*;
import net.minecraft.client.gui.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ GuiChat.class })
public abstract class MixinGuiChat
{
    @Shadow
    protected GuiTextField inputField;
    @Shadow
    public String historyBuffer;
    @Shadow
    public int sentHistoryCursor;
    
    @Shadow
    public abstract void initGui();
    
    @Inject(method = { "Lnet/minecraft/client/gui/GuiChat;keyTyped(CI)V" }, at = { @At("RETURN") })
    public void returnKeyTyped(final char typedChar, final int keyCode, final CallbackInfo info) {
        if (!(Wrapper.getMinecraft().currentScreen instanceof GuiChat) || Wrapper.getMinecraft().currentScreen instanceof KamiGuiChat) {
            return;
        }
        if (this.inputField.getText().startsWith(Command.getCommandPrefix())) {
            Wrapper.getMinecraft().displayGuiScreen((GuiScreen)new KamiGuiChat(this.inputField.getText(), this.historyBuffer, this.sentHistoryCursor));
        }
    }
}
