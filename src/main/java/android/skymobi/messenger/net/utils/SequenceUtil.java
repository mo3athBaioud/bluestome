package android.skymobi.messenger.net.utils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生成序列号的工具单例类
 * @ClassName: SequenceUtil
 * @Description: 
 * 起始值默认为100，默认如果当前的值超过Integer.MAX_VALUE[0x7fffffff],则会将当前的值设置为默认值[1]
 * @author Bluestome.Zhang
 * @date 2012-9-13 上午09:56:02
 */
public class SequenceUtil {
    
    int defaultValue = 1;
    
    int maxValue =  Integer.MAX_VALUE;
    
    public static SequenceUtil instance = null;
    
    AtomicInteger atomic = null;
    
    /**
     * 私有构造函数
     * <p>Title: </p> 
     * <p>Description: </p>
     */
    private SequenceUtil(){
        //设置起始值为100
        atomic = new AtomicInteger(defaultValue);
    }
    
    /**
     * 无参获取实例方法
     * @return
     */
    public static SequenceUtil getInstance(){
        if(null == instance){
            instance = new SequenceUtil(); 
        }
        return instance;
    }
    
    
    /**
     * 获取下一个序列值
     * @return
     */
    public int getSeq(){
        return atomic.incrementAndGet();
    }
    
    /**
     * 获取当前的值
     * @return
     */
    public int get(){
        return atomic.get();
    }
}
