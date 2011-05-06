Ext.BLANK_IMAGE_URL = '../resources/images/default/s.gif';
var app = {};
Ext.onReady(function(){
	
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    Ext.QuickTips.init();
	app.limit = 15;
	app.colName = colName;
	app.values = values;
    app.sm = new Ext.grid.CheckboxSelectionModel();
    
	app.article = Ext.data.Record.create([{
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
	}, {
		name : 'imgCount',
		mapping : 'd_img_count',
		type : 'int'
	}]);
    
    //d_img_count
    var expander = new Ext.grid.RowExpander({
        tpl : new Ext.Template(
        	'<p><b>图片数:</b><br/>{d_img_count}</p>',
            '<p><b>备注:</b><br/>{d_intro}</p>',
            '<p><b>网址:</b><br/>{d_acticle_url}<a href="{d_acticle_url}" target="_blank"><img src="'+project+'/images/world_go.png" alt="{d_web_name}"/></a></p>'
        )
    });
    
    //语言图标
    function qtips(val){
    	if(null == val || '' ==  val){
	        return '<span style="display:table;width:100%;" qtip=\'<img src="'+project+'/images/nopic.jpg">\'>无</span>'
    	}else{
	        return '<span style="display:table;width:100%;" qtip=\'<img src="' + val + '">\'>有</span>'
    	}
    }
    
    app.cm_article = new Ext.grid.ColumnModel([
	    expander,
        {header: "类型", width: 80,
        	renderer : function(value) {
				return '<img src="'+project+'/images/picture.png"/>';
			}
        },
        {header: "ID", width: 80, sortable: true, dataIndex: 'd_id'}, //width: 50, 
        {header: "标题", width: 150, sortable: true, dataIndex: 'd_title'},
        {header: "地址", width: 200, sortable: true, dataIndex: 'd_acticle_url'},
        {header: "图数", width: 80, sortable: true, dataIndex: 'd_img_count'},
        {header: "预览", width: 70, sortable: true, dataIndex: 'd_article_xml_url',renderer: qtips},
        {header: "状态", width: 70, sortable: true, dataIndex: 'd_text'},
        {header: "创建时间", width: 150, sortable: true,dataIndex: 'd_createtime'}
    ]);
    
	app.search_comb_queyrCol_code = new Ext.form.ComboBox({
				fieldLabel : '查询条件',
				id : 'column',
				hiddenName : 'colName',
				valueField : 'id',
				displayField : 'name',
				mode:'local',
				store : new Ext.data.SimpleStore({
					data : [
							['id', '文章ID'],
							['title', '文章标题'],
							['intro','文章介绍'],
							['text','文章状态']
				    ],
					fields : ['id', 'name']
				}),	
				selectOnFocus : true,
				editable : false,
				allowBlank : true,
				triggerAction : 'all',
				emptyText : '查询的字段名'
	});
	
	app.btn_search_code = new Ext.Button({
		text : '查询',
		iconCls : 'icon-search',
		handler : function(){
			alert('search');
			app.searchcode();
		}
	});
	
	
	app.text_search_code = new Ext.form.TextField({
		name : 'textSearchcode',
		width : 200,
		emptyText : '多条件可用逗号或者空格隔开!',
		listeners : {
			'specialkey' : function(field, e) {
				if (e.getKey() == Ext.EventObject.ENTER) {
					app.searchcode();
				}
			}
		}
	});

	app.searchcode = function() {
		app.colName = app.search_comb_queyrCol_code.getValue();
		app.values =app.text_search_code.getValue();
		Ext.Ajax.request({
			url : project+'/article/list.cgi',
			params : {
				colName : app.colName,
				value : app.values
			},
			success:function(response,option){
				var obj = Ext.util.JSON.decode(response.responseText);
				if(obj.success){
					app.ds_article.load({params : {start:0,limit:app.limit}}); //,colName:app.colName,value:app.values
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
		})
	};
		
	app.ds_article = new Ext.data.Store({
		url : project+'/article/list.cgi',
		baseParams:{},
		reader : new Ext.data.JsonReader({
			totalProperty : 'count',
			root : 'article'
		}, [{name : 'd_id',type : 'int'
		}, {name : 'd_web_id',type : 'int'
		}, {name : 'd_title',type : 'string'
		}, {name : 'd_acticle_url',type : 'string'
		}, {name : 'd_article_real_url',type : 'string'
		}, {name : 'd_article_xml_url',type : 'string'
		}, {name : 'd_text',type : 'string'
		}, {name : 'd_createtime',type : 'string'
		}, {name : 'd_intro',type : 'string'
		}, {name : 'd_img_count',type : 'int'
		}])
	});
	
	app.btn_add_code = new Ext.Button({
		text : '添加',
		iconCls : 'icon-add',
		handler : function() {
			app.window_add.show();
		}
	});
	
	app.ds_article.load({
		params : {
			start : 0,
			limit : app.limit
		}
	});
	
	app.ds_article.on('beforeload',function(){
		Ext.apply(
			this.baseParams,{
				colName : app.colName,
				value : app.values
	        }   
	 	);   
	});  
	
	app.pagesize_combo = new Ext.form.ComboBox({
				name : 'pagesize',
				hiddenName : 'pagesize',
				typeAhead : true,
				triggerAction : 'all',
				lazyRender : true,
				mode : 'local',
				store : new Ext.data.SimpleStore({
							fields : ['value', 'text'],
							data : [[10, '10条/页'],[15, '15条/页'],[20, '20条/页']]
						}),
				valueField : 'value',
				displayField : 'text',
				value : '20',
				editable : false,
				width : 85
	});
	
	var number = parseInt(app.pagesize_combo.getValue());
	app.pagesize_combo.on("select", function(comboBox){
				app.ptb.pageSize = parseInt(comboBox.getValue());
				number = parseInt(comboBox.getValue());
				app.ds_article.reload({
					params : {
						start : 0,
						limit : app.ptb.pageSize
					}
				});
	});
	app.ptb = new Ext.PagingToolbar({
		pageSize:app.limit,
		store:app.ds_article,
		displayInfo:true,
		displayMsg:'第 {0} - {1} 条  共 {2} 条',
		emptyMsg:'没有记录',
		items : ['-', '&nbsp;&nbsp;', app.pagesize_combo]
	});
		
	app.grid = new Ext.grid.GridPanel({
		title : '文章管理',
		iconCls : 'icon-plugin',
		region : 'center',
		loadMask : {
			msg : '数据加载中...'
		},
	    cm: app.cm_article,
	    ds: app.ds_article,
//	    width:850,
	    height:600,
        autoScroll: true,
        viewConfig: {
            forceFit:true
        },
 		plugins: expander,
		sm:app.sm,
		tbar : [app.search_comb_queyrCol_code,'-', app.text_search_code,'-', app.btn_search_code],
		bbar : app.ptb
	});
	
	app.grid.addListener('rowdblclick',function(grid, rowIndex){
				if(grid.getSelectionModel().isSelected(rowIndex)){
					var record = app.grid.getSelectionModel().getSelected();
					var url = String.format(project+"/pages/images/image.jsp?id={0}&webid={1}",record.get('d_id'),record.get('d_web_id'));
					window.location = url;
				}
	});

    app.grid.render('div-article');
    
});