//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.gui.kami.component;

import com.veteran.hack.gui.rgui.component.container.*;
import com.veteran.hack.gui.rgui.component.container.use.*;
import com.veteran.hack.gui.rgui.component.use.*;
import com.veteran.hack.gui.rgui.render.theme.*;
import com.veteran.hack.gui.kami.*;
import com.veteran.hack.gui.rgui.layout.*;
import com.veteran.hack.gui.rgui.component.*;
import com.veteran.hack.gui.rgui.component.listen.*;

public class Chat extends AbstractContainer
{
    Scrollpane scrollpane;
    Label label;
    InputField field;
    
    public Chat(final Theme theme, final int width, final int height) {
        super(theme);
        this.label = new Label("", true);
        this.field = new InputField(width);
        (this.scrollpane = new Scrollpane(this.getTheme(), new Stretcherlayout(1), width, height)).setWidth(width);
        this.scrollpane.setHeight(height);
        this.scrollpane.setLockHeight(true).setLockWidth(true);
        this.scrollpane.addChild(this.label);
        this.field.addKeyListener(new KeyListener() {
            @Override
            public void onKeyDown(final KeyEvent event) {
                if (event.getKey() == 28) {
                    Chat.this.label.addLine(Chat.this.field.getText());
                    Chat.this.field.setText("");
                    if (Chat.this.scrollpane.canScrollY()) {
                        Chat.this.scrollpane.setScrolledY(Chat.this.scrollpane.getMaxScrollY());
                    }
                }
            }
            
            @Override
            public void onKeyUp(final KeyEvent event) {
            }
        });
        this.addChild(this.scrollpane);
        this.addChild(this.field);
        this.scrollpane.setLockHeight(false);
        this.scrollpane.setHeight(height - this.field.getHeight());
        this.scrollpane.setLockHeight(true);
        this.setWidth(width);
        this.setHeight(height);
        this.field.setY(this.getHeight() - this.field.getHeight());
    }
}
