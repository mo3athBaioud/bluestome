package com.autohome;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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

public class C {
	
	static String BSS_URL = "http://club.autohome.com.cn/bbs/forum-c-{id}-{page}.html";
	static String BBS_POST_URL = "http://club.autohome.com.cn/bbs/thread-c-{cid}-{pid}-1.html";
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
			connection.setReadTimeout(60*1000);
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

	public void executorPoll(final String carId,final String page) {
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
	
	/**
	 * 解析页面内容
	 * @param content
	 * @param carId
	 */
	private void parserBbs(String content,String carId){
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
						//播放提示音
						MediaPlayCase.play();
						String pid = paras[2].trim();
						String postURL = BBS_POST_URL.replace("{cid}", carId).replace("{pid}", pid);
						System.out.println("\t>>>>>>新帖:["+lang+"]<<<<<<");
						System.out.println("\t>>>>>>"+postURL+"<<<<<<");
						c++;
					}
					i++;
				}while(i<list.size());
				System.out.println("帖子数:"+c);
			}else{
				System.err.println("获取列表失败");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null != p1){
				p1 = null;
			}
			System.out.println("解析页面耗时:"+(System.currentTimeMillis()-start)+"ms");
		}
	}

	public static void main(String args[]) {
		C c = new C();
		c.executorPoll("2001","1");
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