/**
 * 员工管理
 */
var app = {};
Ext.onReady(function(){
  	Ext.BLANK_IMAGE_URL = project+'/resource/extjs3.1/resources/images/default/s.gif';
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    Ext.QuickTips.init();
	app.limit = 15;
	app.colName = "d_username";
	app.values = "";
    app.sm = new Ext.grid.CheckboxSelectionModel();
    
    var fp = new Ext.FormPanel({
    	id:'upload_form',
        url: project + '/bussiness/upload.cgi',
        renderTo: 'import',
        fileUpload: true,
        width: 500,
        frame: true,
        title: '业务数据上传',
        autoHeight: true,
        bodyStyle: 'padding: 10px 10px 0 10px;',
        labelWidth: 100,
        defaults: {
            anchor: '95%',
            allowBlank: false,
            msgTarget: 'side'
        },
         viewConfig: {
            forceFit:true
        },
        items: [
        {
			//下拉选择框
			xtype:'combo',
			fieldLabel : '业务列表',
			hiddenName:'btype',
	        valueField: 'id',
	        displayField: 'name',
	        triggerAction:'all',
	        mode: 'local',
	        store: new Ext.data.SimpleStore({
	            fields: ['id','name'],
	            data: [
            	   [1,'无线音乐'],[2,'139邮箱'],[3,'飞信会员'],[4,'号簿管家'],[5,'全曲下载'],[6,'手机报'],[7,'手机视频'],[8,'手机阅读'],
            	   [9,'手机游戏'],[10,'手机电视'],[11,'移动MM'],[12,'GPRS流量包'],[13,'彩信包'],[14,'手机支付'],[15,'WIFI'],[16,'手机地图']
        	   ]
	        }),
            editable:false,
			emptyText : '请选择业务类型!',
			allowBlank : false
		},{
            xtype: 'fileuploadfield',
            id: 'form-file',
            emptyText: '请选择文件',
            fieldLabel: '文&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;件',
            name: 'bussinessf',
            buttonText: '',
            buttonCfg: {
                iconCls: 'upload-icon'
            }
        }],
        buttons: [{
            text: '上传',
            handler: function(){
            	var frm = Ext.getCmp('upload_form').form;
                if(frm.isValid()){
	                frm.submit({
	                    waitMsg: '正在上传文件，请稍等...',
	                    success: function(frm, action){
							Ext.Msg.show({
								title : '系统提示',
								msg : action.result.msg,
								buttons : Ext.Msg.OK,
								icon : Ext.Msg.INFO
							});
							fp.getForm().reset();
	                    },
	                    failure : function(frm, action){
							Ext.Msg.show({
								title : '系统提示',
								msg : action.result.msg,
								buttons : Ext.Msg.OK,
								icon : Ext.Msg.ERROR
							});
							fp.getForm().reset();
						}
	                });
                }
            }
        }]
    });

});