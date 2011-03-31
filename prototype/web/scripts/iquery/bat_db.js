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
//			 '<p>数据业务推荐</p>',
//			 '优先等级1',
//			 '优先等级2',
//			 '优先等级3'
//        )
        tpl : new Ext.Template(
			 '<tpl for=".">',
			 '<div><span align="left">优先等级1</span>' +
			 '<table border="0"><tr><td>1.飞信[<font color="red">未使用</font>]</td></tr><tr><td>2.移动MM[<font color="red">未使用</font>]</td></tr><tr><td>3.手机阅读[<font color="red">未使用</font>]</td></tr></table></div>',
			 '<div><span align="left">优先等级2</span>' +
			 '<table border="0"><tr><td>1.淘乐汇[<font color="red">未使用</font>]</td></tr><tr><td>2.手机报[<font color="green">使用</font>]</td></tr><tr><td>3.号谱管家[<font color="red">未使用</font>]</td></tr></table></div>',
			 '</td></tr></table></div>',
			 '<div><span align="left">优先等级3</span>' +
			 '<table border="0"><tr><td>1.可视电话[<font color="red">未使用</font>]</td></tr><tr><td>2.彩信相册[<font color="red">未使用</font>]</td></tr><tr><td>3.移动秘书[<font color="red">未使用</font>]</td></tr></table></div>',
			 '</td></tr></table></div>',
			 '</tpl>',
			 '<div class="x-clear"></div>')
    });
    
    app.cm_utp = new Ext.grid.ColumnModel([
	    expander,
        {header: "手机号码", width: 100, sortable: true, dataIndex: 'sn'},
        {header: "品牌", width: 100, sortable: true, dataIndex: 'brand'},
        {header: "型号", width: 100, sortable: true, dataIndex: 'model'}
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
	
	app.data_business = new Ext.form.ComboBox({
				id : 'data_business',
				hiddenName : 'colName',
				valueField : 'id',
				displayField : 'name',
				mode:'local',
				store : new Ext.data.SimpleStore({
					data : [
							['1', '飞信'],
							['2', '手机阅读'],
							['3', '淘乐汇'],
							['4', '手机报'],
							['5', '号谱管家'],
							['6', '可视电话'],
							['7','移动MM']
				    ],
					fields : ['id', 'name']
				}),	
				selectOnFocus : true,
				editable : false,
				allowBlank : true,
				triggerAction : 'all',
				emptyText : '数据业务列表'
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
		tbar : ['品牌:',app.hs_brand_combo,'-',app.hs_model_combo,'<b>其他终端参数暂时省略</b>',app.data_business,'-',app.btn_search_code]
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