
package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import java.util.ArrayList;

/**
 * 彻底删除联系人请求
 * 
 * @author Bluestome.Zhang
 */
@EsbSignal(messageCode = 0xA881)
public class SxCompleteDeleteContactsReq extends ShouxinReqHeader {

    @TLVAttribute(tag = 10000073, description = "可恢复联系人的ID")
    private ArrayList<Integer> restoreIds = new ArrayList<Integer>();

    public void setRestoreIds(ArrayList<Integer> restoreIds) {
        this.restoreIds = restoreIds;
    }

    public ArrayList<Integer> getRestoreIds() {
        return restoreIds;
    }
}
