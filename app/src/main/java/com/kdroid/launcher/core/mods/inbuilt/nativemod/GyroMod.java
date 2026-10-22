package com.kdroid.launcher.core.mods.inbuilt.nativemod;

public class GyroMod {

    public static boolean init() {
        if (!InbuiltModsNative.loadLibrary()) {
            return false;
        }
        return nativeInit();
    }

    public static void nativePreResolve() { org.levimc.launcher.core.mods.inbuilt.nativemod.GyroMod.nativePreResolve(); }
    public static boolean nativeInit() { return org.levimc.launcher.core.mods.inbuilt.nativemod.GyroMod.nativeInit(); }
    public static void nativeSetEnabled(boolean enabled) { org.levimc.launcher.core.mods.inbuilt.nativemod.GyroMod.nativeSetEnabled(enabled); }
    public static void nativeUpdateDelta(float deltaYaw, float deltaPitch) { org.levimc.launcher.core.mods.inbuilt.nativemod.GyroMod.nativeUpdateDelta(deltaYaw, deltaPitch); }
    public static void nativeSetSensitivityX(float sensitivity) { org.levimc.launcher.core.mods.inbuilt.nativemod.GyroMod.nativeSetSensitivityX(sensitivity); }
    public static void nativeSetSensitivityY(float sensitivity) { org.levimc.launcher.core.mods.inbuilt.nativemod.GyroMod.nativeSetSensitivityY(sensitivity); }
    public static void nativeSetInvertX(boolean invert) { org.levimc.launcher.core.mods.inbuilt.nativemod.GyroMod.nativeSetInvertX(invert); }
    public static void nativeSetInvertY(boolean invert) { org.levimc.launcher.core.mods.inbuilt.nativemod.GyroMod.nativeSetInvertY(invert); }
    public static void nativeSetDeadzone(float deadzone) { org.levimc.launcher.core.mods.inbuilt.nativemod.GyroMod.nativeSetDeadzone(deadzone); }
    public static boolean nativeIsInitialized() { return org.levimc.launcher.core.mods.inbuilt.nativemod.GyroMod.nativeIsInitialized(); }
    public static boolean nativeIsEnabled() { return org.levimc.launcher.core.mods.inbuilt.nativemod.GyroMod.nativeIsEnabled(); }
}
