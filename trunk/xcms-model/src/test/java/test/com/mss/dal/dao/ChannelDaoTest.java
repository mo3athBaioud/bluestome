package test.com.mss.dal.dao;

import org.junit.*;
import org.nutz.dao.Cnd;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.combo.ComboIocLoader;

import com.mss.dal.dao.ChannelDao;
import com.mss.dal.domain.Channel;

public class ChannelDaoTest {

	private Ioc ioc;
	
	private ChannelDao channelDAO;

	@Before
	public void init(){
		try{
			ioc = new NutIoc(new ComboIocLoader(
	                "*org.nutz.ioc.loader.json.JsonLoader",
	                "conf/datasource.json", 
	                "*org.nutz.ioc.loader.annotation.AnnotationIocLoader", 
	                "com.mss.dal"
	                ));
		}catch(Exception e){
			
		}
		channelDAO = ioc.get(ChannelDao.class);
	}

	@After
	public void destory(){
		if(null != ioc)
			ioc = null;
		if(null != channelDAO)
			channelDAO = null;
	}

	@Test
	public void asset(){
		if(null != ioc)
			System.out.println(" ioc is not null");
		if(null != channelDAO)
			System.out.println(" channelDAO is not null");
	}

	public void add(){
		Channel chl = new Channel();
		chl.setChannelcode("E0E7A0A1");
		chl.setChannlename("中山路营业2厅");
		chl.setBdcode("E0E7");
		chl.setAddress("陕西省西安市浦城县中山路1024号");
		chl = channelDAO.save(chl);
		System.out.println(" >> "+chl.getCreatetime());
	}
	
	@Test
	public void find(){
		Channel channel = null;
		Cnd condition = null;
		try{
			condition = Cnd.where("d_channel_code", "=", "E0E7A0A1").and("d_status", "=", "1");
			channel = channelDAO.findByCondition(Channel.class, condition);
		}catch(Exception e){
			e.printStackTrace();
		}
		if(null != channel){
			System.out.println(" >> code:"+channel.getChannelcode());
			System.out.println(" >> name:"+channel.getChannlename());
		}
	}
}
