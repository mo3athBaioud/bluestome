package test.android.skymobi.messenger.net;

import android.skymobi.messenger.net.client.INetClient;
import android.skymobi.messenger.net.client.NetClient;
import android.skymobi.messenger.net.client.bean.NetDownloadResponse;
import android.skymobi.messenger.net.client.bean.NetFsDownloadReq;
import android.skymobi.messenger.net.client.bean.NetFsDownloadResponse;
import android.skymobi.messenger.net.client.bean.NetLoginResponse;
import android.skymobi.messenger.net.client.bean.NetUploadResponse;
import android.skymobi.messenger.net.connection.IConnection;
import android.skymobi.messenger.net.connection.NetConfig;
import android.skymobi.messenger.net.notify.IServerBizNotify;
import android.skymobi.messenger.net.notify.impl.ServerBizNotify;
import android.skymobi.messenger.net.utils.SysUtils;
import android.skymobi.messenger.net.client.bean.NetFsDownloadFile;

import com.skymobi.android.util.ByteUtils;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;


/**
 * @ClassName: FileServerTest
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-20 下午07:56:54
 */
public class FileServerTest {

    protected IConnection connection = null;

    protected NetConfig config = new NetConfig();

    protected INetClient clientBiz = null;
    
    protected NetLoginResponse response = null;
   
    protected String token = "12345678";
    
    protected int skyid = -1;
    
    public static final String FS_URL = "http://172.16.3.142:6010/";
    
    public static final String IS_URL = "http://172.16.4.25/webfile";

    public static final String IS_URL_2 = "http://172.16.3.140:9902/webfile";
    
    protected IServerBizNotify serverBizNotify = new ServerBizNotify();
    @Before
    public void init() {
        System.out.println(" init ");
        clientBiz = NetClient.init(config,null);
        try {
            Thread.sleep(5000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @After
    public void destory() {
        if (null != connection && connection.isConnect()) {
            connection.close();
        }
    }
    
    /**
     * 上传文件
     */
    @Test
    public void uploadFs(){
        FileInputStream fis = null;
        byte[] body = null;
        try{
            fis = new FileInputStream(new File("d:\\tmp\\voice\\voice_android.amr"));
            if(null != fis){
                body = new byte[fis.available()];
                System.out.println(" >> 文件长度："+body.length);
                fis.read(body);
                if(null != body && body.length > 0){
                    NetUploadResponse response = clientBiz.uploadFs(FS_URL,1,null,"AMR",body);
                    if(null != response){
                        if(response.isNetError()){
                            System.out.println(response.getResultHint());
                        }else{
                            if(response.isSuccess()){
                                System.out.println(" > 文件MD5值:"+response.getMd5());
                            }else{
                                System.out.println(response.getResultHint());
                            }
                        }
                    }else{
                        System.out.println("上传失败 ");
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            Thread.sleep(1000L);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * 从文件服务下载文件
     */
    @Test
    public void downloadFs(){
        OutputStream out = null;
        String md5 = "1c8f6036451b5bd49f0e92fd52580885";
        ArrayList<NetFsDownloadReq> rlist = new ArrayList<NetFsDownloadReq>();
        NetFsDownloadReq dq = new NetFsDownloadReq(md5,0);
        rlist.add(dq);
        
        NetFsDownloadResponse response = clientBiz.downloadFs(FS_URL,1,null,rlist);
        if(null != response){
            try{
                if(response.isNetError()){
                    System.out.println(response.getResultHint());
                }else{
                    if(response.isSuccess()){
                        for(NetFsDownloadFile file:response.getFileList()){
                            if(file.getFileSize() > 0){
                                out = new FileOutputStream(new File("d:"+File.separator+"tmp"+File.separator+"image"+File.separator+"FS_"+System.currentTimeMillis()+".jpg"));
                                IOUtils.write(file.getFileData(), out);
                                out.close();
                            }
                            try{
                                Thread.sleep(2000);
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                        System.out.println(" > "+response.getResultHint());
                    }else{
                        System.out.println(response.getResultHint());
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 向图片服务上传图片文件
     */
    @Test
    public void uploadImage(){
        FileInputStream fis = null;
        byte[] body = null;
        try{
            fis = new FileInputStream(new File("d:\\123.jpg"));
            if(null != fis){
                body = new byte[fis.available()];
                fis.read(body);
                if(null != body && body.length > 0){
                    NetUploadResponse response = clientBiz.uploadImage(IS_URL_2, 2934, body, 34562320);
                    if(response.isNetError()){
                        System.out.println(response.getResultHint());
                    }else{
                        if(response.isSuccess()){
                            System.out.println(" > id:" + response.getId());
                            System.out.println(" > imageName:" + response.getImageName());
                            System.out.println(" > imageUrl:" + response.getImageUrl());
                        }else{
                            System.out.println(response.getResultHint());
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(null != fis){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != body){
                body = null;
            }
        }
        try{
            Thread.sleep(1000L);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 从图片服务下载图片文件
     */
    @Test
    public void downloadImage(){
        OutputStream out = null;
        //group1@M00/00/00/rBADj09G6VEAAAAAAAA1TmamK1o623.PNG
//        String fileName = "group1@M00/00/29/rBADeE9G6QAAAAAAAAA1TjwE8oQ294.PNG";
        String fileName = "group1@M00/00/00/rBADj09G6qUAAAAAABWZZAw4Yh068.JPEG";
        try{
            NetDownloadResponse response = clientBiz.downloadImage(IS_URL_2, fileName, 400, 2);
            if(null != response){
                try{
                    if(response.isNetError()){
                        System.out.println(" 连接网络失败");
                    }else{
                        if(response.isSuccess()){
                            out = new FileOutputStream(new File("d:"+File.separator+"tmp"+File.separator+"image"+File.separator+"IS_"+System.currentTimeMillis()+getFileExt(fileName)));
                            IOUtils.write(response.getBody(), out);
                            out.close();
                            System.out.println(response.getResultHint());
                        }else{
                            System.out.println(response.getResultHint());
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Test
    public void fileExt(){
        String fileName = "group1@M00/00/29/rBADeE9G6QAAAAAAAAA1TjwE8oQ294.PNG";
        String ext = getFileExt(fileName);
        System.out.println(" > ext:"+ext);
    }
    
    private String getFileExt(String fileName){
        String ext = ".jpg";
        try{
            int start = fileName.lastIndexOf(".");
            if(-1 != start){
                ext = fileName.substring(start);
            }
        }catch(Exception e){
            
        }
        return ext;
    }

}
