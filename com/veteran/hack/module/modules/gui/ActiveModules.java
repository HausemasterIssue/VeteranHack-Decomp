//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.gui;

import com.veteran.hack.setting.*;
import com.veteran.hack.util.*;
import net.minecraft.util.text.*;
import java.awt.*;
import com.veteran.hack.module.*;
import com.veteran.hack.command.*;

@Module.Info(name = "ActiveModules", category = Module.Category.GUI, description = "Configures ActiveModules Colour", showOnArray = Module.ShowOnArray.OFF)
public class ActiveModules extends Module
{
    private Setting<Boolean> forgeHax;
    public Setting<Mode> mode;
    private Setting<Integer> rainbowSpeed;
    public Setting<Integer> saturationR;
    public Setting<Integer> brightnessR;
    public Setting<Integer> hueC;
    public Setting<Integer> saturationC;
    public Setting<Integer> brightnessC;
    private Setting<Boolean> alternate;
    
    public ActiveModules() {
        this.forgeHax = (Setting<Boolean>)this.register((Setting)Settings.b("ForgeHax", false));
        this.mode = (Setting<Mode>)this.register((Setting)Settings.e("Mode", Mode.RAINBOW));
        this.rainbowSpeed = (Setting<Integer>)this.register((Setting)Settings.integerBuilder().withName("Speed R").withValue(30).withMinimum(0).withMaximum(100).withVisibility(v -> this.mode.getValue().equals(Mode.RAINBOW)).build());
        this.saturationR = (Setting<Integer>)this.register((Setting)Settings.integerBuilder().withName("Saturation R").withValue(117).withMinimum(0).withMaximum(255).withVisibility(v -> this.mode.getValue().equals(Mode.RAINBOW)).build());
        this.brightnessR = (Setting<Integer>)this.register((Setting)Settings.integerBuilder().withName("Brightness R").withValue(255).withMinimum(0).withMaximum(255).withVisibility(v -> this.mode.getValue().equals(Mode.RAINBOW)).build());
        this.hueC = (Setting<Integer>)this.register((Setting)Settings.integerBuilder().withName("Hue C").withValue(178).withMinimum(0).withMaximum(255).withVisibility(v -> this.mode.getValue().equals(Mode.CUSTOM)).build());
        this.saturationC = (Setting<Integer>)this.register((Setting)Settings.integerBuilder().withName("Saturation C").withValue(156).withMinimum(0).withMaximum(255).withVisibility(v -> this.mode.getValue().equals(Mode.CUSTOM)).build());
        this.brightnessC = (Setting<Integer>)this.register((Setting)Settings.integerBuilder().withName("Brightness C").withValue(255).withMinimum(0).withMaximum(255).withVisibility(v -> this.mode.getValue().equals(Mode.CUSTOM)).build());
        this.alternate = (Setting<Boolean>)this.register((Setting)Settings.booleanBuilder().withName("Alternate").withValue(true).withVisibility(v -> this.mode.getValue().equals(Mode.INFO_OVERLAY)).build());
    }
    
    public static int getCategoryColour(final Module module) {
        switch (module.getCategory()) {
            case CHAT: {
                return ColourConverter.rgbToInt(129, 171, 174);
            }
            case COMBAT: {
                return ColourConverter.rgbToInt(162, 25, 14);
            }
            case EXPERIMENTAL: {
                return ColourConverter.rgbToInt(175, 175, 31);
            }
            case GUI: {
                return ColourConverter.rgbToInt(158, 159, 197);
            }
            case RENDER: {
                return ColourConverter.rgbToInt(51, 197, 130);
            }
            case PLAYER: {
                return ColourConverter.rgbToInt(99, 202, 191);
            }
            case MOVEMENT: {
                return ColourConverter.rgbToInt(7, 77, 227);
            }
            case MISC: {
                return ColourConverter.rgbToInt(247, 215, 59);
            }
            case UTILS: {
                return ColourConverter.rgbToInt(46, 212, 77);
            }
            default: {
                return ColourConverter.rgbToInt(139, 100, 255);
            }
        }
    }
    
    public int getInfoColour(final int position) {
        if (!this.alternate.getValue()) {
            return this.settingsToColour(false);
        }
        if (InfoCalculator.isNumberEven(position)) {
            return this.settingsToColour(true);
        }
        return this.settingsToColour(false);
    }
    
    private int settingsToColour(final boolean isOne) {
        Color localColor = null;
        switch (this.infoGetSetting(isOne)) {
            case UNDERLINE:
            case ITALIC:
            case RESET:
            case STRIKETHROUGH:
            case OBFUSCATED:
            case BOLD: {
                localColor = ColourTextFormatting.colourEnumMap.get(TextFormatting.WHITE).colorLocal;
                break;
            }
            default: {
                localColor = ColourTextFormatting.colourEnumMap.get(this.infoGetSetting(isOne)).colorLocal;
                break;
            }
        }
        return ColourConverter.rgbToInt(localColor.getRed(), localColor.getGreen(), localColor.getBlue());
    }
    
    private TextFormatting infoGetSetting(final boolean isOne) {
        final InfoOverlay infoOverlay = (InfoOverlay)ModuleManager.getModuleByName("InfoOverlay");
        if (isOne) {
            return this.setToText(infoOverlay.firstColour.getValue());
        }
        return this.setToText(infoOverlay.secondColour.getValue());
    }
    
    private TextFormatting setToText(final ColourTextFormatting.ColourCode colourCode) {
        return ColourTextFormatting.toTextMap.get(colourCode);
    }
    
    public int getRainbowSpeed() {
        final int rSpeed = InfoCalculator.reverseNumber(this.rainbowSpeed.getValue(), 1, 100);
        if (rSpeed == 0) {
            return 1;
        }
        return rSpeed;
    }
    
    public String fHax() {
        if (this.forgeHax.getValue()) {
            return ">";
        }
        return "";
    }
    
    public void onDisable() {
        Command.sendDisableMessage(this.getName());
    }
    
    public enum Mode
    {
        RAINBOW, 
        CUSTOM, 
        CATEGORY, 
        INFO_OVERLAY;
    }
}
