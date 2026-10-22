package com.kdroid.launcher.core.mods.inbuilt.nativemod;

public class SnaplookMod {

    public static boolean init() {
        if (!InbuiltModsNative.loadLibrary()) {
            return false;
        }
        return nativeInit();
    }

    public static boolean nativeInit() { return org.levimc.launcher.core.mods.inbuilt.nativemod.SnaplookMod.nativeInit(); }
    public static void nativeOnKeyDown() { org.levimc.launcher.core.mods.inbuilt.nativemod.SnaplookMod.nativeOnKeyDown(); }
    public static void nativeOnKeyUp() { org.levimc.launcher.core.mods.inbuilt.nativemod.SnaplookMod.nativeOnKeyUp(); }
}
