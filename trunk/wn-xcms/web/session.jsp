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
	boolean isAdmin = false;
	String sessionName = ip + "_" + Constants.USERSESSION;
	Object obj  = request.getSession().getAttribute(sessionName);
	if(null != obj){
		us = (UserSession)obj;
		if(null != us.getStaff()){
			loginName = us.getStaff().getUsername();
			if(us.getStaff().getAdmin() == 1){
			    //是否管理员
				isAdmin = true;
			}
		}
		if(null != us.getChannel()){
			deptName = us.getChannel().getChannlename();
		}
		if(null != us.getBdistrict()){
			bdName = us.getBdistrict().getName();
			bdcode = us.getBdistrict().getCode();
		}
	}else{
	%>
	<script type="text/javascript">
	<!--
		alert('会话超时，请重新登录!');
//		window.location.href = 	'<%=request.getContextPath()%>/login3.jsp';	
		window.parent.location.href = = 	'<%=request.getContextPath()%>/login3.jsp';	
	//-->
	</script>
	<%
//		request.getRequestDispatcher("/error.jsp").forward(request,response);
	}
 %>