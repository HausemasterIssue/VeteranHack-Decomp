//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.event.events;

import com.veteran.hack.event.*;
import net.minecraft.entity.*;
import javax.annotation.*;

public class ClientPlayerAttackEvent extends KamiEvent
{
    private Entity targetEntity;
    
    public ClientPlayerAttackEvent(@Nonnull final Entity targetEntity) {
        if (this.targetEntity == null) {
            throw new IllegalArgumentException("Target Entity cannot be null");
        }
        this.targetEntity = targetEntity;
    }
    
    public Entity getTargetEntity() {
        return this.targetEntity;
    }
}
