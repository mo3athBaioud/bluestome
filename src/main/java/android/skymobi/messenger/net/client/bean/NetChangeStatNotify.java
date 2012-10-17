package android.skymobi.messenger.net.client.bean;

import android.skymobi.messenger.net.beans.commons.StateSt;

import java.util.ArrayList;

/**
 * 状态更新通知
 * @ClassName: NetChangeStatNotify
 * @Description: TODO
 * @author bluestome.zhang
 * @date 2012-2-16 下午01:49:19
 */
public class NetChangeStatNotify extends NetNotify {
    
    private int seqId;
    private short count;
    private ArrayList<StateSt> stateSts = new ArrayList<StateSt>();
    private short state;
    private short detailState;
    private long skyid;
    private int frdSkyId;
    /**
     * @return the seqId
     */
    public int getSeqId() {
        return seqId;
    }
    /**
     * @param seqId the seqId to set
     */
    public void setSeqId(int seqId) {
        this.seqId = seqId;
    }
    /**
     * @return the count
     */
    public short getCount() {
        return count;
    }
    /**
     * @param count the count to set
     */
    public void setCount(short count) {
        this.count = count;
    }
    /**
     * @return the stateSts
     */
    public ArrayList<StateSt> getStateSts() {
        return stateSts;
    }
    /**
     * @param stateSts the stateSts to set
     */
    public void setStateSts(ArrayList<StateSt> stateSts) {
        this.stateSts = stateSts;
    }
    /**
     * @return the state
     */
    public short getState() {
        return state;
    }
    /**
     * @param state the state to set
     */
    public void setState(short state) {
        this.state = state;
    }
    /**
     * @return the detailState
     */
    public short getDetailState() {
        return detailState;
    }
    /**
     * @param detailState the detailState to set
     */
    public void setDetailState(short detailState) {
        this.detailState = detailState;
    }
    /**
     * @return the skyid
     */
    public long getSkyid() {
        return skyid;
    }
    /**
     * @param skyid the skyid to set
     */
    public void setSkyid(long skyid) {
        this.skyid = skyid;
    }
    /**
     * @return the frdSkyId
     */
    public int getFrdSkyId() {
        return frdSkyId;
    }
    /**
     * @param frdSkyId the frdSkyId to set
     */
    public void setFrdSkyId(int frdSkyId) {
        this.frdSkyId = frdSkyId;
    }
    
    

}
