package com.xcms.web.action;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.mss.dal.domain.CustomerTac;
import com.xcms.common.json.JsonObject;
import com.xcms.web.service.CustomerTacService;

@IocBean
@InjectName
@At("/customertac")
public class CustomerTacAction extends BaseAction{

	@Inject
	private CustomerTacService customerTacService;
	
	@At("/list")
	@Ok("json")
	public JsonObject list(){
		
		return json;
	}

	@At("/save")
	@Ok("json")
	public JsonObject insert(@Param("::customerTac.") CustomerTac customerTac){
		json = new JsonObject();
		customerTac = customerTacService.save(customerTac);
		if(customerTac.getId() > 0){
			json.setSuccess(true);
			json.setObj(customerTac);
			json.setMsg("添加成功!");
		}else{
			json.setSuccess(false);
			json.setMsg("添加失败，请检查是否存在相同内容!");
		}
		return json;
	}
	
	public CustomerTacService getCustomerTacService() {
		return customerTacService;
	}

	public void setCustomerTacService(CustomerTacService customerTacService) {
		this.customerTacService = customerTacService;
	}
	
	
	
}
