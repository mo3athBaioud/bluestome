/**
 * 异常信息管理
 * 
 * @author XiongChun
 * @since 2010-05-20
 */
Ext.onReady(function() {
			var expander = new Ext.grid.RowExpander({
						tpl : new Ext.Template('<p style=margin-left:70px;><span style=color:Teal;>异常信息</span><br><span>{exceptionmsg}</span></p>',
								'<p style=margin-left:70px;><span style=color:Teal;>处理方案</span><br><span>{exceptionsolution}</span></p>',
								'<p style=margin-left:70px;><span style=color:Teal;>备注</span><br><span>{remark}</span></p>'),
						// 屏蔽双击事件
						expandOnDblClick : false
					});
			var sm = new Ext.grid.CheckboxSelectionModel();
			var cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), sm, expander, {
						header : '异常编号',
						dataIndex : 'exceptionid',
						hidden : false,
						hidden : false,
						width : 80,
						sortable : true
					}, {
						header : '异常代码',
						dataIndex : 'exceptioncode'
					}, {
						id : 'exceptionmsg',
						header : '异常信息',
						dataIndex : 'exceptionmsg'
					}, {
						id : 'exceptionsolution',
						header : '处理方案',
						dataIndex : 'exceptionsolution'
					}, {
						id : 'remark',
						header : '备注',
						dataIndex : 'remark'
					}]);

			/**
			 * 数据存储
			 */
			var store = new Ext.data.Store({
						proxy : new Ext.data.HttpProxy({
									url : './exception.ered?reqCode=queryExceptionsForManage'
								}),
						reader : new Ext.data.JsonReader({
									totalProperty : 'TOTALCOUNT',
									root : 'ROOT'
								}, [{
											name : 'exceptionid'
										}, {
											name : 'exceptioncode'
										}, {
											name : 'exceptionsolution'
										}, {
											name : 'exceptionmsg'
										}, {
											name : 'remark'
										}])
					});

			// 翻页排序时带上查询条件
			store.on('beforeload', function() {
						this.baseParams = {
							queryParam : Ext.getCmp('queryParam').getValue()
						};
					});

			var pagesize_combo = new Ext.form.ComboBox({
						name : 'pagesize',
						hiddenName : 'pagesize',
						typeAhead : true,
						triggerAction : 'all',
						lazyRender : true,
						mode : 'local',
						store : new Ext.data.ArrayStore({
									fields : ['value', 'text'],
									data : [[10, '10条/页'], [20, '20条/页'], [50, '50条/页'], [100, '100条/页'], [250, '250条/页'], [500, '500条/页']]
								}),
						valueField : 'value',
						displayField : 'text',
						value : '20',
						editable : false,
						width : 85
					});
			var number = parseInt(pagesize_combo.getValue());
			pagesize_combo.on("select", function(comboBox) {
						bbar.pageSize = parseInt(comboBox.getValue());
						number = parseInt(comboBox.getValue());
						store.reload({
									params : {
										start : 0,
										limit : bbar.pageSize
									}
								});
					});

			var bbar = new Ext.PagingToolbar({
						pageSize : number,
						store : store,
						displayInfo : true,
						displayMsg : '显示{0}条到{1}条,共{2}条',
						plugins : new Ext.ux.ProgressBarPager(), // 分页进度条
																	// emptyMsg
																	// :
																	// "没有符合条件的记录",
						items : ['-', '&nbsp;&nbsp;', pagesize_combo]
					});

			var grid = new Ext.grid.GridPanel({
						title : '<img src="./resource/image/ext/bug.png" align="top" class="IEPNG"><span style="font-weight:normal">异常信息表</span>',
						renderTo : 'exceptionGridDiv',
						height : 500,
						// width:600,
						autoScroll : true,
						region : 'center',
						store : store,
						loadMask : {
							msg : '正在加载表格数据,请稍等...'
						},
						stripeRows : true,
						frame : true,
						autoExpandColumn : 'exceptionmsg',
						cm : cm,
						sm : sm,
						plugins : expander,
						tbar : [{
									text : '新增',
									iconCls : 'page_addIcon',
									handler : function() {
										addInit();
									}
								}, '-', {
									text : '修改',
									iconCls : 'page_edit_1Icon',
									handler : function() {
										editInit();
									}
								}, '-', {
									text : '删除',
									iconCls : 'page_delIcon',
									handler : function() {
										deleteExceptionItems();
									}
								}, '-', {
									text : '刷新',
									iconCls : 'page_refreshIcon',
									handler : function() {
										queryExceptionItem();
									}
								}, '->', new Ext.form.TextField({
											id : 'queryParam',
											name : 'queryParam',
											emptyText : '请输入异常代码|异常信息',
											enableKeyEvents : true,
											listeners : {
												specialkey : function(field, e) {
													if (e.getKey() == Ext.EventObject.ENTER) {
														queryExceptionItem();
													}
												}
											},
											width : 150
										}), {
									text : '查询',
									iconCls : 'page_findIcon',
									handler : function() {
										queryExceptionItem();
									}
								}],
						bbar : bbar
					});
			store.load({
						params : {
							start : 0,
							limit : bbar.pageSize
						}
					});
			grid.on('rowdblclick', function(grid, rowIndex, event) {
						editInit();
					});
			grid.on('sortchange', function() {
						grid.getSelectionModel().selectFirstRow();
					});

			bbar.on("change", function() {
						grid.getSelectionModel().selectFirstRow();
					});

			var addExceptionFormPanel = new Ext.form.FormPanel({
						id : 'addExceptionFormPanel',
						name : 'addExceptionFormPanel',
						defaultType : 'textfield',
						labelAlign : 'right',
						labelWidth : 60,
						frame : true,
						items : [{
									fieldLabel : '异常代码',
									name : 'exceptioncode',
									id : 'exceptioncode',
									allowBlank : false,
									anchor : '99%'
								}, {
									fieldLabel : '异常信息',
									name : 'exceptionmsg',
									id : 'exceptionmsg',
									allowBlank : false,
									xtype : 'textarea',
									anchor : '99%'
								}, {
									fieldLabel : '处理方案',
									name : 'exceptionsolution',
									id : 'exceptionsolution',
									allowBlank : true,
									xtype : 'textarea',
									height : '100px',
									anchor : '99%'
								}, {
									fieldLabel : '备注',
									name : 'remark',
									allowBlank : true,
									anchor : '99%'
								}, {
									id : 'exceptionid',
									name : 'exceptionid',
									hidden : true
								}, {
									id : 'windowmode',
									name : 'windowmode',
									hidden : true
								}, {
									fieldLabel : '异常代码',
									name : 'exceptioncode_old',
									id : 'exceptioncode_old',
									anchor : '99%',
									hidden : true
								}]
					});

			var addExceptionWindow = new Ext.Window({
						layout : 'fit',
						width : 450,
						height : 300,
						resizable : false,
						draggable : true,
						closeAction : 'hide',
						title : '新增异常信息',
						iconCls : 'page_addIcon',
						modal : false,
						collapsible : true,
						titleCollapse : true,
						maximizable : false,
						buttonAlign : 'right',
						border : false,
						animCollapse : true,
						pageY : 20,
						pageX : document.body.clientWidth / 2 - 420 / 2,
						animateTarget : Ext.getBody(),
						constrain : true,
						items : [addExceptionFormPanel],
						buttons : [{
									text : '保存',
									iconCls : 'acceptIcon',
									handler : function() {
										var mode = Ext.getCmp('windowmode').getValue();
										if (mode == 'add')
											saveExceptionItem();
										else
											updateExceptionItem();
									}
								}, {
									text : '重置',
									id : 'btnReset',
									iconCls : 'tbar_synchronizeIcon',
									handler : function() {
										clearForm(addExceptionFormPanel.getForm());
									}
								}, {
									text : '关闭',
									iconCls : 'deleteIcon',
									handler : function() {
										addExceptionWindow.hide();
									}
								}]
					});

			/**
			 * 布局
			 */
			var viewport = new Ext.Viewport({
						layout : 'border',
						items : [grid]
					});

			/**
			 * 查询异常
			 */
			function queryExceptionItem() {
				store.load({
							params : {
								start : 0,
								limit : bbar.pageSize,
								queryParam : Ext.getCmp('queryParam').getValue()
							}
						});
			}

			/**
			 * 新增异常初始化
			 */
			function addInit() {
				// clearForm(addExceptionFormPanel.getForm());
				var flag = Ext.getCmp('windowmode').getValue();
				if (typeof(flag) != 'undefined') {
					addExceptionFormPanel.form.getEl().dom.reset();
				} else {
					clearForm(addExceptionFormPanel.getForm());
				}
				addExceptionWindow.show();
				addExceptionWindow.setTitle('新增异常信息');
				Ext.getCmp('windowmode').setValue('add');
				Ext.getCmp('btnReset').show();
			}

			/**
			 * 保存异常数据
			 */
			function saveExceptionItem() {
				if (!addExceptionFormPanel.form.isValid()) {
					return;
				}
				addExceptionFormPanel.form.submit({
							url : './exception.ered?reqCode=saveExceptionItem',
							waitTitle : '提示',
							method : 'POST',
							waitMsg : '正在处理数据,请稍候...',
							success : function(form, action) {
								addExceptionWindow.hide();
								store.reload();
								form.reset();
								Ext.MessageBox.alert('提示', action.result.msg);
							},
							failure : function(form, action) {
								var msg = action.result.msg;
								Ext.MessageBox.alert('提示', '异常数据保存失败:<br>' + msg);
							}
						});
			}

			/**
			 * 删除异常数据
			 */
			function deleteExceptionItems() {
				var rows = grid.getSelectionModel().getSelections();
				if (Ext.isEmpty(rows)) {
					Ext.Msg.alert('提示', '请先选中要删除的项目!');
					return;
				}
				var strChecked = jsArray2JsString(rows, 'exceptionid');
				Ext.Msg.confirm('请确认', '确认删除选中的异常信息吗?', function(btn, text) {
							if (btn == 'yes') {
								showWaitMsg();
								Ext.Ajax.request({
											url : './exception.ered?reqCode=deleteExceptionItems',
											success : function(response) {
												var resultArray = Ext.util.JSON.decode(response.responseText);
												store.reload();
												Ext.Msg.alert('提示', resultArray.msg);
											},
											failure : function(response) {
												var resultArray = Ext.util.JSON.decode(response.responseText);
												Ext.Msg.alert('提示', resultArray.msg);
											},
											params : {
												strChecked : strChecked
											}
										});
							}
						});
			}

			/**
			 * 修改异常初始化
			 */
			function editInit() {
				var record = grid.getSelectionModel().getSelected();
				if (Ext.isEmpty(record)) {
					grid.getSelectionModel().selectFirstRow();
				}
				addExceptionFormPanel.getForm().loadRecord(record);
				addExceptionWindow.show();
				addExceptionWindow.setTitle('修改异常信息');
				Ext.getCmp('windowmode').setValue('edit');
				Ext.getCmp('exceptionid').setValue(record.get('exceptionid'));
				Ext.getCmp('exceptioncode_old').setValue(record.get('exceptioncode'));
				Ext.getCmp('btnReset').hide();
			}

			/**
			 * 修改异常数据
			 */
			function updateExceptionItem() {
				if (!addExceptionFormPanel.form.isValid()) {
					return;
				}
				update();
			}

			/**
			 * 更新
			 */
			function update() {
				addExceptionFormPanel.form.submit({
							url : './exception.ered?reqCode=updateExceptionItem',
							waitTitle : '提示',
							method : 'POST',
							waitMsg : '正在处理数据,请稍候...',
							success : function(form, action) {
								addExceptionWindow.hide();
								store.reload();
								form.reset();
								Ext.MessageBox.alert('提示', action.result.msg);
							},
							failure : function(form, action) {
								var msg = action.result.msg;
								Ext.MessageBox.alert('提示', '异常信息修改失败:<br>' + msg);
							}
						});
			}
		});