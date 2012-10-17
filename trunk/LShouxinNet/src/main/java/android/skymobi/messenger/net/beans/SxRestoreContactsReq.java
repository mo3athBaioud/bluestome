package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import java.util.List;

/**
 * @ClassName: SxRestoreContactsReq
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-8-14 上午10:53:12
 */
@EsbSignal(messageCode=0xA865)
public class SxRestoreContactsReq extends ShouxinReqHeader {

    @TLVAttribute(tag=10000073,description="可恢复联系人ID的List")
    private List<Integer> restoreIds;

    /**
     * @return the restoreIds
     */
    public List<Integer> getRestoreIds() {
        return restoreIds;
    }

    /**
     * @param restoreIds the restoreIds to set
     */
    public void setRestoreIds(List<Integer> restoreIds) {
        this.restoreIds = restoreIds;
    }
    
}
