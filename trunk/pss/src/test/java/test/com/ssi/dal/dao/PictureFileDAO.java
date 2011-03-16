package test.com.ssi.dal.dao;

import java.util.HashMap;
import java.util.List;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssi.common.dal.dao.IArticleDAO;
import com.ssi.common.dal.dao.IImageDAO;
import com.ssi.common.dal.dao.IPictureFileDAO;
import com.ssi.common.dal.domain.Article;
import com.ssi.common.dal.domain.Image;
import com.ssi.common.dal.domain.PictureFile;

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
	
//	public void find(){
//		PictureFile picturefile = pictureFileDAO.find(25818, 410437);
//		if(null != picturefile){
//			System.out.println(">> 文件标题:"+picturefile.getTitle());
//			System.out.println(">> 大图文件地址:"+picturefile.getName());
//			System.out.println(">> 小图文件地址:"+picturefile.getSmallName());
//			System.out.println("\n");
//		}
//	}
	
	public void findLastPictureFile(){
		List<PictureFile> list = pictureFileDAO.findLastPictureFile(30);
		if(null != list && list.size() > 0){
			for(PictureFile pic:list){
				System.out.println("articleid:"+pic.getArticleId()+",imageid:"+pic.getImageId()+",path"+pic.getName());
			}
		}
	}
	
	public void insert(){
		PictureFile picturefile = new PictureFile();
		picturefile.setUrl("d:\\");
		picturefile.setArticleId(25818);
		picturefile.setImageId(410438);
		picturefile.setTitle("测试添加图片文件");
//		if(pictureFileDAO.insert(picturefile)){
//			System.out.println(">> 结果:添加成功");
//		}else{
//			System.out.println(">> 结果:添加失败");
//		}
	}
	
	public void update(){
		PictureFile picturefile = pictureFileDAO.find(16386, 358660);
		if(null != picturefile){
			picturefile.setUrl(picturefile.getUrl()+"_STOP");
			int result = pictureFileDAO.update(picturefile);
			System.out.println(">> 结果:"+result);
		}
	}
	
	public void getCount(){
		HashMap map = new HashMap();
		int count = pictureFileDAO.getCount(map);
		System.out.println(">> 结果:"+count);
	}
	
	@Test
	public void find(){
		HashMap map = new HashMap();
		map.put("articleid", 25818);
		int count = pictureFileDAO.getCount(map);
		System.out.println(">> 结果:"+count);
		if(count < 1000){
		List<PictureFile> list = pictureFileDAO.find(map);
			if(null != list && list.size() > 0){
				for(PictureFile pic:list){
					System.out.println(">> 图片唯一标识:"+pic.getId());
					System.out.println(">> 文件标题:"+pic.getTitle());
					System.out.println(">> 大图文件地址:"+pic.getName());
					System.out.println(">> 小图文件地址:"+pic.getSmallName());
				}
			}
		}else{
			System.out.println(">> 记录数量太大,具体为["+count+"]");
		}
	}
}
