package org.levimc.launcher.core.mods.inbuilt.nativemod;

import androidx.annotation.Keep;

/** JNI compatibility declarations for prebuilt libinbuiltmods.so. */
@Keep
public class FpsMod {
    public static native boolean nativeInit();
    public static native int nativeGetFps();
    public static native boolean nativeIsInitialized();
}
