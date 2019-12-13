/*
MySQL Backup
Source Server Version: 8.0.18
Source Database: likeyun
Date: 2019/12/6 18:11:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `t_admin`
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `realname` varchar(32) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `sex` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '性别：0男 1女',
  `password` char(40) NOT NULL DEFAULT '' COMMENT '密码',
  `login_time` int(10) unsigned DEFAULT '0' COMMENT '登录时间',
  `create_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间',
  `role_id` tinyint(2) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `t_component`
-- ----------------------------
DROP TABLE IF EXISTS `t_component`;
CREATE TABLE `t_component` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `component_name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_parent`
-- ----------------------------
DROP TABLE IF EXISTS `t_parent`;
CREATE TABLE `t_parent` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `realname` varchar(32) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `sex` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '性别：0男 1女',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态：0禁用 1启用',
  `password` char(40) NOT NULL DEFAULT '' COMMENT '密码',
  `login_time` int(10) unsigned DEFAULT '0' COMMENT '登录时间',
  `create_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间',
  `role_id` tinyint(2) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `role` varchar(20) NOT NULL DEFAULT '' COMMENT '角色',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '名称',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态：0禁用 1启用',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_role` (`role`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `t_role_component`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_component`;
CREATE TABLE `t_role_component` (
  `role_id` tinyint(2) NOT NULL,
  `component_id` smallint(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_student`
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `school_id` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '学校编号',
  `team_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '班级编号',
  `username` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `realname` varchar(32) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `sex` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '性别：0男 1女',
  `phone` char(11) DEFAULT '' COMMENT '号码',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '状态：0毕业 1在读 2休学 3退学',
  `password` char(40) NOT NULL DEFAULT '' COMMENT '密码',
  `parents_name` varchar(32) NOT NULL DEFAULT '' COMMENT '家长姓名',
  `parents_phone` char(11) NOT NULL DEFAULT '' COMMENT '家长号码',
  `born_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '出生年月',
  `admit_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '录取时间',
  `login_time` int(10) unsigned DEFAULT '0' COMMENT '登录时间',
  `create_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间',
  `role_id` tinyint(2) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_username` (`username`) USING BTREE,
  KEY `idx_school_id` (`school_id`) USING BTREE,
  KEY `idx_team_id` (`team_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `t_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `school_id` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '学校编号',
  `subject_id` tinyint(3) unsigned NOT NULL COMMENT '学科编号',
  `username` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `realname` varchar(32) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `sex` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '性别：0男 1女',
  `phone` char(11) NOT NULL DEFAULT '' COMMENT '号码',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '类别：0专职 1兼职',
  `password` char(40) NOT NULL DEFAULT '' COMMENT '密码',
  `salt` char(32) NOT NULL DEFAULT '' COMMENT '盐',
  `entry_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '入职时间',
  `login_time` int(10) unsigned DEFAULT '0' COMMENT '登录时间',
  `create_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间',
  `role_id` tinyint(2) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_username` (`username`) USING BTREE,
  KEY `idx_school_id` (`school_id`) USING BTREE,
  KEY `idx_subject_id` (`subject_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `t_teaching`
-- ----------------------------
DROP TABLE IF EXISTS `t_teaching`;
CREATE TABLE `t_teaching` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `school_id` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '学校编号',
  `username` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `realname` varchar(32) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `sex` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '性别：0男 1女',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态：0离职 1在职',
  `is_owner` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否主要：0否 1是',
  `password` char(40) NOT NULL DEFAULT '' COMMENT '密码',
  `login_time` int(10) unsigned DEFAULT '0' COMMENT '登录时间',
  `create_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间',
  `role_id` tinyint(2) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_username` (`username`) USING BTREE,
  KEY `idx_school_id` (`school_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `t_user_component`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_component`;
CREATE TABLE `t_user_component` (
  `user_id` int(10) NOT NULL,
  `role_id` tinyint(2) NOT NULL,
  `component_id` smallint(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_user_login_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_login_log`;
CREATE TABLE `t_user_login_log` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `role_id` tinyint(2) NOT NULL,
  `ip` varchar(20) NOT NULL,
  `address` varchar(20) NOT NULL,
  `login_time` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_worker`
-- ----------------------------
DROP TABLE IF EXISTS `t_worker`;
CREATE TABLE `t_worker` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `realname` varchar(32) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `sex` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '性别：0男 1女',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态：0禁用 1启用',
  `password` char(40) NOT NULL DEFAULT '' COMMENT '密码',
  `login_time` int(10) unsigned DEFAULT '0' COMMENT '登录时间',
  `create_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间',
  `role_id` tinyint(2) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1','13801380001','admin','0','646ac1b3ea2bcea29af268ed16988048','1575626108','1575622825','1');
INSERT INTO `t_component` VALUES ('1','look');
INSERT INTO `t_role` VALUES ('1','admin','管理员','1'), ('2','education','教育局','1'), ('3','teaching','教务处','1'), ('4','teacher','老师','1'), ('5','student','学生','1');
INSERT INTO `t_role_component` VALUES ('1','1');
INSERT INTO `t_student` VALUES ('1','1','1','13801380004','喻文波','0','','1','123456','喻大龙','13801380004','1552377354','1567267200','1573113792','1568096662','5'), ('2','1','1','13801380014','王柳羿','0','','1','123456','王浩然','13801380014','1552377354','1567267200','1573114073','1568096728','5'), ('3','1','1','13801380024','高振宁','0','','1','123456','高地平','13801380024','1552377354','1567267200','1571823276','1568096775','5'), ('4','1','1','13801380034','卢珏','1','','1','123456','卢俊义','13801380034','1552377354','1567267200','1571390913','1568096831','5'), ('5','1','1','13801380044','陈名铭','0','','1','123456','陈世豪','13801380044','1552377354','1567267200','1573114223','1568096900','5'), ('6','1','2','13801380054','李恩静','1','','1','123456','李自成','13801380054','1552377354','1567267200','1568970553','1568096932','5'), ('7','1','2','13801380064','简自豪','0','','1','123456','简灯笼','13801380064','1552377354','1567267200','1573118785','1568096976','5'), ('8','1','2','13801380074','明凯','0','','1','123456','明道','13801380074','1552377354','1567267200','0','1568097017','5'), ('9','1','2','13801380084','李青','0','','1','123456','李世民','13801380084','1552377354','1567267200','0','1568097042','5'), ('10','1','2','13801380094','赵信','0','','1','123456','赵云','13801380094','1552377354','1567267200','1573193046','1568097070','5');
INSERT INTO `t_teacher` VALUES ('1','1','1','13801380003','姜承路','0','13801380003','0','123456','3fecfb78637d8691bfe208a5b01b6602','1546272000','1574925284','1560739707','4'), ('3','1','2','13801380013','宋义近','0','13801380013','0','123456','898399aeedfae5e9dcb35dd3683aa1b0','1324567890','1568877770','1561957793','4'), ('4','1','3','13801380023','高地平','0','13801380023','0','123456','c3626958a14a2c4780ef5b630c8f23a8','1535731200','1568876732','1568096325','4'), ('5','1','4','13801380033','刘谋','1','13801380033','0','123456','563f4c3260c4589935dd41236a51ab7f','1497628800','1568876826','1568096352','4');
INSERT INTO `t_teaching` VALUES ('1','1','13801380002','李相赫','0','1','','123456','1573113557','1560739331','3');
INSERT INTO `t_user_component` VALUES ('1','4','1');
