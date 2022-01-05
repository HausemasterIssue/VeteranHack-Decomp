//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.gui.rgui.component.container;

import com.veteran.hack.gui.rgui.layout.*;
import com.veteran.hack.gui.rgui.render.theme.*;
import com.veteran.hack.gui.rgui.component.*;

public class OrganisedContainer extends AbstractContainer
{
    Layout layout;
    
    public OrganisedContainer(final Theme theme, final Layout layout) {
        super(theme);
        this.layout = layout;
    }
    
    public Layout getLayout() {
        return this.layout;
    }
    
    public void setLayout(final Layout layout) {
        this.layout = layout;
    }
    
    public Container addChild(final Component... component) {
        super.addChild(component);
        this.layout.organiseContainer((Container)this);
        return (Container)this;
    }
    
    public void setOriginOffsetX(final int originoffsetX) {
        super.setOriginOffsetX(originoffsetX);
        this.layout.organiseContainer((Container)this);
    }
    
    public void setOriginOffsetY(final int originoffsetY) {
        super.setOriginOffsetY(originoffsetY);
        this.layout.organiseContainer((Container)this);
    }
}
