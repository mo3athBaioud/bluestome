package com.zol.mobile;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

import com.chinamilitary.bean.MobileBrandTemp;
import com.chinamilitary.dao.IMobileBrandTempDAO;
import com.chinamilitary.dao.PicFileDao;
import com.chinamilitary.factory.DAOFactory;

/**
 * 中关村手机频道解析类
 * 
 * @author bluestome
 * 
 */
public class MobileParser {

	final static String ZOL_MOBILE_URL = "http://mobile.zol.com.cn/manu_list.html";
	
	final static Integer D_PARENT_ID = 1634;
	
	static IMobileBrandTempDAO mobileBrandTempDAO = DAOFactory.getInstance().getMobileBrandTempDAO();
	
	static HashMap<String,MobileBrandTemp> BRAND_MAP = new HashMap<String,MobileBrandTemp>();

	public static void main(String args[]){
		list();
	}
	
	static void list(){
		try{
			List<MobileBrandTemp> list= mobileBrandTempDAO.findByWebId(D_PARENT_ID);
			for(MobileBrandTemp brand:list){
				String url = getPriceList(brand.getUrl());
				System.out.println(" >> brand["+brand.getName()+"].list:"+url);
			}
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
	
	static void add(){
		BRAND_MAP.clear();
		brandWithIcon();
		brandWithoutIcon();
		System.out.println(" >> 品牌数量:"+BRAND_MAP.size());
		Iterator it = BRAND_MAP.keySet().iterator();
		while(it.hasNext()){
			String key = (String)it.next();
			MobileBrandTemp brand = (MobileBrandTemp)BRAND_MAP.get(key);
			if(null != brand){
				brand.setWebid(D_PARENT_ID);
				System.out.println(" >>name:"+brand.getName());
				System.out.println(" >>url:"+brand.getUrl());
				if(null != brand.getIcon()){
					System.out.println(" >>icon:"+brand.getIcon());
				}
				System.out.println();
				try{
					int result = mobileBrandTempDAO.add(brand);
					System.out.println(" >> result:"+result);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 有图表的品牌
	 * 
	 */
	static void brandWithIcon() {
		Parser parser = null;
		MobileBrandTemp brand = null;
		try {
			parser = new Parser();
			parser.setURL(ZOL_MOBILE_URL);
			parser.setEncoding("GB2312");
			NodeFilter fileter = new NodeClassFilter(Div.class);
			NodeList list = parser.extractAllNodesThatMatch(fileter)
					.extractAllNodesThatMatch(
							new HasAttributeFilter("class", "blI"));
			if (null != list && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Div div = (Div) list.elementAt(i);
					int childCount = div.getChildCount();
					if (childCount == 5) {
						if (div.getChild(1) instanceof LinkTag) {
							brand = new MobileBrandTemp();
							LinkTag link = (LinkTag) div.getChild(1);
							brand.setUrl(link.getLink());
							if(link.getChild(0) instanceof ImageTag){
								ImageTag image = (ImageTag)link.getChild(0);
								brand.setIcon(image.getImageURL());
								brand.setName(image.getAttribute("alt"));
								BRAND_MAP.put(link.getLink(), brand);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != parser)
				parser = null;
		}
	}

	/**
	 * 有图表的品牌
	 * 
	 */
	static void brandWithoutIcon() {
		Parser parser = null;
		MobileBrandTemp brand = null;
		try {
			parser = new Parser();
			parser.setURL(ZOL_MOBILE_URL);
			parser.setEncoding("GB2312");
			NodeFilter fileter = new NodeClassFilter(Div.class);
			NodeList list = parser.extractAllNodesThatMatch(fileter)
					.extractAllNodesThatMatch(
							new HasAttributeFilter("class", "blCon2"));
			if (null != list && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Div div = (Div) list.elementAt(i);
					if (div.getChild(0) instanceof LinkTag) {
						brand = new MobileBrandTemp();
						LinkTag link = (LinkTag) div.getChild(0);
						brand.setName(link.getLinkText());
						brand.setUrl(link.getLink());
						if(!BRAND_MAP.containsKey(link.getLink())){
							BRAND_MAP.put(link.getLink(), brand);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != parser)
				parser = null;
		}
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
