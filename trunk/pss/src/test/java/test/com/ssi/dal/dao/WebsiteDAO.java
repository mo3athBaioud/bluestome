package test.com.ssi.dal.dao;

import java.util.HashMap;
import java.util.List;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssi.dal.dao.IWebsiteDAO;
import com.ssi.dal.domain.Article;
import com.ssi.dal.domain.Count;
import com.ssi.dal.domain.Website;

public class WebsiteDAO {

	private IWebsiteDAO websiteDAO;
	final static int[] webids = {36,143,148,300,400,125,600,800,900,1200,1100}; //143,148,300,400,125,600,800,900,1200,1100
	
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
	
	public void getCount(){
		HashMap map = new HashMap();
		int count = websiteDAO.getCount(map);
		System.out.println(">> 结果:"+count);
	}
	
	@Test
	public void getImageCountByWebid(){
		List<Website> list = websiteDAO.findByFatherId(0);
		if(null != list && list.size() > 0){
//			for(int id:webids){
			for(Website website:list){
//				System.out.println(">> id:"+website.getName());
				List<Count> countList = websiteDAO.getImageCountByWebid(website.getId());
				if(null != countList && countList.size() > 0){
					System.out.println("**********************["+website.getName()+"|"+website.getId()+"]*************************");
					for(Count count:countList){
						System.out.println(count.getName()+":"+count.getTotal());
					}
					System.out.println("**********************End*****************************");
				}
			}
		}
	}

}
