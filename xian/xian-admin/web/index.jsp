<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>斯凯2.0群客后台管理系统</title>
	<meta http-equiv="cache-control" content="max-age=3600">
	<meta http-equiv="expires" content="3660000">    
	<meta http-equiv="keywords" content="Takesoon">
	<meta http-equiv="description" content="Takesoon">
	<%@ include file="/commons/head.jsp" %>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/module/login.js"></script>
	<script type="text/javascript">
	    	var project = '${ctx}';
			var token = '${param.token}';
	</script>
  </head>
  
  <body>
      	<script type="text/javascript">
    		var app = {};
    		
      		Ext.onReady(function(){
      			
				var xxfsCombo = new Ext.ux.form.LovCombo({
				    fieldLabel : '操作',
				    hiddenName : 'xxfs',
			        store: new Ext.data.SimpleStore({
			            fields: ['id','name'],
			            data: [[1,'进入系统'],[2,'退出系统']]
			        }),
				    mode : 'local',
				    triggerAction : 'all',
				    valueField : 'id',
				    displayField : 'name',
				    allowBlank : false,
				    editable : false,
				    typeAhead : true,
				    anchor : "100%",
				    beforeBlur : function() {
				    	// 第二种方法直接配在定义处. 
				    	//override beforeBlur method
				    },
				    listeners:{
				    	select:function(combo, record, index){
				    		var val = combo.value;
				    		if(val == '1')
				    		{
								window.location.href = project+'/pages/admin/index.jsp?token='+token;
				    		}
				    		if(val == '2')
				    		{
								window.location.href = project;
				    		}
				    	}
				    }
			   });
			   
			   var tmp_form = new Ext.FormPanel({
					labelWidth : 110,
					labelAlign : 'right',
					border : false,
					baseCls : 'x-plain',
					bodyStyle : 'padding:5px 5px 0',
					anchor : '100%',
					defaults : {
						width : 300,
						msgTarget : 'side'
					},
					items : [
						xxfsCombo
					],
					buttonAlign : 'center',
					minButtonWidth : 60,
					buttons : [
						{
							iconCls:'icon-cancel',
							text : '关闭',
							handler : function() {
								var win = Ext.getCmp('add_win');
								win.close();
								window.location.href = project+'/pages/admin/index.jsp';
							}
						}
					]
				});
				
				app.add_win = new Ext.Window({
					id:'add_win',
					title:'系统界面',
					width:500,
					resizable : false,
					modal : true,
					closeAction : 'hide',
					animCollapse : true,
					constrain : true,
					items : [
						tmp_form
					]
				});	
	
			    app.add_win.show();
      		});
      	</script>
  </body>
</html>
