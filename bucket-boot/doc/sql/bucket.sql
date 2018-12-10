/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 100110
 Source Host           : localhost
 Source Database       : bucket

 Target Server Type    : MySQL
 Target Server Version : 100110
 File Encoding         : utf-8

 Date: 12/02/2018 19:20:12 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `sys_func`
-- ----------------------------
DROP TABLE IF EXISTS `sys_func`;
CREATE TABLE `sys_func` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `system_id` int(11) DEFAULT NULL COMMENT '子系统',
  `real_system_id` int(11) DEFAULT NULL COMMENT '真实子系统',
  `code` varchar(10) DEFAULT '' COMMENT '菜单编号，唯一键',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '名字',
  `remark` varchar(200) DEFAULT '' COMMENT '菜单描述',
  `url` text COMMENT 'url',
  `level` smallint(6) DEFAULT NULL COMMENT '级别',
  `parent_code` varchar(10) DEFAULT '' COMMENT '父编号',
  `sort_id` int(11) DEFAULT NULL COMMENT '排序',
  `is_valid` tinyint(3) unsigned NOT NULL COMMENT '是否有效',
  `event_name` varchar(100) DEFAULT '' COMMENT '事件方法名称',
  `element_type` tinyint(3) unsigned DEFAULT NULL COMMENT '元素类型',
  `is_leaf` tinyint(1) DEFAULT '0' COMMENT '是否是叶子,0:不是,1:是',
  `is_open` varchar(10) DEFAULT '' COMMENT '是否开放',
  `func_seq` varchar(128) DEFAULT NULL COMMENT '功能序列',
  `is_visible` tinyint(3) unsigned DEFAULT NULL COMMENT '是否可见',
  `old_id` int(11) DEFAULT NULL,
  `menu_url_method` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_b_menu_UI_t_menu2` (`code`),
  KEY `FK_t_menu_REF_t_sub_system` (`system_id`),
  CONSTRAINT `FK_t_menu_REF_t_sub_system` FOREIGN KEY (`system_id`) REFERENCES `t_b_system` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=280157 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色编码',
  `role_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色描述',
  `role_scope` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色范围',
  `role_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色类型',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建人员',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` mediumtext CHARACTER SET utf8 COLLATE utf8_bin COMMENT '最新修改时间',
  `IS_DELETE` char(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色表';

-- ----------------------------
--  Table structure for `sys_role_function`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_function`;
CREATE TABLE `sys_role_function` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `func_id` bigint(20) NOT NULL COMMENT '功能ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色功能关联表';

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `avatar` varchar(256) DEFAULT NULL COMMENT '头像',
  `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '匿名',
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
  `password` varchar(36) NOT NULL COMMENT '密码',
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '0' COMMENT '0:正常，1:挂起，2:注销，3:锁定',
  `error_count` int(11) DEFAULT NULL COMMENT '密码错误次数',
  `lastest_login` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最近登录时间',
  `identity_code` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户身份证号',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `cellphone` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `weixin_code` varchar(32) DEFAULT NULL COMMENT '微信号',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `is_modify` tinyint(1) DEFAULT '0' COMMENT '密码是否修改',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` mediumtext NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- ----------------------------
--  Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户角色关联表';

SET FOREIGN_KEY_CHECKS = 1;
