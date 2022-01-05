//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.movement;

import net.minecraftforge.client.event.*;
import me.zero.alpine.listener.*;
import com.veteran.hack.setting.*;
import java.util.function.*;
import net.minecraft.pathfinding.*;
import com.veteran.hack.util.*;
import net.minecraft.entity.player.*;
import com.veteran.hack.module.modules.render.*;
import com.veteran.hack.module.*;

@Module.Info(name = "AutoWalk", category = Module.Category.HIDDEN, description = "Automatically walks forward")
public class AutoWalk extends Module
{
    private Setting<AutoWalkMode> mode;
    @EventHandler
    private Listener<InputUpdateEvent> inputUpdateEventListener;
    
    public AutoWalk() {
        this.mode = (Setting<AutoWalkMode>)this.register((Setting)Settings.e("Mode", AutoWalkMode.FORWARD));
        this.inputUpdateEventListener = (Listener<InputUpdateEvent>)new Listener(event -> {
            switch (this.mode.getValue()) {
                case FORWARD: {
                    event.getMovementInput().moveForward = 1.0f;
                    break;
                }
                case BACKWARDS: {
                    event.getMovementInput().moveForward = -1.0f;
                    break;
                }
                case PATH: {
                    if (Pathfind.points.isEmpty()) {
                        return;
                    }
                    event.getMovementInput().moveForward = 1.0f;
                    if (AutoWalk.mc.player.isInWater() || AutoWalk.mc.player.isInLava()) {
                        AutoWalk.mc.player.movementInput.jump = true;
                    }
                    else if (AutoWalk.mc.player.collidedHorizontally && AutoWalk.mc.player.onGround) {
                        AutoWalk.mc.player.jump();
                    }
                    if (!ModuleManager.isModuleEnabled("Pathfind") || Pathfind.points.isEmpty()) {
                        return;
                    }
                    final PathPoint next = Pathfind.points.get(0);
                    this.lookAt(next);
                    break;
                }
            }
        }, new Predicate[0]);
    }
    
    private void lookAt(final PathPoint pathPoint) {
        final double[] v = EntityUtil.calculateLookAt(pathPoint.x + 0.5f, pathPoint.y, pathPoint.z + 0.5f, (EntityPlayer)AutoWalk.mc.player);
        AutoWalk.mc.player.rotationYaw = (float)v[0];
        AutoWalk.mc.player.rotationPitch = (float)v[1];
    }
    
    private enum AutoWalkMode
    {
        FORWARD, 
        BACKWARDS, 
        PATH;
    }
}
