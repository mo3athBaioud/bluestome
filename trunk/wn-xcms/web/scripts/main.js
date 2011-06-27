//Ext.BLANK_IMAGE_URL = 'resources/images/default/s.gif';
//Ext.QuickTips.init();
//var start = {
//	id : 'start-panel',
//	title : '图片资源管理系统',
//	layout : 'fit',
//	bodyStyle : 'padding:25px',
//	html : '<img src=images/bg.jpg />'
//};

Ext.onReady(function() {
	Ext.QuickTips.init();
	setTimeout(function() {
		Ext.get('loading').remove();
		Ext.getDom('header').style.visibility = 'visible';
		var vp = new Ext.Viewport({
			layout : 'border',
			defaults : {
				collapsible : true,
				split : true
			},
			items : [{
				xtype : 'box',
				region : 'north',
				applyTo : 'header',
				height : 30,
				split : false
			}, {
				title : '后台管理',
				id : 'accordion-panel',
				layout : 'border',
				region : 'west',
				margins : '2 0 5 5',
				width : 200,
				minSize : 200,
				maxSize : 250,
				collapsible:true,//是否让panel能自动缩放
		    	collapseMode:'mini',//在分割线处出现按钮
				bodyStyle : 'background-color:#DFE8F6',
				defaults : {
					border : false
				},
				bbar : [{
					text : '开始',
					iconCls : 'icon-plugin',
					menu : new Ext.menu.Menu({
						items : [
						{
							text : '修改密码',
							iconCls : 'icon-info',
							handler : function() {
								new Ext.Window({
									id:'modityPwd_window',
									title : '修改密码',
									width : 300,
									height : 150,
									resizable : false,
									modal : true,
									closeAction : 'close',
									items:[
										new Ext.FormPanel({
										id:'passwordForm',
										labelWidth : 70,
										labelAlign : 'right',
										url : 'modityPwd.action',
										border : false,
										baseCls : 'x-plain',
										bodyStyle : 'padding:5px 5px 0',
										defaults : {
											anchor : '80%',
											msgTarget : 'side'
										},
										defaultType : 'textfield',
										items : [
											{
												fieldLabel : '原始密码',
												id : 'npassword',
												name : 'oldPwd',
												inputType : 'password',
												vtype:'safe',
												allowBlank:false,
												maxLength : 50
											}, {
												fieldLabel:'密码',
												id:'pwd',
												name:'newPwd',
												inputType:'password',
												vtype:'safe',
												allowBlank:false,
												maxLength:20
											}, {
												fieldLabel:'确认密码',
												inputType:'password',
												name:'confirmPwd',
												allowBlank:false,
												vtype:'password',
												initialPassField: 'pwd', // 要比较的另外一个的组件的id
												maxLength:20
											}										
										],
										buttonAlign : 'right',
										minButtonWidth : 60,
										buttons : [{
											text : '修改密码',
											handler : function(btn) {
												if (Ext.getCmp('passwordForm').form.isValid()) {
													Ext.Msg.show({
														title : '提示',
														msg : '修改密码成功!',
														buttons : Ext.Msg.OK,
														icon : Ext.Msg.INFO
													});
												/**
													frm.submit({
														waitTitle : '请稍候',
														waitMsg : '正在提交表单数据,请稍候...',
														success : function(form, action) {
																Ext.MessageBox.alert('提示',action.result.msg);
																var modityPwd_window = Ext.getCmp('modityPwd_window');
																frm.reset();
																modityPwd_window.close();
														},
														failure : function(form,action) {
															Ext.Msg.show({
																title : '提示',
																msg : action.result.msg,
																buttons : Ext.Msg.OK,
																icon : Ext.Msg.ERROR
														})
														}
													})
													**/
												}
											}
										}, {
											text : '重置密码',
											handler : function() {
												var form = Ext.getCmp('passwordForm').getForm();
												form.reset();
//												this.ownerCt.form.reset();
											}
										}, {
											text : '取消修改',
											handler : function() {
												Ext.getCmp('modityPwd_window').close();
//												this.ownerCt.ownerCt.close();
											}
										}]										
									})
									]
								}).show();
							}
						}, 
						{
							text : '关于系统',
							iconCls : 'icon-info',
							handler : function() {
								new Ext.Window({
									closeAction : 'close',
									resizable : false,
									bodyStyle : 'padding: 7',
									modal : true,
									title : '关于本系统',
									html : '<p><center>西安移动终端业务支撑平台1.0<center></p>',
									width : 300,
									height : 200,
									item:[]
								}).show();
							}
						}, 
						{
							text : '退出系统',
							iconCls : 'icon-delete',
							handler : function() {
								Ext.Msg.confirm('操作提示', '您确定要退出本系统?', function(btn) {
									if ('yes' == btn) {
										window.location.href = "./login.html";
										/**
										Ext.Ajax.request({
											url : 'logout.action',
											success : function() {
												location = './login.jsp';
											},
											failure : function() {
												Ext.Msg.show({
													title : '提示',
													msg : '退出系统失败!',
													icon : Ext.Msg.ERROR,
													buttons : Ext.Msg.OK
												});
											}
										});
										**/
									}
								});
							}
						}]
					})
				}],
				items : [{
					layout : 'accordion',
					region : 'center',
					items : [{
						title : '终端支撑',
						iconCls : 'icon-nav',
						border : false,
						autoScroll:true, 
		                enableDD:false,
		                containerScroll: true,
						items : [{
							id:'website_tree_id',
							xtype : 'treepanel',
							border : false,
							rootVisible : true,
							loader : new Ext.tree.TreeLoader({
								dataUrl : './tree/1.json'
							}),
							root : new Ext.tree.AsyncTreeNode({
								id:'0',
								text:'菜单',
								hrefTarget:'mainFrame'
							}), 
							listeners : {
								'click' : function(n) {
									try {
										var sn = this.selModel.selNode || {};
										if (n.leaf && n.id != sn.id) {
											Ext.getCmp('content-panel').layout.setActiveItem(n.id.substring(0, n.id
													.indexOf('-'))
													+ '-panel');
										}
									} catch (e) {
									}
								}
							}
						}]
					}
					/**
					,{
						title : '个人设置',
						iconCls : 'icon-nav',
						border : false,
						autoScroll:true, 
		                enableDD:false,
		                containerScroll: true,
						items : [{
							id:'user_tree_id',
							xtype : 'treepanel',
							border : false,
							rootVisible : true,
							loader : new Ext.tree.TreeLoader({
								dataUrl : './tree/2.json'
							}),
							root : new Ext.tree.AsyncTreeNode({
								id:'0',
								text:'菜单',
								hrefTarget:'mainFrame'
							}), 
							listeners : {
								'click' : function(n) {
									try {
										var sn = this.selModel.selNode || {};
										if (n.leaf && n.id != sn.id) {
											Ext.getCmp('content-panel').layout.setActiveItem(n.id.substring(0, n.id
													.indexOf('-'))
													+ '-panel');
										}
									} catch (e) {
									}
								}
							}
						}]
					}
					**/
					]
				}]
			}, {
				id : 'content-panel',
				region : 'center',
				layout : 'card',
				autoHeight : true,
				margins : '2 5 5 0',
				activeItem : 5,
				border : false,
				html:'<iframe name="mainFrame" width="98%" height="800" id="mainFrame" frameborder=0 src="./info.jsp"></iframe>'					
			}]
		});
	}, 250);
});
