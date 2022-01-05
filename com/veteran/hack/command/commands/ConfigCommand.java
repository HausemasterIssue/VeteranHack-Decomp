//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.command.commands;

import com.veteran.hack.command.*;
import com.veteran.hack.command.syntax.*;
import com.veteran.hack.command.syntax.parsers.*;
import com.veteran.hack.*;
import java.nio.file.*;
import java.io.*;
import com.veteran.hack.gui.kami.*;

public class ConfigCommand extends Command
{
    public ConfigCommand() {
        super("config", new ChunkBuilder().append("mode", true, new EnumParser(new String[] { "reload", "save", "path" })).append("path", true, new DependantParser(0, new DependantParser.Dependency(new String[][] { { "path", "path" } }, ""))).build(), new String[] { "cfg" });
        this.setDescription("Change where your config is saved or manually save and reload your config");
    }
    
    public void call(final String[] args) {
        if (args[0] == null) {
            Command.sendChatMessage("Missing argument &bmode&r: Choose from reload, save or path");
            return;
        }
        final String lowerCase = args[0].toLowerCase();
        switch (lowerCase) {
            case "reload": {
                this.reload();
                return;
            }
            case "save": {
                try {
                    BaseMod.saveConfigurationUnsafe();
                    Command.sendChatMessage("Saved configuration!");
                }
                catch (IOException e) {
                    e.printStackTrace();
                    Command.sendChatMessage("Failed to save! " + e.getMessage());
                }
                return;
            }
            case "path": {
                if (args[1] == null) {
                    final Path file = Paths.get(BaseMod.getConfigName(), new String[0]);
                    Command.sendChatMessage("Path to configuration: &b" + file.toAbsolutePath().toString());
                    return;
                }
                final String newPath = args[1];
                if (!BaseMod.isFilenameValid(newPath)) {
                    Command.sendChatMessage("&b" + newPath + "&r is not a valid path");
                    return;
                }
                try (final BufferedWriter writer = Files.newBufferedWriter(Paths.get("KAMILastConfig.txt", new String[0]), new OpenOption[0])) {
                    writer.write(newPath);
                    this.reload();
                    Command.sendChatMessage("Configuration path set to &b" + newPath + "&r!");
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                    Command.sendChatMessage("Couldn't set path: " + e2.getMessage());
                    return;
                }
                break;
            }
        }
        Command.sendChatMessage("Incorrect mode, please choose from: reload, save or path");
    }
    
    private void reload() {
        (BaseMod.getInstance().guiManager = new KamiGUI()).initializeGUI();
        BaseMod.loadConfiguration();
        Command.sendChatMessage("Configuration reloaded!");
    }
}
