package org.levimc.launcher.core.mods.inbuilt.nativemod;

import androidx.annotation.Keep;

/** JNI compatibility declarations for prebuilt libinbuiltmods.so. */
@Keep
public class ZoomMod {
    public static native boolean nativeInit();
    public static native void nativeOnKeyDown();
    public static native void nativeOnKeyUp();
    public static native void nativeOnScroll(float delta);
    public static native void nativeSetZoomLevel(long level);
    public static native void nativeSetTransitionDuration(int duration);
}
