package com.takesoon.ssi.parser.china.military;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.takesoon.oms.ssi.entity.Website;
import com.takesoon.oms.ssi.service.ArticleManager;
import com.takesoon.oms.ssi.service.ImageManager;
import com.takesoon.oms.ssi.service.WebsiteManager;
import com.takesoon.ssi.parser.bean.LinkBean;

@Component
@Transactional
public class IndexParser {
	
	private static Log logger = LogFactory.getLog(IndexParser.class);
	
	int D_PARENT_ID = 300;

	@Autowired
	private WebsiteManager websiteManager;
	
	@Autowired
	private ArticleManager articleManager;
	
	@Autowired
	private ImageManager imageManager;
	
	HashMap<String, LinkBean> LINKHASH = new HashMap<String, LinkBean>();
	
	public void start(){
		if(null != websiteManager){
			List<Website> list = websiteManager.findByParsentId(D_PARENT_ID);
			for(Website ws:list){
				// 单独处理子项逻辑
				try {
//					logger.debug(" > webName:"+ws.getName());
					doProcess(ws);
				} catch (Exception e) {
					logger.error("Exception:{1}",e);
				}
			}
			Iterator it = LINKHASH.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				LinkBean link = (LinkBean) LINKHASH.get(key);
				logger.debug(link.getName()+"|"+link.getLink());
			}
			logger.debug(" > size:"+LINKHASH.size());
			if(LINKHASH.size() > 0){
				LINKHASH.clear();
			}
		}
	}

	private void doProcess(Website bean) throws Exception {
		logger.debug(" >> url.id[" + bean.getId() + "] url.url["+ bean.getUrl() + "]");
		try {
			getLink(bean.getUrl());
		} catch (Exception e) {
			logger.error("Exception:{1}",e);
		}

	}
	
	private void getLink(String url) throws Exception {
		Parser p1 = new Parser();
		p1.setURL(url);
		p1.setEncoding("UTF-8");
		
		NodeFilter filter = new NodeClassFilter(LinkTag.class);
		NodeList list = p1.extractAllNodesThatMatch(filter);
		if (list != null && list.size() > 0) {
			LinkBean bean = null;
			for (int i = 0; i < list.size(); i++) {
				LinkTag link = (LinkTag) list.elementAt(i);
				
				bean = new LinkBean();
				bean.setLink(link.getLink());
				String name = link.getAttribute("title") == null ? (link
								.getLinkText() == null ? "无话题" : link
								.getLinkText())
								: link.getAttribute("title");
				if (name.indexOf("“") != -1 || name.indexOf("”") != -1) {
					name = name.replaceAll("“", "");
					name = name.replace("”", "");
				}
				// 判断连接中是否存在创建文件夹时的非法字符
				if (name.indexOf("\"") != -1 && name.indexOf("\"") != -1) {
					name = name.replace("\"", "");
				}

				bean.setName(name);
				LINKHASH.put(bean.getLink(), bean);
			}
		}
		p1 = null;
	}
	
}
