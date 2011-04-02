var app = {};

Ext.onReady (function(){
	
	var limit = 20;	
	var sm = new Ext.grid.CheckboxSelectionModel();
	var opLog = Ext.data.Record.create([
		{name : 'id',mapping : 'D_ID',type : 'int'}, 
		{name : 'opDt',mapping : 'D_OPDT',type : 'string'}, 
		{name : 'opDetail',mapping : 'D_OPDETAIL',type : 'string'}, 
		{name : 'opObject',mapping : 'D_OPOBJECT',type : 'string'}, 
		{name : 'operatorId',mapping : 'D_OPERATORID',type : 'int'}, 
		{name : 'logLevel',mapping : 'D_LOGLEVEL',type : 'int'}, 
		{name : 'opResult',mapping : 'D_OPRESULT',type : 'string'}, 
		{name : 'opValue',	mapping : 'D_OPVALUE',	type : 'string'}, 
		{name : 'readStatus',mapping : 'D_READSTATUS',type : 'int'},
		{name : 'isDel',mapping : 'D_ISDEL',type : 'int'}
	]);
	
	
	var cm_opLog = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),sm, 
	{
		header : '系统日志记录时间',
		width : 130,
		dataIndex : 'D_OPDT',
		sortable : true
	}
	, {
		header : '操作对象',
		width : 100,
		dataIndex : 'D_OPOBJECT',
		menuDisabled : true
	}
	, {
		header : '操作员ID',
		width : 60,
		dataIndex : 'D_OPERATORID',
		menuDisabled : true
	}
	,{
		header : '操作员名称',
		width : 120,
		dataIndex : 'D_OPERATOR_NAME',
		menuDisabled : true
	}
	, {
		header : '日志级别',
		width : 60,
		dataIndex : 'D_LOGLEVEL',
		menuDisabled : true
	},
	 {
		header : '操作结果',
		width : 60,
		dataIndex : 'D_OPRESULT',
		menuDisabled : true
	},
	{
		header : '是否删除',
		width : 60,
		dataIndex : 'D_ISDEL',
		renderer : function(v) {
			var x = parseInt(v);
			switch(x) {
				case 1:
					return "<span style='color:blue;font-weight:bold;'>未删除</span>";
				case 2:
					return "<span style='color:red;font-weight:bold;'>已删除</span>";
				default: 
					return "<span style='color:yellow;font-weight:bold;'>未知</span>";
			}
		},
		menuDisabled : true
	}]);
	
//	var ds_opLog = new Ext.data.Store({
//		proxy : new Ext.data.HttpProxy({
//			url : 'oplog.action'
//		}),
//		reader : new Ext.data.JsonReader({
//			totalProperty : 'count',
//			root : 'opLog'
//		}, [{name : 'D_ID',type : 'int'},
//		{name : 'D_OPDT',type : 'string'},
//		{name : 'D_OPDETAIL',type : 'string'},
//		{name : 'D_OPOBJECT',type : 'string'},
//		{name : 'D_OPERATORID',type : 'int'},
//		{name : 'D_LOGLEVEL',type : 'int'},
//		{name : 'D_OPRESULT',type : 'string'},
//		{name : 'D_OPVALUE',	type : 'string'},
//		{name : 'D_ISDEL',type : 'int'}])
//	});
	
	var ds_opLog = new Ext.data.ArrayStore({
        fields: [
		{name : 'D_ID',type : 'int'},
		{name : 'D_OPDT',type : 'string'},
		{name : 'D_OPDETAIL',type : 'string'},
		{name : 'D_OPOBJECT',type : 'string'},
		{name : 'D_OPERATORID',type : 'int'},
		{name : 'D_LOGLEVEL',type : 'int'},
		{name : 'D_OPRESULT',type : 'string'},
		{name : 'D_OPVALUE',	type : 'string'},
		{name : 'D_ISDEL',type : 'int'},
		{name : 'D_OPERATOR_NAME',type : 'string'}
		]
    });
    
	app.data = [
		[1,'2011-04-01 10:11:11','新增正式终端','tbl_terminal',1,1,'成功','insert',1,'admin'],
		[2,'2011-04-01 11:03:03','新增正式终端','tbl_terminal',1,1,'成功','insert',1,'bluestome'],
		[3,'2011-04-01 12:11:11','新增正式终端','tbl_terminal',1,1,'成功','insert',1,'user'],
		[4,'2011-04-01 14:32:23','新增正式终端','tbl_terminal',1,1,'成功','insert',1,'user'],
		[5,'2011-04-01 14:54:56','新增正式终端','tbl_terminal',1,1,'成功','insert',1,'bluestome'],
		[6,'2011-04-01 14:59:04','新增正式终端','tbl_terminal',1,1,'成功','insert',1,'bluestome']
	];
	
	var btn_del_opLog = new Ext.Button({
		text : '删除',
		iconCls : 'icon-del',
		handler : function() {
			var records = grid_opLog.getSelectionModel().getSelections();
			if(records.length == 0 ){
				Ext.MessageBox.alert('提示','请选择需要删除的记录');
			}else{
				var ids = [];
				for(var i = 0;i<records.length;i++){
					ids.push(records[i].get('D_ID'));
				}
				Ext.Msg.confirm('确认删除', '你确定删除该条记录?', function(btn) {
					if (btn == 'yes') {
						/**
						Ext.Ajax.request({
							url : 'deloplog.action',
							params : {
								id : ids
							},
							success : function(response) {
								var obj = Ext.util.JSON.decode(response.responseText)
								if(obj.success){
									Ext.MessageBox.alert('提示',obj.msg);
									ds_opLog.load({
										params:{
											start:0,
											limit:limit
										}
									})
								}else{
									Ext.MessageBox.alert('提示',obj.msg);
								}
							},
							failure : function(response) {
								var obj = Ext.util.JSON.decode(response.responseText);
								Ext.Msg.show({
									title : '提示',
									msg : obj.msg,
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
	});
	
	var btn_undel_opLog = new Ext.Button({
		text : '解除删除',
		iconCls : 'icon-edit',
		handler : function() {
			var records = grid_opLog.getSelectionModel().getSelections();
			if(records.length == 0 ){
				Ext.MessageBox.alert('提示','请选择需要解除删除的记录');
			}else{
				var ids = [];
				for(var i = 0;i<records.length;i++){
					ids.push(records[i].get('D_ID'));
				}
				Ext.Msg.confirm('确认解除删除', '你确定解除删除该条记录?', function(btn) {
					if (btn == 'yes') {
						/**
						Ext.Ajax.request({
							url : 'undeloplog.action',
							params : {
								id : ids
							},
							success : function(response) {
								var obj = Ext.util.JSON.decode(response.responseText)
								if(obj.success){
									Ext.MessageBox.alert('提示',obj.msg);
									ds_opLog.load({
										params:{
											start:0,
											limit:limit
										}
									})
								}else{
									Ext.MessageBox.alert('提示',obj.msg);
								}
							},
							failure : function(response) {
								var obj = Ext.util.JSON.decode(response.responseText);
								Ext.Msg.show({
									title : '提示',
									msg : obj.msg,
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
	});
	
	var btn_show_oplog_detail = new Ext.Button({
		text : '查看详情',
		iconCls : 'icon-edit',
		handler : function() {
			if(grid_opLog.getSelectionModel().getSelected()){
				var records = grid_opLog.getSelectionModel().getSelected();
				new Ext.Window({
					id:'detail_oplog_win',
					title:'查看日志详情',
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
							fieldLabel:'日志记录时间',
							readOnly:true,
							value:records.get('D_OPDT')
						},{
							fieldLabel:'日志详细描述',
							xtype:'textarea',
							readOnly:true,
							value:records.get('D_OPDETAIL')
						},{
							fieldLabel:'操作对象',
							readOnly:true,
							value:records.get('D_OPOBJECT')
						},{
							fieldLabel:'操作员ID',
							readOnly:true,
							value:records.get('D_OPERATORID')
						},{
							fieldLabel:'操作员名称',
							readOnly:true,
							value:records.get('D_OPERATOR_NAME')
						},{
							fieldLabel:'日志级别',
							readOnly:true,
							value:records.get('D_LOGLEVEL')
						},{
							fieldLabel:'操作结果',
							readOnly:true,
							value:records.get('D_OPRESULT')
						},{
							fieldLabel:'操作值',
							readOnly:true,
							value:records.get('D_OPVALUE')
								
						},{
							fieldLabel:'记录状态',
							readOnly:true,
							value:(records.get('D_ISDEL')==1?'未删除':'已删除')
						}],
					buttonAlign:'right',
					minButtonWidth:60,
					buttons:[
						{ 
							text : '关闭',
							handler : function() {
									Ext.getCmp('detail_oplog_win').close();
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
		
	});
	
	var search_comb_queyrCol_opLog =  new Ext.form.ComboBox({
				fieldLabel : '查询条件',
				id : 'column',
				hiddenName : 'colName',
				valueField : 'id',
				displayField : 'name',
				mode:'local',
				store : new Ext.data.SimpleStore({
					data : [
							['opDetail', '操作详情'],
							['opObject', '操作对象'],
							['opResult','操作结果'],
							['opValue','操作值']
				    ],
					fields : ['id', 'name']
				}),	
				selectOnFocus : true,
				editable : false,
				allowBlank : true,
				triggerAction : 'all',
				emptyText : '查询的字段名'
	});
	
	var searchopLog = function() {
			var name =text_search_opLog.getValue();
			var colName = search_comb_queyrCol_opLog.getValue();
				Ext.Ajax.request({
					url : 'oplog.action',
					params : {
						colName : colName,
						value : name
					},
					success:function(response){
						var obj = Ext.util.JSON.decode(response.responseText);
						if(obj.success){
							ds_opLog.load({
								params : {
									start : 0,
									limit : limit
								}
							})
						}else{
							Ext.MessageBox.alert('提示',obj.msg);
						}
					},
	                failure:function(){
							Ext.MessageBox.alert('提示','系统出错');
	                }
				})
	}
	
	var btn_search_opLog = new Ext.Button({
		text : '操作日志查询',
		iconCls : 'icon-search',
		handler : function(){
			ds_opLog.loadData(app.data);			
		}
	});
	
	
	var text_search_opLog = new Ext.form.TextField({
		name : 'textSearchopLog',
		width : 200,
		emptyText : '请输入查询条件！',
		listeners : {
			'specialkey' : function(field, e) {
				if (e.getKey() == Ext.EventObject.ENTER) {
//					searchopLog()
					ds_opLog.loadData(app.data);			
				}
			}
		}
	});
	
//	var ds_opLog = new Ext.data.Store({
//		proxy : new Ext.data.HttpProxy({
//			url : 'oplog.action'
//		}),
//		reader : new Ext.data.JsonReader({
//			totalProperty : 'count',
//			root : 'opLog'
//		}, [{name : 'D_ID',type : 'int'},
//		{name : 'D_OPDT',type : 'string'},
//		{name : 'D_OPDETAIL',type : 'string'},
//		{name : 'D_OPOBJECT',type : 'string'},
//		{name : 'D_OPERATORID',type : 'int'},
//		{name : 'D_LOGLEVEL',type : 'int'},
//		{name : 'D_OPRESULT',type : 'string'},
//		{name : 'D_OPVALUE',	type : 'string'},
//		{name : 'D_ISDEL',type : 'int'}])
//	});
	
	var grid_opLog = new Ext.grid.GridPanel({
		title : '操作日志管理',
		iconCls : 'icon-plugin',
		region : 'center',
		autoHeight:true,
		width:800,
		autoScroll: true,
		loadMask : {
			msg : '数据加载中...'
		},
		cm : cm_opLog,
		ds : ds_opLog,
		sm : sm,
		tbar : [btn_undel_opLog,'-',btn_show_oplog_detail,'-',btn_del_opLog, '-',search_comb_queyrCol_opLog,'-', text_search_opLog, btn_search_opLog],
		bbar : new Ext.PagingToolbar({
			pageSize : limit,
			store : ds_opLog,
			displayInfo : true,
			displayMsg : '第 {0} - {1} 条  共 {2} 条',
			emptyMsg : "没有记录"
		})
	});
	
	grid_opLog.render('oplog-context')

});
