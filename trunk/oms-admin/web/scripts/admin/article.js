var app = {};
Ext.onReady(function(){
  	Ext.BLANK_IMAGE_URL = project + '/resources/images/default/s.gif';
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    Ext.QuickTips.init();
    app.values = webId;
    app.aid = 0;
    app.qp = {};
	app.qp.limit = 20;
    app.sm = new Ext.grid.CheckboxSelectionModel();
    
     var expander = new Ext.grid.RowExpander({
        tpl : new Ext.Template(
        	'<div class="thumb-wrap" id="{id}" style="pading:10px 100px 10px 100px;">',
            '<div><img src="{acticleXmlUrl}" title={title} height="90" width="120" onError="imgErr(this);"/></div>',
            '</div>'
        )
    });
    
    app.cm_utp = new Ext.grid.ColumnModel({
	    columns:[
				app.sm,
		        {header: "ID", width: 50, sortable: true, dataIndex: 'id'},
		        //webId
		        {header: "站点", width: 50, sortable: true, dataIndex: 'webId'},
		        {header: "标题", width: 250, sortable: true, dataIndex: 'title'},
		        {header: "缩略图", width: 70, sortable: true, dataIndex: 'acticleXmlUrl',renderer:function(v, cellMeta, record, rowIndex, columnIndex, store){
		        	var index = v.lastIndexOf(".");
		        	var b = false;
		        	if(index != -1){
		        		var ext = v.substring(index);
		        		if(ext != '.xml' && ext != '.html' && ext != '.htm'){
		        			b = true;
		        		}
		        	}
		        	if(b){
		        		return '<img src='+v+' title="'+record.get('title')+'" height="20" width="20" />';
		        	}else{
		        		return '<a href='+v+'><img src='+project+'/images/image_missing.png title="找不到图片" alt="找不到图片" height="20" width="20" onError="imgErr(this);"/></a>';
		        	}
		        }},
		        {header: "状态", width: 70, sortable: true, dataIndex: 'text',renderer:function(v){
		        	if(v == 'FD'){
		        		return '<font color=blue>'+v+'</font>';
		        	}else{
		        		return '<font color=red>'+v+'</font>';
		        	}
		        }},
		        {header: "收录时间", width: 130, sortable: true, dataIndex: 'createTime'}
	    ]
//	    ,
//	    listeners:{
//	    	'dbrclick':function(cm, colIndex, hidden){
//	    		//TODO 鼠标移到到此
//	    		alert('双击事件');
//	    	},
//	    	dbrclick:function(){
//	    		alert('单击事件');
//	    	}
//	    }
    });
    
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
								turl: record.get('articleUrl')
							},
							success:function(response,option){
								var obj = Ext.util.JSON.decode(response.responseText);
								if(obj.success){
									createResultWin(record.get('articleUrl'),obj);
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
		height: 40,
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
												app.qp.limit = 20;
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
												app.qp.limit = 20;
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
								value:webId,
								listeners : {
									'specialkey' : function(field, e) {
										if (e.getKey() == Ext.EventObject.ENTER) {
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
								name: 'entity.text',  
								anchor: '80%',
								xtype:'combo',
						        valueField: 'id',
						        displayField: 'name',
						        triggerAction:'all',
						        mode: 'local',
						        store: new Ext.data.SimpleStore({
						            fields: ['id','name'],
						            data: [[null,'请选择'],['0','不可用'],['FD','完成'],['NED','需要下载'],['ENED','ENED'],['NND','NND'],['NNN','NNN'],['FNFE','FNFE'],['WFD','WFD']]
						        }),
					            editable:false,
								listeners : {
									'select': function() {
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
			baseParams: {
				'entity.webId':app.values
			}
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
		url : project + '/admin/article!list.cgi',
		baseParams:{
			'entity.webId':app.values
		},
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
			limit:app.qp.limit
		}
	});
	
	app.ptb = new Ext.PagingToolbar({
		pageSize:app.qp.limit,
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
        plugins: expander,
        viewConfig: {
            forceFit:true
        },
		sm:app.sm,
		//'标题：','-',app.text_phone_number,app.btn_search_code,'-',
		tbar : ['站点ID:','-',app.text_phone_number,app.btn_search_code,'-',app.btn_bug,'-',app.btn_create,'-',app.btn_export_xls],
		bbar : app.ptb
	});
	
	app.grid.addListener('rowdblclick',function(grid, rowIndex){
		if(grid.getSelectionModel().isSelected(rowIndex)){
			var record = grid.getSelectionModel().getSelected();
			if(record.get('text') == 'FD' ){
				app.aid = record.get('id');
				createImageListsWin(app.aid,record.get('title'));
				/**
					Ext.Msg.show({
						title : '系统提示',
						msg : '由于显示界面问题，暂时屏蔽该功能!',
						buttons : Ext.Msg.OK,
						icon : Ext.Msg.ERROR
					});
				**/ 
			}
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

var store = new Ext.data.JsonStore({
	url:project+'/admin/images!list.cgi',
	root: 'records',
    fields: [
    	{name:'id',type:'int'},
    	{name:'articleId',type:'int'},
    	'name', 
    	'title', 
    	'httpUrl',
    	'imgUrl', 
    	{name:'size', type: 'float'}, 
    	{name:'createtime', type:'date', dateFormat:'timestamp'},
    	{name : 'status',type : 'int'}
	]
});
/**	
**/ 

/**
var store = new Ext.data.JsonStore({
	url : project + '/admin/images!list.cgi',
	reader : new Ext.data.JsonReader({
		totalProperty : 'total',
		root : 'records'
	}, [{name : 'id',type : 'int'
	}, {name : 'articleId',type : 'int'
	}, {name : 'name',type : 'string'
	}, {name : 'httpUrl',type : 'string'
	}, {name : 'imgUrl',type : 'string'
	}, {name : 'commentshowurl',type : 'string'
	}, {name : 'commentsuburl',type : 'string'
	}, {name : 'title',type : 'string'
	}, {name : 'link',type : 'string'
	}, {name : 'intro',type : 'string'
	}, {name : 'createtime',type:'date', dateFormat:'timestamp'
	}, {name : 'size',type : 'float'
	}, {name : 'status',type : 'int'
	}])
});
**/

var tpl = new Ext.XTemplate(
	'<tpl for=".">',
        '<div class="thumb-wrap" id="{id}">',
	    '<div class="thumb"><img src="{imgUrl2}" title="{title}" onError="imgErr(this);" ></div>',
	    '<span>{sizeString}</span></div>',
    '</tpl>',
    '<div class="x-clear"></div>'
);

var ptb = new Ext.PagingToolbar({
	pageSize:10,
	store:store,
	displayInfo:true,
	displayMsg:'第 {0} - {1} 条  共 {2} 条',
	emptyMsg:'没有记录'
});

var dataview = new Ext.DataView({
    store: store,
    tpl: tpl,
    autoHeight:true,
    multiSelect: true,
    overClass:'x-view-over',
    itemSelector:'div.thumb-wrap',
    plugins: [
        new Ext.DataView.DragSelector(),
        new Ext.DataView.LabelEditor({dataIndex: 'title'})
    ],

    prepareData: function(data){
    	data.imgUrl2 =  project + '/admin/images!icon.cgi?entity.id='+data.id+'&entity.articleId='+data.articleId;
        data.shortName = Ext.util.Format.ellipsis(data.title, 15);
        data.sizeString = Ext.util.Format.fileSize(data.size);
        data.dateString = data.createtime.format("m/d/Y g:i a");
        return data;
    },
    
    listeners: {
    	selectionchange:function(dv, selections){
    		/**
    		fn: function(dv,nodes){
    			var l = nodes.length;
    			var s = l != 1 ? 's' : '';
    			panel.setTitle('Simple DataView ('+l+' item'+s+' selected)');
    		}
    		**/ 
            if (selections.length > 0) {
                dataActions[0].enable();
                dataActions[1].enable();
                dataActions[2].enable();
                dataActions[3].enable();
            }
            else {
                dataActions[0].disable();
                dataActions[1].disable();
                dataActions[2].disable();
                dataActions[3].disable();
            }
    	},
    	dblclick:function(dv, index, node, e){
			 var data = store.getAt(index);
			 //TODO 提示正在处理请求，显示请求进度
			 //请求获取图片的宽高
			 Ext.Ajax.request({
				waitTitle : '提示',
				waitMsg : '正在请求服务器,请稍候...',
				url : project+'/admin/images!wh.cgi',
				params : {
					'entity.articleId' : data.data.articleId,
					'entity.httpUrl' : data.data.httpUrl
				},
				success:function(response,option){
					var obj = Ext.util.JSON.decode(response.responseText);
					if(obj.success){
						var turl = project + '/admin/images!image.cgi?entity.id='+data.data.id+'&entity.articleId='+data.data.articleId;
						window.open(turl,data.data.id,'height='+obj.height+',width='+obj.width+',top=200,left=300,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no, status=no');
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
			 });
    	}
    }
});

var dataActions = [
	new Ext.Action({
	    id: 'setIcon',
	    text: '设置为文章缩略图',
	    disabled: true,
		iconCls : 'icon-anchor',
	    handler: function(){
            var obj = dataview;
            if (obj.isSelected) {
            	var datas = obj.getSelectedRecords();
            	/**
				Ext.Msg.show({
					title : '系统提示',
					msg : datas[0].data.imgUrl,
					buttons : Ext.Msg.OK,
					icon : Ext.MessageBox.INFO
				});
				**/
				Ext.Ajax.request({
					url : project+'/admin/article!previewicon.cgi',
					params : {
						'entity.id' : datas[0].data.articleId,
						'entity.acticleXmlUrl' : datas[0].data.imgUrl
					},
					success:function(response,option){
						var obj = Ext.util.JSON.decode(response.responseText);
						if(obj.success){
							Ext.Msg.show({
								title : '系统提示',
								msg : obj.msg,
								buttons : Ext.Msg.OK,
								fn:function(){
									store.load();
								},
								icon : Ext.MessageBox.INFO
							});
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
			}else{
				Ext.MessageBox.alert("提示信息!!", "请选择要获取URL的数据!!");
			}
	    }
	}), 
	new Ext.Action({
	    id: 'viewSrcSmallImage',
	    text: '查看原始缩略图',
	    disabled: true,
		iconCls : 'icon-search',
	    handler: function(){}
	}), 
	new Ext.Action({
	    id: 'viewbigimage',
	    text: '查看大图',
	    disabled: true,
		iconCls : 'icon-picture_link',
	    handler: function(){
            var obj = dataview;
            if (obj.isSelected) {
            	var datas = obj.getSelectedRecords();
	 			 Ext.Ajax.request({
					waitTitle : '提示',
					waitMsg : '正在请求服务器,请稍候...',
					url : project+'/admin/images!wh.cgi',
					params : {
						'entity.articleId' : datas[0].data.articleId,
						'entity.httpUrl' : datas[0].data.httpUrl
					},
					success:function(response,option){
						var obj = Ext.util.JSON.decode(response.responseText);
						if(obj.success){
							var turl = project + '/admin/images!image.cgi?entity.id='+datas[0].data.id+'&entity.articleId='+datas[0].data.articleId;
							window.open(turl,datas[0].data.id,'height='+obj.height+',width='+obj.width+',top=200,left=300,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no, status=no');
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
				 });
            }
	    }
	}),
	new Ext.Action({
	    id: 'viewimage',
	    text: '查看',
	    iconCls : 'icon-picture',
	    disabled: true,
	    handler: function(){
	    	
	    }
	})
];

var viewpanel = new Ext.Panel({
    id:'images-view',
	loadMask : {
		msg : '数据加载中...'
	},
    height:400,
    autoScroll: true,
    viewConfig: {
        forceFit:true
    },
    items:[dataview],
    tbar:dataActions,
    bbar:ptb
});

var tplLarge = new Ext.XTemplate(
        '<tpl for=".">',
            '<div class="large-box" id="{id}">',
                '<img src="{httpUrl}" title="{title}">',
            '</div>',
        '</tpl>'
);

var dvLarge = new Ext.DataView({
    autoScroll: true, store: store, tpl: tplLarge,
    autoHeight: false, height: 410, multiSelect: true,
    overClass: 'x-view-over', itemSelector: 'div.thumb-wrap',
    emptyText: 'No images to display',
    border: false
});

// Panel for large images
var panelLarge = new Ext.Panel({
    region: 'center',
    margins:'5 0 5 0',
    items:[dvLarge]
});
    
var imageWin = new Ext.Window({
	id:'result_image_win',
	title:'图片窗口',
	width:600,
//	height: 450,
    autoScroll: true,
	iconCls:'icon-images',
	resizable : false,
	modal : true,
	closable:false,
	animCollapse : true,
	animateTarget : Ext.getBody(),
	constrain : true,
	items:[viewpanel],
	buttonAlign : 'center',
	minButtonWidth : 60,
	buttons : [
		{
			iconCls:'icon-cancel',
			text : '关闭',
			handler : function() {
				var win = Ext.getCmp('result_image_win');
				win.hide();
			}
		}
	]
});

/**
 * aid 文章id
 */
function createImageListsWin(aid,title){
	store.removeAll();
	//为store更新查询参数，以便在分页时能够使用最新的查询参数获取数据。
	Ext.apply(store, {
		baseParams: {
			'entity.articleId':aid,
		}
	});
    store.load({
		params:{
			'entity.articleId':aid,
			'entity.start':0,
			'entity.limit':10
		}
    });
	if(imageWin){
		//添加数据视图
	    imageWin.setTitle(title);
	    imageWin.show();
	}
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
