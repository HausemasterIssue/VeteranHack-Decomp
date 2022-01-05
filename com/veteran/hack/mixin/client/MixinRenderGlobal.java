//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.mixin.client;

import net.minecraft.client.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.*;

@Mixin({ RenderGlobal.class })
public class MixinRenderGlobal
{
    @Shadow
    Minecraft mc;
    @Shadow
    public ChunkRenderContainer renderContainer;
}
