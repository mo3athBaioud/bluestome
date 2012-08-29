package com.taksoon.bluestome.test.dao;

import java.util.List;

import org.junit.Test;

import com.takesoon.oms.ssi.common.NativeCache;
import com.takesoon.oms.ssi.entity.SystemConfig;

public class SysconfigManagerTest extends AbstractTestCase{

	public void test(){
		if(null != systemConfigManager){
			System.out.println(" > systemConfigManager is not null!");
		}
	}

	public void save(){
		SystemConfig entity = new SystemConfig();
		entity.setType("SYS");
		entity.setKey("ALLOW_ICON_SHOW");
		entity.setValue("true");
		entity.setStatus(1);
		systemConfigManager.save(entity);
		if(entity.getId() > 0){
			System.out.println("save success!");
		}
	}
	
	public void list(){
		SystemConfig entity = new SystemConfig();
		List<SystemConfig> list = systemConfigManager.getList(entity);
		for(SystemConfig sc:list){
			System.out.println(" > "+sc.getType()+"|"+sc.getKey()+"|"+sc.getValue());
		}
		
		
	}
	
	@Test
	public void column(){
		NativeCache cache = new NativeCache();
		System.out.println(" > IMAGE_ICON_ENABLE:"+cache.getImageIconEable());
	}
}
