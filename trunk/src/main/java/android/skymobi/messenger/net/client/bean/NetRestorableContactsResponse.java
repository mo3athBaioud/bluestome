package android.skymobi.messenger.net.client.bean;

import android.skymobi.messenger.net.beans.commons.RestorableContacts;

import java.util.ArrayList;

/**
 * @ClassName: NetRestorableContactsResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-8-13 下午04:53:13
 */
public class NetRestorableContactsResponse extends NetResponse {

    private int totalSize;
    
    private int start;
    
    private ArrayList<RestorableContacts> restorableContacts = new ArrayList<RestorableContacts>();

    /**
     * @return the totalSize
     */
    public int getTotalSize() {
        return totalSize;
    }

    /**
     * @param totalSize the totalSize to set
     */
    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    /**
     * @return the start
     */
    public int getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * @return the restorableContacts
     */
    public ArrayList<RestorableContacts> getRestorableContacts() {
        return restorableContacts;
    }

    /**
     * @param restorableContacts the restorableContacts to set
     */
    public void setRestorableContacts(ArrayList<RestorableContacts> restorableContacts) {
        this.restorableContacts = restorableContacts;
    }
    
    
}
