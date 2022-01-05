//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.utils;

import com.veteran.hack.module.*;
import net.minecraftforge.fml.common.gameevent.*;
import me.zero.alpine.listener.*;
import com.veteran.hack.setting.*;
import java.util.function.*;
import org.lwjgl.input.*;
import net.minecraft.util.math.*;
import net.minecraft.nbt.*;
import com.veteran.hack.command.*;

@Module.Info(name = "EntityTools", category = Module.Category.UTILS, description = "Right click entities to perform actions on them")
public class EntityTools extends Module
{
    private Setting<Mode> mode;
    private int delay;
    @EventHandler
    public Listener<InputEvent.MouseInputEvent> mouseListener;
    
    public EntityTools() {
        this.mode = (Setting<Mode>)this.register((Setting)Settings.e("Mode", Mode.DELETE));
        this.delay = 0;
        this.mouseListener = (Listener<InputEvent.MouseInputEvent>)new Listener(event -> {
            if (Mouse.getEventButton() == 1 && this.delay == 0 && EntityTools.mc.objectMouseOver.typeOfHit.equals((Object)RayTraceResult.Type.ENTITY)) {
                if (this.mode.getValue().equals(Mode.DELETE)) {
                    EntityTools.mc.world.removeEntity(EntityTools.mc.objectMouseOver.entityHit);
                }
                if (this.mode.getValue().equals(Mode.INFO)) {
                    final NBTTagCompound tag = new NBTTagCompound();
                    EntityTools.mc.objectMouseOver.entityHit.writeToNBT(tag);
                    Command.sendChatMessage(this.getChatName() + "&6Entity Tags:\n" + tag + "");
                }
            }
        }, new Predicate[0]);
    }
    
    public void onUpdate() {
        if (this.delay > 0) {
            --this.delay;
        }
    }
    
    private enum Mode
    {
        DELETE, 
        INFO;
    }
}
