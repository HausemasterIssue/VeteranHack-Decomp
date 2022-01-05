//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.gui.kami.theme.kami;

import com.veteran.hack.gui.kami.component.*;
import com.veteran.hack.util.*;
import java.awt.*;
import com.veteran.hack.gui.rgui.component.use.*;
import com.veteran.hack.gui.rgui.render.font.*;
import org.lwjgl.opengl.*;
import com.veteran.hack.gui.kami.*;
import com.veteran.hack.gui.rgui.component.*;

public class RootColorizedCheckButtonUI extends RootCheckButtonUI<ColorizedCheckButton>
{
    RootSmallFontRenderer ff;
    
    public RootColorizedCheckButtonUI() {
        this.ff = new RootSmallFontRenderer();
        ColourSet.bgColour = new Color(ColourConverter.toF(200), ColourConverter.toF(ColourSet.bgColour.getGreen()), ColourConverter.toF(ColourSet.bgColour.getBlue()));
        ColourSet.bgColourHover = new Color(ColourConverter.toF(255), ColourConverter.toF(ColourSet.bgColourHover.getGreen()), ColourConverter.toF(ColourSet.bgColourHover.getBlue()));
    }
    
    public void renderComponent(final CheckButton component, final FontRenderer aa) {
        GL11.glColor4f(ColourConverter.toF(ColourSet.bgColour.getRed()), ColourConverter.toF(ColourSet.bgColour.getGreen()), ColourConverter.toF(ColourSet.bgColour.getBlue()), component.getOpacity());
        if (component.isHovered() || component.isPressed()) {
            GL11.glColor4f(ColourConverter.toF(ColourSet.bgColourHover.getRed()), ColourConverter.toF(ColourSet.bgColourHover.getGreen()), ColourConverter.toF(ColourSet.bgColourHover.getBlue()), component.getOpacity());
        }
        if (component.isToggled()) {
            GL11.glColor3f(ColourConverter.toF(ColourSet.bgColour.getRed()), ColourConverter.toF(ColourSet.bgColour.getGreen()), ColourConverter.toF(ColourSet.bgColour.getBlue()));
        }
        GL11.glLineWidth(2.5f);
        GL11.glBegin(1);
        GL11.glVertex2d(0.0, (double)component.getHeight());
        GL11.glVertex2d((double)component.getWidth(), (double)component.getHeight());
        GL11.glEnd();
        final Color idleColour = component.isToggled() ? ColourSet.buttonIdleT : ColourSet.buttonIdleN;
        final Color downColour = component.isToggled() ? ColourSet.buttonHoveredT : ColourSet.buttonHoveredN;
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glEnable(3553);
        this.ff.drawString(component.getWidth() / 2 - KamiGUI.fontRenderer.getStringWidth(component.getName()) / 2, 0, component.isPressed() ? downColour : idleColour, component.getName());
        GL11.glDisable(3553);
    }
}
