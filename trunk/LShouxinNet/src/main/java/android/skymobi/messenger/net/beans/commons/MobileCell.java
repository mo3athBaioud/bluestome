package android.skymobi.messenger.net.beans.commons;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * @ClassName: MobileCell 移动网基站对象
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-6-20 下午05:02:06
 */
public class MobileCell {
    
    /**
     * 基站
     * 必传
     */
    @TLVAttribute(tag=34010002,description="基站")
    private String cellId;
    
    /**
     * 基站地区code
     * 必传
     */
    @TLVAttribute(tag=34010003,description="基站地区code")
    private int lac;
    
    /**
     * 运营商代码
     * 必传
     */
    @TLVAttribute(tag=34010004,description="运营商代码")
    private byte mnc;
    
    /**
     * 国家代码
     */
    @TLVAttribute(tag=34010005,description="国家代码")
    private short mcc;
    
    /**
     * 信号强度
     */
    @TLVAttribute(tag=34010006,description="信号强度")
    private short signalStrength;
    
    /**
     * 离基站距离
     */
    @TLVAttribute(tag=34010007,description="离基站距离")
    private byte timingAdvance;

    /**
     * @return the cellId
     */
    public String getCellId() {
        return cellId;
    }

    /**
     * @param cellId the cellId to set
     */
    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    /**
     * @return the lac
     */
    public int getLac() {
        return lac;
    }

    /**
     * @param lac the lac to set
     */
    public void setLac(int lac) {
        this.lac = lac;
    }

    /**
     * @return the mnc
     */
    public byte getMnc() {
        return mnc;
    }

    /**
     * @param mnc the mnc to set
     */
    public void setMnc(byte mnc) {
        this.mnc = mnc;
    }

    /**
     * @return the mcc
     */
    public short getMcc() {
        return mcc;
    }

    /**
     * @param mcc the mcc to set
     */
    public void setMcc(short mcc) {
        this.mcc = mcc;
    }

    /**
     * @return the signalStrength
     */
    public short getSignalStrength() {
        return signalStrength;
    }

    /**
     * @param signalStrength the signalStrength to set
     */
    public void setSignalStrength(short signalStrength) {
        this.signalStrength = signalStrength;
    }

    /**
     * @return the timingAdvance
     */
    public byte getTimingAdvance() {
        return timingAdvance;
    }

    /**
     * @param timingAdvance the timingAdvance to set
     */
    public void setTimingAdvance(byte timingAdvance) {
        this.timingAdvance = timingAdvance;
    }
    
}
