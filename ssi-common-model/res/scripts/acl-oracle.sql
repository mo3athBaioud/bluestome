create table TBL_CODE
(
  D_ID        NUMBER(18) not null,
  D_CODE_NAME VARCHAR2(50) not null,
  D_CREATE_DT DATE default sysdate,
  D_CODE_TP   VARCHAR2(20) not null,
  D_STATUS    NUMBER(2) default 0,
  D_DESC      VARCHAR2(100),
  D_ISDEL     NUMBER(2) default 0
);

create table TBL_ROUTE_CONFIG
(
  D_ID              NUMBER(18) not null,
  D_INTERFACES_ADDR VARCHAR2(128) not null,
  D_ZONE_NUM        VARCHAR2(20) not null,
  D_CITY_NAME       VARCHAR2(100) not null,
  D_STATUS          INTEGER not null
);

create table TBL_SPINFO
(
  D_ID                   NUMBER(18) not null,
  D_SPSHORTNAME          VARCHAR2(50),
  D_SPCODE               VARCHAR2(20) not null,
  D_SPNAME               VARCHAR2(255),
  D_MANAGERANGE          VARCHAR2(255),
  D_BANKNAME             VARCHAR2(255),
  D_BANKNO               VARCHAR2(255),
  D_LINKMAN_NAME         VARCHAR2(255),
  D_LINKMAN_TEL          VARCHAR2(255),
  D_LINKMAN_PHONE        VARCHAR2(255),
  D_FAX                  VARCHAR2(255),
  D_EMAIL                VARCHAR2(255),
  D_COMPANY_ADDR         VARCHAR2(255),
  D_WEB_URL              VARCHAR2(255),
  D_MESSAGE_ADDR         VARCHAR2(255),
  D_ZIP                  VARCHAR2(255),
  D_CUSTOMER_SERVICENAME VARCHAR2(255),
  D_CUSTOMER_SERVICETEL  VARCHAR2(255),
  D_STATUS               NUMBER(2),
  D_KEY1                 VARCHAR2(255),
  D_KEY2                 VARCHAR2(255),
  D_KEY3                 VARCHAR2(255),
  D_KEY4                 VARCHAR2(255),
  D_SPTYPEID             NUMBER(18) not null,
  D_CREATE_DT            DATE default sysdate,
  D_PARENT_ID            NUMBER(18) default 0 not null,
  D_AREA_CODE            NUMBER(18) not null
);

create table TBL_CONFIG
(
  D_ID           NUMBER(18) not null,
  D_CONFIG_NAME  VARCHAR2(50) not null,
  D_CONFIG_VALUE VARCHAR2(50) not null,
  D_CONFIG_DATE  DATE default sysdate,
  D_STATUS       NUMBER(2) default 0
);

create table TBL_DICTIONARY
(
  D_ID        NUMBER(18) not null,
  D_TABLE     VARCHAR2(50),
  D_COLUMN    VARCHAR2(100),
  D_VALUE     VARCHAR2(250),
  D_DESC      VARCHAR2(250),
  D_OTHER     VARCHAR2(250),
  D_CREATE_DT DATE default sysdate
);

create table TBL_LOGINLOG
(
  D_ID         NUMBER(18) default 0 not null,
  D_LOGINNAME  VARCHAR2(30) not null,
  D_LOGINDT    DATE default sysdate,
  D_LOGINIP    VARCHAR2(30) not null,
  D_STATUS     VARCHAR2(50) not null,
  D_LOGINDESC  VARCHAR2(100) not null,
  D_READSTATUS NUMBER(2) default 0
);

create table TBL_OPERATOR
(
  D_ID           NUMBER(18) not null,
  D_LOGIN_NAME   VARCHAR2(20) not null,
  D_PASSWORD     VARCHAR2(20) not null,
  D_REAL_NAME    VARCHAR2(50),
  D_STATUS       NUMBER(2) default 0,
  D_PHONE        VARCHAR2(50),
  D_MOBILE       VARCHAR2(50),
  D_EMAIL        VARCHAR2(100) not null,
  D_FAX          VARCHAR2(50),
  D_QQ           VARCHAR2(50),
  D_MSN          VARCHAR2(50),
  D_OPERATORTYPE NUMBER(18) not null,
  D_SPID         NUMBER(18) not null,
  D_CREATE_DT    DATE default sysdate
);

create table TBL_ROLE
(
  D_ID          NUMBER(18) not null,
  D_ROLE_NAME   VARCHAR2(50) not null,
  D_ROLE_DESC   VARCHAR2(255),
  D_ROLE_STATUS NUMBER(2) default 1,
  D_CREATE_DT   DATE default sysdate
);

create table TBL_OPERATOR_ROLE
(
  D_ID         NUMBER(18) not null,
  D_OPERATORID NUMBER(18) not null,
  D_ROLEID     NUMBER(18) not null,
  D_CREATE_DT  DATE default sysdate
);

create table TBL_PRIVILEGEOBJECT
(
  D_ID           NUMBER(18) not null,
  D_OBJECTINDEX  NUMBER(18) not null,
  D_OBJECTID     VARCHAR2(50) not null,
  D_OBJECTNAME   VARCHAR2(50) not null,
  D_OBJECTRIGHTS VARCHAR2(50) not null,
  D_STATUS       NUMBER(2) default 0,
  D_CREATE_DT    DATE default sysdate
);

create table TBL_ROLE_PRIV
(
  D_ID        NUMBER(18) not null,
  D_ROLEID    NUMBER(18) not null,
  D_PRIVOBJID NUMBER(18) not null,
  D_DELETE    NUMBER(2) default 2,
  D_CREATE_DT DATE default sysdate,
  D_UPDATE    NUMBER(2) default 2,
  D_INSERT    NUMBER(2) default 2,
  D_SELECT    NUMBER(2) default 2
);

create table TBL_SYSMENU
(
  D_ID             NUMBER(18) not null,
  D_MENU_NAME      VARCHAR2(150) not null,
  D_MENU_URL       VARCHAR2(255) not null,
  D_STATUS         NUMBER(2) default 0,
  D_ISDEL          NUMBER(2) default 0,
  D_MENU_ID        VARCHAR2(50) not null,
  D_PARENT_MENU_ID VARCHAR2(50),
  D_CREATE_DT      DATE default sysdate,
  D_VISIT_NAME     VARCHAR2(50)
);

create table TBL_ROLE_SYSMENU
(
  D_ID        NUMBER(18) not null,
  D_ROLEID    NUMBER(18) not null,
  D_SYSMENUID NUMBER(18) not null,
  D_CREATE_DT DATE default sysdate
);
