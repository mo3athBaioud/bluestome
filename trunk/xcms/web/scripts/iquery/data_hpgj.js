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
        ['15800371329','魅族','M8','支持','支持','81234798','81234798756463728']
//        ['15801801342','夏普','U330','支持','支持','81234798','81234798756463738']
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
    
    var expander = new Ext.grid.RowExpander({
        tpl : new Ext.Template(
			 '<tpl for=".">',
			 '<p>',
			 '业务介绍:<br/>GPRS即“通用分组无线业务”（GeneralPacketRadioService的英文简称），' +
			 '是现有GSM网络上开通的一种新型的分组数据传输技术。具有“永远在线”、“自如切换”、“高速传送”等优点，它能全面提升移动数据通信服务，' +
			 '使“移动梦网”服务更丰富、功能更强大，给客户的生活和工作带来更多便捷与实惠。上网速度约50－70Kbps，最高理论值为171.2Kbps。',
			 '</p>',
			 '<p>',
			 '<img src="http://image.c114.net/0901150.gif" alt="套餐列表"/>',
			 '</p>',
			 '</tpl>',
			 '<div class="x-clear"></div>')
    });
    
    app.cm_utp = new Ext.grid.ColumnModel([
//	    expander,
		app.sm,
        {header: "手机号码", width: 100, sortable: true, dataIndex: 'phoneNumber'},
        {header: "品牌", width: 100, sortable: true, dataIndex: 'hsmanName'},
        {header: "品牌(英文)", width: 100, sortable: true, dataIndex: 'hsmanEnName',renderer:function(v){
        	if(null == v || '' == v){
        		return '<font color="red">暂无英文名</font>';
        	}else{
        		return '<font color="blue">'+v+'</font>';
        	}
        }},
        {header: "型号", width: 100, sortable: true, dataIndex: 'hstypeName'},
        {header: "型号(英文)", width: 100, sortable: true, dataIndex: 'hstypeEnName',renderer:function(v){
        	if(null == v || '' == v){
        		return '<font color="red">暂无英文名</font>';
        	}else{
        		return '<font color="blue">'+v+'</font>';
        	}
        }},
        {header: "号谱管家", width: 150, sortable: true, dataIndex: 'gprs',renderer:function(v){
        	if(v == 0){
        		return '<font color="red">不支持</font>';
        	}else if(v == 1){
        		return '<font color="blue">支持</font>';
        	}else{
        		return '<font color="yellow">未知</font>';
        	}
        }},
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
		allowBlank:false,
		vtype:'mobile'
//		,
//		listeners:{
//			change:function(obj){
//				if(!obj.validate()){
//					alert('输入的参数不合法');
//				}else{
//					app.btn_search_code.enable();
//				}
//			}
//		}
	});
	
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
	        	allowBlank : false,
				vtype:'integer',
				minLength:15,
				maxLength:15,
				maxLengthText:'IMEI最长不能超过15位'
	        },{
	            fieldLabel: '手机号码',
	        	name:'phonenum',
            	emptyText : '请输入手机号码!',
            	allowBlank : false,
            	vtype:'mobile',
				minLength:11,
				maxLength:11,
				maxLengthText:'手机号码最长不能超过11位'
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
								msg : '非常感谢您的参与！',
								buttons : Ext.Msg.OK,
								fn:function(){
									app.ds_utp_1.loadData(app.data);
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
			});
				
		app.add_terminal_win = 	new Ext.Window({
			id:'terminal_add_win',
			title:'终端录入窗口',
			width:650,
//			height:550,
			autoHeight:true,
			resizable : false,
			modal : true,
			closeAction : 'hide',
			animCollapse : true,
			pageY : 20,
			pageX : document.body.clientWidth / 2 - 420 / 2,
			animateTarget : Ext.getBody(),
			constrain : true,
			listeners : {
				'hide' : function() {
					Ext.getCmp('add_form').form.reset();
				}
			},
			items:[
				app.dbm_form
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
					app.ds_utp_1.removeAll();
				},
				icon : Ext.MessageBox.ERROR
			});
			return false;
		}
		/**
		if(true){
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
		**/
		Ext.Ajax.request({
			url : '/utp/gprs.cgi',
			params : {
				phonenum : values,
				loginname :loginName,
				start:0,
				limit:app.limit
			},
			success:function(response,option){
				var obj = Ext.util.JSON.decode(response.responseText);
				if(obj.success){
					app.ds_utp_1.load({
						params : {
							phonenum : values,
							loginname :loginName,
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
//		app.ds_utp.loadData(app.data);
	};
	
	app.ds_utp = new Ext.data.Store({
		url : '/utp/utp.cgi',
		baseParams:{},
		reader : new Ext.data.JsonReader({
			totalProperty : 'count',
			root : 'obj'
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
	
	app.ds_utp_1 = new Ext.data.Store({
		url : '/utp/hpgj.cgi',
		baseParams:{},
		reader : new Ext.data.JsonReader({
			totalProperty : 'count',
			root : 'obj'
		}, [{name : 'phoneNumber',type : 'string'
		}, {name : 'imei',type : 'string'
		}, {name : 'tac',type : 'string'
		}, {name : 'hsmanName',type : 'string'
		}, {name : 'hstypeName',type : 'string'
		}, {name : 'gprs',type : 'int'
		}, {name : 'rbit',type:'string'
		}, {name : 'createtime',type:'string'
		}, {name : 'recommdation',type:'string'
		}, {name : 'bcode',type:'string'
		}, {name : 'uid',type:'string'
		}, {name : 'id',type:'int'
		}])
	});
	/**	
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
	**/
    
	app.ptb = new Ext.PagingToolbar({
		pageSize:app.limit,
		store:app.ds_utp_1,
		displayInfo:true,
		displayMsg:'第 {0} - {1} 条  共 {2} 条',
		emptyMsg:'没有记录'
	});
		
	app.grid = new Ext.grid.GridPanel({
		title : name,
		iconCls : 'icon-application_view_columns',
		region : 'center',
		loadMask : {
			msg : '数据加载中...'
		},
	    cm: app.cm_utp,
//	    ds: app.ds_utp,
		ds: app.ds_utp_1,
//	    width:850,
	    height:500,
        autoScroll: true,
        viewConfig: {
            forceFit:true
        },
 		plugins: expander,
		sm:app.sm,
		tbar : ['请输入手机号码：',app.text_phone_number,'-',app.btn_search_code],
		bbar : app.ptb
	});
	
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

    app.grid.render('utp');
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
