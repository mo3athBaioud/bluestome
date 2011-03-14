var app = {};
Ext.onReady(function(){
	
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    app.start = 0;
	app.limit = 15;
	app.colName = colName;
	app.values = values;
    app.sm = new Ext.grid.CheckboxSelectionModel();
    
	app.article = Ext.data.Record.create([{
		name : 'id',
		mapping : 'd_id',
		type : 'int'
	}, {
		name : 'url',
		mapping : 'd_url',
		type : 'string'
	}, {
		name : 'webId',
		mapping : 'd_web_id',
		type : 'int'
	}, {
		name : 'author',
		mapping : 'd_author',
		type : 'string'
	}, {
		name : 'grade',
		mapping : 'd_grade',
		type : 'int'
	}, {
		name : 'tag',
		mapping : 'd_tag',
		type : 'string'
	}, {
		name : 'status',
		mapping : 'd_status',
		type : 'int'
	}, {
		name : 'title',
		mapping : 'd_title',
		type : 'string'
	}, {
		name : 'createtime',
		mapping : 'd_createtime',
		type : 'string'
	}, {
		name : 'modifytime',
		mapping : 'd_modifytime',
		type : 'string'
	}, {
		name : 'publishtime',
		mapping : 'd_publish_time',
		type : 'string'
	}]);
    
    app.cm_article = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),app.sm, 
//        {header: "ID", sortable: true, dataIndex: 'd_id'}, //width: 50, 
        {header: "类型", width: 35, sortable: true, dataIndex: 'd_title',
        	renderer : function(value) {
				return '<img src="'+project+'/images/page_white_world.png"/>';
			}
        },
        {header: "标题", width: 250, sortable: true, dataIndex: 'd_title'},
        {header: "作者", width: 70, sortable: true, dataIndex: 'd_author'},
        {header: "发布时间", width:100, sortable: true, dataIndex: 'd_publish_time'},
        {header: "状态", width: 70, sortable: true, dataIndex: 'd_status'},
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
							['author','作者'],
							['status','文章状态']
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
		app.values =app.text_search_code.getValue();
		Ext.Ajax.request({
			url : project+'/article/articledoc.cgi?id='+webId,
			params : {
//				start:0,
//				limit:app.limit,
				colName : app.colName,
				value : app.values
			},
			success:function(response,option){
				var obj = Ext.util.JSON.decode(response.responseText);
				if(obj.success){
//    	         	Ext.MessageBox.alert('提示',obj.msg);
					app.ds_article.load({params : {start:0,limit:app.limit}}); //,colName:app.colName,value:app.values
				}else{
	             	Ext.MessageBox.alert('提示',obj.msg);
				}
			},
            failure:function(){
             	Ext.MessageBox.alert('提示','服务器内部错误');
            }
		})
	};
		
	app.ds_article = new Ext.data.Store({
//		proxy : new Ext.data.HttpProxy({
//			url : project+'/article/article.cgi?id='+webId
//		}),
		url : project+'/article/articledoc.cgi?id='+webId,
		baseParams:{},
		reader : new Ext.data.JsonReader({
			totalProperty : 'count',
			root : 'articledoc'
		}, [{name : 'd_id',type : 'int'
		}, {name : 'd_web_id',type : 'int'
		}, {name : 'd_title',type : 'string'
		}, {name : 'd_url',type : 'string'
		}, {name : 'd_author',type : 'string'
		}, {name : 'd_grade',type : 'string'
		}, {name : 'd_tag',type : 'string'
		}, {name : 'd_status',type : 'string'
		}, {name : 'd_createtime',type : 'string'
		}, {name : 'd_modifytime',type : 'string'
		}, {name : 'd_publish_time',type : 'string'
		}])
	});
	
	app.btn_add_code = new Ext.Button({
		text : '添加',
		iconCls : 'icon-add',
		handler : function() {
			app.window_add.show();
		}
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
				id : 'articleDoc.title',
				name : 'articleDoc.title',
				maxLength : 50
			},{
				fieldLabel : '作者',		
				name : 'articleDoc.author',
				maxLength : 150
			},{
				fieldLabel : '文章地址',		
				name : 'articleDoc.url',
				maxLength : 150
			},{
				fieldLabel : '所属网站ID',
				name : 'articleDoc.websiteBean.id',
				readOnly:true,
				value:webId
			},{
				fieldLabel : '状态',
				name : 'articleDoc.status',
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
					height : 440,
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
							name : 'article.webSiteBean.id',
							value : webId
						},{
							fieldLabel : '状态',
							name : 'article.text',
							value : record.get('d_text')
						},{
							fieldLabel : '介绍',
							xtype:'textarea',
							name : 'article.intro',
//							allowBlank : false,
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
											Ext.MessageBox.alert("成功！", "修改成功!");
//											dnfield.reset();
//											btn.enable();
											this.ownerCt.ownerCt.hide();
//											app.ds_article.load({params : {start : 0,limit : app.limit}});
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
											})
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
				Ext.MessageBox.alert("提示信息!!", "请选择要修改行!!");
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
							data : [[10, '10条/页'], [15, '15条/页'], [20, '20条/页']]
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
//		displayMsg:'第 {0} - {1} 条  共 {2} 条',
		displayMsg : '显示{0}条到{1}条,共{2}条',
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
		width:1200,
//		autoWidth:true,
//		width:800,
//		frame : true,
//		autoExpandColumn : 'title',
		sm:app.sm,
		//,'-',
		tbar : [app.btn_get_img_url,'-',app.btn_add_code,'-',app.update_code_btn,'-', {
									text : '刷新',
//									iconCls : 'page_refreshIcon',
									handler : function() {
										app.ds_article.reload({
													params : {
														start : 0,
														limit : app.ptb.pageSize
													}
										});
									}
								},'-',app.search_comb_queyrCol_code,'-', app.text_search_code], //'-',app.btn_search_code
		bbar : app.ptb
	});
	
	app.grid.addListener('rowdblclick',function(grid, rowIndex){
				if(grid.getSelectionModel().isSelected(rowIndex)){
					var record = app.grid.getSelectionModel().getSelected();
//					app.text_search_code.setValue(record.get('d_title'));
					Ext.get('op').dom.value += "ID:"+record.get('d_id') +　"\t" + record.get('d_title') + "\t" + record.get('d_acticle_url') 
							+ '\td_web_id:'+webId
							+ "\n---------------------------------------------------------------------------------------------------\n";
//					var url = String.format(project+"/pages/articlesdoc/explorer.jsp?url={0}&start={1}&limit={2}&webid={3}",record.get('d_url'),0,app.limit,webId);
					var url = record.get('d_url');
//					Ext.MessageBox.alert('Title',url);
					window.open(url,'doc','height=600,width=800,top=200,left=300,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no, status=no');
//					window.open = url;
				}
	});

    app.grid.render('div-article');
    
//    app.grid.getSelectionModel().selectFirstRow();
    
});