
package android.skymobi.messenger.net.notify;

/**
 * 服务端业务通知接口
 * 
 * @ClassName: IServerBizNotify
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-7 下午05:09:06
 */
public interface IServerBizNotify {

    /**
     * 触发获取
     */
    boolean fire();

    /**
     * 添加响应对象
     * 
     * @param value
     */
    void add(Object value);

    /**
     * 从队列中获取响应对象
     * 
     * @return
     */
    Object get();

}
