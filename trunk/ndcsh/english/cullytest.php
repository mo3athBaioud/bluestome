<?
require_once "conn.php"; 
 
$conn->Execute('CREATE DATABASE ndcdbe DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;');
 
$conn->Execute('CREATE TABLE IF NOT EXISTS `codemap_e` (`codetype` varchar(2) NOT NULL,`codeid` varchar(10) NOT NULL,`cname` varchar(100) default NULL, `ename` varchar(100) default NULL,`jname` varchar(100) default NULL,`optid` varchar(10) NOT NULL,`opttime` varchar(14) NOT NULL,PRIMARY KEY  USING BTREE (`codetype`,`codeid`)) ENGINE=MyISAM DEFAULT CHARSET=utf8;');


$conn->Execute('CREATE TABLE IF NOT EXISTS `csimg_e` (
  `csid` int(8) NOT NULL,
  `csimg` varchar(50) NOT NULL,
  `fdate` varchar(8) NOT NULL,
  PRIMARY KEY  (`csid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;');


$conn->Execute('CREATE TABLE IF NOT EXISTS `customer_e` (
  `usrid` varchar(10) NOT NULL,
  `usrname` varchar(30) NOT NULL,
  `birthday` varchar(8) NOT NULL,
  `pid` varchar(20) NOT NULL,
  `sex` varchar(1) NOT NULL,
  `hometel` varchar(15) default NULL,
  `comptel` varchar(15) default NULL,
  `mtel` varchar(15) default NULL,
  `area` varchar(10) NOT NULL,
  `addr` varchar(250) default NULL,
  `email` varchar(100) NOT NULL,
  `pwd` varchar(32) NOT NULL,
  `hint` varchar(50) default NULL,
  `answer` varchar(50) default NULL,
  `bonus` int(10) unsigned NOT NULL,
  `job` varchar(2) default NULL,
  `school` varchar(2) default NULL,
  `optid` varchar(10) NOT NULL,
  `opttime` varchar(14) NOT NULL,
  PRIMARY KEY  USING BTREE (`usrid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;');


$conn->Execute('CREATE TABLE IF NOT EXISTS `jobs_e` (
  `hotid` varchar(14) NOT NULL,
  `f1` varchar(256) NOT NULL,
  `f2` text NOT NULL,
  `f3` text NOT NULL,
  `f4` text NOT NULL,
  `f5` text NOT NULL,
  `f6` text NOT NULL,
  `f7` text NOT NULL,
  `optid` varchar(256) NOT NULL,
  `udate` varchar(14) NOT NULL,
  PRIMARY KEY  (`hotid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;');



$conn->Execute("CREATE TABLE IF NOT EXISTS `pgmlist_e` (
  `pgmid` varchar(20) NOT NULL,
  `pgmname` varchar(30) NOT NULL,
  `ap` varchar(255) NOT NULL,
  `right1` char(1) NOT NULL default 'N',
  `right2` char(1) NOT NULL default 'N',
  `right3` char(1) NOT NULL default 'N',
  `right4` char(1) NOT NULL default 'N',
  `right5` char(1) NOT NULL default 'N',
  `right6` char(1) NOT NULL default 'N',
  `right7` char(1) NOT NULL default 'N',
  `right8` char(1) NOT NULL default 'N',
  `right9` char(1) NOT NULL default 'N',
  `level` varchar(1) NOT NULL,
  PRIMARY KEY  USING BTREE (`pgmid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;");


$conn->Execute('CREATE TABLE IF NOT EXISTS `service_e` (
  `mtitle` varchar(256) NOT NULL,
  `mdesc` text NOT NULL,
  `title1` varchar(256) NOT NULL,
  `desc1` text NOT NULL,
  `titile2` varchar(256) NOT NULL,
  `desc2` text NOT NULL,
  `title3` varchar(256) NOT NULL,
  `desc3` text NOT NULL,
  `title4` varchar(256) NOT NULL,
  `desc4` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;');



$conn->Execute("CREATE TABLE IF NOT EXISTS `usrdat_e` (
  `optid` varchar(10) NOT NULL,
  `optname` varchar(30) default NULL,
  `pwd` varchar(32) NOT NULL,
  `status` char(1) NOT NULL default '1',
  `validdate` varchar(8) default NULL,
  PRIMARY KEY  USING BTREE (`optid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;");


$conn->Execute("CREATE TABLE IF NOT EXISTS `wdata_e` (
  `hotid` varchar(14) NOT NULL COMMENT 'YYYYMMDDhhmm',
  `hotcaption` varchar(255) NOT NULL,
  `ehotcaption` varchar(255) NOT NULL,
  `cdata` text,
  `edata` text,
  `jdata` text,
  `lastdate` varchar(14) NOT NULL,
  `stopdate` varchar(14) default NULL,
  `optid` varchar(10) NOT NULL,
  `html` char(1) NOT NULL default 'N',
  `showmain` char(1) NOT NULL default 'N',
  `IMGPATH` varchar(250) NOT NULL,
  `mtype` varchar(1) NOT NULL,
  `stype` varchar(1) NOT NULL,
  `udate` varchar(14) NOT NULL,
  `background` varchar(1024) NOT NULL,
  `picture` varchar(256) NOT NULL,
  `subcaption` varchar(256) NOT NULL,
  `iorder` varchar(2) NOT NULL,
  `piccaption` varchar(256) NOT NULL,
  `f1` text NOT NULL,
  `f2` text NOT NULL,
  `f3` text NOT NULL,
  `f4` text NOT NULL,
  `f5` text NOT NULL,
  PRIMARY KEY  USING BTREE (`hotid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;");


$conn->Execute("CREATE TABLE IF NOT EXISTS `wdata_pic_e` (
  `hotid` varchar(14) NOT NULL,
  `picture` varchar(256) NOT NULL,
  `udate` varchar(14) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;");

?>










