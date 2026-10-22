package org.levimc.launcher.core.mods.inbuilt.nativemod;

import androidx.annotation.Keep;

/** JNI compatibility declarations for prebuilt libinbuiltmods.so. */
@Keep
public class AutoSprintMod {
    public static native boolean nativeInit();
    public static native void nativeSetEnabled(boolean enabled);
    public static native boolean nativeIsEnabled();
}
