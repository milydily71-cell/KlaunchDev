package org.levimc.launcher.core.mods.inbuilt.nativemod;

import androidx.annotation.Keep;

/** JNI compatibility declarations for prebuilt libinbuiltmods.so. */
@Keep
public class SnaplookMod {
    public static native boolean nativeInit();
    public static native void nativeOnKeyDown();
    public static native void nativeOnKeyUp();
}
