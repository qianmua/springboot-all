/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50529
 Source Host           : localhost:3306
 Source Schema         : shiro

 Target Server Type    : MySQL
 Target Server Version : 50529
 File Encoding         : 65001

 Date: 08/11/2020 22:34:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for perms
-- ----------------------------
DROP TABLE IF EXISTS `perms`;
CREATE TABLE `perms`  (
  `id` int(6) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称\r\n',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限url',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of perms
-- ----------------------------
INSERT INTO `perms` VALUES (1, 'user:*:*', 'user/');
INSERT INTO `perms` VALUES (2, 'product:*:01', NULL);
INSERT INTO `perms` VALUES (3, 'order:*:*', NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(6) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin');
INSERT INTO `role` VALUES (2, 'user');
INSERT INTO `role` VALUES (3, 'prouct');

-- ----------------------------
-- Table structure for role_perms
-- ----------------------------
DROP TABLE IF EXISTS `role_perms`;
CREATE TABLE `role_perms`  (
  `id` int(6) NOT NULL COMMENT '用户权限表id',
  `roleId` int(6) NULL DEFAULT NULL COMMENT '角色表id',
  `permsid` int(11) NULL DEFAULT NULL COMMENT '权限表id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role_perms
-- ----------------------------
INSERT INTO `role_perms` VALUES (1, 1, 1);
INSERT INTO `role_perms` VALUES (2, 1, 2);
INSERT INTO `role_perms` VALUES (3, 2, 1);
INSERT INTO `role_perms` VALUES (4, 3, 2);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加盐',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zym', '22300f832e0c59aca27b57128bba9ddb', 'IDO4F!fQ');
INSERT INTO `user` VALUES (2, 'zl', 'ba7c976700a5cc5f7fefdb9431f37c31', 'gn0p0b0d');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(6) NOT NULL COMMENT '关联表id',
  `userId` int(6) NULL DEFAULT NULL COMMENT '用户表id',
  `roleId` int(6) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1);
INSERT INTO `user_role` VALUES (2, 2, 2);
INSERT INTO `user_role` VALUES (3, 2, 3);
INSERT INTO `user_role` VALUES (4, 3, 2);
INSERT INTO `user_role` VALUES (5, 1, 3);

SET FOREIGN_KEY_CHECKS = 1;
