package com.skymobi.android.transport.protocol.esb.signal;

import com.skymobi.android.bean.esb.annotation.EsbField;
import com.skymobi.android.bean.esb.annotation.EsbSignal;

@EsbSignal(messageCode = 0x9811)
public class RegisterToAccessReq extends AbstractEsbT2ASignal {
    @EsbField(index = 1, fixedLength = 7)
    private byte[] protocolBegin = new byte[]{(byte) 0x8A, (byte) 0xED, (byte) 0x9C, (byte) 0xF3, 0x7E, 0x32, (byte) 0xB9};
    @EsbField(index = 2)
    private short srcModuleId;
    @EsbField(index = 3)
    private short accessModuleId = (short) 0x9800;
    @EsbField(index = 4)
    private short length;
    /**
     * 0x0          普通消息
       0x120        添加了序列号的消息
       0x420        普通加密消息
       0x520        加密加了序列号的消息
     */
    @EsbField(index = 5)
    private short flags = 0x0120;
    @EsbField(index = 6)
    private short seqNum = 1;
    @EsbField(index = 7)
    private short ack = 0;
    @EsbField(index = 8)
    private short msgCode = (short)0x9811;
    @EsbField(index = 9)
    private byte redirectTimes;
    @EsbField(index = 10)
    private byte hsmanLen = 3;
    @EsbField(index = 11, length = "hsmanLen")
    private String hsman = "aux";
    @EsbField(index = 12)
    private byte hstypeLen = 4;
    @EsbField(index = 13, length = "hstypeLen")
    private String hstype = "m267";
    @EsbField(index = 14)
    private int vmv = 0x07AD;
    @EsbField(index = 15)
    private int hsv = 0x00;
    @EsbField(index = 16)
    private short screenWidth = 0xF0;
    @EsbField(index = 17)
    private short screenHeight = 0x140;
    @EsbField(index = 18)
    private byte terminalFontWidth = 0x10;
    @EsbField(index = 19)
    private byte terminalFontHeight = 0x10;
    @EsbField(index = 20)
    private short mem = 0x0493;
    @EsbField(index = 21)
    private byte touch = 1;
    @EsbField(index = 22,fixedLength = 16)
    private String imei = "111111111111111";
    @EsbField(index = 23,fixedLength = 16)
    private String imsi = "222222222212123";
    @EsbField(index = 24)
    private int enter = 1;//OTA   必填
    @EsbField(index = 25)
    private int appId;
    @EsbField(index = 26)
    private byte plat = 4;//0:INVALID,  1:MTK, 	2: SPR, 3:VIA, 4:Android; 必填
    @EsbField(index = 27)
    private byte screenType = 1;//0:INVALID,  1:240320;   2:240400;
    @EsbField(index = 28)
    private byte reserved1;
    @EsbField(index = 29)
    private byte reserved2;
    @EsbField(index = 30)
    private short reserved3;  //必填    填应用版本号 
    @EsbField(index = 31)
    private int reserved4;
    
    public short getSrcModuleId() {
        return srcModuleId;
    }

    public void setSrcModuleId(short srcModuleId) {
        this.srcModuleId = srcModuleId;
    }

    public short getAccessModuleId() {
        return accessModuleId;
    }

    public void setAccessModuleId(short accessModuleId) {
        this.accessModuleId = accessModuleId;
    }

//    public short getLength() {
//        return length;
//    }

    public void setLength(short length) {
        this.length = length;
    }

    public String getHsman() {
        return hsman;
    }

    public void setHsman(String hsman) {
        this.hsman = hsman;
        this.hsmanLen = (byte)hsman.length();
    }

    public byte getHsmanLen() {
        return (byte) hsman.length();
    }

    public void setHsmanLen(byte hsmanLen) {
        this.hsmanLen = hsmanLen;
    }

    public byte[] getProtocolBegin() {
        return protocolBegin;
    }

    public void setProtocolBegin(byte[] protocolBegin) {
        this.protocolBegin = protocolBegin;
    }


    public void setFlags(short flags) {
        this.flags = flags;
    }

    public void setSeqNum(int seqNum) {
        this.seqNum = (short) seqNum;
    }

    public short getAck() {
        return ack;
    }

    public void setAck(short ack) {
        this.ack = ack;
    }

    public short getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(short msgCode) {
        this.msgCode = msgCode;
    }

    public byte getRedirectTimes() {
        return redirectTimes;
    }

    public void setRedirectTimes(byte redirectTimes) {
        this.redirectTimes = redirectTimes;
    }

    public byte getHstypeLen() {
        return hstypeLen;
    }

    public void setHstypeLen(byte hstypeLen) {
        this.hstypeLen = hstypeLen;
    }

    public String getHstype() {
        return hstype;
    }

    public void setHstype(String hstype) {
        this.hstype = hstype;
        this.hstypeLen = (byte)hstype.length();
    }

    public int getVmv() {
        return vmv;
    }

    public void setVmv(int vmv) {
        this.vmv = vmv;
    }

    public int getHsv() {
        return hsv;
    }

    public void setHsv(int hsv) {
        this.hsv = hsv;
    }

    public short getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(short screenWidth) {
        this.screenWidth = screenWidth;
    }

    public short getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(short screenHeight) {
        this.screenHeight = screenHeight;
    }

    public short getMem() {
        return mem;
    }

    public void setMem(short mem) {
        this.mem = mem;
    }

    public byte getTouch() {
        return touch;
    }

    public void setTouch(byte touch) {
        this.touch = touch;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public int getEnter() {
        return enter;
    }

    public void setEnter(int enter) {
        this.enter = enter;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public short getPlat() {
        return plat;
    }

    public void setPlat(byte plat) {
        this.plat = plat;
    }

    public byte getScreenType() {
        return screenType;
    }

    public void setScreenType(byte screenType) {
        this.screenType = screenType;
    }

    /**
     * @return the reserved1
     */
    public short getReserved1() {
        return reserved1;
    }

    /**
     * @param reserved1 the reserved1 to set
     */
    public void setReserved1(byte reserved1) {
        this.reserved1 = reserved1;
    }

    /**
     * @return the reserved2
     */
    public byte getReserved2() {
        return reserved2;
    }

    /**
     * @param reserved2 the reserved2 to set
     */
    public void setReserved2(byte reserved2) {
        this.reserved2 = reserved2;
    }

    /**
     * @return the reserved3
     */
    public short getReserved3() {
        return reserved3;
    }

    /**
     * @param reserved3 the reserved3 to set
     */
    public void setReserved3(short reserved3) {
        this.reserved3 = reserved3;
    }

    /**
     * @return the reserved4
     */
    public int getReserved4() {
        return reserved4;
    }

    /**
     * @param reserved4 the reserved4 to set
     */
    public void setReserved4(int reserved4) {
        this.reserved4 = reserved4;
    }

}
