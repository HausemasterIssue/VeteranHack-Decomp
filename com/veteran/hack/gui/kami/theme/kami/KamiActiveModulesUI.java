//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.gui.kami.theme.kami;

import com.veteran.hack.gui.rgui.render.*;
import com.veteran.hack.gui.kami.component.*;
import com.veteran.hack.gui.rgui.render.font.*;
import org.lwjgl.opengl.*;
import com.veteran.hack.module.*;
import java.util.*;
import java.util.stream.*;
import com.veteran.hack.util.*;
import java.awt.*;
import java.util.function.*;
import com.veteran.hack.gui.rgui.component.*;

public class KamiActiveModulesUI extends AbstractComponentUI<ActiveModules>
{
    com.veteran.hack.module.modules.gui.ActiveModules activeMods;
    
    @Override
    public void renderComponent(final ActiveModules component, final FontRenderer f) {
        GL11.glDisable(2884);
        GL11.glEnable(3042);
        GL11.glEnable(3553);
        final FontRenderer renderer = Wrapper.getFontRenderer();
        String string;
        final FontRenderer fontRenderer;
        final StringBuilder sb;
        final List<Module> mods = ModuleManager.getModules().stream().filter(Module::isEnabled).filter(Module::isOnArray).sorted(Comparator.comparing(module -> {
            new StringBuilder().append(module.getName());
            if (module.getHudInfo() == null) {
                string = "";
            }
            else {
                string = module.getHudInfo() + " ";
            }
            return Integer.valueOf(fontRenderer.getStringWidth(sb.append(string).toString()) * (component.sort_up ? -1 : 1));
        })).collect((Collector<? super Object, ?, List<Module>>)Collectors.toList());
        final int[] y = { 2 };
        this.activeMods = (com.veteran.hack.module.modules.gui.ActiveModules)ModuleManager.getModuleByName("ActiveModules");
        if (component.getParent().getY() < 26 && Wrapper.getPlayer().getActivePotionEffects().size() > 0 && component.getParent().getOpacity() == 0.0f) {
            y[0] = Math.max(component.getParent().getY(), 26 - component.getParent().getY());
        }
        final float[] hue = { System.currentTimeMillis() % (360 * this.activeMods.getRainbowSpeed()) / (360.0f * this.activeMods.getRainbowSpeed()) };
        Function<Integer, Integer> xFunc = null;
        switch (component.getAlignment()) {
            case RIGHT: {
                xFunc = (Function<Integer, Integer>)(i -> component.getWidth() - i);
                break;
            }
            case CENTER: {
                xFunc = (Function<Integer, Integer>)(i -> component.getWidth() / 2 - i / 2);
                break;
            }
            default: {
                xFunc = (Function<Integer, Integer>)(i -> 0);
                break;
            }
        }
        for (int j = 0; j < mods.size(); ++j) {
            final Module module2 = mods.get(j);
            int rgb;
            if (this.activeMods.mode.getValue().equals(com.veteran.hack.module.modules.gui.ActiveModules.Mode.RAINBOW)) {
                rgb = Color.HSBtoRGB(hue[0], ColourConverter.toF(this.activeMods.saturationR.getValue()), ColourConverter.toF(this.activeMods.brightnessR.getValue()));
            }
            else if (this.activeMods.mode.getValue().equals(com.veteran.hack.module.modules.gui.ActiveModules.Mode.CATEGORY)) {
                rgb = com.veteran.hack.module.modules.gui.ActiveModules.getCategoryColour(module2);
            }
            else if (this.activeMods.mode.getValue().equals(com.veteran.hack.module.modules.gui.ActiveModules.Mode.CUSTOM)) {
                rgb = Color.HSBtoRGB(ColourConverter.toF(this.activeMods.hueC.getValue()), ColourConverter.toF(this.activeMods.saturationC.getValue()), ColourConverter.toF(this.activeMods.brightnessC.getValue()));
            }
            else {
                rgb = this.activeMods.getInfoColour(j);
            }
            final String hudInfo = module2.getHudInfo();
            final String text = this.activeMods.fHax() + module2.getName() + ((hudInfo == null) ? "" : (" �7" + hudInfo));
            final int textWidth = renderer.getStringWidth(text);
            final int textHeight = renderer.getFontHeight() + 1;
            final int red = rgb >> 16 & 0xFF;
            final int green = rgb >> 8 & 0xFF;
            final int blue = rgb & 0xFF;
            renderer.drawStringWithShadow(xFunc.apply(textWidth), y[0], red, green, blue, text);
            final float[] array = hue;
            final int n = 0;
            array[n] += 0.02f;
            final int[] array2 = y;
            final int n2 = 0;
            array2[n2] += textHeight;
        }
        component.setHeight(y[0]);
        GL11.glEnable(2884);
        GL11.glDisable(3042);
    }
    
    @Override
    public void handleSizeComponent(final ActiveModules component) {
        component.setWidth(100);
        component.setHeight(100);
    }
}
