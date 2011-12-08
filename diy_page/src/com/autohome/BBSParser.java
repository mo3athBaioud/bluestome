package com.autohome;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinamilitary.memcache.MemcacheClient;

public class BBSParser {

	private static Logger logger = LoggerFactory.getLogger(BBSParser.class);

	static MemcacheClient client = MemcacheClient.getInstance();

	private static ScheduledExecutorService exec = Executors
			.newSingleThreadScheduledExecutor(new ThreadFactory() {

				public Thread newThread(Runnable r) {
					return new Thread(r, "AUTOHOME-BBS-thread");
				}
			});

	static int count = 0;

	static void bbslist(String forumurl, int page) throws IOException,
			ParserException {
		Parser parser = null;
		try {
			String url = null;
			for (int j = 1; j < page + 1; j++) {
				url = forumurl.replace("{page}", "" + j);
				parser = new Parser();
				parser.setURL(url);
				parser.setEncoding("gb2312");
				NodeFilter fileter = new NodeClassFilter(LinkTag.class);
				NodeList list = parser.extractAllNodesThatMatch(fileter)
						.extractAllNodesThatMatch(
								new HasAttributeFilter("class", "font14"));
				if (null != list && list.size() > 0) {
					count += list.size();
					for (int i = 0; i < list.size(); i++) {
						LinkTag lt = (LinkTag) list.elementAt(i);
						logger.info("{}\t【{}】", lt.getLinkText().trim(),lt.getLink());
//						logger.info("{}", lt.getLink());
					}
				}
			}
		} catch (Exception e) {
			logger.error("exception:{}", e);
		} finally {
			if (null != parser) {
				parser = null;
			}
		}
	}

	public static void main(String args[]) {
	       exec.scheduleWithFixedDelay(new Runnable() {

	            public void run() {
	            	try {
						bbslist("http://club.autohome.com.cn/bbs/forum-c-362-{page}.html",
								1);
					} catch (ParserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        },
	       500, 5 * 1000, TimeUnit.MILLISECONDS);
	}
}
