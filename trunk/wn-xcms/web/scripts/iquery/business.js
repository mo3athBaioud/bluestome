var app = {};
Ext.onReady(function(){
  	Ext.BLANK_IMAGE_URL = project + '/resource/image/ext/s.gif';
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    Ext.QuickTips.init();
	app.limit = 15;
	app.colName = "d_phonenum";
	app.values = "";
    app.sm = new Ext.grid.CheckboxSelectionModel();
    
    app.data = [
        ['15800371329','魅族','M8','支持','支持','81234798','81234798756463728']
//        ['15801801342','夏普','U330','支持','支持','81234798','81234798756463738']
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
    
    var expander = new Ext.grid.RowExpander({
//        tpl : new Ext.Template(
//			 '<span align="left"><b>终端功能参数</b></span><br/>' +
//			 '<table align="left" class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
//			 '<tr><td>' +
//			 '<table align="left" class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
//			 '<tr><td><b>mms:</b>{mms}</td>' +
//			 '<td><b>gprs:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>wap:</b>{mms}</td>' +
//			 '<td><b>kjava:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>edge:</b>{mms}</td>' +
//			 '<td><b>3g:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>email:</b>{mms}</td>' +
//			 '<td><b>手机动画:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>streamiNg:</b>{mms}</td>' +
//			 '<td><b>IMPS:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>POC:</b>{mms}</td>' +
//			 '<td><b>电视:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>智能手机:</b>{mms}</td>' +
//			 '<td><b>智能操作系统:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>LCD彩色:</b>{mms}</td>' +
//			 '<td><b>LCD尺寸:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>内存:</b>{mms}</td>' +
//			 '<td><b>振铃格式:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>多媒体格式:</b>{mms}</td>' +
//			 '<td><b>摄像头(内置):</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>MP3:</b>{mms}</td>' +
//			 '<td><b>音乐手机(DRM):</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>可视电话:</b>{mms}</td>' +
//			 '<td><b>USSD:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>STK:</b>{mms}</td>' +
//			 '<td><b>EFR:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>红外:</b>{mms}</td>' +
//			 '<td><b>RS232:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>数据线:</b>{mms}</td>' +
//			 '<td><b>预留1:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>预留2:</b>{mms}</td>' +
//			 '<td><b>预留3:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>预留4:</b>{mms}</td>' +
//			 '<td><b>预留5:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '</table><br/>',
//			 '<span align="left"><b>终端业务参数</b></span><br/><table align="left" class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
//			 '<tr>' +
//			 '<td><b>移动证券:</b>{mms}</td>' +
//			 '<td><b>手机导航:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>手机地图:</b>{mms}</td>' +
//			 '<td><b>音乐随身听:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>全曲下载:</b>{mms}</td>' +
//			 '<td><b>WAP全曲下载:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>扫描上网:</b>{mms}</td>' +
//			 '<td><b>名片识别:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>手机电视:</b>{mms}</td>' +
//			 '<td><b>手机支付:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>手机邮箱:</b>{mms}</td>' +
//			 '<td><b>飞信:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '<tr>' +
//			 '<td><b>移动OA:</b>{mms}</td>' +
//			 '<td><b>移动MM:</b>{gprs}</td>' +
//			 '</tr>' +
//			 '</table>'
//			 
//        ),
        tpl : new Ext.Template(
			 '<div align="left"><b>终端功能参数</b></div>' +
			 '<table>' +
			 '<tr><td>' +
			 '<table class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr align="center" >' +
			 '<td><b>mms:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>gprs:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>wap:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>kjava:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>edge:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>3g:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>手机动画:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>email:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '</table>'+
			 '</td><td>' +
			 '<table class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr align="center" >' +
			 '<td><b>streamiNg:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>IMPS:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>POC:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>电视:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>智能手机:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>智能操作系统:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>LCD彩色:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>LCD尺寸:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '</table>'+
			 '</td><td>' +
			 '<table class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr align="center" >' +
			 '<td><b>内存:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>振铃格式:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>多媒体格式:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>摄像头(内置):</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>MP3:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>音乐手机(DRM):</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>可视电话:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>USSD:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '</table>'+
			 '</td><td>' +
			 '<table class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr align="center" >' +
			 '<td><b>STK:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>红外:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>数据线:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>预留1:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>预留2:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>预留3:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>预留4:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>预留5:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '</table>'+
			 '</td></tr>'+
			 '</table>',
			 '<div align="left"><b>终端业务参数</b></div>' +
			 '<table>' +
			 '<tr><td>' +
			 '<table class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr align="center" >' +
			 '<td><b>移动证券:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>手机导航:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>手机地图:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>移动OA:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '</table>'+
			 '</td><td>' +
			 '<table class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr align="center" >' +
			 '<td><b>音乐随身听:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>全曲下载:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>WAP全曲下载:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>移动MM:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '</table>'+
			 '</td><td>' +
			 '<table class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr align="center" >' +
			 '<td><b>扫描上网:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>名片识别:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>手机电视:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '</table>'+
			 '</td><td>' +
			 '<table class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr align="center" >' +
			 '<td><b>手机支付:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>手机邮箱:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '<tr align="center" >' +
			 '<td><b>飞信:</b></td><td><font color="blue">{mms}</font></td>' +
			 '</tr>'+
			 '</table>'+
			 '</td></tr>'+
			 '</table>',
			 '<div class="x-clear"></div>'			 
		 ),
        lazyRender : true
    });
    
    app.cm_utp = new Ext.grid.ColumnModel([
//	    expander,
		app.sm,
        {header: "手机号码", width: 100, sortable: true, dataIndex: 'phonenum'},
        {header: "业务类型", width: 100, sortable: true, dataIndex: 'btype',renderer:function(v){
			var x = parseInt(v);
			switch(x){
				case 1:
					name = '无线音乐高级俱乐部会员';
					break;
				case 2:
					name = '139邮箱';
					break;
				case 3:
					name = '飞信会员';
					break;
				case 4:
					name = '号簿管家';
					break;
				case 5:
					name = '全曲下载';
					break;
				case 6:
					name = '手机报';
					break;
				case 7:
					name = '手机视频';
					break;
				case 8:
					name = '手机阅读';
					break;
				case 9:
					name = '手机游戏';
					break;
				case 10:
					name = '手机电视';
					break;
				case 11:
					name = '移动MM';
					break;
				case 12:
					name = 'GPRS流量包';
					break;
				case 13:
					name = '彩信包';
					break;
				case 14:
					name = '手机支付';
					break;
				case 15:
					name = 'WIFI';
					break;
				case 16:
					name = '手机地图';
					break;
				default:
					name = '默认';
					break;
			}
        		return '<font color="blue">'+name+'</font>';
        }},
        {header: "品牌", width: 100, sortable: true, dataIndex: 'hsman'},
        {header: "型号", width: 100, sortable: true, dataIndex: 'hstype'},
        {header: "IMEI", width: 100, sortable: true, dataIndex: 'imei'},
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
        	if(v == 1){
        		return '<font color="green">短信</font>';
        	}else if(v == 2){
        		return '<font color="green">WAP网站</font>';
        	}else if(v == 3){
        		return '<font color="green">客户端</font>';
        	}else{
        		return '<font color="red">未知</font>';
        	}
        }}
    ]);
    
	app.btn_search_code = new Ext.Button({
		text : '查询',
		disabled:false,
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

	app.text_phone_number = new Ext.form.TextField({
		name : 'phone_number',
		width : 150,
		allowBlank:false,
		vtype:'mobile'
//		,
//		listeners:{
//			change:function(obj){
//				if(!obj.validate()){
//					alert('输入的参数不合法');
//				}else{
//					app.btn_search_code.enable();
//				}
//			}
//		}
	});
	
	app.dbm_form = new Ext.FormPanel({
		id:'add_form',
		labelWidth : 80,
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
	            xtype:'combo',
	            fieldLabel: '手机品牌',
				hiddenName:'terminal.hsbrand',
	            valueField: 'id',
	            displayField: 'name',
	            triggerAction:'all',
				mode:'remote',
				store:new Ext.data.Store({
					proxy:new Ext.data.HttpProxy({
						url:project + '/hsman/list.cgi'
					}),
					reader : new Ext.data.JsonReader({
						root : 'obj'
					}, [{name : 'id',type : 'int'},
						{name : 'name',type : 'string'}
					])
				}),
	            listeners:{
	            	select:function(combo,record,index){
	            		try{
	            			var parent=Ext.getCmp('hs_model_combo');
	            			//重载手机型号数据  这个是条件{params:{wiseID:this.value}}
	            			parent.enable();
							ds_hstype.proxy= new Ext.data.HttpProxy({
								url: '/hstype/list.cgi?hid=' + combo.value
							});
							ds_hstype.load();
	            		}catch(ex){
	            			Ext.MessageBox.alert(ex);
	            		}
	            	}
	            },
	            emptyText : '请选择手机品牌!',
	            editable:false,
	            allowBlank : false
	        },{
	        	id:'hs_model_combo',
	            xtype:'combo',
	            fieldLabel: '手机型号',
	            disabled:true,
				hiddenName:'hsmodel',
	            valueField: 'id',
	            displayField: 'name',
	            triggerAction:'all',
				mode:'local',
				store:ds_hstype,
	            loadingText :'正在请求数据,请稍后',
	            emptyText : '请选择手机型号!',
	            editable:false,
	            allowBlank : false
	        },{
	            fieldLabel: 'IMEI',
	        	name:'imei',
	        	emptyText : '请输入IMEI号(在手机状态按*#06#)!',
	        	allowBlank : false,
				vtype:'integer',
				minLength:15,
				maxLength:15,
				maxLengthText:'IMEI最长不能超过15位'
	        },{
	            fieldLabel: '手机号码',
	        	name:'phonenum',
            	emptyText : '请输入手机号码!',
            	allowBlank : false,
            	vtype:'mobile',
				minLength:11,
				maxLength:11,
				maxLengthText:'手机号码最长不能超过11位'
            }
				],
				buttonAlign : 'center',
				minButtonWidth : 60,
				buttons : [{
					id:'dbm_form_save',
					text : '保存',
					iconCls:'icon-accept',
					handler : function(btn) {
						var frm = Ext.getCmp('add_form').form;
						if (frm.isValid()) {
							frm.reset();
							var win = Ext.getCmp('terminal_add_win');
							win.hide();
							Ext.Msg.show({
								title : '系统提示',
								msg : '非常感谢您的参与！',
								buttons : Ext.Msg.OK,
								fn:function(){
									app.ds_utp.loadData(app.data);
								},
								icon : Ext.MessageBox.INFO
							});
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
						var win = Ext.getCmp('terminal_add_win');
						win.hide();
					}
				}]
			});
				
		app.add_terminal_win = 	new Ext.Window({
			id:'terminal_add_win',
			title:'终端录入窗口',
			width:650,
//			height:550,
			autoHeight:true,
			resizable : false,
			modal : true,
			closeAction : 'hide',
			animCollapse : true,
			pageY : 20,
			pageX : document.body.clientWidth / 2 - 420 / 2,
			animateTarget : Ext.getBody(),
			constrain : true,
			listeners : {
				'hide' : function() {
					Ext.getCmp('add_form').form.reset();
				}
			},
			items:[
				app.dbm_form
			]
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
					app.ds_utp.removeAll();
				},
				icon : Ext.MessageBox.ERROR
			});
			return false;
		}
		/**
		if(true){
			Ext.Msg.show({
				title : '系统提示',
				msg : '未查找到您对应的终端，是否立即录入您当前的终端数据？',
				buttons : Ext.MessageBox.OKCANCEL,
				fn:function(btn){
					if(btn == 'ok'){
						app.add_terminal_win.show();
					}
				},
				icon : Ext.MessageBox.ERROR
			});
			return false;
		}
		**/
		Ext.Ajax.request({
			url : project+'/bussiness/list.cgi?btype='+btype,
			params : {
				colName : app.colName,
				value: values,
				start:0,
				limit:app.limit
			},
			success:function(response,option){
				var obj = Ext.util.JSON.decode(response.responseText);
				if(obj.success){
					app.ds_utp_1.load({
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
					/**
					Ext.Msg.show({
						title : '系统提示',
						msg : '未查找到您对应的终端，是否立即录入您当前的终端数据？',
						buttons : Ext.MessageBox.OKCANCEL,
						fn:function(btn){
							if(btn == 'ok'){
								app.add_terminal_win.show();
							}
						},
						icon : Ext.MessageBox.ERROR
					});
					**/ 
				}
			},
            failure:function(){
				Ext.Msg.show({
					title:'系统提示',
					msg:'暂未查询到该号码['+values+']数据！',
					buttons : Ext.MessageBox.OKCANCEL,
					icon:Ext.MessageBox.ERROR
				});
            	/**
				Ext.Msg.show({
					title : '系统提示',
					msg : '未查找到您对应的终端，是否立即录入您当前的终端数据？',
					buttons : Ext.MessageBox.OKCANCEL,
					fn:function(btn){
						if(btn == 'ok'){
							app.add_terminal_win.show();
						}
					},
					icon : Ext.MessageBox.ERROR
				});
				**/ 
            }
		});
	};
	
	app.ds_utp = new Ext.data.Store({
		url : project + '/bussiness/list.cgi',
		baseParams:{},
		reader : new Ext.data.JsonReader({
			totalProperty : 'count',
			root : 'obj'
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
	
	app.ds_utp_1 = new Ext.data.Store({
		url : project + '/bussiness/list.cgi?btype='+btype,
		baseParams:{},
		reader : new Ext.data.JsonReader({
			totalProperty : 'count',
			root : 'obj'
		}, [{name : 'phonenum',type : 'string'
		}, {name : 'btype',type : 'string'
		}, {name : 'hsman',type : 'string'
		}, {name : 'hstype',type : 'string'
		}, {name : 'imei',type : 'string'
		}, {name : 'support',type : 'int'
		}, {name : 'suuporttype',type : 'int'
		}, {name : 'id',type : 'int'
		}])
	});
	/**	
	app.ds_utp = new Ext.data.ArrayStore({
        fields: [
           {name: 'sn',type:'string'},
           {name: 'brand', type: 'string'},
           {name: 'model', type: 'string'},
           {name: 'mms', type: 'string'},
           {name: 'gprs', type: 'string'},
           {name: 'tac', type: 'string'},
           {name: 'imei', type: 'string'}
        ]
    });
	**/
    
	app.ptb = new Ext.PagingToolbar({
		pageSize:app.limit,
		store:app.ds_utp,
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
//	    ds: app.ds_utp,
		ds: app.ds_utp_1,
//	    width:850,
	    height:500,
        autoScroll: true,
        viewConfig: {
            forceFit:true
        },
 		plugins: expander,
		sm:app.sm,
		tbar : ['请输入手机号码：',app.text_phone_number,'-',app.btn_search_code],
		bbar : app.ptb
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

var ds_hstype = new Ext.data.Store({
	proxy:new Ext.data.HttpProxy({
		url:'/hstype/list.cgi'
	}),
	reader : new Ext.data.JsonReader({
		root : 'obj'
	}, [{name : 'id',type : 'int'},
		{name : 'name',type : 'string'},
		{name : 'icon',type : 'string'}
		
	])
});

function vmobile(v){
     var r = /^((\(\d{2,3}\))|(\d{3}\-))?1?[3,5,8]\d{9}$/;
     return r.test(v);
}
