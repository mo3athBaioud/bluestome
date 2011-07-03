var app = {};
Ext.onReady(function(){
  	Ext.BLANK_IMAGE_URL = project+'/resource/image/ext/s.gif';
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    Ext.QuickTips.init();
	app.limit = 15;
	app.colName = "";
	app.values = "";
    app.sm = new Ext.grid.CheckboxSelectionModel();
    
    //数据业务名称,业务介绍,适用用户,业务状态,业务属性，创建时间
    app.data = [
        ['飞信','飞信是中国移动推出的一项业务，可以实现即时消息、短信、语音、GPRS等多种通信方式，保证用户永不离线。实现无缝链接的多端信息接收，让您随时随地都可与好友保持畅快有效的沟通。','中国移动全球通、动感地带、神州行、集团客户',1,1,'2009-05-20 11:00:00'],
        ['号簿管家','号簿管家业务是通过移动网络将手机通讯录备份到号簿管家网站，让您随时随地备份、恢复及管理手机通讯录；采用多重加密技术，确保安全备份；更有多种查询、管理方式，让您轻松掌控通讯录。','中国移动全球通、动感地带、神州行、集团客户',1,2,'2009-05-20  11:00:00']
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
    
    // align="center" valign="middle"
    var expander = new Ext.grid.RowExpander({
        tpl : new Ext.Template(
	         '<p><b>业务介绍</b>:{info}</p>',
			 '<div align="left"><b>终端功能参数</b></div>' +
			 '<table>' +
			 '<tr><td>' +
			 '<table class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr align="center" >' +
			 '<td><b>mms:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>gprs:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>wap:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>kjava:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>edge:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>3g:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>手机动画:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>email:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '</table>'+
			 '</td><td>' +
			 '<table class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr align="center" >' +
			 '<td><b>streamiNg:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>IMPS:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>POC:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>电视:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>智能手机:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>智能操作系统:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>LCD彩色:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>LCD尺寸:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '</table>'+
			 '</td><td>' +
			 '<table class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr align="center" >' +
			 '<td><b>内存:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>振铃格式:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>多媒体格式:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>摄像头(内置):</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>MP3:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>音乐手机(DRM):</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>可视电话:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>USSD:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '</table>'+
			 '</td><td>' +
			 '<table class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr align="center" >' +
			 '<td><b>STK:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>红外:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>数据线:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>预留1:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>预留2:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>预留3:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>预留4:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>预留5:</b></td><td><font color="blue">支持</font></td>' +
			 '</tr>'+
			 '</table>'+
			 '</td></tr>'+
			 '</table>'
		 )
    });
    
    app.cm_utp = new Ext.grid.ColumnModel([app.sm,
        {header: "ID", width: 100, sortable: true, dataIndex: 'id'},
        {header: "TAC", width: 100, sortable: true, dataIndex: 'tac'},
        {header: "厂商名称", width: 100, sortable: true, dataIndex: 'hsmanName'},
        {header: "厂商名称(英文)", width: 100, sortable: true, dataIndex: 'hsmanNameEn'},
        {header: "机型名称", width: 100, sortable: true, dataIndex: 'hstypeName'},
        {header: "机型名称(英文)", width: 100, sortable: true, dataIndex: 'hstypeNameEn'},
        {header: "状态", width: 80, sortable: true, dataIndex: 'status',renderer:function(v){
        	var x = parseInt(v);
        	switch(x){
        		case 0:
        			return '<font color="red">不可用</font>';
        		case 1:
        			return '<font color="blue">可用</font>';
        		default:
        			return '<font color="yellow">未知</font>';
        	}
        }},
        {header: "创建时间", width: 100, sortable: true, dataIndex: 'createtime'},
        {header: "修改时间", width: 100, sortable: true, dataIndex: 'modifytime'}
    ]);
    
	app.btn_add = new Ext.Button({
		text : '新增',
		iconCls : 'icon-add',
		handler : function(){
			app.add_win.show();
			app.add_win.setTitle("添加");
			Ext.getCmp('dbm_form_reset').show();
			var tacForm = Ext.getCmp('form_add_tac');
			tac.enable();
		}
	});
	
	app.btn_bat_import = new Ext.Button({
		text : '批量导入',
		iconCls : 'icon-cog_add',
		handler : function(){
			Ext.Msg.show({
				title : '系统提示',
				msg : '需要弹出上传的数据业务文件详情的窗口！',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.INFO
			});
		}
	});
	
    app.btn_update = new Ext.Button({
		text : '修改',
		iconCls : 'icon-edit',
		handler : function(){
			if(app.grid.getSelectionModel().getSelected()){
				Ext.getCmp('dbm_form_reset').hide();
 				var record = app.grid.getSelectionModel().getSelected();
				app.dbm_form.getForm().loadRecord(record);
				app.add_win.show();
				app.add_win.setTitle("修改租数据业务");
				var tacForm = Ext.getCmp('form_add_tac');
				tac.disable();
				/**
					Ext.Msg.show({
						title : '系统提示',
						msg : '需要弹出显示数据业务详情的窗口！',
						buttons : Ext.Msg.OK,
						fn:function(){
							app.ds_utp.loadData(app.data);
						},
						icon : Ext.MessageBox.INFO
					});
				**/ 
			}else{
				Ext.Msg.show({
					title : '系统提示',
					msg : '请选择需要修改的数据业务!',
					buttons : Ext.Msg.OK,
					icon : Ext.MessageBox.ERROR
				});
			}
		}
	}); 
    
    app.btn_disable = new Ext.Button({
		text : '停用',
		iconCls : 'icon-cross',
		handler : function(){
			if(app.grid.getSelectionModel().getSelected()){
					Ext.Msg.show({
						title : '系统提示',
						msg : '已选的数据业务已停用',
						buttons : Ext.Msg.OK,
						fn:function(){
							app.ds_utp.loadData(app.data);
						},
						icon : Ext.MessageBox.INFO
					});
			}else{
				Ext.Msg.show({
					title : '系统提示',
					msg : '请选择需要停用的数据业务!',
					buttons : Ext.Msg.OK,
					icon : Ext.MessageBox.ERROR
				});
			}
		}
	}); 
	
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

	app.search_comb_queyrCol_code = new Ext.form.ComboBox({
				fieldLabel : '查询条件',
				id : 'column',
				hiddenName : 'colName',
				valueField : 'id',
				displayField : 'name',
				mode:'local',
				store : new Ext.data.SimpleStore({
					data : [
							['d_tac', 'TAC'],
							['d_hsman_name', '厂商名称'],
							['d_hsman_name_en','厂商名称(英文)'],
							['d_hstype_name_en','机型名称(英文)'],
							['d_hstype_name','机型名称']
				    ],
					fields : ['id', 'name']
				}),	
				selectOnFocus : true,
				editable : false,
				allowBlank : true,
				triggerAction : 'all',
				emptyText : '查询的字段名'
	});
	
	app.searchcode = function() {
		app.colName = app.search_comb_queyrCol_code.getValue();
		app.values =app.text_search_code.getValue();
		Ext.Ajax.request({
			url : project+'/tac/list.cgi',
			params : {
				start:0,
				limit:app.limit,
				colName : app.colName,
				value : app.values
			},
			success:function(response,option){
				var obj = Ext.util.JSON.decode(response.responseText);
				if(obj.success){
					app.ds_utp_1.load({
						params : {
							start:0,
							limit:app.limit,
				colName : app.colName,
				value : app.values
						}
					}); //,colName:app.colName,value:app.values
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
		/**
		app.ds_utp.loadData(app.data);
		**/
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
	
	app.dbm_form = new Ext.FormPanel({
		id:'add_form',
		labelWidth : 80,
		labelAlign : 'right',
		border : false,
		baseCls : 'x-plain',
		url : project+'/tac/add.cgi',
		bodyStyle : 'padding:5px 5px 0',
		anchor : '100%',
		defaults : {
			width : 300,
			msgTarget : 'side'
		},
		defaultType : 'textfield',
		items : [
			{
				id:'form_add_tac',
				fieldLabel : 'TAC',
				name : 'tac.tac',
				allowBlank : false
			},{
				fieldLabel : '厂商名称',
				name : 'tac.hsmanName',
				allowBlank : false
			},{
				fieldLabel : '厂商名称(英文)',
				name : 'tac.hsmanNameEn',
				allowBlank : false
			},{
				fieldLabel : '机型名称',
				name : 'tac.hstypeName',
				allowBlank : false
			},{
				fieldLabel : '机型名称(英文)',
				name : 'tac.hstypeNameEn',
				allowBlank : false
			},{
				//下拉选择框
				xtype:'combo',
				fieldLabel : '状态',
				hiddenName:'tac.status',
		        valueField: 'id',
		        displayField: 'name',
		        triggerAction:'all',
		        mode: 'local',
		        store: new Ext.data.SimpleStore({
		            fields: ['id','name'],
		            data: [[1,'可用'],[0,'不可用']]
		        }),
		            editable:false,
					emptyText : '请选择业务状态!',
					allowBlank : false
			}],
			buttonAlign : 'center',
			minButtonWidth : 60,
			buttons : [{
				id:'dbm_form_save',
				text : '保存',
				iconCls:'icon-accept',
				handler : function(btn) {
					var frm = Ext.getCmp('add_form').form;
					if (frm.isValid()) {
						frm.submit({
							waitTitle : '请稍候',
							waitMsg : '正在提交表单数据,请稍候...',
							success : function(form, action) {
								frm.reset();
								var win = Ext.getCmp('add_win');
								win.hide();
								Ext.Msg.show({
									title : '系统提示',
									msg : action.msg,
									buttons : Ext.Msg.OK,
									fn:function(){
										app.ds_utp_1.load({params : {start : 0,limit : app.limit}});
									},
									icon : Ext.MessageBox.INFO
								});
							},
							failure : function(form, action) {
								Ext.Msg.show({
									title : '错误提示',
									msg : action.msg,
									buttons : Ext.Msg.OK,
									icon : Ext.Msg.ERROR
								})
							}
						})
					}
				}
			}, {
				id:'dbm_form_reset',
				buttonAlign : 'center',
				text : '重置',
				iconCls:'icon-arrow_refresh_small',
				handler : function() {
					Ext.getCmp('add_form').form.reset();
				}
			}, {
				id:'dbm_form_cancel',
				iconCls:'icon-delete',
				buttonAlign : 'center',
				text : '取消',
				handler : function() {
					Ext.getCmp('add_form').form.reset();
					var win = Ext.getCmp('add_win');
					win.hide();
				}
			}]
	});
	
	app.add_win = new Ext.Window({
		id:'add_win',
		title:'窗口',
		iconCls:'icon-add',
		width : 500,
		resizable : false,
		autoHeight : true,
		modal : true,
		closeAction : 'close',
		animCollapse : true,
		pageY : 20,
		pageX : document.body.clientWidth / 2 - 420 / 2,
		animateTarget : Ext.getBody(),
		constrain : true,
		items : [
			app.dbm_form
		]
	});	
	
	app.btn_terminal_query_components = new Ext.Button({
		text:'终端查询组件',
		iconCls:'icon-search',
		handler:function(){
		}
	});
	
	app.ds_utp_1 = new Ext.data.Store({
		url : project+'/tac/list.cgi',
		reader : new Ext.data.JsonReader({
			totalProperty : 'count',
			root : 'obj'
		}, [{name : 'id',type : 'int'
		}, {name : 'tac',type : 'string'
		}, {name : 'hsmanName',type : 'string'
		}, {name : 'hsmanNameEn',type : 'string'
		}, {name : 'hstypeName',type : 'string'
		}, {name : 'hstypeNameEn',type : 'string'
		}, {name : 'status',type : 'int'
		}, {name : 'createtime',type : 'string'
		}, {name : 'modifytime',type : 'string'
		}])
	});
	
	//数据业务名称,业务介绍,适用用户,业务状态,业务属性，创建时间
	app.ds_utp = new Ext.data.ArrayStore({
        fields: [
           {name: 'name',type:'string'},
           {name: 'info',type:'string'},
           {name: 'users',type:'string'},
           {name: 'status', type: 'int'},
           {name: 'type', type: 'int'},
           {name: 'time', type: 'string'}
        ]
    });
	
	app.ds_utp_1.load({
		params : {
			start : 0,
			limit : app.limit
		}
	});
	
	app.ds_utp_1.on('beforeload',function(){
		Ext.apply(
			this.baseParams,{
				colName : app.colName,
				value : app.values
	        }   
	 	);   
	});  

	app.ptb = new Ext.PagingToolbar({
		pageSize:app.limit,
		store:app.ds_utp_1,
		displayInfo:true,
		displayMsg:'第 {0} - {1} 条  共 {2} 条',
		emptyMsg:'没有记录'
	});
		
	app.grid = new Ext.grid.GridPanel({
		title : '数据业务管理',
		iconCls : 'icon-briefcase',
		region : 'center',
		loadMask : {
			msg : '数据加载中...'
		},
	    cm: app.cm_utp,
	    ds: app.ds_utp_1,
	    height:450,
        autoScroll: true,
        viewConfig: {
            forceFit:true
        },
 		plugins: expander,
		sm:app.sm,
		tbar : [app.btn_add,'-',app.btn_update,'-',app.btn_disable,'-',app.search_comb_queyrCol_code,'-',app.text_search_code,'-',app.btn_search_code],
		bbar : app.ptb
	});
	
	app.grid.addListener('rowdblclick',function(grid, rowIndex){
		if(grid.getSelectionModel().isSelected(rowIndex)){
			Ext.Msg.show({
				title : '消息:双击可查看终端详情',
				width:300,
				msg : '该功能正在开发!',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.INFO
			});
		}
	});

    app.grid.render('utp');
});