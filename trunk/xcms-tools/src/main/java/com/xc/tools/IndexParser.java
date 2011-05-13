package com.xc.tools;

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

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
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
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;

import com.ssi.common.utils.IOUtil;

public class IndexParser {
	
	static final String INDEX_URL = "http://www.numberingplans.com/";
	static final String URL = "http://www.numberingplans.com/?page=plans&sub=imeinr&alpha_2_input=44";
	static String URL_TMPLATE = "http://www.numberingplans.com/?page=plans&sub=imeinr&alpha_2_input={type}";
	static String SAVE_PATH = "d:\\numberingplans\\";
	static String NEW_URL = "http://www.tenaa.com.cn/(S(regadd55rrwgvx45ehb35o55))/WSFW/FlagValidateImei.aspx";
	
	static HashMap<String,String[]> TAC_MAP = new HashMap<String,String[]>();
	static int TMP_COUNT = 0;
	public static void main(String args[]){
		/**
		loopFile(SAVE_PATH);
		System.out.println(" >> size:"+TAC_MAP.size());
		TAC_MAP.clear();
		System.out.println(" >> count:"+TMP_COUNT);
		**/
		
//		getHsmanLIst();
		doPost();
	}
	
	public static HashMap<String,String[]> getTacMap(){
		return TAC_MAP;
	}
	
	/**
	 * 启动解析本地保存的网页内容
	 *
	 */
	public static void init(){
		try{
			loopFile(SAVE_PATH);
		}catch(Exception e){
			System.err.println(e);
		}
	}
	
	public static void process(String type,String url,int pageNum){
		Parser p1 = null;
		StringBuffer sb = new StringBuffer();
		try{
			p1 = new Parser();
			p1.setURL(url);
			p1.setEncoding("UTF-8");
			
			NodeFilter filter = new NodeClassFilter(TableTag.class);
			NodeList list = p1.extractAllNodesThatMatch(filter).extractAllNodesThatMatch(new HasAttributeFilter("id","AutoNumber1"));
			if(null != list && list.size() > 0){
				int size = list.size();
				TableTag table = (TableTag)list.elementAt(size-1);
				
				if(null != table){
					int rowc = table.getRowCount();
					for(int j=0;j<rowc;j++){
						if(j<2){
							continue;
						}
						TableRow row = table.getRow(j);
						TableColumn[] col = row.getColumns();
						if(col.length == 3){
							sb.append(">> Type Allocation Code:"+col[0].toPlainTextString()).append("\r\n");
							sb.append(">> Mobile phone manufacturer:"+col[1].toPlainTextString()).append("\r\n");
							sb.append(">> Mobile phone brand and model"+col[2].toPlainTextString()).append("\r\n");
							sb.append("\r\n");
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
	}
	
	public static void process(String content){
		Parser p1 = null;
		StringBuffer sb = new StringBuffer();
		String[] tacs = new String[3];
		try{
			p1 = new Parser();
			p1.setInputHTML(content);
			p1.setEncoding("UTF-8");
			
			NodeFilter filter = new NodeClassFilter(TableTag.class);
			NodeList list = p1.extractAllNodesThatMatch(filter).extractAllNodesThatMatch(new HasAttributeFilter("id","AutoNumber1"));
			if(null != list && list.size() > 0){
				int size = list.size();
				TableTag table = (TableTag)list.elementAt(size-1);
				if(null != table){
					int rowc = table.getRowCount();
					for(int j=0;j<rowc;j++){
						if(j<2){
							continue;
						}
						TableRow row = table.getRow(j);
						TableColumn[] col = row.getColumns();
						if(col.length == 3){
							
							//TAC
							tacs[0] = col[0].toPlainTextString().trim();
							//BRAND
							tacs[1] = col[1].toPlainTextString().trim();
							//MODEL
							tacs[2] = col[2].toPlainTextString().trim();
							TAC_MAP.put(tacs[0], tacs);
							TMP_COUNT++;
						}
					}
				}
			}
		}catch(Exception e){
			System.err.println(e);
		}finally{
			if(null != p1)
				p1 = null;
		}
	}
	
	/**
	 * 获取TAC类别页面
	 * @return
	 */
	public static HashMap<String,String> getKindsTacFromRemote(){
		Parser p1 = null;
		Parser p2 = null;
		HashMap<String,String> map = new HashMap<String,String>();
		try{
			p1 = new Parser();
			p1.setURL(URL);
			p1.setEncoding("UTF-8");
			
			NodeFilter filter = new NodeClassFilter(SelectTag.class);
			NodeList list = p1.extractAllNodesThatMatch(filter).extractAllNodesThatMatch(new HasAttributeFilter("name","alpha_2_input"));
			if(null != list && list.size() > 0){
				if(list.size() == 1){
					SelectTag tag = (SelectTag)list.elementAt(0);
					OptionTag[] options = tag.getOptionTags();
					for(OptionTag opt:options){
						String url = URL_TMPLATE;
						url = url.replace("{type}",opt.getValue());
						map.put(opt.getValue(), url);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null != p2)
				p2 = null;
			if(null != p1)
				p1 = null;
		}
		return map;
	}
	
	/**
	 * 获取TAC类别页面
	 * @return
	 */
	public static HashMap<String,String> getKindsTacFromLocal(){
		Parser p1 = null;
		Parser p2 = null;
		HashMap<String,String> map = new HashMap<String,String>();
		try{
			String content = IOUtil.readFile(System.getProperty("user.dir")+File.separator+"NUMBERINGPLANS.txt", "GBK", "txt");
			if(null != content && !content.equals("")){
				String[] rows = content.split("\r\n");
				for(String row:rows){
					String[] t = row.split("\t\t");
					map.put(t[0],t[1]);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null != p2)
				p2 = null;
			if(null != p1)
				p1 = null;
		}
		return map;
	}
	
	public static String getPageCount(String url){
		Parser p1 = null;
		Parser p2 = null;
		String count = "1";
		try{
			p1 = new Parser();
			p1.setURL(url);
			p1.setEncoding("UTF-8");
			
			NodeFilter filter = new NodeClassFilter(CompositeTag.class);
			NodeList list = p1.extractAllNodesThatMatch(filter).extractAllNodesThatMatch(new HasAttributeFilter("style","border-left-style:none; border-left-width:medium; border-right-style:none; border-right-width:medium; border-bottom-style:none; border-bottom-width:medium; border-top-style:solid; border-top-width:1"));
			if(null != list && list.size() > 0){
				p2 = new Parser();
				p2.setInputHTML(list.toHtml());
				p2.setEncoding(p1.getEncoding());
				
				filter = new NodeClassFilter(LinkTag.class);
				list = p2.extractAllNodesThatMatch(filter);
				
				if(null != list && list.size() > 0){
					LinkTag link = (LinkTag)list.elementAt(list.size()-1);
					if(null != link){
						String turl = link.getLink();
						String[] params = turl.split("&");
						if(params.length > 0){
							String[] values = params[params.length-1].split("=");
							count = values[1];
						}
					}
				}
			}
		}catch(Exception e){
			System.err.println(e);
		}finally{
			if(null != p2)
				p2 = null;
			if(null != p1)
				p1 = null;
		}
		System.out.println(" >> count:"+count);
		return count;
	}
	
	/**
	 * 获取下载的HTML文件内容，此方法为递归方法
	 * @param filePath
	 */
	public static void loopFile(String filePath){
		File file = null;
		try{
			file = new File(filePath);
			if(file.isDirectory()){
				for(File sub:file.listFiles()){
					loopFile(sub.getAbsolutePath());
				}
			}else{
				String content = IOUtil.readFile(file.getAbsolutePath(), "GBK", "html");
				if(null != content && !"".equals(content)){
					process(content);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 *  
	 */
	public static void getHsmanLIst(){
		Parser p1 = null;
		Parser p2 = null;
		String count = "1";
		try{
			p1 = new Parser();
			p1.setURL(NEW_URL);
			p1.setEncoding("gb2312");
			
			NodeFilter filter = new NodeClassFilter(SelectTag.class);
			NodeList list = p1.extractAllNodesThatMatch(filter).extractAllNodesThatMatch(new HasAttributeFilter("id","DDLPP"));
			if(null != list && list.size() > 0){
				SelectTag select = (SelectTag)list.elementAt(0);
				OptionTag[] opts = select.getOptionTags();
				for(OptionTag op:opts){
					System.out.println(" >> "+op.getOptionText()+"|"+op.getValue());
				}
			}
		}catch(Exception e){
			System.err.println(e);
		}finally{
			if(null != p2)
				p2 = null;
			if(null != p1)
				p1 = null;
		}
		System.out.println(" >> count:"+count);
	}
	
	public static void doPost(){
		PostMethod postMethod = null;
		NameValuePair p1 = null;
		NameValuePair p2 = null;
		NameValuePair p3 = null;
		NameValuePair p4 = null;
		NameValuePair p5 = null;
		NameValuePair p6 = null;
		NameValuePair p7 = null;
		NameValuePair p8 = null;
		NameValuePair p9 = null;
		NameValuePair p10 = null;
		NameValuePair p11 = null;
		NameValuePair p12 = null;
		NameValuePair p13 = null;
		NameValuePair p14 = null;
		NameValuePair p15 = null;
		try{
			HttpClient client = new HttpClient();
			postMethod = new PostMethod("http://www.tenaa.com.cn/(S(regadd55rrwgvx45ehb35o55))/WSFW/FlagValidateImei.aspx");
			p1 = new NameValuePair("__EVENTTARGET","DDLPP");
			p2 = new NameValuePair("__EVENTARGUMENT","DDLPP");
			p3 = new NameValuePair("__LASTFOCUS","DDLPP");
			p4 = new NameValuePair("__VIEWSTATE","/wEPDwUJNzM5MjkxMjIxD2QWAgIBD2QWAgIBD2QWGAIDD2QWAgIBDw8WBB4FV2lkdGgbAAAAAAAAWUABAAAAHgRfIVNCAoACFgoeBXdpZHRoBQQxMDAlHgZoZWlnaHQFBDEwMCUeC2NlbGxTcGFjaW5nBQEwHgtjZWxsUGFkZGluZwUBMB4GYm9yZGVyBQEwZAIFD2QWBAIBDxYCHgdWaXNpYmxlaGQCAw8PFgIeBFRleHQFhQE8YSBocmVmPUZsYWdWYWxpZGF0ZS5hc3B4IGNsYXNzPXdlbnppID7nvZHkuIrmnI3liqE8L2E ID4gPGEgaHJlZj1GbGFnVmFsaWRhdGVJbWVpLmFzcHggY2xhc3M9d2VuemkgPuenu WKqOeUteivneacuklNRUnlj7fpqozor4E8L2E ZGQCCQ9kFgYCBQ8PFgIfB2dkZAIRDxYCHwdoZAITDxYCHwdoZAILDw8WBh4ISW1hZ2VVcmwFKS9UcmFuc0ZpbGUvV2ViUGljLzA5MDI1NzI3LzA5MDI1NzI3LXouanBnHgtDb21tYW5kTmFtZQVZU2hvd0FsbFBpYy5hc3B4P2NvZGU9dkpjdmEzZSUyZkF2R09ERERNZDkxUVVHY01xRzhidkRFU09RWldISWVHTXJROUI4SnljQWIlMmJubzV2Qkttd0hPb1gfB2dkZAINDw8WBB4LTmF2aWdhdGVVcmwFWVNob3dBbGxQaWMuYXNweD9jb2RlPXZKY3ZhM2UlMmZBdkdPRERETWQ5MVFVR2NNcUc4YnZERVNPUVpXSEllR01yUTlCOEp5Y0FiJTJibm81dkJLbXdIT29YHwdnZGQCDw8PFgIfB2dkZAITD2QWAgIDDw9kFgofAgUEMTAwJR8DBQQxMDAlHwQFATAfBQUBMB8GBQEwZAIXDxBkZBYBZmQCHQ9kFgQCAQ8QZA8W7QNmAgECAgIDAgQCBQIGAgcCCAIJAgoCCwIMAg0CDgIPAhACEQISAhMCFAIVAhYCFwIYAhkCGgIbAhwCHQIeAh8CIAIhAiICIwIkAiUCJgInAigCKQIqAisCLAItAi4CLwIwAjECMgIzAjQCNQI2AjcCOAI5AjoCOwI8Aj0CPgI/AkACQQJCAkMCRAJFAkYCRwJIAkkCSgJLAkwCTQJOAk8CUAJRAlICUwJUAlUCVgJXAlgCWQJaAlsCXAJdAl4CXwJgAmECYgJjAmQCZQJmAmcCaAJpAmoCawJsAm0CbgJvAnACcQJyAnMCdAJ1AnYCdwJ4AnkCegJ7AnwCfQJ An8CgAECgQECggECgwEChAEChQEChgEChwECiAECiQECigECiwECjAECjQECjgECjwECkAECkQECkgECkwEClAEClQEClgEClwECmAECmQECmgECmwECnAECnQECngECnwECoAECoQECogECowECpAECpQECpgECpwECqAECqQECqgECqwECrAECrQECrgECrwECsAECsQECsgECswECtAECtQECtgECtwECuAECuQECugECuwECvAECvQECvgECvwECwAECwQECwgECwwECxAECxQECxgECxwECyAECyQECygECywECzAECzQECzgECzwEC0AEC0QEC0gEC0wEC1AEC1QEC1gEC1wEC2AEC2QEC2gEC2wEC3AEC3QEC3gEC3wEC4AEC4QEC4gEC4wEC5AEC5QEC5gEC5wEC6AEC6QEC6gEC6wEC7AEC7QEC7gEC7wEC8AEC8QEC8gEC8wEC9AEC9QEC9gEC9wEC AEC QEC gEC wEC/AEC/QEC/gEC/wECgAICgQICggICgwIChAIChQIChgIChwICiAICiQICigICiwICjAICjQICjgICjwICkAICkQICkgICkwIClAIClQIClgIClwICmAICmQICmgICmwICnAICnQICngICnwICoAICoQICogICowICpAICpQICpgICpwICqAICqQICqgICqwICrAICrQICrgICrwICsAICsQICsgICswICtAICtQICtgICtwICuAICuQICugICuwICvAICvQICvgICvwICwAICwQICwgICwwICxAICxQICxgICxwICyAICyQICygICywICzAICzQICzgICzwIC0AIC0QIC0gIC0wIC1AIC1QIC1gIC1wIC2AIC2QIC2gIC2wIC3AIC3QIC3gIC3wIC4AIC4QIC4gIC4wIC5AIC5QIC5gIC5wIC6AIC6QIC6gIC6wIC7AIC7QIC7gIC7wIC8AIC8QIC8gIC8wIC9AIC9QIC9gIC9wIC AIC QIC gIC wIC/AIC/QIC/gIC/wICgAMCgQMCggMCgwMChAMChQMChgMChwMCiAMCiQMCigMCiwMCjAMCjQMCjgMCjwMCkAMCkQMCkgMCkwMClAMClQMClgMClwMCmAMCmQMCmgMCmwMCnAMCnQMCngMCnwMCoAMCoQMCogMCowMCpAMCpQMCpgMCpwMCqAMCqQMCqgMCqwMCrAMCrQMCrgMCrwMCsAMCsQMCsgMCswMCtAMCtQMCtgMCtwMCuAMCuQMCugMCuwMCvAMCvQMCvgMCvwMCwAMCwQMCwgMCwwMCxAMCxQMCxgMCxwMCyAMCyQMCygMCywMCzAMCzQMCzgMCzwMC0AMC0QMC0gMC0wMC1AMC1QMC1gMC1wMC2AMC2QMC2gMC2wMC3AMC3QMC3gMC3wMC4AMC4QMC4gMC4wMC5AMC5QMC5gMC5wMC6AMC6QMC6gMC6wMC7AMW7QMQBQzkvIHkuJrnroDnp7AFDOS8geS4mueugOensGcQBQRBRlRJBQRBRlRJZxAFBUJEQkFMBQVCREJBTGcQBQJMRwUCTEdnEAUDTkVDBQNORUNnEAUHUEFOVEVDSAUHUEFOVEVDSGcQBQNUQ0wFA1RDTGcQBQlUQ0zlpKnkuIAFCVRDTOWkqeS4gGcQBQRVQ09PBQRVQ09PZxAFC1VU5pav6L6 5bq3BQtVVOaWr i vuW6t2cQBQzpmL/lsJTljaHnibkFDOmYv WwlOWNoeeJuWcQBQnln4Pnq4vnibkFCeWfg eri eJuWcQBQ/niLHlsJTpqazml7bku6MFD eIseWwlOmprOaXtuS7o2cQBQnniLHkuYXkuYUFCeeIseS5heS5hWcQBQnniLHnq4vkv6EFCeeIseeri S/oWcQBQnniLHmkannmbsFCeeIseaRqeeZu2cQBQnniLHlp4blhYsFCeeIseWnhuWFi2cQBQnniLHnurPmmJ8FCeeIsee6s aYn2cQBQnniLHnu7TnibkFCeeIsee7tOeJuWcQBQbniLHmiJEFBueIseaIkWcQBQnniLHmlrDms7AFCeeIseaWsOazsGcQBQnlronnp5Horq8FCeWuieenkeiur2cQBQnlronliKnlmIkFCeWuieWIqeWYiWcQBQnlronliKnmma4FCeWuieWIqeaZrmcQBQblpaXkuIEFBuWlpeS4gWcQBQnlpaXlhYvmlq8FCeWlpeWFi aWr2cQBQblpaXkuZAFBuWlpeS5kGcQBQznmb7ngbXokbPmnJcFDOeZvueBteiRs acl2cQBQzkvbDnm5vpo47kupEFDOS9sOebm mjjuS6kWcQBQbpgqbljY4FBumCpuWNjmcQBQbpgqborq8FBumCpuiur2cQBQnpgqborq/ovr4FCemCpuiur i vmcQBQbluK7nm5sFBuW4ruebm2cQBQnlrp3mjbforq8FCeWuneaNt iur2cQBQblrp3noIEFBuWuneeggWcQBQnotJ3lsJTkuLAFCei0neWwlOS4sGcQBQbotJ3pvpkFBui0nem mWcQBQzmr5Tphbfml7bku6MFDOavlOmFt aXtuS7o2cQBQbms6Llr7wFBuazouWvvGcQBQbljZrkuqgFBuWNmuS6qGcQBQzljZrnkZ7kuJbnuqoFDOWNmueRnuS4lue6qmcQBQnmraXmraXpq5gFCeatpeatpemrmGcQBQblvanmmbYFBuW9qeaZtmcQBQbmmIznoZUFBuaYjOehlWcQBQbplb/ombkFBumVv iZuWcQBQzplb/msZ/lpKnpn7MFDOmVv axn Wkqemfs2cQBQbplb/okKUFBumVv iQpWcQBQbotoXmmI4FBui2heaYjmcQBQbmmajlhbQFBuaZqOWFtGcQBQ/mmajlhbTluIzlp4bpgJoFD aZqOWFtOW4jOWnhumAmmcQBQbor5rln7oFBuivmuWfumcQBQzkvKDlpYfmlbDnoIEFDOS8oOWlh aVsOeggWcQBQnliJvkuJbog70FCeWIm S4luiDvWcQBQbliJvnu7QFBuWIm e7tGcQBQbovr7mmboFBui vuaZumcQBQzlpKfmiJDnp5HmioAFDOWkp aIkOenkeaKgGcQBQzlpKfllJDnlLXkv6EFDOWkp WUkOeUteS/oWcQBQzlpKfllJDnp7vliqgFDOWkp WUkOenu WKqGcQBQblpKfkuLoFBuWkp S4umcQBQblpKfmmL4FBuWkp aYvmcQBQzlpKfmmL7ms5vms7AFDOWkp aYvuazm azsGcQBQzlpKfkvJfnlLXohJEFDOWkp S8l eUteiEkWcQBQbmiLTlsJQFBuaItOWwlGcQBQblvrfotZsFBuW t i1m2cQBQblvrfkv6EFBuW t S/oWcQBQnov6rmr5TnibkFCei/quavlOeJuWcQBQnov6rkvbPpgJoFCei/quS9s mAmmcQBQbov6rnvo4FBui/que jmcQBQbov6rlppkFBui/quWmmWcQBQzov6rmlq/ljaHlqIEFDOi/quaWr WNoeWogWcQBQnov6rms7DlhYMFCei/quazsOWFg2cQBQbluJ3ni7wFBuW4neeLvGcQBQbpvI7nkYQFBum8jueRhGcQBQzkuJzmlrnpgJrkv6EFDOS4nOaWuemAmuS/oWcQBQbkuJzojIIFBuS4nOiMgmcQBQbkuJzmo64FBuS4nOajrmcQBQzkuJzmo67kvJ/ms7AFDOS4nOajruS8n azsGcQBQbkuJzpk4EFBuS4nOmTgWcQBQzkuJzojp7oiKrlpKkFDOS4nOiOnuiIquWkqWcQBQnlpJrovr7lurcFCeWkmui vuW6t2cQBQnlpJrmma7ovr4FCeWkmuaZrui vmcQBQbmnLXllK8FBuacteWUr2cQBQbmganms70FBuaBqeazvWcQBQnpo57liKnmtaYFCemjnuWIqea1pmcQBQzls7Dms73ogZTlkowFDOWzsOazveiBlOWSjGcQBQbng73ngasFBueDveeBq2cQBQbplIvlvakFBumUi W9qWcQBQnplIvovr7pgJoFCemUi i vumAmmcQBQbnpo/ml6UFBuemj aXpWcQBQnnpo/lhbTovr4FCeemj WFtOi vmcQBQnlr4zmmKXmsZ8FCeWvjOaYpeaxn2cQBQnmuK/liKnpgJoFCea4r WIqemAmmcQBQbmuK/pvpkFBua4r m mWcQBQbpq5jph5EFBumrmOmHkWcQBQbpq5jmmIcFBumrmOaYh2cQBQnpq5jnm5vovr4FCemrmOebm i vmcQBQzpq5jmlq/otJ3lsJQFDOmrmOaWr i0neWwlGcQBQbpq5jpgJoFBumrmOmAmmcQBQnpq5jlqIHlsJQFCemrmOWogeWwlGcQBQbpq5jnv5QFBumrmOe/lGcQBQnpq5jmlrDlpYcFCemrmOaWsOWlh2cQBQbpq5jorq8FBumrmOiur2cQBQzpq5jorq/pnLjngbUFDOmrmOiur mcuOeBtWcQBQnmoLzojrHnibkFCeagvOiOseeJuWcQBQbosLfmtL4FBuiwt a0vmcQBQblhqDnkZ4FBuWGoOeRnmcQBQzlub/kuJzpq5jnp5EFDOW5v S4nOmrmOenkWcQBQblub/kv6EFBuW5v S/oWcQBQblm73ombkFBuWbveiZuWcQBQnlm73lipvpgJoFCeWbveWKm mAmmcQBQblm73kub4FBuWbveS5vmcQBQblm73lqIEFBuWbveWogWcQBQblm73kv6EFBuWbveS/oWcQBQnmtbflvrfkuJYFCea1t W t S4lmcQBQbmtbflsJQFBua1t WwlGcQBQbmtbfkv6EFBua1t S/oWcQBQbnpr7lhbQFBuemvuWFtGcQBQblkozkv6EFBuWSjOS/oWcQBQnlkozmraPorq8FCeWSjOato iur2cQBQzmsrPljZfnlLXlrZAFDOays WNl eUteWtkGcQBQ/msrPmupDnibnngbXpgJoFD ays a6kOeJueeBtemAmmcQBQzmgZLln7rkvJ/kuJoFDOaBkuWfuuS8n S4mmcQBQzmgZLmtqborq/ovr4FDOaBkua2puiur i vmcQBQbmgZLkv6EFBuaBkuS/oWcQBQnmgZLlrofkuLAFCeaBkuWuh S4sGcQBQblro/ovr4FBuWuj i vmcQBQblro/lurcFBuWuj W6t2cQBQblro/nooEFBuWuj eigWcQBQnlro/ms7DlsJQFCeWuj azsOWwlGcQBQzlro/kuLrorq/kuJoFDOWuj S4uuiur S4mmcQBQzombnms7Dnsr7oi7EFDOiZueazsOeyvuiLsWcQBQnpuL/mtqbovr4FCem4v a2pui vmcQBQnljY7lpaXpgJoFCeWNjuWlpemAmmcQBQbljY7lrp0FBuWNjuWunWcQBQbljY7oh6MFBuWNjuiHo2cQBQnljY7kvbPov4UFCeWNjuS9s i/hWcQBQbljY7nq4sFBuWNjueri2cQBQzljY7nq4vml7bku6MFDOWNjueri aXtuS7o2cQBQbljY7lvZUFBuWNjuW9lWcQBQbljY7mo64FBuWNjuajrmcQBQzljY7onIDmnJfpgJoFDOWNjuicgOacl mAmmcQBQbljY7llJAFBuWNjuWUkGcQBQbljY7kuLoFBuWNjuS4umcQBQzljY7lpI/ohb7lrocFDOWNjuWkj iFvuWuh2cQBQzljY7kv6Hnmb7ovr4FDOWNjuS/oeeZvui vmcQBQzljY7kv6Hml7bku6MFDOWNjuS/oeaXtuS7o2cQBQzljY7mmJ/orq/ovr4FDOWNjuaYn iur i vmcQBQbljY7orq8FBuWNjuiur2cQBQnljY7lrofog70FCeWNjuWuh iDvWcQBQbljY7nprkFBuWNjuemuWcQBQzmsYfkuLDmupDpgJoFDOaxh S4sOa6kOmAmmcQBQnmsYfnp5Hnm5sFCeaxh enkeebm2cQBQzmsYfpgJrkuJbnuqoFDOaxh mAmuS4lue6qmcQBQbmsYforq8FBuaxh iur2cQBQbmg6Dnp5EFBuaDoOenkWcQBQblkInpgqYFBuWQiemCpmcQBQnlkInkuovovr4FCeWQieS6i i vmcQBQnlkInnpaXkvbMFCeWQieelpeS9s2cQBQnlkInnpaXpuJ8FCeWQieelpem4n2cQBQbpm4blj4sFBumbhuWPi2cQBQbmioDlmIkFBuaKgOWYiWcQBQnkvbPkuJbovr4FCeS9s S4lui vmcQBQbkvbPpgJoFBuS9s mAmmcQBQbkvbPmg7MFBuS9s aDs2cQBQzkvbPln5/lrofpgJoFDOS9s Wfn Wuh mAmmcQBQblmInlsJoFBuWYieWwmmcQBQblmInmupAFBuWYiea6kGcQBQnmnbDms7DlsJQFCeadsOazsOWwlGcQBQbmjbfmnaUFBuaNt adpWcQBQnmjbfot6/pgJoFCeaNt i3r mAmmcQBQnmjbfkvJ/orq8FCeaNt S8n iur2cQBQbmjbforq8FBuaNt iur2cQBQbph5Hnv7AFBumHkee/sGcQBQnph5Hlh6/ms7AFCemHkeWHr azsGcQBQnph5Hlh6/kuLoFCemHkeWHr S4umcQBQbph5Hnq4sFBumHkeeri2cQBQzph5HliKnpgJrovr4FDOmHkeWIqemAmui vmcQBQbph5HpvpkFBumHkem mWcQBQbph5HojIIFBumHkeiMgmcQBQbph5Hmt7wFBumHkea3vGcQBQbph5Hpk60FBumHkemTrWcQBQbph5HpuY8FBumHkem5j2cQBQzph5Hlk4HotKjkv6EFDOmHkeWTgei0qOS/oWcQBQnph5Hohb7kur8FCemHkeiFvuS6v2cQBQzph5HlhbTljY7lrocFDOmHkeWFtOWNjuWuh2cQBQbph5HmmJ8FBumHkeaYn2cQBQbph5HkuJoFBumHkeS4mmcQBQbph5HkvJcFBumHkeS8l2cQBQbkuqznk7cFBuS6rOeTt2cQBQbkuqzltI4FBuS6rOW0jmcQBQznsr7nkZ7mnLroiq8FDOeyvueRnuacuuiKr2cQBQ/kupXlhojlsbHljY7nprkFD S6leWGiOWxseWNjuemuWcQBQ/kupXlhojlsbHnm5vms7AFD S6leWGiOWxseebm azsGcQBQblkJvniLUFBuWQm eItWcQBQbpqo/ln58FBumqj Wfn2cQBQnljaHnvo7mrKcFCeWNoee juasp2cQBQnljaHopb/mrKcFCeWNoeilv asp2cQBQnlh6/ljZrnibkFCeWHr WNmueJuWcQBQnlh6/ov6rkupoFCeWHr i/quS6mmcQBQbpk6Dln7oFBumToOWfumcQBQblurfkvbMFBuW6t S9s2cQBQblurflipsFBuW6t WKm2cQBQnnp5Hmva7ovr4FCeenkea9rui vmcQBQbnp5HlgaUFBuenkeWBpWcQBQbnp5HphbcFBuenkemFt2cQBQ/np5HliKnojrHml7bku6MFD enkeWIqeiOseaXtuS7o2cQBQbnp5HmkakFBuenkeaRqWcQBQbnp5Hor7oFBuenkeivumcQBQbnp5Hnm5sFBuenkeebm2cQBQbnp5Hms7AFBuenkeazsGcQBQznp5Hnjovnur3niLEFDOenkeeOi e6veeIsWcQBQbmuLTmnJsFBua4tOacm2cQBQnlhYvojrHmlq8FCeWFi iOseaWr2cQBQbphbfniLEFBumFt eIsWcQBQbphbfmr5QFBumFt avlGcQBQbphbfmva4FBumFt a9rmcQBQbphbfljaEFBumFt WNoWcQBQbmmIbovr4FBuaYhui vmcQBQbmnJfmnbAFBuacl adsGcQBQbmnJfnv5QFBuacl e/lGcQBQnmnJfmmJ/ovr4FCeacl aYn i vmcQBQbmtarmva4FBua1qua9rmcQBQzmtarmva7kuZDph5EFDOa1qua9ruS5kOmHkWcQBQnkuZDnmb7ngbUFCeS5kOeZvueBtWcQBQzkuZDph5Hmtarmva4FDOS5kOmHkea1qua9rmcQBQnkuZDnvo7kvbMFCeS5kOe juS9s2cQBQblipvlkIgFBuWKm WQiGcQBQbogZTliJsFBuiBlOWIm2cQBQnogZTnu7TkupoFCeiBlOe7tOS6mmcQBQbogZTmg7MFBuiBlOaDs2cQBQblh4znp5EFBuWHjOenkWcQBQblh4zms7AFBuWHjOazsGcQBQblh4zmi5MFBuWHjOaLk2cQBQblh4zpubAFBuWHjOm5sGcQBQzpooboiKrkupLliqgFDOmihuiIquS6kuWKqGcQBQnpvpnpvpnkuZ0FCem mem meS5nWcQBQbpvpnml5cFBum meaXl2cQBQbpmobmiJAFBumahuaIkGcQBQnov4johb7ovr4FCei/iOiFvui vmcQBQbnvo7oj7EFBue juiPsWcQBQnnvo7nm5vpgJoFCee juebm mAmmcQBQznvo7nv7zmma/kuJoFDOe jue/vOaZr S4mmcQBQbprYXml48FBumtheaXj2cQBQbnm5/lrp0FBuebn WunWcQBQbmlY/orq8FBuaVj iur2cQBQzmmI7ln7rnlLXpgJoFDOaYjuWfuueUtemAmmcQBQbpk63ku4EFBumTreS7gWcQBQnmkanlvrfojrEFCeaRqeW t iOsWcQBQzmkannjpvml7bku6MFDOaRqeeOm aXtuS7o2cQBQbmkanog70FBuaRqeiDvWcQBQzmkanlpKnml7bku6MFDOaRqeWkqeaXtuS7o2cQBQzmkanmiZjnvZfmi4kFDOaRqeaJmOe9l aLiWcQBQnnurPkvJ/ku5UFCee6s S8n S7lWcQBQzljZfmlrnpq5jnp5EFDOWNl aWuemrmOenkWcQBQnljZfmnoHmmJ8FCeWNl aegeaYn2cQBQbljZflh68FBuWNl WHr2cQBQznur3mm7zlronmma4FDOe6veabvOWuieaZrmcQBQznur3mm7zmlbDnoIEFDOe6veabvOaVsOeggWcQBQznur3mm7zkvJ/kuJoFDOe6veabvOS8n S4mmcQBQbnur3nibkFBue6veeJuWcQBQbnur3nu7QFBue6vee7tGcQBQzor7roj7LkuJbnuqoFDOivuuiPsuS4lue6qmcQBQnor7rln7rkupoFCeivuuWfuuS6mmcQBQbor7rljaEFBuivuuWNoWcQBQnor7rliKnkvbMFCeivuuWIqeS9s2cQBQnor7rkuprpuL0FCeivuuS6mum4vWcQBQnor7rkuprkv6EFCeivuuS6muS/oWcQBQbmrKfmr5QFBuasp avlGcQBQnmrKfljZrkv6EFCeasp WNmuS/oWcQBQbmrKfnp5EFBuasp enkWcQBQbmrKfphbcFBuasp mFt2cQBQbmrKfnj4AFBuasp ePgGcQBQbmrKflpYcFBuasp Wlh2cQBQbmrKfokKgFBuasp iQqGcQBQbmrKfkuIoFBuasp S4imcQBQbmrKfnm5sFBuasp ebm2cQBQbmrKfnvZEFBuasp e9kWcQBQbmrKfmraMFBuasp ato2cQBQbmtL7msoMFBua0vuayg2cQBQboi7nmnpwFBuiLueaenGcQBQnmma7niLHovr4FCeaZrueIsei vmcQBQnmma7ojrHovr4FCeaZruiOsei vmcQBQbmma7ogZQFBuaZruiBlGcQBQzmma7lpKnnjovoip0FDOaZruWkqeeOi iKnWcQBQzmma7lpKnlrpzpgJoFDOaZruWkqeWunOmAmmcQBQbkuIPllpwFBuS4g WWnGcQBQnkuIPmmJ/msrMFCeS4g aYn ays2cQBQbpvZDkuZAFBum9kOS5kGcQBQzkvIHllYbogZTlkIgFDOS8geWVhuiBlOWQiGcQBQzlkK/kuJzkvJjmgJ0FDOWQr S4nOS8mOaAnWcQBQblvLrlvLoFBuW8uuW8umcQBQbkvqjlhbQFBuS qOWFtGcQBQbluobpgqYFBuW6humCpmcQBQblhajnm4gFBuWFqOebiGcQBQnojaPkuovovr4FCeiNo S6i i vmcQBQbnkZ7mgZIFBueRnuaBkmcQBQnokKjln7rlp4YFCeiQqOWfuuWnhmcQBQnokKjpmYXpgJoFCeiQqOmZhemAmmcQBQnotZvlsJTmnpcFCei1m WwlOael2cQBQbotZvpuL8FBui1m m4v2cQBQnkuInlkozmlrAFCeS4ieWSjOaWsGcQBQbkuInoj7EFBuS4ieiPsWcQBQnkuInnvo7nkKYFCeS4iee jueQpmcQBQbkuInmma4FBuS4ieaZrmcQBQbkuInnibkFBuS4ieeJuWcQBQbkuInmmJ8FBuS4ieaYn2cQBQzkuInmmJ/np5HlgaUFDOS4ieaYn enkeWBpWcQBQbkuInmtIsFBuS4iea0i2cQBQzkuInkvJfnjrDku6MFDOS4ieS8l eOsOS7o2cQBQbmoZHoj7IFBuahkeiPsmcQBQbmo67lr4YFBuajruWvhmcQBQblsbHmsLQFBuWxseawtGcQBQblsbHnlLAFBuWxseeUsGcQBQzkuIrmtbfmg6Dmma4FDOS4iua1t aDoOaZrmcQBQzkuIrlloTnp5HmioAFDOS4iuWWhOenkeaKgGcQBQznlLPnurPnp5HmioAFDOeUs e6s enkeaKgGcQBQbmt7HniLEFBua3seeIsWcQBQzmt7HlnLPmjK/ljY4FDOa3seWcs aMr WNjmcQBQnlnKPlh6/nkZ4FCeWco WHr eRnmcQBQnnm5vpmoblhbQFCeebm mahuWFtGcQBQnnm5vkub7pgJoFCeebm S5vumAmmcQBQnnm5vorq/ovr4FCeebm iur i vmcQBQzml7bku6PljY7pvpkFDOaXtuS7o WNjum mWcQBQzml7bpgJrkvJ/kuJoFDOaXtumAmuS8n S4mmcQBQzkuJbnuqrkvJ/kuJoFDOS4lue6quS8n S4mmcQBQzkuJbnuqrmmJ/lrocFDOS4lue6quaYn Wuh2cQBQzkuJbnuqrljZPpo54FDOS4lue6quWNk mjnmcQBQzkuJboqonkv6Hovr4FDOS4luiqieS/oei vmcQBQbpppbmtL4FBummlua0vmcQBQbpppbkv6EFBummluS/oWcQBQbpppbkur8FBummluS6v2cQBQnmlbDml7bovr4FCeaVsOaXtui vmcQBQbmlbDmupAFBuaVsOa6kGcQBQblj4zkvqgFBuWPjOS qGcQBQblj4zotaIFBuWPjOi1omcQBQznoZXpopbmlbDnoIEFDOehlemiluaVsOeggWcQBQzmgJ3ljaHliKnlvpcFDOaAneWNoeWIqeW l2cQBQbmgJ3nibkFBuaAneeJuWcQBQbmnb7kuIsFBuadvuS4i2cQBQnmnb7orq/ovr4FCeadvuiur i vmcQBQzpmo/lt57ms6Llr7wFDOmaj W3nuazouWvvGcQBQzntKLniLHmlbDnoIEFDOe0oueIseaVsOeggWcQBQbntKLpuL8FBue0oum4v2cQBQ/ntKLlsLzniLHnq4vkv6EFD e0ouWwvOeIseeri S/oWcQBQbms7DkuLAFBuazsOS4sGcQBQbms7DokpkFBuazsOiSmWcQBQnms7DnkZ7kuLAFCeazsOeRnuS4sGcQBQzms7DmmJ/mlbDnoIEFDOazsOaYn aVsOeggWcQBQnnibnngbXpgJoFCeeJueeBtemAmmcQBQblpKnmrYwFBuWkqeatjGcQBQblpKnkuL0FBuWkqeS4vWcQBQblpKnov4gFBuWkqei/iGcQBQblpKnli6QFBuWkqeWLpGcQBQnlpKnml7bovr4FCeWkqeaXtui vmcQBQ/lpKnml7bovr7np5HmioAFD WkqeaXtui vuenkeaKgGcQBQnlpKnlpKnpq5gFCeWkqeWkqemrmGcQBQnlpKnmmJ/pgJoFCeWkqeaYn mAmmcQBQzlpKnlrofmnJfpgJoFDOWkqeWuh acl mAmmcQBQnlpKnlhYPpgJoFCeWkqeWFg mAmmcQBQbpgJrlub8FBumAmuW5v2cQBQbpgJrliJkFBumAmuWImWcQBQnlkIznq4vojrEFCeWQjOeri iOsWcQBQblkIzlqIEFBuWQjOWogWcQBQblkIzmtLIFBuWQjOa0smcQBQblvaTpnJYFBuW9pOmclmcQBQbnu5/luoYFBue7n W6hmcQBQzmi5PlsZXml6DpmZAFDOaLk WxleaXoOmZkGcQBQnkuIfliKnovr4FCeS4h WIqei vmcQBQnkuIfkuovpgJoFCeS4h S6i mAmmcQBQnnvZHml7bku6MFCee9keaXtuS7o2cQBQbnvZHorq8FBue9keiur2cQBQnml7rlvrfor5oFCeaXuuW t ivmmcQBQnml7rlr4zpgJoFCeaXuuWvjOmAmmcQBQbllK/lpaUFBuWUr WlpWcQBQbllK/lvIAFBuWUr W8gGcQBQbllK/np5EFBuWUr enkWcQBQbluLfluYQFBuW4t W5hGcQBQbnu7Tlm74FBue7tOWbvmcQBQbpl7vorq8FBumXu iur2cQBQnmiJHniLHkvaAFCeaIkeeIseS9oGcQBQnmsoPlh6/ms7AFCeayg WHr azsGcQBQnmsoPmma7kuLAFCeayg aZruS4sGcQBQnmsoPms7DlsJQFCeayg azsOWwlGcQBQbmsoPpgJQFBuayg mAlGcQBQbopb/lh68FBuilv WHr2cQBQbopb/lj68FBuilv WPr2cQBQbopb/nvo4FBuilv e jmcQBQnopb/pl6jlrZAFCeilv mXqOWtkGcQBQbluIzlp4YFBuW4jOWnhmcQBQbllpzljaEFBuWWnOWNoWcQBQblpI/mnJcFBuWkj acl2cQBQzlpI/mtablhYnnlLUFDOWkj a1puWFieeUtWcQBQblpI/mma4FBuWkj aZrmcQBQblpI/mlrAFBuWkj aWsGcQBQblpI/mmJ8FBuWkj aYn2cQBQbljqbljY4FBuWOpuWNjmcQBQbljqbmlrAFBuWOpuaWsGcQBQblhYjliJsFBuWFiOWIm2cQBQzlkrjpgJroh7TkuIAFDOWSuOmAmuiHtOS4gGcQBQbnpaXpm4YFBuelpembhmcQBQzljY/liJvnq4vnp5EFDOWNj WIm eri enkWcQBQnmlrDmoIfovr4FCeaWsOagh i vmcQBQnmlrDpo57mtIsFCeaWsOmjnua0i2cQBQnmlrDlm73ohIkFCeaWsOWbveiEiWcQBQbmlrDlqIEFBuaWsOWogWcQBQbmlrDpgq4FBuaWsOmCrmcQBQnmlrDpgq7pgJoFCeaWsOmCrumAmmcQBQnmlrDkuK3moaUFCeaWsOS4reahpWcQBQnpkavogZTlrocFCemRq iBlOWuh2cQBQbpkavor7oFBumRq ivumcQBQnpkavnjovniYwFCemRq eOi eJjGcQBQzpkavlrofkvJ/kuJoFDOmRq Wuh S8n S4mmcQBQnpkavms73lrocFCemRq azveWuh2cQBQnpkavljZPotooFCemRq WNk i2imcQBQnkv6HlvpfkuZAFCeS/oeW l S5kGcQBQbkv6Hlrp4FBuS/oeWunmcQBQbkv6Hnm4gFBuS/oeebiGcQBQzkv6HlhYPlpKnovrAFDOS/oeWFg Wkqei sGcQBQzlhbTliJvkvJ/kuJoFDOWFtOWIm S8n S4mmcQBQnlhbTljY7lrp0FCeWFtOWNjuWunWcQBQnmmJ/lrp3pgJoFCeaYn WunemAmmcQBQbmmJ/ljY4FBuaYn WNjmcQBQnmmJ/np5Hlo6wFCeaYn enkeWjrGcQBQzmmJ/nvZHplJDmjbcFDOaYn e9kemUkOaNt2cQBQbpm4Tpo44FBumbhOmjjmcQBQbpm4Tmi5MFBumbhOaLk2cQBQbnhornjKsFBueGiueMq2cQBQnml63mib/kuLAFCeaXreaJv S4sGcQBQbngqvljY4FBueCq WNjmcQBQnpm4Xorq/ovr4FCembheiur i vmcQBQzpm4Xov4XnvZHnu5wFDOmbhei/hee9kee7nGcQBQnpm4XlpZXlvpcFCembheWlleW l2cQBQnkuprlipvpgJoFCeS6muWKm mAmmcQBQ/kuprlipvpgJrnp7vliqgFD S6muWKm mAmuenu WKqGcQBQnkuprkv6HpgJoFCeS6muS/oemAmmcQBQbmiazmlrAFBuaJrOaWsGcQBQzlrpzlhbTkuK3nlLUFDOWunOWFtOS4reeUtWcQBQbkur/ln44FBuS6v WfjmcQBQnkur/lkozmupAFCeS6v WSjOa6kGcQBQnkur/lmInpkasFCeS6v WYiemRq2cQBQzkur/np5Hms7Dovr4FDOS6v enkeazsOi vmcQBQbkur/pgJoFBuS6v mAmmcQBQblsbnkuJwFBuWxueS4nGcQBQzmmJPkuLDlsZXkuJoFDOaYk S4sOWxleS4mmcQBQnmmJPnibnnp5EFCeaYk eJueenkWcQBQnnv7zovr7pvpkFCee/vOi vum mWcQBQzpk7bmmJ/mlbDnoIEFDOmTtuaYn aVsOeggWcQBQzoi7Hovr7mgJ3lurcFDOiLsei vuaAneW6t2cQBQnoi7HljY7ovr4FCeiLseWNjui vmcQBQnoi7HojrHlsJQFCeiLseiOseWwlGcQBQboi7Hov4gFBuiLsei/iGcQBQboi7Hmma4FBuiLseaZrmcQBQznm4jng6jliJvmlrAFDOebiOeDqOWIm aWsGcQBQnkvJjlsJTlvpcFCeS8mOWwlOW l2cQBQbkvJjmkakFBuS8mOaRqWcQBQzkvJjmtL7njq/lrocFDOS8mOa0vueOr Wuh2cQBQnlj4vliKnpgJoFCeWPi WIqemAmmcQBQblj4vloIIFBuWPi WggmcQBQblj4vml7oFBuWPi aXumcQBQnlj4vkv6Hovr4FCeWPi S/oei vmcQBQblroflurcFBuWuh W6t2cQBQblrofpvpkFBuWuh m mWcQBQblrofpmLMFBuWuh mYs2cQBQbnprnljY4FBuemueWNjmcQBQzor63kv6Hml7bku6MFDOivreS/oeaXtuS7o2cQBQbms73pooYFBuazvemihmcQBQzlsZXnv7zkvJ/kuJoFDOWxlee/vOS8n S4mmcQBQnlhYborq/ovr4FCeWFhuiur i vmcQBQbmjK/ljY4FBuaMr WNjmcQBQzmjK/ljY7nmb7mmboFDOaMr WNjueZvuaZumcQBQzmjK/ljY7lrofnp5EFDOaMr WNjuWuh enkWcQBQzor4HpgJrph5Hkv6EFDOivgemAmumHkeS/oWcQBQznn6Xlt7Hov4XogZQFDOefpeW3sei/heiBlGcQBQboh7Ppq5gFBuiHs mrmGcQBQblv5fkvbMFBuW/l S9s2cQBQzlv5fpgaXlkIzlv4MFDOW/l mBpeWQjOW/g2cQBQbkuK3lrp0FBuS4reWunWcQBQbkuK3ovrAFBuS4rei sGcQBQbkuK3nlLUFBuS4reeUtWcQBQbkuK3lrr0FBuS4reWuvWcQBQnkuK3mtabnkZ4FCeS4rea1pueRnmcQBQbkuK3moaUFBuS4reahpWcQBQ/kuK3lsbHlm73kv6HpgJoFD S4reWxseWbveS/oemAmmcQBQzkuK3lsbHpgLjku5kFDOS4reWxsemAuOS7mWcQBQbkuK3lpKkFBuS4reWkqWcQBQnkuK3kvJ/lpKkFCeS4reS8n WkqWcQBQzkuK3kv6HljavmmJ8FDOS4reS/oeWNq aYn2cQBQbkuK3lhbQFBuS4reWFtGcQBQzkuK3orq/pgJrovr4FDOS4reiur mAmui vmcQBQnkuK3ovbTnur8FCeS4rei9tOe6v2cQBQbkvJfkuIAFBuS8l S4gGcQBQnljZPnq4vmiJAFCeWNk eri aIkGcQBQzntKvlhYnmtbfms7AFDOe0q WFiea1t azsGcWAQIBZAIDDxBkEBUCB0FGVEkgQTgHQUZUSSBBORUCCCc4NzUwJy0wCCc4NzUwJy0xFCsDAmdnFgFmZAIhDw9kFgIeB29uY2xpY2sFD0Rpc2FibGVCdXR0b24oKWQCKg9kFgICAQ8QZGQWAGQCMg9kFgICAQ8PFgQfABsAAAAAAABZQAEAAAAfAQKAAhYKHwIFBDEwMCUfAwUEMTAwJR8EBQEwHwUFATAfBgUBMGQYAQUeX19Db250cm9sc1JlcXVpcmVQb3N0QmFja0tleV9fFgEFCURldmljZVBpY7 RNpiC yiqegD3EhG8qTbYVuAY");
			p5 = new NameValuePair("RAL","DDLPP");
			p6 = new NameValuePair("DR","DDLPP");
			p7 = new NameValuePair("DDLPP","DDLPP");
			p8 = new NameValuePair("DDLXH","DDLPP");
			p9 = new NameValuePair("txtIMEI2","DDLPP");
			p10 = new NameValuePair("txtNowExecute","DDLPP");
			p11 = new NameValuePair("txtXKZ","DDLPP");
			p12 = new NameValuePair("txtTimeCount","DDLPP");
			p13 = new NameValuePair("txtZhen","DDLPP");
			p14 = new NameValuePair("txtJia","DDLPP");
			p15 = new NameValuePair("__EVENTVALIDATION","/wEWqgQCuKnmoAMC c3AhgUC c28hgUC c24hgUC c20hgUC c2whgUC99uM4gMCo8aqqwkCosaqqwkCrKmAxQUCzICIHQL1vtnpDQKNtJnuDQL6lK33AwKNtI3XCQKV2q2IAwK21K2IAwK21K2GAwKt2I3XCQKt2M2MCAKr2o3gAwL98 HsDAL7j5XhDALzla2IAwLXmpL0DAKppZX0DAK/o/LqDALbsvX1AwLR2a2IAwLR2a2GAwKTuJnuDQKTuI3XCQLPl8bCDAL7qK2iAgKSy6GAAwK3pI7XCQKypq73AwKNks60CALMy/GyAQLk6v2aCALDj83WDALN1M7UBAKMv5HvAwLrs87tAwLR16mzDwKWov3CDAKgoo lCgKp2OrYDgLl/YbGBAKwu GzCQLEzYHVBgL9q878BgKbhbOcCwKKi/fhDwKgkNH/CALaxuoMAueJud8GApbBidUKAtqv3tYEAuezz6oDAuCZ/MECApSslYgIAsKg0boHApLbo9UIAo7Mj4kJAoz64/cEAq x6NsEAsml5NwEAvbcjaICAvTV44wJAtuc4YMCApv/qLsLAsqN1uMLApv/5IULAt2ynY8BAuTy/PkKAtrvv90FAtrvo5QPAv7lmbwGAqL58PkHAsLc5fwCApPCqIsEAu3S/q4IApPXi7YOAsiwo0ICnpT0lgoC1vPEvAsC8r/ymg4C/YOlnAYC5/vc gQChInYlQQC8eeLxQ4C/r/l4AwC8eeb6A4Cu9a/ wQC6InUwgUCiMC10AoC1qKO8QUC pr3mgICnobI6Q0ClfHUvgQCstqPxQQCrvDxwg0CuvqLjg4C7KOp6Q4C2f6MuwsC2f78 AQCzL7rggsCuvqnyQkCrJHUqwoCqfrwrQ8CqfqotgsCw5/lzgUC/tvh7AoC5tq/twQC5tqLhgoCy7L01AkC4Yr pAkCk/n03gcCgvuX9QcC7N6Klw4ClNms0w4ClNm8jgUC876I7gwClNmo6QkC/MjSugsCrZbiXgK69ZLiDwKri6DgBQLnjfiuBQK 27KDDwKW5OfkBQLzrLDKBwKF58uyCgKd5I3hDgKBvpjmBAKltMrqDALjiNkfArme1ooFAqGZiLYOAtjA68EJAtjAg/sEAvz 6eEOAsG/5Y4MAtjAj8oJAr6Kyb8IAtjA17EEAv l2ooNAtjAo5QPApr4n8YKApGm9o0PAqnV/6AFAqDu/PUHAtOZteMOAvH5qLYLAvPoiMUOAsvpnogFAvPo/LoLAvPoqJsKAvPoqLYLAoWj7sMDAumX1KsKAumXqLYLArK 1MIFAoTkqLYLAvPGtbwDAo6Ts6YFAouCzPwLAq3zwbAMAr3smb4BAs6NqLYLAqiJp4oIAoHC/LQPAoHCwLwKAoHCqI8EAueHgs0KAvDc2eAOApuZ 78KAtX29c0PAtrwpvwFAsLz JcKAsLzkMYOArPpx9QLAsLzsJkEAv6Bk5kPAsLz2L0KAsLzvI4FAvmOyYYCAsLz5OMFAsLzjLsLAqaFkfgPAqbf8P8KAv6Bi7YCArHS/V0CwvOglA8Cy4CMxQwCwvOIhwQCs8W3/g4C7LmizgYClaGCewL5laCUDwLgjOiBBAKH5ZzMCQKr46anAgLyxOj1BALmsN/4CwL66LPRBQKAkMj7BQKR0bKjAgLt24zKCQLt29DOBALUuMKoDAKH6YyqCgKH6eS/BQLWzIPNCgLpk5jyBALBkM6QDgLY7YVcAumToJQPAs/u57YEAunjo74KAsfyncQOAs/us5kEAqfsscILAs/ui7YOAs/ur9MOAs/u96YFAs/uu 4JAs/uo4UOAsGR77oIAs7b28gLAsOglPcBAs/u4/sEAs/uj7oLAs/uw7MLAqTYwO4HAqTYvKMKAqrir6sBAuW68QoCrKD1wg0CleXY2AcCgcDj8QUC5vzfnAMC5vyD6goC/rOalQoC ebXIgLg5I/xBQKp NCyCwKp PDfBQLztJLaDwLPvpjKBQLPvsDCCQLfno6eAQLPvsjqBALPvoyRDwLPvvD5BwLPvuSuBQKYtYcaAqyZ8PMEArSCjvUMAunv69gHAunv15EFAunvv7IFAunvq9IFArqJ/LQPAomL5PIEAomL1LEEAq2B1uMPAuaXvLIFAuCexqYJAvG5n6gHAviFrNcKAru5i5kEApXwxOsFAszN89sFAqTK4qoJAszN084EAsTu6IEEAsTu5K4FAsTu0NkEAsTu5LoOAqqC624CwIGa/w0Cl/rD4QQCuujn2wQC7NDV6gECgrro1g4CmrueqgcCscL4MgL7w6PhBALRqPiXCgLBh6CUDwLjhOfqCgLj5Ku5CwLcltOCBQLj29TXDgLnhfjODgLj26yADQLexpeuBQKu6NiAAQKrkMnMCwKoj8bBCQLJ86DGBQLg2KnEAwLN  zWDAKSmcm4DwKzuIilBAKzuNS BAKS7uXHAQLO2prtDgL2oqrSBQKhgpqCCgK/grfrDwLb2dSuDwLZ4tSRBQK ntHMCQLZ4uiBBALZ4sDCCQLZ4qTRBwLZ4oCNCgLZ4sToDgLZ4sy7CwLZ4vD5BwLZ4ui9BALZ4pCSBQKyl5CvBQL30Pf3BAKGh97ADgKGh978AQLiidTVDgKF7Oz AQKwlvCFDgK92fTnBQKo27KAAgLQ  eFCwKly9vbBwLDpbXvDAK2 4yzCgKo2tTCBQK6 JzMCQLo78T5BwKByamnAgLSpezVBAL0n7fvCQKAyqm3CQLMyauBDALV1 O4DgLhgZnPAgKH2ejWDgKzn4bDBAKH2bz6BAKH2YilBAKH2eD7BAKc0pGMDAKH2bCjBQLhnamfBALPnezWDgLinJyRCgLv9dSoBQLv9eTgBwKQ ZHoCwKb0qGOAgKC2P6nDQLvlujYBwLehcb9CwLhm4XmBgKblZmcDgKtror3CgL5ppa8BgKe LaxBwLJ58EVAum gP4OAun01qQJAtWJvYYFAu/UhcYBAor /6AFAor q7YLAor 47wLApS9so4PAvCH5L8FAsTyxLQLAsTyrKIPAuX9tOcGAuC3/uEKApONiKUEAvKLsLsLApaBlrwGAsGGlrYDAvj2wLAMAt624LgOAtGvuMILAvCU5LgLAvCUiO4OAsqTg sFAorU2NMMAs 25oQHAqf/tJ0FAqf/ LgLAqf/xLEPAqf/lN4FAsv0so4PAoWV0YcGAomf1YIHAr/8jrQEAt6VpdoJAr/8vr8FApbt47oKApbti9oFAvn2w/cHAoTlqJsKAoTl7KAFApz73PMJAtG4nL8KAteC1cQKAt3WvsAMApHe3ukKAuqTmIYGAs 7oJQPAo6PxpoOAs6HwsMFAqHnmIIKAqHnpLMKAqHn6IEEAqn5lIUKAuy4/P4FAvXmo5QPApmItJ4IAteIoL4KApeTw/kKAuPZg80KAr2U1MsJAvHfo8YFAvHfo9YFAvHfv7cEAuvg7fQHAoT5nIEKApTmqNIFAoH/wPMEAq7TwsMBAoH/vPoEAoH/5OQEAoH/4PsEAtrtvN0FAtrt5OQEAsjv8NsFAv6PlfgPApu/nPkJAqTj/tcDAtS/9rAMAtP90bYFAp2H 2gCsIaomwoCsIa8zAkCiIfKvgkCgeX5owICzMry9gIC5eWPkQ8Co5fFMALEytjWDALMypbKCAKt15mKCgKZ3ba8CAKf2vyXCgKf2sT5BwLs8taBAQLLzMXlDAKVnd6WAQLpjobABQKRibzdBQLv9NOXDQLLk/OQDgL86L JDgL86NPZBAKGrrDQBwL9vYfYCgKlrLzdBQKf5pm8BgLQsvz6BwLykumDAQLu2Z6IBQKixL3oCwLu2bbgCgLkkeTkBAKNkduTDgKx2LzxBQLri96XCgK0i vlCgLLzMCxCwKx2IzKCQL39fS7CwKOruGgBgLigpWIAQLxn7uHAQKEpNrTDAKYremICAKTzpXFDALVlYnrDALv0MexDwLv0L/6BAK7q8rUBgKP/eKpAQKY28jqBALHxbarAwLd89aKBQLF8qyLCgLF8ozhBALp6J7eDQK5wsC8CgK5woi2DgK5wtD4CQK3vrzdBQKf8I62AgLzlJyPDgKq28nwDgKe5Za8BgKhk7zdBQKF/tTXCgKI8IskAtTHmdsPAsyjz8gEAu3Oh5gOAsn60LILAse18JsLAqPZ JcKAqPZ5LQPAqPZ2OAHAqPZ JQKAufJ4cMBAqPZmIoFAv 4ze0JAuXyy7APAqPZyIAKArDx5vQBAqa pOgPAqPZ1MIFArvepr4NAqLqu kCAonbpLsLAoecutAJArynztMMAqiir/4PApXri4INApXrv98FAq230IsIAsTM22kCy6zv9gEC3rvH2AkCi6GvvwcCrtfg6QECofvC9QMCpfDAigcCxJKJ8gMC//qGjgcCvKqv8gH22VgWYXoXtwdQw0DNtMnQlUP9Wg==");
			postMethod.setRequestBody(new NameValuePair[] { p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15});
			int status = client.executeMethod(postMethod);
			if(status == HttpStatus.SC_OK){
				String response =
			           new String(postMethod.getResponseBodyAsString().getBytes("8859_1"));
				System.out.println(" >> response:\r\n"+response);
			}
		}catch(Exception e){
			
		}finally{
			if(null != postMethod){
				postMethod.releaseConnection();
			}
		}
	}
	
	public static List<String> getWGLog(){
		List<String> list = new ArrayList<String>();
		
		return list;
	}
	
}
