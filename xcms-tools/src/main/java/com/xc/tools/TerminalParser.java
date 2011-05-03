package com.xc.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.htmlparser.tags.TableTag;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;

public class TerminalParser {

	public static void main(String args[]) {
		// 非智能 http://product.pcpop.com/000276716/Detail.html
		// 智能 http://product.pcpop.com/000163063/Detail.html
		getTerminalInfo("http://product.pcpop.com/000163063/Detail.html");
	}

	/**
	 * 获取机型数据
	 * 
	 * @param url
	 * @return List<String[]> str[0]=链接地址 str[1]=缩略图
	 */
	public static String[] getTerminalInfo(String url) {
		Parser parser = null;
		Parser p2 = null;
		List<String[]> attrList = new ArrayList<String[]>();
		String[] attrs = new String[14];
		try {
			parser = new Parser();
			parser.setURL(url);
			parser.setEncoding("GB2312");
			NodeFilter fileter = new NodeClassFilter(TableTag.class);
			NodeList list = parser.extractAllNodesThatMatch(fileter)
					.extractAllNodesThatMatch(
							new HasAttributeFilter("class", "tab1125"));
			if (null != list && list.size() > 0) {
				p2 = new Parser();
				p2.setInputHTML(list.toHtml());
				p2.setEncoding("GB2312");
				fileter = new NodeClassFilter(CompositeTag.class);
				list = p2.extractAllNodesThatMatch(fileter)
						.extractAllNodesThatMatch(
								new HasAttributeFilter("name", "PRODUCTTDID0"));
				// new HasAttributeFilter("class", "ri01"));
//				System.out.println(" >> list.size():" + list.size());
				if (list.size() == 96) {
					String price = list.elementAt(2).toPlainTextString();
					attrs[0] = filter(price);
//					System.out.println(" >> price:" + attrs[0]);

					String mobileSystem = list.elementAt(6).toPlainTextString();
					attrs[1] = filter(mobileSystem);
//					System.out.println(" >> mobileSystem:" + attrs[1]);

					String system = list.elementAt(9).toPlainTextString();
					System.out.println(" >> system:"+system);
					attrs[2] = filter(system);
//					System.out.println(" >> system:" + attrs[2]);

					String screenSize = list.elementAt(22).toPlainTextString();
//					if(screenSize.length() > 4){
//						attrs[3] = "1";
//					}else{
						attrs[3] = filter(screenSize);
//					}
//					System.out.println(" >> screenSize:" + attrs[3]);

					String screenColor = list.elementAt(23).toPlainTextString();
//					attrs[4] = filter(screenColor);
					if(null == screenColor || "".equals(screenColor)){
						attrs[4] = "0";
					}else{
						attrs[4] = screenColor;
					}
//					System.out.println(" >> screenColor:" + screenColor);

					String resoulation = list.elementAt(24).toPlainTextString();
					attrs[5] = filter(resoulation);
//					System.out.println(" >> resoulation:" + attrs[5]);

					String isCamera = list.elementAt(38).toPlainTextString();
					if(isCamera.length() > 4){
						attrs[6] = "1";
					}else{
						attrs[6] = filter(isCamera);
					}
//					System.out.println(" >> isCamera:" + attrs[6]);

					String cameraPixels = list.elementAt(37)
							.toPlainTextString();
					if(cameraPixels.length() > 4){
						attrs[7] = "1";
					}else{
						attrs[7] = filter(cameraPixels);
					}
//					System.out.println(" >> cameraPixels:" + attrs[7]);

					String isGps = list.elementAt(60).toPlainTextString();
					if(isGps.length() > 4){
						attrs[8] = "1";
					}else{
						attrs[8] = filter(isGps);
					}
//					System.out.println(" >> isGps:" + attrs[8]);

					String isWap = list.elementAt(66).toPlainTextString();
					if(isWap.length() > 4){
						attrs[9] = "1";
					}else{
						attrs[9] = filter(isWap);
					}
//					System.out.println(" >> isWap:" + attrs[9]);

					String isWww = list.elementAt(67).toPlainTextString();
					if(isWww.length() > 4){
						attrs[10] = "1";
					}else{
						attrs[10] = filter(isWww);
					}
//					System.out.println(" >> isWww:" + attrs[10]);

					String isEmail = list.elementAt(68).toPlainTextString();
					if(isEmail.length() > 4){
						attrs[11] = "1";
					}else{
						attrs[11] = filter(isEmail);
					}
//					System.out.println(" >> isEmail:" + attrs[11]);

					String isMms = list.elementAt(70).toPlainTextString();
					if(isMms.length() > 4){
						attrs[12] = "1";
					}else{
						attrs[12] = filter(isMms);
					}
//					System.out.println(" >> isMms:" + attrs[12]);

					String isJava = list.elementAt(80).toPlainTextString();
					if(isJava.length() > 4){
						attrs[13] = "1";
					}else{
						attrs[13] = filter(isJava);
					}
//					System.out.println(" >> isJava:" + attrs[13]);

					attrList.add(attrs);

				}
				/**
				 * 属性列表 for(int i=0;i<list.size();i++){ CompositeTag ct =
				 * (CompositeTag)list.elementAt(i);
				 * System.out.println("属性["+i+"]:"+ct.toPlainTextString()); //价格 }
				 */
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != p2)
				p2 = null;
			if (null != parser)
				parser = null;
		}
//		return attrList;
		return attrs;
	}

	private static String filter(String value) {
		String result = value.trim();
		if(null == result && "".equals(result))
			result = "-";
		if (result.equals("-")) {
			result = "0";
		} else if (result.equals("支持") || result.startsWith("支持") || result.startsWith("内置")) {
			result = "1";
		} else if (result.equals("不支持") || result.startsWith("不支持")) {
			result = "2";
		} else if(checkChs(result)){
			result = dealChs(result);
		} 
//		else if(null != result && !"".equals(result)){
//			result = "1";
//		}
		return result;
	}

	/**
	 * 检查是否存在汉字
	 * @param str
	 * @return
	 */
	public static boolean checkChs(String str) {
		boolean mark = false;

		Pattern pattern = Pattern.compile("[\u4E00-\u9FA5]");
		Matcher matc = pattern.matcher(str);
		StringBuffer stb = new StringBuffer();
		while (matc.find()) {
			mark = true;
			stb.append(matc.group());
		}

		return mark;
	}
	
	/**
	 * 处理存在汉字的字符串
	 * @param str
	 * @return
	 */
	public static String dealChs(String str) {
		boolean mark = false;
		String result = str;
		Pattern pattern = Pattern.compile("[\u4E00-\u9FA5]");
		Matcher matc = pattern.matcher(str);
		StringBuffer stb = new StringBuffer();
		while (matc.find()) {
			mark = true;
			stb.append(matc.group());
		}

		if (mark) {
			result = str.replace(stb.toString(), "");
		}

		return result.trim();
	}
	

}