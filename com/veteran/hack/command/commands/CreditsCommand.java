//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.command.commands;

import com.veteran.hack.command.*;
import com.veteran.hack.command.syntax.*;

public class CreditsCommand extends Command
{
    public CreditsCommand() {
        super("credits", (SyntaxChunk[])null, new String[0]);
        this.setDescription("Prints KAMI Blue's authors and contributors");
    }
    
    public void call(final String[] args) {
        Command.sendChatMessage("\nName (Github if not same as name)&l&9Author:\n086 (zeroeightysix)\n&l&9Contributors:\nBella (S-B99)\nhub (blockparole)\nSasha (EmotionalLove)\nQther (d1gress / Vonr)\nHHSGPA\n20kdc\nIronException\ncats (Cuhnt)\nKatatje\nDeauthorized\nsnowmii\nkdb424\nJack (jacksonellsworth03)\ncookiedragon234\n0x2E (PretendingToCode)\nbabbaj\nZeroMemes\nTheBritishMidget (TBM)\nHamburger (Hamburger2k)\nDarki\nCrystallinqq\nElementars\nfsck\nJamie (jamie27)\nWaizy\nIt is the end\nfluffcq\nleijurv\npolymer\nBattery Settings (Bluskript)\nAn-En (AnotherEntity)\nArisa (Arisa-Snowbell)\nUrM0ther");
    }
}
