var app = {};
Ext.onReady(function(){
  	Ext.BLANK_IMAGE_URL = project + '/resource/image/ext/s.gif';
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    Ext.QuickTips.init();
	app.limit = 15;
	app.colName = "d_phonenum";
	app.values = "000000";
    app.sm = new Ext.grid.CheckboxSelectionModel();
    
    var expander = new Ext.grid.RowExpander({
        tpl : new Ext.XTemplate(
        	 '<p>业务说明</p>',
			 '<tpl if="'+btype+' == 1">', 
	            '<p>无线音乐高级俱乐部会业务介绍：暂无</p>',
	         '</tpl>',
			 '<tpl if="'+btype+' == 2">', 
	            '<p>139邮箱业务介绍：暂无</p>',
	         '</tpl>',
			 '<tpl if="'+btype+' == 3">', 
	            '<p>飞信会员业务介绍：暂无</p>',
	         '</tpl>',
			 '<tpl if="'+btype+' == 4">', 
	            '<p>号簿管家:</br><b>实惠</b>:免GPRS流量&nbsp;&nbsp;<b>安全</b>:多重加密保护&nbsp;&nbsp;<b>方便</b>:操作简单&nbsp;&nbsp;<b>广泛</b>:机型覆盖范围广&nbsp;&nbsp;</p>',
	         '</tpl>',
			 '<tpl if="'+btype+' == 5">', 
	            '<p>全曲下载业务介绍：暂无</p>',
	         '</tpl>',
			 '<tpl if="'+btype+' == 6">', 
	            '<p>手机报业务介绍：暂无</p>',
	         '</tpl>',
			 '<tpl if="'+btype+' == 7">', 
	            '<p>手机视频业务介绍：暂无</p>',
	         '</tpl>',
			 '<tpl if="'+btype+' == 8">', 
	            '<p>手机阅读业务介绍：暂无</p>',
	         '</tpl>',
			 '<tpl if="'+btype+' == 9">', 
	            '<p>手机游戏业务介绍：暂无</p>',
	         '</tpl>',
			 '<tpl if="'+btype+' == 10">', 
	            '<p>手机电视业务介绍：暂无</p>',
	         '</tpl>',
			 '<tpl if="'+btype+' == 11">', 
	            '<p>移动MM业务介绍：暂无</p>',
	         '</tpl>',
			 '<tpl if="'+btype+' == 12">', 
	            '<p>GPRS流量包业务介绍：暂无</p>',
	         '</tpl>',
			 '<tpl if="'+btype+' == 13">', 
	            '<p>彩信包业务介绍：暂无</p>',
	         '</tpl>',
			 '<tpl if="'+btype+' == 14">', 
	            '<p>手机支付业务介绍：暂无</p>',
	         '</tpl>',
			 '<tpl if="'+btype+' == 15">', 
	            '<p>WIFI业务介绍：暂无</p>',
	         '</tpl>',
			 '<tpl if="'+btype+' == 16">', 
	            '<p>手机地图业务介绍：暂无</p>',
	         '</tpl>',
			 '<div class="x-clear"></div>'			 
		 ),
         lazyRender : true
    });
    
    app.cm_utp = new Ext.grid.ColumnModel([
	    expander,
//		app.sm,
        {header: "手机号码", width: 100, sortable: true, dataIndex: 'phonenum'},
        {header: "业务类型", width: 100, sortable: true, dataIndex: 'btype',renderer:function(v){
			var x = parseInt(btype);
			var typeName = '无线音乐';
			switch(x){
				case 1:
					typeName = '无线音乐高级俱乐部会员';
					break;
				case 2:
					typeName = '139邮箱';
					break;
				case 3:
					typeName = '飞信会员';
					break;
				case 4:
					typeName = '号簿管家';
					break;
				case 5:
					typeName = '全曲下载';
					break;
				case 6:
					typeName = '手机报';
					break;
				case 7:
					typeName = '手机视频';
					break;
				case 8:
					typeName = '手机阅读';
					break;
				case 9:
					typeName = '手机游戏';
					break;
				case 10:
					typeName = '手机电视';
					break;
				case 11:
					typeName = '移动MM';
					break;
				case 12:
					typeName = 'GPRS流量包';
					break;
				case 13:
					typeName = '彩信包';
					break;
				case 14:
					typeName = '手机支付';
					break;
				case 15:
					typeName = 'WIFI';
					break;
				case 16:
					typeName = '手机地图';
					break;
				default:
					typeName = '默认';
					break;
			}
        	return '<font color="blue">'+typeName+'</font>';
        }},
        {header: "业务区", width: 100, sortable: true, dataIndex: 'bdistrict'},
        {header: "是否营销", width: 100, sortable: true, dataIndex: 'isMarket',renderer:function(v){
        	if(v == 0){
        		return '';
        	}else if(v == 1){
        		return '<font color="blue">是</font>';
        	}else if(v == 2){
        		return '<font color="red">否</font>';
        	}else{
        		return '<font color="yellow">未知</font>';
        	}
        }},
        {header: "是否营销成功", width: 100, sortable: true, dataIndex: 'mSuccess',renderer:function(v){
        	if(v == 0){
        		return '';
        	}else if(v == 1){
        		return '<font color="blue">是</font>';
        	}else  if(v == 2){
        		return '<font color="red">否</font>';
        	}else{
        		return '<font color="yellow">未知</font>';
        	}
        }},
        {header: "本平台营销成功", width: 100, sortable: true, dataIndex: 'platsell',renderer:function(v){
        	if(v == 0){
        		return '';
        	}else if(v == 1){
        		return '<font color="blue">是</font>';
        	}else  if(v == 2){
        		return '<font color="red">否</font>';
        	}else{
        		return '<font color="yellow">未知</font>';
        	}
        }},
        {header: "业务是否支持", width: 100, sortable: true, dataIndex: 'support',renderer:function(v){
        	if(v == 0){
        		return '<font color="red">不支持</font>';
        	}else if(v == 1){
        		return '<font color="blue">支持</font>';
        	}else{
        		return '<font color="yellow">未知</font>';
        	}
        }},
        {header: "业务形式", width: 100, sortable: true, dataIndex: 'suuporttype',renderer:function(v){
        	var x = parseInt(v);
        	var typeName = '短信';
        	switch(x){
        		case 0:
        			typeName = '<font color="red">不支持</font>';
        			break;
        		case 1:
    				typeName = 'WAP,短信';
        			break;
        		case 2:
        			if(btype == 10){
	        			typeName = '客户端';
        			}else if(btype == 15){
	        			typeName = 'WIFI';
        			}else{
	        			typeName = '短信,WAP,客户端';
        			}
        			break;
        		default:
        			typeName = '短信'
        			break;	
        	}
        	/**
        	if(v == 0){
        		return '<font color="red">不支持</font>';
        	}else if(v == 1){
        		return '<font color="blue">支持</font>';
        	}else{
        		return '<font color="yellow">未知</font>';
        	}
        	**/
        	return typeName;
        }}
//        {header: "业务形式", width: 100, sortable: true, dataIndex: 'btype',renderer:function(v){
//			var x = parseInt(v);
//			var typeName = '客户端';
//			switch(x){
//				case 1:
//				case 6:
//				case 15:
//					typeName = '';
//					break;
//				case 4:
//					typeName = 'WAP,客户端,短信';
//					break;
//				case 2:
//				case 3:
//				case 5:
//				case 7:
//				case 8:
//				case 9:
//				case 11:
//				case 12:
//				case 13:
//				case 14:
//				case 16:
//					typeName = 'WAP,客户端';
//					break;
//				case 10:
//					typeName = '客户端';
//					break;
//				default:
//					typeName = '默认';
//					break;
//			}
//        	return '<font color="blue">'+typeName+'</font>';
//        }}
    ]);
    
	app.isprompt = new Ext.form.ComboBox({
//				fieldLabel : '查询条件',
				id : 'isprompt',
				hiddenName : 'colName',
				valueField : 'id',
				displayField : 'name',
				mode:'local',
				width:100,
//				value:'1',
				store : new Ext.data.SimpleStore({
					data : [
							['1', '是'],
							['2', '否']
				    ],
					fields : ['id', 'name']
				}),	
				selectOnFocus : true,
				editable : false,
				allowBlank : false,
				triggerAction : 'all',
				emptyText : '是否进行营销'
	});


	app.promptss = new Ext.form.ComboBox({
//				fieldLabel : '营销是否成功',
				id : 'promptss',
				hiddenName : 'colName',
				valueField : 'id',
				displayField : 'name',
				mode:'local',
//				value:'1',
				width:100,
				store : new Ext.data.SimpleStore({
					data : [
							['1', '是'],
							['2', '否']
				    ],
					fields : ['id', 'name']
				}),	
				selectOnFocus : true,
				editable : false,
				allowBlank : false,
				triggerAction : 'all',
				emptyText : '营销是否成功'
	});

	app.platsell = new Ext.form.ComboBox({
//				fieldLabel : '',
				id : 'platsell',
				hiddenName : 'colName',
				valueField : 'id',
				displayField : 'name',
				mode:'local',
//				value:'1',
				width:100,
				store : new Ext.data.SimpleStore({
					data : [
							['1', '是'],
							['2', '否']
				    ],
					fields : ['id', 'name']
				}),	
				selectOnFocus : true,
				editable : false,
				allowBlank : false,
				triggerAction : 'all',
				emptyText : '本平台营销成功'
	});
	
	app.btn_search_code = new Ext.Button({
		text : '查询',
		disabled:false,
		iconCls : 'icon-search',
		handler : function(){
			app.searchcode();
		}
	});
	
	app.btn_save_code = new Ext.Button({
		text : '保存',
		disabled:false,
		iconCls : 'icon-accept',
		handler : function(){
			var values =app.text_phone_number.getValue();
			var records = app.grid.getSelectionModel().getSelections();
			if(records.length == 0 ){
				Ext.Msg.show({
					title : '提示',
					msg:'请选择需要修改的记录',
					buttons : Ext.Msg.OK,
					icon : Ext.Msg.ERROR
				});
			}else{
				var isMarket = app.isprompt.getValue();
				var mSuccess = app.promptss.getValue();
				var platsell = app.platsell.getValue();
				if('' == isMarket || null == isMarket){
					Ext.MessageBox.alert('提示','营销状态必须选择！');
					app.isprompt.focus();
					return;
				}
				
				if('' == mSuccess || null == mSuccess){
					Ext.MessageBox.alert('提示','营销结果必须选择！');
					app.promptss.focus();
					return;
				}
				
				if('' == platsell || null == platsell){
					Ext.MessageBox.alert('提示','是否本平台营销必须选择！');
					app.promptss.focus();
					return;
				}
				
				if(null != values && '' !=  values){
					if(records[0].get('isMarket') == 1 && records[0].get('mSuccess') == 1){
						Ext.Msg.show({
							title : '系统提示',
							msg : '当前记录已经为营销且成功状态，不能修改',
							buttons : Ext.Msg.OK,
							icon : Ext.MessageBox.ERROR
						});
						return;
					}else{
						//执行提交所选号码，并修改该号码的营销状态
						Ext.Msg.confirm('提示', '你确定修改用所选记录的营销状态和结果?', function(btn) {
							if (btn == 'yes') {
								Ext.Ajax.request({
									url : project+'/bussinesssimplify/umarket.cgi',
									params : {
										id : records[0].get('id'),
										phonenum:records[0].get('phonenum'),
										isMarket:isMarket,
										mSuccess:mSuccess,
										platsell:platsell
									},
									success:function(response,option){
										var obj = Ext.util.JSON.decode(response.responseText);
										if(obj.success){
											Ext.Msg.show({
												title : '提示',
												msg:obj.msg,
												buttons : Ext.Msg.OK,
//												fn:function(){
//													app.ds_data.load({
//														params:{
//															colName :app.colName,
//															value:values,
//															start:0,
//															limit:app.limit
//														}
//													});
//												},
												icon : Ext.Msg.INFO
											});
										}else{
											Ext.Msg.show({
												title : '提示',
												msg : obj.msg,
												buttons : Ext.Msg.OK,
												icon : Ext.Msg.ERROR
											});
										}
									},
					                failure:function(response,option){
										Ext.Msg.show({
											title : '提示',
											msg : '系统发生错误!',
											buttons : Ext.Msg.OK,
											icon : Ext.Msg.ERROR
										});
					                }
								});
							}
						});
					}
				}
			}
			/**
			**/
		}
	});

	app.searchcode = function() {
		var values =app.text_phone_number.getValue();
		if(null == values || '' ==  values){
			Ext.Msg.show({
				title : '系统提示',
				msg : '请输入需要查询的手机号码！',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.ERROR
			});
			return false;
		}
		var b = vmobile(values);
		if(!b){
			Ext.Msg.show({
				title : '系统提示',
				msg : '输入的手机号码格式不对，请重新输入!',
				buttons : Ext.Msg.OK,
				fn:function(){
					app.ds_data.removeAll();
				},
				icon : Ext.MessageBox.ERROR
			});
			return false;
		}
		/**
		Ext.Ajax.request({
			url : project+'/bussinesssimplify/list.cgi?btype='+btype,
			params:{
				colName : app.colName,
				value: values,
				start:0,
				limit:app.limit
			},
			success:function(response,option){
				var result = Ext.util.JSON.decode(response.responseText);
				var json = eval(result.success);
				alert(' >> result:'+json);
				if(result.success){
					app.ds_data.load({
						params : {
							colName :app.colName,
							value:values,
							start:0,
							limit:app.limit
						}
					});
				}else{
					Ext.Msg.show({
						title:'系统提示',
						msg:'暂未查询到该号码['+values+']数据！',
						buttons : Ext.MessageBox.OKCANCEL,
						icon:Ext.MessageBox.ERROR
					});
				}
			},
            failure:function(){
				Ext.Msg.show({
					title:'系统提示',
					msg:'暂未查询到该号码['+values+']数据！',
					buttons : Ext.MessageBox.OKCANCEL,
					icon:Ext.MessageBox.ERROR
				});
            }
		});
		**/
		app.ds_data.load({
			params : {
				btype:btype,
				colName :app.colName,
				value:values,
				start:0,
				limit:app.limit
			}
		});
	};
	
	app.text_phone_number = new Ext.form.TextField({
		name : 'phone_number',
		width : 150,
		allowBlank:false,
		vtype:'mobile',
		listeners : {
			'specialkey' : function(field, e) {
				if (e.getKey() == Ext.EventObject.ENTER) {
					app.searchcode();
				}
			}
		}
	});
	
	app.ds_data = new Ext.data.Store({
		url : project + '/bussinesssimplify/list.cgi?btype='+btype,
		baseParams:{},
		reader : new Ext.data.JsonReader({
			totalProperty : 'count',
			root : 'obj'
		}, [{name : 'phonenum',type : 'string'
		}, {name : 'btype',type : 'string'
		}, {name : 'bdistrict',type : 'string'
		}, {name : 'hsman',type : 'string'
		}, {name : 'hstype',type : 'string'
		}, {name : 'imei',type : 'string'
		}, {name : 'support',type : 'int'
		}, {name : 'suuporttype',type : 'int'
		}, {name : 'isMarket',type : 'int'
		}, {name : 'mSuccess',type : 'int'
		}, {name : 'platsell',type : 'int'
		}, {name : 'id',type : 'int'
		}])
	});
	
	app.ptb = new Ext.PagingToolbar({
		pageSize:app.limit,
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
        viewConfig: {
            forceFit:true
        },
 		plugins: expander,
		sm:app.sm,
		tbar : ['请输入手机号码：',app.text_phone_number,'-',app.btn_search_code,'-','是否进行营销','-',app.isprompt,'-','营销是否成功','-',app.promptss,'-','本平台营销成功','-',app.platsell,'-',app.btn_save_code]
//		bbar : app.ptb
	});
	
	app.grid.addListener('rowdblclick',function(grid, rowIndex){
		if(grid.getSelectionModel().isSelected(rowIndex)){
			Ext.Msg.show({
				title : '系统提示',
				width:200,
				msg : '显示终端数据列表详情',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.INFO
			});
		}
	});

    app.grid.render('utp');
});

function vmobile(v){
     var r = /^((\(\d{2,3}\))|(\d{3}\-))?1?[3,5,8]\d{9}$/;
     return r.test(v);
}
