package com.skymobi.qunker.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.skymobi.qunker.util.JSONUtils;

import net.sf.json.JSONArray;
import oms.admin.bean.GroupRoom;

@Namespace("/admin")
@Results({
	@Result(name = "success", location = "/WEB-INF/pages/grouproom.jsp")
})
public class GrouproomAction extends CRUDActionSupport<GroupRoom> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void delete() throws IOException {
		PrintWriter out = getOut(response);
		out.append("success:true,msg:'删除成功'");
		out.flush();
		out.close();
	}

	@Override
	public void list() throws IOException {
		List<GroupRoom> list = new ArrayList<GroupRoom>();
		for(int i=1;i<=100;i++){
			//区别Name的方法，即数据格式中有差不多10个数的差别。
			try{
				Thread.sleep(1);
			}catch(InterruptedException ie){
				logger.error("GrouproomAction.list.InterruptedException:"+ie);
			}
			GroupRoom gr = new GroupRoom();
			gr.setId(Long.valueOf(i));
			gr.setName(String.valueOf(System.currentTimeMillis()));
			list.add(i-1,gr);
		}
		if(list.size() > 0){
			logger.debug(" > GrouproomAction.list.size:{1}",list.size());
			toJson(response, list, list.size());
		}else{
			PrintWriter out = getOut(response);
			out.append("success:false,msg:'暂无数据'");
			out.flush();
			out.close();
		}
	}

	@Override
	public void save() throws IOException {
		PrintWriter out = getOut(response);
		out.append("success:true,msg:'保存成功'");
		out.flush();
		out.close();
	}

	@Override
	protected void toJson(HttpServletResponse response, List<GroupRoom> list, int count)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = getOut(response);
		JSONArray jsonArr = new JSONArray();
		StringBuffer buffer = new StringBuffer();
		Iterator<GroupRoom> it = list.iterator();
		buffer.append("{count:").append(count).append(
				",success:true,msg:'操作成功',grouproom:");
		while (it.hasNext()) {
			GroupRoom gr = it.next();
			String value = JSONUtils.bean2Json(gr);
			jsonArr.add(value);
		}
		buffer.append(jsonArr.toString()).append("}");
		out.print(buffer.toString());
		out.close();
	}

	@Override
	public void update() throws IOException {
		PrintWriter out = getOut(response);
		out.append("success:true,msg:'修改成功'");
		out.flush();
		out.close();
	}

}
