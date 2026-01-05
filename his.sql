/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : his

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 05/01/2026 14:17:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_doctor
-- ----------------------------
DROP TABLE IF EXISTS `tb_doctor`;
CREATE TABLE `tb_doctor`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '医生编号（登录账号）',
  `realname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '医生姓名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `dept_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所在科室',
  `regist_level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '挂号级别',
  `registfee` double NULL DEFAULT NULL COMMENT '挂号费用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_doctor
-- ----------------------------
INSERT INTO `tb_doctor` VALUES ('1001', '华佗', '111', '骨科', '普通', 10);
INSERT INTO `tb_doctor` VALUES ('1002', '扁鹊', '222', '骨科', '主任', 70);
INSERT INTO `tb_doctor` VALUES ('1003', '安道全', '333', '呼吸内科', '副主任', 50);
INSERT INTO `tb_doctor` VALUES ('1004', '韩亚玲', '444', '呼吸内科', '专家', 100);
INSERT INTO `tb_doctor` VALUES ('1005', '张仲景', '555', '神经内科', '主任', 80);
INSERT INTO `tb_doctor` VALUES ('guahao', '挂号员', 'guahao', '前台', '管理员', 0);
INSERT INTO `tb_doctor` VALUES ('yaofang', '药剂师', 'yaofang', '药房', '管理员', 0);

-- ----------------------------
-- Table structure for tb_registinfo
-- ----------------------------
DROP TABLE IF EXISTS `tb_registinfo`;
CREATE TABLE `tb_registinfo`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '病历号(自动生成)',
  `realname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '患者姓名',
  `gender` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '患者性别',
  `card_number` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '患者身份证号',
  `birthdate` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '出生年月',
  `age` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '年龄',
  `home_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '家庭住址',
  `dept_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '挂号科室',
  `doctor_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '挂号医生',
  `regist_level` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '挂号级别',
  `is_book` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否需要病历本',
  `registfee` double NULL DEFAULT 0 COMMENT '挂号费',
  `regist_date` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '挂号日期',
  `diagiosis` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '诊断结果',
  `prescrption` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '开药处方',
  `drug_price` double NULL DEFAULT 0 COMMENT '开药总价',
  `visit_state` int(11) NOT NULL COMMENT '看诊状态(1:已挂号, 2:已看诊, 3:已取药)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100001 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_registinfo
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
