/**
 * 终端业务数据
 */
var app = {};
Ext.onReady(function(){
  	Ext.BLANK_IMAGE_URL = '/resource/image/ext/s.gif';
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    Ext.QuickTips.init();
	app.limit = 15;
	app.colName = "";
	app.values = "";
    app.sm = new Ext.grid.CheckboxSelectionModel();
	
	//业务名称,终端属性集合[GRPS,WAP,CMNET]
    app.data = [
        ['飞信','576分钟','3.5英寸','1677万色',1,1,'2011-03-31 11:00:00'],
        ['移动MM','720分钟','3.2英寸','1677万色',1,1,'2011-03-31 11:00:00'],
        ['手机阅读','420分钟','3.2英寸','1677万色',1,1,'2011-03-31 11:00:00'],
        ['淘乐汇','720分钟','3.5英寸','1677万色',1,1,'2011-03-31 11:00:00'],
        ['手机报','576分钟','3.2英寸','1677万色',1,1,'2011-03-31 11:00:00'],
        ['号谱管家','525分钟','3.2英寸','1677万色',1,1,'2011-03-31 11:00:00'],
        ['可视电话','590分钟','3.7英寸','26万色',1,1,'2011-03-31 11:00:00'],
        ['彩信相册','360分钟','3.1英寸','1677万色',1,1,'2011-03-31 11:00:00'],
        ['移动秘书','400分钟','3.2英寸','26万色',1,1,'2011-03-31 11:00:00']
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
    
     app.expander = new Ext.grid.RowExpander({
        tpl : new Ext.Template(
			 '<span align="left"><b>终端功能参数</b></span><br/><table align="left" class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr>' +
			 '<td><b>mms:</b>{useful}</td>' +
			 '<td><b>gprs:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>wap:</b>{useful}</td>' +
			 '<td><b>kjava:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>edge:</b>{useful}</td>' +
			 '<td><b>3g:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>email:</b>{useful}</td>' +
			 '<td><b>手机动画:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>streamiNg:</b>{useful}</td>' +
			 '<td><b>IMPS:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>POC:</b>{useful}</td>' +
			 '<td><b>电视:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>智能手机:</b>{useful}</td>' +
			 '<td><b>智能操作系统:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>LCD彩色:</b>{useful}</td>' +
			 '<td><b>LCD尺寸:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>内存:</b>{useful}</td>' +
			 '<td><b>振铃格式:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>多媒体格式:</b>{useful}</td>' +
			 '<td><b>摄像头(内置):</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>MP3:</b>{useful}</td>' +
			 '<td><b>音乐手机(DRM):</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>可视电话:</b>{useful}</td>' +
			 '<td><b>USSD:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>STK:</b>{useful}</td>' +
			 '<td><b>EFR:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>红外:</b>{useful}</td>' +
			 '<td><b>RS232:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>数据线:</b>{useful}</td>' +
			 '<td><b>预留1:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>预留2:</b>{useful}</td>' +
			 '<td><b>预留3:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>预留4:</b>{useful}</td>' +
			 '<td><b>预留5:</b>{useful}</td>' +
			 '</tr>' +
			 '</table><br/>',
			 '<span align="left"><b>终端业务参数</b></span><br/><table align="left" class="list" cellspacing="1" cellpadding="0" width="100%" border="0">' +
			 '<tr>' +
			 '<td><b>移动证券:</b>{useful}</td>' +
			 '<td><b>手机导航:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>手机地图:</b>{useful}</td>' +
			 '<td><b>音乐随身听:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>全曲下载:</b>{useful}</td>' +
			 '<td><b>WAP全曲下载:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>扫描上网:</b>{useful}</td>' +
			 '<td><b>名片识别:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>手机电视:</b>{useful}</td>' +
			 '<td><b>手机支付:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>手机邮箱:</b>{useful}</td>' +
			 '<td><b>飞信:</b>{useful}</td>' +
			 '</tr>' +
			 '<tr>' +
			 '<td><b>移动OA:</b>{useful}</td>' +
			 '<td><b>移动MM:</b>{useful}</td>' +
			 '</tr>' +
			 '</table>'
        )
	 });
	 
    app.cm_utp = new Ext.grid.ColumnModel([
	    app.expander,
        {header: "业务名称",width:100,sortable: true, dataIndex: 'bn'},
        {header: "蓝牙", width: 80, sortable: true, dataIndex: 'bluetooth',renderer:function(value){
        	if(value == 1){
        		return '<font color="green">支持</font>';
        	}else{
        		return '<font color="red">不支持</font>';
        	}
        }},
        {header: "GRPS", width: 80, sortable: true, dataIndex: 'camera',renderer:function(value){
        	if(value == 1){
        		return '<font color="green">支持</font>';
        	}else{
        		return '<font color="red">不支持</font>';
        	}
        }},
        {header: "摄像头", width: 80, sortable: true, dataIndex: 'camera',renderer:function(value){
        	if(value == 1){
        		return '<font color="green">支持</font>';
        	}else{
        		return '<font color="red">不支持</font>';
        	}
        }}
    ]);
    
	app.btn_search_code = new Ext.Button({
		text : '查询',
		iconCls : 'icon-search',
		handler : function(){
			app.searchcode();
		}
	});
	
	app.terminal_bussiness_add_form = new Ext.form.FormPanel({
		id:'terminal_bussiness_add_form',
		labelWidth : 60,
		labelAlign : 'right',
        frame:true,
        width:600,
		defaults : {
			msgTarget : 'side',
			anchor : '98%'
		},
		defaultType:'textfield',
		items : [
			{
		    	xtype:'fieldset',
		    	title:'业务基本属性',
				items:[
					{
						//下拉选择框
						xtype:'combo',
						fieldLabel : '业务名称',
						hiddenName:'bn',
		                valueField: 'id',
		                displayField: 'name',
		                triggerAction:'all',
		                mode: 'local',
		                store: new Ext.data.SimpleStore({
		                    fields: ['id','name'],
		                    data: [[1,'飞信'], [2,'移动MM'],[3,'手机阅读'],[4,'淘乐汇'],[5,'手机报'],[6,'号谱管家'],[7,'可视电话'],[8,'彩信相册'],[9,'移动秘书']]
		                }),
		                editable:false,
						emptyText : '请选择手机业务',
						allowBlank : false
					}
				]
			},{
		    	xtype:'fieldset',
		    	title:'终端基本属性',
				layout:'column',
				items:[
		     		{
		                columnWidth:.3,
		                layout: 'form',
		                items: [{
		                    xtype:'combo',
		                    fieldLabel: '蓝牙',
		                    width:100,
							hiddenName:'bluetooth',
//					                    name: 'bluetooth',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'支持'],[0,'不支持']]
			                }),
			                emptyText : '请选择蓝牙属性!',
			                editable:false
		                }, {
		                    xtype:'combo',
		                    fieldLabel: 'GPRS',
		                    width:100,
							hiddenName:'gprs',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'支持'],[0,'不支持']]
			                }),
			                emptyText : '请选择GRPS属性!',
			                editable:false
		                },{
		                    xtype:'combo',
		                    fieldLabel: '操作系统',
		                    width:100,
							hiddenName:'os',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'支持'],[0,'不支持'],[2,'需要后台支持']]
			                }),
			                emptyText : '请选择操作系统属性!',
			                editable:false
		                },{
		                    xtype:'combo',
		                    fieldLabel: '红外',
		                    width:100,
							hiddenName:'ir',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'支持'],[0,'不支持']]
			                }),
			                emptyText : '请选择红外属性!',
			                editable:false
		                },{
		                    xtype:'combo',
		                    fieldLabel: '数据线',
		                    width:100,
							hiddenName:'dataline',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'支持'],[0,'不支持']]
			                }),
			                emptyText : '请选择数据线属性!',
			                editable:false
		                },{
		                    xtype:'combo',
		                    fieldLabel: '可视电话',
		                    width:100,
							hiddenName:'videophone',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'支持'],[0,'不支持']]
			                }),
			                emptyText : '请选择可视电话属性!',
			                editable:false
		                },{
		                    xtype:'combo',
		                    fieldLabel: 'USSD',
		                    width:100,
							hiddenName:'ussd',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'支持'],[0,'不支持']]
			                }),
			                emptyText : '请选择USSD属性!',
			                editable:false
		                },{
		                    xtype:'combo',
		                    fieldLabel: 'STK',
		                    width:100,
							hiddenName:'stk',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'支持'],[0,'不支持']]
			                }),
			                emptyText : '请选择STK属性!',
			                editable:false
		                }]
		            },{
		                columnWidth:.3,
		                layout: 'form',
		                items: [{
		                    xtype:'combo',
		                    fieldLabel: '摄像头',
		                    width:100,
							hiddenName:'camere',
//					                    name: 'camere',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'支持'],[0,'不支持']]
			                }),
			                emptyText : '请选择摄像头属性!',
			                editable:false
		                },{
		                    xtype:'combo',
		                    fieldLabel: '内存卡',
		                    width:100,
							hiddenName:'memory',
//					                    name: 'memory',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'支持'],[0,'不支持']]
			                }),
			                emptyText : '请选择内存卡属性!',
			                editable:false
		                },{
		                    xtype:'combo',
		                    fieldLabel: '智能手机',
		                    width:100,
							hiddenName:'smartphone',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'是'],[0,'否']]
			                }),
			                emptyText : '请选择智能手机属性!',
			                editable:false
		                },{
		                    xtype:'combo',
		                    fieldLabel: '电视',
		                    width:100,
							hiddenName:'tv',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'支持'],[0,'不支持']]
			                }),
			                emptyText : '请选择电视属性!',
			                editable:false
		                },{
		                    xtype:'combo',
		                    fieldLabel: 'POC',
		                    width:100,
							hiddenName:'poc',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'支持'],[0,'不支持']]
			                }),
			                emptyText : '请选择POC属性!',
			                editable:false
		                },{
		                    xtype:'combo',
		                    fieldLabel: 'IMPS',
		                    width:100,
							hiddenName:'imps',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'支持'],[0,'不支持']]
			                }),
			                emptyText : '请选择IMPS属性!',
			                editable:false
		                },{
		                    xtype:'combo',
		                    fieldLabel: 'StreamiNG',
		                    width:100,
							hiddenName:'streaming',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'支持'],[0,'不支持']]
			                }),
			                emptyText : '请选择数据流属性!',
			                editable:false
		                },{
		                    xtype:'combo',
		                    fieldLabel: 'KJAVA',
		                    width:100,
							hiddenName:'kjava',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'支持'],[0,'不支持']]
			                }),
			                emptyText : '请选择KJAVA属性!',
			                editable:false
	               		}]
		            },{
		                columnWidth:.3,
		                layout: 'form',
		                items: [{
		                    xtype:'combo',
		                    fieldLabel: '屏幕大小',
		                    width:100,
							hiddenName:'last',
//					                    name: 'last',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'支持'],[0,'不支持']]
			                }),
			                emptyText : '请选择屏幕大小属性!',
			                editable:false
		                },{
		                    xtype:'combo',
		                    fieldLabel: 'GPS',
		                    width:100,
							hiddenName:'gps',
//					                    name: 'gps',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'支持'],[0,'不支持']]
			                }),
			                emptyText : '请选择GPS属性!',
			                editable:false
		                },{
		                    xtype:'combo',
		                    fieldLabel: 'WAP',
		                    width:100,
							hiddenName:'wap',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'支持'],[0,'不支持']]
			                }),
			                emptyText : '请选择WAP属性!',
			                editable:false
		                },{
		                    xtype:'combo',
		                    fieldLabel: 'EDGE',
		                    width:100,
							hiddenName:'edge',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'支持'],[0,'不支持']]
			                }),
			                emptyText : '请选择EDGE属性!',
			                editable:false
		                },{
		                    xtype:'combo',
		                    fieldLabel: '手机动画',
		                    width:100,
							hiddenName:'phoneanimation',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'支持'],[0,'不支持']]
			                }),
			                emptyText : '请选择手机动画属性!',
			                editable:false
		                },{
		                    xtype:'combo',
		                    fieldLabel: '电子邮件',
		                    width:100,
							hiddenName:'email',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'支持'],[0,'不支持']]
			                }),
			                emptyText : '请选择电子邮件属性!',
			                editable:false
		                },{
		                    xtype:'combo',
		                    fieldLabel: 'MP3',
		                    width:100,
							hiddenName:'mp3',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'支持'],[0,'不支持']]
			                }),
			                emptyText : '请选择MP3属性!',
			                editable:false
		                }]
		            }]
			}
		],
		buttonAlign : 'center',
		minButtonWidth : 60,
		buttons : [{
			text : '保存',
			iconCls:'icon-accept',
			handler : function(btn) {
				var frm = Ext.getCmp('terminal_bussiness_add_form').form;
				if (frm.isValid()) {
					app.add_win_new.hide();
					Ext.Msg.show({
						title : '系统提示',
						msg : '保存成功!',
						buttons : Ext.Msg.OK,
						fn:function(){
							app.ds_utp.loadData(app.data);
						},
						icon : Ext.MessageBox.INFO
					});
				}
			}
		}, {
			text : '重置',
			iconCls:'icon-arrow_refresh',
			buttonAlign : 'center',
			handler : function() {
				Ext.getCmp('terminal_bussiness_add_form').form.reset();
			}
		}, {
			text : '取消',
			iconCls:'icon-cancel',
			buttonAlign : 'center',
			handler : function() {
				Ext.getCmp('terminal_bussiness_add_form').form.reset()
				app.add_win_new.hide();
			}
		}
		]
	});
	
	app.add_win_new = new Ext.Window({
		title : '添加终终端业务关系数据',
		iconCls:'icon-add',
		width:650,
		height:420,
//		autoHeight:true,
		modal : true,
		closeAction : 'hide',
		animCollapse : true,
		pageY : 20,
		pageX : document.body.clientWidth / 2 - 420 / 2,
		animateTarget : Ext.getBody(),
		constrain : true,
		layout : 'fit',
		autoScroll: true,
		bodyStyle : 'padding:5px',
		items : app.terminal_bussiness_add_form
		
	});
	
	app.btn_add_new = new Ext.Button({
		text : '添加',
		iconCls : 'icon-add',
		handler : function(){
			app.add_win_new.show();
			app.add_win_new.setTitle('添加终终端业务关系数据');
		}
	});
	
	app.btn_add = new Ext.Button({
		text : '添加',
		iconCls : 'icon-add',
		handler : function(){
				var updateWin = new Ext.Window({
					id:'add_win',
					title : '添加业务终端关系',
					iconCls:'icon-add',
					width : 450,
					resizable : false,
					autoHeight : true,
					modal : true,
					closeAction : 'close',
					items : [
						new Ext.FormPanel({
							id:'add_form',
							labelWidth : 80,
							labelAlign : 'right',
							url : '/article/update.cgi',
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
								//下拉选择框
								xtype:'combo',
								fieldLabel : '业务名称',
								hiddenName:'article.bn',
				                valueField: 'id',
				                displayField: 'name',
				                triggerAction:'all',
				                mode: 'local',
				                store: new Ext.data.SimpleStore({
				                    fields: ['id','name'],
				                    data: [[1,'飞信'], [2,'移动MM'],[3,'手机阅读'],[4,'淘乐汇'],[5,'手机报'],[6,'号谱管家'],[7,'可视电话'],[8,'彩信相册'],[9,'移动秘书']]
				                }),
				                editable:false,
								emptyText : '请选择手机业务',
								allowBlank : false
							},
//							{
//								fieldLabel : '通话时长',
//								name : 'article.tac',
//								allowBlank : false
//							},
							{
								fieldLabel : '屏幕大小',
								name : 'article.tac',
								allowBlank : false
							},{
								fieldLabel : '屏幕颜色',
								name : 'article.tac',
								allowBlank : false
							},{
								//下拉选择框
								xtype:'combo',
								fieldLabel : '摄像头',
								hiddenName:'article.camera',
				                valueField: 'id',
				                displayField: 'name',
				                triggerAction:'all',
				                mode: 'local',
				                store: new Ext.data.SimpleStore({
				                    fields: ['id','name'],
				                    data: [[1,'支持'],[0,'不支持']]
				                }),
				                editable:false,
								emptyText : '请选择终端是否支持摄像头!',
								allowBlank : false
							},{
								//下拉选择框
								xtype:'combo',
								fieldLabel : '蓝牙',
								hiddenName:'article.bluetooth',
				                valueField: 'id',
				                displayField: 'name',
				                triggerAction:'all',
				                mode: 'local',
				                store: new Ext.data.SimpleStore({
				                    fields: ['id','name'],
				                    data: [[1,'支持'],[0,'不支持']]
				                }),
				                editable:false,
								emptyText : '请选择终端是否支持蓝牙!',
								allowBlank : false
							}],
							buttonAlign : 'center',
							minButtonWidth : 60,
							buttons : [{
								text : '添加',
								handler : function(btn) {
									var frm = Ext.getCmp('add_form').form;
									if (frm.isValid()) {
										var win = Ext.getCmp('add_win');
										win.close();
										Ext.Msg.show({
											title : '系统提示',
											msg : '添加成功!',
											buttons : Ext.Msg.OK,
											fn:function(){
												app.ds_utp.loadData(app.data);
											},
											icon : Ext.MessageBox.INFO
										});
									}
								}
							}, {
								text : '重置',
								buttonAlign : 'center',
								handler : function() {
									Ext.getCmp('add_form').form.reset();
								}
							}, {
								text : '取消',
								buttonAlign : 'center',
								handler : function() {
									var win = Ext.getCmp('add_win');
									win.close();
								}
							}]
						})
				]
			}).show();
		}
	});
	
	app.btn_edit = new Ext.Button({
		text : '修改',
		iconCls : 'icon-edit',
		handler : function(){
			if(app.grid.getSelectionModel().getSelected()){
				var record = app.grid.getSelectionModel().getSelected();
				app.terminal_bussiness_add_form.getForm().loadRecord(record);
				app.add_win_new.show();
				app.add_win_new.setTitle('修改业务和终端关系');
				/**
				var updateWin = new Ext.Window({
					id:'update_win',
					title : '修改',
					iconCls:'icon-edit',
					width : 600,
					resizable : false,
					autoHeight : true,
					modal : true,
					closeAction : 'close',
					items : [
					app.terminal_bussiness_add_form
					new Ext.FormPanel({
						id:'update_form',
						labelWidth : 80,
						labelAlign : 'right',
						url : '/article/update.cgi',
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
							//下拉选择框
							xtype:'combo',
							fieldLabel : '业务名称',
							hiddenName:'article.bn',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'飞信'], [2,'移动MM'],[3,'手机阅读'],[4,'淘乐汇'],[5,'手机报'],[6,'号谱管家'],[7,'可视电话'],[8,'彩信相册'],[9,'移动秘书']]
			                }),
			                editable:false,
							emptyText : '当前手机业务为:"'+record.get('bn')+'"',
							allowBlank : false
						},
						{
							fieldLabel : '屏幕大小',
							name : 'article.tac',
							allowBlank : false,
							value:record.get('screensize')
						},{
							fieldLabel : '屏幕颜色',
							name : 'article.tac',
							allowBlank : false,
							value:record.get('screencolor')
						},{
							//下拉选择框
							xtype:'combo',
							fieldLabel : '摄像头',
							hiddenName:'article.camera',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'支持'],[0,'不支持']]
			                }),
			                editable:false,
							emptyText : '当前'+(record.get('carmera') == 1?'支持':'不支持')+'摄像头',
							allowBlank : false
						},{
							//下拉选择框
							xtype:'combo',
							fieldLabel : '蓝牙',
							hiddenName:'article.bluetooth',
			                valueField: 'id',
			                displayField: 'name',
			                triggerAction:'all',
			                mode: 'local',
			                store: new Ext.data.SimpleStore({
			                    fields: ['id','name'],
			                    data: [[1,'支持'],[0,'不支持']]
			                }),
			                editable:false,
							emptyText : '当前'+(record.get('carmera') == 1?'支持':'不支持')+'蓝牙',
							allowBlank : false
						},{
							fieldLabel : '其他终端参',
							xtype:'textarea',
							name : 'article.remarks',
							allowBlank : false,
							value:'可扩展'
						}],
						buttonAlign : 'center',
						minButtonWidth : 60,
						buttons : [{
							text : '更新',
							handler : function(btn) {
								var frm = Ext.getCmp('update_form').form;
								if (frm.isValid()) {
									var win = Ext.getCmp('update_win');
									win.close();
									Ext.Msg.show({
										title : '系统提示',
										msg : '修改成功!',
										buttons : Ext.Msg.OK,
										fn:function(){
											app.ds_utp.loadData(app.data);
										},
										icon : Ext.MessageBox.INFO
									});
								}
							}
						}, {
							text : '重置',
							buttonAlign : 'center',
							handler : function() {
								this.ownerCt.form.reset();
							}
						}, {
							text : '取消',
							buttonAlign : 'center',
							handler : function() {
								var win = Ext.getCmp('update_win');
								win.close();
							}
						}]
					})
					]
			}).show();
					**/
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
	
	app.btn_del = new Ext.Button({
		text : '删除',
		iconCls : 'icon-del',
		handler : function(){
			var records = app.grid.getSelectionModel().getSelections();
			if(records.length == 0 ){
				Ext.Msg.show({
					title : '提示',
					msg:'请选择需要删除的记录',
					buttons : Ext.Msg.OK,
					icon : Ext.Msg.ERROR
				});
			}else{
				Ext.Msg.confirm('确认删除', '你确定删除所选记录?', function(btn) {
					if (btn == 'yes') {
						Ext.Msg.show({
							title : '提示',
							msg:'删除记录成功!',
							buttons : Ext.Msg.OK,
							fn : function() {
								app.ds_utp.loadData(app.data);
							},
							icon : Ext.Msg.INFO
						});
					}
				});
			}
		}
	});
	
	app.text_search_code = new Ext.form.TextField({
		name : 'textSearchcode',
		width : 150,
		emptyText : '该查询支持模糊查询,例如：移动MM,可以只输入MM',
		listeners : {
			'specialkey' : function(field, e) {
				if (e.getKey() == Ext.EventObject.ENTER) {
					app.searchcode();
				}
			}
		}
	});

	app.text_imei_code = new Ext.form.TextField({
		name : 'text_imei',
		width : 150,
		listeners : {
			'specialkey' : function(field, e) {
				if (e.getKey() == Ext.EventObject.ENTER) {
					app.searchcode();
				}
			}
		}
	});
	
	app.text_tac_code = new Ext.form.TextField({
		name : 'text_tac',
		width : 150,
		listeners : {
			'specialkey' : function(field, e) {
				if (e.getKey() == Ext.EventObject.ENTER) {
					app.searchcode();
				}
			}
		}
	});
	
	app.searchcode = function() {
		/**
		app.colName = app.search_comb_queyrCol_code.getValue();
		app.values =app.text_search_code.getValue();
		Ext.Ajax.request({
			url : '/utp/utp.cgi',
			params : {
				colName : app.colName,
				value : app.values
			},
			success:function(response,option){
				var obj = Ext.util.JSON.decode(response.responseText);
				if(obj.success){
					app.ds_utp.load({params : {start:0,limit:app.limit}}); //,colName:app.colName,value:app.values
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
		**/
		app.ds_utp.loadData(app.data);
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
	
	app.btn_terminal_query_components = new Ext.Button({
		text:'终端查询组件',
		iconCls:'icon-search',
		handler:function(){
			showTerminalWindow();
		}
	});
	
	//定义一个终端查询组件
	app.terminalQueryWin = new TerminalQuery({
		ds:'ds-123',
		width:650,
		height:500,
		///servlet/FormServlet http://180.168.68.82:6012/nutzd/website/root.cgi
		url:'/servlet/FormServlet',
		parentId:'terminal1_grid',
		other:null
	});
		
	var showTerminalWindow = function(){
		app.terminalQueryWin.show();
	}
	
	app.hs_bluetooth = new Ext.form.ComboBox({
				id : 'hs_bluetooth',
				hiddenName : 'colName',
				valueField : 'id',
				displayField : 'name',
				mode:'local',
				store : new Ext.data.SimpleStore({
					data : [
							['1', '支持'],
							['2','不支持']
				    ],
					fields : ['id', 'name']
				}),	
				selectOnFocus : true,
				editable : false,
				allowBlank : true,
				triggerAction : 'all',
				emptyText : '是否支持蓝牙?'
	});
	
	app.hs_camera = new Ext.form.ComboBox({
				id : 'hs_camera',
				hiddenName : 'colName',
				valueField : 'id',
				displayField : 'name',
				mode:'local',
				store : new Ext.data.SimpleStore({
					data : [
							['1', '支持'],
							['2','不支持']
				    ],
					fields : ['id', 'name']
				}),	
				selectOnFocus : true,
				editable : false,
				allowBlank : true,
				triggerAction : 'all',
				emptyText : '是否支持摄像头?'
	});
	
	app.list_bussiness = new Ext.form.ComboBox({
				id : 'list_bussiness',
				hiddenName : 'colName',
				valueField : 'id',
				displayField : 'name',
				mode:'local',
				store : new Ext.data.SimpleStore({
                    fields: ['id','name'],
                    data: [[1,'飞信'], [2,'移动MM'],[3,'手机阅读'],[4,'淘乐汇'],[5,'手机报'],[6,'号谱管家'],[7,'可视电话'],[8,'彩信相册'],[9,'移动秘书']]
				}),	
				selectOnFocus : true,
				editable : false,
				allowBlank : true,
				triggerAction : 'all',
				emptyText : '请选择业务'
	});
	
	app.ds_utp = new Ext.data.ArrayStore({
        fields: [
           {name: 'bn',type:'string'},
           {name: 'thsc',type:'string'},
           {name: 'screensize', type: 'string'},
           {name: 'screencolor', type: 'string'},
           {name: 'camera', type: 'int'},
           {name: 'bluetooth', type: 'int'},
           {name: 'time', type: 'boolean'}
        ]
    });
	
	app.ds_utp.loadData(app.data);
	
	app.ptb = new Ext.PagingToolbar({
		pageSize:app.limit,
		store:app.ds_utp,
		displayInfo:true,
		displayMsg:'第 {0} - {1} 条  共 {2} 条',
		emptyMsg:'没有记录'
	});
		
	app.grid = new Ext.grid.GridPanel({
		title : '终端业务数据管理',
		iconCls : 'icon-script_link',
		region : 'center',
		loadMask : {
			msg : '数据加载中...'
		},
	    cm: app.cm_utp,
	    ds: app.ds_utp,
//	    width:1000,
	    height:550,
        autoScroll: true,
        viewConfig: {
            forceFit:true
        },
 		plugins: app.expander,
		sm:app.sm,
		//,'-','请输入业务名称:',app.text_search_code,'-',app.hs_bluetooth,'-',app.hs_camera,'-',app.btn_search_code
		tbar : [app.btn_terminal_query_components,'-',app.btn_add_new,'-',app.btn_edit,'-',app.btn_del,'-','业务列表:',app.list_bussiness,app.btn_search_code],
		bbar : app.ptb
	});
	
	app.grid.addListener('rowdblclick',function(grid, rowIndex){
				if(grid.getSelectionModel().isSelected(rowIndex)){
					Ext.Msg.show({
						title : '系统提示',
						width:300,
						msg : '该功能正在开发!',
						buttons : Ext.Msg.OK,
						icon : Ext.MessageBox.INFO
					});
				}
	});

    app.grid.render('terminal_business');
}); 