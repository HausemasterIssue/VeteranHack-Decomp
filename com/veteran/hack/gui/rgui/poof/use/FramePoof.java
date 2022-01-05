//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.gui.rgui.poof.use;

import com.veteran.hack.gui.rgui.component.*;
import com.veteran.hack.gui.rgui.poof.*;

public abstract class FramePoof<T extends Component, S extends PoofInfo> extends Poof<T, S>
{
    public static class FramePoofInfo extends PoofInfo
    {
        private Action action;
        
        public FramePoofInfo(final Action action) {
            this.action = action;
        }
        
        public Action getAction() {
            return this.action;
        }
    }
    
    public enum Action
    {
        MINIMIZE, 
        MAXIMIZE, 
        CLOSE;
    }
}
