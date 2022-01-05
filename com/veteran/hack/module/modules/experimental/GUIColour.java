//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.experimental;

import com.veteran.hack.module.*;
import com.veteran.hack.setting.*;

@Module.Info(name = "GUI Colour", description = "Change GUI Colours", category = Module.Category.EXPERIMENTAL)
public class GUIColour extends Module
{
    public Setting<Integer> red;
    public Setting<Integer> green;
    public Setting<Integer> blue;
    public Setting<Integer> alpha;
    
    public GUIColour() {
        this.red = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Red").withMinimum(0).withValue(13).withMaximum(255).build());
        this.green = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Green").withMinimum(0).withValue(13).withMaximum(255).build());
        this.blue = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Blue").withMinimum(0).withValue(13).withMaximum(255).build());
        this.alpha = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Alpha").withMinimum(0).withValue(117).withMaximum(255).build());
    }
}
