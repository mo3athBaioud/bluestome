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
//        tpl : new Ext.Template(
//			 '<p>租机业务推荐</p>'+
//			 '<table align="left" class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
//			 '<tr><td>1.IPHONE4(TDS-CDMA)' +
//			 '<p>图片:<img src="http://images.sanhaostreet.com/product//9/373/132886/main201010211137093432.jpg" width="100" height="80" alt="huawei"/></p>' +
//			 '<p>资费:预存5000送5000</p>' +
//			 '<p>业务介绍：为了满足需求，增加字数，补充字数！</p>' +
//			 '</td></tr>' +
//			 '<tr><td>2.华为E100：' +
//			 '<p>图片:<img src="http://phone.10086.cn/UploadFile/smallpic/GO_hw_T2010_20101222.jpg" width="40" height="80" alt="huawei"/></p>' +
//			 '<p>资费:预存500送500</p>' +
//			 '<p>业务介绍：为了满足需求，增加字数，补充字数！</p>' +
//			 '</td></tr>' +
//			 '<tr><td>3.MOTO ME525' +
//			 '<p>图片:<img src="http://phone.10086.cn/UploadFile/smallpic/GO_hw_T2010_20101222.jpg" width="40" height="80" alt="huawei"/></p>' +
//			 '<p>资费:预存4000送4000</p>' +
//			 '<p>业务介绍：为了满足需求，增加字数，补充字数！</p>' +
//			 '</td></tr>' +
//			 '</table>'
//        )
        tpl : new Ext.Template(
			 '<tpl for=".">',
			 '<table>' +
			 '<tr><td>' +
			 '<table align="left" class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr><td><b>业务名称:</b>1.IPHONE4(TDS-CDMA)</td></tr>' +
			 '<tr><td><img width="100" height="80" src="http://images.sanhaostreet.com/product//9/373/132886/main201010211137093432.jpg"/></td></tr>' +
			 '</table> '+
			 '</td><td>' +
			 '<table align="left" class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr><td><b>资费:</b></td></tr>' +
			 '<tr align="left"><td width="100" height="80">预存5000送5000</td></tr>' +
			 '</table>' +
			 '</td><td>' +
			 '<table align="left" class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr><td><b>业务介绍:</b></td></tr>' +
			 '<tr align="left"><td width="100" height="80">买手机，送话费！</td></tr>' +
			 '</table>' +
			 '</td></tr>' +
			 '</table>',
			 '<table>' +
			 '<tr><td>' +
			 '<table align="left" class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr><td><b>业务名称:</b>2.华为U8500</td></tr>' +
			 '<tr><td><img width="100" height="80" src="http://img5.pcpop.com/ProductImages/Leader/3/3779/003779548.jpg"/></td></tr>' +
			 '</table> '+
			 '</td><td>' +
			 '<table align="left" class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr><td><b>资费:</b></td></tr>' +
			 '<tr align="left"><td width="100" height="80">预存4000送4000</td></tr>' +
			 '</table>' +
			 '</td><td>' +
			 '<table align="left" class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr><td><b>业务介绍:</b></td></tr>' +
			 '<tr align="left"><td width="100" height="80">买手机，送话费！</td></tr>' +
			 '</table>' +
			 '</td></tr>' +
			 '</table>',
			 '<table>' +
			 '<tr><td>' +
			 '<table align="left" class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr><td><b>业务名称:</b>3.MOTO ME525</td></tr>' +
			 '<tr><td><img width="100" height="80" src="http://img5.pcpop.com/ProductImages/Leader/4/4287/004287752.jpg"/></td></tr>' +
			 '</table> '+
			 '</td><td>' +
			 '<table align="left" class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr><td><b>资费:</b></td></tr>' +
			 '<tr align="left"><td width="100" height="80">预存3000送3000</td></tr>' +
			 '</table>' +
			 '</td><td>' +
			 '<table align="left" class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr><td><b>业务介绍:</b></td></tr>' +
			 '<tr align="left"><td width="100" height="80">买手机，送话费！</td></tr>' +
			 '</table>' +
			 '</td></tr>' +
			 '</table>',
			 '</tpl>',
			 '<div class="x-clear"></div>')
    });
    
    function doo(obj){
		if(confirm( "Statement here. ")){ 
		   	alert('show obj:'+obj);
		}else{ 
			return; 
		}
    }
    
    app.cm_utp = new Ext.grid.ColumnModel([
	    expander,
//        {header: "手机号码", width: 100, sortable: true, dataIndex: 'sn'},
//        {header: "品牌[在用]", width: 100, sortable: true, dataIndex: 'brand'},
//        {header: "型号[在用]", width: 100, sortable: true, dataIndex: 'model'},
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
	
	app.hs_bsi = new Ext.form.ComboBox({
				id : 'hs_bsi',
				hiddenName : 'colName',
				valueField : 'id',
				displayField : 'name',
				mode:'local',
				store : new Ext.data.SimpleStore({
					data : [
							['1', '乐PHONE'],
							['2','MOTO ME525']
				    ],
					fields : ['id', 'name']
				}),	
				selectOnFocus : true,
				editable : false,
				allowBlank : true,
				triggerAction : 'all',
				emptyText : '租机业务列表'
	});
	
	app.rent_bussiness_list = new Ext.form.ComboBox({
				id : 'hs_wap',
				hiddenName : 'colName',
				valueField : 'id',
				displayField : 'name',
				mode:'local',
				store : new Ext.data.SimpleStore({
					data : [
							['1', 'IPHONE租机'],
							['2','I9000租机'],
							['3','ME525机']
				    ],
					fields : ['id', 'name']
				}),	
				selectOnFocus : true,
				editable : false,
				allowBlank : true,
				triggerAction : 'all',
				emptyText : '租机业务列表'
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

	app.text_phone_number = new Ext.form.TextField({
		name : 'phone_number',
		width : 150,
		vtype:'mobile'
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
	
	app.btn_terminal_query_components = new Ext.Button({
		text:'终端查询组件',
		iconCls:'icon-search',
		handler:function(){
			showTerminalWindow();
		}
	});
	
	//定义一个终端查询组件
	app.terminalQueryWin = new TerminalQuery({
		ds:'ds-123',
		width:650,
		height:500,
		///servlet/FormServlet http://180.168.68.82:6012/nutzd/website/root.cgi
		url:'/servlet/FormServlet',
		parentId:'terminal1_grid',
		other:null
	});
		
	var showTerminalWindow = function(){
		app.terminalQueryWin.show();
	}
	
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
		title : '租机业务推荐查询',
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
		//'<b>其他终端参数暂时省略</b>'
		//'品牌:',app.hs_brand_combo,'-',app.hs_model_combo,'-',app.hs_bsi,'-',
		tbar : [app.btn_terminal_query_components,'-','手机号码：',app.text_phone_number,'-','租机业务列表：',app.rent_bussiness_list,'-',app.btn_search_code]
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

function vmobile(v){
     var r = /^((\(\d{2,3}\))|(\d{3}\-))?1?[3,5,8]\d{9}$/;
     return r.test(v);
}