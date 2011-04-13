package test.com.dao;

import java.util.HashMap;
import java.util.List;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssi.mobile.dal.dao.IMobileBrandDAO;
import com.ssi.mobile.dal.domain.MobileBrand;

public class IMobileBrandDAOTest {

	private IMobileBrandDAO mobileBrandDAO;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		mobileBrandDAO = (IMobileBrandDAO)context.getBean("mobileBrandDAO");
	}

	@After
	public void destory(){
		if(null != mobileBrandDAO){
			mobileBrandDAO = null;
		}
	}
	
	@Test
	public void find(){
		StringBuffer sb = new StringBuffer();
		HashMap map = new HashMap();
		map.put("source", 5000);
		List<MobileBrand> list = mobileBrandDAO.find(map);
		int i=0;
		for(MobileBrand b:list){
//			sb.append("\t{\n");
//			sb.append("\t\t\"text\":\""+b.getName()+"\",\n");
//			sb.append("\t\t\"id\":\""+b.getId()+"\",\n");
//			sb.append("\t\t\"icon\":\""+b.getIcon()+"\",\n");
//			sb.append("\t\t\"leaf\":true\n");
//			sb.append("\t}");
			sb.append("["+b.getId()+",'"+b.getName()+"','"+b.getName()+"','"+b.getIcon()+"',1,'暂无']");
			if(i < list.size()-1){
				sb.append(",");
			}
			sb.append("\r\n");
			i++;
		}
		System.out.println(" >> \r\n"+sb.toString());
	}
	
	public void insert(){
		MobileBrand b = new MobileBrand();
		b.setBrandId(-1);
		b.setName("Test");
		b.setSource(-1);
		b.setSourceUrl("http://www.china.com");
		Integer result = mobileBrandDAO.insert(b);
		if(result > 0){
			System.out.println(">> 添加成功["+result+"]");
		}
	}
}
