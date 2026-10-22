package org.levimc.launcher.core.minecraft

import androidx.annotation.Keep

/**
 * JNI compatibility entry point for the prebuilt LeviLauncher libgxcore.so.
 *
 * The native binary registers nativeSetupRuntime against the original
 * org.levimc.launcher.core.minecraft.MinecraftRuntimePreparer class. Keep this
 * class at that exact JVM name even though the Android app ID and main sources
 * now use com.kdroid.launcher.
 */
@Keep
object MinecraftRuntimePreparer {
    @JvmStatic
    @JvmName("nativeSetupRuntime")
    private external fun nativeSetupRuntime(modsPath: String)
}
