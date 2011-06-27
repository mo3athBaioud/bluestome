/**
 * 终端销售模块
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
	
    app.data = [
        ['18809130029','诺基亚','C7','￥2340','GSM,WCDMA','576分钟','3.5英寸','1677万色','支持','支持','泡泡网','2011-03-31 11:00:00'],
        ['15991490009','诺基亚','C5-03','￥2540','GSM,WCDMA','720分钟','3.2英寸','1677万色','支持','支持','泡泡网','2011-03-31 11:00:00'],
        ['15991303686','诺基亚','5230','￥1840','GSM,WCDMA','420分钟','3.2英寸','1677万色','支持','支持','泡泡网','2011-03-31 11:00:00'],
        ['15991178167','诺基亚','N8','￥2640','GSM,WCDMA','720分钟','3.5英寸','1677万色','支持','支持','泡泡网','2011-03-31 11:00:00'],
        ['15991137808','诺基亚','C6','￥2140','GSM,WCDMA','576分钟','3.2英寸','1677万色','支持','支持','泡泡网','2011-03-31 11:00:00'],
        ['15929272894','诺基亚','5800XM','￥2260','GSM,WCDMA','525分钟','3.2英寸','1677万色','支持','支持','泡泡网','2011-03-31 11:00:00'],
        ['15929268215','摩托罗拉','MB525 Defy','￥2070','GSM,WCDMA','590分钟','3.7英寸','26万色','支持','支持','泡泡网','2011-03-31 11:00:00'],
        ['15929234501','摩托罗拉','CLIQ(MB200)','￥2120','GSM,WCDMA','360分钟','3.1英寸','1677万色','支持','支持','泡泡网','2011-03-31 11:00:00'],
        ['15891323741','摩托罗拉','XT502','￥1940','GSM,WCDMA','400分钟','3.2英寸','26万色','支持','支持','泡泡网','2011-03-31 11:00:00'],
        ['15909130008','摩托罗拉','XT720','￥1840','GSM,WCDMA','540分钟','3.7英寸','1677万色','支持','支持','泡泡网','2011-03-31 11:00:00']
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
            '<p><b>图标:</b><img src="{icon}"/></p>',
            '<p><b>通话时间:</b>{thsc}</p>',
            '<p><b>屏幕大小:</b>{screensize}</p>',
            '<p><b>屏幕颜色:</b>{screencolor}</p>',
            '<p><b>摄像头:</b>{camera}</p>',
            '<p><b>蓝牙:</b>{bluetooth}</p>'
        )
	 });
	 
//   ['品牌','型号','TAC','手机制式','通话时间','手机屏幕','主屏颜色','摄像头','蓝牙','来源','采集时间']
    app.cm_utp = new Ext.grid.ColumnModel([
//	    app.expander,
		app.sm,
        {header: "手机号码", width: 100, sortable: true, dataIndex: 'phonenum'},
        {header: "推荐品牌", width: 100, sortable: true, dataIndex: 'brand'},
        {header: "推荐型号", width: 80, sortable: true, dataIndex: 'model'},
        {header: "价格", width: 150, sortable: true, dataIndex: 'tac'}
    ]);
    
    app.cm_hstype = new Ext.grid.ColumnModel([
	    app.expander,
        {header: "ID", width: 100, sortable: true, dataIndex: 'id'},
        {header: "型号", width: 100, sortable: true, dataIndex: 'name'},
//        {header: "图标", width: 100, sortable: true, dataIndex: 'icon',
//        	renderer:function(v){ 
//				return '<img src='+v+'/>';
//		}},
        {header: "创建时间", width: 100, sortable: true, dataIndex: 'createtime'}
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
		allowBlank:false,
		vtype:'mobile',
		listeners : {
			'specialkey' : function(field, e) {
				if (e.getKey() == Ext.EventObject.ENTER) {
					app.searchcode();
				}
			}
		}
	});
	
	app.searchcode = function() {
		var values =app.text_tac_code.getValue();
		if(null != values && '' !=  values){
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
		}
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
	
//   ['品牌','型号','TAC','手机制式','通话时间','手机屏幕','主屏颜色','摄像头','蓝牙','来源','采集时间']
	app.ds_utp = new Ext.data.ArrayStore({
        fields: [
           {name:'phonenum',type:'string' },
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
    
 	app.ds_hstype = new Ext.data.Store({
		url : '/hstype/list.cgi',
		baseParams:{
			hid:-1
		},
		reader : new Ext.data.JsonReader({
			totalProperty : 'count',
			root : 'obj'
		}, [{name: 'id',type:'int'},
           {name: 'name',type:'string'},
           {name: 'hsmanId',type:'int'},
           {name: 'remarks',type:'string'},
           {name: 'createtime',type:'string'},
           {name: 'status',type:'int'},
           {name: 'icon', type: 'string'}
        ])
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
		title : name,
		iconCls : 'icon-monitor_lightning',
		region : 'center',
		loadMask : {
			msg : '数据加载中...'
		},
	    cm: app.cm_utp,
//		cm: app.cm_hstype,
	    ds: app.ds_utp,
//		ds: app.ds_hstype,
//	    width:1000,
	    height:550,
        autoScroll: true,
        viewConfig: {
            forceFit:true
        },
 		plugins: app.expander,
		sm:app.sm,
		tbar : ['手机号码:',app.text_tac_code,app.btn_search_code],
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

    app.grid.render('terminal');
    
	var root = new Ext.tree.AsyncTreeNode({
		text : '手机品牌',
		expanded : true,
		id : '001'
	});
	
	var hsBrandTree = new Ext.tree.TreePanel({
		loader : new Ext.tree.TreeLoader({
					baseAttrs : {},
					dataUrl:'/hsman/tree.cgi'
				}),
		root : root,
//		applyTo : 'terminal_brand',
		autoScroll : false,
		animate : false,
		useArrows : false,
		border : false
	});
	
	hsBrandTree.root.select();
	
	hsBrandTree.on('click', function(node) {
//		app.ds_utp.loadData(app.data);
		/**
		**/
		var hid = node.attributes.id;
		app.ds_hstype.load({
			params : {
				start : 0,
				limit : 50,
				hid : hid
			}
		});
	});
	
	/**
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
					items : [hsBrandTree]
				}, {
					region : 'center',
					layout : 'fit',
					items : [app.grid]
				}]
	});
	**/
}); 

function vtac(v){
     var r = /^((\(\d{2,3}\))|(\d{3}\-))?1?[3,5,8]\d{9}$/;
     return r.test(v);
}

function vmobile(v){
     var r = /^((\(\d{2,3}\))|(\d{3}\-))?1?[3,5,8]\d{9}$/;
     return r.test(v);
}