package android.skymobi.messenger.net.client;

import android.skymobi.messenger.net.client.bean.NetDownloadImageRespInfo;
import android.skymobi.messenger.net.client.bean.NetDownloadResponse;
import android.skymobi.messenger.net.client.bean.NetImageDownloadResponse;
import android.skymobi.messenger.net.client.bean.NetUploadResponse;

import java.util.ArrayList;

/**
 * 图片服务接口
 * @ClassName: IImageBiz
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-17 上午10:08:49
 */
public interface IImageBiz {

   /**
    * 上传图片
    * @param url 图片服务器URL
    * @param appid 应用ID
    * @param body 图片流
    * @param skyid 上传者ID
    * @return
    */
    NetUploadResponse uploadImage(String url,int appid,byte[] body,int skyid);

    /**
     * 上传图片
     * @param url 图片服务器URL
     * @param body 图片流
     * @param fileExtName 图片扩展名
     * @param skyid 上传者ID
     * @param token 用户登录TOKEN
     * @return
     */
     NetUploadResponse sfsUploadImage(String url,byte[] body,String fileExtName,int skyid,String token);
     
   /**
    * 下载图片
    * @param url 图片服务器URL
    * @param fileName 文件名
    * @param width 图片宽度
    * @param forceGif 是否强制转为GIF
    * @return
    */
   NetDownloadResponse downloadImage(String url,String fileName,int width,int forceGif);

   /**
    * 下载图片
    * @param url 文件服务器URL
    * @param fileName 文件名
    * @param width 图片宽度
    * @param forceGif 是否强制转为GIF
    * @return
    */
   NetImageDownloadResponse sfsDownloadImage(String url,int skyid,String token,ArrayList<NetDownloadImageRespInfo> reqList);
}
