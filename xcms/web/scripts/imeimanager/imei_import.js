/**
 * IMEI
 */
var app = {};
Ext.onReady(function(){
  	Ext.BLANK_IMAGE_URL = '/resource/image/ext/s.gif';
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    Ext.QuickTips.init();
	app.limit = 50;
	app.colName = "";
	app.values = "";
    app.sm = new Ext.grid.CheckboxSelectionModel();
	
	//统计日期,用户标识,手机号码,imei号码,业务区编码,通话次数,基本计费跳,通话时长,tac码    
    app.data = [
        ['2010/6/27','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700'],
        ['2010/6/27','1610041222370609','18729637525','812347982824500','FA49','4','14','735','81234798'],
        ['2010/6/27','1610041222370609','18729637526','863417452824500','F0E9','4','14','735','86341745'],
        ['2010/6/27','1610041222370609','18729637527','883210322824500','FC79','4','14','735','88321032'],
        ['2010/6/27','1610041222370609','18729637528','873319122824500','FD69','4','14','735','87331912'],
        ['2010/6/27','1610041222370609','18729637529','813247092824500','F0F9','4','14','735','81324709'],
        ['2010/6/27','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700'],
        ['2010/6/27','1610041222370609','18729637525','812347982824500','FA49','4','14','735','81234798'],
        ['2010/6/27','1610041222370609','18729637526','863417452824500','F0E9','4','14','735','86341745'],
        ['2010/6/27','1610041222370609','18729637527','883210322824500','FC79','4','14','735','88321032'],
        ['2010/6/27','1610041222370609','18729637528','873319122824500','FD69','4','14','735','87331912'],
        ['2010/6/27','1610041222370609','18729637529','813247092824500','F0F9','4','14','735','81324709'],
        ['2010/6/27','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700'],
        ['2010/6/27','1610041222370609','18729637525','812347982824500','FA49','4','14','735','81234798'],
        ['2010/6/27','1610041222370609','18729637526','863417452824500','F0E9','4','14','735','86341745'],
        ['2010/6/27','1610041222370609','18729637527','883210322824500','FC79','4','14','735','88321032'],
        ['2010/6/27','1610041222370609','18729637528','873319122824500','FD69','4','14','735','87331912'],
        ['2010/6/27','1610041222370609','18729637529','813247092824500','F0F9','4','14','735','81324709'],
        ['2010/6/27','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700'],
        ['2010/6/27','1610041222370609','18729637525','812347982824500','FA49','4','14','735','81234798'],
        ['2010/6/27','1610041222370609','18729637526','863417452824500','F0E9','4','14','735','86341745'],
        ['2010/6/27','1610041222370609','18729637527','883210322824500','FC79','4','14','735','88321032'],
        ['2010/6/27','1610041222370609','18729637528','873319122824500','FD69','4','14','735','87331912'],
        ['2010/6/27','1610041222370609','18729637529','813247092824500','F0F9','4','14','735','81324709'],
        ['2010/6/27','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700'],
        ['2010/6/27','1610041222370609','18729637525','812347982824500','FA49','4','14','735','81234798'],
        ['2010/6/27','1610041222370609','18729637526','863417452824500','F0E9','4','14','735','86341745'],
        ['2010/6/27','1610041222370609','18729637527','883210322824500','FC79','4','14','735','88321032'],
        ['2010/6/27','1610041222370609','18729637528','873319122824500','FD69','4','14','735','87331912'],
        ['2010/6/27','1610041222370609','18729637529','813247092824500','F0F9','4','14','735','81324709'],
        ['2010/6/27','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700'],
        ['2010/6/27','1610041222370609','18729637525','812347982824500','FA49','4','14','735','81234798'],
        ['2010/6/27','1610041222370609','18729637526','863417452824500','F0E9','4','14','735','86341745'],
        ['2010/6/27','1610041222370609','18729637527','883210322824500','FC79','4','14','735','88321032'],
        ['2010/6/27','1610041222370609','18729637528','873319122824500','FD69','4','14','735','87331912'],
        ['2010/6/27','1610041222370609','18729637529','813247092824500','F0F9','4','14','735','81324709'],
        ['2010/6/27','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700'],
        ['2010/6/27','1610041222370609','18729637525','812347982824500','FA49','4','14','735','81234798'],
        ['2010/6/27','1610041222370609','18729637526','863417452824500','F0E9','4','14','735','86341745'],
        ['2010/6/27','1610041222370609','18729637527','883210322824500','FC79','4','14','735','88321032'],
        ['2010/6/27','1610041222370609','18729637528','873319122824500','FD69','4','14','735','87331912'],
        ['2010/6/27','1610041222370609','18729637529','813247092824500','F0F9','4','14','735','81324709'],
        ['2010/6/27','1610041222370609','18729637524','863417002824500','F0F9','4','14','735','86341700'],
        ['2010/6/27','1610041222370609','18729637525','812347982824500','FA49','4','14','735','81234798'],
        ['2010/6/27','1610041222370609','18729637526','863417452824500','F0E9','4','14','735','86341745'],
        ['2010/6/27','1610041222370609','18729637527','883210322824500','FC79','4','14','735','88321032'],
        ['2010/6/27','1610041222370609','18729637528','873319122824500','FD69','4','14','735','87331912'],
        ['2010/6/27','1610041222370609','18729637529','813247092824500','F0F9','4','14','735','81324709']
	];
    
    app.cm_utp = new Ext.grid.ColumnModel([
	    new Ext.grid.RowNumberer(),app.sm,
        {header: "统计日期", width: 100, sortable: true, dataIndex: 'time'},
        {header: "用户标识", width: 150, sortable: true, dataIndex: 'uid'},
        {header: "手机号码", width: 100, sortable: true, dataIndex: 'sn'},
        {header: "imei号码", width: 150, sortable: true, dataIndex: 'imei'},
        {header: "业务区编码", width: 100, sortable: true, dataIndex: 'ywqbm'},
        {header: "通话次数", width: 80, sortable: true, dataIndex: 'thcs'},
        {header: "基本计费跳", width: 100, sortable: true, dataIndex: 'jbjft'},
        {header: "通话时长", width: 80, sortable: true, dataIndex: 'thsc'},
        {header: "tac码", width: 80, sortable: true, dataIndex: 'tac'}
    ]);
    
	app.btn_search_code = new Ext.Button({
		text : '查询',
		iconCls : 'icon-search',
		handler : function(){
			app.searchcode();
		}
	});
	
	app.btn_upload = new Ext.Button({ 
		text:'上传',
		iconCls:'upload-icon',
		handler:function(){
			win.show();
			/**
			Ext.Msg.show({
				title : '系统提示',
				width:300,
				msg : '显示上传界面!',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.INFO
			});
			**/ 
			
		}
	});
	
	app.text_search_code = new Ext.form.TextField({
		name : 'textSearchcode',
		width : 150,
		listeners : {
			'specialkey' : function(field, e) {
				if (e.getKey() == Ext.EventObject.ENTER) {
					app.searchcode();
				}
			}
		}
	});

	app.text_imei_code = new Ext.form.TextField({
		name : 'text_imei',
		width : 150,
		listeners : {
			'specialkey' : function(field, e) {
				if (e.getKey() == Ext.EventObject.ENTER) {
					app.searchcode();
				}
			}
		}
	});
	
	var uploadForm = new Ext.form.FormPanel({
	     baseCls : 'x-plain',
	     labelWidth : 70,
	     fileUpload : true,
	     defaultType : 'textfield',
	     items : [{
	        xtype : 'textfield',
	        fieldLabel : '上传文件名',
	        name : 'userfile',
	        id : 'userfile',
	        inputType : 'file',
	        blankText : '请选择需要上传的文件!',
	        anchor : '100%' // anchor width by percentage
	      }]
    });
  
// 	 var pbar4 = new Ext.ProgressBar({
//	        text:'请等待',
//	        id:'pbar4',
//	        textEl:'p4text',
//	        cls:'custom'
//    });
    
	var win = new Ext.Window({
	 	 id:'imei_import_upload_win',
	     title : 'IMEI文件上传',
	     width : 400,
	     autoHeight : true,
	     layout : 'fit',
	     plain : true,
	     bodyStyle : 'padding:5px;',
	     buttonAlign : 'center',
	     items : [
	     uploadForm,
//	     pbar4,
	     new Ext.ProgressBar({
	        id:'pbar4',
	        textEl:'p4text',
	        cls:'custom'
    	}),
 		{
 			id:'p4',
 			xtype:'label',
 			width:400, 
            border:0
        },{
 			applyTo:'p4text', 
        	xtype:'label',
            border:0
        }],
	     buttons : [
		     {
			      text : '上传',
			      handler : function() {
				       if (uploadForm.form.isValid()) {
					        if(Ext.getCmp('userfile').getValue() == ''){
						         Ext.Msg.alert('错误','请选择你要上传的文件');
						         return;
					        }else{
					        	var pbar4 = Ext.getCmp('pbar4');
						        Runner.run(pbar4, this, 19, function(){
						        	pbar4.reset();
						            pbar4.updateText('上传完成!');
									Ext.Msg.show({
										title : '系统提示',
										width:300,
										msg : '上传成功!',
										buttons : Ext.Msg.OK,
										fn : function() {
											uploadForm.form.reset();
											win.hide();
											app.ds_utp.loadData(app.data);
											Ext.getCmp('imei_import_upload_win').close();
//											pbar4.updateText('请选择文件!');
										},
										icon : Ext.MessageBox.INFO
									});
						        });
					        }
				       }
		      	 }
		     }, {
			      text : '关闭',
			      handler : function() {
			      	uploadForm.form.reset();
			       	win.hide();
			      }
		     }
 		]
    });

	app.text_tac_code = new Ext.form.TextField({
		name : 'text_tac',
		width : 150,
		listeners : {
			'specialkey' : function(field, e) {
				if (e.getKey() == Ext.EventObject.ENTER) {
					app.searchcode();
				}
			}
		}
	});
	//终端查询组件
//	app.terminal_query_components = new TerminalQuery(){
//		ds:app.ds_utp
//	}
	
	app.searchcode = function() {
		app.ds_utp.loadData(app.data);
	};
	
	/**	
	app.ds_utp = new Ext.data.Store({
		url : '/utp/utp.cgi',
		baseParams:{},
		reader : new Ext.data.JsonReader({
			totalProperty : 'count',
			root : 'utp'
		}, [{name : 'd_id',type : 'int'
		}, {name : 'd_web_id',type : 'int'
		}, {name : 'd_title',type : 'string'
		}, {name : 'd_acticle_url',type : 'string'
		}, {name : 'd_article_real_url',type : 'string'
		}, {name : 'd_article_xml_url',type : 'string'
		}, {name : 'd_text',type : 'string'
		}, {name : 'd_createtime',type : 'string'
		}, {name : 'd_intro',type : 'string'
		}])
	});
	**/
	
	app.ds_utp = new Ext.data.ArrayStore({
        fields: [
           {name: 'time',type:'string'},
           {name: 'uid',type:'string'},
           {name: 'sn',type:'string'},
           {name: 'imei',type:'string'},
           {name: 'ywqbm',type:'string'},
           {name: 'thcs', type: 'string'},
           {name: 'jbjft', type: 'string'},
           {name: 'thsc', type: 'string'},
           {name: 'tac', type: 'string'}
        ]
    });
	
	app.ptb = new Ext.PagingToolbar({
		pageSize:app.limit,
		store:app.ds_utp,
		displayInfo:true,
		displayMsg:'第 {0} - {1} 条  共 {2} 条',
		emptyMsg:'没有记录'
	});
		
	app.grid = new Ext.grid.GridPanel({
		id:'imei_import_grid',
		title : '通话IMEI导入',
		iconCls : 'icon-cart_put',
		region : 'center',
		loadMask : {
			msg : '数据加载中...'
		},
	    cm: app.cm_utp,
	    ds: app.ds_utp,
//	    width:1000,
	    height:500,
        autoScroll: true,
		sm:app.sm,
		tbar : [app.btn_upload,'-','请输入手机号码:',app.text_search_code,'-','请输入IMEI:',app.text_imei_code,'请输入TAC码:',app.text_tac_code,app.btn_search_code],
		bbar : app.ptb
	});
	
	app.grid.addListener('rowdblclick',function(grid, rowIndex){
				if(grid.getSelectionModel().isSelected(rowIndex)){
					Ext.Msg.show({
						title : '系统提示',
						width:300,
						msg : '该功能正在开发!',
						buttons : Ext.Msg.OK,
						icon : Ext.MessageBox.INFO
					});
				}
	});

    app.grid.render('imei_import');
});

var Runner = function(){
    var f = function(v, pbar, btn, count, cb){
        return function(){
            if(v > count){
//                btn.dom.disabled = false;
                cb();
            }else{
                if(pbar.id=='pbar4'){
                    //give this one a different count style for fun
                    var i = v/count;
                    pbar.updateProgress(i, '已上传['+Math.round(100*i)+'%]');
                }else{
                    pbar.updateProgress(v/count, 'Loading item ' + v + ' of '+count+'...');
                }
            }
       };
    };
    return {
        run : function(pbar, btn, count, cb){
//            btn.dom.disabled = true;
            var ms = 5000/count;
            for(var i = 1; i < (count+2); i++){
               setTimeout(f(i, pbar, btn, count, cb), i*ms);
            }
        }
    }
}();
