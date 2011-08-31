/**
 * 后台管理展示页面
 * 
 * @author XiongChun
 * @since 2010-01-13
 */
Ext.onReady(function() {
		Ext.QuickTips.init();
		
        mainTabs = new Ext.TabPanel({
            region:'center', 
            activeTab:0,
            id:'mainTabs',
            enableTabScroll:true,
            height:550,
            border:false,
            frame:true,
            plugins: new Ext.ux.TabCloseMenu(),
            items:[{
                  title:"<img align='top' class='IEPNG' src='"+project+"/images/user.png'/>我的工作台",
                  listeners: {
                  	activate: function(){
                  		Ext.getCmp('centerPanel').setTitle(projectname+' -> 我的工作台');
                	}
               	  },
                  html:"<iframe name='mainFrame'  src='"+project+"/mydesk.jsp' scrolling='auto' frameborder='0' width='100%' height='100%' ></iframe>"
                }
            ]
         });

        var viewport = new Ext.Viewport({
            layout:'border',
            items:[
	            new Ext.Panel({
	                region:'north',
	                contentEl:'north', 
//	                iconCls:'application_homeIcon', 
	                height:85,
	                collapsible:true,
	                border:false,
	                layout: 'fit',
	                title:projectname
	            }),
	            new Ext.BoxComponent({
	                region:'south',
	                contentEl: 'south',
	                height:17,
	                layout: 'fit',
	                collapsible: true
	            }),{
	            	region:'center', 
	                id: 'centerPanel', 
	                iconCls:'',
	                title:projectname,
	                autoScroll:false,
	                layout: 'fit',
	                items:[mainTabs]
				},{
				   region:'west',
	               width: 220,
	               collapsible: true,
	               minSize: 200,
	               maxSize: 350,
	               split: true,
//	               iconCls:'book_previousIcon',
//	               title: '系统导航',
	               layout:'accordion',
	               layoutConfig:{
		               animate:true,
		               activeOnTop :true
	               },
					tools : [{
						id : 'refresh',
						handler : function() {
							var tree = Ext.getCmp('menu_tree_id');
							tree.root.reload()
						}
					}],
	             	items: [
                   /**
                    {
                    autoScroll:true,
                    border:false,
                    contentEl: 'div.card.0101',
                    iconCls:'folder_wrenchIcon',
                    title:'业务管理'
                    }
					**/
					{
						id:'menu_tree_id',
						xtype : 'treepanel',
						border : false,
						rootVisible : true,
						loader : new Ext.tree.TreeLoader({
							dataUrl : project+'/tree.json'
						}),
						root : new Ext.tree.AsyncTreeNode({
							id:'0',
							text:'业务管理'
						}), 
						listeners : {
							'click' : function(n) {
								try{
									//子节点点击事件，json中最好不要使用href这个参数，extjs会将该参数名转换为a链接，然后打开新的窗口。
									var sn = this.selModel.selNode || {};
									if (n.leaf && (null != n.attributes.ahref && '' != n.attributes.ahref)) {
										//' -> 统计分析 -> 自定义报表 -> 手机电视推荐清单'
										addTab(n.attributes.ahref,n.text,n.id,projectname+' -> '+n.parentNode.text+' -> '+n.text,n.attributes.tabicon);								
									}
								}catch(e){
								}
							}
						}
					}
		            ]
	            }
           ]
       }); 
});

	/**
	 * 响应树节点单击事件
	 */
	function addTab(url,tname,menuid,pathCh,icon){
	  var tid = "tab_id_" + menuid;
	  if(url == '#'){
	    showOkTipMsg('此菜单还没有指定请求页面,请点击其它功能菜单.');
	  }else if('undefine' != url && '' != url && null != url){
	      var index = url.indexOf('.ered');
	      if(index != -1)
	    	url = url + '&menuid4Log=' + menuid;
		  var n = mainTabs.getComponent(tid);
	      if (!n) {
	         // 如果对centerPanel进行遮罩,则可以出现阴影mainTabs
	         Ext.getCmp('centerPanel').getEl().mask('<span style=font-size:12>正在加载页面,请稍等...</span>', 'x-mask-loading');
	         // 兼容IE和FF触发.click()函数
	         var endIeStatus = document.getElementById("endIeStatus");
	         if(document.createEvent){
	             var ev = document.createEvent('HTMLEvents');
	             ev.initEvent('click', false, true);
	             endIeStatus.dispatchEvent(ev);
	         }
	         else endIeStatus.click();
	         n = mainTabs.add({
	           id:tid,
			   title:"<img align='top' class='IEPNG' src='"+project+ icon + "'/>" + tname,
	           closable:true,
	           layout:'fit',
	           listeners: {
	        	 	activate: function(){
	        	 		Ext.getCmp('centerPanel').setTitle(pathCh)
	    	   		}
	           },
	           html:'<iframe scrolling="auto" frameborder="0" onload="unmaskCenterPanel()" width="100%" height="100%" src='+url+'></iframe>'
	         });
	     }
	     mainTabs.setActiveTab(n);
	 }
	}
	
	/**
	* 取消阴影(当子页面加载完成后通过parent.XXXX来调用)
	*/
	
	function unmaskCenterPanel(){
		// 如果对centerPanel进行遮罩,则可以出现阴影
		Ext.getCmp('centerPanel').getEl().unmask();
	}

	// 解决Iframe IE加载不完全的问题
	function endIeStatus(){}
	
	// 定时去掉载入图标
	Ext.EventManager.on(window, 'load', function() {
		setTimeout(function() {
			Ext.get('loading').remove();
			Ext.get('loading-mask').fadeOut( {
				remove : true
			});
		}, 200);
	});
	
