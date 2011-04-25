var app = {};
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL = '/resource/image/ext/s.gif';
	Ext.QuickTips.init();
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
	app.limit = 15;
	app.colName = '';
	app.values = '';
    app.sm = new Ext.grid.CheckboxSelectionModel();
    
	app.lookup = Ext.data.Record.create([{
		name : 'id',
		mapping : 'd_id',
		type : 'int'
	}, {
		name : 'url',
		mapping : 'd_value',
		type : 'string'
	},{
		name : 'name',
		mapping : 'd_name',
		type : 'string'
	},{
		name : 'type',
		mapping : 'd_type',
		type : 'string'
	}, {
		name : 'remarks',
		mapping : 'd_remarks',
		type : 'string'
	}, {
		name : 'status',
		mapping : 'd_status',
		type : 'string'
	}, {
		name : 'createtime',
		mapping : 'd_createtime',
		type : 'string'
	}, {
		name : 'modifytime',
		mapping : 'd_modifytime',
		type : 'string'
	}]);
	
    app.data = [
    	[1,'营业厅','CONSTANT_MOBILE_DEPT','01','组织结构',1,'2011-04-11 11:00:00','2011-04-11 11:00:00'],
    	[2,'机关','CONSTANT_MOBILE_DEPT','02','组织结构',1,'2011-04-11 11:00:00','2011-04-11 11:00:00'],
    	[3,'其他','CONSTANT_MOBILE_DEPT','03','组织结构',1,'2011-04-11 11:00:00','2011-04-11 11:00:00']
    ];
    
    
    var expander = new Ext.grid.RowExpander({
        tpl : new Ext.Template(
            '<p><b>备注:</b><br/>{d_remarks}</p>'
        )
    });
    
    app.cm_lookup = new Ext.grid.ColumnModel([
    	expander, 
        {header: "ID", sortable: true, dataIndex: 'd_id'},
        {header: "常量类型", width: 120, sortable: true, dataIndex: 'd_type'},
        {header: "常量名称", width: 150, sortable: true, dataIndex: 'd_name'},
        {header: "常量值",   width: 100, sortable: true, dataIndex: 'd_value'},
        {header: "创建时间", width: 150, sortable: true,dataIndex: 'd_createtime'},
        {header: "修改时间", width: 170, sortable: true,dataIndex: 'd_modifytime'}
    ]);
    
	app.search_comb_queyrCol_code = new Ext.form.ComboBox({
				fieldLabel : '查询条件',
				id : 'column',
				hiddenName : 'colName',
				valueField : 'id',
				displayField : 'name',
				mode:'local',
				store : new Ext.data.SimpleStore({
					data : [
							['name', '常量名称'],
							['type', '常量类型'],
							['value','常量值']
				    ],
					fields : ['id', 'name']
				}),	
				selectOnFocus : true,
				editable : false,
				allowBlank : true,
				triggerAction : 'all',
				emptyText : '查询的字段名'
	});
	
	app.btn_search_code = new Ext.Button({
		text : '查询',
		iconCls : 'icon-search',
		handler : app.searchcode
	});
	
	app.btn_get_img_url = new Ext.Button({ 
		text:'获取文章图片',
		iconCls:'icon-save',
		handler:function(){
			if(app.grid.getSelectionModel().getSelected()){
				Ext.MessageBox.alert("系统提示", "此功能正在开发,请等待....!");
			}else{
				Ext.MessageBox.alert("系统提示", "请选择数据!");
			}
		}
	});
	
	app.text_search_code = new Ext.form.TextField({
		name : 'textSearchcode',
		width : 200,
//		emptyText : '多条件可用逗号或者空格隔开!',
		listeners : {
			'specialkey' : function(field, e) {
				if (e.getKey() == Ext.EventObject.ENTER) {
					app.searchcode();
				}
			}
		}
	});

	app.searchcode = function() {
		app.colName = app.search_comb_queyrCol_code.getValue();
		app.values = app.text_search_code.getValue();
//		app.ds_lookup.loadData(app.data);
//		Ext.Ajax.request({
//			url : project+'/lookup/type.cgi',
//			params : {
//				colName : app.colName,
//				value : app.values
//			},
//			success:function(response,option){
//				var obj = Ext.util.JSON.decode(response.responseText);
//				if(obj.success){
//					app.ds_lookup.load({params : {start:0,limit:app.limit}}); //,colName:app.colName,value:app.values
//				}else{
//	             	Ext.MessageBox.alert('提示',obj.msg);
//				}
//			},
//            failure:function(){
//             	Ext.MessageBox.alert('提示','服务器内部错误');
//            }
//		});
	};
		
//	app.ds_lookup = new Ext.data.Store({
//		url : project+'/lookup/list.cgi',
//		baseParams:{},
//		reader : new Ext.data.JsonReader({
//			totalProperty : 'count',
//			root : 'lookup'
//		}, [{name : 'd_id',type : 'int'
//		}, {name : 'd_value',type : 'string'
//		}, {name : 'd_name',type : 'string'
//		}, {name : 'd_type',type : 'string'
//		}, {name : 'd_remarks',type : 'string'
//		}, {name : 'd_status',type : 'int'
//		}, {name : 'd_createtime',type : 'string'
//		}, {name : 'd_modifytime',type : 'string'
//		}])
//	});
	
	app.ds_lookup = new Ext.data.ArrayStore({
        fields: [
	 		{name : 'd_id',type : 'int'
			}, 
			{name : 'd_value',type : 'string'
			}, 
			{name : 'd_name',type : 'string'
			}, 
			{name : 'd_type',type : 'string'
			}, 
			{name : 'd_remarks',type : 'string'
			}, 
			{name : 'd_status',type : 'int'
			}, 
			{name : 'd_createtime',type : 'string'
			}, 
			{name : 'd_modifytime',type : 'string'
			}
		]
	});

	app.btn_add_code = new Ext.Button({
		text : '添加',
		iconCls : 'icon-add',
		handler : function() {
			app.window_add.show();
		}
	});
	
	var btn_disable = new Ext.Button({
		text : '禁用',
		iconCls : 'icon-application_delete',
		disabled: true,
		handler : function() {
			var records = app.grid.getSelectionModel().getSelections();
			if(records.length == 0 ){
				Ext.Msg.show({
					title : '提示',
					msg:'请选择需要禁用的站点',
					buttons : Ext.Msg.OK,
					icon : Ext.Msg.ERROR
				});
			}else{
				var ids = [];
				for(i = 0;i < records.length;i++){
					var tmp = records[i].get('d_id');
					Ext.MessageBox.alert('notice',tmp);
					ids.push(tmp);
				}
				Ext.Msg.confirm('禁用站点', '你确定需要禁用所选站点?', function(btn) {
					if (btn == 'yes') {
//						Ext.Ajax.request({
//							url : project+'/lookup/disable.cgi',
//							params : {
//								ids : ids
//							},
//							success:function(response,option){
//								var obj = Ext.util.JSON.decode(response.responseText);
//								if(obj.success){
//									Ext.MessageBox.alert("提示", obj.msg);
//									app.ds_lookup.load({
//										params:{
//											start: 0,
//											limit :app.limit
//										}
//									});
//								}else{
									Ext.Msg.show({
										title : '提示',
//										msg : obj.msg,
										msg:'禁用成功',
										buttons : Ext.Msg.OK,
										icon : Ext.Msg.ERROR
									});
//								}
//							},
//			                failure:function(response,option){
//								Ext.Msg.show({
//									title : '提示',
//									msg : '系统发生错误!',
//									buttons : Ext.Msg.OK,
//									icon : Ext.Msg.ERROR
//								});
//			                }
//						});
					}
				})
			}
		}
	});
	
	var btn_enable = new Ext.Button({
		text : '启用',
		iconCls : 'icon-application_add',
		disabled: true,
		handler : function() {
			var records = app.grid.getSelectionModel().getSelections();
			if(records.length == 0 ){
				Ext.Msg.show({
					title : '提示',
					msg:'请选择需要启用的站点',
					buttons : Ext.Msg.OK,
					icon : Ext.Msg.ERROR
				});
			}else{
				var ids = [];
				for(i = 0;i < records.length;i++){
					ids.push(records[i].get('d_id'));
				}
				Ext.Msg.confirm('启用站点', '你确定启用所选站点?', function(btn) {
					if (btn == 'yes') {
//						Ext.Ajax.request({
//							url : project+'/lookup/enable.cgi',
//							params : {
//								ids : ids
//							},
//							success:function(response,option){
//								var obj = Ext.util.JSON.decode(response.responseText);
//								if(obj.success){
//									app.ds_lookup.load({
//										params:{
//											start: 0,
//											limit :app.limit
//										}
//									});
//									Ext.MessageBox.alert("提示", obj.msg);
//								}else{
									Ext.Msg.show({
										title : '提示',
//										msg : obj.msg,
										msg : '启用成功',
										buttons : Ext.Msg.OK,
										icon : Ext.Msg.INFO
									});
//								}
//							},
//			                failure:function(response,option){
//								Ext.Msg.show({
//									title : '提示',
//									msg : '系统发生错误!',
//									buttons : Ext.Msg.OK,
//									icon : Ext.Msg.ERROR
//								})
//			                }
//						});
					}
				});
				/**
				**/
			}
		}
	});
	
	app.window_add = new Ext.Window({
		id:'navigate_add_windows',
		title : '添加',
		iconCls : 'icon-add',
		width : 400,
		resizable : false,
		autoHeight : true,
		modal : true,
		closeAction : 'hide',
		x:300,
		y:200,
		listeners : {
			'hide' : function() {
				this.setTitle('添加');
			}
		},
		items : [new Ext.FormPanel({
			id:'constants_add_form',
			labelWidth : 80,
			labelAlign : 'right',
//			url : project+'/lookup/add.cgi',
			border : false,
			baseCls : 'x-plain',
			bodyStyle : 'padding:5px 5px 0',
			anchor : '100%',
			defaults : {
				width : 233,
				msgTarget : 'side'
			},
			defaultType : 'textfield',
			items : [{
				fieldLabel : '常量类型',
				name : 'lookup.type',
				maxLength : 64
			}, {
				fieldLabel : '常量名称',
				name : 'lookup.name',
				maxLength : 64
			}, {
				fieldLabel : '常量值',		
				name : 'lookup.value',
				maxLength : 256
			},{
				fieldLabel : '备注',
				xtype:'textarea',
				name : 'lookup.remarks',
				maxLength : 512
			}],
			buttonAlign : 'center',
			minButtonWidth : 60,
			buttons : [{
				text : '保存',
				iconCls:'icon-accpet',
				handler : function(btn) {
					var frm = this.ownerCt.form;
					if (frm.isValid()) {
						Ext.Msg.show({
							title : '提示',
//										msg : obj.msg,
							msg : '添加成功',
							buttons : Ext.Msg.OK,
							icon : Ext.Msg.INFO
						});
//						btn.disable();
//						var dnfield = frm.findField('lookup.name');
//						frm.submit({
//							waitTitle : '请稍候',
//							waitMsg : '正在提交表单数据,请稍候...',
//							success : function(form, action) {
//								frm.reset();
//								app.window_add.hide();
//								Ext.Msg.show({
//									title : '系统提示',
//									msg:action.result.msg,
//									buttons : Ext.Msg.OK,
//									fn:function(){
//										btn.enable();
//										app.ds_lookup.load({params : {start : 0,limit : app.limit}});
//									},
//									icon : Ext.Msg.INFO
//								});
//							},
//							failure : function(form,action) {
//								Ext.Msg.show({
//									title : '错误提示',
//									msg : '"' + dnfield.getValue() + '" ' + '名称可能已经存在或者您没有添加数据的权限!',
//									buttons : Ext.Msg.OK,
//									icon : Ext.Msg.ERROR
//								});
//							}
//						});
					}
				
 				}
			}, {
				text : '重置',
				iconCls:'icon-arrow_refresh',
				handler : function() {
					Ext.getCmp('constants_add_form').form.reset();
				}
			}, {
				text : '取消',
				iconCls:'icon-cancel',
				handler : function() {
					Ext.getCmp('constants_add_form').form.reset();
					var addWin = Ext.getCmp('navigate_add_windows');
					addWin.close();
				}
			}]
		})]
	});
	
	app.update_code_btn = new Ext.Button({ 
		text : '编辑',
		iconCls : 'icon-edit',
		disabled: true,
		handler : function() {
			if(app.grid.getSelectionModel().getSelected()){
				var record = app.grid.getSelectionModel().getSelected();
				var updateWin = new Ext.Window({ 
					id:'update_navagite_window',
					title : '编辑',
					iconCls:'icon-edit',
					width : 450,
					height : 440,
					x:300,
					y:200,
					resizable : false,
					autoHeight : true,
					modal : true,
					closeAction : 'close',
					items : [
						new Ext.FormPanel({
						id:'update_navagite_form',
						labelWidth : 80,
						labelAlign : 'right',
//						url : project+'/lookup/update.cgi',
						border : false,
						baseCls : 'x-plain',
						bodyStyle : 'padding:5px 5px 0',
						anchor : '100%',
						defaults : {
							width : 300,
							msgTarget : 'side'
						},
						defaultType : 'textfield',
						items : [
						{
							fieldLabel : 'ID',
							name : 'lookup.id',
							maxLength : 64,
							value:record.get('d_id'),
							readOnly:true
						},{
							fieldLabel : '常量类型',
							name : 'lookup.type',
							maxLength : 64,
							value:record.get('d_type')
						},{
							fieldLabel : '常量名称',
							name : 'lookup.name',
							maxLength : 64,
							value:record.get('d_name')
						},{
							fieldLabel : '常量值',		
							name : 'lookup.value',
							maxLength : 256,
							value:record.get('d_value')
						},{
							fieldLabel : '备注',
							xtype:'textarea',
							name : 'lookup.remarks',
							maxLength : 512,
							value:record.get('d_remarks')
						}],
						buttonAlign : 'center',
						minButtonWidth : 60,
						buttons : [{
							text : '保存',
							iconCls:'icon-accpet',
							handler : function(btn) {
								var frm = this.ownerCt.form;
								if (frm.isValid()) {
									Ext.Msg.show({
										title : '提示',
										msg : '修改成功',
										buttons : Ext.Msg.OK,
										icon : Ext.Msg.INFO
									});
//									btn.disable();
//									var dnfield = frm.findField('lookup.name');
//									frm.submit({
//										waitTitle : '请稍候',
//										waitMsg : '正在提交表单数据,请稍候...',
//										success : function(form, action) {
//											app.ds_lookup.load({params : {start : 0,limit : app.limit}});
//											var updateWin = Ext.getCmp('update_navagite_window');
//											updateWin.close();
//											frm.reset();
//											Ext.MessageBox.alert("系统提示", "修改成功!");
//										},
//										failure : function() {
//											Ext.Msg.show({
//												title : '错误提示',
//												msg : '"' + dnfield.getValue() + '" ' + '名称可能已经存在或者您没有更新数据的权限!',
//												buttons : Ext.Msg.OK,
//												fn : function() {
//													var updateWin = Ext.getCmp('update_navagite_window');
//													updateWin.close();
//												},
//												icon : Ext.Msg.ERROR
//											});
//										}
//									});
								}
							}
						}, {
							text : '重置',
							iconCls:'icon-arrow_refresh',
							handler : function() {
								Ext.getCmp('update_navagite_form').form.reset();
							}
						}, {
							text : '取消',
							iconCls:'icon-cancel',
							handler : function() {
								Ext.getCmp('update_navagite_form').form.reset();
								var updateWin = Ext.getCmp('update_navagite_window');
								updateWin.close();
							}
						}]
					})]
			}).show();
		}else{
				Ext.MessageBox.alert("提示信息!!", "请选择要修改行!!");
		}
		}
	});
	
	app.copy_code_btn = new Ext.Button({ 
		text : '复制',
		iconCls : 'icon-page_copy',
		disabled: true,
		handler : function() {
			if(app.grid.getSelectionModel().getSelected()){
				var record = app.grid.getSelectionModel().getSelected();
				var updateWin = new Ext.Window({ 
					id:'copy_navagite_window',
					title : '复制',
					iconCls:'icon-page_copy',
					width : 450,
					height : 440,
					x:300,
					y:200,
					resizable : false,
					autoHeight : true,
					modal : true,
					closeAction : 'close',
					items : [
						new Ext.FormPanel({
						id:'copy_navagite_form',
						labelWidth : 80,
						labelAlign : 'right',
//						url : project+'/lookup/add.cgi',
						border : false,
						baseCls : 'x-plain',
						bodyStyle : 'padding:5px 5px 0',
						anchor : '100%',
						defaults : {
							width : 300,
							msgTarget : 'side'
						},
						defaultType : 'textfield',
						items : [
						{
							fieldLabel : '常量类型',
							name : 'lookup.type',
							maxLength : 64,
							value:record.get('d_type')
						},{
							fieldLabel : '常量名称',
							name : 'lookup.name',
							maxLength : 64,
							value:record.get('d_name')
						},{
							fieldLabel : '常量值',		
							name : 'lookup.value',
							maxLength : 256,
							value:record.get('d_value'),
							allowBlank:false
						},{
							fieldLabel : '备注',
							xtype:'textarea',
							name : 'lookup.remarks',
							maxLength : 512,
							value:record.get('d_remarks')
						}],
						buttonAlign : 'center',
						minButtonWidth : 60,
						buttons : [{
							text : '保存',
							iconCls:'icon-accpet',
							handler : function(btn) {
								var frm = Ext.getCmp('copy_navagite_form').form;
								if (frm.isValid()) {
									Ext.Msg.show({
										title : '提示',
										msg : '复制成功',
										buttons : Ext.Msg.OK,
										icon : Ext.Msg.INFO
									});
//									btn.disable();
//									var dnfield = frm.findField('lookup.name');
//									frm.submit({
//										waitTitle : '请稍候',
//										waitMsg : '正在提交表单数据,请稍候...',
//										success : function(form, action) {
//											var copyWin = Ext.getCmp('copy_navagite_window');
//											copyWin.close();
//											frm.reset();
//											Ext.Msg.show({
//												title : '成功提示',
//												msg : '复制字段数据成功!',
//												buttons : Ext.Msg.OK,
//												fn : function() {
//													app.ds_lookup.load({params : {start : 0,limit : app.limit}});
//												},
//												icon : Ext.Msg.INFO
//											});
//										},
//										failure : function(form,action) {
//											Ext.Msg.show({
//												title : '错误提示',
//												msg : action.result.msg,
//												buttons : Ext.Msg.OK,
//												fn : function() {
//													var copyWin = Ext.getCmp('copy_navagite_window');
//													copyWin.close();
//												},
//												icon : Ext.Msg.ERROR
//											});
//										}
//									});
								}
							}
						}, {
							text : '重置',
							iconCls:'icon-arrow_refresh',
							handler : function() {
								Ext.getCmp('copy_navagite_form').form.reset();
							}
						}, {
							text : '取消',
							iconCls:'icon-cancel',
							handler : function() {
								Ext.getCmp('copy_navagite_form').form.reset();
								var copyWin = Ext.getCmp('copy_navagite_window');
								copyWin.close();
							}
						}]
					})]
			}).show();
		}else{
				Ext.MessageBox.alert("提示信息!!", "请选择要修改行!!");
		}
		}
	});
	
//	app.ds_lookup.load({
//		params : {
//			start : 0,
//			limit : app.limit
//		}
//	});
	
//	app.ds_lookup.on('beforeload',function(){
//		Ext.apply(
//			this.baseParams,{
//				colName : app.colName,
//				value : app.values
//	        }   
//	 	);   
//	});  
	
	app.ds_lookup.loadData(app.data);
	
	app.ptb = new Ext.PagingToolbar({
		pageSize:app.limit,
		store:app.ds_lookup,
		displayInfo:true,
		displayMsg:'第 {0} - {1} 条  共 {2} 条',
		emptyMsg:'没有记录'
	});
		
	app.grid = new Ext.grid.GridPanel({
		title : '常量数据管理',
		iconCls : 'icon-application_view_columns',
		region : 'center',
		loadMask : {
			msg : '数据加载中...'
		},
	    cm: app.cm_lookup,
	    ds: app.ds_lookup,
		sm:app.sm,
		height:500,
        autoScroll: true,
        viewConfig: {
            forceFit:true
        },
 		plugins: expander,
		tbar : [app.btn_add_code,'-',app.update_code_btn,'-',app.copy_code_btn,'-',app.search_comb_queyrCol_code,'-', app.text_search_code,'-',app.btn_search_code], 
		bbar : app.ptb
	});
	
	app.grid.addListener('rowdblclick',function(grid, rowIndex){
				if(grid.getSelectionModel().isSelected(rowIndex)){
					Ext.MessageBox.alert("系统提示","此功能正在开发,请等待....!");
				}
	});

	app.grid.addListener('rowclick',function(grid, rowIndex){
		if(grid.getSelectionModel().isSelected(rowIndex)){
            btn_enable.enable();
            btn_disable.enable();
            app.copy_code_btn.enable();
            app.update_code_btn.enable();
		}else{
            btn_enable.disable();
            btn_disable.disable();
            app.copy_code_btn.disable();
            app.update_code_btn.disable();
		}
	});
	
    app.grid.render('div-lookup');
});