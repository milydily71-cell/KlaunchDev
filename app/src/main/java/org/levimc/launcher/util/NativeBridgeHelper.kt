package org.levimc.launcher.util

import android.content.Context
import androidx.annotation.Keep
import com.kdroid.launcher.core.minecraft.LauncherApplication
import com.kdroid.launcher.R
import com.kdroid.launcher.util.NativeBridgeHelper as CurrentBridge

/**
 * JNI ABI compatibility facade for the original prebuilt libgxcore.so.
 * Keep the legacy JVM class and wrapper/native method signatures stable.
 */
@Keep
object NativeBridgeHelper {
    @JvmStatic
    fun getAppContext(): Context = LauncherApplication.context

    @JvmStatic
    fun ensureGxCoreLoaded(): Boolean = CurrentBridge.ensureGxCoreLoaded()

    @JvmStatic
    fun scanImage(inputPath: String): Boolean =
        CurrentBridge.ensureGxCoreLoaded() && nativeScanImage(inputPath)

    @JvmStatic
    fun rewriteImage(inputPath: String, outputPath: String): Boolean =
        CurrentBridge.ensureGxCoreLoaded() && nativeRewriteImage(inputPath, outputPath)

    @JvmStatic
    fun bootstrapGxCore(): Boolean {
        if (!CurrentBridge.ensureGxCoreLoaded()) return false
        return try {
            nativeBootstrapGxCore()
            true
        } catch (_: Throwable) {
            false
        }
    }

    @JvmStatic
    fun loadMinecraftRuntime(libraryPath: String) {
        if (!CurrentBridge.ensureGxCoreLoaded()) {
            throw UnsatisfiedLinkError(
                "Failed to load gxcore for the Minecraft runtime: ${CurrentBridge.getGxCoreLoadError()}"
            )
        }
        nativeLoadMinecraftRuntime(libraryPath)?.let { error ->
            throw UnsatisfiedLinkError(error)
        }
    }

    @JvmStatic
    fun getPrefs(name: String) = CurrentBridge.getPrefs(name)

    @JvmStatic
    fun getBoolean(prefsName: String, key: String, defaultValue: Boolean): Boolean =
        CurrentBridge.getBoolean(prefsName, key, defaultValue)

    @JvmStatic
    fun putBoolean(prefsName: String, key: String, value: Boolean) =
        CurrentBridge.putBoolean(prefsName, key, value)

    @JvmStatic
    fun getString(prefsName: String, key: String, defaultValue: String?): String? =
        CurrentBridge.getString(prefsName, key, defaultValue)

    @JvmStatic
    fun putString(prefsName: String, key: String, value: String) =
        CurrentBridge.putString(prefsName, key, value)

    @JvmStatic
    fun isLauncherManagedLoginEnabled(): Boolean =
        CurrentBridge.isLauncherManagedLoginEnabled()

    @JvmStatic
    fun showInvalidLicenseOverlay() = CurrentBridge.showInvalidLicenseOverlay()

    @JvmStatic
    private external fun nativeScanImage(inputPath: String): Boolean

    @JvmStatic
    private external fun nativeRewriteImage(inputPath: String, outputPath: String): Boolean

    @JvmStatic
    private external fun nativeBootstrapGxCore()

    @JvmStatic
    private external fun nativeLoadMinecraftRuntime(libraryPath: String): String?
}
