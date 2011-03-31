/**
 * 登陆页面
 * 
 * @author XiongChun
 * @since 2010-01-13
 */
Ext.onReady(function() {
			Ext.QuickTips.init();
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
										title : "登录",
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
													cls : 'user',
													name : 'username',
													id : 'account',
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
													cls : 'key',
													name : 'password',
													id : 'password',
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
									},{
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
								}
								/**
								, {
									text : '清除记忆设置(cookie)',
									iconCls : 'status_busyIcon',
									handler : function() {
										clearCookie()
									}
								}
								, {
									text : '切换到全屏模式',
									iconCls : 'imageIcon',
									handler : function() {
										window.location.href = './fullScreen.jsp';
									}
								}**/
								
								]
					});

			var win = new Ext.Window({
						title : '西安移动终端业务支持平台(西安泰讯信息技术技术有限公司测试版)',
						renderTo : Ext.getBody(),
						layout : 'fit',
						width : 460,
						height : 300,
						closeAction : 'hide',
						plain : true,
						modal : true,
//						collapsible : true,
//						titleCollapse : true,
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
										/**
										if (Ext.isIE6) {
											top.Ext.MessageBox.alert('温馨提示', 'G4演示系统拒绝IE6客户端登录<br>我们强烈建议您立即升级IE或者切换到<b>FireFox</b>、<b>GoogleChrome</b>浏览器');
											return;
										}
										**/
										login();
									}
								}, {
									text : '&nbsp;选项',
									iconCls : 'tbar_synchronizeIcon',
									menu : mainMenu
								}]
					});
			win.show();	
			/**
			var account = Ext.getCmp('loginForm').findById('account');
			win.on('show', function() {
						setTimeout(function() {
									account.focus();
								}, 200);
					}, this);
			**/
			/**
			 * 提交登陆请求
			 */
			function login() {
				if (Ext.getCmp('loginForm').form.isValid()) {
					window.location.href = "./index.html";
					/**
					Ext.getCmp('loginForm').form.submit({
								url : 'login.cgi',
								waitTitle : '提示',
								method : 'POST',
								waitMsg : '正在验证您的身份,请稍候.....',
								success : function(form, action) {
									var loginResult = action.result.success;
									window.location.href = 'index.jsp';
								},
								failure : function(form, action) {
									var errmsg = action.result.msg;
									var errtype = action.result.errorType;
									Ext.Msg.alert('提示', errmsg, function() {
												if (errtype == '1') {
													Ext.getCmp('loginForm').form.reset();
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
							**/
				}
			}
			/**
			 * 清除当前输入
			 */
			function clearCurrentInput() {
				var form = Ext.getCmp('loginForm').form;
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
//			if (runMode == '0') {
//				Ext.getCmp('account').setValue('developer');
//				Ext.getCmp('password').setValue('111111');
//			}

/**			
			setTimeout(function() {
				if (Ext.isIE) {
					top.Ext.MessageBox.alert('温馨提示', '系统监测到您正在使用基于MSIE内核的浏览器<br>我们强烈建议你立即切换到<b>FireFox</b>或者<b>GoogleChrome</b>浏览器体验飞一般的感觉!')
				}
			}, 500);
**/
	});
