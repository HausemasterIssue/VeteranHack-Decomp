//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.util;

import com.veteran.hack.gui.rgui.render.font.*;
import com.veteran.hack.gui.kami.*;
import net.minecraft.client.*;
import net.minecraft.client.entity.*;
import net.minecraft.world.*;
import org.lwjgl.input.*;

public class Wrapper
{
    private static FontRenderer fontRenderer;
    
    public static void init() {
        Wrapper.fontRenderer = (FontRenderer)KamiGUI.fontRenderer;
    }
    
    public static Minecraft getMinecraft() {
        return Minecraft.getMinecraft();
    }
    
    public static EntityPlayerSP getPlayer() {
        return getMinecraft().player;
    }
    
    public static World getWorld() {
        return (World)getMinecraft().world;
    }
    
    public static int getKey(final String keyname) {
        return Keyboard.getKeyIndex(keyname.toUpperCase());
    }
    
    public static FontRenderer getFontRenderer() {
        return Wrapper.fontRenderer;
    }
}
