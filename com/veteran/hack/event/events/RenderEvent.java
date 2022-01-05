//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.event.events;

import com.veteran.hack.event.*;
import net.minecraft.util.math.*;
import net.minecraft.client.renderer.*;

public class RenderEvent extends KamiEvent
{
    private final Tessellator tessellator;
    private final Vec3d renderPos;
    
    public RenderEvent(final Tessellator tessellator, final Vec3d renderPos) {
        this.tessellator = tessellator;
        this.renderPos = renderPos;
    }
    
    public Tessellator getTessellator() {
        return this.tessellator;
    }
    
    public BufferBuilder getBuffer() {
        return this.tessellator.getBuffer();
    }
    
    public Vec3d getRenderPos() {
        return this.renderPos;
    }
    
    public void setTranslation(final Vec3d translation) {
        this.getBuffer().setTranslation(-translation.x, -translation.y, -translation.z);
    }
    
    public void resetTranslation() {
        this.setTranslation(this.renderPos);
    }
}
