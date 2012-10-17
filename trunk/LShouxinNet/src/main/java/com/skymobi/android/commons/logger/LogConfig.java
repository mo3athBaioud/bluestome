package com.skymobi.android.commons.logger;

/**
 * @ClassName: LogConfig
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-4-23 下午09:25:45
 */
public class LogConfig {

    //单例模式
    private static LogConfig instance = null;
    
    //是否写文件
    private boolean isWrite = false;
    
    //日志开关，写文件依赖该开关
    private boolean isLog = true;
    
    //是否显示System.out信息
    private boolean showLog = false;
    
    //其他日志输出路径
    private String otherLogPath = null;
    
    private LogConfig(){
    }
    
    /**
     * 单例模式
     * @param isWrite 是否写文件
     * @param isLog 是否使用日志
     * @return
     */
    public static LogConfig getInstance(){
        if(null == instance){
            instance = new LogConfig();
        }
        return instance;
    }

    /**
     * @return the isWrite
     */
    public boolean isWrite() {
        return isWrite;
    }

    /**
     * @param isWrite the isWrite to set
     */
    public void setWrite(boolean isWrite) {
        this.isWrite = isWrite;
    }

    /**
     * @return the isLog
     */
    public boolean isLog() {
        return isLog;
    }

    /**
     * @param isLog the isLog to set
     */
    public void setLog(boolean isLog) {
        this.isLog = isLog;
    }

    /**
     * @return the otherLogPath
     */
    public String getOtherLogPath() {
        return otherLogPath;
    }

    /**
     * @param otherLogPath the otherLogPath to set
     */
    public void setOtherLogPath(String otherLogPath) {
        this.otherLogPath = otherLogPath;
    }

    /**
     * @return the showLog
     */
    public boolean isShowLog() {
        return showLog;
    }

    /**
     * @param showLog the showLog to set
     */
    public void setShowLog(boolean showLog) {
        this.showLog = showLog;
    }

}
