//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.render;

import com.veteran.hack.module.*;
import com.veteran.hack.setting.*;
import com.veteran.hack.event.events.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.*;
import java.util.function.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import org.lwjgl.opengl.*;
import com.veteran.hack.util.*;

@Module.Info(name = "Tracers", description = "Draws lines to other living entities", category = Module.Category.HIDDEN)
public class Tracers extends Module
{
    private Setting<Boolean> players;
    private Setting<Boolean> friends;
    private Setting<Boolean> animals;
    private Setting<Boolean> mobs;
    private Setting<Double> range;
    private Setting<Boolean> renderInvis;
    private Setting<Boolean> customColours;
    private Setting<Float> opacity;
    private Setting<Integer> r;
    private Setting<Integer> g;
    private Setting<Integer> b;
    HueCycler cycler;
    
    public Tracers() {
        this.players = (Setting<Boolean>)this.register((Setting)Settings.b("Players", true));
        this.friends = (Setting<Boolean>)this.register((Setting)Settings.b("Friends", true));
        this.animals = (Setting<Boolean>)this.register((Setting)Settings.b("Animals", false));
        this.mobs = (Setting<Boolean>)this.register((Setting)Settings.b("Mobs", false));
        this.range = (Setting<Double>)this.register((Setting)Settings.d("Range", 200.0));
        this.renderInvis = (Setting<Boolean>)this.register((Setting)Settings.b("Invisible", false));
        this.customColours = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("Custom Colours").withValue(true).build());
        this.opacity = (Setting<Float>)this.register((Setting)Settings.floatBuilder("Opacity").withRange(0.0f, 1.0f).withValue(1.0f).build());
        this.r = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Red").withMinimum(0).withValue(155).withMaximum(255).withVisibility(v -> this.customColours.getValue()).build());
        this.g = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Green").withMinimum(0).withValue(144).withMaximum(255).withVisibility(v -> this.customColours.getValue()).build());
        this.b = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Blue").withMinimum(0).withValue(255).withMaximum(255).withVisibility(v -> this.customColours.getValue()).build());
        this.cycler = new HueCycler(3600);
    }
    
    public void onWorldRender(final RenderEvent event) {
        GlStateManager.pushMatrix();
        final int colour;
        int colour2;
        final float r;
        final float g;
        final float b;
        Minecraft.getMinecraft().world.loadedEntityList.stream().filter(EntityUtil::isLiving).filter(entity -> !entity.isInvisible() || this.renderInvis.getValue()).filter(entity -> !EntityUtil.isFakeLocalPlayer(entity)).filter(entity -> (entity instanceof EntityPlayer) ? (this.players.getValue() && Tracers.mc.player != entity) : (EntityUtil.isPassive(entity) ? this.animals.getValue() : ((boolean)this.mobs.getValue()))).filter(entity -> Tracers.mc.player.getDistance(entity) < this.range.getValue()).forEach(entity -> {
            colour = this.getColour(entity);
            if (colour == Integer.MIN_VALUE) {
                if (!this.friends.getValue()) {
                    return;
                }
                else if (this.customColours.getValue()) {
                    colour2 = ColourConverter.rgbToInt(this.r.getValue(), this.g.getValue(), this.b.getValue(), (int)(this.opacity.getValue() * 255.0f));
                }
                else {
                    colour2 = this.cycler.current();
                }
            }
            else {
                colour2 = this.cycler.current();
            }
            r = (colour2 >>> 16 & 0xFF) / 255.0f;
            g = (colour2 >>> 8 & 0xFF) / 255.0f;
            b = (colour2 & 0xFF) / 255.0f;
            drawLineToEntity(entity, r, g, b, this.opacity.getValue());
            return;
        });
        GlStateManager.popMatrix();
    }
    
    public void onUpdate() {
        this.cycler.next();
    }
    
    private void drawRainbowToEntity(final Entity entity, final float opacity) {
        final Vec3d eyes = new Vec3d(0.0, 0.0, 1.0).rotatePitch(-(float)Math.toRadians(Minecraft.getMinecraft().player.rotationPitch)).rotateYaw(-(float)Math.toRadians(Minecraft.getMinecraft().player.rotationYaw));
        final double[] xyz = interpolate(entity);
        final double posx = xyz[0];
        final double posy = xyz[1];
        final double posz = xyz[2];
        final double posx2 = eyes.x;
        final double posy2 = eyes.y + Tracers.mc.player.getEyeHeight();
        final double posz2 = eyes.z;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(1.5f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        this.cycler.reset();
        this.cycler.setNext(opacity);
        GlStateManager.disableLighting();
        GL11.glLoadIdentity();
        Tracers.mc.entityRenderer.orientCamera(Tracers.mc.getRenderPartialTicks());
        GL11.glBegin(1);
        GL11.glVertex3d(posx, posy, posz);
        GL11.glVertex3d(posx2, posy2, posz2);
        this.cycler.setNext(opacity);
        GL11.glVertex3d(posx2, posy2, posz2);
        GL11.glVertex3d(posx2, posy2, posz2);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glColor3d(1.0, 1.0, 1.0);
        GlStateManager.enableLighting();
    }
    
    private int getColour(final Entity entity) {
        if (entity instanceof EntityPlayer) {
            return Friends.isFriend(entity.getName()) ? Integer.MIN_VALUE : ColourUtils.Colors.WHITE;
        }
        if (EntityUtil.isPassive(entity)) {
            return ColourUtils.Colors.GREEN;
        }
        return ColourUtils.Colors.RED;
    }
    
    public static double interpolate(final double now, final double then) {
        return then + (now - then) * Tracers.mc.getRenderPartialTicks();
    }
    
    public static double[] interpolate(final Entity entity) {
        final double posX = interpolate(entity.posX, entity.lastTickPosX) - Tracers.mc.getRenderManager().renderPosX;
        final double posY = interpolate(entity.posY, entity.lastTickPosY) - Tracers.mc.getRenderManager().renderPosY;
        final double posZ = interpolate(entity.posZ, entity.lastTickPosZ) - Tracers.mc.getRenderManager().renderPosZ;
        return new double[] { posX, posY, posZ };
    }
    
    public static void drawLineToEntity(final Entity e, final float red, final float green, final float blue, final float opacity) {
        final double[] xyz = interpolate(e);
        drawLine(xyz[0], xyz[1], xyz[2], e.height, red, green, blue, opacity);
    }
    
    public static void drawLine(final double posx, final double posy, final double posz, final double up, final float red, final float green, final float blue, final float opacity) {
        final Vec3d eyes = new Vec3d(0.0, 0.0, 1.0).rotatePitch(-(float)Math.toRadians(Minecraft.getMinecraft().player.rotationPitch)).rotateYaw(-(float)Math.toRadians(Minecraft.getMinecraft().player.rotationYaw));
        drawLineFromPosToPos(eyes.x, eyes.y + Tracers.mc.player.getEyeHeight(), eyes.z, posx, posy, posz, up, red, green, blue, opacity);
    }
    
    public static void drawLineFromPosToPos(final double posx, final double posy, final double posz, final double posx2, final double posy2, final double posz2, final double up, final float red, final float green, final float blue, final float opacity) {
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(1.5f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, opacity);
        GlStateManager.disableLighting();
        GL11.glLoadIdentity();
        Tracers.mc.entityRenderer.orientCamera(Tracers.mc.getRenderPartialTicks());
        GL11.glBegin(1);
        GL11.glVertex3d(posx, posy, posz);
        GL11.glVertex3d(posx2, posy2, posz2);
        GL11.glVertex3d(posx2, posy2, posz2);
        GL11.glVertex3d(posx2, posy2 + up, posz2);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glColor3d(1.0, 1.0, 1.0);
        GlStateManager.enableLighting();
    }
}
