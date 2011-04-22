package com.nutzd.mvc.pb;

import org.nutz.dao.Dao;
import org.nutz.ioc.Ioc2;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.ioc.loader.json.JsonLoader;

@InjectName
@IocBean(create="init")
public class BasePbModule {
	
	protected Dao dao;

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	public void init(){
		if(null == dao){
			Ioc2 ioc = new NutIoc(new JsonLoader("conf/jdbc.js"));
			dao = ioc.get(Dao.class,"dao");
		}
	}
}
