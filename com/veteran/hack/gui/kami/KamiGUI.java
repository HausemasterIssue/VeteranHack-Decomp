//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.gui.kami;

import com.veteran.hack.gui.rgui.*;
import com.veteran.hack.gui.rgui.render.theme.*;
import com.veteran.hack.gui.kami.theme.kami.*;
import com.veteran.hack.module.*;
import com.veteran.hack.gui.rgui.layout.*;
import com.veteran.hack.gui.rgui.poof.*;
import com.veteran.hack.gui.rgui.component.container.use.*;
import com.veteran.hack.gui.rgui.component.container.*;
import com.veteran.hack.gui.kami.component.*;
import com.veteran.hack.gui.rgui.component.use.*;
import com.veteran.hack.gui.rgui.render.font.*;
import com.veteran.hack.module.modules.gui.*;
import com.veteran.hack.util.*;
import java.math.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.entity.player.*;
import net.minecraft.init.*;
import net.minecraft.entity.*;
import com.veteran.hack.gui.rgui.component.listen.*;
import net.minecraft.client.*;
import java.util.function.*;
import java.util.stream.*;
import net.minecraft.util.text.*;
import com.veteran.hack.gui.rgui.component.*;
import java.text.*;
import javax.annotation.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.projectile.*;
import java.util.*;
import com.veteran.hack.gui.rgui.util.*;

public class KamiGUI extends GUI
{
    public static final RootFontRenderer fontRenderer;
    public Theme theme;
    public static ColourHolder primaryColour;
    private static final int DOCK_OFFSET = 0;
    
    public KamiGUI() {
        super(new KamiTheme());
        this.theme = this.getTheme();
    }
    
    @Override
    public void drawGUI() {
        super.drawGUI();
    }
    
    @Override
    public void initializeGUI() {
        final HashMap<Module.Category, Pair<Scrollpane, SettingsPanel>> categoryScrollpaneHashMap = new HashMap<Module.Category, Pair<Scrollpane, SettingsPanel>>();
        for (final Module module : ModuleManager.getModules()) {
            if (module.getCategory().isHidden()) {
                continue;
            }
            final Module.Category moduleCategory = module.getCategory();
            if (!categoryScrollpaneHashMap.containsKey(moduleCategory)) {
                final Stretcherlayout stretcherlayout = new Stretcherlayout(1);
                stretcherlayout.setComponentOffsetWidth(0);
                final Scrollpane scrollpane = new Scrollpane(this.getTheme(), stretcherlayout, 300, 260);
                scrollpane.setMaximumHeight(180);
                categoryScrollpaneHashMap.put(moduleCategory, new Pair<Scrollpane, SettingsPanel>(scrollpane, new SettingsPanel(this.getTheme(), (Module)null)));
            }
            final Pair<Scrollpane, SettingsPanel> pair = categoryScrollpaneHashMap.get(moduleCategory);
            final Scrollpane scrollpane = pair.getKey();
            final CheckButton checkButton = new CheckButton(module.getName());
            checkButton.setToggled(module.isEnabled());
            final CheckButton checkButton2;
            final Module module2;
            checkButton.addTickListener(() -> {
                checkButton2.setToggled(module2.isEnabled());
                checkButton2.setName(module2.getName());
                return;
            });
            checkButton.addMouseListener(new MouseListener() {
                @Override
                public void onMouseDown(final MouseButtonEvent event) {
                    if (event.getButton() == 1) {
                        pair.getValue().setModule(module);
                        pair.getValue().setX(event.getX() + checkButton.getX());
                        pair.getValue().setY(event.getY() + checkButton.getY());
                    }
                }
                
                @Override
                public void onMouseRelease(final MouseButtonEvent event) {
                }
                
                @Override
                public void onMouseDrag(final MouseButtonEvent event) {
                }
                
                @Override
                public void onMouseMove(final MouseMoveEvent event) {
                }
                
                @Override
                public void onScroll(final MouseScrollEvent event) {
                }
            });
            checkButton.addPoof(new CheckButton.CheckButtonPoof<CheckButton, CheckButton.CheckButtonPoof.CheckButtonPoofInfo>() {
                @Override
                public void execute(final CheckButton component, final CheckButtonPoofInfo info) {
                    if (info.getAction().equals(CheckButtonPoofInfo.CheckButtonPoofInfoAction.TOGGLE)) {
                        module.setEnabled(checkButton.isToggled());
                    }
                }
            });
            scrollpane.addChild(checkButton);
        }
        int x = 10;
        int nexty;
        int y = nexty = 10;
        for (final Map.Entry<Module.Category, Pair<Scrollpane, SettingsPanel>> entry : categoryScrollpaneHashMap.entrySet()) {
            final Stretcherlayout stretcherlayout2 = new Stretcherlayout(1);
            stretcherlayout2.COMPONENT_OFFSET_Y = 1;
            final Frame frame = new Frame(this.getTheme(), stretcherlayout2, entry.getKey().getName());
            final Scrollpane scrollpane2 = entry.getValue().getKey();
            frame.addChild(scrollpane2);
            frame.addChild((Component)entry.getValue().getValue());
            scrollpane2.setOriginOffsetY(0);
            scrollpane2.setOriginOffsetX(0);
            frame.setCloseable(false);
            frame.setX(x);
            frame.setY(y);
            this.addChild(frame);
            nexty = Math.max(y + frame.getHeight() + 10, nexty);
            x += frame.getWidth() + 10;
            if (x > Wrapper.getMinecraft().displayWidth / 1.2f) {
                y = (nexty = nexty);
            }
        }
        this.addMouseListener(new MouseListener() {
            private boolean isNotBetween(final int min, final int val, final int max) {
                return val > max || val < min;
            }
            
            @Override
            public void onMouseDown(final MouseButtonEvent event) {
                final List<SettingsPanel> panels = ContainerHelper.getAllChildren((Class<? extends SettingsPanel>)SettingsPanel.class, (Container)KamiGUI.this);
                for (final SettingsPanel settingsPanel : panels) {
                    if (!settingsPanel.isVisible()) {
                        continue;
                    }
                    final int[] real = GUI.calculateRealPosition((Component)settingsPanel);
                    final int pX = event.getX() - real[0];
                    final int pY = event.getY() - real[1];
                    if (!this.isNotBetween(0, pX, settingsPanel.getWidth()) && !this.isNotBetween(0, pY, settingsPanel.getHeight())) {
                        continue;
                    }
                    settingsPanel.setVisible(false);
                }
            }
            
            @Override
            public void onMouseRelease(final MouseButtonEvent event) {
            }
            
            @Override
            public void onMouseDrag(final MouseButtonEvent event) {
            }
            
            @Override
            public void onMouseMove(final MouseMoveEvent event) {
            }
            
            @Override
            public void onScroll(final MouseScrollEvent event) {
            }
        });
        final ArrayList<Frame> frames = new ArrayList<Frame>();
        Frame frame2 = new Frame(this.getTheme(), new Stretcherlayout(1), "Active modules");
        frame2.setCloseable(false);
        frame2.addChild((Component)new ActiveModules());
        frame2.setPinnable(true);
        frames.add(frame2);
        frame2 = new Frame(this.getTheme(), new Stretcherlayout(1), "Player View");
        frame2.setCloseable(false);
        frame2.setMinimizeable(true);
        frame2.setPinnable(true);
        frame2.setWidth(100);
        frame2.setHeight(100);
        final Label playerv = new Label("");
        playerv.setShadow(false);
        final AbstractComponent abstractComponent;
        playerv.addTickListener(() -> {
            abstractComponent.setWidth(21);
            abstractComponent.setHeight(60);
            return;
        });
        frame2.addChild(playerv);
        playerv.setFontRenderer(KamiGUI.fontRenderer);
        frames.add(frame2);
        frame2 = new Frame(this.getTheme(), new Stretcherlayout(1), "Info");
        frame2.setCloseable(false);
        frame2.setPinnable(true);
        final Label information = new Label("");
        information.setShadow(true);
        final InfoOverlay info;
        final Label label;
        information.addTickListener(() -> {
            info = (InfoOverlay)ModuleManager.getModuleByName("InfoOverlay");
            label.setText("");
            info.infoContents().forEach(label::addLine);
            return;
        });
        frame2.addChild(information);
        information.setFontRenderer(KamiGUI.fontRenderer);
        frames.add(frame2);
        frame2 = new Frame(this.getTheme(), new Stretcherlayout(1), "Inventory Viewer");
        frame2.setCloseable(false);
        frame2.setMinimizeable(false);
        frame2.setPinnable(true);
        frame2.setPinned(false);
        final Label inventory = new Label("");
        inventory.setShadow(false);
        final AbstractComponent abstractComponent2;
        inventory.addTickListener(() -> {
            abstractComponent2.setWidth(151);
            abstractComponent2.setHeight(40);
            abstractComponent2.setOpacity(0.1f);
            return;
        });
        frame2.addChild(inventory);
        inventory.setFontRenderer(KamiGUI.fontRenderer);
        frames.add(frame2);
        frame2 = new Frame(this.getTheme(), new Stretcherlayout(1), "Friends");
        frame2.setCloseable(false);
        frame2.setPinnable(true);
        frame2.setMinimizeable(true);
        final Label friends = new Label("");
        friends.setShadow(true);
        final Frame finalFrame = frame2;
        final Label label2;
        final Frame frame4;
        friends.addTickListener(() -> {
            label2.setText("");
            if (!frame4.isMinimized()) {
                Friends.friends.getValue().forEach(friend -> label2.addLine(friend.getUsername()));
            }
            else {
                label2.setWidth(50);
            }
            return;
        });
        frame2.addChild(friends);
        friends.setFontRenderer(KamiGUI.fontRenderer);
        frames.add(frame2);
        frame2 = new Frame(this.getTheme(), new Stretcherlayout(1), "Text Radar");
        final Label list = new Label("");
        final DecimalFormat dfHealth = new DecimalFormat("#.#");
        dfHealth.setRoundingMode(RoundingMode.HALF_UP);
        final StringBuilder healthSB = new StringBuilder();
        final Label label3;
        Minecraft mc;
        List<EntityPlayer> entityList;
        Map<String, Integer> players;
        final Iterator<EntityPlayer> iterator3;
        Entity e;
        String s;
        String posString;
        EntityPlayer ePlayer;
        String weaknessFactor;
        String strengthFactor;
        String extraPaddingForFactors;
        float hpRaw;
        final NumberFormat numberFormat;
        String hp;
        final StringBuilder sb;
        Map<String, Integer> players2;
        final Iterator<Map.Entry<String, Integer>> iterator4;
        Map.Entry<String, Integer> player;
        list.addTickListener(() -> {
            if (!label3.isVisible()) {
                return;
            }
            else {
                label3.setText("");
                mc = Wrapper.getMinecraft();
                if (mc.player == null) {
                    return;
                }
                else {
                    entityList = (List<EntityPlayer>)mc.world.playerEntities;
                    players = new HashMap<String, Integer>();
                    entityList.iterator();
                    while (iterator3.hasNext()) {
                        e = (Entity)iterator3.next();
                        if (e.getName().equals(mc.player.getName())) {
                            continue;
                        }
                        else {
                            if (e.posY > mc.player.posY) {
                                s = ChatFormatting.DARK_GREEN + "+";
                            }
                            else if (e.posY == mc.player.posY) {
                                s = " ";
                            }
                            else {
                                s = ChatFormatting.DARK_RED + "-";
                            }
                            posString = s;
                            ePlayer = (EntityPlayer)e;
                            if (ePlayer.isPotionActive(MobEffects.WEAKNESS)) {
                                weaknessFactor = "W";
                            }
                            else {
                                weaknessFactor = "";
                            }
                            if (ePlayer.isPotionActive(MobEffects.STRENGTH)) {
                                strengthFactor = "S";
                            }
                            else {
                                strengthFactor = "";
                            }
                            if (weaknessFactor.equals("") && strengthFactor.equals("")) {
                                extraPaddingForFactors = "";
                            }
                            else {
                                extraPaddingForFactors = " ";
                            }
                            hpRaw = ((EntityLivingBase)e).getHealth() + ((EntityLivingBase)e).getAbsorptionAmount();
                            hp = numberFormat.format(hpRaw);
                            sb.append('§');
                            if (hpRaw >= 20.0f) {
                                sb.append("a");
                            }
                            else if (hpRaw >= 10.0f) {
                                sb.append("e");
                            }
                            else if (hpRaw >= 5.0f) {
                                sb.append("6");
                            }
                            else {
                                sb.append("c");
                            }
                            sb.append(hp);
                            players.put(ChatFormatting.GRAY + posString + " " + sb.toString() + " " + ChatFormatting.DARK_GRAY + weaknessFactor + ChatFormatting.DARK_PURPLE + strengthFactor + ChatFormatting.GRAY + extraPaddingForFactors + e.getName(), (int)mc.player.getDistance(e));
                            sb.setLength(0);
                        }
                    }
                    if (players.isEmpty()) {
                        label3.setText("");
                        return;
                    }
                    else {
                        players2 = sortByValue(players);
                        players2.entrySet().iterator();
                        while (iterator4.hasNext()) {
                            player = iterator4.next();
                            label3.addLine("§7" + player.getKey() + " " + '§' + "8" + player.getValue());
                        }
                        return;
                    }
                }
            }
        });
        frame2.setCloseable(false);
        frame2.setPinnable(true);
        frame2.setMinimumWidth(75);
        list.setShadow(true);
        frame2.addChild(list);
        list.setFontRenderer(KamiGUI.fontRenderer);
        frames.add(frame2);
        frame2 = new Frame(this.getTheme(), new Stretcherlayout(1), "Entities");
        final Label entityLabel = new Label("");
        frame2.setCloseable(false);
        final Frame finalFrame2 = frame2;
        entityLabel.addTickListener(new TickListener() {
            Minecraft mc = Wrapper.getMinecraft();
            
            @Override
            public void onTick() {
                if (!finalFrame2.isMinimized()) {
                    if (this.mc.player == null || !entityLabel.isVisible()) {
                        return;
                    }
                    final List<Entity> entityList = new ArrayList<Entity>(this.mc.world.loadedEntityList);
                    if (entityList.size() <= 1) {
                        entityLabel.setText("");
                        return;
                    }
                    final Map<String, Integer> entityCounts = entityList.stream().filter(Objects::nonNull).filter(e -> !(e instanceof EntityPlayer)).collect(Collectors.groupingBy(x$0 -> getEntityName(x$0), (Collector<? super Object, ?, Integer>)Collectors.reducing((D)0, ent -> {
                        if (ent instanceof EntityItem) {
                            return Integer.valueOf(ent.getItem().getCount());
                        }
                        else {
                            return Integer.valueOf(1);
                        }
                    }, Integer::sum)));
                    entityLabel.setText("");
                    finalFrame2.setWidth(50);
                    entityCounts.entrySet().stream().sorted((Comparator<? super Object>)Map.Entry.comparingByValue()).map(entry -> TextFormatting.GRAY + entry.getKey() + " " + TextFormatting.DARK_GRAY + "x" + entry.getValue()).forEach((Consumer<? super Object>)entityLabel::addLine);
                }
                else {
                    finalFrame2.setWidth(50);
                }
            }
        });
        frame2.addChild(entityLabel);
        frame2.setPinnable(true);
        entityLabel.setShadow(true);
        entityLabel.setFontRenderer(KamiGUI.fontRenderer);
        frames.add(frame2);
        frame2 = new Frame(this.getTheme(), new Stretcherlayout(1), "Coordinates");
        frame2.setCloseable(false);
        frame2.setPinnable(true);
        final Label coordsLabel = new Label("");
        coordsLabel.addTickListener(new TickListener() {
            Minecraft mc = Minecraft.getMinecraft();
            
            @Override
            public void onTick() {
                final boolean inHell = this.mc.world.getBiome(this.mc.player.getPosition()).getBiomeName().equals("Hell");
                final int posX = (int)this.mc.player.posX;
                final int posY = (int)this.mc.player.posY;
                final int posZ = (int)this.mc.player.posZ;
                final float f = inHell ? 8.0f : 0.125f;
                final int hposX = (int)(this.mc.player.posX * f);
                final int hposZ = (int)(this.mc.player.posZ * f);
                final char direction = Character.toUpperCase(this.mc.player.getHorizontalFacing().toString().charAt(0));
                final String colouredSeparator = "§7 \u23d0§r";
                coordsLabel.setText(direction + colouredSeparator + String.format(" %sf%,d%s7, %sf%,d%s7, %sf%,d %s7(%sf%,d%s7, %sf%,d%s7, %sf%,d%s7)", '§', posX, '§', '§', posY, '§', '§', posZ, '§', '§', hposX, '§', '§', posY, '§', '§', hposZ, '§'));
            }
        });
        frame2.addChild(coordsLabel);
        coordsLabel.setFontRenderer(KamiGUI.fontRenderer);
        coordsLabel.setShadow(true);
        frame2.setHeight(20);
        frames.add(frame2);
        frame2 = new Frame(this.getTheme(), new Stretcherlayout(1), "Radar");
        frame2.setCloseable(false);
        frame2.setMinimizeable(true);
        frame2.setPinnable(true);
        frame2.setWidth(100);
        frame2.setHeight(100);
        frames.add(frame2);
        for (final Frame frame3 : frames) {
            frame3.setX(x);
            frame3.setY(y);
            nexty = Math.max(y + frame3.getHeight() + 10, nexty);
            x += frame3.getWidth() + 10;
            if (x * DisplayGuiScreen.getScale() > Wrapper.getMinecraft().displayWidth / 1.2f) {
                y = (nexty = nexty);
                x = 10;
            }
            this.addChild(frame3);
        }
    }
    
    private static String getEntityName(@Nonnull final Entity entity) {
        if (entity instanceof EntityItem) {
            return TextFormatting.DARK_AQUA + ((EntityItem)entity).getItem().getItem().getItemStackDisplayName(((EntityItem)entity).getItem());
        }
        if (entity instanceof EntityWitherSkull) {
            return TextFormatting.DARK_GRAY + "Wither skull";
        }
        if (entity instanceof EntityEnderCrystal) {
            return TextFormatting.LIGHT_PURPLE + "End crystal";
        }
        if (entity instanceof EntityEnderPearl) {
            return "Thrown ender pearl";
        }
        if (entity instanceof EntityMinecart) {
            return "Minecart";
        }
        if (entity instanceof EntityItemFrame) {
            return "Item frame";
        }
        if (entity instanceof EntityEgg) {
            return "Thrown egg";
        }
        if (entity instanceof EntitySnowball) {
            return "Thrown snowball";
        }
        return entity.getName();
    }
    
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(final Map<K, V> map) {
        final List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, Comparator.comparing(o -> o.getValue()));
        final Map<K, V> result = new LinkedHashMap<K, V>();
        for (final Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
    
    @Override
    public void destroyGUI() {
        this.kill();
    }
    
    public static void dock(final Frame component) {
        final Docking docking = component.getDocking();
        if (docking.isTop()) {
            component.setY(0);
        }
        if (docking.isBottom()) {
            component.setY(Wrapper.getMinecraft().displayHeight / DisplayGuiScreen.getScale() - component.getHeight() - 0);
        }
        if (docking.isLeft()) {
            component.setX(0);
        }
        if (docking.isRight()) {
            component.setX(Wrapper.getMinecraft().displayWidth / DisplayGuiScreen.getScale() - component.getWidth() - 0);
        }
        if (docking.isCenterHorizontal()) {
            component.setX(Wrapper.getMinecraft().displayWidth / (DisplayGuiScreen.getScale() * 2) - component.getWidth() / 2);
        }
        if (docking.isCenterVertical()) {
            component.setY(Wrapper.getMinecraft().displayHeight / (DisplayGuiScreen.getScale() * 2) - component.getHeight() / 2);
        }
    }
    
    static {
        fontRenderer = new RootFontRenderer(1.0f);
        KamiGUI.primaryColour = new ColourHolder(29, 29, 29);
    }
}
