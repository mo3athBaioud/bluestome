var app = {};
Ext.onReady(function(){
	
	var limit = 20;
	var sm = new Ext.grid.CheckboxSelectionModel();
	var loginLog = Ext.data.Record.create([{
		name : 'id',mapping : 'D_ID',type : 'int'}, 
		{name : 'loginName',mapping : 'D_LOGINNAME',type : 'string'}, 
		{name : 'loginDt',mapping : 'D_LOGINDT',type : 'string'}, 
		{name : 'loginIp',mapping : 'D_LOGINIP',type : 'string'}, 
		{name : 'status',mapping : 'D_STATUS',type : 'string'}, 
		{name : 'loginDesc',mapping : 'D_LOGINDESC',type : 'string'}, 
		{name : 'readStatus',mapping : 'D_READSTATUS',type : 'int'}
	])
	
//	var ds_loginLog = new Ext.data.Store({
//		proxy : new Ext.data.HttpProxy({
//			url : 'loginlog.action'
//		}),
//		reader : new Ext.data.JsonReader({
//			totalProperty : 'count',
//			root : 'loginLog'
//		}, [{name : 'D_ID',type : 'int'},
//		{name : 'D_LOGINNAME',type : 'string'},
//		{name : 'D_LOGINDT',type : 'string'},
//		{name : 'D_LOGINIP',type : 'string'},
//		{name : 'D_STATUS',type : 'string'},
//		{name : 'D_LOGINDESC',type : 'string'}
//		])
//	});
	
	var ds_loginLog = new Ext.data.ArrayStore({
        fields: [
		{name : 'D_ID',type : 'int'},
		{name : 'D_LOGINNAME',type : 'string'},
		{name : 'D_LOGINDT',type : 'string'},
		{name : 'D_LOGINIP',type : 'string'},
		{name : 'D_STATUS',type : 'string'},
		{name : 'D_LOGINDESC',type : 'string'}
		]
    });
    
    app.data = [
    	[1,'admin','2011-04-02 09:00:00','127.0.0.1','成功',''],
    	[1,'bluestome','2011-04-02 10:56:30','172.18.1.25','成功',''],
    	[1,'bluestome','2011-04-02 10:59:45','172.18.1.110','成功',''],
    	[1,'admin','2011-04-02 20:04:12','172.18.1.125','成功','']
    ];
	
	var cm_loginlog = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),sm, 
	{
		header : '登录名',
		autoWidth:true,
		dataIndex : 'D_LOGINNAME',
		sortable : true
	}, {
		header : '登录时间',
		width : 130,
		dataIndex : 'D_LOGINDT',
		menuDisabled : true
	}, {
		header : '登录的IP',
		autoWidth:true,
		dataIndex : 'D_LOGINIP',
		menuDisabled : true
	}, {
		header : '登录状态:',
		width : 60,
		dataIndex : 'D_STATUS',
		menuDisabled : true
	}, {
		header : '登录描述:',
		autoWidth:true,
		dataIndex : 'D_LOGINDESC',
		menuDisabled : true,
		renderer : function(v) {
			if(null == v || '' == v){
					return "<span style='color:blue;font-weight:bold;'>暂无描述</span>";
			}else{
					return v;
			}
		},
		menuDisabled : true
		
	}
	]);
	
	var btn_del_loginLog = new Ext.Button({
		text : '删除登陆日志',
		iconCls : 'icon-del',
		handler : function() {
			var records = grid_loginLog.getSelectionModel().getSelections();
			if(records.length == 0 ){
				Ext.MessageBox.alert('提示','请选择需要删除的记录!')
			}else{
				var ids = [];
				for(var i = 0 ;i < records.length;i++){
					ids.push(records[i].get('D_ID'));
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
								url : 'delloginlog.action',
								params : {
									id : ids
								},
								success : function(response,option) {
									var obj = Ext.util.JSON.decode(response.responseText);
									if(obj.success){
										Ext.MessageBox.alert('提示',obj.msg);
										ds_loginLog.load({
											params:{
												start:0,
												limit:limit
											}									
										})
									}else{
										Ext.MessageBox.alert('提示',obj.msg);
									}
								},
								failure : function() {
									Ext.Msg.show({
										title : '错误提示',
										msg : '删除时发生错误!',
										buttons : Ext.Msg.OK,
										icon : Ext.Msg.ERROR
									})
								}
							})
							**/
						}
					})
			}
		}
	});
	
	var search_comb_queyrCol_loginLog = new Ext.form.ComboBox({
				fieldLabel : '查询条件',
				id : 'column',
				hiddenName : 'colName',
				valueField : 'id',
				displayField : 'name',
				mode:'local',
				store : new Ext.data.SimpleStore({
					data : [
	//						['id','系统ID'],
							['loginName', '登陆名'],
							['loginIp', '登陆的IP'],
							['status','登陆状态'],
							['loginDesc','登陆描述']
				    ],
					fields : ['id', 'name']
				}),	
				selectOnFocus : true,
				editable : false,
				allowBlank : true,
				triggerAction : 'all',
				emptyText : '查询的字段名'
	});
	
	var text_search_logicLog = new Ext.form.TextField({
		name : 'search_loginlog_text',
		width : 200,
		emptyText : '请输入查询条件！',
		listeners : {
			'specialkey' : function(field, e) {
				if (e.getKey() == Ext.EventObject.ENTER) {
//					searchloginLog();
					ds_loginLog.loadData(app.data);
				}
			}
		}
	});
	
	var searchloginLog = function() {
			var values =text_search_logicLog.getValue();
			var colName = search_comb_queyrCol_loginLog.getValue();
				Ext.Ajax.request({
					url : 'loginlog.action',
					params : {
						colName : colName,
						value : values
					},
					success:function(response,option){
						var obj = Ext.util.JSON.decode(response.responseText);
						if(obj.success){
							Ext.MessageBox.alert('提示',obj.msg);
							ds_loginLog.load({
								params : {
									start : 0,
									limit : limit
								}
							})
						}else{
							Ext.MessageBox.alert('提示',obj.msg);
						}
					},
	                failure:function(response,option){
							Ext.MessageBox.alert('提示','服务器端异常');
	                }
				})
	}
	
	var btn_search_logicLog = new Ext.Button({
		text : '查询',
		iconCls : 'icon-search',
		handler : function(){
			ds_loginLog.loadData(app.data);
		}
	});
	
//	ds_loginLog.load({
//		params : {
//			start : 0,
//			limit : limit,
//			colName : '',
//			value : ''
//		}
//	});
	
	var grid_loginLog = new Ext.grid.GridPanel({
		title : '登陆日志管理',
		iconCls : 'icon-plugin',
		region : 'center',
		autoHeight:true,
		width:800,
		loadMask : {
			msg : '数据加载中...'
		},
		cm : cm_loginlog,
		ds : ds_loginLog,
		sm : new Ext.grid.CheckboxSelectionModel(),
		tbar : [btn_del_loginLog,'-',search_comb_queyrCol_loginLog,'-', 
				text_search_logicLog, btn_search_logicLog],
		bbar : new Ext.PagingToolbar({
			pageSize : limit,
			store : ds_loginLog,
			displayInfo : true,
			displayMsg : '第 {0} - {1} 条  共 {2} 条',
			emptyMsg : "没有记录"
		})
	});
	grid_loginLog.render('loginlog-context');
})
