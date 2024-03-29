Ext.BLANK_IMAGE_URL = 'resources/images/default/s.gif';
Ext.QuickTips.init();
var start = {
	id : 'start-panel',
	title : '图片资源管理系统',
	layout : 'fit',
	bodyStyle : 'padding:25px',
	html : '<img src=images/bg.jpg />'
};

Ext.onReady(function() {
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
												var frm = this.ownerCt.form;
												if (frm.isValid()) {
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
												}
											}
										}, {
											text : '重置密码',
											handler : function() {
												this.ownerCt.form.reset();
											}
										}, {
											text : '取消修改',
											handler : function() {
												this.ownerCt.ownerCt.close();
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
									html : '<p>FCMP1.0</p>',
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
										Ext.Ajax.request({
											url : project+'/logout.cgi',
											success : function(response,option) {
												var obj = Ext.util.JSON.decode(response.responseText);
												Ext.Msg.show({
													title : '提示',
													msg : obj.msg,
													icon : Ext.Msg.INFO,
													fn:function(){
														window.parent.location = obj.url;
													},
													buttons : Ext.Msg.OK
												});
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
						title : '图片资源',
						iconCls : 'icon-world',
						border : false,
						autoScroll:true, 
		                enableDD:false,
		                containerScroll: true,
						tools : [{
							id : 'refresh',
							handler : function() {
								var tree = Ext.getCmp('website_tree_id');
								tree.root.reload()
							}
						}],
						items : [{
							id:'website_tree_id',
							xtype : 'treepanel',
							border : false,
							rootVisible : true,
							loader : new Ext.tree.TreeLoader({
								dataUrl : 'website/tree2.cgi?webType=1'
							}),
							//
							root : new Ext.tree.AsyncTreeNode({
								id:'0',
								text:'图片管理',
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
									/**
									**/
								}
							}
						}]
					},{
						title : '文章资源',
						iconCls : 'icon-nav',
						border : false,
						autoScroll:true, 
		                enableDD:false,
		                containerScroll: true,
						items : [{
							xtype : 'treepanel',
							border : false,
							rootVisible : true,
							loader : new Ext.tree.TreeLoader({
								dataUrl : 'website/tree2.cgi?webType=2'
							}),
							//
							root : new Ext.tree.AsyncTreeNode({
								id:'0',
								text:'文章管理',
								href:'/resource-cms/doExe.cgi',
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
					},{
						title : '基础数据',
						iconCls : 'icon-nav',
						border : false,
						autoScroll:true, 
		                enableDD:false,
		                containerScroll: true
					},{
						title : '系统管理',
						iconCls : 'icon-nav',
						border : false,
						autoScroll:true, 
		                enableDD:false,
		                containerScroll: true,
						items : [{
							xtype : 'treepanel',
							border : false,
							rootVisible : true,
							loader : new Ext.tree.TreeLoader({
								dataUrl : project+'/scripts/tree.json'
							}),
							//
							root : new Ext.tree.AsyncTreeNode({
								id:'0',
								text:'系统管理',
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
									/**
									**/
								}
							}
						}]
					}]
				}]
			}, {
				id : 'content-panel',
				region : 'center',
				layout : 'card',
				autoHeight : true,
				margins : '2 5 5 0',
				activeItem : 0,
				border : false,
				html:'<iframe name="mainFrame" width="98%" height="800" id="mainFrame" frameborder=0 src="pages/alla.jsp"></iframe>'					
			}]
		});
	}, 250);
});
