/**
 * 正式终端数据
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
	
	//统计日期,用户标识,手机号码,imei号码,业务区编码,通话次数,基本计费跳,通话时长,tac码    
//        ['品牌','型号','TAC','手机制式','通话时间','手机屏幕','主屏颜色','摄像头','蓝牙','来源','采集时间']
    app.data = [
        ['诺基亚','C7','81234798','GSM,WCDMA','576分钟','3.5英寸','1677万色','支持','支持','泡泡网','2011-03-31 11:00:00'],
        ['诺基亚','C5-03','81234798','GSM,WCDMA','720分钟','3.2英寸','1677万色','支持','支持','泡泡网','2011-03-31 11:00:00'],
        ['诺基亚','5230','81234798','GSM,WCDMA','420分钟','3.2英寸','1677万色','支持','支持','泡泡网','2011-03-31 11:00:00'],
        ['诺基亚','N8','81234798','GSM,WCDMA','720分钟','3.5英寸','1677万色','支持','支持','泡泡网','2011-03-31 11:00:00'],
        ['诺基亚','C6','81234798','GSM,WCDMA','576分钟','3.2英寸','1677万色','支持','支持','泡泡网','2011-03-31 11:00:00'],
        ['诺基亚','5800XM','81234798','GSM,WCDMA','525分钟','3.2英寸','1677万色','支持','支持','泡泡网','2011-03-31 11:00:00'],
        ['摩托罗拉','MB525 Defy','81234798','GSM,WCDMA','590分钟','3.7英寸','26万色','支持','支持','泡泡网','2011-03-31 11:00:00'],
        ['摩托罗拉','CLIQ(MB200)','81234798','GSM,WCDMA','360分钟','3.1英寸','1677万色','支持','支持','泡泡网','2011-03-31 11:00:00'],
        ['摩托罗拉','XT502','81234798','GSM,WCDMA','400分钟','3.2英寸','26万色','支持','支持','泡泡网','2011-03-31 11:00:00'],
        ['摩托罗拉','XT720','81234798','GSM,WCDMA','540分钟','3.7英寸','1677万色','支持','支持','泡泡网','2011-03-31 11:00:00']
	];
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
    
     app.expander = new Ext.grid.RowExpander({
        tpl : new Ext.Template(
            '<p><b>通话时间:</b>{thsc}</p>',
            '<p><b>屏幕大小:</b>{screensize}</p>',
            '<p><b>屏幕颜色:</b>{screencolor}</p>',
            '<p><b>摄像头:</b>{camera}</p>',
            '<p><b>蓝牙:</b>{bluetooth}</p>'
        )
	 });
	 
//   ['品牌','型号','TAC','手机制式','通话时间','手机屏幕','主屏颜色','摄像头','蓝牙','来源','采集时间']
    app.cm_utp = new Ext.grid.ColumnModel([
	    app.expander,
        {header: "品牌", width: 100, sortable: true, dataIndex: 'brand'},
        {header: "型号", width: 80, sortable: true, dataIndex: 'model'},
        {header: "TAC", width: 150, sortable: true, dataIndex: 'tac'}
    ]);
    
	app.hs_brand_combo = new Ext.form.ComboBox({
				id : 'hs_brand',
				hiddenName : 'colName',
				valueField : 'id',
				displayField : 'name',
				mode:'local',
				store : new Ext.data.SimpleStore({
					data : [
							['1', '联想'],
							['2','MOTO'],
							['3','魅族'],
							['4','诺基亚']
				    ],
					fields : ['id', 'name']
				}),	
				selectOnFocus : true,
				editable : false,
				allowBlank : true,
				triggerAction : 'all',
				emptyText : '请选择终端品牌!'
	});
	
	app.hs_model_combo = new Ext.form.ComboBox({
				id : 'hs_model',
				hiddenName : 'colName',
				valueField : 'id',
				displayField : 'name',
				mode:'local',
				store : new Ext.data.SimpleStore({
					data : [
							['1', 'N95'],
							['2','N80'],
							['3','M8'],
							['3','N61']
				    ],
					fields : ['id', 'name']
				}),	
				selectOnFocus : true,
				editable : false,
				allowBlank : true,
				triggerAction : 'all',
				emptyText : '请选择终端型号，如果没有列出手机型号，请先选择终端品牌!'
	});
	
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
					title : '添加正式终端',
					iconCls:'icon-add',
					width : 450,
					resizable : false,
					autoHeight : true,
					modal : true,
					closeAction : 'close',
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
								fieldLabel : '手机品牌',
								hiddenName:'article.useful',
				                valueField: 'id',
				                displayField: 'name',
				                triggerAction:'all',
				                mode: 'local',
				                store: new Ext.data.SimpleStore({
				                    fields: ['id','name'],
				                    data: [[1,'诺基亚'], [2,'摩托罗拉'],[3,'联想']]
				                }),
				                editable:false,
								emptyText : '请选择品牌',
								allowBlank : false
							},{
								//下拉选择框
								xtype:'combo',
								fieldLabel : '手机型号',
								hiddenName:'article.useful',
				                valueField: 'id',
				                displayField: 'name',
				                triggerAction:'all',
				                mode: 'local',
				                store: new Ext.data.SimpleStore({
				                    fields: ['id','name'],
				                    data: [[1,'C7'],[2,'C5'],[3,'5230'],[4,'MB525 Defy'],[5,'ME525'],[6,'XT502'],[7,'S708'],[8,'S710'],[9,'TD80t'],[10,'EX128']]
				                }),
				                editable:false,
								emptyText : '请选择型号',
								allowBlank : false
							},{
								fieldLabel : 'TAC码',
								name : 'article.tac',
								allowBlank : false
							},{
								fieldLabel : '通话时长',
								name : 'article.tac',
								allowBlank : false
							},{
								fieldLabel : '屏幕大小',
								name : 'article.tac',
								allowBlank : false
							},{
								fieldLabel : '屏幕颜色',
								name : 'article.tac',
								allowBlank : false
							},{
								//下拉选择框
								xtype:'combo',
								fieldLabel : '摄像头',
								hiddenName:'article.camera',
				                valueField: 'id',
				                displayField: 'name',
				                triggerAction:'all',
				                mode: 'local',
				                store: new Ext.data.SimpleStore({
				                    fields: ['id','name'],
				                    data: [[1,'支持'],[0,'不支持']]
				                }),
				                editable:false,
								emptyText : '请选择型号是否带摄像头',
								allowBlank : false
							},{
								//下拉选择框
								xtype:'combo',
								fieldLabel : '蓝牙',
								hiddenName:'article.bluetooth',
				                valueField: 'id',
				                displayField: 'name',
				                triggerAction:'all',
				                mode: 'local',
				                store: new Ext.data.SimpleStore({
				                    fields: ['id','name'],
				                    data: [[1,'支持'],[0,'不支持']]
				                }),
				                editable:false,
								emptyText : '请选择是否支持蓝牙',
								allowBlank : false
							},{
								fieldLabel : '其他终端参',
								xtype:'textarea',
								name : 'article.remarks',
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
										frm.reset();
										var win = Ext.getCmp('add_win');
										win.hide();
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
								text : '重置',
								iconCls:'icon-arrow_refresh',
								handler : function() {
									Ext.getCmp('add_form').form.reset();
								}
							}, {
								text : '取消',
								iconCls:'icon-cancel',
								handler : function() {
									var win = Ext.getCmp('add_win');
									win.hide();
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
					title : '修改正式终端',
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
								fieldLabel : '手机品牌',
								hiddenName:'article.brand',
				                valueField: 'id',
				                displayField: 'name',
				                triggerAction:'all',
				                mode: 'local',
				                store: new Ext.data.SimpleStore({
				                    fields: ['id','name'],
				                    data: [[1,'诺基亚'], [2,'摩托罗拉'],[3,'联想']]
				                }),
				                editable:false,
								emptyText : '当前品牌'+record.get('brand')+'.',
								allowBlank : false
							},{
								//下拉选择框
								xtype:'combo',
								fieldLabel : '手机型号',
								hiddenName:'article.model',
				                valueField: 'id',
				                displayField: 'name',
				                triggerAction:'all',
				                mode: 'local',
				                store: new Ext.data.SimpleStore({
				                    fields: ['id','name'],
				                    data: [[1,'C7'],[2,'C5'],[3,'5230'],[4,'MB525 Defy'],[5,'ME525'],[6,'XT502'],[7,'S708'],[8,'S710'],[9,'TD80t'],[10,'EX128']]
				                }),
				                editable:false,
								emptyText : '当前型号'+record.get('model')+'.',
								allowBlank : false
							},{
								fieldLabel : 'TAC码',
								name : 'article.tac',
								allowBlank : false,
								value:record.get('tac')
							},{
								fieldLabel : '通话时长',
								name : 'article.tac',
								allowBlank : false,
								value:record.get('thsc')
							},{
								fieldLabel : '屏幕大小',
								name : 'article.tac',
								allowBlank : false,
								value:record.get('screensize')
							},{
								fieldLabel : '屏幕颜色',
								name : 'article.tac',
								allowBlank : false,
								value:record.get('screencolor')
							},{
								//下拉选择框
								xtype:'combo',
								fieldLabel : '摄像头',
								hiddenName:'article.camera',
				                valueField: 'id',
				                displayField: 'name',
				                triggerAction:'all',
				                mode: 'local',
				                store: new Ext.data.SimpleStore({
				                    fields: ['id','name'],
				                    data: [[1,'支持'],[0,'不支持']]
				                }),
				                editable:false,
								emptyText : '当前'+(record.get('carmera') == 1?'支持':'不支持')+'摄像头',
								allowBlank : false
							},{
								//下拉选择框
								xtype:'combo',
								fieldLabel : '蓝牙',
								hiddenName:'article.bluetooth',
				                valueField: 'id',
				                displayField: 'name',
				                triggerAction:'all',
				                mode: 'local',
				                store: new Ext.data.SimpleStore({
				                    fields: ['id','name'],
				                    data: [[1,'支持'],[0,'不支持']]
				                }),
				                editable:false,
								emptyText : '当前'+(record.get('carmera') == 1?'支持':'不支持')+'蓝牙',
								allowBlank : false
							},{
								fieldLabel : '其他终端参',
								xtype:'textarea',
								name : 'article.remarks',
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
									frm.reset();
									var win = Ext.getCmp('update_win');
									win.hide();
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
							handler : function() {
								Ext.getCmp('update_form').form.reset();
							}
						}, {
							text : '取消',
							buttonAlign : 'center',
							handler : function() {
								Ext.getCmp('update_form').form.reset();
								var win = Ext.getCmp('update_win');
								win.hide();
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
	
	app.btn_terminal_query_components = new Ext.Button({
		text:'终端查询组件',
		iconCls:'icon-search',
		handler:function(){
			showTerminalWindow();
		}
	});
	
	//定义一个终端查询组件
	app.terminalQueryWin = new TerminalQuery({
		ds:'ds-123',
		width:650,
		height:500,
		///servlet/FormServlet http://180.168.68.82:6012/nutzd/website/root.cgi
		url:'/servlet/FormServlet',
		parentId:'terminal1_grid',
		other:null
	});
		
	var showTerminalWindow = function(){
		app.terminalQueryWin.show();
	}
	
	app.btn_transto_office = new Ext.Button({
		text : '转入正式数据',
		iconCls : 'icon-edit',
		handler : function(){
			if(app.grid.getSelectionModel().getSelected()){
				Ext.Msg.confirm('转入正式终端数据', '你确定需要转入所选终端数据到正式数据下?', function(btn) {
					if (btn == 'yes') {
						var record = app.grid.getSelectionModel().getSelected();
						Ext.Msg.show({
							title : '系统提示',
							msg : '数据['+record.get('brand')+'|'+record.get('model')+'|'+record.get('tac')+']将转入正式数据中',
							buttons : Ext.Msg.OK,
							fn:function(){
								app.ds_utp.loadData(app.data);
							},
							icon : Ext.MessageBox.INFO
						});
					}
				});
			}else{
				Ext.Msg.show({
					title : '系统提示',
					msg : '请选择需要转入到正式数据的记录!',
					buttons : Ext.Msg.OK,
					icon : Ext.MessageBox.ERROR
				});
			}
		}
	});
	
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
	
//   ['品牌','型号','TAC','手机制式','通话时间','手机屏幕','主屏颜色','摄像头','蓝牙','来源','采集时间']
	app.ds_utp = new Ext.data.ArrayStore({
        fields: [
           {name: 'brand',type:'string'},
           {name: 'model',type:'string'},
           {name: 'tac',type:'string'},
           {name: 'hs_model',type:'string'},
           {name: 'thsc',type:'string'},
           {name: 'screensize', type: 'string'},
           {name: 'screencolor', type: 'string'},
           {name: 'camera', type: 'string'},
           {name: 'bluetooth', type: 'string'},
           {name: 'source', type: 'string'},
           {name: 'time', type: 'boolean'}
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
		title : '正式终端数据管理',
		iconCls : 'icon-monitor_lightning',
		region : 'center',
		loadMask : {
			msg : '数据加载中...'
		},
	    cm: app.cm_utp,
	    ds: app.ds_utp,
//	    width:1000,
	    height:550,
        autoScroll: true,
        viewConfig: {
            forceFit:true
        },
 		plugins: app.expander,
		sm:app.sm,
		//app.btn_add,'-',app.hs_brand_combo,'-',app.hs_model_combo,
		tbar : [app.btn_terminal_query_components,'-',app.btn_edit,'-',app.btn_del,'-','TAC码:',app.text_tac_code,app.btn_search_code],
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

//    app.grid.render('terminal');
    
	var root = new Ext.tree.AsyncTreeNode({
		text : '手机品牌',
		expanded : true,
		id : '001'
	});
	
	var hsBrandTree = new Ext.tree.TreePanel({
		loader : new Ext.tree.TreeLoader({
					baseAttrs : {},
					dataUrl : '/tree/hs_brand.json'
				}),
		root : root,
		title : '',
		applyTo : 'terminal_brand',
		autoScroll : false,
		animate : false,
		useArrows : false,
		border : false
	});
	
	hsBrandTree.root.select();
	
	hsBrandTree.on('click', function(node) {
		app.ds_utp.loadData(app.data);
		/**
		deptid = node.attributes.id;
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
					title : '<span style="font-weight:normal">手机品牌</span>',
					iconCls : 'icon-phone',
					tools : [{
								id : 'refresh',
								handler : function() {
									hsBrandTree.root.reload()
								}
							}],
					collapsible : true,
					width : 210,
					minSize : 160,
					maxSize : 280,
					split : true,
					region : 'west',
					autoScroll : true,
					// collapseMode:'mini',
					items : [hsBrandTree]
				}, {
					region : 'center',
					layout : 'fit',
					items : [app.grid]
				}]
	});
}); 