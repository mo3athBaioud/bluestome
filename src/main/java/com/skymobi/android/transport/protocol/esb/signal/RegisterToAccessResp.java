package com.skymobi.android.transport.protocol.esb.signal;

import com.skymobi.android.bean.esb.annotation.EsbField;
import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.transport.protocol.esb.AbstractAccessHeaderable;

@EsbSignal(messageCode = 0x9804)
public class RegisterToAccessResp extends AbstractAccessHeaderable {
    
    @EsbField(index = 8)
	private byte	result = 0;
	@EsbField(index = 9,description="接入服务索引值,用于重连请求")
	private int accessIndex = 0;
    @EsbField(index = 10,description="接入服务验证码,用于重连请求")
	private int authCode = 0;
    @EsbField(index = 11)
	private int ownIPAddr = 0;
    @EsbField(index = 12)
	private short ownPort;
    @EsbField(index = 13,description="服务端返回的加密密钥")
    private int encryptKey;

    public short getResult() {
        return result;
    }

    public void setResult(byte result) {
        this.result = result;
    }

    public int getAccessIndex() {
        return accessIndex;
    }

    public void setAccessIndex(int accessIndex) {
        this.accessIndex = accessIndex;
    }

    public int getAuthCode() {
        return authCode;
    }

    public void setAuthCode(int authCode) {
        this.authCode = authCode;
    }

    public int getOwnIPAddr() {
        return ownIPAddr;
    }

    public void setOwnIPAddr(int ownIPAddr) {
        this.ownIPAddr = ownIPAddr;
    }

    public short getOwnPort() {
        return ownPort;
    }

    public void setOwnPort(short ownPort) {
        this.ownPort = ownPort;
    }
    public int getEncryptKey() {
        return encryptKey;
    }

    public void setEncryptKey(int encryptKey) {
        this.encryptKey = encryptKey;
    }
}

