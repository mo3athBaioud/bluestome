<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>管理</title>
		<%@ include file="/commons/head.jsp"%>
		<script type="text/javascript">
	    	var project = '${ctx}';
		</script>
		<script type="text/javascript" src="${ctx}/scripts/admin/sysconfig.js"></script>
	</head>

	<body>
		<div id="grid-query"></div>
		<div id="grid-div"></div>
	</body>
</html>
