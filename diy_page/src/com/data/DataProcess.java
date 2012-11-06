
package com.data;

import com.chinamilitary.bean.Article;
import com.chinamilitary.bean.ImageBean;
import com.chinamilitary.bean.WebsiteBean;
import com.chinamilitary.dao.ArticleDao;
import com.chinamilitary.dao.ImageDao;
import com.chinamilitary.dao.WebSiteDao;
import com.chinamilitary.factory.DAOFactory;
import com.chinamilitary.memcache.MemcacheClient;
import com.chinamilitary.util.HttpClientUtils;
import com.zhuoku.ZHUOKUParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class DataProcess {
    
    private static String TAG = DataProcess.class.getSimpleName();

    static Logger logger = LoggerFactory.getLogger(ZHUOKUParser.class);

    static MemcacheClient client = MemcacheClient.getInstance();
    
    static BlockingQueue<Integer> blockQueue = new LinkedBlockingQueue<Integer>(1000); 

    private static void patchImageFileSize() {
        ImageDao imageDao = DAOFactory.getInstance().getImageDao();
        ArticleDao articleDao = DAOFactory.getInstance().getArticleDao();
        List<ImageBean> list = null;
        do {
            try {
                list = imageDao.findImageByFilesizeEqualX(0, 0, 200);
                for (ImageBean b : list) {
                    String key = genKey(b.getArticleId());
                    String aUrl = (String) client.get(key);
                    if (null == aUrl) {
                        logger.error(genKey(b.getArticleId()) + "\t在缓存中没有记录");
                        Article a = articleDao.findById(b.getArticleId());
                        if(null == a){
                            continue;
                        }
                        key = genKey(a.getId());
                        if (null == client.get(key)) {
                            if (client.add(key, a.getArticleUrl())) {
                                System.out.println("添加\t" + key + "\t成功!");
                                aUrl = a.getArticleUrl();
                            }
                        }
                    }
                    if(null == aUrl)
                        return;
                    String len = HttpClientUtils.getHttpConentLength(aUrl, null, b.getHttpUrl());
                    if (null != len && !len.equals("0") && b.getStatus() != -3) {
                        int length = Integer.valueOf(len);
                        if (length > 0) {
                            b.setFileSize(Long.valueOf(length));
                            if (imageDao.update(b)) {
                                System.out.println(key + "|" + b.getId() + "|" + b.getArticleId()
                                        + "|" + b.getHttpUrl() + "|\t获取图片大小成功，大小为:" + length + "!");
                            }
                        }
                    } else {
                        blockQueue.add(b.getArticleId());
                        logger.info(key + "|" + b.getId() + "|" + b.getArticleId() + "|"
                                + b.getHttpUrl() + "|" + b.getStatus() + "|\t获取图片大小失败!");
                        b.setFileSize(-1L);
                        // 经过处理发现图片大小获取不到
                        b.setStatus(-3);
                        imageDao.update(b);
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        } while (null != list && list.size() > 0);
    }

    private static void initArticle2Cache() {
        WebSiteDao wesiteDao = DAOFactory.getInstance().getWebSiteDao();
        ArticleDao articleDao = DAOFactory.getInstance().getArticleDao();
        List<WebsiteBean> list = null; 
        try{
            list = wesiteDao.findAll();
            for(WebsiteBean b:list){
                List<Article> al = articleDao.findByWebId(b.getId());
                for(Article a:al){
                    if(!a.getText().equals("EOF")){
                        String key = genKey(a.getId());
                        if(null == client.get(key)){
                            if(client.add(key, a.getArticleUrl())){
                                System.out.println("添加\t"+key+"\t成功!");
                            }
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    /**
     * 检查文章站点是否可用
     *
     */
    private static void patchArticleAvailable(){
        WebSiteDao wesiteDao = DAOFactory.getInstance().getWebSiteDao();
        ArticleDao articleDao = DAOFactory.getInstance().getArticleDao();
        List<WebsiteBean> list = null; 
        try{
            list = wesiteDao.findAll();
            for(WebsiteBean b:list){
                List<Article> al = articleDao.findByWebId(b.getId());
                for(Article a:al){
                    if(!HttpClientUtils.urlValidation(a.getArticleUrl())){
                        a.setText("EOF");
                        if(articleDao.update(a)){
                            System.err.println("更新当前文章["+a.getId()+"|"+a.getArticleUrl()+"]为不可用状态[EOF]");
                        }
                    }else{
                        System.out.println(a.getArticleUrl()+"\t可用");
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private static String genKey(Object key){
        return TAG+File.separator+"Article"+File.separator+key;
    }
    
    private static void testURL(){
        String length = HttpClientUtils.getHttpConentLength("http://tuku.news.china.com/history/html/2010-01-05/135216.htm",
                null, "http://images2.china.com/news/zh_cn/historygallery/11127886/20121031/17502229_2012103109350296689800.jpg");
        System.out.println(length);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        final byte[] lock = new byte[0];
        final ArticleDao articleDao = DAOFactory.getInstance().getArticleDao();
        new Thread(new Runnable(){
            public void run(){
                while (true) {
                    try {
                        Integer aid = blockQueue.poll(100L, TimeUnit.MILLISECONDS);
                        if (null != aid && aid > 0) {
                            String key = genKey(aid);
                            String aUrl = (String) client.get(key);
                            // TODO 检查网站是否可用，如果不可用，则更新为EOF状态
                            if (!HttpClientUtils.urlValidation(aUrl)) {
                                System.out.println("\t aid:"+aid+"|"+aUrl+"\t访问失败!");
                                try {
                                    Article a = articleDao.findById(aid);
                                    if (null == a)
                                        return;
                                    if(a.getText().equals("EOF")){
                                        return;
                                    }
                                    a.setText("EOF");
                                    synchronized(lock){
                                        if (articleDao.update(a)) {
                                            System.err.println("更新当前文章[" + a.getId() + "|"
                                                    + a.getArticleUrl() + "]为不可用状态[EOF]");
                                        }
                                    }
                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                        }
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        patchImageFileSize();
    }

}
