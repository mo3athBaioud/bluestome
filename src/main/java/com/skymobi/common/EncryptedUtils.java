package com.skymobi.common;



public class EncryptedUtils {

    public native static void init(byte[] in);

    public native static byte[] encode(byte[] key, byte[] in);

    public native static byte[] decode(byte[] key, byte[] in);

    static {
        System.loadLibrary("aio");
    }

}
