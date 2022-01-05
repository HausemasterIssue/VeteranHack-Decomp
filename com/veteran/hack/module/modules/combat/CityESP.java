//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.combat;

import net.minecraft.util.math.*;
import com.veteran.hack.setting.*;
import com.veteran.hack.event.events.*;
import com.veteran.hack.module.modules.render.*;
import com.veteran.hack.module.*;
import java.awt.*;
import net.minecraft.entity.player.*;
import java.util.stream.*;
import net.minecraft.init.*;
import com.veteran.hack.util.*;
import java.util.*;

@Module.Info(name = "CityESP", category = Module.Category.RENDER, description = "draws a square on the face of a block that can be mined to city-trap your opponent.")
public class CityESP extends Module
{
    private Setting<Integer> red;
    private Setting<Integer> green;
    private Setting<Integer> blue;
    private Setting<Integer> alpha;
    private final BlockPos[] surroundOffset;
    
    public CityESP() {
        this.red = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Red").withMinimum(0).withMaximum(255).withValue(119).build());
        this.green = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Green").withMinimum(0).withMaximum(255).withValue(189).build());
        this.blue = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Blue").withMinimum(0).withMaximum(255).withValue(11).build());
        this.alpha = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Alpha").withMinimum(0).withMaximum(255).withValue(100).build());
        this.surroundOffset = new BlockPos[] { new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(-1, 0, 0) };
    }
    
    public void onWorldRender(final RenderEvent event) {
        if (CityESP.mc.world == null) {
            return;
        }
        final HoleESP h = (HoleESP)ModuleManager.getModuleByName("HoleESP");
        final Autocrystal a = (Autocrystal)ModuleManager.getModuleByName("Autocrystal");
        final Color c = new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue());
        final List<EntityPlayer> entities = new ArrayList<EntityPlayer>();
        entities.addAll((Collection<? extends EntityPlayer>)CityESP.mc.world.playerEntities.stream().filter(entityPlayer -> !Friends.isFriend(entityPlayer.getName())).collect(Collectors.toList()));
        for (final EntityPlayer e : entities) {
            int i = 0;
            for (final BlockPos add : this.surroundOffset) {
                ++i;
                final BlockPos o = new BlockPos(e.getPositionVector().x, e.getPositionVector().y, e.getPositionVector().z).add(add.x, add.y, add.z);
                if (CityESP.mc.world.getBlockState(o).getBlock() == Blocks.OBSIDIAN) {
                    VetHackTessellator.prepare(7);
                    if (i == 1 && a.canPlaceCrystal(o.north(1).down())) {
                        VetHackTessellator.drawBox(o.x, o.y, o.z, c.getRGB(), 4);
                    }
                    if (i == 2 && a.canPlaceCrystal(o.east(1).down())) {
                        VetHackTessellator.drawBox(o.x, o.y, o.z, c.getRGB(), 32);
                    }
                    if (i == 3 && a.canPlaceCrystal(o.south(1).down())) {
                        VetHackTessellator.drawBox(o.x, o.y, o.z, c.getRGB(), 8);
                    }
                    if (i == 4 && a.canPlaceCrystal(o.west(1).down())) {
                        VetHackTessellator.drawBox(o.x, o.y, o.z, c.getRGB(), 16);
                    }
                    VetHackTessellator.release();
                }
            }
        }
    }
}
