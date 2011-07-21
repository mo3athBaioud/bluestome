var app = {};
var mapwin;
Ext.onReady(function(){
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    Ext.QuickTips.init();
	app.limit = 20;
	app.colName = '';
	app.values = '';
    app.sm = new Ext.grid.CheckboxSelectionModel();
    
	app.usgs = Ext.data.Record.create([{
		name : 'id',
		mapping : 'd_id',
		type : 'int'
	}, {
		name : 'date',
		mapping : 'd_date',
		type : 'string'
//		type : 'date',
//		dateFormat:'Y-m-d H:i:s' 
	}, {
		name : 'latitude',
		mapping : 'd_latitude',
		type : 'string'
	}, {
		name : 'longitude',
		mapping : 'd_longitude',
		type : 'string'
	}, {
		name : 'depth',
		mapping : 'd_depth',
		type : 'float'
	}, {
		name : 'magnitude',
		mapping : 'd_magnitude',
		type : 'float'
	}, {
		name : 'comments',
		mapping : 'd_comments',
		type : 'string'
	}, {
		name : 'url',
		mapping : 'd_url',
		type : 'string'
	}, {
		name : 'eventid',
		mapping : 'd_eventid',
		type : 'string'
	}, {
		name : 'distinces',
		mapping : 'd_distinces',
		type : 'string'
	}, {
		name : 'location',
		mapping : 'd_location',
		type : 'string'
	}, {
		name : 'remarks',
		mapping : 'd_remarks',
		type : 'string'
	}, {
		name : 'source',
		mapping : 'd_source',
		type : 'string'
	}, {
		name : 'lu',
		mapping : 'd_lu',
		type : 'string'
	}, {
		name : 'parameters',
		mapping : 'd_parameters',
		type : 'string'
	}, {
		name : 'createtime',
		mapping : 'd_createtime',
		type : 'string'
	}, {
		name : 'modifytime',
		mapping : 'd_modifytime',
		type : 'string'
	}]);
    
    var expander = new Ext.grid.RowExpander({
        tpl : new Ext.Template(
            '<p><b>事件ID:</b><br/>{d_eventid}</p>',
            '<p><b>区域:</b><br/>{d_comments}</p>',
            '<p><b>距离:</b><br/>{d_distinces}</p>',
            '<p><b>来源:</b><br/>{d_source}</p>',
            '<p><b>网址:</b><br/>{d_url}<a href={d_url} target="_blank"><img src="'+project+'/images/world_go.png"/></a></p>',
            '<p><b>备注:</b><br/>{d_remarks}</p><br>',
            '<p><b>参数:</b><br/>{d_parameters}</p><br>'
        )
    });
    
    //new Ext.grid.RowNumberer(),app.sm,
    app.cm_usgs = new Ext.grid.ColumnModel([
	    expander,
        {header: "ID",width: 50,sortable: true, dataIndex: 'd_id'}, //width: 50, 
        {header: "事件编号",width: 100,dataIndex: 'd_eventid'}, //width: 50, 
        {header: "发生时间", width: 150,dataIndex: 'd_date'},
        {header: "地震级别", width: 100, sortable: true, dataIndex: 'd_magnitude',
        	renderer:function(value){ 
        		if(value > 6){
        			return "<font color=red><b>"+value+"</b></font>";
        		}else{
        			return value;
        		}
			}
		},
		{header:"深度",width:100,dataIndex:'d_depth',
		   renderer:function(value){ 
        		if(value < 30){
        			return "<font color=red><b>"+value+"</b></font>km";
        		}else{
        			return value+"km";
        		}
			}
		},
        {header: "纬度", width: 100, sortable: true, dataIndex: 'd_latitude'},
        {header: "经度", width: 100, sortable: true, dataIndex: 'd_longitude'},
        {header: "记录时间", width: 100, sortable: true, dataIndex: 'd_createtime'}
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
							['date', '发生时间'],
							['magnitude','地震级别'],
							['depth','震源深度'],
							['longitude','经度'],
							['latitude','纬度']
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
	
	app.btn_get_img_url = new Ext.Button({ 
		text:'获取文章图片',
		iconCls:'icon-save',
		handler:function(){
			if(app.grid.getSelectionModel().getSelected()){
				var record = app.grid.getSelectionModel().getSelected();
			}else{
				Ext.Msg.show({
					title : '系统提示',
					msg : '请选择要获取URL的数据!',
					buttons : Ext.Msg.OK,
					fn : function() {
						dnfield.focus(true);
						btn.enable();
					},
					icon : Ext.MessageBox.ERROR
				});
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
//					Ext.Msg.show({
//						title : '系统提示',
//						msg : '此功能正在开发，请等待发布!',
//						buttons : Ext.Msg.OK,
//						fn : function() {
//							dnfield.focus(true);
//							btn.enable();
//						},
//						icon : Ext.MessageBox.INFO
//					});
				}
			}
		}
	});

	app.searchcode = function() {
		app.colName = app.search_comb_queyrCol_code.getValue();
		app.values =app.text_search_code.getValue();
		Ext.Ajax.request({
			url : project+'/usgs/list.cgi',
			params : {
				colName : app.colName,
				value : app.values
			},
			success:function(response,option){
				var obj = Ext.util.JSON.decode(response.responseText);
				if(obj.success){
					app.ds_usgs.load({params : {start:0,limit:app.limit}}); //,colName:app.colName,value:app.values
				}else{
					Ext.Msg.show({
						title : '系统提示',
						msg : obj.msg,
						buttons : Ext.Msg.OK,
//						fn : function() {
//							dnfield.focus(true);
//							btn.enable();
//						},
						icon : Ext.MessageBox.ERROR
					});
				}
			},
            failure:function(){
				Ext.Msg.show({
					title : '系统提示',
					msg : '服务器内部错误',
					buttons : Ext.Msg.OK,
//					fn : function() {
//						dnfield.focus(true);
//						btn.enable();
//					},
					icon : Ext.MessageBox.ERROR
				});
            }
		})
	};
		
	app.ds_usgs = new Ext.data.Store({
		url : project+'/usgs/list.cgi',
		baseParams:{},
		reader : new Ext.data.JsonReader({
			totalProperty : 'count',
			root : 'usgs'
		}, [{name : 'd_id',type : 'int'
		}, {name : 'd_date',type : 'string'
		}, {name : 'd_latitude',type : 'string'
		}, {name : 'd_longitude',type : 'string'
		}, {name : 'd_depth',type : 'string'
		}, {name : 'd_magnitude',type : 'string'
		}, {name : 'd_comments',type : 'string'
		}, {name : 'd_url',type : 'string'
		}, {name : 'd_createtime',type : 'string'
		}, {name : 'd_modifytime',type : 'string'
		}, {name : 'd_eventid',type : 'string'
		}, {name : 'd_distinces',type : 'string'
		}, {name : 'd_remarks',type : 'string'
		}, {name : 'd_source',type : 'string'
		}, {name : 'd_lu',type : 'string'
		}, {name : 'd_parameters',type : 'string'
		}])
	});
	
	app.btn_add_code = new Ext.Button({
		text : '添加',
		iconCls : 'icon-add',
		handler : function() {
			app.window_add.show();
		}
	});
	
//	app.update_code_btn = new Ext.Button({ 
//		text : '编辑',
//		iconCls : 'icon-edit',
//		handler : function() {
//			Ext.Msg.show({
//				title : '系统提示',
//				msg : '此功能正在开发，请等待发布!',
//				buttons : Ext.Msg.OK,
//				icon : Ext.MessageBox.INFO
//			});
//		}
//	});
	
	app.update_usgs_btn = new Ext.Button({ 
		text : '编辑',
		iconCls : 'icon-edit',
		handler : function() {
			if(app.grid.getSelectionModel().getSelected()){
			var record = app.grid.getSelectionModel().getSelected();
				var updateWin = new Ext.Window({
					title : '编辑地震信息',
					iconCls:'icon-edit',
					width : 500,
					resizable : false,
					autoHeight : true,
//					height:600,
					modal : true,
					closeAction : 'close',
					items : [new Ext.FormPanel({
						labelWidth : 80,
						labelAlign : 'right',
						url : project+'/usgs/update.cgi',
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
							fieldLabel : '类型ID',
							id : 'quakeInfo.id',
							name : 'quakeInfo.id',
							readOnly:true,
							value : record.get('d_id')
						},{
							fieldLabel : '发生时间',
							name : 'quakeInfo.date',
							value : record.get('d_date'),
							readOnly:true
						},{
							fieldLabel : '经度',
							name : 'quakeInfo.latitude',
							value : record.get('d_latitude')
						},{
							fieldLabel : '纬度',
							name : 'quakeInfo.longitude',
							value : record.get('d_longitude')
						},{
							fieldLabel : '深度',
							name : 'quakeInfo.depth',
							value : record.get('d_depth')
						},{
							fieldLabel : '震级',
							xtype:'hidden',
							name : 'quakeInfo.magnitude',
							value:record.get('d_magnitude')
						},{
							fieldLabel : '备注',
							name : 'quakeInfo.comments',
							value:record.get('d_comments')
						},{
							fieldLabel : '原址',
							name : 'quakeInfo.url',
							value : record.get('d_url')
						}],
						buttonAlign : 'right',
						minButtonWidth : 60,
						buttons : [{
							text : '更新',
							handler : function(btn) {
								var frm = this.ownerCt.form;
								if (frm.isValid()) {
//									btn.disable();
									var dnfield = frm.findField('quakeInfo.date');
									var magnitude = frm.findField('quakeInfo.magnitude');
									frm.submit({
										waitTitle : '请稍候',
										waitMsg : '正在提交表单数据,请稍候...',
										success : function(form, action) {
											Ext.Msg.show({
												title : '系统提示',
												msg : '修改地震信息"' + dnfield.getValue() + '|'+magnitude.getValue()+ '"成功!',
												buttons : Ext.Msg.OK,
												icon : Ext.MessageBox.INFO
											});
											app.ds_usgs.load({params : {start : 0,limit : app.limit}});
										},
										failure : function() {
											Ext.Msg.show({
												title : '错误提示',
												msg : '"' + dnfield.getValue() + '|'+magnitude.getValue()+ '" ' + '名称可能已经存在或者您没有更新数据的权限!',
												buttons : Ext.Msg.OK,
												fn : function() {
													dnfield.focus(true);
													btn.enable();
												},
												icon : Ext.Msg.ERROR
											});
										}
									})
								}
							}
						}, {
							text : '重置',
							handler : function() {
								this.ownerCt.form.reset();
							}
						}, {
							text : '取消',
							handler : function() {
								this.ownerCt.ownerCt.close();
								this.ownerCt.form.reset();
							}
						}]
					})]
			}).show();
		}else{
			Ext.Msg.show({
				title : '系统提示',
				msg : '请选择要修改的记录!',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.ERROR
			});
		}
		}
	});
	
	app.ds_usgs.load({
		params : {
			start : 0,
			limit : app.limit
		}
	});
	
	app.ds_usgs.on('beforeload',function(){
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
		app.ds_usgs.reload({
			params : {
				start : 0,
				limit : app.ptb.pageSize
			}
		});
	});
	app.ptb = new Ext.PagingToolbar({
		pageSize:app.limit,
		store:app.ds_usgs,
		displayInfo:true,
		displayMsg:'第 {0} - {1} 条  共 {2} 条',
		emptyMsg:'没有记录',
		items : ['-', '&nbsp;&nbsp;', app.pagesize_combo]
	});
		
	app.grid = new Ext.grid.GridPanel({
		title : 'USGS-地震数据管理',
		iconCls : 'icon-plugin',
		region : 'center',
		loadMask : {
			msg : '数据加载中...'
		},
	    cm: app.cm_usgs,
	    ds: app.ds_usgs,
	    width:document.body.clientWidth-50,
	    height:document.body.clientHeight-50,
        autoScroll: true,
        viewConfig: {
            forceFit:true
        },
 		plugins: expander,
		sm:app.sm,
		tbar : ['-',app.update_usgs_btn,'-',app.search_comb_queyrCol_code,'-', app.text_search_code], //'-',app.btn_search_code
		bbar : app.ptb
	});
	
	app.grid.addListener('rowdblclick',function(grid, rowIndex){
		if(grid.getSelectionModel().isSelected(rowIndex)){
			var record = app.grid.getSelectionModel().getSelected();
			/**
			*/
			Ext.Msg.show({
				title : '系统提示',
				msg : '弹出窗口显示GMAP内容!',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.ERROR
			});
			/**
			var record = app.grid.getSelectionModel().getSelected();
			var url = record.get('d_url');
			window.location = url;
			*/
		}
	});

    app.grid.render('div-usgs');
});