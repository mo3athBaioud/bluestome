
Ext开发小窍门：

1.利用一个Window,From实现添加，修改功能.
 例如：
 				var record = app.grid.getSelectionModel().getSelected();
				app.add_form.getForm().loadRecord(record);
				app.add_win.show();
				add.add_win.setTitle("修改租机业务");
 其中app.add_form为独立的Form,app.add_win为独立的Window.
  此操作可以减少编码工作量，不至于出现添加，编辑2套代码。
 
 
2011-04-28
1.新增对输入条件非空的判断
2.需要后台数据库支持
3.增加一个终端销售模块
  某地区在售的所有终端数据，但是要区分是否裸机销售，需要在字段中给予标识，如果非裸机销售，则表明该终端参与租机业务，需要在租机查询中体现出，如果为裸机销售，则将这部分终端，在终端销售模块查询
  中体现出来。
  租机业务查询
  终端销售模块
4.系统进入测试阶段！
5.新增在没有查询到终端数据的情况下，让用户输入终端信息，用于保存到我们数据库.
6.TAC数据获取
  www.numberingplans.com
  u:liyang0906@vip.qq.com
  p:859600