package com.science.gtnl.common.machine.multiMachineClasses.EdenGardenManager;

import java.util.HashMap;

import com.science.gtnl.common.machine.multiMachineClasses.EdenGardenManager.modes.EIGIC2Mode;
import com.science.gtnl.common.machine.multiMachineClasses.EdenGardenManager.modes.EIGNormalMode;

import gregtech.api.util.MultiblockTooltipBuilder;

public class EIGModes {

    private static final HashMap<String, EIGMode> modes = new HashMap<>();

    public static final EIGMode Normal = addMode(EIGNormalMode.instance);
    public static final EIGMode IC2 = addMode(EIGIC2Mode.instance);

    // this is basically a fake enum, plz don't instantiate
    private EIGModes() {}

    private static EIGMode addMode(EIGMode mode) {
        modes.put(mode.getName(), mode);
        return mode;
    }

    public static EIGMode getModeFromName(String name) {
        return modes.get(name);
    }

    public static EIGMode getNextMode(EIGMode from) {
        int id = (from.getUIIndex() + 1) % modes.size();
        for (EIGMode mode : modes.values()) {
            if (mode.getUIIndex() == id) return mode;
        }
        return Normal;
    }

    public static void addTooltipInfo(MultiblockTooltipBuilder tt) {
        // maybe make this use the mods list instead
        EIGModes.Normal.addTooltipInfo(tt);
        EIGModes.IC2.addTooltipInfo(tt);
    }
}
