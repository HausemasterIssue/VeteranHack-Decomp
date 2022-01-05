//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.module.modules.hidden;

import com.veteran.hack.module.*;
import java.util.*;
import com.veteran.hack.event.events.*;
import me.zero.alpine.listener.*;
import com.veteran.hack.setting.*;
import java.util.function.*;
import com.veteran.hack.command.*;
import net.minecraft.network.play.server.*;

@Module.Info(name = "AutoFish", category = Module.Category.HIDDEN, description = "Automatically catch fish")
public class AutoFish extends Module
{
    private Setting<Boolean> defaultSetting;
    private Setting<Integer> baseDelay;
    private Setting<Integer> extraDelay;
    private Setting<Integer> variation;
    Random random;
    @EventHandler
    private Listener<PacketEvent.Receive> receiveListener;
    
    public AutoFish() {
        this.defaultSetting = (Setting<Boolean>)this.register((Setting)Settings.b("Defaults", false));
        this.baseDelay = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Throw Delay").withValue(450).withMinimum(50).withMaximum(1000).build());
        this.extraDelay = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Catch Delay").withValue(300).withMinimum(0).withMaximum(1000).build());
        this.variation = (Setting<Integer>)this.register((Setting)Settings.integerBuilder("Variation").withValue(50).withMinimum(0).withMaximum(1000).build());
        this.receiveListener = (Listener<PacketEvent.Receive>)new Listener(e -> {
            if (e.getPacket() instanceof SPacketSoundEffect) {
                final SPacketSoundEffect pck = (SPacketSoundEffect)e.getPacket();
                if (pck.getSound().getSoundName().toString().toLowerCase().contains("entity.bobber.splash")) {
                    if (AutoFish.mc.player.fishEntity == null) {
                        return;
                    }
                    final int soundX = (int)pck.getX();
                    final int soundZ = (int)pck.getZ();
                    final int fishX = (int)AutoFish.mc.player.fishEntity.posX;
                    final int fishZ = (int)AutoFish.mc.player.fishEntity.posZ;
                    if (this.kindaEquals(soundX, fishX) && this.kindaEquals(fishZ, soundZ)) {
                        new Thread(() -> {
                            this.random = new Random();
                            try {
                                Thread.sleep(this.extraDelay.getValue() + this.random.ints(1L, -this.variation.getValue(), this.variation.getValue()).findFirst().getAsInt());
                            }
                            catch (InterruptedException ex) {}
                            AutoFish.mc.rightClickMouse();
                            this.random = new Random();
                            try {
                                Thread.sleep(this.baseDelay.getValue() + this.random.ints(1L, -this.variation.getValue(), this.variation.getValue()).findFirst().getAsInt());
                            }
                            catch (InterruptedException e2) {
                                e2.printStackTrace();
                            }
                            AutoFish.mc.rightClickMouse();
                        }).start();
                    }
                }
            }
        }, new Predicate[0]);
    }
    
    public void onUpdate() {
        if (this.defaultSetting.getValue()) {
            this.baseDelay.setValue(450);
            this.extraDelay.setValue(300);
            this.variation.setValue(50);
            this.defaultSetting.setValue(false);
            Command.sendChatMessage(this.getChatName() + " Set to defaults!");
            Command.sendChatMessage(this.getChatName() + " Close and reopen the " + this.getName() + " settings menu to see changes");
        }
    }
    
    public boolean kindaEquals(final int kara, final int ni) {
        return ni == kara || ni == kara - 1 || ni == kara + 1;
    }
}
