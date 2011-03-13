<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/style.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>文章管理</title>
		<link rel="stylesheet" type="text/css" media="all" href="${ctx}/resources/css/ext-all.css" />
		<link rel="stylesheet" type="text/css" media="all" href="${ctx}/styles/index.css" />
		<script type="text/javascript" src="${ctx}/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="${ctx}/adapter/ext/ext-all.js"></script>
		<script type="text/javascript" src="${ctx}/adapter/ext/ext-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/models/article.js"></script>
		<script type="text/javascript">
		    	var project = '<%=request.getContextPath()%>';
			
		</script>
	</head>

	<body>
		<div id="div-article"></div>
	</body>
</html>
