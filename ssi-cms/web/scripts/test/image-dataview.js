            var app = {}
            
            Ext.onReady(function(){
                Ext.BLANK_IMAGE_URL = project+'/resources/images/default/s.gif'
                Ext.QuickTips.init();
                Ext.form.Field.prototype.msgTarget = 'side';
                app.limit = 20;
                var store = new Ext.data.Store({
					url : project+'/image/image.cgi?id='+articleId,
                    baseParams: {},
					reader : new Ext.data.JsonReader({
						totalProperty : 'count',
						root : 'image'
					}, [{name : 'd_id',type : 'int'
					}, {name : 'd_article_id',type : 'int'
					}, {name : 'd_title',type : 'string'
					}, {name : 'd_imgurl',type : 'string'
					}, {name : 'd_httpurl',type : 'string'
					}, {name : 'd_imgurl',type : 'string'
					}, {name : 'd_src_imgurl',type : 'string'
					}, {name : 'd_src_httpurl',type : 'string'
					}, {name : 'd_commentsuburl',type : 'string'
					}, {name : 'd_name',type : 'string'
					}, {name : 'd_createtime',type : 'string'
					}, {name : 'd_commentshowurl',type : 'string'
					}, {name : 'd_link',type : 'string'
					}, {name : 'd_time',type : 'string'
					}, {name : 'd_orderid',type : 'int'
					}]),
                    remoteSort: true,
                    listeners: {
                        update: function(store, rec, op){
                            if (op == Ext.data.Record.EDIT) {
                                Ext.Ajax.request({
                                    url: 'dataview_action.ashx?act=edit&value=' + escape(rec.data.title) + '&id=' + rec.id,
                                    scripts: true,
                                    scope: this,
                                    success: function(oResponse, op){
                                        var msg = oResponse.responseText;
                                        var returnData = Ext.util.JSON.decode(msg);
                                        if (typeof returnData == "object") {
                                            if (returnData.success == "true") {
                                                store.commitChanges();
                                                return;
                                            }
                                            else {
                                                msg = returnData.data;
                                            }
                                        }
                                        store.rejectChanges();
			 							Ext.Msg.show({
											title : '系统提示',
											msg : msg,
											buttons : Ext.Msg.OK,
											icon : Ext.MessageBox.INFO
										});
                                    },//success
                                    failure: function(oResponse, op){
                                        store.rejectChanges();
			 							Ext.Msg.show({
											title : '系统提示',
											msg : oResponse.responseText,
											buttons : Ext.Msg.OK,
											icon : Ext.MessageBox.ERROR
										});
                                    }
                                });
                            }
                        }
                    }
                }); //store
                //style="width:100px;height:100px" 
                var tpl = new Ext.XTemplate('<tpl for=".">', '<div class="thumb-wrap" id="{id}">', '<div class="thumb"><table qtip="{tips} border="0"><tr><td align="center" valign="middle">', '<img qtip="{tips}" alt="{tips}" class="thumb-img" src="{imgUrl}" onerror="nofind()">', '</td></tr></table></div>','</div>','</tpl>', '<div class="x-clear"></div>');//tpl
                var dataView = new Ext.DataView({
                    store: store,
                    autoWidth:true,
                    autoHeight:true,
//                    width: 600,
//                    height: 400,
                    style: 'overflow:auto',
                    tpl: tpl,
                    multiSelect: true,
                    overClass: 'x-view-over',
                    itemSelector: 'div.thumb-wrap',
                    emptyText: '没有上传图片！',
                    plugins: [new Ext.DataView.DragSelector({
                        dragSafe: true
                    }), new Ext.DataView.LabelEditor({
                        dataIndex: 'title'
                    })],
                    prepareData: function(data){
                        data.sizeString = Ext.util.Format.fileSize(data.filesize);
                        data.id = data.d_id;
                        data.showtitle = (data.d_title.trim()) ? data.d_title : '无标题';
                        data.imgUrl = (data.d_imgurl.trim()) ? data.d_imgurl : '无图片';
                        data.tips = '标题：' + data.showtitle;
                        data.srcSmallImgUrl=data.d_src_imgurl;
                        data.srcBigImgUrl=data.d_src_httpurl;
                        return data;
                    },
                    listeners: {
                        selectionchange: function(dv, selections){
                            if (selections.length > 0) {
                                dataAction[1].enable();
                                dataAction[2].enable();
                                dataAction[3].enable();
                                dataAction[4].enable();
                                dataAction[5].enable();
                            }
                            else {
                                dataAction[1].disable();
                                dataAction[2].disable();
                                dataAction[3].disable();
                                dataAction[4].disable();
                                dataAction[5].disable();
                            }
                        },
                        dblclick: function(dv, index, node, e){
                            var data = store.getAt(index);
                            if (typeof data == 'object') {
//                                window.open(data.data.d_httpurl);
								window.open(data.data.d_httpurl,'image','top=200,left=300,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no, status=no');
                            }
                        }
                    }
                });//dataview
                var dataAction = [new Ext.Action({
                    id: 'addimage',
                    text: '上传',
					iconCls : 'icon-search',
                    handler: function(){
						Ext.Msg.show({
							title : '系统提示',
							msg : '上传图片!',
							buttons : Ext.Msg.OK,
							icon : Ext.MessageBox.INFO
						});
                    }
                }), new Ext.Action({
                    id: 'viewSrcSmallImage',
                    text: '查看原始缩略图',
                    disabled: true,
					iconCls : 'icon-search',
                    handler: function(){
                        var obj = dataView;
                        if (obj.isSelected) {
                            var datas = obj.getSelectedRecords();
                            for (var i = 0; i < datas.length; i++) {
                                var data = datas[i].data;
								window.open(data.srcSmallImgUrl,'image','height=600,width=800,top=200,left=300,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no, status=no');
                            }
                        }
                        else {
							Ext.Msg.show({
								title : '系统提示',
								msg : '上传图片!',
								buttons : Ext.Msg.OK,
								icon : Ext.MessageBox.INFO
							});
                        }
                    }
                }), new Ext.Action({
                    id: 'viewSrcBigImage',
                    text: '查看原始大图',
                    disabled: true,
					iconCls : 'icon-search',
                    handler: function(){
                        var obj = dataView;
                        if (obj.isSelected) {
                            var datas = obj.getSelectedRecords();
                            for (var i = 0; i < datas.length; i++) {
                                var data = datas[i].data;
								window.open(data.srcBigImgUrl,'doc','top=200,left=300,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no, status=no');
                            }
                        }
                        else {
	 						Ext.Msg.show({
								title : '系统提示',
								msg : '上传图片!',
								buttons : Ext.Msg.OK,
								icon : Ext.MessageBox.INFO
							});
                        }
                    }
                }), new Ext.Action({
                    id: 'viewbigimage',
                    text: '查看大图',
                    disabled: true,
					iconCls : 'icon-search',
                    handler: function(){
                        var obj = dataView;
                        if (obj.isSelected) {
                            var datas = obj.getSelectedRecords();
                            for (var i = 0; i < datas.length; i++) {
                                var data = datas[i].data;
                                window.open(data.imgUrl);
                            }
                        }
                        else {
							Ext.Msg.show({
								title : '系统提示',
								msg : '请选择一副图片!',
								buttons : Ext.Msg.OK,
								icon : Ext.MessageBox.ERROR
							});
                        }
                    }
                }),new Ext.Action({
                    id: 'delimage',
                    text: '删除',
					iconCls : 'icon-del',
                    disabled: true,
                    handler: function(){
                        Ext.Msg.alert('信息', '删除');
//                        var obj = dataView;
//                        if (obj.isSelected) {
//                            var datas = obj.getSelectedRecords();
//                            var ids = '', titles = '';
//                            for (var i = 0; i < datas.length; i++) {
//                                var data = datas[i].data;
//                                ids += data.id + ',';
//                                titles += ((data.title.trim()) ? data.title : '无标题') + '(' + data.filename + ')<br>';
//                            }
//                            ids = ids.substr(0, ids.length - 1);
//                            Ext.MessageBox.confirm('信息', '确定删除以下图片？<br>' + titles, function(btn){
//                                if (btn == 'yes') {
//                                    Ext.Ajax.request({
//                                        url: 'dataview_action.ashx?act=del&value=' + escape(ids),
//                                        scope: this,
//                                        scripts: true,
//                                        success: function(oResponse, op){
//                                            var msg = oResponse.responseText;
//                                            var returnData = Ext.util.JSON.decode(msg);
//                                            if (typeof returnData == "object") {
//                                                if (returnData.success == "true") {
//                                                    store.reload();
//                                                }
//                                                else {
//                                                    Ext.Msg.alert('信息', returnData.data);
//                                                }
//                                            }
//                                        },//success
//                                        failure: function(oResponse, op){
//                                            Ext.Msg.alert('信息', oResponse.responseText);
//                                        }
//                                    })
//                                }
//                            });
//                        }
//                        else {
//                            Ext.Msg.alert('信息', '发生错误');
//                        }
                    }
                }), new Ext.Action({
                    id: 'viewimage',
                    text: '查看',
                    disabled: true,
                    handler: function(){
                        var obj = dataView;
                        if (obj.isSelected) {
                            var datas = obj.getSelectedRecords();
                            for (var i = 0; i < datas.length; i++) {
                                var data = datas[i].data;
                                window.open(data.imgUrl);
                            }
                        }
                        else {
							Ext.Msg.show({
								title : '系统提示',
								msg : '请选择一副图片!',
								buttons : Ext.Msg.OK,
								icon : Ext.MessageBox.ERROR
							});
                        }
                    }
                })];
                
			 	app.pagesize_combo = new Ext.form.ComboBox({
							name : 'pagesize',
							hiddenName : 'pagesize',
							typeAhead : true,
							triggerAction : 'all',
							lazyRender : true,
							mode : 'local',
							store : new Ext.data.SimpleStore({
										fields : ['value', 'text'],
										data : [[10, '10条/页'], [15, '15条/页'], [20, '20条/页']]
									}),
							valueField : 'value',
							displayField : 'text',
							value : '20',
							editable : false,
							width : 85
				});
				var number = parseInt(app.pagesize_combo.getValue());
				app.pagesize_combo.on("select", function(comboBox){
							app.pageToolbar.pageSize = parseInt(comboBox.getValue());
							number = parseInt(comboBox.getValue());
							store.reload({
										params : {
											start : 0,
											limit : app.pageToolbar.pageSize
										}
									});
				});
			                
                app.pageToolbar = new Ext.PagingToolbar({
                    pageSize: app.limit,
                    displayInfo: true,
                    store: store,
                    items: ['-','&nbsp;&nbsp;', app.pagesize_combo,dataAction[0],'-', dataAction[1], '-',dataAction[2],'-', dataAction[3],'-', dataAction[4],'-', dataAction[5]]
                });
                
                
                var dataPanel = new Ext.Panel({
                    style: 'padding:0px 0px 5px 0px',
                    region: 'center',
                    title: '图库管理',
                    layout: 'fit',
                    autoScroll: true,
                    closable: true,
                    tbar: app.pageToolbar,
                    items: dataView
                });
                
                var viewport = new Ext.Viewport({
                    layout: 'border',
                    items: [dataPanel] //tree, 
                });
                
                var dragZone = new ImageDragZone(dataView, {
                    containerScroll: false,
                    ddGroup: 'treeDD',
                    handle:function(){
							Ext.Msg.show({
								title : '系统提示',
								msg : 'dAAG!',
								buttons : Ext.Msg.OK,
								icon : Ext.MessageBox.INFO
							});
                    }
	            });
                
                store.load({
					params : {
						start : 0,
						limit : app.limit
					}
                });
                
                /**
                nofind:function(){
                	Ext.MessageBox.alert('Notice','图片未找到!');
                	var img = event.srcElement;
                	img.src="images/loading32.gif";
                	img.onerror = null;
                }
                **/
                
            });//onReady

            function nofind(){
            	var img = document.getElementsByTagName("img")
            	img.src="images/loading32.gif";
            	img.onerror = null;
            }