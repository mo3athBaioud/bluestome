var app = {};
Ext.onReady(function(){
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    Ext.QuickTips.init();
	app.limit = 15;
    app.sm = new Ext.grid.CheckboxSelectionModel();
    
	var website =Ext.data.Record.create([
		{name:'id',mapping:'d_id',type:'int'},
		{name:'parentId',mapping:'d_parent_id',type:'int'},
		{name:'parentName',mapping:'d_parent_name',type:'string'},
		{name:'type',mapping:'d_web_type',type:'string'},
		{name:'url',mapping:'d_web_url',type:'string'},
		{name:'name',mapping:'d_web_name',type:'string'},
		{name:'remarks',mapping:'d_remarks',type:'string'},
		{name:'count',mapping:'d_count',type:'int'},
		{name:'subcount',mapping:'d_sub_count',type:'int'},
		{name:'status',mapping:'d_status',type:'int'},
		{name:'modifytime',mapping:'d_modify_time',type:'int'},
		{name:'createtime',mapping:'d_createtime',type:'string'}
	]);
	
    var expander = new Ext.grid.RowExpander({
        tpl : new Ext.Template(
            '<p><b>父站点ID:</b>{d_parent_id}</p>',
            '<p><b>子站点数量:</b>{d_sub_count}</p>',
            '<p><b>文章统计:</b>{d_count}</p>',
            '<p><b>修改时间:</b>{d_modify_time}</p>',
            '<p><b>备注:</b><br/>{d_remarks}</p>',
            '<p><b>网址:</b><br/>{d_web_url}<a href="{d_web_url}" target="_blank"><img src="'+project+'/images/world_go.png" alt="{d_web_name}"/></a></p>'
        )
    });
    
	//new Ext.grid.RowNumberer(),
	//expander
	//new Ext.grid.RowNumberer(),
    //app.sm, 
    app.cm_website = new Ext.grid.ColumnModel([
    	expander,
        {id:'ID',header: "ID", width: 50, sortable: true, dataIndex: 'd_id'},
        {header: "类型", width: 50,
        	renderer : function(value) {
				return '<a href="{d_url}"><img src="'+project+'/images/world_link.png"/></a>';
			}
        },
        {header: "父站点名称", width: 70, sortable: true, dataIndex: 'd_parent_name'},
        {header: "网站名称", width: 150, sortable: false, dataIndex: 'd_web_name'},
        {header: "网站类型", width: 100, sortable: true, dataIndex: 'd_web_type',renderer:function(value){
        	var v = parseInt(value);
        	switch(v){
        		case 1:
					return '图片类网站';
				case 2:
					return '信息类网站';
				default:
					return '未识别网站';
        	}
        }},
        {header: "记录状态", width: 100, sortable: true, dataIndex: 'd_status',renderer : function(v){var x = parseInt(v);
	        switch(x) {
				case 1:
					return "<span style='color:blue;font-weight:bold;'>启用</span>";
				case 0:
					return "<span style='color:red;font-weight:bold;'>停用</span>";
				default: 
					return "<span style='color:yellow;font-weight:bold;'>未知</span>";
			}
		},
		menuDisabled : true},
        {header: "创建时间", width: 130, sortable: true,dataIndex: 'd_createtime'}
    ]);
    
    app.cm_website.defaultSortable = false;
	app.ds_website = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : project+'/website/website.cgi'
		}),
		reader : new Ext.data.JsonReader({
			totalProperty : 'count',
			root : 'website'
		}, [{name : 'd_id',type : 'int'
		}, {name : 'd_parent_id',type : 'int'
		}, {name : 'd_parent_name',type : 'string'
		}, {name : 'd_web_url',type : 'string'
		}, {name : 'd_web_name',type : 'string'
		}, {name : 'd_web_type',type : 'int'
		}, {name : 'd_count',type : 'int'
		}, {name : 'd_sub_count',type : 'int'
		}, {name : 'd_status',type : 'int'
		}, {name : 'd_remarks',type : 'string'
		}, {name : 'd_createtime',type : 'string'
		}, {name : 'd_modify_time',type : 'string'
		}])
	});
	
	app.search_comb_queyrCol_code = new Ext.form.ComboBox({
				fieldLabel : '查询条件',
				id : 'column',
				hiddenName : 'colName',
				valueField : 'id',
				displayField : 'name',
				mode:'local',
				store : new Ext.data.SimpleStore({
					data : [
							['id','ID'],
							['parentId','父站点ID'],
							['name', '站点名称'],
							['type','站点类型'],
							['status','站点状态']
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
			url : project+'/website/website.cgi',
			params : {
				colName : app.colName,
				value : app.values
			},
			success:function(response,option){
				var obj = Ext.util.JSON.decode(response.responseText);
				if(obj.success){
					app.ds_website.load({params : {start:0,limit:app.limit}});
				}else{
		 			Ext.Msg.show({
						title : '系统提示',
						msg : '未找到"'+app.values+'"相关数据',
						buttons : Ext.Msg.OK,
						icon : Ext.MessageBox.ERROR
					});
				}
			},
            failure:function(){
	 			Ext.Msg.show({
					title : '系统提示',
					msg : '服务器内部错误!',
					buttons : Ext.Msg.OK,
					icon : Ext.MessageBox.ERROR
				});
            }
		})
	};
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
						Ext.Ajax.request({
							url : project+'/website/disable.cgi',
							params : {
								ids : ids
							},
							success:function(response,option){
								var obj = Ext.util.JSON.decode(response.responseText);
								if(obj.success){
									Ext.MessageBox.alert("提示", obj.msg);
									app.ds_website.load({
										params:{
											start: 0,
											limit :app.limit
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
//					Ext.MessageBox.alert('系统调试信息',' >> 获取的ID为:'+records[i].get('d_id'));
				}
//				for(i=0;i<ids.length;i++){
//					Ext.MessageBox.alert('系统调试信息',' >> 获取注入的ID为:'+records[i].get('d_id'));
//				}
				Ext.Msg.confirm('启用站点', '你确定启用所选站点?', function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
							url : project+'/website/enable.cgi',
							params : {
								ids : ids
							},
							success:function(response,option){
								var obj = Ext.util.JSON.decode(response.responseText);
								if(obj.success){
									Ext.MessageBox.alert("提示", obj.msg);
									app.ds_website.load({
										params:{
											start: 0,
											limit :app.limit
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
				});
				/**
				**/
			}
		}
	});

	app.window_add = new Ext.Window({
		layout : 'fit',
		title : '添加',
		iconCls : 'icon-world_add',
		width : 400,
		height:300,
		resizable : false,
		modal : true,
		closeAction : 'hide',
		listeners : {
			'hide' : function() {
				this.setTitle('添加');
			}
		},
		items : [new Ext.FormPanel({
			labelWidth : 80,
			labelAlign : 'right',
			url : project+'/website/insert.cgi',
			border : false,
			baseCls : 'x-plain',
			bodyStyle : 'padding:5px 5px 0',
			anchor : '100%',
			defaults : {
				width : 233,
				msgTarget : 'side'
			},
			defaultType : 'textfield',
			items : [
			{
				fieldLabel : '站点名称',
				name : 'website.name',
				allowBlank : false,
				maxLength : 30
			},{
				fieldLabel : '站点地址',
				name : 'website.url',
				allowBlank : false,
				maxLength : 256
			},{
				xtype:'combo',
				fieldLabel : '站点类型',
				id:'add_website_type',
				hiddenName:'website.type',
                valueField: "id",
                displayField: "name",
                mode: 'local',
                store: new Ext.data.SimpleStore({
                    fields: ["id","name"],
                    data: [[1,'图片类型'], [2,'信息类型']]
                }),
                triggerAction:'all',
                blankText: '请选择站点类型',
                emptyText: '请选择站点类型',
                editable:false,
				allowBlank : false
			},{
				//下拉框
				xtype:'combo',
				fieldLabel : '父站点ID',
				id:'add_parentId',
				hiddenName:'website.parentId',
                mode:'remote',
				store:new Ext.data.Store({
					proxy:new Ext.data.HttpProxy({ 
						url:project+'/website/root.cgi'
					}),
					reader : new Ext.data.JsonReader({
						root : 'website'
					}, [{name : 'd_id',type : 'int'},
						{name : 'd_web_name',type : 'string'}
					])
				}),
				selectOnFocus:true,
				triggerAction:'all',
                valueField: "d_id",
                displayField: "d_web_name",
                blankText: '请选择父站点',
                emptyText: '请选择父站点',
                editable:false,
				allowBlank : false
			},{
				//下拉选择框
				xtype:'combo',
				fieldLabel : '状态',
				id:'add_website_status',
				hiddenName:'website.status',
                valueField: "id",
                displayField: "name",
                mode: 'local',
                store: new Ext.data.SimpleStore({
                    fields: ["id","name"],
                    data: [[1,'可用'], [0,'停用']]
                }),
                triggerAction:'all',
                blankText: '请选择状态',
                emptyText: '请选择状态',
                editable:false,
				allowBlank : false
			},{
				xtype: 'textarea',
				fieldLabel : '备注',
				name : 'website.remarks',
				allowBlank : true,
				maxLength : 512
			}],
			buttonAlign : 'right',
			minButtonWidth : 60,
			buttons : [{
				text : '添加',
				handler : function(btn) {
					var frm = this.ownerCt.form;
					if (frm.isValid()) {
						btn.disable();
						var dnfield = frm.findField('website.name');
						frm.submit({
							waitTitle : '请稍候',
							waitMsg : '正在提交表单数据,请稍候...',
							success : function(form, action) {
					 			Ext.Msg.show({
									title : '系统提示',
									msg : '添加成功!',
									buttons : Ext.Msg.OK,
									fn:function(){
										frm.reset();
										app.ds_website.load({params : {start : 0,limit : app.limit}});
										btn.enable();
										app.window_add.hide();
									},
									icon : Ext.MessageBox.INFO
								});
							},
							failure : function(){
								Ext.Msg.show({
									title : '错误提示',
									msg : '"' + dnfield.getValue() + '" ' + '名称可能已经存在或者您没有添加数据的权限!',
									buttons : Ext.Msg.OK,
//									fn : function() {
//										dnfield.focus(true);
//										btn.enable();
//									},
									icon : Ext.Msg.ERROR
								})
							}
						})
					}
 				}
			}, {
				text : '重置',
				handler : function() {
					this.ownerCt.form.reset();
				}
			}, {
				text : '取消',
				handler : function() {
					this.ownerCt.ownerCt.hide();
					this.ownerCt.form.reset();
				}
			}]
		})]
	});

	
    var dataAction = [new Ext.Action({
        id: 'add_website',
        text: '添加',
		iconCls : 'icon-world_add',
		disabled:false,
        handler: function(){
            app.window_add.show();
        }
    }), new Ext.Action({
        id: 'edit_website',
        text: '编辑',
        disabled: true,
		iconCls : 'icon-world_edit',
        handler: function(){
			if(app.grid.getSelectionModel().getSelected()){
				var record = app.grid.getSelectionModel().getSelected();
				var updateWin = new Ext.Window({ 
					layout : 'fit',
					id:'update_website_win',
					title : '编辑',
					iconCls:'icon-world_edit',
					width : 450,
					height : 440,
					resizable : false,
					modal : true,
					closeAction : 'close',
					scope : this,
					items : [new Ext.FormPanel({
						labelWidth : 80,
						labelAlign : 'right',
						url : project+'/website/update.cgi',
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
							id : 'website.id',
							name : 'website.id',
							readOnly:true,
							value : record.get('d_id')
						},{
							fieldLabel : '站点名称',
							name : 'website.name',
							value : record.get('d_web_name')
						},{
							fieldLabel : '站点地址',
							name : 'website.url',
							readOnly:true,
							value : record.get('d_web_url')
						},{
							xtype:'combo',
							fieldLabel : '站点类型',
							id:'update_website_type',
							hiddenName:'website.type',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'图片类型'], [2,'信息类型']]
			                }),
			                blankText: '请选择站点类型',
			                emptyText : '当前状态:'+(record.get('d_web_type') == 1?'图片类型':'信息类型'),
			                editable:false,
							allowBlank : false,
							value : record.get('d_web_type')					
						},{
							//下拉框
							xtype:'combo',
							fieldLabel : '父站点ID',
							id:'update_website_parentId',
							hiddenName:'website.parentId',
			                mode:'remote',
							store:new Ext.data.Store({
								proxy:new Ext.data.HttpProxy({ 
									url:project+'/website/root.cgi'
								}),
								reader : new Ext.data.JsonReader({
									root : 'website'
								}, [{name : 'd_id',type : 'int'},
									{name : 'd_web_name',type : 'string'}
								])
							}),
							selectOnFocus:true,
							triggerAction:'all',
			                valueField: 'd_id',
			                displayField: 'd_web_name',
			                blankText: '请选择父站点',
			                emptyText: '请选择父站点',
			                editable:false,
							allowBlank : false,
							value : record.get('d_parent_id')
						},{
							fieldLabel : '时间',
							xtype:'hidden',
							name : 'website.createtime',
							value:record.get('d_createtime')
						},{
							//下拉选择框
							xtype:'combo',
							fieldLabel : '状态',
							id : 'update_website_status',
							hiddenName:'website.status',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'可用'], [0,'停用']]
			                }),
			                editable:false,
			                blankText: '请选择状态',
							emptyText : '当前状态:'+(record.get('d_status') == 1?'启用':'停用'),
							allowBlank : false,
							value : record.get('d_status')
						},{
							xtype: 'textarea',
							fieldLabel : '备注',
							name : 'website.remarks',
							allowBlank : true,
							maxLength : 512,
							value : record.get('d_remarks')
						}],
						buttonAlign : 'right',
						minButtonWidth : 60,
						buttons : [{
							text : '更新',
							handler : function(btn) {
								var frm = this.ownerCt.form;
								if (frm.isValid()) {
									btn.disable();
									var dnfield = frm.findField('website.name');
									frm.submit({
										waitTitle : '请稍候',
										waitMsg : '正在提交表单数据,请稍候...',
										success : function(form, action) {
											Ext.Msg.show({
												title : '系统提示',
												msg : '修改站点1"' + dnfield.getValue() + '"成功!',
												buttons : Ext.Msg.OK,
												icon : Ext.MessageBox.INFO
											});
											app.ds_website.load({params : {start : 0,limit : app.limit}});
											dnfield.reset();
											btn.enable();
											var update_website_win = Ext.getCmp('update_website_win');
											update_website_win.close();
										},
										failure : function() {
											Ext.Msg.show({
												title : '错误提示',
												msg : '"' + dnfield.getValue() + '" ' + '名称可能已经存在或者您没有更新数据的权限!',
												buttons : Ext.Msg.OK,
												icon : Ext.Msg.ERROR
											})
										}
									})
								}
							}
						}, {
							text : '重置',
							handler : function() {
								this.ownerCt.form.reset();
							}
						}, {
							text : '取消',
							handler : function() {
								this.ownerCt.ownerCt.close();
								this.ownerCt.form.reset();
							}
						}]
					})]
				}).show();
			}else{
	 			Ext.Msg.show({
					title : '系统提示',
					msg : '请选择要修改行!',
					buttons : Ext.Msg.OK,
					icon : Ext.MessageBox.ERROR
				});
			}
        }
    }), new Ext.Action({
        id: 'view_website',
        text: '查看',
        disabled: true,
		iconCls : 'icon-world',
        handler: function(){
			if(app.grid.getSelectionModel().getSelected()){
				var record = app.grid.getSelectionModel().getSelected();
				var url = record.get('d_web_url');
				window.open(url,'_blank');
			}else{
//        		Ext.Msg.alert('温馨提示','此功能暂正在开发!');
                Ext.Msg.alert('系统提示', '请选择站点!！');
			}
        }
    }),new Ext.Action({
        id: 'delimage',
        text: '删除',
		iconCls : 'icon-world_delete',
        disabled: true,
        handler: function(){
			var records = app.grid.getSelectionModel().getSelections();
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
					ids.push(records[i].get('d_id'));
				}
				Ext.Msg.confirm('确认删除', '你确定删除所选记录?', function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
							url : project+'/website/delete.cgi',
							params : {
								ids : ids
							},
							success:function(response,option){
								var obj = Ext.util.JSON.decode(response.responseText);
								if(obj.success){
									Ext.Msg.show({
										title : '提示',
										msg:obj.msg,
										buttons : Ext.Msg.OK,
										icon : Ext.Msg.INFO
									});
									app.ds_website.load({
										params:{
											start:0,
											limit : app.limit
										}
									});
								}else{
									Ext.Msg.show({
										title : '提示',
										msg : obj.msg,
										buttons : Ext.Msg.OK,
										icon : Ext.Msg.ERROR
									});
								}
							},
			                failure:function(response,option){
								Ext.Msg.show({
									title : '提示',
									msg : '系统发生错误!',
									buttons : Ext.Msg.OK,
									icon : Ext.Msg.ERROR
								});
			                }
						});
					}
				});
			}
	   }
    })];
                
	
	app.ds_website.load({
		params : {
			start : 0,
			limit : app.limit
		}
	});
	
	app.ds_website.on('beforeload',function(){
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
				app.ds_website.reload({
							params : {
								start : 0,
								limit : app.ptb.pageSize
							}
						});
	});
	
	app.ptb = new Ext.PagingToolbar({
		pageSize:app.limit,
		store:app.ds_website,
		displayInfo:true,
		displayMsg:'第 {0} - {1} 条  共 {2} 条',
		emptyMsg:'没有记录',
		items : ['-', '&nbsp;&nbsp;', app.pagesize_combo]
	});
		
	app.grid = new Ext.grid.GridPanel({
		title : '网站管理',
		iconCls : 'icon-plugin',
		region : 'center',
		loadMask : {
			msg : '正在加载站点数据，请等待...'
		},
	    cm: app.cm_website,
	    ds: app.ds_website,
		width:850,
 		height:600,
        autoScroll: true,
        viewConfig: {
            forceFit:true
        },
 		plugins: expander,
		sm: app.sm,
		tbar : ['-', dataAction[0],'-',btn_enable,'-',btn_disable,'-', dataAction[1], '-',dataAction[2],'-', dataAction[3],'-',app.search_comb_queyrCol_code,'-', app.text_search_code], //'-',app.btn_search_code
		bbar : app.ptb
	});
	
	var update = function(record){
		var updateWin = new Ext.Window({ 
			title : '编辑',
			iconCls:'icon-world_edit',
			width : 450,
			height : 440,
			resizable : false,
			autoHeight : true,
			modal : true,
			closeAction : 'close',
			scope : this,
			items : [new Ext.FormPanel({
				labelWidth : 80,
				labelAlign : 'right',
				url : project+'/website/update.cgi',
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
					id : 'website.id',
					name : 'website.id',
					readOnly:true,
					value : record.get('d_id')
				},{
					fieldLabel : '站点名称',
					name : 'website.name',
					value : record.get('d_web_name')
				},{
					fieldLabel : '站点地址',
					name : 'website.url',
					readOnly:true,
					value : record.get('d_web_url')
				},{
					xtype:'combo',
					fieldLabel : '站点类型',
					id:'update_website_type',
					hiddenName:'website.type',
	                valueField: 'id',
	                displayField: 'name',
	                triggerAction:'all',
	                mode: 'local',
	                store: new Ext.data.SimpleStore({
	                    fields: ['id','name'],
	                    data: [[1,'图片类型'], [2,'信息类型']]
	                }),
	                emptyText : '当前状态:'+(record.get('d_status') == 1?'图片类型':'信息类型'),
	                editable:false,
	                value : record.get('d_web_type')
				},{
					//下拉框
					xtype:'combo',
					fieldLabel : '父站点ID',
					id:'update_website_parentId',
					hiddenName:'website.parentId',
	                mode:'remote',
					store:new Ext.data.Store({
						proxy:new Ext.data.HttpProxy({ 
							url:project+'/website/root.cgi'
						}),
						reader : new Ext.data.JsonReader({
							root : 'website'
						}, [{name : 'd_id',type : 'int'},
							{name : 'd_web_name',type : 'string'}
						])
					}),
					selectOnFocus:true,
					triggerAction:'all',
	                valueField: 'd_id',
	                displayField: 'd_web_name',
	                emptyText: '请选择父站点',
	                editable:false,
					allowBlank : false,
					value : record.get('d_parent_id')
				},{
					fieldLabel : '时间',
					xtype:'hidden',
					name : 'website.createtime',
					value:record.get('d_createtime')
				},{
					//下拉选择框
					xtype:'combo',
					fieldLabel : '状态',
					id : 'update_website_status',
					hiddenName:'website.status',
	                valueField: 'id',
	                displayField: 'name',
	                triggerAction:'all',
	                mode: 'local',
	                store: new Ext.data.SimpleStore({
	                    fields: ['id','name'],
	                    data: [[1,'可用'], [0,'停用']]
	                }),
					emptyText : '当前状态:'+(record.get('d_status') == 1?'启用':'停用'),
	                editable:false,
					allowBlank : false,
					value : record.get('d_status')
				},{
					xtype: 'textarea',
					fieldLabel : '备注',
					name : 'website.remarks',
					allowBlank : true,
					maxLength : 512,
					value : record.get('d_remarks')
				}],
				buttonAlign : 'right',
				minButtonWidth : 60,
				buttons : [{
					text : '更新',
					handler : function(btn) {
						var frm = this.ownerCt.form;
						if (frm.isValid()) {
							btn.disable();
							var dnfield = frm.findField('website.name');
							frm.submit({
								waitTitle : '请稍候',
								waitMsg : '正在提交更新数据,请稍候...',
								success : function(form, action) {
									Ext.Msg.show({
										title : '系统提示',
										msg : '修改站点"' + dnfield.getValue() + '"成功!',
										buttons : Ext.Msg.OK,
										icon : Ext.MessageBox.INFO
									});
									app.ds_website.load({params : {start : 0,limit : app.limit}});
								},
								failure : function() {
									Ext.Msg.show({
										title : '错误提示',
										msg : '"' + dnfield.getValue() + '" ' + '名称可能已经存在或者您没有更新数据的权限!',
										buttons : Ext.Msg.OK,
										icon : Ext.Msg.ERROR
									})
								}
							})
						}
					}
				}, {
					text : '重置',
					handler : function() {
						this.ownerCt.form.reset();
					}
				}, {
					text : '取消',
					handler : function() {
						this.ownerCt.ownerCt.close();
						this.ownerCt.form.reset();
					}
				}]
			})]
		});
		updateWin.show();
	}
	
	//双击监听器 编辑站点
	app.grid.addListener('rowdblclick',function(grid, rowIndex){
		if(grid.getSelectionModel().isSelected(rowIndex)){
			var record = app.grid.getSelectionModel().getSelected();
			update(record);
			/**
			var url = record.get('d_web_url');
			var arturl = project+'/pages/articles/article.jsp?id='+record.get('d_id');
			window.open(arturl,'_blank');
			**/
		}
	});
	
	//点击/选择监听器
	app.grid.addListener('rowclick',function(grid, rowIndex){
		if(grid.getSelectionModel().isSelected(rowIndex)){
            dataAction[1].enable();
            dataAction[2].enable();
            dataAction[3].enable();
            btn_enable.enable();
            btn_disable.enable();
		}else{
            dataAction[1].disable();
            dataAction[2].disable();
            dataAction[3].disable();
            btn_enable.disable();
            btn_disable.disable();
		}
	});
                            
    app.grid.render('div-website');
    
});