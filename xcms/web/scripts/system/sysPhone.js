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
	
	var record =Ext.data.Record.create([
		{name:'id',mapping:'D_ID',type:'int'},
		{name:'enName',mapping:'D_ENNAME',type:'string'},
		{name:'chName',mapping:'D_CHNAME',type:'string'},
		{name:'icon',mapping:'D_ICON',type:'string'},
		{name:'status',mapping:'D_STATUS',type:'int'},
		{name:'region',mapping:'D_REGION',type:'string'}
	]);

	app.data = [
//		[1,'Nokia','诺基亚','images/nokia.jpg',1,'芬兰'],
//		[2,'Moto','摩托罗拉','images/moto.jpg',1,'美国'],
//		[3,'HTC','宏达','images/htc.jpg',1,'中国台湾'],
//		[4,'Sanm','三星','images/sx.jpg',1,'韩国']
		[1,'NOKIA（诺基亚）','NOKIA（诺基亚）','http://2.zol-img.com.cn/manu_photo/297_.jpg',1,'暂无'],
		[2,'Samsung（三星）','Samsung（三星）','http://2.zol-img.com.cn/manu_photo/98_.jpg',1,'暂无'],
		[3,'MOTO（摩托罗拉）','MOTO（摩托罗拉）','http://2.zol-img.com.cn/manu_photo/295_.jpg',1,'暂无'],
		[4,'HTC','HTC','http://2.zol-img.com.cn/manu_photo/33080_.jpg',1,'暂无'],
		[5,'Apple（苹果）','Apple（苹果）','http://2.zol-img.com.cn/manu_photo/544_.jpg',1,'暂无'],
		[6,'Sony Ericsson（索尼爱立信）','Sony Ericsson（索尼爱立信）','http://2.zol-img.com.cn/manu_photo/1069_.jpg',1,'暂无'],
		[7,'联想','联想','http://2.zol-img.com.cn/manu_photo/1763_.jpg',1,'暂无'],
		[8,'LG','LG','http://2.zol-img.com.cn/manu_photo/143_.jpg',1,'暂无'],
		[9,'天语','天语','http://2.zol-img.com.cn/manu_photo/32729_.jpg',1,'暂无'],
		[10,'步步高','步步高','http://2.zol-img.com.cn/manu_photo/1795_.jpg',1,'暂无'],
		[11,'MEIZU（魅族）','MEIZU（魅族）','http://2.zol-img.com.cn/manu_photo/1434_.jpg',1,'暂无'],
		[12,'Sharp（夏普）','Sharp（夏普）','http://2.zol-img.com.cn/manu_photo/300_.jpg',1,'暂无'],
		[13,'Apanda（首派）','Apanda（首派）','http://2.zol-img.com.cn/manu_photo/33941_.jpg',1,'暂无'],
		[14,'华为','华为','http://2.zol-img.com.cn/manu_photo/613_.jpg',1,'暂无'],
		[15,'CoolPAD（酷派）','CoolPAD（酷派）','http://2.zol-img.com.cn/manu_photo/1606_.jpg',1,'暂无'],
		[16,'ZTE（中兴）','ZTE（中兴）','http://2.zol-img.com.cn/manu_photo/642_.jpg',1,'暂无'],
		[17,'纽曼','纽曼','http://2.zol-img.com.cn/manu_photo/1081_.jpg',1,'暂无'],
		[18,'多普达','多普达','http://2.zol-img.com.cn/manu_photo/1041_.jpg',1,'暂无'],
		[19,'OPPO','OPPO','http://2.zol-img.com.cn/manu_photo/1673_.jpg',1,'暂无'],
		[20,'现代手机','现代手机','http://2.zol-img.com.cn/manu_photo/34004_.jpg',1,'暂无'],
		[21,'金立','金立','http://2.zol-img.com.cn/manu_photo/1632_.jpg',1,'暂无'],
		[22,'DELL（戴尔）','DELL（戴尔）','http://2.zol-img.com.cn/manu_photo/21_.jpg',1,'暂无'],
		[23,'长虹','长虹','http://2.zol-img.com.cn/manu_photo/1589_.jpg',1,'暂无'],
		[24,'Konka（康佳）','Konka（康佳）','http://2.zol-img.com.cn/manu_photo/599_.jpg',1,'暂无'],
		[25,'Acer宏?','Acer宏?','http://2.zol-img.com.cn/manu_photo/218_.jpg',1,'暂无'],
		[26,'泰克飞石','泰克飞石','http://2.zol-img.com.cn/manu_photo/32833_.jpg',1,'暂无'],
		[27,'iHKC（弘谷电）','iHKC（弘谷电）','http://2.zol-img.com.cn/manu_photo/32730_.jpg',1,'暂无'],
		[28,'Toshiba（东芝）','Toshiba（东芝）','http://2.zol-img.com.cn/manu_photo/209_.jpg',1,'暂无'],
		[29,'DEC（中恒）','DEC（中恒）','http://2.zol-img.com.cn/manu_photo/1099_.jpg',1,'暂无'],
		[30,'Haier（海尔）','Haier（海尔）','http://2.zol-img.com.cn/manu_photo/221_.jpg',1,'暂无'],
		[31,'Hisense（海信）','Hisense（海信）','http://2.zol-img.com.cn/manu_photo/19_.jpg',1,'暂无'],
		[32,'ASUS（华硕）','ASUS（华硕）','http://2.zol-img.com.cn/manu_photo/227_.jpg',1,'暂无'],
		[33,'EY（亿通）','EY（亿通）','http://2.zol-img.com.cn/manu_photo/34096_.jpg',1,'暂无'],
		[34,'koobee','koobee','http://2.zol-img.com.cn/manu_photo/34023_.jpg',1,'暂无'],
		[35,'HEDY（七喜）','HEDY（七喜）','http://2.zol-img.com.cn/manu_photo/23_.jpg',1,'暂无'],
		[36,'TCL','TCL','http://2.zol-img.com.cn/manu_photo/171_.jpg',1,'暂无'],
		[37,'Amoi（夏新）','Amoi（夏新）','http://2.zol-img.com.cn/manu_photo/563_.jpg',1,'暂无'],
		[38,'ViewSonic（优派）','ViewSonic（优派）','http://2.zol-img.com.cn/manu_photo/314_.jpg',1,'暂无'],
		[39,'CASIO（卡西欧）','CASIO（卡西欧）','http://2.zol-img.com.cn/manu_photo/321_.jpg',1,'暂无'],
		[40,'GIGABYTE（技嘉）','GIGABYTE（技嘉）','http://2.zol-img.com.cn/manu_photo/234_.jpg',1,'暂无'],
		[41,'HP（惠普）','HP（惠普）','http://2.zol-img.com.cn/manu_photo/223_.jpg',1,'暂无'],
		[42,'Philips（飞利浦）','Philips（飞利浦）','http://2.zol-img.com.cn/manu_photo/159_.jpg',1,'暂无'],
		[43,'爱国者','爱国者','http://2.zol-img.com.cn/manu_photo/29_.jpg',1,'暂无'],
		[44,'malata（万利达）','malata（万利达）','http://2.zol-img.com.cn/manu_photo/1071_.jpg',1,'暂无'],
		[45,'西铂','西铂','http://2.zol-img.com.cn/manu_photo/34048_.jpg',1,'暂无'],
		[46,'首亿','首亿','http://2.zol-img.com.cn/manu_photo/34445_.jpg',1,'暂无'],
		[47,'FADAR（锋达通）','FADAR（锋达通）','http://2.zol-img.com.cn/manu_photo/33477_.jpg',1,'暂无'],
		[48,'华录','华录','http://2.zol-img.com.cn/manu_photo/33139_.jpg',1,'暂无'],
		[49,'Sansui（山水）','Sansui（山水）','http://2.zol-img.com.cn/manu_photo/1008_.jpg',1,'暂无'],
		[50,'宇达电通MIO','宇达电通MIO','http://2.zol-img.com.cn/manu_photo/1806_.jpg',1,'暂无'],
		[51,'bodee（博迪）','bodee（博迪）','http://2.zol-img.com.cn/manu_photo/33502_.jpg',1,'暂无']	
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
    
    //语言图片
    function qtips(val){
//    	var record = grid_phone.getSelectionModel().getSelected();
    	if('' != val && null != val){
        	return '<span style="display:table;width:100%;" qtip=\'<img src="' + val + '">\'>缩略图</span>'
    	}else{
        	return '<span style="display:table;width:100%;" qtip=\'<img src="/images/nopic.jpg">\'>暂无图标</span>'
    	}
    }
                
	 var cm_phone =new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),sm,
				{	
					id:'d_enname',
					header:"英文名称", 
					dataIndex:"D_ENNAME", 
					sortable:true,
					width:120,
					menuDisabled : true
				},{
					id:'d_chname',
					header:"中文名称", 
					dataIndex:"D_CHNAME", 
					sortable:false,
					width:150,
					menuDisabled : true
				},{
					header:"产地", 
					dataIndex:"D_REGION", 
					sortable:false,
					width:100,
					menuDisabled : true
				},{
					header:"图标", 
					dataIndex:"D_ICON", 
					sortable:true,
					width:130,
				    renderer: qtips
//					renderer : function(value) {
//						return '<img height=20px src="'+value+'"/>';
//					}
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
			Ext.getCmp('btn_form_reset').show();
	 		windows_add_phone.show();
	 		windows_add_phone.setTitle('<img src="../../images/phone_add.png" align="top" class="IEPNG">添加');
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
		iconCls : 'icon-cancel',
		handler : function() {
			var records = grid_phone.getSelectionModel().getSelections();
			if(records.length != 0 ){
				var ids = [];
				for(i = 0;i<records.length;i++){
					ids.push(records[i].get('D_ID'))
				}
				Ext.Msg.confirm('确认解除删除', '你确定解除品牌删除状态?', function(btn) {
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
	};
	
	var search_comb_queyrCol_phone = new Ext.form.ComboBox({
		fieldLabel : '查询条件',
		id : 'column_operator',
		hiddenName : 'colName',
		valueField : 'id',
		displayField : 'name',
		mode:'local',
		store : new Ext.data.SimpleStore({
			data : [
					['enName', '英文名称'],
					['realName', '中文名称']
		    ],
			fields : ['id', 'name']
		}),	
		selectOnFocus : true,
		editable : false,
		allowBlank : true,
		triggerAction : 'all',
		emptyText : '查询的字段名'
	});
	
	var search_comb_queyrCol_region = new Ext.form.ComboBox({
		fieldLabel : '查询条件',
		id : 'column_operator',
		hiddenName : 'colName',
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
		editable : false,
		allowBlank : true,
		triggerAction : 'all',
		emptyText : '请选择手机产品'
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
		iconCls : 'icon-application_view_detail',
		handler : function() {
			Ext.getCmp('btn_form_reset').hide();
			Ext.getCmp('btn_form_add').hide();
			var record = grid_phone.getSelectionModel().getSelected();
			app.phone_form.getForm().loadRecord(record);
	 		windows_add_phone.show();
	 		windows_add_phone.setTitle('<img src="../../images/phone_add.png" align="top" class="IEPNG">查看详情');
		}
	});
	
	var update_phone_btn = new Ext.Button({  
		text: '修改',
		iconCls:'icon-edit',
		handler:function(){
			if(grid_phone.getSelectionModel().getSelected()){
				Ext.getCmp('btn_form_add').show();
				Ext.getCmp('btn_form_reset').hide();
				var record = grid_phone.getSelectionModel().getSelected();
				app.phone_form.getForm().loadRecord(record);
				windows_add_phone.show();
				windows_add_phone.setTitle('<img src="../../images/user_edit.png" align="top" class="IEPNG">编辑');
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
	
	
	app.phone_form = new Ext.FormPanel({
			id:'phone_form',
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
				name:'D_ENNAME',
				allowBlank:false,
//				vtype:'alphanum',
				maxLength:64
			},{	
				fieldLabel:'中文名称',
				id:'pass',
				name:'D_CHNAME',
				vtype:'safe',
//				inputType:'chName',
				allowBlank:false,
				maxLength:64
			},{
				fieldLabel:'图标',
				name:'D_ICON',
	            inputType: 'icon',
	            allowBlank: false, //false则不能为空，默认为true
	            maxLength:512
			},{
				fieldLabel:'状态',
				xtype:'combo',
				id:'D_STATUS',
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
				hiddenName : 'D_REGION',
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
			buttonAlign:'center',
			minButtonWidth:60,
			buttons:[{
			  id:'btn_form_add',
			  iconCls:'icon-accept',
			  text:'保存',
			  handler:function(btn){
			  	var frm =Ext.getCmp('phone_form').form;
			  	if(frm.isValid()){
						Ext.Msg.show({
							title : '提示',
							msg : '添加操作员成功!',
							buttons : Ext.Msg.OK,
							fn:function(){
								hsBrandTree.root.reload()
								Ext.getCmp('phone_form').form.reset();
								windows_add_phone.hide();
							},
							icon : Ext.Msg.INFO
						});
			  	}
			  }
			},{
				id:'btn_form_reset',
				text : '重置',
				iconCls:'icon-arrow_refresh_small',
				handler : function() {
					Ext.getCmp('phone_form').form.reset();
				}
			}, {
				id:'btn_form_cancel',
				iconCls:'icon-delete',
				text : '取消',
				handler : function() {
					Ext.getCmp('phone_form').form.reset();
					windows_add_phone.hide();
				}
			}]
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
		animCollapse : true,
		pageY : 20,
		pageX : document.body.clientWidth / 2 - 420 / 2,
		animateTarget : Ext.getBody(),
		constrain : true,
		items:[
			app.phone_form
		]
		
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
	    tbar : [btn_add_phone, '-', btn_undel_phone,'-',btn_del_phone,'-',update_phone_btn,'-',btn_show_phone_detail,'-',search_comb_queyrCol_region,'-',search_comb_queyrCol_phone,'-',
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
		items : [
		{
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
		},{
			region : 'center',
			layout : 'fit',
			items : [grid_phone]
		}]
	});

})