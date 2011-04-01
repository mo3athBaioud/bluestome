Ext.onReady(function(){
	
var limit = 20;
var sm = new Ext.grid.CheckboxSelectionModel();
var SysMenu =Ext.data.Record.create([
{name:'menuId',mapping:'D_MENU_ID',type:'string'},
{name:'parentMenuId',mapping:'D_PARENT_MENU_ID',type:'string'},
{name:'menuName',mapping:'D_MENU_NAME',type:'string'},
{name:'menuUrl',mapping:'D_MENU_URL',type:'string'},
{name:'visitName',mapping:'D_VISIT_NAME',type:'string'},
{name:'status',mapping:'D_STATUS',type:'int'},
{name:'isDel',mapping:'D_ISDEL',type:'int'},
{name:'createDt',mapping:'D_CREATE_DT',type:'string'}
]);

var ds_sysmenu = new Ext.data.Store({
	proxy : new Ext.data.HttpProxy({
		url : 'sysmenu.action'
	}),
	reader : new Ext.data.JsonReader({
		totalProperty : 'count',
		root : 'sysmenu'
	}, [{
		name : 'D_ID',
		type : 'int'
	}, {
		name : 'D_MENU_NAME',
		type : 'string'
	}, {
		name : 'D_MENU_URL',
		type : 'string'
	}, {
		name : 'D_VISIT_NAME',
		type : 'string'
	},{
		name : 'D_STATUS',
		type : 'int'
	}, {
		name : 'D_ISDEL',
		type : 'int'
	},{
		name : 'D_MENU_ID',
		type : 'string'
	}, {
		name : 'D_PARENT_MENU_ID',
		type : 'string'
	}, {
		name : 'D_CREATE_DT',
		type : 'string'
	}])
});

 var cm_sysmenu =new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),sm,
//            {header:"系统ID", dataIndex:"D_ID", sortable:true,width:50,menuDisabled : true},
            {header:"菜单名称", dataIndex:"D_MENU_NAME", sortable:true,width:100,menuDisabled : true},
			{header:"菜单URL", dataIndex:"D_MENU_URL", sortable:true,width:250,menuDisabled : true},
//			{header:"父菜单ID", dataIndex:"D_PARENT_MENU_ID", sortable:true,width:80},
//			{header:"菜单ID", dataIndex:"D_MENU_ID", sortable:true,width:80},
			{header:"菜单使用状态", dataIndex:"D_STATUS", sortable:true,width:50,menuDisabled : true,renderer : function(v) {
				var x = parseInt(v);
				switch(x) {
					case 1:
						return "<span style='color:blue;font-weight:bold;'>启用</span>";
					case 2:
						return "<span style='color:red;font-weight:bold;'>停用</span>";
					default: 
						return "<span style='color:yellow;font-weight:bold;'>未知</span>";
				}
			}
			},
			{header:"菜单记录状态", dataIndex:"D_ISDEL", sortable:true,width:70,menuDisabled : true,renderer : function(v) {
				var x = parseInt(v);
				switch(x) {
					case 1:
						return "<span style='color:blue;font-weight:bold;'>未删除</span>";
					case 2:
						return "<span style='color:red;font-weight:bold;'>已删除</span>";
					default: 
						return "<span style='color:yellow;font-weight:bold;'>未知</span>";
				}
			}
			},
			{header:"菜单创建时间", dataIndex:"D_CREATE_DT", sortable:true,width:120}
 ]);
 
var windows_add_sysmenu =new Ext.Window({
 	title:'添加',
 	width:400,
 	resizable : false,
	autoHeight : true,
	modal : true,
	closable:false,
//	closeAction : 'hide',
//	listeners : {
//		'hide' : function() {
//			this.setTitle('添加菜单');
//		}
//	},
	items:[new Ext.FormPanel({
		border:false,
		baseCls:'x-plain',
		bodyStyle:'padding:5px 5px 0',
		labelAlign:'right',
		url:'addsysmenu.action',
		defaults:{
			anchor:'80%',
			msgTarget : 'side'
		},
		defaultType:'textfield',
		items : [
		//role.roleName Action中Bean的名称和bean的属性,此处的name与下文中提交数据的store中的name对应
		{fieldLabel : '菜单名称',id:'sysMenu.menuName',name : 'sysMenu.menuName',maxLength : 150,allowBlank:false,vtype:'chinese'},
		{fieldLabel : '菜单URL',name : 'sysMenu.menuUrl',maxLength : 255,allowBlank:false,vtype:'url'},
		{
			fieldLabel : '父菜单ID',
			xtype:'combo',
			id:'add_parentMenuId',
			hiddenName:'sysMenu.parentMenuId',
			valueField:'D_MENU_ID',
			displayField:'D_MENU_NAME',
			mode:'remote',
			store:new Ext.data.Store({
				proxy:new Ext.data.HttpProxy({ 
					url:'getRootSysMenu.action'
				}),
				reader : new Ext.data.JsonReader({
					root : 'sysmenu'
				}, [{name : 'D_MENU_ID',type : 'int'},
					{name : 'D_MENU_NAME',type : 'string'}
				])
			}),
			selectOnFocus:true,
			editable:false,
			allowBlank:false,
			triggerAction:'all',
			emptyText:'父菜单ID'
		},{fieldLabel : '菜单ID',name : 'sysMenu.menuId',maxLength : 50,allowBlank:false,vtype:'integer'},
		{	
			fieldLabel:'菜单使用状态',
			xtype:'combo',
			id:'status',
			hiddenName:'sysMenu.status',
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
			emptyText:'菜单使用状态(启用,禁用)'
		}
		],
		buttonAlign:'right',
		minButtonWidth:60,
		buttons:[{
		  text:'添加菜单',
		  handler:function(btn){
		  	var frm =this.ownerCt.form;
		  	if(frm.isValid()){
		  		frm.submit({
		  				waitTitle : '请稍候',
						waitMsg : '正在提交表单数据,请稍候...',
						success : function(form, action) {
							Ext.MessageBox.alert("提示", action.result.msg);
							ds_sysmenu.load({ 
								params:{
									start:0,
									limit : limit									
								}
							})
							windows_add_sysmenu.hide();
							frm.reset();
						},
						failure : function(form,action){
							Ext.Msg.show({
								title : '提示',
								msg:action.result.msg,
								buttons : Ext.Msg.OK,
								icon : Ext.Msg.ERROR,
								fn:function(){
								  		btn.enable();
								}
							});
						}
		  		});
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
				windows_add_sysmenu.hide();
				this.ownerCt.form.reset();
			}
		}]
	})]
	
})
 
 var btn_add_sysmenu =new Ext.Button({
 	text:'添加',
 	iconCls:'icon-add',
 	handler:function(){
 	   		windows_add_sysmenu.show();
 	}
 })
 
 var btn_del_sysmenu = new Ext.Button({
	text : '删除',
	iconCls : 'icon-del',
	handler : function() {
		var records = grid_sysmenu.getSelectionModel().getSelections();
		if(records.length == 0 ){
			Ext.Msg.show({
				title : '提示',
				msg:'请选择需要删除的记录',
				buttons : Ext.Msg.OK,
				icon : Ext.Msg.ERROR
			});
		}else{
			var ids = [];
			for(i = 0;i < records.length;i++){
				ids.push(records[i].get('D_ID'));
			}
			Ext.Msg.confirm('确认删除', '你确定删除所选记录?', function(btn) {
				if (btn == 'yes') {
					Ext.Ajax.request({
						url : 'delsysmenu.action',
						params : {
							id : ids
						},
						success:function(response,option){
							var obj = Ext.util.JSON.decode(response.responseText);
							if(obj.success){
								Ext.MessageBox.alert("提示", obj.msg);
								ds_sysmenu.load({
									params:{
										start:0,
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
								msg : '系统发生错误!',
								buttons : Ext.Msg.OK,
								icon : Ext.Msg.ERROR
							})
		                }
					})
				}
			})
		}
	}
})

 var btn_undel_sysmenu = new Ext.Button({
	text : '解除删除',
	iconCls : 'icon-edit',
	handler : function() {
		var records = grid_sysmenu.getSelectionModel().getSelections();
		if(records.length == 0 ){
			Ext.Msg.show({
				title : '提示',
				msg:'请选择需要解除删除的记录',
				buttons : Ext.Msg.OK,
				icon : Ext.Msg.ERROR
			});
		}else{
			var ids = [];
			for(i = 0;i < records.length;i++){
				ids.push(records[i].get('D_ID'));
			}
			Ext.Msg.confirm('确认解除删除', '你确定解除删除所选记录?', function(btn) {
				if (btn == 'yes') {
					Ext.Ajax.request({
						url : 'unDelSysMenu.action',
						params : {
							id : ids
						},
						success:function(response,option){
							var obj = Ext.util.JSON.decode(response.responseText);
							if(obj.success){
								Ext.MessageBox.alert("提示", obj.msg);
								ds_sysmenu.load({
									params:{
										start:0,
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
								msg : '系统发生错误!',
								buttons : Ext.Msg.OK,
								icon : Ext.Msg.ERROR
							})
		                }
					})
				}
			})
		}
	}
})

var text_search_sysmenu = new Ext.form.TextField({
	name : 'textSearchSysMenu',
	width : 200,
	emptyText : '请输入查询条件!', //多条件可用逗号或者空格隔开
	listeners : {
		'specialkey' : function(field, e) {
			if (e.getKey() == Ext.EventObject.ENTER) {
				searchSysMenu();
			}
		}
	}
})

var search_comb_queyrCol_sysmenu = new Ext.form.ComboBox({
			fieldLabel : '查询条件',
			id : 'column_sysmenu',
			hiddenName : 'colName',
			valueField : 'id',
			displayField : 'name',
			mode:'local',
			store : new Ext.data.SimpleStore({
				data : [
						['menuName', '菜单名称'],
						['menuUrl', '菜单URL']
			    ],
				fields : ['id', 'name']
			}),	
			selectOnFocus : true,
			editable : false,
			allowBlank : true,
			triggerAction : 'all',
			emptyText : '选择查询的字段名'
})

 var searchSysMenu = function() {
		var values =text_search_sysmenu.getValue();
		var colName = search_comb_queyrCol_sysmenu.getValue();
			Ext.Ajax.request({
				url : 'sysmenu.action',
				params : {
					colName : colName,
					value : values
				},
				success:function(response,option){
					var obj = Ext.util.JSON.decode(response.responseText);
					if(obj.success){
						ds_sysmenu.load({
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
                failure:function(){
					Ext.Msg.show({
						title : '提示',
						msg : '服务器端异常!',
						buttons : Ext.Msg.OK,
						icon : Ext.Msg.ERROR
					})
                }
			});
}
 
var btn_search_sysmenu = new Ext.Button({
	text : '查询',
	iconCls : 'icon-search',
	handler : searchSysMenu
});

var btn_update_sysmenu = new Ext.Button({
	text:'修改',
	iconCls:'icon-edit',
	handler:function(){
		if(grid_sysmenu.getSelectionModel().getSelected()){
			var records = grid_sysmenu.getSelectionModel().getSelected();
			var updateWin = new Ext.Window({
				id:'sysMenu_update_win',
				title:'修改菜单窗口',
				width:400,
				resizable : false,
				autoHeight : true,
				modal : true,
				closable:false,
//				closeAction : 'close',
				items : [
					new Ext.FormPanel({ 
					url : 'updateSysMenu.action',
					labelAlign : 'right',
					labelWidth : 100,
					border : false,
					baseCls : 'x-plain',
					bodyStyle : 'padding:5px 5px 0',
					defaults:{
						anchor:'80%',
						msgTarget:'side'
					},
					defaultType : 'textfield',
					items : [
					{
						fieldLabel:'系统ID',
						xtype:'hidden',
						editable : false,
						allowBlank:true,
						name:'sysMenu.id',
						readOnly:true,
						value:records.get('D_ID')
					},{
						fieldLabel:'菜单ID',
						editable : false,
						allowBlank:true,
						name:'sysMenu.menuId',
						value:records.get('D_MENU_ID'),
						vtype:'integer'
							
					},		
					{
			fieldLabel : '父菜单ID',
			xtype:'combo',
			id:'parentMenuId',
			hiddenName:'sysMenu.parentMenuId',
			valueField:'D_MENU_ID',
			displayField:'D_MENU_NAME',
			mode:'remote',
			store:new Ext.data.Store({
				proxy:new Ext.data.HttpProxy({ 
					url:'getRootSysMenu.action'
				}),
				reader : new Ext.data.JsonReader({
					root : 'sysmenu'
				}, [{name : 'D_MENU_ID',type : 'int'},
					{name : 'D_MENU_NAME',type : 'string'}
				])
			}),
			selectOnFocus:true,
			editable:false,
			allowBlank:false,
			triggerAction:'all',
			emptyText:'父菜单ID',
			value:records.get('D_PARENT_MENU_ID')
		},
//		{
//						fieldLabel:'父菜单ID',
//						editable : false,
//						allowBlank:true,
//						name:'sysMenu.parentMenuId',
//						value:records.get('D_PARENT_MENU_ID'),
//						vtype:'integer'
//							
//					},
					{
						fieldLabel:'菜单名称',
						editable : false,
						allowBlank:true,
						name:'sysMenu.menuName',	
						value:records.get('D_MENU_NAME'),
						vtype:'chinese'
							
					},{
						fieldLabel:'创建时间',
						xtype:'hidden',
						name:'sysMenu.createDt',
						value:records.get('D_CREATE_DT')
					},{
						fieldLabel:'菜单URL',
						editable : false,
						allowBlank:true,
						name:'sysMenu.menuUrl',
						value:records.get('D_MENU_URL'),
						vtype:'url'
							
					},{
					fieldLabel:'菜单使用状态',
						xtype:'combo',
						id:'STATUS_D',
						hiddenName:'sysMenu.status',
						valueField:'D_STATUS_ID',
						displayField:'D_STATUS_NAME',
						mode:'local',
						store:new Ext.data.SimpleStore({
							data:[
									['1','启用'],
									['2', '停用']
							],
							fields:['D_STATUS_ID','D_STATUS_NAME']
						}),
						selectOnFocus:true,
						editable:false,
						allowBlank:false,
						triggerAction:'all',
						loadingText:'加载中...',
						emptyText:'当前记录状态：'+(records.get('D_STATUS') == 1 ?'启用':'停用')
							
					},{
						fieldLabel:'菜单记录状态',
						xtype:'hidden',
						name:'sysMenu.isDel',
						value:records.get('D_ISDEL')
							
					}],
				buttonAlign:'right',
				minButtonWidth:60,
				buttons:[
					{ 
						text:'修改',
						handler:function(btn){
						  	var frm = this.ownerCt.form;
							if(frm.isValid()){
						  		frm.submit({
						  				waitTitle : '请稍候',
										waitMsg : '正在提交表单数据,请稍候...',
										success : function(form, action) {
											Ext.MessageBox.alert("提示！", action.result.msg);
											ds_sysmenu.load({params : {start : 0,limit : limit}});
											var sysMenu_update_win = Ext.getCmp('sysMenu_update_win');
											sysMenu_update_win.close();
										},
										failure : function(form,action){
											Ext.Msg.show({
												title : '提示',
												msg : action.result.msg,
												buttons : Ext.Msg.OK,
												icon : Ext.Msg.ERROR,
												fn:function(){
													frm.reset();
												}
											})
										}
						  		});
							}
						}
					},{ 
						text : '重置',
						handler : function() {
								this.ownerCt.form.reset();
						}
					},{ 
						text : '取消',
						handler : function() {
								this.ownerCt.ownerCt.close();
						}
					}]
				})]
			}).show()
		}else{
			Ext.Msg.show({
				title : '提示',
				msg : '请选择需要修改的记录',
				buttons : Ext.Msg.OK,
				icon : Ext.Msg.ERROR
			})
		}
		
	}
 })
var btn_show_sysmenu_info = new Ext.Button({
	text:'查看详情',
	iconCls:'icon-edit',
	handler:function(){
		if(grid_sysmenu.getSelectionModel().getSelected()){
			var records = grid_sysmenu.getSelectionModel().getSelected();
			var updateWin = new Ext.Window({
				id:'sysMenu_update_win',
				title:'查看菜单详情',
				width:400,
				resizable : false,
				autoHeight : true,
				modal : true,
				closable:false,
//				closeAction : 'close',
				items : [
					new Ext.FormPanel({ 
					labelAlign : 'right',
					labelWidth : 100,
					border : false,
					baseCls : 'x-plain',
					bodyStyle : 'padding:5px 5px 0',
					defaults:{
						anchor:'80%',
						msgTarget:'side'
					},
					defaultType : 'textfield',
					items : [
					{
						fieldLabel:'系统ID',
						xtype:'hidden',
						readOnly:true,
						value:records.get('D_ID')
					},{
						fieldLabel:'菜单ID',
						readOnly:true,
						value:records.get('D_MENU_ID')
					},{
						fieldLabel:'父菜单ID',
						readOnly:true,
						value:records.get('D_PARENT_MENU_ID')
					},{
						fieldLabel:'菜单名称',
						readOnly:true,
						value:records.get('D_MENU_NAME')
					},{
						fieldLabel:'创建时间',
						readOnly:true,
						value:records.get('D_CREATE_DT')
					},{
						fieldLabel:'菜单URL',
						readOnly:true,
						value:records.get('D_MENU_URL')
					},{
						fieldLabel:'菜单使用状态',
						readOnly:true,
						value:(records.get('D_STATUS') == 1 ?'启用':'停用')
					},{
						fieldLabel:'菜单记录状态',
						readOnly:true,
						value:(records.get('D_ISDEL') == 1?'未删除':'已删除')
							
					}],
				buttonAlign:'right',
				minButtonWidth:60,
				buttons:[
					{ 
						text : '关闭',
						handler : function() {
								this.ownerCt.ownerCt.close();
						}
					}]
				})]
			}).show()
		}else{
			Ext.Msg.show({
				title : '提示',
				msg : '请选择需要修改的记录',
				buttons : Ext.Msg.OK,
				icon : Ext.Msg.ERROR
			})
		}
		
	}
 })

/** 载入数据 **/ 
ds_sysmenu.load({
	params : {
		colName : '',
		value : '',
		start : 0,
		limit : limit
	}
});

var grid_sysmenu = new Ext.grid.GridPanel({
	title : '菜单管理',
	width:800,
	autoHeight:true,
	iconCls : 'icon-plugin',
	region : 'center',
	loadMask : {
		msg : '数据加载中...'
	},
	ds : ds_sysmenu,
	cm : cm_sysmenu,
	sm : sm,
	tbar : [btn_add_sysmenu, '-',btn_undel_sysmenu,'-', btn_del_sysmenu, '-',btn_update_sysmenu,'-',btn_show_sysmenu_info,'-',search_comb_queyrCol_sysmenu,'-',
			text_search_sysmenu, btn_search_sysmenu],
	bbar : new Ext.PagingToolbar({ 
		pageSize:limit,
		store:ds_sysmenu,
		displayInfo:true,
		displayMsg:'第 {0} - {1} 条  共 {2} 条',
		emptyMsg:'没有记录'
	})

})

Ext.QuickTips.init();
grid_sysmenu.render('sysmenu');

})
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 