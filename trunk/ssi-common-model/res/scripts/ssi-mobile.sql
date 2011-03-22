create table tbl_mobile_brand_tmp(
  d_id        INT(4) NOT NULL AUTO_INCREMENT,
  d_web_id VARCHAR(128) not null,
  d_url VARCHAR(256) not null,
  d_name VARCHAR(256),
  d_icon VARCHAR(256),
  d_status    INT(4) DEFAULT 1,
  d_createtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  d_modifytime TIMESTAMP,
  PRIMARY KEY (d_id)
);