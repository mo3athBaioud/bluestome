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
//										defaultType : 'textfield',
										labelWidth : 80,
										labelSeparator : '：',
										items : [{
													xtype:'textfield',
													fieldLabel : '帐&nbsp;号',
													iconCls : 'icon-user',
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
													xtype:'textfield',
													fieldLabel : '密&nbsp;码',
													iconCls : 'icon-key',
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
												}
												/**
												, {
													fieldLabel : '验证码',
													baseCls : 'x-plain',
													bodyStyle : 'padding:0 10px 0 0',
													layout:'column',
													items:[
														{
															columnWidth:.7,
															items:[
																{
																	width:200,
												                	xtype:'textfield',
												                	id:'checkcode',
																	name:'checkcode',
																	blankText : '请输入验证码!',
																	allowBlank : false
																}
															]
														},{
															columnWidth:.3,
															items:[{
																xtype:'panel',
																html:'<img src="/servlet/CheckCodeServlet?d'+new Date()+'" >'
															}]
														}
													]
												}
												**/
												]
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
				title : projectname,
				renderTo : Ext.getBody(),
				layout : 'fit',
				width : 460,
	//						height : 300,
				autoHeight : true,
				closeAction : 'hide',
				plain : true,
				modal : true,
				maximizable : false,
				draggable : false,
				closable : false,
				resizable : false,
				pageY : document.body.clientHeight / 2 - 460 / 2,
				pageX : document.body.clientWidth / 2 - 420 / 2,
				animateTarget : Ext.getBody(),
				items : panel,
				buttons : [{
					text : '&nbsp;登录',
					iconCls : 'acceptIcon',
					handler : function() {
						if (Ext.isIE6) {
							top.Ext.MessageBox.alert('温馨提示', projectname+'拒绝IE6客户端登录<br>我们强烈建议您立即升级IE或者切换到<b>FireFox</b>、<b>GoogleChrome</b>浏览器');
							return;
						}
						login();
					}}, {
						text : '&nbsp;选项',
						iconCls : 'icon-arrow_rotate_anticlockwise',
						menu : mainMenu
					}
				]
		});
		win.show();
			
			
			/**
			 * 提交登陆请求
			 */
			function login() {
				if (Ext.getCmp('loginForm').form.isValid()) {
					window.location.href = project+'/index.jsp';
					//临时注释掉 该部分由后台逻辑实现
					/**
					Ext.getCmp('loginForm').form.submit({
						url : project+'/login.cgi',
						waitTitle : '提示',
						method : 'POST',
						waitMsg : '正在验证您的身份,请稍候.....',
						success : function(form, action) {
							window.location.href = action.result.url;
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
								} else if(errtype == '2'){
									var form = Ext.getCmp('loginForm').form;
									form.reset();
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

/**			
			setTimeout(function() {
				if (Ext.isIE) {
					top.Ext.MessageBox.alert('温馨提示', '系统监测到您正在使用基于MSIE内核的浏览器<br>我们强烈建议你立即切换到<b>FireFox</b>或者<b>GoogleChrome</b>浏览器体验飞一般的感觉!')
				}
			}, 500);
**/
	});
