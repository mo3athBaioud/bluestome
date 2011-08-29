/**
 * 员工管理
 */
var app = {};
Ext.onReady(function(){
  	Ext.BLANK_IMAGE_URL = project+'/resource/image/ext/s.gif';
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    Ext.QuickTips.init();
	app.limit = 15;
	app.colName = "";
	app.values = "";
    app.sm = new Ext.grid.CheckboxSelectionModel();
    
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
    
    app.cm_data = new Ext.grid.ColumnModel([
		app.sm,
            {id:'title',header: '号码', width: 100, sortable: true, dataIndex: 'phonenum'},
            {header: '号码所属业务区', width: 100, sortable: true, dataIndex: 'phonenumBDistrict'},
            {header: '登录名', width: 100, sortable: true, dataIndex: 'loginname'},
            {header: '登录名所属业务区', width: 100, sortable: true, dataIndex: 'loginnameBDistrict'},
            {header: '时间', width: 150, sortable: true, dataIndex: 'createtime'},
	         {header: "是否营销", width: 100, sortable: true, dataIndex: 'isMarket',renderer:function(v){
	        	if(v == 0){
	        		return '';
	        	}else if(v == 1){
	        		return '<font color="blue">是</font>';
	        	}else if(v == 2){
	        		return '<font color="yellow">否</font>';
	        	}else{
	        		return '<font color="yellow">未知</font>';
	        	}
	        }},
	        {header: "是否营销成功", width: 100, sortable: true, dataIndex: 'mSuccess',renderer:function(v){
	        	if(v == 0){
	        		return '';
	        	}else if(v == 1){
	        		return '<font color="blue">是</font>';
	        	}else  if(v == 2){
	        		return '<font color="yellow">否</font>';
	        	}else{
	        		return '<font color="yellow">未知</font>';
	        	}
	        }},
	        {header: "本平台营销成功", width: 100, sortable: true, dataIndex: 'platsell',renderer:function(v){
	        	if(v == 0){
	        		return '';
	        	}else if(v == 1){
	        		return '<font color="blue">是</font>';
	        	}else  if(v == 2){
	        		return '<font color="red">否</font>';
	        	}else{
	        		return '<font color="yellow">未知</font>';
	        	}
	        }},
        	{header: "业务类型", width: 100, sortable: true, dataIndex: 'btype',renderer:function(v){
				var x = parseInt(v);
				switch(x){
					case 1:
						name = '无线音乐高级俱乐部会员';
						break;
					case 2:
						name = '139邮箱';
						break;
					case 3:
						name = '飞信会员';
						break;
					case 4:
						name = '号簿管家';
						break;
					case 5:
						name = '全曲下载';
						break;
					case 6:
						name = '手机报';
						break;
					case 7:
						name = '手机视频';
						break;
					case 8:
						name = '手机阅读';
						break;
					case 9:
						name = '手机游戏';
						break;
					case 10:
						name = '手机电视';
						break;
					case 11:
						name = '移动MM';
						break;
					case 12:
						name = 'GPRS流量包';
						break;
					case 13:
						name = '彩信包';
						break;
					case 14:
						name = '手机支付';
						break;
					case 15:
						name = 'WIFI';
						break;
					case 16:
						name = '手机地图';
						break;
					default:
						name = '默认';
						break;
				}
        		return '<font color="blue">'+name+'</font>';
        	}}
    ]);
    
	app.search_comb_queyrCol_code = new Ext.form.ComboBox({
				fieldLabel : '查询条件',
				id : 'column',
				hiddenName : 'colName',
				valueField : 'id',
				displayField : 'name',
				mode:'local',
				store : new Ext.data.SimpleStore({
					data : [
						['d_phonenum', '手机号码'],
						['d_phonenum_bdistrict','号码业务区代码)'],
						['d_loginname','登录名/员工号'],
						['d_loginname_bdistrict','登录名所属业务区']
				    ],
					fields : ['id', 'name']
				}),	
				selectOnFocus : true,
				editable : false,
				allowBlank : true,
				triggerAction : 'all',
				emptyText : '查询的字段名'
	});
	
	app.btn_export = new Ext.Button({
		text : '导出日志',
		iconCls : 'icon-database_save',
		handler : function(){
			app.data_form.getForm().reset();
//			app.data_form.getForm().findField('start').setValue(0);
//			app.data_form.getForm().findField('limit').setValue(app.limit);
//			app.data_form.getForm().findField('colName').setValue(app.colName);
//			app.data_form.getForm().findField('value').setValue(app.value);
			app.add_win.show();
			app.add_win.setTitle("导出日志");
			Ext.getCmp('data_form_reset').show();
		}
	});
    
	app.btn_search_code = new Ext.Button({
		text : '查询',
		iconCls : 'icon-search',
		handler : function(){
			app.searchcode();
		}
	});
	
	app.data_form = new Ext.FormPanel({
		id:'add_form',
		labelWidth : 80,
		labelAlign : 'right',
		border : false,
		baseCls : 'x-plain',
		bodyStyle : 'padding:5px 5px 0',
		anchor : '100%',
		url : project + '/noplog/count.cgi?loginName='+username,
		defaults : {
			width : 300,
			msgTarget : 'side'
		},
		defaultType : 'textfield',
		items : [
			new Ext.form.DateField({
				xtype:'datetime',
				fieldLabel : '开始时间',
				name : 'startdate',
				format:'Y-m-d H:i:s',
				allowBlank : false
			}),
			new Ext.form.DateField({
				xtype:'datetime',
				fieldLabel : '结束时间',
				name : 'enddate',
				format:'Y-m-d H:i:s'
			})],
			buttonAlign : 'center',
			minButtonWidth : 60,
			buttons : [{
				id:'dbm_form_save',
				text : '导出',
				iconCls:'icon-accept',
				handler : function(btn) {
					//TODO 首先获取当前条件下查询出的记录数
					//TODO 然后再获取实际查询出的数据。
					var frm = Ext.getCmp('add_form').form;
					if (frm.isValid()) {
						frm.submit({
							waitTitle : '请稍候',
							waitMsg : '正在提交表单数据,请稍候...',
							success : function(form, action) {
								var count = action.result.count;
								if(count > 0){
									Ext.Msg.show({
										title : '提示',
										msg : '查询到将有['+count+']条数据需要导出，是否导出!',
										buttons : Ext.Msg.OKCANCEL,
										fn:function(btn){
											if(btn == 'ok'){
												var startdate = frm.findField('startdate').getValue().format("Y-m-d H:i:s");
												var enddate = frm.findField('enddate').getValue();
												//TODO 执行请求下载
												var url = project + '/noplog/download.cgi?startdate='+startdate;
												Ext.Msg.show({
													title : '提示',
													msg : '确认导出查询的日志数据？',
													buttons : Ext.Msg.OKCANCEL,
													fn:function(btn){
														if(btn == 'ok'){
															if('' != enddate && null != enddate){
																window.open(url+'&enddate='+(frm.findField('enddate').getValue().format("Y-m-d H:i:s")));
															}else{
																window.open(url);
															}
														}
													},
													icon : Ext.Msg.INFO
												});
 											}
										},
										icon : Ext.Msg.INFO
									});
								}else{
									Ext.Msg.sow({
										title : '提示',
										msg : '未查询到相关数据，请重新查询!',
										buttons : Ext.Msg.OK,
										icon : Ext.Msg.ERROR
									});
								}
							},
							failure : function(){
								Ext.Msg.show({
									title : '提示',
									msg : '"' + dnfield.getValue() + '" ' + '名称可能已经存在或者您没有添加数据的权限!',
									buttons : Ext.Msg.OK,
									icon : Ext.Msg.ERROR
								});
							}
						})
					}
				}
			}, {
				id:'data_form_reset',
				text : '重置',
				iconCls:'icon-arrow_refresh_small',
				handler : function() {
					Ext.getCmp('add_form').form.reset();
				}
			}, {
				iconCls:'icon-cancel',
				text : '取消',
				handler : function() {
					app.data_form.getForm().reset();
					var win = Ext.getCmp('add_win');
					win.hide();
				}
			}]
	});
	
	app.add_win = new Ext.Window({
		id:'add_win',
		title:'窗口',
		iconCls:'icon-database_save',
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
			app.data_form
		]
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

	app.searchcode = function() {
			app.colName = app.search_comb_queyrCol_code.getValue();
			app.values = app.text_search_code.getValue();
			Ext.Ajax.request({
				url : project + '/noplog/list.cgi?loginName='+username,
				params : {
					start:0,
					limit:app.limit,
					colName : app.colName,
					value : app.values
				},
				success:function(response,option){
					var obj = Ext.util.JSON.decode(response.responseText);
					if(obj.success){
						app.ds_data.load({
							params : {
								start:0,
								limit:app.limit,
								colName : app.colName,
								value : app.values
							}
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
		        failure:function(){
					Ext.Msg.show({
						title : '系统提示',
						msg : '服务器内部错误',
						buttons : Ext.Msg.OK,
						icon : Ext.MessageBox.ERROR
					});
		        }
		});
	};
	
	app.text_phone_number = new Ext.form.TextField({
		name : 'phone_number',
		width : 150,
		allowBlank:false,
		vtype:'mobile'
	});
	
	app.ds_data = new Ext.data.Store({
		url : project + '/noplog/list.cgi?loginName='+username,
		baseParams:{},
		reader : new Ext.data.JsonReader({
			totalProperty : 'count',
			root : 'obj'
		}, [{name : 'phonenum',type : 'string'
		}, {name : 'btype',type : 'string'
		}, {name : 'loginname',type : 'string'
		}, {name : 'loginnameBDistrict',type : 'string'
		}, {name : 'phonenumBDistrict',type : 'string'
		}, {name : 'result',type : 'int'
		}, {name : 'createtime',type : 'string'
		}, {name : 'isMarket',type : 'int'
		}, {name : 'mSuccess',type : 'int'
		}, {name : 'platsell',type : 'int'
		}, {name : 'id',type : 'int'
		}, {name : 'uid',type : 'int'
		}])
	});
	
	app.ds_data.load({
		params : {
			start : 0,
			limit : app.limit,
			colName : app.colName,
			value : app.values
		}
	});
	
	app.ds_data.on('beforeload',function(){
		Ext.apply(
			this.baseParams,{
				start : 0,
				limit : app.limit,
				colName : app.colName,
				value : app.values
	        }   
	 	);   
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
		iconCls : 'icon-user',
		region : 'center',
		loadMask : {
			msg : '数据加载中...'
		},
	    cm: app.cm_data,
		ds: app.ds_data,
	    height:450,
//	    width:800,
        autoScroll: true,
        viewConfig: {
            forceFit:true
        },
		sm:app.sm,
		//app.btn_detail,'-','-',app.btn_update,'请输入员工名:',
		tbar : [app.btn_export,'-',app.search_comb_queyrCol_code,'-',app.text_search_code,'-',app.btn_search_code],
		bbar : app.ptb
	});
	
	/**
	 * 数据双击事件
	 */
	app.grid.addListener('rowdblclick',function(grid, rowIndex){
				if(grid.getSelectionModel().isSelected(rowIndex)){
					Ext.Msg.show({
						title : '系统提示',
						width:200,
						msg : '显示终端数据列表详情',
						buttons : Ext.Msg.OK,
						icon : Ext.MessageBox.INFO
					});
				}
	});

	/**
	 * 记录单击事件
	 */
	app.grid.addListener('rowclick',function(grid, rowIndex){
				if(grid.getSelectionModel().isSelected(rowIndex)){
				}else{
				}
	});
	
    app.grid.render('staff');
});