/**
 * 一级终端数据
 */
var app = {};
Ext.onReady(function(){
  	Ext.BLANK_IMAGE_URL = '/resource/image/ext/s.gif';
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    Ext.QuickTips.init();
	app.limit = 15;
	app.colName = "";
	app.values = "";
    app.sm = new Ext.grid.CheckboxSelectionModel();
	
	// 部门编号,部门名称,排序号
    app.data = [
        ['tbl_terminal','xcms','bdb','1','终端表'],
        ['tbl_operator','xcms','bdb','1','操作员表'],
        ['tbl_imei','xcms','bdb','1','IMEI表'],
        ['tbl_login_log','xcms','bdb','1','登录日志'],
        ['tbl_oplog','xcms','bdb','1','操作日志']
	];

    app.data01 = [
        ['tbl_terminal','xcms','bdb','1','终端表'],
        ['tbl_operator','xcms','bdb','1','操作员表'],
        ['tbl_imei','xcms','bdb','1','IMEI表']
	];

    app.data02 = [
        ['tbl_login_log','xcms','bdb','1','登录日志'],
        ['tbl_oplog','xcms','bdb','1','操作日志']
	];
	
	/**
    app.data03 = [
        ['0302','西北三路营业厅','1','03','营业厅'],
        ['0303','省政府机关综合楼','2','03','营业厅'],
        ['0304','咸阳新机场','3','03','营业厅'],
        ['0305','钟楼营业厅','4','03','营业厅']
	];
	**/ 
	
	/**
	app.utp = Ext.data.Record.create([{
		name : 'id',
		mapping : 'd_id',
		type : 'int'
	}, {
		name : 'acticleRealUrl',
		mapping : 'd_article_real_url',
		type : 'string'
	}, {
		name : 'acticleXmlUrl',
		mapping : 'd_article_xml_url',
		type : 'string'
	}, {
		name : 'articleUrl',
		mapping : 'd_acticle_url',
		type : 'string'
	}, {
		name : 'createTime',
		mapping : 'd_createtime',
		type : 'string'
	}, {
		name : 'intro',
		mapping : 'd_intro',
		type : 'string'
	}, {
		name : 'text',
		mapping : 'd_text',
		type : 'string'
	}, {
		name : 'title',
		mapping : 'd_title',
		type : 'string'
	}, {
		name : 'webId',
		mapping : 'd_web_id',
		type : 'int'
	}]);
    **/
    
    app.cm_utp = new Ext.grid.ColumnModel([
	    new Ext.grid.RowNumberer(),app.sm,
        {header: "类型", width: 80,
        	renderer : function(value) {
				return '<img src="/images/database_table.png" alt="数据库表" />';
			}
        },
        {header: "表名", width: 150, sortable: true, dataIndex: 'name'},
        {header: "所属用户", width: 100, sortable: true, dataIndex: 'user'},
        {header: "所属数据库", width: 150, sortable: true, dataIndex: 'database'},
        {
			header:"状态", 
			dataIndex:"status",
			width:100,
			renderer:function(v){
				var x = parseInt(v);
				switch(v){
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
		}
    ]);
    
	app.btn_search_code = new Ext.Button({
		text : '查询',
		iconCls : 'icon-search',
		handler : function(){
			app.searchcode();
		}
	});
	
	app.btn_add = new Ext.Button({
		text : '添加',
		iconCls : 'icon-add',
		handler : function(){
				var updateWin = new Ext.Window({
					id:'add_win',
					title : '添加扩展IMEI',
					iconCls:'icon-add',
					width : 500,
					resizable : false,
					autoHeight : true,
					modal : true,
					closeAction : 'close',
					animCollapse : true,
					pageY : 20,
					pageX : document.body.clientWidth / 2 - 420 / 2,
					animateTarget : Ext.getBody(),
					constrain : true,
					items : [
						new Ext.FormPanel({
							id:'add_form',
							labelWidth : 80,
							labelAlign : 'right',
							url : '/article/update.cgi',
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
								//下拉选择框
								xtype:'combo',
								fieldLabel : '所属数据库',
								hiddenName:'database',
				                valueField: 'id',
				                displayField: 'name',
				                triggerAction:'all',
				                mode: 'local',
				                store: new Ext.data.SimpleStore({
				                    fields: ['id','name'],
				                    data: [[1,'业务数据库'], [2,'日志数据库']]
				                }),
				                editable:false,
								emptyText : '请选择所属数据库!',
								allowBlank : false
							},{
								fieldLabel : '表名',
								name : 'name',
								allowBlank : false
							},{
								fieldLabel : '所属用户',
								name : 'users',
								allowBlank : false
							},{
								xtype:'textarea',
								fieldLabel : '备注',
								name : 'remark',
								allowBlank : false
							},{
								//下拉选择框
								xtype:'combo',
								fieldLabel : '状态',
								hiddenName:'status',
				                valueField: 'id',
				                displayField: 'name',
				                triggerAction:'all',
				                mode: 'local',
				                store: new Ext.data.SimpleStore({
				                    fields: ['id','name'],
				                    data: [[1,'可用'], [2,'不可用']]
				                }),
				                editable:false,
								emptyText : '请选择表状态!',
								allowBlank : false
							}],
							buttonAlign : 'center',
							minButtonWidth : 60,
							buttons : [{
								text : '保存',
								iconCls:'icon-accept',
								handler : function(btn) {
									var frm = Ext.getCmp('add_form').form;
									if (frm.isValid()) {
										var win = Ext.getCmp('add_win');
										win.close();
										Ext.Msg.show({
											title : '系统提示',
											msg : '添加成功!',
											buttons : Ext.Msg.OK,
											fn:function(){
												app.ds_utp.loadData(app.data);
											},
											icon : Ext.MessageBox.INFO
										});
									}
								}
							}, {
								buttonAlign : 'center',
								text : '重置',
								iconCls:'icon-arrow_refresh',
								handler : function() {
									Ext.getCmp('add_form').form.reset();
								}
							}, {
								buttonAlign : 'center',
								text : '取消',
								iconCls:'icon-cancel',
								handler : function() {
									var win = Ext.getCmp('add_win');
									win.close();
								}
							}]
						})
				]
			}).show();
		}
	});
	
	app.btn_edit = new Ext.Button({
		text : '修改',
		iconCls : 'icon-edit',
		handler : function(){
			if(app.grid.getSelectionModel().getSelected()){
				var record = app.grid.getSelectionModel().getSelected();
				var updateWin = new Ext.Window({
					id:'update_win',
					title : '编辑',
					iconCls:'icon-edit',
					width : 450,
					resizable : false,
					autoHeight : true,
					modal : true,
					closeAction : 'close',
					items : [new Ext.FormPanel({
						id:'update_form',
						labelWidth : 80,
						labelAlign : 'right',
						url : '/article/update.cgi',
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
								//下拉选择框
								xtype:'combo',
								fieldLabel : '所属数据库',
								hiddenName:'database',
				                valueField: 'id',
				                displayField: 'name',
				                triggerAction:'all',
				                mode: 'local',
				                store: new Ext.data.SimpleStore({
				                    fields: ['id','name'],
				                    data: [[1,'业务库'], [2,'日志库']]
				                }),
				                editable:false,
								emptyText : '当前上级部门为:"'+record.get('updept')+'"!',
								allowBlank : false
							},{
								fieldLabel : '表名',
								name : 'name',
								allowBlank : false,
								value:record.get('name')
							},{
								fieldLabel : '所属用户',
								name : 'user',
								allowBlank : false,
								value:record.get('user')
							},{
								fieldLabel : '备注',
								name : 'remark',
								allowBlank : false,
								value:record.get('remark')
							},{
								//下拉选择框
								xtype:'combo',
								fieldLabel : '状态',
								hiddenName:'status',
				                valueField: 'id',
				                displayField: 'name',
				                triggerAction:'all',
				                mode: 'local',
				                store: new Ext.data.SimpleStore({
				                    fields: ['id','name'],
				                    data: [[1,'可用'], [2,'不可用']]
				                }),
				                editable:false,
								emptyText : (record.get('status') == 1 ? "可用" : "不可用"),
								allowBlank : false
							}],
						buttonAlign : 'center',
						minButtonWidth : 60,
						buttons : [{
							text : '保存',
							iconCls:'icon-accept',
							handler : function(btn) {
								var frm = Ext.getCmp('update_form').form;
								if (frm.isValid()) {
									var win = Ext.getCmp('update_win');
									win.close();
									Ext.Msg.show({
										title : '系统提示',
										msg : '修改成功!',
										buttons : Ext.Msg.OK,
										fn:function(){
											app.ds_utp.loadData(app.data);
										},
										icon : Ext.MessageBox.INFO
									});
								}
							}
						}, {
							text : '重置',
							iconCls:'icon-arrow_refresh',
							buttonAlign : 'center',
							handler : function() {
								this.ownerCt.form.reset();
							}
						}, {
							text : '取消',
							iconCls:'icon-cancel',
							buttonAlign : 'center',
							handler : function() {
								var win = Ext.getCmp('update_win');
								win.close();
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
	});
	
	app.btn_del = new Ext.Button({
		text : '删除',
		iconCls : 'icon-del',
		handler : function(){
			var records = app.grid.getSelectionModel().getSelections();
			
			if(records.length == 0 ){
				Ext.Msg.show({
					title : '提示',
					msg:'请选择需要删除的记录',
					buttons : Ext.Msg.OK,
					icon : Ext.Msg.ERROR
				});
			}else{
				Ext.Msg.confirm('确认删除', '你确定删除所选记录?', function(btn) {
					if (btn == 'yes') {
						Ext.Msg.show({
							title : '提示',
							msg:'删除记录成功!',
							buttons : Ext.Msg.OK,
							fn : function() {
								app.ds_utp.loadData(app.data);
							},
							icon : Ext.Msg.INFO
						});
					}
				});
			}
		}
	});
	
	app.text_search_code = new Ext.form.TextField({
		name : 'textSearchcode',
		width : 150,
		listeners : {
			'specialkey' : function(field, e) {
				if (e.getKey() == Ext.EventObject.ENTER) {
					app.searchcode();
				}
			}
		}
	});

	app.text_imei_code = new Ext.form.TextField({
		name : 'text_imei',
		width : 150,
		listeners : {
			'specialkey' : function(field, e) {
				if (e.getKey() == Ext.EventObject.ENTER) {
					app.searchcode();
				}
			}
		}
	});
	
	app.text_tac_code = new Ext.form.TextField({
		name : 'text_tac',
		width : 150,
		listeners : {
			'specialkey' : function(field, e) {
				if (e.getKey() == Ext.EventObject.ENTER) {
					app.searchcode();
				}
			}
		}
	});
	
	app.searchcode = function() {
		/**
		app.colName = app.search_comb_queyrCol_code.getValue();
		app.values =app.text_search_code.getValue();
		Ext.Ajax.request({
			url : '/utp/utp.cgi',
			params : {
				colName : app.colName,
				value : app.values
			},
			success:function(response,option){
				var obj = Ext.util.JSON.decode(response.responseText);
				if(obj.success){
					app.ds_utp.load({params : {start:0,limit:app.limit}}); //,colName:app.colName,value:app.values
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
		});
		**/
		app.ds_utp.loadData(app.data);
	};
	
	/**	
	app.ds_utp = new Ext.data.Store({
		url : '/utp/utp.cgi',
		baseParams:{},
		reader : new Ext.data.JsonReader({
			totalProperty : 'count',
			root : 'utp'
		}, [{name : 'd_id',type : 'int'
		}, {name : 'd_web_id',type : 'int'
		}, {name : 'd_title',type : 'string'
		}, {name : 'd_acticle_url',type : 'string'
		}, {name : 'd_article_real_url',type : 'string'
		}, {name : 'd_article_xml_url',type : 'string'
		}, {name : 'd_text',type : 'string'
		}, {name : 'd_createtime',type : 'string'
		}, {name : 'd_intro',type : 'string'
		}])
	});
	**/
	
	//部门编号，部门名称,排序号，上级部门编号，上级部门名称
	app.ds_utp = new Ext.data.ArrayStore({
        fields: [
           {name: 'name',type:'string'},
           {name: 'database',type:'string'},
           {name: 'user',type:'string'},
           {name: 'status',type:'int'},
           {name: 'remark',type:'string'}
        ]
    });
	
//	app.ds_utp.loadData(app.data);
	
	app.ptb = new Ext.PagingToolbar({
		pageSize:app.limit,
		store:app.ds_utp,
		displayInfo:true,
		displayMsg:'第 {0} - {1} 条  共 {2} 条',
		emptyMsg:'没有记录'
	});
		
	app.grid = new Ext.grid.GridPanel({
		title : '数据库表列表',
		iconCls : 'icon-database_table',
		region : 'center',
		applyTo : 'org_detail_list',
		loadMask : {
			msg : '数据加载中...'
		},
	    cm: app.cm_utp,
	    ds: app.ds_utp,
	    height:550,
        autoScroll: true,
		sm:app.sm,
		tbar : [app.btn_add,'-',app.btn_edit,'-',app.btn_del,'-','数据表名称:',app.text_tac_code,app.btn_search_code],
		bbar : app.ptb
	});
	
	app.grid.addListener('rowdblclick',function(grid, rowIndex){
				if(grid.getSelectionModel().isSelected(rowIndex)){
					Ext.Msg.show({
						title : '系统提示',
						width:300,
						msg : '该功能正在开发!',
						buttons : Ext.Msg.OK,
						icon : Ext.MessageBox.INFO
					});
				}
	});

//    app.grid.render('terminal_1');

	var root = new Ext.tree.AsyncTreeNode({
		text : '数据中心',
		expanded : true,
		id : '001'
	});
	
	var deptTree = new Ext.tree.TreePanel({
		loader : new Ext.tree.TreeLoader({
					baseAttrs : {},
					dataUrl : '/tree/sys_object.json'
				}),
		root : root,
		title : '',
		applyTo : 'org_tree',
		autoScroll : false,
		animate : false,
		useArrows : false,
		border : false
	});
	
	deptTree.root.select();
	
	deptTree.on('click', function(node) {
		deptid = node.attributes.id;
//		Ext.MessageBox.alert('提示','部门ID为:'+deptid);
		// || deptid.startWith('02')
		if(deptid == '01' || deptid.startWith('01')){
			app.ds_utp.loadData(app.data01);
		}else if(deptid == '02' || deptid.startWith('02')){
			app.ds_utp.loadData(app.data02);
		}else{
			app.ds_utp.loadData(app.data);
		}
		/**
		store.load({
			params : {
				start : 0,
				limit : bbar.pageSize,
				deptid : deptid
			}
		});
		**/ 
	});
	
	var viewport = new Ext.Viewport({
			layout : 'border',
			items : [{
						title : '<span style="font-weight:normal">数据库列表</span>',
						iconCls : 'icon-chart_organisation',
						tools : [{
									id : 'refresh',
									handler : function() {
										deptTree.root.reload()
									}
								}],
						collapsible : true,
						width : 210,
						minSize : 160,
						maxSize : 280,
						split : true,
						region : 'west',
						autoScroll : true,
						items : [deptTree]
					}, {
						region : 'center',
						layout : 'fit',
						items : [app.grid]
					}]
		});
	
}); 

String.prototype.endWith=function(str){
	if(str==null||str==""||this.length==0||str.length>this.length)
	  return false;
	if(this.substring(this.length-str.length)==str)
	  return true;
	else
	  return false;
	return true;
}

String.prototype.startWith=function(str){
	if(str==null||str==""||this.length==0||str.length>this.length)
	  return false;
	if(this.substr(0,str.length)==str)
	  return true;
	else
	  return false;
	return true;
}