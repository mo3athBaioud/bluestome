
package com.example.devinfodemo.json.bean;

public class Phone extends BaseJson implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    // 客户端硬件信息
    private Hardware hardware;
    // 客户端软件信息
    private Software software;

    public Hardware getHardware() {
        return hardware;
    }

    public void setHardware(Hardware hardware) {
        this.hardware = hardware;
    }

    public Software getSoftware() {
        return software;
    }

    public void setSoftware(Software software) {
        this.software = software;
    }

}
