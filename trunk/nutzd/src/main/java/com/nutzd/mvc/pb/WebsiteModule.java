package com.nutzd.mvc.pb;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.nutzd.JsonObject;
import com.nutzd.domain.Website;

@InjectName
@IocBean(fields="dao")
@Ok("json")
@At("/website")
public class WebsiteModule extends BasePbModule{

	private JsonObject jobj = null;
	
	private int count = 0;
	
	@At
	public JsonObject detail(@Param("id") Integer id){
		jobj = new JsonObject();
		if(null != dao){
			if(null != id){
				List<Website> list = null;
				list = dao.query(Website.class, Cnd.where("d_id","=",id),null);
				if(null != list && list.size() == 1){
					jobj.setObj(list.get(0));
					jobj.setCount(list.size());
					jobj.setMsg("获取站点数据成功");
				}else{
					jobj.setSuccess(false);
					jobj.setMsg("获取站点数据失败");
				}
			}else{
				jobj.setSuccess(false);
				jobj.setMsg("获取站点数据失败,未传入文章ID");
			}
		}
		return jobj;
	}
	
	@At
	public JsonObject root(){
		jobj = new JsonObject();
		if(null != dao){
			List<Website> list = null;
			list = dao.query(Website.class, Cnd.where("d_parent_id","=",0),null);
			if(null != list){
				jobj.setObj(list);
				jobj.setCount(list.size());
				jobj.setMsg("获取站点数据成功");
			}else{
				jobj.setSuccess(false);
				jobj.setMsg("获取站点数据失败");
			}
		}
		return jobj;
	}
	
	@At
	public JsonObject list(@Param("pid") Integer id,@Param("pageNo") int pageNo,
			   @Param("limit") int limit){
		jobj = new JsonObject();
		if(null != id){
			List<Website> list = null;
			count = dao.count(Website.class, Cnd.where("d_parent_id","=",id));
			list = dao.query(Website.class, Cnd.where("d_parent_id","=",id),dao.createPager(pageNo, limit));
			if(null != list && list.size() > 0){
				jobj.setObj(list);
				jobj.setCount(list.size());
				jobj.setMsg("获取站点数据成功");
			}else{
				jobj.setSuccess(false);
				jobj.setMsg("获取站点数据失败，数据记录为0");
			}
		}else{
			jobj.setSuccess(false);
			jobj.setMsg("获取站点数据失败,未传入父站点ID");
		}
		return jobj;
	}
	
	@At
	public JsonObject count(@Param("pid") Integer id){
		jobj = new JsonObject();
		if(null == id){
			count = dao.count(Website.class);
			jobj.setCount(count);
			jobj.setMsg("获取站点总数成功!");
		}else{
			count = dao.count(Website.class,Cnd.where("d_parent_id","=",id));
			jobj.setCount(count);
			jobj.setMsg("获取父站点["+id+"]下的站点总数成功!");
		}
		return jobj;
	}
}
