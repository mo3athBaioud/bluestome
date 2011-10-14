package test.com.takesoon.oms.ssi.common;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.ssi.common.utils.FileUtils;
import com.ssi.common.utils.HttpClientUtils;
import com.takesoon.oms.ssi.service.ImageCacheManager;


public class EhcacheManagerTest {

	private CacheManager cacheManager;
	
	private ImageCacheManager imageCacheManager;

	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		cacheManager = (CacheManager)context.getBean("ehcacheManager");
		imageCacheManager = (ImageCacheManager)context.getBean("imageCacheManager");
	}
	
	@After
	public void destory(){
		if(null != cacheManager){
			cacheManager = null;
		}
		if(null != imageCacheManager){
			imageCacheManager = null;
		}
	}
	
	public void test(){
		if(null != cacheManager){
			System.out.println(" cacheManager is not null");
			Cache cache = cacheManager.getCache("fileCache");
			
			if(null != cache){
				String url = "http://www.easyicon.cn/image/mini_logo_new.gif";
				byte[] body = HttpClientUtils.getResponseBodyAsByte(url);
				System.out.println(" before set body.length:"+body.length);
				Element element = new Element(url, body);
				cache.put(element);
				
				Element re = cache.get(url);
				byte[] tb = (byte[])re.getValue();
				System.out.println("after get body.length:" + tb.length);
			}
		}
	}
	
	@Test
	public void testImageCacheManager(){
		String url = "http://www.easyicon.cn/image/mini_logo_new.gif";
		for(int i=0;i<100;i++)
		{
			byte[] body = HttpClientUtils.getResponseBodyAsByte(url);
			imageCacheManager.putByte(url, body);
			
			byte[] tmp = imageCacheManager.getByte(url);
			int r = FileUtils.saveFile("d:/tmp/"+FileUtils.generateFileName(url), tmp, false);
			if( r == 0)
			{
				System.out.println(" SUccess!");
			}
		}
	}
}
