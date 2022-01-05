//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.render;

import com.veteran.hack.module.*;
import com.veteran.hack.setting.*;
import com.veteran.hack.event.events.*;
import net.minecraft.entity.player.*;
import java.util.function.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import com.veteran.hack.util.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;

@Module.Info(name = "EyeFinder", description = "Draw lines from entity's heads to where they are looking", category = Module.Category.RENDER)
public class EyeFinder extends Module
{
    private Setting<Boolean> players;
    private Setting<Boolean> mobs;
    private Setting<Boolean> animals;
    
    public EyeFinder() {
        this.players = (Setting<Boolean>)this.register((Setting)Settings.b("Players", true));
        this.mobs = (Setting<Boolean>)this.register((Setting)Settings.b("Mobs", false));
        this.animals = (Setting<Boolean>)this.register((Setting)Settings.b("Animals", false));
    }
    
    public void onWorldRender(final RenderEvent event) {
        final boolean b;
        EyeFinder.mc.world.loadedEntityList.stream().filter(EntityUtil::isLiving).filter(entity -> EyeFinder.mc.player != entity).map(entity -> entity).filter(entityLivingBase -> !entityLivingBase.isDead).filter(entity -> {
            if (!this.players.getValue() || !(entity instanceof EntityPlayer)) {
                if (!(EntityUtil.isPassive(entity) ? this.animals.getValue() : this.mobs.getValue())) {
                    return b;
                }
            }
            return b;
        }).forEach(this::drawLine);
    }
    
    private void drawLine(final EntityLivingBase e) {
        final RayTraceResult result = e.rayTrace(6.0, Minecraft.getMinecraft().getRenderPartialTicks());
        if (result == null) {
            return;
        }
        final Vec3d eyes = e.getPositionEyes(Minecraft.getMinecraft().getRenderPartialTicks());
        GlStateManager.enableDepth();
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        final double posx = eyes.x - EyeFinder.mc.getRenderManager().renderPosX;
        final double posy = eyes.y - EyeFinder.mc.getRenderManager().renderPosY;
        final double posz = eyes.z - EyeFinder.mc.getRenderManager().renderPosZ;
        final double posx2 = result.hitVec.x - EyeFinder.mc.getRenderManager().renderPosX;
        final double posy2 = result.hitVec.y - EyeFinder.mc.getRenderManager().renderPosY;
        final double posz2 = result.hitVec.z - EyeFinder.mc.getRenderManager().renderPosZ;
        GL11.glColor4f(0.2f, 0.1f, 0.3f, 0.8f);
        GlStateManager.glLineWidth(1.5f);
        GL11.glBegin(1);
        GL11.glVertex3d(posx, posy, posz);
        GL11.glVertex3d(posx2, posy2, posz2);
        GL11.glVertex3d(posx2, posy2, posz2);
        GL11.glVertex3d(posx2, posy2, posz2);
        GL11.glEnd();
        if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
            VetHackTessellator.prepare(7);
            GL11.glEnable(2929);
            final BlockPos b = result.getBlockPos();
            final float x = b.x - 0.01f;
            final float y = b.y - 0.01f;
            final float z = b.z - 0.01f;
            VetHackTessellator.drawBox(VetHackTessellator.getBufferBuilder(), x, y, z, 1.01f, 1.01f, 1.01f, 51, 25, 73, 200, 63);
            VetHackTessellator.release();
        }
        GlStateManager.enableTexture2D();
        GlStateManager.enableLighting();
    }
}
