
package android.skymobi.messenger.net.client.bean;

/**
 * @ClassName: NetCompareTerminalUIDResp
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-10-25 下午03:15:41
 */
public class NetCompareTerminalUIDResp extends NetResponse {

    private byte result = -1;
    private boolean isSame;

    /**
     * @return the result
     */
    public byte getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(byte result) {
        this.result = result;
    }

    /**
     * @return the isSame
     */
    public boolean isSame() {
        return result == 1 ? true : false;
    }

}
