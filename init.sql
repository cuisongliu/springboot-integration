/*
Navicat MySQL Data Transfer

Source Server         : cuisongliu
Source Server Version : 50716
Source Host           : cuisongliu.com:3306
Source Database       : xinyue_test

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-05-20 20:53:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for m_param
-- ----------------------------
DROP TABLE IF EXISTS `m_param`;
CREATE TABLE `m_param` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `param_code` varchar(50) NOT NULL COMMENT '参数编码',
  `param_name` varchar(50) NOT NULL COMMENT '参数名称',
  `param_value` varchar(500) NOT NULL COMMENT '参数值',
  `param_type` int(11) NOT NULL COMMENT '类型 0 为可编辑  1为不可编辑 2为只读',
  PRIMARY KEY (`id`),
  UNIQUE KEY `param_code_2` (`param_code`),
  KEY `param_code` (`param_code`),
  KEY `param_type` (`param_type`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_param
-- ----------------------------
INSERT INTO `m_param` VALUES ('1', 'QR_PREFIX', '二维码前缀', 'http://web.cuisongliu.com/serial/', '1');

-- ----------------------------
-- Table structure for m_phone
-- ----------------------------
DROP TABLE IF EXISTS `m_phone`;
CREATE TABLE `m_phone` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `phone_serial` varchar(500) NOT NULL COMMENT '手持机序号',
  `phone_name` varchar(500) NOT NULL COMMENT '手持机名称',
  `user_id` int(11) DEFAULT NULL COMMENT '用户Id',
  `phone_type` varchar(500) NOT NULL COMMENT '手持机型号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_serial` (`phone_serial`) USING HASH,
  KEY `phone_serial_2` (`phone_serial`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_phone
-- ----------------------------
INSERT INTO `m_phone` VALUES ('1', '73890832c50ac149', '测试手持机', null, 'U8000S');
INSERT INTO `m_phone` VALUES ('2', '21abfb932732ac8e', '手持机四川', '2', 'U8000S');

-- ----------------------------
-- Table structure for m_phone_log
-- ----------------------------
DROP TABLE IF EXISTS `m_phone_log`;
CREATE TABLE `m_phone_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone_serial_id` varchar(255) NOT NULL,
  `phone_system_id` varchar(255) NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `phone_serial_id` (`phone_serial_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_phone_log
-- ----------------------------

-- ----------------------------
-- Table structure for m_record
-- ----------------------------
DROP TABLE IF EXISTS `m_record`;
CREATE TABLE `m_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `record_address` varchar(500) NOT NULL COMMENT '记录地址',
  `record_store` int(11) NOT NULL COMMENT '记录货物Id',
  `record_date` datetime NOT NULL COMMENT '记录时间',
  `record_operator` int(11) NOT NULL COMMENT '操作人',
  `record_type` int(11) NOT NULL COMMENT '操作类型',
  `record_company` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_record
-- ----------------------------
INSERT INTO `m_record` VALUES ('1', '1111', '3', '2016-12-07 09:10:44', '2', '1', '11');

-- ----------------------------
-- Table structure for m_store
-- ----------------------------
DROP TABLE IF EXISTS `m_store`;
CREATE TABLE `m_store` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `store_code` varchar(500) NOT NULL COMMENT '编码',
  `store_name` varchar(500) NOT NULL COMMENT '货物名称',
  `store_address` varchar(500) NOT NULL COMMENT '货物地址',
  `user_id` int(11) DEFAULT NULL COMMENT '用户Id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `store_code` (`store_code`),
  KEY `store_code_2` (`store_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_store
-- ----------------------------
INSERT INTO `m_store` VALUES ('2', 'cj201611272256', '丹棱橙桔春见170105', '四川', '2');
INSERT INTO `m_store` VALUES ('3', 'xywl20161218211348', 'dddd', 'ddd', '1');

-- ----------------------------
-- Table structure for m_system
-- ----------------------------
DROP TABLE IF EXISTS `m_system`;
CREATE TABLE `m_system` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `system_id` varchar(50) NOT NULL COMMENT '访问应用',
  `system_code` varchar(50) DEFAULT NULL COMMENT '应用编号',
  `system_name` varchar(50) DEFAULT NULL COMMENT '应用名称',
  `system_type` int(11) NOT NULL COMMENT '应用类型  0 为android 1为 IOS',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_system
-- ----------------------------
INSERT INTO `m_system` VALUES ('1', 'Y9Y6tjWyNqVZyvFl8x7EyOYsTiCa38bF', '8908431', '物联网APP', '0');

-- ----------------------------
-- Table structure for m_type
-- ----------------------------
DROP TABLE IF EXISTS `m_type`;
CREATE TABLE `m_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_code` varchar(30) NOT NULL COMMENT '类型编码',
  `type_name` varchar(50) NOT NULL COMMENT '类型名称',
  `type_order` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_type
-- ----------------------------
INSERT INTO `m_type` VALUES ('1', 'OPT_INIT', '未成熟已预定', '0');
INSERT INTO `m_type` VALUES ('2', 'OPT_SETUP1', '采摘', '1');
INSERT INTO `m_type` VALUES ('3', 'OPT_PACKAGE', '分类装箱', '2');
INSERT INTO `m_type` VALUES ('4', 'OPT_WULIU', '递交物流运输', '3');
INSERT INTO `m_type` VALUES ('5', 'OPT_END', '到达恒温库房', '4');
INSERT INTO `m_type` VALUES ('6', 'OPT_TEST', 'TEST', '5');

-- ----------------------------
-- Table structure for m_user
-- ----------------------------
DROP TABLE IF EXISTS `m_user`;
CREATE TABLE `m_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(500) NOT NULL,
  `login_name` varchar(500) NOT NULL,
  `login_passwd` varchar(500) NOT NULL,
  `user_type` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_type` (`user_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_user
-- ----------------------------
INSERT INTO `m_user` VALUES ('1', '测试用户', 'admin', '6611BB6B81CCC750B6A06EB90947B801', '0');
INSERT INTO `m_user` VALUES ('2', '四川区域代理', '四川区域代理', '7CCE6DFFF2C270085BDA864ED00E6178', '1');

-- ----------------------------
-- Table structure for m_user_type
-- ----------------------------
DROP TABLE IF EXISTS `m_user_type`;
CREATE TABLE `m_user_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户主键',
  `type_id` int(11) NOT NULL COMMENT '类型主键',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`,`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_user_type
-- ----------------------------
INSERT INTO `m_user_type` VALUES ('15', '1', '6');
INSERT INTO `m_user_type` VALUES ('3', '2', '1');
INSERT INTO `m_user_type` VALUES ('4', '2', '2');
INSERT INTO `m_user_type` VALUES ('5', '2', '3');
INSERT INTO `m_user_type` VALUES ('6', '2', '4');
INSERT INTO `m_user_type` VALUES ('7', '2', '5');
