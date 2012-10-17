
package android.skymobi.messenger.net.client.bean;

/**
 * @ClassName: DeviceInfo
 * @Description: TODO
 * @author Michael.Pan
 * @date 2012-2-8 下午04:06:10
 */
public class NetUserAgent {
    public NetUserAgent() {

    }

    // 厂商
    public String product;
    // 机型
    public String modle;
    // 手机系统版本
    public short sdk;
    // IMSI
    public String imsi;
    // IMEI
    public String imei;
    // screen width
    public int screenWidth;
    // screen height
    public int screenHeight;
    // 总内存大小
    public long totalMem;
}
