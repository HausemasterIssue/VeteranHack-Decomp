//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.gui;

import com.veteran.hack.module.*;
import com.veteran.hack.setting.*;

@Module.Info(name = "Zoom", category = Module.Category.GUI, description = "Configures FOV", showOnArray = Module.ShowOnArray.OFF)
public class Zoom extends Module
{
    private float fov;
    private float sensi;
    private Setting<Integer> fovChange;
    private Setting<Float> sensChange;
    private Setting<Boolean> smoothCamera;
    private Setting<Boolean> sens;
    
    public Zoom() {
        this.fov = 0.0f;
        this.sensi = 0.0f;
        this.fovChange = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("FOV").withMinimum(30).withValue(30).withMaximum(179).build());
        this.sensChange = (Setting<Float>)this.register((Setting)Settings.floatBuilder("Sensitivity").withMinimum(0.25f).withValue(1.3f).withMaximum(2.0f).build());
        this.smoothCamera = (Setting<Boolean>)this.register((Setting)Settings.b("Cinematic Camera", true));
        this.sens = (Setting<Boolean>)this.register((Setting)Settings.b("Sensitivity", true));
    }
    
    public void onEnable() {
        if (Zoom.mc.player == null) {
            return;
        }
        this.fov = Zoom.mc.gameSettings.fovSetting;
        this.sensi = Zoom.mc.gameSettings.mouseSensitivity;
        if (this.smoothCamera.getValue()) {
            Zoom.mc.gameSettings.smoothCamera = true;
        }
    }
    
    public void onDisable() {
        Zoom.mc.gameSettings.fovSetting = this.fov;
        Zoom.mc.gameSettings.mouseSensitivity = this.sensi;
        if (this.smoothCamera.getValue()) {
            Zoom.mc.gameSettings.smoothCamera = false;
        }
    }
    
    public void onUpdate() {
        if (Zoom.mc.player == null) {
            return;
        }
        Zoom.mc.gameSettings.fovSetting = this.fovChange.getValue();
        if (this.smoothCamera.getValue()) {
            Zoom.mc.gameSettings.smoothCamera = true;
        }
        else {
            Zoom.mc.gameSettings.smoothCamera = false;
        }
        if (this.sens.getValue()) {
            Zoom.mc.gameSettings.mouseSensitivity = this.sensi * this.sensChange.getValue();
        }
    }
}
