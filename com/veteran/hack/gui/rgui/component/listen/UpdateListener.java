//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.gui.rgui.component.listen;

import com.veteran.hack.gui.rgui.component.*;

public interface UpdateListener<T extends Component>
{
    void updateSize(final T p0, final int p1, final int p2);
    
    void updateLocation(final T p0, final int p1, final int p2);
}
