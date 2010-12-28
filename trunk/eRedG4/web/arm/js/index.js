/**
 * 首页部分JS
 * 
 * @author XiongChun
 * @since 2010-03-13
 */
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
						renderTo : 'themeDiv',
						handler : function() {
							themeWindowInit();
						}
					});

			var configButton = new Ext.Button({
						text : '首选项',
						iconCls : 'config2Icon',
						iconAlign : 'left',
						scale : 'medium',
						width : 50,
						tooltip : '<span style="font-size:12px">首选项菜单</span>',
						pressed : true,
						renderTo : 'configDiv'
					});

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
							window.location.href = './login.ered?reqCode=logout';
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
						o.innerHTML = '<img src="./resource/image/theme/' + theme + '.jpg" />';
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
				o.innerHTML = '<img src="./resource/image/theme/' + default_theme + '.jpg" />';
				themeWindow.show();

			}

			/**
			 * 保存用户自定义主题
			 */
			function saveUserTheme(o) {
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
			}
		});

/**
 * 显示系统时钟
 */
// TODO 此功能会一定程度浪费客户端系统资源
function showTime() {
	var date = new Date();
	var h = date.getHours();
	h = h < 10 ? '0' + h : h;
	var m = date.getMinutes();
	m = m < 10 ? '0' + m : m;
	var s = date.getSeconds();
	s = s < 10 ? '0' + s : s;
	document.getElementById('rTime').innerHTML = h + ":" + m + ":" + s;
}

window.onload = function(){
	setInterval("showTime()", 1000);
}