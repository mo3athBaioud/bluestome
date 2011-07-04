
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
1.新增对输入条件非空的判断 未完成
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
  
7.IMEI（TAC）数据库的设计
  数据库重新设计
  终端和TAC表关联

  
泡泡网终端属性列表,对应索引：
属性[0]:产品名称
属性[1]:产品图片
属性[2]:产品价格
属性[3]:基本属性
属性[4]:手机昵称
属性[5]:手机制式
属性[6]:支持频段
属性[7]:网络连接
属性[8]:操作系统
属性[9]:视频通话
属性[10]:标配电池
属性[11]:理论通话时间
属性[12]:视频通话时间
属性[13]:理论待机时间
属性[14]:CPU
属性[15]:内存容量
属性[16]:扩展卡
属性[17]:显示芯片
属性[18]:手机铃声
属性[19]:标配
属性[20]:显示屏幕
属性[21]:手机屏幕
属性[22]:主屏颜色
属性[23]:分辨率(宽×高)
属性[24]:主屏类型
属性[25]:副屏尺寸
属性[26]:副屏颜色
属性[27]:副屏分辨率
属性[28]:副屏类型
属性[29]:触摸屏
属性[30]:外观设计
属性[31]:外观样式
属性[32]:机身颜色
属性[33]:尺寸(长宽厚)
属性[34]:产品重量
属性[35]:娱乐功能
属性[36]:摄像头像素
属性[37]:摄像头
属性[38]:闪光灯
属性[39]:摄像头描述
属性[40]:场景模式
属性[41]:副摄像头
属性[42]:定时拍摄
属性[43]:连拍功能
属性[44]:照片质量
属性[45]:视频拍摄
属性[46]:耳机接口
属性[47]:扬声器
属性[48]:音乐格式
属性[49]:视频格式
属性[50]:数字电视
属性[51]:TV-OUT
属性[52]:收音机
属性[53]:Flash播放
属性[54]:游戏
属性[55]:电子书
属性[56]:内置聊天软件
属性[57]:重力感应
属性[58]:传输功能
属性[59]:GPS导航
属性[60]:蓝牙
属性[61]:数据线接口
属性[62]:Wi-Fi/WAPI
属性[63]:辅助定位
属性[64]:网络功能
属性[65]:WAP浏览器
属性[66]:WWW浏览器
属性[67]:电子邮件
属性[68]:短信息
属性[69]:彩信
属性[70]:短信群发
属性[71]:商务功能
属性[72]:飞行模式
属性[73]:语音命令
属性[74]:隐私安全
属性[75]:日程表
属性[76]:世界时钟
属性[77]:OFFICE套件
属性[78]:扩展性能
属性[79]:Java扩展
属性[80]:光线感应
属性[81]:距离感应
属性[82]:基本功能
属性[83]:中文输入方式
属性[84]:话机通讯录
属性[85]:免提接听
属性[86]:来电识别
属性[87]:闹钟
属性[88]:防火墙
属性[89]:日历
属性[90]:主题模式
属性[91]:计算器
属性[92]:情景模式
属性[93]:录音功能
属性[94]:其他

对客户的处理，都是通过手机号码作为输入条件。
 只是后台在匹配数据是，需要根据匹配优先级来
   首先匹配通话日志
   如果通话日志中没有数据，则匹配WAP日志。
处理流程：
    先通话日志
    后WAP日志,没有IMEI数据，有手机号码，品牌，型号。通过型号关联到我们终端库。

移动数据解析IMEI,将IMEI

查询中，将IEMI查询条件从查询表单中去掉。

数据展现并非实时。需要有数据导入的过程

2011-05-12
1.新增几个员工号，只有访问数据查询的GRPS数据查询业务。权限系统要赶快弄了。
 4个帐号，只有GRPS查询功能。
2.关联终端属性，1W多个终端数据。
3.从1W多个终端中匹配出支持GPRS的终端，并归类


厂商常量：
SonyEricsson 
MOT
Nokia
Huawei
SAMSUNG

2011-05-14
1.针对员工号，展现不同的内容。
  a.为员工号分配不同段的号码资源。即不同员工看到的内容不一样。
2.导出GRPS清单。 数据处理完,等待
3.建议，对于终端数据的获取，特别是基本属性判断，需要经过几层。即数据不可能一次性完全获取到位。需要不同网站之间的数据协调。

94.72% 9472条 厂商机型识别

2011-05-25
区分渭南市下的数据，同时对几个帐号的数据进行分配,四个工号.
3，5工号查询的数据内容进行变更。
目的是让
1，2号负责一个渭南，
3，大理，
5.浦城。
目的是让移动明白，我们这个系统覆盖的范围。

区域码:
蒲城：E0E7 -> weinan3
大荔：E0EA -> weinan5
临渭区：E0E1 -> weinan1|weinan2

2011-05-26
1.检查weinan5中相同机型下的数据出现不同的GPRS支持的属性。要特别注意weinan5中的数据。

2011-06-02
将李洋给予的数据进行机型匹配,然后将匹配出的数据,然后从中选200个左右的数据。
然后等比删除原有数据中的数据,并且表示为支持GPRS.


2011-06-05
从2011-06-03_GPRS.csv
共四天,5-30,5-31,6-01,6-02
渭南 -> 10个/天
大荔 -> 5个/天
蒲城 -> 5个/天


需要新增业务包表，用于管理移动当前使用的业务包数据。

所选数据字段
号码，业务区，套餐编码，套餐编码对应中文，办理时间，厂商，机型

导入的数据格式不需要标明GPRS套餐名称套餐代码这些。


号谱管家要求
1.终端能上网
2.终端必须为智能手机。


2011-06-24
1.登录界面不能有选择，直接是渭南系统登录。
2.权限分配系统。
  员工与区位码匹配，只能查询到对应区位码的数据。
  设计区局表和渠道表关系表，然后就是员工与渠道关系表
  [员工表]
  create table tbl_staff(
	  d_id  int(4) not null AUTO_INCREMENT,
	  d_username varchar(16) not null,
	  d_password varchar(16) not null,
	  d_mobile varchar(11),
	  d_officephone varchar(13) not null,
	  d_channel_code varchar(50) not null,
	  d_status int(4) default 1,
	  d_createtime timestamp default current_timestamp,
	  PRIMARY KEY (d_id),
	  UNIQUE INDEX tbl_staff_username (d_username)
  );
  
  [渠道表]
  create table tbl_channel(
	  d_channel_code varchar(50) not null,
	  d_channel_name varchar(50) not null,
	  d_address varchar(128),
	  d_status int(4) default 1,
	  d_bdcode varchar(50) not null,
	  d_createtime timestamp default current_timestamp,
	  UNIQUE INDEX tbl_channel_code (d_channel_code)
  );
  
  [业务区]
  create table tbl_bdistrict(
	  d_code varchar(50) NOT NULL,
	  d_name varchar(50) NOT NULL,
	  d_parent_code varchar(50) DEFAULT '0000',
	  d_description varchar(256),
	  d_status int(4) default 1,
	  d_createtime timestamp default current_timestamp,
	  UNIQUE INDEX tbl_bdistrict_code (d_code)
  );
  
  渠道编码，业务区，营业厅名称
  
  
  业务区表 代表某个县区
  
  一个营业厅只有一个渠道编码，一个营业厅只有一个工号。
  工号 -> 渠道编码 -> 业务区 -> 地市
  所属数据需要
  
  
  健云网络
  jack.zhang@gmail.com
  
  
  2011-07-02
  终端基本参数：
  1.GPRS  数据业务： 查找是否有GPRS字眼
  2.WAP   WAP浏览器：查找是否有WAP字眼
  3.智能手机  手机类型： 查找是否存在智能手机字眼.
  4.操作系统  操作系统： 查找操作系统名称.
  5.WIFI      WLAN功能：查找是否有WIFI.
  思考:
      智能手机: 是否支持GPRS,WAP业务。
  2011-07-03
  1.业务数据导入，
    如果导入的数据已经存在系统中，此数据不导入，如果导入的数据不存在系统中，并且是换机用户。
    此时，删除系统中原有的终端数据。导入新的终端数据。
  2.业务查询显示字段
    用户号码，支持业务情况，支持业务形式，查询次数

    用户号码，厂商，机型，IMEI,支持业务情况，支持业务形式，查询次数

 2011-07-03
 [终端属性表]
  create table tbl_terminal_property(
	  d_id  int(4) not null AUTO_INCREMENT,
	  d_hsman varchar(32) not null,
	  d_hstype varchar(32) not null,
	  d_gprs int(4) default 0,
	  d_wap int(4) default 0,
	  d_smartphone int(4) default 0,
	  d_os int(4) default 0,
	  d_wifi int(4) default 0,
	  d_createtime timestamp default current_timestamp,
	  PRIMARY KEY (d_id)
 );

【16业务表】
 create table tbl_bussiness_{id}(
	  d_id  int(4) not null AUTO_INCREMENT,
	  d_phonenum varchar(11) not null,
	  d_hsman varchar(32),
	  d_hstype varchar(32),
	  d_imei varchar(16),
	  d_support int(4) default 0,
	  d_support_type int(4) default 0,
	  PRIMARY KEY (d_id)
 );

【单一业务表】通过TYPE来区分每个业务
CREATE TABLE `tbl_bussiness` (
	`d_id` INT(4) NOT NULL AUTO_INCREMENT,
	`d_btype` INT(4) NOT NULL,
	`d_bdistrict` VARCHAR(15) NOT NULL COMMENT '业务区',
	`d_phonenum` VARCHAR(11) NOT NULL,
	`d_hsman` VARCHAR(32) NULL DEFAULT NULL,
	`d_hstype` VARCHAR(32) NULL DEFAULT NULL,
	`d_imei` VARCHAR(16) NULL DEFAULT NULL,
	`d_support` INT(4) NULL DEFAULT '0',
	`d_support_type` INT(4) NULL DEFAULT '0',
	PRIMARY KEY (`d_id`),
	INDEX `IDX_BTYPE_PHONENUM` (`d_btype`, `d_phonenum`),
	UNIQUE INDEX `d_btype_d_phonenum` (`d_btype`, `d_phonenum`, `d_imei`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

【日志表】
CREATE TABLE `tbl_noplog` (
	`d_id` INT(4) NOT NULL AUTO_INCREMENT ,
	`d_uid` INT(4) NOT NULL COMMENT '员工ID',
	`d_phonenum` VARCHAR(11) NOT NULL COMMENT '号码',
	`d_phonenum_bdistrict` VARCHAR(11) NOT NULL COMMENT '号码所属业务区',
	`d_btype` VARCHAR(32) NULL DEFAULT NULL COMMENT '业务类型',
	`d_loginname` VARCHAR(32) NOT NULL COMMENT '工号',
	`d_loginname_bdistrict` VARCHAR(32) NOT NULL COMMENT '员工所属业务去',
	`d_createtime` timestamp default current_timestamp COMMENT '查询时间',
	`d_result` int(4) default 0 COMMENT '查询结果',
	PRIMARY KEY (`d_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

【终端核心表】
CREATE TABLE `tbl_terminal_core` (
	`d_id` INT(4) NOT NULL AUTO_INCREMENT ,
	`d_hsman` VARCHAR(32) NOT NULL COMMENT '厂商',
	`d_hsman_ch` VARCHAR(32) NOT NULL COMMENT '厂商(中文)',
	`d_hstype` VARCHAR(32) NOT NULL COMMENT '机型',
	`d_hstype_ch` VARCHAR(32) NOT NULL COMMENT '机型(中文)',
	`d_tac` VARCHAR(8) NOT NULL COMMENT 'TAC码',
	`d_gprs` int(4) default 0 COMMENT '是否支持GPRS',
	`d_wap` int(4) default 0 COMMENT '是否支持WAP',
	`d_smartphone` int(4) default 0 COMMENT '是否智能手机',
	`d_os` int(4) default 0 COMMENT '是否有操作系统',
	`d_wifi` int(4) default 0 COMMENT '是否支持WIFI',
	`d_createtime` timestamp default current_timestamp COMMENT '创建时间',
	PRIMARY KEY (`d_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

日志导出功能，可以提供时间区间的选择。

定义16类业务的业务ID
1.无线音乐高级俱乐部会员
2.139邮箱
3.飞信会员
4.号簿管家
5.全曲下载
6.手机报
7.手机视频
8.手机阅读
9.手机游戏
10.手机电视
11.移动MM
12.GPRS流量包
13.彩信包
14.手机支付
15.WIFI
16.手机地图

--数据库脚本
select distinct trim(lower(d_hsman_name_en)) as name from tbl_tac where trim(lower(d_hsman_name_en)) = 'alcatel';
select distinct lower(d_hsman) as name from tbl_terminal_property;
select distinct b.d_hsman,b.d_hstype,a.d_tac,b.d_gprs,b.d_wap,b.d_smartphone,b.d_os,b.d_wifi from tbl_tac a, tbl_terminal_property b where trim(lower(a.d_hsman_name_en)) = trim(lower(b.d_hsman));

2011-07-04
1.终端数据适配

2.业务数据导入
  导入流程
  业务导入数据格式:
  业务区,号码,厂商,机型,IMEI,业务是否支持,业务支持类型
  'E0E1','15800371329','魅族','M8','354707041147044',1,1

缺少对核心数据的管理。 需要有对应的页面来支撑。
终端识别功能未完成。
终端匹配功能未完成。
