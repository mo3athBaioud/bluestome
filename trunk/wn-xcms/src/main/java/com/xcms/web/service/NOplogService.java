package com.xcms.web.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.ExpGroup;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.mss.dal.dao.NOplogDao;
import com.mss.dal.domain.Noplog;
import com.ssi.common.utils.DateUtils;

@IocBean
@InjectName
public class NOplogService {

	private static Log log = LogFactory.getLog(NOplogService.class);
	
	@Inject
	private NOplogDao nOplogDao;

	@Inject
	private Dao dao;
	
	/**
	 * 添加日志
	 * @param noplog
	 * @return
	 */
	public boolean save(Noplog noplog){
		boolean b = false;
		try{
			noplog = nOplogDao.save(noplog);
			if(noplog.getId() > 0){
				b = true;
			}
		}catch(Exception e){
			log.error(e);
		}
		return b;
	}
	
	/**
	 * 分页查询
	 * @param phonenum
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Noplog> search(String colName,String value,int start,int limit){
		Condition  cnd = null;
		if(null != colName && !"".equals(colName)){
			if(null != value && !"".equals(value)){
				cnd = Cnd.where(colName, "like", "%"+value+"%").orderBy().desc("d_id");
			}else{
				cnd = Cnd.where(colName, "like", "%%").orderBy().desc("d_id");
			}
		}
		start = start/limit+1;
		if(start == 0){
			start = 1;
		}
		Pager pager = dao.createPager(start, limit);
		List<Noplog> list = dao.query(Noplog.class, cnd, pager);
		return list;
	}
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public int getCount(String colName,String value){
		Condition  cnd = null;
		if(null != colName && !"".equals(colName)){
			if(null != value && !"".equals(value)){
				cnd = Cnd.where(colName, "like", "%"+value+"%");
			}else{
				cnd = Cnd.where(colName, "like", "%%");
			}
		}
		return nOplogDao.searchCount(Noplog.class, cnd);
	}
	
	/**
	 * 获取指定时间范围内的总记录数
	 * @return
	 */
	public int getCountByDate(String start,String end){
		Condition  cnd = null;
		if(null == end || "".equals(end)){
			Date ad = DateUtils.addDays(new Date(), +1);
			cnd = Cnd.where("d_createtime", ">", start).and("d_createtime", "<", DateUtils.formatDate(ad,"yyyy-MM-dd HH:mm:ss"));
		}else{
			cnd = Cnd.where("d_createtime", ">", start).and("d_createtime", "<", end);
		}
		return nOplogDao.searchCount(Noplog.class, cnd);
	}
	
	/**
	 * 查询指定时间范围内的日志列表
	 * @param phonenum
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Noplog> search(String start,String end){
		Condition  cnd = null;
		if(null == end || "".equals(end)){
			Date ad = DateUtils.addDays(new Date(), +1);
			cnd = Cnd.where("d_createtime", ">", start).and("d_createtime", "<", DateUtils.formatDate(ad,"yyyy-MM-dd HH:mm:ss"));
		}else{
			cnd = Cnd.where("d_createtime", ">", start).and("d_createtime", "<", end);
		}
		List<Noplog> list = nOplogDao.search(Noplog.class, cnd);
		return list;
	}
	
	/**
	 * 业务数据查询
	 * @param btype 业务类型
	 * @param colName 字段名称
	 * @param value 字段值
	 * @param start 起始位置
	 * @param limit 分页数量
	 * @return
	 */
	public List<Noplog> search(String loginanem,String colName,String value,int start,int limit){
		Condition  cnd = null;
		ExpGroup e1 = Cnd.exps("d_loginname", "=", loginanem);
		if(null != colName && !"".equals(colName)){
			if(null != value && !"".equals(value)){
				cnd = Cnd.where(colName, "=", value).and(e1).orderBy().desc("d_id");
			}else{
				cnd = Cnd.where(colName, "like", "%%").and(e1).orderBy().desc("d_id");
			}
		}else{
			cnd = Cnd.where(e1);
		}
		start = start/limit+1;
		if(start == 0){
			start = 1;
		}
		Pager pager = dao.createPager(start, limit);
		List<Noplog> list = dao.query(Noplog.class, cnd, pager);
		return list;
	}
	
	/**
	 * 业务数据统计
	 * @param btype 业务类型
	 * @param colName 字段名
	 * @param value 字段值
	 * @return
	 */
	public int getCount(String loginanem,String colName,String value){
		Condition  cnd = null;
		ExpGroup e1 = Cnd.exps("d_loginname", "=", loginanem);
		if(null != colName && !"".equals(colName)){
			if(null != value && !"".equals(value)){
				cnd = Cnd.where(colName, "=", value).and(e1);
			}else{
				cnd = Cnd.where(colName, "like", "%%").and(e1);
			}
		}else{
			cnd = Cnd.where(e1);
		}
		return nOplogDao.searchCount(Noplog.class, cnd);
	}

	/**
	 * 根据日志列表，生成CSV文件。
	 * @param list
	 * @return
	 */
	public String generatorCsv(List<Noplog> list){
		StringBuffer sb = new StringBuffer();
		sb.append("查询号码").append(",").append("号码所属业务区").append(",").append("查询时间").append(",").append("是否进行营销").append(",").append("营销是否成功").append(",").append("查询业务种类").append(",").append("查询工号").append(",").append("查询工号业务区").append("\r\n");
		for(Noplog op:list){
			sb.append(op.getPhonenum()).append(",").append(op.getPhonenumBDistrict()).append(",").append(DateUtils.formatDate(op.getCreatetime(), "yyyy-MM-dd HH:mm:ss")).append(",");
			switch(op.getIsMarket()){
				case 1:
					sb.append("否");
					break;
				case 2:
					sb.append("是");
					break;
				case 0:
				default:
					sb.append("");
					break;
			}
			sb.append(",");
			switch(op.getMSuccess()){
				case 1:
					sb.append("否");
					break;
				case 2:
					sb.append("是");
					break;
				case 0:
				default:
					sb.append("");
					break;
			}
			sb.append(",");
			switch(op.getBtype()){
			case 1:
				sb.append("无线音乐");
				break;
			case 2:
				sb.append("139邮箱");
				break;
			case 3:
				sb.append("飞信会员");
				break;
			case 4:
				sb.append("号簿管家");
				break;
			case 5:
				sb.append("全曲下载");
				break;
			case 6:
				sb.append("手机报");
				break;
			case 7:
				sb.append("手机视频");
				break;
			case 8:
				sb.append("手机阅读");
				break;
			case 9:
				sb.append("手机游戏");
				break;
			case 10:
				sb.append("手机电视");
				break;
			case 11:
				sb.append("移动MM");
				break;
			case 12:
				sb.append("GPRS流量包");
				break;
			case 13:
				sb.append("彩信包");
				break;
			case 14:
				sb.append("手机支付");
				break;
			case 15:
				sb.append("WIFI");
				break;
			case 16:
				sb.append("手机地图");
				break;
			default:
				sb.append("无法识别");
				break;
			}
			sb.append(",").append(op.getLoginname()).append(",").append(op.getLoginnameBDistrict());
			sb.append("\r\n");
		}
		return sb.toString();
	}
	public NOplogDao getNOplogDao() {
		return nOplogDao;
	}

	public void setNOplogDao(NOplogDao oplogDao) {
		nOplogDao = oplogDao;
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	
}
