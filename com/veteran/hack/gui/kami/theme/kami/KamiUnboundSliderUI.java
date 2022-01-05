//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.gui.kami.theme.kami;

import com.veteran.hack.gui.rgui.render.*;
import com.veteran.hack.gui.kami.component.*;
import com.veteran.hack.gui.rgui.render.font.*;
import org.lwjgl.opengl.*;
import com.veteran.hack.gui.rgui.component.container.*;
import com.veteran.hack.gui.rgui.component.*;

public class KamiUnboundSliderUI extends AbstractComponentUI<UnboundSlider>
{
    @Override
    public void renderComponent(final UnboundSlider component, final FontRenderer fontRenderer) {
        final String s = component.getText() + ": " + component.getValue();
        int c = component.isPressed() ? 11184810 : 14540253;
        if (component.isHovered()) {
            c = (c & 0x7F7F7F) << 1;
        }
        fontRenderer.drawString(component.getWidth() / 2 - fontRenderer.getStringWidth(s) / 2, component.getHeight() - fontRenderer.getFontHeight() / 2 - 4, c, s);
        GL11.glDisable(3042);
    }
    
    @Override
    public void handleAddComponent(final UnboundSlider component, final Container container) {
        component.setHeight(component.getTheme().getFontRenderer().getFontHeight());
        component.setWidth(component.getTheme().getFontRenderer().getStringWidth(component.getText()));
    }
}
