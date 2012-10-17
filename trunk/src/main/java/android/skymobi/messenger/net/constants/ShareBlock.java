
package android.skymobi.messenger.net.constants;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 共享区块
 * @ClassName: ShareBlock
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-8 下午07:58:57
 */
public class ShareBlock {

    private static BlockingQueue<Object> pendings = new LinkedBlockingQueue<Object>(1000);
    //按照每个请求生成对应的Block队列
    @SuppressWarnings("rawtypes")
    private static HashMap<Class, BlockingQueue<Object>> pendings2 = new HashMap<Class, BlockingQueue<Object>>();
    
    public static void add(Object value) {
        pendings.add(value);
    }

    public static Object get() {
        Object bean = null;
        try {
            long fetchSignalTimeout = 1000L;
            bean = pendings.poll(fetchSignalTimeout, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 清理队列
     */
    public static void clear() {
        if (null != pendings && pendings.size() > 0) {
            pendings.clear();
        }
        if(null != pendings2 && pendings2.size() > 0){
            pendings2.clear();
        }
    }
    
    /**
     * 设置对象MAP中的队列数据
     * @param cls
     * @param value
     */
    public static void add(Class<?> cls,Object value){
        BlockingQueue<Object> blockingQuene = pendings2.get(cls);
        if(null == blockingQuene){
            blockingQuene = new LinkedBlockingQueue<Object>(100);
            pendings2.put(cls, blockingQuene);
        }
        blockingQuene.add(value);
    }

    /**
     * 从HASHMAP中获取指定CLASS的队列，然后从队列中获取最新的响应数据
     * @param cls
     * @return
     */
    public static Object get(Class<?> cls){
        Object bean = null;
        try{
            long fetchSignalTimeout = 100L;
            BlockingQueue<Object> blockingQuene = pendings2.get(cls);
            if(null != blockingQuene && blockingQuene.size() > 0){
                bean = blockingQuene.poll(fetchSignalTimeout, TimeUnit.MILLISECONDS);
            }else if(null == blockingQuene){
                blockingQuene = new LinkedBlockingQueue<Object>(100);
                pendings2.put(cls, blockingQuene);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return bean;
    }
}
