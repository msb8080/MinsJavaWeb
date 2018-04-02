/*
Navicat MySQL Data Transfer

Source Server         : localmysql
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-04-02 20:17:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `departments`
-- ----------------------------
DROP TABLE IF EXISTS `departments`;
CREATE TABLE `departments` (
  `department_id` int(4) NOT NULL,
  `department_name` varchar(30) DEFAULT NULL,
  `manager_id` int(6) DEFAULT NULL,
  `location_id` int(4) DEFAULT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of departments
-- ----------------------------
INSERT INTO `departments` VALUES ('2001', '技术部', null, '1001');
INSERT INTO `departments` VALUES ('2002', '劳动部', null, '1002');
INSERT INTO `departments` VALUES ('2003', '休闲部', null, '1001');
INSERT INTO `departments` VALUES ('2004', '技术部2', null, '1002');
INSERT INTO `departments` VALUES ('2005', '阿里巴巴', null, '1005');
INSERT INTO `departments` VALUES ('2006', '网易', null, '1005');
INSERT INTO `departments` VALUES ('2007', '微软', null, '1004');
INSERT INTO `departments` VALUES ('2008', '富士康', null, '1006');

-- ----------------------------
-- Table structure for `employees`
-- ----------------------------
DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees` (
  `employee_id` int(6) NOT NULL,
  `department_id` int(6) DEFAULT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(25) NOT NULL,
  `email` varchar(25) NOT NULL,
  `salary` int(8) DEFAULT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employees
-- ----------------------------
INSERT INTO `employees` VALUES ('3001', '2005', null, '马云', '111223@qq.com', '100005000');
INSERT INTO `employees` VALUES ('3002', '2001', null, '郭靖', 'guojing@163.com', '10000');
INSERT INTO `employees` VALUES ('3003', '2002', null, '黄蓉', 'huangrong@qq.com', '200000');
INSERT INTO `employees` VALUES ('3004', '2003', null, '成龙', 'jacson_chen@163.com', '2000000');
INSERT INTO `employees` VALUES ('3005', '2004', null, '沈腾', 'shengteng@qq.com', '20000');
INSERT INTO `employees` VALUES ('3006', '2006', null, '王思聪', 'wangsicong@qq.com', '2222222');
INSERT INTO `employees` VALUES ('3007', '2007', null, 'john', 'john@qq.com', '211111');
INSERT INTO `employees` VALUES ('3008', '2008', null, '李二狗', 'ligou@qq.com', '2333');
INSERT INTO `employees` VALUES ('3009', '2008', null, '姜大牙', 'jiangdaya@qq.com', '20003');

-- ----------------------------
-- Table structure for `locations`
-- ----------------------------
DROP TABLE IF EXISTS `locations`;
CREATE TABLE `locations` (
  `location_id` int(4) NOT NULL,
  `street_address` varchar(40) DEFAULT NULL,
  `postal_code` varchar(12) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `state_province` varchar(25) DEFAULT NULL,
  `country_id` char(2) DEFAULT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of locations
-- ----------------------------
INSERT INTO `locations` VALUES ('1001', null, null, '北京', '北京', null);
INSERT INTO `locations` VALUES ('1002', null, null, '上海', '上海', null);
INSERT INTO `locations` VALUES ('1004', null, null, '纽约', '纽约', null);
INSERT INTO `locations` VALUES ('1005', null, null, '杭州', '浙江省', null);
INSERT INTO `locations` VALUES ('1006', null, null, '郑州', '河南省', null);
