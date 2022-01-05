//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.hidden;

import com.veteran.hack.module.*;
import com.veteran.hack.event.events.*;
import me.zero.alpine.listener.*;
import java.util.function.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.tileentity.*;
import net.minecraft.client.gui.*;
import java.io.*;
import net.minecraft.util.text.*;

@Module.Info(name = "ColourSign", description = "Allows ingame colouring of text on signs", category = Module.Category.HIDDEN)
public class ColourSign extends Module
{
    @EventHandler
    public Listener<GuiScreenEvent.Displayed> eventListener;
    
    public ColourSign() {
        this.eventListener = (Listener<GuiScreenEvent.Displayed>)new Listener(event -> {
            if (event.getScreen() instanceof GuiEditSign && this.isEnabled()) {
                event.setScreen((GuiScreen)new KamiGuiEditSign(((GuiEditSign)event.getScreen()).tileSign));
            }
        }, new Predicate[0]);
    }
    
    private class KamiGuiEditSign extends GuiEditSign
    {
        public KamiGuiEditSign(final TileEntitySign teSign) {
            super(teSign);
        }
        
        public void initGui() {
            super.initGui();
        }
        
        protected void actionPerformed(final GuiButton button) throws IOException {
            if (button.id == 0) {
                this.tileSign.signText[this.editLine] = (ITextComponent)new TextComponentString(this.tileSign.signText[this.editLine].getFormattedText().replaceAll("(§)(.)", "$1$1$2$2"));
            }
            super.actionPerformed(button);
        }
        
        protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
            super.keyTyped(typedChar, keyCode);
            String s = ((TextComponentString)this.tileSign.signText[this.editLine]).getText();
            s = s.replace("&", "§");
            this.tileSign.signText[this.editLine] = (ITextComponent)new TextComponentString(s);
        }
    }
}
