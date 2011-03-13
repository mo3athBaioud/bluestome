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
		<!-- 
		<script type="text/javascript" src="${ctx}/scripts/models/explorer.js"></script>
		 -->
		<script type="text/javascript">
		    	var project = '${ctx}';
				var url = '${param.url}';
				var webid = '${param.webid}';
				var start = '${param.start}'
				var limit = '${param.limit}'
				alert("url:"+url);
				alert("webid:"+webid);
				alert("start:"+start);
				alert("limit:"+limit);
//				alert("values:"+values);
//				window.location = url;
		</script>
	</head>

	<body>
		<div id="div-explorer"></div>
		<a href="${ctx}/pages/articlesdoc/articledoc.jsp?id=${param.webid}&start=${param.start}&limit=${param.limit}">返回</a>
		<div>执行操作</div>
		<textarea id="op" rows="10" style="width:800px;"></textarea>
	</body>
</html>
