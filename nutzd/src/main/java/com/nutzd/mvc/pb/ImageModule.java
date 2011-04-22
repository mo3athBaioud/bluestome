package com.nutzd.mvc.pb;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.nutzd.JsonObject;
import com.nutzd.domain.Article;
import com.nutzd.domain.Image;

@InjectName
@IocBean(fields="dao")
@Ok("json")
@At("/image")
public class ImageModule extends BasePbModule{

	private JsonObject jobj = null;
	
	private int count = 0;
	
	@At
	public JsonObject detail(@Param("id") Integer id){
		jobj = new JsonObject();
		if(null != dao){
			if(null != id){
				List<Image> list = null;
				list = dao.query(Image.class, Cnd.where("d_id","=",id),null);
				if(null != list && list.size() == 1){
					jobj.setObj(list.get(0));
					jobj.setCount(list.size());
					jobj.setMsg("获取图片数据成功");
				}else{
					jobj.setSuccess(false);
					jobj.setMsg("获取图片数据失败");
				}
			}else{
				jobj.setSuccess(false);
				jobj.setMsg("获取图片数据失败,未传入文章ID");
			}
		}
		return jobj;
	}
	
	@At
	public JsonObject list(@Param("aid") Integer id,@Param("pageNo") int pageNo,
			   @Param("limit") int limit){
		jobj = new JsonObject();
		if(null != id){
			List<Image> list = null;
			count = dao.count(Image.class, Cnd.where("d_article_id","=",id));
			list = dao.query(Image.class, Cnd.where("d_article_id","=",id),dao.createPager(pageNo, limit));
			if(null != list && list.size() > 0){
				jobj.setObj(list);
				jobj.setCount(count);
				jobj.setMsg("获取图片数据成功");
			}else{
				jobj.setSuccess(false);
				jobj.setMsg("获取图片数据失败，数据记录为0");
			}
		}else{
			jobj.setSuccess(false);
			jobj.setMsg("获取图片数据失败,未传入文章ID");
		}
		return jobj;
	}
	
	@At
	public JsonObject count(@Param("aid") Integer id){
		jobj = new JsonObject();
		if(null == id){
			count = dao.count(Image.class);
			jobj.setCount(count);
			jobj.setMsg("获取图片总数成功!");
		}else{
			count = dao.count(Image.class,Cnd.where("d_article_id","=",id));
			jobj.setCount(count);
			jobj.setMsg("获取文章ID["+id+"]下的图片总数成功!");
		}
		return jobj;
	}
}
