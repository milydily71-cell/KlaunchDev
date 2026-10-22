package org.levimc.launcher.util

import android.content.Context
import androidx.annotation.Keep
import com.kdroid.launcher.core.minecraft.LauncherApplication
import com.kdroid.launcher.util.NativeBridgeHelper as CurrentBridge

/**
 * Binary-compatibility bridge for the prebuilt libgxcore.so.
 *
 * The native library was built against the original Java package name. Keep this
 * class and its JNI entry points so JNI_OnLoad can find/register them after the
 * launcher application package migration to com.kdroid.launcher.
 */
@Keep
object NativeBridgeHelper {
    @JvmStatic
    fun getAppContext(): Context = LauncherApplication.context

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
    external fun nativeScanImage(inputPath: String): Boolean

    @JvmStatic
    external fun nativeRewriteImage(inputPath: String, outputPath: String): Boolean

    @JvmStatic
    external fun nativeBootstrapGxCore()

    @JvmStatic
    external fun nativeLoadMinecraftRuntime(libraryPath: String): String?
}
