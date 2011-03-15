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
        <script type="text/javascript" src="${ctx}/scripts/common/data-view-plugins.js">
        </script>
        <script type="text/javascript" src="${ctx}/scripts/common/ImageDragZone.js">
        </script>
		<script type="text/javascript" src="${ctx}/adapter/ext/ext-lang-zh_CN.js"></script>
		<!-- 
		<script type="text/javascript" src="${ctx}/scripts/models/image.js"></script>
		 -->
		<script type="text/javascript" src="${ctx}/scripts/models/image-dataview.js"></script>
		<script type="text/javascript">
		    	var project = '${ctx}';
				var articleId = '${param.id}';
				var colName = '${colName}';
				var values = '${value}';
		</script>
        <style type="text/css">
            .thumb {
                background: #dddddd;
                padding: 3px;
            } .thumb table {
                height: 120px;
                width: 120px;
            } .thumb img {
                border: 1px solid white;
            } .thumb-wrap {
                float: left;
                margin: 4px;
                margin-right: 0;
                padding: 5px;
                /*
                width: 130px;
                height: 144px;
                */
            } .thumb-wrap span {
                display: block;
                overflow: hidden;
                text-align: center;
                height: 24px;
                line-height: 24px;
            } .x-view-over {
                border: 1px solid #dddddd;
                background: #efefef url(../../resources/images/default/grid/row-over.gif) repeat-x left top;
                padding: 4px;
            } .x-view-selected {
                background: #eff5fb url(../../images/selected.gif) no-repeat right bottom;
                border: 1px solid #99bbe8;
                padding: 4px;
            } .x-view-selected .thumb {
                background: transparent;
            }
        </style>	</head>

	<body>
		<div id="div-image"></div>
		<div>执行操作</div>
		<textarea id="op" rows="10" style="width:800px;"></textarea>
	</body>
</html>
