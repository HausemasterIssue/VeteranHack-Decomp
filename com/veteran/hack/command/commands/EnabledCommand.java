//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.command.commands;

import com.veteran.hack.command.*;
import com.veteran.hack.command.syntax.*;
import java.util.concurrent.atomic.*;
import com.veteran.hack.module.*;
import org.apache.commons.lang3.*;
import net.minecraft.util.text.*;
import java.util.*;

public class EnabledCommand extends Command
{
    public EnabledCommand() {
        super("enabled", (SyntaxChunk[])null, new String[0]);
        this.setDescription("Prints enabled modules");
    }
    
    public void call(final String[] args) {
        final AtomicReference<String> enabled = new AtomicReference<String>("");
        final List<Module> mods = new ArrayList<Module>(ModuleManager.getModules());
        final AtomicReference<String> atomicReference;
        mods.forEach(module -> {
            if (module.isEnabled()) {
                atomicReference.set(atomicReference + module.getName() + ", ");
            }
            return;
        });
        enabled.set(StringUtils.chop(StringUtils.chop(String.valueOf(enabled))));
        Command.sendChatMessage("Enabled modules: \n" + TextFormatting.GRAY + enabled);
    }
}
