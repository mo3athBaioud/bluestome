var app = {};
Ext.onReady(function(){
  	Ext.BLANK_IMAGE_URL = project + '/resources/images/default/s.gif';
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    Ext.QuickTips.init();
    app.values = webId;
    app.aid = 0;
    app.qp = {};
	app.qp.limit = 25;
    app.sm = new Ext.grid.CheckboxSelectionModel();
    
    app.cm_utp = new Ext.grid.ColumnModel([
		app.sm,
        {header: "ID", width: 50, sortable: true, dataIndex: 'id'},
        {header: "站点", width: 50, sortable: true, dataIndex: 'webId'},
        {header: "标题", width: 250, sortable: true, dataIndex: 'title',renderer:function(v){
        	if(null == v || '' == v){
        		return '<font color=blue>暂无</font>';
        	}else{
        		return v;
        	}
        }},
        {header: "作者", width: 100, sortable: true, dataIndex: 'author',renderer:function(v){
        	if(null == v || '' == v){
        		return '<font color=blue>暂无</font>';
        	}else{
        		return v;
        	}
        }},
        {header: "状态", width: 70, sortable: true, dataIndex: 'status',renderer:function(v){
        	if(v == 3){
        		return '<font color=blue>正常</font>';
        	}else{
        		return v;
        	}
        }},
        {header: "发布时间", width: 130, sortable: true, dataIndex: 'publishTime'},
        {header: "修改时间", width: 130, sortable: true, dataIndex: 'createTime'},
        {header: "收录时间", width: 130, sortable: true, dataIndex: 'createTime'}
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
		iconCls:'icon-add',
		disabled:false
	});
	
	app.btn_dissolve = new Ext.Button({
		text : '解散',
		disabled:false
	});
	
	app.btn_bug = new Ext.Button({
		text : '页面测试',
		iconCls : 'icon-bug',
		disabled:false,
		handler:function(){
			if(app.grid.getSelectionModel().getSelected()){
				var record = app.grid.getSelectionModel().getSelected();
				//TODO 测试返回的内容 1.页面大小 2.响应时间
				Ext.Msg.confirm('提示', '你确定测试该页面吗?', function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
							url : project+'/admin/website!debug.action',
							params : {
								id : record.get('id'),
								turl: record.get('url')
							},
							success:function(response,option){
								var obj = Ext.util.JSON.decode(response.responseText);
								if(obj.success){
									createResultWin(record.get('url'),obj);
								}else{
									Ext.Msg.show({
										title : '系统提示',
										msg : obj.msg,
										buttons : Ext.Msg.OK,
										icon : Ext.MessageBox.ERROR
									});
								}
							},
			                failure:function(response,option){
								Ext.Msg.show({
									title : '系统提示',
									msg : '服务器内部错误',
									buttons : Ext.Msg.OK,
									icon : Ext.MessageBox.ERROR
								});
			                }
						})
					}
				});
			}else{
				Ext.Msg.show({
					title : '系统提示',
					msg : '请选择需要测试的站点!',
					buttons : Ext.Msg.OK,
					icon : Ext.MessageBox.ERROR
				});
			}
		}
	});
	
	var queryConditionPanel = new Ext.form.FormPanel({
		border: false,
		layout: 'form', 
		width: '100%', 
		height: 35,
		autoScroll: false,
		renderTo:'grid-query',
		bodyStyle: 'padding:10px',
		frame : false,
		items : [ 
			new Ext.Panel({
				border: false,  
		        layout: 'column',  
		        items: [  
		            new Ext.Panel({  
		                columnWidth: .2,  
		                layout: 'form',
		                border: false,  
		                style: 'text-align: left; ',
		      	        labelWidth: 80,  
		                labelAlign: 'right',  
		                items: [  
		                    {  
		                    	fieldLabel: '文章标题',
								name: 'entity.title',  
								xtype:'textfield',
								anchor: '80%',
								listeners : {
									'specialkey' : function(field, e) {
										if (e.getKey() == Ext.EventObject.ENTER) {
											if(queryConditionPanel.form.isValid()){
												app.qp = getComps2Object(queryConditionPanel);
												app.qp.start = 0;
												app.qp.limit = 25;
												Ext.apply(app.ds_data, {
													baseParams: app.qp
												});
												app.ds_data.removeAll();
												app.ds_data.load();
											}
										}
									}
								}
		                    }  
		                ]  
		            }),
		            new Ext.Panel({  
		                columnWidth: .2,  
		                layout: 'form',
		                border: false,  
		                style: 'text-align: left; ',
		      	        labelWidth: 80,  
		                labelAlign: 'right',  
		                items: [  
		                    {  
		                    	fieldLabel: '文章作者',
								name: 'entity.author',  
								xtype:'textfield',
								anchor: '80%',
								listeners : {
									'specialkey' : function(field, e) {
										if (e.getKey() == Ext.EventObject.ENTER) {
											if(queryConditionPanel.form.isValid()){
												app.qp = getComps2Object(queryConditionPanel);
												app.qp.start = 0;
												app.qp.limit = 25;
												Ext.apply(app.ds_data, {
													baseParams: app.qp
												});
												app.ds_data.removeAll();
												app.ds_data.load();
											}
										}
									}
								}
		                    }  
		                ]  
		            }),
		            new Ext.Panel({  
		                columnWidth: .2,  
		                layout: 'form',
		                border: false,  
		                style: 'text-align: left; ',
		      	        labelWidth: 80,  
		                labelAlign: 'right',  
		                items: [  
		                    {  
		                    	fieldLabel: '文章ID',
								name: 'entity.id',  
								xtype:'textfield',
								vtype:'integer',
								anchor: '80%',
								listeners : {
									'specialkey' : function(field, e) {
										if (e.getKey() == Ext.EventObject.ENTER) {
											if(queryConditionPanel.form.isValid()){
												app.qp = getComps2Object(queryConditionPanel);
												app.qp.start = 0;
												app.qp.limit = 25;
												Ext.apply(app.ds_data, {
													baseParams: app.qp
												});
												app.ds_data.removeAll();
												app.ds_data.load();
											}
										}
									}
								}
		                    }  
		                ]  
		            }),
		            new Ext.Panel({  
		                columnWidth: .2,  
		                layout: 'form',
		                border: false,  
		                style: 'text-align: left; ',
		      	        labelWidth: 80,  
		                labelAlign: 'right',  
		                items: [  
		                    {  
		                    	fieldLabel: '站点ID',
		                    	readOnly:true,
								name: 'entity.webId',  
								xtype:'textfield',
								vtype:'integer',
								anchor: '80%',
								listeners : {
									'specialkey' : function(field, e) {
										if (e.getKey() == Ext.EventObject.ENTER) {
											if(queryConditionPanel.form.isValid()){
												app.qp = getComps2Object(queryConditionPanel);
												app.qp.start = 0;
												app.qp.limit = 25;
												Ext.apply(app.ds_data, {
													baseParams: app.qp
												});
												app.ds_data.removeAll();
												app.ds_data.load();
											}
										}
									}
								}
		                    }  
		                ]  
		            }),
		            new Ext.Panel({  
		                columnWidth: .2,  
		                layout: 'form',
		                border: false,  
		                style: 'text-align: left; ',
		      	        labelWidth: 80,  
		                labelAlign: 'right',  
		                items: [  
		                    {  
		                    	fieldLabel: '状态',
								name: 'entity.status',  
								anchor: '80%',
								xtype:'combo',
						        valueField: 'id',
						        displayField: 'name',
						        triggerAction:'all',
						        mode: 'local',
						        store: new Ext.data.SimpleStore({
						            fields: ['id','name'],
						            data: [[null,'请选择'],[3,'可用'],[null,'不可用']]
						        }),
					            editable:false,
								listeners : {
									'select': function() {
										if(queryConditionPanel.form.isValid()){
											app.qp = getComps2Object(queryConditionPanel);
											app.qp.start = 0;
											app.qp.limit = 25;
											Ext.apply(app.ds_data, {
												baseParams: app.qp
											});
											app.ds_data.removeAll();
											app.ds_data.load();
										}
									}
								}
		                    }  
		                ]  
		            })
		            /**
		            ,
		            new Ext.Panel({  
		                columnWidth: .2,  
		                layout: 'form',
		                border: false,  
		                style: 'text-align: left; ',
		      	        labelWidth: 200,  
		                labelAlign: 'right',  
		                items: [
							new Ext.Button({
								text : '查询',
								iconCls : 'icon-search',
								handler : function() {
									if(queryConditionPanel.form.isValid()){
										app.qp = getComps2Object(queryConditionPanel);
										app.qp.start = 0;
										app.qp.limit = 20;
										Ext.apply(app.ds_data, {
											baseParams: app.qp
										});
										app.ds_data.removeAll();
										app.ds_data.load();
									}
								}
							})
		                ]  
				    })
				    **/
		        ]  
		    })
		]
	});
	
	app.btn_export_xls = new Ext.Button({
		text : '导出文件',
		disabled:false,
		iconCls:'icon-page_white_excel',
		handler:function(){
			app.ds_data.load({
				params:{
					start:0,
					limit:app.qp.limit
				}
			});
		}
	});
	
	app.searchcode = function() {
		app.values =app.text_phone_number.getValue();
		if(null == app.values || '' ==  app.values){
			Ext.Msg.show({
				title : '系统提示',
				msg : '请输入需要查询的标题！',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.ERROR
			});
			return false;
		}
		Ext.apply(app.ds_data, {
//			baseParams: {
//				'entity.webId':app.values
//			}
		});
		app.ds_data.removeAll();
		app.ds_data.load({
			params : {
				start:0,
				limit:app.qp.limit
			}
		});
	};
	
	app.text_phone_number = new Ext.form.TextField({
		name : 'webId',
		width : 150,
		allowBlank:false,
		listeners : {
			'specialkey' : function(field, e) {
				if (e.getKey() == Ext.EventObject.ENTER) {
					app.searchcode();
				}
			}
		}
	});
	
	app.ds_data = new Ext.data.Store({
		url : project + '/admin/articledoc!list.cgi',
//		baseParams:{
//			'entity.webId':app.values
//		},
		reader : new Ext.data.JsonReader({
			totalProperty : 'total',
			root : 'records'
		}, [{name : 'id',type : 'int'
		}, {name : 'webId',type : 'int'
		}, {name : 'status',type : 'int'
		}, {name : 'url',type : 'string'
		}, {name : 'title',type : 'string'
		}, {name : 'content',type : 'string'
		}, {name : 'author',type : 'string'
		}, {name : 'grade',type : 'int'
		}, {name : 'publishTime',type : 'string'
		}, {name : 'modifyTime',type : 'string'
		}, {name : 'createTime',type : 'string'
		}, {name : 'tag',type : 'string'
		}])
	});
	
	app.ds_data.load({
		params:{
			start:0,
			limit:app.qp.limit
		}
	});
	
	app.pageSizePlugin = new EasyExt.widgets.PageSizePlugin();
	
	app.ptb = new Ext.PagingToolbar({
		pageSize:app.qp.limit,
		store:app.ds_data,
		displayInfo:true,
		displayMsg:'第 {0} - {1} 条  共 {2} 条',
		emptyMsg:'没有记录'
	});
	
	app.toolbaritems = [
		app.btn_bug,'-',app.btn_create,'-',app.btn_export_xls
	]
		
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
		//'站点ID:','-',app.text_phone_number,app.btn_search_code,'-',
		tbar : [Common.pageingToolBar(app.pageSizePlugin, app.ds_data, app.toolbaritems)],
		bbar : app.ptb
	});
	
	app.grid.addListener('rowdblclick',function(grid, rowIndex){
		if(grid.getSelectionModel().isSelected(rowIndex)){
			var record = app.grid.getSelectionModel().getSelected();
			var url = project+'/admin/articledoc!doc.cgi?id='+record.get('id');
//			var url = record.get('url');
			//height=600,width=800,
			window.open(url,record.get('id'),'top=200,left=300,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no, status=no');
		}
	});

    app.grid.render('grid-div');
});

function createResultWin(url,obj){
	var win = new Ext.Window({
		id:'result_win',
		title:'测试结果',
		width:500,
		iconCls:'icon-bug_go',
		resizable : false,
		autoHeight : true,
		modal : true,
		closable:false,
		animCollapse : true,
		pageY : 20,
		pageX : document.body.clientWidth / 2 - 420 / 2,
		animateTarget : Ext.getBody(),
		constrain : true,
		items:[
			new Ext.FormPanel({
				id:'result_form',
				labelWidth : 110,
				labelAlign : 'right',
				border : false,
				baseCls : 'x-plain',
				bodyStyle : 'padding:5px 5px 0',
				anchor : '100%',
				defaults : {
					width : 300,
					msgTarget : 'side'
				},
				defaultType : 'textfield',
				items : [
					{
						fieldLabel : '网址',
						name : 'entity.Content-Type',
						value:url
					},{
						fieldLabel : '响应代码',
						name : 'entity.responseStatus',
						value:obj.object.responseStatus
					},{
						fieldLabel : '页面类型',
						name : 'entity.contentType',
						value:obj.object.contentType
					},{
						fieldLabel : '页面大小',
						name : 'entity.contentLength',
						value:obj.object.contentLength
					},{
						fieldLabel : '修改时间',
						name : 'entity.lastModified',
						value:obj.object.lastModified
					},{
						fieldLabel : '缓存时长',
						name : 'entity.cacheControl',
						value:obj.object.cacheControl
					},{
						fieldLabel : '响应时间',
						name : 'entity.endDate',
						value:'暂无'
					}		
				],
				buttonAlign : 'center',
				minButtonWidth : 60,
				buttons : [
					{
						iconCls:'icon-cancel',
						text : '关闭',
						handler : function() {
							Ext.getCmp('result_form').form.reset();
							var win = Ext.getCmp('result_win');
							win.close();
						}
					}
				]
			})
		]
	}).show();
}

/**
 * 图片载入失败时调用的方法
 * <img onError="imgErr(this)" />
 */
function imgErr(img)
{
	  img.src = project+'/images/image_waiting.png';
	  img.qtip = '载入图片失败!';
}
