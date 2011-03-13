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
	}]);
    
    app.cm_article = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),app.sm,
//        {header: "ID", sortable: true, dataIndex: 'd_id'}, //width: 50, 
        {header: "类型", width: 50,
        	renderer : function(value) {
				return '<img src="'+project+'/images/picture.png"/>';
			}
        },
        {header: "标题", width: 200, sortable: true, dataIndex: 'd_title'},
        {header: "地址", width: 300, sortable: true, dataIndex: 'd_acticle_url'},
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
		handler : app.searchcode
	});
	
	app.btn_get_img_url = new Ext.Button({ 
		text:'获取文章图片',
		iconCls:'icon-save',
		handler:function(){
			if(app.grid.getSelectionModel().getSelected()){
				var record = app.grid.getSelectionModel().getSelected();
				alert('获取文章ID:'+record.get('d_id'));
				alert('获取文章所属网站ID:'+webId);
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
				}
			}
		}
	});

	app.searchcode = function() {
		app.colName = app.search_comb_queyrCol_code.getValue();
		app.values =app.text_search_code.getValue();
		Ext.Ajax.request({
			url : project+'/article/article.cgi?id='+webId,
			params : {
//				start:0,
//				limit:app.limit,
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
						fn : function() {
							dnfield.focus(true);
							btn.enable();
						},
						icon : Ext.MessageBox.ERROR
					});
				}
			},
            failure:function(){
				Ext.Msg.show({
					title : '系统提示',
					msg : '服务器内部错误',
					buttons : Ext.Msg.OK,
					fn : function() {
						dnfield.focus(true);
						btn.enable();
					},
					icon : Ext.MessageBox.ERROR
				});
            }
		})
	};
		
	app.ds_article = new Ext.data.Store({
//		proxy : new Ext.data.HttpProxy({
//			url : project+'/article/article.cgi?id='+webId
//		}),
		url : project+'/article/article.cgi?id='+webId,
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
		}])
	});
	
	app.btn_add_code = new Ext.Button({
		text : '添加',
		iconCls : 'icon-add',
		handler : function() {
			app.window_add.show();
		}
	});
	
	app.showUploadDialog =  function(){
		var dialog = new UploadDialog({
			uploadUrl : 'uploadFiles.action',
			filePostName : 'myUpload', //这里很重要，默认值为'fileData',这里匹配action中的setMyUpload 属性
			flashUrl : 'js/swfupload.swf',
			fileSize : '500 MB', 
			fileTypes : '*.*',
			fileTypesDescription : '所有文件',
			scope : this
		})
		dialog.show();
	};
	
	app.btn_upload = new Ext.Button({
		text:'上传',
		iconCls:'icon-add',
		handler:function(){
			app.showUploadDialog();
		},
		scope:this
	});
	
	app.window_add = new Ext.Window({
		title : '添加',
		iconCls : 'icon-add',
		width : 400,
		resizable : false,
		autoHeight : true,
		modal : true,
		closeAction : 'hide',
		listeners : {
			'hide' : function() {
				this.setTitle('添加');
			}
		},
		items : [new Ext.FormPanel({
			labelWidth : 80,
			labelAlign : 'right',
			url : 'addcode.action',
			border : false,
			baseCls : 'x-plain',
			bodyStyle : 'padding:5px 5px 0',
			anchor : '100%',
			defaults : {
				width : 233,
				msgTarget : 'side'
			},
			defaultType : 'textfield',
			items : [{
				fieldLabel : '标题',
				id : 'article.title',
				name : 'article.title',
				maxLength : 50
			}, {
				fieldLabel : '文章地址',		
				name : 'article.articleUrl',
				maxLength : 150
			}, {
				fieldLabel : '实际地址',		
				name : 'article.articleUrl',
				maxLength : 150
			},{
				fieldLabel : '内容地址',		
				name : 'article.articleUrl',
				maxLength : 150
			},{
				fieldLabel : '所属网站ID',
				name : 'article.websiteBean.id',
				readOnly:true,
				value:webId
			},{
				fieldLabel : '介绍',
				xtype:'textarea',
				name : 'article.text',
//				allowBlank : false,
				maxLength : 20
			},{
				fieldLabel : '状态',
				name : 'article.text',
//				allowBlank : false,
				maxLength : 20
			}],
			buttonAlign : 'right',
			minButtonWidth : 60,
			buttons : [{
				text : '添加',
				handler : function(btn) {
//					var frm = this.ownerCt.form;
//					if (frm.isValid()) {
//						btn.disable();
//						var dnfield = frm.findField('code.codeName');
//						frm.submit({
//							waitTitle : '请稍候',
//							waitMsg : '正在提交表单数据,请稍候...',
//							success : function(form, action) {
//								var store = grid_code.getStore();
//									Ext.MessageBox.alert("成功！", "添加成功!");
//									app.ds_article.load({params : {start : 0,limit : limit}});
//								if (store.data.length <= 20) {
//									var code = new code({
//										codeName: dnfield.getValue(),
//										createDt : frm.findField('code.createDt').getValue(),
//										codeTp : frm.findField('code.codeTp').getValue(),
//										status : frm.findField('code.status').getValue(),
//										desc : frm.findField('code.desc').getValue(),
//										isDel : frm.findField('code.isDel').getValue()
//									});
//								   store.insert(0, [code]);
//								}
//								app.window_add.setTitle('[ ' + dnfield.getValue() + ' ]   添加成功!!');
//								dnfield.reset();
//								btn.enable();
//								app.window_add.close();
//							},
//							failure : function() {
//								Ext.Msg.show({
//									title : '错误提示',
//									msg : '"' + dnfield.getValue() + '" ' + '名称可能已经存在或者您没有添加数据的权限!',
//									buttons : Ext.Msg.OK,
//									fn : function() {
//										dnfield.focus(true);
//										btn.enable();
//									},
//									icon : Ext.Msg.ERROR
//								})
//							}
//						})
//					}
//				
 				}
			}, {
				text : '重置',
				handler : function() {
					this.ownerCt.form.reset();
				}
			}, {
				text : '取消',
				handler : function() {
					this.ownerCt.ownerCt.hide();
					this.ownerCt.form.reset();
				}
			}]
		})]
	});
	
	app.update_code_btn = new Ext.Button({ 
		text : '编辑',
		iconCls : 'icon-edit',
		handler : function() {
			if(app.grid.getSelectionModel().getSelected()){
			var record = app.grid.getSelectionModel().getSelected();
				var updateWin = new Ext.Window({ 
					title : '编辑',
					iconCls:'icon-edit',
					width : 450,
//					height : 440,
					resizable : false,
					autoHeight : true,
					modal : true,
					closeAction : 'close',
					items : [new Ext.FormPanel({
						labelWidth : 80,
						labelAlign : 'right',
						url : project+'/article/update.cgi',
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
							id : 'article.id',
							name : 'article.id',
							readOnly:true,
							value : record.get('d_id')
						},{
							fieldLabel : '标题',
							name : 'article.title',
							value : record.get('d_title')
						},{
							fieldLabel : '外部地址',
							name : 'article.articleUrl',
							value : record.get('d_acticle_url')
						},{
							fieldLabel : '内容URL',
							name : 'article.acticleRealUrl',
							value : record.get('d_article_real_url')
						},{
							fieldLabel : '内容XML',
							name : 'article.acticleXmlUrl',
							value : record.get('d_article_xml_url')
						},{
							fieldLabel : '时间',
							xtype:'hidden',
							name : 'article.createTime',
							value:record.get('d_createtime')
						},{
							fieldLabel : '所属网站',
							name : 'article.webId',
							value : webId
						},{
							fieldLabel : '状态',
							name : 'article.text',
							value : record.get('d_text')
						},{
							fieldLabel : '介绍',
							xtype:'textarea',
							name : 'article.intro',
							value:record.get('d_intro')
						}],
						buttonAlign : 'right',
						minButtonWidth : 60,
						buttons : [{
							text : '更新',
							handler : function(btn) {
								var frm = this.ownerCt.form;
								if (frm.isValid()) {
									btn.disable();
									var dnfield = frm.findField('article.title');
									frm.submit({
										waitTitle : '请稍候',
										waitMsg : '正在提交表单数据,请稍候...',
										success : function(form, action) {
											Ext.Msg.show({
												title : '系统提示',
												msg : '修改文章"' + dnfield.getValue() + '"成功!',
												buttons : Ext.Msg.OK,
												fn : function() {
													dnfield.focus(true);
													btn.enable();
												},
												icon : Ext.MessageBox.INFO
											});
											app.ds_article.load({params : {start : 0,limit : app.limit}});
										},
										failure : function() {
											Ext.Msg.show({
												title : '错误提示',
												msg : '"' + dnfield.getValue() + '" ' + '名称可能已经存在或者您没有更新数据的权限!',
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
	})
	
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
		autoHeight:true,
		autoWidth:true,
//		width:1200,
		sm:app.sm,
		tbar : [app.btn_get_img_url,'-',app.btn_add_code,'-',app.update_code_btn,'-',app.btn_upload,'-',app.search_comb_queyrCol_code,'-', app.text_search_code], //'-',app.btn_search_code
		bbar : app.ptb
	});
	
	app.grid.addListener('rowdblclick',function(grid, rowIndex){
				if(grid.getSelectionModel().isSelected(rowIndex)){
					var record = app.grid.getSelectionModel().getSelected();
					app.text_search_code.setValue(record.get('d_title'));
					Ext.get('op').dom.value += "ID:"+record.get('d_id') +　"\t" + record.get('d_title') + "\t" + record.get('d_acticle_url') 
							+ '\td_web_id:'+webId
							+ "\n---------------------------------------------------------------------------------------------------\n";
					var url = String.format(project+"/pages/images/image.jsp?id={0}",record.get('d_id'));
					window.location = url;
				}
	});

    app.grid.render('div-article');
    
//    app.grid.getSelectionModel().selectFirstRow();
    
});