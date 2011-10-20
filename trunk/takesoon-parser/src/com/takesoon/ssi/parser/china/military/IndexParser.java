package com.takesoon.ssi.parser.china.military;

import java.util.ArrayList;
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
	
	public void start(){
		if(null != websiteManager){
			List<Website> list = websiteManager.findByParsentId(D_PARENT_ID);
			for(Website ws:list){
				// 单独处理子项逻辑
				try {
					logger.debug(" > webName:"+ws.getName());
					doProcess(ws);
				} catch (Exception e) {
					logger.error("Exception:{1}",e);
				}
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
			for (int i = 0; i < list.size(); i++) {
				LinkTag link = (LinkTag) list.elementAt(i);
				logger.debug(link.getLinkText() + "\t" + link.getLink());
			}
		}
		p1 = null;
	}
	
}
