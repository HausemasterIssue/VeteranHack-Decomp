//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.util;

import org.lwjgl.input.*;
import com.veteran.hack.command.commands.*;

public class Bind
{
    boolean ctrl;
    boolean alt;
    boolean shift;
    int key;
    
    public Bind(final boolean ctrl, final boolean alt, final boolean shift, final int key) {
        this.ctrl = ctrl;
        this.alt = alt;
        this.shift = shift;
        this.key = key;
    }
    
    public int getKey() {
        return this.key;
    }
    
    public boolean isCtrl() {
        return this.ctrl;
    }
    
    public boolean isAlt() {
        return this.alt;
    }
    
    public boolean isShift() {
        return this.shift;
    }
    
    public boolean isEmpty() {
        return !this.ctrl && !this.shift && !this.alt && this.key < 0;
    }
    
    public void setAlt(final boolean alt) {
        this.alt = alt;
    }
    
    public void setCtrl(final boolean ctrl) {
        this.ctrl = ctrl;
    }
    
    public void setKey(final int key) {
        this.key = key;
    }
    
    public void setShift(final boolean shift) {
        this.shift = shift;
    }
    
    @Override
    public String toString() {
        return this.isEmpty() ? "None" : ((this.isCtrl() ? "Ctrl+" : "") + (this.isAlt() ? "Alt+" : "") + (this.isShift() ? "Shift+" : "") + ((this.key < 0) ? "None" : this.capitalise(Keyboard.getKeyName(this.key))));
    }
    
    public boolean isDown(final int eventKey) {
        return !this.isEmpty() && (!(boolean)BindCommand.modifiersEnabled.getValue() || (this.isShift() == isShiftDown() && this.isCtrl() == isCtrlDown() && this.isAlt() == isAltDown())) && eventKey == this.getKey();
    }
    
    public static boolean isShiftDown() {
        return Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54);
    }
    
    public static boolean isCtrlDown() {
        return Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157);
    }
    
    public static boolean isAltDown() {
        return Keyboard.isKeyDown(56) || Keyboard.isKeyDown(184);
    }
    
    public String capitalise(final String str) {
        if (str.isEmpty()) {
            return "";
        }
        return Character.toUpperCase(str.charAt(0)) + ((str.length() != 1) ? str.substring(1).toLowerCase() : "");
    }
    
    public static Bind none() {
        return new Bind(false, false, false, -1);
    }
}
