
package com.example.devinfodemo.json.bean;

import com.example.devinfodemo.json.bean.hd.PhoneNetworkInfo;
import com.example.devinfodemo.json.bean.hd.JSDInfo;
import com.example.devinfodemo.json.bean.hd.JSensor;

public class Hardware {

    // 基本配置信息
    private int cameraNum = 0;
    private String imsi = "0000000000000000";
    private String imei = "0000000000000000";

    // 硬件信息
    private JSensor sersor;
    private PhoneNetworkInfo network;
    private JSDInfo sdInfo;

    /**
     * @return the cameraNum
     */
    public int getCameraNum() {
        return cameraNum;
    }

    /**
     * @param cameraNum the cameraNum to set
     */
    public void setCameraNum(int cameraNum) {
        this.cameraNum = cameraNum;
    }

    /**
     * @return the imsi
     */
    public String getImsi() {
        return imsi;
    }

    /**
     * @param imsi the imsi to set
     */
    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    /**
     * @return the imei
     */
    public String getImei() {
        return imei;
    }

    /**
     * @param imei the imei to set
     */
    public void setImei(String imei) {
        this.imei = imei;
    }

    /**
     * @return the sersor
     */
    public JSensor getSersor() {
        return sersor;
    }

    /**
     * @param sersor the sersor to set
     */
    public void setSersor(JSensor sersor) {
        this.sersor = sersor;
    }

    /**
     * @return the network
     */
    public PhoneNetworkInfo getNetwork() {
        return network;
    }

    /**
     * @param network the network to set
     */
    public void setNetwork(PhoneNetworkInfo network) {
        this.network = network;
    }

    /**
     * @return the sdInfo
     */
    public JSDInfo getSdInfo() {
        return sdInfo;
    }

    /**
     * @param sdInfo the sdInfo to set
     */
    public void setSdInfo(JSDInfo sdInfo) {
        this.sdInfo = sdInfo;
    }

}
