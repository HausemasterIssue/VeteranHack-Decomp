//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.gui.rgui.component.container.use;

import com.veteran.hack.gui.rgui.component.container.*;
import com.veteran.hack.gui.rgui.render.theme.*;

public class Groupbox extends AbstractContainer
{
    String name;
    
    public Groupbox(final Theme theme, final String name) {
        super(theme);
        this.name = name;
    }
    
    public Groupbox(final Theme theme, final String name, final int x, final int y) {
        this(theme, name);
        this.setX(x);
        this.setY(y);
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
}
