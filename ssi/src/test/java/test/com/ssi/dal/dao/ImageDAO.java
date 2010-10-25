package test.com.ssi.dal.dao;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssi.dal.dao.IArticleDAO;
import com.ssi.dal.dao.IImageDAO;
import com.ssi.dal.domain.Article;
import com.ssi.dal.domain.Image;

public class ImageDAO {
	
	private IImageDAO imageDAO;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		imageDAO = (IImageDAO)context.getBean("imageDAO");
	}
	
	@After
	public void destory(){
		if(null != imageDAO){
			imageDAO = null;
		}
	}
	
	@Test
	public void findById(){
		Image image = imageDAO.findById(358660);
		if(null != image){
			System.out.println("文章ID:"+image.getArticleId());
			System.out.println("图片标题:"+image.getTitle());
			System.out.println("图片地址："+image.getHttpUrl());
			
			if(null != image.getArticle()){
				System.out.println(">> 文章标题："+image.getArticle().getTitle());
				System.out.println(">> 文章地址："+image.getArticle().getArticleUrl());
			}
			
			//TODO 以下方法暂时未调通
//			if(null != image.getPictureFile()){
//				System.out.println(">> 图片文章标题："+image.getArticle().getTitle());
//				System.out.println(">> 图片文章大图地址："+image.getPictureFile().getName());
//				System.out.println(">> 图片文章小图地址："+image.getPictureFile().getSmallName());
//			}
		}
	}
}
