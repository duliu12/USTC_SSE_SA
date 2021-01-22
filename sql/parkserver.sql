/*
Navicat MySQL Data Transfer

Source Server         : aliyun5.7
Source Server Version : 50729
Source Host           : 47.96.22.144:3307
Source Database       : parkserver

Target Server Type    : MYSQL
Target Server Version : 50729
File Encoding         : 65001

Date: 2021-01-22 20:20:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `name` varchar(50) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `ip` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('admin', '123123', '2020-12-17 19:44:11', '127.0.0.1');

-- ----------------------------
-- Table structure for `client_load`
-- ----------------------------
DROP TABLE IF EXISTS `client_load`;
CREATE TABLE `client_load` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `client_load` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '0表示日常记录，1表示峰值，2表示谷值，3表示当日总值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=296 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of client_load
-- ----------------------------
INSERT INTO `client_load` VALUES ('38', '1', '2020-12-11 12:09:26', '6', '1');
INSERT INTO `client_load` VALUES ('39', '1', '2020-12-11 00:00:00', '24', '3');
INSERT INTO `client_load` VALUES ('40', '1', '2020-12-11 12:07:48', '0', '2');
INSERT INTO `client_load` VALUES ('41', '1', '2020-12-11 12:28:22', '7', '0');
INSERT INTO `client_load` VALUES ('42', '1', '2020-12-11 14:48:16', '0', '0');
INSERT INTO `client_load` VALUES ('43', '1', '2020-12-11 15:09:34', '4', '0');
INSERT INTO `client_load` VALUES ('44', '1', '2020-12-11 15:26:14', '4', '0');
INSERT INTO `client_load` VALUES ('45', '2', '2020-12-11 20:28:33', '2', '1');
INSERT INTO `client_load` VALUES ('46', '2', '2020-12-11 00:00:00', '4', '3');
INSERT INTO `client_load` VALUES ('47', '2', '2020-12-11 20:29:18', '1', '2');
INSERT INTO `client_load` VALUES ('48', '4', '2020-12-11 20:40:10', '5', '1');
INSERT INTO `client_load` VALUES ('49', '4', '2020-12-11 00:00:00', '6', '3');
INSERT INTO `client_load` VALUES ('50', '4', '2020-12-11 20:39:25', '2', '2');
INSERT INTO `client_load` VALUES ('51', '4', '2020-12-15 14:23:26', '1', '1');
INSERT INTO `client_load` VALUES ('52', '4', '2020-12-15 00:00:00', '1', '3');
INSERT INTO `client_load` VALUES ('53', '14', '2020-12-15 14:23:54', '2', '1');
INSERT INTO `client_load` VALUES ('54', '14', '2020-12-15 00:00:00', '2', '3');
INSERT INTO `client_load` VALUES ('55', '4', '2020-12-15 14:37:57', '1', '0');
INSERT INTO `client_load` VALUES ('56', '14', '2020-12-15 14:37:57', '2', '0');
INSERT INTO `client_load` VALUES ('57', '4', '2020-12-15 14:54:38', '1', '0');
INSERT INTO `client_load` VALUES ('58', '14', '2020-12-15 14:54:38', '2', '0');
INSERT INTO `client_load` VALUES ('59', '14', '2020-12-15 16:43:06', '0', '0');
INSERT INTO `client_load` VALUES ('60', '14', '2020-12-15 17:10:37', '0', '0');
INSERT INTO `client_load` VALUES ('61', '1', '2020-12-17 12:20:45', '0', '0');
INSERT INTO `client_load` VALUES ('62', '1', '2020-12-17 12:37:25', '0', '0');
INSERT INTO `client_load` VALUES ('63', '4', '2020-12-17 12:37:26', '0', '0');
INSERT INTO `client_load` VALUES ('64', '1', '2020-12-17 13:08:51', '0', '0');
INSERT INTO `client_load` VALUES ('65', '4', '2020-12-17 13:08:51', '0', '0');
INSERT INTO `client_load` VALUES ('66', '1', '2020-12-22 14:16:40', '0', '0');
INSERT INTO `client_load` VALUES ('67', '1', '2020-12-22 14:51:47', '0', '0');
INSERT INTO `client_load` VALUES ('68', '2', '2020-12-22 14:51:47', '0', '0');
INSERT INTO `client_load` VALUES ('69', '3', '2020-12-22 14:51:48', '0', '0');
INSERT INTO `client_load` VALUES ('70', '1', '2020-12-22 15:08:28', '0', '0');
INSERT INTO `client_load` VALUES ('71', '2', '2020-12-22 15:08:28', '0', '0');
INSERT INTO `client_load` VALUES ('72', '3', '2020-12-22 15:08:28', '0', '0');
INSERT INTO `client_load` VALUES ('73', '3', '2020-12-22 15:27:32', '2', '1');
INSERT INTO `client_load` VALUES ('74', '3', '2020-12-22 00:00:00', '2', '3');
INSERT INTO `client_load` VALUES ('75', '1', '2020-12-22 15:25:08', '0', '0');
INSERT INTO `client_load` VALUES ('76', '2', '2020-12-22 15:25:09', '0', '0');
INSERT INTO `client_load` VALUES ('77', '3', '2020-12-22 15:25:09', '1', '0');
INSERT INTO `client_load` VALUES ('78', '1', '2020-12-22 15:41:49', '0', '0');
INSERT INTO `client_load` VALUES ('79', '2', '2020-12-22 15:41:49', '0', '0');
INSERT INTO `client_load` VALUES ('80', '3', '2020-12-22 15:41:49', '2', '0');
INSERT INTO `client_load` VALUES ('81', '1', '2020-12-22 15:58:29', '0', '0');
INSERT INTO `client_load` VALUES ('82', '2', '2020-12-22 15:58:30', '0', '0');
INSERT INTO `client_load` VALUES ('83', '3', '2020-12-22 15:58:30', '2', '0');
INSERT INTO `client_load` VALUES ('84', '1', '2020-12-22 16:15:10', '0', '0');
INSERT INTO `client_load` VALUES ('85', '2', '2020-12-22 16:15:10', '0', '0');
INSERT INTO `client_load` VALUES ('86', '3', '2020-12-22 16:15:10', '2', '0');
INSERT INTO `client_load` VALUES ('87', '1', '2020-12-22 16:37:33', '0', '0');
INSERT INTO `client_load` VALUES ('88', '2', '2020-12-22 16:37:33', '0', '0');
INSERT INTO `client_load` VALUES ('89', '3', '2020-12-22 16:37:34', '2', '0');
INSERT INTO `client_load` VALUES ('90', '15', '2020-12-22 20:11:03', '2', '1');
INSERT INTO `client_load` VALUES ('91', '15', '2020-12-22 00:00:00', '4', '3');
INSERT INTO `client_load` VALUES ('92', '13', '2020-12-22 20:10:55', '1', '1');
INSERT INTO `client_load` VALUES ('93', '13', '2020-12-22 00:00:00', '2', '3');
INSERT INTO `client_load` VALUES ('94', '14', '2020-12-22 20:10:58', '1', '1');
INSERT INTO `client_load` VALUES ('95', '14', '2020-12-22 00:00:00', '2', '3');
INSERT INTO `client_load` VALUES ('96', '13', '2020-12-22 20:18:18', '1', '0');
INSERT INTO `client_load` VALUES ('97', '14', '2020-12-22 20:18:19', '0', '0');
INSERT INTO `client_load` VALUES ('98', '15', '2020-12-22 20:18:19', '2', '0');
INSERT INTO `client_load` VALUES ('99', '13', '2020-12-22 20:34:59', '1', '0');
INSERT INTO `client_load` VALUES ('100', '14', '2020-12-22 20:34:59', '1', '0');
INSERT INTO `client_load` VALUES ('101', '15', '2020-12-22 20:35:00', '2', '0');
INSERT INTO `client_load` VALUES ('102', '3', '2020-12-23 21:52:49', '1', '1');
INSERT INTO `client_load` VALUES ('103', '3', '2020-12-23 00:00:00', '1', '3');
INSERT INTO `client_load` VALUES ('104', '2', '2020-12-23 21:52:52', '0', '0');
INSERT INTO `client_load` VALUES ('105', '3', '2020-12-23 21:52:52', '1', '0');
INSERT INTO `client_load` VALUES ('106', '2', '2020-12-23 21:52:56', '1', '1');
INSERT INTO `client_load` VALUES ('107', '2', '2020-12-23 00:00:00', '2', '3');
INSERT INTO `client_load` VALUES ('108', '16', '2020-12-23 22:09:32', '0', '0');
INSERT INTO `client_load` VALUES ('109', '1', '2020-12-23 22:09:32', '0', '0');
INSERT INTO `client_load` VALUES ('110', '2', '2020-12-23 22:09:32', '0', '0');
INSERT INTO `client_load` VALUES ('111', '3', '2020-12-23 22:09:32', '1', '0');
INSERT INTO `client_load` VALUES ('112', '4', '2020-12-23 22:09:32', '0', '0');
INSERT INTO `client_load` VALUES ('113', '13', '2020-12-23 22:09:32', '0', '0');
INSERT INTO `client_load` VALUES ('114', '14', '2020-12-23 22:09:33', '0', '0');
INSERT INTO `client_load` VALUES ('115', '15', '2020-12-23 22:09:33', '0', '0');
INSERT INTO `client_load` VALUES ('116', '16', '2020-12-23 22:26:13', '0', '0');
INSERT INTO `client_load` VALUES ('117', '1', '2020-12-23 22:26:13', '0', '0');
INSERT INTO `client_load` VALUES ('118', '2', '2020-12-23 22:26:13', '0', '0');
INSERT INTO `client_load` VALUES ('119', '3', '2020-12-23 22:26:13', '1', '0');
INSERT INTO `client_load` VALUES ('120', '4', '2020-12-23 22:26:13', '0', '0');
INSERT INTO `client_load` VALUES ('121', '13', '2020-12-23 22:26:13', '0', '0');
INSERT INTO `client_load` VALUES ('122', '14', '2020-12-23 22:26:13', '0', '0');
INSERT INTO `client_load` VALUES ('123', '15', '2020-12-23 22:26:13', '0', '0');
INSERT INTO `client_load` VALUES ('124', '1', '2020-12-23 23:13:12', '3', '1');
INSERT INTO `client_load` VALUES ('125', '1', '2020-12-23 00:00:00', '3', '3');
INSERT INTO `client_load` VALUES ('126', '1', '2020-12-23 23:36:04', '0', '0');
INSERT INTO `client_load` VALUES ('127', '2', '2020-12-23 23:36:04', '0', '0');
INSERT INTO `client_load` VALUES ('128', '3', '2020-12-23 23:36:04', '0', '0');
INSERT INTO `client_load` VALUES ('129', '4', '2020-12-23 23:36:05', '0', '0');
INSERT INTO `client_load` VALUES ('130', '13', '2020-12-23 23:36:05', '0', '0');
INSERT INTO `client_load` VALUES ('131', '14', '2020-12-23 23:36:05', '0', '0');
INSERT INTO `client_load` VALUES ('132', '16', '2020-12-23 23:38:17', '1', '1');
INSERT INTO `client_load` VALUES ('133', '16', '2020-12-23 00:00:00', '1', '3');
INSERT INTO `client_load` VALUES ('134', '13', '2020-12-23 23:45:21', '1', '1');
INSERT INTO `client_load` VALUES ('135', '13', '2020-12-23 00:00:00', '1', '3');
INSERT INTO `client_load` VALUES ('136', '16', '2020-12-23 23:52:45', '1', '0');
INSERT INTO `client_load` VALUES ('137', '1', '2020-12-23 23:52:45', '0', '0');
INSERT INTO `client_load` VALUES ('138', '2', '2020-12-23 23:52:45', '0', '0');
INSERT INTO `client_load` VALUES ('139', '3', '2020-12-23 23:52:45', '0', '0');
INSERT INTO `client_load` VALUES ('140', '4', '2020-12-23 23:52:45', '0', '0');
INSERT INTO `client_load` VALUES ('141', '13', '2020-12-23 23:52:45', '1', '0');
INSERT INTO `client_load` VALUES ('142', '14', '2020-12-23 23:52:45', '0', '0');
INSERT INTO `client_load` VALUES ('143', '15', '2020-12-23 23:52:45', '0', '0');
INSERT INTO `client_load` VALUES ('144', '2', '2020-12-24 11:21:32', '4', '1');
INSERT INTO `client_load` VALUES ('145', '2', '2020-12-24 00:00:00', '4', '3');
INSERT INTO `client_load` VALUES ('146', '3', '2020-12-24 11:21:00', '1', '1');
INSERT INTO `client_load` VALUES ('147', '3', '2020-12-24 00:00:00', '1', '3');
INSERT INTO `client_load` VALUES ('148', '13', '2020-12-24 11:21:24', '1', '1');
INSERT INTO `client_load` VALUES ('149', '13', '2020-12-24 00:00:00', '1', '3');
INSERT INTO `client_load` VALUES ('150', '1', '2020-12-24 11:22:02', '1', '1');
INSERT INTO `client_load` VALUES ('151', '1', '2020-12-24 00:00:00', '1', '3');
INSERT INTO `client_load` VALUES ('152', '16', '2020-12-24 11:25:01', '2', '1');
INSERT INTO `client_load` VALUES ('153', '16', '2020-12-24 00:00:00', '3', '3');
INSERT INTO `client_load` VALUES ('154', '14', '2020-12-24 11:25:10', '2', '1');
INSERT INTO `client_load` VALUES ('155', '14', '2020-12-24 00:00:00', '2', '3');
INSERT INTO `client_load` VALUES ('156', '16', '2020-12-25 14:26:21', '0', '0');
INSERT INTO `client_load` VALUES ('157', '1', '2020-12-25 14:26:22', '0', '0');
INSERT INTO `client_load` VALUES ('158', '2', '2020-12-25 14:26:22', '0', '0');
INSERT INTO `client_load` VALUES ('159', '3', '2020-12-25 14:26:23', '0', '0');
INSERT INTO `client_load` VALUES ('160', '4', '2020-12-25 14:26:23', '0', '0');
INSERT INTO `client_load` VALUES ('161', '13', '2020-12-25 14:26:23', '0', '0');
INSERT INTO `client_load` VALUES ('162', '15', '2020-12-25 14:26:23', '0', '0');
INSERT INTO `client_load` VALUES ('163', '16', '2020-12-25 14:43:03', '0', '0');
INSERT INTO `client_load` VALUES ('164', '1', '2020-12-25 14:43:04', '0', '0');
INSERT INTO `client_load` VALUES ('165', '2', '2020-12-25 14:43:04', '0', '0');
INSERT INTO `client_load` VALUES ('166', '3', '2020-12-25 14:43:04', '0', '0');
INSERT INTO `client_load` VALUES ('167', '4', '2020-12-25 14:43:04', '0', '0');
INSERT INTO `client_load` VALUES ('168', '13', '2020-12-25 14:43:04', '0', '0');
INSERT INTO `client_load` VALUES ('169', '15', '2020-12-25 14:43:04', '0', '0');
INSERT INTO `client_load` VALUES ('170', '16', '2020-12-25 14:59:44', '0', '0');
INSERT INTO `client_load` VALUES ('171', '1', '2020-12-25 14:59:45', '0', '0');
INSERT INTO `client_load` VALUES ('172', '2', '2020-12-25 14:59:45', '0', '0');
INSERT INTO `client_load` VALUES ('173', '3', '2020-12-25 14:59:46', '0', '0');
INSERT INTO `client_load` VALUES ('174', '4', '2020-12-25 14:59:46', '0', '0');
INSERT INTO `client_load` VALUES ('175', '13', '2020-12-25 14:59:46', '0', '0');
INSERT INTO `client_load` VALUES ('176', '15', '2020-12-25 14:59:47', '0', '0');
INSERT INTO `client_load` VALUES ('177', '14', '2020-12-25 15:13:25', '1', '1');
INSERT INTO `client_load` VALUES ('178', '14', '2020-12-25 00:00:00', '1', '3');
INSERT INTO `client_load` VALUES ('179', '13', '2020-12-25 15:15:10', '1', '1');
INSERT INTO `client_load` VALUES ('180', '13', '2020-12-25 00:00:00', '2', '3');
INSERT INTO `client_load` VALUES ('181', '16', '2020-12-25 15:16:27', '0', '0');
INSERT INTO `client_load` VALUES ('182', '1', '2020-12-25 15:16:27', '0', '0');
INSERT INTO `client_load` VALUES ('183', '2', '2020-12-25 15:16:28', '0', '0');
INSERT INTO `client_load` VALUES ('184', '3', '2020-12-25 15:16:28', '0', '0');
INSERT INTO `client_load` VALUES ('185', '4', '2020-12-25 15:16:28', '0', '0');
INSERT INTO `client_load` VALUES ('186', '13', '2020-12-25 15:16:28', '1', '0');
INSERT INTO `client_load` VALUES ('187', '14', '2020-12-25 15:16:28', '1', '0');
INSERT INTO `client_load` VALUES ('188', '15', '2020-12-25 15:16:28', '0', '0');
INSERT INTO `client_load` VALUES ('189', '15', '2020-12-25 15:26:04', '1', '1');
INSERT INTO `client_load` VALUES ('190', '15', '2020-12-25 00:00:00', '1', '3');
INSERT INTO `client_load` VALUES ('191', '16', '2020-12-25 15:26:09', '1', '1');
INSERT INTO `client_load` VALUES ('192', '16', '2020-12-25 00:00:00', '1', '3');
INSERT INTO `client_load` VALUES ('193', '3', '2020-12-25 15:26:14', '1', '1');
INSERT INTO `client_load` VALUES ('194', '3', '2020-12-25 00:00:00', '4', '3');
INSERT INTO `client_load` VALUES ('195', '16', '2020-12-25 15:33:09', '1', '0');
INSERT INTO `client_load` VALUES ('196', '1', '2020-12-25 15:33:09', '0', '0');
INSERT INTO `client_load` VALUES ('197', '2', '2020-12-25 15:33:09', '0', '0');
INSERT INTO `client_load` VALUES ('198', '3', '2020-12-25 15:33:09', '1', '0');
INSERT INTO `client_load` VALUES ('199', '4', '2020-12-25 15:33:10', '0', '0');
INSERT INTO `client_load` VALUES ('200', '13', '2020-12-25 15:33:10', '1', '0');
INSERT INTO `client_load` VALUES ('201', '14', '2020-12-25 15:33:10', '1', '0');
INSERT INTO `client_load` VALUES ('202', '15', '2020-12-25 15:33:10', '1', '0');
INSERT INTO `client_load` VALUES ('203', '16', '2020-12-25 15:49:50', '1', '0');
INSERT INTO `client_load` VALUES ('204', '1', '2020-12-25 15:49:51', '0', '0');
INSERT INTO `client_load` VALUES ('205', '2', '2020-12-25 15:49:51', '0', '0');
INSERT INTO `client_load` VALUES ('206', '3', '2020-12-25 15:49:51', '1', '0');
INSERT INTO `client_load` VALUES ('207', '4', '2020-12-25 15:49:51', '0', '0');
INSERT INTO `client_load` VALUES ('208', '13', '2020-12-25 15:49:51', '1', '0');
INSERT INTO `client_load` VALUES ('209', '14', '2020-12-25 15:49:52', '1', '0');
INSERT INTO `client_load` VALUES ('210', '15', '2020-12-25 15:49:52', '1', '0');
INSERT INTO `client_load` VALUES ('211', '16', '2020-12-25 16:06:32', '1', '0');
INSERT INTO `client_load` VALUES ('212', '1', '2020-12-25 16:06:32', '0', '0');
INSERT INTO `client_load` VALUES ('213', '2', '2020-12-25 16:06:32', '0', '0');
INSERT INTO `client_load` VALUES ('214', '3', '2020-12-25 16:06:33', '1', '0');
INSERT INTO `client_load` VALUES ('215', '4', '2020-12-25 16:06:33', '0', '0');
INSERT INTO `client_load` VALUES ('216', '13', '2020-12-25 16:06:33', '1', '0');
INSERT INTO `client_load` VALUES ('217', '14', '2020-12-25 16:06:33', '1', '0');
INSERT INTO `client_load` VALUES ('218', '15', '2020-12-25 16:06:33', '1', '0');
INSERT INTO `client_load` VALUES ('219', '2', '2020-12-25 19:29:53', '2', '1');
INSERT INTO `client_load` VALUES ('220', '2', '2020-12-25 00:00:00', '6', '3');
INSERT INTO `client_load` VALUES ('221', '4', '2020-12-25 19:30:22', '1', '1');
INSERT INTO `client_load` VALUES ('222', '4', '2020-12-25 00:00:00', '1', '3');
INSERT INTO `client_load` VALUES ('223', '1', '2020-12-25 19:40:25', '0', '0');
INSERT INTO `client_load` VALUES ('224', '2', '2020-12-25 19:40:25', '1', '0');
INSERT INTO `client_load` VALUES ('225', '3', '2020-12-25 19:40:25', '1', '0');
INSERT INTO `client_load` VALUES ('226', '4', '2020-12-25 19:40:26', '0', '0');
INSERT INTO `client_load` VALUES ('227', '1', '2020-12-25 22:25:09', '0', '0');
INSERT INTO `client_load` VALUES ('228', '2', '2020-12-25 22:25:29', '1', '0');
INSERT INTO `client_load` VALUES ('229', '3', '2020-12-25 22:25:29', '1', '0');
INSERT INTO `client_load` VALUES ('230', '4', '2020-12-25 22:25:30', '0', '0');
INSERT INTO `client_load` VALUES ('231', '1', '2020-12-25 22:42:10', '0', '0');
INSERT INTO `client_load` VALUES ('232', '2', '2020-12-25 22:42:10', '1', '0');
INSERT INTO `client_load` VALUES ('233', '3', '2020-12-25 22:42:10', '1', '0');
INSERT INTO `client_load` VALUES ('234', '4', '2020-12-25 22:42:10', '0', '0');
INSERT INTO `client_load` VALUES ('235', '1', '2020-12-25 22:58:50', '0', '0');
INSERT INTO `client_load` VALUES ('236', '2', '2020-12-25 22:58:50', '1', '0');
INSERT INTO `client_load` VALUES ('237', '3', '2020-12-25 22:58:50', '1', '0');
INSERT INTO `client_load` VALUES ('238', '4', '2020-12-25 22:58:50', '0', '0');
INSERT INTO `client_load` VALUES ('239', '1', '2020-12-25 23:45:04', '1', '1');
INSERT INTO `client_load` VALUES ('240', '1', '2020-12-25 00:00:00', '2', '3');
INSERT INTO `client_load` VALUES ('242', '1', '2020-12-25 00:00:00', '254', '0');
INSERT INTO `client_load` VALUES ('243', '1', '2020-12-25 02:00:00', '202', '0');
INSERT INTO `client_load` VALUES ('244', '1', '2020-12-25 04:00:00', '228', '0');
INSERT INTO `client_load` VALUES ('245', '1', '2020-12-25 06:00:00', '125', '0');
INSERT INTO `client_load` VALUES ('246', '1', '2020-12-25 08:00:00', '105', '0');
INSERT INTO `client_load` VALUES ('247', '1', '2020-12-25 10:00:00', '55', '0');
INSERT INTO `client_load` VALUES ('248', '1', '2020-12-25 12:00:00', '25', '0');
INSERT INTO `client_load` VALUES ('249', '1', '2020-12-25 14:00:00', '10', '0');
INSERT INTO `client_load` VALUES ('250', '1', '2020-12-25 16:00:00', '15', '0');
INSERT INTO `client_load` VALUES ('251', '1', '2020-12-25 18:00:00', '125', '0');
INSERT INTO `client_load` VALUES ('252', '1', '2020-12-25 20:00:00', '225', '0');
INSERT INTO `client_load` VALUES ('253', '1', '2020-12-25 22:00:00', '280', '0');
INSERT INTO `client_load` VALUES ('254', '1', '2020-12-24 00:00:00', '124', '0');
INSERT INTO `client_load` VALUES ('255', '1', '2020-12-24 02:00:00', '102', '0');
INSERT INTO `client_load` VALUES ('256', '1', '2020-12-24 04:00:00', '218', '0');
INSERT INTO `client_load` VALUES ('257', '1', '2020-12-24 06:00:00', '185', '0');
INSERT INTO `client_load` VALUES ('258', '1', '2020-12-24 08:00:00', '105', '0');
INSERT INTO `client_load` VALUES ('259', '1', '2020-12-24 10:00:00', '65', '0');
INSERT INTO `client_load` VALUES ('260', '1', '2020-12-24 12:00:00', '55', '0');
INSERT INTO `client_load` VALUES ('261', '1', '2020-12-24 14:00:00', '70', '0');
INSERT INTO `client_load` VALUES ('262', '1', '2020-12-24 16:00:00', '45', '0');
INSERT INTO `client_load` VALUES ('263', '1', '2020-12-24 18:00:00', '125', '0');
INSERT INTO `client_load` VALUES ('264', '1', '2020-12-24 20:00:00', '325', '0');
INSERT INTO `client_load` VALUES ('265', '1', '2020-12-24 22:00:00', '380', '0');
INSERT INTO `client_load` VALUES ('266', '2', '2020-12-26 14:40:36', '2', '1');
INSERT INTO `client_load` VALUES ('267', '2', '2020-12-26 00:00:00', '2', '3');
INSERT INTO `client_load` VALUES ('268', '1', '2020-12-26 18:07:01', '3', '1');
INSERT INTO `client_load` VALUES ('269', '1', '2020-12-26 00:00:00', '6', '3');
INSERT INTO `client_load` VALUES ('270', '4', '2020-12-26 14:41:07', '3', '1');
INSERT INTO `client_load` VALUES ('271', '4', '2020-12-26 00:00:00', '4', '3');
INSERT INTO `client_load` VALUES ('272', '16', '2020-12-26 14:46:29', '0', '0');
INSERT INTO `client_load` VALUES ('273', '1', '2020-12-26 14:46:29', '2', '0');
INSERT INTO `client_load` VALUES ('274', '2', '2020-12-26 14:46:30', '2', '0');
INSERT INTO `client_load` VALUES ('275', '4', '2020-12-26 14:46:30', '3', '0');
INSERT INTO `client_load` VALUES ('276', '14', '2020-12-26 14:46:30', '0', '0');
INSERT INTO `client_load` VALUES ('277', '16', '2020-12-26 15:03:10', '0', '0');
INSERT INTO `client_load` VALUES ('278', '1', '2020-12-26 15:03:11', '2', '0');
INSERT INTO `client_load` VALUES ('279', '2', '2020-12-26 15:03:11', '2', '0');
INSERT INTO `client_load` VALUES ('280', '4', '2020-12-26 15:03:11', '3', '0');
INSERT INTO `client_load` VALUES ('281', '14', '2020-12-26 15:03:11', '0', '0');
INSERT INTO `client_load` VALUES ('282', '16', '2020-12-26 15:19:52', '0', '0');
INSERT INTO `client_load` VALUES ('283', '1', '2020-12-26 15:19:52', '2', '0');
INSERT INTO `client_load` VALUES ('284', '2', '2020-12-26 15:19:52', '2', '0');
INSERT INTO `client_load` VALUES ('285', '4', '2020-12-26 15:19:52', '3', '0');
INSERT INTO `client_load` VALUES ('286', '14', '2020-12-26 15:19:53', '0', '0');
INSERT INTO `client_load` VALUES ('287', '16', '2020-12-26 15:36:33', '0', '0');
INSERT INTO `client_load` VALUES ('288', '1', '2020-12-26 15:36:33', '2', '0');
INSERT INTO `client_load` VALUES ('289', '2', '2020-12-26 15:36:34', '2', '0');
INSERT INTO `client_load` VALUES ('290', '4', '2020-12-26 15:36:34', '3', '0');
INSERT INTO `client_load` VALUES ('291', '14', '2020-12-26 15:36:34', '0', '0');
INSERT INTO `client_load` VALUES ('292', '1', '2020-12-26 16:46:32', '1', '0');
INSERT INTO `client_load` VALUES ('293', '4', '2020-12-26 16:46:33', '1', '0');
INSERT INTO `client_load` VALUES ('294', '13', '2020-12-26 18:04:59', '4', '1');
INSERT INTO `client_load` VALUES ('295', '13', '2020-12-26 00:00:00', '4', '3');

-- ----------------------------
-- Table structure for `client_online`
-- ----------------------------
DROP TABLE IF EXISTS `client_online`;
CREATE TABLE `client_online` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) DEFAULT NULL,
  `client_name` varchar(50) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=293 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of client_online
-- ----------------------------
INSERT INTO `client_online` VALUES ('1', '1', '留下停车场', '2020-12-10 22:23:16', 'online');
INSERT INTO `client_online` VALUES ('2', '1', '留下停车场', '2020-12-10 22:25:02', 'offline');
INSERT INTO `client_online` VALUES ('3', '1', '留下停车场', '2020-12-10 22:25:14', 'online');
INSERT INTO `client_online` VALUES ('4', '1', '留下停车场', '2020-12-10 22:25:14', 'offline');
INSERT INTO `client_online` VALUES ('5', '1', '留下停车场', '2020-12-10 22:25:57', 'online');
INSERT INTO `client_online` VALUES ('6', '1', '留下停车场', '2020-12-10 22:25:57', 'offline');
INSERT INTO `client_online` VALUES ('7', '1', '留下停车场', '2020-12-10 22:27:40', 'online');
INSERT INTO `client_online` VALUES ('8', '1', '留下停车场', '2020-12-10 22:27:40', 'offline');
INSERT INTO `client_online` VALUES ('9', '1', '留下停车场', '2020-12-10 22:29:03', 'online');
INSERT INTO `client_online` VALUES ('10', '1', '留下停车场', '2020-12-10 22:29:03', 'offline');
INSERT INTO `client_online` VALUES ('11', '1', '留下停车场', '2020-12-10 22:30:11', 'online');
INSERT INTO `client_online` VALUES ('12', '1', '留下停车场', '2020-12-10 22:32:56', 'offline');
INSERT INTO `client_online` VALUES ('13', '1', '留下停车场', '2020-12-10 22:35:33', 'online');
INSERT INTO `client_online` VALUES ('14', '1', '留下停车场', '2020-12-10 22:39:58', 'offline');
INSERT INTO `client_online` VALUES ('15', '1', '留下停车场', '2020-12-10 22:41:33', 'online');
INSERT INTO `client_online` VALUES ('16', '1', '留下停车场', '2020-12-10 22:41:48', 'offline');
INSERT INTO `client_online` VALUES ('17', '1', '留下停车场', '2020-12-10 22:41:53', 'online');
INSERT INTO `client_online` VALUES ('18', '1', '留下停车场', '2020-12-10 22:47:56', 'offline');
INSERT INTO `client_online` VALUES ('19', '1', '留下停车场', '2020-12-10 22:48:19', 'online');
INSERT INTO `client_online` VALUES ('20', '1', '留下停车场', '2020-12-10 22:59:45', 'offline');
INSERT INTO `client_online` VALUES ('21', '1', '留下停车场', '2020-12-10 23:16:26', 'online');
INSERT INTO `client_online` VALUES ('22', '1', '留下停车场', '2020-12-10 23:18:00', 'offline');
INSERT INTO `client_online` VALUES ('23', '1', '留下停车场', '2020-12-10 23:29:12', 'online');
INSERT INTO `client_online` VALUES ('24', '1', '留下停车场', '2020-12-10 23:29:31', 'offline');
INSERT INTO `client_online` VALUES ('25', '1', '留下停车场', '2020-12-10 23:31:12', 'online');
INSERT INTO `client_online` VALUES ('26', '1', '留下停车场', '2020-12-10 23:32:39', 'offline');
INSERT INTO `client_online` VALUES ('27', '1', '留下停车场', '2020-12-10 23:40:06', 'online');
INSERT INTO `client_online` VALUES ('28', '1', '留下停车场', '2020-12-10 23:40:38', 'offline');
INSERT INTO `client_online` VALUES ('29', '1', '留下停车场', '2020-12-10 23:43:14', 'online');
INSERT INTO `client_online` VALUES ('30', '1', '留下停车场', '2020-12-10 23:44:03', 'offline');
INSERT INTO `client_online` VALUES ('31', '1', '留下停车场', '2020-12-10 23:52:19', 'online');
INSERT INTO `client_online` VALUES ('32', '1', '留下停车场', '2020-12-10 23:53:46', 'offline');
INSERT INTO `client_online` VALUES ('33', '1', '留下停车场', '2020-12-10 23:56:28', 'online');
INSERT INTO `client_online` VALUES ('34', '1', '留下停车场', '2020-12-10 23:57:33', 'offline');
INSERT INTO `client_online` VALUES ('35', '1', '留下停车场', '2020-12-11 00:02:39', 'online');
INSERT INTO `client_online` VALUES ('36', '1', '留下停车场', '2020-12-11 00:04:53', 'offline');
INSERT INTO `client_online` VALUES ('37', '1', '留下停车场', '2020-12-11 09:46:55', 'online');
INSERT INTO `client_online` VALUES ('38', '1', '留下停车场', '2020-12-11 09:49:04', 'offline');
INSERT INTO `client_online` VALUES ('39', '1', '留下停车场', '2020-12-11 10:41:14', 'online');
INSERT INTO `client_online` VALUES ('40', '1', '留下停车场', '2020-12-11 10:41:17', 'offline');
INSERT INTO `client_online` VALUES ('41', '1', '留下停车场', '2020-12-11 10:42:57', 'online');
INSERT INTO `client_online` VALUES ('42', '1', '留下停车场', '2020-12-11 10:43:05', 'offline');
INSERT INTO `client_online` VALUES ('43', '1', '留下停车场', '2020-12-11 10:48:28', 'online');
INSERT INTO `client_online` VALUES ('44', '1', '留下停车场', '2020-12-11 10:48:46', 'offline');
INSERT INTO `client_online` VALUES ('45', '1', '留下停车场', '2020-12-11 10:49:07', 'online');
INSERT INTO `client_online` VALUES ('46', '1', '留下停车场', '2020-12-11 10:49:32', 'offline');
INSERT INTO `client_online` VALUES ('47', '1', '留下停车场', '2020-12-11 11:12:08', 'online');
INSERT INTO `client_online` VALUES ('48', '1', '留下停车场', '2020-12-11 11:12:10', 'offline');
INSERT INTO `client_online` VALUES ('49', '1', '留下停车场', '2020-12-11 11:44:25', 'online');
INSERT INTO `client_online` VALUES ('50', '1', '留下停车场', '2020-12-11 11:52:39', 'offline');
INSERT INTO `client_online` VALUES ('51', '1', '留下停车场', '2020-12-11 11:52:56', 'online');
INSERT INTO `client_online` VALUES ('52', '1', '留下停车场', '2020-12-11 11:55:51', 'offline');
INSERT INTO `client_online` VALUES ('53', '1', '留下停车场', '2020-12-11 12:07:22', 'online');
INSERT INTO `client_online` VALUES ('54', '1', '留下停车场', '2020-12-11 12:20:58', 'offline');
INSERT INTO `client_online` VALUES ('55', '1', '留下停车场', '2020-12-11 14:32:05', 'online');
INSERT INTO `client_online` VALUES ('56', '1', '留下停车场', '2020-12-11 14:32:29', 'offline');
INSERT INTO `client_online` VALUES ('57', '1', '留下停车场', '2020-12-11 14:54:44', 'online');
INSERT INTO `client_online` VALUES ('58', '2', '中国科大停车场1', '2020-12-11 20:27:16', 'online');
INSERT INTO `client_online` VALUES ('59', '1', '留下停车场', '2020-12-11 20:27:44', 'online');
INSERT INTO `client_online` VALUES ('60', '2', '中国科大停车场1', '2020-12-11 20:37:29', 'offline');
INSERT INTO `client_online` VALUES ('61', '1', '留下停车场', '2020-12-11 20:37:29', 'offline');
INSERT INTO `client_online` VALUES ('62', '2', '中国科大停车场1', '2020-12-11 20:38:11', 'online');
INSERT INTO `client_online` VALUES ('63', '1', '留下停车场', '2020-12-11 20:38:14', 'online');
INSERT INTO `client_online` VALUES ('64', '1', '留下停车场', '2020-12-11 20:38:17', 'offline');
INSERT INTO `client_online` VALUES ('65', '1', '留下停车场', '2020-12-11 20:38:23', 'online');
INSERT INTO `client_online` VALUES ('66', '4', '中国人民大学苏州校区西停车场', '2020-12-11 20:38:32', 'online');
INSERT INTO `client_online` VALUES ('67', '4', '中国人民大学苏州校区西停车场', '2020-12-11 20:41:34', 'offline');
INSERT INTO `client_online` VALUES ('68', '2', '中国科大停车场1', '2020-12-11 20:41:36', 'offline');
INSERT INTO `client_online` VALUES ('69', '1', '留下停车场', '2020-12-11 20:41:37', 'offline');
INSERT INTO `client_online` VALUES ('70', '2', '中国科大停车场1', '2020-12-15 14:04:04', 'online');
INSERT INTO `client_online` VALUES ('72', '2', '中国科大停车场1', '2020-12-15 14:09:30', 'online');
INSERT INTO `client_online` VALUES ('73', '2', '中国科大停车场1', '2020-12-15 14:09:53', 'offline');
INSERT INTO `client_online` VALUES ('74', '2', '中国科大停车场1', '2020-12-15 14:13:01', 'online');
INSERT INTO `client_online` VALUES ('75', '2', '中国科大停车场1', '2020-12-15 14:21:12', 'offline');
INSERT INTO `client_online` VALUES ('76', '14', '文星停车场', '2020-12-15 14:21:41', 'online');
INSERT INTO `client_online` VALUES ('77', '4', '中国人民大学苏州校区西停车场', '2020-12-15 14:22:01', 'online');
INSERT INTO `client_online` VALUES ('78', '14', '文星停车场', '2020-12-15 14:58:02', 'offline');
INSERT INTO `client_online` VALUES ('79', '4', '中国人民大学苏州校区西停车场', '2020-12-15 14:58:02', 'offline');
INSERT INTO `client_online` VALUES ('80', '14', '文星停车场', '2020-12-15 16:27:13', 'online');
INSERT INTO `client_online` VALUES ('81', '14', '文星停车场', '2020-12-15 16:27:22', 'offline');
INSERT INTO `client_online` VALUES ('82', '14', '文星停车场', '2020-12-15 16:27:23', 'online');
INSERT INTO `client_online` VALUES ('83', '14', '文星停车场', '2020-12-15 16:57:34', 'offline');
INSERT INTO `client_online` VALUES ('84', '1', '留下停车场', '2020-12-17 12:16:30', 'online');
INSERT INTO `client_online` VALUES ('85', '4', '中国人民大学苏州校区西停车场', '2020-12-17 12:21:37', 'online');
INSERT INTO `client_online` VALUES ('86', '1', '留下停车场', '2020-12-17 12:52:02', 'offline');
INSERT INTO `client_online` VALUES ('87', '4', '中国人民大学苏州校区西停车场', '2020-12-17 12:52:03', 'offline');
INSERT INTO `client_online` VALUES ('88', '4', '中国人民大学苏州校区西停车场', '2020-12-17 12:52:20', 'online');
INSERT INTO `client_online` VALUES ('89', '1', '留下停车场', '2020-12-17 12:52:22', 'online');
INSERT INTO `client_online` VALUES ('90', '4', '中国人民大学苏州校区西停车场', '2020-12-17 13:02:25', 'offline');
INSERT INTO `client_online` VALUES ('91', '4', '中国人民大学苏州校区西停车场', '2020-12-17 13:02:26', 'online');
INSERT INTO `client_online` VALUES ('92', '4', '中国人民大学苏州校区西停车场', '2020-12-17 13:08:48', 'offline');
INSERT INTO `client_online` VALUES ('93', '1', '留下停车场', '2020-12-17 13:08:48', 'offline');
INSERT INTO `client_online` VALUES ('94', '1', '留下停车场', '2020-12-22 14:14:38', 'online');
INSERT INTO `client_online` VALUES ('95', '1', '留下停车场', '2020-12-22 14:30:17', 'offline');
INSERT INTO `client_online` VALUES ('96', '1', '留下停车场', '2020-12-22 14:31:25', 'online');
INSERT INTO `client_online` VALUES ('97', '2', '中国科大停车场1', '2020-12-22 14:31:29', 'online');
INSERT INTO `client_online` VALUES ('98', '3', '苏州大学北停车场', '2020-12-22 14:31:33', 'online');
INSERT INTO `client_online` VALUES ('99', '3', '苏州大学北停车场', '2020-12-22 14:34:59', 'offline');
INSERT INTO `client_online` VALUES ('100', '1', '留下停车场', '2020-12-22 14:34:59', 'offline');
INSERT INTO `client_online` VALUES ('101', '2', '中国科大停车场1', '2020-12-22 14:34:59', 'offline');
INSERT INTO `client_online` VALUES ('102', '1', '留下停车场', '2020-12-22 14:36:28', 'online');
INSERT INTO `client_online` VALUES ('103', '2', '中国科大停车场1', '2020-12-22 14:36:30', 'online');
INSERT INTO `client_online` VALUES ('104', '3', '苏州大学北停车场', '2020-12-22 14:36:32', 'online');
INSERT INTO `client_online` VALUES ('105', '2', '中国科大停车场1', '2020-12-22 16:19:00', 'offline');
INSERT INTO `client_online` VALUES ('106', '1', '留下停车场', '2020-12-22 16:19:00', 'offline');
INSERT INTO `client_online` VALUES ('107', '3', '苏州大学北停车场', '2020-12-22 16:19:00', 'offline');
INSERT INTO `client_online` VALUES ('108', '15', '仁爱停车场', '2020-12-22 20:02:10', 'online');
INSERT INTO `client_online` VALUES ('109', '15', '仁爱停车场', '2020-12-22 20:02:17', 'offline');
INSERT INTO `client_online` VALUES ('110', '15', '仁爱停车场', '2020-12-22 20:02:27', 'online');
INSERT INTO `client_online` VALUES ('111', '13', '文荟停车场', '2020-12-22 20:02:40', 'online');
INSERT INTO `client_online` VALUES ('112', '14', '文星停车场', '2020-12-22 20:02:48', 'online');
INSERT INTO `client_online` VALUES ('113', '15', '仁爱停车场', '2020-12-22 20:17:58', 'offline');
INSERT INTO `client_online` VALUES ('114', '13', '文荟停车场', '2020-12-22 20:18:04', 'offline');
INSERT INTO `client_online` VALUES ('115', '14', '文星停车场', '2020-12-22 20:18:12', 'offline');
INSERT INTO `client_online` VALUES ('116', '14', '文星停车场', '2020-12-22 20:18:18', 'online');
INSERT INTO `client_online` VALUES ('117', '13', '文荟停车场', '2020-12-22 20:18:20', 'online');
INSERT INTO `client_online` VALUES ('118', '15', '仁爱停车场', '2020-12-22 20:18:22', 'online');
INSERT INTO `client_online` VALUES ('119', '15', '仁爱停车场', '2020-12-22 20:36:45', 'offline');
INSERT INTO `client_online` VALUES ('120', '13', '文荟停车场', '2020-12-22 20:36:46', 'offline');
INSERT INTO `client_online` VALUES ('121', '14', '文星停车场', '2020-12-22 20:36:46', 'offline');
INSERT INTO `client_online` VALUES ('122', '1', '留下停车场', '2020-12-23 21:13:30', 'online');
INSERT INTO `client_online` VALUES ('123', '1', '留下停车场', '2020-12-23 21:13:30', 'offline');
INSERT INTO `client_online` VALUES ('124', '1', '留下停车场', '2020-12-23 21:14:30', 'online');
INSERT INTO `client_online` VALUES ('125', '1', '留下停车场', '2020-12-23 21:14:30', 'offline');
INSERT INTO `client_online` VALUES ('126', '1', '留下停车场', '2020-12-23 21:15:08', 'online');
INSERT INTO `client_online` VALUES ('127', '1', '留下停车场', '2020-12-23 21:15:08', 'offline');
INSERT INTO `client_online` VALUES ('128', '1', '留下停车场', '2020-12-23 21:15:29', 'online');
INSERT INTO `client_online` VALUES ('129', '1', '留下停车场', '2020-12-23 21:17:44', 'offline');
INSERT INTO `client_online` VALUES ('130', '1', '留下停车场', '2020-12-23 21:17:45', 'online');
INSERT INTO `client_online` VALUES ('131', '1', '留下停车场', '2020-12-23 21:19:22', 'offline');
INSERT INTO `client_online` VALUES ('132', '1', '留下停车场', '2020-12-23 21:19:33', 'online');
INSERT INTO `client_online` VALUES ('133', '1', '留下停车场', '2020-12-23 21:19:34', 'offline');
INSERT INTO `client_online` VALUES ('134', '1', '留下停车场', '2020-12-23 21:19:59', 'online');
INSERT INTO `client_online` VALUES ('135', '1', '留下停车场', '2020-12-23 21:36:06', 'offline');
INSERT INTO `client_online` VALUES ('136', '2', '中国科大停车场1', '2020-12-23 21:38:12', 'online');
INSERT INTO `client_online` VALUES ('137', '3', '苏州大学北停车场', '2020-12-23 21:38:18', 'online');
INSERT INTO `client_online` VALUES ('138', '2', '中国科大停车场1', '2020-12-23 21:53:00', 'offline');
INSERT INTO `client_online` VALUES ('139', '2', '中国科大停车场1', '2020-12-23 21:53:10', 'online');
INSERT INTO `client_online` VALUES ('140', '2', '中国科大停车场1', '2020-12-23 21:56:13', 'offline');
INSERT INTO `client_online` VALUES ('141', '2', '中国科大停车场1', '2020-12-23 21:56:16', 'online');
INSERT INTO `client_online` VALUES ('142', '4', '中国人民大学苏州校区西停车场', '2020-12-23 21:56:24', 'online');
INSERT INTO `client_online` VALUES ('143', '1', '留下停车场', '2020-12-23 21:57:03', 'online');
INSERT INTO `client_online` VALUES ('144', '13', '文荟停车场', '2020-12-23 21:58:30', 'online');
INSERT INTO `client_online` VALUES ('145', '15', '仁爱停车场', '2020-12-23 21:58:57', 'online');
INSERT INTO `client_online` VALUES ('146', '14', '文星停车场', '2020-12-23 22:02:35', 'online');
INSERT INTO `client_online` VALUES ('147', '14', '文星停车场', '2020-12-23 22:03:25', 'offline');
INSERT INTO `client_online` VALUES ('148', '16', '苏大停车场', '2020-12-23 22:04:20', 'online');
INSERT INTO `client_online` VALUES ('149', '16', '苏大停车场', '2020-12-23 22:05:48', 'offline');
INSERT INTO `client_online` VALUES ('150', '16', '苏大停车场', '2020-12-23 22:05:49', 'online');
INSERT INTO `client_online` VALUES ('151', '14', '文星停车场', '2020-12-23 22:07:10', 'online');
INSERT INTO `client_online` VALUES ('152', '16', '苏大停车场', '2020-12-23 22:33:04', 'offline');
INSERT INTO `client_online` VALUES ('153', '2', '中国科大停车场1', '2020-12-23 22:33:04', 'offline');
INSERT INTO `client_online` VALUES ('154', '1', '留下停车场', '2020-12-23 22:33:04', 'offline');
INSERT INTO `client_online` VALUES ('155', '13', '文荟停车场', '2020-12-23 22:33:04', 'offline');
INSERT INTO `client_online` VALUES ('156', '14', '文星停车场', '2020-12-23 22:33:04', 'offline');
INSERT INTO `client_online` VALUES ('157', '4', '中国人民大学苏州校区西停车场', '2020-12-23 22:33:04', 'offline');
INSERT INTO `client_online` VALUES ('158', '15', '仁爱停车场', '2020-12-23 22:33:04', 'offline');
INSERT INTO `client_online` VALUES ('159', '3', '苏州大学北停车场', '2020-12-23 22:33:04', 'offline');
INSERT INTO `client_online` VALUES ('160', '1', '留下停车场', '2020-12-23 22:49:54', 'online');
INSERT INTO `client_online` VALUES ('161', '1', '留下停车场', '2020-12-23 22:53:18', 'offline');
INSERT INTO `client_online` VALUES ('162', '1', '留下停车场', '2020-12-23 22:54:12', 'online');
INSERT INTO `client_online` VALUES ('163', '2', '中国科大停车场1', '2020-12-23 22:54:35', 'online');
INSERT INTO `client_online` VALUES ('164', '2', '中国科大停车场1', '2020-12-23 22:59:43', 'offline');
INSERT INTO `client_online` VALUES ('165', '1', '留下停车场', '2020-12-23 22:59:43', 'offline');
INSERT INTO `client_online` VALUES ('166', '2', '中国科大停车场1', '2020-12-23 23:10:40', 'online');
INSERT INTO `client_online` VALUES ('167', '1', '留下停车场', '2020-12-23 23:11:44', 'online');
INSERT INTO `client_online` VALUES ('168', '1', '留下停车场', '2020-12-23 23:11:45', 'offline');
INSERT INTO `client_online` VALUES ('169', '1', '留下停车场', '2020-12-23 23:11:47', 'online');
INSERT INTO `client_online` VALUES ('170', '1', '留下停车场', '2020-12-23 23:12:28', 'offline');
INSERT INTO `client_online` VALUES ('171', '1', '留下停车场', '2020-12-23 23:12:34', 'online');
INSERT INTO `client_online` VALUES ('172', '2', '中国科大停车场1', '2020-12-23 23:12:51', 'offline');
INSERT INTO `client_online` VALUES ('173', '2', '中国科大停车场1', '2020-12-23 23:12:52', 'online');
INSERT INTO `client_online` VALUES ('174', '2', '中国科大停车场1', '2020-12-23 23:18:38', 'offline');
INSERT INTO `client_online` VALUES ('175', '1', '留下停车场', '2020-12-23 23:18:38', 'offline');
INSERT INTO `client_online` VALUES ('176', '1', '留下停车场', '2020-12-23 23:21:36', 'online');
INSERT INTO `client_online` VALUES ('177', '2', '中国科大停车场1', '2020-12-23 23:24:22', 'online');
INSERT INTO `client_online` VALUES ('178', '1', '留下停车场', '2020-12-23 23:28:14', 'offline');
INSERT INTO `client_online` VALUES ('179', '2', '中国科大停车场1', '2020-12-23 23:28:14', 'offline');
INSERT INTO `client_online` VALUES ('180', '2', '中国科大停车场1', '2020-12-23 23:29:47', 'online');
INSERT INTO `client_online` VALUES ('181', '1', '留下停车场', '2020-12-23 23:29:51', 'online');
INSERT INTO `client_online` VALUES ('182', '3', '苏州大学北停车场', '2020-12-23 23:29:56', 'online');
INSERT INTO `client_online` VALUES ('183', '13', '文荟停车场', '2020-12-23 23:30:53', 'online');
INSERT INTO `client_online` VALUES ('184', '4', '中国人民大学苏州校区西停车场', '2020-12-23 23:32:45', 'online');
INSERT INTO `client_online` VALUES ('185', '14', '文星停车场', '2020-12-23 23:34:21', 'online');
INSERT INTO `client_online` VALUES ('186', '15', '仁爱停车场', '2020-12-23 23:37:28', 'online');
INSERT INTO `client_online` VALUES ('187', '16', '苏大停车场', '2020-12-23 23:37:54', 'online');
INSERT INTO `client_online` VALUES ('188', '16', '苏大停车场', '2020-12-23 23:38:13', 'offline');
INSERT INTO `client_online` VALUES ('189', '16', '苏大停车场', '2020-12-23 23:38:14', 'online');
INSERT INTO `client_online` VALUES ('190', '1', '留下停车场', '2020-12-23 23:59:26', 'offline');
INSERT INTO `client_online` VALUES ('191', '14', '文星停车场', '2020-12-23 23:59:26', 'offline');
INSERT INTO `client_online` VALUES ('192', '3', '苏州大学北停车场', '2020-12-23 23:59:26', 'offline');
INSERT INTO `client_online` VALUES ('193', '16', '苏大停车场', '2020-12-23 23:59:26', 'offline');
INSERT INTO `client_online` VALUES ('194', '13', '文荟停车场', '2020-12-23 23:59:26', 'offline');
INSERT INTO `client_online` VALUES ('195', '2', '中国科大停车场1', '2020-12-23 23:59:26', 'offline');
INSERT INTO `client_online` VALUES ('196', '15', '仁爱停车场', '2020-12-23 23:59:26', 'offline');
INSERT INTO `client_online` VALUES ('197', '4', '中国人民大学苏州校区西停车场', '2020-12-23 23:59:26', 'offline');
INSERT INTO `client_online` VALUES ('198', '2', '中国科大停车场1', '2020-12-24 11:20:31', 'online');
INSERT INTO `client_online` VALUES ('199', '3', '苏州大学北停车场', '2020-12-24 11:20:49', 'online');
INSERT INTO `client_online` VALUES ('200', '13', '文荟停车场', '2020-12-24 11:21:22', 'online');
INSERT INTO `client_online` VALUES ('201', '1', '留下停车场', '2020-12-24 11:21:49', 'online');
INSERT INTO `client_online` VALUES ('202', '2', '中国科大停车场1', '2020-12-24 11:22:11', 'offline');
INSERT INTO `client_online` VALUES ('203', '2', '中国科大停车场1', '2020-12-24 11:22:12', 'online');
INSERT INTO `client_online` VALUES ('204', '16', '苏大停车场', '2020-12-24 11:22:34', 'online');
INSERT INTO `client_online` VALUES ('205', '16', '苏大停车场', '2020-12-24 11:22:41', 'offline');
INSERT INTO `client_online` VALUES ('206', '16', '苏大停车场', '2020-12-24 11:22:43', 'online');
INSERT INTO `client_online` VALUES ('207', '14', '文星停车场', '2020-12-24 11:24:39', 'online');
INSERT INTO `client_online` VALUES ('208', '16', '苏大停车场', '2020-12-24 11:26:12', 'offline');
INSERT INTO `client_online` VALUES ('209', '2', '中国科大停车场1', '2020-12-24 11:26:12', 'offline');
INSERT INTO `client_online` VALUES ('210', '3', '苏州大学北停车场', '2020-12-24 11:26:12', 'offline');
INSERT INTO `client_online` VALUES ('211', '13', '文荟停车场', '2020-12-24 11:26:12', 'offline');
INSERT INTO `client_online` VALUES ('212', '14', '文星停车场', '2020-12-24 11:26:12', 'offline');
INSERT INTO `client_online` VALUES ('213', '1', '留下停车场', '2020-12-24 11:26:12', 'offline');
INSERT INTO `client_online` VALUES ('214', '1', '留下停车场', '2020-12-25 14:06:07', 'online');
INSERT INTO `client_online` VALUES ('215', '1', '留下停车场', '2020-12-25 14:06:07', 'offline');
INSERT INTO `client_online` VALUES ('216', '1', '留下停车场', '2020-12-25 14:06:50', 'online');
INSERT INTO `client_online` VALUES ('217', '1', '留下停车场', '2020-12-25 14:06:51', 'offline');
INSERT INTO `client_online` VALUES ('218', '1', '留下停车场', '2020-12-25 14:07:10', 'online');
INSERT INTO `client_online` VALUES ('219', '1', '留下停车场', '2020-12-25 14:07:10', 'offline');
INSERT INTO `client_online` VALUES ('220', '1', '留下停车场', '2020-12-25 14:08:33', 'online');
INSERT INTO `client_online` VALUES ('221', '1', '留下停车场', '2020-12-25 14:08:33', 'offline');
INSERT INTO `client_online` VALUES ('222', '1', '留下停车场', '2020-12-25 14:09:54', 'online');
INSERT INTO `client_online` VALUES ('223', '2', '中国科大停车场1', '2020-12-25 14:10:59', 'online');
INSERT INTO `client_online` VALUES ('224', '3', '苏州大学北停车场', '2020-12-25 14:11:36', 'online');
INSERT INTO `client_online` VALUES ('225', '4', '中国人民大学苏州校区西停车场', '2020-12-25 14:15:22', 'online');
INSERT INTO `client_online` VALUES ('226', '13', '文荟停车场', '2020-12-25 14:19:48', 'online');
INSERT INTO `client_online` VALUES ('227', '15', '仁爱停车场', '2020-12-25 14:23:32', 'online');
INSERT INTO `client_online` VALUES ('228', '16', '苏大停车场', '2020-12-25 14:25:29', 'online');
INSERT INTO `client_online` VALUES ('229', '14', '文星停车场', '2020-12-25 15:12:53', 'online');
INSERT INTO `client_online` VALUES ('230', '14', '文星停车场', '2020-12-25 15:13:32', 'offline');
INSERT INTO `client_online` VALUES ('231', '13', '文荟停车场', '2020-12-25 15:15:14', 'offline');
INSERT INTO `client_online` VALUES ('232', '13', '文荟停车场', '2020-12-25 15:25:54', 'online');
INSERT INTO `client_online` VALUES ('233', '16', '苏大停车场', '2020-12-25 15:44:20', 'offline');
INSERT INTO `client_online` VALUES ('234', '15', '仁爱停车场', '2020-12-25 15:45:34', 'offline');
INSERT INTO `client_online` VALUES ('235', '4', '中国人民大学苏州校区西停车场', '2020-12-25 16:15:34', 'offline');
INSERT INTO `client_online` VALUES ('236', '1', '留下停车场', '2020-12-25 16:15:34', 'offline');
INSERT INTO `client_online` VALUES ('237', '2', '中国科大停车场1', '2020-12-25 16:15:34', 'offline');
INSERT INTO `client_online` VALUES ('238', '3', '苏州大学北停车场', '2020-12-25 16:15:34', 'offline');
INSERT INTO `client_online` VALUES ('239', '13', '文荟停车场', '2020-12-25 16:15:35', 'offline');
INSERT INTO `client_online` VALUES ('240', '1', '留下停车场', '2020-12-25 19:24:43', 'online');
INSERT INTO `client_online` VALUES ('241', '2', '中国科大停车场1', '2020-12-25 19:25:11', 'online');
INSERT INTO `client_online` VALUES ('242', '3', '苏州大学北停车场', '2020-12-25 19:29:33', 'online');
INSERT INTO `client_online` VALUES ('243', '4', '中国人民大学苏州校区西停车场', '2020-12-25 19:29:39', 'online');
INSERT INTO `client_online` VALUES ('244', '3', '苏州大学北停车场', '2020-12-25 19:29:43', 'offline');
INSERT INTO `client_online` VALUES ('245', '3', '苏州大学北停车场', '2020-12-25 19:29:49', 'online');
INSERT INTO `client_online` VALUES ('246', '2', '中国科大停车场1', '2020-12-25 19:30:05', 'offline');
INSERT INTO `client_online` VALUES ('247', '2', '中国科大停车场1', '2020-12-25 19:30:11', 'online');
INSERT INTO `client_online` VALUES ('248', '2', '中国科大停车场1', '2020-12-25 19:30:35', 'offline');
INSERT INTO `client_online` VALUES ('249', '2', '中国科大停车场1', '2020-12-25 19:30:46', 'online');
INSERT INTO `client_online` VALUES ('250', '1', '留下停车场', '2020-12-25 19:30:54', 'offline');
INSERT INTO `client_online` VALUES ('251', '2', '中国科大停车场1', '2020-12-25 19:30:57', 'offline');
INSERT INTO `client_online` VALUES ('252', '3', '苏州大学北停车场', '2020-12-25 19:30:59', 'offline');
INSERT INTO `client_online` VALUES ('253', '4', '中国人民大学苏州校区西停车场', '2020-12-25 19:31:00', 'offline');
INSERT INTO `client_online` VALUES ('254', '2', '中国科大停车场1', '2020-12-25 19:32:27', 'online');
INSERT INTO `client_online` VALUES ('255', '2', '中国科大停车场1', '2020-12-25 19:32:30', 'offline');
INSERT INTO `client_online` VALUES ('256', '3', '苏州大学北停车场', '2020-12-25 19:32:43', 'online');
INSERT INTO `client_online` VALUES ('257', '3', '苏州大学北停车场', '2020-12-25 19:32:44', 'offline');
INSERT INTO `client_online` VALUES ('258', '3', '苏州大学北停车场', '2020-12-25 19:32:50', 'online');
INSERT INTO `client_online` VALUES ('259', '3', '苏州大学北停车场', '2020-12-25 19:32:55', 'offline');
INSERT INTO `client_online` VALUES ('260', '3', '苏州大学北停车场', '2020-12-25 19:32:56', 'online');
INSERT INTO `client_online` VALUES ('261', '4', '中国人民大学苏州校区西停车场', '2020-12-25 19:33:04', 'online');
INSERT INTO `client_online` VALUES ('262', '4', '中国人民大学苏州校区西停车场', '2020-12-25 19:33:06', 'offline');
INSERT INTO `client_online` VALUES ('263', '3', '苏州大学北停车场', '2020-12-25 19:54:24', 'offline');
INSERT INTO `client_online` VALUES ('264', '1', '留下停车场', '2020-12-25 23:44:47', 'online');
INSERT INTO `client_online` VALUES ('265', '2', '中国科大停车场1', '2020-12-25 23:44:56', 'online');
INSERT INTO `client_online` VALUES ('266', '1', '留下停车场', '2020-12-25 23:45:14', 'offline');
INSERT INTO `client_online` VALUES ('267', '1', '留下停车场', '2020-12-25 23:45:20', 'online');
INSERT INTO `client_online` VALUES ('268', '2', '中国科大停车场1', '2020-12-25 23:54:03', 'offline');
INSERT INTO `client_online` VALUES ('269', '1', '留下停车场', '2020-12-25 23:54:03', 'offline');
INSERT INTO `client_online` VALUES ('270', '1', '留下停车场', '2020-12-26 14:39:19', 'online');
INSERT INTO `client_online` VALUES ('271', '2', '中国科大停车场1', '2020-12-26 14:39:31', 'online');
INSERT INTO `client_online` VALUES ('272', '4', '中国人民大学苏州校区西停车场', '2020-12-26 14:39:39', 'online');
INSERT INTO `client_online` VALUES ('273', '16', '苏大停车场', '2020-12-26 14:40:21', 'online');
INSERT INTO `client_online` VALUES ('274', '16', '苏大停车场', '2020-12-26 14:40:23', 'offline');
INSERT INTO `client_online` VALUES ('275', '2', '中国科大停车场1', '2020-12-26 14:40:50', 'offline');
INSERT INTO `client_online` VALUES ('276', '14', '文星停车场', '2020-12-26 14:41:16', 'online');
INSERT INTO `client_online` VALUES ('277', '1', '留下停车场', '2020-12-26 16:36:36', 'online');
INSERT INTO `client_online` VALUES ('278', '4', '中国人民大学苏州校区西停车场', '2020-12-26 16:36:54', 'online');
INSERT INTO `client_online` VALUES ('279', '4', '中国人民大学苏州校区西停车场', '2020-12-26 16:36:56', 'offline');
INSERT INTO `client_online` VALUES ('280', '4', '中国人民大学苏州校区西停车场', '2020-12-26 16:36:58', 'online');
INSERT INTO `client_online` VALUES ('281', '4', '中国人民大学苏州校区西停车场', '2020-12-26 16:37:08', 'offline');
INSERT INTO `client_online` VALUES ('282', '1', '留下停车场', '2020-12-26 16:37:24', 'offline');
INSERT INTO `client_online` VALUES ('283', '1', '留下停车场', '2020-12-26 17:58:45', 'online');
INSERT INTO `client_online` VALUES ('284', '3', '苏州大学北停车场', '2020-12-26 18:01:15', 'online');
INSERT INTO `client_online` VALUES ('285', '13', '文荟停车场', '2020-12-26 18:01:26', 'online');
INSERT INTO `client_online` VALUES ('286', '3', '苏州大学北停车场', '2020-12-26 18:04:43', 'offline');
INSERT INTO `client_online` VALUES ('287', '3', '苏州大学北停车场', '2020-12-26 18:04:44', 'online');
INSERT INTO `client_online` VALUES ('288', '13', '文荟停车场', '2020-12-26 18:05:31', 'offline');
INSERT INTO `client_online` VALUES ('289', '13', '文荟停车场', '2020-12-26 18:05:53', 'online');
INSERT INTO `client_online` VALUES ('290', '1', '留下停车场', '2020-12-26 18:09:02', 'offline');
INSERT INTO `client_online` VALUES ('291', '13', '文荟停车场', '2020-12-26 18:09:04', 'offline');
INSERT INTO `client_online` VALUES ('292', '3', '苏州大学北停车场', '2020-12-26 18:09:04', 'offline');

-- ----------------------------
-- Table structure for `server_load`
-- ----------------------------
DROP TABLE IF EXISTS `server_load`;
CREATE TABLE `server_load` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `server_id` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `server_load` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '0表示日常记录，1表示峰值，2表示谷值，3表示当日总值',
  `server_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of server_load
-- ----------------------------
INSERT INTO `server_load` VALUES ('1', '1', '2020-12-11 20:27:44', '2', '1', '华东服务器');
INSERT INTO `server_load` VALUES ('2', '1', '2020-12-11 20:38:32', '3', '1', '华东服务器');
INSERT INTO `server_load` VALUES ('3', '1', '2020-12-11 20:41:38', '0', '2', '华东服务器');
INSERT INTO `server_load` VALUES ('4', '1', '2020-12-15 14:22:01', '2', '1', '华东服务器');
INSERT INTO `server_load` VALUES ('5', '1', '2020-12-15 14:58:02', '0', '2', '华东服务器');
INSERT INTO `server_load` VALUES ('6', '1', '2020-12-17 12:21:37', '2', '1', '华东服务器');
INSERT INTO `server_load` VALUES ('7', '1', '2020-12-17 12:52:03', '0', '2', '华东服务器');
INSERT INTO `server_load` VALUES ('8', '2', '2020-12-19 10:08:48', '2', '1', '上海服务器');
INSERT INTO `server_load` VALUES ('9', '1', '2020-12-22 14:31:33', '3', '1', '');
INSERT INTO `server_load` VALUES ('10', '1', '2020-12-22 14:35:00', '0', '2', '');
INSERT INTO `server_load` VALUES ('11', '1', '2020-12-23 22:07:10', '8', '1', '');
INSERT INTO `server_load` VALUES ('12', '1', '2020-12-23 21:13:30', '0', '2', '');
INSERT INTO `server_load` VALUES ('13', '1', '2020-12-24 11:24:39', '6', '1', '');
INSERT INTO `server_load` VALUES ('14', '1', '2020-12-24 11:26:12', '0', '2', '');
INSERT INTO `server_load` VALUES ('15', '1', '2020-12-25 15:12:53', '8', '1', '');
INSERT INTO `server_load` VALUES ('16', '1', '2020-12-25 14:06:08', '0', '2', '');
INSERT INTO `server_load` VALUES ('17', '1', '2020-12-26 14:40:21', '4', '1', '');
INSERT INTO `server_load` VALUES ('18', '1', '2020-12-26 16:37:24', '0', '2', '');
