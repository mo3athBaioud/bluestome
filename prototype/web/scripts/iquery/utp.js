var app = {};
Ext.onReady(function(){
	
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    Ext.QuickTips.init();
	app.limit = 15;
	app.colName = "";
	app.values = "";
    app.sm = new Ext.grid.CheckboxSelectionModel();
    
    app.data = [
        ['15800371329','魅族','M8','支持','支持']
//        ['15801801342','夏普','U330','支持','支持']
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
			 '<span align="left"><b>终端功能参数</b></span><br/><table align="left" class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr>' +
			 '<td><b>mms:</b>{mms}</td>' +
			 '<td><b>gprs:</b>{gprs}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>wap:</b>{mms}</td>' +
			 '<td><b>kjava:</b>{gprs}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>edge:</b>{mms}</td>' +
			 '<td><b>3g:</b>{gprs}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>email:</b>{mms}</td>' +
			 '<td><b>手机动画:</b>{gprs}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>streamiNg:</b>{mms}</td>' +
			 '<td><b>IMPS:</b>{gprs}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>POC:</b>{mms}</td>' +
			 '<td><b>电视:</b>{gprs}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>智能手机:</b>{mms}</td>' +
			 '<td><b>智能操作系统:</b>{gprs}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>LCD彩色:</b>{mms}</td>' +
			 '<td><b>LCD尺寸:</b>{gprs}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>内存:</b>{mms}</td>' +
			 '<td><b>振铃格式:</b>{gprs}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>多媒体格式:</b>{mms}</td>' +
			 '<td><b>摄像头(内置):</b>{gprs}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>MP3:</b>{mms}</td>' +
			 '<td><b>音乐手机(DRM):</b>{gprs}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>可视电话:</b>{mms}</td>' +
			 '<td><b>USSD:</b>{gprs}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>STK:</b>{mms}</td>' +
			 '<td><b>EFR:</b>{gprs}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>红外:</b>{mms}</td>' +
			 '<td><b>RS232:</b>{gprs}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>数据线:</b>{mms}</td>' +
			 '<td><b>预留1:</b>{gprs}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>预留2:</b>{mms}</td>' +
			 '<td><b>预留3:</b>{gprs}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>预留4:</b>{mms}</td>' +
			 '<td><b>预留5:</b>{gprs}</td>' +
			 '</tr>' +
			 '</table><br/>',
			 '<span align="left"><b>终端业务参数</b></span><br/><table align="left" class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr>' +
			 '<td><b>移动证券:</b>{mms}</td>' +
			 '<td><b>手机导航:</b>{gprs}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>手机地图:</b>{mms}</td>' +
			 '<td><b>音乐随身听:</b>{gprs}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>全曲下载:</b>{mms}</td>' +
			 '<td><b>WAP全曲下载:</b>{gprs}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>扫描上网:</b>{mms}</td>' +
			 '<td><b>名片识别:</b>{gprs}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>手机电视:</b>{mms}</td>' +
			 '<td><b>手机支付:</b>{gprs}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>手机邮箱:</b>{mms}</td>' +
			 '<td><b>飞信:</b>{gprs}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>移动OA:</b>{mms}</td>' +
			 '<td><b>移动MM:</b>{gprs}</td>' +
			 '</tr>' +
			 '</table>'
			 
        ),
        lazyRender : true
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
		title : '用户终端属性查询',
		iconCls : 'icon-plugin',
		region : 'center',
		loadMask : {
			msg : '数据加载中...'
		},
	    cm: app.cm_utp,
	    ds: app.ds_utp,
	    width:850,
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
					var record = app.grid.getSelectionModel().getSelected();
					var url = String.format("../../pages/images/image.jsp?id={0}",record.get('d_id'));
					window.location = url;
				}
	});

    app.grid.render('utp');
});