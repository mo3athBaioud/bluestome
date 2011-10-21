package com.takesoon.ssi.parser.china.military;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ssi.common.utils.FileUtils;
import com.ssi.common.utils.HttpClientUtils;
import com.ssi.common.utils.MD5;
import com.takesoon.oms.ssi.entity.Website;
import com.takesoon.oms.ssi.service.ArticleManager;
import com.takesoon.oms.ssi.service.FileCacheManager;
import com.takesoon.oms.ssi.service.ImageManager;
import com.takesoon.oms.ssi.service.WebsiteManager;
import com.takesoon.ssi.parser.bean.LinkBean;

@Component
@Transactional
public class IndexParser {
	
	private static Log logger = LogFactory.getLog(IndexParser.class);
	
	int D_PARENT_ID = 300;

	@Autowired
	private WebsiteManager websiteManager;
	
	@Autowired
	private ArticleManager articleManager;
	
	@Autowired
	private ImageManager imageManager;
	
	@Autowired
	private FileCacheManager fileCacheManager;
	
	HashMap<String, LinkBean> LINKHASH = new HashMap<String, LinkBean>();
	
	static Long SIZE_COUNT = 0l;
	
	public void start(){
		if(null != websiteManager){
			List<Website> list = websiteManager.findByParsentId(D_PARENT_ID);
			for(Website ws:list){
				// 单独处理子项逻辑
				try {
//					logger.debug(" > webName:"+ws.getName());
					doProcess(ws);
				} catch (Exception e) {
					logger.error("Exception:{1}",e);
				}
			}
			Iterator it = LINKHASH.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				LinkBean link = (LinkBean) LINKHASH.get(key);
//				logger.debug(link.getName()+"|"+link.getLink());
				try{
					byte[] body = HttpClientUtils.getResponseBodyAsByte(link.getLink());
					if(null != body && body.length > 0){
						String md5 = MD5.getInstance().getMD5ofStr(body);
						logger.debug(link.getName()+".md5:{1}"+md5);
					}
				}catch(Exception e){
					logger.error(link.getLink()+" Exception:"+e.getMessage());
					continue;
				}
			}
			logger.debug(" > size:"+LINKHASH.size());
			if(LINKHASH.size() > 0){
				LINKHASH.clear();
			}
		}
		
	}
	
	/**
	 * 测试往缓存填充文件
	 *
	 */
	public void loopFillFile2Ehcache()
	{
		String filePath = "d:/test2.jpg";
		byte[] body = null;
		try 
		{
			body = FileUtils.readFileToByteArray(new File(filePath));
			for(int i=0;i<1242;i++)
			{
				if(null != body && body.length > 0)
				{
					SIZE_COUNT += body.length;
					String key = String.valueOf(System.currentTimeMillis());
//					fileCacheManager.putByte(key, body);
					logger.debug("i["+i+"] put key["+key+"]");
					Thread.sleep(10);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		logger.debug(" > fill size:" + SIZE_COUNT);
	}

	private void doProcess(Website bean) throws Exception {
		logger.debug(" >> url.id[" + bean.getId() + "] url.url["+ bean.getUrl() + "]");
		try {
			//先根据URL取出md5，再根据md5取出byte[]，比较md5是否一致，如果一致的话，就不处理
			String md5 = fileCacheManager.getUrl(bean.getUrl());
			if(null != md5)
			{
				//TODO 可以将URL对应的文件下载到本地，然后在本地执行解析
				byte[] body = HttpClientUtils.getResponseBodyAsByte(bean.getUrl());
				if(null != body && body.length > 0)
				{
					String tmd5 = MD5.getInstance().getMD5ofStr(body);
					if(!md5.equals(tmd5)){
						logger.debug(" > md5 different ,will  remove old one of then!");
						fileCacheManager.removeByte(bean.getUrl());
						fileCacheManager.removeByte(md5);
						
						fileCacheManager.putUrl(bean.getUrl(), tmd5);
						fileCacheManager.putByte(tmd5, body);
//						String html = new String(body,"UTF-8");
//						getLinkByHtml(html);
					}
				}
			}
			else
			{
				logger.debug(" > md5 is null,try to get new one!");
				//TODO 可以将URL对应的文件下载到本地，然后在本地执行解析
				byte[] body = HttpClientUtils.getResponseBodyAsByte(bean.getUrl());
				if(null != body && body.length > 0)
				{
					String tmd5 = MD5.getInstance().getMD5ofStr(body);
					fileCacheManager.putUrl(bean.getUrl(), tmd5);
					fileCacheManager.putByte(tmd5, body);
//					String html = new String(body,"UTF-8");
//					getLinkByHtml(html);
				}
				
			}
		} catch (Exception e) {
			logger.error("Exception:{1}",e);
		}

	}
	
	private void getLink(String url) throws Exception {
		Parser p1 = new Parser();
		p1.setURL(url);
		p1.setEncoding("UTF-8");
		
		NodeFilter filter = new NodeClassFilter(LinkTag.class);
		NodeList list = p1.extractAllNodesThatMatch(filter);
		if (list != null && list.size() > 0) {
			LinkBean bean = null;
			for (int i = 0; i < list.size(); i++) {
				LinkTag link = (LinkTag) list.elementAt(i);
				
				bean = new LinkBean();
				bean.setLink(link.getLink());
				String name = link.getLinkText();
				if (name.indexOf("“") != -1 || name.indexOf("”") != -1) {
					name = name.replaceAll("“", "");
					name = name.replace("”", "");
				}
				// 判断连接中是否存在创建文件夹时的非法字符
				if (name.indexOf("\"") != -1 && name.indexOf("\"") != -1) {
					name = name.replace("\"", "");
				}

				bean.setName(name);
				LINKHASH.put(bean.getLink(), bean);
			}
		}
		p1 = null;
	}
	
	void getLinkByHtml(String html) throws Exception {
		Parser p1 = new Parser();
		p1.setInputHTML(html);
		p1.setEncoding("UTF-8");
		
		NodeFilter filter = new NodeClassFilter(LinkTag.class);
		NodeList list = p1.extractAllNodesThatMatch(filter);
		if (list != null && list.size() > 0) {
			LinkBean bean = null;
			for (int i = 0; i < list.size(); i++) {
				LinkTag link = (LinkTag) list.elementAt(i);
				
				bean = new LinkBean();
				bean.setLink(link.getLink());
				String name = link.getLinkText();
				if (name.indexOf("“") != -1 || name.indexOf("”") != -1) {
					name = name.replaceAll("“", "");
					name = name.replace("”", "");
				}
				// 判断连接中是否存在创建文件夹时的非法字符
				if (name.indexOf("\"") != -1 && name.indexOf("\"") != -1) {
					name = name.replace("\"", "");
				}

				bean.setName(name);
				LINKHASH.put(bean.getLink(), bean);
			}
		}
		p1 = null;
	}
}
