Common = function() {
	return {
		comboBox : function(id, store, fieldLabel, width, isAllowBlank) {
			return new Ext.form.ComboBox({
						store : store,
						width : width,
						emptyText : '请选择',
						allowBlank : isAllowBlank,
						hiddenName : id,
						fieldLabel : fieldLabel,
						mode : 'local',
						triggerAction : 'all',
						valueField : 'code',
						displayField : 'name'
					});
		},
		textField : function(id, fieldLabel, width, isAllowBlank) {
			return new Ext.form.TextField({
						width : width,
						id : id,
						fieldLabel : fieldLabel,
						allowBlank : isAllowBlank
					});
		},
		searchBar : function(height, items, func) {
			return new Ext.FormPanel({
						region : 'north',
						labelWidth : 40,
						labelAlign : 'right',
						frame : false,
						border : true,
						bodyStyle : 'padding:5px 5px 0',
						layout : 'column',
						height : height,
						items : items,
						keys : {
							key : Ext.EventObject.ENTER,
							fn : func
						}
					});
		},
		sendJsonArray : function(jsonArray, url, store) {
			Ext.lib.Ajax.request('POST', url, {
						success : function(response) {
							Ext.example.msg('信息', response.responseText);
							store.reload();
						},
						failure : function() {
							Ext.Msg.alert("错误", '与后台联系时出问题');
						}
					}, 'data=' + encodeURIComponent(Ext.encode(jsonArray)));
		},
		getMsg : function(url) {
			Ext.lib.Ajax.request('POST', url, {
						success : function(response) {
							return response.responseText;
						},
						failure : function() {
							return '获取失败';
						}
					});
		},
		syncRequest : function(url) {
			var conn = Ext.lib.Ajax.getConnectionObject().conn;
			conn.open("GET", url, false);
			conn.send(null);
			return conn.responseText;
		},
		addWindow : function(url, recordItems, store, width, height,
				isFileUpload) {
			// 添加条目的窗口和表单
			var addForm = new Ext.form.FormPanel({
						labelAlign : 'right',
						frame : true,
						// method : 'POST',
						url : url,
						fileUpload : isFileUpload,
						items : recordItems
					});

			var addWin = new Ext.Window({
						title : '添加',
						layout : 'fit',
						width : width,
						height : height,
						closeAction : 'hide',
						items : [addForm],
						buttons : [{
							text : '添加',
							type : 'button',
							handler : function() {
								if (addForm.form.isValid()) {
									addForm.form.submit({
												waitMsg : "记录添加中...",
												success : function(form, action) {
													Ext.example.msg('信息',
															action.result.msg);
													addForm.form.reset();
													addWin.hide();
													store.load();
												},
												failure : function(form, action) {
													Ext.MessageBox.alert('错误',
															'操作失败');
												}
											});
								}
							}
						}, {
							text : '重置',
							type : 'reset',
							handler : function() {
								addForm.form.reset();
							}
						}, {
							text : '取消',
							handler : function() {
								addForm.form.reset();
								addWin.hide();
							}
						}]
					});
			return addWin;
		},
		addButton : function(addWin) {
			return new Ext.Button({
						text : '添加',
						iconCls : "addBtn",
						handler : function() {
							addWin.show();
						}
					});
		},

		editButton : function(url, store) {
			return new Ext.Button({
						text : '保存修改',
						iconCls : "saveBtn",
						handler : function() {
							var m = store.modified.slice(0);
							var jsonArray = [];
							Ext.each(m, function(item) {
										jsonArray.push(item.data);
									});
							if (jsonArray.length > 0) {
								Common.sendJsonArray(jsonArray, url, store);
							}
						}
					});
		},
		process : function(url, store, cells, msg) {
			if (cells.length == 0) {
				Ext.Msg.alert("提醒", '请至少选择一行.');
			} else {
				Ext.Msg.confirm('信息', msg, function(btn) {
							if (btn == 'yes') {
								var jsonArray = [];
								for (var i = 0; i < cells.length; i++) {
									jsonArray.push(cells[i].data);
								}
								Common.sendJsonArray(jsonArray, url, store);
							}
						})
			}
		},
		downloadProcess : function(url, store, cells, msg) {
			if (cells.length == 0) {
				Ext.Msg.alert("提醒", '请至少选择一行.');
			} else {
				Ext.Msg.confirm('信息', msg, function(btn) {
					if (btn == 'yes') {
						var mk = Common.showLoadMask();
						var jsonArray = [];
						for (var i = 0; i < cells.length; i++) {
							jsonArray.push(cells[i].data);
						}
						Ext.lib.Ajax.request('POST', url, {
							success : function(response) {
								var result = response.responseText;
								var filePath = "";
								var msg = "";
								var results = null;
								if (result != "" && result != null) {
									results = result.split('|');
									if (results.length == 2) {
										msg = results[0];
										filePath = results[1];
									} else {
										msg = result;
									}
								}
								mk.hide();
								if (filePath != "") {
									Ext.MessageBox.show({
										title : "提示",
										msg : msg,
										buttons : {
											'ok' : '保存到本地',
											"cancel" : "关闭窗口"
										},
										fn : function(btn) {
											if (btn == 'ok') {
												if (filePath != ""
														&& filePath != null) {
													var contextPath = document
															.getElementById("contextPath").value;
													window.location.href = contextPath
															+ "/sublist/task_download.do?filePath="
															+ Common
																	.replaceSpecialCharForUrl(filePath);
												}
											}
										}
									});
								} else {
									Ext.MessageBox.show({
												title : "提示",
												msg : msg,
												buttons : {
													"cancel" : "关闭窗口"
												}
											});
								}
							},
							failure : function() {
								mk.hide();
								Ext.Msg.alert("错误", '与后台联系时出问题');
							}
						}, 'data=' + encodeURIComponent(Ext.encode(jsonArray)));
					}
				})
			}
		},
		directDownloadProcess : function(url, msg) {
			Ext.Msg.confirm('信息', msg, function(btn) {
				if (btn == 'yes') {
					var contextPath = document.getElementById("contextPath").value;
					window.location.href = contextPath +url;
				}
			})
		},
		deleteProcess : function(url, store, cells) {
			if (cells.length == 0) {
				Ext.Msg.alert("提醒", '请至少选择一行.');
			} else {
				Ext.Msg.confirm('信息', '确定要删除?', function(btn) {
							if (btn == 'yes') {
								var jsonArray = [];
								for (var i = 0; i < cells.length; i++) {
									jsonArray.push(cells[i].data);
									store.remove(cells[i]);
								}
								Common.sendJsonArray(jsonArray, url, store);
							}
						})
			}
		},
		showLoadMask : function(id) {
			if (id == null) {
				id = 'main-tabs';
			}
			mk = new Ext.LoadMask(Ext.get(id), {
						id : 'loadMask',
						msg : '加载数据中, 请稍侯…',
						removeMask : true
					});

			mk.show(); // 显示
			if (Ext.isIE && !Ext.isStrict) {
				mk.repaint();
			}
			return mk;
		},
		pageingToolBar : function(pageSizePlugin, store, useItems) {
			return new Ext.PagingToolbar({
						pageSize : 15,
						store : store,
						displayInfo : true,
						items : useItems,
						plugins : pageSizePlugin
					});
		},

		showIETips : function() {
			if (Ext.isIE6) {
				Ext.example.msg('使用小贴士',
						'对不起，你使用的IE6，将无法正常显示该平台内容。<br/> 强烈建议使用火狐浏览器。', 60);
			} else if (Ext.isIE) {
				Ext.example.msg('使用小贴士',
						'您使用的是IE浏览器。<br/>如果感觉页面展现很慢，建议使用火狐浏览器。', 20);
			}
		},

		comboRelation : function(srcComponent, destComponent, url) {
			srcComponent.on("blur", function(comboBox) {
						var value = comboBox.getValue();
						if (value != "") {
							destComponent.store.proxy = new Ext.data.HttpProxy(
									{
										url : url + value
									});
							destComponent.clearValue();
							destComponent.store.load();
						}
					});
		},
		replaceSpecialCharForUrl : function(s) {
			// 1.+ URL 中+号表示空格 %2B
			// 2.空格 URL中的空格可以用+号或者编码 %20
			// 3./ 分隔目录和子目录 %2F
			// 4.? 分隔实际的 URL 和参数 %3F
			// 5.% 指定特殊字符 %25
			// 6.# 表示书签 %23
			// 7.& URL 中指定的参数间的分隔符 %26
			// 8.= URL 中指定参数的值 %3D
			s = s.replace('%', '%25');
			s = s.replace('+', '%2B');
			s = s.replace(' ', '%20');
			s = s.replace('/', '%2F');
			s = s.replace('?', '%3F');
			s = s.replace('#', '%23');
			s = s.replace('&', '%26');
			s = s.replace('=', '%3D');
			return s;
		},
		// 内容换行
		renderContent : function(value, cell) {
			cell.attr = 'style="white-space:pre-wrap;"';// 这里设置为pre-wrap，不会处理多余的空白符，也不会把换行符变成一个空格。
			return "<span style='font-size:12px; font-family:Verdana; line-height: 150%'>"
					+ value + "</span>";// 这里设置字体和行距
		}
	};
}();
