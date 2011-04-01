/**
 * 终端业务数据
 */
var app = {};
Ext.onReady(function(){
	
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    Ext.QuickTips.init();
	app.limit = 15;
	app.colName = "";
	app.values = "";
    app.sm = new Ext.grid.CheckboxSelectionModel();
	
	//业务名称,终端属性集合[GRPS,WAP,CMNET]
    app.data = [
        ['飞信','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700','备注',true],
        ['移动MM','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700','备注',true],
        ['手机阅读','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700','备注',true],
        ['淘乐汇','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700','备注',true],
        ['手机报','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700','备注',true],
        ['号谱管家','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700','备注',true],
        ['可视电话','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700','备注',true],
        ['彩信相册','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700','备注',true],
        ['移动秘书','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700','备注',true],
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
			 '<span align="left"><b>终端功能参数</b></span><br/><table align="left" class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr>' +
			 '<td><b>mms:</b>{useful}</td>' +
			 '<td><b>gprs:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>wap:</b>{useful}</td>' +
			 '<td><b>kjava:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>edge:</b>{useful}</td>' +
			 '<td><b>3g:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>email:</b>{useful}</td>' +
			 '<td><b>手机动画:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>streamiNg:</b>{useful}</td>' +
			 '<td><b>IMPS:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>POC:</b>{useful}</td>' +
			 '<td><b>电视:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>智能手机:</b>{useful}</td>' +
			 '<td><b>智能操作系统:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>LCD彩色:</b>{useful}</td>' +
			 '<td><b>LCD尺寸:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>内存:</b>{useful}</td>' +
			 '<td><b>振铃格式:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>多媒体格式:</b>{useful}</td>' +
			 '<td><b>摄像头(内置):</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>MP3:</b>{useful}</td>' +
			 '<td><b>音乐手机(DRM):</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>可视电话:</b>{useful}</td>' +
			 '<td><b>USSD:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>STK:</b>{useful}</td>' +
			 '<td><b>EFR:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>红外:</b>{useful}</td>' +
			 '<td><b>RS232:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>数据线:</b>{useful}</td>' +
			 '<td><b>预留1:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>预留2:</b>{useful}</td>' +
			 '<td><b>预留3:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>预留4:</b>{useful}</td>' +
			 '<td><b>预留5:</b>{useful}</td>' +
			 '</tr>' +
			 '</table><br/>',
			 '<span align="left"><b>终端业务参数</b></span><br/><table align="left" class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr>' +
			 '<td><b>移动证券:</b>{useful}</td>' +
			 '<td><b>手机导航:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>手机地图:</b>{useful}</td>' +
			 '<td><b>音乐随身听:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>全曲下载:</b>{useful}</td>' +
			 '<td><b>WAP全曲下载:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>扫描上网:</b>{useful}</td>' +
			 '<td><b>名片识别:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>手机电视:</b>{useful}</td>' +
			 '<td><b>手机支付:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>手机邮箱:</b>{useful}</td>' +
			 '<td><b>飞信:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>移动OA:</b>{useful}</td>' +
			 '<td><b>移动MM:</b>{useful}</td>' +
			 '</tr>' +
			 '</table>'
        )
	 });
	 
    app.cm_utp = new Ext.grid.ColumnModel([
	    app.expander,
        {header: "业务名称",sortable: true, dataIndex: 'time'}
//        {header: "有效性", width: 80, sortable: true, dataIndex: 'useful',renderer:function(value){
//        	if(value){
//        		return '<font color="green">有效</font>';
//        	}else{
//        		return '<font color="red">无效</font>';
//        	}
//        }},
//        {header: "用户标识", width: 150, sortable: true, dataIndex: 'uid'},
//        {header: "手机号码", width: 100, sortable: true, dataIndex: 'sn'},
//        {header: "imei号码", width: 150, sortable: true, dataIndex: 'imei'},
//        {header: "业务区编码", width: 100, sortable: true, dataIndex: 'ywqbm'},
//        {header: "tac码", width: 80, sortable: true, dataIndex: 'tac'}
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
								fieldLabel : '统计日期',
								name : 'article.time',
								allowBlank : false
							},{
								fieldLabel : '用户标示',
								name : 'article.uid',
								allowBlank : false
							},{
								fieldLabel : '手机号码',
								name : 'article.sn',
								allowBlank : false
							},{
								fieldLabel : 'IMEI号码',
								name : 'article.imei',
								allowBlank : false
							},{
								fieldLabel : '业务区编码',
								name : 'article.ywqbm',
								allowBlank : false
							},{
								fieldLabel : 'TAC码',
								name : 'article.tac',
								allowBlank : false
							},{
								//下拉选择框
								xtype:'combo',
								fieldLabel : '有效性',
								hiddenName:'article.useful',
				                valueField: 'id',
				                displayField: 'name',
				                triggerAction:'all',
				                mode: 'local',
				                store: new Ext.data.SimpleStore({
				                    fields: ['id','name'],
				                    data: [[1,'有效'], [0,'无效']]
				                }),
				                editable:false,
								emptyText : '请选择数据有效性',
								allowBlank : false
							},{
								fieldLabel : '备注',
								xtype:'textarea',
								name : 'article.remarks',
								allowBlank : false
							}],
							buttonAlign : 'right',
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
								text : '重置',
								handler : function() {
									Ext.getCmp('add_form').form.reset();
								}
							}, {
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
							fieldLabel : '统计日期',
							name : 'article.time',
							readOnly:true,
							allowBlank : false,
							value : record.get('time')
						},{
							fieldLabel : '用户标示',
							name : 'article.uid',
							allowBlank : false,
							value : record.get('uid')
						},{
							fieldLabel : '手机号码',
							name : 'article.sn',
							allowBlank : false,
							value : record.get('sn')
						},{
							fieldLabel : 'IMEI号码',
							name : 'article.imei',
							allowBlank : false,
							value : record.get('imei')
						},{
							fieldLabel : '业务区编码',
							name : 'article.ywqbm',
							allowBlank : false,
							value : record.get('ywqbm')
						},{
							//下拉选择框
							xtype:'combo',
							fieldLabel : '状态',
							id : 'article_useful',
							hiddenName:'article.useful',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'有效'], [0,'无效']]
			                }),
			                editable:false,
			                blankText: '请选择状态',
							emptyText : '当前状态:'+(record.get('useful') == 1?'有效':'无效'),
							allowBlank : false
						},{
							fieldLabel : 'TAC码',
							name : 'article.tac',
							allowBlank : false,
							value:record.get('tac')
						}],
						buttonAlign : 'right',
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
							handler : function() {
								this.ownerCt.form.reset();
							}
						}, {
							text : '取消',
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
	
	app.ds_utp = new Ext.data.ArrayStore({
        fields: [
           {name: 'time',type:'string'},
           {name: 'uid',type:'string'},
           {name: 'sn',type:'string'},
           {name: 'imei',type:'string'},
           {name: 'ywqbm',type:'string'},
           {name: 'thcs', type: 'string'},
           {name: 'jbjft', type: 'string'},
           {name: 'thsc', type: 'string'},
           {name: 'tac', type: 'string'},
           {name: 'remarks', type: 'string'},
           {name: 'useful', type: 'boolean'}
        ]
    });
	
	app.ds_utp.loadData(app.data);
	
	app.ptb = new Ext.PagingToolbar({
		pageSize:app.limit,
		store:app.ds_utp,
		displayInfo:true,
		displayMsg:'第 {0} - {1} 条  共 {2} 条',
		emptyMsg:'没有记录'
	});
		
	app.grid = new Ext.grid.GridPanel({
		title : '终端业务数据管理',
		iconCls : 'icon-plugin',
		region : 'center',
		loadMask : {
			msg : '数据加载中...'
		},
	    cm: app.cm_utp,
	    ds: app.ds_utp,
	    width:1000,
	    height:550,
        autoScroll: true,
        viewConfig: {
            forceFit:true
        },
 		plugins: app.expander,
		sm:app.sm,
		tbar : [app.btn_add,'-',app.btn_edit,'-',app.btn_del,'-','请输入手机号码:',app.text_search_code,'-','请输入IMEI:',app.text_imei_code,'请输入TAC码:',app.text_tac_code,app.btn_search_code],
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

    app.grid.render('terminal_business');
}); 