package com.skymobi.android.transport.protocol.esb.signal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import com.skymobi.android.bean.esb.annotation.EsbField;
import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.transport.protocol.esb.AbstractAccessHeaderable;

/**
 * 自定义0x98041做为UA重定向的messageCode,方便解码使用,access不会真正返回这个messageCode
 */
@EsbSignal(messageCode = 0x98042)
public class RegisterToAccessRedirectResp extends AbstractAccessHeaderable {

    @EsbField(index = 8)
    private byte result = 0;

    //ua_redirect
    @EsbField(index = 9)
    private int newAccessAddress;
    @EsbField(index = 10)
    private short newAccessPort;
    @EsbField(index = 11)
    private short reserved;

    public InetSocketAddress getNewAccessNetAddress() {
        byte[] bytes = BigInteger.valueOf(newAccessAddress).toByteArray();
        try {
            return new InetSocketAddress(InetAddress.getByAddress(bytes), newAccessPort);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("can't resolve new ip address");
    }

    public short getResult() {
        return result;
    }

    public void setResult(byte result) {
        this.result = result;
    }

    public int getNewAccessAddress() {
        return newAccessAddress;
    }

    public void setNewAccessAddress(int newAccessAddress) {
        this.newAccessAddress = newAccessAddress;
    }

    public short getNewAccessPort() {
        return newAccessPort;
    }

    public void setNewAccessPort(short newAccessPort) {
        this.newAccessPort = newAccessPort;
    }

    public short getReserved() {
        return reserved;
    }

    public void setReserved(short reserved) {
        this.reserved = reserved;
    }

    @Override
    public String toString() {
        return "RegisterToAccessRedirectResp{" +
                "getNewAccessNetAddress=" + getNewAccessNetAddress() +
                '}';
    }
}