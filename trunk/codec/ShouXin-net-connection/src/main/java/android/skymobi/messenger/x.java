package android.skymobi.messenger;


/**
 * @ClassName: x
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-5-17 下午05:56:34
 */
public class x {
    public native static void i(byte[] in);

    public native static byte[] e(byte[] key, byte[] in);

    public native static byte[] d(byte[] key, byte[] in);

    static {
        System.loadLibrary("aio");
    }

}
