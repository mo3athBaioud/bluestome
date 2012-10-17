package android.skymobi.messenger.net.client;

import android.skymobi.messenger.net.client.bean.NetFsDownloadReq;
import android.skymobi.messenger.net.client.bean.NetFsDownloadResponse;
import android.skymobi.messenger.net.client.bean.NetUploadResponse;

import java.util.ArrayList;

/**
 * 文件服务接口
 * @ClassName: IFSBiz
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-17 上午10:08:32
 */
public interface IFSBiz {

    
    /**
     * 上传文件
     * @param url 文件服务器地址
     * @param fileExtName 文件扩展名，不包含.，例如:meili.jpg 只需要取"jpg"即可。
     * @param body 文件内容
     * @return
     */
    NetUploadResponse uploadFs(String url,int skyid,String token,String fileExtName,byte[] body);
    
    /**
     * 下载文件
     * @param url 文件服务器地址
     * @param downloadList 下载请求列表，支持多文件下载
     * @return
     */
    NetFsDownloadResponse downloadFs(String url,int skyid,String token,ArrayList<NetFsDownloadReq> downloadList);
}
