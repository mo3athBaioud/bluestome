Ext.onReady(function() {
	Ext.QuickTips.init();
	Ext.BLANK_IMAGE_URL = '/resource/image/ext/s.gif';
	
     var tools = [{
        id:'gear',
        handler: function(){
            Ext.Msg.alert('消息', '此功能正在开发!');
        }
    },{
        id:'close',
        handler: function(e, target, panel){
            panel.ownerCt.remove(panel, true);
        }
    }];

    var data = [
    	['<p><font color="green">系统消息</font></p>','终端数据最近已更新...','2011-03-01 12:00:00'],
    	['<p><font color="green">系统消息</font></p>','您已查询过一次终端[15800371329]的数据','2011-03-26 10:10:00'],
    	['<p><font color="green">系统消息</font></p>','您已查询过一次终端[15800371330]的数据','2011-03-26 10:15:00'],
    	['<p><font color="green">系统消息</font></p>','您已查询过一次终端[15800371331]的数据','2011-03-26 10:16:00'],
    	['<p><font color="green">系统消息</font></p>','您已查询过一次终端[15800371332]的数据','2011-03-26 10:16:00'],
    	['<p><font color="green">系统消息</font></p>','您已查询过一次终端[15800371333]的数据','2011-03-26 10:20:00'],
    	['<p><font color="green">系统消息</font></p>','您已查询过一次终端[15800371334]的数据','2011-03-26 10:21:00'],
    	['<p><font color="green">系统消息</font></p>','终端数据最近已更新...','2011-04-01 12:00:00']
    ];
    
    var store = new Ext.data.ArrayStore({
        fields: [
           {name: 'title'},
           {name: 'contents', type: 'string'},
           {name: 'times', type: 'string'}
        ]
    });
    
	store.loadData(data);
	
	var btn_del = new Ext.Button({
		text : '删除',
		iconCls : 'icon-del',
		handler : function(){
			Ext.Msg.alert('提示', '此功能正在开发!');
		}
	});
	
	var btn_search = new Ext.Button({
		text : '查询',
		iconCls : 'icon-search',
		handler : function(){
			Ext.Msg.alert('提示', '此功能正在开发!');
		}
	});
	
	var text_search = new Ext.form.TextField({
		name : 'textSearchcode',
		width : 150,
		listeners : {
			'specialkey' : function(field, e) {
				if (e.getKey() == Ext.EventObject.ENTER) {
					Ext.Msg.alert('提示', '此功能正在开发!');
				}
			}
		}
	});
	
	var ptb = new Ext.PagingToolbar({
		pageSize:10,
		store:store,
		displayInfo:true,
		displayMsg:'第 {0} - {1} 条  共 {2} 条',
		emptyMsg:'没有记录'
	});
	
    var grid = 	new Ext.grid.GridPanel({
    	title: '<img align="TOP" class="IEPNG" src="/images/comments.png"/>系统消息',
        store: store,
        columns: [
            {id:'title',header: '标题', width: 100, sortable: true, dataIndex: 'title'},
            {header: '内容', width: 200, sortable: true, dataIndex: 'contents'},
            {header: '时间', width: 150, sortable: true, dataIndex: 'times'}
        ],
		tools: tools,
        collapsible: true,
        animCollapse: false,
        closable:false,
        height:220,
        autoScroll: true,
        bbar : ptb,
        tbar : [btn_del,'-','请输入查询内容:','-',text_search,'-',btn_search]
    });	
	
//    var panel = new Ext.ux.Portal({
	  var panel = new Ext.Panel({
        id:'main-panel',
        baseCls:'x-plain',
        renderTo: 'mydesk',
        layout:'table',
        defaults: {
        	frame:true,
        	height: 300
    	},
        bodyStyle:'padding:5px 0 5px 5px',
        items:[
    		{
                baseCls:'x-plain',
				bodyStyle:'padding:10px 0 10px 10px',
		        margins:'35 5 5 0',
                items:[
                {
                	columnWidth:.5,
                    title: '<p><img align="TOP" class="IEPNG" src="/images/vcard.png"/>我的信息<p>',
                    width:200,
			        collapsible: true,
			        animCollapse: false,
			        closable:false,
			        tools: tools,
			        bodyStyle:'padding:10px',
                    items:[
			    		{
			                columnWidth:.5,
			                baseCls:'x-plain',
			                bodyStyle:'padding:5px 0 5px 5px',
			                items:[
			                	{
				                	xtype:'button',
				                	iconCls : 'icon-user_edit',
				                	text:'修改信息',
				                	handler:function(){
				                		Ext.MessageBox.alert('提示','"修改信息"正在开发!');
	                				}
			                	}
			                ]
			    		},{
			                columnWidth:.5,
			                baseCls:'x-plain',
			                bodyStyle:'padding:5px 0 5px 5px',
			                items:[
			                	{
				                	xtype:'button',
				                	iconCls : 'icon-edit',
				                	text:'修改密码',
				                	handler:function(){
										new Ext.Window({
											id:'modityPwd_window',
											title : '修改密码',
											width : 300,
											height : 150,
											resizable : false,
											modal : true,
											closeAction : 'close',
											items:[
												new Ext.FormPanel({
												id:'passwordForm',
												labelWidth : 70,
												labelAlign : 'right',
												url : project+'/staff/mp.cgi',
												border : false,
												baseCls : 'x-plain',
												bodyStyle : 'padding:5px 5px 0',
												defaults : {
													anchor : '80%',
													msgTarget : 'side'
												},
												defaultType : 'textfield',
												items : [
													{
														fieldLabel : '原始密码',
														id : 'npassword',
														name : 'oldPwd',
														inputType : 'password',
														vtype:'safe',
														allowBlank:false,
														maxLength : 50
													}, {
														fieldLabel:'密码',
														id:'pwd',
														name:'newPwd',
														inputType:'password',
														vtype:'safe',
														allowBlank:false,
														maxLength:20
													}, {
														fieldLabel:'确认密码',
														inputType:'password',
														name:'confirmPwd',
														allowBlank:false,
														vtype:'password',
														initialPassField: 'pwd', // 要比较的另外一个的组件的id
														maxLength:20
													}										
												],
												buttonAlign : 'center',
												minButtonWidth : 60,
												buttons : [{
													text : '修改密码',
													handler : function(btn) {
														/**
														if (Ext.getCmp('passwordForm').form.isValid()) {
															Ext.Msg.show({
																title : '提示',
																msg : '修改密码成功!',
																buttons : Ext.Msg.OK,
																icon : Ext.Msg.INFO
															});
														}else{
															alert('No');
														}
														**/
														var frm = Ext.getCmp('passwordForm').form;
														if (frm.isValid()) {
															frm.submit({
																waitTitle : '请稍候',
																waitMsg : '正在提交表单数据,请稍候...',
																success : function(form, action) {
																		var modityPwd_window = Ext.getCmp('modityPwd_window');
																		frm.reset();
																		modityPwd_window.close();
																		//退出系统
																		Ext.Msg.confirm('提示', '密码已修改成功，是否重新登录?', function(btn) {
																			if ('yes' == btn) {
																				Ext.Ajax.request({
																					url : project+'/servlet/LogoutServlet',
																					success : function() {
																						window.parent.location = project+'/login3.jsp';
																					},
																					failure : function() {
																						Ext.Msg.show({
																							title : '提示',
																							msg : '退出系统失败!',
																							icon : Ext.Msg.ERROR,
																							buttons : Ext.Msg.OK
																						});
																					}
																				});
																			}
																		});
																},
																failure : function(form,action) {
																	Ext.Msg.show({
																		title : '提示',
																		msg : action.result.msg,
																		buttons : Ext.Msg.OK,
																		icon : Ext.Msg.ERROR
																})
																}
															})
														}
														}
												}, {
													text : '重置密码',
													handler : function() {
														var form = Ext.getCmp('passwordForm').getForm();
														form.reset();
													}
												}, {
													text : '取消修改',
													handler : function() {
														Ext.getCmp('modityPwd_window').close();
													}
												}]										
											})
											]
										}).show();
									}
		                		}
			                ]
			    		}
                    ]
                },{
                	xtype:'panel',
                	baseCls:'x-plain',
                	height:10
	            }
//	            ,{
//	            	columnWidth:.5,
//                    title:'<img align="TOP" class="IEPNG" src="/images/icons/views.gif"/>其他内容',
//                    width:200,
//			        collapsible: true,
//			        animCollapse: false,
//			        tools: tools,
//            		html:'<p>暂无</p>'
//	            }
	            ]
            },{
                columnWidth:.33, 
                layout:'fit',
                baseCls:'x-plain',
				style:'padding:10px 0 10px 10px',
                items:[grid]
            }
        ]
    });
    
});