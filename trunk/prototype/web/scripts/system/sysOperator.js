var app = {};
Ext.onReady(function(){
	
	Ext.QuickTips.init();
		
	var limit = 20;
	var sm = new Ext.grid.CheckboxSelectionModel();
	
	Ext.form.Field.prototype.msgTarget = 'side';
	
	Ext.tree.TreePanel.prototype.getChecked = function(node){ 
		var checked = [], i; 
		var root = false; 
		if( typeof node == 'undefined' ) { 
			node = this.getRootNode(); 
			root = true; 
		} 
		if( node.attributes.checked || root) { 
			if (!root) 
			checked.push(node.id); 
			if( !node.isLeaf() ) { 
				for( i = 0; i < node.childNodes.length; i++ ) { 
				checked = checked.concat( this.getChecked(node.childNodes[i]) ); 
				}
			}
		} 
		return checked
	}
	
	var Operator =Ext.data.Record.create([
		{name:'id',mapping:'D_ID',type:'int'},
		{name:'spId',mapping:'D_SPID_ID',type:'int'},
		{name:'operatorType',mapping:'D_OPERATORTYPE_ID',type:'int'},
		{name:'loginName',mapping:'D_LOGIN_NAME',type:'string'},
		{name:'password',mapping:'D_PASSWORD',type:'string'},
		{name:'realName',mapping:'D_REAL_NAME',type:'string'},
		{name:'status',mapping:'D_STATUS',type:'int'},
		{name:'phone',mapping:'D_PHONE',type:'string'},
		{name:'mobile',mapping:'D_MOBILE',type:'string'},
		{name:'email',mapping:'D_EMAIL',type:'string'},
		{name:'fax',mapping:'D_FAX',type:'string'},
		{name:'qq',mapping:'D_QQ',type:'string'},
		{name:'msn',mapping:'D_MSN',type:'string'}
	]);

	app.data = [
		[1,'admin','123456','管理员',1,'021-52128907','15800371329','admin@ctc.com','021-52128907','262372042','admin@ctc.com',1,'SYS_OPER',1,'CHINA','2011-04-01 12:00:00'],
		[2,'bluestome','123456','曾经的流星雨',1,'021-52128907','15800371329','zhangxiao917@21cn.com','021-52128907','262372042','zhangxiao917@21cn.com',1,'SYS_OPER',1,'CHINA','2011-04-01 12:00:00'],
		[3,'user','123456','用户',1,'021-52128907','15800371329','user@ctc.com','021-52128907','262372042','user@ctc.com',1,'SYS_OPER',1,'CHINA','2011-04-01 12:00:00']
	];

//	var ds_sysOperator = new Ext.data.Store({
//		proxy : new Ext.data.HttpProxy({
//			url : 'sysOperator.action'
//		}),
//		reader : new Ext.data.JsonReader({
//			totalProperty : 'count',
//			root : 'operator'
//		}, [{
//			name : 'D_ID',
//			type : 'int'
//		}, {
//			name : 'D_LOGIN_NAME',
//			type : 'string'
//		}, {
//			name : 'D_PASSWORD',
//			type : 'string'
//		}, {
//			name : 'D_REAL_NAME',
//			type : 'string'
//		}, {
//			name : 'D_STATUS',
//			type : 'int'
//		}, {
//			name : 'D_PHONE',
//			type : 'string'
//		}, {
//			name : 'D_MOBILE',
//			type : 'string'
//		}, {
//			name : 'D_EMAIL',
//			type : 'string'
//		}, {
//			name : 'D_FAX',
//			type : 'string'
//		}, {
//			name : 'D_QQ',
//			type : 'string'
//		}, {
//			name : 'D_MSN',
//			type : 'string'
//		}, {
//			name : 'D_OPERATORTYPE_ID',
//			type : 'int'
//		}, {
//			name : 'D_OPERATORTYPE',
//			type : 'string'
//		},  {
//			name : 'D_SPID_ID',
//			type : 'int'
//		}, {
//			name : 'D_SPID',
//			type : 'string'
//		}, {
//			name : 'D_CREATE_DT',
//			type : 'string'
//		}])
//	});
	
	var ds_sysOperator = new Ext.data.ArrayStore({
        fields: [
		{
			name : 'D_ID',
			type : 'int'
		}, {
			name : 'D_LOGIN_NAME',
			type : 'string'
		}, {
			name : 'D_PASSWORD',
			type : 'string'
		}, {
			name : 'D_REAL_NAME',
			type : 'string'
		}, {
			name : 'D_STATUS',
			type : 'int'
		}, {
			name : 'D_PHONE',
			type : 'string'
		}, {
			name : 'D_MOBILE',
			type : 'string'
		}, {
			name : 'D_EMAIL',
			type : 'string'
		}, {
			name : 'D_FAX',
			type : 'string'
		}, {
			name : 'D_QQ',
			type : 'string'
		}, {
			name : 'D_MSN',
			type : 'string'
		}, {
			name : 'D_OPERATORTYPE_ID',
			type : 'int'
		}, {
			name : 'D_OPERATORTYPE',
			type : 'string'
		},  {
			name : 'D_SPID_ID',
			type : 'int'
		}, {
			name : 'D_SPID',
			type : 'string'
		}, {
			name : 'D_CREATE_DT',
			type : 'string'
		}]
    });
    
	 var cm_operator =new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),sm,
				{	
					header:"登录名", 
					dataIndex:"D_LOGIN_NAME", 
					sortable:true,
					width:120,
					menuDisabled : true,
					renderer:function(v){ 
						if( v == '') {
							return "<span style='color:red;font-weight:bold;'>暂无</span>";
						}else{
							return v;
						}
					}
				},{
					header:"真实姓名", 
					dataIndex:"D_REAL_NAME", 
					sortable:false,
					width:150,
					menuDisabled : true,
					renderer:function(v){ 
						if( v == '') {
							return "<span style='color:red;font-weight:bold;'>暂无</span>";
						}else{
							return v;
						}
					}
				},{
					header:"电邮地址", 
					dataIndex:"D_EMAIL", 
					sortable:false,
					width:200,
					menuDisabled : true,
					renderer:function(v){ 
						if( v == '') {
							return "<span style='color:red;font-weight:bold;'>暂无</span>";
						}else{
							return v;
						}
					}
				},{
					header:"创建时间", 
					dataIndex:"D_CREATE_DT", 
					sortable:true,
					width:130
				},{
					header:"用户状态", 
					dataIndex:"D_STATUS",
					width:100,
					renderer:function(v){
						var x = parseInt(v);
						switch(v){
							case 1:
								return "<span style='color:blue;font-weight:bold;'>启用</span>";
							case 2:
								return "<span style='color:yellow;font-weight:bold;'>停用</span>";
							case 3:
								return "<span style='color:red;font-weight:bold;'>删除</span>";
							default:
								return "<span style='color:red;font-weight:bold;'>未知</span>";
						}
					}
				}
	 ])
	 
	 var btn_add_operator =new Ext.Button({
	 	text:'添加',
	 	iconCls:'icon-add',
	 	handler:function(){
	 		windows_add_operator.show();
	 	}
	 });
	 
	 var btn_del_operator = new Ext.Button({
		text : '删除',
		iconCls : 'icon-del',
		handler : function() {
			var records = grid_operator.getSelectionModel().getSelections();
			if(records.length != 0 ){
				var ids = [];
				for(i = 0;i<records.length;i++){
					ids.push(records[i].get('D_ID'))
				}
				Ext.Msg.confirm('确认删除', '你确定删除该条记录?', function(btn) {
					if (btn == 'yes') {
					Ext.Msg.show({
						title : '提示',
						msg : '删除成功!',
						buttons : Ext.Msg.OK,
						icon : Ext.Msg.INFO
					});
				/**
						Ext.Ajax.request({
							url : 'deloperator.action',
							params : {
								id : ids
							},
							success : function(response,option) {
								var obj = Ext.util.JSON.decode(response.responseText);
								if(obj.success){
									Ext.MessageBox.alert('提示', obj.msg);
									ds_sysOperator.load({
										params : {
											start : 0,
											limit : limit
										}
									})						
								}else{
									Ext.Msg.show({
										title : '提示',
										msg : obj.msg,
										buttons : Ext.Msg.OK,
										icon : Ext.Msg.ERROR
									})
								}
							},
			                failure:function(response,option){
								Ext.Msg.show({
									title : '提示',
									msg : '系统错误',
									buttons : Ext.Msg.OK,
									icon : Ext.Msg.ERROR
								})
			                }
						})
				**/
					}
				});
			}else{
				Ext.Msg.show({
					title : '提示',
					msg : '请选择需要删除的记录',
					buttons : Ext.Msg.OK,
					icon : Ext.Msg.ERROR
				})
			}
		}
	});
	
	 var btn_undel_operator = new Ext.Button({
		text : '解除删除',
		iconCls : 'icon-edit',
		handler : function() {
			var records = grid_operator.getSelectionModel().getSelections();
			if(records.length != 0 ){
				var ids = [];
				for(i = 0;i<records.length;i++){
					ids.push(records[i].get('D_ID'))
				}
				Ext.Msg.confirm('确认解除删除', '你确定解除一下操作员删除状态?', function(btn) {
					if (btn == 'yes') {
							Ext.Msg.show({
								title : '提示',
								msg : '删除记录成功!',
								buttons : Ext.Msg.OK,
								icon : Ext.Msg.INFO
							});
					/**	
						Ext.Ajax.request({
							url : 'unDel.action',
							params : {
								id : ids
							},
							success : function(response,option) {
								var obj = Ext.util.JSON.decode(response.responseText);
								if(obj.success){
									Ext.MessageBox.alert('提示', obj.msg);
									ds_sysOperator.load({
										params : {
											start : 0,
											limit : limit
										}
									})						
								}else{
									Ext.Msg.show({
										title : '提示',
										msg : obj.msg,
										buttons : Ext.Msg.OK,
										icon : Ext.Msg.ERROR
									})
								}
							},
			                failure:function(response,option){
								Ext.Msg.show({
									title : '提示',
									msg : '系统错误',
									buttons : Ext.Msg.OK,
									icon : Ext.Msg.ERROR
								})
			                }
						});
					**/	
					}
				})
			}else{
				Ext.Msg.show({
					title : '提示',
					msg : '请选择需要删除的记录',
					buttons : Ext.Msg.OK,
					icon : Ext.Msg.ERROR
				})
			}
		}
	});
	
	var text_search_operator = new Ext.form.TextField({
		id:'textSearchoperator_01',
		name : 'textSearchoperator',
		emptyText : '请输入查询的条件!',
		vtype:'search',
		listeners : {
			'specialkey' : function(field, e) {
				if (e.getKey() == Ext.EventObject.ENTER) {
					ds_sysOperator.loadData(app.data);
//					searchoperator();
				}
			}
		}
	});
	
	var searchoperator = function() {
			var colname = search_comb_queyrCol_operator.getValue();
			var values = text_search_operator.getValue();
				Ext.Ajax.request({
					url : 'sysOperator.action',
					params : {
						colName : colname,
						value :  values
					},
					success:function(response,option){
						var obj = Ext.util.JSON.decode(response.responseText);
						if(obj.success){
							ds_sysOperator.load({
								params : 
								{
									start : 0,
									limit : limit
								}
							})
						}else{
							if(obj.msg == null || obj.msg == ''){
								Ext.Msg.show({
									title : '提示',
									msg : '查询数据失败',
									buttons : Ext.Msg.OK,
									icon : Ext.Msg.ERROR
								})
							}else{
								Ext.Msg.show({
									title : '提示',
									msg : obj.msg,
									buttons : Ext.Msg.OK,
									icon : Ext.Msg.ERROR
								})
							}
						}
					},
	                failure:function(response,option){
							Ext.Msg.show({
								title : '提示',
								msg : '系统错误',
								buttons : Ext.Msg.OK,
								icon : Ext.Msg.ERROR
							})
	                }
				})
	}
	
	var search_comb_queyrCol_operator = new Ext.form.ComboBox({
				fieldLabel : '查询条件',
				id : 'column_operator',
				hiddenName : 'colName',
				valueField : 'id',
				displayField : 'name',
				mode:'local',
				store : new Ext.data.SimpleStore({
					data : [
							['loginName', '登录名'],
							['realName', '真实姓名'],
						    ['email', '电子邮件']
				    ],
					fields : ['id', 'name']
				}),	
				selectOnFocus : true,
				editable : false,
				allowBlank : true,
				triggerAction : 'all',
				emptyText : '查询的字段名'
	});
	
	var btn_search_operator = new Ext.Button({
		text : '查询',
		iconCls : 'icon-search',
		handler : function(){
			ds_sysOperator.loadData(app.data);
		}
	});
	
	var btn_show_oper_detail = new Ext.Button({
		text : '查看详情',
		iconCls : 'icon-edit',
		handler : function() {
			if(grid_operator.getSelectionModel().getSelected()){
				var record = grid_operator.getSelectionModel().getSelected();
				new Ext.Window({ 
					title:'查看系统操作员详情',
					id:'show_operator_detail_window',
					width : 350,
					resizable : false,
					autoHeight : true,
					modal : true,
					closable:false,
					items : [new Ext.FormPanel({
						labelWidth : 70,
						url : 'updateoperator.action',
						border : false,
						baseCls : 'x-plain',
						bodyStyle : 'padding:5px 5px 0',
						defaultType : 'textfield',
						defaults:{
							anchor : '80%',
							msgTarget:'side'
						},
						items : [
						{ 
							fieldLabel : '系统流水号',
							xtype:'hidden',
							value : record.get('D_ID'),
							readOnly : true
						},
						{
							fieldLabel : '登录名',
							value : record.get('D_LOGIN_NAME'),
							readOnly : true
						},{
							fieldLabel : '密码',	
							xtype:'hidden',
							value:record.get('D_PASSWORD'),
							readOnly : true
						},{
							fieldLabel : '真实姓名',
							value : record.get('D_REAL_NAME'),
							readOnly : true
						},{
							fieldLabel : '操作员类型',
							value:'系统操作员',
							readOnly:true
						},{
							fieldLabel : '固定电话',		
							value:record.get('D_PHONE'),
							readOnly : true
						},{ 
							fieldLabel : '手机号码',		
							value:record.get('D_MOBILE'),
							readOnly : true
						},{
							fieldLabel : '电子邮件',		
							value:record.get('D_EMAIL'),
							readOnly : true
						},{
							fieldLabel : '传真号码',		
							value:record.get('D_FAX'),
							readOnly : true
						},{
							fieldLabel : '时间',
							readOnly : true,
							value:record.get('D_CREATE_DT')
						}, {
							fieldLabel : '状态',
							readOnly:true,
							value:(record.get('D_STATUS') == 1?'启用':'停用')
						},{
							fieldLabel : '所属部门',
							xtype:'combo',
							hiddenName : 'operator.district',
							valueField : 'id',
							displayField : 'name',
							mode:'local',
							store : new Ext.data.SimpleStore({
								data : [
										['1','机关'],
										['2', '营业厅']
							    ],
								fields : ['id', 'name']
							}),	
							selectOnFocus : true,
							editable:false,
							allowBlank : false,
							triggerAction : 'all',
							emptyText : '当前所属:"机关"'
						},{
							fieldLabel:'选择角色',
							name:'role_id',
							xtype:'checkbox',
							handler:function(v){
								if(v.getValue()){
									Ext.Msg.show({
										title : '提示',
										msg : '角色已启用',
										buttons : Ext.Msg.OK,
										icon : Ext.Msg.INFO
									});
									/**
									new Ext.Window({
										id:'updateOperatorRole',
										title:'角色窗口',
										width : 350,
										resizable : false,
										autoHeight : true,
										modal : true,
										closable:false,
										items:[
										new Ext.FormPanel({
											id:'tree_id',
											border:false,
											autoWidth:true,
											autoHeight:true,
											baseCls:'x-plain',
											bodyStyle:'pding:5px 5px 0',
											labelAlign:'left',
											labelWidth:70,
											defaults:{anchor:'80%',msgTarget : 'side'},
											defaultType:'textfield',
											items:[
												{ 
													fieldLabel:'角色列表',
													id:'roleList',
													name:'tree',
													xtype:'treepanel',
												    animate: false,   
												    rootVisible: true,   
												    autoScroll:true,   
													loader: new Ext.tree.TreeLoader({   
												        url:project+'/roleList.action?operatorId='+record.get('D_ID'),   //jsonmenu.action
												        baseAttrs: { 
												        	uiProvider: Ext.ux.TreeCheckNodeUI 
												    	} //添加 uiProvider 属性   
												    }),
											 	    root: new Ext.tree.AsyncTreeNode({id:'0',text:'角色列表'})
												}
											],
											buttons:[
											{
												text : '关闭',
												handler : function() {
													Ext.getCmp('updateOperatorRole').close();
												}
											}]								
										})
										]
									}).show();
									**/
								}
							}
						}],
						buttonAlign : 'right',
						minButtonWidth : 60,
						buttons : [
						{
							text : '关闭',
							handler : function() {
								Ext.getCmp('show_operator_detail_window').close();
							}
						}]
					})]
				}).show()
			}else{
				Ext.Msg.show({
					title : '提示',
					msg : '请选择一条你需要查看的记录',
					buttons : Ext.Msg.OK,
					icon : Ext.Msg.ERROR
				});
			}
		}
	});
	
	var update_operator_btn = new Ext.Button({  
		text: '修改操作员',
		iconCls:'icon-edit',
		handler:function(){
			if(grid_operator.getSelectionModel().getSelected()){
			var record = grid_operator.getSelectionModel().getSelected();
			/**
				Ext.Msg.show({
					title : '提示',
					msg : '选择修改"'+record.get('D_LOGIN_NAME')+'"记录!',
					buttons : Ext.Msg.OK,
					fn:function(){
						
					},
					icon : Ext.Msg.INFO
				});
			**/ 
			new Ext.Window({ 
					id:'update_operator_window',
					title : '更新操作员',
					width : 350,
					resizable : false,
					autoHeight : true,
					modal : true,
					closable:false,
					items : [new Ext.FormPanel({
						id:'update_operator_form',
						labelWidth : 70,
						labelAlign : 'right',
						url : 'updateoperator.action',
						border : false,
						baseCls : 'x-plain',
						bodyStyle : 'pding:5px 5px 0',
						defaultType : 'textfield',
						defaults: {
	                        anchor: '80%',
	                        msgTarget:'side'
	                    },
	                    items : [
						{ 
							fieldLabel : '系统流水号',
							xtype:'hidden',
							id : 'id',
							name : 'operator.id',
							readOnly : true,
							value : record.get('D_ID')
						},
						{
							fieldLabel : '登录名',
							name : 'operator.loginName',
							readOnly : true,
							value : record.get('D_LOGIN_NAME'),
							vtype:'alphanum'
							
						}, {
							fieldLabel : '密码',	
							xtype:'hidden',
							name : 'operator.password',
							readOnly:true,
							value:record.get('D_PASSWORD')
						},{
							fieldLabel : '真实姓名',
							name : 'operator.realName',
							value : record.get('D_REAL_NAME'),
							vtype:'chinese'
						},{
							field:'操作员类型',
							name:'operator.code.id',
							xtype:'hidden',
							value:record.get('D_OPERATORTYPE_ID')
						},{
							fieldLabel : '固定电话',		
							name : 'operator.phone',
							value:record.get('D_PHONE'),
							vtype:'phone'
						},{ 
							fieldLabel : '手机号码',		
							name : 'operator.mobile',
							value:record.get('D_MOBILE'),
							vtype:'mobile'
						},{
							fieldLabel : '电子邮件',		
							name : 'operator.email',
							value:record.get('D_EMAIL'),
							vtype:'email'
						},{
							fieldLabel : '传真号码',		
							name : 'operator.fax',
							value:record.get('D_FAX'),
							vtype:'phone'
						},{
							fieldLabel : '时间',
							xtype:'hidden',
							name : 'operator.createDt',
							readOnly : true,
							value:record.get('D_CREATE_DT')
						},{
							fieldLabel : '状态',
							xtype:'combo',
							id:'sysOperator_update_status',
							hiddenName : 'operator.status',
							valueField : 'id',
							displayField : 'name',
							mode:'local',
							store : new Ext.data.SimpleStore({
								data : [
										['1','启用'],
										['2', '停用']
							    ],
								fields : ['id', 'name']
							}),	
							selectOnFocus : true,
							editable:false,
							allowBlank : false,
							triggerAction : 'all',
							emptyText : '当前状态为:' + (record.get('D_STATUS') == 1 ? '启用':'停用')
						},{
							fieldLabel : '所属部门',
							xtype:'combo',
							id:'update_operator_district',
							hiddenName : 'operator.district',
							valueField : 'id',
							displayField : 'name',
							mode:'local',
							store : new Ext.data.SimpleStore({
								data : [
										['1','机关'],
										['2', '营业厅']
							    ],
								fields : ['id', 'name']
							}),	
							selectOnFocus : true,
							editable:false,
							allowBlank : false,
							triggerAction : 'all',
							emptyText : '当前属性为:"机关"'
						},{
							fieldLabel:'SP类型',
							xtype:'hidden',
							name:'operator.spInfo.id',
							value:0
						},{
							fieldLabel:'选择角色',
							name:'role_id',
							xtype:'checkbox',
							handler:function(v){
								if(v.getValue()){
									Ext.Msg.show({
										title : '提示',
										msg : '角色已启用',
										buttons : Ext.Msg.OK,
										icon : Ext.Msg.INFO
									});
								}
								/**
								if(v.getValue()){
									new Ext.Window({
										id:'updateSysOperWin',
										title:'角色窗口',
										width:400,
										autoHeight:true,
										closable:false,
										items:[
											new Ext.FormPanel({
													id:'roleListForm',
													border:false,
													autoWidth:true,
													autoHeight:true,
													baseCls:'x-plain',
													bodyStyle:'pding:5px 5px 0',
													labelAlign:'right',
													labelWidth:70,
													defaults:{anchor:'80%',msgTarget : 'side'},
													defaultType:'textfield',
													url:'addoperatorrole.action',
													id:'tree_id',
													text:'属性面板',
													items:[
														{ 
															fieldLabel:'角色列表',
															id:'roleList',
															name:'tree',
															xtype:'treepanel',
															useArrows:true,
														    animate: false,   
														    rootVisible: true,   
														    autoScroll:true,   
															loader: new Ext.tree.TreeLoader({   
														        url:project+'/roleList.action?operatorId='+record.get('D_ID'),   //jsonmenu.action
														        baseAttrs: { 
														        	uiProvider: Ext.ux.TreeCheckNodeUI 
														    	} //添加 uiProvider 属性   
														    }),
													 	    root: new Ext.tree.AsyncTreeNode({
													 	    	id:'0',
													 	    	text:'角色列表',
													 	    	expend:true
												 	    	})
														},{
															fieldLabel:'操作员ID',
															name:'operator_id',
															xtype:'hidden',
															value:record.get('D_ID')
														}
													],
													buttons:[{
													  text:'添加',
													  handler:function(btn){
													  	var tree = Ext.getCmp('roleList');
													  	var ids = tree.getChecked();
													  	var frm = this.ownerCt.form;
													  	var opid = frm.findField('operator_id').getValue();  //操作员ID
													  	if(ids == null || ids == ''){
															Ext.Msg.show({
																title : '提示',
																msg : '请先选择角色',
																buttons : Ext.Msg.OK,
																icon : Ext.Msg.ERROR
															})
													  	}else{
														  	if(frm.isValid()){
															  		frm.submit({
															  			params:{
															  				operatorId:opid,
															  				id:ids
															  			},
															  			success:function(form,action){
															  				Ext.MessageBox.alert('提示',action.result.msg);
																			ds_sysOperator.load({params : {start : 0,limit : limit}});
																			var updateSysOperWin =  Ext.getCmp('updateSysOperWin');
																			updateSysOperWin.close();
															  			},
															  			failure:function(form,action){
																			Ext.Msg.show({
																				title : '提示',
																				msg : action.result.msg,
																				buttons : Ext.Msg.OK,
																				icon : Ext.Msg.ERROR
																			});
															  			}
															  		})
															  	}
													  }
													  }
													},{
														text : '重置',
														handler : function() {
															Ext.getCmp('roleListForm').form.reset();
														}
													}, {
														text : '取消',
														handler : function() {
															Ext.getCmp('roleListForm').form.reset();
															var updateSysOperWin =  Ext.getCmp('updateSysOperWin');
															updateSysOperWin.close();
														}
													}]
											})   
											
										]
									}).show()
								}
								**/
							}
						}],
						buttonAlign : 'right',
						minButtonWidth : 60,
						buttons : [{
							text : '更新',
							handler : function(btn) {
								var frm = Ext.getCmp('update_operator_form').form;
								if (frm.isValid()) {
										btn.disable();
										Ext.Msg.show({
											title : '提示',
											msg : '更新记录成功!',
											buttons : Ext.Msg.OK,
											fn : function() {
												dnfield.focus(true);
												btn.enable();
											},
											icon : Ext.Msg.INFO
										});
									 /**
										frm.submit({
											waitTitle : '请稍候',
											waitMsg : '正在提交表单数据,请稍候...',
											success : function(form,action) {
												Ext.MessageBox.alert("提示", action.result.msg);
												btn.enable();
												ds_sysOperator.load({params : {start : 0,limit : limit}});
												//添加窗口关闭动作
												var updateOperatorWin = Ext.getCmp('update_operator_window');
												updateOperatorWin.close();
											},
											failure : function(form,action) {
												Ext.Msg.show({
													title : '错误提示',
													msg : action.result.msg,
													buttons : Ext.Msg.OK,
													fn : function() {
														dnfield.focus(true);
														btn.enable();
													},
													icon : Ext.Msg.ERROR
												});
											}
										});
									**/
								}
							}
						}, {
							text : '重置',
							handler : function() {
								Ext.getCmp('update_operator_form').form.reset();
							}
						}, {
							text : '取消',
							handler : function() {
								Ext.getCmp('update_operator_form').form.reset();
								Ext.getCmp('update_operator_window').close();
							}
						}]
					})]
				
			}).show()
		}else{
			Ext.Msg.show({
				title : '提示',
				msg : '请选择要修改的记录!!!',
				buttons : Ext.Msg.OK,
				icon : Ext.Msg.ERROR
			})
		}
		}
	});
	
	 var windows_add_operator = new Ext.Window({
	 	id:'windows_add_operator',
	 	title:'添加',
		width : 350,
		height : 440,
		resizable : false,
		autoHeight : true,
		modal : true,
		closable:false,
		items:[new Ext.FormPanel({
			id:'add_operator_form',
			border:false,
			baseCls:'x-plain',
			bodyStyle:'pding:5px 5px 0',
			labelAlign:'right',
			labelWidth:70,
			frame: true,
			autoWidth : true,
			url:'addoperator.action',
			defaults:{anchor:'80%',msgTarget : 'side'},
			defaultType:'textfield',
			items:[{	
				fieldLabel : '登录名',
				id:'operator.loginName',
				name:'operator.loginName',
				allowBlank:false,
				vtype:'alphanum',
				maxLength:20
			},{	
				fieldLabel:'登陆密码',
				id:'pass',
				name:'operator.password',
				vtype:'safe',
				inputType:'password',
				allowBlank:false,
				maxLength:20
			},{
				fieldLabel:'确认密码',
				inputType:'password',
				name:'repwd',
	            inputType: 'password',
	            vtype:'password',
	            initialPassField: 'pass', // 要比较的另外一个的组件的id
	            allowBlank: false, //false则不能为空，默认为true
	            maxLength:20
			},{
				fieldLabel:'SP类型',
				xtype:'hidden',
				name:'operator.spInfo.id',
				value:0
			},{
				field:'操作员类型',
				name:'operator.code.id',
				xtype:'hidden',
				value:'1002'
			},{
				fieldLabel:'真实姓名',
				name:'operator.realName',
				allowBlank:true,
				vtype:'chinese',
				maxLength:50
			},{
				fieldLabel:'操作员状态',
				xtype:'combo',
				id:'sysOperator_add_status',
				hiddenName:'operator.status',
				valueField:'id',
				displayField:'name',
				mode:'local',
				store:new Ext.data.SimpleStore({
					data:[
						['1','启用'],
						['2','停用']
						],
					fields:['id','name']
				}),
				selectOnFocus:true,
				editable:false,
				allowBlank:false,
				triggerAction:'all',
				loingText:'加载中...',
				emptyText:'基本状态:1:启用,2:停用',
				maxLength:2
			},{
				fieldLabel:'固定电话',
				name:'operator.phone',
				allowBlank:true,
				vtype:'phone',
				maxLength:50
			},{
				fieldLabel:'手机号码',
				name:'operator.mobile',
				allowBlank:true,
				vtype:'mobile',
				maxLength:50
			},{
				fieldLabel:'电子邮箱',
				name:'operator.email',
				vtype:'email',
				allowBlank:false,
				maxLength:100
			},{
				fieldLabel:'传真号码',
				name:'operator.fax',
				allowBlank:true,
				vtype:'phone',
				maxLength:50
			},{
				fieldLabel : '所属部门',
				xtype:'combo',
				id:'detail_operator_district',
				hiddenName : 'operator.district',
				valueField : 'id',
				displayField : 'name',
				mode:'local',
				store : new Ext.data.SimpleStore({
					data : [
							['1','机关'],
							['2', '营业厅']
				    ],
					fields : ['id', 'name']
				}),	
				selectOnFocus : true,
				editable:false,
				allowBlank : false,
				triggerAction : 'all',
				emptyText : '请选择所属部门!'
			}],
			buttonAlign:'right',
			minButtonWidth:60,
			buttons:[{
			  text:'添加',
			  handler:function(btn){
			  	var frm =Ext.getCmp('add_operator_form').form;
			  	if(frm.isValid()){
						Ext.Msg.show({
							title : '提示',
							msg : '添加操作员成功!',
							buttons : Ext.Msg.OK,
							icon : Ext.Msg.INFO
						});
			  		/**
			  		btn.disable();
			  		var unfield=frm.findField('operator.loginName');
					Ext.Ajax.request({
							   url: 'addoperator.action',
								params: {
										"operator.status":frm.findField('operator.status').getValue(),
										"operator.phone":frm.findField('operator.phone').getValue(),
										"operator.mobile":frm.findField('operator.mobile').getValue(),
										"operator.email":frm.findField('operator.email').getValue(),
										"operator.fax":frm.findField('operator.fax').getValue(),
										"operator.qq":frm.findField('operator.qq').getValue(),
										"operator.msn":frm.findField('operator.msn').getValue(),
										"operator.realName":frm.findField('operator.realName').getValue(),
										"operator.code.id":frm.findField('operator.code.id').getValue(),
										"operator.spInfo.id":frm.findField('operator.spInfo.id').getValue(),
										"operator.password":frm.findField('operator.password').getValue(),
										"operator.loginName":frm.findField('operator.loginName').getValue()
						         },
							   success: function(response,options)
								{
							  		btn.enable();
									var obj = Ext.util.JSON.decode(response.responseText);
									if(obj.success){
									windows_add_operator.close();
									var opId = obj.msg;
									new Ext.Window({
										id:'addOperatorRole',
										title:'角色窗口',
										width : 350,
										resizable : false,
										autoHeight : true,
										modal : true,
										closable:false,
										items:[
											new Ext.FormPanel({
												id:'tree_id',
												border:false,
												autoWidth:true,
												autoHeight:true,
												baseCls:'x-plain',
												bodyStyle:'pding:5px 5px 0',
												labelAlign:'right',
												labelWidth:70,
												defaults:{anchor:'80%',msgTarget : 'side'},
												defaultType:'textfield',
												url:'addoperatorrole.action',
												items:[
													{ 
														fieldLabel:'角色列表',
														id:'roleList',
														name:'role_tree_list',
														xtype:'treepanel',
													    autoScroll:true,   
														loader: new Ext.tree.TreeLoader({   
													        url:project+'/roleList.action?operatorId='+opId,   //jsonmenu.action
													        baseAttrs: { 
													        	uiProvider: Ext.ux.TreeCheckNodeUI 
													    	} //添加 uiProvider 属性   
													    }),
												 	    root: new Ext.tree.AsyncTreeNode({
												 	    	id:'0',
												 	    	text:'角色列表'
											 	    	})
													}
												],
												buttons:[{
												  text:'添加',
												  handler:function(btn){
												  	var tree = Ext.getCmp('roleList');
												  	var ids = tree.getChecked();
												  	var frms = this.ownerCt.form;
												  	if(ids == null || ids == ''){
														Ext.Msg.show({
															title : '提示',
															msg : '请先选择角色!',
															buttons : Ext.Msg.OK,
															icon : Ext.Msg.ERROR
														})
												  	}else{
												  		frms.submit({
												  			params:{
												  				operatorId:opId,
												  				id:ids
												  			},
												  			success:function(form,action){
												  				Ext.MessageBox.alert('提示',action.result.msg);
												  				var currWin = Ext.getCmp('addOperatorRole');
												  				currWin.close();
																ds_sysOperator.load({
																	params : {
																		start : 0,
																		limit : limit
																	}
																})
												  			},
												  			failure:function(form,action){
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
												},{
													text : '重置',
													handler : function() {
														Ext.getCmp('tree_id').form.reset();
													}
												}, {
													text : '取消',
													handler : function() {
														Ext.getCmp('tree_id').form.reset();
														this.ownerCt.ownerCt.close();
													}
												}]								
											})
										]
								}).show()
								}else{
									Ext.Msg.show({
										title : '提示',
										msg : obj.msg,
										buttons : Ext.Msg.OK,
										icon : Ext.Msg.ERROR
									})
								}
								},
							    failure: function(){
									Ext.Msg.show({
										title : '提示',
										msg : '服务器端异常!',
										buttons : Ext.Msg.OK,
										icon : Ext.Msg.ERROR
									})
								}
					});
					**/
			  	}
			  }
			},{
				text : '重置',
				handler : function() {
					Ext.getCmp('add_operator_form').form.reset();
				}
			}, {
				text : '取消',
				handler : function() {
					windows_add_operator.hide();
					Ext.getCmp('add_operator_form').form.reset();
				}
			}]
		})]
		
	});
	
	//ds_sysOperator.load({
	//	params : {
	//		value : '',
	//		colName : '',
	//		start : 0,
	//		limit : limit
	//	}
	//})
		
//	ds_sysOperator.loadData(data);
	
	var ptb = new Ext.PagingToolbar({ 
		pageSize:limit,
		store:ds_sysOperator,
		displayInfo:true,
		displayMsg:'第 {0} - {1} 条  共 {2} 条',
		emptyMsg:'没有记录'
	});
	
	var grid_operator = new Ext.grid.GridPanel({
		title : '系统操作员管理',
		width : 800,
		autoHeight : true,
		iconCls : 'icon-plugin',
		region : 'center',
		loadMask : {
			msg : '数据加载中...'
		},
		cm : cm_operator,
		ds : ds_sysOperator,
		sm : sm,
	    tbar : [btn_add_operator, '-', btn_undel_operator,'-',btn_del_operator,'-',update_operator_btn,'-',btn_show_oper_detail,'-',search_comb_queyrCol_operator,'-',
				text_search_operator, btn_search_operator],
		bbar : ptb
	});
	
	grid_operator.render('sysOperator')

})