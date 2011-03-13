	var app = {};
Ext.onReady(function(){
	Ext.QuickTips.init();
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
	app.limit = 10;
 	app.colName = colName;
	app.values = values;
    app.sm = new Ext.grid.CheckboxSelectionModel();
    
    function qtips(val){
//	style="display:table;width:100%;" 
		var imgUrl = '<span qtip=\'<img src="'+val+'"> \' >' + val + '</span>';
    	return imgUrl;
    }
    
    app.cm_image = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),app.sm, 
        {id:'ID',header: "ID", sortable: true, dataIndex: 'd_id'},
        {header: "标题", sortable: true, dataIndex: 'd_title'},
        {header: "图片URL", sortable: true,renderer:qtips, dataIndex: 'd_imgurl'},
//        {header: "状态", sortable: true, dataIndex: 'd_link'},
        {header: "创建时间", sortable: true,dataIndex: 'd_createtime'}
    ]);
    
    app.cm_image.defaultSortable = false;
    
	app.search_comb_queyrCol_code = new Ext.form.ComboBox({
				fieldLabel : '查询条件',
				id : 'column',
				hiddenName : 'colName',
				valueField : 'id',
				displayField : 'name',
				mode:'local',
				store : new Ext.data.SimpleStore({
					data : [
							['title', '文章标题'],
							['articleId','文章编号']
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
		handler : app.searchcode
	});
	
	app.btn_download_img = new Ext.Button({
		text:'下载图片',
		iconCls:'icon-save',
		handler:function(){
			if(app.grid.getSelectionModel().getSelected()){
				var record = app.grid.getSelectionModel().getSelected();
				alert('获取文章ID:'+record.get('d_id'));
			}else{
				Ext.MessageBox.alert("提示信息!!", "请选择要获取URL的数据!!");
			}
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
		app.values  = app.text_search_code.getValue();
		Ext.Ajax.request({
			url : project+'/image/image.cgi?id='+articleId,
			params : {
				colName : app.colName,
				value : app.values
			},
			success:function(response,option){
				var obj = Ext.util.JSON.decode(response.responseText);
				if(obj.success){
					app.ds_image.load({params : {start:0,limit:app.limit}}); //,colName:app.colName,value:app.values
				}else{
	             	Ext.MessageBox.alert('提示',obj.msg);
				}
			},
            failure:function(){
             	Ext.MessageBox.alert('提示','服务器内部错误');
            }
		})
	};
	
	app.ds_image = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : project+'/image/image.cgi?id='+articleId
		}),
		reader : new Ext.data.JsonReader({
			totalProperty : 'count',
			root : 'image'
		}, [{name : 'd_id',type : 'int'
		}, {name : 'd_article_id',type : 'int'
		}, {name : 'd_title',type : 'string'
		}, {name : 'd_imgurl',type : 'string'
		}, {name : 'd_httpurl',type : 'string'
		}, {name : 'd_commentsuburl',type : 'string'
		}, {name : 'd_name',type : 'string'
		}, {name : 'd_createtime',type : 'string'
		}, {name : 'd_imgurl',type : 'string'
		}, {name : 'd_commentshowurl',type : 'string'
		}, {name : 'd_link',type : 'string'
		}, {name : 'd_time',type : 'string'
		}, {name : 'd_orderid',type : 'int'
		}])
	});
	
	app.ds_image.load({
		params : {
			start : 0,
			limit : app.limit
		}
	});
	
	
	app.ds_image.on('beforeload',function(){
		Ext.apply(
			this.baseParams,{
				colName : app.colName,
				value : app.values
	        }   
	 	);   
	}); 
	
	app.ptb = new Ext.PagingToolbar({
		pageSize:app.limit,
		store:app.ds_image,
		displayInfo:true,
		displayMsg:'第 {0} - {1} 条  共 {2} 条',
		emptyMsg:'没有记录'
	});
		
	app.grid = new Ext.grid.GridPanel({
		title : '图片管理',
		iconCls : 'icon-plugin',
		region : 'center',
		loadMask : {
			msg : '数据加载中...'
		},
	    cm: app.cm_image,
	    ds: app.ds_image,
		autoHeight : true,
//		autoWidth : true,
//		width:800,
		tbar : [app.btn_download_img,'-',app.search_comb_queyrCol_code,'-',app.text_search_code,'-',app.btn_search_code], //'-',app.btn_search_code
		bbar : app.ptb
	});
	
	app.grid.addListener('rowdblclick',function(grid, rowIndex){
		if(grid.getSelectionModel().isSelected(rowIndex)){
			var record = app.grid.getSelectionModel().getSelected();
			Ext.get('op').dom.value += "ID:"+record.get('d_id') +　"\t" + record.get('d_title') + "\n" + record.get('d_name') 
					+ '\t\t' + record.get('d_imgurl')
					+ "\n---------------------------------------------------------------------------------------------------\n";
			var url = record.get('d_imgurl');
			window.location = url
		}
	});

    app.grid.render('div-image');
    
//    app.grid.getSelectionModel().selectFirstRow();
    
});