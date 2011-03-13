package test.com.service;

import java.util.HashMap;
import java.util.List;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssi.cms.web.service.IWebsiteService;
import com.ssi.common.dal.domain.Website;

public class WebsiteService {
	
	private IWebsiteService websiteService;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		websiteService = (IWebsiteService)context.getBean("websiteService");
	}
	
	@After
	public void destory(){
		if(null != websiteService){
			websiteService = null;
		}
	}
	
	public void find() throws Exception{
		HashMap map = new HashMap();
		map.put("limit", 10);
		map.put("offset", 10);
		
		List<Website> list = websiteService.find(map);
		System.out.println(" >> 列表数量:"+list.size());
		for(Website art:list){
			System.out.println("ID:"+art.getId());
			System.out.println("文章标题:"+art.getName());
		}
	}
	
	public void root() throws Exception{
		List<Website> list = websiteService.getRootWebSite();
		System.out.println(" >> 列表数量:"+list.size());
		for(Website art:list){
			System.out.println("ID:"+art.getId());
			System.out.println("文章标题:"+art.getName());
		}
	}
	
	public void getWebsiteByParentID() throws Exception{
		List<Website> list = websiteService.findByParentId(143);
		System.out.println(" >> 列表数量:"+list.size());
		for(Website art:list){
			System.out.println("ID:"+art.getId());
			System.out.println("网站名称:"+art.getName());
			if(null != art.getChildren()){
				for(Website sub:art.getChildren()){
					System.out.println("子ID:"+sub.getId());
					System.out.println("子网站名:"+sub.getName());
				}
			}
		}
	}
	
	@Test
	public void getCount(){
		int count = websiteService.getCount(null,"");
		System.out.println(" >> count:"+count);
	}
	
}
