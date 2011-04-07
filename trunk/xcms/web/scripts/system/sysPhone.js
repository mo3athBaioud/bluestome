var app = {};
Ext.onReady(function(){
  	Ext.BLANK_IMAGE_URL = '/resource/image/ext/s.gif';
	Ext.QuickTips.init();
		
	var limit = 20;
	var sm = new Ext.grid.CheckboxSelectionModel();
	
	Ext.form.Field.prototype.msgTarget = 'side';
	
	Ext.tree.TreePanel.prototype.getChecked = function(node){ 
		var checked = [], i; 
		var root = false; 
		if( typeof node == 'undefined' ) { 
			node = this.getRootNode(); 
			root = true; 
		} 
		if( node.attributes.checked || root) { 
			if (!root) 
			checked.push(node.id); 
			if( !node.isLeaf() ) { 
				for( i = 0; i < node.childNodes.length; i++ ) { 
				checked = checked.concat( this.getChecked(node.childNodes[i]) ); 
				}
			}
		} 
		return checked
	}
	
	var Operator =Ext.data.Record.create([
		{name:'id',mapping:'D_ID',type:'int'},
		{name:'enName',mapping:'D_ENNAME',type:'string'},
		{name:'chName',mapping:'D_CHNAME',type:'string'},
		{name:'icon',mapping:'D_ICON',type:'string'},
		{name:'status',mapping:'D_STATUS',type:'int'},
		{name:'region',mapping:'D_REGION',type:'string'}
	]);

	app.data = [
		[1,'Nokia','诺基亚','images/nokia.jpg',1,'芬兰'],
		[2,'Moto','摩托罗拉','images/moto.jpg',1,'美国'],
		[3,'HTC','宏达','images/htc.jpg',1,'中国台湾'],
		[4,'Sanm','三星','images/sx.jpg',1,'韩国']
	];

//	var ds_phone = new Ext.data.Store({
//		proxy : new Ext.data.HttpProxy({
//			url : 'sysOperator.action'
//		}),
//		reader : new Ext.data.JsonReader({
//			totalProperty : 'count',
//			root : 'operator'
//		}, [{
//			name : 'D_ID',
//			type : 'int'
//		}, {
//			name : 'D_ENNAME',
//			type : 'string'
//		}, {
//			name : 'D_CHNAME',
//			type : 'string'
//		}, {
//			name : 'D_ICON',
//			type : 'string'
//		}, {
//			name : 'D_STATUS',
//			type : 'int'
//		}, {
//			name : 'D_PHONE',
//			type : 'string'
//		}, {
//			name : 'D_MOBILE',
//			type : 'string'
//		}, {
//			name : 'D_EMAIL',
//			type : 'string'
//		}, {
//			name : 'D_FAX',
//			type : 'string'
//		}, {
//			name : 'D_QQ',
//			type : 'string'
//		}, {
//			name : 'D_MSN',
//			type : 'string'
//		}, {
//			name : 'D_OPERATORTYPE_ID',
//			type : 'int'
//		}, {
//			name : 'D_OPERATORTYPE',
//			type : 'string'
//		},  {
//			name : 'D_SPID_ID',
//			type : 'int'
//		}, {
//			name : 'D_SPID',
//			type : 'string'
//		}, {
//			name : 'D_CREATE_DT',
//			type : 'string'
//		}])
//	});
	
	var ds_phone = new Ext.data.ArrayStore({
        fields: [
		{
			name : 'D_ID',
			type : 'int'
		}, {
			name : 'D_ENNAME',
			type : 'string'
		}, {
			name : 'D_CHNAME',
			type : 'string'
		}, {
			name : 'D_ICON',
			type : 'string'
		}, {
			name : 'D_STATUS',
			type : 'int'
		}, {
			name : 'D_REGION',
			type : 'string'
		}]
    });
    
	 var cm_phone =new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),sm,
				{	
					header:"英文名称", 
					dataIndex:"D_ENNAME", 
					sortable:true,
					width:120,
					menuDisabled : true
				},{
					header:"中文名称", 
					dataIndex:"D_CHNAME", 
					sortable:false,
					width:150,
					menuDisabled : true
				},{
					header:"产地", 
					dataIndex:"D_REGION", 
					sortable:false,
					width:200,
					menuDisabled : true
				},{
					header:"图标", 
					dataIndex:"D_ICON", 
					sortable:true,
					width:130
				},{
					header:"状态", 
					dataIndex:"D_STATUS",
					width:100,
					renderer:function(v){
						var x = parseInt(v);
						switch(v){
							case 1:
								return "<span style='color:blue;font-weight:bold;'>启用</span>";
							case 2:
								return "<span style='color:yellow;font-weight:bold;'>停用</span>";
							case 3:
								return "<span style='color:red;font-weight:bold;'>删除</span>";
							default:
								return "<span style='color:red;font-weight:bold;'>未知</span>";
						}
					}
				}
	 ])
	 
	 var btn_add_phone =new Ext.Button({
	 	text:'添加',
	 	iconCls:'icon-phone_add',
	 	handler:function(){
	 		windows_add_phone.show();
	 	}
	 });
	 
	 var btn_del_phone = new Ext.Button({
		text : '删除',
		iconCls : 'icon-phone_delete',
		handler : function() {
			var records = grid_phone.getSelectionModel().getSelections();
			if(records.length != 0 ){
				var ids = [];
				for(i = 0;i<records.length;i++){
					ids.push(records[i].get('D_ID'))
				}
				Ext.Msg.confirm('确认删除', '你确定删除该条记录?', function(btn) {
					if (btn == 'yes') {
					Ext.Msg.show({
						title : '提示',
						msg : '删除成功!',
						buttons : Ext.Msg.OK,
						icon : Ext.Msg.INFO
					});
					}
				});
			}else{
				Ext.Msg.show({
					title : '提示',
					msg : '请选择需要删除的记录',
					buttons : Ext.Msg.OK,
					icon : Ext.Msg.ERROR
				})
			}
		}
	});
	
	 var btn_undel_phone = new Ext.Button({
		text : '解除删除',
//		iconCls : 'icon-user_delete',
		handler : function() {
			var records = grid_phone.getSelectionModel().getSelections();
			if(records.length != 0 ){
				var ids = [];
				for(i = 0;i<records.length;i++){
					ids.push(records[i].get('D_ID'))
				}
				Ext.Msg.confirm('确认解除删除', '你确定解除一下操作员删除状态?', function(btn) {
					if (btn == 'yes') {
							Ext.Msg.show({
								title : '提示',
								msg : '删除记录成功!',
								buttons : Ext.Msg.OK,
								icon : Ext.Msg.INFO
							});
					}
				});
			}else{
				Ext.Msg.show({
					title : '提示',
					msg : '请选择需要删除的记录',
					buttons : Ext.Msg.OK,
					icon : Ext.Msg.ERROR
				})
			}
		}
	});
	
	var text_search_phone = new Ext.form.TextField({
		id:'textSearchoperator_01',
		name : 'textSearchoperator',
		emptyText : '请输入查询的条件!',
		vtype:'search',
		listeners : {
			'specialkey' : function(field, e) {
				if (e.getKey() == Ext.EventObject.ENTER) {
					ds_phone.loadData(app.data);
				}
			}
		}
	});
	
	var searchoperator = function() {
			var colname = search_comb_queyrCol_phone.getValue();
			var values = text_search_phone.getValue();
				Ext.Ajax.request({
					url : 'sysOperator.action',
					params : {
						colName : colname,
						value :  values
					},
					success:function(response,option){
						var obj = Ext.util.JSON.decode(response.responseText);
						if(obj.success){
							ds_phone.load({
								params : 
								{
									start : 0,
									limit : limit
								}
							})
						}else{
							if(obj.msg == null || obj.msg == ''){
								Ext.Msg.show({
									title : '提示',
									msg : '查询数据失败',
									buttons : Ext.Msg.OK,
									icon : Ext.Msg.ERROR
								})
							}else{
								Ext.Msg.show({
									title : '提示',
									msg : obj.msg,
									buttons : Ext.Msg.OK,
									icon : Ext.Msg.ERROR
								})
							}
						}
					},
	                failure:function(response,option){
							Ext.Msg.show({
								title : '提示',
								msg : '系统错误',
								buttons : Ext.Msg.OK,
								icon : Ext.Msg.ERROR
							});
	                }
				})
	}
	
	var search_comb_queyrCol_phone = new Ext.form.ComboBox({
		fieldLabel : '查询条件',
		id : 'column_operator',
		hiddenName : 'colName',
		valueField : 'id',
		displayField : 'name',
		mode:'local',
		store : new Ext.data.SimpleStore({
			data : [
					['enName', '登录名'],
					['realName', '真实姓名'],
				    ['email', '电子邮件']
		    ],
			fields : ['id', 'name']
		}),	
		selectOnFocus : true,
		editable : false,
		allowBlank : true,
		triggerAction : 'all',
		emptyText : '查询的字段名'
	});
	
	var btn_search_phone = new Ext.Button({
		text : '查询',
		iconCls : 'icon-search',
		handler : function(){
			ds_phone.loadData(app.data);
		}
	});
	
	var btn_show_phone_detail = new Ext.Button({
		text : '查看详情',
//		iconCls : 'icon-user_comment',
		handler : function() {
			if(grid_phone.getSelectionModel().getSelected()){
				var record = grid_phone.getSelectionModel().getSelected();
				new Ext.Window({ 
					title:'<img src="../../images/user_comment.png" align="top" class="IEPNG"><span style="font-weight:normal">查看系统操作员详情</span>',
					id:'show_phone_detail_window',
					width : 350,
					resizable : false,
					autoHeight : true,
					modal : true,
					closable:false,
					items : [new Ext.FormPanel({
						labelWidth : 70,
						url : 'updateoperator.action',
						border : false,
						baseCls : 'x-plain',
						bodyStyle : 'padding:5px 5px 0',
						defaultType : 'textfield',
						defaults:{
							anchor : '80%',
							msgTarget:'side'
						},
						items : [
						{ 
							fieldLabel : 'ID',
							xtype:'hidden',
							value : record.get('D_ID'),
							readOnly : true
						},
						{
							fieldLabel : '英文名称',
							value : record.get('D_ENNAME'),
							readOnly : true
						},{
							fieldLabel : '中文名称',	
							xtype:'hidden',
							value:record.get('D_CHNAME'),
							readOnly : true
						},{
							fieldLabel : '图标',
							value : record.get('D_ICON'),
							readOnly : true
						},{
							fieldLabel : '状态',
							value:'系统操作员',
							readOnly:true
						},{
							fieldLabel : '产地',
							xtype:'combo',
							hiddenName : 'phone.district',
							valueField : 'id',
							displayField : 'name',
							mode:'local',
							store : new Ext.data.SimpleStore({
								data : [
										['1','中国'],
										['2', '美国'],
										['3', '韩国'],
										['4', '日本'],
										['5', '台湾'],
										['6', '芬兰']
							    ],
								fields : ['id', 'name']
							}),	
							selectOnFocus : true,
							editable:false,
							allowBlank : false,
							triggerAction : 'all',
							emptyText : '当前所属:"'+record.get('D_REGION')+'"'
						}],
						buttonAlign : 'right',
						minButtonWidth : 60,
						buttons : [
						{
							text : '关闭',
							handler : function() {
								Ext.getCmp('show_phone_detail_window').close();
							}
						}]
					})]
				}).show()
			}else{
				Ext.Msg.show({
					title : '提示',
					msg : '请选择一条你需要查看的记录',
					buttons : Ext.Msg.OK,
					icon : Ext.Msg.ERROR
				});
			}
		}
	});
	
	var update_phone_btn = new Ext.Button({  
		text: '修改',
//		iconCls:'icon-user_edit',
		handler:function(){
			if(grid_phone.getSelectionModel().getSelected()){
			var record = grid_phone.getSelectionModel().getSelected();
			new Ext.Window({ 
					id:'update_phone_window',
					title : '<img src="../../images/user_edit.png" align="top" class="IEPNG">更新操作员',
					width : 350,
					resizable : false,
					autoHeight : true,
					modal : true,
					closable:false,
					items : [new Ext.FormPanel({
						id:'update_phone_form',
						labelWidth : 70,
						labelAlign : 'right',
						url : 'updateoperator.action',
						border : false,
						baseCls : 'x-plain',
						bodyStyle : 'pding:5px 5px 0',
						defaultType : 'textfield',
						defaults: {
	                        anchor: '80%',
	                        msgTarget:'side'
	                    },
	                    items : [
						{ 
							fieldLabel : 'ID',
							xtype:'hidden',
							id : 'id',
							name : 'phone.id',
							readOnly : true,
							value : record.get('D_ID')
						},
						{
							fieldLabel : '英文名称',
							name : 'phone.enName',
							value : record.get('D_ENNAME'),
							vtype:'alphanum'
							
						}, {
							fieldLabel : '中文名称',	
							name : 'phone.chName',
							value:record.get('D_CHNAME')
						},{
							fieldLabel : '图标',
							name : 'phone.icon',
							value : record.get('D_ICON')
						},{
							fieldLabel : '状态',
							xtype:'combo',
							id:'sysOperator_update_status',
							hiddenName : 'phone.status',
							valueField : 'id',
							displayField : 'name',
							mode:'local',
							store : new Ext.data.SimpleStore({
								data : [
										['1','启用'],
										['2','停用']
							    ],
								fields : ['id', 'name']
							}),	
							selectOnFocus : true,
							editable:false,
							allowBlank : false,
							triggerAction : 'all',
							emptyText : '当前状态为:' + (record.get('D_STATUS') == 1 ? '启用':'停用')
						},{
							fieldLabel : '产地',
							xtype:'combo',
							id:'update_operator_district',
							hiddenName : 'phone.district',
							valueField : 'id',
							displayField : 'name',
							mode:'local',
							store : new Ext.data.SimpleStore({
								data : [
										['1','中国'],
										['2','美国'],
										['3','韩国'],
										['4','日本'],
										['5','台湾'],
										['6','芬兰']
							    ],
								fields : ['id', 'name']
							}),	
							selectOnFocus : true,
							editable:false,
							allowBlank : false,
							triggerAction : 'all',
							emptyText : '当前产地为'+record.get('D_REGION')+'!'
						}],
						buttonAlign : 'right',
						minButtonWidth : 60,
						buttons : [{
							text : '更新',
							handler : function(btn) {
								var frm = Ext.getCmp('update_phone_form').form;
								if (frm.isValid()) {
										btn.disable();
										Ext.Msg.show({
											title : '提示',
											msg : '更新记录成功!',
											buttons : Ext.Msg.OK,
											fn : function() {
												dnfield.focus(true);
												btn.enable();
											},
											icon : Ext.Msg.INFO
										});
								}
							}
						}, {
							text : '重置',
							handler : function() {
								Ext.getCmp('update_phone_form').form.reset();
							}
						}, {
							text : '取消',
							handler : function() {
								Ext.getCmp('update_phone_form').form.reset();
								Ext.getCmp('update_phone_window').close();
							}
						}]
					})]
				
			}).show();
		}else{
			Ext.Msg.show({
				title : '提示',
				msg : '请选择要修改的记录!!!',
				buttons : Ext.Msg.OK,
				icon : Ext.Msg.ERROR
			});
		}
		}
	});
	
	 var windows_add_phone = new Ext.Window({
	 	id:'windows_add_phone',
	 	title:'<img src="../../images/phone_add.png" align="top" class="IEPNG">添加',
		width : 350,
		height : 440,
		resizable : false,
		autoHeight : true,
		modal : true,
		closable:false,
		items:[new Ext.FormPanel({
			id:'add_phone_form',
			border:false,
			baseCls:'x-plain',
			bodyStyle:'pding:5px 5px 0',
			labelAlign:'right',
			labelWidth:70,
			frame: true,
			autoWidth : true,
			url:'addoperator.action',
			defaults:{anchor:'80%',msgTarget : 'side'},
			defaultType:'textfield',
			items:[{	
				fieldLabel : '英文名称',
				id:'phone.enName',
				name:'phone.enName',
				allowBlank:false,
				vtype:'alphanum',
				maxLength:20
			},{	
				fieldLabel:'中文名称',
				id:'pass',
				name:'phone.chName',
				vtype:'safe',
				inputType:'chName',
				allowBlank:false,
				maxLength:20
			},{
				fieldLabel:'图标',
				name:'icon',
	            inputType: 'icon',
	            allowBlank: false, //false则不能为空，默认为true
	            maxLength:20
			},{
				fieldLabel:'状态',
				xtype:'combo',
				id:'sysOperator_add_status',
				hiddenName:'phone.status',
				valueField:'id',
				displayField:'name',
				mode:'local',
				store:new Ext.data.SimpleStore({
					data:[
						['1','启用'],
						['2','停用']
						],
					fields:['id','name']
				}),
				selectOnFocus:true,
				editable:false,
				allowBlank:false,
				triggerAction:'all',
				loingText:'加载中...',
				emptyText:'基本状态:1:启用,2:停用',
				maxLength:2
			},{
				fieldLabel : '产地',
				xtype:'combo',
				id:'detail_operator_district',
				hiddenName : 'phone.district',
				valueField : 'id',
				displayField : 'name',
				mode:'local',
				store : new Ext.data.SimpleStore({
					data : [
							['1','中国'],
							['2', '美国'],
							['3', '韩国'],
							['4', '日本'],
							['5', '台湾'],
							['6', '芬兰']
				    ],
					fields : ['id', 'name']
				}),	
				selectOnFocus : true,
				editable:false,
				allowBlank : false,
				triggerAction : 'all',
				emptyText : '请选择产地!'
			}],
			buttonAlign:'right',
			minButtonWidth:60,
			buttons:[{
			  text:'添加',
			  handler:function(btn){
			  	var frm =Ext.getCmp('add_phone_form').form;
			  	if(frm.isValid()){
						Ext.Msg.show({
							title : '提示',
							msg : '添加操作员成功!',
							buttons : Ext.Msg.OK,
							icon : Ext.Msg.INFO
						});
			  	}
			  }
			},{
				text : '重置',
				handler : function() {
					Ext.getCmp('add_phone_form').form.reset();
				}
			}, {
				text : '取消',
				handler : function() {
					windows_add_phone.hide();
					Ext.getCmp('add_phone_form').form.reset();
				}
			}]
		})]
		
	});
	
//	ds_phone.loadData(data);
	
	var ptb = new Ext.PagingToolbar({ 
		pageSize:limit,
		store:ds_phone,
		displayInfo:true,
		displayMsg:'第 {0} - {1} 条  共 {2} 条',
		emptyMsg:'没有记录'
	});
	
	var grid_phone = new Ext.grid.GridPanel({
		title : '<img src="../../images/phone.png" align="top" class="IEPNG"><span style="font-weight:normal">手机品牌管理</span>',
		height:500,
		autoScroll : true,
		region : 'center',
		applyTo : 'sysPhone',
		loadMask : {
			msg : '数据加载中...'
		},
		cm : cm_phone,
		ds : ds_phone,
		sm : sm,
	    tbar : [btn_add_phone, '-', btn_undel_phone,'-',btn_del_phone,'-',update_phone_btn,'-',btn_show_phone_detail,'-',search_comb_queyrCol_phone,'-',
				text_search_phone, btn_search_phone],
		bbar : ptb
	});
	
	var root = new Ext.tree.AsyncTreeNode({
		text : '手机品牌',
		expanded : true,
		id : '001'
	});
	
	var hsBrandTree = new Ext.tree.TreePanel({
		loader : new Ext.tree.TreeLoader({
					baseAttrs : {},
					dataUrl : '/tree/hs_brand.json'
				}),
		root : root,
		title : '',
		applyTo : 'terminal_brand',
		autoScroll : false,
		animate : false,
		useArrows : false,
		border : false
	});
	
	hsBrandTree.root.select();
	
	hsBrandTree.on('click', function(node) {
		app.ds_utp.loadData(app.data);
		/**
		deptid = node.attributes.id;
		store.load({
			params : {
				start : 0,
				limit : bbar.pageSize,
				deptid : deptid
			}
		});
		**/
	});
	
	var viewport = new Ext.Viewport({
		layout : 'border',
		items : [{
					title : '<span style="font-weight:normal">手机品牌</span>',
					iconCls : 'icon-phone',
					tools : [{
								id : 'refresh',
								handler : function() {
									hsBrandTree.root.reload()
								}
							}],
					collapsible : true,
					width : 210,
					minSize : 160,
					maxSize : 280,
					split : true,
					region : 'west',
					autoScroll : true,
					// collapseMode:'mini',
					items : [hsBrandTree]
				}, {
					region : 'center',
					layout : 'fit',
					items : [grid_phone]
				}]
	});

})