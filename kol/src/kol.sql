/*
Navicat MySQL Data Transfer

Source Server         : common
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : kol

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2015-03-22 14:38:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `kol_channel`
-- ----------------------------
DROP TABLE IF EXISTS `kol_channel`;
CREATE TABLE `kol_channel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `create_date` varchar(14) DEFAULT NULL,
  `update_date` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kol_channel
-- ----------------------------

-- ----------------------------
-- Table structure for `kol_comment`
-- ----------------------------
DROP TABLE IF EXISTS `kol_comment`;
CREATE TABLE `kol_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `testimonials_id` bigint(20) NOT NULL,
  `contents` varchar(5000) NOT NULL,
  `create_date` varchar(14) NOT NULL,
  `update_date` varchar(14) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kol_comment
-- ----------------------------
INSERT INTO `kol_comment` VALUES ('1', '29', 'sdfesdfsfd', '20150311212553', '20150311212553', '1');
INSERT INTO `kol_comment` VALUES ('2', '29', 'fesdfsdf', '20150311213212', '20150311213212', '1');
INSERT INTO `kol_comment` VALUES ('3', '39', '评论评论评论评论评论评论', '20150311220304', '20150311220304', '1');
INSERT INTO `kol_comment` VALUES ('4', '39', '评论评论评论评论评论评论', '20150311221345', '20150311221345', '1');
INSERT INTO `kol_comment` VALUES ('5', '39', '吐要不要打点滴吐要不要打点滴吐要不要打点滴吐要不要打点滴', '20150311221504', '20150311221504', '1');
INSERT INTO `kol_comment` VALUES ('6', '39', '吐要不要打点滴吐要不要打点滴吐要不要打点滴吐要不要打点滴吐要不要打点滴', '20150311221508', '20150311221508', '1');
INSERT INTO `kol_comment` VALUES ('7', '39', '吐要不要打点滴吐要不要打点滴吐要不要打点滴吐要不要打点滴吐要不要打点滴', '20150311221512', '20150311221512', '1');

-- ----------------------------
-- Table structure for `kol_fast_comment`
-- ----------------------------
DROP TABLE IF EXISTS `kol_fast_comment`;
CREATE TABLE `kol_fast_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `testimonial_id` bigint(20) NOT NULL COMMENT '??ID',
  `grade` varchar(1) NOT NULL,
  `create_time` varchar(14) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kol_fast_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `kol_testimonials`
-- ----------------------------
DROP TABLE IF EXISTS `kol_testimonials`;
CREATE TABLE `kol_testimonials` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `contents` varchar(5000) NOT NULL,
  `create_date` varchar(14) NOT NULL,
  `update_date` varchar(14) DEFAULT NULL,
  `channel_id` bigint(20) DEFAULT NULL,
  `title` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kol_testimonials
-- ----------------------------
INSERT INTO `kol_testimonials` VALUES ('28', '1', '人生总是有希望的。明天总是会有阳光。相信自己，相信未来', '20150305223344', '20150305223344', '0', '站长第一言');
INSERT INTO `kol_testimonials` VALUES ('29', '1', '大哥大霖大哥大霖要厅要', '20150305225007', '20150305225007', '1', '顶替需要');
INSERT INTO `kol_testimonials` VALUES ('30', '1', '枯顶替枯霜霜霜要', '20150305225024', '20150305225024', '2', '枯无可奈何枯');
INSERT INTO `kol_testimonials` VALUES ('31', '1', '??????????????????????????????????????????????????????????????????', '20150311215752', '20150311215752', '0', '??????');
INSERT INTO `kol_testimonials` VALUES ('32', '1', '??????????????????', '20150311215910', '20150311215910', '0', '????????????');
INSERT INTO `kol_testimonials` VALUES ('33', '1', '人生如此无聊人生如此无聊人生如此无聊人生如此无聊人生如此无聊人生如此无聊人生如此无聊人生如此无聊人生如此无聊人生如此无聊人生如此无聊', '20150311220117', '20150311220117', '0', '人生如此无聊');
INSERT INTO `kol_testimonials` VALUES ('34', '1', '人生如此无聊人生如此无聊人生如此无聊人生如此无聊人生如此无聊人生如此无聊人生如此无聊人生如此无聊人生如此无聊人生如此无聊人生如此无聊', '20150311220124', '20150311220124', '0', '人生如此无聊');
INSERT INTO `kol_testimonials` VALUES ('35', '1', '人生如此无聊人生如此无聊人生如此无聊人生如此无聊人生如此无聊', '20150311220132', '20150311220132', '0', '人生如此无聊人生如此无聊人生如此无聊');
INSERT INTO `kol_testimonials` VALUES ('36', '1', '人生如此无聊人生如此无聊', '20150311220148', '20150311220148', '0', '人生如此无聊');
INSERT INTO `kol_testimonials` VALUES ('37', '1', '人生如此无聊人生如此无聊', '20150311220151', '20150311220151', '0', '人生如此无聊');
INSERT INTO `kol_testimonials` VALUES ('38', '1', '天上人间天上人间', '20150311220212', '20150311220212', '0', '天上人间');
INSERT INTO `kol_testimonials` VALUES ('39', '1', '天上人间天上人间天上人间天上人间天上人间天上人间天上人间', '20150311220228', '20150311220228', '0', '天上人间');
INSERT INTO `kol_testimonials` VALUES ('40', '1', '', '20150314134522', '20150314134522', '0', '');
INSERT INTO `kol_testimonials` VALUES ('41', '1', '其他其他其他其他其他其他其他其他其他其他其他其他其他其他其他其他其他其他其他其他其他其他其他其他', '20150314135507', '20150314135507', '3', '其他其他其他其他其他其他');

-- ----------------------------
-- Table structure for `kol_user`
-- ----------------------------
DROP TABLE IF EXISTS `kol_user`;
CREATE TABLE `kol_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(50) NOT NULL,
  `mail` varchar(50) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `regist_type` varchar(1) DEFAULT '' COMMENT '0ϵͳע 1ûע',
  `nick_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kol_user
-- ----------------------------
