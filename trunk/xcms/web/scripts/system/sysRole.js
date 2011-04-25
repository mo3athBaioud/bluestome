var app = {};
Ext.onReady(function(){
  	Ext.BLANK_IMAGE_URL = '/resource/image/ext/s.gif';
	var rolePtb;	
	var roleSysMenuPtb;
	var limit = 20;
	var sm = new Ext.grid.CheckboxSelectionModel();
	
	var Role = Ext.data.Record.create([
	{name:'id',mapping:'D_ID',type:'int'},
	{name:'roleName',mapping:'D_ROLE_NAME',type:'string'},
	{name:'roleDesc',mapping:'D_ROLE_DESC',type:'string'},
	{name:'roleStatus',mapping:'D_ROLE_STATUS',type:'int'}
	]);
	
	var RoleSysMenu = Ext.data.Record.create([
	{name:'id',mapping:'D_ID',type:'int'},
	{name:'roleSysMenu.role.id',mapping:'D_ROLEID',type:'int'},
	{name:'roleSysMenu.sysMenu.id',mapping:'D_SYSMENUID',type:'int'}
	]);
	
	app.roledata = [
		[1,'系统角色','系统管理员',1,'2011-04-02 12:00:01'],
		[2,'机关角色','机关用户',1,'2011-04-02 12:00:01'],
		[3,'营业厅角色','营业厅用户',1,'2011-04-02 12:00:01']
	];
	
	/**
	var ds_role = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : 'role.action'
		}),
		reader : new Ext.data.JsonReader({
			totalProperty : 'count',
			root : 'role'
		}, [{
			name : 'D_ID',
			type : 'int'
		}, {
			name : 'D_ROLE_NAME',
			type : 'string'
		}, {
			name : 'D_ROLE_DESC',
			type : 'string'
		}, {
			name : 'D_ROLE_STATUS',
			type : 'int'
		}, {
			name : 'D_CREATE_DT',
			type : 'string'
		}])
	})
	**/
	
	var ds_role = new Ext.data.ArrayStore({
        fields: [
		{
			name : 'D_ID',
			type : 'int'
		}, {
			name : 'D_ROLE_NAME',
			type : 'string'
		}, {
			name : 'D_ROLE_DESC',
			type : 'string'
		}, {
			name : 'D_ROLE_STATUS',
			type : 'int'
		}, {
			name : 'D_CREATE_DT',
			type : 'string'
		}]
    });
	
	
	
	
	
	
	app.rolesysmenudata = [
		[1,1,1,'2011-04-02 12:00:01'],
		[2,2,1,'2011-04-02 12:00:01'],
		[3,3,1,'2011-04-02 12:00:01']
	];
	
	/**
	var ds_rolesysmenu = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : 'roleSysMenu.action'
		}),
		reader : new Ext.data.JsonReader({
			totalProperty : 'count',
			root : 'roleSysMenu'
		}, [{
			name : 'D_ID',
			type : 'int'
		}, {
			name : 'D_ROLEID',
			type : 'int'
		}, {
			name : 'D_SYSMENUID',
			type : 'int'
		}, {
			name : 'D_CREATE_DT',
			type : 'int'
		}])
	});
	**/
	
	var ds_rolesysmenu = new Ext.data.ArrayStore({
        fields: [
		{
			name : 'D_ID',
			type : 'int'
		}, {
			name : 'D_ROLEID',
			type : 'int'
		}, {
			name : 'D_SYSMENUID',
			type : 'int'
		}, {
			name : 'D_CREATE_DT',
			type : 'int'
		}]
    });
	
	 var cm_role =new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),sm,
	//            {header:"角色ID", dataIndex:"D_ID", sortable:true,width:100,menuDisabled : true},
	            {header:"角色名称", dataIndex:"D_ROLE_NAME", sortable:true,width:100,menuDisabled : true},
				{header:"角色描述", dataIndex:"D_ROLE_DESC", sortable:true,width:150,menuDisabled : true},
				{header:"角色状态", dataIndex:"D_ROLE_STATUS", sortable:true,width:100,menuDisabled : true,renderer : function(v) {
					var x = parseInt(v);
					switch(x) {
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
				},
				{header:"角色创建时间", dataIndex:"D_CREATE_DT", sortable:true,width:130}
	 ])
	 
	 
	  var cm_roleprivilegeCM =new Ext.grid.ColumnModel([
	            {header:"权限名称", dataIndex:"D_OBJECTNAME", sortable:false,width:80,menuDisabled : true},
	            {header:"查询", dataIndex:"D_SELECT", sortable:false,width:60,menuDisabled : true},
				{header:"删除", dataIndex:"D_DELETE", sortable:false,width:60,menuDisabled : true},
				{header:"修改", dataIndex:"D_UPDATE", sortable:false,width:60,menuDisabled : true},
				{header:"新建", dataIndex:"D_INSERT", sortable:false,width:60,menuDisabled : true}
	 ])
	 
	 
	var roleadd = new Ext.FormPanel({
			id:'role_form_add', 
			border:false,
			baseCls:'x-plain',
			bodyStyle:'padding:5px 5px 0',
			labelAlign:'right',
			labelWidth:70,
			anchor : '100%',
			url:'addrole.action',
			defaults:{anchor:'94%',msgTarget : 'side'},
			defaultType:'textfield',
			items : [
			//role.roleName Action中Bean的名称和bean的属性,此处的name与下文中提交数据的store中的name对应
			{fieldLabel : '角色名称',id:'role.roleName',name : 'role.roleName',allowBlank:false,maxLength : 50},
			{fieldLabel : '角色描述',xtype:'textarea',name : 'role.roleDesc',	allowBlank:false,maxLength : 255},
			{	
				fieldLabel:'角色状态',
				xtype:'combo',
				id:'roleStatus',
				hiddenName:'role.roleStatus',
				valueField:'ID',
				displayField:'NAME',
				mode:'local',
				store : new Ext.data.SimpleStore({
					data : [[1, '启用'], [2, '停用']],
					fields : ['ID', 'NAME']
				}),	
				selectOnFocus:true,
				editable:false,
				allowBlank:false,
				triggerAction:'all',
				emptyText:'角色状态(启用,停用)'
			}],
			buttonAlign:'center',
			minButtonWidth:60,
			buttons:[{
			  text:'添加',
			  handler:function(btn){
			  	var frm =Ext.getCmp('role_form_add').form;
			  	if(frm.isValid()){
			  		var unfield=frm.findField('role.roleName');
					Ext.Msg.show({
						title : '提示',
						msg : '添加角色成功!',
						buttons : Ext.Msg.OK,
						icon : Ext.Msg.INFO
					});
			  		/**
			  		frm.submit({
			  				waitTitle : '请稍候',
							waitMsg : '正在提交表单数据,请稍候...',
							success : function(form, action) {
	//							var store = grid_role.getStore();
						  		btn.enable();
								Ext.MessageBox.alert("成功！", action.result.msg);
								ds_role.load({params : {start : 0,limit : limit}})							
								windows_add_role.hide();
								frm.reset();
							},
							failure : function(form,action) {
								btn.enable();
								Ext.Msg.show({
									title : '错误提示',
									msg : action.result.msg,
									buttons : Ext.Msg.OK,
									icon : Ext.Msg.ERROR
								})
							}
	//						failure : function(form,action){
	//							Ext.MessageBox.alert("提示", action.result.msg);
	//							windows_add_role.hide();
	//							frm.reset();
	//						}
			  		});
			  		**/
			  	}
			  }
			},{
				text : '重置',
				handler : function() {
					Ext.getCmp('role_form_add').form.reset();
				}
			}, {
				text : '取消',
				handler : function() {
					Ext.getCmp('role_form_add').form.reset();
					windows_add_role.hide();
				}
			}]
	}) 
	 
	var windows_add_role =new Ext.Window({
	 	title:'添加',
	 	width:320,
	 	resizable : false,
		autoHeight : true,
		modal : true,
		closable:false,
	//	closeAction : 'hide',
	//	listeners : {
	//		'hide' : function() {
	//			this.setTitle('添加角色');
	//		}
	//	},
		items:[roleadd]
		
	})
	 
	 var btn_add_role =new Ext.Button({
	 	text:'添加',
	 	iconCls:'icon-add',
	 	handler:function(){
	 	   windows_add_role.show();
	 	}
	 });
	 
	var btn_show_role_sysmenu = new Ext.Button({
	 	text:'显示角色菜单',
	 	iconCls:'icon-add',
	 	handler:function(){
	 		window.open('rolesysmenu.action')
	// 	clickEvent : 'rolesysmenu.action'
	// 		click : '/rolesysmenu.jsp';
	// 	   show_role_sysmenu_window.show();
	 	}
	})
	
	var show_role_sysmenu_window = new Ext.Window({ 
			title:'显示角色菜单',
			width:670,
			resizable : false,
			autoHeight : true,
			modal : false,
			closable:false
	//		closeAction : 'close',
	//		listeners : {
	//			'hide' : function() {
	//				this.setTitle('显示角色拥有的菜单');
	//			}
	//		}
	//		items : [show_role_sysmenu]
	})
	
	var show_role_sysmenu = new Ext.FormPanel({ 
			url : '*.action',
			labelAlign : 'left',
			labelWidth : 85,
			bodyStyle : 'padding:5px',
			border : false,
			fileUpload : true,
			baseCls : 'x-plain'
	//		items : [
	//			{
	//				fileLabel : '角色名称'				
	//			}
	//		]
	})
	
	var btn_add_role_sysmenu =new Ext.Button({
	 	text:'分配角色菜单',
	 	iconCls:'icon-add',
	 	handler:function(){
	 		if(grid_role.getSelectionModel().getSelected()){
		 		var records = grid_role.getSelectionModel().getSelected();
				var updateWin = new Ext.Window({
					id:'giveMenuPriv',
					title:'分配角色菜单',
					width:670,
					resizable : false,
					autoHeight : true,
					modal : true,
					closable:false,
	//				closeAction : 'close',
					items : [
					new Ext.FormPanel({ 
					id:'role_sysmenu_add_form',
					url : 'addRoleSysMenu.action',
					labelAlign : 'left',
					labelWidth : 85,
					bodyStyle : 'padding:5px',
					border : false,
					fileUpload : true,
					baseCls : 'x-plain',
					items : [{
						//role.roleName Action中Bean的名称和bean的属性,此处的name与下文中提交数据的store中的name对应
						layout : 'column',
						border : false,
						baseCls : 'x-plain',
						items :[{	
							columnWidth : .5,
							layout : 'form',
							baseCls : 'x-plain',
							border : false,
							defaultType : 'textfield',
							defaults : {
								anchor : '80%',
								msgTarget:'side'
							},
							items : [			
								{
									fieldLabel:'角色名称',
									id : 'roleSysMenu.role.roleName',
									name : 'roleSysMenu.role.roleName',
									editable : false,
									allowBlank:true,
									value:records.get('D_ROLE_NAME')
								}, {
									fieldLabel:'角色ID',
									xtype : 'hidden',
									hidden : true,
									id : 'roleSysMenu.role.id',
									name : 'roleSysMenu.role.id',
									value:records.get('D_ID')
								},
								]},{
									columnWidth : .5,
									layout : 'form',
									border : false,
									baseCls : 'x-plain',
									defaultType : 'textfield',
									defaults : {anchor : '95%'},
									items : [{
										fieldLabel:'菜单列表',
										xtype:'combo',
										id:'sysMenuList',
										hiddenName:'roleSysMenu.sysMenu.id',
										valueField:'D_ID',
										displayField:'D_MENU_NAME',
										mode:'remote',
										store:new Ext.data.Store({
												proxy : new Ext.data.HttpProxy({
													url : 'getRoleSysMenu.action?type='+records.get('D_ID') 
												}),
												reader : new Ext.data.JsonReader({
													root : 'sysmenu'
												}, [
													{name : 'D_ID',type : 'int'},
													{name : 'D_MENU_NAME',type : 'string'}
												])
											}),
										selectOnFocus:true,
										editable:false,
										allowBlank:false,
										triggerAction:'all',
										loadingText:'加载中...',
										emptyText:'菜单列表'
								}]						
							}],
					buttonAlign:'right',
					minButtonWidth:60,
					buttons:[{
					  text:'添加',
					  handler:function(btn){
					  	var frm = this.ownerCt.ownerCt.form;
					  	if(frm.isValid()){
					  		frm.submit({
					  				waitTitle : '请稍候',
									waitMsg : '正在提交表单数据,请稍候...',
									success : function(form, action) {
										Ext.MessageBox.alert("成功！", action.result.msg);
										btn.enable();
										ds_role.load({
											params:{
												start:0,
												limit:limit
											}
										});
										var giveMenuPriv = Ext.getCmp('giveMenuPriv');
										giveMenuPriv.hide();
										frm.reset();
									},
									failure : function(form,action) {
										Ext.Msg.show({
											title : '错误提示',
											msg : action.result.msg,
											buttons : Ext.Msg.OK,
											icon : Ext.Msg.ERROR
										})
									}
					  		});
					  	}
					  }
					},{
						text : '重置',
						handler : function() {
								Ext.getCmp('role_sysmenu_add_form').form.reset();
						}
					}, {
						text : '取消',
						handler : function() {
								var giveMenuPriv = Ext.getCmp('giveMenuPriv');
								giveMenuPriv.hide();
								Ext.getCmp('role_sysmenu_add_form').form.reset();
						}
					}]
				}]
			})]
	}).show();
	 		}else{
	 			Ext.MessageBox.alert('提示','请选择需要修改的记录！');
	 		}
	 	}
	 });
	
	 var update_role_btn = new Ext.Button({
	 	text:'修改',
	 	iconCls:'icon-edit',
	 	handler:function(){
			if (grid_role.getSelectionModel().getSelected()) {
			var record = grid_role.getSelectionModel().getSelected();
			var roleid = record.get('D_ID');
			var ds_roleprivilegestore = new Ext.data.Store({
			proxy : new Ext.data.HttpProxy({
				url : 'roleprivilegeobjects.action?roleid='+roleid
			}),
			reader : new Ext.data.JsonReader({
				totalProperty : 'count',
				root : 'roleprivileges'
			}, [{
				name : 'D_ID',
				type : 'int'
			}, {
				name : 'D_OBJECTNAME',
				type : 'string'
			}, {
				name : 'D_SELECT',
				type : 'int'
			}, {
				name : 'D_DELETE',
				type : 'int'
			}, {
				name : 'D_UPDATE',
				type : 'int'
			}, {
				name : 'D_INSERT',
				type : 'int'
			}])
		})
		
		ds_roleprivilegestore.addListener('load', function(st, rds, opts) {
		   // st 是当前的store, rds是读到的Record[], opts是store的配置
		   var tmptag = document.getElementById("privs");
		   for( var c=0; c<rds.length; c++ ) {
		   	   if(rds[c].get('D_SELECT')==1)
		   	   {
		   	   		tmptag.value = tmptag.value + ","+rds[c].get('D_ID')+"_select";
		   	   		rds[c].set('D_SELECT', "<input type=\"checkbox\" onclick=\"check(this);\" id=\""+ rds[c].get('D_ID') + "_select\" checked=\"true\" />查看");
		   	   }
		   	   else
		   	   {
		   	   		rds[c].set('D_SELECT', "<input type=\"checkbox\" onclick=\"check(this);\" id=\""+ rds[c].get('D_ID') + "_select\" />查看");
		   	   }
		   	   if(rds[c].get('D_DELETE')==1)
		   	   {
		   	   		tmptag.value = tmptag.value+ "," + rds[c].get('D_ID')+"_delete";
		   	   		rds[c].set('D_DELETE', "<input type=\"checkbox\" onclick=\"check(this);\" id=\""+ rds[c].get('D_ID') + "_delete\" checked=\"true\" />删除");
		   	   }
		   	   else
		   	   {
		   	   		rds[c].set('D_DELETE', "<input type=\"checkbox\" onclick=\"check(this);\" id=\""+ rds[c].get('D_ID') + "_delete\" />删除");
		   	   }
		   	   if(rds[c].get('D_UPDATE')==1)
		   	   {
		   	   		tmptag.value = tmptag.value+ "," + rds[c].get('D_ID')+"_update";
		   	   		rds[c].set('D_UPDATE', "<input type=\"checkbox\" onclick=\"check(this);\" id=\""+ rds[c].get('D_ID') + "_update\" checked=\"true\" />修改");
		   	   }
		   	   else
		   	   {
		   	   		rds[c].set('D_UPDATE', "<input type=\"checkbox\" onclick=\"check(this);\" id=\""+ rds[c].get('D_ID') + "_update\" />修改");
		   	   }
		   	   if(rds[c].get('D_INSERT')==1)
		   	   {
		   	   		tmptag.value = tmptag.value+ "," + rds[c].get('D_ID')+"_insert";
		   	   		rds[c].set('D_INSERT', "<input type=\"checkbox\" onclick=\"check(this);\" id=\""+ rds[c].get('D_ID') + "_insert\" checked=\"true\" />添加");
		   	   }
		   	   else
		   	   {
		   	   		rds[c].set('D_INSERT', "<input type=\"checkbox\" onclick=\"check(this);\" id=\""+ rds[c].get('D_ID') + "_insert\" />添加");
		   	   }
		      }
		})
			ds_roleprivilegestore.load();
			var window_role_update = new Ext.Window({
				id:'window_role_update',
				title:'修改',
				width : 350,
				resizable : false,
				autoHeight : true,
				modal : true,	
				closable:false,
	//			closeAction : 'close',
				items : [
						new Ext.FormPanel({
	//					id:'form_role_update',
					 	url : 'updaterole.action',
						labelAlign : 'right',
						labelWidth : 70,
						border : false,
						baseCls : 'x-plain',
						bodyStyle : 'padding:5px 5px 0',
						defaultType : 'textfield',
						defaults: {
	                        anchor: '80%', //labelWidth剩下的宽度的80%，留下20%作为后面提到的验证错误提示
	                        msgTarget:'side'
	                    },
						items : [{
									fieldLabel:'角色ID',
									xtype:'hidden',
									name : 'role.id',
									readOnly:true,
									value:record.get('D_ID')
								},{
									fieldLabel:'角色名称',
									name : 'role.roleName',
									allowBlank:false,
									value:record.get('D_ROLE_NAME')
								},{
									fieldLabel:'创建时间',
									xtype:'hidden',
									name:'role.createDt',
									value:record.get('D_CREATE_DT')
								},{
									fieldLabel:'角色描述',
									xtype:'textarea',
									name : 'role.roleDesc',
									allowBlank:false,
									value:record.get('D_ROLE_DESC')
								},{
									fieldLabel:'角色状态',
									id:'ROLE_D_STATUS',
									xtype:'combo',
									hiddenName:'role.roleStatus',
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
									selectOnFocus:true,
									editable:false,
									allowBlank:false,
									triggerAction:'all',
									loadingText:'加载中...',
									emptyText : '当前状态为:' + (record.get('D_ROLE_STATUS') == 1 ? '启用':'禁用')
								},{
									fieldLabel:'角色menu',
									id:'choosemenuids',
									name : 'choosemenuids',
									xtype : 'hidden',
									allowBlank:true
								},{
									fieldLabel:'角色privileges',
									id:'chooseprivileges',
									name : 'chooseprivileges',
									xtype : 'hidden',
									allowBlank:true
								},{
									 xtype:'tabpanel',
								     plain:true,
								     activeTab: 0,
								     height:300,
								     autoWidth:true,
								     defaults:{
								     	bodyStyle:'padding:10px',
				                        anchor: '80%', //labelWidth剩下的宽度的80%，留下20%作为后面提到的验证错误提示
				                        msgTarget:'side'
								     	},
								     
								     items:[{
								     	   title:'系统菜单',
										   id:'SysmenuTree',
									       xtype: 'treepanel',
									       checkModel: 'cascade',
									       useArrows:true,   
									       autoScroll:true,  
									       animate:false,
									       enableDD:false,
									       containerScroll: true,
									       loader: new Ext.tree.TreeLoader({
									       	   baseAttrs:{uiProvider:Ext.ux.TreeCheckNodeUI},
									           dataUrl:'getRoleSysmenuTree.action?roleid='+record.get('D_ID')
									       }),
									       root: new Ext.tree.AsyncTreeNode({
									         text: 'root',
									         draggable:false,
									         id:'root'
									       }),
									       rootVisible: false
								     },{
	            					 	title: '权限列表',
	            					 	id :'RolePrivilegeObjectList',
	            					 	xtype:'grid',	
								        useArrows:true,   
								        autoScroll:true,  
	            					 	cm:cm_roleprivilegeCM,
	            					 	store:ds_roleprivilegestore
	            					 }
								     ]
								  }],
				buttonAlign:'center',
				minButtonWidth:60,
				buttons:[{
				  text:'修改',
				  handler:function(btn){
				  	tree = Ext.getCmp('SysmenuTree');
					var str = tree.getChecked('id');
					var rolemenus = Ext.getCmp('choosemenuids');
					var roleprivis = Ext.getCmp('chooseprivileges');
					var tmpvalue = document.getElementById('privs').value;
					rolemenus.setValue(str);
					roleprivis.setValue(tmpvalue);
				  	var frm = this.ownerCt.form;
				  	if(frm.isValid()){
				  		frm.submit({
				  				waitTitle : '请稍候',
								waitMsg : '正在提交表单数据,请稍候...',
								success : function(form, action) {
									Ext.MessageBox.alert("成功！", action.result.msg);
									ds_role.load({
										params : {
											start : 0,
											limit : limit
										}
									});
		  							var tmptag2 = document.getElementById("privs");
		  							tmptag2.value = '';
									var window_role_update = Ext.getCmp('window_role_update');
									window_role_update.close();
								},
								failure : function(form,action) {
									Ext.Msg.show({
										title : '错误提示',
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
						this.ownerCt.form.reset();
					}
				}, {
					text : '取消',
					handler : function() {
						var window_role_update = Ext.getCmp('window_role_update');
						window_role_update.close();
				}
				}]
			})]
		}).show()
		} else {
			Ext.Msg.show({
				title : '提示',
				msg : '请选择要修改行!!!',
				buttons : Ext.Msg.OK,
				icon : Ext.Msg.ERROR
			})
		}
	  }
	 })
	 
	 var btn_del_role = new Ext.Button({
		text : '删除',
		iconCls : 'icon-del',
		handler : function() {
			var records = grid_role.getSelectionModel().getSelections();
			if(records.length == 0 ){
				Ext.MessageBox.alert('提示','请选择需要删除的记录');
			}else{
				var ids = [];
				for(i=0;i<records.length;i++){
					ids.push(records[i].get('D_ID'));
				}
				Ext.Msg.confirm('确认删除', '你确定删除已选择记录?', function(btn) {
					if (btn == 'yes') {
						Ext.Msg.show({
							title : '提示',
							msg : '删除成功!',
							buttons : Ext.Msg.OK,
							icon : Ext.Msg.INFO
						});
						/**
						Ext.Ajax.request({
							url : 'delrole.action',
							params : {
								id : ids
							},
							success:function(response,option){
								var obj = Ext.util.JSON.decode(response.responseText);
								if(obj.success){
									Ext.MessageBox.alert('提示', obj.msg);
									ds_role.load({params:{start:0, limit:limit}});
								}else{
									Ext.MessageBox.alert('提示', obj.msg);
								}
							},
							failure : function(form,action) {
								Ext.Msg.show({
									title : '错误提示',
									msg : '服务器内部错误',
									buttons : Ext.Msg.OK,
									icon : Ext.Msg.ERROR
								})
							}
						});
						**/
					}
				});
			}
		}
	})
	
	 var btn_undel_role = new Ext.Button({
		text : '解除删除',
		iconCls : 'icon-edit',
		handler : function() {
			var records = grid_role.getSelectionModel().getSelections();
			if(records.length == 0 ){
				Ext.MessageBox.alert('提示','请选择需要解除删除的记录');
			}else{
				var ids = [];
				for(i=0;i<records.length;i++){
					ids.push(records[i].get('D_ID'));
				}
				Ext.Msg.confirm('确认解除删除', '你确定解除删除已选择记录?', function(btn) {
					if (btn == 'yes') {
							Ext.Msg.show({
								title : '提示',
								msg : '解除删除成功',
								buttons : Ext.Msg.OK,
								icon : Ext.Msg.INFO
							});
						/**
						Ext.Ajax.request({
							url : 'unDelRole.action',
							params : {
								id : ids
							},
							success:function(response,option){
								var obj = Ext.util.JSON.decode(response.responseText);
								if(obj.success){
									Ext.MessageBox.alert('提示', obj.msg);
									ds_role.load({params:{start:0, limit:limit}});
								}else{
									Ext.Msg.show({
										title : '错误提示',
										msg : obj.msg,
										buttons : Ext.Msg.OK,
										icon : Ext.Msg.ERROR
									})
								}
							},
							failure : function(form,action) {
								Ext.Msg.show({
									title : '错误提示',
									msg : '服务器内部错误',
									buttons : Ext.Msg.OK,
									icon : Ext.Msg.ERROR
								})
							}
						});
						**/
					}
				})
			}
		}
	})
	
	var text_search_role = new Ext.form.TextField({
		name : 'textSearchrole',
		width : 200,
		emptyText : '请输入角色名称!',
		listeners : {
			'specialkey' : function(field, e) {
				if (e.getKey() == Ext.EventObject.ENTER) {
//					searchrole();
//					Ext.MessageBox.alert('系统提示','显示查询结果');
					ds_role.loadData(app.roledata);
				}
			}
		}
	})
	
	var search_comb_queyrCol_role = new Ext.form.ComboBox({
				fieldLabel : '查询条件',
				id : 'column',
				hiddenName : 'colName',
				valueField : 'id',
				displayField : 'name',
				mode:'local',
				store : new Ext.data.SimpleStore({
					data : [
	//						['id','角色ID'],
							['roleName', '角色名称'],
							['roleDesc', '角色描述']
				    ],
					fields : ['id', 'name']
				}),	
				selectOnFocus : true,
				editable : false,
				allowBlank : true,
				triggerAction : 'all',
				emptyText : '请选择查询的字段'
	})
	
	 var searchrole = function() {
			var value =text_search_role.getValue();
			var colName = search_comb_queyrCol_role.getValue();
				Ext.Ajax.request({
					url : 'role.action',
					params : {
						value : value,
						colName : colName
					},
					success:function(response,option){
						var obj = Ext.util.JSON.decode(response.responseText);
						if(obj.success){
							ds_role.load({params : {start : 0,limit : limit}});
						}else{
							Ext.MessageBox.alert('提示',obj.msg);
						}
					},
					failure : function(form,action) {
						Ext.Msg.show({
							title : '错误提示',
							msg : '服务器端错误',
							buttons : Ext.Msg.OK,
							icon : Ext.Msg.ERROR
						})
					}
				})
	}
	 
	var btn_search_role = new Ext.Button({
		text : '查询',
		iconCls : 'icon-search',
		handler : function(){
//			searchrole
//			Ext.MessageBox.alert('系统提示','显示查询结果');
			ds_role.loadData(app.roledata);
		}
	})
	
	/** 载入数据 **/ 
//	ds_role.load({
//		params : {
//			value : '',
//			colName : '',
//			start : 0,
//			limit : limit
//		}
//	})
	
	rolePtb = new Ext.PagingToolbar({ 
		pageSize:limit,
		store:ds_role,
		displayInfo:true,
		displayMsg:'第 {0} - {1} 条  共 {2} 条',
		emptyMsg:'没有记录'
	})
	
	roleSysMenuPtb = new Ext.PagingToolbar({ 
		pageSize:limit,
		store:ds_rolesysmenu,
		displayInfo:true,
		displayMsg:'第 {0} - {1} 条  共 {2} 条',
		emptyMsg:'没有记录'
	})
	
	var grid_role = new Ext.grid.GridPanel({
		title : '角色管理',
		iconCls : 'icon-plugin',
		region : 'center',
		loadMask : {
			msg : '数据加载中...'
		},
		height:500,
		autoScroll : true,
//		autoHeight : true,
//		width:800,
		cm : cm_role,
		ds : ds_role,
		sm : sm,
		tbar : [btn_add_role, '-', btn_undel_role,'-',btn_del_role, '-',
				update_role_btn,'-', search_comb_queyrCol_role,'-',
				text_search_role, btn_search_role], //btn_add_role_sysmenu,'-',btn_show_role_sysmenu,'-',
		bbar : rolePtb
	});
	
	var grid_rolesysmenu = new Ext.grid.GridPanel({  
		title : '角色菜单管理',
		iconCls : 'icon-plugin',
		region : 'center',
		loadMask : {
			msg : '数据加载中...'
		},
		autoHeight : true,
		autoWidth  : true,
		ds : ds_rolesysmenu,
		sm : sm,
		bbar:roleSysMenuPtb
	
	})
	
	
	grid_role.render('role')
})
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 