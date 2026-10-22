package com.kdroid.launcher.core.mods.inbuilt.nativemod;

public class FpsMod {

    public static boolean init() {
        if (!InbuiltModsNative.loadLibrary()) {
            return false;
        }
        return nativeInit();
    }

    public static boolean nativeInit() { return org.levimc.launcher.core.mods.inbuilt.nativemod.FpsMod.nativeInit(); }
    public static int nativeGetFps() { return org.levimc.launcher.core.mods.inbuilt.nativemod.FpsMod.nativeGetFps(); }
    public static boolean nativeIsInitialized() { return org.levimc.launcher.core.mods.inbuilt.nativemod.FpsMod.nativeIsInitialized(); }
}
