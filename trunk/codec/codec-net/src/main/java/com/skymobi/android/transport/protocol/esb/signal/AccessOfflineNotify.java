package com.skymobi.android.transport.protocol.esb.signal;

import com.skymobi.android.bean.esb.annotation.EsbField;
import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.transport.protocol.esb.AbstractAccessHeaderable;

/**
 * ACCESS推送的离线消息通知
 * @ClassName: SxOfflineNotify
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-3-12 上午11:12:39
 */
@EsbSignal(messageCode=0x9815)
public class AccessOfflineNotify extends AbstractAccessHeaderable {
    
    @EsbField(index = 8)
    private byte result = 0;

    /**
     * @return the result
     */
    public short getResult() {
        return result;
    }


    /**
     * @param result the result to set
     */
    public void setResult(byte result) {
        this.result = result;
    }

    
}
