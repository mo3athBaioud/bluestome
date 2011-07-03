package com.xcms.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.UploadAdaptor;

import com.mss.dal.domain.Bussiness;
import com.xcms.common.json.JsonObject;
import com.xcms.web.service.BussinessService;

@IocBean
@InjectName
@At("/bussiness")
public class BussinessAction extends BaseAction {

	@Inject
	private BussinessService bussinessService;
	
	
	/**
	 * 查询员工
	 * @param colName
	 * @param value
	 * @param start
	 * @param limit
	 * @return
	 */
	@At("/list")
	@Ok("json")
	public JsonObject list(@Param("btype") int btype,@Param("colName") String colName,@Param("value") String value,@Param("start") int start,@Param("limit") int limit){
		json = new JsonObject();
		int count = bussinessService.getCount(btype,colName,value);
		if(count > 0){
			List<Bussiness> list =  bussinessService.search(btype,colName,value,start, limit);
			logger.debug(" >> count:"+count);
			if(null != list && list.size() > 0){
				//保存查询日志 异步
				json.setCount(count);
				json.setObj(list);
			}else{
				json.setSuccess(false);
			}
		}else{
			json.setSuccess(false);
			json.setMsg("未查询到["+colName+"]为["+value+"]的数据");
		}
		return json;
	}


	/**
	 * 查询员工
	 * @param colName
	 * @param value
	 * @param start
	 * @param limit
	 * @return
	 */
	@At("/search")
	@Ok("json")
	public JsonObject search(@Param("btype") int btype,@Param("colName") String colName,@Param("value") String value,@Param("start") int start,@Param("limit") int limit){
		json = new JsonObject();
		int count = bussinessService.getCount(btype,colName,value);
		if(count > 0){
			List<Bussiness> list =  bussinessService.search(btype,colName,value,start, limit);
			logger.debug(" >> count:"+count);
			if(null != list && list.size() > 0){
				//保存查询日志 异步
				json.setCount(count);
				json.setObj(list);
			}else{
				json.setSuccess(false);
			}
		}else{
			json.setSuccess(false);
			json.setMsg("未查询到["+colName+"]为["+value+"]的数据");
		}
		return json;
	}
	
	@At("/upload")
//	@Ok("json")
	@AdaptBy(type = UploadAdaptor.class, args = { "ioc:myUpload" })
	public void upload(@Param("btype") int btype, @Param("bussinessf") File f, ServletContext context,
			HttpServletResponse resp) throws IOException {
		String data = "{success:true,msg:\"上传成功!\"}";
		try{
			if(null != f){
				logger.info(" >> 文件保存路径:" + f.getAbsolutePath());
				//TODO 文件复制操作，业务处理
			}else{
				logger.info(" >> btype:"+btype);
				data = "{success:false,msg:\"文件上传失败!\"}";
			}
		}catch(org.nutz.mvc.upload.UploadUnsupportedFileNameException e){
			logger.error(" >> UploadUnsupportedFileNameException:"+e);
			data = "{success:false,msg:\"文件上传不受支持!\"}";
		}catch(Exception e){
			logger.error(" >> Exception:"+e);
			data = "{success:false,msg:\"文件上传失败!\"}";
		}
		resp.setContentType("text/html");
		resp.getWriter().print(data);
	}
	
	public BussinessService getBussinessService() {
		return bussinessService;
	}


	public void setBussinessService(BussinessService bussinessService) {
		this.bussinessService = bussinessService;
	}

	
}
