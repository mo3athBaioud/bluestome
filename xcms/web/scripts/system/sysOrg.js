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
        ['0102','西北三路营业厅','1','01','营业厅'],
        ['0103','省政府机关综合楼','2','01','营业厅'],
        ['0104','咸阳新机场','3','01','营业厅'],
        ['0105','钟楼营业厅','4','01','营业厅'],
        ['0202','数据部','1','02','机关单位'],
        ['0203','运营部','2','02','机关单位'],
        ['0204','总经理办公室','3','02','机关单位'],
        ['0205','客户经理','4','02','机关单位']
	];

    app.data01 = [
        ['0102','西北三路营业厅','1','01','营业厅'],
        ['0103','省政府机关综合楼','2','01','营业厅'],
        ['0104','咸阳新机场','3','01','营业厅'],
        ['0105','钟楼营业厅','4','01','营业厅']
	];

    app.data02 = [
        ['0202','数据部','1','02','机关单位'],
        ['0203','运营部','2','02','机关单位'],
        ['0204','总经理办公室','3','02','机关单位'],
        ['0205','客户经理','4','02','机关单位']
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
        {header: "部门名称", width: 150, sortable: true, dataIndex: 'deptname'},
        {header: "部门编号", width: 100, sortable: true, dataIndex: 'deptnumber'},
        {header: "上级部门", width: 150, sortable: true, dataIndex: 'updept'},
        {header: "排序号", width: 100, sortable: true, dataIndex: 'orderby'}
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
								fieldLabel : '上级部门',
								hiddenName:'article.brand',
				                valueField: 'id',
				                displayField: 'name',
				                triggerAction:'all',
				                mode: 'local',
				                store: new Ext.data.SimpleStore({
				                    fields: ['id','name'],
				                    data: [[1,'营业厅'], [2,'机关单位'],[3,'其他']]
				                }),
				                editable:false,
								emptyText : '请选择上级部门!',
								allowBlank : false
							},{
								fieldLabel : '部门名称',
								name : 'article.tac',
								allowBlank : false
							},{
								fieldLabel : '部门编号',
								name : 'article.tac',
								allowBlank : false
							},{
								fieldLabel : '排序',
								name : 'article.tac',
								allowBlank : false
							}],
							buttonAlign : 'center',
							minButtonWidth : 60,
							buttons : [{
								text : '添加',
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
								handler : function() {
									Ext.getCmp('add_form').form.reset();
								}
							}, {
								buttonAlign : 'center',
								text : '取消',
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
								fieldLabel : '上级部门',
								hiddenName:'article.brand',
				                valueField: 'id',
				                displayField: 'name',
				                triggerAction:'all',
				                mode: 'local',
				                store: new Ext.data.SimpleStore({
				                    fields: ['id','name'],
				                    data: [[1,'营业厅'], [2,'机关单位'],[3,'其他']]
				                }),
				                editable:false,
								emptyText : '当前上级部门为:"'+record.get('updept')+'"!',
								allowBlank : false
							},{
								fieldLabel : '部门名称',
								name : 'article.tac',
								allowBlank : false,
								value:record.get('deptname')
							},{
								fieldLabel : '部门编号',
								name : 'article.tac',
								allowBlank : false,
								value:record.get('deptnumber')
							},{
								fieldLabel : '排序',
								name : 'article.tac',
								allowBlank : false,
								value:record.get('orderby')
							}],
						buttonAlign : 'center',
						minButtonWidth : 60,
						buttons : [{
							text : '更新',
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
							buttonAlign : 'center',
							handler : function() {
								this.ownerCt.form.reset();
							}
						}, {
							text : '取消',
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
           {name: 'deptnumber',type:'string'},
           {name: 'deptname',type:'string'},
           {name: 'orderby',type:'string'},
           {name: 'updeptnumber',type:'string'},
           {name: 'updept',type:'string'}
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
		title : '组织结构列表',
		iconCls : 'icon-application_view_list',
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
		tbar : [app.btn_add,'-',app.btn_edit,'-',app.btn_del,'-','请输入组织名称:',app.text_tac_code,app.btn_search_code],
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
		text : '西安移动终端支撑平台',
		expanded : true,
		id : '001'
	});
	
	var deptTree = new Ext.tree.TreePanel({
		loader : new Ext.tree.TreeLoader({
					baseAttrs : {},
					dataUrl : '/tree/sys_org.json'
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
						title : '<span style="font-weight:normal">西安终端支撑平台</span>',
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