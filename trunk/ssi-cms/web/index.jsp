<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>图片资源管理系统</title>
		<style type="text/css">
			#loading{
		        position:absolute;
		        left:40%;
		        top:40%;
		        padding:2px;
		        z-index:20001;
		        height:auto;
		    }
		    #loading a {
		        color:#225588;
		    }
		    #loading .loading-indicator{
		        background:white;
		        color:#444;
		        font:bold 13px tahoma,arial,helvetica;
		        padding:10px;
		        margin:0;
		        height:auto;
		    }
		    #loading-msg {
		        font: normal 10px arial,tahoma,sans-serif;
		    }
		</style>
		<script type="text/javascript">
		    	var project = '${ctx}';
		</script>
	</head>
	<body>
		<div id="loading">
			<div class="loading-indicator">
				<img src="${ctx}/images/loading32.gif" width="31" height="31"
					style="margin-right:8px;float:left;vertical-align:top;" />
				图片资源管理系统
				<br />
				<span id="loading-msg">加载样式和图片...</span>
			</div>
		</div>
		<div id="bd">
			<link rel="stylesheet" type="text/css" media="all"
				href="${ctx}/resources/css/ext-all.css" />
			<link rel="stylesheet" type="text/css" media="all"
				href="${ctx}/styles/index.css" />
			<script type="text/javascript">document.getElementById('loading-msg').innerHTML = '加载核心...';</script>
			<script type="text/javascript" src="${ctx}/adapter/ext/ext-base.js"></script>
			<script type="text/javascript">document.getElementById('loading-msg').innerHTML = '加载UI组件...';</script>
			<script type="text/javascript" src="${ctx}/adapter/ext/ext-all.js"></script>
			<script type="text/javascript">document.getElementById('loading-msg').innerHTML = '模块初始化...';</script>
			<script type="text/javascript" src="${ctx}/scripts/common/RowExpander.js"></script>
			<script type="text/javascript"
				src="${ctx}/adapter/ext/ext-lang-zh_CN.js"></script>
			<script type="text/javascript"
				src="${ctx}/scripts/common/TreeCheckNodeUI.js"></script>
			<script type="text/javascript"
				src="${ctx}/scripts/common/validation.js"></script>
			<script type="text/javascript" src="${ctx}/scripts/main.js"></script>
		</div>
		<div id="header">
			<h1>
				<center>图片资源管理系统</center>
			</h1>
		</div>
	</body>
</html>
