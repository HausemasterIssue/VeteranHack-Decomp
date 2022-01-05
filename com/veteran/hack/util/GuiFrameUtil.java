//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.util;

import com.veteran.hack.gui.rgui.component.container.use.*;
import com.veteran.hack.*;
import com.veteran.hack.gui.rgui.util.*;
import com.veteran.hack.gui.rgui.component.container.*;
import com.veteran.hack.gui.kami.*;
import java.util.*;
import net.minecraft.client.*;
import org.lwjgl.opengl.*;

public class GuiFrameUtil
{
    public static Frame getFrameByName(final String name) {
        final KamiGUI kamiGUI = BaseMod.getInstance().getGuiManager();
        if (kamiGUI == null) {
            return null;
        }
        final List<Frame> frames = (List<Frame>)ContainerHelper.getAllChildren((Class)Frame.class, (Container)kamiGUI);
        for (final Frame frame : frames) {
            if (frame.getTitle().equalsIgnoreCase(name)) {
                return frame;
            }
        }
        return null;
    }
    
    public static Frame getFrameByName(final KamiGUI kamiGUI, final String name) {
        if (kamiGUI == null) {
            return null;
        }
        final List<Frame> frames = (List<Frame>)ContainerHelper.getAllChildren((Class)Frame.class, (Container)kamiGUI);
        for (final Frame frame : frames) {
            if (frame.getTitle().equalsIgnoreCase(name)) {
                return frame;
            }
        }
        return null;
    }
    
    public static void fixFrames(final Minecraft mc) {
        final KamiGUI kamiGUI = BaseMod.getInstance().getGuiManager();
        if (kamiGUI == null) {
            return;
        }
        final List<Frame> frames = (List<Frame>)ContainerHelper.getAllChildren((Class)Frame.class, (Container)kamiGUI);
        for (final Frame frame : frames) {
            int divider = mc.gameSettings.guiScale;
            if (divider == 0) {
                divider = 3;
            }
            if (frame.getX() > Display.getWidth() / divider) {
                frame.setX(Display.getWidth() / divider - frame.getWidth());
            }
            if (frame.getY() > Display.getHeight() / divider) {
                frame.setY(Display.getHeight() / divider - frame.getHeight());
            }
        }
    }
}
