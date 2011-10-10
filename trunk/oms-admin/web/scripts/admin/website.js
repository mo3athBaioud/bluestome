var app = {};
Ext.onReady(function(){
  	Ext.BLANK_IMAGE_URL = project + '/resources/images/default/s.gif';
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    Ext.QuickTips.init();
	app.limit = 25;
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
									createResultWin(obj);
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
		disabled:false
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
													limit:app.limit
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
													limit:app.limit
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
	
	app.btn_invate = new Ext.Button({
		text : '邀请好友',
		iconCls : 'icon-del',
		disabled:false
	});
	
	app.btn_export_xls = new Ext.Button({
		text : '导出文件',
		disabled:false,
		handler:function(){
				app.ds_data.load({
					params:{
						start:0,
						limit:app.limit
					}
				});
		}
	});
	
	app.btn_save_code = new Ext.Button({
		text : '保存',
		disabled:false,
		iconCls : 'icon-accept',
		handler : function(){
			var values =app.text_phone_number.getValue();
			var records = app.grid.getSelectionModel().getSelections();
			if(records.length == 0 ){
				Ext.Msg.show({
					title : '提示',
					msg:'请选择需要修改的记录',
					buttons : Ext.Msg.OK,
					icon : Ext.Msg.ERROR
				});
			}else{
				
			}
		}
	});

	app.searchcode = function() {
		var values =app.text_phone_number.getValue();
		if(null == values || '' ==  values){
			Ext.Msg.show({
				title : '系统提示',
				msg : '请输入需要查询的网站父ID！',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.ERROR
			});
			return false;
		}
		app.ds_data.load({
			params : {
				'entity.parentId':values,
				start:0,
				limit:app.limit
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
		baseParams:{},
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
			'entity.parentId':app.text_phone_number.getValue(),
			start:0,
			limit:app.limit
		}
	});
	
	app.ptb = new Ext.PagingToolbar({
		pageSize:app.limit,
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
//        autoScroll: true,
        viewConfig: {
            forceFit:true
        },
		sm:app.sm,
		//,'-',app.btn_create,'-',app.btn_dissolve,'-','-',app.btn_export_xls
		tbar : ['标题：','-',app.text_phone_number,app.btn_search_code,'-',app.btn_bug,'-',app.btn_create,'-',app.btn_delete,'-',app.btn_enabled,'-',app.btn_disabled],
		bbar : app.ptb
	});
	
	app.grid.addListener('rowdblclick',function(grid, rowIndex){
		if(grid.getSelectionModel().isSelected(rowIndex)){
			Ext.Msg.show({
				title : '系统提示',
				width:200,
				msg : '显示数据详情',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.INFO
			});
		}
	});

    app.grid.render('grid-div');
});

function createResultWin(obj){
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
						fieldLabel : '页面大小',
						name : 'entity.startDate',
						value:obj.plength
					},{
						fieldLabel : '响应时间',
						name : 'entity.endDate',
						value:obj.times+'ms'
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
