<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>斯凯2.0群客后台管理系统</title>
	<meta http-equiv="cache-control" content="max-age=3600">
	<meta http-equiv="expires" content="3660000">    
	<meta http-equiv="keywords" content="群客,群客管理后台,OMS">
	<meta http-equiv="description" content="群客管理后台">
	<%@ include file="/commons/head.jsp" %>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/module/login.js"></script>
	<script type="text/javascript">
	    	var project = '${ctx}';
			var webId = '${param.id}';
			var colName = '${colName}';
			var values = '${value}';
	</script>
  </head>
  
  <body>
      	<script type="text/javascript">
      		Ext.onReady(function(){
				Ext.Msg.show({
					title : '系统提示',
					msg : '欢迎进入'+projectname,
					buttons : Ext.Msg.OK,
					fn:function(){
						//是否提交登录成功
						//转入管理后台
						window.location.href = project+'/pages/admin/index.jsp';
//						window.location.href = project+'/admin/grouproom.action';
					},
					icon : Ext.MessageBox.INFO
				});
      		});
      	</script>
  </body>
</html>
