package com.ssi.htmlparser.kpbz;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
import org.htmlparser.util.NodeList;

import com.ssi.common.dal.domain.Article;
import com.ssi.common.dal.domain.Image;
import com.ssi.common.dal.domain.Website;
import com.ssi.common.utils.HttpClientUtils;
import com.ssi.htmlparser.BaseHtmlParser;
import com.ssi.htmlparser.bean.LinkBean;
import com.ssi.htmlparser.bean.ResultBean;
import com.ssi.htmlparser.utils.CacheUtils;
import com.ssi.htmlparser.utils.CommonUtil;

public class KPBZParser extends BaseHtmlParser{

	String URL_ = "http://www.kpbz.net/";

	String URL = "http://www.kpbz.net";

	Integer D_PARENT_ID = 1100;

	List<LinkBean> LINKLIST = new ArrayList<LinkBean>();

	List<Article> ARTICLELIST = new ArrayList<Article>();

	HashMap<String, LinkBean> LINKHASH = new HashMap<String, LinkBean>();
	
	/**
	 * 获取分类下的分页信息
	 * 
	 * @param url
	 * @param attribute
	 * @param value
	 * @return
	 * @throws Exception
	 */
	private ResultBean hasPaging(String url, String cls, String value)
			throws Exception {
		boolean b = false;
		ResultBean result = new ResultBean();
		Parser parser = new Parser();
		parser.setURL(url);

		// 获取指定ID的DIV内容
		NodeFilter filter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(new HasAttributeFilter(cls, value));
		if (list != null && list.size() > 0) {
			Parser p2 = new Parser();
			p2.setInputHTML(list.toHtml());

			NodeFilter filter2 = new NodeClassFilter(LinkTag.class);
			NodeList list2 = p2.extractAllNodesThatMatch(filter2);
			String tmp = null;
			LinkBean l1 = null;
			if (null != list && list2.size() > 0) {
				String vurl = url.substring(0,url.lastIndexOf("/")+1);
				for (int i = 0; i < list2.size(); i++) {
					l1 = new LinkBean();
					LinkTag link2 = (LinkTag) list2.elementAt(i);
					if(link2.getLink().equalsIgnoreCase("#")){
						tmp = url;
					}else{
						if (!link2.getLink().startsWith("http://")) {
							tmp = vurl + link2.getLink();
						} else {
							tmp = link2.getLink();
						}
					}
					
					l1.setLink(tmp);
					l1.setTitle(link2.getLinkText());
					result.put(tmp, l1);
				}
				result.setBool(true);
			}else{
				l1 = new LinkBean();
				l1.setLink(url);
				l1.setTitle("Title");
				result.put(tmp, l1);
				result.setBool(true);
			}
			if (null != p2)
				p2 = null;
		} else {
			result.setBool(b);
		}
		return result;
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
	private ResultBean hasPaging2(String url)
			throws Exception {
		ResultBean result = new ResultBean();
		Parser parser = new Parser();
		parser.setURL(url);

		// 获取指定ID的DIV内容
		NodeFilter filter = new NodeClassFilter(SelectTag.class);
		NodeList list = parser.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(new HasAttributeFilter("name", "sldd"));
		String tmp = null;
		LinkBean l1 = null;
		if (list != null && list.size() > 0) {
			SelectTag select = (SelectTag)list.elementAt(0);
			OptionTag[] options = select.getOptionTags();
			for(OptionTag option:options){
				l1 = new LinkBean();
				if (!option.getValue().startsWith("http://")) {
					tmp = url + option.getValue();
				} else {
					tmp = option.getValue();
				}
				l1.setLink(tmp);
				l1.setTitle("Title");
				result.put(tmp, l1);
			}
			result.setBool(true);
		} else {
			l1 = new LinkBean();
			l1.setLink(url);
			l1.setTitle("Title");
			result.put(tmp, l1);
			result.setBool(true);
		}
		return result;
	}
	
	/**
	 * 获取指定URL下的源码
	 * 
	 * @param url1
	 * @return
	 */
	public String ViewSourceFrame(String url1) throws Exception {
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

	/**
	 * 获取分类下数据
	 * 
	 * @param link
	 * @param webId
	 * @throws Exception
	 */
	public void secondURL(LinkBean link, int webId) throws Exception {
		Parser parser = new Parser();
		parser.setURL(link.getLink());
		parser.setEncoding("UTF-8");

		// 获取指定ID的TableTag内容
		NodeFilter filter = new NodeClassFilter(CompositeTag.class);
		NodeList list = parser
				.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(new HasAttributeFilter("class", "pimg"));
		if (list != null && list.size() > 0) {
			Parser p2 = null;
			for (int i = 0; i < list.size(); i++) {
				Article article = null;
				String url = null;
				if (null != list && list.size() > 0) {
					LinkTag ltmp = (LinkTag) list.elementAt(i);
					if (!ltmp.getLink().startsWith("http://")) {
						url = URL + ltmp.getLink();
					} else {
						url = ltmp.getLink();
					}

					if (null == client.get(url)) {
						article = new Article();
						article.setWebId(webId);
						article.setArticleUrl(url);
						article.setText("NED"); // NED_WALLCOO
						article.setIntro("");
						NodeList tmp = ltmp.getChildren();
						if (tmp != null && tmp.size() > 0) {
							ImageTag imgTag = (ImageTag) tmp.elementAt(0);
							logger.info(imgTag.getAttribute("alt")
									+ "|[" + url + "]");
							article.setActicleXmlUrl(imgTag.getImageURL());
							article.setTitle(imgTag.getAttribute("alt")); // NT:No
																			// Title
						}
						
						logger.info("*****************Start***************");
						logger.info("ArticleUrl:"+article.getArticleUrl());
						logger.info("ActicleXmlUrl:"+article.getActicleXmlUrl());
						logger.info("Title:"+article.getTitle());
						logger.info("Webid:"+article.getWebId());
						logger.info("Text:"+article.getText());
						logger.info("*****************End***************\n");
						
						int key = articleDao.insert(article);
						if (key > 0) {
							logger.info(ltmp.getLinkText() + "\t|" + url);
							article.setId(key);
							articleCache.put(CacheUtils.getArticleKey(key), article);
							client.put(url, url);
						}
					} else {
						logger.error(">> 已存在相同的内容 [" + ltmp.getLinkText()
								+ "]");
					}
				}

				if (null != p2)
					p2 = null;
			}
		}
		if (null != parser)
			parser = null;
	}

	/**
	 * 获取图片地址下数据
	 * 
	 * @param link
	 * @param webId
	 * @throws Exception
	 */
	public boolean getImage(Article article) throws Exception {
		boolean b = true;
		ResultBean result = hasPaging(article.getArticleUrl(),"id","pagelist");
		if (result.isBool()) {
			Iterator it = result.getMap().keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				LinkBean link = result.getMap().get(key);
				b = getImage(link, article.getId());
			}
		}

		return b;
	}

	/**
	 * 获取分类下数据
	 * 
	 * @param link
	 * @param webId
	 * @throws Exception
	 */
	public boolean getImage(LinkBean link, int artId) throws Exception {

		Parser parser = new Parser();
		parser.setURL(link.getLink());
		parser.setEncoding("UTF-8");
		boolean resultB = true;
		// 获取指定ID的TableTag内容
		NodeFilter filter = new NodeClassFilter(CompositeTag.class);
		NodeList list = parser
				.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(new HasAttributeFilter("id", "imglist"));
		if (null != list && list.size() > 0) {
			Parser p3 = new Parser();
			p3.setInputHTML(list.toHtml());

			NodeFilter filter3 = new NodeClassFilter(LinkTag.class);
			NodeList list3 = p3.extractAllNodesThatMatch(filter3);
			String length = "0";
			int size = 0;
			
			if(null == list3 && list3.size() == 0){
				return false;
			}
			
			for (int i = 0; i < list3.size(); i++) {
				Image imgBean = null;
				String url = null;
				String imgSrc = null;
					LinkTag ltmp = (LinkTag) list3.elementAt(i);
					if (!ltmp.getLink().startsWith("http://")) {
						url = URL + ltmp.getLink();
					} else {
						url = ltmp.getLink();
					}

					imgSrc = getImageURL(url);
					if (null != imgSrc) {
						if (null == client.get(imgSrc)) {
							imgBean = new Image();
							imgBean = new Image();
							imgBean.setArticleId(artId);
							imgBean.setHttpUrl(imgSrc);
							NodeList tmp = ltmp.getChildren();
							if (tmp != null && tmp.size() > 0) {
								ImageTag imgTag = (ImageTag) tmp.elementAt(0);
								if (null != imgTag.getImageURL())
									imgBean.setImgUrl(URL+imgTag.getImageURL());
								if (null != imgTag.getAttribute("alt"))
									imgBean
											.setTitle(imgTag
													.getAttribute("alt"));
								else
									imgBean.setTitle("NT:"
											+ CommonUtil.getDateTimeString());
							}
							length = HttpClientUtils.getHttpHeaderResponse(
									imgSrc, "Content-Length");
							
							logger.info("Title:"+imgBean.getTitle());
							logger.info("ArticleId:"+imgBean.getArticleId());
							logger.info("大图地址:"+imgBean.getHttpUrl());
							logger.info("小图地址:"+imgBean.getImgUrl());
							imgBean.setLink("NED");
							try {
								size = Integer.parseInt(length);
								imgBean.setSize(Long.valueOf(size));
								imgBean.setStatus(3);
							} catch (Exception e) {
								e.printStackTrace();
								logger.error(">> IMAGE SIZE ERROR");
								size = 0;
								imgBean.setSize(0l);
								imgBean.setStatus(1);
							}
							imgBean.setCommentshowurl(link.getLink());
							int key = imageDao.insert(imgBean);
							if (key > 0) {
								logger.info(imgBean.getTitle()+"\t|"+ imgSrc);
								client.put(imgSrc, imgSrc);
							}
						} else {
							logger.error(">> 已存在相同的内容 ["
									+ ltmp.getLinkText() + "]");
						}
					} else {
						resultB = false;
						break;
					}
				if (null != p3)
					p3 = null;
			}
		}

		return resultB;
	}

	/**
	 * 获取图片实际地址
	 * 
	 * @param url
	 * @return
	 */
	String getImageURL(String url) {
		String result = null;
		try {
			Parser p1 = new Parser();
			p1.setURL(url);

			NodeFilter filter = new NodeClassFilter(Div.class);
			NodeList list = p1.extractAllNodesThatMatch(filter)
					.extractAllNodesThatMatch(
							new HasAttributeFilter("id", "pic"));

			if (null != list && list.size() == 1) {
				Div div = (Div) list.elementAt(0);
				
				Parser p3 = new Parser();
				p3.setInputHTML(div.toHtml());

				NodeFilter filter3 = new NodeClassFilter(LinkTag.class);
				NodeList list3 = p3.extractAllNodesThatMatch(filter3);
				
				if(null == list3){
					return null;
				}
				LinkTag link = (LinkTag)list3.elementAt(0);
				if(!link.getLink().startsWith("http://")){
					result = URL+link.getLink();
				}else{
					result = link.getLink();
				}
				
				if(null != p3 )
					p3 = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取图片地址下数据
	 * 
	 * @param link
	 * @param webId
	 * @throws Exception
	 */
	public void getImage(LinkBean link, int webId, int articleId)
			throws Exception {
		Parser parser = new Parser();
		parser.setURL(link.getLink());
		parser.setEncoding("UTF-8");
		String length = "0";
		int size = 0;
		// 获取指定ID的TableTag内容
		NodeFilter filter = new NodeClassFilter(TableTag.class);
		NodeList list = parser.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("id", "DataList1"));
		if (list != null && list.size() > 0) {
			NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
			NodeFilter imageFilter = new NodeClassFilter(ImageTag.class);
			OrFilter lastFilter = new OrFilter();
			lastFilter
					.setPredicates(new NodeFilter[] { linkFilter, imageFilter });
			Parser p2 = new Parser();
			p2.setInputHTML(list.toHtml());
			p2.setEncoding("UTF-8");
			NodeList list4 = p2.parse(lastFilter);
			if (list4 != null || list4.size() > 0) {
				for (int i = 0; i < list4.size(); i++) {
					// 地址
					if (list4.elementAt(i) instanceof LinkTag) {
						LinkTag nl = (LinkTag) list4.elementAt(i);
						NodeList cnl = nl.getChildren();
						if (cnl != null && cnl.size() > 0) {
							// 小图 可能存在部分图片无法访问，需要判断
							Image imgBean = null;
							if (cnl.elementAt(0) instanceof ImageTag) {
								ImageTag it = (ImageTag) cnl.elementAt(0);
								String url = ""
										+ getImageUrl(nl.getLink());
								if (null == client.get(url)) {
									length = HttpClientUtils
											.getHttpHeaderResponse(url,
													"Content-Length");
									imgBean = new Image();
									imgBean.setArticleId(articleId);
									imgBean.setHttpUrl(url);
									imgBean.setImgUrl(it.getImageURL());
									imgBean.setTitle(it.getAttribute("alt"));
									try {
										size = Integer.parseInt(length);
										imgBean.setSize(Long.valueOf(size));
										imgBean.setStatus(3);
									} catch (Exception e) {
										e.printStackTrace();
										System.err
												.println(">> IMAGE SIZE ERROR");
										size = 0;
										imgBean.setSize(0l);
										imgBean.setStatus(1);
									}
									imgBean.setLink("NED");
									imgBean.setOrderId(i);
									imgBean.setArticleId(articleId);
									// HttpClientUtils
									int result = imageDao.insert(imgBean);
									if (result > 0) {
										logger.info(">> add article["
												+ articleId + "] image id["
												+ result + "] to DB");
										imgBean.setId(result);
										client.put(url, url);
									} else {
										logger.error(">> 未添加[" + url
												+ "]到数据库中");
									}
								} else {
									logger.error(">> 缓存中已存在相同的内容 ["
											+ nl.getLink() + "]");
								}
							}
						}
					}
					Thread.sleep(1000);
				}
			}
		}
	}

	String getImageUrl(String link) {
		int start = link.indexOf("=");
		int end = link.length();
		String imgUrl = link.substring(start + 1, end);
		return imgUrl;
	}

	String getTitle(String title, String defaultTitle) {
		if (null == title || "".equalsIgnoreCase(title)) {
			return defaultTitle + ":" + System.currentTimeMillis();
		}
		return title;
	}

	@Override
	public void init(){
		try{
			update();
			loadImg();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void update() throws Exception {
		List<Website> webList = websiteDao.findByFatherId(D_PARENT_ID);
		for (Website bean : webList) {
			ResultBean result = hasPaging2(bean.getUrl());
			if (result.isBool()) {
				Iterator it = result.getMap().keySet().iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					LinkBean link = result.getMap().get(key);
					try {
						secondURL(link, bean.getId());
					} catch (Exception e) {
						e.printStackTrace();
						logger.info("key:" + key);
						continue;
					}
				}
			}
		}
	}

	public void loadImg() throws Exception {
		List<Website> webList = websiteDao.findByFatherId(D_PARENT_ID);
		for (Website bean : webList) {
			HashMap map = new HashMap();
			map.put("webId", bean.getId());
			map.put("text", "NED");
			List<Article> list = articleDao.find(map);
			for (Article art : list) {
				if (getImage(art)) {
					art.setText("FD");
					if (articleDao.update(art) > 0) {
						System.out
								.println("更新记录[" + art.getTitle() + "]成功");
					}
				}
			}
		}
	}

	public void vistDesk() throws Exception {
		Parser parser = new Parser();
		parser.setURL("http://vista.zol.com.cn/desk.html");
		parser.setEncoding("GB2312");

		// 获取指定ID的TableTag内容
		NodeFilter filter = new NodeClassFilter(CompositeTag.class);
		NodeList list = parser.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("class", "deskP1"));
		if (list != null && list.size() > 0) {

			// logger.info(list.toHtml());
			Parser p2 = null;
			for (int i = 0; i < list.size(); i++) {
				Div div = (Div) list.elementAt(i);
				p2 = new Parser();
				p2.setInputHTML(div.toHtml());

				NodeFilter filter2 = new NodeClassFilter(LinkTag.class);
				NodeList list2 = p2.extractAllNodesThatMatch(filter2);

				Article article = null;
				String url = null;
				if (null != list2 && list2.size() > 0) {
					LinkTag ltmp = (LinkTag) list2.elementAt(0);
					if (!ltmp.getLink().startsWith("http://")) {
						url = URL_ + ltmp.getLink();
					} else {
						url = ltmp.getLink();
					}

					logger.info(ltmp.getLinkText() + "|[" + url + "]");

					if (null == client.get(url)) {
						article = new Article();
						article.setWebId(713);
						article.setArticleUrl(url);
						article.setText("NED"); // NED_WALLCOO
						article.setIntro("");
						NodeList tmp = ltmp.getChildren();
						if (tmp != null && tmp.size() > 0) {
							ImageTag imgTag = (ImageTag) tmp.elementAt(0);
							if (null != imgTag.getImageURL())
								article.setActicleXmlUrl(imgTag.getImageURL());
							if (null == imgTag.getAttribute("alt")) {
								article.setTitle("NT:"
										+ CommonUtil.getDateTimeString());
							} else {
								article.setTitle(imgTag.getAttribute("alt"));
							}

							int key = articleDao.insert(article);
							if (key > 0) {
								System.out.print(">> 连接名称:"
										+ ltmp.getLinkText());
								logger.info(">> URL:" + url);
								client.put(url, url);
							}
						} else {
							logger.error(">> 已存在相同的内容 ["
									+ ltmp.getLinkText() + "]");
						}
					}

					if (null != p2)
						p2 = null;
				}
			}
			if (null != parser)
				parser = null;
		}
	}

	void test() throws Exception{
		ResultBean result = hasPaging2("http://www.kpbz.net/bizhi/anime/");
		if (result.isBool()) {
			Iterator it = result.getMap().keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				logger.info("key:"+key);
				LinkBean link = result.getMap().get(key);
				try {
					secondURL(link, 1201);
				} catch (Exception e) {
					e.printStackTrace();
					logger.info("key:" + key);
					break;
				}
				break;
			}
		}
	}
}
