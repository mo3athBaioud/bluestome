package com.xc.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.CompositeTag;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;

public class HstypeParser {

	final static String ZOL_MOBILE_URL = "http://mobile.zol.com.cn/manu_list.html";
	
	final static Integer D_PARENT_ID = 1634;
	
	public static void main(String args[]){
//		HashMap<String, LinkTag>  page = getPageList("http://product.pcpop.com/Mobile/00283_1.html");
//		Iterator it = page.keySet().iterator();
//		while(it.hasNext()){
//			String key = (String)it.next();
//			hstypeWithIcon(key);
//			break;
//		}
		
		try{
			String title = getTitle("http://product.pcpop.com/000198201/Index.html");
			System.out.println(" >> title:"+title);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	static String getPriceList(String url) throws Exception {
		String target = null;
		Parser parser = null;
		Parser p2 = null;
		try{
			parser = new Parser();
			parser.setURL(url);
			parser.setEncoding("GB2312");
			NodeList list = parser.extractAllNodesThatMatch(new TagNameFilter("ul"))
					.extractAllNodesThatMatch(
							new HasAttributeFilter("class", "brand_nav white clearfix"));
			if (null != list && list.size() > 0) {
				String content = list.toHtml();
				p2 = new Parser();
				p2.setInputHTML(content);
				p2.setEncoding("GB2312");
				NodeFilter fileter = new NodeClassFilter(LinkTag.class);
				NodeList list2 = p2.extractAllNodesThatMatch(fileter);
				if(null != list2 && list2.size() > 0){
					LinkTag link = (LinkTag)list2.elementAt(0);
					target = link.getLink();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null != parser)
				parser = null;
			if(null != p2)
				p2 = null;
		}
		return target;
	}
	
	static void add(){}

	/**
	 * 获取机型数据
	 * @param url
	 * @return List<String[]>  str[0]=链接地址  str[1]=缩略图
	 */
	public static List<String[]> hstypeWithIcon(String url) {
		Parser parser = null;
		Parser p2 = null;
		List<String[]> hsList = new ArrayList<String[]>(); 
		try {
			parser = new Parser();
			parser.setURL(url);
			parser.setEncoding("GB2312");
			NodeFilter fileter = new NodeClassFilter(Div.class);
			NodeList list = parser.extractAllNodesThatMatch(fileter)
					.extractAllNodesThatMatch(
							new HasAttributeFilter("class", "product"));
			if (null != list && list.size() > 0) {
				p2 = new Parser();
				p2.setInputHTML(list.toHtml());
				p2.setEncoding("GB2312");
				fileter = new NodeClassFilter(CompositeTag.class);
				list = p2.extractAllNodesThatMatch(fileter)
						.extractAllNodesThatMatch(
								new TagNameFilter("dt"));
				for(int i=0;i<list.size();i++){
					CompositeTag ct = (CompositeTag)list.elementAt(i);
					int cc = ct.getChildCount();
					if(cc > 0){
						Node node = ct.getChild(0);
						if(node instanceof LinkTag){
							LinkTag lt = (LinkTag)node;
							String[] result = new String[2];
							if(lt.getChild(0) instanceof ImageTag){
								ImageTag image = (ImageTag)lt.getChild(0);
								result[0] = lt.getLink();
								result[1] = image.getImageURL();
								hsList.add(result);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != p2)
				p2 = null;
			if (null != parser)
				parser = null;
		}
		return hsList;
	}
	
	/**
	 * 获取资源标题
	 * @param url
	 *  【友利通UNC-F670】 UNC-F670手机 最新报价 图片 参数 论坛 友利通UNC-F670报价大全 泡泡手机网
	 * @return
	 */
	public static String getTitle(String url){
		String title = null;
		Parser p1 = null;
		int start = -1;
		int end = -1;
		try{
			p1 = new Parser();
			p1.setURL(url);
			p1.setEncoding("GB2312");
			NodeFilter fileter = new NodeClassFilter(TitleTag.class);
			NodeList list = p1.extractAllNodesThatMatch(fileter);
			if(null != list && list.size() > 0){
				TitleTag tt = (TitleTag)list.elementAt(0);
				title = tt.getTitle();
				start = title.lastIndexOf("【");
				end = title.lastIndexOf("】");
				if(start != -1 && end != -1){
					title = title.substring(start+1,end);
				}
			}
		}catch(Exception e){
			title = "hstype";
		}finally{
			if(null != p1)
				p1 = null;
		}
		return title;
	}

	/**
	 * 获取分页列表
	 * @param url
	 * @return
	 */
	public static HashMap<String,LinkTag> getPageList(String url){
		HashMap<String,LinkTag> page = new HashMap<String,LinkTag>();
		Parser p1 = null;
		Parser p2 = null;
		try{
			p1 = new Parser();
			p1.setURL(url);
			p1.setEncoding("GB2312");
			NodeFilter fileter = new NodeClassFilter(Div.class);
			NodeList list = p1.extractAllNodesThatMatch(fileter)
					.extractAllNodesThatMatch(
							new HasAttributeFilter("class", "page_r"));
			if (null != list && list.size() > 0) {
				String c = list.toHtml();
				p2 = new Parser();
				p2.setInputHTML(c);
				p2.setEncoding("GB2312");
				fileter = new NodeClassFilter(LinkTag.class);
				list = p2.extractAllNodesThatMatch(fileter);
				if(null != list && list.size() > 0){
					for(int i=0;i<list.size();i++){
						LinkTag link = (LinkTag)list.elementAt(i);
						if(null != link.getLink() && !"".equals(link.getLink())){
							page.put(link.getLink(), link);
						}
					}
				}
			}else{
				LinkTag link = new LinkTag();
				link.setLink(url);
				page.put(url, link);
			}
		}catch(Exception e){
			LinkTag link = new LinkTag();
			link.setLink(url);
			page.put(url, link);
		}finally{
			if(null != p2){
				p2 = null;
			}
			if(null != p1){
				p1 = null;
			}
		}
		return page;
	}
	
}

/**
 * 品牌BEAN
 * @author bluestome
 *
 */
class Brand{
	String name;
	String url;
	String icon;
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}