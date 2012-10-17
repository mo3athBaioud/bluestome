package com.skymobi.android.transport.protocol.esb.signal;

import com.skymobi.android.bean.esb.annotation.EsbField;
import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.transport.protocol.esb.AbstractAccessHeaderable;

/**
 * 重连到ACCESS响应
 * @ClassName: RetryToAccessResp
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-24 下午02:05:35
 */
@EsbSignal(messageCode=0x9808)
public class RetryToAccessResp extends AbstractAccessHeaderable {
    
    @EsbField(index = 8)
    private byte    result = 0;

    public short getResult() {
        return result;
    }

    public void setResult(byte result) {
        this.result = result;
    }

}
