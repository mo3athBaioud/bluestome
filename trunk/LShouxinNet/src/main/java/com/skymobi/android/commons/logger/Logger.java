package com.skymobi.android.commons.logger;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日志类
 * @ClassName: Logger
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-4-13 下午01:24:31
 */
public class Logger {
    
    /**
     * 构造函数
     * <p>Title: </p> 
     * <p>Description: </p> 
     * @param cls
     */
    protected Logger(Class<?> cls){
        this.MYLOG_WRITE_TO_FILE = false;
        this.MYLOG_SWITCH = true;
        this.MYLOG_SHOW_SWITCH = true;
        if(null != cls){
            tag = cls.getSimpleName();
        }
    }
    
    /**
     * 构造函数
     * <p>Title: </p> 
     * <p>Description: </p> 
     * @param cls 需要记录的类名
     * @param isWriteFile 是否保存到文件
     * @param isLog 是否需要记录到日志
     * @param isShow 是否将打印信息显示在LOGCAT中
     */
    protected Logger(Class<?> cls,boolean isWriteFile,boolean isLog,boolean isShow){
        this.MYLOG_WRITE_TO_FILE = isWriteFile;
        this.MYLOG_SWITCH = isLog;
        this.MYLOG_SHOW_SWITCH = isShow;
        if(null != cls){
            tag = cls.getSimpleName();
        }
    }
    
    /**
     * 构造函数
     * <p>Title: </p> 
     * <p>Description: </p> 
     * @param cls 需要记录的类名
     * @param isWriteFile 是否保存到文件
     * @param isLog 是否需要记录到日志
     * @param isShow 是否将打印信息显示在LOGCAT中
     * @param path 文件保存路径
     */
    protected Logger(Class<?> cls,boolean isWriteFile,boolean isLog,boolean isShow,String path){
        this.MYLOG_WRITE_TO_FILE = isWriteFile;
        this.MYLOG_SWITCH = isLog;
        this.MYLOG_SHOW_SWITCH = isShow;
        this.path = path;
        if(null != cls){
            tag = cls.getSimpleName();
        }
        
    }
    
    // 打印的TAG
    private String tag = this.getClass().getSimpleName();
    
    // 日志文件总开关
    private Boolean MYLOG_SWITCH=true; 
    
    // 日志写入文件开关
    private Boolean MYLOG_WRITE_TO_FILE=false;
    
    //是否显示打印的日志
    private boolean MYLOG_SHOW_SWITCH = false;
    
    //日志保存路径
    private String path = null;
    
    // 日志文件在sdcard中的路径
    private String MYLOG_PATH_SDCARD_DIR="/sdcard/.shouxin/";
    
    // sd卡中日志文件的最多保存天数
    private int SDCARD_LOG_FILE_SAVE_DAYS = 0;
    
    // 本类输出的日志文件名称
    private String MYLOGFILEName = "shouxin-android-{date}.log";
    
    // 日志的输出格式
    private SimpleDateFormat myLogSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    // 日志文件格式
    private static SimpleDateFormat logfile = new SimpleDateFormat("yyyy-MM-dd");

    //警告信息
    public void warn(Object msg) { 
        log(tag, msg.toString(), "warn");
    }

    //错误信息
    public void error(Object msg) { 
        log(tag, msg.toString(), "error");
    }

    //调试信息
    public void debug(Object msg) {
        log(tag, msg.toString(), "debug");
    }

    public void info(Object msg) {//
        log(tag, msg.toString(), "info");
    }

    public void v(Object msg) {
        log(tag, msg.toString(), "verbose");
    }

    public void warn(String text) {
        log(tag, text, "warn");
    }

    public void warn(String text,Object e) {
        log(tag, text,e, "warn");
    }
    
    public void error(String text) {
        log(tag, text, "error");
    }

    public void error(String text,Object e) {
        log(tag, text, "error");
    }
    
    public void debug(String text) {
        log(tag, text, "debug");
    }

    public void debug(String text,Object e) {
        log(tag, text,e,"debug");
    }
    
    public void info(String text) {
        log(tag, text, "info");
    }

    public void info(String text,Object e) {
        log(tag, text,e,"info");
    }
    
    public void v(String text) {
        log(tag, text, "verbose");
    }

    public void v(String text,Throwable e) {
        log(tag, text,e,"verbose");
    }
    
    /**
     * 根据tag, msg和等级，输出日志
     * @param tag 打印的标签
     * @param msg 消息内容
     * @param level 消息级别
     */
    private void log(String tag, Object msg,String level) {
        if(MYLOG_SWITCH){
            if (MYLOG_WRITE_TO_FILE){
                writeLogtoFile(String.valueOf(level),tag, msg,null);
            }else{
                if(MYLOG_SHOW_SWITCH){
                    Date nowtime = new Date();
                    String needWriteMessage = myLogSdf.format(nowtime) + "\t" + String.valueOf(level).toUpperCase()
                            + "\t" + tag + "\t" + msg.toString();
                    System.out.println(needWriteMessage);
                }
            }
        }
    }
    
    /**
     * 根据tag, msg和等级，输出日志
     * 
     * @param tag 输出的标签
     * @param msg 日志内容
     * @param e 异常对象
     * @param level 
     */
    private void log(String tag, Object msg,Object e,String level) {
        if(MYLOG_SWITCH){
            if (MYLOG_WRITE_TO_FILE){
                writeLogtoFile(String.valueOf(level), tag, msg,e);
            }else{
                if(MYLOG_SHOW_SWITCH){
                    Date nowtime = new Date();
                    String needWriteMessage = myLogSdf.format(nowtime) + "\t" + String.valueOf(level).toUpperCase()
                            + "\t" + tag + "\t" + msg.toString();
                    System.out.println(needWriteMessage);
                }
            }
        }
    }

    /**
     * 打开日志文件并写入日志
     * @param mylogtype 日志类型 INFO,DEBUG,WARN,ERROR,VERBOSE
     * @param tag 打印的标签
     * @param text 日志内容
     * @param throwable 是否异常
     */
    private void writeLogtoFile(String mylogtype, String tag, Object text,Object throwable) {// 新建或打开日志文件
        Date nowtime = new Date();
        File file = null;
        String needWriteFiel = logfile.format(nowtime);
        String needWriteMessage = myLogSdf.format(nowtime) + "\t" + mylogtype.toUpperCase()
                + "\t" + tag + "\t" + text.toString();
        if(null != throwable){
            if(needWriteMessage.contains("{}")){
                needWriteMessage = needWriteMessage.replace("{}", throwable.toString());
            }else{
                needWriteMessage += "Exception:"+throwable.toString();
            }
        }
        if(MYLOGFILEName.contains("{date}")){
            MYLOGFILEName = MYLOGFILEName.replace("{date}", needWriteFiel);
        }
        //根据指定PATH写入指定的文件
        if(null != path && path.equals("")){
            file = new File(path);
        }else{
            //使用默认路径
            file = new File(MYLOG_PATH_SDCARD_DIR+File.separator+MYLOGFILEName);
        }
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        try {
            // 后面这个参数代表是不是要接上文件中原来的数据，不进行覆盖
            FileWriter filerWriter = new FileWriter(file, true);
            BufferedWriter bufWriter = new BufferedWriter(filerWriter);
            bufWriter.write(needWriteMessage);
            bufWriter.write("\r\n");
            bufWriter.close();
            filerWriter.close();
        } catch (IOException e) {
            System.out.println(" > SD Card Cann't Write File! ");
        } finally{
            if(MYLOG_SHOW_SWITCH){
                System.out.println(needWriteMessage);
            }
        }
    }

    /**
     * 删除制定的日志文件
     * */
    public void delFile() {// 删除日志文件
        String needDelFiel = logfile.format(getDateBefore());
        File file = new File(MYLOG_PATH_SDCARD_DIR, needDelFiel + File.separator+MYLOGFILEName);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 得到现在时间前的几天日期，用来得到需要删除的日志文件名
     * */
    private Date getDateBefore() {
        Date nowtime = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(nowtime);
        now.set(Calendar.DATE, now.get(Calendar.DATE)
                - SDCARD_LOG_FILE_SAVE_DAYS);
        return now.getTime();
    }

    /**
     * @return
     */
    public boolean isDebugEnabled() {
        return true;
    }

    /**
     * @return
     */
    public boolean isWarnEnabled() {
        return true;
    }

    /**
     * @return
     */
    public boolean isInfoEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}
