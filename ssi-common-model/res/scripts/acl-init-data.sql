insert into TBL_CODE (D_ID, D_CODE_NAME, D_CREATE_DT, D_CODE_TP, D_STATUS, D_DESC, D_ISDEL)
values (2001, '固网支付', now(), 'D_PAYTYPEID', 1, '固网支付', 1);
insert into TBL_CODE (D_ID, D_CODE_NAME, D_CREATE_DT, D_CODE_TP, D_STATUS, D_DESC, D_ISDEL)
values (2002, '网银支付', now(), 'D_PAYTYPEID', 1, '网银支付', 1);
insert into TBL_CODE (D_ID, D_CODE_NAME, D_CREATE_DT, D_CODE_TP, D_STATUS, D_DESC, D_ISDEL)
values (2003, '支付宝支付', now(), 'D_PAYTYPEID', 1, '支付宝支付', 1);
insert into TBL_CODE (D_ID, D_CODE_NAME, D_CREATE_DT, D_CODE_TP, D_STATUS, D_DESC, D_ISDEL)
values (2004, '邮局汇款', now(), 'D_PAYTYPEID', 1, '邮局汇款', 1);
insert into TBL_CODE (D_ID, D_CODE_NAME, D_CREATE_DT, D_CODE_TP, D_STATUS, D_DESC, D_ISDEL)
values (2005, '支票支付', now(), 'D_PAYTYPEID', 1, '支票支付一二', 1);
insert into TBL_CODE (D_ID, D_CODE_NAME, D_CREATE_DT, D_CODE_TP, D_STATUS, D_DESC, D_ISDEL)
values (1001, 'SP操作员', now(), 'D_OPERATORTYPE', 1, '操作员类型', 1);
insert into TBL_CODE (D_ID, D_CODE_NAME, D_CREATE_DT, D_CODE_TP, D_STATUS, D_DESC, D_ISDEL)
values (1002, '系统操作员', now(), 'D_OPERATORTYPE', 1, '操作员类型', 1);
insert into TBL_CODE (D_ID, D_CODE_NAME, D_CREATE_DT, D_CODE_TP, D_STATUS, D_DESC, D_ISDEL)
values (1003, '超级管理员', now(), 'D_OPERATORTYPE', 1, '操作员类型', 1);
insert into TBL_CODE (D_ID, D_CODE_NAME, D_CREATE_DT, D_CODE_TP, D_STATUS, D_DESC, D_ISDEL)
values (6, 'SP操作员', now(), 'D_SPTYPEID', 1, 'SP类型', 1);
insert into TBL_CODE (D_ID, D_CODE_NAME, D_CREATE_DT, D_CODE_TP, D_STATUS, D_DESC, D_ISDEL)
values (7, '数据库对象', now(), 'D_OBJECTTYPE', 1, '权限对象类型', 1);
insert into TBL_CODE (D_ID, D_CODE_NAME, D_CREATE_DT, D_CODE_TP, D_STATUS, D_DESC, D_ISDEL)
values (8, '家电', now(), 'D_MERCHANT_TYPE', 1, '商品类型', 1);
insert into TBL_CODE (D_ID, D_CODE_NAME, D_CREATE_DT, D_CODE_TP, D_STATUS, D_DESC, D_ISDEL)
values (9, '食品', now(), 'D_MERCHANT_TYPE', 1, '商品类型', 1);
insert into TBL_CODE (D_ID, D_CODE_NAME, D_CREATE_DT, D_CODE_TP, D_STATUS, D_DESC, D_ISDEL)
values (10, 'ee', now(), 'D_ORDERTYPEID', 1, '订单类型', 1);
insert into TBL_CODE (D_ID, D_CODE_NAME, D_CREATE_DT, D_CODE_TP, D_STATUS, D_DESC, D_ISDEL)
values (11, 'ff', now(), 'D_ORDERTYPEID', 1, '订单类型', 1);
insert into TBL_CODE (D_ID, D_CODE_NAME, D_CREATE_DT, D_CODE_TP, D_STATUS, D_DESC, D_ISDEL)
values (1049, '数据库表对象', now(), 'D_OBJECT_TYPE', 1, '数据库表对象', 1);
insert into TBL_CODE (D_ID, D_CODE_NAME, D_CREATE_DT, D_CODE_TP, D_STATUS, D_DESC, D_ISDEL)
values (3002, 'B_B', now(), 'D_RETNTYPE', 1, '交易返回方式', 1);
insert into TBL_CODE (D_ID, D_CODE_NAME, D_CREATE_DT, D_CODE_TP, D_STATUS, D_DESC, D_ISDEL)
values (3003, 'B_C', now(), 'D_RETNTYPE', 1, '交易返回方式', 1);
insert into TBL_CODE (D_ID, D_CODE_NAME, D_CREATE_DT, D_CODE_TP, D_STATUS, D_DESC, D_ISDEL)
values (2, '固网支付SP',now(), 'D_SPTYPEID', 1, 'SP类型', 1);
insert into TBL_CODE (D_ID, D_CODE_NAME, D_CREATE_DT, D_CODE_TP, D_STATUS, D_DESC, D_ISDEL)
values (3, '卡友SP', now(), 'D_SPTYPEID', 1, 'SP类型', 1);
insert into TBL_CODE (D_ID, D_CODE_NAME, D_CREATE_DT, D_CODE_TP, D_STATUS, D_DESC, D_ISDEL)
values (4, '统一支付SP', now(), 'D_SPTYPEID', 1, 'SP类型', 1);


insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (176, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0818 ', '达县市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (177, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0819 ', '万县市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (178, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0825 ', '遂宁市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (179, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0826 ', '广安市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (181, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0830 ', '泸州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (182, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0831 ', '宜宾市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (183, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0832 ', '内江市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (184, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0833 ', '乐山市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (186, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0835 ', '雅安市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (187, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0836 ', '康定市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (188, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0837 ', '马尔康', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (189, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0838 ', '德阳市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (191, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0840 ', '泸州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (192, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0730 ', '岳阳市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (193, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0731 ', '长沙市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (194, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0732 ', '湘潭市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (196, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0734 ', '衡阳市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (197, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0735 ', '郴州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (198, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0736 ', '常德市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (199, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0737 ', '益阳市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (201, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0739 ', '邵阳市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (202, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0743 ', '吉首市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (203, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0744 ', '张家界', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (205, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0746 ', '永州冷', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (206, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0370 ', '商丘市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (207, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0371 ', '郑州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (208, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0372 ', '安阳市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (210, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0374 ', '许昌市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (211, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0375 ', '平顶山', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (212, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0376 ', '信阳市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (213, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0377 ', '南阳市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (215, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0379 ', '洛阳市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (216, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0391 ', '焦作市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (217, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0392 ', '鹤壁市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (218, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0393 ', '濮阳市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (220, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0395 ', '漯河市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (221, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0396 ', '驻马店', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (222, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0398 ', '三门峡', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (223, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0870 ', '昭通市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (225, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0872 ', '大理市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (226, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0873 ', '个旧市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (227, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0874 ', '曲靖市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (228, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0875 ', '保山市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (230, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0877 ', '玉溪市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (231, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0878 ', '楚雄市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (232, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0879 ', '思茅市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (233, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0691 ', '景洪市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (235, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0881 ', '东川市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (236, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0883 ', '临沧市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (237, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0886 ', '六库市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (238, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0887 ', '中甸市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (240, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0550 ', '滁州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (241, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0551 ', '合肥市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (242, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0552 ', '蚌埠市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (244, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0554 ', '淮南市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (245, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0555 ', '马鞍山', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (246, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0556 ', '安庆市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (247, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0557 ', '宿州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (249, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0559 ', '黄山市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (250, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0561 ', '淮北市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (251, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0562 ', '铜陵市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (252, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0563 ', '宣城市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (254, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0565 ', '巢湖市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (255, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0566 ', '贵池市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (256, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0951 ', '银川市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (257, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0952 ', '石嘴山', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (288, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0859 ', '兴义市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (289, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '029 ', '西安市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (290, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0910 ', '咸阳市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (291, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0911 ', '延安市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (293, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0913 ', '渭南市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (294, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0914 ', '商洛市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (295, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0915 ', '安康市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (296, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0916 ', '汉中市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (298, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0919 ', '铜川市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (299, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0971 ', '西宁市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (300, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0972 ', '海东市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (301, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0973 ', '同仁市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (303, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0975 ', '玛沁市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (304, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0976 ', '玉树市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (305, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0977 ', '德令哈', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (306, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0890 ', '儋州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (308, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0899 ', '三亚市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (310, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0892 ', '日喀则', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (311, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0893 ', '山南市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (273, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0773 ', '桂林市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (1, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '021', '上海', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (2, 'http://192.168.1.1/interface2', '010', '北京', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (101, 'http://192.168.1.1/interface5', '023', '重庆', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (102, 'http://192.168.1.1/interfact6', '852', '香港', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (90, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0795 ', '宜春市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (91, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0796 ', '吉安市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (93, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0798 ', '景德镇', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (94, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0799 ', '萍乡市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (95, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0701 ', '鹰潭市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (96, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0350 ', '忻州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (98, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0352 ', '大同市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (99, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0353 ', '阳泉市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (103, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0357 ', '临汾市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (104, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0358 ', '离石市', 1);

insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (105, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0359 ', '运城市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (106, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0930 ', '临夏市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (108, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0932 ', '定西市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (109, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0933 ', '平凉市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (110, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0934 ', '西峰市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (111, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0935 ', '武威市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (113, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0937 ', '酒泉市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (114, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0938 ', '天水市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (115, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0941 ', '甘南州', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (116, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0943 ', '白银市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (118, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0531 ', '济南市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (119, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0532 ', '青岛市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (120, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0533 ', '淄博市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (121, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0534 ', '德州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (123, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0536 ', '淮坊市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (124, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0537 ', '济宁市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (125, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0538 ', '泰安市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (126, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0539 ', '临沂市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (128, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0451 ', '哈尔滨', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (129, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0452', '齐齐哈尔', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (130, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0453 ', '牡丹江', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (132, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0455 ', '绥化市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (133, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0456 ', '黑河市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (134, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0457 ', '加格达奇', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (135, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0458 ', '伊春市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (137, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0591 ', '福州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (274, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0774 ', '梧州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (275, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0775 ', '玉林市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (276, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0776 ', '百色市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (277, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0777 ', '钦州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (278, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0778 ', '河池市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (279, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0779 ', '北海市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (280, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0851 ', '贵阳市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (281, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0852 ', '遵义市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (282, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0853 ', '安顺市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (283, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0854 ', '都均市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (284, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0855 ', '凯里市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (285, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0856 ', '铜仁市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (286, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0857 ', '毕节市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (287, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0858 ', '六盘水', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (292, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0912 ', '榆林市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (297, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0917 ', '宝鸡市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (302, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0974 ', '共和市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (307, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0898 ', '海口市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (309, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0891 ', '拉萨市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (9, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0310', '邯郸市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (10, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0311', '石家庄', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (11, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0312', '保定市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (12, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0313', '张家口', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (14, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0315', '唐山市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (15, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0316', '廊坊市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (16, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0317', '沧州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (17, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0318', '衡水市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (18, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0319', '邢台市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (20, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '05', '浙江省', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (21, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0570', '衢州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (22, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0571', '杭州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (23, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0572', '湖州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (25, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0574', '宁波市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (26, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0575', '绍兴市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (27, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0576', '台州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (28, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0577', '温州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (29, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0578', '丽水市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (31, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0580', '舟山市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (32, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '024', '沈阳市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (33, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0410', '铁岭市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (34, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0411', '大连市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (36, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0413', '抚顺市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (37, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0414', '本溪市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (38, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0415', '丹东市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (39, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0416', '锦州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (40, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0417', '营口市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (42, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0419', '辽阳市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (43, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0421', '朝阳市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (44, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0427', '盘锦市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (45, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0429', '葫芦岛', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (47, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0710 ', '襄城市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (48, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0711 ', '鄂州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (49, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0712 ', '孝感市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (50, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0713 ', '黄州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (52, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0715 ', '咸宁市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (53, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0716 ', '荆沙市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (54, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0717 ', '宜昌市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (55, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0718 ', '恩施市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (57, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0722 ', '随枣市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (58, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0724 ', '荆门市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (59, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0728 ', '江汉市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (60, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '025 ', '南京市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (61, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0510 ', '无锡市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (63, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0512 ', '苏州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (64, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0513 ', '南通市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (65, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0514 ', '扬州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (66, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0515 ', '盐城市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (68, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0517 ', '淮阴市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (69, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0517 ', '淮安市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (70, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0518 ', '连云港', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (71, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0519 ', '常州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (73, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0470 ', '海拉尔', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (74, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '471', '呼和浩特', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (75, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0472 ', '包头市', 1);

insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (76, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0473 ', '乌海市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (78, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0475 ', '通辽市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (79, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0476 ', '赤峰市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (80, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0477 ', '东胜市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (81, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0478 ', '临河市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (83, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0482 ', '乌兰浩特', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (84, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0483', '阿拉善左旗', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (85, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0790 ', '新余市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (86, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0791 ', '南昌市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (88, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0793 ', '上饶市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (89, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0794 ', '临川市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (259, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0954 ', '固原市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (260, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0431 ', '长春市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (261, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0432 ', '吉林市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (262, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0433 ', '延吉市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (263, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0434 ', '四平市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (264, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0435 ', '通化市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (265, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0436 ', '白城市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (266, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0437 ', '辽源市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (267, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0438 ', '松原市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (268, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0439 ', '浑江市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (269, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0440 ', '珲春市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (270, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0770 ', '防城港', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (271, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0771 ', '南宁市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (272, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0772 ', '柳州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (4, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '022', '天津市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (5, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '023', '重庆市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (6, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '852', '香港', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (7, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '853', '澳门', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (8, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '03', '河北省', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (13, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0314', '承德市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (19, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0335', '秦皇岛', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (24, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0573', '嘉兴市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (30, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0579', '金华市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (35, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0412', '鞍山市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (41, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0418', '阜新市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (46, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '027 ', '武汉市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (51, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0714 ', '黄石市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (56, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0719 ', '十堰市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (62, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0511 ', '镇江市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (67, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0516 ', '徐州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (72, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0523 ', '泰州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (77, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0474 ', '集宁市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (82, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0479 ', '锡林浩特', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (87, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0792 ', '九江市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (92, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0797 ', '赣州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (97, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0351 ', '太原市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (107, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0931 ', '兰州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (112, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0936 ', '张掖市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (117, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0530 ', '菏泽市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (122, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0535 ', '烟台市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (127, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0450 ', '阿城市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (131, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0454 ', '佳木斯', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (136, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0459 ', '大庆市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (141, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0595 ', '泉州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (146, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0599 ', '南平市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (151, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0754 ', '汕头市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (156, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0759 ', '湛江市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (161, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0766 ', '云浮市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (165, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0661 ', '潮阳市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (170, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0811 ', '重庆市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (175, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0817 ', '南充市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (180, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0827 ', '巴中市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (185, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0834 ', '西昌市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (190, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0839 ', '广元市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (195, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0733 ', '株州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (200, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0738 ', '娄底市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (204, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0745 ', '怀化市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (209, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0373 ', '新乡市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (214, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0378 ', '开封市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (219, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0394 ', '周口市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (224, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0871 ', '昆明市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (229, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0876 ', '文山市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (234, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0692 ', '潞西市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (239, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0888 ', '丽江市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (243, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0553 ', '芜湖市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (248, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0558 ', '阜阳市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (253, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0564 ', '六安市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (258, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0953 ', '吴忠市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (153, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0756 ', '珠海市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (154, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0757 ', '佛山市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (155, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0758 ', '肇庆市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (157, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0760 ', '中山市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (158, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0762 ', '河源市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (159, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0763 ', '清远市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (160, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0765 ', '顺德市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (162, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0768 ', '潮州市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (163, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0769 ', '东莞市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (164, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0660 ', '汕尾市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (166, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0662 ', '阳江市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (167, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0663 ', '揭西市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (168, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '028 ', '成都市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (169, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0810 ', '涪陵市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (171, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0812 ', '攀枝花', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (172, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0813 ', '自贡市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (173, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0814 ', '永川市', 1);
insert into TBL_ROUTE_CONFIG (D_ID, D_INTERFACES_ADDR, D_ZONE_NUM, D_CITY_NAME, D_STATUS)
values (174, 'http://192.168.1.58/Gw/InterfaceForDispatchOrder.asmx?wsdl', '0816 ', '绵阳市', 1);

insert into TBL_SPINFO (D_ID, D_SPSHORTNAME, D_SPCODE, D_SPNAME, D_MANAGERANGE, D_BANKNAME, D_BANKNO, D_LINKMAN_NAME, D_LINKMAN_TEL, D_LINKMAN_PHONE, D_FAX, D_EMAIL, D_COMPANY_ADDR, D_WEB_URL, D_MESSAGE_ADDR, D_ZIP, D_CUSTOMER_SERVICENAME, D_CUSTOMER_SERVICETEL, D_STATUS, D_KEY1, D_KEY2, D_KEY3, D_KEY4, D_SPTYPEID, D_CREATE_DT, D_PARENT_ID, D_AREA_CODE)
values (0, '无', '20021001', '无', '无', '无', '100000000011', '无', '20', '0', null, 'admin@mengniu.com', null, null, null, null, null, null, 2, '111', null, null, null, 11, null, 0, 1);
insert into TBL_SPINFO (D_ID, D_SPSHORTNAME, D_SPCODE, D_SPNAME, D_MANAGERANGE, D_BANKNAME, D_BANKNO, D_LINKMAN_NAME, D_LINKMAN_TEL, D_LINKMAN_PHONE, D_FAX, D_EMAIL, D_COMPANY_ADDR, D_WEB_URL, D_MESSAGE_ADDR, D_ZIP, D_CUSTOMER_SERVICENAME, D_CUSTOMER_SERVICETEL, D_STATUS, D_KEY1, D_KEY2, D_KEY3, D_KEY4, D_SPTYPEID, D_CREATE_DT, D_PARENT_ID, D_AREA_CODE)
values (1008, '蒙牛', '20021001', '内蒙古蒙牛集团', '奶制品', '中国银行', '100000000011', '牛根生', '20', '13838385438', null, 'niugensheng@mengniu.com', null, null, null, null, null, null, 2, '111', null, null, null, 11, null, 0, 1);
insert into TBL_SPINFO (D_ID, D_SPSHORTNAME, D_SPCODE, D_SPNAME, D_MANAGERANGE, D_BANKNAME, D_BANKNO, D_LINKMAN_NAME, D_LINKMAN_TEL, D_LINKMAN_PHONE, D_FAX, D_EMAIL, D_COMPANY_ADDR, D_WEB_URL, D_MESSAGE_ADDR, D_ZIP, D_CUSTOMER_SERVICENAME, D_CUSTOMER_SERVICETEL, D_STATUS, D_KEY1, D_KEY2, D_KEY3, D_KEY4, D_SPTYPEID, D_CREATE_DT, D_PARENT_ID, D_AREA_CODE)
values (1009, '三星电子', '1002', '三星电子有限公司', '电器', '中国农业银行', '95566022222', '张三', '120222', '19888999988', '5234111', 'qian@163.com', 't', 'http://www.com', 't', '200333', '李四', '13456788765', 1, '0', null, null, null, 6,now(), 0, 1);
insert into TBL_SPINFO (D_ID, D_SPSHORTNAME, D_SPCODE, D_SPNAME, D_MANAGERANGE, D_BANKNAME, D_BANKNO, D_LINKMAN_NAME, D_LINKMAN_TEL, D_LINKMAN_PHONE, D_FAX, D_EMAIL, D_COMPANY_ADDR, D_WEB_URL, D_MESSAGE_ADDR, D_ZIP, D_CUSTOMER_SERVICENAME, D_CUSTOMER_SERVICETEL, D_STATUS, D_KEY1, D_KEY2, D_KEY3, D_KEY4, D_SPTYPEID, D_CREATE_DT, D_PARENT_ID, D_AREA_CODE)
values (1010, '诺基亚', '071', '诺基亚公司', '手机', '中国银行', '1111111', '李', '211110011', '12345678911', '1233333', 'qian@163.com', 'no', 'http://www.com', 'no', '100000', '唐', '23022222', 1, '0', null, null, null, 6, now(), 0, 1);
insert into TBL_SPINFO (D_ID, D_SPSHORTNAME, D_SPCODE, D_SPNAME, D_MANAGERANGE, D_BANKNAME, D_BANKNO, D_LINKMAN_NAME, D_LINKMAN_TEL, D_LINKMAN_PHONE, D_FAX, D_EMAIL, D_COMPANY_ADDR, D_WEB_URL, D_MESSAGE_ADDR, D_ZIP, D_CUSTOMER_SERVICENAME, D_CUSTOMER_SERVICETEL, D_STATUS, D_KEY1, D_KEY2, D_KEY3, D_KEY4, D_SPTYPEID, D_CREATE_DT, D_PARENT_ID, D_AREA_CODE)
values (1011, '上海帕科', 'c001', '上海帕科', '家电', '中国农业银行', '1000000111111', '张某某', '52832900', '15299022234', '52832900', 'test@163.com', '上海市', 'http://www.test.com', '上海市', '200333', '李某某', '5345987', 1, '111', null, null, null, 3, now(), 0, 1);


insert into TBL_OPERATOR (D_ID, D_LOGIN_NAME, D_PASSWORD, D_REAL_NAME, D_STATUS, D_PHONE, D_MOBILE, D_EMAIL, D_FAX, D_QQ, D_MSN, D_OPERATORTYPE, D_SPID, D_CREATE_DT)
values (0, 'admin', 'admin1', '管理员', 1, '021-52382999-8029', '15800371329', 'aaa@21cn.com', '021-52382999-8029', '52382999', 'aaa@21cn.com', 1002, 1, now());

insert into TBL_ROLE (D_ID, D_ROLE_NAME, D_ROLE_DESC, D_ROLE_STATUS, D_CREATE_DT)
values (1, 'SUPERADMIN', '超级管理员', 1, now());
insert into TBL_ROLE (D_ID, D_ROLE_NAME, D_ROLE_DESC, D_ROLE_STATUS, D_CREATE_DT)
values (2, 'GUEST', '访问者', 1, now());

insert into TBL_OPERATOR_ROLE (D_ID, D_OPERATORID, D_ROLEID, D_CREATE_DT)
values (182, 1, 1, now());


--undo--
insert into TBL_PRIVILEGEOBJECT (D_ID, D_OBJECTINDEX, D_OBJECTID, D_OBJECTNAME, D_OBJECTRIGHTS, D_STATUS)
values (103, 16, 'TBL_SECURITY_KEY', '密钥管理', '1##2##3##4##', 1);
insert into TBL_PRIVILEGEOBJECT (D_ID, D_OBJECTINDEX, D_OBJECTID, D_OBJECTNAME, D_OBJECTRIGHTS, D_STATUS)
values (104, 17, 'TBL_ROUTE_CONFIG', '路由配置', '1##2##3##4##', 1);
insert into TBL_PRIVILEGEOBJECT (D_ID, D_OBJECTINDEX, D_OBJECTID, D_OBJECTNAME, D_OBJECTRIGHTS, D_STATUS)
values (105, 18, 'TBL_PRODUCT', '产品表', '1##2##3##4##', 1);
insert into TBL_PRIVILEGEOBJECT (D_ID, D_OBJECTINDEX, D_OBJECTID, D_OBJECTNAME, D_OBJECTRIGHTS, D_STATUS)
values (106, 19, 'TBL_PAYORG', '支付组织表', '1##2##3##4##', 1);
insert into TBL_PRIVILEGEOBJECT (D_ID, D_OBJECTINDEX, D_OBJECTID, D_OBJECTNAME, D_OBJECTRIGHTS, D_STATUS)
values (107, 19, 'TBL_ORDER', '订单表', '1##2##3##4##', 1);
insert into TBL_PRIVILEGEOBJECT (D_ID, D_OBJECTINDEX, D_OBJECTID, D_OBJECTNAME, D_OBJECTRIGHTS, D_STATUS)
values (108, 21, 'TBL_MONITOR', '监控表', '1##2##3##4##', 1);
insert into TBL_PRIVILEGEOBJECT (D_ID, D_OBJECTINDEX, D_OBJECTID, D_OBJECTNAME, D_OBJECTRIGHTS, D_STATUS)
values (109, 22, 'TBL_LOGINLOG', '登陆日志表', '1##2##3##4##', 1);
insert into TBL_PRIVILEGEOBJECT (D_ID, D_OBJECTINDEX, D_OBJECTID, D_OBJECTNAME, D_OBJECTRIGHTS, D_STATUS)
values (110, 23, 'TBL_CONFIG', '监控配置表', '1##2##3##4##', 1);
insert into TBL_PRIVILEGEOBJECT (D_ID, D_OBJECTINDEX, D_OBJECTID, D_OBJECTNAME, D_OBJECTRIGHTS, D_STATUS)
values (1, 1, 'TBL_CODE', '代码表', '1##2##3##4##', 1);
insert into TBL_PRIVILEGEOBJECT (D_ID, D_OBJECTINDEX, D_OBJECTID, D_OBJECTNAME, D_OBJECTRIGHTS, D_STATUS)
values (2, 2, 'TBL_ROLE', '角色表', '1##2##3##4##', 1);
insert into TBL_PRIVILEGEOBJECT (D_ID, D_OBJECTINDEX, D_OBJECTID, D_OBJECTNAME, D_OBJECTRIGHTS, D_STATUS)
values (3, 3, 'TBL_OPERATOR', '操作员表', '1##2##3##4##', 1);
insert into TBL_PRIVILEGEOBJECT (D_ID, D_OBJECTINDEX, D_OBJECTID, D_OBJECTNAME, D_OBJECTRIGHTS, D_STATUS)
values (5, 5, 'TBL_SYSMENU', '菜单表', '1##2##3##4##', 1);
insert into TBL_PRIVILEGEOBJECT (D_ID, D_OBJECTINDEX, D_OBJECTID, D_OBJECTNAME, D_OBJECTRIGHTS, D_STATUS)
values (7, 7, 'TBL_PRIVILEGEOBJECT', '权限对象表', '1##2##3##4##', 1);
insert into TBL_PRIVILEGEOBJECT (D_ID, D_OBJECTINDEX, D_OBJECTID, D_OBJECTNAME, D_OBJECTRIGHTS, D_STATUS)
values (12, 12, 'TBL_DICTIONARY', '字典表', '1##2##3##4##', 1);
insert into TBL_PRIVILEGEOBJECT (D_ID, D_OBJECTINDEX, D_OBJECTID, D_OBJECTNAME, D_OBJECTRIGHTS, D_STATUS)
values (4, 4, 'TBL_OPLOG', '操作日志表', '1##2##3##4##', 1);
insert into TBL_PRIVILEGEOBJECT (D_ID, D_OBJECTINDEX, D_OBJECTID, D_OBJECTNAME, D_OBJECTRIGHTS, D_STATUS)
values (6, 6, 'TBL_ROLE_SYSMENU', '角色菜单表', '1##2##3##4##', 1);
insert into TBL_PRIVILEGEOBJECT (D_ID, D_OBJECTINDEX, D_OBJECTID, D_OBJECTNAME, D_OBJECTRIGHTS, D_STATUS)
values (100, 13, 'TBL_SPINFO', '商户信息', '1##2##3##4##', 1);

insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (21, '登陆日志', '/module/log/loginLog.jsp', 1, 1, '10011101', '100111', now(), '无');
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (114, 'test', '/module/test.jsp', 1, 2, '15001', '0', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (203, '订单扭转日志', '/module/log/orderLog.jsp', 1, 1, '10011103', '100111', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (204, '账单生成日志', '/module/log/billLogCreate.jsp', 1, 1, '10011104', '100111', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (20, '订单管理', '/module/order/order.jsp', 1, 1, '7001', '0', now(), 'null');
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (3, '商品管理', '/module/product/product.jsp', 1, 1, '8001', '0', now(), 'product-manager');
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (100, 'SP操作员管理', '/module/sys/spOperator.jsp', 1, 1, '1100101', '11001', now(), 'null');
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (101, '系统操作员管理', '/module/sys/sysOperator.jsp', 1, 1, '1100102', '11001', now(), 'null');
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (22, '权限管理', '/module/rolerights/roleList.jsp', 1, 1, '10001', '0', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (23, '角色管理', '/module/rolerights/roleList.jsp', 1, 1, '1000101', '10001', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (24, '权限对象管理', '/module/rolerights/PrivilegeObjectList.jsp', 1, 1, '1000102', '10001', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (110, '抽成规则', '/', 1, 1, '4001', '0', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (77, '商品管理', '/module/product/index.jsp', 1, 1, '800101', '8001', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (66, '帐单管理', '/module/bill/index.jsp', 1, 1, '300101', '3001', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (202, '路由配置管理', '/module/sys/routeConfig.jsp', 1, 1, '1200102', '12001', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (6, '操作日志管理', '/module/sys/oplog.jsp', 1, 1, '10011102', '100111', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (113, '根菜单', '/module/index.jsp', 1, 2, '0', '0', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (26, '我的帐单', '/module/bill/index.jsp', 1, 1, '900101', '9001', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (27, '我的商品', '/module/product/productsp.jsp', 1, 1, '900102', '9001', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (116, '我的订单', '/module/order/order.jsp', 1, 1, '900104', '9001', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (201, '一二三', '/test/index.jsp', 1, 2, '20001', '0', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (11, '账单管理', '/module/', 1, 1, '3001', '0', now(), 'bill-manager');
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (8, '我的订单/帐单', '/index.jsp', 1, 1, '9001', '0',now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (5, '代码管理', '/module/sys/code.jsp', 2, 1, '100103', '1001', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (9, '菜单管理', '/module/sys/sysMenu.jsp', 1, 1, '100104', '1001',now(), 'null');
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (10, 'SP管理/SP商品', '/module/', 1, 1, '2001', '0', now(), 'sp-manager');
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (1, '系统管理', '/module/sys/index.jsp', 1, 1, '1001', '0', now(), 'null');
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (2, '角色管理', '/module/sys/role.jsp', 2, 1, '100101', '1001', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (4, '操作员管理', '/module/sys/operator.jsp', 2, 1, '100102', '1001', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (15, '账单报表', '/module/reports/bill/index.jsp', 1, 1, '500101', '5001', now(), 'null');
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (16, '日报', '/module/reports/bill/day.jsp', 1, 1, '50010101', '500101', now(), 'bill_day_manager000');
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (17, '周报', '/module/reports/bill/week.jsp', 1, 1, '50010102', '500101', now(), 'bill_week_manager');
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (18, '月报', '/module/reports/bill/month.jsp', 1, 1, '50010103', '500101', now(), 'bill_month_manager');
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (19, '年报', '/module/reports/bill/year.jsp', 1, 1, '50010104', '500101', now(), 'bill_year_manager');
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (13, '统计报表', '/module/reports/index.jsp', 1, 1, '5001', '0', now(), 'reports-manager');
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (102, '测试菜单', '/module/test/index.jsp', 2, 2, '100110', '1001', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (103, '操作员管理', '/module/operator/', 1, 1, '11001', '0', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (104, '系统配置管理', '/module/sysconfig/', 1, 1, '12001', '0',now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (105, '基础数据管理', '/module/sysconfig/basedata', 1, 1, '1200101', '12001', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (106, '支付类型', '/module/sys/payType.jsp?type=D_PAYTYPEID', 1, 1, '120010101', '1200101', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (107, '支付机构', '/module/payorg/index.jsp', 1, 1, '120010102', '1200101', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (108, '操作员类型', '/module/sys/operatorType.jsp?type=D_OPERATORTYPE', 1, 1, '120010103', '1200101', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (109, '其他类型', '/module/sys/otherType.jsp', 1, 1, '120010104', '1200101', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (7, '信息管理', '/module/sp/spinfo.jsp', 1, 1, '900103', '9001', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (115, '系统日志', '/module/syslog/index.jsp', 1, 1, '100111', '1001', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (88, 'SP管理', '/module/sp/spinfo.jsp', 1, 1, '200101', '2001', now(), null);
insert into TBL_SYSMENU (D_ID, D_MENU_NAME, D_MENU_URL, D_STATUS, D_ISDEL, D_MENU_ID, D_PARENT_MENU_ID, D_CREATE_DT, D_VISIT_NAME)
values (85, '抽成管理', '/module/sharerule/sharerule.jsp', 1, 1, '400101', '4001', now(), null);

insert into TBL_ROLE_PRIV (D_ID, D_ROLEID, D_PRIVOBJID, D_DELETE, D_CREATE_DT, D_UPDATE, D_INSERT, D_SELECT)
values (453, 2, 6, 2, to_date('02-02-2009 10:03:54', 'dd-mm-yyyy hh24:mi:ss'), 2, 1, 1);
insert into TBL_ROLE_PRIV (D_ID, D_ROLEID, D_PRIVOBJID, D_DELETE, D_CREATE_DT, D_UPDATE, D_INSERT, D_SELECT)
values (451, 2, 4, 2, to_date('02-02-2009 10:03:54', 'dd-mm-yyyy hh24:mi:ss'), 2, 2, 1);
insert into TBL_ROLE_PRIV (D_ID, D_ROLEID, D_PRIVOBJID, D_DELETE, D_CREATE_DT, D_UPDATE, D_INSERT, D_SELECT)
values (452, 2, 11, 1, to_date('02-02-2009 10:03:54', 'dd-mm-yyyy hh24:mi:ss'), 1, 1, 1);
insert into TBL_ROLE_PRIV (D_ID, D_ROLEID, D_PRIVOBJID, D_DELETE, D_CREATE_DT, D_UPDATE, D_INSERT, D_SELECT)
values (455, 1, 6, 1, to_date('03-02-2009 10:10:44', 'dd-mm-yyyy hh24:mi:ss'), 1, 1, 1);
insert into TBL_ROLE_PRIV (D_ID, D_ROLEID, D_PRIVOBJID, D_DELETE, D_CREATE_DT, D_UPDATE, D_INSERT, D_SELECT)
values (456, 1, 4, 1, to_date('03-02-2009 10:10:44', 'dd-mm-yyyy hh24:mi:ss'), 1, 1, 1);
insert into TBL_ROLE_PRIV (D_ID, D_ROLEID, D_PRIVOBJID, D_DELETE, D_CREATE_DT, D_UPDATE, D_INSERT, D_SELECT)
values (457, 1, 3, 1, to_date('03-02-2009 10:10:44', 'dd-mm-yyyy hh24:mi:ss'), 1, 1, 1);
insert into TBL_ROLE_PRIV (D_ID, D_ROLEID, D_PRIVOBJID, D_DELETE, D_CREATE_DT, D_UPDATE, D_INSERT, D_SELECT)
values (458, 1, 2, 1, to_date('03-02-2009 10:10:44', 'dd-mm-yyyy hh24:mi:ss'), 1, 1, 1);
insert into TBL_ROLE_PRIV (D_ID, D_ROLEID, D_PRIVOBJID, D_DELETE, D_CREATE_DT, D_UPDATE, D_INSERT, D_SELECT)
values (459, 1, 1, 1, to_date('03-02-2009 10:10:44', 'dd-mm-yyyy hh24:mi:ss'), 1, 1, 1);
insert into TBL_ROLE_PRIV (D_ID, D_ROLEID, D_PRIVOBJID, D_DELETE, D_CREATE_DT, D_UPDATE, D_INSERT, D_SELECT)
values (460, 1, 7, 1, to_date('03-02-2009 10:10:44', 'dd-mm-yyyy hh24:mi:ss'), 1, 1, 1);
insert into TBL_ROLE_PRIV (D_ID, D_ROLEID, D_PRIVOBJID, D_DELETE, D_CREATE_DT, D_UPDATE, D_INSERT, D_SELECT)
values (454, 1, 11, 1, to_date('03-02-2009 10:10:44', 'dd-mm-yyyy hh24:mi:ss'), 1, 1, 1);
