<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>工作台</title>
	<meta http-equiv="cache-control" content="max-age=3660000">
	<meta http-equiv="expires" content="3660000">
	<%@ include file="/commons/head.jsp" %>
	<script type="text/javascript">
	    	var project = '${ctx}';
	</script>
  </head>
  
  <body>
		<div id="mydesk"></div>
		<div id="mydesk-grid"></div>
  </body>
</html>
