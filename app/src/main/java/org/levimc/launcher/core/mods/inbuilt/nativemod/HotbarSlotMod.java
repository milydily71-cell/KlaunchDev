package org.levimc.launcher.core.mods.inbuilt.nativemod;

import androidx.annotation.Keep;

/** JNI compatibility declarations for prebuilt libinbuiltmods.so. */
@Keep
public class HotbarSlotMod {
    public static native boolean nativeInit();
    public static native void nativeSetOverlayEnabled(boolean enabled);
    public static native int nativeGameplayVisibilityState();
    public static native boolean nativeItemIconsAvailable();
    public static native void nativeCopyItemCounts(int[] destination);
    public static native void nativeSetEnabled(boolean enabled);
    public static native void nativeSetItemIconsEnabled(boolean enabled);
    public static native void nativeSetSlotState(int slot, float x, float y, float width, float height,
                                                   float surfaceWidth, float surfaceHeight, float alpha,
                                                   boolean visible, boolean pressed);
    public static native boolean nativeHasItem(int slot);
    public static native int nativeGetItemCount(int slot);
    public static native void nativeClearSlot(int slot);
}
