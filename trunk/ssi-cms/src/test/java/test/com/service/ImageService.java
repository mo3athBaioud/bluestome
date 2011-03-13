package test.com.service;

import java.util.HashMap;
import java.util.List;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssi.cms.web.service.ImageIService;
import com.ssi.common.dal.domain.Image;
import com.ssi.common.utils.JSONUtils;

public class ImageService {
	
	private ImageIService imageService;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		imageService = (ImageIService)context.getBean("imageService");
	}
	
	@After
	public void destory(){
		if(null != imageService){
			imageService = null;
		}
	}
	
	@Test
	public void find() throws Exception{
		HashMap map = new HashMap();
		map.put("limit", 10);
		map.put("offset", 10);
		
		List<Image> list = imageService.findByArticleId(102908);
		System.out.println(" >> 列表数量:"+list.size());
		for(Image art:list){
			System.out.println("ID:"+art.getId());
			System.out.println("文章标题:"+art.getTitle());
			if(null != art.getPictureFile()){
				System.out.println("小图："+art.getPictureFile().getSmallName());
			}
//			String json = JSONUtils.bean2Json(art);
//			System.out.println(" >> json:"+json);
		}
	}
	
}
