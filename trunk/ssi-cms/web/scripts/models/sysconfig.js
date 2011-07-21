var app = {};
Ext.onReady(function(){
	
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    Ext.QuickTips.init();
	app.limit = 20;
	app.colName = colName;
	app.values = values;
    app.sm = new Ext.grid.CheckboxSelectionModel();
    
	app.sysconfig = Ext.data.Record.create([{
		name : 'id',
		mapping : 'd_id',
		type : 'int'
	}, {
		name : 'name',
		mapping : 'd_name',
		type : 'string'
	}, {
		name : 'type',
		mapping : 'd_type',
		type : 'string'
	}, {
		name : 'value',
		mapping : 'd_value',
		type : 'string'
	}, {
		name : 'createtime',
		mapping : 'd_create_dt',
		type : 'string'
	},  {
		name : 'modifytime',
		mapping : 'd_modify_dt',
		type : 'string'
	}, {
		name : 'description',
		mapping : 'd_description',
		type : 'string'
	}, {
		name : 'groupid',
		mapping : 'd_groupid',
		type : 'string'
	}]);
    
    var expander = new Ext.grid.RowExpander({
        tpl : new Ext.Template(
            '<p><b>描述:</b><br/>{d_description}</p>'
        )
    });
    
    app.cm_sysconfig = new Ext.grid.ColumnModel([
	    expander,
        {header: "ID", sortable: true, dataIndex: 'd_id'}, //width: 50, 
        {header: "类型", width: 50,dataIndex:'d_type'},
        {header: "名称", width: 150, sortable: true, dataIndex: 'd_name'},
        {header: "值", width: 150, sortable: true, dataIndex: 'd_value'},
        {header: "组ID", width: 150, sortable: true, dataIndex: 'd_groupid'},
        {header: "创建时间", width: 150, sortable: true, dataIndex: 'd_create_dt'},
        {header: "修改时间", width: 150, sortable: true,dataIndex: 'd_modify_dt'}
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
							['name', '配置名称'],
							['type','配置类型'],
							['value','配置值']
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
	
	app.text_search_code = new Ext.form.TextField({
		name : 'textSearchcode',
		width : 200,
		emptyText : '多条件可用逗号或者空格隔开!',
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
		app.values =app.text_search_code.getValue();
		Ext.Ajax.request({
			url : project+'/sysconfig/list.cgi',
			params : {
				colName : app.colName,
				value : app.values
			},
			success:function(response,option){
				var obj = Ext.util.JSON.decode(response.responseText);
				if(obj.success){
					app.ds_sysconfig.load({params : {start:0,limit:app.limit}}); //,colName:app.colName,value:app.values
				}else{
					Ext.Msg.show({
						title : '系统提示',
						msg : obj.msg,
						buttons : Ext.Msg.OK,
						icon : Ext.MessageBox.ERROR
					});
				}
			},
            failure:function(){
				Ext.Msg.show({
					title : '系统提示',
					msg : '服务器内部错误',
					buttons : Ext.Msg.OK,
					icon : Ext.MessageBox.ERROR
				});
            }
		})
	};
		
	app.ds_sysconfig = new Ext.data.Store({
		url : project+'/sysconfig/list.cgi',
		baseParams:{},
		reader : new Ext.data.JsonReader({
			totalProperty : 'count',
			root : 'sysconfig'
		}, [{name : 'd_id',type : 'int'
		}, {name : 'd_name',type : 'string'
		}, {name : 'd_type',type : 'string'
		}, {name : 'd_value',type : 'string'
		}, {name : 'd_description',type : 'string'
		}, {name : 'd_groupid',type : 'int'
		}, {name : 'd_modify_dt',type : 'string'
		}, {name : 'd_create_dt',type : 'string'
		}])
	});
	
	app.btn_add_code = new Ext.Button({
		text : '添加',
		iconCls : 'icon-add',
		handler : function() {
			app.window_add.show();
		}
	});
	
	app.window_add = new Ext.Window({
		id:'article_add_win',
		title : '添加',
		iconCls : 'icon-add',
		width : 500,
		height:300,
		resizable : false,
		modal : true,
		closeAction : 'hide',
		listeners : {
			'hide' : function() {
				this.setTitle('添加文章');
			}
		},
		items : [new Ext.FormPanel({
			id:'article_add_form',
			labelWidth : 80,
			labelAlign : 'center',
			url : project+'/sysconfig/insert.cgi',
			baseCls : 'x-plain',
			bodyStyle : 'padding:5px 5px 0',
			anchor : '100%',
			defaults : {
				width : '98%',
				msgTarget : 'side'
			},
			defaultType : 'textfield',
			items : [{
				fieldLabel : '配置名称',
				id : 'sysconfig.name',
				name : 'sysconfig.name',
				maxLength : 50
			}, {
				fieldLabel : '配置类型',		
				name : 'sysconfig.type',
				maxLength : 150
			}, {
				fieldLabel : '配置值',		
				name : 'sysconfig.value',
				maxLength : 150
			},{
				fieldLabel : '所属组ID',
				name : 'sysconfig.groupid',
				readOnly:true,
				value:webId
			},{
				fieldLabel : '介绍',
				xtype:'textarea',
				name : 'sysconfig.description',
				maxLength : 20
			}],
			buttonAlign : 'right',
			minButtonWidth : 60,
			buttons : [{
				text : '添加',
				handler : function(btn) {
//					var frm = this.ownerCt.form;
//					if (frm.isValid()) {
//						btn.disable();
//						var dnfield = frm.findField('code.codeName');
//						frm.submit({
//							waitTitle : '请稍候',
//							waitMsg : '正在提交表单数据,请稍候...',
//							success : function(form, action) {
//								var store = grid_code.getStore();
//									Ext.MessageBox.alert("成功！", "添加成功!");
//									app.ds_sysconfig.load({params : {start : 0,limit : limit}});
//								if (store.data.length <= 20) {
//									var code = new code({
//										codeName: dnfield.getValue(),
//										createDt : frm.findField('code.createDt').getValue(),
//										codeTp : frm.findField('code.codeTp').getValue(),
//										status : frm.findField('code.status').getValue(),
//										desc : frm.findField('code.desc').getValue(),
//										isDel : frm.findField('code.isDel').getValue()
//									});
//								   store.insert(0, [code]);
//								}
//								app.window_add.setTitle('[ ' + dnfield.getValue() + ' ]   添加成功!!');
//								dnfield.reset();
//								btn.enable();
//								app.window_add.close();
//							},
//							failure : function() {
//								Ext.Msg.show({
//									title : '错误提示',
//									msg : '"' + dnfield.getValue() + '" ' + '名称可能已经存在或者您没有添加数据的权限!',
//									buttons : Ext.Msg.OK,
//									fn : function() {
//										dnfield.focus(true);
//										btn.enable();
//									},
//									icon : Ext.Msg.ERROR
//								})
//							}
//						})
//					}
//				
 				}
			}, {
				text : '重置',
				handler : function() {
					Ext.getCmp('article_add_form').getForm().reset();
				}
			}, {
				text : '取消',
				handler : function() {
					Ext.getCmp('article_add_win').hide();
					Ext.getCmp('article_add_form').getForm().reset();
				}
			}]
		})]
	});
	
	app.update_code_btn = new Ext.Button({
		text : '编辑',
		iconCls : 'icon-edit',
		handler : function() {
			if(app.grid.getSelectionModel().getSelected()){
			var record = app.grid.getSelectionModel().getSelected();
				var updateWin = new Ext.Window({ 
					id:'article_update_win',
					title : '编辑',
					iconCls:'icon-edit',
					width : 500,
					height: 350,
					resizable : false,
					closeAction : 'close',
					items : [new Ext.FormPanel({
						id:'article_update_form',
						labelWidth : 80,
						labelAlign : 'center',
						url : project+'/sysconfig/update.cgi',
						baseCls : 'x-plain',
						bodyStyle : 'padding:5px 5px 0',
						defaults : {
							width : '98%',
							msgTarget : 'side'
						},
						defaultType : 'textfield',
						items : [
						{
							fieldLabel : 'ID',
							id : 'sysconfig.id',
							name : 'sysconfig.id',
							maxLength : 50,
							readOnly:true,
							value:record.get('d_id')
						}, {
							fieldLabel : '配置名称',
							name : 'sysconfig.name',
							maxLength : 50,
							value:record.get('d_name')
						}, {
							fieldLabel : '配置类型',		
							name : 'sysconfig.type',
							maxLength : 150,
							value:record.get('d_type')
						}, {
							fieldLabel : '配置值',		
							name : 'sysconfig.value',
							maxLength : 150,
							value:record.get('d_value')
						},{
							fieldLabel : '所属组ID',
							name : 'sysconfig.groupid',
							readOnly:true,
							value:record.get('d_groupid')
						},{
							fieldLabel : '介绍',
							xtype:'textarea',
							name : 'sysconfig.description',
							maxLength : 20,
							value:record.get('d_description')
						}],
						buttonAlign : 'right',
						minButtonWidth : 60,
						buttons : [{
							text : '更新',
							handler : function(btn) {
								var frm = this.ownerCt.form;
								if (frm.isValid()) {
									btn.disable();
									var dnfield = frm.findField('sysconfig.name');
									frm.submit({
										waitTitle : '请稍候',
										waitMsg : '正在提交表单数据,请稍候...',
										success : function(form, action) {
											Ext.Msg.show({
												title : '系统提示',
												msg : '修改配置"' + dnfield.getValue() + '"成功!',
												buttons : Ext.Msg.OK,
												icon : Ext.MessageBox.INFO
											});
											app.ds_sysconfig.load({params : {start : 0,limit : app.limit}});
											Ext.getCmp('article_update_win').close();
										},
										failure : function() {
											Ext.Msg.show({
												title : '错误提示',
												msg : '"' + dnfield.getValue() + '" ' + '名称可能已经存在或者您没有更新数据的权限!',
												buttons : Ext.Msg.OK,
												icon : Ext.Msg.ERROR
											});
										}
									})
								}
							}
						}, {
							text : '重置',
							handler : function() {
//								this.ownerCt.form.reset();
								Ext.getCmp('article_update_form').getForm().reset();
							}
						}, {
							text : '取消',
							handler : function() {
								Ext.getCmp('article_update_form').getForm().reset();
								Ext.getCmp('article_update_win').close();
//								this.ownerCt.ownerCt.close();
//								this.ownerCt.form.reset();
							}
						}]
					})]
			}).show();
		}else{
			Ext.Msg.show({
				title : '系统提示',
				msg : '请选择要修改的记录!',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.ERROR
			});
		}
		}
	})
	
	app.ds_sysconfig.load({
		params : {
			start : 0,
			limit : app.limit
		}
	});
	
	app.ds_sysconfig.on('beforeload',function(){
		Ext.apply(
			this.baseParams,{
				colName : app.colName,
				value : app.values
	        }   
	 	);   
	});  
	
	app.pagesize_combo = new Ext.form.ComboBox({
				name : 'pagesize',
				hiddenName : 'pagesize',
				typeAhead : true,
				triggerAction : 'all',
				lazyRender : true,
				mode : 'local',
				store : new Ext.data.SimpleStore({
							fields : ['value', 'text'],
							data : [[10, '10条/页'],[15, '15条/页'],[20, '20条/页']]
						}),
				valueField : 'value',
				displayField : 'text',
				value : '20',
				editable : false,
				width : 85
	});
	var number = parseInt(app.pagesize_combo.getValue());
	app.pagesize_combo.on("select", function(comboBox){
				app.ptb.pageSize = parseInt(comboBox.getValue());
				number = parseInt(comboBox.getValue());
				app.ds_sysconfig.reload({
							params : {
								start : 0,
								limit : app.ptb.pageSize
							}
						});
	});
	app.ptb = new Ext.PagingToolbar({
		pageSize:app.limit,
		store:app.ds_sysconfig,
		displayInfo:true,
		displayMsg:'第 {0} - {1} 条  共 {2} 条',
		emptyMsg:'没有记录',
		items : ['-', '&nbsp;&nbsp;', app.pagesize_combo]
	});
		
	app.grid = new Ext.grid.GridPanel({
		title : '文章管理',
		iconCls : 'icon-plugin',
		region : 'center',
		loadMask : {
			msg : '数据加载中...'
		},
	    cm: app.cm_sysconfig,
	    ds: app.ds_sysconfig,
	    width:document.body.clientWidth-50,
	    height:document.body.clientHeight-50,
        autoScroll: true,
        viewConfig: {
            forceFit:true
        },
 		plugins: expander,
		sm:app.sm,
		tbar : [app.btn_add_code,'-',app.update_code_btn,'-',app.search_comb_queyrCol_code,'-', app.text_search_code], //'-',app.btn_search_code
		bbar : app.ptb
	});
	
	app.grid.addListener('rowdblclick',function(grid, rowIndex){
				if(grid.getSelectionModel().isSelected(rowIndex)){
					var record = app.grid.getSelectionModel().getSelected();
				}
	});

    app.grid.render('div-sysconfig');
    
});