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

 Date: 16/02/2020 21:00:58
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
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0代表女，1代表男',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dept_id` int(11) NULL DEFAULT NULL COMMENT '设置外键关联tbl_dept.id 和tbl_employee.dept_id时，外键的属性和长度都要一致',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_emp_dept`(`dept_id`) USING BTREE,
  CONSTRAINT `fk_emp_dept` FOREIGN KEY (`dept_id`) REFERENCES `tbl_dept` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_employee
-- ----------------------------
INSERT INTO `tbl_employee` VALUES (1, 'Mr Chen', '1', 'chzeyi@126.com', 1);
INSERT INTO `tbl_employee` VALUES (2, 'MyGirl2', '0', 'MyGirl@126.com', 2);
INSERT INTO `tbl_employee` VALUES (3, 'Jerry', '1', 'Jerry@aliyun.com', 5);
INSERT INTO `tbl_employee` VALUES (4, 'Marry', '0', 'Marry@qq.com', 4);
INSERT INTO `tbl_employee` VALUES (5, '卢本伟', '0', 'lubenwei@163.com', 5);
INSERT INTO `tbl_employee` VALUES (6, 'PDD', '0', 'pdd@188.com', 5);
INSERT INTO `tbl_employee` VALUES (7, 'Daniel Wu', '1', 'DanielWu@188.com', 3);
INSERT INTO `tbl_employee` VALUES (8, 'Daniel Lu', '1', 'DanielWu@188.com', 3);
INSERT INTO `tbl_employee` VALUES (9, '彭于晏', '1', '彭于晏@188.com', 2);

SET FOREIGN_KEY_CHECKS = 1;
