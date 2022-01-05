//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.command.commands;

import com.veteran.hack.command.*;
import com.veteran.hack.command.syntax.*;
import com.veteran.hack.module.*;
import com.veteran.hack.*;
import java.util.*;

public class GenerateWebsiteCommand extends Command
{
    public GenerateWebsiteCommand() {
        super("genwebsite", (SyntaxChunk[])null, new String[0]);
        this.setDescription("Generates the module page for the website");
    }
    
    private static String nameAndDescription(final Module module) {
        return "<li>" + module.getName() + "<p><i>" + module.getDescription() + "</i></p></li>";
    }
    
    public void call(final String[] args) {
        final List<Module> mods = new ArrayList<Module>(ModuleManager.getModules());
        final String[] modCategories = { "Chat", "Combat", "Gui", "Misc", "Movement", "Player", "Render", "Utils" };
        final List<String> modCategoriesList = new ArrayList<String>(Arrays.asList(modCategories));
        final List<String> modsChat = new ArrayList<String>();
        final List<String> modsCombat = new ArrayList<String>();
        final List<String> modsGui = new ArrayList<String>();
        final List<String> modsMisc = new ArrayList<String>();
        final List<String> modsMovement = new ArrayList<String>();
        final List<String> modsPlayer = new ArrayList<String>();
        final List<String> modsRender = new ArrayList<String>();
        final List<String> modsUtils = new ArrayList<String>();
        final List<String> list;
        final List<String> list2;
        final List<String> list3;
        final List<String> list4;
        final List<String> list5;
        final List<String> list6;
        final List<String> list7;
        final List<String> list8;
        mods.forEach(module -> {
            switch (module.getCategory()) {
                case CHAT: {
                    list.add(nameAndDescription(module));
                }
                case COMBAT: {
                    list2.add(nameAndDescription(module));
                }
                case GUI: {
                    list3.add(nameAndDescription(module));
                }
                case MISC: {
                    list4.add(nameAndDescription(module));
                }
                case MOVEMENT: {
                    list5.add(nameAndDescription(module));
                }
                case PLAYER: {
                    list6.add(nameAndDescription(module));
                }
                case RENDER: {
                    list7.add(nameAndDescription(module));
                }
                case UTILS: {
                    list8.add(nameAndDescription(module));
                    break;
                }
            }
            return;
        });
        final Iterable iterable;
        modCategoriesList.forEach(modCategory -> {
            BaseMod.log.info("<details>");
            BaseMod.log.info("    <summary>" + modCategory + "</summary>");
            BaseMod.log.info("    <p><ul>");
            iterable.forEach(module -> {
                if (module.getCategory().toString().equalsIgnoreCase(modCategory)) {
                    BaseMod.log.info("        <li>" + module.getName() + "<p><i>" + module.getDescription() + "</i></p></li>");
                }
                return;
            });
            BaseMod.log.info("    </ul></p>");
            BaseMod.log.info("</details>");
            return;
        });
        Command.sendChatMessage(this.getLabel().substring(0, 1).toUpperCase() + this.getLabel().substring(1) + ": Generated website to log file!");
    }
}
