	var app = {};
Ext.onReady(function(){
	Ext.QuickTips.init();
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
	app.limit = 15;
 	app.colName = colName;
	app.values = values;
    app.sm = new Ext.grid.CheckboxSelectionModel();
    
    app.expander = new Ext.grid.RowExpander({
        tpl : new Ext.Template(
        	'<tpl for=".">', 
        	'<p>缩略图:<a href="{d_imgurl_src}" target="_blank">地址</a></p>',
        	'<p>原始图:<a href="{d_httpurl_src}" target="_blank">地址</a></p>',
        	'<div class="thumb-wrap" id="{id}">', 
        	'<div class="thumb"><table qtip="{d_title} border="0" width="100%"><tr><td align="center" valign="middle">', 
        	'<img qtip="{d_title}" alt="{d_title}" class="thumb-img" src="{d_imgurl_src}" >', 
        	'</td></tr></table></div>',
        	'</div>',
        	'</tpl>', 
        	'<div class="x-clear"></div>')
	 });
	 
    function qtips(val){
		var imgUrl = '<span qtip=\'<img src="'+val+'"> \' >'+val+'</span>';
    	return imgUrl;
    }
    
    function download(val){
		var url = val;
		window.location = url;
    }
    
    app.cm_image = new Ext.grid.ColumnModel([
    	app.expander, 
        {id:'ID',header: "ID", width:100,sortable: true, dataIndex: 'd_id'},
        {header: "标题", sortable: true, width:250,dataIndex: 'd_title'},
        {header: "图片名称", sortable: true, dataIndex: 'd_name'},
        {header: "状态", sortable: true, width:100,dataIndex: 'd_link'},
        {header: "创建时间", sortable: true,width:150,dataIndex: 'd_createtime'}
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
	
//		}, [{name : 'd_id',type : 'int'
//		}, {name : 'd_article_id',type : 'int'
//		}, {name : 'd_title',type : 'string'
//		}, {name : 'd_imgurl_src',type : 'string'
//		}, {name : 'd_httpurl_src',type : 'string'
//		}, {name : 'd_commentsuburl',type : 'string'
//		}, {name : 'd_name',type : 'string'
//		}, {name : 'd_createtime',type : 'string'
//		}, {name : 'd_imgurl',type : 'string'
//		}, {name : 'd_commentshowurl',type : 'string'
//		}, {name : 'd_link',type : 'string'
//		}, {name : 'd_time',type : 'string'
//		}, {name : 'd_orderid',type : 'int'
	//表单
	app.imageForm = new Ext.FormPanel({
		name:'imageForm',
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
		items : [
//		{
//			fieldLabel : '名称',
//			name : 'd_name',
//			maxLength : 50
//		},
		{
			fieldLabel : '标题',
			name : 'd_title',
			maxLength : 50
		}, {
			fieldLabel : '小图',		
			name : 'd_imgurl_src',
			maxLength : 150
		}, {
			fieldLabel : '大图',		
			name : 'd_httpurl_src',
			maxLength : 150
		},{
			fieldLabel : '内容地址',		
			name : 'd_commentsuburl',
			maxLength : 150
		},{
			fieldLabel : '排序号',
			name : 'd_orderid',
			readOnly:true,
			value:webId
		},{
			//下拉选择框
			xtype:'combo',
			fieldLabel : '状态',
			hiddenName:'d_link',
            valueField: 'id',
            displayField: 'name',
            triggerAction:'all',
            mode: 'local',
            store: new Ext.data.SimpleStore({
                fields: ['id','name'],
                data: [['FD','可用'], ['NED','停用']]
            }),
            emptyText : '请选择记录状态!',
            editable:false,
			allowBlank : false,
			maxLength : 20
		}]
	});
	
	app.win =  new Ext.Window({ 
			id:'image_window',
			title : '窗口',
			iconCls:'icon-edit',
			width : 450,
			resizable : false,
			autoHeight : true,
			modal : true,
			closeAction : 'hide',
			items : [app.imageForm],
			buttonAlign : 'center',
			minButtonWidth : 60,
			buttons : [{
				text : '保存',
				iconCls:'icon-accept',
				handler : function(btn) {
				}
			}, {
				id:'image_form_reset',
				text : '重置',
				iconCls:'icon-arrow_refresh_small',
				handler : function() {
					app.imageForm.getForm().reset();
				}
			}, {
				text : '取消',
				iconCls:'icon-cancel',
				handler : function() {
					app.win.hide();
					app.imageForm.getForm().reset();
				}
			}]
	});
	
	app.btn_edit = new Ext.Button({
		text : '编辑',
		iconCls : 'icon-edit',
		handler : function(){
			var record = app.grid.getSelectionModel().getSelected();
			if(record){
				app.imageForm.getForm().loadRecord(record);
				app.win.show();
				app.win.setTitle('编辑图片信息');
				Ext.getCmp('image_form_reset').hide();
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
				download(record.get('d_imgurl_src'));
			}else{
				Ext.MessageBox.alert("提示信息!!", "请选择要获取URL的数据!!");
			}
		}
	});
	
	app.btn_back = new Ext.Button({
		text:'返回',
		iconCls:'icon-arrow_left',
		handler:function(){
			var url = String.format(project+"/pages/articles/article.jsp?id={0}",webId);
			window.location = url;
		}
	});
	
	app.btn_set_article_icon = new Ext.Button({
		text:'设置文章缩略图',
		iconCls:'icon-anchor',
		handler:function(){
			if(app.grid.getSelectionModel().getSelected()){
				var record = app.grid.getSelectionModel().getSelected();
				Ext.Ajax.request({
					url : project+'/article/picon.cgi',
					params : {
						id :record.get('d_article_id'),
						icon : record.get('d_imgurl_src')
					},
					success:function(response,option){
						var obj = Ext.util.JSON.decode(response.responseText);
						if(obj.success){
							Ext.Msg.show({
								title : '系统提示',
								msg : obj.msg,
								buttons : Ext.Msg.OK,
								fn:function(){
									app.ds_image.load({params : {start:0,limit:app.limit}}); 
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
				})
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
		}, {name : 'd_imgurl_src',type : 'string'
		}, {name : 'd_httpurl_src',type : 'string'
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
	    height:600,
        autoScroll: true,
        viewConfig: {
            forceFit:true
        },
 		plugins: app.expander,
 		sm:app.sm,
		tbar : [app.btn_back,'-',app.btn_edit,'-',app.btn_set_article_icon,'-',app.btn_download_img,'-',app.search_comb_queyrCol_code,'-',app.text_search_code,'-',app.btn_search_code], //'-',app.btn_search_code
		bbar : app.ptb
	});
	
	app.grid.addListener('rowdblclick',function(grid, rowIndex){
		if(grid.getSelectionModel().isSelected(rowIndex)){
					var record = app.grid.getSelectionModel().getSelected();
					var url = String.format(project+"/pages/images/image-dataview.jsp?id={0}&webid={1}",record.get('d_article_id'),webId);
					window.location = url;
		}
	});

    app.grid.render('div-image');
    
});