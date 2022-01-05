//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.gui.rgui.component.container;

import com.veteran.hack.gui.rgui.component.*;
import java.util.*;

public interface Container extends Component
{
    ArrayList<Component> getChildren();
    
    Component getComponentAt(final int p0, final int p1);
    
    Container addChild(final Component... p0);
    
    Container removeChild(final Component p0);
    
    boolean hasChild(final Component p0);
    
    void renderChildren();
    
    int getOriginOffsetX();
    
    int getOriginOffsetY();
    
    boolean penetrateTest(final int p0, final int p1);
}
