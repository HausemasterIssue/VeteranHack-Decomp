//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.event.events;

import com.veteran.hack.event.*;
import net.minecraft.entity.*;

public class EntityUseTotem extends KamiEvent
{
    private Entity entity;
    
    public EntityUseTotem(final Entity entity) {
        this.entity = entity;
    }
    
    public Entity getEntity() {
        return this.entity;
    }
}
