package com.chinamilitary.htmlparser;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.SelectTag;
import org.htmlparser.tags.OptionTag;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;

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
import com.chinamilitary.threadpool.RequestRecordThread;
import com.chinamilitary.threadpool.ThreadPoolManager;
import com.chinamilitary.util.CacheUtils;
import com.chinamilitary.util.IOUtil;

//import com.chinamilitary.util.StringUtils;

public class WallcooParser {

	private static Log log = LogFactory.getLog(WallcooParser.class);

	private static final String URL = "http://www.wallcoo.com/";

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
					tmp = new WebsiteBean();
					tmp.setName(link.getLinkText());
					tmp.setUrl(link.getLink());
					tmp.setParentId(bean.getId());
					boolean b = dao.insert(tmp);
					if (b) {
						System.out.println("成功");
					} else {
						System.out.println("失败");
					}
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
			String tmp = div.getStringText().substring(0,
					div.getStringText().indexOf("页"));
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
				// System.out.println(list4.size());
				for (int i = 0; i < list4.size(); i++) {
					// 地址
					if (list4.elementAt(i) instanceof LinkTag) {
						LinkTag nl = (LinkTag) list4.elementAt(i);
						NodeList cnl = nl.getChildren();
						if (cnl != null && cnl.size() > 0) {
							// 小图 可能存在部分图片无法访问，需要判断
							Article article = null;
							if (cnl.elementAt(1) instanceof ImageTag) {
								article = new Article();
								article.setWebId(webId);
								article.setArticleUrl(URL_ + nl.getLink());
								ImageTag it = (ImageTag) cnl.elementAt(1);
								article.setTitle(it.getAttribute("alt"));
								article.setText("NED_WALLCOO");
								article.setIntro(getArticleText(URL_
										+ nl.getLink()));
								int key = articleDao.insert(article);
								if (key > 0) {
									System.out.println("link:" + URL_
											+ nl.getLink());
									System.out.println("\ttitle:"
											+ it.getAttribute("alt"));
									System.out.println("imageURL:" + URL_
											+ it.getImageURL());
									System.out.println("添加成功!!!\n");
								} else {
									LINKHASH.put(link.getLink(), link);
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
	 * 从文章地址获取图片缩略图和地址
	 * 
	 * @param articleId
	 * @param url
	 */
	static boolean getPicUrlAndThum(int articleId, String url) throws Exception { // String
		// url
		boolean b = true;
		if (articleId < 0) {
			System.out.println("条件:articleID不满足，退出");
			return false;
		}
		if (StringUtils.isEmpty(url)) {
			System.out.println("条件:地址不满足，退出");
			return false;
		}
		Parser parser = null;
		try {
			parser = new Parser();
			parser.setURL(url);
			parser.setEncoding("GB2312");

			NodeFilter filter = new NodeClassFilter(Div.class);
			NodeList list = parser.extractAllNodesThatMatch(filter)
					.extractAllNodesThatMatch(
							new HasAttributeFilter("id", "thums"));

			if (list != null) {

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
									imageBean.setLink(picUrl);
									imageBean.setArticleId(articleId);
									if (!getPicUrl(imageBean)) {
										b = false;
										// break;
									}
								}
							}
						}

					}
				}

			}
		} catch (Exception e) {
			b = false;
			log.error("getPicUrlAndThum:\tarticleId:" + articleId
					+ "\tException" + e);
		}
		// return b;
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
										String tmp = nl1.getLink().replace(
												"../../", "");
										String picUrl = url.substring(0, url
												.indexOf("html"))
												+ tmp.substring(tmp
														.indexOf("/") + 1);
										image.setOrderId(k);
										image.setName(nl1.getLinkText());
										log.debug("图片地址:" + picUrl);
										if (!getPic(image, picUrl)) {
											b = false;
											// break;
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
			log.error("getPicUrl:\tarticleId:" + image.getArticleId()
					+ "\tException" + e);
			b = false;
		}
		return b;
	}

	static boolean getPic(ImageBean image, String url) throws Exception {
		Parser parser = null;
		boolean b = true;
		System.out.println("图片地址:" + url);
		try {
			parser = new Parser(url);
			parser.setURL(url);
			parser.setEncoding("GB2312");

			NodeFilter filter = new NodeClassFilter(Div.class);
			NodeList list = parser.extractAllNodesThatMatch(filter)
					.extractAllNodesThatMatch(
							new HasAttributeFilter("id", "wallpaper-images")); // download-images
			// wallpaper-images

			if (list != null) {
				Div div = (Div) list.elementAt(0);
				if (null != div) {
					ImageTag nl = (ImageTag) div.getChild(0);
					image
							.setCommentshowurl(nl.getAttribute("width") == null ? ""
									: nl.getAttribute("width"));
					image
							.setCommentsuburl(nl.getAttribute("height") == null ? ""
									: nl.getAttribute("height"));
					image.setHttpUrl(nl.getImageURL());
					image.setLink("NED");
					System.out.println("图片地址：" + nl.getImageURL());
					int key = imageDao.insert(image);
					if (key > 0) {
						System.out.println("添加成功!!!" + COUNT);
						COUNT++;
					} else {
						System.out.println("getPic:添加失败!!!" + url);
						System.out.println("添加失败!!!");
						b = false;
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
				// wallpaper-images

				if (list != null) {
					Div div = (Div) list.elementAt(0);
					if (null != div) {
						ImageTag nl = (ImageTag) div.getChild(0);
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
							System.out.println("添加成功!!!" + COUNT);
							COUNT++;
						} else {

							System.out
									.println("getPic.NullPointerException:添加失败!!!"
											+ url);
							b = false;
						}
					} else {
						b = false;
					}
				}
			} catch (Exception exception) {
				log.error(e);
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
			e.printStackTrace();
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

	public static void main(String args[]) {
		try {
			List<WebsiteBean> list = wesiteDao.findByParentId(125);
			/**
			 * 文章获取并入库 List<WebsiteBean> list = wesiteDao.findByParentId(125);
			 */
			/**
			if (list != null && list.size() > 0) {
				ResultBean result = null;
				int i = 0;
				for (WebsiteBean bean : list) {
					result = hasPaging(bean, "id", "linkid");
					if (result.isBool()) {
						List<LinkBean> pageList = result.getList();
						if (pageList != null && pageList.size() > 0) {
							for (LinkBean link : pageList) {
								try {
									secondURL(link, bean.getId());
								} catch (org.htmlparser.util.EncodingChangeException e) {
									LINKHASH.put(link.getLink(), link);
									continue;
								}
							}
						}
					}
					i++;
					// 更新菜单列表排序
					wesiteDao.update(bean);
				}
			}
			**/

			//查找文章下的图片数量
			/**
			**/
			for (WebsiteBean website : list) {
				List<Article> aList = articleDao.findShowImg(website.getId(),
						"FNFE", 0); //NED_WALLCOO
				System.out.println("图片数量:"+aList.size());
				if (aList != null && aList.size() > 0) {
					int i = 0;
					ResultBean result = null;
					for (Article article : aList) {
						try {
							System.out
									.println("URL:" + article.getArticleUrl());
							result = hasPagingWithArticleSelectTag(article
									.getArticleUrl());
							if (result.isBool()) {
								List<LinkBean> ll = result.getList();
								System.out.println("分页数量：" + ll.size());
								if (ll != null && ll.size() > 0) {
									for (LinkBean bean : ll) {
										if (!getPicUrlAndThum(article.getId(),
												bean.getLink())) {
											continue;
										}
									}
									article.setText("FD");
									if (articleDao.update(article)) {
										System.out.println("articleId="
												+ article.getId() + "\t"
												+ article.getArticleUrl()
												+ ",有分页，更新成功!!");
									}
								}
							} else {
								System.out.println("没有分页");
								if (!getPicUrlAndThum(article.getId(), article
										.getArticleUrl())) {
									continue;
								}
								article.setText("FD");
								if (articleDao.update(article)) {
									System.out.println("articleId="
											+ article.getId() + "\t"
											+ article.getArticleUrl()
											+ ",无分页，更新成功!!");
								}
							}
						} catch (FileNotFoundException e) {
							e.printStackTrace();
							article.setText("FNFE"); // FileNotFoundException
							articleDao.update(article);
							System.out.println("文章:"+article.getTitle()+".Exception:");
							continue;
						} catch (Exception e) {
							e.printStackTrace();
							article.setText("FNFE");//FileNotFoundException
							articleDao.update(article);
							System.out.println("文章:"+article.getTitle()+".Exception:");
							continue;
						}
					}
					i++;
					System.out.println("共更新" + i + "条记录");
				}
			}
			
			/**
			secondURL("http://www.wallcoo.com/engine/index.htm");
			String url = thirdURL("http://www.wallcoo.com/nature/Sz_216_Hawaii_Sky_and_Sea_Aquamarine_1920x1200/index.html");
			System.out.println("url:" + url);
			String url = "http://www.wallcoo.com/animal/MX069_Pretty_Puppies_puppy_garden_adventure/html/wallpaper1.html";
			System.out
					.println(url.replace(url.substring(
							url.lastIndexOf("/") + 1, url.length()),
							"wallpaper2.html"));
			ResultBean result = hasPagingWithSelectTag(url);
			if (result.isBool()) {
				for (LinkBean bean : result.getList()) {
					System.out.println(bean.getLink());
				}
			}
			Iterator it1 = LINKHASH.keySet().iterator();
			int count = 0;
			while (it1.hasNext()) {
				String key = (String) it1.next();
				if (it1.next() instanceof LinkBean) {
					LinkBean bean = (LinkBean) LINKHASH.get(key);
					System.out.println("未解析的地址为：" + bean.getLink());
				}
				if (it1.next() instanceof LinkTag) {
					LinkTag link = (LinkTag) it1.next();
					System.out.println("文章地址:"
							+ link.getLink()
							+ "\t"
							+ (link.getLinkText() == null ? "文章内容" : link
									.getLinkText()));
				}
			}
			**/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
