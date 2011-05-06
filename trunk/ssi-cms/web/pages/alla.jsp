<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/style.jsp"%>
<!-- 
	所有文章数据
 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>文章管理</title>
		<link rel="stylesheet" type="text/css" media="all" href="${ctx}/resources/css/ext-all.css" />
		<link rel="stylesheet" type="text/css" media="all" href="${ctx}/styles/index.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/UploadDialog.css">
		<script type="text/javascript" src="${ctx}/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="${ctx}/adapter/ext/ext-all.js"></script>
		<script type="text/javascript" src="${ctx}/adapter/ext/ext-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/common/swfupload.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/common/UploadDialog.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/common/RowExpander.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/common/examples.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/models/allarticle.js"></script>
		<script type="text/javascript">
		    	var project = '${ctx}';
				var colName = '${colName}';
				var values = '${value}';
		</script>
	</head>

	<body>
		<div id="div-article"></div>
	</body>
</html>
