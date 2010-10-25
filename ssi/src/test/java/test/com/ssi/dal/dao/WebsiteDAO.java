package test.com.ssi.dal.dao;

import java.util.List;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssi.dal.dao.IWebsiteDAO;
import com.ssi.dal.domain.Article;
import com.ssi.dal.domain.Website;

public class WebsiteDAO {

	private IWebsiteDAO websiteDAO;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		websiteDAO = (IWebsiteDAO)context.getBean("websiteDAO");
	}
	
	@After
	public void destory(){
		if(null != websiteDAO)
			websiteDAO = null;
	}
	
	@Test
	public void findById() {
		Website website = websiteDAO.findById(143);
		if(null != website){
			System.out.println("websiteName:"+website.getName());
			if(null != website.getChildren() && website.getChildren().size() > 0){
				for(Website sub:website.getChildren()){
					System.out.println("网站名称:"+sub.getName());
					System.out.println("网站地址:"+sub.getUrl());
					System.out.println("网站下文章数量:"+sub.getCount());
				}
			}
		}
	}
	
	public void findByFatherId(){
		List<Website> list = websiteDAO.findByFatherId(143);
		if(null != list && list.size() > 0){
			System.out.println(">> list.size:"+list.size());
		}
	}
	
	
}
