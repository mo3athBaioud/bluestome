/**
 * IMEI查询
 */
var app = {};
Ext.onReady(function(){
	
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    Ext.QuickTips.init();
	app.limit = 15;
	app.colName = "";
	app.values = "";
    app.sm = new Ext.grid.CheckboxSelectionModel();
	
	//统计日期,用户标识,手机号码,imei号码,业务区编码,通话次数,基本计费跳,通话时长,tac码    
    app.data = [
        ['2010/6/27','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700'],
        ['2010/6/27','1610041222370609','18729637525','812347982824500','FA49','4','14','735','81234798'],
        ['2010/6/27','1610041222370609','18729637526','863417452824500','F0E9','4','14','735','86341745'],
        ['2010/6/27','1610041222370609','18729637527','883210322824500','FC79','4','14','735','88321032'],
        ['2010/6/27','1610041222370609','18729637528','873319122824500','FD69','4','14','735','87331912'],
        ['2010/6/27','1610041222370609','18729637529','813247092824500','F0F9','4','14','735','81324709'],
        ['2010/6/27','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700'],
        ['2010/6/27','1610041222370609','18729637525','812347982824500','FA49','4','14','735','81234798'],
        ['2010/6/27','1610041222370609','18729637526','863417452824500','F0E9','4','14','735','86341745'],
        ['2010/6/27','1610041222370609','18729637527','883210322824500','FC79','4','14','735','88321032'],
        ['2010/6/27','1610041222370609','18729637528','873319122824500','FD69','4','14','735','87331912'],
        ['2010/6/27','1610041222370609','18729637529','813247092824500','F0F9','4','14','735','81324709'],
        ['2010/6/27','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700'],
        ['2010/6/27','1610041222370609','18729637525','812347982824500','FA49','4','14','735','81234798'],
        ['2010/6/27','1610041222370609','18729637526','863417452824500','F0E9','4','14','735','86341745'],
        ['2010/6/27','1610041222370609','18729637527','883210322824500','FC79','4','14','735','88321032'],
        ['2010/6/27','1610041222370609','18729637528','873319122824500','FD69','4','14','735','87331912'],
        ['2010/6/27','1610041222370609','18729637529','813247092824500','F0F9','4','14','735','81324709'],
        ['2010/6/27','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700'],
        ['2010/6/27','1610041222370609','18729637525','812347982824500','FA49','4','14','735','81234798'],
        ['2010/6/27','1610041222370609','18729637526','863417452824500','F0E9','4','14','735','86341745'],
        ['2010/6/27','1610041222370609','18729637527','883210322824500','FC79','4','14','735','88321032'],
        ['2010/6/27','1610041222370609','18729637528','873319122824500','FD69','4','14','735','87331912'],
        ['2010/6/27','1610041222370609','18729637529','813247092824500','F0F9','4','14','735','81324709'],
        ['2010/6/27','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700'],
        ['2010/6/27','1610041222370609','18729637525','812347982824500','FA49','4','14','735','81234798'],
        ['2010/6/27','1610041222370609','18729637526','863417452824500','F0E9','4','14','735','86341745'],
        ['2010/6/27','1610041222370609','18729637527','883210322824500','FC79','4','14','735','88321032'],
        ['2010/6/27','1610041222370609','18729637528','873319122824500','FD69','4','14','735','87331912'],
        ['2010/6/27','1610041222370609','18729637529','813247092824500','F0F9','4','14','735','81324709'],
        ['2010/6/27','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700'],
        ['2010/6/27','1610041222370609','18729637525','812347982824500','FA49','4','14','735','81234798'],
        ['2010/6/27','1610041222370609','18729637526','863417452824500','F0E9','4','14','735','86341745'],
        ['2010/6/27','1610041222370609','18729637527','883210322824500','FC79','4','14','735','88321032'],
        ['2010/6/27','1610041222370609','18729637528','873319122824500','FD69','4','14','735','87331912'],
        ['2010/6/27','1610041222370609','18729637529','813247092824500','F0F9','4','14','735','81324709'],
        ['2010/6/27','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700'],
        ['2010/6/27','1610041222370609','18729637525','812347982824500','FA49','4','14','735','81234798'],
        ['2010/6/27','1610041222370609','18729637526','863417452824500','F0E9','4','14','735','86341745'],
        ['2010/6/27','1610041222370609','18729637527','883210322824500','FC79','4','14','735','88321032'],
        ['2010/6/27','1610041222370609','18729637528','873319122824500','FD69','4','14','735','87331912'],
        ['2010/6/27','1610041222370609','18729637529','813247092824500','F0F9','4','14','735','81324709'],
        ['2010/6/27','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700'],
        ['2010/6/27','1610041222370609','18729637525','812347982824500','FA49','4','14','735','81234798'],
        ['2010/6/27','1610041222370609','18729637526','863417452824500','F0E9','4','14','735','86341745'],
        ['2010/6/27','1610041222370609','18729637527','883210322824500','FC79','4','14','735','88321032'],
        ['2010/6/27','1610041222370609','18729637528','873319122824500','FD69','4','14','735','87331912'],
        ['2010/6/27','1610041222370609','18729637529','813247092824500','F0F9','4','14','735','81324709']
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
    
    app.cm_utp = new Ext.grid.ColumnModel([
	    new Ext.grid.RowNumberer(),app.sm,
        {header: "统计日期", width: 100, sortable: true, dataIndex: 'time'},
        {header: "用户标识", width: 150, sortable: true, dataIndex: 'uid'},
        {header: "手机号码", width: 100, sortable: true, dataIndex: 'sn'},
        {header: "imei号码", width: 150, sortable: true, dataIndex: 'imei'},
        {header: "业务区编码", width: 100, sortable: true, dataIndex: 'ywqbm'},
        {header: "通话次数", width: 80, sortable: true, dataIndex: 'thcs'},
        {header: "基本计费跳", width: 100, sortable: true, dataIndex: 'jbjft'},
        {header: "通话时长", width: 80, sortable: true, dataIndex: 'thsc'},
        {header: "tac码", width: 80, sortable: true, dataIndex: 'tac'}
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
           {name: 'time',type:'string'},
           {name: 'uid',type:'string'},
           {name: 'sn',type:'string'},
           {name: 'imei',type:'string'},
           {name: 'ywqbm',type:'string'},
           {name: 'thcs', type: 'string'},
           {name: 'jbjft', type: 'string'},
           {name: 'thsc', type: 'string'},
           {name: 'tac', type: 'string'}
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
		title : '通话IMEI查询',
		iconCls : 'icon-plugin',
		region : 'center',
		loadMask : {
			msg : '数据加载中...'
		},
	    cm: app.cm_utp,
	    ds: app.ds_utp,
	    width:1000,
	    height:550,
        autoScroll: true,
		sm:app.sm,
		tbar : ['请输入手机号码:',app.text_search_code,'-','请输入IMEI:',app.text_imei_code,'请输入TAC码:',app.text_tac_code,app.btn_search_code]
//		bbar : app.ptb
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

    app.grid.render('imei_query');
}); 