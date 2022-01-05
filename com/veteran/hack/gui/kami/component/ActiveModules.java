//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.gui.kami.component;

import com.veteran.hack.gui.rgui.component.use.*;
import com.veteran.hack.gui.rgui.component.listen.*;
import com.veteran.hack.gui.rgui.component.container.use.*;
import com.veteran.hack.gui.rgui.component.*;
import com.veteran.hack.gui.rgui.util.*;

public class ActiveModules extends Label
{
    public boolean sort_up;
    
    public ActiveModules() {
        super("");
        this.sort_up = true;
        this.addRenderListener(new RenderListener() {
            @Override
            public void onPreRender() {
                final Frame parentFrame = ContainerHelper.getFirstParent((Class<? extends Frame>)Frame.class, (Component)ActiveModules.this);
                if (parentFrame == null) {
                    return;
                }
                final Docking docking = parentFrame.getDocking();
                if (docking.isTop()) {
                    ActiveModules.this.sort_up = true;
                }
                if (docking.isBottom()) {
                    ActiveModules.this.sort_up = false;
                }
            }
            
            @Override
            public void onPostRender() {
            }
        });
    }
}
