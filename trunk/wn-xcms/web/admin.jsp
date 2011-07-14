<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="/session.jsp" %>
<html>
	<head>
		<title>渭南移动终端业务营销系统-管理员</title>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache, must-revalidate">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/style/index.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resource/css/ext_icon.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resource/theme/default/resources/css/ext-all.css" />

		<script type="text/javascript" src="${ctx}/resource/extjs3.1/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="${ctx}/resource/extjs3.1/ext-all.js"></script>
		<script type="text/javascript" src="${ctx}/resource/commonjs/ext-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${ctx}/resource/commonjs/eredg4.js"></script>
		<link rel="stylesheet" type="text/css" href="${ctx}/resource/css/eredg4.css" />
		<script type="text/javascript" src="${ctx}/resource/extjs3.1/ux/ux-all.js"></script>
		<link rel="stylesheet" type="text/css" href="${ctx}/resource/extjs3.1/ux/css/ux-all.css" />
		<script type="text/javascript">
		  var project = '${ctx}';
		  var webContext = '';
		  var runMode = '1';
		
		  Ext.QuickTips.init();
		
		  Ext.form.Field.prototype.msgTarget = 'qtip';
		
		  Ext.BLANK_IMAGE_URL = '${ctx}/resource/image/ext/s.gif';

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
	<script type="text/javascript" src="${ctx}/scripts/theme.js"></script>
	<script type="text/javascript"
		src="${ctx}//resource/commonjs/extTabCloseMenu.js"></script>
	<!-- onunload="unonloadTheme();" -->
	<body onload="onloadTheme();">
		<div id="themeTreeDiv" class="x-hidden"></div>
		<div id="previewDiv" class="x-hidden">
			<img src="${ctx}/resource/image/theme/default.jpg" />
		</div>
		<script type="text/javascript">
		Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL= '${ctx}/resource/image/ext/s.gif';

    var node_0101 = new Ext.tree.TreeNode({

        text:'系统管理',

        listeners: {

          'click': function(){

           }},

    	expanded:false,

        iconCls:'folder_wrenchIcon',

        id:'id_node_0101'

    });
    
    var node_010109 = new Ext.tree.TreeNode({

        text:'系统管理',

        listeners: {

          'click': function(){
           }},

    	expanded:true,

        id:'id_node_010109'

    });
    
    var node_01010901 = new Ext.tree.TreeNode({

        text:'员工管理',

        listeners: {

          'click': function(){
           }},

    	expanded:false,

        iconCls:'icon-magnifier',

        id:'id_node_01010901'

    });

    var node_01010903 = new Ext.tree.TreeNode({
        text:'日志管理',
        listeners: {
          'click': function(){
           }},
    	expanded:false,
        iconCls:'icon-text_linespacing',
        id:'id_node_01010903'
    });
    
  	var node_0101090301 = new Ext.tree.TreeNode({
 		text:'日志管理',
 		listeners: {
 		'click': function(){
 		  addTab(project+'/pages/admin/noplog.jsp','日志管理','0101090301','渭南移动终端业务营销系统 -> 系统管理 -> 日志管理 -> 日志管理','application_view_columns.png');
 		}},
 		expanded:false,
 		iconCls:'icon-text_linespacing',
 		id:'id_node_0101090301'
 	});
 	
  	var node_0101090101 = new Ext.tree.TreeNode({
 		text:'员工管理',
 		listeners: {
 		'click': function(){
 		  addTab(project+'/pages/admin/staff.jsp','员工管理','0101090101','渭南移动终端业务营销系统 -> 系统管理 -> 员工管理 -> 员工管理','application_view_columns.png');
 		}},
 		expanded:false,
 		iconCls:'icon-application_view_columns',
 		id:'id_node_0101090101'
 	});

  	var node_0101090102 = new Ext.tree.TreeNode({
 		text:'渠道管理',
 		listeners: {
 		'click': function(){
 		  addTab(project+'/pages/admin/channel.jsp','渠道管理','0101090102','渭南移动终端业务营销系统 -> 系统管理 -> 员工管理 -> 渠道管理','application_view_columns.png');
 		}},
 		expanded:false,
 		iconCls:'icon-application_view_columns',
 		id:'id_node_0101090102'
 	});
 	
  	var node_0101090103 = new Ext.tree.TreeNode({
 		text:'业务区管理',
 		listeners: {
 		'click': function(){
 		  addTab(project+'/pages/admin/bdistrict.jsp','业务区管理','0101090103','渭南移动终端业务营销系统 -> 系统管理 -> 员工管理 -> 业务区管理','application_view_columns.png');
 		}},
 		expanded:false,
 		iconCls:'icon-application_view_columns',
 		id:'id_node_0101090103'
 	});
 	
    var node_01010902 = new Ext.tree.TreeNode({

        text:'终端管理',

        listeners: {

          'click': function(){
           }},

    	expanded:false,

        iconCls:'icon-magnifier',

        id:'id_node_01010902'

    });
    
  	var node_0101090201 = new Ext.tree.TreeNode({
 		text:'TAC管理',
 		listeners: {
 		'click': function(){
 		  addTab(project+'/pages/tac/index.jsp','TAC管理','0101090201','渭南移动终端业务营销系统 -> 系统管理 -> 终端管理 -> TAC管理','application_view_columns.png');
 		}},
 		expanded:false,
 		iconCls:'icon-application_view_columns',
 		id:'id_node_0101090201'
 	});
 	
  	var node_0101090202 = new Ext.tree.TreeNode({
 		text:'终端属性管理',
 		listeners: {
 		'click': function(){
 		  addTab(project+'/pages/terminal/terminal-property.jsp','终端属性管理','0101090202','渭南移动终端业务营销系统 -> 系统管理 -> 终端管理 -> 终端属性管理','application_view_columns.png');
 		}},
 		expanded:false,
 		iconCls:'icon-application_view_columns',
 		id:'id_node_0101090202'
 	});
 	
  	var node_0101090203 = new Ext.tree.TreeNode({
 		text:'业务数据导入',
 		listeners: {
 		'click': function(){
 		  addTab(project+'/pages/admin/import.jsp','业务数据导入','0101090203','渭南移动终端业务营销系统 -> 系统管理 -> 终端管理 -> 业务数据导入','application_view_columns.png');
 		}},
 		expanded:false,
 		iconCls:'icon-application_view_columns',
 		id:'id_node_0101090203'
 	});
 	
  	var node_0101090204 = new Ext.tree.TreeNode({
 		text:'终端核心库管理',
 		listeners: {
 		'click': function(){
 		  addTab(project+'/pages/terminal/terminal-core.jsp','终端核心库管理','0101090204','渭南移动终端业务营销系统 -> 系统管理 -> 终端管理 -> 终端核心库管理','application_view_columns.png');
 		}},
 		expanded:false,
 		iconCls:'icon-application_view_columns',
 		id:'id_node_0101090204'
 	});
 	
    node_01010903.appendChild(node_0101090301);
 	
    node_01010902.appendChild(node_0101090204);
//    node_01010902.appendChild(node_0101090203);
    node_01010902.appendChild(node_0101090202);
    node_01010902.appendChild(node_0101090201);
    
    node_01010901.appendChild(node_0101090101);
    node_01010901.appendChild(node_0101090102);
    node_01010901.appendChild(node_0101090103);
    
    node_010109.appendChild(node_01010903);
    node_010109.appendChild(node_01010901);
    node_010109.appendChild(node_01010902);
    node_0101.appendChild(node_010109);
    
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

  Ext.BLANK_IMAGE_URL= '${ctx}/resource/image/ext/s.gif';

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

                      title:"<img align='top' class='IEPNG' src='${ctx}/resource/image/ext/user.png'/>我的工作台",

                      listeners: {
                      	activate: function(){
                      		Ext.getCmp('centerPanel').setTitle('渭南移动终端业务营销系统 -> 我的工作台');
                    	}
                   	  },

                      html:"<iframe name='mainFrame'  src='info.jsp' scrolling='auto' frameborder='0' width='100%' height='100%' ></iframe>"

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
                    title:'系统管理'
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

       title:"<img align='top' class='IEPNG' src='${ctx}/resource/image/ext/" + icon + "'/>" + name,

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
					src="${ctx}/resource/image/ajax1.gif" width=36 align=absMiddle>
				正在初始化,请稍等...
			</DIV>

		</DIV>

		<div id="north">

			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				height="60"
				background="${ctx}/resource/image/banner_background/default.png">
				<tr>
					<td style="padding-left:15px">
						<img class="IEPNG" src="${ctx}/resource/image/weinan_login.png" />
					</td>
					<td style="padding-right:5px">
						<table width="100%" border="0" cellpadding="0" cellspacing="3"
							class="banner">
							<tr align="right">
								<td>
									<!-- 
									今天是:2011-04-06 星期三
									 -->
									您好,<%=loginName%>!
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
							<img class="IEPNG" src="${ctx}/resource/image/ext/comments2.png" />
							&nbsp;欢迎您,帐户:<%=loginName%>&nbsp;所属渠道:<%=deptName%>&nbsp;所属业务区:<%=bdName%>|<%=bdcode%>
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
