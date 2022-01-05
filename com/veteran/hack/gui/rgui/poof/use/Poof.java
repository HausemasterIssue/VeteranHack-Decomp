//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.gui.rgui.poof.use;

import com.veteran.hack.gui.rgui.component.*;
import com.veteran.hack.gui.rgui.poof.*;
import java.lang.reflect.*;

public abstract class Poof<T extends Component, S extends PoofInfo> implements IPoof<T, S>
{
    private Class<T> componentclass;
    private Class<S> infoclass;
    
    public Poof() {
        this.componentclass = (Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.infoclass = (Class<S>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
    
    public Class getComponentClass() {
        return this.componentclass;
    }
    
    public Class<S> getInfoClass() {
        return this.infoclass;
    }
}
