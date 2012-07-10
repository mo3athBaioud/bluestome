package com.autohome;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.CompositeTag;
import org.htmlparser.util.NodeList;

public class C {

	BlockingQueue<byte[]> byteQuene = new LinkedBlockingQueue<byte[]>(100);

	static Map<String, Integer> SIZEHASH = new HashMap<String, Integer>();

	public void timeout(String webSite, Callback call) {
		URL cURL = null;
		HttpURLConnection connection = null;
		OutputStream out = null;
		InputStream in = null;
		long start = System.currentTimeMillis();
		try {
			cURL = new URL(webSite);
			connection = (HttpURLConnection) cURL.openConnection();
			// 获取输出流
			connection.setDoOutput(true);
			connection.setConnectTimeout(10 * 1000);
			connection.setReadTimeout(10 * 1000);
			connection
					.addRequestProperty(
							"User-Agent",
							"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.63 Safari/535.7");
			connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			connection.setRequestProperty("Connection", "close");
			connection.connect();
			System.out.println("\t 连接耗时: "
					+ (System.currentTimeMillis() - start) + " ms");
			start = System.currentTimeMillis();
			int code = connection.getResponseCode();
			System.out.println("\t 响应码:" + code);
			System.out.println("\t 获取响应码耗时: "
					+ (System.currentTimeMillis() - start) + " ms");
			start = System.currentTimeMillis();

			in = connection.getInputStream();
			ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
			int ch;
			while ((ch = in.read()) != -1) {
				byteBuffer.write(ch);
			}
			byteBuffer.flush();
			int size = byteBuffer.size();
			if (size > 0) {
				call.work(byteBuffer.toByteArray());
				System.out.println("\t 获取内容提耗时: "
						+ (System.currentTimeMillis() - start) + " ms");
			}
			start = System.currentTimeMillis();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void executorPoll() {
		final long pollTime = 30 * 1000L;
		final long timeout = 500L;
		final ScheduledExecutorService pool = Executors
				.newSingleThreadScheduledExecutor();
		final ExecutorService taskPool = Executors.newFixedThreadPool(3);
		pool.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				boolean isOk = false;
				long start = System.currentTimeMillis();
				try {
					while (!isOk) {
						long end = System.currentTimeMillis() - start;
						if (end <= pollTime + (timeout * 5)) {
							timeout(
									"http://club.autohome.com.cn/bbs/forum-c-2001-1.html",
									new Callback() {
										@Override
										public void work(final byte[] body) {
											taskPool.submit(new Runnable() {
												public void run() {
													String key = String.valueOf(body.length);
													Integer value = SIZEHASH.get(key);
													if(null == value){
														SIZEHASH.put(key, 1);
														String content = new String(body);
														parserBbs(content);
													}else{
														SIZEHASH.put(key, (value+1));
													}
												}
											});
										}
									});
							isOk = true;
						} else {
							System.out.println("\t Timeout["+ (pollTime / 1000) + "s]");
							break;
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("\t 方法执行耗时:"+ (System.currentTimeMillis() - start) + "ms");
				System.out.println("\r\n");
			}

		}, 100L, pollTime, TimeUnit.MILLISECONDS);
	}
	
	private void parserBbs(String content){
		Parser p1 = null;
		try{
			p1 = new Parser();
			p1.setInputHTML(content);
			p1.setEncoding("GB2312");
			NodeFilter fileter = new NodeClassFilter(CompositeTag.class);
			NodeList list = p1.extractAllNodesThatMatch(fileter)
					.extractAllNodesThatMatch(
							new HasAttributeFilter("class", "list_dl "));
			System.out.println(" 列表数量:"+list.size());
			/**
			 * c|2001|16099453|3|2012-07-10 13:36|2012-07-10 20:02|5385276|1048731|0|0|0|tnwwzh 
			 * 格式说明:
			 * 论坛标识|论坛编号|帖子编号|回复数|发帖时间|最后跟帖时间|未知|未知|未知|未知|未知|发帖人
			 */
			if(null != list && list.size() > 0){
				int i =0;
				do{
					CompositeTag tag = (CompositeTag)list.elementAt(i);
					String lang = tag.getAttribute("lang");
					String[] paras = lang.split("\\|");
					if(paras.length > 0 && paras[0].trim().equals("c") && paras[1].trim().equals("2001")){
						System.out.println(lang);
					}
					i++;
				}while(i<list.size());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null != p1){
				p1 = null;
			}
		}
	}

	public static void main(String args[]) {
		C c = new C();
		c.executorPoll();
	}
}