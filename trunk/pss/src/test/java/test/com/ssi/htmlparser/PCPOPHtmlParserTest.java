package test.com.ssi.htmlparser;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssi.common.cache.CacheException;
import com.ssi.htmlparser.pcpop.PCPOPHtmlParser;
import com.ssi.htmlparser.cache.WebsiteCache;
public class PCPOPHtmlParserTest {

	
	@Test
	public void process(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		PCPOPHtmlParser pcpopHtml = (PCPOPHtmlParser)context.getBean("pcpopParser");
		pcpopHtml.process();

//		WebsiteCache webCache = (WebsiteCache)context.getBean("websiteCache");
//		try {
//			webCache.containsKey("123");
//		} catch (CacheException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
