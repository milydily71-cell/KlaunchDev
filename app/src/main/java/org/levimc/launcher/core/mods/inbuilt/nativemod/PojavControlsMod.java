package org.levimc.launcher.core.mods.inbuilt.nativemod;

import androidx.annotation.Keep;

/** JNI compatibility declarations for prebuilt libinbuiltmods.so. */
@Keep
public class PojavControlsMod {
    public static native void nativeSendKey(int keyCode, boolean down);
    public static native void nativeSendMouseButton(int androidButton, boolean down);
    public static native void nativeSendScroll(float vertical);
    public static native void nativeSendLookDelta(float deltaX, float deltaY);
    public static native void nativeSendPointer(float x, float y);
    public static native boolean nativeInit();
    public static native void nativeSetEnabled(boolean enabled);
}
