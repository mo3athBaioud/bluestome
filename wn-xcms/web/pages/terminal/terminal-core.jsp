<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="/session.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
		<meta content="text/html; charset=UTF-8" http-equiv="content-type">
		<title>终端核心库管理</title>
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
	    .settings {
	        background-image:url(${ctx}/images/icons/fam/folder_wrench.png);
	    }
	    .nav {
	        background-image:url(${ctx}/images/icons/fam/folder_go.png);
	    }
	    </style>
		<link rel="stylesheet" type="text/css" href="${ctx}/style/upload-icons.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/style/upload-dialog.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/style/index.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resource/extjs3.1/resources/css/ext-all.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resource/css/ext_icon.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resource/css/eredg4.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resource/extjs3.1/ux/css/ux-all.css" />
		<script type="text/javascript" src="${ctx}/resource/extjs3.1/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="${ctx}/resource/extjs3.1/ext-all.js"></script>
		<script type="text/javascript" src="${ctx}/resource/commonjs/ext-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${ctx}/resource/commonjs/eredg4.js"></script>
		<script type="text/javascript" src="${ctx}/resource/extjs3.1/ux/ux-all.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/common/swfupload.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/common/UploadDialog.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/common/RowExpander.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/common/examples.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/common/validation.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/components/TerminaQuery.js"></script>
		<script type="text/javascript" src="${ctx}/resource/extjs3.1/ux/fileuploadfield/FileUploadField.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/terminal/terminal_core.js"></script>
		<script type="text/javascript">
			var project = '${ctx}';
			var name = '终端核心库管理';
		</script>
	</head>
	<body>
			<div id="terminal-property"></div>
	</body>
</html>
