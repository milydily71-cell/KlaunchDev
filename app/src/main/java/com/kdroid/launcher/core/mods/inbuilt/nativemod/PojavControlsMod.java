package com.kdroid.launcher.core.mods.inbuilt.nativemod;

public final class PojavControlsMod {
    private static boolean initialized;
    private static boolean initAttempted;

    private PojavControlsMod() {}

    public static synchronized boolean initialize() {
        if (initialized) return true;
        if (initAttempted) return false;
        initAttempted = true;
        if (!InbuiltModsNative.loadLibrary()) return false;
        initialized = nativeInit();
        return initialized;
    }

    public static boolean setEnabled(boolean enabled) {
        if (enabled && !initialize()) return false;
        if (initialized) nativeSetEnabled(enabled);
        return !enabled || initialized;
    }

    public static void nativeSendKey(int keyCode, boolean down) { org.levimc.launcher.core.mods.inbuilt.nativemod.PojavControlsMod.nativeSendKey(keyCode, down); }
    public static void nativeSendMouseButton(int androidButton, boolean down) { org.levimc.launcher.core.mods.inbuilt.nativemod.PojavControlsMod.nativeSendMouseButton(androidButton, down); }
    public static void nativeSendScroll(float vertical) { org.levimc.launcher.core.mods.inbuilt.nativemod.PojavControlsMod.nativeSendScroll(vertical); }
    public static void nativeSendLookDelta(float deltaX, float deltaY) { org.levimc.launcher.core.mods.inbuilt.nativemod.PojavControlsMod.nativeSendLookDelta(deltaX, deltaY); }
    public static void nativeSendPointer(float x, float y) { org.levimc.launcher.core.mods.inbuilt.nativemod.PojavControlsMod.nativeSendPointer(x, y); }

    private static boolean nativeInit() { return org.levimc.launcher.core.mods.inbuilt.nativemod.PojavControlsMod.nativeInit(); }
    private static void nativeSetEnabled(boolean enabled) { org.levimc.launcher.core.mods.inbuilt.nativemod.PojavControlsMod.nativeSetEnabled(enabled); }
}
