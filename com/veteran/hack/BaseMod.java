//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack;

import net.minecraftforge.fml.common.*;
import com.veteran.hack.gui.kami.*;
import com.google.common.base.*;
import org.lwjgl.opengl.*;
import net.minecraftforge.fml.common.event.*;
import java.util.function.*;
import net.minecraftforge.common.*;
import com.veteran.hack.event.*;
import com.veteran.hack.command.*;
import com.veteran.hack.setting.*;
import com.veteran.hack.util.*;
import com.veteran.hack.module.*;
import com.veteran.hack.module.modules.misc.*;
import com.veteran.hack.module.modules.hidden.*;
import com.veteran.hack.module.modules.render.*;
import com.veteran.hack.module.modules.gui.*;
import java.nio.file.*;
import com.veteran.hack.setting.config.*;
import com.veteran.hack.gui.rgui.component.container.use.*;
import com.veteran.hack.gui.rgui.util.*;
import com.veteran.hack.gui.rgui.component.container.*;
import com.veteran.hack.gui.rgui.component.*;
import com.google.gson.*;
import java.util.*;
import java.nio.file.attribute.*;
import java.io.*;
import org.apache.logging.log4j.*;
import me.zero.alpine.*;

@Mod(modid = "vethack", name = "Veteran Hack", version = "b2.3")
public class BaseMod
{
    public static final String MODNAME = "Veteran Hack";
    public static final String MODID = "vethack";
    public static final String MODVER = "b2.3";
    public static final String MODVERSMALL = "b2.3";
    public static final String APP_ID = "692773855667945572";
    public static final String AUTH_JSON = "https://raw.githubusercontent.com/pol-y-mer/vethackuserJSON/master/users.json";
    public static final String NAME_LONG = "\u1d20\u1d07\u1d1b\u1d07\u0280\u1d00\u0274 \u029c\u1d00\u1d04\u1d0b";
    public static final String NAME_SHORT = "\u1d20\u1d07\u1d1b\u029c\u1d00\u1d04\u1d0b";
    public static final String ONTOP_2B = "Veteran Hack On Top";
    public static final String NAME_2B = "Veteran Hack";
    public static final String WEBSITE = "hack.veteran.com :^)";
    public static final char colour = '§';
    public static final char separator = '\u23d0';
    public static final char quoteLeft = '«';
    public static final char quoteRight = '»';
    private static final String KAMI_CONFIG_NAME_DEFAULT = "VETHACK.cfg";
    public static final Logger log;
    public static final EventBus EVENT_BUS;
    @Mod.Instance
    private static BaseMod INSTANCE;
    public KamiGUI guiManager;
    public CommandManager commandManager;
    private Setting<JsonObject> guiStateSetting;
    
    public BaseMod() {
        this.guiStateSetting = Settings.custom("gui", new JsonObject(), new Converter<JsonObject, JsonObject>() {
            protected JsonObject doForward(final JsonObject jsonObject) {
                return jsonObject;
            }
            
            protected JsonObject doBackward(final JsonObject jsonObject) {
                return jsonObject;
            }
        }).buildAndRegister("");
    }
    
    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
    }
    
    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent event) {
        Display.setTitle("Veteran Hack b2.3");
    }
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        BaseMod.log.info("\n\nInitializing Veteran Hack b2.3");
        ModuleManager.initialize();
        ModuleManager.getModules().stream().filter(module -> module.alwaysListening).forEach(BaseMod.EVENT_BUS::subscribe);
        MinecraftForge.EVENT_BUS.register((Object)new ForgeEventProcessor());
        LagCompensator.INSTANCE = new LagCompensator();
        Wrapper.init();
        (this.guiManager = new KamiGUI()).initializeGUI();
        this.commandManager = new CommandManager();
        Friends.initFriends();
        SettingsRegister.register("commandPrefix", Command.commandPrefix);
        loadConfiguration();
        BaseMod.log.info("Settings loaded");
        new RichPresence();
        ModuleManager.getModules().stream().filter(Module::isEnabled).forEach(Module::enable);
        try {
            ModuleManager.getModuleByName("InfoOverlay").setEnabled(true);
            ModuleManager.getModuleByName("InventoryViewer").setEnabled(true);
            ModuleManager.getModuleByName("ActiveModules").setEnabled(true);
            if (((DiscordSettings)ModuleManager.getModuleByName("DiscordSettings")).startupGlobal.getValue()) {
                ModuleManager.getModuleByName("DiscordSettings").setEnabled(true);
            }
            if (((FixGui)ModuleManager.getModuleByName("Hidden:FixGui")).shouldAutoEnable.getValue()) {
                ModuleManager.getModuleByName("Hidden:FixGui").setEnabled(true);
            }
            if (((TabFriends)ModuleManager.getModuleByName("TabFriends")).startupGlobal.getValue()) {
                ModuleManager.getModuleByName("TabFriends").setEnabled(true);
            }
            if (((PrefixChat)ModuleManager.getModuleByName("PrefixChat")).startupGlobal.getValue()) {
                ModuleManager.getModuleByName("PrefixChat").setEnabled(true);
            }
        }
        catch (NullPointerException e) {
            BaseMod.log.error("NPE in loading always enabled modules\n");
        }
        BaseMod.log.info("Veteran Hack Has been initialized!\n");
        BaseMod.log.info(" This client is skidded from KAMI BLUE, which is a fork of KAMI. please go check out the original client at https://github.com/s-b99/kamiblue");
    }
    
    public static String getConfigName() {
        final Path config = Paths.get("VETHACK_LAST.txt", new String[0]);
        String kamiConfigName = "VETHACK.cfg";
        try (final BufferedReader reader = Files.newBufferedReader(config)) {
            kamiConfigName = reader.readLine();
            if (!isFilenameValid(kamiConfigName)) {
                kamiConfigName = "VETHACK.cfg";
            }
        }
        catch (NoSuchFileException e3) {
            try (final BufferedWriter writer = Files.newBufferedWriter(config, new OpenOption[0])) {
                writer.write("VETHACK.cfg");
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        return kamiConfigName;
    }
    
    public static void loadConfiguration() {
        try {
            loadConfigurationUnsafe();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void loadConfigurationUnsafe() throws IOException {
        final String kamiConfigName = getConfigName();
        final Path kamiConfig = Paths.get(kamiConfigName, new String[0]);
        if (!Files.exists(kamiConfig, new LinkOption[0])) {
            return;
        }
        Configuration.loadConfiguration(kamiConfig);
        final JsonObject gui = BaseMod.INSTANCE.guiStateSetting.getValue();
        for (final Map.Entry<String, JsonElement> entry : gui.entrySet()) {
            final Optional<Component> optional = BaseMod.INSTANCE.guiManager.getChildren().stream().filter(component -> component instanceof Frame).filter(component -> component.getTitle().equals(entry.getKey())).findFirst();
            if (optional.isPresent()) {
                final JsonObject object = entry.getValue().getAsJsonObject();
                final Frame frame = optional.get();
                frame.setX(object.get("x").getAsInt());
                frame.setY(object.get("y").getAsInt());
                final Docking docking = Docking.values()[object.get("docking").getAsInt()];
                if (docking.isLeft()) {
                    ContainerHelper.setAlignment(frame, AlignedComponent.Alignment.LEFT);
                }
                else if (docking.isRight()) {
                    ContainerHelper.setAlignment(frame, AlignedComponent.Alignment.RIGHT);
                }
                else if (docking.isCenterVertical()) {
                    ContainerHelper.setAlignment(frame, AlignedComponent.Alignment.CENTER);
                }
                frame.setDocking(docking);
                frame.setMinimized(object.get("minimized").getAsBoolean());
                frame.setPinned(object.get("pinned").getAsBoolean());
            }
            else {
                System.err.println("Found GUI config entry for " + entry.getKey() + ", but found no frame with that name");
            }
        }
        getInstance().getGuiManager().getChildren().stream().filter(component -> component instanceof Frame && component.isPinnable() && component.isVisible()).forEach(component -> component.setOpacity(0.0f));
    }
    
    public static void saveConfiguration() {
        try {
            saveConfigurationUnsafe();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void saveConfigurationUnsafe() throws IOException {
        final JsonObject object = new JsonObject();
        final JsonObject frameObject;
        final JsonObject jsonObject;
        BaseMod.INSTANCE.guiManager.getChildren().stream().filter(component -> component instanceof Frame).map(component -> component).forEach(frame -> {
            frameObject = new JsonObject();
            frameObject.add("x", (JsonElement)new JsonPrimitive((Number)frame.getX()));
            frameObject.add("y", (JsonElement)new JsonPrimitive((Number)frame.getY()));
            frameObject.add("docking", (JsonElement)new JsonPrimitive((Number)Arrays.asList(Docking.values()).indexOf(frame.getDocking())));
            frameObject.add("minimized", (JsonElement)new JsonPrimitive(Boolean.valueOf(frame.isMinimized())));
            frameObject.add("pinned", (JsonElement)new JsonPrimitive(Boolean.valueOf(frame.isPinned())));
            jsonObject.add(frame.getTitle(), (JsonElement)frameObject);
            return;
        });
        BaseMod.INSTANCE.guiStateSetting.setValue(object);
        final Path outputFile = Paths.get(getConfigName(), new String[0]);
        if (!Files.exists(outputFile, new LinkOption[0])) {
            Files.createFile(outputFile, (FileAttribute<?>[])new FileAttribute[0]);
        }
        Configuration.saveConfiguration(outputFile);
        ModuleManager.getModules().forEach(Module::destroy);
    }
    
    public static boolean isFilenameValid(final String file) {
        final File f = new File(file);
        try {
            f.getCanonicalPath();
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }
    
    public static BaseMod getInstance() {
        return BaseMod.INSTANCE;
    }
    
    public KamiGUI getGuiManager() {
        return this.guiManager;
    }
    
    public CommandManager getCommandManager() {
        return this.commandManager;
    }
    
    static {
        log = LogManager.getLogger("VETERAN");
        EVENT_BUS = (EventBus)new EventManager();
    }
    
    public class User
    {
        public String uuid;
    }
}
