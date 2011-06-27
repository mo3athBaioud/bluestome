/**
 * 渠道管理
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
        {header: "渠道代码", width: 100, sortable: true, dataIndex: 'channelcode'},
        {header: "渠道名称", width: 100, sortable: true, dataIndex: 'channlename'},
        {header: "渠道地址", width: 100, sortable: true, dataIndex: 'address'},
        {header: "渠道状态", width: 150, sortable: true, dataIndex: 'status',renderer:function(v){
        	if(v == 0){
        		return '<font color="red">不可用</font>';
        	}else if(v == 1){
        		return '<font color="blue">可用</font>';
        	}else{
        		return '<font color="yellow">未知</font>';
        	}
        }},
        {header: "业务区代码", width: 100, sortable: true, dataIndex: 'bdcode'},
        {header: "创建时间", width: 120, sortable: true, dataIndex: 'createtime'}
    ]);
    
	app.btn_detail = new Ext.Button({
		text : '详情',
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
//		disabled:true,
		iconCls : 'icon-search',
		handler : function(){
			Ext.Msg.show({
				title : '提示',
				msg : '该功能正在开发!',
				buttons : Ext.Msg.OK,
				icon : Ext.Msg.INFO
			});
		}
	});
	
	app.btn_add = new Ext.Button({
		text : '添加',
		iconCls:'icon-add',
		disabled:true,
		handler : function(){
			Ext.Msg.show({
				title : '提示',
				msg : '该功能正在开发!',
				buttons : Ext.Msg.OK,
				icon : Ext.Msg.INFO
			});
		}
	});
	
	app.btn_disable = new Ext.Button({
		text : '禁用',
		iconCls:'icon-disable',
		disabled:true,
		handler : function(){
			Ext.Msg.show({
				title : '提示',
				msg : '该功能正在开发!',
				buttons : Ext.Msg.OK,
				icon : Ext.Msg.INFO
			});
		}
	});
	
	app.btn_enable = new Ext.Button({
		text : '启用',
		iconCls:'icon-accept',
		disabled:true,
		handler : function(){
			Ext.Msg.show({
				title : '提示',
				msg : '该功能正在开发!',
				buttons : Ext.Msg.OK,
				icon : Ext.Msg.INFO
			});
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
	});
	
	app.ds_data = new Ext.data.Store({
		url : '/channel/list.cgi',
		baseParams:{},
		reader : new Ext.data.JsonReader({
			totalProperty : 'count',
			root : 'obj'
		}, [{name : 'bdcode',type : 'string'
		}, {name : 'status',type : 'string'
		}, {name : 'address',type : 'string'
		}, {name : 'channelcode',type : 'string'
		}, {name : 'channlename',type : 'string'
		}, {name : 'createtime',type:'string'
		}])
	});
	
	app.ds_data.load({
		params : {
			start : 0,
			limit : app.limit
		}
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
	    height:500,
        autoScroll: true,
		sm:app.sm,
		tbar : [app.btn_detail,'-',app.btn_add,'-',app.btn_disable,'-',app.btn_enable,'-','请输入渠道名称:',app.text_search_code,'-',app.btn_search_code],
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
					app.btn_add.enable();
					app.btn_detail.enable();
				}else{
					app.btn_disable.disable();
					app.btn_enable.disable();
					app.btn_add.disable();
					app.btn_detail.disable();
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
