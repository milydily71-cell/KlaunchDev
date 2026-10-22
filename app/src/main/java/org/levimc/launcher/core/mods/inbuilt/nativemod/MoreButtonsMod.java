package org.levimc.launcher.core.mods.inbuilt.nativemod;

import androidx.annotation.Keep;

/** JNI compatibility declarations for prebuilt libinbuiltmods.so. */
@Keep
public class MoreButtonsMod {
    public static native boolean nativeInit();
    public static native void nativeSendKey(int keyCode, boolean down);
}
