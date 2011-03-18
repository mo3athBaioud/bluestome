create table TBL_CODE
(
  D_ID        INT(4) NOT NULL AUTO_INCREMENT,
  D_CODE_NAME VARCHAR(256) not null,
  D_CREATE_DT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  D_CODE_TP   VARCHAR(20) not null,
  D_STATUS    INT(4) DEFAULT 0,
  D_DESC      VARCHAR(100),
  D_ISDEL     INT(4) DEFAULT 0,
  PRIMARY KEY (D_ID)
);

create table TBL_CONFIG
(
  D_ID           INT(4) NOT NULL AUTO_INCREMENT,
  D_CONFIG_NAME  VARCHAR(256) not null,
  D_CONFIG_VALUE VARCHAR(256) not null,
  D_CONFIG_DATE  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  D_STATUS       INT(4) DEFAULT 0,
  PRIMARY KEY (D_ID)
);

create table TBL_ROUTE_CONFIG
(
  D_ID              INT(4) NOT NULL AUTO_INCREMENT,
  D_INTERFACES_ADDR VARCHAR(128) not null,
  D_ZONE_NUM        VARCHAR(20) not null,
  D_CITY_NAME       VARCHAR(100) not null,
  D_STATUS          INTEGER not null,
  PRIMARY KEY (D_ID)
);

create table TBL_SPINFO
(
  D_ID                   INT(4) NOT NULL AUTO_INCREMENT,
  D_SPSHORTNAME          VARCHAR(256),
  D_SPCODE               VARCHAR(20) not null,
  D_SPNAME               VARCHAR(255),
  D_MANAGERANGE          VARCHAR(255),
  D_BANKNAME             VARCHAR(255),
  D_BANKNO               VARCHAR(255),
  D_LINKMAN_NAME         VARCHAR(255),
  D_LINKMAN_TEL          VARCHAR(255),
  D_LINKMAN_PHONE        VARCHAR(255),
  D_FAX                  VARCHAR(255),
  D_EMAIL                VARCHAR(255),
  D_COMPANY_ADDR         VARCHAR(255),
  D_WEB_URL              VARCHAR(255),
  D_MESSAGE_ADDR         VARCHAR(255),
  D_ZIP                  VARCHAR(255),
  D_CUSTOMER_SERVICENAME VARCHAR(255),
  D_CUSTOMER_SERVICETEL  VARCHAR(255),
  D_STATUS               INT(4),
  D_KEY1                 VARCHAR(255),
  D_KEY2                 VARCHAR(255),
  D_KEY3                 VARCHAR(255),
  D_KEY4                 VARCHAR(255),
  D_SPTYPEID             INT(4) NOT NULL,
  D_CREATE_DT            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  D_PARENT_ID            INT(4) NOT NULL,
  D_AREA_CODE            INT(4) NOT NULL,
  PRIMARY KEY (D_ID),
  CONSTRAINT TBL_SPINFO_INX FOREIGN KEY (D_AREA_CODE) REFERENCES TBL_ROUTE_CONFIG (D_ID) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT TBL_SPINFO_INX2 FOREIGN KEY (D_SPTYPEID) REFERENCES TBL_CODE (D_ID) ON UPDATE CASCADE ON DELETE CASCADE
);

create table TBL_DICTIONARY
(
  D_ID        INT(4) NOT NULL AUTO_INCREMENT,
  D_TABLE     VARCHAR(256),
  D_COLUMN    VARCHAR(100),
  D_VALUE     VARCHAR(250),
  D_DESC      VARCHAR(250),
  D_OTHER     VARCHAR(250),
  D_CREATE_DT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (D_ID)
);

drop table if exists TBL_LOGINLOG;
create table TBL_LOGINLOG
(
  D_ID         INT(4) NOT NULL AUTO_INCREMENT,
  D_LOGINNAME  VARCHAR(30) not null,
  D_OPERATOR_ID INT(4) not null,
  D_LOGINIP    VARCHAR(30) not null,
  D_STATUS     VARCHAR(256) not null,
  D_LOGINDESC  VARCHAR(100) not null,
  D_READSTATUS INT(4) DEFAULT 0,
  D_LOGINDT    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
  PRIMARY KEY (D_ID),
  CONSTRAINT TBL_LOGINLOG_INX FOREIGN KEY (D_OPERATOR_ID) REFERENCES TBL_OPERATOR (D_ID) ON UPDATE CASCADE ON DELETE CASCADE
);

drop table if exists TBL_OPERATOR;
create table TBL_OPERATOR
(
  D_ID           INT(4) NOT NULL AUTO_INCREMENT,
  D_LOGIN_NAME   VARCHAR(20) not null,
  D_PASSWORD     VARCHAR(20) not null,
  D_REAL_NAME    VARCHAR(256),
  D_STATUS       INT(4) DEFAULT 0,
  D_PHONE        VARCHAR(256),
  D_MOBILE       VARCHAR(256),
  D_EMAIL        VARCHAR(100) not null,
  D_FAX          VARCHAR(256),
  D_QQ           VARCHAR(256),
  D_MSN          VARCHAR(256),
  D_OPERATORTYPE INT(4) NOT NULL,
  D_SPID         INT(4) NOT NULL,
  D_CREATE_DT    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (D_ID),
  CONSTRAINT TBL_OPERATOR_INX FOREIGN KEY (D_OPERATORTYPE) REFERENCES TBL_CODE (D_ID) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT TBL_OPERATOR_INX2 FOREIGN KEY (D_SPID) REFERENCES TBL_SPINFO (D_ID) ON UPDATE CASCADE ON DELETE CASCADE
);

drop table if exists TBL_ROLE;
create table TBL_ROLE
(
  D_ID          INT(4) NOT NULL AUTO_INCREMENT,
  D_ROLE_NAME   VARCHAR(256) not null,
  D_ROLE_DESC   VARCHAR(255),
  D_ROLE_STATUS INT(4) default 1,
  D_CREATE_DT   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (D_ID)
);

drop table if exists TBL_OPERATOR_ROLE;
create table TBL_OPERATOR_ROLE
(
  D_ID         INT(4) NOT NULL AUTO_INCREMENT,
  D_OPERATORID INT(4) NOT NULL,
  D_ROLEID     INT(4) NOT NULL,
  D_CREATE_DT  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (D_ID),
  UNIQUE INDEX UNIQUE_TBL_OPERATOR_ROLE (D_OPERATORID, D_ROLEID),
  CONSTRAINT TBL_OPERATOR_ROLE_INX FOREIGN KEY (D_ROLEID) REFERENCES TBL_ROLE (D_ID) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT TBL_OPERATOR_ROLE_INX2 FOREIGN KEY (D_OPERATORID) REFERENCES TBL_OPERATOR (D_ID) ON UPDATE CASCADE ON DELETE CASCADE
);

drop table if exists TBL_PRIVILEGEOBJECT;
create table TBL_PRIVILEGEOBJECT
(
  D_ID           INT(4) NOT NULL AUTO_INCREMENT,
  D_OBJECTINDEX  INT(4) NOT NULL,
  D_OBJECTID     VARCHAR(256) not null,
  D_OBJECTNAME   VARCHAR(256) not null,
  D_OBJECTRIGHTS VARCHAR(256) not null,
  D_STATUS       INT(4) DEFAULT 0,
  D_CREATE_DT    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (D_ID)
);

drop table if exists TBL_ROLE_PRIV;
create table TBL_ROLE_PRIV
(
  D_ID        INT(4) NOT NULL AUTO_INCREMENT,
  D_ROLEID    INT(4) NOT NULL,
  D_PRIVOBJID INT(4) NOT NULL,
  D_DELETE    INT(4) DEFAULT 2,
  D_UPDATE    INT(4) DEFAULT 2,
  D_INSERT    INT(4) DEFAULT 2,
  D_SELECT    INT(4) DEFAULT 2,
  D_CREATE_DT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (D_ID),
  UNIQUE INDEX UNIQUE_TBL_ROLE_PRIV (D_ROLEID, D_PRIVOBJID),
  CONSTRAINT TBL_ROLE_PRIV_INX FOREIGN KEY (D_ROLEID) REFERENCES TBL_ROLE (D_ID) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT TBL_ROLE_PRIV_INX2 FOREIGN KEY (D_PRIVOBJID) REFERENCES TBL_PRIVILEGEOBJECT (D_ID) ON UPDATE CASCADE ON DELETE CASCADE
);

drop table if exists TBL_SYSMENU;
create table TBL_SYSMENU
(
  D_ID             INT(4) NOT NULL AUTO_INCREMENT,
  D_MENU_NAME      VARCHAR(150) not null,
  D_MENU_URL       VARCHAR(255) not null,
  D_STATUS         INT(4) DEFAULT 0,
  D_ISDEL          INT(4) DEFAULT 0,
  D_MENU_ID        VARCHAR(256) not null,
  D_PARENT_MENU_ID VARCHAR(256),
  D_CREATE_DT      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  D_VISIT_NAME     VARCHAR(256),
  PRIMARY KEY (D_ID)
);

drop table if exists TBL_ROLE_SYSMENU;
create table TBL_ROLE_SYSMENU
(
  D_ID        INT(4) NOT NULL AUTO_INCREMENT,
  D_ROLEID    INT(4) NOT NULL,
  D_SYSMENUID INT(4) NOT NULL,
  D_CREATE_DT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (D_ID),
  UNIQUE INDEX UNIQUE_TBL_ROLE_SYSMENU (D_ROLEID, D_SYSMENUID),
  CONSTRAINT TBL_ROLE_SYSMENU_INX FOREIGN KEY (D_ROLEID) REFERENCES TBL_ROLE (D_ID) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT TBL_ROLE_SYSMENU_INX2 FOREIGN KEY (D_SYSMENUID) REFERENCES TBL_SYSMENU (D_ID) ON UPDATE CASCADE ON DELETE CASCADE
);