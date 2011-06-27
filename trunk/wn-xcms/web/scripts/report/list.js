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
        ['13409134450','魅族','M8','支持','支持','81234798','81234798756463728'],
        ['13429760946','魅族','M8','支持','支持','81234798','81234798756463728'],
        ['13429762650','魅族','M8','支持','支持','81234798','81234798756463728'],
        ['13429763871','魅族','M8','支持','支持','81234798','81234798756463728'],
        ['13429766739','魅族','M8','支持','支持','81234798','81234798756463728'],
        ['13429767131','魅族','M8','支持','支持','81234798','81234798756463728'],
        ['13429767845','魅族','M8','支持','支持','81234798','81234798756463728'],
        ['13429768055','魅族','M8','支持','支持','81234798','81234798756463728'],
        ['13429769579','魅族','M8','支持','支持','81234798','81234798756463728'],
        ['13468640283','魅族','M8','支持','支持','81234798','81234798756463728'],
        ['13468640878','魅族','M8','支持','支持','81234798','81234798756463728'],
        ['13468644988','魅族','M8','支持','支持','81234798','81234798756463728'],
        ['15191328641','魅族','M8','支持','支持','81234798','81234798756463728'],
        ['15991490009','魅族','M8','支持','支持','81234798','81234798756463728'],
        ['15929841234','魅族','M8','支持','支持','81234798','81234798756463728']
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
//        tpl : new Ext.Template(
//			 '<span align="left"><b>终端功能参数</b></span><br/>' +
//			 '<table align="left" class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
//			 '<tr><td>' +
//			 '<table align="left" class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
//			 '<tr><td><b>mms:</b>{mms}</td>' +
//			 '<td><b>gprs:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>wap:</b>{mms}</td>' +
//			 '<td><b>kjava:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>edge:</b>{mms}</td>' +
//			 '<td><b>3g:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>email:</b>{mms}</td>' +
//			 '<td><b>手机动画:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>streamiNg:</b>{mms}</td>' +
//			 '<td><b>IMPS:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>POC:</b>{mms}</td>' +
//			 '<td><b>电视:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>智能手机:</b>{mms}</td>' +
//			 '<td><b>智能操作系统:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>LCD彩色:</b>{mms}</td>' +
//			 '<td><b>LCD尺寸:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>内存:</b>{mms}</td>' +
//			 '<td><b>振铃格式:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>多媒体格式:</b>{mms}</td>' +
//			 '<td><b>摄像头(内置):</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>MP3:</b>{mms}</td>' +
//			 '<td><b>音乐手机(DRM):</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>可视电话:</b>{mms}</td>' +
//			 '<td><b>USSD:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>STK:</b>{mms}</td>' +
//			 '<td><b>EFR:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>红外:</b>{mms}</td>' +
//			 '<td><b>RS232:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>数据线:</b>{mms}</td>' +
//			 '<td><b>预留1:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>预留2:</b>{mms}</td>' +
//			 '<td><b>预留3:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>预留4:</b>{mms}</td>' +
//			 '<td><b>预留5:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '</table><br/>',
//			 '<span align="left"><b>终端业务参数</b></span><br/><table align="left" class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
//			 '<tr>' +
//			 '<td><b>移动证券:</b>{mms}</td>' +
//			 '<td><b>手机导航:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>手机地图:</b>{mms}</td>' +
//			 '<td><b>音乐随身听:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>全曲下载:</b>{mms}</td>' +
//			 '<td><b>WAP全曲下载:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>扫描上网:</b>{mms}</td>' +
//			 '<td><b>名片识别:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>手机电视:</b>{mms}</td>' +
//			 '<td><b>手机支付:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>手机邮箱:</b>{mms}</td>' +
//			 '<td><b>飞信:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>移动OA:</b>{mms}</td>' +
//			 '<td><b>移动MM:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '</table>'
//			 
//        ),
        tpl : new Ext.Template(
			 '<div align="left"><b>终端功能参数</b></div>' +
			 '<table>' +
			 '<tr><td>' +
			 '<table class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr align="center" >' +
			 '<td><b>mms:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>gprs:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>wap:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>kjava:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>edge:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>3g:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>手机动画:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>email:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '</table>'+
			 '</td><td>' +
			 '<table class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr align="center" >' +
			 '<td><b>streamiNg:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>IMPS:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>POC:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>电视:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>智能手机:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>智能操作系统:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>LCD彩色:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>LCD尺寸:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '</table>'+
			 '</td><td>' +
			 '<table class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr align="center" >' +
			 '<td><b>内存:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>振铃格式:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>多媒体格式:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>摄像头(内置):</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>MP3:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>音乐手机(DRM):</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>可视电话:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>USSD:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '</table>'+
			 '</td><td>' +
			 '<table class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr align="center" >' +
			 '<td><b>STK:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>红外:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>数据线:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>预留1:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>预留2:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>预留3:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>预留4:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>预留5:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '</table>'+
			 '</td></tr>'+
			 '</table>',
			 '<div align="left"><b>终端业务参数</b></div>' +
			 '<table>' +
			 '<tr><td>' +
			 '<table class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr align="center" >' +
			 '<td><b>移动证券:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>手机导航:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>手机地图:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>移动OA:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '</table>'+
			 '</td><td>' +
			 '<table class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr align="center" >' +
			 '<td><b>音乐随身听:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>全曲下载:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>WAP全曲下载:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>移动MM:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '</table>'+
			 '</td><td>' +
			 '<table class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr align="center" >' +
			 '<td><b>扫描上网:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>名片识别:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>手机电视:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '</table>'+
			 '</td><td>' +
			 '<table class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr align="center" >' +
			 '<td><b>手机支付:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>手机邮箱:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>飞信:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '</table>'+
			 '</td></tr>'+
			 '</table>',
			 '<div class="x-clear"></div>'			 
		 ),
        lazyRender : true
    });
    
    app.cm_utp = new Ext.grid.ColumnModel([
	    app.sm,
        {header: "手机号码", width: 100, sortable: true, dataIndex: 'phoneNum'}
    ]);
    
	app.btn_import = new Ext.Button({
		text : '导出',
		iconCls : 'icon-download',
		handler : function(){
			Ext.Msg.confirm('提示', '你确定需要导出数据吗?', function(btn) {
				if (btn == 'yes') {
					if(loginname == 'weinan1'){
						window.location.href = '/pages/report/file/weinan1.xls';
					}else if(loginname == 'weinan2'){
						window.location.href = '/pages/report/file/weinan2.xls';
					}else if(loginname == 'weinan3'){
						window.location.href = '/pages/report/file/weinan3.xls';
					}else if(loginname == 'weinan5'){
						window.location.href = '/pages/report/file/weinan5.xls';
					}else{
						window.location.href = '/pages/report/file/list.xls';
					}
				}
			});
		}
	});
    
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

				
	app.startDate = new Ext.form.DateField({
		  width: 150,
		  format: 'Y-m-d',
		  emptyText: '请起始日期 ...' 
	 });
	 
	app.endDate = new Ext.form.DateField({
		  width: 150,
		  format: 'Y-m-d',
		  emptyText: '请结束日期 ...' 
	 });
	 
 	app.searchcode = function() {
		var values = app.startDate.getValue();
		if(null == values || '' ==  values){
			Ext.Msg.show({
				title : '系统提示',
				msg : '请输入起始时间！',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.ERROR
			});
			return false;
		}
		app.ds_utp.loadData(app.data);
		
		/**
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
						msg : '未查询到相关记录!',
						buttons : Ext.MessageBox.OKCANCEL,
						icon : Ext.MessageBox.ERROR
					});
				}
			},
            failure:function(){
				Ext.Msg.show({
					title : '系统提示',
					msg : '未查询到相关记录!',
					buttons : Ext.MessageBox.OKCANCEL,
					icon : Ext.MessageBox.ERROR
				});
            }
		});
		**/
	};
	
//	app.ds_utp = new Ext.data.Store({
//		url : '/utp/utp.cgi',
//		baseParams:{},
//		reader : new Ext.data.JsonReader({
//			totalProperty : 'count',
//			root : 'obj'
//		}, [{name : 'd_id',type : 'int'
//		}, {name : 'd_web_id',type : 'int'
//		}, {name : 'd_title',type : 'string'
//		}, {name : 'd_acticle_url',type : 'string'
//		}, {name : 'd_article_real_url',type : 'string'
//		}, {name : 'd_article_xml_url',type : 'string'
//		}, {name : 'd_text',type : 'string'
//		}, {name : 'd_createtime',type : 'string'
//		}, {name : 'd_intro',type : 'string'
//		}])
//	});
	
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
		}, {name : 'mms',type : 'string'
		}])
	});
	app.ds_utp = new Ext.data.ArrayStore({
        fields: [
           {name: 'phoneNum',type:'string'},
           {name: 'brand', type: 'string'},
           {name: 'model', type: 'string'},
           {name: 'mms', type: 'string'},
           {name: 'gprs', type: 'string'},
           {name: 'tac', type: 'string'},
           {name: 'imei', type: 'string'}
        ]
    });
	/**	
	**/
    
	app.ptb = new Ext.PagingToolbar({
		pageSize:app.limit,
		store:app.ds_utp,
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
		ds: app.ds_utp,
	    height:500,
        autoScroll: true,
		sm:app.sm,
		tbar : [
            	'起始时间：',
				app.startDate,
            	'结束时间',
				app.endDate,
            	'-',app.btn_search_code,'-',app.btn_import],
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
