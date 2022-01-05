//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.misc;

import com.veteran.hack.module.*;
import net.minecraftforge.fml.common.gameevent.*;
import me.zero.alpine.listener.*;
import java.util.function.*;
import com.veteran.hack.util.*;
import java.util.*;
import com.veteran.hack.command.*;
import com.veteran.hack.command.commands.*;
import org.lwjgl.input.*;
import net.minecraft.client.*;
import net.minecraft.util.math.*;
import net.minecraft.client.entity.*;
import net.minecraft.entity.*;

@Module.Info(name = "MidClickFriends", category = Module.Category.MISC, description = "Middle click players to friend or unfriend them", showOnArray = Module.ShowOnArray.OFF)
public class MidClickFriends extends Module
{
    private int delay;
    @EventHandler
    public Listener<InputEvent.MouseInputEvent> mouseListener;
    
    public MidClickFriends() {
        this.delay = 0;
        this.mouseListener = (Listener<InputEvent.MouseInputEvent>)new Listener(event -> {
            if (this.delay == 0 && Mouse.getEventButton() == 2 && Minecraft.getMinecraft().objectMouseOver.typeOfHit.equals((Object)RayTraceResult.Type.ENTITY)) {
                final Entity lookedAtEntity = Minecraft.getMinecraft().objectMouseOver.entityHit;
                if (!(lookedAtEntity instanceof EntityOtherPlayerMP)) {
                    return;
                }
                if (Friends.isFriend(lookedAtEntity.getName())) {
                    this.remove(lookedAtEntity.getName());
                }
                else {
                    this.add(lookedAtEntity.getName());
                }
            }
        }, new Predicate[0]);
    }
    
    public void onUpdate() {
        if (this.delay > 0) {
            --this.delay;
        }
    }
    
    private void remove(final String name) {
        this.delay = 20;
        final Friends.Friend friend2 = Friends.friends.getValue().stream().filter(friend1 -> friend1.getUsername().equalsIgnoreCase(name)).findFirst().get();
        Friends.friends.getValue().remove(friend2);
        Command.sendChatMessage("&b" + friend2.getUsername() + "&r has been unfriended.");
    }
    
    private void add(final String name) {
        this.delay = 20;
        final Friends.Friend f;
        new Thread(() -> {
            f = new FriendCommand().getFriendByName(name);
            if (f == null) {
                Command.sendChatMessage("Failed to find UUID of " + name);
            }
            else {
                Friends.friends.getValue().add(f);
                Command.sendChatMessage("&b" + f.getUsername() + "&r has been friended.");
            }
        }).start();
    }
}
