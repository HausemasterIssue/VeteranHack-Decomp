//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.gui.kami.theme.kami;

import com.veteran.hack.gui.rgui.render.theme.*;
import com.veteran.hack.gui.rgui.render.font.*;
import com.veteran.hack.gui.kami.*;
import com.veteran.hack.gui.rgui.render.*;

public class KamiTheme extends AbstractTheme
{
    FontRenderer fontRenderer;
    
    public KamiTheme() {
        this.installUI(new RootButtonUI<Object>());
        this.installUI(new GUIUI());
        this.installUI(new RootGroupboxUI());
        this.installUI((ComponentUI<?>)new KamiFrameUI());
        this.installUI(new RootScrollpaneUI());
        this.installUI(new RootInputFieldUI<Object>());
        this.installUI(new RootLabelUI<Object>());
        this.installUI(new RootChatUI());
        this.installUI(new RootCheckButtonUI<Object>());
        this.installUI((ComponentUI<?>)new KamiActiveModulesUI());
        this.installUI((ComponentUI<?>)new KamiSettingsPanelUI());
        this.installUI(new RootSliderUI());
        this.installUI((ComponentUI<?>)new KamiEnumButtonUI());
        this.installUI(new RootColorizedCheckButtonUI());
        this.installUI(new KamiUnboundSliderUI());
        this.fontRenderer = (FontRenderer)KamiGUI.fontRenderer;
    }
    
    @Override
    public FontRenderer getFontRenderer() {
        return this.fontRenderer;
    }
    
    public class GUIUI extends AbstractComponentUI<KamiGUI>
    {
    }
}
