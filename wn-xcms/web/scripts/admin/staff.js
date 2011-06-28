/**
 * 员工管理
 */
var app = {};
Ext.onReady(function(){
  	Ext.BLANK_IMAGE_URL = '/resource/image/ext/s.gif';
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    Ext.QuickTips.init();
	app.limit = 15;
	app.colName = "d_username";
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
        {header: "用户名", width: 100, sortable: true, dataIndex: 'username'},
        {header: "手机号码", width: 100, sortable: true, dataIndex: 'mobile'},
        {header: "办公室号码", width: 100, sortable: true, dataIndex: 'officephone'},
        {header: "用户状态", width: 150, sortable: true, dataIndex: 'status',renderer:function(v){
        	if(v == 0){
        		return '<font color="red">不可用</font>';
        	}else if(v == 1){
        		return '<font color="blue">可用</font>';
        	}else{
        		return '<font color="yellow">未知</font>';
        	}
        }},
        {header: "渠道代码", width: 100, sortable: true, dataIndex: 'channelcode'},
        {header: "创建时间", width: 120, sortable: true, dataIndex: 'createtime'}
    ]);
    
	app.btn_detail = new Ext.Button({
		text : '员工详情',
		disabled:true,
		iconCls : 'icon-application_view_detail',
		handler : function(){
			Ext.Msg.show({
				title : '提示',
				msg : '该功能正在开发!',
				buttons : Ext.Msg.OK,
				icon : Ext.Msg.INFO
			});
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
		url : project+'/staff/add.cgi',
		defaults : {
			width : 300,
			msgTarget : 'side'
		},
		defaultType : 'textfield',
		items : [
			{
				xtype:'hidden',
				fieldLabel : 'ID',
				name : 'id',
				allowBlank : false
			},
			{
				fieldLabel : '员工名',
				name : 'username',
				allowBlank : false
			},
			{
				fieldLabel:'密码',
				id:'pwd',
				name:'newPwd',
				inputType:'password',
				vtype:'safe',
//				allowBlank:false,
				maxLength:20
			},
			 {
				fieldLabel:'确认密码',
				inputType:'password',
				name:'password',
//				allowBlank:false,
				vtype:'password',
				initialPassField: 'pwd', // 要比较的另外一个的组件的id
				maxLength:20
			},
//			{
//			 	xtype:'hidden',
//				name:'password',
//				allowBlank:false
//			},
			{
				fieldLabel : '手机号码',
				name : 'mobile',
				allowBlank : false
			},{
				fieldLabel : '办公室号码',
				name : 'officephone',
				allowBlank : false
			},{
				//下拉选择框
				xtype:'combo',
				fieldLabel : '所属业务区',
				hiddenName:'bdistrict',
		        valueField: 'id',
		        displayField: 'name',
		        triggerAction:'all',
		        mode: 'local',
		        store: new Ext.data.SimpleStore({
		            fields: ['id','name'],
		            data: [['E0E1','临渭区'],['E0E7','蒲城'],['E0EA','大荔']]
		        }),
	            editable:false,
				emptyText : '请选择所属业务区!',
				allowBlank : false
			},{
				//下拉选择框
				xtype:'combo',
				fieldLabel : '所属渠道',
				hiddenName:'channelcode',
		        valueField: 'id',
		        displayField: 'name',
		        triggerAction:'all',
		        mode: 'local',
		        store: new Ext.data.SimpleStore({
		            fields: ['id','name'],
		            data: [['E0E7A0A1','中山路营业2厅']]
		        }),
	            editable:false,
				emptyText : '请选择所属渠道!',
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
		            data: [[1,'可用'],[0,'不可用']]
		        }),
	            editable:false,
				emptyText : '请选择业务状态!',
				allowBlank : false
			}],
			buttonAlign : 'center',
			minButtonWidth : 60,
			buttons : [{
				id:'dbm_form_save',
				text : '保存',
				iconCls:'icon-accept',
				handler : function(btn) {
					var frm = Ext.getCmp('add_form').form;
					if (frm.isValid()) {
						var dnfield = frm.findField('username');
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
										app.ds_data.load({params : {
											start : 0,
											limit : app.limit,
											colName : app.colName,
											value : app.values
											}
										});
										btn.enable();
										var win = Ext.getCmp('add_win');
										win.hide();
									},
									icon : Ext.MessageBox.INFO
								});
							},
							failure : function(){
								Ext.Msg.show({
									title : '错误提示',
									msg : '"' + dnfield.getValue() + '" ' + '名称可能已经存在或者您没有添加数据的权限!',
									buttons : Ext.Msg.OK,
									icon : Ext.Msg.ERROR
								})
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
					Ext.getCmp('add_form').form.reset();
					var win = Ext.getCmp('add_win');
					win.hide();
				}
			}]
	});
	
	app.add_win = new Ext.Window({
		id:'add_win',
		title:'窗口',
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
			app.data_form
		]
	});	
	
	app.btn_add = new Ext.Button({
		text : '添加员工',
		iconCls:'icon-user_add',
		disabled:false,
		handler : function(){
			app.add_win.show();
			app.add_win.setTitle("添加");
			Ext.getCmp('data_form_reset').show();
		}
	});
	
	app.btn_disable = new Ext.Button({
		text : '禁用员工',
		iconCls:'icon-disable',
		disabled:true,
		handler : function(){
			var records = app.grid.getSelectionModel().getSelections();
			if(records.length == 0 ){
				Ext.Msg.show({
					title : '提示',
					msg:'请选择需要禁用的员工',
					buttons : Ext.Msg.OK,
					icon : Ext.Msg.ERROR
				});
			}else{
				var ids = [];
				for(i = 0;i < records.length;i++){
					ids.push(records[i].get('id'));
				}
				Ext.Msg.confirm('提示', '你确定禁用所选记录?', function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
							url : project+'/staff/disable.cgi',
							params : {
								id : ids
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
									app.ds_data.load({
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
	});
	
	app.btn_enable = new Ext.Button({
		text : '启用员工',
		iconCls:'icon-accept',
		disabled:true,
		handler : function(){
			var records = app.grid.getSelectionModel().getSelections();
			if(records.length == 0 ){
				Ext.Msg.show({
					title : '提示',
					msg:'请选择需要启用的员工',
					buttons : Ext.Msg.OK,
					icon : Ext.Msg.ERROR
				});
			}else{
				var ids = [];
				for(i = 0;i < records.length;i++){
					ids.push(records[i].get('id'));
				}
				Ext.Msg.confirm('提示', '你确定启用用所选记录?', function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
							url : project+'/staff/enable.cgi',
							params : {
								id : ids
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
									app.ds_data.load({
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
			}}
	});
	
    app.btn_update = new Ext.Button({
		text : '修改',
		iconCls : 'icon-edit',
		disabled:true,
		handler : function(){
			if(app.grid.getSelectionModel().getSelected()){
				Ext.getCmp('data_form_reset').hide();
 				var record = app.grid.getSelectionModel().getSelected();
				app.data_form.getForm().loadRecord(record);
				app.add_win.show();
				app.add_win.setTitle("修改员工信息");
			}else{
				Ext.Msg.show({
					title : '系统提示',
					msg : '请选择需要修改的数据业务!',
					buttons : Ext.Msg.OK,
					icon : Ext.MessageBox.ERROR
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

	app.searchcode = function() {
		app.values =app.text_search_code.getValue();
		Ext.Ajax.request({
			url : project+'/staff/list.cgi',
			params : {
				start : 0,
				limit : app.limit,
				colName : app.colName,
				value : app.values
			},
			success:function(response,option){
				var obj = Ext.util.JSON.decode(response.responseText);
				if(obj.success){
					app.ds_data.load({
						params : {
							start : 0,
							limit : app.limit,
							colName : app.colName,
							value : app.values
						}
					});
				}else{
	             	Ext.MessageBox.alert('提示',obj.msg);
				}
			},
            failure:function(){
             	Ext.MessageBox.alert('提示','服务器内部错误');
            }
		})
	};
	
	app.text_phone_number = new Ext.form.TextField({
		name : 'phone_number',
		width : 150,
		allowBlank:false,
		vtype:'mobile'
	});
	
	app.ds_data = new Ext.data.Store({
		url : '/staff/list.cgi',
		baseParams:{},
		reader : new Ext.data.JsonReader({
			totalProperty : 'count',
			root : 'obj'
		}, [{name : 'username',type : 'string'
		}, {name : 'mobile',type : 'string'
		}, {name : 'officephone',type : 'string'
		}, {name : 'channelcode',type : 'string'
		}, {name : 'password',type : 'string'
		}, {name : 'status',type : 'int'
		}, {name : 'createtime',type:'string'
		}, {name : 'id',type:'int'
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
	
	/**
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
	**/  
	
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
	    height:500,
        autoScroll: true,
		sm:app.sm,
		tbar : [app.btn_detail,'-',app.btn_add,'-',app.btn_disable,'-',app.btn_enable,'-',app.btn_update,'-','请输入员工名:',app.text_search_code,'-',app.btn_search_code],
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
					app.btn_disable.enable();
					app.btn_enable.enable();
					app.btn_add.disable();
					app.btn_detail.enable();
					app.btn_update.enable();
				}else{
					app.btn_disable.disable();
					app.btn_enable.disable();
					app.btn_add.enable();
					app.btn_detail.disable();
					app.btn_update.disable();
				}
	});
	
    app.grid.render('staff');
});

var ds_hstype = new Ext.data.Store({
	proxy:new Ext.data.HttpProxy({
		url:'/hstype/list.cgi'
	}),
	reader : new Ext.data.JsonReader({
		root : 'obj'
	}, [{name : 'id',type : 'int'},
		{name : 'name',type : 'string'},
		{name : 'icon',type : 'string'}
		
	])
});

function vmobile(v){
     var r = /^((\(\d{2,3}\))|(\d{3}\-))?1?[3,5,8]\d{9}$/;
     return r.test(v);
}
