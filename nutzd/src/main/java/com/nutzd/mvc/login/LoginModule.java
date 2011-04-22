package com.nutzd.mvc.login;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.nutzd.JsonObject;
import com.nutzd.domain.Website;

@InjectName
@IocBean(fields="dao")
@Ok("json")
public class LoginModule {

	private static final Log log = Logs.getLog(LoginModule.class);
	private Dao dao;

	@At("/auth/login")
	public boolean login(@Param("username")
	String userName, @Param("password")
	String password, HttpSession session, HttpServletRequest request) {
		int count = dao.count(Website.class);
		log.debugf("%d website.count:",count);
		log.debugf(" username:%s ", userName);
		log.debugf(" password:%s ", password);
		if (true) {
			log.debugf(" %s login success from %s ", userName, request
					.getRemoteAddr());
			session.setAttribute(userName, userName);
			return true;
		}
		return false;
	}
	
	@At("/auth/wlist")
	public JsonObject wlist(){
		JsonObject obj = new JsonObject();
		if(null != dao){
			List<Website> list = new ArrayList<Website>();
			list = dao.query(Website.class, Cnd.where("d_parent_id","=",0),null);
			obj.setObj(list);
			obj.setCount(list.size());
			obj.setMsg("获取数据成功");
		}
		return obj;
	}

	@At
	public void logout(HttpSession session) {
		session.invalidate();
	}
	

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	
}
