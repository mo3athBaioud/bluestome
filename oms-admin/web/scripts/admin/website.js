var app = {};
Ext.onReady(function(){
  	Ext.BLANK_IMAGE_URL = project + '/resources/images/default/s.gif';
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    Ext.QuickTips.init();
    app.values = null;
    app.qp = {};
	app.qp.limit = 20;
    app.sm = new Ext.grid.CheckboxSelectionModel();
    
    app.cm_utp = new Ext.grid.ColumnModel([
		app.sm,
        {header: "ID", width: 100, sortable: true, dataIndex: 'id'},
        {header: "父ID", width: 100, sortable: true, dataIndex: 'parentId'},
        {header: "名称", width: 100, sortable: true, dataIndex: 'name'},
        {header: "类型", width: 100, sortable: true, dataIndex: 'type',renderer:function(v){
        	var x = parseInt(v);
        	var note = "图片";
        	switch(x){
        		case 1:
        			note="图片";
        			break;
        		case 2:
        			note="文章";
        			break;
        		case 3:
        			note="未知";
        			break;
        		default:
        			break;
        	}
        	return note;
        }},
        {header: "链接", width: 100, sortable: true, dataIndex: 'url',renderer:function(v){
    		return '<a href='+v+'  target="_blank">访问</a>';
        }},
        {header: "状态", width: 100, sortable: true, dataIndex: 'status',renderer:function(v){
        	var x = parseInt(v);
        	var note = "<font color=green>可用</font>";
        	switch(x){
        		case 0:
        			note="<font color=red>不可用</font>";
        			break;
        		case 1:
        			break;
        		case 2:
        			note="<font color=yellow>未知</font>";
        			break;
        		default:
        			break;
        	}
        	return note;
        }},
        {header: "收录时间", width: 100, sortable: true, dataIndex: 'createtime'},
    ]);
    
	app.btn_search_code = new Ext.Button({
		text : '查询',
		disabled:false,
		iconCls : 'icon-search',
		handler : function(){
			app.searchcode();
		}
	});
	
	app.btn_bug = new Ext.Button({
		text : '页面测试',
		iconCls : 'icon-bug',
		disabled:false,
		handler:function(){
			if(app.grid.getSelectionModel().getSelected()){
				var record = app.grid.getSelectionModel().getSelected();
				//TODO 测试返回的内容 1.页面大小 2.响应时间
				Ext.Msg.confirm('提示', '你确定测试该页面吗?', function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
							url : project+'/admin/website!debug.action',
							params : {
								id : record.get('id'),
								turl: record.get('url')
							},
							success:function(response,option){
								var obj = Ext.util.JSON.decode(response.responseText);
								if(obj.success){
									createResultWin(record.get('url'),obj);
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
				});
			}else{
				Ext.Msg.show({
					title : '系统提示',
					msg : '请选择需要测试的站点!',
					buttons : Ext.Msg.OK,
					icon : Ext.MessageBox.ERROR
				});
			}
		}
	});
	
	app.btn_create = new Ext.Button({
		text : '创建',
		iconCls : 'icon-add',
		disabled:false,
		handler:function(){
			app.data_form.form.reset();
			
			/**
			app.data_form.form.findField('mtype').setValue('add');
			app.data_form.form.findField('entity.id').setValue('');
			app.data_form.form.findField('entity.parentId').setValue('');
			app.data_form.form.findField('entity.name').setValue('');
			app.data_form.form.findField('entity.type').setValue('');
			app.data_form.form.findField('entity.remarks').setValue('');
			app.data_form.form.findField('entity.status').setValue('');
			app.data_form.form.findField('entity.createtime').setValue('');
			app.data_form.form.findField('entity.modifytime').setValue('');
			**/
			
			Ext.getCmp('data_form_reset').show();
			var win = Ext.getCmp('add_win');
			win.setTitle("创建站点");
			win.show();
		}
	});
	
	app.btn_edit = new Ext.Button({
		text : '编辑',
		iconCls : 'icon-world_edit',
		disabled:false,
		handler:function(){
			if(app.grid.getSelectionModel().getSelected()){
				Ext.getCmp('data_form_reset').hide();
				app.data_form.form.findField('mtype').setValue('edit');
				var record = app.grid.getSelectionModel().getSelected();
				
				app.data_form.form.findField('entity.id').setValue(record.get('id'));
				app.data_form.form.findField('entity.parentId').setValue(record.get('parentId'));
				app.data_form.form.findField('entity.name').setValue(record.get('name'));
				app.data_form.form.findField('entity.type').setValue(record.get('type'));
				app.data_form.form.findField('entity.remarks').setValue(record.get('remarks'));
				app.data_form.form.findField('entity.status').setValue(record.get('status'));
				app.data_form.form.findField('entity.createtime').setValue(record.get('createtime'));
				app.data_form.form.findField('entity.modifytime').setValue(record.get('modifytime'));
				
				var win = Ext.getCmp('add_win');
				win.setTitle("编辑站点");
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
	
	app.btn_delete = new Ext.Button({
		text : '删除',
		iconCls : 'icon-cross',
		disabled:false
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
							url : project+'/admin/website!disable.action',
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
							url : project+'/admin/website!enable.action',
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
	
	var queryConditionPanel = new Ext.form.FormPanel({
		border: false,
		layout: 'form', 
		width: '100%', 
		height: 60,
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
		                    	fieldLabel: '站点名称',
								name: 'entity.name',  
								xtype:'textfield',
								anchor: '80%'
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
		                    	fieldLabel: '站点ID',
								name: 'entity.id',  
								xtype:'textfield',
								vtype:'integer',
								anchor: '80%'
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
		                    	fieldLabel: '站点父ID',
								name: 'entity.parentId',  
								xtype:'textfield',
								vtype:'integer',
								anchor: '80%'
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
					            editable:false
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
		                    	fieldLabel: '站点类型',
								name: 'entity.type', 
								anchor: '80%',
								xtype:'combo',
						        valueField: 'id',
						        displayField: 'name',
						        triggerAction:'all',
						        mode: 'local',
						        store: new Ext.data.SimpleStore({
						            fields: ['id','name'],
						            //[2,'解封'],
						            data: [[null,'请选择'],[1,'图片类型'],[2,'文章类型']]
						        }),
					            editable:false
		                    }  
		                ]  
		            })
		        ]  
		    }),
			new Ext.Panel({
				border: false,  
		        layout: 'form',  
                style: 'text-align: center; ',
				bodyStyle: 'padding:0 30px',
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
		url:project+'/admin/website!save.cgi',
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
				fieldLabel : '父站点',
				hiddenName:'entity.parentId',
		        valueField: 'id',
		        displayField: 'name',
		        triggerAction:'all',
				mode:'remote',
				store:new Ext.data.Store({
					proxy : new Ext.data.HttpProxy({
						url : project + '/admin/website!root.cgi?entity.parentId=0' 
					}),
					reader : new Ext.data.JsonReader({
						root : 'records'
					}, [
						{name : 'id',type : 'int'},
						{name : 'name',type : 'string'}
					])
				}),
	            editable:false,
				emptyText : '请选择!',
				allowBlank:false
			},{ 
				fieldLabel : '网站名称',
				name : 'entity.name',
				maxLength:32
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
		            data: [[1,'图片'],[2,'文章'],[3,'其他']]
		        }),
	            editable:false,
				emptyText : '请选择!',
				allowBlank:false
			},{
				xtype: 'textarea',
				fieldLabel : '网站介绍',
				name : 'entity.remarks'
			},{
				//下拉选择框
				xtype:'combo',
				fieldLabel : '网站状态',
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
				name:'entity.modifytime',
				value:'true'
			},{ 
				xtype:'hidden',
				name:'entity.createtime',
				value:'true'
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
	
	app.searchcode = function() {
		app.values = app.text_phone_number.getValue();
		if(null == app.values || '' ==  app.values){
			Ext.Msg.show({
				title : '系统提示',
				msg : '请输入需要查询的网站父ID！',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.ERROR
			});
			return false;
		}
		Ext.apply(app.ds_data, {
			baseParams: {
				'entity.parentId':app.values
			}
		});
		app.ds_data.removeAll();
		app.ds_data.load({
			params : {
				start : 0,
				limit : app.qp.limit
			}
		});
	};
	
	app.text_phone_number = new Ext.form.TextField({
		name : 'phone_number',
		width : 150,
		allowBlank:false,
		vtype:'integer',
		listeners : {
			'specialkey' : function(field, e) {
				if (e.getKey() == Ext.EventObject.ENTER) {
					app.searchcode();
				}
			}
		}
	});
	
	app.ds_data = new Ext.data.Store({
		url : project + '/admin/website!list.cgi',
		baseParams:{
			'entity.parentId':app.values
		},
		reader : new Ext.data.JsonReader({
			totalProperty : 'total',
			root : 'records'
		}, [{name : 'id',type : 'int'
		}, {name : 'name',type : 'string'
		}, {name : 'url',type : 'string'
		}, {name : 'type',type : 'int'
		}, {name : 'parentId',type : 'int'
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
	    height:500,
        viewConfig: {
            forceFit:true
        },
		sm:app.sm,
		//'标题：','-',app.text_phone_number,app.btn_search_code,'-',
		tbar : [app.btn_bug,'-',app.btn_create,'-',app.btn_edit,'-',app.btn_delete,'-',app.btn_enabled,'-',app.btn_disabled],
		bbar : app.ptb
	});
	
	app.grid.addListener('rowdblclick',function(grid, rowIndex){
		if(grid.getSelectionModel().isSelected(rowIndex)){
			var record = grid.getSelectionModel().getSelected();
			app.values = record.get('id');
			app.text_phone_number.setValue(app.values);
			app.searchcode();
			/**
			Ext.Msg.show({
				title : '系统提示',
				width:200,
				msg : '显示数据详情',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.INFO
			});
			**/
		}
	});

    app.grid.render('grid-div');
});

function createResultWin(url,obj){
	var win = new Ext.Window({
		id:'result_win',
		title:'测试结果',
		width:500,
		iconCls:'icon-bug_go',
		resizable : false,
		autoHeight : true,
		modal : true,
		closable:false,
		animCollapse : true,
		pageY : 20,
		pageX : document.body.clientWidth / 2 - 420 / 2,
		animateTarget : Ext.getBody(),
		constrain : true,
		items:[
			new Ext.FormPanel({
				id:'result_form',
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
				defaultType : 'textfield',
				items : [
					{
						fieldLabel : '网址',
						name : 'entity.Content-Type',
						value:url
					},{
						fieldLabel : '响应代码',
						name : 'entity.responseStatus',
						value:obj.object.responseStatus
					},{
						fieldLabel : '页面类型',
						name : 'entity.contentType',
						value:obj.object.contentType
					},{
						fieldLabel : '页面大小',
						name : 'entity.contentLength',
						value:obj.object.contentLength
					},{
						fieldLabel : '修改时间',
						name : 'entity.lastModified',
						value:obj.object.lastModified
					},{
						fieldLabel : '缓存时长',
						name : 'entity.cacheControl',
						value:obj.object.cacheControl
					},{
						fieldLabel : '响应时间',
						name : 'entity.endDate',
						value:'暂无'
					}		
				],
				buttonAlign : 'center',
				minButtonWidth : 60,
				buttons : [
					{
						iconCls:'icon-cancel',
						text : '关闭',
						handler : function() {
							Ext.getCmp('result_form').form.reset();
							var win = Ext.getCmp('result_win');
							win.close();
						}
					}
				]
			})
		]
	}).show();
}
