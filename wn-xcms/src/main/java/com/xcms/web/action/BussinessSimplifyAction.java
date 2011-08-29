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
import com.mss.dal.domain.BussinessSimplify;
import com.mss.dal.domain.Noplog;
import com.mss.dal.domain.OPlog;
import com.ssi.common.utils.FileUtils;
import com.xc.tools.CvsFileParser;
import com.xcms.UserSession;
import com.xcms.common.Constants;
import com.xcms.common.json.JsonObject;
import com.xcms.web.service.BussinessService;
import com.xcms.web.service.BussinessSimplifyService;
import com.xcms.web.service.NOplogService;

@IocBean
@InjectName
@At("/bussinesssimplify")
public class BussinessSimplifyAction extends BaseAction {

	@Inject
	private BussinessSimplifyService bussinessSimplifyService;
	
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
		BussinessSimplify bus = null;
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
					op.setIp(request.getRemoteAddr());
				}
			}			
			int count = bussinessSimplifyService.getCount(colName, value);
			if (count > 0) {
				List<BussinessSimplify> list = bussinessSimplifyService.search(colName,
						value, start, limit);
				logger.debug(" >> count:" + count);
				if (null != list && list.size() > 0) {
					bus = list.get(0);
					if(null != bus){
						op.setPhonenum(bus.getPhonenum());
						op.setPhonenumBDistrict(bus.getBdistrict());
						op.setResult(1);
						op.setMSuccess(bus.getMSuccess());
						op.setIsMarket(bus.getIsMarket());
						op.setPlatsell(bus.getPlatsell());
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
//		BussinessSimplify bus = null;
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
			int count = bussinessSimplifyService.getCount(btype, colName, value);
			if (count > 0) {
				List<BussinessSimplify> list = bussinessSimplifyService.search(btype, colName,
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
	
	/**
	 * 修改当前号码的营销状态 
	 * @param id
	 * @param phonenum
	 * @param isMarket
	 * @param mSuccess
	 * @return
	 */
	@At("/umarket")
	@Ok("json")
	public JsonObject umarket(@Param("id")int id, @Param("phonenum") String phonenum, @Param("isMarket") int isMarket, @Param("mSuccess") int mSuccess,@Param("platsell") int platsell,HttpServletRequest request) {
		json = new JsonObject();
		try{
			BussinessSimplify bs = bussinessSimplifyService.find(id);
			if(null != bs && bs.getPhonenum().equals(phonenum)){
				bs.setMSuccess(mSuccess);
				bs.setIsMarket(isMarket);
				bs.setPlatsell(platsell);
				if(bussinessSimplifyService.update(bs)){
					json.setSuccess(true);
					json.setMsg("更新数据状态成功!");
				}else{
					json.setSuccess(false);
					json.setMsg("更新数据失败");
				}
			}else{
				json.setSuccess(false);
				json.setMsg("未查询到手机号码为[" + phonenum + "]的数据");
			}
		}catch(Exception e){
			logger.error("BussinessAction.search.exception:"+e);
			json.setSuccess(false);
			json.setMsg("更新数据出现异常");
		}finally{
			
		}
		return json;
	}
	

	/**
	 * 查询业务数据
	 * 
	 * @param colName
	 * @param value
	 * @param start
	 * @param limit
	 * @return
	 */
	@At("/admin/list")
	@Ok("json")
	public JsonObject alist(@Param("colName") String colName, @Param("value") String value, @Param("start") int start, @Param("limit") int limit,HttpServletRequest request) {
		json = new JsonObject();
		UserSession us = null;
		Bussiness bus = null;
		try{
			String ip = request.getRemoteAddr();
			String sessionName = ip + "_" + Constants.USERSESSION;
			Object obj  = request.getSession().getAttribute(sessionName);
			if(null != obj){
				us = (UserSession)obj;
			}			
			int count = bussinessSimplifyService.getCount(colName, value);
			if (count > 0) {
				List<BussinessSimplify> list = bussinessSimplifyService.search(colName,
						value, start, limit);
				logger.debug(" >> count:" + count);
				if (null != list && list.size() > 0) {
					// 保存查询日志 异步
					json.setCount(count);
					json.setObj(list);
				} else {
					json.setSuccess(false);
				}
			} else {
				json.setSuccess(false);
				json.setMsg("未查询到[" + colName + "]为[" + value + "]的数据");
			}
		}catch(Exception e){
			logger.error("BussinessAction.search.exception:"+e);
		}
		return json;
	}
	
	/**
	 * 重置当前号码的营销状态 
	 * @param id
	 * @param phonenum
	 * @param isMarket
	 * @param mSuccess
	 * @return
	 */
	@At("/admin/reset")
	@Ok("json")
	public JsonObject reset(@Param("id")int id, @Param("phonenum") String phonenum,HttpServletRequest request) {
		json = new JsonObject();
		try{
			BussinessSimplify bs = bussinessSimplifyService.find(id);
			if(null != bs && bs.getPhonenum().equals(phonenum)){
				if(bs.getMSuccess() == 0 && bs.getIsMarket() == 0){
					json.setSuccess(false);
					json.setMsg("号码["+phonenum+"],不需要重置");
				}else{
					bs.setMSuccess(0);
					bs.setIsMarket(0);
					if(bussinessSimplifyService.update(bs)){
						json.setSuccess(true);
						json.setMsg("重置号码["+phonenum+"]数据状态成功!");
					}else{
						json.setSuccess(false);
						json.setMsg("重置号码["+phonenum+"]失败");
					}
				}
			}else{
				json.setSuccess(false);
				json.setMsg("未查询到手机号码为[" + phonenum + "]的数据");
			}
		}catch(Exception e){
			logger.error("BussinessAction.search.exception:"+e);
			json.setSuccess(false);
			json.setMsg("重置号码["+phonenum+"]出现异常");
		}
		return json;
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

	public BussinessSimplifyService getBussinessSimplifyService() {
		return bussinessSimplifyService;
	}

	public void setBussinessSimplifyService(
			BussinessSimplifyService bussinessSimplifyService) {
		this.bussinessSimplifyService = bussinessSimplifyService;
	}

	
}
