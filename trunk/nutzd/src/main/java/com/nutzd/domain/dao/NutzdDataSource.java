package com.nutzd.domain.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;

import com.nutzd.domain.Website;

public class NutzdDataSource {
	
	private static NutzdDataSource instance = null;
	
	private BasicDataSource bds;
	
	private NutzdDataSource(){
		init();
	}
	
	void init(){
		bds = new BasicDataSource();
		bds.setDriverClassName("com.mysql.jdbc.Driver");
		bds.setUrl("jdbc:mysql://127.0.0.1:3306/filecollection?useUnicode=true&characterEncoding=UTF-8");
		bds.setUsername("root");
		bds.setPassword("123456");
	}
	
	public void close() throws SQLException{
		if(!bds.isClosed()){
			bds.close();
		}
	}
	
	public static NutzdDataSource getInstance(){
		if(null == instance){
			instance = new NutzdDataSource();
		}
		return instance;
	}
	
	
	public BasicDataSource getBds() {
		return bds;
	}

}
