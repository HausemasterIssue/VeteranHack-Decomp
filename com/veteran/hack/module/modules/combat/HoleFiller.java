//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.combat;

import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import me.zero.alpine.listener.*;
import com.veteran.hack.setting.*;
import java.util.function.*;
import com.veteran.hack.module.*;
import com.mojang.realmsclient.gui.*;
import com.veteran.hack.command.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;
import com.veteran.hack.event.events.*;
import com.veteran.hack.util.*;
import net.minecraft.util.*;
import java.util.stream.*;
import java.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

@Module.Info(name = "HoleFiller", category = Module.Category.COMBAT)
public class HoleFiller extends Module
{
    private static BlockPos PlayerPos;
    private Setting<Double> range;
    private Setting<Boolean> smart;
    private Setting<Integer> smartRange;
    private Setting<Boolean> announceUsage;
    private BlockPos render;
    private Entity renderEnt;
    private EntityPlayer closestTarget;
    private long systemTime;
    private static boolean togglePitch;
    private boolean switchCooldown;
    private boolean isAttacking;
    private boolean caOn;
    private int newSlot;
    double d;
    private static boolean isSpoofingAngles;
    private static double yaw;
    private static double pitch;
    @EventHandler
    private Listener<PacketEvent.Send> packetListener;
    
    public HoleFiller() {
        this.range = (Setting<Double>)this.register((Setting)Settings.d("Range", 4.5));
        this.smart = (Setting<Boolean>)this.register((Setting)Settings.b("Smart", false));
        this.smartRange = (Setting<Integer>)this.register((Setting)Settings.i("Smart Range", 4));
        this.announceUsage = (Setting<Boolean>)this.register((Setting)Settings.b("Announce Usage", false));
        this.systemTime = -1L;
        this.switchCooldown = false;
        this.isAttacking = false;
        this.packetListener = (Listener<PacketEvent.Send>)new Listener(event -> {
            final Packet packet = event.getPacket();
            if (packet instanceof CPacketPlayer && HoleFiller.isSpoofingAngles) {
                ((CPacketPlayer)packet).yaw = (float)HoleFiller.yaw;
                ((CPacketPlayer)packet).pitch = (float)HoleFiller.pitch;
            }
        }, new Predicate[0]);
    }
    
    public void onEnable() {
        if (ModuleManager.getModuleByName("Autocrystal").isEnabled()) {
            this.caOn = true;
        }
        if (this.announceUsage.getValue()) {
            Command.sendChatMessage("[HoleFiller] " + ChatFormatting.GREEN.toString() + "Enabled" + ChatFormatting.RESET.toString() + "!");
        }
    }
    
    public void onUpdate() {
        if (HoleFiller.mc.world == null) {
            return;
        }
        if (this.smart.getValue()) {
            this.findClosestTarget();
        }
        final List<BlockPos> blocks = this.findCrystalBlocks();
        BlockPos q = null;
        final double dist = 0.0;
        final double prevDist = 0.0;
        int obsidianSlot = (HoleFiller.mc.player.getHeldItemMainhand().getItem() == Item.getItemFromBlock(Blocks.OBSIDIAN)) ? HoleFiller.mc.player.inventory.currentItem : -1;
        if (obsidianSlot == -1) {
            for (int l = 0; l < 9; ++l) {
                if (HoleFiller.mc.player.inventory.getStackInSlot(l).getItem() == Item.getItemFromBlock(Blocks.OBSIDIAN)) {
                    obsidianSlot = l;
                    break;
                }
            }
        }
        if (obsidianSlot == -1) {
            return;
        }
        for (final BlockPos blockPos : blocks) {
            if (HoleFiller.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(blockPos)).isEmpty()) {
                if (this.smart.getValue() && this.isInRange(blockPos)) {
                    q = blockPos;
                }
                else {
                    q = blockPos;
                }
            }
        }
        this.render = q;
        if (q != null && HoleFiller.mc.player.onGround) {
            if (this.caOn) {
                ModuleManager.getModuleByName("Autocrystal").disable();
            }
            final int oldSlot = HoleFiller.mc.player.inventory.currentItem;
            if (HoleFiller.mc.player.inventory.currentItem != obsidianSlot) {
                HoleFiller.mc.player.inventory.currentItem = obsidianSlot;
            }
            this.lookAtPacket(q.x + 0.5, q.y - 0.5, q.z + 0.5, (EntityPlayer)HoleFiller.mc.player);
            BlockInteractionHelper.placeBlockScaffold(this.render);
            HoleFiller.mc.player.swingArm(EnumHand.MAIN_HAND);
            HoleFiller.mc.player.inventory.currentItem = oldSlot;
            resetRotation();
            if (this.caOn) {
                ModuleManager.getModuleByName("Autocrystal").enable();
            }
        }
    }
    
    public void onWorldRender(final RenderEvent event) {
        if (this.render != null) {
            VetHackTessellator.prepare(7);
            VetHackTessellator.drawBox(this.render, 822018048, 63);
            VetHackTessellator.release();
            VetHackTessellator.prepare(7);
            VetHackTessellator.drawBoundingBoxBlockPos(this.render, 1.0f, 0, 255, 0, 170);
            VetHackTessellator.release();
        }
    }
    
    private double getDistanceToBlockPos(final BlockPos pos1, final BlockPos pos2) {
        final double x = pos1.getX() - pos2.getX();
        final double y = pos1.getY() - pos2.getY();
        final double z = pos1.getZ() - pos2.getZ();
        return Math.sqrt(x * x + y * y + z * z);
    }
    
    private void lookAtPacket(final double px, final double py, final double pz, final EntityPlayer me) {
        final double[] v = EntityUtil.calculateLookAt(px, py, pz, me);
        setYawAndPitch((float)v[0], (float)v[1]);
    }
    
    private boolean IsHole(final BlockPos blockPos) {
        final BlockPos boost = blockPos.add(0, 1, 0);
        final BlockPos boost2 = blockPos.add(0, 0, 0);
        final BlockPos boost3 = blockPos.add(0, 0, -1);
        final BlockPos boost4 = blockPos.add(1, 0, 0);
        final BlockPos boost5 = blockPos.add(-1, 0, 0);
        final BlockPos boost6 = blockPos.add(0, 0, 1);
        final BlockPos boost7 = blockPos.add(0, 2, 0);
        final BlockPos boost8 = blockPos.add(0.5, 0.5, 0.5);
        final BlockPos boost9 = blockPos.add(0, -1, 0);
        return HoleFiller.mc.world.getBlockState(boost).getBlock() == Blocks.AIR && HoleFiller.mc.world.getBlockState(boost2).getBlock() == Blocks.AIR && HoleFiller.mc.world.getBlockState(boost7).getBlock() == Blocks.AIR && (HoleFiller.mc.world.getBlockState(boost3).getBlock() == Blocks.OBSIDIAN || HoleFiller.mc.world.getBlockState(boost3).getBlock() == Blocks.BEDROCK) && (HoleFiller.mc.world.getBlockState(boost4).getBlock() == Blocks.OBSIDIAN || HoleFiller.mc.world.getBlockState(boost4).getBlock() == Blocks.BEDROCK) && (HoleFiller.mc.world.getBlockState(boost5).getBlock() == Blocks.OBSIDIAN || HoleFiller.mc.world.getBlockState(boost5).getBlock() == Blocks.BEDROCK) && (HoleFiller.mc.world.getBlockState(boost6).getBlock() == Blocks.OBSIDIAN || HoleFiller.mc.world.getBlockState(boost6).getBlock() == Blocks.BEDROCK) && HoleFiller.mc.world.getBlockState(boost8).getBlock() == Blocks.AIR && (HoleFiller.mc.world.getBlockState(boost9).getBlock() == Blocks.OBSIDIAN || HoleFiller.mc.world.getBlockState(boost9).getBlock() == Blocks.BEDROCK);
    }
    
    public static BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(HoleFiller.mc.player.posX), Math.floor(HoleFiller.mc.player.posY), Math.floor(HoleFiller.mc.player.posZ));
    }
    
    public BlockPos getClosestTargetPos() {
        if (this.closestTarget != null) {
            return new BlockPos(Math.floor(this.closestTarget.posX), Math.floor(this.closestTarget.posY), Math.floor(this.closestTarget.posZ));
        }
        return null;
    }
    
    private void findClosestTarget() {
        final List<EntityPlayer> playerList = (List<EntityPlayer>)HoleFiller.mc.world.playerEntities;
        this.closestTarget = null;
        for (final EntityPlayer target : playerList) {
            if (target == HoleFiller.mc.player) {
                continue;
            }
            if (Friends.isFriend(target.getName())) {
                continue;
            }
            if (!EntityUtil.isLiving((Entity)target)) {
                continue;
            }
            if (target.getHealth() <= 0.0f) {
                continue;
            }
            if (this.closestTarget == null) {
                this.closestTarget = target;
            }
            else {
                if (HoleFiller.mc.player.getDistance((Entity)target) >= HoleFiller.mc.player.getDistance((Entity)this.closestTarget)) {
                    continue;
                }
                this.closestTarget = target;
            }
        }
    }
    
    private boolean isInRange(final BlockPos blockPos) {
        final NonNullList<BlockPos> positions = (NonNullList<BlockPos>)NonNullList.create();
        positions.addAll((Collection)this.getSphere(getPlayerPos(), this.range.getValue().floatValue(), this.range.getValue().intValue(), false, true, 0).stream().filter((Predicate<? super Object>)this::IsHole).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
        return positions.contains((Object)blockPos);
    }
    
    private List<BlockPos> findCrystalBlocks() {
        final NonNullList<BlockPos> positions = (NonNullList<BlockPos>)NonNullList.create();
        if (this.smart.getValue() && this.closestTarget != null) {
            positions.addAll((Collection)this.getSphere(this.getClosestTargetPos(), this.smartRange.getValue(), this.range.getValue().intValue(), false, true, 0).stream().filter((Predicate<? super Object>)this::IsHole).filter((Predicate<? super Object>)this::isInRange).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
        }
        else if (!this.smart.getValue()) {
            positions.addAll((Collection)this.getSphere(getPlayerPos(), this.range.getValue().floatValue(), this.range.getValue().intValue(), false, true, 0).stream().filter((Predicate<? super Object>)this::IsHole).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
        }
        return (List<BlockPos>)positions;
    }
    
    public List<BlockPos> getSphere(final BlockPos loc, final float r, final int h, final boolean hollow, final boolean sphere, final int plus_y) {
        final List<BlockPos> circleblocks = new ArrayList<BlockPos>();
        final int cx = loc.getX();
        final int cy = loc.getY();
        final int cz = loc.getZ();
        for (int x = cx - (int)r; x <= cx + r; ++x) {
            for (int z = cz - (int)r; z <= cz + r; ++z) {
                for (int y = sphere ? (cy - (int)r) : cy; y < (sphere ? (cy + r) : ((float)(cy + h))); ++y) {
                    final double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? ((cy - y) * (cy - y)) : 0);
                    if (dist < r * r && (!hollow || dist >= (r - 1.0f) * (r - 1.0f))) {
                        final BlockPos l = new BlockPos(x, y + plus_y, z);
                        circleblocks.add(l);
                    }
                }
            }
        }
        return circleblocks;
    }
    
    private static void setYawAndPitch(final float yaw1, final float pitch1) {
        HoleFiller.yaw = yaw1;
        HoleFiller.pitch = pitch1;
        HoleFiller.isSpoofingAngles = true;
    }
    
    private static void resetRotation() {
        if (HoleFiller.isSpoofingAngles) {
            HoleFiller.yaw = HoleFiller.mc.player.rotationYaw;
            HoleFiller.pitch = HoleFiller.mc.player.rotationPitch;
            HoleFiller.isSpoofingAngles = false;
        }
    }
    
    public void onDisable() {
        this.closestTarget = null;
        this.render = null;
        resetRotation();
        if (this.announceUsage.getValue()) {
            Command.sendChatMessage("[HoleFiller] " + ChatFormatting.RED.toString() + "Disabled" + ChatFormatting.RESET.toString() + "!");
        }
    }
    
    static {
        HoleFiller.togglePitch = false;
    }
}
