package test.com.ssi.dal.dao;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssi.common.dal.dao.IArticleDAO;
import com.ssi.common.dal.dao.IImageDAO;
import com.ssi.common.dal.domain.Article;
import com.ssi.common.dal.domain.Image;

public class ImageDAO {
	
	private IImageDAO imageDAO;
	
	private static Log log = LogFactory.getLog(ImageDAO.class);
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		PropertyConfigurator.configure(ImageDAO.class.getClassLoader().getResource("conf/log4j.properties")); 
		imageDAO = (IImageDAO)context.getBean("imageDAO");
	}
	
	@After
	public void destory(){
		if(null != imageDAO){
			imageDAO = null;
		}
	}
	public void findById(){
		Image image = imageDAO.findById(358660);
		if(null != image){
			log.debug("文章ID:"+image.getArticleId());
			log.debug("图片标题:"+image.getTitle());
			log.debug("图片地址："+image.getHttpUrl());
			
			if(null != image.getArticle()){
				log.debug(">> 文章标题："+image.getArticle().getTitle());
				log.debug(">> 文章地址："+image.getArticle().getArticleUrl());
			}
			
			//TODO 以下方法暂时未调通
			if(null != image.getPictureFile()){
				log.debug(">> 图片文章标题："+image.getArticle().getTitle());
				log.debug(">> 图片文章大图地址："+image.getPictureFile().getName());
				log.debug(">> 图片文章小图地址："+image.getPictureFile().getSmallName());
			}
		}
	}
	
	public void update(){
		Image image = imageDAO.findById(358660);
		if(null != image){
			image.setIntro("测试修改");
			int result = imageDAO.update(image);
			log.debug(">> 结果:"+result);
		}
	}
	
	public void insert(){
		Image image = new Image();
		image.setArticleId(100);
		image.setHttpUrl("http://www.china.com/");
		int result = imageDAO.insert(image);
		log.debug(">> 结果:"+result);
	}
	
	@Test
	public void getCount(){
		HashMap map = new HashMap();
		int count = imageDAO.getCount(map);
		System.out.println(">> 结果:"+count);
	}

}
