/*
Navicat MySQL Data Transfer

Source Server         : 114.212.82.60
Source Server Version : 50096
Source Host           : 114.212.82.60:3306
Source Database       : weibo

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2015-12-14 19:44:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `weibodata`
-- ----------------------------
DROP TABLE IF EXISTS `weibodata`;
CREATE TABLE `weibodata` (
  `id` int(10) NOT NULL auto_increment,
  `baseUrl` varchar(100) NOT NULL,
  `author` varchar(20) NOT NULL,
  `text` text NOT NULL,
  `loves` int(10) NOT NULL,
  `repost` int(10) NOT NULL,
  `comment` int(10) NOT NULL,
  `time` varchar(20) NOT NULL,
  `device` varchar(20) NOT NULL,
  `inputTime` varchar(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of weibodata
-- ----------------------------
