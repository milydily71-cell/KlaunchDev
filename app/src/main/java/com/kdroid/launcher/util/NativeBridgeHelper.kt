package com.kdroid.launcher.util

import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import android.os.Looper
import org.json.JSONObject
import com.kdroid.launcher.core.minecraft.LauncherApplication
import androidx.core.content.edit
import android.widget.Toast
import android.view.Gravity
import com.kdroid.launcher.R
import androidx.annotation.Keep

@Keep
object NativeBridgeHelper {
    @Volatile
    private var gxcoreLoaded = false

    @JvmStatic
    fun getAppContext(): Context {
        return LauncherApplication.context
    }

    @Volatile
    private var gxcoreLoadError: String? = null

    @JvmStatic
    fun ensureGxCoreLoaded(): Boolean {
        if (gxcoreLoaded) return true
        return synchronized(this) {
            if (gxcoreLoaded) return@synchronized true

            var firstError: Throwable? = null
            try {
                // libgxcore.so was compiled against this legacy JNI class name.
                // Resolve it before loading so JNI_OnLoad can register against the app class loader.
                Class.forName(
                    "org.levimc.launcher.util.NativeBridgeHelper",
                    true,
                    getAppContext().classLoader
                )
                System.loadLibrary("gxcore")
                gxcoreLoaded = true
                gxcoreLoadError = null
                return@synchronized true
            } catch (t: Throwable) {
                firstError = t
                android.util.Log.e("KLauncher-NativeBridge", "First gxcore load failed", t)
            }

            // Some Android package/linker combinations resolve bundled native libraries
            // more reliably by their explicit installed path.
            try {
                val nativeDir = getAppContext().applicationInfo.nativeLibraryDir
                val library = java.io.File(nativeDir, "libgxcore.so")
                if (!library.isFile || library.length() == 0L) {
                    throw UnsatisfiedLinkError("Bundled library missing: ${library.absolutePath}")
                }
                System.load(library.absolutePath)
                gxcoreLoaded = true
                gxcoreLoadError = null
                true
            } catch (fallbackError: Throwable) {
                android.util.Log.e("KLauncher-NativeBridge", "Absolute-path gxcore load failed", fallbackError)
                val firstMessage = firstError?.let { "${it.javaClass.simpleName}: ${it.message}" }
                    ?: "unknown loadLibrary error"
                val fallbackMessage = "${fallbackError.javaClass.simpleName}: ${fallbackError.message}"
                gxcoreLoadError = "Initial loadLibrary failure: $firstMessage; absolute-path retry: $fallbackMessage. JNI_OnLoad failures are not repaired by retrying the same binary; verify legacy JNI class names, registered method names/signatures, and dependent libraries."
                false
            }
        }
    }

    @JvmStatic
    fun getGxCoreLoadError(): String {
        return gxcoreLoadError ?: "No detailed gxcore loader error available"
    }

    @JvmStatic
    fun scanImage(inputPath: String): Boolean {
        if (!ensureGxCoreLoaded()) {
            return false
        }
        return org.levimc.launcher.util.NativeBridgeHelper.nativeScanImage(inputPath)
    }

    @JvmStatic
    fun rewriteImage(inputPath: String, outputPath: String): Boolean {
        if (!ensureGxCoreLoaded()) {
            return false
        }
        return org.levimc.launcher.util.NativeBridgeHelper.nativeRewriteImage(inputPath, outputPath)
    }

    @JvmStatic
    fun bootstrapGxCore(): Boolean {
        if (!ensureGxCoreLoaded()) {
            return false
        }
        return try {
            org.levimc.launcher.util.NativeBridgeHelper.nativeBootstrapGxCore()
            true
        } catch (_: Throwable) {
            false
        }
    }

    @JvmStatic
    fun loadMinecraftRuntime(libraryPath: String) {
        if (!ensureGxCoreLoaded()) {
            throw UnsatisfiedLinkError("Failed to load gxcore for the Minecraft runtime: ${getGxCoreLoadError()}")
        }
        org.levimc.launcher.util.NativeBridgeHelper.nativeLoadMinecraftRuntime(libraryPath)?.let { error ->
            throw UnsatisfiedLinkError(error)
        }
    }

    @JvmStatic
    fun getPrefs(name: String): SharedPreferences {
        return getAppContext().getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    @JvmStatic
    fun getBoolean(prefsName: String, key: String, defaultValue: Boolean = false): Boolean {
        return try {
            getPrefs(prefsName).getBoolean(key, defaultValue)
        } catch (t: Throwable) {
            defaultValue
        }
    }

    @JvmStatic
    fun putBoolean(prefsName: String, key: String, value: Boolean) {
        try {
            getPrefs(prefsName).edit { putBoolean(key, value) }
        } catch (_: Throwable) {
        }
    }

    @JvmStatic
    fun getString(prefsName: String, key: String, defaultValue: String? = null): String? {
        return try {
            getPrefs(prefsName).getString(key, defaultValue)
        } catch (t: Throwable) {
            defaultValue
        }
    }

    @JvmStatic
    fun putString(prefsName: String, key: String, value: String) {
        try {
            getPrefs(prefsName).edit { putString(key, value) }
        } catch (_: Throwable) {
        }
    }

    @JvmStatic
    fun isLauncherManagedLoginEnabled(): Boolean {
        return try {
            val json = getString("feature_settings", "settings_json", null)
            if (!json.isNullOrEmpty()) {
                val obj = JSONObject(json)
                obj.optBoolean("launcherManagedMcLoginEnabled", false)
            } else {
                false
            }
        } catch (t: Throwable) {
            false
        }
    }

    @JvmStatic
    fun showInvalidLicenseOverlay() {
        val ctx = getAppContext()
        try {
            val handler = Handler(Looper.getMainLooper())
            handler.post {
                Toast.makeText(
                    ctx,
                    ctx.getString(R.string.invalid_license_detected_toast),
                    Toast.LENGTH_LONG
                ).apply {
                    setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 100)
                    show()
                }
            }
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }

}
