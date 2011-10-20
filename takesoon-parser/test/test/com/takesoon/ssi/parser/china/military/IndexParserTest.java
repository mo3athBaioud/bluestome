package test.com.takesoon.ssi.parser.china.military;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.takesoon.oms.ssi.service.ImageManager;
import com.takesoon.ssi.parser.china.military.IndexParser;

public class IndexParserTest {

	private IndexParser indexParser;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		indexParser = (IndexParser)context.getBean("indexParser");
	}

	@After
	public void destory(){
		if(null != indexParser){
			indexParser = null;
		}
	}
	
	@Test
	public void start(){
		indexParser.start();
	}
}
