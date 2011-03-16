package com.ssi.htmlparser.zol;

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

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.CompositeTag;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;

import com.ssi.common.dal.domain.Article;
import com.ssi.common.dal.domain.Image;
import com.ssi.common.dal.domain.PictureFile;
import com.ssi.common.dal.domain.Website;
import com.ssi.common.utils.HttpClientUtils;
import com.ssi.common.utils.StringUtils;
import com.ssi.htmlparser.BaseHtmlParser;
import com.ssi.htmlparser.bean.LinkBean;
import com.ssi.htmlparser.bean.ResultBean;
import com.ssi.htmlparser.utils.CacheUtils;
import com.ssi.htmlparser.utils.CommonUtil;
import com.ssi.htmlparser.utils.IOUtil;

public class ZOLDESKParser extends BaseHtmlParser{

	 String URL_ = "http://desk.zol.com.cn/";

	 String URL = "http://desk.zol.com.cn";

	 String IMAGE_URL = "http://image6.tuku.cn/";
	
	 int D_PARENT_ID = 701;

	 String VISTA_URL = "http://vista.zol.com.cn";
	
	 List<LinkBean> LINKLIST = new ArrayList<LinkBean>();

	 List<Article> ARTICLELIST = new ArrayList<Article>();

	 HashMap<String, LinkBean> LINKHASH = new HashMap<String, LinkBean>();

	/**
	 * 获取分类链接
	 * 
	 * @param url
	 * @throws Exception
	 */
	 void catalog(String url) throws Exception { // Website bean
		Parser parser = new Parser();
		parser.setURL(url);
		parser.setEncoding("UTF-8");
		NodeFilter fileter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(fileter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("class", "logoMenu"));

		if (null != list && list.size() > 0) {
			// 主页中的第7个table
			Div table = (Div) list.elementAt(0);
			Parser p2 = new Parser();
			p2.setInputHTML(table.getChildrenHTML());
			NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
			NodeList linkList = p2.extractAllNodesThatMatch(linkFilter);
			if (linkList != null && linkList.size() > 0) {
				Website tmp = null;
				for (int i = 0; i < linkList.size(); i++) {
					LinkTag link = (LinkTag) linkList.elementAt(i);
					if (link.getLink().endsWith(".html")) {
						logger.info(link.getLinkText());
						tmp = new Website();
						tmp.setName(link.getLinkText());
						if (!link.getLink().startsWith("http://")) {
							logger.info(URL + link.getLink() + "\n");
							tmp.setUrl(URL + link.getLink());
						} else {
							logger.info(link.getLink() + "\n");
							tmp.setUrl(link.getLink());
						}
						tmp.setParentId(D_PARENT_ID);
						// boolean b = websiteDao.insert(tmp);
						// if (b) {
						// client.add(tmp.getUrl(), tmp.getUrl());
						// logger.info("成功");
						// } else {
						// logger.info("失败");
						// }
					}
				}
			}
			if (null != p2)
				p2 = null;
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
	 ResultBean hasPaging(String url, String cls, String value)
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
			if (null != list && list2.size() > 0) {
				String tmp = null;
				LinkBean l1 = null;
				for (int i = 0; i < list2.size(); i++) {
					l1 = new LinkBean();
					LinkTag link2 = (LinkTag) list2.elementAt(i);
					if (!link2.getLink().startsWith("http://")) {
						tmp = URL_ + link2.getLink();
					} else {
						tmp = link2.getLink();
					}
					tmp = tmp.replace("&amp;", "&");
					l1.setLink(tmp);
					l1.setTitle(link2.getLinkText());
					result.put(tmp, l1);
				}
				result.setBool(true);
			}else{
				LinkBean l1 = new LinkBean();;
				l1.setLink(url);
				l1.setTitle(url);
				result.put(url, l1);
				result.setBool(true);
			}
			if (null != p2)
				p2 = null;
		} else {
			LinkBean l1 = new LinkBean();;
			l1.setLink(url);
			l1.setTitle(url);
			result.put(url, l1);
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
	public  String ViewSourceFrame(String url1) throws Exception {
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
	 * 获取指定列表中页面源码
	 * 
	 * @param webList
	 */
	 void initHTML(List<Website> webList) {
		Long start = System.currentTimeMillis();
		for (Website bean : webList) {
			try {
				Long start1 = System.currentTimeMillis();
				String content = ViewSourceFrame(bean.getUrl());
				if (null != content && !"".equalsIgnoreCase(content)) {
					// processWithDoc(bean.getId(), content);
					Long end1 = System.currentTimeMillis();
					logger.info("单条耗时:" + (end1 - start1) + "长度："
							+ content.getBytes().length);
				}
			} catch (Exception e) {
				logger.info("Exception:" + e.getMessage());
				continue;
			}
		}
		Long end = System.currentTimeMillis();
		System.out.print("总耗时:" + (end - start));
	}

	/**
	 * 获取分类下数据
	 * 
	 * @param link
	 * @param webId
	 * @throws Exception
	 */
	public  void secondURL(LinkBean link, int webId) throws Exception {
		Parser parser = new Parser();
		parser.setURL(link.getLink());
		parser.setEncoding("UTF-8");

		// 获取指定ID的TableTag内容
		NodeFilter filter = new NodeClassFilter(CompositeTag.class);
		NodeList list = parser
				.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(new HasAttributeFilter("class", "lb"));
		if (list != null && list.size() > 0) {
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
						int key = articleDao.insert(article);
						if (key > 0) {
							client.put(url, url);
							article.setId(key);
							articleCache.put(CacheUtils.getArticleKey(key), article);
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
	public  boolean getImage(Article article) throws Exception {
		boolean b = false;
		ResultBean result = hasPaging(article.getArticleUrl(), "class",
				"page f14b");
		if (result.isBool()) {
			Iterator it = result.getMap().keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				logger.info("需要解析的URL:"+key);
				LinkBean link = result.getMap().get(key);
				b = getImage(link, article.getId());
			}
			b = true;
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
	public  boolean getImage(LinkBean link, int artId) throws Exception {

		Parser parser = new Parser();
		parser.setURL(link.getLink());
		parser.setEncoding("UTF-8");
		boolean resultB = true;
		// 获取指定ID的TableTag内容
		NodeFilter filter = new NodeClassFilter(Div.class);
		NodeList list = parser
				.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(new HasAttributeFilter("class", "lb"));
		if (null != list && list.size() > 0) {
			Parser p2 = null;
			String length = "0";
			int size = 0;
			for (int i = 0; i < list.size(); i++) {
				Div div = (Div) list.elementAt(i);
				p2 = new Parser();
				p2.setInputHTML(div.toHtml());

				NodeFilter filter2 = new NodeClassFilter(LinkTag.class);
				NodeList list2 = p2.extractAllNodesThatMatch(filter2);

				Image imgBean = null;
				String url = null;
				String imgSrc = null;
				if (null != list2 && list2.size() > 0) {
					LinkTag ltmp = (LinkTag) list2.elementAt(0);
					if (!ltmp.getLink().startsWith("http://")) {
						url = URL + ltmp.getLink();
					} else {
						url = ltmp.getLink();
					}

					imgSrc = getImageURL(url);
					if (null != imgSrc) {
						if (null == client.get(url)) {
							imgBean = new Image();
							imgBean = new Image();
							imgBean.setArticleId(artId);
							imgBean.setHttpUrl(imgSrc);
							NodeList tmp = ltmp.getChildren();
							if (tmp != null && tmp.size() > 0) {
								ImageTag imgTag = (ImageTag) tmp.elementAt(0);
								if (null != imgTag.getImageURL())
									imgBean.setImgUrl(imgTag.getImageURL());
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
							int key = imageDao.insert(imgBean);
							if (key > 0) {
								logger.info(imgBean.getTitle() + "\t|"
										+ url);
								client.put(url, url);
							}
						} else {
							logger.error(">> 已存在相同的内容 ["
									+ ltmp.getLinkText() + "]");
						}
					} else {
						resultB = false;
						break;
					}
				}

				if (null != p2)
					p2 = null;
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

			NodeFilter filter = new NodeClassFilter(CompositeTag.class);
			NodeList list = p1.extractAllNodesThatMatch(filter)
					.extractAllNodesThatMatch(
							new HasAttributeFilter("class", "a_fen12bi"));

			if (null != list && list.size() == 1) {
				LinkTag link = (LinkTag) list.elementAt(0);
				result = link.getLink();
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
	public  void getImage(LinkBean link, int webId, int articleId)
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
								String url = IMAGE_URL
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

	 /**
	  * 入口方法
	  */
	 public void init() {
		try {
			update();
			loadImg();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	 void update() throws Exception {
		List<Website> webList = websiteDao.findByFatherId(D_PARENT_ID);
		for (Website bean : webList) {
			if (!bean.getUrl().equalsIgnoreCase(
					"http://vista.zol.com.cn/desk.html")) {
				ResultBean result = hasPaging(bean.getUrl(), "class",
						"page f14b");
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
	}

	 void loadImg() throws Exception {
		// Website bean = websiteDao.findById(702);
		List<Website> webList = websiteDao.findByFatherId(D_PARENT_ID);
		HashMap map = new HashMap();
		for (Website bean : webList) {
			map.put("webId",bean.getId());
			map.put("text", "NED");
			List<Article> list = articleDao.find(map);
			map.clear();
			for (Article art : list) {
				map.put("articleId", art.getId());
				List<Image> imgList = imageDao.find(map);
				logger.info(">> zol.com.cn 记录[" + art.getTitle() + "|"+art.getId()+"]下的图片数量["+imgList.size()+"]");
				if(imgList.size() == 0 ){
					if (!art.getArticleUrl().startsWith("http://vista.zol.com.cn")) {
						if (getImage(art)) {
							art.setText("FD");
							if (articleDao.update(art) > 0) {
								logger.info(">> zol.com.cn 更新记录[" + art.getTitle() + "|"+art.getId()+"]成功");
							}
						}
					}
				}
			}
		}
	}

	 void vistDesk() throws Exception {
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
						url = VISTA_URL + ltmp.getLink();
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
}