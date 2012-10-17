package com.skymobi.android.util;

public class EncryptedUtils {

    static {
        //System.out.println(System.getProperty("java.library.path"));
        System.loadLibrary("access_codec");
        init("$*GFX:LKQC#$)MT08bsd~)/".getBytes());
    }

    public native static void init(byte[] key);

    public native static byte[] encode(byte[] key, byte[] in);

    public native static byte[] decode(byte[] key, byte[] in);
}
