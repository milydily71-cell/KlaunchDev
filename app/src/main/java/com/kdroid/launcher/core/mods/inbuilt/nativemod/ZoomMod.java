package com.kdroid.launcher.core.mods.inbuilt.nativemod;

public class ZoomMod {

    public static boolean init() {
        if (!InbuiltModsNative.loadLibrary()) {
            return false;
        }
        return nativeInit();
    }

    public static boolean nativeInit() { return org.levimc.launcher.core.mods.inbuilt.nativemod.ZoomMod.nativeInit(); }
    public static void nativeOnKeyDown() { org.levimc.launcher.core.mods.inbuilt.nativemod.ZoomMod.nativeOnKeyDown(); }
    public static void nativeOnKeyUp() { org.levimc.launcher.core.mods.inbuilt.nativemod.ZoomMod.nativeOnKeyUp(); }
    public static void nativeOnScroll(float delta) { org.levimc.launcher.core.mods.inbuilt.nativemod.ZoomMod.nativeOnScroll(delta); }

    public static void nativeSetZoomLevel(long level) { org.levimc.launcher.core.mods.inbuilt.nativemod.ZoomMod.nativeSetZoomLevel(level); }
    public static void nativeSetTransitionDuration(int duration) { org.levimc.launcher.core.mods.inbuilt.nativemod.ZoomMod.nativeSetTransitionDuration(duration); }
}
