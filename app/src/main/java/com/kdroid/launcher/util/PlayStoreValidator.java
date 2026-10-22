package com.kdroid.launcher.util;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Minecraft presence checks.
 *
 * Installing Minecraft outside Google Play is supported. Installer provenance is not
 * a reliable compatibility or license signal, so launch eligibility must be based on
 * the selected version/runtime being usable rather than the installer package name.
 */
public final class PlayStoreValidator {
    private static final String MINECRAFT_PACKAGE_NAME = "com.mojang.minecraftpe";

    private PlayStoreValidator() {
    }

    /**
     * Legacy-compatible method name. Returns whether the official Minecraft package
     * is installed, regardless of which installer supplied it.
     */
    public static boolean isMinecraftFromPlayStore(Context context) {
        return isMinecraftInstalled(context);
    }

    public static boolean isMinecraftInstalled(Context context) {
        try {
            context.getPackageManager().getPackageInfo(MINECRAFT_PACKAGE_NAME, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Legacy-compatible method name; this does not perform a Google Play license check.
     */
    public static boolean isLicenseVerified(Context context) {
        return isMinecraftInstalled(context);
    }
}
