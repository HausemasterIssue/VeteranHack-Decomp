//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.gui.rgui.render.util;

import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.*;

public final class Uniform
{
    private final String name;
    private final int location;
    
    private Uniform(final String name, final int location) {
        this.name = name;
        this.location = location;
    }
    
    public final void setInt(final int value) {
        ARBShaderObjects.glUniform1iARB(this.location, value);
    }
    
    public final void setFloat(final float value) {
        ARBShaderObjects.glUniform1fARB(this.location, value);
    }
    
    public final void setBoolean(final boolean value) {
        ARBShaderObjects.glUniform1fARB(this.location, value ? 1.0f : 0.0f);
    }
    
    public final void setVec(final Vector2f value) {
        ARBShaderObjects.glUniform2fARB(this.location, value.x, value.y);
    }
    
    public final void setVec(final Vector3f value) {
        ARBShaderObjects.glUniform3fARB(this.location, value.x, value.y, value.z);
    }
    
    public final String getName() {
        return this.name;
    }
    
    public final int getLocation() {
        return this.location;
    }
    
    public static Uniform get(final int shaderID, final String uniformName) {
        return new Uniform(uniformName, ARBShaderObjects.glGetUniformLocationARB(shaderID, (CharSequence)uniformName));
    }
}
