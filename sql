/*
 Navicat MySQL Data Transfer

 Source Server         : ycy
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : 47.94.147.119
 Source Database       : luckwheel

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : utf-8

 Date: 06/03/2019 11:44:20 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `luck_product`
-- ----------------------------
DROP TABLE IF EXISTS `luck_product`;
CREATE TABLE `luck_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '商品名',
  `weight` float DEFAULT '0' COMMENT '概率',
  `flag` int(11) DEFAULT '1' COMMENT '是否展示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `luck_user`
-- ----------------------------
DROP TABLE IF EXISTS `luck_user`;
CREATE TABLE `luck_user` (
  `id` int(11) NOT NULL DEFAULT '0',
  `uid` varchar(255) NOT NULL,
  `uname` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `luck_num` int(11) DEFAULT '0' COMMENT '可抽奖次数',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `luck_user_product`
-- ----------------------------
DROP TABLE IF EXISTS `luck_user_product`;
CREATE TABLE `luck_user_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `pid` int(11) DEFAULT '0',
  `pname` varchar(255) DEFAULT NULL COMMENT '商品名字',
  `exchange` int(11) DEFAULT '0' COMMENT '是否兑换 0未发货  1发货了',
  `ltime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2918 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
