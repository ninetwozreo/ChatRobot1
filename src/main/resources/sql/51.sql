/*
 Navicat Premium Data Transfer

 Source Server         : 11
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : 51

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 07/07/2019 16:05:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for books
-- ----------------------------
-- DROP TABLE IF EXISTS `books`;
-- CREATE TABLE `books`  (
--   `id` int(11) NOT NULL,
--   `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
--   `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
--   `contacts` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
--   `sellercall` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
--   `sellerstudentnumber` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
--   PRIMARY KEY (`id`) USING BTREE
-- ) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for conversation_content
-- ----------------------------
DROP TABLE IF EXISTS `robot_conversation_content`;
CREATE TABLE `robot_conversation_content`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `words` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类型：file，word，video，img',
  `create_date` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `times` int(11) DEFAULT NULL,
  `file_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `CHARACTERED` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for talk
-- ----------------------------
DROP TABLE IF EXISTS `robot_talk_relation`;
CREATE TABLE `robot_talk_relation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `come_id` int(11) DEFAULT NULL,
  `back_id` int(11) DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '模块类型，待扩展使用',
  `create_date` date DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户邮箱',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户身份',
  `status` int(1) NOT NULL COMMENT '用户状态',
  `regTime` datetime(0) NOT NULL COMMENT '注册时间',
  `regIp` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '注册IP',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for users
-- ----------------------------
-- DROP TABLE IF EXISTS `users`;
-- CREATE TABLE `users`  (
--   `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
--   `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
--   `studentnumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号\r\n',
--   `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
--   `schoolname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
--   `NO` int(11) NOT NULL AUTO_INCREMENT,
--   PRIMARY KEY (`NO`, `studentnumber`) USING BTREE
-- ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Procedure structure for test1
-- ----------------------------
DROP PROCEDURE IF EXISTS `test1`;
delimiter ;;
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
delimiter ;

-- ----------------------------
-- Function structure for yy
-- ----------------------------
DROP FUNCTION IF EXISTS `yy`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `yy`() RETURNS int(11)
BEGIN
DECLARE i int; -- 声明变量
DECLARE groupid int;
set i=LAST_INSERT_ID();  -- 拿到最后一个插入的id，避免id冲突
WHILE i<5000 do   -- 插入数据的多少
set groupid=RAND()*100;   -- 随机函数 RAND()生成的是0~1之间的小数，乘以多少就可以放大多少倍，如起点不是0，则加上起点数 如20~80：RAND()*60+20
INSERT INTO `crm_project_list`(project_list_name, group_id, project_list_enable_from, project_list_enable_to,   
delete_flag, register_user_id, register_datetime, modify_user_id, modify_datetime, version) VALUES (CONCAT('listName',i), groupid, '2016-04-18', '2016-04-18', '0', '5', '2016-04-18 16:50:17', '5', '2016-04-18 16:50:20', '0');  -- 插入数据，可以直接在navicat里面创建一条数据，然后把数据和表结构导出SQL，文件中就会有插入语句，直接复制过来修改即可。

SET i=i+1; 
RETURN 0;
END 
WHILE;

END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
