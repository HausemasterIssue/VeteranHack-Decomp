//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.combat;

import me.zero.alpine.listener.*;
import com.veteran.hack.setting.*;
import java.util.function.*;
import java.util.stream.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.player.*;
import com.veteran.hack.command.*;
import net.minecraft.network.*;
import net.minecraft.client.entity.*;
import com.veteran.hack.event.events.*;
import com.veteran.hack.module.modules.gui.*;
import com.veteran.hack.module.*;
import java.awt.*;
import com.veteran.hack.util.*;
import com.veteran.hack.module.modules.render.*;
import net.minecraft.init.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.enchantment.*;
import net.minecraft.util.math.*;
import net.minecraft.potion.*;
import java.util.*;
import net.minecraft.item.*;
import net.minecraft.network.play.client.*;

@Module.Info(name = "Autocrystal", category = Module.Category.COMBAT, description = "Places Crystals to kill enemies.")
public class Autocrystal extends Module
{
    private Setting<Page> p;
    private Setting<PlaceBehavior> placeBehavior;
    private Setting<Boolean> autoSwitch;
    private Setting<ExplodeBehavior> explodeBehavior;
    private Setting<Boolean> place;
    private Setting<Boolean> explode;
    private Setting<Boolean> noToolExplode;
    private Setting<Boolean> antiWeakness;
    private Setting<Boolean> checkAbsorption;
    public Setting<Double> range;
    public Setting<Double> wallRange;
    private Setting<Double> delay;
    private Setting<Double> minDmg;
    public Setting<Double> maxSelfDmg;
    public Setting<Double> facePlaceHealth;
    private Setting<Boolean> facePlace;
    private Setting<Boolean> noGapSwitch;
    private Setting<Boolean> statusMessages;
    private Setting<Boolean> players;
    private Setting<Boolean> tracer;
    private Setting<Boolean> chroma;
    private Setting<Boolean> outline;
    private Setting<Boolean> customColours;
    private Setting<Integer> aBlock;
    private Setting<Integer> aTracer;
    private Setting<Integer> r;
    private Setting<Integer> g;
    private Setting<Integer> b;
    private BlockPos render;
    private Entity renderEnt;
    private long systemTime;
    private static boolean togglePitch;
    private boolean switchCoolDown;
    private boolean isAttacking;
    private int oldSlot;
    private BlockPos lastPos;
    int timer;
    private static boolean isSpoofingAngles;
    private static double yaw;
    private static double pitch;
    @EventHandler
    private Listener<PacketEvent.Send> cPacketListener;
    
    public Autocrystal() {
        this.p = (Setting<Page>)this.register((Setting)Settings.enumBuilder(Page.class).withName("Page").withValue(Page.ONE).build());
        this.placeBehavior = (Setting<PlaceBehavior>)Settings.enumBuilder(PlaceBehavior.class).withName("Place Behavior").withValue(PlaceBehavior.VETPLACE).withVisibility(v -> this.p.getValue().equals(Page.ONE)).build();
        this.autoSwitch = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("Auto Switch").withValue(true).withVisibility(v -> this.p.getValue().equals(Page.ONE)).build());
        this.explodeBehavior = (Setting<ExplodeBehavior>)this.register((Setting)Settings.enumBuilder(ExplodeBehavior.class).withName("Explode Behavior").withValue(ExplodeBehavior.DISTANCE).withVisibility(v -> this.p.getValue().equals(Page.ONE)).build());
        this.place = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("Place").withValue(false).withVisibility(v -> this.p.getValue().equals(Page.ONE)).build());
        this.explode = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("Explode").withValue(false).withVisibility(v -> this.p.getValue().equals(Page.ONE)).build());
        this.noToolExplode = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("No Tool Explode").withValue(false).withVisibility(v -> this.p.getValue().equals(Page.TWO)).build());
        this.antiWeakness = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("Anti Weakness").withValue(false).withVisibility(v -> this.p.getValue().equals(Page.ONE)).build());
        this.checkAbsorption = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("Check Absorption").withValue(true).withVisibility(v -> this.p.getValue().equals(Page.ONE)).build());
        this.range = (Setting<Double>)this.register((Setting)Settings.doubleBuilder("Range").withMinimum(1.0).withValue(5.0).withMaximum(10.0).withVisibility(v -> this.p.getValue().equals(Page.ONE)).build());
        this.wallRange = (Setting<Double>)this.register((Setting)Settings.doubleBuilder("Wall Range").withMinimum(1.0).withValue(3.0).withMaximum(8.0).withVisibility(v -> this.p.getValue().equals(Page.ONE)).build());
        this.delay = (Setting<Double>)this.register((Setting)Settings.doubleBuilder("Hit Delay").withMinimum(0.0).withValue(5.0).withMaximum(10.0).withVisibility(v -> this.p.getValue().equals(Page.ONE)).build());
        this.minDmg = (Setting<Double>)this.register((Setting)Settings.doubleBuilder("Minimum Damage").withMinimum(0.0).withValue(0.0).withMaximum(32.0).withVisibility(v -> this.p.getValue().equals(Page.ONE)).build());
        this.maxSelfDmg = (Setting<Double>)this.register((Setting)Settings.doubleBuilder("Max Self Damage").withMinimum(1.0).withValue(10.0).withMaximum(36.0).withVisibility(v -> this.p.getValue().equals(Page.ONE)).build());
        this.facePlaceHealth = (Setting<Double>)this.register((Setting)Settings.doubleBuilder("Face Place Health").withMinimum(1.0).withValue(10.0).withMaximum(36.0).withVisibility(v -> this.p.getValue().equals(Page.ONE)).build());
        this.facePlace = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("Face Place").withValue(true).withVisibility(v -> this.p.getValue().equals(Page.ONE)).build());
        this.noGapSwitch = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("No Gap Switch").withValue(true).withVisibility(v -> this.p.getValue().equals(Page.ONE) && this.autoSwitch.getValue()).build());
        this.statusMessages = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("Status Messages").withValue(false).withVisibility(v -> this.p.getValue().equals(Page.ONE)).build());
        this.players = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("Players").withValue(true).withVisibility(v -> this.p.getValue().equals(Page.TWO)).build());
        this.tracer = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("Tracer").withValue(false).withVisibility(v -> this.p.getValue().equals(Page.TWO)).build());
        this.chroma = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("Chroma").withValue(true).withVisibility(v -> this.p.getValue().equals(Page.TWO)).build());
        this.outline = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("Render Block Outline").withValue(true).withVisibility(v -> this.p.getValue().equals(Page.TWO)).build());
        this.customColours = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder("Custom Colours").withValue(false).withVisibility(v -> this.p.getValue().equals(Page.TWO) && !this.chroma.getValue()).build());
        this.aBlock = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Block Transparency").withMinimum(0).withValue(44).withMaximum(205).withVisibility(v -> this.p.getValue().equals(Page.TWO) && this.customColours.getValue()).build());
        this.aTracer = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Tracer Transparency").withMinimum(0).withValue(200).withMaximum(255).withVisibility(v -> this.p.getValue().equals(Page.TWO) && this.customColours.getValue()).build());
        this.r = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Red").withMinimum(0).withValue(155).withMaximum(255).withVisibility(v -> this.p.getValue().equals(Page.TWO) && this.customColours.getValue()).build());
        this.g = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Green").withMinimum(0).withValue(144).withMaximum(255).withVisibility(v -> this.p.getValue().equals(Page.TWO) && this.customColours.getValue()).build());
        this.b = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Blue").withMinimum(0).withValue(255).withMaximum(255).withVisibility(v -> this.p.getValue().equals(Page.TWO) && this.customColours.getValue()).build());
        this.systemTime = 0L;
        this.switchCoolDown = false;
        this.isAttacking = false;
        this.oldSlot = -1;
        this.lastPos = new BlockPos(0, 0, 0);
        this.timer = 20;
        this.cPacketListener = (Listener<PacketEvent.Send>)new Listener(event -> {
            final Packet packet = event.getPacket();
            if (packet instanceof CPacketPlayer && Autocrystal.isSpoofingAngles) {
                ((CPacketPlayer)packet).yaw = (float)Autocrystal.yaw;
                ((CPacketPlayer)packet).pitch = (float)Autocrystal.pitch;
            }
        }, new Predicate[0]);
    }
    
    public void onUpdate() {
        --this.timer;
        final List<Entity> entities = new ArrayList<Entity>();
        if (this.players.getValue()) {
            entities.addAll((Collection<? extends Entity>)Autocrystal.mc.world.playerEntities.stream().filter(entityPlayer -> !Friends.isFriend(entityPlayer.getName())).collect(Collectors.toList()));
        }
        if (entities.isEmpty()) {
            return;
        }
        final EntityEnderCrystal crystal = (EntityEnderCrystal)Autocrystal.mc.world.loadedEntityList.stream().filter(entity -> entity instanceof EntityEnderCrystal).map(entity -> entity).min(Comparator.comparing(c -> Autocrystal.mc.player.getDistance(c))).orElse(null);
        if (this.explode.getValue() && crystal != null && Autocrystal.mc.player.getDistance((Entity)crystal) <= this.range.getValue() && this.passSwordCheck()) {
            if (System.nanoTime() / 1000000.0f - this.systemTime >= this.delay.getValue() * 25.0) {
                this.explode(crystal);
            }
        }
        else {
            resetRotation();
            if (this.oldSlot != -1) {
                Wrapper.getPlayer().inventory.currentItem = this.oldSlot;
                this.oldSlot = -1;
            }
            this.isAttacking = false;
        }
        int crystalSlot = (Autocrystal.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL) ? Autocrystal.mc.player.inventory.currentItem : -1;
        if (crystalSlot == -1) {
            for (int l = 0; l < 9; ++l) {
                if (Autocrystal.mc.player.inventory.getStackInSlot(l).getItem() == Items.END_CRYSTAL) {
                    crystalSlot = l;
                    break;
                }
            }
        }
        boolean offhand = false;
        if (Autocrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
            offhand = true;
        }
        else if (crystalSlot == -1) {
            return;
        }
        final List<BlockPos> blocks = this.findCrystalBlocks();
        BlockPos q = null;
        double damage = 0.5;
        EntityPlayer pt = (EntityPlayer)Autocrystal.mc.player;
        if (this.place.getValue() && this.placeBehavior.getValue() == PlaceBehavior.VETPLACE) {
            for (final Entity entity2 : entities) {
                if (entity2 != Autocrystal.mc.player) {
                    if (((EntityLivingBase)entity2).getHealth() <= 0.0f) {
                        continue;
                    }
                    for (final BlockPos blockPos : blocks) {
                        final double b = entity2.getDistanceSq(blockPos);
                        if (b > 75.0) {
                            continue;
                        }
                        final double d = calculateDamage(blockPos.x + 0.5, blockPos.y + 1, blockPos.z + 0.5, entity2);
                        final double self = calculateDamage(blockPos.x + 0.5, blockPos.y + 1, blockPos.z + 0.5, (Entity)Autocrystal.mc.player);
                        if (this.timer <= 0 && entity2 instanceof EntityPlayer && this.statusMessages.getValue()) {
                            Command.sendChatMessage("Autocrystal - placing against target &c" + entity2.getName() + ".");
                            this.timer = 600;
                        }
                        if (self >= Autocrystal.mc.player.getHealth() + Autocrystal.mc.player.getAbsorptionAmount() - 1.0f || self > d) {
                            continue;
                        }
                        if (self >= this.maxSelfDmg.getValue()) {
                            continue;
                        }
                        if (d <= this.minDmg.getValue() && (blockPos.y != entity2.getPosition().y || !this.facePlace.getValue() || ((EntityLivingBase)entity2).getHealth() + ((EntityLivingBase)entity2).getAbsorptionAmount() > this.facePlaceHealth.getValue() || b > 1.2)) {
                            continue;
                        }
                        q = blockPos;
                        damage = d;
                        this.renderEnt = entity2;
                        if (!(entity2 instanceof EntityPlayer)) {
                            continue;
                        }
                        pt = (EntityPlayer)entity2;
                    }
                }
            }
        }
        if (damage == 0.5) {
            this.render = null;
            this.renderEnt = null;
            resetRotation();
            return;
        }
        final float lastPosDmg = calculateDamage(this.lastPos.x + 0.5, this.lastPos.y + 1, this.lastPos.z + 0.5, (Entity)pt);
        final float lastPosSelf = calculateDamage(this.lastPos.x + 0.5, this.lastPos.y + 1, this.lastPos.z + 0.5, (Entity)Autocrystal.mc.player);
        if (lastPosDmg >= this.minDmg.getValue() && lastPosDmg >= damage && lastPosSelf <= this.maxSelfDmg.getValue() && this.isEmpty(this.lastPos.up()) && this.isEmpty(this.lastPos.up(2))) {
            q = this.lastPos;
            damage = lastPosDmg;
        }
        if (this.place.getValue()) {
            this.render = q;
            this.lastPos = q;
            if (!offhand && Autocrystal.mc.player.inventory.currentItem != crystalSlot) {
                if ((this.autoSwitch.getValue() && !this.noGapSwitch.getValue()) || (this.autoSwitch.getValue() && this.noGapSwitch.getValue() && (Autocrystal.mc.player.getHeldItemMainhand().getItem() != Items.GOLDEN_APPLE || !Autocrystal.mc.gameSettings.keyBindUseItem.isKeyDown()))) {
                    Autocrystal.mc.player.inventory.currentItem = crystalSlot;
                    resetRotation();
                    this.switchCoolDown = true;
                }
                return;
            }
            this.lookAtPacket(q.x + 0.5, q.y - 0.5, q.z + 0.5, (EntityPlayer)Autocrystal.mc.player);
            final RayTraceResult result = Autocrystal.mc.world.rayTraceBlocks(new Vec3d(Autocrystal.mc.player.posX, Autocrystal.mc.player.posY + Autocrystal.mc.player.getEyeHeight(), Autocrystal.mc.player.posZ), new Vec3d(q.x + 0.5, q.y - 0.5, q.z + 0.5));
            EnumFacing f;
            if (result == null || result.sideHit == null) {
                f = EnumFacing.UP;
            }
            else {
                f = result.sideHit;
            }
            Autocrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(q, f, offhand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
        }
        if (Autocrystal.isSpoofingAngles) {
            if (Autocrystal.togglePitch) {
                final EntityPlayerSP player = Autocrystal.mc.player;
                player.rotationPitch += (float)4.0E-4;
                Autocrystal.togglePitch = false;
            }
            else {
                final EntityPlayerSP player2 = Autocrystal.mc.player;
                player2.rotationPitch -= (float)4.0E-4;
                Autocrystal.togglePitch = true;
            }
        }
    }
    
    public void onWorldRender(final RenderEvent event) {
        if (this.render != null) {
            VetHackTessellator.prepare(7);
            int colour = 1157627903;
            if (this.customColours.getValue()) {
                colour = ColourConverter.rgbToInt(this.r.getValue(), this.g.getValue(), this.b.getValue(), this.aBlock.getValue());
            }
            if (this.chroma.getValue()) {
                final ActiveModules activeMods = (ActiveModules)ModuleManager.getModuleByName("ActiveModules");
                final float[] hue = { System.currentTimeMillis() % (360 * activeMods.getRainbowSpeed()) / (360.0f * activeMods.getRainbowSpeed()) };
                final int rgb = Color.HSBtoRGB(hue[0], ColourConverter.toF(activeMods.saturationR.getValue()), ColourConverter.toF(activeMods.brightnessR.getValue()));
                final int red = rgb >> 16 & 0xFF;
                final int green = rgb >> 8 & 0xFF;
                final int blue = rgb & 0xFF;
                colour = ColourConverter.rgbToInt(red, green, blue, this.aBlock.getValue());
            }
            VetHackTessellator.drawBox(this.render, colour, 63);
            VetHackTessellator.release();
            if (this.outline.getValue()) {
                VetHackTessellator.prepare(7);
                VetHackTessellator.drawBoundingBoxBlockPos(this.render, 4.0f, colour >> 16 & 0xFF, colour >> 8 & 0xFF, colour & 0xFF, this.aBlock.getValue() + 50);
                VetHackTessellator.release();
            }
            if (this.renderEnt != null && this.tracer.getValue()) {
                final Vec3d p = EntityUtil.getInterpolatedRenderPos(this.renderEnt, Autocrystal.mc.getRenderPartialTicks());
                float rL = 1.0f;
                float gL = 1.0f;
                float bL = 1.0f;
                float aL = 1.0f;
                if (this.customColours.getValue()) {
                    rL = ColourConverter.toF(this.r.getValue());
                    gL = ColourConverter.toF(this.g.getValue());
                    bL = ColourConverter.toF(this.b.getValue());
                    aL = ColourConverter.toF(this.aTracer.getValue());
                }
                Tracers.drawLineFromPosToPos(this.render.x - Autocrystal.mc.getRenderManager().renderPosX + 0.5, this.render.y - Autocrystal.mc.getRenderManager().renderPosY + 1.0, this.render.z - Autocrystal.mc.getRenderManager().renderPosZ + 0.5, p.x, p.y, p.z, this.renderEnt.getEyeHeight(), rL, gL, bL, aL);
            }
        }
    }
    
    private void lookAtPacket(final double px, final double py, final double pz, final EntityPlayer me) {
        final double[] v = EntityUtil.calculateLookAt(px, py, pz, me);
        setYawAndPitch((float)v[0], (float)v[1] + 1.0f);
    }
    
    boolean canPlaceCrystal(final BlockPos blockPos) {
        final BlockPos boost = blockPos.add(0, 1, 0);
        final BlockPos boost2 = blockPos.add(0, 2, 0);
        return (Autocrystal.mc.world.getBlockState(blockPos).getBlock() == Blocks.BEDROCK || Autocrystal.mc.world.getBlockState(blockPos).getBlock() == Blocks.OBSIDIAN) && Autocrystal.mc.world.getBlockState(boost).getBlock() == Blocks.AIR && Autocrystal.mc.world.getBlockState(boost2).getBlock() == Blocks.AIR && Autocrystal.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(boost)).isEmpty() && Autocrystal.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(boost2)).isEmpty();
    }
    
    public static BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(Autocrystal.mc.player.posX), Math.floor(Autocrystal.mc.player.posY), Math.floor(Autocrystal.mc.player.posZ));
    }
    
    private List<BlockPos> findCrystalBlocks() {
        final NonNullList<BlockPos> positions = (NonNullList<BlockPos>)NonNullList.create();
        positions.addAll((Collection)this.getSphere(getPlayerPos(), this.range.getValue().floatValue(), this.range.getValue().intValue(), false, true, 0).stream().filter((Predicate<? super Object>)this::canPlaceCrystal).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
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
    
    public static float calculateDamage(final double posX, final double posY, final double posZ, final Entity entity) {
        final float doubleExplosionSize = 12.0f;
        final double distancedSize = entity.getDistance(posX, posY, posZ) / doubleExplosionSize;
        final Vec3d vec3d = new Vec3d(posX, posY, posZ);
        final double blockDensity = entity.world.getBlockDensity(vec3d, entity.getEntityBoundingBox());
        final double v = (1.0 - distancedSize) * blockDensity;
        final float damage = (float)(int)((v * v + v) / 2.0 * 7.0 * doubleExplosionSize + 1.0);
        double finalD = 1.0;
        if (entity instanceof EntityLivingBase) {
            finalD = getBlastReduction((EntityLivingBase)entity, getDamageMultiplied(damage), new Explosion((World)Autocrystal.mc.world, (Entity)null, posX, posY, posZ, 6.0f, false, true));
        }
        return (float)finalD;
    }
    
    public static float getBlastReduction(final EntityLivingBase entity, float damage, final Explosion explosion) {
        if (entity instanceof EntityPlayer) {
            final EntityPlayer ep = (EntityPlayer)entity;
            final DamageSource ds = DamageSource.causeExplosionDamage(explosion);
            damage = CombatRules.getDamageAfterAbsorb(damage, (float)ep.getTotalArmorValue(), (float)ep.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
            final int k = EnchantmentHelper.getEnchantmentModifierDamage(ep.getArmorInventoryList(), ds);
            final float f = MathHelper.clamp((float)k, 0.0f, 20.0f);
            damage *= 1.0f - f / 25.0f;
            if (entity.isPotionActive((Potion)Objects.requireNonNull(Potion.getPotionById(11)))) {
                damage -= damage / 4.0f;
            }
            damage = Math.max(damage, 0.0f);
            return damage;
        }
        damage = CombatRules.getDamageAfterAbsorb(damage, (float)entity.getTotalArmorValue(), (float)entity.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
        return damage;
    }
    
    private static float getDamageMultiplied(final float damage) {
        final int diff = Autocrystal.mc.world.getDifficulty().getId();
        return damage * ((diff == 0) ? 0.0f : ((diff == 2) ? 1.0f : ((diff == 1) ? 0.5f : 1.5f)));
    }
    
    public static float calculateDamage(final EntityEnderCrystal crystal, final Entity entity) {
        return calculateDamage(crystal.posX, crystal.posY, crystal.posZ, entity);
    }
    
    private static void setYawAndPitch(final float yaw1, final float pitch1) {
        Autocrystal.yaw = yaw1;
        Autocrystal.pitch = pitch1;
        Autocrystal.isSpoofingAngles = true;
    }
    
    private static void resetRotation() {
        if (Autocrystal.isSpoofingAngles) {
            Autocrystal.yaw = Autocrystal.mc.player.rotationYaw;
            Autocrystal.pitch = Autocrystal.mc.player.rotationPitch;
            Autocrystal.isSpoofingAngles = false;
        }
    }
    
    public void onEnable() {
        if (this.statusMessages.getValue()) {
            Command.sendChatMessage(this.getChatName() + "&aEnabled&r");
        }
    }
    
    public void onDisable() {
        if (this.statusMessages.getValue()) {
            Command.sendChatMessage(this.getChatName() + "&cDisabled&r");
        }
        this.render = null;
        this.renderEnt = null;
        resetRotation();
    }
    
    public void explode(final EntityEnderCrystal crystal) {
        this.lookAtPacket(crystal.posX, crystal.posY, crystal.posZ, (EntityPlayer)Autocrystal.mc.player);
        final RayTraceResult r = Autocrystal.mc.player.rayTrace((double)this.range.getValue(), Autocrystal.mc.getRenderPartialTicks());
        if (r.typeOfHit.equals((Object)RayTraceResult.Type.BLOCK) && Autocrystal.mc.player.getDistance((Entity)crystal) >= this.wallRange.getValue()) {
            return;
        }
        Autocrystal.mc.playerController.attackEntity((EntityPlayer)Autocrystal.mc.player, (Entity)crystal);
        Autocrystal.mc.player.swingArm(EnumHand.MAIN_HAND);
        this.systemTime = System.nanoTime() / 1000000L;
    }
    
    private float explodeRate() {
        return this.delay.getValue().floatValue();
    }
    
    private boolean passSwordCheck() {
        return !(Autocrystal.mc.player.getHeldItemMainhand().getItem() instanceof ItemTool) || !this.noToolExplode.getValue();
    }
    
    private boolean isEmpty(final BlockPos pos) {
        final List<Entity> playersInAABB = (List<Entity>)Autocrystal.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(pos)).stream().filter(e -> e instanceof EntityPlayer).collect(Collectors.toList());
        return playersInAABB.isEmpty();
    }
    
    public String getHudInfo() {
        return String.valueOf(this.explodeRate());
    }
    
    static {
        Autocrystal.togglePitch = false;
    }
    
    private enum PlaceBehavior
    {
        VETPLACE;
    }
    
    private enum ExplodeBehavior
    {
        DISTANCE;
    }
    
    private enum Page
    {
        ONE, 
        TWO;
    }
}
