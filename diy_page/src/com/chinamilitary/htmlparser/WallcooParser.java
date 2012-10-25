package com.chinamilitary.htmlparser;

import com.chinamilitary.bean.Article;
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
import com.chinamilitary.threadpool.ThreadPoolManager;
import com.chinamilitary.util.CacheUtils;
import com.chinamilitary.util.HttpClientUtils;
import com.chinamilitary.util.IOUtil;
import com.chinamilitary.util.StringUtils;
import com.common.Constants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.spi.LoggerFactory;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.OptionTag;
import org.htmlparser.tags.SelectTag;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

//import com.chinamilitary.util.StringUtils;

public class WallcooParser {

    static Log logger = LogFactory.getLog(WallcooParser.class);
	
	static final String PIC_SAVE_PATH = Constants.FILE_SERVER;
	
	static Integer D_PARENT_ID = 125;
	
	static Integer D_ENG_PARENT_ID = 1600;

	private static final String URL = "http://www.wallcoo.com/";

	private static final String URL_ENG = "http://www.wallcoo.net/";
	
	private static final String URL_ = "http://www.wallcoo.com";

	private static HashMap<String, LinkBean> LINKHASH = new HashMap<String, LinkBean>();

	private static HashMap<String, LinkBean> SECONDLINKHASH = new HashMap<String, LinkBean>();

	private static HashMap<String, ResultBean> PAGELINKHASH = new HashMap<String, ResultBean>();

	private static int COUNT = 0;

	private static ThreadPoolManager manager = null;

	private static ImageDao imageDao = DAOFactory.getInstance().getImageDao();

	private static ArticleDao articleDao = DAOFactory.getInstance()
			.getArticleDao();

	private static WebSiteDao wesiteDao = DAOFactory.getInstance()
			.getWebSiteDao();

	private static PicFileDao picFileDao = DAOFactory.getInstance()
			.getPicFileDao();

	private static MemcacheClient client = MemcacheClient.getInstance();

	/**
	 * 首页分类
	 * 
	 * @throws Exception
	 */
	public static void catelogy(String url) throws Exception {
		WebSiteDao dao = DAOFactory.getInstance().getWebSiteDao();
		Parser parser = new Parser();
		parser.setURL(url);
		parser.setEncoding("GB2312");
		NodeFilter fileter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(fileter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("class", "sort2")); // id
		if (list != null && list.size() > 0) {
			Parser p1 = new Parser();
			p1.setInputHTML(list.toHtml());
			p1.setEncoding("GB2312");
			NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
			NodeList linkList = p1.extractAllNodesThatMatch(linkFilter);
			if (linkList != null && linkList.size() > 0) {
				WebsiteBean tmp = null;
				for (int i = 0; i < linkList.size(); i++) {
					LinkTag link = (LinkTag) linkList.elementAt(i);
					System.out.println(link.getLinkText());
					System.out.println(URL_ENG + link.getLink() + "\n");
//					tmp = new WebsiteBean();
//					tmp.setName(link.getLinkText());
//					tmp.setUrl(URL_ENG + link.getLink());
//					tmp.setParentId(D_ENG_PARENT_ID);
//					boolean b = dao.insert(tmp);
//					if (b) {
//						System.out.println("成功");
//					} else {
//						System.out.println("失败");
//					}
				}
			}
		}
	}
	
	/**
	 * 首页分类
	 * 
	 * @throws Exception
	 */
	public static void catelogy(WebsiteBean bean) throws Exception {
		WebSiteDao dao = DAOFactory.getInstance().getWebSiteDao();
		Parser parser = new Parser();
		parser.setURL(bean.getUrl());
		parser.setEncoding("GB2312");

		NodeFilter fileter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(fileter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("id", "tbc_02")); // id
		// blk_lmdh_01
		// "class",
		// "nav"
		if (list != null && list.size() > 0) {
			Parser p1 = new Parser();
			p1.setInputHTML(list.toHtml());
			p1.setEncoding("GB2312");
			NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
			NodeList linkList = p1.extractAllNodesThatMatch(linkFilter);
			if (linkList != null && linkList.size() > 0) {
				WebsiteBean tmp = null;
				for (int i = 0; i < linkList.size(); i++) {
					LinkTag link = (LinkTag) linkList.elementAt(i);
					System.out.println(link.getLinkText());
					System.out.println(URL_ + link.getLink() + "\n");
//					tmp = new WebsiteBean();
//					tmp.setName(link.getLinkText());
//					tmp.setUrl(link.getLink());
//					tmp.setParentId(bean.getId());
//					boolean b = dao.insert(tmp);
//					if (b) {
//						System.out.println("成功");
//					} else {
//						System.out.println("失败");
//					}
				}
			}
		}
	}

	/**
	 * 分页判断
	 * 
	 * @param url
	 * @param attribute
	 * @param value
	 * @return
	 * @throws Exception
	 */
	private static ResultBean hasPaging(WebsiteBean bean, String attribute,
			String value) throws Exception {
		ResultBean result = new ResultBean();
		Parser parser = new Parser();
		parser.setURL(bean.getUrl());
		parser.setEncoding("GB2312");

		// 获取指定ID的DIV内容
		NodeFilter filter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter(attribute, value));
		if (list != null && list.size() > 0) {
			Div div = (Div) list.elementAt(0);
			String tmp = div.getStringText().substring(0,div.getStringText().length());
			int pageSize = Integer.valueOf(tmp.substring(tmp.indexOf("/") + 1,
					tmp.indexOf("]")));
			LinkBean l1 = null;
			for (int i = 1; i < pageSize + 1; i++) {
				l1 = new LinkBean();
				l1.setLink(bean.getUrl()
						.replace(".h", String.valueOf(i) + ".h"));
				result.add(l1);
			}
			result.setBool(true);
		}else{
			LinkBean l1 = null;
			l1 = new LinkBean();
			l1.setLink(bean.getUrl());
			result.add(l1);
			result.setBool(true);
		}

		return result;
	}

	/**
	 * 获取分类下数据
	 * 
	 * @param link
	 * @param webId
	 * @throws Exception
	 */
	public static void secondURL(LinkBean link, int webId) throws Exception {
		Parser parser = new Parser();
		parser.setURL(link.getLink());
		parser.setEncoding("GB2312");

		// 获取指定ID的DIV内容
		NodeFilter filter = new NodeClassFilter(TableTag.class);
		NodeList list = parser.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(new HasAttributeFilter("id", "list"));
		if (list != null && list.size() > 0) {
			NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
			NodeFilter imageFilter = new NodeClassFilter(ImageTag.class);
			OrFilter lastFilter = new OrFilter();
			lastFilter
					.setPredicates(new NodeFilter[] { linkFilter, imageFilter });
			Parser p2 = new Parser();
			p2.setInputHTML(list.toHtml());
			p2.setEncoding("GB2312");
			NodeList list4 = p2.parse(lastFilter);
			if (list4 != null || list4.size() > 0) {
				for (int i = 0; i < list4.size(); i++) {
					// 地址
					if (list4.elementAt(i) instanceof LinkTag) {
						LinkTag nl = (LinkTag) list4.elementAt(i);
						NodeList cnl = nl.getChildren();
						if (cnl != null && cnl.size() > 0) {
							// 小图 可能存在部分图片无法访问，需要判断
							Article article = null;
							if (cnl.elementAt(1) instanceof ImageTag) {
								String url = URL_ + nl.getLink();
									article = new Article();
									article.setWebId(webId);
									article.setArticleUrl(url);
									ImageTag it = (ImageTag) cnl.elementAt(1);
									article.setTitle(it.getAttribute("alt"));
									article.setText("NED"); //NED_WALLCOO
									article.setIntro(getArticleText(url));
									int key = articleDao.insert(article);
									if (key > 0) {
									    article.setId(key);
                                        try{
                                            ResultBean result = hasPagingWithArticleSelectTag(article.getArticleUrl());
                                            if(result.isBool()){
                                                logger.info(">a :正在解析["+article.getId()+"|"+article.getArticleUrl()+"],分页数量为:"+result.getList().size());
                                                for(LinkBean lb:result.getList()){
                                                    getPicUrlAndThum(article.getId(),lb.getLink());
                                                }
                                                article.setText("FD");
                                            }
                                        }catch(Exception e){
                                            article.setText("NED");
                                        }
                                        if(!articleDao.update(article)){
                                            logger.debug(" >> 站点:["+article.getWebId()+"],更新记录"+article.getTitle()+"失败");
                                        }
									} else {
                                        logger.error("Add Article["+article.getArticleUrl()+"] Failure!");
									}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 获取分类下数据
	 * 
	 * @param link
	 * @param webId
	 * @throws Exception
	 */
	public static void secondURL(String url) throws Exception {
		Parser parser = new Parser();
		parser.setURL(url);
		parser.setEncoding("GB2312");

		// 获取指定ID的DIV内容
		NodeFilter filter = new NodeClassFilter(TableTag.class);
		NodeList list = parser.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(new HasAttributeFilter("id", "list"));
		if (list != null && list.size() > 0) {
			NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
			NodeFilter imageFilter = new NodeClassFilter(ImageTag.class);
			OrFilter lastFilter = new OrFilter();
			lastFilter
					.setPredicates(new NodeFilter[] { linkFilter, imageFilter });
			Parser p2 = new Parser();
			p2.setInputHTML(list.toHtml());
			p2.setEncoding("GB2312");
			NodeList list4 = p2.parse(lastFilter);
			if (list4 != null || list4.size() > 0) {
				// System.out.println(list4.size());
				for (int i = 0; i < list4.size(); i++) {
					// 地址
					if (list4.elementAt(i) instanceof LinkTag) {
						LinkTag nl = (LinkTag) list4.elementAt(i);
						NodeList cnl = nl.getChildren();
						if (cnl != null && cnl.size() > 0) {
							// 小图 可能存在部分图片无法访问，需要判断
							if (cnl.elementAt(1) instanceof ImageTag) {
								ImageTag it = (ImageTag) cnl.elementAt(1);
								System.out.print("link:" + URL_ + nl.getLink());
								System.out.print("\ttitle:"
										+ it.getAttribute("alt"));
								System.out.println("\timageUrl:" + URL_
										+ it.getImageURL());
								System.out.println("添加成功!!!");
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 获取文章下图片下拉框页面
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	static String thirdURL(String url) throws Exception {
		Parser parser = new Parser();
		parser.setURL(url);
		parser.setEncoding("GB2312");

		// 获取指定ID的DIV内容
		NodeFilter filter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("id", "pic_intro"));
		LinkTag link = null;
		if (list != null && list.size() > 0) {
			Parser p1 = new Parser();
			p1.setInputHTML(list.toHtml());
			p1.setEncoding("GB2312");

			NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
			NodeList nodes = p1.extractAllNodesThatMatch(linkFilter);
			if (nodes != null && nodes.size() > 0) {
				if (nodes.elementAt(1) instanceof LinkTag) {
					link = (LinkTag) nodes.elementAt(1);
					// System.out.print(link.getLinkText());
					// System.out.println(link.getLink());
				}
			}
		}
		if (link != null) {
			if (url.indexOf("/index.html") > 0) {
				return url.replace("index.html", link.getLink());
			} else {
				return null;
			}
		} else {
			return null;
		}
		// return url.replace("index.html", link.getLink());
	}

	/**
	 * 获得文章的下拉分页
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	static ResultBean hasPagingWithSelectTag(String url) throws Exception {
		System.out.println("hasPagingWithSelectTag.url:" + url);
		ResultBean result = new ResultBean();
		Parser parser = new Parser();
		parser.setURL(url);
		parser.setEncoding("GB2312");

		NodeFilter selectFilter = new NodeClassFilter(SelectTag.class);
		NodeList selectList = parser.extractAllNodesThatMatch(selectFilter);
		if (selectList != null && selectList.size() > 0) {
			for (int i = 0; i < selectList.size(); i++) {
				SelectTag select = (SelectTag) selectList.elementAt(i);
				if (select
						.toHtml()
						.contains(
								"<select name=\"imagelist\" class=\"imagequicklist\" onChange=\"gotonewpage(this)\">")) {
					result.setBool(true);
					Parser p1 = new Parser();
					p1.setInputHTML(select.toHtml());
					NodeFilter optionFilter = new NodeClassFilter(
							OptionTag.class);
					NodeList optionList = p1
							.extractAllNodesThatMatch(optionFilter);
					if (optionList != null && optionList.size() > 1) {
						LinkBean oplink = null;
						for (int j = 0; j < optionList.size(); j++) {
							OptionTag option = (OptionTag) optionList
									.elementAt(j);
							oplink = new LinkBean();
							oplink.setLink(url.replace(url.substring(url
									.lastIndexOf("/") + 1, url.length()),
									option.getValue()));
							result.add(oplink);
						}
					}
					break;
				}
			}
		}
		return result;
	}

	/**
	 * 获得文章的下的图片拉分页
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	static ResultBean hasPagingWithArticleSelectTag(String url)
			throws Exception {
		LinkBean oplink = null;
		ResultBean result = new ResultBean();
		Parser parser = new Parser();
		parser.setURL(url);
		parser.setEncoding("GB2312");

		NodeFilter selectFilter = new NodeClassFilter(SelectTag.class);
		NodeList selectList = parser.extractAllNodesThatMatch(selectFilter);
		if (selectList != null && selectList.size() > 0) {
			for (int i = 0; i < selectList.size(); i++) {
				SelectTag select = (SelectTag) selectList.elementAt(i);
				if (select != null
						&& select
								.getText()
								.contains(
										"select name=\"indexlist\" class=\"indexquicklist\" onChange=\"gotonewpage(this)\"")) {
					result.setBool(true);
					Parser p1 = new Parser();
					p1.setInputHTML(select.toHtml());
					NodeFilter optionFilter = new NodeClassFilter(
							OptionTag.class);
					NodeList optionList = p1
							.extractAllNodesThatMatch(optionFilter);
					if (optionList != null && optionList.size() > 1) {
						for (int j = 0; j < optionList.size(); j++) {
							OptionTag option = (OptionTag) optionList
									.elementAt(j);
							oplink = new LinkBean();
							oplink.setLink(url.replace(url.substring(url
									.lastIndexOf("/") + 1, url.length()),
									option.getValue()));
							result.add(oplink);
						}
					}
					break;
				}
			}
		}else{
			result.setBool(true);
			oplink = new LinkBean();
			oplink.setLink(url);
			result.add(oplink);
		}
		return result;
	}

	/**
	 * 从文章地址获取图片缩略图和地址
	 * 
	 * @param articleId
	 * @param url
	 */
	static boolean getPicUrlAndThum(int articleId, String url) throws Exception { // String
		long start = System.currentTimeMillis();
		// url
		boolean b = true;
		if (articleId < 0) {
			logger.error("条件:文章ID["+articleId+"]不满足，退出");
			return false;
		}
		if (null == url) { //StringUtils.isEmpty(url)
            logger.error("条件:地址["+url+"]不满足，退出");
			return false;
		}
			Parser parser = null;
			parser = new Parser();
			parser.setURL(url);
			parser.setEncoding("GB2312");

			NodeFilter filter = new NodeClassFilter(Div.class);
			NodeList list = parser.extractAllNodesThatMatch(filter)
					.extractAllNodesThatMatch(
							new HasAttributeFilter("id", "thums"));

			if (list != null) {
				logger.debug("\t解析网页:"+(System.currentTimeMillis()-start));
				NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
				NodeFilter imageFilter = new NodeClassFilter(ImageTag.class);
				OrFilter lastFilter = new OrFilter();
				lastFilter.setPredicates(new NodeFilter[] { linkFilter,
						imageFilter });

				Parser parser2 = new Parser();
				parser2.setInputHTML(list.toHtml());
				parser2.setEncoding("GB2312");

				NodeList list2 = parser2.parse(lastFilter);
				ImageBean imageBean = null;
				if (list2 != null && list2.size() > 0) {
					for (int i = 0; i < list2.size(); i++) {
						if (list2.elementAt(i) instanceof LinkTag) {
							start = System.currentTimeMillis();
							LinkTag nl = (LinkTag) list2.elementAt(i);
							NodeList cnl = nl.getChildren();
							if (cnl != null && cnl.size() > 0) {
								// 小图
								if (cnl.elementAt(0) instanceof ImageTag) {
									imageBean = new ImageBean();
									ImageTag it = (ImageTag) cnl.elementAt(0);
									String imgUrl = url.replace(url.substring(
											url.lastIndexOf("/") + 1, url
													.length()), it
											.getImageURL());
									String picUrl = url.replace(url.substring(
											url.lastIndexOf("/") + 1, url
													.length()), nl.getLink());
									imageBean
											.setTitle(it.getAttribute("alt") == null ? ""
													: it.getAttribute("title"));
									imageBean.setImgUrl(imgUrl);
									imageBean.setLink(picUrl); //picUrl
									imageBean.setArticleId(articleId);
									start = System.currentTimeMillis();
									if (!getPicUrl(imageBean)) {
										b = false;
										// break;
									}
									logger.debug("\t>>>> getPicUrlAndThum.getPicUrl.end:"+(System.currentTimeMillis()-start));
								}
							}
							logger.debug("\t>>>> getPicUrlAndThum.end:"+(System.currentTimeMillis()-start));
						}

					}
				}

			}
		return true;
	}

	/**
	 * 获取不同分辨率的图片
	 * 
	 * @param url
	 */
	static boolean getPicUrl(ImageBean image) {
		Parser parser = null;
		boolean b = true;
		String url = image.getLink();
		try {
			parser = new Parser();
			parser.setURL(url);
			parser.setEncoding("GB2312");

			NodeFilter filter = new NodeClassFilter(Div.class);
			NodeList list = parser.extractAllNodesThatMatch(filter)
					.extractAllNodesThatMatch(
							new HasAttributeFilter("id", "right"));

			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					Div div = (Div) list.elementAt(i);
					Parser parser2 = new Parser();
					parser2.setInputHTML(div.toHtml());
					NodeFilter filter2 = new NodeClassFilter(Div.class);
					NodeList list2 = parser2.parse(filter2);
					if (list2 != null && list2.size() > 0) {
						int k = 1;
						for (int j = 0; j < list2.size(); j++) {
							Div div2 = (Div) list2.elementAt(j);
							if (div2.getText().startsWith("div id=\"download")) {
								NodeList list5 = div2.getChildren();
								if (list5 != null) {
									if (list5.elementAt(0) instanceof LinkTag) {
										LinkTag nl1 = (LinkTag) list5
												.elementAt(0);
										image.setOrderId(k);
										image.setName(nl1.getLinkText());
										if (!getPic(image, url)) {
											b = false;
										}
										k++;
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			b = false;
		}
		return b;
	}

	static boolean getPic(ImageBean image, String url) throws Exception {
		Parser parser = null;
		boolean b = true;
		try {
			parser = new Parser(url);
			parser.setURL(url);
			parser.setEncoding("GB2312");

			NodeFilter filter = new NodeClassFilter(Div.class);
			NodeList list = parser.extractAllNodesThatMatch(filter)
					.extractAllNodesThatMatch(
							new HasAttributeFilter("id", "wallpaper-images")); // download-images
			if (list != null) {
				Div div = (Div) list.elementAt(0);
				if (null != div) {
					LinkTag link = (LinkTag)div.getChild(0);
					if(null != link){
						ImageTag nl = (ImageTag) link.getChild(0);
						image
								.setCommentshowurl(nl.getAttribute("width") == null ? ""
										: nl.getAttribute("width"));
						image
								.setCommentsuburl(nl.getAttribute("height") == null ? ""
										: nl.getAttribute("height"));
						image.setHttpUrl(nl.getImageURL());
						image.setLink("NED");
						if(null == client.get(nl.getImageURL())){
							int key = imageDao.insert(image);
							if (key > 0) {
								logger.debug("添加图片记录" + (COUNT++)+"\t成功!");
							} else {
								b = false;
							}
						}
					}
				} else {
					b = false;
				}
			}
		} catch (NullPointerException e) {
			try {
				parser = new Parser(url);
				parser.setURL(url);
				parser.setEncoding("GB2312");

				NodeFilter filter = new NodeClassFilter(Div.class);
				NodeList list = parser
						.extractAllNodesThatMatch(filter)
						.extractAllNodesThatMatch(
								new HasAttributeFilter("id", "download-images")); // download-images
				if (list != null) {
					Div div = (Div) list.elementAt(0);
					if (null != div) {
						LinkTag link = (LinkTag)div.getChild(0);
						if(null != link){
							ImageTag nl = (ImageTag) link.getChild(0);
								image
										.setCommentshowurl(nl.getAttribute("width") == null ? ""
												: nl.getAttribute("width"));
								image
										.setCommentsuburl(nl.getAttribute("height") == null ? ""
												: nl.getAttribute("height"));
								image.setHttpUrl(nl.getImageURL());
								image.setLink("NED");
								int key = imageDao.insert(image);
								if (key > 0) {
                                    logger.debug("在异常中执行的解析策略,添加图片记录" + (COUNT++)+"\t成功!");
									client.add(nl.getImageURL(), nl.getImageURL());
								} else {
									b = false;
								}
						}
					} else {
						b = false;
					}
				}
			} catch (Exception exception) {
				b = false;
			}
		}
		return true;

	}

	static void getPicUrl(Article article) throws Exception {
		String url = thirdURL(article.getArticleUrl());
		if (url != null) {
			ResultBean result = hasPagingWithSelectTag(url);
			if (result.isBool()) {
				for (LinkBean bean : result.getList()) {
					Parser parser = new Parser();
					parser.setURL(bean.getLink());
					parser.setEncoding("GB2312");

					NodeFilter filter = new NodeClassFilter(Div.class);
					NodeList list = parser.extractAllNodesThatMatch(filter)
							.extractAllNodesThatMatch(
									new HasAttributeFilter("id",
											"wallpaper-images"));

					if (list != null && list.size() > 0) {
						Parser p1 = new Parser();
						p1.setInputHTML(list.toHtml());
						p1.setEncoding("GB2312");

						NodeFilter imgFilter = new NodeClassFilter(
								ImageTag.class);
						NodeList img = p1.extractAllNodesThatMatch(imgFilter);

						if (img != null && img.size() > 0) {
							ImageTag imgtag = (ImageTag) img.elementAt(0);
							if (imgtag != null) {
								//TODO 获取图片以及入库
								
								System.out.println("" + imgtag.getImageURL());
								// http://www.wallcoo.com/nature/Sunsets_Sunrises_Wallpapers_widescreen/images/%5Bwallcoo_com%5D_Sunsets_Sunrises_Ocean_View_Pier.jpg
								System.out
										.println(""
												+ imgtag.getAttribute("title") == null ? imgtag
												.getAttribute("alt")
												: imgtag.getAttribute("title"));
							}

						}
					}
				}
			}
		}
	}

	/**
	 * 更新Article介绍 Wallcoo
	 * 
	 * @param article
	 */
	static String getArticleText(String articleUrl) {
		Parser parser = null;
		String intro = "";
		try {
			parser = new Parser();
			parser.setURL(articleUrl);
			parser.setEncoding("GB2312");
			NodeFilter filter = new NodeClassFilter(Div.class);
			NodeList list = parser.extractAllNodesThatMatch(filter)
					.extractAllNodesThatMatch(
							new HasAttributeFilter("id", "intro2"));
			if (list != null) {
				Div div = (Div) list.elementAt(0);
				NodeList divList = div.getChildren();
				if (divList != null && divList.size() > 0) {
					intro = divList.toHtml();
				}
				/**
				 * String[] ids = div.getIds(); for(int i=0;i<ids.length;i++){
				 * System.out.println("ids["+i+"]:"+ids[i]); }
				 */
			}
			parser = null;
		} catch (Exception e) {
			System.err.println( "getArticleText.exception:" +e.getMessage() );
		}
		return intro;
	}

	static void clear() {
		if (LINKHASH.size() > 0) {
			LINKHASH.clear();
		}

		if (SECONDLINKHASH.size() > 0) {
			SECONDLINKHASH.clear();
		}

		if (PAGELINKHASH.size() > 0) {
			PAGELINKHASH.clear();
		}
	}

	static void init2cache() {
		try{
			List<String> articleURLlist = articleDao.findAllArticleURL(125);
			long start1 = System.currentTimeMillis();
			for(String key:articleURLlist){
				Object obj = client.get(key);
				if(null == obj){
					client.add(key, key);
				}
			}
			long end1 = System.currentTimeMillis();
			
//			List<String> imageURLList = imageDao.findImageURL(125);
//			long start2 = System.currentTimeMillis();
//			for(String key:imageURLList){
//				Object obj = client.get(key);
//				if(null == obj){
//					client.add(key, key);
//				}
//			}
//			long end2 = System.currentTimeMillis();
		}catch(Exception e){
			System.out.println(">> Exception:"+e.getMessage());
		}
	}
	
	static void update() throws Exception{
		List<WebsiteBean> list = wesiteDao.findByParentId(D_PARENT_ID);
		/**
		 * 文章获取并入库 List<WebsiteBean> list = wesiteDao.findByParentId(125);
		 */
		if (list != null && list.size() > 0) {
			ResultBean result = null;
			int i = 0;
			for (WebsiteBean bean : list) {
				System.out.println("\t"+bean.getUrl()+"\t"+bean.getName());
				
				String lastModify = HttpClientUtils.getLastModifiedByUrl(bean.getUrl());
				if(null != bean.getLastModifyTime() && !"".equals(bean.getLastModifyTime()) && bean.getLastModifyTime().equals(lastModify)){
					continue;
				}
				
				try{
					result = hasPaging(bean, "id", "linkid");
					if (result.isBool()) {
						List<LinkBean> pageList = result.getList();
						if (pageList != null && pageList.size() > 0) {
							for (LinkBean link : pageList) {
								try {
									logger.debug(" >> link:"+link.getLink());
									secondURL(link, bean.getId());
								} catch(Exception e){
									continue;
								}
							}
						}
						
						if(lastModify != null && !"".equals(lastModify) ){
							if(null == bean.getLastModifyTime() || "".equals(bean.getLastModifyTime()) || !bean.getLastModifyTime().equals(lastModify)){
								bean.setLastModifyTime(lastModify);
								if(wesiteDao.update(bean)){
                                    logger.info(" >> 更新网站["+bean.getName()+"|"+bean.getUrl()+"]最后时间["+lastModify+"]成功!");
								}
							}
						}
						
					}
					i++;
					// 更新菜单列表排序
//					wesiteDao.update(bean);
				}catch(Exception e){
					System.out.println(">> Exception :"+e.getMessage());
				}
				
			}
		}
	}
	
	public static void main(String args[]) {
		try {
			
//			catelogy("http://www.wallcoo.net/");
			
			//初始化数据到缓存中
			update();
			
			
			
			//测试分页
//			ResultBean result = hasPagingWithArticleSelectTag("http://www.wallcoo.com/nature/Unclassified_Europe_Landscape/index.html");
//			if(result.isBool()){
//				for(LinkBean link:result.getList()){
//					getPicUrlAndThum(10605,link.getLink());
//				}
//			}
			
//			patchIcon();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void patchIcon() throws Exception {

		List<WebsiteBean> list = wesiteDao.findByParentId(D_PARENT_ID);
		/**
		 * 文章获取并入库 List<WebsiteBean> list = wesiteDao.findByParentId(125);
		 */
		if (list != null && list.size() > 0) {
			ResultBean result = null;
			int i = 0;
			for (WebsiteBean bean : list) {
				System.out.println("\t"+bean.getUrl()+"\t"+bean.getName());
				try{
					result = hasPaging(bean, "id", "linkid");
					if (result.isBool()) {
						List<LinkBean> pageList = result.getList();
						if (pageList != null && pageList.size() > 0) {
							for (LinkBean link : pageList) {
								try {
									System.out.println(" >> link:"+link.getLink());
									getIconByURL(link, bean.getId());
								} catch(Exception e){
									continue;
								}
							}
						}
					}
					i++;
				}catch(Exception e){
					System.out.println(">> Exception :"+e.getMessage());
				}
			}
		}
		}
	
	/**
	 * 获取分类下数据
	 * 
	 * @param link
	 * @param webId
	 * @throws Exception
	 */
	public static void getIconByURL(LinkBean link, int webId) throws Exception {
		Parser parser = new Parser();
		parser.setURL(link.getLink());
		parser.setEncoding("GB2312");

		// 获取指定ID的DIV内容
		NodeFilter filter = new NodeClassFilter(TableTag.class);
		NodeList list = parser.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(new HasAttributeFilter("id", "list"));
		if (list != null && list.size() > 0) {
			NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
			NodeFilter imageFilter = new NodeClassFilter(ImageTag.class);
			OrFilter lastFilter = new OrFilter();
			lastFilter
					.setPredicates(new NodeFilter[] { linkFilter, imageFilter });
			Parser p2 = new Parser();
			p2.setInputHTML(list.toHtml());
			p2.setEncoding("GB2312");
			NodeList list4 = p2.parse(lastFilter);
			if (list4 != null || list4.size() > 0) {
				for (int i = 0; i < list4.size(); i++) {
					// 地址
					if (list4.elementAt(i) instanceof LinkTag) {
						LinkTag nl = (LinkTag) list4.elementAt(i);
						NodeList cnl = nl.getChildren();
						if (cnl != null && cnl.size() > 0) {
							// 小图 可能存在部分图片无法访问，需要判断
							Article article = null;
							if (cnl.elementAt(1) instanceof ImageTag) {
								String url = URL_ + nl.getLink();
									article = new Article();
									article.setWebId(webId);
									article.setArticleUrl(url);
									ImageTag it = (ImageTag) cnl.elementAt(1);
									if(null != it){
										if(it.getImageURL().startsWith("http://")){
											article.setActicleXmlUrl(it.getImageURL());
										}else{
											article.setActicleXmlUrl(URL+it.getImageURL());
										}
										article.setTitle(it.getAttribute("alt") == null ? "NT" : it.getAttribute("alt"));
										Article atmp = articleDao.findByUrl(article.getArticleUrl(), webId);
										if(null != atmp){
											if(atmp.getArticleUrl().equals(article.getArticleUrl()) && atmp.getTitle().equals(article.getTitle())){
												atmp.setActicleXmlUrl(article.getActicleXmlUrl());
												if(articleDao.update(atmp)){
													System.out.println(" >> 更新"+atmp.getId()+".ActicleXmlUrl:"+atmp.getActicleXmlUrl()+"成功!");
												}
											}
										}
									}
							}
						}
					}
				}
			}
		}
	}
	

	static void loadImg() throws Exception{
		List<WebsiteBean> webList = wesiteDao.findByParentId(D_PARENT_ID);
		long start = System.currentTimeMillis();
		for (WebsiteBean bean : webList) {
			List<Article> list = articleDao.findByWebId(bean.getId(), "NED");
			logger.info(">> 网站["+bean.getName()+"]|"+bean.getUrl()+"还有"+list.size()+"条记录未解析"+(System.currentTimeMillis()-start)+"ms");
			for(Article article:list){
				try{
					start = System.currentTimeMillis();
					int count = articleDao.getCount("select count(*) from tbl_image where d_article_id = "+article.getId());
					System.out.println("\t>>>>> 文章总数:"+count + (System.currentTimeMillis()-start) + "ms");
					if(count > 0){
						System.out.println(" >> ["+article.getTitle()+"|"+article.getId()+"]文章下有"+count+"条图片");
						article.setText("FD");
						if(!articleDao.update(article)){
							System.err.println(" >> 站点:["+article.getWebId()+"],更新记录"+article.getTitle()+"失败");
						}
						continue;
					}
					start = System.currentTimeMillis();
					ResultBean result = hasPagingWithArticleSelectTag(article.getArticleUrl());
					System.out.println("\t hasPagingWithArticleSelectTag:"+(System.currentTimeMillis()-start)+"ms");
					if(result.isBool()){
						for(LinkBean link:result.getList()){
							start = System.currentTimeMillis();
							getPicUrlAndThum(article.getId(),link.getLink());
							System.out.println("\t getPicUrlAndThum:"+(System.currentTimeMillis()-start)+"ms");
						}
						article.setText("FD");
					}
				}catch(Exception e){
					e.printStackTrace();
					article.setText("ENED");
				}
				if(!articleDao.update(article)){
					System.err.println(" >> 站点:["+article.getWebId()+"],更新记录"+article.getTitle()+"失败");
				}
			}
		}
	}
	static void imgDownload() throws Exception {
		List<WebsiteBean> webList = wesiteDao.findByParentId(D_PARENT_ID);
		for (WebsiteBean bean : webList) {
			WebsiteBean website = wesiteDao.findById(bean.getId());
				List<Article> list = articleDao.findByWebId(website.getId(),"FD");
				System.out.println(">> 网站["+bean.getId()+"|"+bean.getName()+"|"+bean.getUrl()+"]\t下文章数量"+list.size());
				for (Article art : list) {
					List<ImageBean> imgList = imageDao.findImage(art.getId());
					System.out.println(">> 文章["+art.getId()+"|"+art.getTitle()+"]\t下的图片数量"+imgList.size());
					for (ImageBean img : imgList) {
						if(img.getLink().equals("NED")){
							long start = System.currentTimeMillis();
							if (download(img,art.getArticleUrl())) {
								img.setStatus(-1);
								img.setLink("FD");
								if (imageDao.update(img)) {
									System.out.println(">> 更新图片对象["+art.getTitle()+"|" + img.getId() + "]成功!");
									long end = System.currentTimeMillis();
									System.out.println(">> 整个记录耗时:"+(end-start)+"ms");
								}
							}
						}
					}
//				}
			}
		}
	}
	
	static boolean download(ImageBean imgBean,String url) {
		PicfileBean bean = null;
		bean = new PicfileBean();
		String s_fileName = imgBean.getImgUrl().substring(
				imgBean.getImgUrl().lastIndexOf("/") + 1,
				imgBean.getImgUrl().length());
		String fileName = imgBean.getHttpUrl().substring(
				imgBean.getHttpUrl().lastIndexOf("/") + 1,
				imgBean.getHttpUrl().length());
		String length = "0";
		try {
			System.out.println(">> imgID["+imgBean.getId()+"],articleID["+imgBean.getArticleId()+"]");
			if(picFileDao.checkExists(imgBean.getId(), imgBean.getArticleId())){
				System.out.println(">> 已存在相同记录imgID["+imgBean.getId()+"],articleID["+imgBean.getArticleId()+"]");
				return false;
			}
			byte[] big = null;
			long bstart = System.currentTimeMillis();
			big = HttpClientUtils.getResponseBodyAsByte(imgBean.getCommentshowurl(), null, imgBean.getHttpUrl());
			if(null == big)
				return false;
			length = String.valueOf(big.length);
			long bend = System.currentTimeMillis();
			if(length.equalsIgnoreCase("20261")){
				return false;
			}
			System.out.println(">> 消耗多少时间:"+(bend-bstart));
			//小图
			if (client.get(CacheUtils.getShowImgKey(PIC_SAVE_PATH
					+ StringUtils.gerDir(String.valueOf(imgBean.getArticleId()))
					+ imgBean.getArticleId() + File.separator
					+ s_fileName)) == null) {
				long start = System.currentTimeMillis();
				IOUtil.createPicFile(imgBean.getImgUrl(), PIC_SAVE_PATH
						+ StringUtils.gerDir(String.valueOf(imgBean.getArticleId()))
						+ imgBean.getArticleId() + File.separator
						+ s_fileName);
				long end = System.currentTimeMillis();
				System.out.println(">> 小图生成时间:"+(end-start)+"ms");
			}
			
			//大图
			if (client.get(CacheUtils.getBigPicFileKey(PIC_SAVE_PATH
					+ StringUtils.gerDir(String.valueOf(imgBean.getArticleId()))
					+ imgBean.getArticleId() + File.separator
					+ fileName)) == null) {
				long start = System.currentTimeMillis();
				IOUtil.createFile(big, PIC_SAVE_PATH
						+ StringUtils.gerDir(String.valueOf(imgBean.getArticleId()))
						+ imgBean.getArticleId() + File.separator
						+ fileName);
				long end = System.currentTimeMillis();
				System.out.println(">> 大图生成时间:"+(end-start)+"ms");
			}
			bean.setArticleId(imgBean.getArticleId());
			bean.setImageId(imgBean.getId());
			bean.setTitle(imgBean.getTitle());
			bean.setSmallName(File.separator
					+ StringUtils.gerDir(String.valueOf(imgBean.getArticleId()))
					+ imgBean.getArticleId() + File.separator
					+ s_fileName);
			bean.setName(File.separator
					+ StringUtils.gerDir(String.valueOf(imgBean.getArticleId()))
					+ imgBean.getArticleId() + File.separator
					+ fileName);
			bean.setUrl(PIC_SAVE_PATH);
			try {
				long start = System.currentTimeMillis();
				imgBean.setFileSize(Long.valueOf(length));
				if(imageDao.update(imgBean)){
					//TODO 异步完成数据库入库操作
					boolean b = picFileDao.insert(bean);
					if (b) {
						long end = System.currentTimeMillis();
						client.add(CacheUtils.getBigPicFileKey(bean.getUrl()
								+ bean.getName()), bean);
						client.add(CacheUtils.getSmallPicFileKey(bean.getUrl()
								+ bean.getSmallName()), bean);
						System.out.println(">> 记录添加成功,图片文件记录入库耗时:"+(end-start)+"ms\n");
					} else {
						long end = System.currentTimeMillis();
						System.out.println(">> 记录添加失败,图片文件记录入库耗时:"+(end-start)+"ms\n");
						return false;
					}
				}
			} catch (Exception e) {
				System.out.println("数据库异常");
				e.printStackTrace();
				return false;
			}
		} catch (IOException e) {
			System.out.println("网络连接，文件IO异常");
			return false;
		}catch(Exception e){
			System.out.println("其他异常"+e.getMessage());
			return false;
		}
		return true;
	}
	
}
