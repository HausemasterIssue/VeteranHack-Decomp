//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.util;

import java.util.*;
import org.reflections.scanners.*;
import org.reflections.*;

public class ClassFinder
{
    public static Set<Class> findClasses(final String pack, final Class subType) {
        final Reflections reflections = new Reflections(pack, new Scanner[0]);
        return (Set<Class>)reflections.getSubTypesOf(subType);
    }
}
