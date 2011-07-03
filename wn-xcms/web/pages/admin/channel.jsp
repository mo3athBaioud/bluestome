<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="/session.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
		<meta content="text/html; charset=UTF-8" http-equiv="content-type">
		<title>渠道管理</title>
		<link rel="stylesheet" type="text/css"
			href="${ctx}/style/index.css" />
		<link href="${ctx}/style/default/common.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/style/default/form.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/style/default/list.css" rel="stylesheet" type="text/css">
		<style type="text/css">
	    html, body {
	        font:normal 12px verdana;
	        margin:0;
	        padding:0;
	        border:0 none;
	        overflow:hidden;
	        height:100%;
	    }
	    p {
	        margin:5px;
	    }
	    td{
			font-family:宋体;
			line-height:17pt;
			font-size:12pt;
	    }
	    .icon-disable {
			background-image: url(${ctx}/images/disable.png) !important;
		}
	    
	    </style>
		<link rel="stylesheet" type="text/css"
			href="${ctx}/resource/theme/default/resources/css/ext-all.css" />
		<script type="text/javascript"
			src="${ctx}/resource/extjs3.1/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="${ctx}/resource/extjs3.1/ext-all.js"></script>
		<script type="text/javascript"
			src="${ctx}/resource/commonjs/ext-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${ctx}/resource/commonjs/eredg4.js"></script>
		<link rel="stylesheet" type="text/css" href="${ctx}/resource/css/eredg4.css" />
		<script type="text/javascript" src="${ctx}/resource/extjs3.1/ux/ux-all.js"></script>
		<link rel="stylesheet" type="text/css"
			href="${ctx}/resource/extjs3.1/ux/css/ux-all.css" />
		<script type="text/javascript" src="${ctx}/scripts/common/swfupload.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/common/UploadDialog.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/common/RowExpander.js"></script>
		<script type="text/javascript"
			src="${ctx}/scripts/common/validation.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/common/examples.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/admin/channel.js"></script>
		<script type="text/javascript">
			var name = '渠道管理';
			var project = '${ctx}';
		</script>
	</head>
	<body>
			<div id="staff"></div>
	</body>
</html>