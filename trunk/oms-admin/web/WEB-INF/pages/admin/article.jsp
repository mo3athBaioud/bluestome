<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>管理</title>
		<%@ include file="/commons/head.jsp"%>
		<style type="text/css">
		<!--
			#images-view .x-panel-body{
				background: white;
				font: 11px Arial, Helvetica, sans-serif;
			}
			#images-view .thumb{
				background: #dddddd;
				padding: 3px;
			}
			#images-view .thumb img{
				height: 90px;
				width: 120px;
			}
			#images-view .thumb-wrap{
				float: left;
				margin: 4px;
				margin-right: 0;
				padding: 5px;
			}
			#images-view .thumb-wrap span{
				display: block;
				overflow: hidden;
				text-align: center;
			}
			
			#images-view .x-view-over{
			    border:1px solid #dddddd;
			    background: #efefef url(../resources/images/default/grid/row-over.gif) repeat-x left top;
				padding: 4px;
			}
			
			#images-view .x-view-selected{
				background: #eff5fb url(../images/selected.gif) no-repeat right bottom;
				border:1px solid #99bbe8;
				padding: 4px;
			}
			#images-view .x-view-selected .thumb{
				background:transparent;
			}
			
			#images-view .loading-indicator {
				font-size:11px;
				background-image:url('../resources/images/default/grid/loading.gif');
				background-repeat: no-repeat;
				background-position: left;
				padding-left:20px;
				margin:10px;
			}
		-->	
	</style>
		<script type="text/javascript">
	    	var project = '${ctx}';
	    	var webId = '${webId}';
	</script>
		<script type="text/javascript"
			src="${ctx}/scripts/extjs/ux/DataView-more.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/admin/article.js"></script>
	</head>

	<body>
		<div id="grid-query"></div>
		<div id="grid-div"></div>
	</body>
</html>
