//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.gui.kami.theme.kami;

import com.veteran.hack.gui.rgui.render.*;
import com.veteran.hack.gui.kami.component.*;
import com.veteran.hack.gui.rgui.render.font.*;
import org.lwjgl.opengl.*;
import com.veteran.hack.gui.kami.*;
import com.veteran.hack.gui.rgui.component.*;

public class RootSettingsPanelUI extends AbstractComponentUI<SettingsPanel>
{
    @Override
    public void renderComponent(final SettingsPanel component, final FontRenderer fontRenderer) {
        GL11.glColor4f(1.0f, 0.33f, 0.33f, 0.2f);
        RenderHelper.drawOutlinedRoundedRectangle(0, 0, component.getWidth(), component.getHeight(), 6.0f, 0.14f, 0.14f, 0.14f, component.getOpacity(), 1.0f);
    }
}
