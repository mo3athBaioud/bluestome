var app = {};
Ext.onReady(function(){
  	Ext.BLANK_IMAGE_URL = project + '/resources/images/default/s.gif';
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    Ext.QuickTips.init();
	app.limit = 25;
    app.sm = new Ext.grid.CheckboxSelectionModel();
    
    app.cm_utp = new Ext.grid.ColumnModel([
		app.sm,
        {header: "ID", width: 100, sortable: true, dataIndex: 'id'},
        {header: "父ID", width: 100, sortable: true, dataIndex: 'parentId'},
        {header: "名称", width: 100, sortable: true, dataIndex: 'name'},
        {header: "类型", width: 100, sortable: true, dataIndex: 'type',renderer:function(v){
        	var x = parseInt(v);
        	var note = "图片";
        	switch(x){
        		case 1:
        			note="图片";
        			break;
        		case 2:
        			note="文章";
        			break;
        		case 3:
        			note="未知";
        			break;
        		default:
        			break;
        	}
        	return note;
        }},
        {header: "操作", width: 100, sortable: true, dataIndex: 'status',renderer:function(v){
        	var x = parseInt(v);
        	var note = "正常";
        	switch(x){
        		case 0:
        			note="不可用";
        			break;
        		case 1:
        			note="可用";
        			break;
        		case 2:
        			note="未知";
        			break;
        		default:
        			break;
        	}
        	return note;
        }},
        {header: "收录时间", width: 100, sortable: true, dataIndex: 'createtime'},
    ]);
    
	app.btn_search_code = new Ext.Button({
		text : '查询',
		disabled:false,
		iconCls : 'icon-search',
		handler : function(){
			app.searchcode();
		}
	});
	
	app.btn_create = new Ext.Button({
		text : '创建',
		disabled:false
	});
	
	app.btn_dissolve = new Ext.Button({
		text : '解散',
		disabled:false
	});
	
	
	app.btn_invate = new Ext.Button({
		text : '邀请好友',
		disabled:false
	});
	
	app.btn_export_xls = new Ext.Button({
		text : '导出文件',
		disabled:false,
		handler:function(){
				app.ds_data.load({
					params:{
						start:0,
						limit:app.limit
					}
				});
		}
	});
	
	app.btn_save_code = new Ext.Button({
		text : '保存',
		disabled:false,
		iconCls : 'icon-accept',
		handler : function(){
			var values =app.text_phone_number.getValue();
			var records = app.grid.getSelectionModel().getSelections();
			if(records.length == 0 ){
				Ext.Msg.show({
					title : '提示',
					msg:'请选择需要修改的记录',
					buttons : Ext.Msg.OK,
					icon : Ext.Msg.ERROR
				});
			}else{
				
			}
		}
	});

	app.searchcode = function() {
		var values =app.text_phone_number.getValue();
		if(null == values || '' ==  values){
			Ext.Msg.show({
				title : '系统提示',
				msg : '请输入需要查询的标题！',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.ERROR
			});
			return false;
		}
		app.ds_data.load({
			params : {
				'entity.title':values,
				start:0,
				limit:app.limit
			}
		});
	};
	
	app.text_phone_number = new Ext.form.TextField({
		name : 'phone_number',
		width : 150,
		allowBlank:false,
		vtype:'mobile',
		listeners : {
			'specialkey' : function(field, e) {
				if (e.getKey() == Ext.EventObject.ENTER) {
					app.searchcode();
				}
			}
		}
	});
	
	app.ds_data = new Ext.data.Store({
		url : project + '/admin/website!list.cgi',
		baseParams:{},
		reader : new Ext.data.JsonReader({
			totalProperty : 'total',
			root : 'records'
		}, [{name : 'id',type : 'int'
		}, {name : 'name',type : 'string'
		}, {name : 'url',type : 'string'
		}, {name : 'type',type : 'int'
		}, {name : 'parentId',type : 'int'
		}, {name : 'remarks',type : 'string'
		}, {name : 'status',type : 'int'
		}, {name : 'modifytime',type : 'string'
		}, {name : 'createTime',type : 'string'
		}])
	});
	
	app.ds_data.load({
		params:{
			start:0,
			limit:app.limit
		}
	});
	
	app.ptb = new Ext.PagingToolbar({
		pageSize:app.limit,
		store:app.ds_data,
		displayInfo:true,
		displayMsg:'第 {0} - {1} 条  共 {2} 条',
		emptyMsg:'没有记录'
	});
		
	app.grid = new Ext.grid.GridPanel({
		title : name,
		iconCls : 'icon-application_view_columns',
		region : 'center',
		loadMask : {
			msg : '数据加载中...'
		},
	    cm: app.cm_utp,
		ds: app.ds_data,
	    height:500,
//        autoScroll: true,
        viewConfig: {
            forceFit:true
        },
		sm:app.sm,
//		tbar : ['标题：','-',app.text_phone_number,app.btn_search_code,'-',app.btn_create,'-',app.btn_dissolve,'-',app.btn_invate,'-',app.btn_export_xls],
		bbar : app.ptb
	});
	
	app.grid.addListener('rowdblclick',function(grid, rowIndex){
		if(grid.getSelectionModel().isSelected(rowIndex)){
			Ext.Msg.show({
				title : '系统提示',
				width:200,
				msg : '显示数据详情',
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.INFO
			});
		}
	});

    app.grid.render('grid-div');
});