package com.autohome;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.CompositeTag;
import org.htmlparser.util.NodeList;

import com.autohome.json.ReplyJson;
import com.utils.DateUtils;
import com.utils.JSONUtils;

public class C {
	
	static String BSS_URL = "http://club.autohome.com.cn/bbs/forum-c-{id}-{page}.html";
	static String BBS_POST_URL = "http://club.autohome.com.cn/bbs/thread-c-{cid}-{pid}-1.html";
	BlockingQueue<byte[]> byteQuene = new LinkedBlockingQueue<byte[]>(100);
	BlockingQueue<String[]> replyQuene = new LinkedBlockingQueue<String[]>(Short.MAX_VALUE);

	static Map<String, Integer> SIZEHASH = new HashMap<String, Integer>();
	static Map<String, Integer> REPLAYED = new HashMap<String, Integer>();
	
	public void timeout(String webSite, Callback call) {
		System.err.println("执行时间:"+DateUtils.getNow());
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
			connection.setRequestProperty("Connection", "keep-alive");
			connection.setRequestProperty("Cache-Control", "max-age=0");
			connection.setRequestProperty("Referer", webSite);
			connection.connect();
			start = System.currentTimeMillis();
			int code = connection.getResponseCode();
			System.out.println("响应码:" + code);
			switch(code){
				case 200:
					start = System.currentTimeMillis();
					connection.setReadTimeout(60*1000);
					in = connection.getInputStream();
					ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
					int ch;
					while ((ch = in.read()) != -1) {
						byteBuffer.write(ch);
					}
					byteBuffer.flush();
					int size = byteBuffer.size();
					System.out.println("输出流大小:"+size);
					if (size > 0) {
						call.work(byteBuffer.toByteArray());
					}
					byteBuffer.close();
					start = System.currentTimeMillis();
					break;
				default:
					System.err.println("错误码:"+code);
					break;
			}
		} catch (IOException e) {
			System.err.println(e);
		} catch (Exception e) {
			System.err.println(e);
		} finally{
			if(null != connection){
				connection.disconnect();
			}
		}
	}

	public void executorPoll(final String carId,final String page) {
		final long pollTime = 25 * 1000L;
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
									BSS_URL.replace("{id}", carId).replace("{page}", page),
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
														//TODO 解析页面内容
														parserBbs(content,"2001");
													}else{
														SIZEHASH.put(key, (value+1));
													}
												}
											});
										}
									});
							isOk = true;
						} else {
							System.err.println("超时:["+ (pollTime / 1000) + "s]");
							break;
						}
					}
				} catch (Exception e) {
					System.err.println(e);
				}
				System.out.println("方法执行耗时:"+ (System.currentTimeMillis() - start) + "ms");
			}

		}, 100L, pollTime, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * 解析页面内容
	 * @param content
	 * @param carId
	 */
	private void parserBbs(String content,final String carId){
		long start = System.currentTimeMillis();
		Parser p1 = null;
		try{
			p1 = new Parser();
			p1.setInputHTML(content);
			p1.setEncoding("GB2312");
			NodeFilter fileter = new NodeClassFilter(CompositeTag.class);
			NodeList list = p1.extractAllNodesThatMatch(fileter)
					.extractAllNodesThatMatch(
							new HasAttributeFilter("class", "list_dl "));
			/**
			 * c|2001|16099453|3|2012-07-10 13:36|2012-07-10 20:02|5385276|1048731|0|0|0|tnwwzh 
			 * 格式说明:
			 * 论坛标识|论坛编号|帖子编号|回复数|发帖时间|最后跟帖时间|发帖人ID|最后跟帖人ID|帖子推荐类型|提问|有无图片|发帖人昵称
			 * 帖子推荐类型： 1:推荐  3:精华
			 * 帖子属性: 18:提问
			 * 有无图片: 1:有 0:没有
			 */
			if(null != list && list.size() > 0){
				int i =0;
				int c = 0;
				do{
					CompositeTag tag = (CompositeTag)list.elementAt(i);
					String lang = tag.getAttribute("lang");
					String[] paras = lang.split("\\|");
					if(paras.length > 0 && paras[1].trim().equals(carId) && paras[3].trim().equals("0")){
						final String pid = paras[2].trim();
						final String uid = paras[6].trim();
//						synchronized(REPLAYED){
							if(!REPLAYED.containsKey(pid) && !uid.equals("4212192")){
								//播放提示音
								MediaPlayCase.play();
								final int rid = new java.util.Random().nextInt(30);
								String postURL = BBS_POST_URL.replace("{cid}", carId).replace("{pid}", pid);
								System.out.println("发帖时间:"+paras[4]);
								System.out.println(">>>>>>"+postURL+"<<<<<<");
								doReply(carId,pid,"我又来占沙发了.. <img style=\";\" src=\"http://img.autohome.com.cn/Album/kindeditor/smiles/"+rid+".gif\">");
								System.err.println(">> 新帖子来了 ");
								c++;
							}
//						}
					}
					i++;
				}while(i<list.size());
				System.out.println("帖子数:"+c);
			}else{
				System.err.println("获取列表失败");
			}
		}catch(Exception e){
			System.err.println(e);
		}finally{
			if(null != p1){
				p1 = null;
			}
			System.out.println("解析页面耗时:"+(System.currentTimeMillis()-start)+"ms");
		}
	}

	/**
	 * 自动回复消息网络代码
	 */
	private synchronized void doReply(String carId,String topicId,String content){
		URL url = null;
		HttpURLConnection connection = null;
		OutputStream out = null;
		InputStream in = null;
		ByteArrayOutputStream byteArray = null;
		StringBuffer sb = new StringBuffer();
		try{
			url = new URL("http://club.autohome.com.cn/Detail/AddReply");
			connection = (HttpURLConnection)url.openConnection();
			connection.addRequestProperty("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.3");
			connection.addRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
			connection.addRequestProperty("Accept", "*/*");
			connection.addRequestProperty("Origin", "http://club.autohome.com.cn");
			connection.addRequestProperty("Referer", "http://club.autohome.com.cn/bbs/thread-c-2001-16106870-1.html");
			connection.addRequestProperty("Cookie", "ASP.NET_SessionId=i5rklwmtposzhh55egotym55; isLoginedWeb=T");
			connection.addRequestProperty("Cache-Control", "no-cache");
			connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.63 Safari/535.7");
			connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			connection.addRequestProperty("Cookie", "__utma=37235314.1856846134.1321246603.1323769135.1323772004.40; area=330199; sessionip=115.238.91.226; UP=30; CKVER=1; samemappingcookie=m; UN=%e6%9b%be%e7%bb%8fde%e6%b5%81%e6%98%9f%e9%9b%a8; UID=4212192; UC=2001; MISUSER=2; CKVER=1; Roleid=0; isCarOwnerCamp=1; bbsOfMaster=; picture=userheaders/2012/6/13/300500d0-c220-487b-af1e-c109cd251b23_b30422f7-1475-4086-9d06-7060b5e5497b_120X120_545X800_22X1_138X138.jpg; pcpopclub=74A96643E4093218E0C894DE59BB26867805001F132A69E43148C00EEF0F442A4CD7E5D16513441D16E6547DFD0418FE6D3CAE74251FEDC996782D332205DC0F046E705C501C389AC1D62785E17032201565324A14C4E602EB9FD8CE2D82283E6C71AB5C002248455D6443513F1A843549A722D62E760EB6E6F6B82479885432F2C9F002EE3A8E97708BA0BA815C7A0F1A684815B556E89A20647A31A152CBFA4069CBF5EA29368870FFB4D1815C87F6DA6A9639816F76B9A346CDF5877B224465D8F864; ssoUN=186*****690|4212192|3; Hm_lvt_90ad5679753bd2b5dec95c4eb965145d=1340602656708; cookieKnow=1; sessionid=ef1989f3-d75e-45a4-ae9a-178bcdc5b599; smsso=oDA2TQEmTgY7YqU4qYbsCGIS52JmdH-bxuAf66uvrEE7XYCsbFK8jg; historybbsName2=c-2001%7C%E5%93%88%E5%BC%97M2%2Cc-2476%7C%E4%BC%98%E4%BC%98%2Cc-2002%7C%E8%B5%B7%E4%BA%9AMagentis%2Cc-2003%7C%E6%96%AF%E6%9F%AF%E8%BE%BERoomster%20; __utma=1.1484792975.1322543017.1341971128.1341974893.279; __utmb=1.0.10.1341974893; __utmc=1; __utmz=1.1341827251.272.30.utmcsr=google|utmccn=(organic)|utmcmd=organic|utmctr=Android%20%E6%93%8D%E6%8E%A7%E6%B1%BD%E8%BD%A6; Hm_lvt_a14e5f772a500c6bc3f506e86c3567c2=1341976175989; Hm_lpvt_a14e5f772a500c6bc3f506e86c3567c2=1341976175989; sessionprovince=36; sessioncity=538; sessionvid=DE23DFE2-95ED-13CB-E3BB-17FB55789DFA");
			connection.setConnectTimeout(15*1000);
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.connect();
			
			sb.append(URLEncoder.encode("bbs", "UTF-8")+"="+URLEncoder.encode("c", "UTF-8"));
			sb.append("&");
			sb.append(URLEncoder.encode("bbsid", "UTF-8")+"="+URLEncoder.encode(carId, "UTF-8"));
			sb.append("&");
			sb.append(URLEncoder.encode("topicId", "UTF-8")+"="+URLEncoder.encode(topicId, "UTF-8"));
			sb.append("&");
			sb.append(URLEncoder.encode("content", "UTF-8")+"="+URLEncoder.encode(content, "UTF-8"));
			
			String requestBody = sb.toString();
			System.out.println(requestBody);
			out = connection.getOutputStream();
			out.write(requestBody.getBytes());
			out.flush();
			out.close();
			
			int code = connection.getResponseCode();
			switch(code){
				case 200:
					REPLAYED.put(topicId, 0);
					in = connection.getInputStream();
					byteArray = new ByteArrayOutputStream();
					int ch;
					while((ch = in.read()) != -1){
						byteArray.write(ch);
					}
					byteArray.flush();
					String result = byteArray.toString("GB2312");
					System.out.println(result);
					ReplyJson json = (ReplyJson)JSONUtils.json2Object(result,ReplyJson.class);
					if(json.getSucceed()){
						System.out.println("回复成功!");
					}else{
						System.err.println("回复失败,原因为:"+json.getErrMsg());
					}
					break;
				default:
					System.err.println(connection.getResponseCode()+":"+connection.getResponseMessage());
					break;
			}
			
			if(null != out){
				out.close();
			}
			if(null != in){
				in.close();
			}
		}catch(Exception e){
			if(null != byteArray){
				try {
					byteArray.close();
				} catch (IOException e1) {
					System.err.println(e1);
				}
			}
			
			if(null != connection){
				connection.disconnect();
				connection = null;
			}
			
			if(null != url){
				url = null;
			}
			sb = null;
			e.printStackTrace();
		}
	}
	
	/**
	 * 自动回复功能
	 *
	 */
	public synchronized void replayQueueAction(){
		try{
			String[] paras = replyQuene.poll(500L, TimeUnit.MILLISECONDS);
			if(null != paras){
				final String carId = paras[1].trim();
				final String pid = paras[2].trim();
				final int rid = new java.util.Random().nextInt(30);
				String postURL = BBS_POST_URL.replace("{cid}", carId).replace("{pid}", pid);
				System.out.println("发帖时间:"+paras[4]);
				System.out.println(">>>>>>"+postURL+"<<<<<<");
				doReply(carId,pid,"占个沙发，楼下的来回答吧! <img style=\";\" src=\"http://img.autohome.com.cn/Album/kindeditor/smiles/"+rid+".gif\">");
				try {
					Thread.sleep(3000L);
				} catch (InterruptedException e) {
					System.err.println(e);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			replayQueueAction();
		}
	}
	
	public static void main(String args[]) {
		final C c = new C();
		c.executorPoll("2001","1");
//		final ExecutorService taskPool = Executors.newFixedThreadPool(3);
//		taskPool.submit(new Runnable(){
//			public void run(){
//				c.replayQueueAction();
//			}
//		});
	}
	
}

/**
 * 播放音频文件
 * @author Bluestome.Zhang
 *
 */
class MediaPlayCase{
	static void play() {
		new Thread(new Runnable(){
			public void run() {
				try {
					// From file
					AudioInputStream stream = AudioSystem
							.getAudioInputStream(new File(
									"D:\\projects\\sky2.0\\qunke2\\diy_page\\src\\com\\autohome\\notify.wav"));

					// From URL
					// stream = AudioSystem.getAudioInputStream(new URL(
					// "http://hostname/audiofile"));

					// At present, ALAW and ULAW encodings must be converted
					// to PCM_SIGNED before it can be played
					AudioFormat format = stream.getFormat();
					if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
						format = new AudioFormat(
								AudioFormat.Encoding.PCM_SIGNED, format
										.getSampleRate(), format
										.getSampleSizeInBits() * 2, format
										.getChannels(),
								format.getFrameSize() * 2, format
										.getFrameRate(), true); // big endian
						stream = AudioSystem
								.getAudioInputStream(format, stream);
					}

					// Create the clip
					DataLine.Info info = new DataLine.Info(Clip.class, stream
							.getFormat(),
							((int) stream.getFrameLength() * format
									.getFrameSize()));
					Clip clip = (Clip) AudioSystem.getLine(info);

					// This method does not return until the audio file is
					// completely loaded
					clip.open(stream);

					// Start playing
					clip.start();
					
					Thread.sleep(2*1000L);
					
					clip.stop();
					clip = null;
				} catch (MalformedURLException e) {
					System.err.println(e);
				} catch (IOException e) {
					System.err.println(e);
				} catch (LineUnavailableException e) {
					System.err.println(e);
				} catch (UnsupportedAudioFileException e) {
					System.err.println(e);
				} catch (Exception e){
					System.err.println(e);
				}
			}
		}).start();
	}
}