//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.gui.rgui.component.listen;

public interface KeyListener
{
    void onKeyDown(final KeyEvent p0);
    
    void onKeyUp(final KeyEvent p0);
    
    public static class KeyEvent
    {
        int key;
        
        public KeyEvent(final int key) {
            this.key = key;
        }
        
        public int getKey() {
            return this.key;
        }
        
        public void setKey(final int key) {
            this.key = key;
        }
    }
}
