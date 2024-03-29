var app = {};
Ext.onReady(function(){
  	Ext.BLANK_IMAGE_URL = '/resource/image/ext/s.gif';
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    Ext.QuickTips.init();
	app.limit = 15;
	app.colName = "";
	app.values = "";
    app.sm = new Ext.grid.CheckboxSelectionModel();
    
    app.data = [
        ['15800371329','魅族','M8','支持','支持','81234798','81234798756463728'],
        ['15800371330','摩托罗拉','ME525','支持','支持','81234798','81234798756463728']
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
    
    // align="center" valign="middle"
    var expander = new Ext.grid.RowExpander({
        tpl : new Ext.Template(
			 '<tpl for=".">',
			 '<table>' +
			 '<tr><td>' +
			 '<table align="left" class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr><td><b>优先等级1</b></td></tr>' +
			 '<tr align="left"><td>' +
			 '<ul>' +
			 '<li>飞信[<font color="blue">使用</font>]</li>' +
			 '<li>移动MM[<font color="blue">使用</font>]</li> ' +
			 '<li><b>手机阅读[<font color="red">未使用</font>]</b></li>' +
			 '</ul>' +
			 '</td></tr>' +
			 '</table> '+
			 '</td><td>' +
			 '<table align="left" class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr><td><b>优先等级2</b></td></tr>' +
			 '<tr align="left"><td>' +
			 '<ul>' +
			 '<li>淘乐汇[<font color="blue">使用</font>]</li> 	'+
			 '<li>手机报[<font color="blue">使用</font>]</li>' +
			 '<li><b>号谱管家[<font color="red">未使用</font>]</b></li>' +
			 '</ul>' +
			 '</td></tr>' +
			 '</table>' +
			 '</td><td>' +
			 '<table align="left" class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr><td><b>优先等级3</b></td></tr>' +
			 '<tr align="left"><td>' +
			 '<ul>'+
			 '<li><b>可视电话[<font color="red">未使用</font>]</b></li>' +
			 '<li><b>彩信相册[<font color="red">未使用</font>]</b></li>' +
			 '<li><b>移动秘书[<font color="red">未使用</font>]</b></li>'+
			 '</ul>' +
			 '</td></tr>' +
			 '</table>' +
			 '</td></tr>' +
			 '</table>',
			 '</tpl>',
			 '<div class="x-clear"></div>')
    });
    
    app.cm_utp = new Ext.grid.ColumnModel([
	    expander,
//        {header: "手机号码", width: 100, sortable: true, dataIndex: 'sn'},
//        {header: "品牌", width: 100, sortable: true, dataIndex: 'brand'},
//        {header: "型号", width: 100, sortable: true, dataIndex: 'model'},
//        {header: "TAC", width: 100, sortable: true, dataIndex: 'tac'},
//        {header: "IMEI", width: 100, sortable: true, dataIndex: 'imei'}
        {header: "手机号码", width: 100, sortable: true, dataIndex: 'phoneNum'},
        {header: "品牌", width: 100, sortable: true, dataIndex: 'hsmanName'},
        {header: "品牌(英文)", width: 100, sortable: true, dataIndex: 'hsmanEnName'},
        {header: "型号", width: 100, sortable: true, dataIndex: 'hstypeName'},
        {header: "型号(英文)", width: 100, sortable: true, dataIndex: 'hstypeEnName'},
        {header: "TAC", width: 100, sortable: true, dataIndex: 'tac'},
        {header: "IMEI", width: 100, sortable: true, dataIndex: 'imei'}
    ]);
    
	app.btn_search_code = new Ext.Button({
		text : '查询',
		disabled:false,
		iconCls : 'icon-search',
		handler : function(){
			app.searchcode();
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

	app.text_phone_number = new Ext.form.TextField({
		name : 'phone_number',
		width : 150,
		vtype:'mobile'
//		,
//		listeners:{
//			change:function(){
//				if(!this.validate()){
//					if(app.btn_search_code.disabled == false){
//						app.btn_search_code.disabled();
//					}
//				}else{
//					if(app.btn_search_code.disabled){
//						app.btn_search_code.enable();
//					}
//				}
//			}
//		}
	});

	app.add_terminal_win = 	new Ext.Window({
		id:'terminal_add_win',
		title:'终端录入窗口',
		width:650,
		height:500,
		resizable : false,
		modal : true,
		closeAction : 'hide',
		animCollapse : true,
		pageY : 20,
		pageX : document.body.clientWidth / 2 - 420 / 2,
		animateTarget : Ext.getBody(),
		constrain : true,
		items:[
			app.dbm_form = new Ext.FormPanel({
				id:'add_form',
				labelWidth : 80,
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
	                    xtype:'combo',
	                    fieldLabel: '手机品牌',
						hiddenName:'terminal.hsbrand',
		                valueField: 'id',
		                displayField: 'name',
		                triggerAction:'all',
						mode:'remote',
						store:new Ext.data.Store({
							proxy:new Ext.data.HttpProxy({
								url:'/hsman/list.cgi'
							}),
							reader : new Ext.data.JsonReader({
								root : 'obj'
							}, [{name : 'id',type : 'int'},
								{name : 'name',type : 'string'}
							])
						}),
		                listeners:{
		                	select:function(combo,record,index){
		                		try{
		                			var parent=Ext.getCmp('hs_model_combo');
		                			//重载手机型号数据  这个是条件{params:{wiseID:this.value}}
		                			parent.enable();
									ds_hstype.proxy= new Ext.data.HttpProxy({
										url: '/hstype/list.cgi?hid=' + combo.value
									});
									ds_hstype.load();
		                		}catch(ex){
		                			Ext.MessageBox.alert(ex);
		                		}
		                	}
		                },
		                emptyText : '请选择手机品牌!',
		                editable:false,
		                allowBlank : false
	                },{
	                	id:'hs_model_combo',
	                    xtype:'combo',
	                    fieldLabel: '手机型号',
	                    disabled:true,
						hiddenName:'hsmodel',
		                valueField: 'id',
		                displayField: 'name',
		                triggerAction:'all',
						mode:'local',
						store:ds_hstype,
		                loadingText :'正在请求数据,请稍后',
		                emptyText : '请选择手机型号!',
		                editable:false,
		                allowBlank : false
	                },{
	                    fieldLabel: 'IMEI',
	                	name:'imei',
	                	emptyText : '请输入IMEI号(在手机状态按*#06#)!',
	                	allowBlank : false
	                },{
	                    fieldLabel: '手机号码',
	                	name:'phonenum',
	                	emptyText : '请输入手机号码!',
	                	allowBlank : false
	                }
				],
				buttonAlign : 'center',
				minButtonWidth : 60,
				buttons : [{
					id:'dbm_form_save',
					text : '保存',
					iconCls:'icon-accept',
					handler : function(btn) {
						var frm = Ext.getCmp('add_form').form;
						if (frm.isValid()) {
							frm.reset();
							var win = Ext.getCmp('terminal_add_win');
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
					id:'dbm_form_reset',
					buttonAlign : 'center',
					text : '重置',
					iconCls:'icon-arrow_refresh_small',
					handler : function() {
						Ext.getCmp('add_form').form.reset();
					}
				}, {
					id:'dbm_form_cancel',
					iconCls:'icon-delete',
					buttonAlign : 'center',
					text : '取消',
					handler : function() {
						Ext.getCmp('add_form').form.reset();
						var win = Ext.getCmp('terminal_add_win');
						win.hide();
					}
				}]
			})
		]
	});
	
	app.searchcode = function() {
		var values =app.text_phone_number.getValue();
		if(null == values || '' ==  values){
			Ext.Msg.show({
				title : '系统提示',
				msg : '请输入需要查询的手机号码！',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.ERROR
			});
			return false;
		}
		var b = vmobile(values);
		if(!b){
			Ext.Msg.show({
				title : '系统提示',
				msg : '输入的手机号码格式不对，请重新输入!',
				buttons : Ext.Msg.OK,
				fn:function(){
					app.ds_utp.removeAll();
				},
				icon : Ext.MessageBox.ERROR
			});
			return false;
		}
		/**
		if(false){
			Ext.Msg.show({
				title : '系统提示',
				msg : '未查找到您对应的终端，是否立即录入您当前的终端数据？',
				buttons : Ext.MessageBox.OKCANCEL,
				fn:function(btn){
					if(btn == 'ok'){
						app.add_terminal_win.show();
					}
				},
				icon : Ext.MessageBox.ERROR
			});
			return false;
		}
		app.ds_utp.loadData(app.data);
		**/
		Ext.Ajax.request({
			url : '/utp/search.cgi',
			params : {
				phonenum : values
			},
			success:function(response,option){
				var obj = Ext.util.JSON.decode(response.responseText);
				if(obj.success){
					app.ds_utp_1.load({
						params : {
							phonenum : values,
							start:0,
							limit:app.limit
						}
					}); //,colName:app.colName,value:app.values
				}else{
					Ext.Msg.show({
						title : '系统提示',
						msg : '未查找到您对应的终端，是否立即录入您当前的终端数据？',
						buttons : Ext.MessageBox.OKCANCEL,
						fn:function(btn){
							if(btn == 'ok'){
								app.add_terminal_win.show();
							}
						},
						icon : Ext.MessageBox.ERROR
					});
				}
			},
            failure:function(){
				Ext.Msg.show({
					title : '系统提示',
					msg : '未查找到您对应的终端，是否立即录入您当前的终端数据？',
					buttons : Ext.MessageBox.OKCANCEL,
					fn:function(btn){
						if(btn == 'ok'){
							app.add_terminal_win.show();
						}
					},
					icon : Ext.MessageBox.ERROR
				});
            }
		});
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
           {name: 'sn',type:'string'},
           {name: 'brand', type: 'string'},
           {name: 'model', type: 'string'},
           {name: 'mms', type: 'string'},
           {name: 'gprs', type: 'string'},
           {name: 'tac', type: 'string'},
           {name: 'imei', type: 'string'}
        ]
    });
	
	app.ds_utp_1 = new Ext.data.Store({
		url : '/utp/utp.cgi',
		baseParams:{},
		reader : new Ext.data.JsonReader({
			totalProperty : 'count',
			root : 'obj'
		}, [{name : 'phoneNum',type : 'string'
		}, {name : 'imei',type : 'string'
		}, {name : 'tac',type : 'string'
		}, {name : 'hsmanName',type : 'string'
		}, {name : 'hsmanEnName',type : 'string'
		}, {name : 'hstypeName',type : 'string'
		}, {name : 'hstypeEnName',type : 'string'
		}])
	});
	
	app.ptb = new Ext.PagingToolbar({
		pageSize:app.limit,
		store:app.ds_utp,
		displayInfo:true,
		displayMsg:'第 {0} - {1} 条  共 {2} 条',
		emptyMsg:'没有记录'
	});
		
	app.grid = new Ext.grid.GridPanel({
		title : '数据业务推荐查询',
		iconCls : 'icon-application_view_columns',
		region : 'center',
		loadMask : {
			msg : '数据加载中...'
		},
	    cm: app.cm_utp,
//	    ds: app.ds_utp,
		ds: app.ds_utp_1,
//	    width:850,
	    height:550,
        autoScroll: true,
        viewConfig: {
            forceFit:true
        },
 		plugins: expander,
		sm:app.sm,
		tbar : ['请输入手机号码：',app.text_phone_number,'-',app.btn_search_code]
//		bbar : app.ptb
	});
	
	app.grid.addListener('rowdblclick',function(grid, rowIndex){
				if(grid.getSelectionModel().isSelected(rowIndex)){
					Ext.Msg.show({
						title : '消息:双击可查看终端详情',
						width:300,
						msg : '该功能正在开发!',
						buttons : Ext.Msg.OK,
						icon : Ext.MessageBox.INFO
					});
				}
	});

    app.grid.render('utp');
});

function vmobile(v){
     var r = /^((\(\d{2,3}\))|(\d{3}\-))?1?[3,5,8]\d{9}$/;
     return r.test(v);
}

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
