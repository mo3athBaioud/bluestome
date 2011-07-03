<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="/session.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
		<meta content="text/html; charset=UTF-8" http-equiv="content-type">
		<title>业务查询</title>
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
		<script type="text/javascript" src="${ctx}/scripts/iquery/business.js"></script>
		<script type="text/javascript">
			var project = '${ctx}';
			var name = '标题';
			var btype = '${param.btype}';
			var x = parseInt(btype);
			switch(x){
				case 1:
					name = '无线音乐高级俱乐部会员推荐查询';
					break;
				case 2:
					name = '139邮箱推荐查询';
					break;
				case 3:
					name = '飞信会员推荐查询';
					break;
				case 4:
					name = '号簿管家推荐查询';
					break;
				case 5:
					name = '全曲下载推荐查询';
					break;
				case 6:
					name = '手机报推荐查询';
					break;
				case 7:
					name = '手机视频推荐查询';
					break;
				case 8:
					name = '手机阅读推荐查询';
					break;
				case 9:
					name = '手机游戏推荐查询';
					break;
				case 10:
					name = '手机电视推荐查询';
					break;
				case 11:
					name = '移动MM推荐查询';
					break;
				case 12:
					name = 'GPRS流量包推荐查询';
					break;
				case 13:
					name = '彩信包推荐查询';
					break;
				case 14:
					name = '手机支付推荐查询';
					break;
				case 15:
					name = 'WIFI推荐查询';
					break;
				case 16:
					name = '手机地图推荐查询';
					break;
				default:
					name = '默认';
					break;
			}
		</script>
	</head>
	<body>
			<div id="utp"></div>
	</body>
</html>
