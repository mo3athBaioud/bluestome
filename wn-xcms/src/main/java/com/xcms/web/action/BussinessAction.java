package com.xcms.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.data.io.CSV;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.UploadAdaptor;

import com.mss.dal.domain.Bussiness;
import com.mss.dal.domain.Noplog;
import com.mss.dal.domain.OPlog;
import com.ssi.common.utils.FileUtils;
import com.xc.tools.CvsFileParser;
import com.xcms.UserSession;
import com.xcms.common.Constants;
import com.xcms.common.json.JsonObject;
import com.xcms.web.service.BussinessService;
import com.xcms.web.service.NOplogService;

@IocBean
@InjectName
@At("/bussiness")
public class BussinessAction extends BaseAction {

	@Inject
	private BussinessService bussinessService;
	
	@Inject
	private NOplogService nOplogService;

	@Inject("java:'$config.get('save_path')'")
	private String SAVE_DIR = "D:/upload/";

	private static String SAVE_PATH = "D:/upload/";

	/**
	 * 查询员工
	 * 
	 * @param colName
	 * @param value
	 * @param start
	 * @param limit
	 * @return
	 */
	@At("/list")
	@Ok("json")
	public JsonObject list(@Param("btype") int btype, @Param("colName") String colName, @Param("value") String value, @Param("start") int start, @Param("limit") int limit,HttpServletRequest request) {
		json = new JsonObject();
		Noplog op = new Noplog();
		UserSession us = null;
		Bussiness bus = null;
		try{
			String ip = request.getRemoteAddr();
			String sessionName = ip + "_" + Constants.USERSESSION;
			Object obj  = request.getSession().getAttribute(sessionName);
			if(null != obj){
				us = (UserSession)obj;
				if(null != us){
					op.setLoginname(us.getStaff().getUsername());
					op.setLoginnameBDistrict(us.getBdistrict().getCode());
					op.setUid(us.getStaff().getId());
					op.setBtype(btype);
				}
			}			
			int count = bussinessService.getCount(btype, colName, value);
			if (count > 0) {
				List<Bussiness> list = bussinessService.search(btype, colName,
						value, start, limit);
				logger.debug(" >> count:" + count);
				if (null != list && list.size() > 0) {
					bus = list.get(0);
					if(null != bus){
						op.setPhonenum(bus.getPhonenum());
						op.setPhonenumBDistrict(bus.getBdistrict());
						op.setResult(1);
					}
					// 保存查询日志 异步
					json.setCount(count);
					json.setObj(list);
				} else {
					op.setPhonenum(value);
					op.setPhonenumBDistrict("0000");
					op.setResult(0);
					json.setSuccess(false);
				}
			} else {
				op.setPhonenum(value);
				op.setPhonenumBDistrict("0000");
				op.setResult(0);
				json.setSuccess(false);
				json.setMsg("未查询到[" + colName + "]为[" + value + "]的数据");
			}
		}catch(Exception e){
			logger.error("BussinessAction.search.exception:"+e);
			op.setPhonenum(value);
			op.setPhonenumBDistrict("0000");
			op.setResult(-1);
		}finally{
			if(null != op){
				nOplogService.save(op);
			}
		}
		return json;
	}

	/**
	 * 查询员工
	 * 
	 * @param colName
	 * @param value
	 * @param start
	 * @param limit
	 * @return
	 */
	@At("/search")
	@Ok("json")
	public JsonObject search(@Param("btype")int btype, @Param("colName") String colName, @Param("value") String value, @Param("start") int start, @Param("limit") int limit,HttpServletRequest request) {
		json = new JsonObject();
//		Noplog op = new Noplog();
//		UserSession us = null;
//		Bussiness bus = null;
		try{
//			String ip = request.getRemoteAddr();
//			String sessionName = ip + "_" + Constants.USERSESSION;
//			Object obj  = request.getSession().getAttribute(sessionName);
//			if(null != obj){
//				us = (UserSession)obj;
//				if(null != us){
//					op.setLoginname(us.getStaff().getUsername());
//					op.setLoginnameBDistrict(us.getBdistrict().getCode());
//					op.setUid(us.getStaff().getId());
//					op.setBtype(btype);
//				}
//			}			
			int count = bussinessService.getCount(btype, colName, value);
			if (count > 0) {
				List<Bussiness> list = bussinessService.search(btype, colName,
						value, start, limit);
				logger.debug(" >> count:" + count);
				if (null != list && list.size() > 0) {
//					bus = list.get(0);
//					if(null != bus){
//						op.setPhonenum(bus.getPhonenum());
//						op.setPhonenumBDistrict(bus.getBdistrict());
//						op.setResult(1);
//					}
					// 保存查询日志 异步
					json.setCount(count);
					json.setObj(list);
				} else {
//					op.setPhonenum(value);
//					op.setPhonenumBDistrict("0000");
//					op.setResult(0);
					json.setSuccess(false);
				}
			} else {
				json.setSuccess(false);
				json.setMsg("未查询到[" + colName + "]为[" + value + "]的数据");
			}
		}catch(Exception e){
			logger.error("BussinessAction.search.exception:"+e);
		}finally{
//			if(null != op){
//				nOplogService.save(op);
//			}
		}
		return json;
	}

	@At("/upload")
	// @Ok("json")
	@AdaptBy(type = UploadAdaptor.class, args = { "ioc:myUpload" })
	public void upload(@Param("btype")
	int btype, @Param("bussinessf")
	File f, ServletContext context, HttpServletResponse resp)
			throws IOException {
		String data = "{success:true,msg:\"上传成功!\"}";
		try {
			if (null != f) {
				logger.info(" >> SAVE_DIR:" + SAVE_DIR);
				String fileName = UUID.randomUUID().toString() + "."
						+ getFileSuffix(f.getName());
				String destpath = SAVE_PATH + fileName;
				boolean b = FileUtils.copyFileCover(f.getAbsolutePath(),
						destpath, true);
				if (b) {
					int cc = 0;
					// TODO 文件复制操作，业务处理
					List<String[]> list = CvsFileParser.getCSVAbsPath(destpath);
					for (String[] ss : list) {
						Bussiness bus = bussinessService.adapter(ss, btype);
						boolean result = bussinessService.save(bus);
						if (result) {
							cc++;
						}
					}
					if (FileUtils.deleteFile(destpath)) {
						logger.info(" >> 删除文件[" + destpath + "]成功!");
					}
					data = "{success:true,msg:\"上传成功，解析文件内数据为[" + list.size()
							+ "]条,入库[" + cc + "]条!\"}";
				} else {
					data = "{success:false,msg:\"文件复制失败!\"}";
				}
			} else {
				logger.info(" >> btype:" + btype);
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

	public BussinessService getBussinessService() {
		return bussinessService;
	}

	public void setBussinessService(BussinessService bussinessService) {
		this.bussinessService = bussinessService;
	}

	public NOplogService getNOplogService() {
		return nOplogService;
	}

	public void setNOplogService(NOplogService oplogService) {
		nOplogService = oplogService;
	}

	public String getSAVE_DIR() {
		return SAVE_DIR;
	}

	public void setSAVE_DIR(String save_dir) {
		SAVE_DIR = save_dir;
	}

}
