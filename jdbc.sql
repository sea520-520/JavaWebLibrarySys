/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80030
Source Host           : localhost:3306
Source Database       : jdbc

Target Server Type    : MYSQL
Target Server Version : 80030
File Encoding         : 65001

Date: 2024-06-16 23:04:23
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `IBSN` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `num` int NOT NULL,
  `place` varchar(32) NOT NULL,
  `dataime` varchar(32) NOT NULL,
  `flag` int NOT NULL,
  PRIMARY KEY (`IBSN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', '程序设计', '5', '一楼', '20240603', '1');
INSERT INTO `book` VALUES ('3', '计算机导论', '6', '总馆', '20240602', '0');
INSERT INTO `book` VALUES ('4', '计算机网络', '9', '三楼西', '20240614', '1');
INSERT INTO `book` VALUES ('6', '算法分析与设计', '10', '二楼东', '20240614', '1');
INSERT INTO `book` VALUES ('7', '数据结构与算法', '10', '总馆', '20240616', '0');
INSERT INTO `book` VALUES ('8', '数据结构', '10', '三楼西', '20240616', '1');

-- ----------------------------
-- Table structure for `borrow`
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow` (
  `username` varchar(32) NOT NULL,
  `relname` varchar(32) NOT NULL,
  `bookname` varchar(32) NOT NULL,
  `dataime` varchar(32) NOT NULL,
  `flag` int NOT NULL,
  PRIMARY KEY (`username`,`bookname`,`dataime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES ('xiaocheng', '小辰', '数据结构', '20240616', '1');
INSERT INTO `borrow` VALUES ('xiaocheng', '小辰', '程序设计', '20240616', '1');
INSERT INTO `borrow` VALUES ('xiaocheng', '小辰', '计算机网络', '20240616', '0');
INSERT INTO `borrow` VALUES ('xiaogang', '小刚', '数据结构', '20240615', '1');
INSERT INTO `borrow` VALUES ('xiaogang', '小刚', '程序设计', '20240302', '1');
INSERT INTO `borrow` VALUES ('xiaogang', '小刚', '程序设计', '20240616', '1');
INSERT INTO `borrow` VALUES ('xiaogang', '小刚', '计算机导论', '20240506', '1');
INSERT INTO `borrow` VALUES ('xiaohong', '小红', '计算机导论', '20230602', '1');
INSERT INTO `borrow` VALUES ('xiaohong', '小红', '计算机网络', '20240309', '0');
INSERT INTO `borrow` VALUES ('xiaolin', '小林', '计算机网络', '20240616', '1');
INSERT INTO `borrow` VALUES ('xiaoming', '小明', '数据结构', '20230502', '1');
INSERT INTO `borrow` VALUES ('xiaoming', ' 小明', '数据结构', '20240615', '0');
INSERT INTO `borrow` VALUES ('xiaoming', ' 小明', '程序设计', '20240615', '1');
INSERT INTO `borrow` VALUES ('xiaoming', ' 小明', '计算机网络', '20240614', '1');
INSERT INTO `borrow` VALUES ('xiaoming', '小明', '计算机网络', '20240616', '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `relname` varchar(32) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('xiaocheng', 'xcgfd', '小辰');
INSERT INTO `user` VALUES ('xiaogang', '123', '小刚');
INSERT INTO `user` VALUES ('xiaohong', 'ghjygf012', '小红');
INSERT INTO `user` VALUES ('xiaoming', '123', '小明');
INSERT INTO `user` VALUES ('xiaowang', '124625', '小王');
