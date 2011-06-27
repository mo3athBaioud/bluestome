/**
 * IMEI查询
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
    app.data = [
        ['2010/6/27','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700'],
        ['2010/6/27','1610041222370609','18729637525','812347982824500','FA49','4','14','735','81234798'],
        ['2010/6/27','1610041222370609','18729637526','863417452824500','F0E9','4','14','735','86341745'],
        ['2010/6/27','1610041222370609','18729637527','883210322824500','FC79','4','14','735','88321032'],
        ['2010/6/27','1610041222370609','18729637528','873319122824500','FD69','4','14','735','87331912'],
        ['2010/6/27','1610041222370609','18729637529','813247092824500','F0F9','4','14','735','81324709'],
        ['2010/6/27','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700'],
        ['2010/6/27','1610041222370609','18729637525','812347982824500','FA49','4','14','735','81234798'],
        ['2010/6/27','1610041222370609','18729637526','863417452824500','F0E9','4','14','735','86341745'],
        ['2010/6/27','1610041222370609','18729637527','883210322824500','FC79','4','14','735','88321032'],
        ['2010/6/27','1610041222370609','18729637528','873319122824500','FD69','4','14','735','87331912'],
        ['2010/6/27','1610041222370609','18729637529','813247092824500','F0F9','4','14','735','81324709'],
        ['2010/6/27','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700'],
        ['2010/6/27','1610041222370609','18729637525','812347982824500','FA49','4','14','735','81234798'],
        ['2010/6/27','1610041222370609','18729637526','863417452824500','F0E9','4','14','735','86341745'],
        ['2010/6/27','1610041222370609','18729637527','883210322824500','FC79','4','14','735','88321032'],
        ['2010/6/27','1610041222370609','18729637528','873319122824500','FD69','4','14','735','87331912'],
        ['2010/6/27','1610041222370609','18729637529','813247092824500','F0F9','4','14','735','81324709'],
        ['2010/6/27','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700'],
        ['2010/6/27','1610041222370609','18729637525','812347982824500','FA49','4','14','735','81234798'],
        ['2010/6/27','1610041222370609','18729637526','863417452824500','F0E9','4','14','735','86341745'],
        ['2010/6/27','1610041222370609','18729637527','883210322824500','FC79','4','14','735','88321032'],
        ['2010/6/27','1610041222370609','18729637528','873319122824500','FD69','4','14','735','87331912'],
        ['2010/6/27','1610041222370609','18729637529','813247092824500','F0F9','4','14','735','81324709'],
        ['2010/6/27','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700'],
        ['2010/6/27','1610041222370609','18729637525','812347982824500','FA49','4','14','735','81234798'],
        ['2010/6/27','1610041222370609','18729637526','863417452824500','F0E9','4','14','735','86341745'],
        ['2010/6/27','1610041222370609','18729637527','883210322824500','FC79','4','14','735','88321032'],
        ['2010/6/27','1610041222370609','18729637528','873319122824500','FD69','4','14','735','87331912'],
        ['2010/6/27','1610041222370609','18729637529','813247092824500','F0F9','4','14','735','81324709'],
        ['2010/6/27','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700'],
        ['2010/6/27','1610041222370609','18729637525','812347982824500','FA49','4','14','735','81234798'],
        ['2010/6/27','1610041222370609','18729637526','863417452824500','F0E9','4','14','735','86341745'],
        ['2010/6/27','1610041222370609','18729637527','883210322824500','FC79','4','14','735','88321032'],
        ['2010/6/27','1610041222370609','18729637528','873319122824500','FD69','4','14','735','87331912'],
        ['2010/6/27','1610041222370609','18729637529','813247092824500','F0F9','4','14','735','81324709'],
        ['2010/6/27','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700'],
        ['2010/6/27','1610041222370609','18729637525','812347982824500','FA49','4','14','735','81234798'],
        ['2010/6/27','1610041222370609','18729637526','863417452824500','F0E9','4','14','735','86341745'],
        ['2010/6/27','1610041222370609','18729637527','883210322824500','FC79','4','14','735','88321032'],
        ['2010/6/27','1610041222370609','18729637528','873319122824500','FD69','4','14','735','87331912'],
        ['2010/6/27','1610041222370609','18729637529','813247092824500','F0F9','4','14','735','81324709'],
        ['2010/6/27','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700'],
        ['2010/6/27','1610041222370609','18729637525','812347982824500','FA49','4','14','735','81234798'],
        ['2010/6/27','1610041222370609','18729637526','863417452824500','F0E9','4','14','735','86341745'],
        ['2010/6/27','1610041222370609','18729637527','883210322824500','FC79','4','14','735','88321032'],
        ['2010/6/27','1610041222370609','18729637528','873319122824500','FD69','4','14','735','87331912'],
        ['2010/6/27','1610041222370609','18729637529','813247092824500','F0F9','4','14','735','81324709']
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
    
    app.cm_utp = new Ext.grid.ColumnModel([
	    new Ext.grid.RowNumberer(),app.sm,
        {header: "统计日期", width: 100, sortable: true, dataIndex: 'time'},
        {header: "用户标识", width: 150, sortable: true, dataIndex: 'uid'},
        {header: "手机号码", width: 100, sortable: true, dataIndex: 'sn'},
        {header: "imei号码", width: 150, sortable: true, dataIndex: 'imei'},
        {header: "业务区编码", width: 100, sortable: true, dataIndex: 'ywqbm'},
//        {header: "通话次数", width: 80, sortable: true, dataIndex: 'thcs'},
//        {header: "基本计费跳", width: 100, sortable: true, dataIndex: 'jbjft'},
//        {header: "通话时长", width: 80, sortable: true, dataIndex: 'thsc'},
        {header: "tac码", width: 80, sortable: true, dataIndex: 'tac'}
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
					title : '添加外呼数据',
					iconCls:'icon-add',
					width : 500,
					resizable : false,
					autoHeight : true,
					modal : true,
					closeAction : 'hide',
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
							}],
							buttonAlign : 'center',
							minButtonWidth : 60,
							buttons : [{
								text : '添加',
								iconCls:'icon-add',
								handler : function(btn) {
									var frm = Ext.getCmp('add_form').form;
									if (frm.isValid()) {
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
									Ext.getCmp('add_form').form.reset();
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
					title : '修改',
					iconCls:'icon-edit',
					width : 500,
					resizable : false,
					autoHeight : true,
					modal : true,
					closeAction : 'hide',
					animCollapse : true,
					pageY : 20,
					pageX : document.body.clientWidth / 2 - 420 / 2,
					animateTarget : Ext.getBody(),
					constrain : true,
					items : [
					new Ext.FormPanel({
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
							value : record.get('time')
						},{
							fieldLabel : '用户标示',
							name : 'article.uid',
							value : record.get('uid')
						},{
							fieldLabel : '手机号码',
							name : 'article.sn',
							value : record.get('sn')
						},{
							fieldLabel : 'IMEI号码',
							name : 'article.imei',
							value : record.get('imei')
						},{
							fieldLabel : '业务区编码',
							name : 'article.ywqbm',
							value : record.get('ywqbm')
						},{
							fieldLabel : 'TAC码',
							name : 'article.tac',
							value:record.get('tac')
						}],
						buttonAlign : 'center',
						minButtonWidth : 60,
						buttons : [{
							text : '保存',
							iconCls:'icon-accept',
							handler : function(btn) {
								var win = Ext.getCmp('update_win');
								win.hide();
								Ext.Msg.show({
									title : '系统提示',
									msg : '修改成功!',
									buttons : Ext.Msg.OK,
									fn:function(){
										Ext.getCmp('update_form').form.reset();
										app.ds_utp.loadData(app.data);
									},
									icon : Ext.MessageBox.INFO
								});
							}
						}, {
							text : '重置',
							iconCls:'icon-arrow_refresh',
							handler : function() {
								Ext.getCmp('update_form').form.reset();
							}
						}, {
							text : '取消',
							iconCls:'icon-cancel',
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
           {name: 'tac', type: 'string'}
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
		title : '扩展IMEI管理',
		iconCls : 'icon-basket_go',
		region : 'center',
		loadMask : {
			msg : '数据加载中...'
		},
	    cm: app.cm_utp,
	    ds: app.ds_utp,
	    height:500,
        autoScroll: true,
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

    app.grid.render('imei_extends');
}); 