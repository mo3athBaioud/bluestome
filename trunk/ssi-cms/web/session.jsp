<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ssi.common.Constants" %>
<%
	String loginName = "guest";
	String ip = request.getRemoteAddr();
	boolean isAdmin = false;
	String sessionName = ip + "_" + Constants.USERSESSION;
	Object obj  = request.getSession().getAttribute(sessionName);
	if(null != obj){
		loginName = (String)obj;
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