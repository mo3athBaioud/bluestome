Ext.onReady(function() {
			var themeButton = new Ext.Button({
						text : '主题',
						iconCls : 'themeIcon',
						iconAlign : 'left',
						scale : 'medium',
						width : 50,
						tooltip : '<span style="font-size:12px">切换系统主题主题样式</span>',
						pressed : true,
						arrowAlign : 'right',
//						renderTo : 'themeDiv',
						handler : function() {
							themeWindowInit();
						}
			});

//			var configButton = new Ext.Button({
//						text : '首选项',
//						iconCls : 'config2Icon',
//						iconAlign : 'left',
//						scale : 'medium',
//						width : 50,
//						tooltip : '<span style="font-size:12px">首选项菜单</span>',
//						pressed : true,
//						renderTo : 'configDiv'
//					});

			var closeButton = new Ext.Button({
				// text : '退出',
				iconCls : 'cancel_48Icon',
				iconAlign : 'left',
				scale : 'medium',
				width : 30,
				tooltip : '<span style="font-size:12px">切换用户,安全退出系统</span>',
				pressed : true,
				arrowAlign : 'right',
				renderTo : 'closeDiv',
				handler : function() {
					Ext.Msg.confirm('提示', '你确退出系统吗?', function(btn) {
						if (btn == 'yes') {
							Ext.Ajax.request({
								url : project+'/servlet/LogoutServlet',
								params : {
								},
								success:function(response,option){
									var obj = Ext.util.JSON.decode(response.responseText);
									if(obj.success){
										/**
										Ext.Msg.show({
											title : '系统提示',
											msg : obj.msg,
											buttons : Ext.Msg.OK,
											fn:function(){
												window.location.href = '/login2.html';
											},
											icon : Ext.MessageBox.INFO
										});
										**/
										window.location.href = project+'/login3.jsp';
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
						}
					});
				}
			});

			var root = new Ext.tree.TreeNode({
				text : '根节点',
				id : '00'
			});
			var node01 = new Ext.tree.TreeNode({
				text : '蓝色妖姬',
				theme : 'default',
				id : '01'
			});
			var node02 = new Ext.tree.TreeNode({
						text : '粉红之恋',
						theme : 'lightRed',
						id : '02'
					});
			var node03 = new Ext.tree.TreeNode({
						text : '金碧辉煌',
						theme : 'lightYellow',
						id : '03'
					});
			var node04 = new Ext.tree.TreeNode({
						text : '钢铁战士',
						theme : 'gray',
						id : '04'
					});
			var node05 = new Ext.tree.TreeNode({
						text : '绿水青山',
						theme : 'lightGreen',
						id : '05'
					});
			var node06 = new Ext.tree.TreeNode({
						text : '紫色忧郁',
						theme : 'purple2',
						id : '06'
					});
			root.appendChild(node01);
			root.appendChild(node02);
			root.appendChild(node03);
			root.appendChild(node04);
			root.appendChild(node05);
			root.appendChild(node06);
			var themeTree = new Ext.tree.TreePanel({
						autoHeight : false,
						autoWidth : false,
						autoScroll : false,
						animate : false,
						rootVisible : false,
						border : false,
						containerScroll : true,
						applyTo : 'themeTreeDiv',
						root : root
					});
			themeTree.expandAll();
			themeTree.on('click', function(node) {
						var theme = node.attributes.theme;
						var o = document.getElementById('previewDiv');
						o.innerHTML = '<img src="'+project+'/resource/image/theme/' + theme + '.jpg" />';
			});

			var previewPanel = new Ext.Panel({
						region : 'center',
						title : '<span style="font-weight:normal">主题预览</span>',
						margins : '3 3 3 0',
						activeTab : 0,
						defaults : {
							autoScroll : true
						},
						contentEl : 'previewDiv'
					});

			var themenav = new Ext.Panel({
						title : '<span style="font-weight:normal">主题列表</span>',
						region : 'west',
						split : true,
						width : 120,
						minSize : 120,
						maxSize : 150,
						collapsible : true,
						margins : '3 0 3 3',
						// cmargins : '3 3 3 3',
						contentEl : 'themeTreeDiv',
						bbar : [{
									text : '保存',
									iconCls : 'acceptIcon',
									handler : function() {
										if (runMode == '0') {
											Ext.Msg.alert('提示', '系统正处于演示模式下运行,您的操作被取消!该模式下只能进行查询操作!');
											return;
										}
										var o = themeTree.getSelectionModel().getSelectedNode();
										saveUserTheme(o);
									}
								}, '->', {
									text : '关闭',
									iconCls : 'deleteIcon',
									handler : function() {
										themeWindow.hide();
									}
								}]
					});

			var themeWindow = new Ext.Window({
						title : '主题设置',
						closable : true,
						width : 500,
						height : 350,
						closeAction : 'hide',
						iconCls : 'theme2Icon',
						collapsible : true,
						titleCollapse : true,
						border : true,
						maximizable : false,
						resizable : false,
						// border:false,
						plain : true,
						layout : 'border',
						items : [themenav, previewPanel]
					});

			/**
			 * 主题窗口初始化
			 */
			function themeWindowInit() {
				for (i = 0; i < root.childNodes.length; i++) {
					var child = root.childNodes[i];
					if (default_theme == child.attributes.theme) {
						child.select();
					}
				}
				var o = document.getElementById('previewDiv');
				o.innerHTML = '<img src="'+project+'/resource/image/theme/' + default_theme + '.jpg" />';
				themeWindow.show();

			}

			/**
			 * 保存用户自定义主题
			 */
			function saveUserTheme(o){
				Ext.MessageBox.confirm('请确认', '您选择的[' + o.text + ']主题保存成功,立即应用该主题吗?<br>提示：页面会被刷新,请先确认是否有尚未保存的业务数据,以免丢失!', function(btn, text) {
					if (btn == 'yes') {
//						showWaitMsg('正在为您应用主题...');
//						location.reload();
					    var cookie = readCookie("style");
					    var title = cookie ? cookie : getPreferredStyleSheet();
					    setActiveStyleSheet(title);
					} else {
						Ext.Msg.alert('提示', '请在任何时候按[F5]键刷新页面或者重新登录系统以启用[' + o.text + ']主题!', function() {
									themeWindow.hide();
						});

					}
				});
				/**
				showWaitMsg();
				Ext.Ajax.request({
					url : './index.ered?reqCode=saveUserTheme',
					success : function(response) {
						var resultArray = Ext.util.JSON.decode(response.responseText);
						Ext.MessageBox.confirm('请确认', '您选择的[' + o.text + ']主题保存成功,立即应用该主题吗?<br>提示：页面会被刷新,请先确认是否有尚未保存的业务数据,以免丢失!', function(btn, text) {
									if (btn == 'yes') {
										showWaitMsg('正在为您应用主题...');
										location.reload();
									} else {
										Ext.Msg.alert('提示', '请在任何时候按[F5]键刷新页面或者重新登录系统以启用[' + o.text + ']主题!', function() {
													themeWindow.hide();
												});

									}
								});
					},
					failure : function(response) {
						var resultArray = Ext.util.JSON.decode(response.responseText);
						Ext.Msg.alert('提示', '数据保存失败');
					},
					params : {
						theme : o.attributes.theme
					}
				});
				**/
			}
			
			function readCookie(name) {
			    var nameEQ = name + "=",
			    ca = document.cookie.split(';'),
			    i,
			    c,
			    len = ca.length;
			for ( i = 0; i < len; i++) {
			    c = ca[i];
			    while (c.charAt(0) == ' ') {
			            c = c.substring(1, c.length);
			        }
			        if (c.indexOf(nameEQ) == 0) {
			            return c.substring(nameEQ.length, c.length);
			        }
			    }
			    return null;
			}
			
			function setActiveStyleSheet(title) {
			    var i,
			        a,
			        links = document.getElementsByTagName("link"),
			        len = links.length;
			    for (i = 0; i < len; i++) {
			        a = links[i];
			        if (a.getAttribute("rel").indexOf("style") != -1 && a.getAttribute("title")) {
			            a.disabled = true;
			            if (a.getAttribute("title") == title) a.disabled = false;
			        }
			    }
			}
			
			function getPreferredStyleSheet() {
			    var i,
			        a,
			        links = document.getElementsByTagName("link"),
			        len = links.length;
			    for (i = 0; i < len; i++) {
			        a = links[i];
			        if (a.getAttribute("rel").indexOf("style") != -1 && a.getAttribute("rel").indexOf("alt") == -1 && a.getAttribute("title")) {
			            return a.getAttribute("title");
			        }
			    }
			    return null;
			}
			
			function createCookie(name, value, days) {
			    if (days) {
			        var date = new Date();
			        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
			        var expires = "; expires=" + date.toGMTString();
			    } else {
			        expires = "";
			    }
			    document.cookie = name + "=" + value + expires + "; path=/";
			}
							
		});
