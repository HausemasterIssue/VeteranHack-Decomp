//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.gui.rgui.render.util;

import java.util.*;
import java.io.*;

public final class StreamReader
{
    private final InputStream stream;
    
    public StreamReader(final InputStream stream) {
        this.stream = stream;
    }
    
    public final String read() {
        final StringJoiner joiner = new StringJoiner("\n");
        try {
            final BufferedReader br = new BufferedReader(new InputStreamReader(this.stream));
            String line;
            while ((line = br.readLine()) != null) {
                joiner.add(line);
            }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return joiner.toString();
    }
}
