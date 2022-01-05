//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.gui;

import com.veteran.hack.module.*;
import com.veteran.hack.util.*;
import com.veteran.hack.gui.rgui.component.container.use.*;
import net.minecraft.client.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.*;

@Module.Info(name = "Player View", description = "renders player", category = Module.Category.GUI)
public class PlayerView extends Module
{
    public void onRender() {
        final Frame frame = GuiFrameUtil.getFrameByName("Player View");
        if (frame == null) {
            return;
        }
        if (frame.isPinned()) {
            final float mouseXX = PlayerView.mc.getRenderViewEntity().rotationYaw * -1.0f;
            final float mouseYY = PlayerView.mc.getRenderViewEntity().rotationPitch * -1.0f;
            drawEntityOnScreen(frame.getX() + 15, frame.getY() + 725, 30, mouseXX, mouseYY, (EntityLivingBase)PlayerView.mc.player);
        }
    }
    
    public static void drawEntityOnScreen(final int posX, final int posY, final int scale, final float mouseX, final float mouseY, final EntityLivingBase ent) {
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)posX, (float)posY, 50.0f);
        GlStateManager.scale((float)(-scale), (float)scale, (float)scale);
        GlStateManager.rotate(180.0f, 0.0f, 0.0f, 1.0f);
        final float f = ent.renderYawOffset;
        final float f2 = ent.rotationYaw;
        final float f3 = ent.rotationPitch;
        final float f4 = ent.prevRotationYawHead;
        final float f5 = ent.rotationYawHead;
        GlStateManager.rotate(135.0f, 0.0f, 1.0f, 0.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-(float)Math.atan(mouseY / 40.0f) * 20.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.translate(0.0f, 0.0f, 0.0f);
        final RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0f);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntity((Entity)ent, 0.0, 0.0, 0.0, 0.0f, 1.0f, false);
        rendermanager.setRenderShadow(true);
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
}
