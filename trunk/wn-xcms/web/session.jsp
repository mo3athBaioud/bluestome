<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.xcms.common.Constants" %>
<%@ page import="com.xcms.UserSession" %>
<%
	UserSession us = null;
	String loginName = "weinan";
	String deptName = "渭南移动";
	String bdName = "渭南区局";
	String bdcode = "0000";
	String ip = request.getRemoteAddr();
	String sessionName = ip + "_" + Constants.USERSESSION;
	Object obj  = request.getSession().getAttribute(sessionName);
	if(null != obj){
		us = (UserSession)obj;
		loginName = us.getStaff().getUsername();
		deptName = us.getChannel().getChannlename();
		bdName = us.getBdistrict().getName();
		bdcode = us.getBdistrict().getCode();
	}
 %>