//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package me.zero.alpine;

public interface EventBus
{
    void subscribe(final Object p0);
    
    void subscribeAll(final Object... p0);
    
    void subscribeAll(final Iterable<Object> p0);
    
    void unsubscribe(final Object p0);
    
    void unsubscribeAll(final Object... p0);
    
    void unsubscribeAll(final Iterable<Object> p0);
    
    void post(final Object p0);
    
    void attach(final EventBus p0);
    
    void detach(final EventBus p0);
}
