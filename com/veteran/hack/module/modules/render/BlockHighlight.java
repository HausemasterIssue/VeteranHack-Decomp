//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.render;

import com.veteran.hack.setting.*;
import com.veteran.hack.event.events.*;
import net.minecraft.client.*;
import net.minecraft.init.*;
import com.veteran.hack.module.modules.gui.*;
import com.veteran.hack.module.*;
import com.veteran.hack.util.*;
import java.awt.*;
import net.minecraft.util.math.*;

@Module.Info(name = "BlockHighlight", description = "renders better blovk highlight for some ppl ", category = Module.Category.RENDER)
public class BlockHighlight extends Module
{
    private Setting<Integer> red;
    private Setting<Integer> green;
    private Setting<Integer> blue;
    private Setting<Integer> a;
    private Setting<Boolean> peftMode;
    
    public BlockHighlight() {
        this.red = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Red").withMinimum(0).withMaximum(255).withValue(0).build());
        this.green = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Green").withMinimum(0).withMaximum(255).withValue(0).build());
        this.blue = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Blue").withMinimum(0).withMaximum(255).withValue(200).build());
        this.a = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Alpha").withMinimum(0).withMaximum(255).withValue(0).build());
        this.peftMode = (Setting<Boolean>)this.register((Setting)Settings.b("Chroma", false));
    }
    
    public void onWorldRender(final RenderEvent event) {
        final RayTraceResult result = BlockHighlight.mc.player.rayTrace(6.0, Minecraft.getMinecraft().getRenderPartialTicks());
        if (result == null) {
            return;
        }
        final BlockPos lookingAt = new BlockPos((Vec3i)result.getBlockPos());
        if (BlockHighlight.mc.world.getBlockState(lookingAt).getBlock() == Blocks.AIR) {
            return;
        }
        VetHackTessellator.prepare(7);
        if (!this.peftMode.getValue()) {
            VetHackTessellator.drawBoundingBoxBlockPos(lookingAt, 1.0f, this.red.getValue(), this.green.getValue(), this.blue.getValue(), 255);
        }
        else {
            final ActiveModules activeMods = (ActiveModules)ModuleManager.getModuleByName("ActiveModules");
            final float[] hue = { System.currentTimeMillis() % (360 * activeMods.getRainbowSpeed()) / (360.0f * activeMods.getRainbowSpeed()) };
            final int rgb = Color.HSBtoRGB(hue[0], ColourConverter.toF(activeMods.saturationR.getValue()), ColourConverter.toF(activeMods.brightnessR.getValue()));
            final int r = rgb >> 16 & 0xFF;
            final int g = rgb >> 8 & 0xFF;
            final int b = rgb & 0xFF;
            VetHackTessellator.drawBoundingBoxBlockPos(lookingAt, 1.0f, r, g, b, this.a.getValue());
        }
        VetHackTessellator.release();
    }
}
