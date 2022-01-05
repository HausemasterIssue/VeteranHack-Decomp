//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.gui.kami.theme.kami;

import com.veteran.hack.gui.rgui.component.use.*;
import com.veteran.hack.gui.rgui.render.*;
import com.veteran.hack.gui.rgui.render.font.*;
import org.lwjgl.opengl.*;
import com.veteran.hack.gui.kami.*;
import com.veteran.hack.gui.rgui.component.container.*;
import com.veteran.hack.gui.rgui.component.*;

public class RootInputFieldUI<T extends InputField> extends AbstractComponentUI<InputField>
{
    @Override
    public void renderComponent(final InputField component, final FontRenderer fontRenderer) {
        GL11.glColor3f(0.33f, 0.22f, 0.22f);
        RenderHelper.drawFilledRectangle(0.0f, 0.0f, (float)component.getWidth(), (float)component.getHeight());
        GL11.glLineWidth(1.5f);
        GL11.glColor4f(1.0f, 0.33f, 0.33f, 0.6f);
        RenderHelper.drawRectangle(0.0f, 0.0f, (float)component.getWidth(), (float)component.getHeight());
    }
    
    @Override
    public void handleAddComponent(final InputField component, final Container container) {
        component.setWidth(200);
        component.setHeight(component.getTheme().getFontRenderer().getFontHeight());
    }
}
