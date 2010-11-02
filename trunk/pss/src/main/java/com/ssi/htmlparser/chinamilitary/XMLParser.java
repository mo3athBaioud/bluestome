package com.ssi.htmlparser.chinamilitary;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ssi.common.utils.HttpClientUtils;
import com.ssi.dal.dao.IImageDAO;
import com.ssi.dal.domain.Image;
import com.ssi.htmlparser.utils.IOUtil;

public class XMLParser {

	private final Log logger = LogFactory.getLog(XMLParser.class);

	private IImageDAO imageDao;

	public void readXmlFromURL(String url, String dir) throws Exception {
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			// 读取XML文件
			document = reader.read(new URL(url));
		} catch (Exception e) {
			String value = HttpClientUtils.getResponseBody(url);
			if (!"".equalsIgnoreCase(value)) {
				return;
			}
			if (!"".equalsIgnoreCase(value)) {
				int start = value.indexOf("intro=") + 6;
				int end = value.indexOf("comment");
				String tmp = value.substring(start, end);
				logger.debug("value:" + value.replace(tmp, "\"\""));
				String tmp2 = value.replace(tmp, "\"\" ");
				// 读取XML文件
				document = reader.read(tmp2);
			}
		}
		Element root = document.getRootElement();// 得到根节点
		List list = root.elements("Image");
		Image bean = null;
		String title = null;
		for (int i = 0; i < list.size(); i++) {
			bean = new Image();
			Element eles = (Element) list.get(i);
			title = eles.attributeValue("title");
			String name = eles.attributeValue("name");
			String imgurl = eles.attributeValue("imgurl");
			String httpurl = eles.attributeValue("httpurl");
			Integer id = Integer.valueOf(eles.attributeValue("id"));
			String time = eles.attributeValue("time");
			String intro = eles.attributeValue("intro");
			String commentsuburl = eles.attributeValue("commentsuburl");
			String commentshowurl = eles.attributeValue("commentshowurl");
			String link = eles.attributeValue("link");
			bean.setTitle(title);
			bean.setCommentshowurl(commentshowurl);
			bean.setCommentsuburl(commentsuburl);
			bean.setHttpUrl(httpurl);
			bean.setOrderId(id);
			bean.setImgUrl(imgurl);
			bean.setIntro(intro);
			bean.setLink(link);
			bean.setName(name);
			bean.setTime(time);
			int ids = imageDao.insert(bean);
			String s_fileName = imgurl.substring(imgurl.lastIndexOf("/") + 1,
					imgurl.length());
			String fileName = httpurl.substring(httpurl.lastIndexOf("/") + 1,
					httpurl.length());
			s_fileName = s_fileName.replace(".", "_s.");
			IOUtil.createPicFile(imgurl, dir + fileName.replace(".", "_s."));
			IOUtil.createPicFile(httpurl, dir + fileName);
			logger.debug("添加图片数据记录，ID为：" + ids);
		}
		if (title == null || title.equals(""))
			title = "readme";
		IOUtil.createFile("XMLURL:" + url + "\n", dir + title, ".txt");
	}

	public List<Image> readXmlFromURL(String xmlUrl) throws Exception {
		List<Image> imageList = new ArrayList<Image>();
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			// 读取XML文件
			document = reader.read(new URL(xmlUrl));
		} catch (Exception e) {
			String value = HttpClientUtils.getResponseBody(xmlUrl);
			if ("".equalsIgnoreCase(value)) {
				return imageList;
			}
			if (!"".equalsIgnoreCase(value)) {
				int start = value.indexOf("intro=") + 6;
				int end = value.indexOf("comment");
				String tmp = value.substring(start, end - 1);
				logger.debug(tmp);
				String tmp2 = value.replace(tmp, "\"\" ");
				logger.debug(tmp2);
				// 读取XML字符串文件
				try {
					document = reader.read(tmp2);
				} catch (Exception ex) {
					logger.debug("ex:" + ex);
				}
			}
		}
		if (null == document) {
			logger.debug(">> doucment is null");
			return imageList;
		}
		// reader.read(new URL(xmlUrl));// 读取XML文件
		Element root = document.getRootElement();// 得到根节点
		List list = root.elements("Image");
		Image bean = null;
		String title = null;
		for (int i = 0; i < list.size(); i++) {
			bean = new Image();
			Element eles = (Element) list.get(i);
			title = eles.attributeValue("title");
			String name = eles.attributeValue("name");
			String imgurl = eles.attributeValue("imgurl");
			String httpurl = eles.attributeValue("httpurl");
			Integer id = Integer.valueOf(eles.attributeValue("id"));
			String time = eles.attributeValue("time");
			String intro = eles.attributeValue("intro");
			String commentsuburl = eles.attributeValue("commentsuburl");
			String commentshowurl = eles.attributeValue("commentshowurl");
			String link = eles.attributeValue("link");
			bean.setTitle(title);
			bean.setCommentshowurl(commentshowurl);
			bean.setCommentsuburl(commentsuburl);
			bean.setHttpUrl(httpurl);
			bean.setOrderId(id);
			bean.setImgUrl(imgurl);
			bean.setIntro(intro);
			bean.setLink("NED");
			bean.setName(name);
			bean.setTime(time);
			imageList.add(bean);
		}
		return imageList;
	}

	public void readXmlFromURL(String linkUrl, String xmlUrl, String dir)
			throws Exception {
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			// 读取XML文件
			document = reader.read(new URL(xmlUrl));
		} catch (Exception e) {
			String value = HttpClientUtils.getResponseBody(xmlUrl);
			if (!"".equalsIgnoreCase(value)) {
				return;
			}
			if (!"".equalsIgnoreCase(value)) {
				int start = value.indexOf("intro=") + 6;
				int end = value.indexOf("comment");
				String tmp = value.substring(start, end);
				logger.debug("value:" + value.replace(tmp, "\"\""));
				String tmp2 = value.replace(tmp, "\"\" ");
				// 读取XML文件
				document = reader.read(tmp2);
			}
		}
		Element root = document.getRootElement();// 得到根节点
		List list = root.elements("Image");
		Image bean = null;
		String title = null;
		for (int i = 0; i < list.size(); i++) {
			bean = new Image();
			Element eles = (Element) list.get(i);
			title = eles.attributeValue("title");
			String name = eles.attributeValue("name");
			String imgurl = eles.attributeValue("imgurl");
			String httpurl = eles.attributeValue("httpurl");
			Integer id = Integer.valueOf(eles.attributeValue("id"));
			String time = eles.attributeValue("time");
			String intro = eles.attributeValue("intro");
			String commentsuburl = eles.attributeValue("commentsuburl");
			String commentshowurl = eles.attributeValue("commentshowurl");
			String link = eles.attributeValue("link");
			bean.setTitle(title);
			bean.setCommentshowurl(commentshowurl);
			bean.setCommentsuburl(commentsuburl);
			bean.setHttpUrl(httpurl);
			bean.setOrderId(id);
			bean.setImgUrl(imgurl);
			bean.setIntro(intro);
			bean.setLink(link);
			bean.setName(name);
			bean.setTime(time);
			int ids = imageDao.insert(bean);
			logger.debug("添加图片数据记录，ID为：" + ids);
			if (ids > 0) {
				String s_fileName = imgurl.substring(
						imgurl.lastIndexOf("/") + 1, imgurl.length());
				String fileName = httpurl.substring(
						httpurl.lastIndexOf("/") + 1, httpurl.length());
				s_fileName = s_fileName.replace(".", "_s.");
				IOUtil
						.createPicFile(imgurl, dir
								+ fileName.replace(".", "_s."));
				IOUtil.createPicFile(httpurl, dir + fileName);
				if (intro != null && !intro.equals(""))
					IOUtil.createFile(intro, dir
							+ fileName.substring(0,
									fileName.lastIndexOf(".") - 1), ".txt");
				if (title == null || title.equals(""))
					title = "readme";
				IOUtil.createFile("LINK地址" + linkUrl + "\nXML地址:" + xmlUrl, dir
						+ title, ".txt");
			}
		}
	}

	public IImageDAO getImageDao() {
		return imageDao;
	}

	public void setImageDao(IImageDAO imageDao) {
		this.imageDao = imageDao;
	}

}