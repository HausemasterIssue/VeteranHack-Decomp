//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.gui.rgui.render;

import com.veteran.hack.gui.rgui.component.*;
import java.lang.reflect.*;
import com.veteran.hack.gui.rgui.render.font.*;
import com.veteran.hack.gui.rgui.component.container.*;

public abstract class AbstractComponentUI<T extends Component> implements ComponentUI<T>
{
    private Class<T> persistentClass;
    
    public AbstractComponentUI() {
        this.persistentClass = (Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    @Override
    public void renderComponent(final T component, final FontRenderer fontRenderer) {
    }
    
    @Override
    public void handleMouseDown(final T component, final int x, final int y, final int button) {
    }
    
    @Override
    public void handleMouseRelease(final T component, final int x, final int y, final int button) {
    }
    
    @Override
    public void handleMouseDrag(final T component, final int x, final int y, final int button) {
    }
    
    @Override
    public void handleScroll(final T component, final int x, final int y, final int amount, final boolean up) {
    }
    
    @Override
    public void handleAddComponent(final T component, final Container container) {
    }
    
    @Override
    public void handleKeyDown(final T component, final int key) {
    }
    
    @Override
    public void handleKeyUp(final T component, final int key) {
    }
    
    @Override
    public void handleSizeComponent(final T component) {
    }
    
    @Override
    public Class<? extends Component> getHandledClass() {
        return this.persistentClass;
    }
}
