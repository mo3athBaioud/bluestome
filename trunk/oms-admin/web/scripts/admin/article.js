var app = {};
Ext.onReady(function(){
  	Ext.BLANK_IMAGE_URL = project + '/resources/images/default/s.gif';
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    Ext.QuickTips.init();
	app.limit = 15;
    app.sm = new Ext.grid.CheckboxSelectionModel();
    
    app.cm_utp = new Ext.grid.ColumnModel([
		app.sm,
        {header: "ID", width: 100, sortable: true, dataIndex: 'id'},
        {header: "标题", width: 100, sortable: true, dataIndex: 'title'},
        {header: "缩略图", width: 100, sortable: true, dataIndex: 'acticleXmlUrl',renderer:function(v){
        	var index = v.lastIndexOf(".");
        	var b = false;
        	if(index != -1){
        		var ext = v.substring(index);
        		if(ext != '.xml' && ext != '.html' && ext != '.htm'){
        			b = true;
        		}
        	}
        	if(b){
        		return '<img src='+v+' alt="img" height="50" width="50" />';
        	}else{
        		return '<a href='+v+'>访问</a>';
        	}
        }},
        {header: "收录时间", width: 100, sortable: true, dataIndex: 'createTime'},
        {header: "操作", width: 100, sortable: true, dataIndex: 'acticleXmlUrl',renderer:function(v){
        	return '<a href="#">删除</a>|<a href="#">编辑</a>|<a href="#">重载</a>';
        }}
    ]);
    
	app.btn_search_code = new Ext.Button({
		text : '查询',
		disabled:false,
		iconCls : 'icon-search',
		handler : function(){
			app.searchcode();
		}
	});
	
	app.btn_create = new Ext.Button({
		text : '创建',
		disabled:false
	});
	
	app.btn_dissolve = new Ext.Button({
		text : '解散',
		disabled:false
	});
	
	
	app.btn_invate = new Ext.Button({
		text : '邀请好友',
		disabled:false
	});
	
	app.btn_export_xls = new Ext.Button({
		text : '导出文件',
		disabled:false,
		handler:function(){
				app.ds_data.load({
					params:{
						start:0,
						limit:app.limit
					}
				});
		}
	});
	
    app.tbar_pal = new Ext.Panel({
        title: 'My Panel',
        collapsible:true,
        width:400,
        items:[
        	app.btn_invate,app.btn_export_xls,
        ]
    });
	
	app.btn_save_code = new Ext.Button({
		text : '保存',
		disabled:false,
		iconCls : 'icon-accept',
		handler : function(){
			var values =app.text_phone_number.getValue();
			var records = app.grid.getSelectionModel().getSelections();
			if(records.length == 0 ){
				Ext.Msg.show({
					title : '提示',
					msg:'请选择需要修改的记录',
					buttons : Ext.Msg.OK,
					icon : Ext.Msg.ERROR
				});
			}else{
				var isMarket = app.isprompt.getValue();
				var mSuccess = app.promptss.getValue();
				var platsell = app.platsell.getValue();
				if('' == isMarket || null == isMarket){
					Ext.MessageBox.alert('提示','营销状态必须选择！');
					app.isprompt.focus();
					return;
				}
				
				if('' == mSuccess || null == mSuccess){
					Ext.MessageBox.alert('提示','营销结果必须选择！');
					app.promptss.focus();
					return;
				}
				
				if('' == platsell || null == platsell){
					Ext.MessageBox.alert('提示','是否本平台营销必须选择！');
					app.promptss.focus();
					return;
				}
				
				if(null != values && '' !=  values){
					if(records[0].get('isMarket') == 1 && records[0].get('mSuccess') == 1){
						Ext.Msg.show({
							title : '系统提示',
							msg : '当前记录已经为营销且成功状态，不能修改',
							buttons : Ext.Msg.OK,
							icon : Ext.MessageBox.ERROR
						});
						return;
					}else{
						//执行提交所选号码，并修改该号码的营销状态
						Ext.Msg.confirm('提示', '你确定修改用所选记录的营销状态和结果?', function(btn) {
							if (btn == 'yes') {
								Ext.Ajax.request({
									url : project+'/bussinesssimplify/umarket.cgi',
									params : {
										id : records[0].get('id'),
										phonenum:records[0].get('phonenum'),
										isMarket:isMarket,
										mSuccess:mSuccess,
										platsell:platsell
									},
									success:function(response,option){
										var obj = Ext.util.JSON.decode(response.responseText);
										if(obj.success){
											Ext.Msg.show({
												title : '提示',
												msg:obj.msg,
												buttons : Ext.Msg.OK,
//												fn:function(){
//													app.ds_data.load({
//														params:{
//															start:0,
//															limit:app.limit
//														}
//													});
//												},
												icon : Ext.Msg.INFO
											});
										}else{
											Ext.Msg.show({
												title : '提示',
												msg : obj.msg,
												buttons : Ext.Msg.OK,
												icon : Ext.Msg.ERROR
											});
										}
									},
					                failure:function(response,option){
										Ext.Msg.show({
											title : '提示',
											msg : '系统发生错误!',
											buttons : Ext.Msg.OK,
											icon : Ext.Msg.ERROR
										});
					                }
								});
							}
						});
					}
				}
			}
			/**
			**/
		}
	});

	app.searchcode = function() {
		var values =app.text_phone_number.getValue();
		if(null == values || '' ==  values){
			Ext.Msg.show({
				title : '系统提示',
				msg : '请输入需要查询的标题！',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.ERROR
			});
			return false;
		}
//		var b = vmobile(values);
//		if(!b){
//			Ext.Msg.show({
//				title : '系统提示',
//				msg : '输入的房间号码格式不对，请重新输入!',
//				buttons : Ext.Msg.OK,
//				fn:function(){
//					app.ds_data.removeAll();
//				},
//				icon : Ext.MessageBox.ERROR
//			});
//			return false;
//		}
		app.ds_data.load({
			params : {
				'entity.title':values,
				start:0,
				limit:app.limit
			}
		});
	};
	
	app.text_phone_number = new Ext.form.TextField({
		name : 'phone_number',
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
	
	app.ds_data = new Ext.data.Store({
		url : project + '/admin/article!list.cgi',
		baseParams:{},
		reader : new Ext.data.JsonReader({
			totalProperty : 'total',
			root : 'records'
		}, [{name : 'id',type : 'int'
		}, {name : 'webId',type : 'int'
		}, {name : 'articleUrl',type : 'string'
		}, {name : 'acticleRealUrl',type : 'string'
		}, {name : 'acticleXmlUrl',type : 'string'
		}, {name : 'title',type : 'string'
		}, {name : 'text',type : 'string'
		}, {name : 'intro',type : 'string'
		}, {name : 'createTime',type : 'string'
		}])
	});
	
	app.ds_data.load({
		params:{
			start:0,
			limit:app.limit
		}
	});
	
	app.ptb = new Ext.PagingToolbar({
		pageSize:app.limit,
		store:app.ds_data,
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
		ds: app.ds_data,
	    height:500,
        autoScroll: true,
        viewConfig: {
            forceFit:true
        },
		sm:app.sm,
		//,'-','创建时间','-',app.text_phone_number,'-','房间人数','-',app.text_phone_number,'-','房间内成员ID','-',app.text_phone_number,'-',
		tbar : ['标题：','-',app.text_phone_number,app.btn_search_code,'-',app.btn_create,'-',app.btn_dissolve,'-',app.btn_invate,'-',app.btn_export_xls],
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

    app.grid.render('grid-div');
});

function vmobile(v){
     var r = /^((\(\d{2,3}\))|(\d{3}\-))?1?[3,5,8]\d{9}$/;
     return r.test(v);
}
