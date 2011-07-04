package com.xcms.web.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.UploadAdaptor;

@IocBean
@InjectName
@At("/upload")
public class UploadAction extends BaseAction {

	@At("/upload")
//	@Ok("json")
	@AdaptBy(type = UploadAdaptor.class, args = { "ioc:myUpload" })
	public void upload(@Param("btype") int btype, @Param("bussinessf") File f, ServletContext context,
			HttpServletResponse resp) throws IOException {
		String data = "{success:true,msg:\"上传成功!\"}";
		try{
			if(null != f){
				logger.info(" >> 文件保存路径:" + f.getAbsolutePath());
				data = "{success:true,msg:\"文件保存于["+f.getAbsolutePath()+"]!\"}";
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

}
