/*
Navicat MySQL Data Transfer

Source Server         : common
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : kol

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2015-03-15 10:28:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `kol_user`
-- ----------------------------
DROP TABLE IF EXISTS `kol_user`;
CREATE TABLE `kol_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(50) NOT NULL,
  `mail` varchar(50) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `regist_type` varchar(1) DEFAULT '' COMMENT '0：系统注册 1：用户注册',
  `nick_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kol_user
-- ----------------------------
