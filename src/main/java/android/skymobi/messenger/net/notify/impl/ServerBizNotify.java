
package android.skymobi.messenger.net.notify.impl;

import android.skymobi.messenger.net.notify.IServerBizNotify;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 网络层内部通知接口(废弃)
 * @ClassName: ServerBizNotify
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-8 下午08:01:52
 */
public class ServerBizNotify implements IServerBizNotify {

    private boolean rec = false;

    private final long fetchSignalTimeout = 1000l;

    protected BlockingQueue<Object> pendings = new LinkedBlockingQueue<Object>(1000);

    /**
     * 是否已经触发获取结果
     */
    public boolean fire() {
        return rec;
    }

    /**
     * 添加响应结果
     */
    public synchronized void add(Object value) {
        System.out.println(" > ServerBizNotify.add:" + value);
        pendings.add(value);
        rec = true;
    }

    /**
     * 获取响应结果
     */
    public synchronized Object get() {
        Object bean = null;
        try {
            bean = pendings.poll(fetchSignalTimeout, TimeUnit.MILLISECONDS);
            rec = false;
        } catch (Exception e) {
        }
        return bean;
    }

    public boolean isRec() {
        return rec;
    }

    public void setRec(boolean rec) {
        this.rec = rec;
    }

    public BlockingQueue<Object> getPendings() {
        return pendings;
    }

    public void setPendings(BlockingQueue<Object> pendings) {
        this.pendings = pendings;
    }

    public long getFetchSignalTimeout() {
        return fetchSignalTimeout;
    }

}
