//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.render;

import com.veteran.hack.setting.*;
import com.veteran.hack.event.events.*;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.*;
import java.util.stream.*;
import java.awt.*;
import com.veteran.hack.module.modules.gui.*;
import com.veteran.hack.module.*;
import com.veteran.hack.util.*;
import java.util.*;

@Module.Info(name = "BoxESP", description = "Draws a box around EXP Bottles and Pearls", category = Module.Category.RENDER)
public class BoxESP extends Module
{
    private Setting<Integer> red;
    private Setting<Integer> green;
    private Setting<Integer> blue;
    private Setting<Boolean> peftMode;
    
    public BoxESP() {
        this.red = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Red").withMinimum(0).withMaximum(255).withValue(200).build());
        this.green = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Green").withMinimum(0).withMaximum(255).withValue(69).build());
        this.blue = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Blue").withMinimum(0).withMaximum(255).withValue(69).build());
        this.peftMode = (Setting<Boolean>)this.register((Setting)Settings.b("Chroma", false));
    }
    
    public void onWorldRender(final RenderEvent event) {
        final List<Entity> entities = new ArrayList<Entity>();
        entities.addAll((Collection<? extends Entity>)BoxESP.mc.world.loadedEntityList.stream().filter(entity -> entity instanceof EntityThrowable || entity instanceof EntityArrow).collect(Collectors.toList()));
        for (final Entity e : entities) {
            VetHackTessellator.prepare(7);
            final Color c = new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), 200);
            if (!this.peftMode.getValue()) {
                VetHackTessellator.drawBoxSmall(e.getPositionVector().x - 0.125, e.getPositionVector().y, e.getPositionVector().z - 0.125, c.getRGB(), 63);
            }
            else {
                final ActiveModules activeMods = (ActiveModules)ModuleManager.getModuleByName("ActiveModules");
                final float[] hue = { System.currentTimeMillis() % (360 * activeMods.getRainbowSpeed()) / (360.0f * activeMods.getRainbowSpeed()) };
                final int rgb = Color.HSBtoRGB(hue[0], ColourConverter.toF(activeMods.saturationR.getValue()), ColourConverter.toF(activeMods.brightnessR.getValue()));
                VetHackTessellator.drawBoxSmall(e.getPositionVector().x - 0.125, e.getPositionVector().y, e.getPositionVector().z - 0.125, rgb, 63);
            }
            VetHackTessellator.release();
        }
    }
}
