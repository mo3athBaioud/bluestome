package com.ssi.htmlparser.chinamilitary;

import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.PrototypicalNodeFactory;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasChildFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.StringFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.ObjectTag;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.ScriptTag;

import com.ssi.htmlparser.bean.LinkBean;
import com.ssi.htmlparser.utils.IOUtil;

public class HTMLParser {

	private static List<LinkBean> LinkList = new ArrayList<LinkBean>();
	
	private XMLParser xmlParser;
	
	/**
	 * 解析得到真实的页面地址
	 * @param link
	 * @return
	 * @throws Exception
	 */
	public String getRealUrl(String link) throws Exception{
        Parser parser=new Parser(link); 
        parser.setEncoding("utf-8"); 
        NodeList nodelist=parser.extractAllNodesThatMatch(new NodeFilter(){
			public boolean accept(Node arg0) {
				// TODO Auto-generated method stub
				return true;
			}
			
		}).extractAllNodesThatMatch(new NodeClassFilter(ScriptTag.class)); 		
		String content = nodelist.toHtml();
		String url = content.substring(content.indexOf("\"")+1,content.lastIndexOf("\""));
		return url;
	}

	/**
	 * 得到XML地址
	 * @param link
	 * @return
	 * @throws Exception
	 */
	public String getXmlUrl(String link) throws Exception{
        Parser parser=new Parser(link); 
		String xmlUrl = null;
        parser.setEncoding("UTF-8"); 
		NodeFilter filter =  new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(filter).extractAllNodesThatMatch(new HasAttributeFilter("class","PicShowBox"));
		if(list != null){
			String content = list.toHtml();
			int start = content.indexOf("&xmlpath=");
			int end = content.indexOf("&curid");
			if(start > 0 && end > 0 ){
				xmlUrl = content.substring(start+9,end);
			}else{ 
				//使用新的解析XML方法获取XML
				xmlUrl = getXmlUrl2(link);
			}
			if(!xmlUrl.toLowerCase().endsWith(".xml") || xmlUrl == null){
				return null;
			}
		}
		return xmlUrl;
	}
	
	/**
	 * 得到XML地址
	 * @param link
	 * @return
	 * @throws Exception
	 */
	public static String getXmlUrl2(String link) throws Exception{
        Parser parser=new Parser(link); 
        parser.setEncoding("utf-8"); 
        NodeList nodelist=parser.extractAllNodesThatMatch(new NodeFilter(){
			public boolean accept(Node arg0) {
				// TODO Auto-generated method stub
				return true;
			}
			
		}).extractAllNodesThatMatch(new NodeClassFilter(ScriptTag.class)); 		
		String content = nodelist.toHtml();
		String url = content.substring(content.indexOf("\"")+1,content.lastIndexOf("\""));
		String xmlUrl = null;
		if(url != null){
	    	int start = url.indexOf("getmessage(\"");
	    	int end = url.indexOf(".xml\");");
	    	xmlUrl = url.substring(start+12, end+4);
	    	if(!xmlUrl.toLowerCase().endsWith(".xml")){
	    		return null;
	    	}
		}
		return xmlUrl;
	}
	
	public static List getLinkList(){
		return LinkList;
	}

	public static void clear(){
		System.out.println("清除List中的内容");
		LinkList.clear();
	}
	
    public XMLParser getXmlParser() {
		return xmlParser;
	}

	public void setXmlParser(XMLParser xmlParser) {
		this.xmlParser = xmlParser;
	}

}
