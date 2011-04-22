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
import com.nutzd.domain.Website;

@InjectName
@IocBean(fields="dao")
@Ok("json")
@At("/article")
public class ArticleModule extends BasePbModule{

	private JsonObject jobj = null;
	
	private int count = 0;

	
	@At
	public JsonObject detail(@Param("id") Integer id){
		jobj = new JsonObject();
		if(null != dao){
			if(null != id){
				List<Article> list = null;
				list = dao.query(Article.class, Cnd.where("d_id","=",id),null);
				if(null != list && list.size() == 1){
					jobj.setObj(list.get(0));
					jobj.setCount(list.size());
					jobj.setMsg("获取文章数据成功");
				}else{
					jobj.setSuccess(false);
					jobj.setMsg("获取文章数据失败");
				}
			}else{
				jobj.setSuccess(false);
				jobj.setMsg("获取文章数据失败,未传入文章ID");
			}
		}
		return jobj;
	}
	
	@At
	public JsonObject list(@Param("wid") Integer id,@Param("pageNo") int pageNo,
						   @Param("limit") int limit){
		jobj = new JsonObject();
		if(null != id){
			List<Article> list = null;
			count = dao.count(Article.class, Cnd.where("d_web_id","=",id));
			list = dao.query(Article.class, Cnd.where("d_web_id","=",id),dao.createPager(pageNo, limit));
			if(null != list && list.size() > 0){
				jobj.setObj(list);
				jobj.setCount(count);
				jobj.setMsg("获取文章数据成功");
			}else{
				jobj.setSuccess(false);
				jobj.setMsg("获取文章数据失败，数据记录为0");
			}
		}else{
			jobj.setSuccess(false);
			jobj.setMsg("获取图片数据失败,未传入文章ID");
		}
		return jobj;
	}
	
	@At
	public JsonObject count(@Param("wid") Integer id){
		jobj = new JsonObject();
		if(null == id){
			count = dao.count(Article.class);
			jobj.setCount(count);
			jobj.setMsg("获取文章总数成功!");
		}else{
			count = dao.count(Article.class,Cnd.where("d_web_id","=",id));
			jobj.setCount(count);
			jobj.setMsg("获取网站["+id+"]下的图片总数成功!");
		}
		return jobj;
	}
}
