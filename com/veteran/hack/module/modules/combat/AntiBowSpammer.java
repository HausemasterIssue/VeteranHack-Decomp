//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.combat;

import com.veteran.hack.module.*;
import net.minecraft.entity.*;
import com.veteran.hack.util.*;
import java.util.stream.*;
import net.minecraft.entity.player.*;
import net.minecraft.init.*;
import com.veteran.hack.command.*;
import java.util.*;

@Module.Info(name = "Anti Bowspammer", category = Module.Category.CHAT, description = "insults bowspammers so they feel bad about themselves xd")
public class AntiBowSpammer extends Module
{
    int sentLastMessage;
    
    public AntiBowSpammer() {
        this.sentLastMessage = 0;
    }
    
    public void onUpdate() {
        final List<Entity> entities = new ArrayList<Entity>();
        entities.addAll((Collection<? extends Entity>)AntiBowSpammer.mc.world.playerEntities.stream().filter(entityPlayer -> !Friends.isFriend(entityPlayer.getName())).collect(Collectors.toList()));
        for (final Entity e : entities) {
            if (((EntityPlayer)e).getHeldItemMainhand().getItem() == Items.BOW && this.sentLastMessage > 300) {
                this.sentLastMessage = 0;
                Command.sendServerMessage("/w " + e.getName() + " hey bowspamming larper faggot im going to rape your kids :cat_joy:");
            }
        }
        ++this.sentLastMessage;
    }
    
    public void onEnable() {
        Command.sendChatMessage("This module involves entities and /w. it may get you kicked, or lag your game. be cautious. this is a caveat");
    }
}
