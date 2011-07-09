package com.xcms.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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
import com.mss.dal.domain.TerminalCore;
import com.ssi.common.utils.FileUtils;
import com.xc.tools.CvsFileParser;
import com.xcms.common.json.JsonObject;
import com.xcms.web.service.TerminalCoreService;

@IocBean
@InjectName
@At("/tereminalcore")
public class TerminalCoreAction extends BaseAction {

	@Inject
	private TerminalCoreService terminalCoreService;
	
	private static String SAVE_PATH = "D:/upload/";
	
	/**
	 * 添加终端核心数据数据
	 * @param terminalCore
	 * @return
	 */
	@At("/add")
	@Ok("json")
	public JsonObject insert(@Param("::object.") TerminalCore terminalCore,HttpServletRequest request){
		json = new JsonObject();
		if(null != request.getParameter("action") && "edit".equals(request.getParameter("action"))){
			if(terminalCoreService.update(terminalCore)){
				json.setSuccess(true);
				json.setMsg("编辑终端核心数据数据成功");
			}else{
				json.setSuccess(false);
				json.setMsg("编辑终端核心数据数据失败,是否存在相同终端TAC代码["+terminalCore.getTac()+"]!");
			}
		}else{
			if(terminalCoreService.add(terminalCore)){
				json.setSuccess(true);
				json.setMsg("添加终端核心数据数据成功");
			}else{
				json.setSuccess(false);
				json.setMsg("添加终端核心数据数据失败,是否存在相同终端核心数据代码!");
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
	@At("/list")
	@Ok("json")
	public JsonObject list(@Param("colName") String colName,@Param("value") String value,@Param("start") int start,@Param("limit") int limit){
		json = new JsonObject();
		int count = terminalCoreService.getCount(colName,value);
		List<TerminalCore> list =  terminalCoreService.search(colName,value,start, limit);
		logger.debug(" >> count:"+count);
		if(null != list && list.size() > 0){
			json.setCount(count);
			json.setObj(list);
		}else{
			json.setSuccess(false);
		}
		return json;
	}
	
	@At("/upload")
	@AdaptBy(type = UploadAdaptor.class, args = { "ioc:myUpload" })
	public void upload(@Param("filef")File f, ServletContext context, HttpServletResponse resp)
			throws IOException {
		String data = "{success:true,msg:\"上传成功!\"}";
		try {
			if (null != f) {
				String fileName = "tc_"+UUID.randomUUID().toString() + "."
						+ getFileSuffix(f.getName());
				String destpath = SAVE_PATH + fileName;
				boolean b = FileUtils.copyFileCover(f.getAbsolutePath(),
						destpath, true);
				if (b) {
					int cc = 0;
					// TODO 文件复制操作，处理终端核心数据
					List<String[]> list = CvsFileParser.getCSVAbsPath(destpath);
					for (String[] ss : list) {
						TerminalCore tc = terminalCoreService.adapter(ss);
						if(null != tc){
							boolean result = terminalCoreService.add(tc);
							if (result) {
								cc++;
							}
						}
					}
					if (FileUtils.deleteFile(destpath)) {
						logger.info(" >> 删除文件[" + destpath + "]成功!");
					}
					if(cc > 0){
						data = "{success:true,msg:\"上传成功，解析文件内数据为[" + list.size()
								+ "]条,入库[" + cc + "]条!\"}";
					}else{
						data = "{success:false,msg:\"上传成功，但入库数据为["+cc+"]条，可能原因为系统中已经存在相同的TAC码的数据了。}";
					}
				} else {
					data = "{success:false,msg:\"文件复制失败!\"}";
				}
			} else {
				data = "{success:false,msg:\"文件上传失败!\"}";
			}
		} catch (org.nutz.mvc.upload.UploadUnsupportedFileNameException e) {
			logger.error(" >> UploadUnsupportedFileNameException:" + e);
			data = "{success:false,msg:\"文件上传不受支持!\"}";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" >> Exception:" + e);
			data = "{success:false,msg:\"文件上传失败!\"}";
		}
		resp.setContentType("text/html");
		resp.getWriter().print(data);
	}
	
	public TerminalCoreService getTerminalCoreService() {
		return terminalCoreService;
	}

	public void setTerminalCoreService(TerminalCoreService terminalCoreService) {
		this.terminalCoreService = terminalCoreService;
	}
	
	
}
