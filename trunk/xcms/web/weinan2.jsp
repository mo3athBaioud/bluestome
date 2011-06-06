<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String loginName = "weinan";
	Object obj  = request.getSession().getAttribute("LOGIN_SESSION_NAME");
	if(null != obj){
		loginName = (String)obj;
	}
 %>
<html>
	<head>
		<title>渭南移动终端业务营销系统</title>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache, must-revalidate">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="/style/index.css" />
		<link rel="stylesheet" type="text/css"
			href="/resource/css/ext_icon.css" />
		<link rel="stylesheet" type="text/css"
			href="/resource/theme/default/resources/css/ext-all.css" />

		<script type="text/javascript"
			src="/resource/extjs3.1/adapter/ext/ext-base.js"></script>

		<script type="text/javascript" src="/resource/extjs3.1/ext-all.js"></script>

		<script type="text/javascript"
			src="/resource/commonjs/ext-lang-zh_CN.js"></script>

		<script type="text/javascript" src="/resource/commonjs/eredg4.js"></script>

		<link rel="stylesheet" type="text/css" href="/resource/css/eredg4.css" />

		<script type="text/javascript" src="/resource/extjs3.1/ux/ux-all.js"></script>

		<link rel="stylesheet" type="text/css"
			href="/resource/extjs3.1/ux/css/ux-all.css" />

		<script type="text/javascript">
		  var webContext = '';
		
		  var runMode = '1';
		
		  Ext.QuickTips.init();
		
		  Ext.form.Field.prototype.msgTarget = 'qtip';
		
		  Ext.BLANK_IMAGE_URL = '/resource/image/ext/s.gif';

			function readCookie(name) {
			    var nameEQ = name + "=",
			    ca = document.cookie.split(';'),
			    i,
			    c,
			    len = ca.length;
			for ( i = 0; i < len; i++) {
			    c = ca[i];
			    while (c.charAt(0) == ' ') {
			            c = c.substring(1, c.length);
			        }
			        if (c.indexOf(nameEQ) == 0) {
			            return c.substring(nameEQ.length, c.length);
			        }
			    }
			    return null;
			}
			
			function getPreferredStyleSheet() {
			    var i,
			        a,
			        links = document.getElementsByTagName("link"),
			        len = links.length;
			    for (i = 0; i < len; i++) {
			        a = links[i];
			        if (a.getAttribute("rel").indexOf("style") != -1 && a.getAttribute("rel").indexOf("alt") == -1 && a.getAttribute("title")) {
			            return a.getAttribute("title");
			        }
			    }
			    return null;
			}
			
			function createCookie(name, value, days) {
			    if (days) {
			        var date = new Date();
			        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
			        var expires = "; expires=" + date.toGMTString();
			    } else {
			        expires = "";
			    }
			    document.cookie = name + "=" + value + expires + "; path=/";
			}
			
			function onloadTheme(e){
			    var cookie = readCookie("style");
			    var title = cookie ? cookie : getPreferredStyleSheet();
			    setActiveStyleSheet(title);
			}
			
			function unonloadTheme(e){
			    var title = getActiveStyleSheet();
			    createCookie("style", title, 365);
			}
		
		</script>
	</head>
	<script type="text/javascript" src="/scripts/theme.js"></script>
	<script type="text/javascript"
		src="/resource/commonjs/extTabCloseMenu.js"></script>
	<!-- onunload="unonloadTheme();" -->
	<body onload="onloadTheme();">
		<div id="themeTreeDiv" class="x-hidden"></div>
		<div id="previewDiv" class="x-hidden">
			<img src="./resource/image/theme/default.jpg" />
		</div>
		<script type="text/javascript">Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL= './resource/image/ext/s.gif';

    var node_010102 = new Ext.tree.TreeNode({

        text:'权限管理',

        listeners: {

          'click': function(){

           }},

    	expanded:true,

        id:'id_node_010102'

    });

    var node_010103 = new Ext.tree.TreeNode({

        text:'通话IMEI管理',

        listeners: {

          'click': function(){
           }},

    	expanded:true,

        id:'id_node_010103'

    });

    var node_010104 = new Ext.tree.TreeNode({

        text:'终端管理',

        listeners: {

          'click': function(){

           }},

    	expanded:true,

        id:'id_node_010104'

    });
    
    var node_010105 = new Ext.tree.TreeNode({

        text:'日志管理',

        listeners: {

          'click': function(){
           }},

    	expanded:true,

        id:'id_node_010105'

    });
    
    var node_010106 = new Ext.tree.TreeNode({

        text:'统计分析',

        listeners: {

          'click': function(){
           }},

    	expanded:true,

        id:'id_node_010106'

    });
    
    var node_010107 = new Ext.tree.TreeNode({

        text:'外呼数据管理',

        listeners: {

          'click': function(){
           }},

    	expanded:true,

        id:'id_node_010107'

    });
    
    var node_010108 = new Ext.tree.TreeNode({

        text:'扩展IMEI管理',

        listeners: {

          'click': function(){
           }},

    	expanded:true,

        id:'id_node_010108'

    });
    
    var node_010109 = new Ext.tree.TreeNode({

        text:'业务查询',

        listeners: {

          'click': function(){
           }},

    	expanded:true,

        id:'id_node_010109'

    });
    
    var node_01010401 = new Ext.tree.TreeNode({

        text:'终端一级数据维护',

        listeners: {

        'click': function(){

           addTab('pages/terminal/terminal_1.html','终端一级数据维护','01010401','渭南移动终端业务营销系统 -> 系统管理 -> 终端管理 -> 终端一级数据维护','xhtml.png');

         }},

    	expanded:false,

        iconCls:'icon-xhtml',

        id:'id_node_01010401'

    });

    var node_01010301 = new Ext.tree.TreeNode({

        text:'通话IMEI导入',

        listeners: {

          'click': function(){

             addTab('pages/imei/imei_import.html','通话IMEI导入','01010301','渭南移动终端业务营销系统 -> 系统管理 -> 通话IMEI管理 -> 通话IMEI导入','cart_put.png');

           }},

    	expanded:false,

        iconCls:'icon-cart_put',

        id:'id_node_01010301'

    });

    var node_0101 = new Ext.tree.TreeNode({

        text:'业务管理',

        listeners: {

          'click': function(){

           }},

    	expanded:false,

        iconCls:'folder_wrenchIcon',

        id:'id_node_0101'

    });

     var node_0201 = new Ext.tree.TreeNode({

        text:'系统管理',

        listeners: {

          'click': function(){

           }},

    	expanded:false,

        iconCls:'folder_wrenchIcon',

        id:'id_node_0201'

    });

    var node_01010302 = new Ext.tree.TreeNode({

        text:'通话IMEI查询',

        listeners: {

          'click': function(){

             addTab('pages/imei/imei_query.html','通话IMEI查询','01010302','渭南移动终端业务营销系统 -> 系统管理 -> 通话IMEI管理 -> 通话IMEI查询','cart.png');

           }},

    	expanded:false,

        iconCls:'icon-cart',

        id:'id_node_01010302'

    });

    var node_01010402 = new Ext.tree.TreeNode({

        text:'终端二级数据维护',

        listeners: {

          'click': function(){

             addTab('pages/terminal/terminal_2.html','终端二级数据维护','01010402','渭南移动终端业务营销系统 -> 系统管理 -> 终端管理 -> 终端二级数据维护','script_lightning.png');

           }},

    	expanded:false,

        iconCls:'icon-script_lightning',

        id:'id_node_01010402'

    });

    var node_01010204 = new Ext.tree.TreeNode({

        text:'角色管理与授权',

        listeners: {

          'click': function(){

             addTab('pages/system/sysRole.html','角色管理与授权','01010204','渭南移动终端业务营销系统 -> 系统管理 -> 权限管理 -> 角色管理与授权','award_star_silver_3.png');

           }},

    	expanded:false,

        iconCls:'award_star_silver_3Icon',

        id:'id_node_01010204'

    });

    var node_01010303 = new Ext.tree.TreeNode({

        text:'通话IMEI导出',

        listeners: {

          'click': function(){

             addTab('pages/imei/imei_export.html','通话IMEI导出','01010303','渭南移动终端业务营销系统 -> 系统管理 -> 通话IMEI管理 -> 通话IMEI导出','cart_remove.png');

           }},

    	expanded:false,

        iconCls:'icon-cart_remove',

        id:'id_node_01010303'

    });

    var node_01010304 = new Ext.tree.TreeNode({

        text:'异常通话IMEI处理',

        listeners: {

          'click': function(){

             addTab('pages/imei/imei_exception.html','异常通话IMEI处理','01010304','渭南移动终端业务营销系统 -> 系统管理 -> 通话IMEI管理 -> 异常通话IMEI处理','cart_error.png');

           }},

    	expanded:false,

        iconCls:'icon-cart_error',

        id:'id_node_01010304'

    });
    
    var node_01010201 = new Ext.tree.TreeNode({

        text:'组织机构管理',

        listeners: {

          'click': function(){

             addTab('pages/system/sysOrg.html','组织机构管理','01010201','渭南移动终端业务营销系统 -> 系统管理 -> 权限管理 -> 组织机构管理','chart_organisation.png');

           }},

    	expanded:false,

        iconCls:'chart_organisationIcon',

        id:'id_node_01010201'

    });
    
    var node_01010202 = new Ext.tree.TreeNode({

        text:'人员管理与授权',

        listeners: {

          'click': function(){

             addTab('pages/system/sysOperator.html','人员管理与授权','01010202','渭南移动终端业务营销系统 -> 系统管理 -> 权限管理 -> 人员管理与授权','group_link.png');

           }},

    	expanded:false,

        iconCls:'group_linkIcon',

        id:'id_node_01010202'

    });

    var node_01010403 = new Ext.tree.TreeNode({

        text:'正式终端数据维护',

        listeners: {

          'click': function(){

             addTab('pages/terminal/terminal.html','正式终端数据维护','01010403','渭南移动终端业务营销系统 -> 系统管理 -> 终端管理 -> 正式终端数据维护','monitor_lightning.png');

           }},

    	expanded:false,

        iconCls:'icon-monitor_lightning',

        id:'id_node_01010403'

    });

    var node_01010205 = new Ext.tree.TreeNode({

        text:'菜单资源管理',

        listeners: {

          'click': function(){

             addTab('pages/system/sysMenu.html','菜单资源管理','01010205','渭南移动终端业务营销系统 -> 系统管理 -> 权限管理 -> 菜单资源管理','layout_content.png');

           }},

    	expanded:false,

        iconCls:'layout_contentIcon',

        id:'id_node_01010205'

    });

    var node_01010206 = new Ext.tree.TreeNode({

        text:'数据对象管理',

        listeners: {

          'click': function(){

             addTab('pages/system/sysObject.html','数据对象管理','01010206','渭南移动终端业务营销系统 -> 系统管理 -> 权限管理 -> 数据对象管理','database.png');

           }},

    	expanded:false,

        iconCls:'icon-database',

        id:'id_node_01010206'

    });
    
    var node_01010404 = new Ext.tree.TreeNode({

        text:'业务终端关联维护',

        listeners: {

          'click': function(){
			
             addTab('pages/terminal/terminal_business.html','业务终端关联维护','01010404','渭南移动终端业务营销系统 -> 系统管理 -> 终端管理 -> 业务终端关联维护','script_link.png');

           }},

    	expanded:false,

        iconCls:'icon-script_link',

        id:'id_node_01010404'

    });

     var node_01010501 = new Ext.tree.TreeNode({

        text:'操作日志',

        listeners: {

          'click': function(){

             addTab('pages/system/opLog.html','操作日志','01010501','渭南移动终端业务营销系统 -> 系统管理 -> 日志管理 -> 操作日志','page_white_text.png');

           }},

    	expanded:false,

        iconCls:'icon-page_white_text',

        id:'id_node_01010501'

    });

     var node_01010502 = new Ext.tree.TreeNode({

        text:'登录日志',

        listeners: {

          'click': function(){

             addTab('pages/system/loginLog.html','操作日志','01010502','渭南移动终端业务营销系统 -> 系统管理 -> 日志管理 -> 登录日志','page_white_text.png');

           }},

    	expanded:false,

        iconCls:'icon-page_white_text',

        id:'id_node_01010502'

    });
    
     var node_01010601 = new Ext.tree.TreeNode({

        text:'基础报表',

        listeners: {

          'click': function(){
           }},

    	expanded:false,

        iconCls:'icon-chart_pie',

        id:'id_node_01010601'

    });

     var node_01010602 = new Ext.tree.TreeNode({

        text:'自定义报表',

        listeners: {

          'click': function(){
           }},

    	expanded:false,

        iconCls:'icon-chart_bar',

        id:'id_node_01010602'

    });
    
     var node_01010701 = new Ext.tree.TreeNode({

        text:'外呼数据管理',

        listeners: {

          'click': function(){

             addTab('pages/customimei/imei_custom.html','外呼数据管理','01010701','渭南移动终端业务营销系统 -> 系统管理 -> 外呼数据管理 -> 外呼数据管理','telephone.png');

           }},

    	expanded:false,

        iconCls:'icon-telephone',

        id:'id_node_01010701'

    });
    
     var node_01010801 = new Ext.tree.TreeNode({

        text:'扩展IMEI管理',

        listeners: {

          'click': function(){

             addTab('pages/imeiextends/imei_extends.html','扩展IMEI管理','01010801','渭南移动终端业务营销系统 -> 系统管理 -> 扩展IMEI管理 -> 扩展IMEI管理','basket_go.png');

           }},

    	expanded:false,

        iconCls:'icon-basket_go',

        id:'id_node_01010801'

    });
    
     var node_01010901 = new Ext.tree.TreeNode({

        text:'数据业务查询',

        listeners: {

          'click': function(){
           }},

    	expanded:false,

        iconCls:'icon-magnifier',

        id:'id_node_01010901'

    });

 	var node_0101090101 = new Ext.tree.TreeNode({
 		text:'全网（手机报-短信）推荐查询',
 		listeners: {
 		'click': function(){
 		  addTab('pages/iquery/data/1.html','全网（手机报-短信）推荐查询','0101090101','渭南移动终端业务营销系统 -> 数据查询 -> 数据业务查询 -> 全网（手机报-短信）推荐查询','application_view_columns.png');
 		}},
 		expanded:false,
 		iconCls:'icon-application_view_columns',
 		id:'id_node_0101090101'
 	});

 	var node_0101090102 = new Ext.tree.TreeNode({
 		text:'全网（手机报-彩信）推荐查询',
 		listeners: {
 		'click': function(){
 		  addTab('pages/iquery/data/2.html','全网（手机报-彩信）推荐查询','0101090102','渭南移动终端业务营销系统 -> 数据查询 -> 数据业务查询 -> 全网（手机报-彩信）推荐查询','application_view_columns.png');
 		}},
 		expanded:false,
 		iconCls:'icon-application_view_columns',
 		id:'id_node_0101090102'
 	});

 	var node_0101090103 = new Ext.tree.TreeNode({
 		text:'号簿管家推荐查询',
 		listeners: {
 		'click': function(){
 		  addTab('pages/iquery/data/3.html','号簿管家推荐查询','0101090103','渭南移动终端业务营销系统 -> 数据查询 -> 数据业务查询 -> 号簿管家推荐查询','application_view_columns.png');
 		}},
 		expanded:false,
 		iconCls:'icon-application_view_columns',
 		id:'id_node_0101090103'
 	});

 	var node_0101090104 = new Ext.tree.TreeNode({
 		text:'手机地图推荐查询',
 		listeners: {
 		'click': function(){
 		  addTab('pages/iquery/data/4.html','手机地图推荐查询','0101090104','渭南移动终端业务营销系统 -> 数据查询 -> 数据业务查询 -> 手机地图推荐查询','application_view_columns.png');
 		}},
 		expanded:false,
 		iconCls:'icon-application_view_columns',
 		id:'id_node_0101090104'
 	});

 	var node_0101090105 = new Ext.tree.TreeNode({
 		text:'铃音盒推荐查询',
 		listeners: {
 		'click': function(){
 		  addTab('pages/iquery/data/5.html','铃音盒推荐查询','0101090105','渭南移动终端业务营销系统 -> 数据查询 -> 数据业务查询 -> 铃音盒推荐查询','application_view_columns.png');
 		}},
 		expanded:false,
 		iconCls:'icon-application_view_columns',
 		id:'id_node_0101090105'
 	});

 	var node_0101090106 = new Ext.tree.TreeNode({
 		text:'GPRS流量包推荐查询',
 		listeners: {
 		'click': function(){
 		  addTab('pages/iquery/data/6.jsp','GPRS流量包推荐查询','0101090106','渭南移动终端业务营销系统 -> 数据查询 -> 数据业务查询 -> GPRS流量包推荐查询','application_view_columns.png');
 		}},
 		expanded:false,
 		iconCls:'icon-application_view_columns',
 		id:'id_node_0101090106'
 	});

 	var node_0101090107 = new Ext.tree.TreeNode({
 		text:'手机支付推荐查询',
 		listeners: {
 		'click': function(){
 		  addTab('pages/iquery/data/7.html','手机支付推荐查询','0101090107','渭南移动终端业务营销系统 -> 数据查询 -> 数据业务查询 -> 手机支付推荐查询','application_view_columns.png');
 		}},
 		expanded:false,
 		iconCls:'icon-application_view_columns',
 		id:'id_node_0101090107'
 	});

 	var node_0101090108 = new Ext.tree.TreeNode({
 		text:'MM推荐查询',
 		listeners: {
 		'click': function(){
 		  addTab('pages/iquery/data/8.html','MM推荐查询','0101090108','渭南移动终端业务营销系统 -> 数据查询 -> 数据业务查询 -> MM推荐查询','application_view_columns.png');
 		}},
 		expanded:false,
 		iconCls:'icon-application_view_columns',
 		id:'id_node_0101090108'
 	});

 	var node_0101090109 = new Ext.tree.TreeNode({
 		text:'飞信推荐查询',
 		listeners: {
 		'click': function(){
 		  addTab('pages/iquery/data/9.html','飞信推荐查询','0101090109','渭南移动终端业务营销系统 -> 数据查询 -> 数据业务查询 -> 飞信推荐查询','application_view_columns.png');
 		}},
 		expanded:false,
 		iconCls:'icon-application_view_columns',
 		id:'id_node_0101090109'
 	});

 	var node_0101090110 = new Ext.tree.TreeNode({
 		text:'手机阅读推荐查询',
 		listeners: {
 		'click': function(){
 		  addTab('pages/iquery/data/10.html','手机阅读推荐查询','0101090110','渭南移动终端业务营销系统 -> 数据查询 -> 数据业务查询 -> 手机阅读推荐查询','application_view_columns.png');
 		}},
 		expanded:false,
 		iconCls:'icon-application_view_columns',
 		id:'id_node_0101090110'
 	});

 	var node_0101090111 = new Ext.tree.TreeNode({
 		text:'139邮箱推荐查询',
 		listeners: {
 		'click': function(){
 		  addTab('pages/iquery/data/11.html','139邮箱推荐查询','0101090111','渭南移动终端业务营销系统 -> 数据查询 -> 数据业务查询 -> 139邮箱推荐查询','application_view_columns.png');
 		}},
 		expanded:false,
 		iconCls:'icon-application_view_columns',
 		id:'id_node_0101090111'
 	});

 	var node_0101090112 = new Ext.tree.TreeNode({
 		text:'WIFI推荐查询',
 		listeners: {
 		'click': function(){
 		  addTab('pages/iquery/data/12.html','WIFI推荐查询','0101090112','渭南移动终端业务营销系统 -> 数据查询 -> 数据业务查询 -> WIFI推荐查询','application_view_columns.png');
 		}},
 		expanded:false,
 		iconCls:'icon-application_view_columns',
 		id:'id_node_0101090112'
 	});

 	var node_0101090113 = new Ext.tree.TreeNode({
 		text:'手机电视推荐查询',
 		listeners: {
 		'click': function(){
 		  addTab('pages/iquery/data/13.html','手机电视推荐查询','0101090113','渭南移动终端业务营销系统 -> 数据查询 -> 数据业务查询 -> 手机电视推荐查询','application_view_columns.png');
 		}},
 		expanded:false,
 		iconCls:'icon-application_view_columns',
 		id:'id_node_0101090113'
 	});


  	var node_0101090114 = new Ext.tree.TreeNode({
 		text:'号谱管家推荐查询',
 		listeners: {
 		'click': function(){
 		  addTab('pages/iquery/data/14.jsp','号谱管家推荐查询','0101090114','渭南移动终端业务营销系统 -> 数据查询 -> 数据业务查询 -> 号谱管家推荐查询','application_view_columns.png');
 		}},
 		expanded:false,
 		iconCls:'icon-application_view_columns',
 		id:'id_node_0101090114'
 	});
 	
     var node_01010902 = new Ext.tree.TreeNode({

        text:'终端销售子系统',

        listeners: {

          'click': function(){
           }},

    	expanded:false,

        iconCls:'icon-magnifier',

        id:'id_node_01010902'

    });
    
     var node_0101090201 = new Ext.tree.TreeNode({

        text:'用户2G终端推荐查询子系统',

        listeners: {

          'click': function(){

             addTab('pages/iquery/its2.html','用户2G终端推荐查询子系统','0101090201','渭南移动终端业务营销系统 -> 业务查询 -> 终端销售子系统 -> 用户2G终端推荐查询子系统','application_view_columns.png');

           }},

    	expanded:false,

        iconCls:'icon-application_view_columns',

        id:'id_node_0101090201'

    });
    
     var node_0101090203 = new Ext.tree.TreeNode({

        text:'异网2G终端推荐查询子系统',

        listeners: {

          'click': function(){

             addTab('pages/iquery/ots2.html','异网2G终端推荐查询子系统','0101090203','渭南移动终端业务营销系统 -> 业务查询 -> 终端销售子系统 -> 异网2G终端推荐查询子系统','application_view_columns.png');

           }},

    	expanded:false,

        iconCls:'icon-application_view_columns',

        id:'id_node_0101090203'

    });
    
     var node_0101090202 = new Ext.tree.TreeNode({

        text:'用户TD终端推荐查询子系统',

        listeners: {

          'click': function(){

             addTab('pages/iquery/its2.html','用户TD终端推荐查询子系统','0101090202','渭南移动终端业务营销系统 -> 业务查询 -> 终端销售子系统 -> 用户TD终端推荐查询子系统','application_view_columns.png');

           }},

    	expanded:false,

        iconCls:'icon-application_view_columns',

        id:'id_node_0101090202'

    });
    
      var node_0101090204 = new Ext.tree.TreeNode({

        text:'异网TD终端推荐查询子系统',

        listeners: {

          'click': function(){

             addTab('pages/iquery/otst.html','异网TD终端推荐查询子系统','0101090204','渭南移动终端业务营销系统 -> 业务查询 -> 终端销售子系统 -> 异网TD终端推荐查询子系统','application_view_columns.png');

           }},

    	expanded:false,

        iconCls:'icon-application_view_columns',

        id:'id_node_0101090204'

    });
    
     var node_020110 = new Ext.tree.TreeNode({
        text:'参数管理',
        listeners: {
        'click': function(){
         }},
    	expanded:true,
        id:'id_node_020110'
    });
    
      var node_02011001 = new Ext.tree.TreeNode({

        text:'常量数据管理',

        listeners: {

          'click': function(){

             addTab('pages/system/sysConstants.html','常量数据管理','02011001','渭南移动终端业务营销系统 -> 系统管理 -> 参数管理 -> 常量数据管理','application_view_columns.png');

           }},

    	expanded:false,

        iconCls:'icon-application_view_columns',

        id:'id_node_02011001'

    });

      var node_02011002 = new Ext.tree.TreeNode({

        text:'系统参数管理',

        listeners: {

          'click': function(){

             addTab('pages/iquery/bat_db.html','系统参数管理','02011002','渭南移动终端业务营销系统 -> 系统管理 -> 参数管理 -> 系统参数管理','application_view_columns.png');

           }},

    	expanded:false,

        iconCls:'icon-application_view_columns',

        id:'id_node_02011002'

    });
    
      var node_02011003 = new Ext.tree.TreeNode({

        text:'手机品牌管理',

        listeners: {

          'click': function(){

             addTab('pages/system/sysPhone.html','手机品牌管理','02011003','渭南移动终端业务营销系统 -> 系统管理 -> 参数管理 -> 手机品牌管理','phone.png');

           }},

    	expanded:false,

        iconCls:'icon-phone',

        id:'id_node_02011003'

    });
    
    var node_010110 = new Ext.tree.TreeNode({
        text:'业务管理',
        listeners: {
        'click': function(){
         }},
    	expanded:true,
        id:'id_node_010110'
    });
    
    
      var node_01011001 = new Ext.tree.TreeNode({

        text:'租机业务管理',

        listeners: {

          'click': function(){

             addTab('pages/bm/rbm.html','租机业务管理','01011001','渭南移动终端业务营销系统 -> 业务管理 -> 租机业务管理 ','bricks.png');

           }},

    	expanded:false,

        iconCls:'icon-bricks',

        id:'id_node_01011001'

    });

      var node_01011002 = new Ext.tree.TreeNode({

        text:'数据业务管理',

        listeners: {

          'click': function(){

             addTab('pages/bm/dbm.html','数据业务管理','01011002','渭南移动终端业务营销系统 -> 业务管理 -> 数据业务管理','briefcase.png');

           }},

    	expanded:false,

        iconCls:'icon-briefcase',

        id:'id_node_01011002'

    });
    
      var node_0101060101 = new Ext.tree.TreeNode({

        text:'月新增终端总体分布表',

        listeners: {

          'click': function(){

             addTab('pages/report/weinan/m_increase_termianl_dis.html','月新增终端总体分布表','0101060101','渭南移动终端业务营销系统 -> 统计分析 -> 基础报表 -> 月新增终端总体分布表','chart_pie.png');

           }},

    	expanded:false,

        iconCls:'icon-chart_pie',

        id:'id_node_0101060101'

    });

      var node_0101060102 = new Ext.tree.TreeNode({

        text:'月新增终端品牌分布表',

        listeners: {

          'click': function(){

             addTab('pages/report/weinan/m_increase_brand_dis.html','月新增终端品牌分布表','0101060102','渭南移动终端业务营销系统 -> 统计分析 -> 基础报表 -> 月新增终端品牌分布表','chart_pie.png');

           }},

    	expanded:false,

        iconCls:'icon-chart_pie',

        id:'id_node_0101060102'

    });

      var node_0101060103 = new Ext.tree.TreeNode({

        text:'月新增TD终端情况分布表',

        listeners: {

          'click': function(){

             addTab('pages/report/weinan/m_increase_3gtermianl_dis.html','月新增TD终端情况分布表','0101060103','渭南移动终端业务营销系统 -> 统计分析 -> 基础报表 -> 月新增TD终端情况分布表','chart_pie.png');

           }},

    	expanded:false,

        iconCls:'icon-chart_pie',

        id:'id_node_0101060103'

    });


      var node_0101060104 = new Ext.tree.TreeNode({

        text:'月新增智能终端情况分布表',

        listeners: {

          'click': function(){

             addTab('pages/report/weinan/m_increase_smart_termianl_dis.html','月新增智能终端情况分布表','0101060104','渭南移动终端业务营销系统 -> 统计分析 -> 基础报表 -> 月新增智能终端情况分布表','chart_pie.png');

           }},

    	expanded:false,

        iconCls:'icon-chart_pie',

        id:'id_node_0101060104'

    });

      var node_0101060105 = new Ext.tree.TreeNode({

        text:'月新增WIFI终端情况统计表',

        listeners: {

          'click': function(){

             addTab('pages/report/weinan/m_increase_wifi_termianl_dis.html','月新增WIFI终端情况统计表','0101060105','渭南移动终端业务营销系统 -> 统计分析 -> 基础报表 -> 月新增WIFI终端情况统计表','chart_pie.png');

           }},

    	expanded:false,

        iconCls:'icon-chart_pie',

        id:'id_node_0101060105'

    });
    
      var node_0101060106 = new Ext.tree.TreeNode({

        text:'全量终端总体分布表',

        listeners: {

          'click': function(){

             addTab('pages/report/weinan/a_increase_termianl_dis.html','全量终端总体分布表','0101060106','渭南移动终端业务营销系统 -> 统计分析 -> 基础报表 -> 全量终端总体分布表','chart_pie.png');

           }},

    	expanded:false,

        iconCls:'icon-chart_pie',

        id:'id_node_0101060106'

    });

      var node_0101060107 = new Ext.tree.TreeNode({

        text:'全量终端品牌分布表',

        listeners: {

          'click': function(){

             addTab('pages/report/weinan/a_increase_brand_dis.html','全量终端品牌分布表','0101060107','渭南移动终端业务营销系统 -> 统计分析 -> 基础报表 -> 全量终端品牌分布表','chart_pie.png');

           }},

    	expanded:false,

        iconCls:'icon-chart_pie',

        id:'id_node_0101060107'

    });

      var node_0101060108 = new Ext.tree.TreeNode({

        text:'全量TD终端情况分布表',

        listeners: {

          'click': function(){

             addTab('pages/report/weinan/a_increase_3gtermianl_dis.html','全量TD终端情况分布表','0101060108','渭南移动终端业务营销系统 -> 统计分析 -> 基础报表 -> 全量TD终端情况分布表','chart_pie.png');

           }},

    	expanded:false,

        iconCls:'icon-chart_pie',

        id:'id_node_0101060108'

    });

      var node_0101060109 = new Ext.tree.TreeNode({

        text:'全量智能终端情况分布表',

        listeners: {

          'click': function(){

             addTab('pages/report/weinan/a_increase_smart_termianl_dis.html','全量智能终端情况分布表','0101060109','渭南移动终端业务营销系统 -> 统计分析 -> 基础报表 -> 全量智能终端情况分布表','chart_pie.png');

           }},

    	expanded:false,

        iconCls:'icon-chart_pie',

        id:'id_node_0101060109'

    });
    
      var node_0101060110 = new Ext.tree.TreeNode({

        text:'全量WIFI终端情况统计表',

        listeners: {

          'click': function(){

             addTab('pages/report/weinan/a_increase_wifi_termianl_dis.html','全量WIFI终端情况统计表','0101060110','渭南移动终端业务营销系统 -> 统计分析 -> 基础报表 -> 全量WIFI终端情况统计表','chart_pie.png');

           }},

    	expanded:false,

        iconCls:'icon-chart_pie',

        id:'id_node_0101060110'

    });
    
      var node_0101060201 = new Ext.tree.TreeNode({

        text:'全网手机报-短信推荐清单',

        listeners: {

          'click': function(){

             addTab('pages/report/custome/1.html','全网手机报-短信推荐清单','01010602101','渭南移动终端业务营销系统 -> 统计分析 -> 自定义报表 -> 全网手机报-短信推荐清单','chart_pie.png');

           }},

    	expanded:false,

        iconCls:'icon-application_view_columns',

        id:'id_node_0101060201'

    });
    
      var node_0101060202 = new Ext.tree.TreeNode({

        text:'全网手机报-彩信推荐清单',

        listeners: {

          'click': function(){

             addTab('pages/report/custome/2.html','全网手机报-彩信推荐清单','01010602102','渭南移动终端业务营销系统 -> 统计分析 -> 自定义报表 -> 全网手机报-彩信推荐清单','chart_pie.png');

           }},

    	expanded:false,

        iconCls:'icon-application_view_columns',

        id:'id_node_0101060202'

    });
    
      var node_0101060203 = new Ext.tree.TreeNode({

        text:'号簿管家推荐清单',

        listeners: {

          'click': function(){

             addTab('pages/report/custome/3.html','号簿管家推荐清单','01010602103','渭南移动终端业务营销系统 -> 统计分析 -> 自定义报表 -> 号簿管家推荐清单','chart_pie.png');

           }},

    	expanded:false,

        iconCls:'icon-application_view_columns',

        id:'id_node_0101060203'

    });

      var node_0101060204 = new Ext.tree.TreeNode({

        text:'手机地图推荐清单',

        listeners: {

          'click': function(){

             addTab('pages/report/custome/4.html','手机地图推荐清单','01010602104','渭南移动终端业务营销系统 -> 统计分析 -> 自定义报表 -> 手机地图推荐清单','chart_pie.png');

           }},

    	expanded:false,

        iconCls:'icon-application_view_columns',

        id:'id_node_0101060204'

    });

      var node_0101060205 = new Ext.tree.TreeNode({

        text:'铃音盒推荐清单',

        listeners: {

          'click': function(){

             addTab('pages/report/custome/5.html','铃音盒推荐清单','01010602105','渭南移动终端业务营销系统 -> 统计分析 -> 自定义报表 -> 铃音盒推荐清单','chart_pie.png');

           }},

    	expanded:false,

        iconCls:'icon-application_view_columns',

        id:'id_node_0101060205'

    });
    
      var node_0101060206 = new Ext.tree.TreeNode({

        text:'GPRS流量包月推荐清单',

        listeners: {

          'click': function(){
             addTab('pages/report/custome/6.jsp','GPRS流量包月推荐清单','01010602106','渭南移动终端业务营销系统 -> 统计分析 -> 自定义报表 -> GPRS流量包月推荐清单','chart_pie.png');
           }},

    	expanded:false,

        iconCls:'icon-application_view_columns',

        id:'id_node_0101060206'

    });
    
      var node_0101060207 = new Ext.tree.TreeNode({

        text:'手机支付推荐清单',

        listeners: {

          'click': function(){

             addTab('pages/report/custome/7.html','手机支付推荐清单','01010602107','渭南移动终端业务营销系统 -> 统计分析 -> 自定义报表 -> 手机支付推荐清单','chart_pie.png');

           }},

    	expanded:false,

        iconCls:'icon-application_view_columns',

        id:'id_node_0101060207'

    });
    
      var node_0101060208 = new Ext.tree.TreeNode({

        text:'MM推荐清单',

        listeners: {

          'click': function(){

             addTab('pages/report/custome/8.html','MM推荐清单','01010602108','渭南移动终端业务营销系统 -> 统计分析 -> 自定义报表 -> MM推荐清单','chart_pie.png');

           }},

    	expanded:false,

        iconCls:'icon-application_view_columns',

        id:'id_node_0101060208'

    });
    
      var node_0101060209 = new Ext.tree.TreeNode({

        text:'飞信推荐清单',

        listeners: {

          'click': function(){

             addTab('pages/report/custome/9.html','飞信推荐清单','01010602109','渭南移动终端业务营销系统 -> 统计分析 -> 自定义报表 -> 飞信推荐清单','chart_pie.png');

           }},

    	expanded:false,

        iconCls:'icon-application_view_columns',

        id:'id_node_0101060209'

    });
    
      var node_0101060210 = new Ext.tree.TreeNode({

        text:'手机阅读清单',

        listeners: {

          'click': function(){

             addTab('pages/report/custome/10.html','手机阅读清单','0101060210','渭南移动终端业务营销系统 -> 统计分析 -> 自定义报表 -> 手机阅读清单','chart_pie.png');

           }},

    	expanded:false,

        iconCls:'icon-application_view_columns',

        id:'id_node_0101060210'

    });
    
      var node_0101060211 = new Ext.tree.TreeNode({

        text:'139邮箱推荐清单',

        listeners: {

          'click': function(){

             addTab('pages/report/custome/11.html','139邮箱推荐清单','0101060211','渭南移动终端业务营销系统 -> 统计分析 -> 自定义报表 -> 139邮箱推荐清单','chart_pie.png');

           }},

    	expanded:false,

        iconCls:'icon-application_view_columns',

        id:'id_node_0101060211'

    });
    
      var node_0101060212 = new Ext.tree.TreeNode({

        text:'WIFI推荐清单',

        listeners: {

          'click': function(){

             addTab('pages/report/custome/12.html','WIFI推荐清单','0101060212','渭南移动终端业务营销系统 -> 统计分析 -> 自定义报表 -> WIFI推荐清单','chart_pie.png');

           }},

    	expanded:false,

        iconCls:'icon-application_view_columns',

        id:'id_node_0101060212'

    });
    
      var node_0101060213 = new Ext.tree.TreeNode({

        text:'手机电视推荐清单',

        listeners: {

          'click': function(){

             addTab('pages/report/custome/13.html','手机电视推荐清单','0101060213','渭南移动终端业务营销系统 -> 统计分析 -> 自定义报表 -> 手机电视推荐清单','chart_pie.png');

           }},

    	expanded:false,

        iconCls:'icon-application_view_columns',

        id:'id_node_0101060213'

    });
    
    /**
 	node_01010601.appendChild(node_0101060101);
	node_01010601.appendChild(node_0101060102);
	node_01010601.appendChild(node_0101060103);
	node_01010601.appendChild(node_0101060104);
	node_01010601.appendChild(node_0101060105);
	node_01010601.appendChild(node_0101060106);
	node_01010601.appendChild(node_0101060107);
	node_01010601.appendChild(node_0101060108);
	node_01010601.appendChild(node_0101060109);
	node_01010601.appendChild(node_0101060110);
    node_010106.appendChild(node_01010601);
 	node_01010602.appendChild(node_0101060201);
	node_01010602.appendChild(node_0101060202);
	node_01010602.appendChild(node_0101060203);
	node_01010602.appendChild(node_0101060204);
	node_01010602.appendChild(node_0101060205);
	node_01010602.appendChild(node_0101060207);
	node_01010602.appendChild(node_0101060208);
	node_01010602.appendChild(node_0101060209);
	node_01010602.appendChild(node_0101060210);
	node_01010602.appendChild(node_0101060211);
	node_01010602.appendChild(node_0101060212);
	node_01010602.appendChild(node_0101060213);
    node_0101.appendChild(node_010106);
    node_010106.appendChild(node_01010602);
	node_01010602.appendChild(node_0101060206);
	**/    
    node_0101.appendChild(node_010109);
//    node_01010901.appendChild(node_0101090101);
//    node_01010901.appendChild(node_0101090102);
//    node_01010901.appendChild(node_0101090103);
//    node_01010901.appendChild(node_0101090104);
//    node_01010901.appendChild(node_0101090105);
    node_01010901.appendChild(node_0101090106);
    node_01010901.appendChild(node_0101090114);
//    node_01010901.appendChild(node_0101090107);
//    node_01010901.appendChild(node_0101090108);
//    node_01010901.appendChild(node_0101090109);
//    node_01010901.appendChild(node_0101090110);
//    node_01010901.appendChild(node_0101090111);
//    node_01010901.appendChild(node_0101090112);
//    node_01010901.appendChild(node_0101090113);
    node_010109.appendChild(node_01010901);
    
    /**
    node_01010902.appendChild(node_0101090201);
    node_01010902.appendChild(node_0101090202);
    node_01010902.appendChild(node_0101090203);
    node_01010902.appendChild(node_0101090204);
    node_010109.appendChild(node_01010902);
	**/
    var treePanel_0101 = new Ext.tree.TreePanel({

       autoHeight:false,

       autoWidth:false,

       autoScroll:false,

       animate:false,

       rootVisible:false,

       useArrows:true,

       title:'',

       border: false,

       containerScroll:true,

       applyTo:'div.card.0101',

       root:node_0101

    });
    
	});
	</script>
		<!-- 由<终端支撑平台:arm.Viewport/>标签生成的代码开始 -->

		<!-- overflow: auto;  -->
		<div id="div.card.0101" style="height: 100%; width: 100%;"></div>

		<script type="text/javascript">

var default_theme = 'default';

var mainTabs;

Ext.onReady(function(){

  Ext.BLANK_IMAGE_URL= './resource/image/ext/s.gif';

            mainTabs = new Ext.TabPanel({

                region:'center', 

                activeTab:0,

                id:'mainTabs',

                enableTabScroll:true,

                height:550,

                border:false,

                frame:true,

                plugins: new Ext.ux.TabCloseMenu(),

                items:[{

                      title:"<img align='top' class='IEPNG' src='./resource/image/ext/user.png'/>我的工作台",

                      listeners: {
                      	activate: function(){
                      		Ext.getCmp('centerPanel').setTitle('渭南移动终端业务营销系统 -> 我的工作台');
                    	}
                   	  },

                      html:"<iframe name='mainFrame'  src='info.html' scrolling='auto' frameborder='0' width='100%' height='100%' ></iframe>"

                    }]

             });

             

             var viewport = new Ext.Viewport({

                 layout:'border',

                 items:[

                 new Ext.Panel({

                     region:'north',

                     contentEl:'north', 

                     iconCls:'application_homeIcon', 

                     height:85,

                     collapsible:true,

                     border:false,

                     layout: 'fit',

                     title:'渭南移动终端业务营销系统'}),

                 

                 new Ext.BoxComponent({

                     region:'south',

                     contentEl: 'south',

                     height:17,

                     layout: 'fit',

                     collapsible: true}),

                 

                 {region:'center', 

                     id: 'centerPanel', 

                     iconCls:'',

                     title:'渭南移动终端业务营销系统',

                     autoScroll:false,

                     layout: 'fit',

                     items:[mainTabs]},

                   {region:'west',

                    width: 220,

                    collapsible: true,

                    minSize: 200,

                    maxSize: 350,

                    split: true,

                    //collapseMode:'mini',

                    iconCls:'book_previousIcon',

                    title: '系统导航',

                    layout:'accordion',

                    layoutConfig:{

                      animate:true,

                      activeOnTop :true

                    },

                   items: [
                    {
                    autoScroll:true,
                    border:false,
                    contentEl: 'div.card.0101',
                    iconCls:'folder_wrenchIcon',
                    title:'业务管理'
                   }]}
                ]}); 

    });

/**

 * 响应树节点单击事件

 */

function addTab(url,name,menuid,pathCh,icon){

//  alert('addTab.menuid:'+menuid);
  var id = "tab_id_" + menuid;
  if(url == '#'){
    showOkTipMsg('此菜单还没有指定请求页面,请点击其它功能菜单.');
  }else{
  var index = url.indexOf('.ered');
  if(index != -1)
    url = url + '&menuid4Log=' + menuid;
	 var n = mainTabs.getComponent(id);
 
  if (!n) {

     // 如果对centerPanel进行遮罩,则可以出现阴影mainTabs

     Ext.getCmp('centerPanel').getEl().mask('<span style=font-size:12>正在加载页面,请稍等...</span>', 'x-mask-loading');

     //document.getElementById('endIeStatus').click();//解决Iframe IE加载不完全的问题

     // 兼容IE和FF触发.click()函数

     var endIeStatus = document.getElementById("endIeStatus");

     if(document.createEvent){

         var ev = document.createEvent('HTMLEvents');

         ev.initEvent('click', false, true);

         endIeStatus.dispatchEvent(ev);

     }

     else endIeStatus.click();

     n = mainTabs.add({

       id:id,

       title:"<img align='top' class='IEPNG' src='./resource/image/ext/" + icon + "'/>" + name,

       closable:true,

       layout:'fit',

       listeners: {activate: function(){
       	Ext.getCmp('centerPanel').setTitle(pathCh)
      	}
     	},

       //html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src='+url+'></iframe>'

       //如果功能页面使用中心区域阴影加载模式则使用下面的代码unmaskCenterPanel();页面加载完毕后取消阴影

       	html:'<iframe scrolling="auto" frameborder="0" onload="unmaskCenterPanel()" width="100%" height="100%" src='+url+'></iframe>'

         });

       }

  mainTabs.setActiveTab(n);

 }

  }



// 解决Iframe IE加载不完全的问题

function endIeStatus(){}



Ext.EventManager.on(window, 'load', function(){

     setTimeout(

         function() {

            Ext.get('loading').remove();

            Ext.get('loading-mask').fadeOut({remove:true});

            }, 200); 

});

  

/**

 * 取消阴影(当子页面加载完成后通过parent.XXXX来调用)

 */

function unmaskCenterPanel(){

 // 如果对centerPanel进行遮罩,则可以出现阴影

 Ext.getCmp('centerPanel').getEl().unmask();

}

/**
 * 显示日期
 * 今天是:2011-04-06 星期三
 */
function showDate(){
	var d=new Date();
	var m = a(d.getMonth());
//	var str = "今天是 "+b(d.getDay())+","+a(d.getMonth())+" "+d.getDate()+","+d.getFullYear();
	var str = "今天是:"+d.getFullYear()+"-"+a(d.getMonth())+"-"+d.getDate()+" "+b(d.getDay());
	document.getElementById('rDate').innerHTML = str;
}

/**
 * 返回月份
 */
function a(num){
 switch(num){
  case 0:return "01";break;
  case 1:return "02";break;
  case 2:return "03";break;
  case 3:return "04";break;
  case 4:return "05";break;
  case 5:return "06";break;
  case 6:return "07";break;
  case 7:return "08";break;
  case 8:return "09";break;
  case 9:return "10";break;
  case 10:return "11";break;
  case 11:return "12";break;
 }
}
/**
 * 返回星期
 */
function b(num){
 switch(num){
  case 0:return "星期日";break;
  case 1:return "星期一";break;
  case 2:return "星期二";break;
  case 3:return "星期三";break;
  case 4:return "星期四";break;
  case 5:return "星期五";break;
  case 6:return "星期六";break;
 }
}

/**
 * 显示系统时钟
 */
// TODO 此功能会一定程度浪费客户端系统资源
function showTime() {
	var date = new Date();
	var h = date.getHours();
	h = h < 10 ? '0' + h : h;
	var m = date.getMinutes();
	m = m < 10 ? '0' + m : m;
	var s = date.getSeconds();
	s = s < 10 ? '0' + s : s;
	var str = "今天是:"+date.getFullYear()+"-"+a(date.getMonth())+"-"+date.getDate()+" "+b(date.getDay());
	document.getElementById('rTime').innerHTML = str+" "+h + ":" + m + ":" + s;
}

window.onload = function(){
	setInterval("showTime()", 1000);
}
</script>

		<style type="text/css">

 #loading-mask {

    Z-INDEX: 20000;

    LEFT: 0px;

    WIDTH: 100%;

    POSITION: absolute;

    TOP: 0px;

    HEIGHT: 100%;

    BACKGROUND-COLOR: white

}

#loading {

    PADDING-RIGHT: 2px;

    PADDING-LEFT: 2px;

    Z-INDEX: 20001;

    LEFT: 45%;

    PADDING-BOTTOM: 2px;

    PADDING-TOP: 2px;

    POSITION: absolute;

    TOP: 40%;

    HEIGHT: auto

}

#loading IMG {

    MARGIN-BOTTOM: 5px

}

#loading .loading-indicator {

    PADDING-RIGHT: 10px;

    PADDING-LEFT: 10px;

    BACKGROUND: white;

    PADDING-BOTTOM: 10px;

    MARGIN: 0px;

    FONT: 12px 宋体, arial, helvetica;

    COLOR: #555;

    PADDING-TOP: 10px;

    HEIGHT: auto;

    TEXT-ALIGN: center

}



.banner {

    font-family: "宋体";

    font-size: 12px;

    color:4798D7;

}
</style>

		<!--显示loding区域-->

		<DIV id=loading-mask></DIV>

		<DIV id=loading>

			<DIV class=loading-indicator>
				<IMG style="MARGIN-RIGHT: 8px" height=32
					src="./resource/image/ajax1.gif" width=36 align=absMiddle>
				正在初始化,请稍等...
			</DIV>

		</DIV>

		<div id="north">

			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				height="60"
				background="./resource/image/banner_background/default.png">
				<tr>
					<td style="padding-left:15px">
						<img class="IEPNG" src="./resource/image/weinan_login.png" />
					</td>
					<td style="padding-right:5px">
						<table width="100%" border="0" cellpadding="0" cellspacing="3"
							class="banner">
							<tr align="right">
								<td>
									<!-- 
									今天是:2011-04-06 星期三
									 -->
									上午好,渭南移动!
									<span id="rTime"><span>
								</td>

							</tr>

							<tr align="right">

								<td>

									<table border="0" cellpadding="0" cellspacing="1">

										<tr>

											<td>
												<div id="themeDiv">
											</td>

											<td>
												&nbsp;
											</td>
											<td>
												<div id="configDiv">
											</td>
											<td>
												&nbsp;
											</td>
											<td>
												<div id="closeDiv">
											</td>

										</tr>

									</table>

								</td>

							</tr>

						</table>

					</td>

				</tr>

			</table>

		</div>

		<div id="south" align="left">
			<table class="banner" width="100%">
				<tr>
					<td width="65%">
						<nobr>
							&nbsp;
							<img class="IEPNG" src="./resource/image/ext/comments2.png" />
							&nbsp;欢迎您,帐户:weinan&nbsp;所属部门:市场部
						</nobr>
					</td>

					<td width="35%">
						<div align="right">
							<nobr>
								Copyright&copy 2011 西安泰讯科技有限公司
							</nobr>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<a href="#" onclick="javascript:endIeStatus();" id="endIeStatus"
			style="display: none;" />
	</body>

</html>
