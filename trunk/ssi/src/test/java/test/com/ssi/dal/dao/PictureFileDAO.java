package test.com.ssi.dal.dao;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssi.dal.dao.IArticleDAO;
import com.ssi.dal.dao.IImageDAO;
import com.ssi.dal.dao.IPictureFileDAO;
import com.ssi.dal.domain.Article;
import com.ssi.dal.domain.Image;
import com.ssi.dal.domain.PictureFile;

public class PictureFileDAO {
	
	private IPictureFileDAO pictureFileDAO;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		pictureFileDAO = (IPictureFileDAO)context.getBean("pictureFileDAO");
	}
	
	@After
	public void destory(){
		if(null != pictureFileDAO){
			pictureFileDAO = null;
		}
	}
	
	@Test
	public void find(){
		PictureFile picturefile = pictureFileDAO.find(25818, 410437);
		if(null != picturefile){
			System.out.println(">> 文件标题:"+picturefile.getTitle());
			System.out.println(">> 大图文件地址:"+picturefile.getName());
			System.out.println(">> 小图文件地址:"+picturefile.getSmallName());
			System.out.println("\n");
			System.out.println(">> 文章标题:"+picturefile.getArticle().getTitle());
			System.out.println(">> 图片标题:"+picturefile.getImage().getTitle());
		}
	}
}
