--USGS区域表
create table tbl_usgs_region
(
  d_id        INT(4) NOT NULL AUTO_INCREMENT,
  d_region_name VARCHAR(128) not null,
  d_region_url VARCHAR(256) not null,
  d_region_desc VARCHAR(1024),
  d_status    INT(4) DEFAULT 0,
  d_createtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  d_modifytime TIMESTAMP,
  PRIMARY KEY (d_id)
);


--地震信息表
create table tbl_usgs_erathquakeinfo(
  d_id        INT(4) NOT NULL AUTO_INCREMENT,
  d_date varchar（128),
  d_latitude varchar(128),
  d_longitude varchar(128),
  d_depth float,
  d_magnitude float,
  d_comments varchar(1024),
  d_url varchar(256),
  d_createtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  d_modifytime TIMESTAMP,
  PRIMARY KEY (d_id)
);

--地震详情表
create table tbl_usgs_erathquakedetail(
  d_id        INT(4) NOT NULL AUTO_INCREMENT,
  d_info_id int(4) not null,
  d_location varchar(256),
  d_region varchar(256),
  d_distinces varchar(512),
  d_lu varchar(256),
  d_parameters varchar(512),
  d_source varchar(256),
  d_remarks varchar(1024),
  d_eventId varchar(128),
  d_createtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  d_modifytime TIMESTAMP,
  PRIMARY KEY (d_id),
  CONSTRAINT tbl_usgs_erathquakedetail_INX FOREIGN KEY (d_info_id) REFERENCES tbl_usgs_erathquakeinfo (d_id) ON UPDATE CASCADE ON DELETE CASCADE
);