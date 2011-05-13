package com.xc.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.CompositeTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

public class GoogleSearchTools {

    private static String turl = "http://www.google.com.hk/search?hl=zh-CN&newwindow=1&safe=strict&biw=1280&bih=909&q={1}&aq=2g&aqi=g-g9&aql=&oq={2}";
	/**
     * @param args
     */
    public static void main(String[] args) {
        if(process("三星","SGH-S300")){
        	System.out.println("支持GPRS");
        }
    }
    
    /**
     * 处理是否支持GRPS主方法
     * @param hsman
     * @param hstype
     * @return
     */
    public static boolean process(String hsman,String hstype){
    	boolean b = false;
    	if(null ==  hsman || "".equals(hsman)){
    		return b;
    	}
    	if(null == hstype || "".equals(hstype)){
    		return b;
    	}
    	String search = hsman+hstype+"参数";
    	try{
    		search = URLEncoder.encode(search);
    		String url = turl.replace("{1}", search).replace("{2}", search);
//    		System.out.println(" >> turl:"+url);
    		String html = getUrlHtmlByHttpClient(url);
//    		List<String> list = getGooglePage(html);
//    		for(String page:list){
//    			html = getUrlHtmlByHttpClient(page);
//    			System.out.println(html);
	    		String link = parseHtmlLink(html);
	    		if(null == link || "".equals(link)){
//	    			continue;
	    			return b;
	    		}
	    		if(isParamPage(link)){
	    			if(isGrps(link)){
	    				b = true;
	    			}
	    			if(!b){
	    				b = isGrps2(link);
	    			}
	    		}else{
	    			link = getParamPage(link);
	    			if(isGrps(link)){
	    				b = true;
	    			}
	    			if(!b){
	    				b = isGrps2(link);
	    			}
	    		}
//	    		if(b){
//	    			break;
//	    		}
//    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return b;
    }
    
    public static List<String> getGooglePage(String content){
    	Parser p1 = null;
    	List<String> list = new ArrayList<String>();
    	try{
    		p1 = new Parser();
    		p1.setInputHTML(content);
    		
    		NodeFilter filter = new NodeClassFilter(LinkTag.class);
    		NodeList nlist = p1.extractAllNodesThatMatch(filter).extractAllNodesThatMatch(new HasAttributeFilter("class","fl"));
    		if(null != nlist && nlist.size() > 0){
    			for(int i=0;i<nlist.size();i++){
    				LinkTag tag = (LinkTag)nlist.elementAt(i);
    				if(null != tag){
    					System.out.println(tag.getLink());
    					if(tag.getLink().startsWith("/search?q")){
	    					if(tag.getLink().startsWith("http://www.google.com.hk/")){
	    						list.add(tag.getLink());
	    					}else{
	    						list.add("http://www.google.com.hk/"+tag.getLink());
	    					}
    					}
    				}
    			}
    		}
	   	}catch(Exception e){
	    		e.printStackTrace();
		}finally{
			if(null != p1)
				p1 = null;
		}
    	return list;
    }
    
	/**
     * 处理搜索结果字符串
     * @param htmlstr
     */
    private static String parseHtmlLink(String htmlstr) {
    	Parser parser = null;
    	String link = null;
        try {
            parser = Parser.createParser(htmlstr, "utf-8");
            // 创建TagNameFilter实例
            TagNameFilter filter = new TagNameFilter("A");
            // 筛选出所有A标签节点
            NodeList nodes = parser.extractAllNodesThatMatch(filter);
            if (nodes != null) {
                System.out.println(nodes.size());
                for (int i = 0; i < nodes.size(); i++) {
                    LinkTag tag = (LinkTag) nodes.elementAt(i);
                    if(tag.getLink().startsWith("http://detail.zol.com.cn")){
	                    System.out.println(tag.getLinkText() + " -- "
	                            + tag.getLink());
	                    link = tag.getLink();
	                    break;
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
        	if(null != parser){
        		parser = null;
        	}
        }
        return link;
    }

    /**
     * 模拟客户端访问获取搜索结果页面
     * @param url
     * @return
     */
    private static String getUrlHtmlByHttpClient(String url) {
        String searchHtml = null;
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(
                5000);
        GetMethod getMethod = new GetMethod(url);
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler());
        try {
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: "
                        + getMethod.getStatusLine());
            }
            InputStream bodyIs = getMethod.getResponseBodyAsStream();//
            System.out.println("get reoponse body stream:" + bodyIs);

            //如果中文乱码 修改字符集
            // BufferedReader br = new BufferedReader(
            // new InputStreamReader(bodyIs,"GBK"));
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(bodyIs));
            StringBuffer sb = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            searchHtml = sb.toString();
            return searchHtml;
        } catch (HttpException e) {
            System.out.println("Please check your http address!");
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            getMethod.releaseConnection();
        }
    }
    
    private static String getParamPage(String url){
    	Parser p1 = null;
    	String link = null;
    	try{
    		p1 = new Parser();
    		p1.setURL(url);
    		
    		NodeFilter filter = new NodeClassFilter(LinkTag.class);
    		NodeList list = p1.extractAllNodesThatMatch(filter).extractAllNodesThatMatch(new HasAttributeFilter("id","tag_param"));
    		if(null != list && list.size() > 0){
    			LinkTag t = (LinkTag)list.elementAt(0);
    			System.out.println(" >> t:"+t.getLinkText()+"|"+t.getLink());
    			if(t.getLink().startsWith("http://")){
    				link = t.getLink();
    			}else{
    				link = "http://detail.zol.com.cn"+t.getLink();
    			}
    		}
    	}catch(Exception e){
    		
    	}finally{
    		if(null != p1)
    			p1 = null;
    	}
    	return link;
    }

    private static boolean isParamPage(String link){
    	boolean b = false;
    	if(null == link || "".equals(link)){
    		return b;
    	}
    	try{
    		if(link.endsWith("param.shtml")){
    			b = true;
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return b;
    }
    
    /**
     * 判断是否支持GPRS
     * LinkTag.id={param-val-3619,param-val-3620} 或者 Td.id = {param-tit-725,param-val-725} 中包含GPRS
     * @param url
     * @return
     */
    private static boolean isGrps2(String url){
    	boolean b = false;
    	Parser p1 = null;
    	Parser p2 = null;
    	try{
    		p1 = new Parser();
    		p1.setURL(url);
    		
    		NodeFilter filter = new NodeClassFilter(CompositeTag.class);
    		NodeList list = p1.extractAllNodesThatMatch(filter).extractAllNodesThatMatch(new HasAttributeFilter("id","param-val-3619"));
    		if(null != list && list.size() > 0){
				b = true;
    		}
    		
    		if(!b){
    			p2 = new Parser();
    			p2.setURL(url);
    			filter = new NodeClassFilter(CompositeTag.class);
    			list = p2.extractAllNodesThatMatch(filter).extractAllNodesThatMatch(new HasAttributeFilter("id","param-val-725"));
    			if(null != list && list.size() > 0){
    					b = true;
    			}
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		if(null != p1)
    			p1 = null;
    		if(null != p2)
    			p2 = null;
    	}
    	return b;
    }
    
    /**
     * 判断是否支持GPRS
     * LinkTag.id={param-val-3619,param-val-3620} 或者 Td.id = {param-tit-725,param-val-725} 中包含GPRS
     * @param url
     * @return
     */
    private static boolean isGrps(String url){
    	boolean b = false;
    	Parser p1 = null;
    	Parser p2 = null;
    	try{
    		p1 = new Parser();
    		p1.setURL(url);
    		
    		NodeFilter filter = new NodeClassFilter(CompositeTag.class);
    		NodeList list = p1.extractAllNodesThatMatch(filter).extractAllNodesThatMatch(new HasAttributeFilter("id","param-tit-725"));
    		if(null != list && list.size() > 0){
    			p2 = new Parser();
    			p2.setURL(url);
    			filter = new NodeClassFilter(CompositeTag.class);
    			list = p2.extractAllNodesThatMatch(filter).extractAllNodesThatMatch(new HasAttributeFilter("id","param-val-725"));
    			if(null != list && list.size() > 0){
    				CompositeTag tag = (CompositeTag)list.elementAt(0);
    				String cont = tag.toPlainTextString();
    				if(cont.toLowerCase().contains("gprs")){
    					b = true;
    				}
    			}
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		if(null != p1)
    			p1 = null;
    		if(null != p2)
    			p2 = null;
    	}
    	return b;
    }
}
