package com.takesoon.oms.ssi.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssi.common.utils.HttpClientUtils;
import com.takesoon.oms.ssi.common.Constants;
import com.takesoon.oms.ssi.entity.Website;
import com.takesoon.oms.ssi.service.ImageCacheManager;
import com.takesoon.oms.ssi.service.WebsiteManager;
import com.takesoon.oms.ssi.utils.ExtUtil;

/**
 * 缓存清理类
 * @author Bluestome.Zhang
 *
 */
@Namespace("/admin")
@Action("cache")
@Results({
	@Result(name = "success", location = "/WEB-INF/pages/admin/cache.jsp"),
})
public class CacheAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String key = "CACHE_SIZE_LIST_KEY";
	static List<int[]> CACHE_SIZE_LIST = null;
	
	@Autowired
	private ImageCacheManager imageCacheManager;
	
	@Override
	public String execute(){
		return SUCCESS;
	}
	
	/**
	 * 获取缓存基本参数
	 * @throws IOException
	 */
	public void chart() throws IOException{
		response.setCharacterEncoding(Constants.CHARSET);
		PrintWriter out = null;
		StringBuffer json = new StringBuffer();
		try
		{
			// 是否需要参数防止请求攻击
			/* 调式  清理缓存 */
//			imageCacheManager.remove(key);
			 out = getOut(response);
			 if(null != imageCacheManager.get(key))
			 {
				 CACHE_SIZE_LIST = (List<int[]>)imageCacheManager.get(key);
			 }
			 else
			 {
				 CACHE_SIZE_LIST = new ArrayList<int[]>();
			 }
			 System.out.println(" > CACHE_SIZE_LIST.size:"+CACHE_SIZE_LIST.size());
			 int[] sizes = imageCacheManager.getCacheElementSize();
			 CACHE_SIZE_LIST.add(CACHE_SIZE_LIST.size(), sizes);
			 for(int j=0;j<CACHE_SIZE_LIST.size();j++)
			 {
				 json.append("{");
				 int[] ele = CACHE_SIZE_LIST.get(j);
				 json.append("id:").append(j+1).append(",");
				 for(int i=0;i<ele.length;i++)
				 {
					 int size = ele[i];
					 switch(i)
					 {
						 case 0:
							 //缓存中总的缓存数目
							 json.append("total:").append(size);
							 break;
						 case 1:
							 //内存中的元素数量 
							 json.append("mem:").append(size);
							 break;
						 case 2:
							 //硬盘中的缓存元素数量
							 json.append("disk:").append(size);
							 break;
						 default:
							 //缓存中总的缓存数目
							 json.append("total:").append(size);
							 break;
					 }
					 if(i < sizes.length-1)
					 {
						 json.append(",");
					 }
				 }
				 json.append("}");
				 if(j < CACHE_SIZE_LIST.size()-1)
				 {
					 json.append(",");
				 }
			 }
			 logger.debug("cache.json:"+json.toString());
			 String tmp = ExtUtil.getJsonFromList(CACHE_SIZE_LIST.size(), CACHE_SIZE_LIST);
			 System.out.println("cache.tmp:"+tmp);
			 out.println("{success:true,total:"+CACHE_SIZE_LIST.size()+",cache:["+json.toString()+"]}"); 
		}catch(Exception e){
			 out.println("{success:false,total:"+CACHE_SIZE_LIST.size()+",msg:'异常【"+e.getMessage()+"】'}");
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
			if(null != json)
			{
				json = null;
			}
			/* 重加载缓存 */
			imageCacheManager.remove(key);
			imageCacheManager.put(key, CACHE_SIZE_LIST);
		}
	}
}
