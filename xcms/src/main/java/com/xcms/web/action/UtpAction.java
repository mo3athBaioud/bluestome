package com.xcms.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.mss.dal.dao.OPlogDao;
import com.mss.dal.domain.OPlog;
import com.mss.dal.domain.TmpGprs;
import com.mss.dal.domain.TmpHpgj;
import com.mss.dal.view.ViewTerminal;
import com.xcms.common.json.JsonObject;
import com.xcms.web.service.TmpGprsService;
import com.xcms.web.service.TmpHpgjService;
import com.xcms.web.service.UtpService;

/**
 * 用户终端查询Action
 * @author bluestome
 *
 */
@IocBean
@InjectName
@At("/utp")
public class UtpAction extends BaseAction{

	@Inject
	private UtpService utpService;
	
	@Inject
	private OPlogDao oPlogDao;
	
	@Inject
	private TmpGprsService tmpGprsService;
	
	@Inject
	private TmpHpgjService tmpHpgjService;
	
	@At("/search")
	@Ok("json")
	public JsonObject search(@Param("phonenum") String phonenum,HttpServletRequest request){
		json = new JsonObject();
		OPlog op = new OPlog();
		try{
			String loginName = (String)request.getSession().getAttribute("LOGIN_SESSION_NAME");
			if(null != loginName){
				System.out.println(" >> loginName:"+loginName);
				op.setName(loginName);
				op.setOpType("GPRS_QUERY");
				op.setOpValue(phonenum);
			}
			ViewTerminal v = utpService.search(phonenum);
			if(null != v){
				json.setCount(1);
				json.setObj(v);
				json.setMsg("已查找到对应的终端数据!");
				op.setOpResult("成功");
			}else{
				json.setSuccess(false);
				json.setMsg("未查找到对应的终端数据!");
				op.setOpResult("失败");
			}
		}catch(Exception e){
			json.setSuccess(false);
			json.setMsg("查询数据发生异常,异常为:"+e);
			op.setOpResult("失败");
			e.printStackTrace();
		}finally{
			op = oPlogDao.save(op);
			logger.debug(" >> 操作日志添加:"+(op.getId() > 0?"成功":"失败"));
		}
		logger.debug(">> "+Json.toJson(json));
		return json;
	}

	@At("/utp")
	@Ok("json")
	public JsonObject utp(@Param("phonenum") String phonenum,@Param("start") int start,@Param("limit") int limit){
		json = new JsonObject();
		List<ViewTerminal> list = utpService.search(phonenum, start, limit);
		if(null != list && list.size() > 0){
			json.setCount(list.size());
			json.setObj(list);
			json.setMsg("已查找到对应的终端数据!");
		}else{
			json.setSuccess(false);
			json.setMsg("未查找到对应的终端数据!");
		}
		return json;
	}
	

	@At("/gprs")
	@Ok("json")
	public JsonObject gprs(@Param("phonenum") String phonenum,@Param("loginname") String loginname,@Param("start") int start,@Param("limit") int limit,HttpServletRequest request){
		System.out.println(" >> from page loginname:"+loginname);
		OPlog op = new OPlog();
		try{
			String loginName = (String)request.getSession().getAttribute("LOGIN_SESSION_NAME");
			if(null != loginName){
				
				System.out.println(" >> loginName:"+loginName);
				op.setName(loginName);
				op.setOpType("GPRS_QUERY");
				op.setOpValue(phonenum);
			}
			json = new JsonObject();
			List<TmpGprs> list = tmpGprsService.search(phonenum,loginName,start, limit);
			if(null != list && list.size() > 0){
				json.setCount(list.size());
				json.setObj(list);
				json.setMsg("已查找到对应的终端数据!");
				op.setOpResult("成功");
			}else{
				json.setSuccess(false);
				json.setMsg("未查找到对应的终端数据!");
				op.setOpResult("失败");
			}
		}catch(Exception e){
			json.setSuccess(false);
			json.setMsg("未查找到对应的终端数据!");
			op.setOpResult("失败");
		}finally{
			op = oPlogDao.save(op);	
			logger.debug(" >> 操作日志添加:"+(op.getId() > 0?"成功":"失败"));
		}
		return json;
	}
	
	@At("/hpgj")
	@Ok("json")
	public JsonObject hpgj(@Param("phonenum") String phonenum,@Param("loginname") String loginname,@Param("start") int start,@Param("limit") int limit,HttpServletRequest request){
		System.out.println(" >> from page loginname:"+loginname);
		OPlog op = new OPlog();
		try{
			String loginName = (String)request.getSession().getAttribute("LOGIN_SESSION_NAME");
			if(null != loginName){
				
				System.out.println(" >> loginName:"+loginName);
				op.setName(loginName);
				op.setOpType("HPGJ_QUERY");
				op.setOpValue(phonenum);
//				op.setOpResult(request.getRemoteAddr());
			}
			json = new JsonObject();
			List<TmpHpgj> list = tmpHpgjService.search(phonenum,loginName,start, limit);
			if(null != list && list.size() > 0){
				json.setCount(list.size());
				json.setObj(list);
				json.setMsg("已查找到对应的终端数据!");
				op.setOpResult("成功");
			}else{
				json.setSuccess(false);
				json.setMsg("未查找到对应的终端数据!");
				op.setOpResult("失败");
			}
		}catch(Exception e){
			json.setSuccess(false);
			json.setMsg("未查找到对应的终端数据!");
			op.setOpResult("失败");
		}finally{
			op = oPlogDao.save(op);	
			logger.debug(" >> 操作日志添加:"+(op.getId() > 0?"成功":"失败"));
		}
		return json;
	}
	
	public UtpService getUtpService() {
		return utpService;
	}

	public void setUtpService(UtpService utpService) {
		this.utpService = utpService;
	}

	public OPlogDao getOPlogDao() {
		return oPlogDao;
	}

	public void setOPlogDao(OPlogDao plogDao) {
		oPlogDao = plogDao;
	}

	public TmpGprsService getTmpGprsService() {
		return tmpGprsService;
	}

	public void setTmpGprsService(TmpGprsService tmpGprsService) {
		this.tmpGprsService = tmpGprsService;
	}
	
	
}
