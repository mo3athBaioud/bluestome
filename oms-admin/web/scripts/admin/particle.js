var app = {};
Ext.onReady(function(){
  	Ext.BLANK_IMAGE_URL = project + '/resources/images/default/s.gif';
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    Ext.QuickTips.init();
    app.values = 0;
    app.aid = 0;
    app.qp = {};
	app.qp.limit = 30;
    app.sm = new Ext.grid.CheckboxSelectionModel();
    
	app.tpl = new Ext.XTemplate(
		'<tpl for=".">',
	        '<div class="thumb-wrap" id="{id}">',
		    '<div class="thumb"><img src="{imgUrl2}" title="{title}" onError="imgErr(this);" ></div>',
		    '<span>{id}</span></div>',
	    '</tpl>',
	    '<div class="x-clear"></div>'
	);
	
	app.ds_data = new Ext.data.Store({
		url : project + '/admin/particle!list.cgi',
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
	
	app.dataview = new Ext.DataView({
	    store: app.ds_data,
	    tpl: app.tpl,
	    autoHeight:true,
	    multiSelect: true,
	    overClass:'x-view-over',
	    itemSelector:'div.thumb-wrap',
	    plugins: [
	        new Ext.DataView.DragSelector(),
	        new Ext.DataView.LabelEditor({dataIndex: 'title'})
	    ],
	
	    prepareData: function(data){
	    	data.imgUrl2 =  project + '/admin/images!icon2.cgi?iconUrl='+data.acticleXmlUrl+'&referUrl='+data.articleUrl;
        	data.shortName = Ext.util.Format.ellipsis(data.title, 10);
	        return data;
	    },
	    
	    listeners: {
	    	dblclick:function(dv, index, node, e){
				 var record = app.ds_data.getAt(index);
				 app.aid = record.data.id;
				 createImageListsWin(app.aid,record.data.title);
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
												app.ds_data.removeAll();
												app.qp = getComps2Object(queryConditionPanel);
												app.qp.start = 0;
												app.qp.limit = 30;
												Ext.apply(app.ds_data, {
													baseParams: app.qp
												});
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
												app.ds_data.removeAll();
												app.qp = getComps2Object(queryConditionPanel);
												app.qp.start = 0;
												app.qp.limit = 30;
												Ext.apply(app.ds_data, {
													baseParams: app.qp
												});
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
								name: 'entity.webId',  
								xtype:'textfield',
								vtype:'integer',
								anchor: '80%',
								listeners : {
									'specialkey' : function(field, e) {
										if (e.getKey() == Ext.EventObject.ENTER) {
											if(queryConditionPanel.form.isValid()){
												app.ds_data.removeAll();
												app.qp = getComps2Object(queryConditionPanel);
												app.qp.start = 0;
												app.qp.limit = 30;
												Ext.apply(app.ds_data, {
													baseParams: app.qp
												});
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
											app.ds_data.removeAll();
											app.qp = getComps2Object(queryConditionPanel);
											app.qp.start = 0;
											app.qp.limit = 30;
											Ext.apply(app.ds_data, {
												baseParams: app.qp
											});
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
										app.ds_data.removeAll();
										app.qp = getComps2Object(queryConditionPanel);
										app.qp.start = 0;
										app.qp.limit = 30;
										Ext.apply(app.ds_data, {
											baseParams: app.qp
										});
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
		
	app.grid = new Ext.Panel({
		id:'images-view',
        style: 'padding:0px 0px 5px 0px',
        region: 'center',
		loadMask : {
			msg : '数据加载中...'
		},
	    height:500,
	    autoScroll: true,
	    viewConfig: {
	        forceFit:true
	    },
       items: app.dataview,
        bbar:app.ptb
	});
	
	app.grid.addListener('rowdblclick',function(grid, rowIndex){
		if(grid.getSelectionModel().isSelected(rowIndex)){
			var record = grid.getSelectionModel().getSelected();
			app.aid = record.get('id');
			Ext.Msg.show({
				title : '系统提示',
				msg : '由于显示界面问题，暂时屏蔽该功能!',
				buttons : Ext.Msg.OK,
				icon : Ext.Msg.ERROR
			});
		}
	});

	  app.grid.render('grid-div');
});

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
	    handler: function(){
            var obj = dataview;
            if (obj.isSelected) {
            	var datas = obj.getSelectedRecords();
				window.open(datas[0].data.imgUrl,datas[0].data.id,'height=400,width=300,top=200,left=300,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no, status=no');
            }
	    }
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
            var obj = dataview;
            if (obj.isSelected) {
            	var datas = obj.getSelectedRecords();
				window.open(datas[0].data.httpUrl,datas[0].data.id,'height=400,width=300,top=200,left=300,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no, status=no');
            }
	    }
	}),
	new Ext.Action({
	    id: 'autoPlay',
	    text: '自动播放',
	    iconCls : 'icon-picture',
	    handler: function(){
	    	//TODO 自动播放
	    	//思路 在新页面中讲图片URL展示出来。
           var obj = dataview;
            if (obj.isSelected) {
            	var datas = obj.getSelectedRecords();
				var turl = project + '/admin/images!autoplay.cgi?entity.articleId='+datas[0].data.articleId;
//				window.open(turl,datas[0].data.id,'height=300,width=400,top=200,left=300,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no, status=no');
				window.open(turl);
            }
	    }
	})
];

var viewpanel = new Ext.Panel({
    id:'images-view2',
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

var imageWin = new Ext.Window({
	id:'result_image_win',
	title:'图片窗口',
	width:600,
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
	    imageWin.setTitle(aid+'|'+title);
	    imageWin.show();
	}
}

/**
 * 图片载入失败时调用的方法
 * <img onError="imgErr(this)" />
 */
function imgErr(img)
{
  img.src = project+'/images/image_missing.png';
//  img.qtip = '载入图片失败!';
}

/**
 * 获取路径后的文件扩展名
 */
function endsWith(str)
{
	var result = 'no';
	var end  = str.toLowerCase().lastIndexOf(".");
	if(end != -1){
		result = str.substring(end);
	}
	return result;
}
