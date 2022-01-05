//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\u1\Documents\Java Decompiler\1.12 stable mappings"!

//Decompiled by Procyon!

package com.veteran.hack.gui.rgui.util;

import com.veteran.hack.gui.rgui.component.container.*;
import com.veteran.hack.gui.rgui.render.theme.*;
import com.veteran.hack.gui.rgui.component.*;
import com.veteran.hack.gui.rgui.*;
import java.util.*;

public class ContainerHelper
{
    public static void setTheme(final Container parent, final Theme newTheme) {
        final Theme old = parent.getTheme();
        parent.setTheme(newTheme);
        for (final Component c : parent.getChildren()) {
            if (c.getTheme().equals(old)) {
                c.setTheme(newTheme);
            }
        }
    }
    
    public static void setAlignment(final Container container, final AlignedComponent.Alignment alignment) {
        for (final Component component : container.getChildren()) {
            if (component instanceof Container) {
                setAlignment((Container)component, alignment);
            }
            if (component instanceof AlignedComponent) {
                ((AlignedComponent)component).setAlignment(alignment);
            }
        }
    }
    
    public static AlignedComponent.Alignment getAlignment(final Container container) {
        for (final Component component : container.getChildren()) {
            if (component instanceof Container) {
                return getAlignment((Container)component);
            }
            if (component instanceof AlignedComponent) {
                return ((AlignedComponent)component).getAlignment();
            }
        }
        return AlignedComponent.Alignment.LEFT;
    }
    
    public static Component getHighParent(final Component child) {
        if (child.getParent() instanceof GUI || child.getParent() == null) {
            return child;
        }
        return getHighParent((Component)child.getParent());
    }
    
    public static <T extends Component> T getFirstParent(final Class<? extends T> parentClass, final Component component) {
        if (component.getClass().equals(parentClass)) {
            return (T)component;
        }
        if (component == null) {
            return null;
        }
        return (T)getFirstParent((Class<? extends Component>)parentClass, (Component)component.getParent());
    }
    
    public static <S extends Component> List<S> getAllChildren(final Class<? extends S> childClass, final Container parent) {
        final ArrayList<S> list = new ArrayList<S>();
        for (final Component c : parent.getChildren()) {
            if (childClass.isAssignableFrom(c.getClass())) {
                list.add((S)c);
            }
            if (c instanceof Container) {
                list.addAll((Collection<? extends S>)getAllChildren((Class<? extends Component>)childClass, (Container)c));
            }
        }
        return list;
    }
}
