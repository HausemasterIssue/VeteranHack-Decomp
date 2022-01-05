//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.gui.kami.theme.kami;

import com.veteran.hack.gui.rgui.render.*;
import com.veteran.hack.gui.kami.component.*;
import com.veteran.hack.gui.rgui.render.font.*;
import org.lwjgl.opengl.*;
import com.veteran.hack.module.modules.experimental.*;
import com.veteran.hack.module.*;
import com.veteran.hack.gui.kami.*;
import com.veteran.hack.gui.rgui.component.*;

public class KamiSettingsPanelUI extends AbstractComponentUI<SettingsPanel>
{
    @Override
    public void renderComponent(final SettingsPanel component, final FontRenderer fontRenderer) {
        super.renderComponent(component, fontRenderer);
        GL11.glLineWidth(2.0f);
        final float red = ((GUIColour)ModuleManager.getModuleByName("GUI Colour")).red.getValue() / 255.0f;
        final float green = ((GUIColour)ModuleManager.getModuleByName("GUI Colour")).green.getValue() / 255.0f;
        final float blue = ((GUIColour)ModuleManager.getModuleByName("GUI Colour")).blue.getValue() / 255.0f;
        final float alpha = ((GUIColour)ModuleManager.getModuleByName("GUI Colour")).alpha.getValue() / 255.0f;
        if (ModuleManager.getModuleByName("GUI Colour").isEnabled()) {
            GL11.glColor4f(red, green, blue, alpha);
        }
        else {
            GL11.glColor4f(0.17f, 0.17f, 0.18f, 0.9f);
        }
        RenderHelper.drawFilledRectangle(0.0f, 0.0f, (float)component.getWidth(), (float)component.getHeight());
    }
}
