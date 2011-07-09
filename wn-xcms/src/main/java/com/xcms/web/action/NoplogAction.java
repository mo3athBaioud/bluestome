package com.xcms.web.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.mss.dal.domain.Noplog;
import com.ssi.common.utils.FileUtils;
import com.ssi.common.utils.IOUtil;
import com.xcms.common.json.JsonObject;
import com.xcms.web.service.BDistrictService;
import com.xcms.web.service.NOplogService;

@IocBean
@InjectName
@At("/noplog")
public class NoplogAction extends BaseAction {

	@Inject
	private NOplogService nOplogService;

	//文件大小
	final static int BYTE_LENGTH = 1024*1024*10;
	
	static String SAVE_PATH = "D:/noplog/";
	
	/**
	 * 查询业务区数据
	 * @param colName
	 * @param value
	 * @param start
	 * @param limit
	 * @return
	 */
	@At("/alist")
	@Ok("json")
	public JsonObject alist(@Param("colName") String colName,@Param("value") String value,@Param("start") int start,@Param("limit") int limit){
		json = new JsonObject();
		int count = nOplogService.getCount(colName,value);
		List<Noplog> list =  nOplogService.search(colName,value,start, limit);
		logger.debug(" >> count:"+count);
		if(null != list && list.size() > 0){
			json.setCount(count);
			json.setObj(list);
		}else{
			json.setSuccess(false);
		}
		return json;
	}
	
	/**
	 * 查询业务区数据
	 * @param colName
	 * @param value
	 * @param start
	 * @param limit
	 * @return
	 */
	@At("/list")
	@Ok("json")
	public JsonObject list(@Param("loginName") String loginName,@Param("colName") String colName,@Param("value") String value,@Param("start") int start,@Param("limit") int limit){
		json = new JsonObject();
		List<Noplog> list =  null;
		if(loginName.equals("admin")){
			int count = nOplogService.getCount(colName,value);
			list =  nOplogService.search(colName,value,start, limit);
			logger.debug(" >> count:"+count);
			if(null != list && list.size() > 0){
				json.setCount(count);
				json.setObj(list);
			}else{
				json.setSuccess(false);
			}
		}else{
			int count = nOplogService.getCount(loginName,colName,value);
			list =  nOplogService.search(loginName,colName,value,start, limit);
			logger.debug(" >> count:"+count);
			if(null != list && list.size() > 0){
				json.setCount(count);
				json.setObj(list);
			}else{
				json.setSuccess(false);
			}
		}
		return json;
	}
	
	/**
	 * 查询业务区数据
	 * @param colName
	 * @param value
	 * @param start
	 * @param limit
	 * @return
	 */
	@At("/count")
	@Ok("json")
	public JsonObject count(@Param("loginName") String loginName,@Param("startdate") String startdate,@Param("enddate") String enddate){
		json = new JsonObject();
		if(loginName.equals("admin")){
			int count = nOplogService.getCountByDate(startdate,enddate);
			logger.debug(" >> count:"+count);
			json.setSuccess(true);
			json.setCount(count);
			json.setObj(count);
		}else{
			json.setSuccess(false);
			json.setMsg("您没有该查询权限，请与管理员联系!");
		}
		return json;
	}
	
	
	/**
	 *  下载指定时间范围内的日志
	 * @param startdate
	 * @param enddate
	 */
	@At("/download")
	public void downloadNoplog(@Param("startdate") String startdate,@Param("enddate") String enddate, ServletContext context, HttpServletResponse resp)
		throws IOException{
		List<Noplog> list = null;
		File file = null;
		InputStream inStream = null;
		BufferedOutputStream outStream = null;
		ServletOutputStream out = null;
		int count = nOplogService.getCountByDate(startdate,enddate);
		if(count > 0){
			//TODO 生成CSV文件
			list = nOplogService.search(startdate, enddate);
			if(null != list && list.size() > 0){
				//TODO 生成文件到指定路径
				String fileName = System.currentTimeMillis() + ".csv";
				String content = nOplogService.generatorCsv(list);
				String path = SAVE_PATH+File.separator+fileName;
				try{
					//创建文件对象
					IOUtil.createFile(content, SAVE_PATH, fileName);
					file = new File(path);
					//文件存在
					if(file.exists()){
						inStream = new BufferedInputStream(new FileInputStream(path));
						byte[] buffer = new byte[inStream.available()];
						inStream.read(buffer);
						inStream.close();
						
						//Reset Response
						resp.reset();
						resp.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName));
						resp.addHeader("Content-Length", "" + file.length());
						
						//创建输出流对象
						outStream = new BufferedOutputStream(resp.getOutputStream());
						resp.setContentType("application/octet-stream;charset=utf-8");
						outStream.write(buffer);
						outStream.flush();
					}
				}catch(Exception e){
					logger.error(" >> NoplogAction.downloadNoplog.Exception:"+e);
				} finally {
					if (outStream != null)
						outStream.close();
					if (inStream != null)
						inStream.close();
					if (out != null) {
						out.close();
					}
					out = null;
					outStream = null;
					inStream = null;
					if(FileUtils.delFile(path)){
						logger.debug(" >> 删除文件["+path+"]成功!");
					}
					if(null != list){
						list.clear();
					}
				}
			}
		}
	}
	
	
	public NOplogService getNOplogService() {
		return nOplogService;
	}

	public void setNOplogService(NOplogService oplogService) {
		nOplogService = oplogService;
	}

	
	
}
