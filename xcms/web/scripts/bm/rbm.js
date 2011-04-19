var app = {};
Ext.onReady(function(){
	
  	Ext.BLANK_IMAGE_URL = '/resource/image/ext/s.gif';
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    Ext.QuickTips.init();
	app.limit = 15;
	app.colName = "";
	app.values = "";
    app.sm = new Ext.grid.CheckboxSelectionModel();
    
    //品牌,机型,功能,网络制式,指导价格,租机形式,业务状态
    app.data = [
        ['Nokia','C5','功能描述','GSM/WCDMA','￥3548','合约机,在网2年',1],
        ['Nokia','C6','功能描述','GSM/WCDMA','￥2548','合约机,在网2年',1]
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
        '<p><b>租机形式:</b><br/>{remarks}</p>',
        '<p><b>功能:</b><br/>{functions}</p>'
        )
    });
    
    function doo(obj){
		if(confirm( "Statement here. ")){ 
		   	alert('show obj:'+obj);
		}else{ 
			return; 
		}
    }
    
	app.btn_add = new Ext.Button({
		text : '新增',
		iconCls : 'icon-add',
		handler : function(){
			app.add_win.show();
			app.add_win.setTitle("添加");
			/**
			Ext.Msg.show({
				title : '系统提示',
				msg : '需要弹出新增的租机业务详情的窗口！',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.INFO
			});
			**/
		}
	});
	
	app.btn_bat_import = new Ext.Button({
		text : '批量导入',
		iconCls : 'icon-cog_add',
		handler : function(){
			Ext.Msg.show({
				title : '系统提示',
				msg : '需要弹出上传的租机业务文件详情的窗口！',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.INFO
			});
		}
	});
	
    app.btn_update = new Ext.Button({
		text : '修改',
		iconCls : 'icon-edit',
		handler : function(){
			if(app.grid.getSelectionModel().getSelected()){
				var record = app.grid.getSelectionModel().getSelected();
				app.add_form.getForm().loadRecord(record);
				app.add_win.show();
				app.add_win.setTitle("修改");
				Ext.getCmp('btn_win_reset').hide();
				/**
					Ext.Msg.show({
						title : '系统提示',
						msg : '需要弹出修改的租机业务详情的窗口！',
						buttons : Ext.Msg.OK,
						fn:function(){
							app.ds_utp.loadData(app.data);
						},
						icon : Ext.MessageBox.INFO
					});
				**/ 
			}else{
				Ext.Msg.show({
					title : '系统提示',
					msg : '请选择需要修改的租机业务!',
					buttons : Ext.Msg.OK,
					icon : Ext.MessageBox.ERROR
				});
			}
		}
	}); 
    
    app.btn_disable = new Ext.Button({
		text : '停用',
		iconCls : 'icon-cross',
		handler : function(){
			if(app.grid.getSelectionModel().getSelected()){
					Ext.Msg.show({
						title : '系统提示',
						msg : '已选的租机业务已停用',
						buttons : Ext.Msg.OK,
						fn:function(){
							app.ds_utp.loadData(app.data);
						},
						icon : Ext.MessageBox.INFO
					});
			}else{
				Ext.Msg.show({
					title : '系统提示',
					msg : '请选择需要停用的租机业务!',
					buttons : Ext.Msg.OK,
					icon : Ext.MessageBox.ERROR
				});
			}
		}
	}); 
	
    app.cm_utp = new Ext.grid.ColumnModel([
	    expander,
        {header: "品牌", width: 100, sortable: true, dataIndex: 'brand'},
        {header: "型号", width: 100, sortable: true, dataIndex: 'model'},
        {header: "网络制式", width: 100, sortable: true, dataIndex: 'hs_type'},
        {header: "指导价格", width: 100, sortable: true, dataIndex: 'hs_price'},
        {header: "状态", width: 80, sortable: true, dataIndex: 'status',renderer:function(v){
        	var x = parseInt(v);
        	switch(x){
        		case 0:
        			return '<font color="red">业务废除</font>';
        		case 1:
        			return '<font color="blue">业务可用</font>';
        		default:
        			return '<font color="yellow">未知</font>';
        	}
        }}
    ]);
    
	app.btn_search_code = new Ext.Button({
		text : '查询',
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
		app.ds_utp.removeAll();
		app.ds_utp.loadData(app.data);
	};
	
	app.add_form = new Ext.FormPanel({
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
			//下拉选择框
			xtype:'combo',
			fieldLabel : '手机品牌',
			hiddenName:'brand',
            valueField: 'id',
            displayField: 'name',
            triggerAction:'all',
            mode: 'local',
            store: new Ext.data.SimpleStore({
                fields: ['id','name'],
                data: [[1,'诺基亚'], [2,'摩托罗拉'],[3,'联想']]
            }),
            editable:false,
			emptyText : '请选择手机品牌!',
			allowBlank : false
		},{
			//下拉选择框
			xtype:'combo',
			fieldLabel : '手机型号',
			hiddenName:'model',
            valueField: 'id',
            displayField: 'name',
            triggerAction:'all',
            mode: 'local',
            store: new Ext.data.SimpleStore({
                fields: ['id','name'],
                data: [[1,'C7'],[2,'C5'],[3,'5230'],[4,'MB525 Defy'],[5,'ME525'],[6,'XT502'],[7,'S708'],[8,'S710'],[9,'TD80t'],[10,'EX128']]
            }),
            editable:false,
			emptyText : '请选择手机型号!',
			allowBlank : false
		},{
			//下拉选择框
			xtype:'combo',
			fieldLabel : '网络制式',
			hiddenName:'hs_type',
            valueField: 'id',
            displayField: 'name',
            triggerAction:'all',
            mode: 'local',
            store: new Ext.data.SimpleStore({
                fields: ['id','name'],
                data: [[1,'GSM'],[2,'WCDMA'],[3,'TDS-CDMA']]
            }),
            editable:false,
			emptyText : '请选择网络制式!',
			allowBlank : false
		},{
			fieldLabel : '指导价格',
			name : 'hs_price',
			allowBlank : false
		},{
			fieldLabel : '租机形式',
			xtype:'textarea',
			name : 'remarks',
			allowBlank : false
		},{
			fieldLabel : '功能',
			xtype:'textarea',
			name : 'functions',
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
                data: [[1,'业务可用'],[0,'业务不可用']]
            }),
            editable:false,
			emptyText : '请选择业务状态!',
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
							Ext.getCmp('btn_win_reset').hide();
							app.ds_utp.loadData(app.data);
						},
						icon : Ext.MessageBox.INFO
					});
				}
			}
		}, {
			id:'btn_win_reset',
			iconCls:'icon-arrow_rotate_anticlockwise',
			buttonAlign : 'center',
			text : '重置',
			handler : function() {
				Ext.getCmp('add_form').form.reset();
			}
		}, {
			buttonAlign : 'center',
			iconCls:'icon-delete',
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
//		title : '新增',
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
			app.add_form
		]
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
	
	app.ds_utp = new Ext.data.ArrayStore({
        fields: [
           {name: 'brand', type: 'string'},
           {name: 'model', type: 'string'},
           {name: 'functions', type: 'string'},
           {name: 'hs_type', type: 'string'},
           {name: 'hs_price', type: 'string'},
           {name: 'remarks', type: 'string'},
           {name: 'status', type: 'int'}
        ]
    });
	
	app.ptb = new Ext.PagingToolbar({
		pageSize:app.limit,
		store:app.ds_utp,
		displayInfo:true,
		displayMsg:'第 {0} - {1} 条  共 {2} 条',
		emptyMsg:'没有记录'
	});
		
	app.grid = new Ext.grid.GridPanel({
		title : '租机业务管理',
		iconCls : 'icon-bricks',
		region : 'center',
		loadMask : {
			msg : '数据加载中...'
		},
	    cm: app.cm_utp,
	    ds: app.ds_utp,
//	    width:850,
	    height:550,
        autoScroll: true,
        viewConfig: {
            forceFit:true
        },
 		plugins: expander,
		sm:app.sm,
		tbar : [app.btn_add,'-',app.btn_bat_import,'-',app.btn_update,'-',app.btn_disable,'-',app.text_search_code,'-',app.btn_search_code]
//		bbar : app.ptb
	});
	
	app.grid.addListener('rowdblclick',function(grid, rowIndex){
				if(grid.getSelectionModel().isSelected(rowIndex)){
					Ext.MessageBox.alert('提示','功能正在开发!',function(){alert('Function');});
				}
	});

    app.grid.render('utp');
});