var app = {};
Ext.onReady(function(){
	
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
//	app.limit = 15;
//    app.sm = new Ext.grid.CheckboxSelectionModel();

 /**
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
	
	app.ptb = new Ext.PagingToolbar({
		pageSize:app.limit,
		store:app.ds_article,
		displayInfo:true,
		displayMsg:'第 {0} - {1} 条  共 {2} 条',
		emptyMsg:'没有记录'
	});
**/
		
	app.grid = new Ext.grid.GridPanel({
		title : '文章管理',
		iconCls : 'icon-plugin',
		region : 'center',
		loadMask : {
			msg : '数据加载中...'
		},
		autoHeight:true,
		width:1200
	});
	
    app.grid.render('div-explorer');
});