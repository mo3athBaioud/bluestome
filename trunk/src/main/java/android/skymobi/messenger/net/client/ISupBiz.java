package android.skymobi.messenger.net.client;

import android.skymobi.messenger.net.client.bean.NetSupResponse;

import com.skymobi.android.TerminalInfo;

import java.io.IOException;

/**
 * @ClassName: ISupBiz
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-20 下午01:42:50
 */
public interface ISupBiz {

    /**
     * 检查更新
     * 方法内部需要对请求内容进行编码
     * @param url SUP服务器地址
     * @param info 终端对象
     * @return 是否需要更新，以及更新提示
     */
    NetSupResponse checkSupupdate(String url,TerminalInfo info) throws IOException;
    
    
    /**
     * 更新 如果有文件，会下发文件内容
     * @param info 终端对象
     * @param url SUP服务器地址
     * @param md5 文件MD5值
     * @param startPos 文件起始位置 用于断点续传
     * @param fileSize 文件的总长度
     * @return
     */
    NetSupResponse update(TerminalInfo info,String url,String md5,int startPos);
    
    /**
     * 新SUP服务端下载最新版本文件
     * @param info 终端属性
     * @param url SUP服务器地址
     * @param path 文件保存路径
     * @param md5 下载文件的MD5值
     * @param startPos 下载起始值
     * @param fileTotalSize 文件总长度
     */
    NetSupResponse nupdate(TerminalInfo info,String url,String path,String md5,int startPos,int fileTotalSize) throws IOException;
    
}
