var app = {};
Ext.onReady(function() {
	Ext.QuickTips.init();
	Ext.BLANK_IMAGE_URL = '/resource/image/ext/s.gif';
	app.limit = 20;
	app.colName = "d_loginname"; //d_phonenum
	app.values = "";
	
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
    
	var ds_data = new Ext.data.Store({
		url : project + '/noplog/list.cgi?loginName='+username+'',
		baseParams:{},
		reader : new Ext.data.JsonReader({
			totalProperty : 'count',
			root : 'obj'
		}, [{name : 'phonenum',type : 'string'
		}, {name : 'btype',type : 'string'
		}, {name : 'loginname',type : 'string'
		}, {name : 'loginnameBDistrict',type : 'string'
		}, {name : 'phonenumBDistrict',type : 'string'
		}, {name : 'result',type : 'int'
		}, {name : 'createtime',type : 'string'
		}, {name : 'id',type : 'int'
		}, {name : 'uid',type : 'int'
		}])
	});

    var store = new Ext.data.ArrayStore({
        fields: [
           {name: 'title'},
           {name: 'contents', type: 'string'},
           {name: 'times', type: 'string'}
        ]
    });
    
	ds_data.load({
		params : {
			start : 0,
			limit : app.limit,
			colName : app.colName,
			value : username
		}
	});
	
	ds_data.on('beforeload',function(){
		Ext.apply(
			this.baseParams,{
				start : 0,
				limit : app.limit,
				colName : app.colName,
				value : username
	        }   
	 	);   
	});
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
		pageSize:app.limit,
		store:ds_data,
		displayInfo:true,
		displayMsg:'第 {0} - {1} 条  共 {2} 条',
		emptyMsg:'没有记录'
	});
	
    var grid = 	new Ext.grid.GridPanel({
    	title: '<img align="TOP" class="IEPNG" src="/images/comments.png"/>系统消息',
        store: ds_data,
        columns: [
            {id:'title',header: '号码', width: 100, sortable: true, dataIndex: 'phonenum'},
            {header: '号码业务区', width: 100, sortable: true, dataIndex: 'phonenumBDistrict'},
            {header: '登录名', width: 100, sortable: true, dataIndex: 'loginname'},
            {header: '时间', width: 150, sortable: true, dataIndex: 'createtime'},
        	{header: "业务类型", width: 100, sortable: true, dataIndex: 'btype',renderer:function(v){
				var x = parseInt(v);
				switch(x){
					case 1:
						name = '无线音乐高级俱乐部会员';
						break;
					case 2:
						name = '139邮箱';
						break;
					case 3:
						name = '飞信会员';
						break;
					case 4:
						name = '号簿管家';
						break;
					case 5:
						name = '全曲下载';
						break;
					case 6:
						name = '手机报';
						break;
					case 7:
						name = '手机视频';
						break;
					case 8:
						name = '手机阅读';
						break;
					case 9:
						name = '手机游戏';
						break;
					case 10:
						name = '手机电视';
						break;
					case 11:
						name = '移动MM';
						break;
					case 12:
						name = 'GPRS流量包';
						break;
					case 13:
						name = '彩信包';
						break;
					case 14:
						name = '手机支付';
						break;
					case 15:
						name = 'WIFI';
						break;
					case 16:
						name = '手机地图';
						break;
					default:
						name = '默认';
						break;
				}
        		return '<font color="blue">'+name+'</font>';
        	}}
        ],
//		tools: tools,
        collapsible: true,
        animCollapse: false,
        closable:false,
        height:350,
        width:600,
        autoScroll: true,
        bbar : ptb,
        tbar : [btn_del,'-','请输入查询内容:','-',text_search,'-',btn_search]
    });	
	
	/**
	 * 渠道下拉框
	 */
	app.channel_combo_store = new Ext.data.Store({
		proxy: new Ext.data.HttpProxy({
                        //这里是参数可以顺便写,这个数据源是在第一个下拉框select的时候load的
			url: project+'/channel/list.cgi?start=0&limit=100'
		}),
		reader: new Ext.data.JsonReader({
		root: 'obj',
		id: 'id'
		}, [
			{name: 'code', mapping: 'channelcode'},
			{name: 'name', mapping: 'channlename'}
		])
	}); 
	
	app.data_form = new Ext.FormPanel({
		id:'add_form',
		labelWidth : 80,
		labelAlign : 'right',
		border : false,
		baseCls : 'x-plain',
		bodyStyle : 'padding:5px 5px 0',
		anchor : '100%',
		url : project+'/staff/add.cgi',
		defaults : {
			width : 300,
			msgTarget : 'side'
		},
		defaultType : 'textfield',
		items : [
			{
				xtype:'hidden',
				name : 'staff.id',
				allowBlank : false,
				value:uid
			},{
				fieldLabel : '员工名',
				name : 'staff.username',
				allowBlank : false,
				readOnly:true,
				maxLength:16,
				value:username
			},{
				fieldLabel : '手机号码',
				name : 'staff.mobile',
				allowBlank : false,
				maxLength:11,
				value:mobile
			},{
				fieldLabel : '办公室号码',
				name : 'staff.officephone',
				allowBlank : false,
				maxLength : 12,
				value:phone
			}],
			buttonAlign : 'center',
			minButtonWidth : 60,
			buttons : [{
				id:'dbm_form_save',
				text : '保存',
				iconCls:'icon-accept',
				handler : function(btn) {
					var frm = Ext.getCmp('add_form').form;
					if (frm.isValid()) {
						var dnfield = frm.findField('username');
						frm.submit({
							waitTitle : '请稍候',
							waitMsg : '正在提交表单数据,请稍候...',
							success : function(form, action) {
								Ext.Msg.confirm('提示', '信息已修改成功,系统将强制退出并且请您重新登录!', function(btn) {
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
								});
							},
							failure : function(){
								Ext.Msg.show({
									title : '错误提示',
									msg : '"' + dnfield.getValue() + '" ' + '名称可能已经存在或者您没有添加数据的权限!',
									buttons : Ext.Msg.OK,
									icon : Ext.Msg.ERROR
								})
							}
						})
					}
				}
			}, {
				id:'data_form_reset',
				text : '重置',
				iconCls:'icon-arrow_refresh_small',
				handler : function() {
					Ext.getCmp('add_form').form.reset();
				}
			}, {
				iconCls:'icon-cancel',
				text : '取消',
				handler : function() {
					app.data_form.getForm().reset();
					var win = Ext.getCmp('add_win');
					win.hide();
				}
			}]
	});
	
	app.add_win = new Ext.Window({
		id:'add_win',
		title:'窗口',
		iconCls:'icon-add',
		width : 500,
		resizable : false,
		autoHeight : true,
		modal : true,
		closeAction : 'hide',
		animCollapse : true,
		pageY : 20,
		pageX : document.body.clientWidth / 2 - 420 / 2,
		animateTarget : Ext.getBody(),
		constrain : true,
		items : [
			app.data_form
		]
	});	
	

	var modpwd_win = new Ext.Window({
		id:'modityPwd_window',
		title : '修改密码',
		width : 300,
		height : 150,
		resizable : false,
		modal : true,
		closeAction : 'hide',
		animCollapse : true,
		pageY : 20,
		pageX : document.body.clientWidth / 2 - 420 / 2,
		animateTarget : Ext.getBody(),
		constrain : true,
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
					Ext.getCmp('passwordForm').form.reset();
					Ext.getCmp('modityPwd_window').hide();
				}
			}]										
		})
		]
	});

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
//			        tools: tools,
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
										app.add_win.show();
										app.add_win.setTitle("修改个人信息");
										Ext.getCmp('data_form_reset').show();
//				                		Ext.MessageBox.alert('提示','"修改信息"正在开发!');
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
				                		modpwd_win.show();
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