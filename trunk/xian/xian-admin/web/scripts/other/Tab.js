	/**
	 * 响应树节点单击事件
	 */
	function addTab(url,tname,menuid,pathCh,icon){
	  var tid = "tab_id_" + menuid;
	  var mainTabss = Ext.getCmp('mainTabs');
	  if(url == '#'){
	    showOkTipMsg('此菜单还没有指定请求页面,请点击其它功能菜单.');
	  }else if('undefine' != url && '' != url && null != url){
	      var index = url.indexOf('.ered');
	      if(index != -1)
	    	url = url + '&menuid4Log=' + menuid;
		  var n = Ext.getCmp(tid);
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
	         n = mainTabss.add({
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
	     mainTabss.setActiveTab(n);
	 }
	}
