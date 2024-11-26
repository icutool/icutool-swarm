/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50724 (5.7.24)
 Source Host           : localhost:3306
 Source Schema         : icutool

 Target Server Type    : MySQL
 Target Server Version : 50724 (5.7.24)
 File Encoding         : 65001

 Date: 25/09/2023 22:43:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品类型',
  `sni` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品编号及商品编号',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品描述',
  `product` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES (1, '墨盒', 'X4E79AA', '680BlackInkCartridge2-Pack', '联强/神码', 100.00);
INSERT INTO `products` VALUES (2, '墨盒', 'X4E78AA', '680Color/BlackInkCrtgCombo2-Pk', '联强/神码', 101.00);
INSERT INTO `products` VALUES (3, '墨盒', 'X4E75AA', 'BlackPrinthead', '联强/神码', 102.00);
INSERT INTO `products` VALUES (4, '墨盒', 'X4D20AC', '993AC BlkContractPageWideCrtg', '紫光', 103.00);
INSERT INTO `products` VALUES (5, '墨盒', 'X4D17AC', '993AC YelContractPageWideCrtg', '紫光', 104.00);
INSERT INTO `products` VALUES (6, '墨盒', 'X4D14AC', '993AC MagContractPageWideCrtg', '紫光', 105.00);
INSERT INTO `products` VALUES (7, '墨盒', 'X4D11AC', '993AC CyanContractPageWideCrtg', '紫光', 106.00);
INSERT INTO `products` VALUES (8, '墨盒', 'T6N12AA', '804XL黑色墨盒', '联强/神码', 107.00);
INSERT INTO `products` VALUES (9, '墨盒', 'T6N11AA', '804XL彩色墨盒', '联强/神码', 108.00);
INSERT INTO `products` VALUES (10, '墨盒', 'T6N10AA', '804黑色墨盒', '联强/神码', 109.00);
INSERT INTO `products` VALUES (11, '墨盒', 'T6N09AA', '804彩色墨盒', '联强/神码', 110.00);
INSERT INTO `products` VALUES (12, '墨盒', 'T6M21AA', '909XL黑色墨盒', '紫光/佳杰', 111.00);
INSERT INTO `products` VALUES (13, '墨盒', 'T6M17AA', '905XL黑色墨盒', '紫光/佳杰', 112.00);
INSERT INTO `products` VALUES (14, '墨盒', 'T6M13AA', '905XL黄色墨盒', '紫光/佳杰', 113.00);
INSERT INTO `products` VALUES (15, '墨盒', 'T6M09AA', '905XL品色墨盒', '紫光/佳杰', 114.00);
INSERT INTO `products` VALUES (16, '墨盒', 'T6M05AA', '905XL青色墨盒', '紫光/佳杰', 115.00);
INSERT INTO `products` VALUES (17, '墨盒', 'T6M01AA', '905黑色墨盒', '紫光/佳杰', 116.00);
INSERT INTO `products` VALUES (18, '墨盒', 'T6L97AA', '905黄色墨盒', '紫光/佳杰', 117.00);
INSERT INTO `products` VALUES (19, '墨盒', 'T6L93AA', '905品色墨盒', '紫光/佳杰', 118.00);
INSERT INTO `products` VALUES (20, '墨盒', 'T6L89AA', '905青色墨盒', '紫光/佳杰', 119.00);
INSERT INTO `products` VALUES (21, '墨盒', 'T0A83AA', '950xl双黑墨盒套装', '紫光/佳杰', 120.00);
INSERT INTO `products` VALUES (22, '墨盒', 'T0A82AA', '950XL/951XL黑彩墨盒套装', '紫光/佳杰', 121.00);
INSERT INTO `products` VALUES (23, '墨盒', 'T0A81AA', '932xl双黑墨盒套装', '紫光/佳杰', 122.00);
INSERT INTO `products` VALUES (24, '墨盒', 'T0A80AA', '932XL/933XL黑彩墨盒套装', '紫光/佳杰', 123.00);
INSERT INTO `products` VALUES (25, '墨盒', 'M0H56AA', 'GT52黄色原装墨水瓶', '联强/神码', 132.00);
INSERT INTO `products` VALUES (26, '墨盒', 'M0H55AA', 'GT52品色原装墨水瓶', '联强/神码', 133.00);
INSERT INTO `products` VALUES (27, '墨盒', 'M0H54AA', 'GT52青色原装墨水瓶', '联强/神码', 134.00);
INSERT INTO `products` VALUES (28, '墨盒', 'M0H51AA', 'BlackPrinthead', '联强/神码', 135.00);
INSERT INTO `products` VALUES (29, '墨盒', 'M0H50AA', 'Tri-ColourPrinthead', '联强/神码', 136.00);
INSERT INTO `products` VALUES (30, '墨盒', 'L0S72AA', '955XL黑色墨盒', '紫光/佳杰', 137.00);
INSERT INTO `products` VALUES (31, '墨盒', 'L0S69AA', '955XL黄色墨盒', '紫光/佳杰', 138.00);
INSERT INTO `products` VALUES (32, '墨盒', 'L0S66AA', '955XL品色墨盒', '紫光/佳杰', 139.00);
INSERT INTO `products` VALUES (33, '墨盒', 'L0S63AA', '955XL青色墨盒', '紫光/佳杰', 140.00);
INSERT INTO `products` VALUES (34, '墨盒', 'L0S60AA', '955黑色墨盒', '紫光/佳杰', 141.00);
INSERT INTO `products` VALUES (35, '墨盒', 'L0S57AA', '955黄色墨盒', '紫光/佳杰', 142.00);
INSERT INTO `products` VALUES (36, '墨盒', 'L0S54AA', '955品色墨盒', '紫光/佳杰', 143.00);
INSERT INTO `products` VALUES (37, '墨盒', 'L0S51AA', '955青色墨盒', '紫光/佳杰', 144.00);
INSERT INTO `products` VALUES (38, '墨盒', 'L0S24AA', '678黑彩墨盒套装', '联强/神码', 145.00);
INSERT INTO `products` VALUES (39, '墨盒', 'L0S23AA', '678双黑墨盒套装', '联强/神码', 146.00);
INSERT INTO `products` VALUES (40, '墨盒', 'L0S21AA', '802双黑墨盒套装', '联强/神码', 147.00);
INSERT INTO `products` VALUES (41, '墨盒', 'L0S09AA', '975X黑色页宽打印机耗材', '联强/神码', 148.00);
INSERT INTO `products` VALUES (42, '墨盒', 'L0S06AA', '975X黄色页宽打印机耗材', '联强/神码', 149.00);
INSERT INTO `products` VALUES (43, '墨盒', 'L0S03AA', '975X品色页宽打印机耗材', '联强/神码', 150.00);
INSERT INTO `products` VALUES (44, '墨盒', 'L0S00AA', '975X青色页宽打印机耗材', '联强/神码', 151.00);
INSERT INTO `products` VALUES (45, '墨盒', 'L0R97AA', '975A黑色页宽打印机耗材', '联强/神码', 152.00);
INSERT INTO `products` VALUES (46, '墨盒', 'L0R94AA', '975A黄色页宽打印机耗材', '联强/神码', 153.00);
INSERT INTO `products` VALUES (47, '墨盒', 'L0R91AA', '975A品色页宽打印机耗材', '联强/神码', 154.00);
INSERT INTO `products` VALUES (48, '墨盒', 'L0R88AA', '975A青色页宽打印机耗材', '联强/神码', 155.00);
INSERT INTO `products` VALUES (49, '墨盒', 'L0R42AA', '959XL黑色墨盒', '紫光/佳杰', 156.00);
INSERT INTO `products` VALUES (50, '墨盒', 'L0R16A', '981Y黑色页宽打印机耗材', '紫光', 157.00);
INSERT INTO `products` VALUES (51, '墨盒', 'L0R15A', '981Y黄色页宽打印机耗材', '紫光', 158.00);
INSERT INTO `products` VALUES (52, '墨盒', 'L0R14A', '981Y品色页宽打印机耗材', '紫光', 159.00);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'NULL' COMMENT '菜单名',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_by` bigint(20) NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `del_flag` int(11) NULL DEFAULT 0 COMMENT '是否删除（0未删除 1已删除）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色权限字符串',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
  `del_flag` int(1) NULL DEFAULT 0 COMMENT 'del_flag',
  `create_by` bigint(200) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_by` bigint(200) NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(200) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `menu_id` bigint(200) NOT NULL DEFAULT 0 COMMENT '菜单id',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'NULL' COMMENT '用户名',
  `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'NULL' COMMENT '昵称',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'NULL' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户性别（0男，1女，2未知）',
  `avatar` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `user_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT '用户类型（0管理员，1普通用户）',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人的用户id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) NULL DEFAULT 0 COMMENT '删除标志（0代表未删除，1代表已删除）',
  `open_id` varchar(255) DEFAULT NULL COMMENT '微信openid',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

ALTER TABLE sys_user ADD CONSTRAINT unique_constraint_openId UNIQUE (`open_id`);
-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '涛哥', '$2a$10$BGwqfJBYLhgx5lO0zLQpjOvfSAT2D1lDFcf8XLhwFAsajr8UrZTIe', '0', '599653466@qq.com', '17681267123', '1', NULL, '1', NULL, NULL, NULL, NULL, 0, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(200) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `role_id` bigint(200) NOT NULL DEFAULT 0 COMMENT '角色id',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
