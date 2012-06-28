package com.skymobi.android.commons.logger;

/**
 * 日志工厂类
 * @ClassName: LoggerFactory
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-4-16 上午10:57:42
 */
public class LoggerFactory {

    /**
     * 构造函数
     * @param cls 类对象
     * @return Logger对象
     */
    public static Logger getLogger(Class<?> cls){
        LogConfig config = LogConfig.getInstance();
        return new Logger(cls,config.isWrite(),config.isLog(),config.isShowLog());
    }
    
    /**
     * 构造函数
     * @param cls 类对象
     * @param path 日志输出路径
     * @return Logger对象
     */
    public static Logger getLogger(Class<?> cls,String path){
        LogConfig config = LogConfig.getInstance();
        return new Logger(cls,config.isWrite(),config.isLog(),config.isShowLog(),path);
    }
    
    /**
     * 构造函数
     * @param cls 类对象
     * @param isWriteFile 是否写文件 true:写文件 false:不写文件
     * @param isLog 是否记录到日志  true:记录  false:不记录
     * @return
     */
    public static Logger getLogger(Class<?> cls,boolean isWriteFile,boolean isLog,boolean isShow){
        return new Logger(cls,isWriteFile,isLog,isShow);
    }
    
}
