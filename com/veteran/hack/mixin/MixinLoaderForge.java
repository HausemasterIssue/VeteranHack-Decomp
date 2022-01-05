//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.mixin;

import net.minecraftforge.fml.relauncher.*;
import com.veteran.hack.*;
import org.spongepowered.asm.launch.*;
import org.spongepowered.asm.mixin.*;
import java.util.*;

public class MixinLoaderForge implements IFMLLoadingPlugin
{
    private static boolean isObfuscatedEnvironment;
    
    public MixinLoaderForge() {
        BaseMod.log.info("VETHACK mixins initialized");
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.kami.json");
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
        BaseMod.log.info(MixinEnvironment.getDefaultEnvironment().getObfuscationContext());
    }
    
    public String[] getASMTransformerClass() {
        return new String[0];
    }
    
    public String getModContainerClass() {
        return null;
    }
    
    public String getSetupClass() {
        return null;
    }
    
    public void injectData(final Map<String, Object> data) {
        MixinLoaderForge.isObfuscatedEnvironment = data.get("runtimeDeobfuscationEnabled");
    }
    
    public String getAccessTransformerClass() {
        return null;
    }
    
    static {
        MixinLoaderForge.isObfuscatedEnvironment = false;
    }
}
