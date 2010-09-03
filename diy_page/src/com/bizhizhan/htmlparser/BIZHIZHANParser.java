package com.bizhizhan.htmlparser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.CompositeTag;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.OptionTag;
import org.htmlparser.tags.SelectTag;
import org.htmlparser.tags.TableTag;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;

import com.chinamilitary.bean.Article;
import com.chinamilitary.bean.ArticleDoc;
import com.chinamilitary.bean.ImageBean;
import com.chinamilitary.bean.LinkBean;
import com.chinamilitary.bean.PicfileBean;
import com.chinamilitary.bean.ResultBean;
import com.chinamilitary.bean.WebsiteBean;
import com.chinamilitary.dao.ArticleDao;
import com.chinamilitary.dao.ImageDao;
import com.chinamilitary.dao.PicFileDao;
import com.chinamilitary.dao.WebSiteDao;
import com.chinamilitary.factory.DAOFactory;
import com.chinamilitary.memcache.MemcacheClient;
import com.chinamilitary.test.TestHttpClient;
import com.chinamilitary.util.CacheUtils;
import com.chinamilitary.util.CommonUtil;
import com.chinamilitary.util.HttpClientUtils;
import com.chinamilitary.util.IOUtil;
import org.htmlparser.PrototypicalNodeFactory;

public class BIZHIZHANParser {

	private static final String PIC_SAVE_PATH = "d:\\share\\bizhizhan\\";

	static String URL = "http://www.bizhizhan.com";

	static String IMAGE_URL = "http://image6.tuku.cn/";
	
	static String ARTICLE_COM_URL = "";

	static List<LinkBean> LINKLIST = new ArrayList<LinkBean>();

	static List<Article> ARTICLELIST = new ArrayList<Article>();

	static HashMap<String, LinkBean> LINKHASH = new HashMap<String, LinkBean>();

	static MemcacheClient client = MemcacheClient.getInstance();

	static ArticleDao articleDao = DAOFactory.getInstance().getArticleDao();

	static WebSiteDao webSiteDao = DAOFactory.getInstance().getWebSiteDao();

	static ImageDao imageDao = DAOFactory.getInstance().getImageDao();

	static PicFileDao picFiledao = DAOFactory.getInstance().getPicFileDao();
	
	static String[] CATALOG_URL = {
		"http://www.bizhizhan.com/fzlsjbz/","http://www.bizhizhan.com/katongshoujibizhi/","http://www.bizhizhan.com/youxidongman/",
		"http://www.bizhizhan.com/fengjingshoujibizhi/","http://www.bizhizhan.com/jierishoujibizhi/","http://www.bizhizhan.com/renwenjijiabizhi/",
		"http://www.bizhizhan.com/yingshimingxingbizhi/","http://www.bizhizhan.com/tiyubizhi/","http://www.bizhizhan.com/qicheshoujibizhi/",
		"http://www.bizhizhan.com/junshikeji/","http://www.bizhizhan.com/dongwubizhi/","http://www.bizhizhan.com/dianyinghaibao/",
		"http://www.bizhizhan.com/shoujiyuelibizhi/"
	};

	/**
	 * 获取分类链接
	 * 
	 * @param url
	 * @throws Exception
	 */
	static void catalog(String url) throws Exception { // WebsiteBean bean
		Parser parser = new Parser();
		parser.setURL(url);
		parser.setEncoding("UTF-8");

		NodeFilter fileter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(fileter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("class", "nav")); // id

		if (null != list && list.size() > 0) {

			System.out.println("" + list.size());

			Div div = (Div) list.elementAt(0);
			Parser p2 = new Parser();
			p2.setInputHTML(div.toHtml());
			NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
			NodeList linkList = p2.extractAllNodesThatMatch(linkFilter);
			if (linkList != null && linkList.size() > 0) {
				WebsiteBean tmp = null;
				for (int i = 0; i < linkList.size(); i++) {
					LinkTag link = (LinkTag) linkList.elementAt(i);
					System.out.println(link.getLinkText() + "|" + URL
							+ link.getLink() + "\n");
					tmp = new WebsiteBean();
					tmp.setName(link.getLinkText());
					tmp.setUrl(URL + link.getLink());
					tmp.setParentId(600);
					boolean b = webSiteDao.insert(tmp);
					if (b) {
						client.add(tmp.getUrl(), tmp.getUrl());
						System.out.println("成功");
					} else {
						System.out.println("失败");
					}
				}
			}
		}
		if (null != parser)
			parser = null;
	}

	/**
	 * 获取分类下的分页信息
	 * 
	 * @param url
	 * @param attribute
	 * @param value
	 * @return
	 * @throws Exception
	 */
	static ResultBean hasPaging(String url) throws Exception {
		boolean b = false;
		ResultBean result = new ResultBean();
		Parser parser = new Parser();
		parser.setURL(url);

		// 获取指定ID的DIV内容
		NodeFilter filter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("class", "page padding5"));
		if (list != null && list.size() > 0) {
			Parser p2 = new Parser();
			p2.setInputHTML(list.elementAt(0).toHtml());

			NodeFilter optionFilter = new NodeClassFilter(OptionTag.class);
			NodeList optionList = p2.extractAllNodesThatMatch(optionFilter);
			if (optionList != null && optionList.size() > 1) {
				result.setBool(true);
				LinkBean oplink = null;
				for (int j = 0; j < optionList.size(); j++) {
					OptionTag option = (OptionTag) optionList.elementAt(j);
					oplink = new LinkBean();
					String url2 = url + option.getValue();
					oplink.setLink(url + option.getValue());
					result.put(url2, oplink);
				}
			}
			b = true;
			result.setBool(b);
		}
		return result;
	}

	public static void getArtcile(String url, int webId) throws Exception {
		Parser p1 = new Parser();
		p1.setURL(url);

		NodeFilter filter = new NodeClassFilter(Div.class);
		NodeList list = p1.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("class", "list"));

		if (null != list && list.size() > 0) {
			Parser p2 = new Parser();
			p2.setInputHTML(list.toHtml());

			NodeFilter filter2 = new NodeClassFilter(CompositeTag.class);
			NodeList list2 = p2.extractAllNodesThatMatch(filter2)
					.extractAllNodesThatMatch(
							new HasAttributeFilter("class", "cont_list"));
			;

			if (null != list2) {
				Parser p3 = new Parser();
				p3.setInputHTML(list2.toHtml());

				NodeFilter filter3 = new NodeClassFilter(LinkTag.class);
				NodeList list3 = p3.extractAllNodesThatMatch(filter3);
				Article article = null;
				for (int i = 0; i < list3.size(); i++) {
					LinkTag link = (LinkTag) list3.elementAt(i);
					String url2 = URL + link.getLink();
					System.out.println(link.getLinkText() + "|" + url2);
					if (null == client.get(url2)) {
						article = new Article();
						article.setWebId(webId);
						article.setArticleUrl(url2);
						article.setTitle(link.getLinkText());
						article.setText("NED"); // No Execute Download
						int key = articleDao.insert(article);
						if (key > 0) {
							client.add(url, url);
							System.out.println("添加" + link.getLinkText()
									+ ",成功");
						} else {
							System.out.println("添加" + link.getLinkText()
									+ "失败,已存在相同标题的内容");
						}
					} else {
						System.out.println(">> 缓存中存在该图片[" + url2 + "]地址 <<");
					}
				}

			}
		}

	}
	
	static boolean getImage(Article article) throws Exception{
		boolean b = false;
		List<String> linkList = new ArrayList<String>();
		ResultBean result = getImagePage(article.getArticleUrl());
		//TODO 先从页面中判断是否有分页
		if(result.isBool()){
			System.out.println("分页数量:"+result.getMap().size());
			
			//TODO 有分页，则执行获取分页地址，然后记录图片地址
			Iterator it = result.getMap().keySet().iterator();
			while(it.hasNext()){
				String key = (String)it.next();
				linkList.add(key);
			}
		}else{
			//TODO 无分页
			linkList.add(article.getArticleUrl());
		}
		Parser p1 = null;
		for(String link:linkList){
			p1 = new Parser();
			p1.setURL(link);
			
			NodeFilter filter = new NodeClassFilter(ImageTag.class);
			NodeList list3 = p1.extractAllNodesThatMatch(filter).extractAllNodesThatMatch(new HasAttributeFilter("id","bigimg"));
			if(null != list3 && list3.size() > 0){
				ImageTag img = (ImageTag)list3.elementAt(0);
				System.out.println(img.getAttribute("alt")+"|"+img.getImageURL());
				ImageBean imageBean = null;
				imageBean = new ImageBean();
				imageBean.setArticleId(article.getId());
    			imageBean.setImgUrl(img.getImageURL());
    			imageBean.setTitle(img.getAttribute("alt"));
    			imageBean.setHttpUrl(img.getImageURL());
				imageBean.setLink("NED");
				imageBean.setCommentshowurl(link);
				//TODO 需要增加缓存判断
				if(null == client.get(imageBean.getHttpUrl())){
    				int size = imageDao.insert(imageBean);
    				if(size > 0){
    					client.add(imageBean.getHttpUrl(), imageBean.getHttpUrl());
    					System.out.println("添加图片记录:["+imageBean.getHttpUrl()+"]"+imageBean.getArticleId()+"]\t|" +article.getTitle()+"\t:"+"成功!");
    				}else{
						ImageBean tmpImg = imageDao.findByHttpUrl(imageBean.getHttpUrl());
						if(null == tmpImg.getCommentshowurl()){
							if(null != tmpImg){
								tmpImg.setCommentshowurl(link);
								if(!imageDao.update(tmpImg)){
									System.out.println("更新图片记录:["+tmpImg.getTitle() + "|"+tmpImg.getArticleId()+"]\t|" + link+"\t成功");
									client.add(link, link);
								}
							}
						}
					}
				}else{
					ImageBean tmpImg = imageDao.findByHttpUrl(imageBean.getHttpUrl());
					if(null != tmpImg){
						if(null == tmpImg.getCommentshowurl()){
							tmpImg.setCommentshowurl(link);
							if(!imageDao.update(tmpImg)){
								System.out.println("更新图片记录:["+tmpImg.getTitle() + "|"+tmpImg.getArticleId()+"]\t|" + link+"\t成功");
								client.add(link, link);
							}
						}
					}
				}
				b = true;
			}
		}
		return b;
	}
	
	static ResultBean getImagePage(String url) throws Exception{
		LinkBean oplink = null;
		boolean b = false;
		ResultBean result = new ResultBean();
		Parser parser = new Parser();
		parser.setURL(url);

		// 获取指定ID的DIV内容
		NodeFilter filter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("class", "content-page"));
		if (list != null && list.size() > 0) {
			Parser p2 = new Parser();
			p2.setInputHTML(list.elementAt(0).toHtml());

			NodeFilter filter2 = new NodeClassFilter(LinkTag.class);
			NodeList linkList = p2.extractAllNodesThatMatch(filter2);
			if (linkList != null && linkList.size() > 1) {
				String tmp = null;
				for (int j = 0; j < linkList.size(); j++) {
					LinkTag link = (LinkTag) linkList.elementAt(j);
					oplink = new LinkBean();
					tmp = url.substring(0,url.lastIndexOf("/")+1);
					tmp = tmp+link.getLink();
					oplink.setLink(tmp);
					result.put(tmp, oplink);
				}
			}
		}
		oplink = new LinkBean();
		oplink.setLink(url);
		result.put(url, oplink);
		result.setBool(true);

		return result;
		}

	/**
	 * 获取指定URL下的源码
	 * 
	 * @param url1
	 * @return
	 */
	public static String ViewSourceFrame(String url1) throws Exception {
		String url = url1;
		String linesep = System.getProperty("line.separator");
		String htmlLine;
		String htmlSource = "";
		java.net.URL source = new URL(url);
		InputStream in = new BufferedInputStream(source.openStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		while ((htmlLine = br.readLine()) != null) {
			htmlSource = htmlSource + htmlLine + linesep;
		}
		return htmlSource;
	}

	static void init(){
		try{
			List<String> articleURLlist = articleDao.findAllArticleURL(600);
			for(String key:articleURLlist){
				Object obj = client.get(key);
				if(null == obj){
					client.add(key, key);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			// catalog(URL);
			// ResultBean result =
			// hasPaging("http://www.bizhizhan.com/fzlsjbz/");
			// if(result.isBool()){
			// Iterator it = result.getMap().keySet().iterator();
			// while(it.hasNext()){
			// String key = (String)it.next();
			// System.out.println("key:"+key);
			// }
			// }

			// getImage("http://www.bizhizhan.com/fzlsjbz/23-1.html");
			
//			index();
			
//			init();
			
//			update();
			
//			image();
			
			downloadImg();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void index() throws Exception{
		Parser p1 = new Parser();
		p1.setURL("http://www.bizhizhan.com/");
		
		NodeFilter filter = new NodeClassFilter(LinkTag.class);
		NodeList list = p1.extractAllNodesThatMatch(filter);
		
		if(null != list && list.size() > 0){
			Article article = null;
			for(int i=0;i<list.size();i++){
				LinkTag link = (LinkTag) list.elementAt(i);
				String url2 = URL + link.getLink();
				boolean b = validationURL(url2);
				if(b){
					if (null == client.get(url2)) {
						article = new Article();
						article.setWebId(601);
						article.setArticleUrl(url2);
						article.setTitle(link.getLinkText());
						article.setText("NED"); // No Execute Download
						int key = articleDao.insert(article);
						if (key > 0) {
							client.add(url2, url2);
							System.out.println("添加" + link.getLinkText()
									+ ",成功");
						} else {
							System.out.println("添加" + link.getLinkText()
									+ "失败,已存在相同标题的内容");
						}
					} else {
						System.out.println(">> 缓存中存在该图片[" + url2 + "]地址 <<");
					}
//					System.out.println(">> 地址合法["+url2+"]");
				}else{
					System.err.println(">> 地址不合法["+url2+"]");
				}
			}
		}
	}

	/**
	 * 更新网站记录
	 * @throws Exception
	 */
	public static void update() throws Exception{
		List<WebsiteBean> list = webSiteDao.findByParentId(600);
		for (WebsiteBean bean : list) {
			ResultBean result = hasPaging(bean.getUrl());
			if (result.isBool()) {
				Iterator it = result.getMap().keySet().iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					System.out.println("key:" + key);
					getArtcile(key, bean.getId());
				}

			}
		}
	}
	
	/**
	 * 获取图片
	 * @throws Exception
	 */
	static void image() throws Exception{
		List<WebsiteBean> webList = webSiteDao.findByParentId(600);
		for(WebsiteBean bean:webList){
			List<Article> artlist = articleDao.findByWebId(bean.getId(),"FD");
			System.out.println("网站["+bean.getId()+"|"+bean.getName()+"|"+bean.getUrl()+"]下的文章数"+artlist.size());
			for(Article art:artlist){
				List<ImageBean> imgList = imageDao.findImage(art.getId());
				if(imgList.size() == 0){
					try{
						if(getImage(art)){
							art.setText("FD");
							if(!articleDao.update(art)){
								System.err.println(">> 更新文章["+art.getTitle()+"]失败");
							}
						}
					}catch(Exception e){
						System.err.println("更新图片出现异常，文章为["+art.getTitle()+"]");
					}
				}
			}
		}
	}
	
	static void downloadImg() throws Exception{
		List<WebsiteBean> webList = webSiteDao.findByParentId(600);
		for(WebsiteBean bean:webList){
			List<Article> artlist = articleDao.findByWebId(bean.getId(),"FD");
			System.out.println("网站["+bean.getId()+"|"+bean.getName()+"|"+bean.getUrl()+"]下的文章数"+artlist.size());
			for(Article art:artlist){
				List<ImageBean> imgList = imageDao.findImage(art.getId());
				System.out.println(">> 文章["+art.getId()+"|"+art.getTitle()+"]\t下的图片数量"+imgList.size());
				ARTICLE_COM_URL = art.getArticleUrl();
				for (ImageBean img : imgList) {
					if((img.getLink().equalsIgnoreCase("NED")) || (img.getStatus() == 3)){
						if (download(img,art.getArticleUrl())) {
							img.setStatus(1);
							img.setLink("FD");
							if (imageDao.update(img)) {
								System.out.println(">> 更新图片对象["+art.getTitle()+"|" + img.getId() + "]成功!");
							}
						}
					}
				}
				ARTICLE_COM_URL = null;
			}
		}
	}
	
	private static boolean download(ImageBean imgBean, String articleUrl) {
		PicfileBean bean = null;
		bean = new PicfileBean();
		String s_fileName = imgBean.getImgUrl().substring(
				imgBean.getImgUrl().lastIndexOf("/") + 1,
				imgBean.getImgUrl().length());
		String fileName = imgBean.getHttpUrl().substring(
				imgBean.getHttpUrl().lastIndexOf("/") + 1,
				imgBean.getHttpUrl().length());
		s_fileName = s_fileName.replace(".", "_s.");
		String date = CommonUtil.getDate("");
		String length = "0";
		try {
			if(null != ARTICLE_COM_URL){
			byte[] big = null;
			//不下载小图
			System.out.println("引用的地址："+imgBean.getCommentshowurl());
			if(null == imgBean.getCommentshowurl())
				return false;
			big = HttpClientUtils.getResponseBodyAsByte(imgBean.getCommentshowurl(), "rtime=4; ltime=1283479552367; cnzz_eid=5808015-1282816593-http%3A//www.tuku.cn/; virtualwall=vsid=0c8cafa6001de309645c11edffa3aa43; cnzz_a1235385=1; sin1235385=", imgBean.getHttpUrl());
			if(null == big)
				return false;
			length = String.valueOf(big.length);
			if(length.equalsIgnoreCase("20261")){
				return false;
			}
			if (client.get(CacheUtils.getBigPicFileKey(PIC_SAVE_PATH
					+ date + File.separator
					+ imgBean.getArticleId() + File.separator + fileName)) == null) {
				IOUtil.createFile(big, PIC_SAVE_PATH
						+ date + File.separator
						+ imgBean.getArticleId() + File.separator + fileName);
			}
			bean.setArticleId(imgBean.getArticleId());
			bean.setImageId(imgBean.getId());
			bean.setTitle(imgBean.getTitle());
			bean.setSmallName(imgBean.getImgUrl());
			bean.setName(CommonUtil.getDate("") + File.separator
					+ imgBean.getArticleId() + File.separator + fileName);
			bean.setUrl(PIC_SAVE_PATH);
			try {
				boolean b = picFiledao.insert(bean);
				if (b) {
					client.add(CacheUtils.getBigPicFileKey(bean.getUrl()
							+ bean.getName()), bean);
					client.add(CacheUtils.getSmallPicFileKey(bean.getUrl()
							+ bean.getSmallName()), bean);
				} else {
					return false;
				}
			} catch (Exception e) {
				System.out.println("数据库异常");
				e.printStackTrace();
				return false;
			}
			}
		} catch (IOException e) {
			System.out.println("网络连接，文件IO异常");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 验证地址是否为可以请求的地址
	 * @param url
	 * @return
	 */
	static boolean validationURL(String url){
		boolean b = false;
		for(String tmp:CATALOG_URL){
			if(url.startsWith(tmp) && !url.equalsIgnoreCase(tmp)){
				b = true;
				break;
			}
		}
		return b;
	}
}
