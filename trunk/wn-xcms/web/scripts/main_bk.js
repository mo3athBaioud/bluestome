    Ext.onReady(function(){
    
        // NOTE: This is an example showing simple state management. During development,
        // it is generally best to disable state management as dynamically-generated ids
        // can change across page loads, leading to unpredictable results.  The developer
        // should ensure that stable state ids are set for stateful components in real apps.
        Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
        
		Ext.BLANK_IMAGE_URL = 'resources/images/default/s.gif';
		Ext.QuickTips.init();
		
		var start = {
			id : 'start-panel',
			title : '西安移动IMEI支撑系统',
			layout : 'fit',
			bodyStyle : 'padding:25px',
			html : '<img src=images/bg.jpg />'
		};
		
        var viewport = new Ext.Viewport({
            layout: 'border',
            items: [
            
            // create instance immediately
            new Ext.BoxComponent(
            {
                region: 'north',
                height: 32, // give north and south regions a height
								width:250,
								applyTo : 'header'
								/**
                autoEl: {
                    tag: 'div',
                    html:'<p>north- 用户相关信息</p>'
                }
                **/
            }),
			/**
			{
                region: 'east',
                title: 'East Side',
                collapsible: true,
                split: true,
                width: 225, // give east and west regions a width
                minSize: 175,
                maxSize: 400,
                margins: '0 5 0 0',
                layout: 'fit', // specify layout manager for items
                items:            // this TabPanel is wrapped by another Panel so the title will be applied
                new Ext.TabPanel({
                    border: false, // already wrapped so don't add another border
                    activeTab: 1, // second tab initially active
                    tabPosition: 'bottom',
                    items: [{
                        html: '<p>A TabPanel component can be a region.</p>',
                        title: 'A Tab',
                        autoScroll: true
                    }, new Ext.grid.PropertyGrid({
                        title: 'Property Grid',
                        closable: true,
                        source: {
                            "(name)": "Properties Grid",
                            "grouping": false,
                            "autoFitColumns": true,
                            "productionQuality": false,
                            "created": new Date(Date.parse('10/15/2006')),
                            "tested": false,
                            "version": 0.01,
                            "borderWidth": 1
                        }
                    })]
                })
            },
			**/
			{
                region: 'west',
                id: 'west-panel', // see Ext.getCmp() below
                title: '导航栏',
                split: true,
                width: 200,
                minSize: 175,
                maxSize: 200,
                collapsible: true,
                margins: '0 0 0 5',
                layout: {
                    type: 'accordion',
                    animate: true
                },
				bbar : [
					{
					text : '开始',
					iconCls : 'icon-plugin',
					items:new Ext.menu.Menu({
						items:[{
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
						},{
							text : '退出系统',
							iconCls : 'icon-delete',
							handler : function() {
								Ext.Msg.confirm('操作提示', '您确定要退出本系统?', function(btn) {
									if ('yes' == btn) {
										Ext.Ajax.request({
											url : 'logout.action',
											success : function() {
												location = project+'/login.jsp';
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
					}
				],
                items: [{
                    contentEl: 'west',
                    title: 'IMEI-通话用户处理',
                    border: false,
                    iconCls: 'nav', // see the HEAD section for style used
                    items:[
                    {
							xtype : 'treepanel',
							border : false,
							rootVisible : true,
							loader : new Ext.tree.TreeLoader({
								dataUrl : 'tree.json'
							}),
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
								}
							}
						}]
                }, {
						title : '基础数据',
						iconCls : 'icon-nav',
						border : false,
						autoScroll:true, 
		                enableDD:false,
		                containerScroll: true
				},{
						title : '基础数据',
						iconCls : 'icon-nav',
						border : false,
						autoScroll:true, 
		                enableDD:false,
		                containerScroll: true,
						items:[
{
							xtype : 'treepanel',
							border : false,
							rootVisible : true,
							loader : new Ext.tree.TreeLoader({
								dataUrl : 'tree.json'
							}),
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
								}
							}
					}]
				}]
            },
            new Ext.TabPanel({
                region: 'center', // a center region is ALWAYS required for border layout
                deferredRender: false,
                activeTab: 0,     // first tab initially active
                items: [{
                    contentEl: 'center1',
                    title: '主页',
                    autoScroll: true
                }, {
                    contentEl: 'center2',
                    title: 'Close Me',
					closable: true,
                    autoScroll: true
                }]
            })]
        });

    });
