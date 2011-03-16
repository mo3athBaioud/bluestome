package test.com.ssi.dal.dao;

import java.util.HashMap;
import java.util.List;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssi.common.dal.dao.IChartDAO;
import com.ssi.common.dal.domain.Chart;

public class ChartDAO {

	private IChartDAO chartDao;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		chartDao = (IChartDAO)context.getBean("chartDAO");
	}
	
	@After
	public void destory(){
		if(chartDao != null)
			chartDao = null;
	}
	
	public void article(){
		List<Chart> list = chartDao.article(new HashMap());
		if(null != list && list.size() > 0){
			System.out.println("下载记录");
			int i=0;
			for(Chart chart:list){
				System.out.println("Name:"+chart.getName());
				System.out.println("Total:"+chart.getTotal());
				i++;
			}
		}
	}

	public void articleDoc(){
		List<Chart> list = chartDao.articleDoc(new HashMap());
		if(null != list && list.size() > 0){
			System.out.println("下载记录");
			int i=0;
			for(Chart chart:list){
				System.out.println("Name:"+chart.getName());
				System.out.println("Total:"+chart.getTotal());
				i++;
			}
		}
	}
	public void image(){
		List<Chart> list = chartDao.image(new HashMap());
		if(null != list && list.size() > 0){
			System.out.println("下载记录");
			int i=0;
			for(Chart chart:list){
				System.out.println("Name:"+chart.getName());
				System.out.println("Total:"+chart.getTotal());
				i++;
			}
		}
	}

	public void pictureFile(){
		List<Chart> list = chartDao.pictureFile(new HashMap());
		if(null != list && list.size() > 0){
			System.out.println("图片文件记录");
			int i=0;
			for(Chart chart:list){
				System.out.println("Name:"+chart.getName());
				System.out.println("Total:"+chart.getTotal());
				i++;
			}
		}
	}
	
	@Test
	public void insert(){
		String tableName = "TBL_PIC_FILE";
		List<Chart> list = chartDao.pictureFile(new HashMap());
		if(null != list && list.size() > 0){
			System.out.println("图片文件记录");
			int i=0;
			for(Chart chart:list){
				chart.setChartType("DAY");
				chart.setTableName(tableName);
				int result = chartDao.insert(chart);
				if(result > 0){
					System.out.println(">> 添加"+tableName+"数据记录成功！"+result);
				}
				i++;
			}
		}
	}
	
	public void find(){
		HashMap map = new HashMap();
		map.put("limit", 10);
		map.put("tableName", "TBL_ARTICLE");
		List<Chart> list = chartDao.find(map);
		if(null != list && list.size() > 0){
			for(Chart chart:list){
				System.out.println("TableName:"+chart.getTableName());
				System.out.println("Name:"+chart.getName());
				System.out.println("Total:"+chart.getTotal());
			}
		}
	}
	
}
