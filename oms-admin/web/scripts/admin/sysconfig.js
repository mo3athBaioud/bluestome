var app = {};
Ext.onReady(function(){
  	Ext.BLANK_IMAGE_URL = project + '/resources/images/default/s.gif';
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    Ext.QuickTips.init();
    app.qp = {};
	app.qp.limit = 20;
    app.sm = new Ext.grid.CheckboxSelectionModel();
    
    app.cm_utp = new Ext.grid.ColumnModel({
	    columns:[
				app.sm,
		        {header: "ID", width: 50, sortable: true, dataIndex: 'id'},
		        {header: "类型", width: 50, sortable: true, dataIndex: 'type'},
		        {header: "关键字", width: 250, sortable: true, dataIndex: 'key'},
		        {header: "值", width: 70, sortable: true, dataIndex: 'value'},
		        {header: "状态", width: 70, sortable: true, dataIndex: 'status',renderer:function(v){
		        	if(v == 1){
		        		return '<font color=blue>可用</font>';
		        	}else if(v == 0){
		        		return '<font color=red>不可用</font>';
		        	}else{
		        		return '<font color=yellow>未知</font>';
		        	}
		        }},
		        {header: "创建时间", width: 130, sortable: true, dataIndex: 'createtime'},
		        {header: "修改时间", width: 130, sortable: true, dataIndex: 'modifytime'}
	    ]
    });
    
	app.btn_search_code = new Ext.Button({
		text : '查询',
		disabled:false,
		iconCls : 'icon-search',
		handler : function(){
			app.searchcode();
		}
	});
	
	app.btn_create = new Ext.Button({
		text : '创建',
		iconCls:'icon-add',
		disabled:false,
		handler:function(){
			app.data_form.form.reset();
			
			app.data_form.form.findField('mtype').setValue('add');
			app.data_form.form.findField('entity.id').setValue('');
			app.data_form.form.findField('entity.key').setValue('');
			app.data_form.form.findField('entity.value').setValue('');
			app.data_form.form.findField('entity.type').setValue('');
			app.data_form.form.findField('entity.remarks').setValue('');
			app.data_form.form.findField('entity.status').setValue('');
//			app.data_form.form.findField('entity.createtime').setValue('');
//			app.data_form.form.findField('entity.modifytime').setValue('');
			Ext.getCmp('data_form_reset').show();
			var win = Ext.getCmp('add_win');
			win.setTitle("创建系统配置");
			win.show();
		}
	});
	
	app.btn_edit = new Ext.Button({
		text : '编辑',
		iconCls:'icon-edit',
		disabled:false,
		handler:function(){
			if(app.grid.getSelectionModel().getSelected()){
				Ext.getCmp('data_form_reset').hide();
				app.data_form.form.findField('mtype').setValue('edit');
				var record = app.grid.getSelectionModel().getSelected();
				
				app.data_form.form.findField('entity.id').setValue(record.get('id'));
				app.data_form.form.findField('entity.key').setValue(record.get('key'));
				app.data_form.form.findField('entity.value').setValue(record.get('value'));
				app.data_form.form.findField('entity.type').setValue(record.get('type'));
				app.data_form.form.findField('entity.remarks').setValue(record.get('remarks'));
				app.data_form.form.findField('entity.status').setValue(record.get('status'));
				app.data_form.form.findField('entity.createtime').setValue(record.get('createtime'));
				app.data_form.form.findField('entity.modifytime').setValue(record.get('modifytime'));
				
				var win = Ext.getCmp('add_win');
				win.setTitle("编辑系统配置");
				win.show();
			}else{
				Ext.Msg.show({
					title : '系统提示',
					msg : '请选择需要编辑的站点!',
					buttons : Ext.Msg.OK,
					icon : Ext.MessageBox.ERROR
				});
			}
		}
	});
	
	app.btn_disabled = new Ext.Button({
		text : '禁用',
		iconCls : 'icon-delete',
		disabled:false,
		handler:function(){
			var records = app.grid.getSelectionModel().getSelections();
			if(records.length == 0){
				Ext.Msg.show({
					title : '系统提示',
					msg : '请选择需要禁用的记录!',
					buttons : Ext.Msg.OK,
					icon : Ext.MessageBox.ERROR
				});
			}else{
				var ids = [];
				for(i = 0 ; i < records.length ; i ++){
					var status = records[i].get('status');
					if(status != 1){
						continue;
					}
					ids.push(records[i].get('id'))
				}
				
				if(ids.length == 0){
					Ext.Msg.show({
						title : '系统提示',
						msg : '没有可禁用的记录，请检查记录是否无效!',
						buttons : Ext.Msg.OK,
						icon : Ext.MessageBox.ERROR
					});
					return;
				}
				
				Ext.Msg.confirm('提示', '你确定禁用['+ids.length+']条记录?', function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
							url : project+'/admin/sysconfig!disable.action',
							params : {
								ids : ids
							},
							success:function(response,option){
								var obj = Ext.util.JSON.decode(response.responseText);
								if(obj.success){
									Ext.Msg.show({
										title : '系统提示',
										msg : obj.msg,
										buttons : Ext.Msg.OK,
										fn:function(){
											app.ds_data.removeAll();
											app.ds_data.load({
												params:{
													start:0,
													limit:app.qp.limit
												}
											})
										},
										icon : Ext.MessageBox.INFO
									});
								}else{
									Ext.Msg.show({
										title : '系统提示',
										msg : obj.msg,
										buttons : Ext.Msg.OK,
										icon : Ext.MessageBox.ERROR
									});
								}
							},
			                failure:function(response,option){
								Ext.Msg.show({
									title : '系统提示',
									msg : '服务器内部错误',
									buttons : Ext.Msg.OK,
									icon : Ext.MessageBox.ERROR
								});
			                }
						})
					}
				})
			}
		}
	});
	
	app.btn_enabled = new Ext.Button({
		text : '启用',
		iconCls : 'icon-accept',
		disabled:false,
		handler:function(){
			var records = app.grid.getSelectionModel().getSelections();
			if(records.length == 0){
				Ext.Msg.show({
					title : '系统提示',
					msg : '请选择需要启用的记录!',
					buttons : Ext.Msg.OK,
					icon : Ext.MessageBox.ERROR
				});
			}else{
				var ids = [];
				for(i = 0 ; i < records.length ; i ++){
					var status = records[i].get('status');
					if(status != 0){
						continue;
					}
					ids.push(records[i].get('id'))
				}
				
				if(ids.length == 0){
					Ext.Msg.show({
						title : '系统提示',
						msg : '没有可启用的记录，请检查记录是否无效!',
						buttons : Ext.Msg.OK,
						icon : Ext.MessageBox.ERROR
					});
					return;
				}
				
				Ext.Msg.confirm('提示', '你确定启用['+ids.length+']条记录?', function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
							url : project+'/admin/sysconfig!enable.action',
							params : {
								ids : ids
							},
							success:function(response,option){
								var obj = Ext.util.JSON.decode(response.responseText);
								if(obj.success){
									Ext.Msg.show({
										title : '系统提示',
										msg : obj.msg,
										buttons : Ext.Msg.OK,
										fn:function(){
											app.ds_data.removeAll();
											app.ds_data.load({
												params:{
													start:0,
													limit:app.qp.limit
												}
											})
										},
										icon : Ext.MessageBox.INFO
									});
								}else{
									Ext.MessageBox.alert('提示',obj.msg);
								}
							},
			                failure:function(response,option){
			                	Ext.MessageBox.alert('提示','服务器内部错误!')
			                }
						})
					}
				})
			}
		}
	});
	
	app.btn_delete = new Ext.Button({
		text : '删除',
		iconCls:'icon-cancel',
		disabled:false,
		handler:function(){
			Ext.Msg.show({
				title : '系统提示',
				msg : '该功能暂未开放!',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.ERROR
			});
		}
	});
	
	app.data_form = new Ext.FormPanel({
		id:'add_form',
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
		url:project+'/admin/sysconfig!save.cgi',
		frame: true,
		fileUpload:true,
		defaultType : 'textfield',
		items : [
			{
				xtype:'hidden',
				name : 'mtype',
				value:'add'
			},{
				xtype:'hidden',
				fieldLabel : '站点ID',
				name : 'entity.id'
			},{
				xtype:'combo',
				fieldLabel : '网站类型',
				hiddenName : 'entity.type',
		        valueField: 'id',
		        displayField: 'name',
		        triggerAction:'all',
		        mode: 'local',
		        store: new Ext.data.SimpleStore({
		            fields: ['id','name'],
		            data: [['SYS','系统'],['USER','用户'],['OTHER','其他']]
		        }),
	            editable:false,
				emptyText : '请选择!',
				allowBlank:false
			},{ 
				fieldLabel : '关键字',
				name : 'entity.key',
	            vtype:'beta',
				maxLength:32,
				allowBlank:false
			},{ 
				fieldLabel : '值',
				name : 'entity.value',
	            vtype:'alphanum',
				maxLength:32,
				allowBlank:false
			},{
				xtype: 'textarea',
				fieldLabel : '介绍',
				name : 'entity.remarks'
			},{
				//下拉选择框
				xtype:'combo',
				fieldLabel : '状态',
				hiddenName:'entity.status',
		        valueField: 'id',
		        displayField: 'name',
		        triggerAction:'all',
		        mode: 'local',
		        store: new Ext.data.SimpleStore({
		            fields: ['id','name'],
		            data: [[1,'可用'],[0,'禁用']]
		        }),
	            editable:false,
				emptyText : '请选择!',
				allowBlank:false
			},{ 
				xtype:'hidden',
				name:'entity.modifytime'
			},{ 
				xtype:'hidden',
				name:'entity.createtime'
			}	
		],
		buttonAlign : 'center',
		minButtonWidth : 60,
		buttons : [
			{
				id:'dbm_form_save',
				text : '保存',
				iconCls:'icon-accept',
				handler : function(btn) {
					var frm = Ext.getCmp('add_form').form;
					var action = frm.findField('mtype').getValue();
					if (frm.isValid()) {
						frm.submit({
							waitTitle : '请稍候',
							waitMsg : '正在提交表单数据,请稍候...',
							success : function(form, action) {
								if(action.result.success){
						 			Ext.Msg.show({
										title : '系统提示',
										msg : '保存成功!',
										buttons : Ext.Msg.OK,
										fn:function(){
											app.ds_data.removeAll();
											app.ds_data.load({
												params : {
													start : 0,
													limit : app.qp.limit
												}
											});
										},
										icon : Ext.MessageBox.INFO
									});
									app.data_form.form.reset();
									var win = Ext.getCmp('add_win');
									win.hide();
								}else{
						 			Ext.Msg.show({
										title : '系统提示',
										msg : action.result.msg,
										buttons : Ext.Msg.OK,
										icon : Ext.MessageBox.ERROR
									});
								}
							},
							failure : function(form, action){
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
				id:'data_form_reset',
				text : '重置',
				iconCls:'icon-arrow_refresh_small',
				handler : function() {
					app.data_form.form.reset();
				}
			}, {
				iconCls:'icon-cancel',
				text : '取消',
				handler : function() {
					app.data_form.form.reset();
					var win = Ext.getCmp('add_win');
					win.hide();
			}
		}]
	});
	
	app.add_win = new Ext.Window({
		id:'add_win',
		title:'窗口',
		iconCls:'icon-add',
		width:500,
		resizable : false,
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
	
	var queryConditionPanel = new Ext.form.FormPanel({
		border: false,
		layout: 'form', 
		width: '100%', 
		height: 40,
		autoScroll: false,
		renderTo:'grid-query',
		bodyStyle: 'padding:10px',
		frame : false,
		items : [ 
			new Ext.Panel({
				border: false,  
		        layout: 'column',  
		        items: [  
		            new Ext.Panel({  
		                columnWidth: .2,  
		                layout: 'form',
		                border: false,  
		                style: 'text-align: left; ',
		      	        labelWidth: 80,  
		                labelAlign: 'right',  
		                items: [  
		                    {  
		                    	fieldLabel: '类型',
								name: 'entity.type',  
								anchor: '80%',
								xtype:'combo',
						        valueField: 'id',
						        displayField: 'name',
						        triggerAction:'all',
						        mode: 'local',
						        store: new Ext.data.SimpleStore({
						            fields: ['id','name'],
						            data: [[null,'请选择'],['SYS','系统'],['USER','用户'],['OTHER','其他']]
						        }),
					            editable:false,
								listeners : {
									'select': function() {
										if(queryConditionPanel.form.isValid()){
											app.qp = getComps2Object(queryConditionPanel);
											app.qp.start = 0;
											app.qp.limit = 20;
											Ext.apply(app.ds_data, {
												baseParams: app.qp
											});
											app.ds_data.removeAll();
											app.ds_data.load();
										}
									}
								}
		                    }  
		                ]  
		            }),
		            new Ext.Panel({  
		                columnWidth: .2,  
		                layout: 'form',
		                border: false,  
		                style: 'text-align: left; ',
		      	        labelWidth: 80,  
		                labelAlign: 'right',  
		                items: [  
		                    {  
		                    	fieldLabel: '关键字',
								name: 'entity.key',  
								xtype:'textfield',
								vtype:'beta',
								anchor: '80%',
								listeners : {
									'specialkey' : function(field, e) {
										if (e.getKey() == Ext.EventObject.ENTER) {
											if(queryConditionPanel.form.isValid()){
												app.qp = getComps2Object(queryConditionPanel);
												app.qp.start = 0;
												app.qp.limit = 20;
												Ext.apply(app.ds_data, {
													baseParams: app.qp
												});
												app.ds_data.removeAll();
												app.ds_data.load();
											}
										}
									}
								}
		                    }  
		                ]  
		            }),
		            new Ext.Panel({  
		                columnWidth: .2,  
		                layout: 'form',
		                border: false,  
		                style: 'text-align: left; ',
		      	        labelWidth: 80,  
		                labelAlign: 'right',  
		                items: [  
		                    {  
		                    	fieldLabel: '值',
								name: 'entity.value',  
								xtype:'textfield',
								vtype:'alphanum',
								anchor: '80%',
								listeners : {
									'specialkey' : function(field, e) {
										if (e.getKey() == Ext.EventObject.ENTER) {
											if(queryConditionPanel.form.isValid()){
												app.qp = getComps2Object(queryConditionPanel);
												app.qp.start = 0;
												app.qp.limit = 20;
												Ext.apply(app.ds_data, {
													baseParams: app.qp
												});
												app.ds_data.removeAll();
												app.ds_data.load();
											}
										}
									}
								}
		                    }  
		                ]  
		            }),
		            new Ext.Panel({  
		                columnWidth: .2,  
		                layout: 'form',
		                border: false,  
		                style: 'text-align: left; ',
		      	        labelWidth: 80,  
		                labelAlign: 'right',  
		                items: [  
		                    {  
		                    	fieldLabel: '状态',
								name: 'entity.status',  
								anchor: '80%',
								xtype:'combo',
						        valueField: 'id',
						        displayField: 'name',
						        triggerAction:'all',
						        mode: 'local',
						        store: new Ext.data.SimpleStore({
						            fields: ['id','name'],
						            data: [[null,'请选择'],[0,'不可用'],[1,'可用']]
						        }),
					            editable:false,
								listeners : {
									'select': function() {
										if(queryConditionPanel.form.isValid()){
											app.qp = getComps2Object(queryConditionPanel);
											app.qp.start = 0;
											app.qp.limit = 20;
											Ext.apply(app.ds_data, {
												baseParams: app.qp
											});
											app.ds_data.removeAll();
											app.ds_data.load();
										}
									}
								}
		                    }  
		                ]  
		            }),
		            new Ext.Panel({  
		                columnWidth: .2,  
		                layout: 'form',
		                border: false,  
		                style: 'text-align: left; ',
		      	        labelWidth: 80,  
		                labelAlign: 'right',  
		                items: [
							new Ext.Button({
								text : '查询',
								iconCls : 'icon-search',
								handler : function() {
									if(queryConditionPanel.form.isValid()){
										app.qp = getComps2Object(queryConditionPanel);
										app.qp.start = 0;
										app.qp.limit = 20;
										Ext.apply(app.ds_data, {
											baseParams: app.qp
										});
										app.ds_data.removeAll();
										app.ds_data.load();
									}
								}
							})
		                ]  
				    })
		        ]  
		    })
		]
	});
	
	app.ds_data = new Ext.data.Store({
		url : project + '/admin/sysconfig!search.cgi',
		reader : new Ext.data.JsonReader({
			totalProperty : 'total',
			root : 'records'
		}, [{name : 'id',type : 'int'
		}, {name : 'type',type : 'string'
		}, {name : 'key',type : 'string'
		}, {name : 'value',type : 'string'
		}, {name : 'remarks',type : 'string'
		}, {name : 'status',type : 'int'
		}, {name : 'modifytime',type : 'string'
		}, {name : 'createtime',type : 'string'
		}])
	});
	
	app.ds_data.load({
		params:{
			start:0,
			limit:app.qp.limit
		}
	});
	
	app.ptb = new Ext.PagingToolbar({
		pageSize:app.qp.limit,
		store:app.ds_data,
		displayInfo:true,
		displayMsg:'第 {0} - {1} 条  共 {2} 条',
		emptyMsg:'没有记录'
	});
		
	app.grid = new Ext.grid.GridPanel({
		title : name,
		iconCls : 'icon-application_view_columns',
		region : 'center',
		loadMask : {
			msg : '数据加载中...'
		},
	    cm: app.cm_utp,
		ds: app.ds_data,
	    height:400,
        autoScroll: true,
        viewConfig: {
            forceFit:true
        },
		sm:app.sm,
		tbar : [app.btn_create,'-',app.btn_edit,'-',app.btn_enabled,'-',app.btn_disabled,'-',app.btn_delete],
		bbar : app.ptb
	});
	
	app.grid.addListener('rowdblclick',function(grid, rowIndex){
		if(grid.getSelectionModel().isSelected(rowIndex)){
			var record = grid.getSelectionModel().getSelected();
			if(record.get('status') == '1' ){
				Ext.Msg.show({
					title : '系统提示',
					msg : '由于显示界面问题，暂时屏蔽该功能!',
					buttons : Ext.Msg.OK,
					icon : Ext.Msg.ERROR
				});
			}
		}
	});

    app.grid.render('grid-div');
});