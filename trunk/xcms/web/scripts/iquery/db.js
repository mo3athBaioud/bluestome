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
        ['15800371329','魅族','M8','支持','支持'],
        ['15800371330','摩托罗拉','ME525','支持','支持']
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
        {header: "手机号码", width: 100, sortable: true, dataIndex: 'sn'},
        {header: "品牌", width: 100, sortable: true, dataIndex: 'brand'},
        {header: "型号", width: 100, sortable: true, dataIndex: 'model'}
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
           {name: 'sn',type:'string'},
           {name: 'brand', type: 'string'},
           {name: 'model', type: 'string'},
           {name: 'mms', type: 'string'},
           {name: 'gprs', type: 'string'}
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
		title : '数据业务推荐查询',
		iconCls : 'icon-application_view_columns',
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
		tbar : ['请输入手机号码:',app.text_search_code,'-',app.btn_search_code]
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