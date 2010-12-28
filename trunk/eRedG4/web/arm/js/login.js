/**
 * 登陆页面
 * 
 * @author XiongChun
 * @since 2010-01-13
 */
Ext.onReady(function() {
			var panel = new Ext.Panel({
						el : 'hello-tabs',
						autoTabs : true,
						deferredRender : false,
						border : false,
						items : {
							xtype : 'tabpanel',
							activeTab : 0,
							height : 180,
							border : false,
							items : [{
										title : "身份认证",
										xtype : 'form',
										id : 'loginForm',
										defaults : {
											width : 260
										},
										bodyStyle : 'padding:20 0 0 50',
										defaultType : 'textfield',
										labelWidth : 40,
										labelSeparator : '：',
										items : [{
													fieldLabel : '帐&nbsp;号',
													name : 'account',
													id : 'account',
													cls : 'user',
													blankText : '帐号不能为空,请输入!',
													maxLength : 20,
													maxLengthText : '账号的最大长度为20个字符',
													allowBlank : false,
													listeners : {
														specialkey : function(field, e) {
															if (e.getKey() == Ext.EventObject.ENTER) {
																Ext.getCmp('password').focus();
															}
														}
													}
												}, {
													fieldLabel : '密&nbsp;码',
													name : 'password',
													id : 'password',
													cls : 'key',
													inputType : 'password',
													blankText : '密码不能为空,请输入!',
													maxLength : 20,
													maxLengthText : '密码的最大长度为20个字符',
													allowBlank : false,
													listeners : {
														specialkey : function(field, e) {
															if (e.getKey() == Ext.EventObject.ENTER) {
																login();
															}
														}
													}
												}]
									}, {
										title : '信息公告栏',
										contentEl : 'infoDiv',
										defaults : {
											width : 230
										}
									}, {
										title : '关于',
										contentEl : 'aboutDiv',
										defaults : {
											width : 230
										}
									}]
						}
					});

			// 清除按钮上下文菜单
			var mainMenu = new Ext.menu.Menu({
						id : 'mainMenu',
						items : [{
									text : '清除当前输入',
									iconCls : 'status_awayIcon',
									handler : function() {
										clearCurrentInput()
									}
								}, {
									text : '清除记忆设置(cookie)',
									iconCls : 'status_busyIcon',
									handler : function() {
										clearCookie()
									}
								}, {
									text : '切换到全屏模式',
									iconCls : 'imageIcon',
									handler : function() {
										window.location.href = './fullScreen.jsp';
									}
								}]
					});

			var win = new Ext.Window({
						title : 'eRedG4&reg - 易道系统集成与应用开发平台V1.03',
						renderTo : Ext.getBody(),
						layout : 'fit',
						width : 460,
						height : 300,
						closeAction : 'hide',
						plain : true,
						modal : true,
						collapsible : true,
						titleCollapse : true,
						maximizable : false,
						draggable : false,
						closable : false,
						resizable : false,
						animateTarget : document.body,
						items : panel,
						buttons : [{
									text : '&nbsp;登录',
									iconCls : 'acceptIcon',
									handler : function() {
										if (Ext.isIE6) {
											top.Ext.MessageBox.alert('温馨提示', 'G4演示系统拒绝IE6客户端登录<br>我们强烈建议您立即升级IE或者切换到<b>FireFox</b>、<b>GoogleChrome</b>浏览器');
											return;
										}
										login();
									}
								}, {
									text : '&nbsp;选项',
									iconCls : 'tbar_synchronizeIcon',
									menu : mainMenu
								}]
					});
			win.show();
			var account = Ext.getCmp('loginForm').findById('account');
			win.on('show', function() {
						setTimeout(function() {
									account.focus();
								}, 200);
					}, this);

			var addUserFormPanel = new Ext.form.FormPanel({
						id : 'addUserFormPanel',
						name : 'addUserFormPanel',
						defaultType : 'textfield',
						labelAlign : 'right',
						labelWidth : 65,
						frame : true,
						items : [{
									fieldLabel : '登录帐户',
									name : 'account',
									id : 'account',
									allowBlank : false,
									anchor : '99%'
								}, {
									fieldLabel : '姓名/昵称',
									name : 'username',
									id : 'username',
									allowBlank : false,
									anchor : '99%'
								}, {
									fieldLabel : '密码',
									name : 'password',
									id : 'password',
									inputType : 'password',
									allowBlank : false,
									anchor : '99%'
								}, {
									fieldLabel : '确认密码',
									name : 'password1',
									id : 'password1',
									inputType : 'password',
									allowBlank : false,
									anchor : '99%'
								}]
					});

			var addUserWindow = new Ext.Window({
						layout : 'fit',
						width : 400,
						height : 310,
						resizable : false,
						draggable : true,
						closeAction : 'hide',
						title : '注册新用户',
						iconCls : 'page_addIcon',
						modal : false,
						collapsible : true,
						titleCollapse : true,
						maximizable : false,
						buttonAlign : 'right',
						border : false,
						animCollapse : true,
						pageY : 20,
						pageX : document.body.clientWidth / 2 - 420 / 2,
						animateTarget : Ext.getBody(),
						constrain : true,
						items : [addUserFormPanel],
						buttons : [{
									text : '保存',
									iconCls : 'acceptIcon',
									handler : function() {
										if (runMode == '0') {
											Ext.Msg.alert('提示', '系统正处于演示模式下运行,您的操作被取消!该模式下只能进行查询操作!');
											return;
										}
										var mode = Ext.getCmp('windowmode').getValue();
										if (mode == 'add')
											saveUserItem();
										else
											updateUserItem();
									}
								}, {
									text : '重置',
									id : 'btnReset',
									iconCls : 'tbar_synchronizeIcon',
									handler : function() {
										clearForm(addUserFormPanel.getForm());
									}
								}, {
									text : '关闭',
									iconCls : 'deleteIcon',
									handler : function() {
										addUserWindow.hide();
									}
								}]
					});

			/**
			 * 提交登陆请求
			 */
			function login() {
				if (Ext.getCmp('loginForm').form.isValid()) {
					Ext.getCmp('loginForm').form.submit({
								url : 'login.ered?reqCode=login',
								waitTitle : '提示',
								method : 'POST',
								waitMsg : '正在验证您的身份,请稍候.....',
								success : function(form, action) {
									var loginResult = action.result.success;
									window.location.href = 'index.ered?reqCode=indexInit';
								},
								failure : function(form, action) {
									var errmsg = action.result.msg;
									var errtype = action.result.errorType;
									Ext.Msg.alert('提示', errmsg, function() {
												if (errtype == '1') {
													win.getComponent('loginForm').form.reset();
													var account = Ext.getCmp('loginForm').findById('account');
													account.focus();
													account.validate();
												} else {
													var password = Ext.getCmp('loginForm').findById('password');
													password.focus();
													password.setValue('');
												}
											});
								}
							});
				}
			}
			/**
			 * 清除当前输入
			 */
			function clearCurrentInput() {
				var form = win.getComponent('loginForm').form;
				form.reset();
				var account = Ext.getCmp('loginForm').findById('account');
				account.focus();
			}

			/**
			 * 清除当前输入
			 */
			function clearCookie() {
				alert('清除Cookie!');
			}

			// 演示帐户
			if (runMode == '0') {
				Ext.getCmp('account').setValue('developer');
				Ext.getCmp('password').setValue('111111');
			}

			setTimeout(function() {
						if (Ext.isIE) {
							top.Ext.MessageBox.alert('温馨提示', '系统监测到您正在使用基于MSIE内核的浏览器<br>我们强烈建议你立即切换到<b>FireFox</b>或者<b>GoogleChrome</b>浏览器体验飞一般的感觉!')
						}
					}, 500);
		});
