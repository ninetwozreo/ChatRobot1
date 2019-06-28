/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80014
Source Host           : localhost:3306
Source Database       : 51

Target Server Type    : MYSQL
Target Server Version : 80014
File Encoding         : 65001

Date: 2019-06-28 14:59:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
  `id` int(11) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `contacts` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sellercall` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sellerstudentnumber` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of books
-- ----------------------------
INSERT INTO `books` VALUES ('0', '3', '1', '4', '5', '6');

-- ----------------------------
-- Table structure for conversation_content
-- ----------------------------
DROP TABLE IF EXISTS `conversation_content`;
CREATE TABLE `conversation_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL COMMENT '类型：img,video,word,file',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of conversation_content
-- ----------------------------
INSERT INTO `conversation_content` VALUES ('22', '???', null);
INSERT INTO `conversation_content` VALUES ('40', '秒买哦', null);
INSERT INTO `conversation_content` VALUES ('41', '往往', null);
INSERT INTO `conversation_content` VALUES ('42', '虎皮', null);
INSERT INTO `conversation_content` VALUES ('43', '虎皮墙角', null);
INSERT INTO `conversation_content` VALUES ('44', '瞄瞄', null);
INSERT INTO `conversation_content` VALUES ('45', 'back', null);
INSERT INTO `conversation_content` VALUES ('46', '瞄瞄', null);
INSERT INTO `conversation_content` VALUES ('47', 'back0', null);
INSERT INTO `conversation_content` VALUES ('48', '往往', null);
INSERT INTO `conversation_content` VALUES ('49', 'WANGDA', null);
INSERT INTO `conversation_content` VALUES ('50', '往往111', null);
INSERT INTO `conversation_content` VALUES ('51', 'WANGDA222', null);
INSERT INTO `conversation_content` VALUES ('52', '今天8月20号', null);
INSERT INTO `conversation_content` VALUES ('53', '8月20 号的日记', null);
INSERT INTO `conversation_content` VALUES ('54', '你是谁', null);
INSERT INTO `conversation_content` VALUES ('55', '我是谁', null);
INSERT INTO `conversation_content` VALUES ('56', '今天是8.26', null);
INSERT INTO `conversation_content` VALUES ('57', '8.26日记', null);

-- ----------------------------
-- Table structure for people
-- ----------------------------
DROP TABLE IF EXISTS `people`;
CREATE TABLE `people` (
  `ID` int(100) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `age` int(3) NOT NULL,
  `work` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `residence` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `best_friend` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`ID`,`work`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of people
-- ----------------------------
INSERT INTO `people` VALUES ('1', '老李', '50', '民工', '青羊区', '老红');
INSERT INTO `people` VALUES ('2', '老孙', '50', '民工', '青羊区', '老hong');
INSERT INTO `people` VALUES ('3', '老王', '50', '民工', '青羊区', '老孙');

-- ----------------------------
-- Table structure for students
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of students
-- ----------------------------
INSERT INTO `students` VALUES ('1', '张三', '65');
INSERT INTO `students` VALUES ('2', 'www', '25');
INSERT INTO `students` VALUES ('3', 'zhang', '25');
INSERT INTO `students` VALUES ('21', '得到', '11');
INSERT INTO `students` VALUES ('22', '得到', '11');
INSERT INTO `students` VALUES ('23', '傲视', '22');
INSERT INTO `students` VALUES ('29', '333', '55');
INSERT INTO `students` VALUES ('30', '44', '66');
INSERT INTO `students` VALUES ('31', '55', '66');
INSERT INTO `students` VALUES ('32', '44', '55');
INSERT INTO `students` VALUES ('33', '55', '66');
INSERT INTO `students` VALUES ('34', '噢噢', '55');
INSERT INTO `students` VALUES ('35', '77', '99');
INSERT INTO `students` VALUES ('36', '99', '88');

-- ----------------------------
-- Table structure for talk
-- ----------------------------
DROP TABLE IF EXISTS `talk`;
CREATE TABLE `talk` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `come_id` int(11) DEFAULT NULL,
  `back_id` int(11) DEFAULT NULL,
  `types` varchar(50) DEFAULT NULL COMMENT '模块',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of talk
-- ----------------------------
INSERT INTO `talk` VALUES ('1', '42', '43', null);
INSERT INTO `talk` VALUES ('2', '48', '0', null);
INSERT INTO `talk` VALUES ('3', '50', '51', null);
INSERT INTO `talk` VALUES ('4', '52', '53', null);
INSERT INTO `talk` VALUES ('5', '54', '55', null);
INSERT INTO `talk` VALUES ('6', '56', '57', null);

-- ----------------------------
-- Table structure for tb
-- ----------------------------
DROP TABLE IF EXISTS `tb`;
CREATE TABLE `tb` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb
-- ----------------------------
INSERT INTO `tb` VALUES ('1', 'qq');
INSERT INTO `tb` VALUES ('2', 'qq');
INSERT INTO `tb` VALUES ('3', 'qq');
INSERT INTO `tb` VALUES ('4', 'qq');
INSERT INTO `tb` VALUES ('5', 'qq');
INSERT INTO `tb` VALUES ('6', 'qq');
INSERT INTO `tb` VALUES ('7', 'qq');
INSERT INTO `tb` VALUES ('8', 'qq');
INSERT INTO `tb` VALUES ('9', 'qq');
INSERT INTO `tb` VALUES ('10', 'qq');
INSERT INTO `tb` VALUES ('11', 'qq');
INSERT INTO `tb` VALUES ('12', 'qq');
INSERT INTO `tb` VALUES ('13', 'qq');
INSERT INTO `tb` VALUES ('14', 'qq');
INSERT INTO `tb` VALUES ('15', 'qq');
INSERT INTO `tb` VALUES ('16', 'qq');
INSERT INTO `tb` VALUES ('17', 'qq');
INSERT INTO `tb` VALUES ('18', 'qq');
INSERT INTO `tb` VALUES ('19', 'qq');
INSERT INTO `tb` VALUES ('20', 'qq');
INSERT INTO `tb` VALUES ('21', 'qq');
INSERT INTO `tb` VALUES ('22', 'qq');
INSERT INTO `tb` VALUES ('23', 'qq');
INSERT INTO `tb` VALUES ('24', 'qq');
INSERT INTO `tb` VALUES ('25', 'qq');
INSERT INTO `tb` VALUES ('26', 'qq');
INSERT INTO `tb` VALUES ('27', 'qq');
INSERT INTO `tb` VALUES ('28', 'qq');
INSERT INTO `tb` VALUES ('29', 'qq');
INSERT INTO `tb` VALUES ('30', 'qq');
INSERT INTO `tb` VALUES ('31', 'qq');
INSERT INTO `tb` VALUES ('32', 'qq');
INSERT INTO `tb` VALUES ('33', 'qq');
INSERT INTO `tb` VALUES ('34', 'qq');
INSERT INTO `tb` VALUES ('35', 'qq');
INSERT INTO `tb` VALUES ('36', 'qq');
INSERT INTO `tb` VALUES ('37', 'qq');
INSERT INTO `tb` VALUES ('38', 'qq');
INSERT INTO `tb` VALUES ('39', 'qq');
INSERT INTO `tb` VALUES ('40', 'qq');
INSERT INTO `tb` VALUES ('41', '老李');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户邮箱',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户身份',
  `status` int(1) NOT NULL COMMENT '用户状态',
  `regTime` datetime NOT NULL COMMENT '注册时间',
  `regIp` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '注册IP',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'xxx', 'xxxxx', 'xxxxx', 'root', '0', '2017-03-28 09:40:31', '127.0.0.1');
INSERT INTO `user` VALUES ('18', 'JSDL', 'DFJSILF', 'DFJSIL', 'DFJSI', '1', '2018-03-29 10:49:25', '1211');
INSERT INTO `user` VALUES ('19', 'dsa', 'sahku', 'sah', 's', '1', '2018-03-29 10:57:08', '1211');
INSERT INTO `user` VALUES ('20', 'dsfj', 'sjdkf', 'fdjsk', 'dsjk', '1', '2018-03-29 10:59:24', '1211');
INSERT INTO `user` VALUES ('21', 'hsuai', 'fdhsiu', 'chsd', 'dvshiu', '1', '2018-03-29 11:01:51', '1211');
INSERT INTO `user` VALUES ('22', '3524', '6eyiro', '3628', '非', '1', '2018-03-29 14:53:02', '1211');
INSERT INTO `user` VALUES ('23', '覆盖时', 'iwye', '丢', '都是', '1', '2018-03-29 14:54:22', '1211');
INSERT INTO `user` VALUES ('24', '还得', 'hoif', ' 带上我', 'foil', '1', '2018-03-29 14:57:07', '1211');
INSERT INTO `user` VALUES ('25', '啊哟搭配', ' jfodaj ', ' of拍摄角度哦 就', '破解方式的', '1', '2018-03-29 14:57:53', '1211');
INSERT INTO `user` VALUES ('26', '翻跟斗', 'ofdsi ', '富哦电视剧', '欧迪芬', '1', '2018-03-29 15:04:19', '1211');
INSERT INTO `user` VALUES ('27', '共有', 'yttf', '并与', '预付', '1', '2018-03-29 15:08:12', '1211');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `studentnumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号\r\n',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `schoolname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NO` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`NO`,`studentnumber`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------

-- ----------------------------
-- Table structure for 房子
-- ----------------------------
DROP TABLE IF EXISTS `房子`;
CREATE TABLE `房子` (
  `地址` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `距离` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `价格` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `空调` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `方便` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `舒适度` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of 房子
-- ----------------------------
INSERT INTO `房子` VALUES ('政府东苑', '11', '580', '无', '50', '80');
INSERT INTO `房子` VALUES ('高攀路东苑', '17', '550', '无', '20', '50');
INSERT INTO `房子` VALUES ('桂溪苑', '17', '550', '无', '60', '30');
INSERT INTO `房子` VALUES ('天符长城', '11', '700', '有', '60', '60');
INSERT INTO `房子` VALUES ('凯丽', '8', '685', '无', '20', '60');
INSERT INTO `房子` VALUES ('锦馨B', '12', '600', '无', '60', '60');
INSERT INTO `房子` VALUES ('礼顿山', '9', '760', '无', '30', '60');

-- ----------------------------
-- Procedure structure for test1
-- ----------------------------
DROP PROCEDURE IF EXISTS `test1`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `test1`()
begin
DECLARE i bigint;
set i=1;
while i<=3 do
insert into TB
(NAME) SELECT name FROM people WHERE ID=1;
set i=i+1;
END while;
END
;;
DELIMITER ;
