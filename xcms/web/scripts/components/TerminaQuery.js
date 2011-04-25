/**
 * 终端查询窗口
 * 由于终端参数众多，在Grid顶部栏放不下所有条件，故将该类查询集成到一个单独的查询窗口中。
 */
TerminalQuery =  Ext.extend(Ext.Window, {
	constructor : function(_cfg) { //构造函数
		TerminalQuery.superclass.constructor.call(this,  Ext.applyIf(_cfg || {},{
			id:'terminal_query_win',
			title:'终端查询组件',
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
			items:[
				new Ext.form.FormPanel({
					id:'terminal_query_form',
					labelWidth : 60,
					labelAlign : 'right',
			        frame:true,
			        url:_cfg.url,
			        width:_cfg.width,
					defaults : {
						msgTarget : 'side',
						type:'combo',
						anchor : '98%'
					},
					defaultType:'textfield',
				    items : [
				    { 
				    	xtype:'fieldset',
				    	title:'手机基本属性',
						layout:'column',
						items:[
							{
								columnWidth:.3,
								id:'terminal_model_pic',
								html:'<p><img src="/images/nopic.jpg" alt="手机图片" width="120"  height="90" /></p>'
							},{
								columnWidth:.3,
								layout: 'form',
								items:[
								{
					                    xtype:'combo',
					                    fieldLabel: '手机品牌',
					                    width:100,
										hiddenName:'terminal.hsbrand',
//					                    name: 'hsbrand',
						                valueField: 'id',
						                displayField: 'name',
						                triggerAction:'all',
						                mode: 'local',
						                store: new Ext.data.SimpleStore({
						                    fields: ['id','name'],
						                    data: [[1,'诺基亚'], [2,'摩托罗拉'],[3,'联想']]
						                }),
						                listeners:{
						                	select:function(){
						                		try{
						                			var parent=Ext.getCmp('hs_model_combo');
						                			//重载手机型号数据  这个是条件{params:{wiseID:this.value}}
						                			parent.enable();
//						                			parent.store.reload();
						                		}catch(ex){
						                			Ext.MessageBox.alert(ex);
						                		}
						                	}
						                },
						                emptyText : '请选择手机品牌!',
						                editable:false
					                },{
					                	id:'hs_model_combo',
					                    xtype:'combo',
					                    fieldLabel: '手机型号',
					                    disabled:true,
					                    width:100,
										hiddenName:'hsmodel',
//					                    name: 'hsmodel',
						                valueField: 'id',
						                displayField: 'name',
						                triggerAction:'all',
						                mode: 'local',
						                store: hs_model_ds,
						                loadingText :'正在请求数据,请稍后',
						                emptyText : '请选择手机型号!',
						                editable:false,
						                listeners:{
						                	select:function(){
						                		try{
						                			var pic = document.getElementById('terminal_model_pic');
						                			pic.innerHTML = '<p><img src="http://img5.pcpop.com/ProductImages/Standard/4/4287/004287752.jpg" alt="手机图片"/></p>';
						                		}catch(ext){
						                			Ext.MessageBox.alert(ex);
						                		}
						                	}
						                }
					                },{
					                	xtype:'textfield',
					                	width:100,
					                	fieldLabel: '手机价格',
										name:'price',
					                	emptyText : '请输入价格!'
					                }
								]
							},{
								columnWidth:.3,
								layout:'form',
								items:[{
					                    xtype:'combo',
					                    fieldLabel: '其他属性',
					                    width:100,
										hiddenName:'other',
//					                    name: 'other',
						                valueField: 'id',
						                displayField: 'name',
						                triggerAction:'all',
						                mode: 'local',
						                store: new Ext.data.SimpleStore({
						                    fields: ['id','name'],
						                    data: [[1,'支持'],[0,'不支持']]
						                }),
					                    emptyText : '请选择其他属性!',
						                editable:false
				                },{
					                    xtype:'combo',
					                    fieldLabel: '3G',
					                    width:100,
										hiddenName:'3g',
//					                    name: 'bluetooth',
						                valueField: 'id',
						                displayField: 'name',
						                triggerAction:'all',
						                mode: 'local',
						                store: new Ext.data.SimpleStore({
						                    fields: ['id','name'],
						                    data: [[1,'是'],[0,'不是']]
						                }),
						                emptyText : '请选择3G属性!',
						                editable:false
				                }]
							}
						]
				    },
				    {
				    		xtype: 'fieldset',
				    		title: '终端硬件条件',
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
					            }
					     	]
				     	}
				     ]
			    })
//			    ,_cfg.other
			],
			buttonAlign : 'center',
			minButtonWidth : 60,
		 	buttons: [
		 		{
		 			text:'查询',
		 			iconCls:'icon-search',
		 			handler:function(btn){
		 				var frm =Ext.getCmp('terminal_query_form').form;
				  		frm.submit({
				  				url:_cfg.url,
				  				method:'GET',
				  				waitTitle : '请稍候',
								waitMsg : '正在提交表单数据,请稍候...',
								success : function(form, action) {
							  		btn.enable();
									Ext.MessageBox.alert("成功！", action.result.msg,function(){
										terminalReset();
									},this);
					 				if(null != _cfg.parentId){
					 					var grid = Ext.getCmp(_cfg.parentId);
					 					if(null != grid){
						 					var store = grid.getStore();
						 					if(null != store){
						 						store.load();
//												Ext.MessageBox.alert("提示", obj.msg,function(){
//							 						store.load();
//													frm.reset();
//												},this);
						 					}
					 					}
					 				}
									frm.reset();
								},
								failure : function(form,action) {
									btn.enable();
									Ext.Msg.show({
										title : '错误提示',
										msg : action.result.msg,
										buttons : Ext.Msg.OK,
										icon : Ext.Msg.ERROR
									})
								}
				  		});
		 				/**
						Ext.Ajax.request({
							url : _cfg.url,
							success:function(response,option){
								var obj = Ext.util.JSON.decode(response.responseText);
								alert(obj);
								if(obj.success){
					 				if(null != _cfg.parentId){
					 					var grid = Ext.getCmp(_cfg.parentId);
					 					if(null != grid){
						 					var store = grid.getStore();
						 					if(null != store){
						 						terminalReset();
												Ext.MessageBox.alert("成功！", obj.msg);
						 						store.load();
												frm.reset();
						 					}
					 					}
					 				}
								}else{
									Ext.MessageBox.alert('提示', obj.msg);
								}
							},
							failure : function(form,action) {
								Ext.Msg.show({
									title : '错误提示',
									msg : '服务器内部错误',
									buttons : Ext.Msg.OK,
									icon : Ext.Msg.ERROR
								})
							}
						});
						**/
		 			}
		 		},{
		 			text:'重置',
		 			iconCls:'icon-arrow_refresh',
		 			handler:function(){
	 					//重置表单数据
	 					var form = Ext.getCmp('terminal_query_form');
	 					form.getForm().reset();
	 					//将手机型号设置为不可选
	 					Ext.getCmp('hs_model_combo').disable();
		 			}
		 		},{
		 			text:'取消',
		 			iconCls:'icon-cancel',
		 			handler:function(){
		 				terminalReset();
		 			}
		 		}
		 	]
		}));
	}
});

var hs_model_ds = new Ext.data.SimpleStore({
    fields: ['id','name'],
    data: [[1,'C7'],[2,'C5'],[3,'5230'],[4,'MB525 Defy'],[5,'ME525'],[6,'XT502'],[7,'S708'],[8,'S710'],[9,'TD80t'],[10,'EX128']]
});

var terminalReset = function(){
		//重置表单数据
		Ext.getCmp('terminal_query_form').form.reset();
		
		//设置手机型号下拉框不可选
		if(!Ext.getCmp('hs_model_combo').disable()){
			alert('terminalReset is not disable');
			Ext.getCmp('hs_model_combo').disable();
		}
		
		//隐藏终端查询窗口
		var win = Ext.getCmp('terminal_query_win');
		win.hide();
		
		//重置图片
		var pic = document.getElementById('terminal_model_pic');
		pic.innerHTML = '<p><img src="/images/nopic.jpg" alt="手机图片" width="120"  height="90" /></p>';
}
