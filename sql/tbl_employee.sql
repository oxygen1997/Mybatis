/*
 Navicat Premium Data Transfer

 Source Server         : localhost@root-3306
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : mybatis

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 25/02/2020 12:16:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_employee
-- ----------------------------
DROP TABLE IF EXISTS `tbl_employee`;
CREATE TABLE `tbl_employee`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` int(1) NULL DEFAULT NULL COMMENT '0代表女，1代表男',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dept_id` int(11) NULL DEFAULT NULL COMMENT '设置外键关联tbl_dept.id 和tbl_employee.dept_id时，外键的属性和长度都要一致',
  `employee_enum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '枚举状态类型',
  `salary` decimal(10, 2) UNSIGNED NULL DEFAULT NULL COMMENT '工资',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_emp_dept`(`dept_id`) USING BTREE,
  CONSTRAINT `fk_emp_dept` FOREIGN KEY (`dept_id`) REFERENCES `tbl_dept` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_employee
-- ----------------------------
INSERT INTO `tbl_employee` VALUES (1, '许文强', 1, 'xyq@126.com', 1, '100', 50000.00);
INSERT INTO `tbl_employee` VALUES (2, '冯程程', 0, 'fcc@126.com', 2, '300', 50000.00);
INSERT INTO `tbl_employee` VALUES (3, '刘备', 1, 'lb@aliyun.com', 5, '100', 10000.00);
INSERT INTO `tbl_employee` VALUES (4, '张飞', 0, 'zf@qq.com', 4, '200', 5000.00);
INSERT INTO `tbl_employee` VALUES (5, '卢本伟', 1, 'lubenwei@163.com', 5, '300', 20000.00);
INSERT INTO `tbl_employee` VALUES (6, '关羽', 1, 'gy@188.com', 5, '300', 20000.00);
INSERT INTO `tbl_employee` VALUES (7, '吴彦祖', 0, 'wyz@cool.com', 3, '100', 30000.00);
INSERT INTO `tbl_employee` VALUES (8, '孙权', 1, 'sq@188.com', 3, '200', 30000.00);
INSERT INTO `tbl_employee` VALUES (9, '彭于晏', 1, 'pyy@188.com', 2, '100', 18888.00);
INSERT INTO `tbl_employee` VALUES (10, '乔峰', 1, 'qf@cool.com', 1, '300', 23333.00);
INSERT INTO `tbl_employee` VALUES (11, '陈冠希', 1, 'cgx@cool.com', 1, '200', 15555.00);
INSERT INTO `tbl_employee` VALUES (12, 'Test_Enum_谢逊', 1, 'xxEnum@test.com', 1, '100', 2000.00);
INSERT INTO `tbl_employee` VALUES (13, 'Test_Enum_张无忌', 1, 'zwjEnum@test.com', 5, '200', 3500.00);
INSERT INTO `tbl_employee` VALUES (14, 'Test_Enum_周芷若', 0, 'zzrEnum@test.com', 4, '300', 4500.00);

SET FOREIGN_KEY_CHECKS = 1;
