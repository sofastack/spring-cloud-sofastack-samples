-- ----------------------------
-- Create Database
-- ----------------------------
CREATE DATABASE IF NOT EXISTS sofastackcloudDB DEFAULT CHARACTER SET = utf8mb4;
Use sofastackcloudDB;

-- ----------------------------
-- Table structure for sofastack_cloud_accounting
-- ----------------------------
DROP TABLE IF EXISTS `sofastack_cloud_accounting`;
CREATE TABLE `sofastack_cloud_accounting` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '户头号，用户唯一标识',
  `user_id` int(11) DEFAULT '-1' COMMENT '用户ID',
  `total_assets` decimal(10,0) DEFAULT '0' COMMENT '总资产',
  `frozen_assets` decimal(10,0) DEFAULT '0' COMMENT '冻结资产',
  `available_assets` decimal(10,0) DEFAULT '0' COMMENT '可用资产',
  `state` varchar(4) DEFAULT '' COMMENT '账户状态：未实名/实名/冻结/',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '账户创建时间',
  `type` varchar(16) DEFAULT '' COMMENT '账户类型：个人/商家',
  `level` int(2) DEFAULT '0' COMMENT '账户等级',
  `points` int(5) DEFAULT '0' COMMENT '账户积分',
  `pay_password` varchar(32) DEFAULT '' COMMENT '加密之后的支付密码',
  `reserved_field` varchar(128) DEFAULT '' COMMENT '预留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '账户表';

-- ----------------------------
-- Records of sofastack_cloud_accounting
-- ----------------------------
BEGIN;
INSERT INTO `sofastack_cloud_accounting`(id,user_id,total_assets,frozen_assets,available_assets,state,create_time,type,level,points,pay_password,reserved_field) VALUES (1, 1000, 9999, 99, 9900, '1', '2019-03-11 21:41:58', 'normal', 1, 9999, '123abc', NULL);
INSERT INTO `sofastack_cloud_accounting`(id,user_id,total_assets,frozen_assets,available_assets,state,create_time,type,level,points,pay_password,reserved_field) VALUES (2, 1001, 1999, 99, 1900, '1', '2019-03-11 22:00:31', 'normal', 1, 1999, '123abc', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sofastack_cloud_bill
-- ----------------------------
DROP TABLE IF EXISTS `sofastack_cloud_bill`;
CREATE TABLE `sofastack_cloud_bill` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `pay` int(11) DEFAULT '0' COMMENT '支出',
  `income` int(11) DEFAULT '0' COMMENT '收入',
  `pay_amount` decimal(10,0) DEFAULT '0' COMMENT '支出金额',
  `income_amount` decimal(10,0) DEFAULT '0' COMMENT '收入金额',
  `user_id` int(11) DEFAULT '0' COMMENT '用户ID',
  `create_time` date DEFAULT CURRENT_TIMESTAMP COMMENT '账单时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT '账单表';

-- ----------------------------
-- Table structure for sofastack_cloud_order
-- ----------------------------
DROP TABLE IF EXISTS `sofastack_cloud_order`;
CREATE TABLE `sofastack_cloud_order` (
  `id` varchar(32) NOT NULL COMMENT '订单号',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `order_type` varchar(32) DEFAULT '' COMMENT '订单类型',
  `order_state` varchar(32) DEFAULT '' COMMENT '订单状态：0未付款,1已付款,-1取消交易\n',
  `order_amount` decimal(10,0) DEFAULT '0' COMMENT '订单金额',
  `source_account` int(11) DEFAULT '0' COMMENT '来源账户',
  `target_account` int(11) DEFAULT '0' COMMENT '目标账户',
  `order_group` varchar(32) DEFAULT '' COMMENT '订单分类',
  `order_mark` varchar(30) DEFAULT '' COMMENT '订单备注',
  `serial_no` varchar(20) DEFAULT '' COMMENT '关联的交易\n流水号',
  `pay_channel` varchar(32) DEFAULT '' COMMENT '支付渠道 0余额 1余额宝 \n',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '账单表';

-- ----------------------------
-- Table structure for sofastack_cloud_serial
-- ----------------------------
DROP TABLE IF EXISTS `sofastack_cloud_serial`;
CREATE TABLE `sofastack_cloud_serial` (
  `id` varchar(20) NOT NULL COMMENT '流水号',
  `serial_type` varchar(16) DEFAULT '' COMMENT '类型：收入、支出',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `target_type` varchar(32) DEFAULT '' COMMENT '用户类型',
  `target_id` int(11) DEFAULT '0' COMMENT '用户/商家ID',
  `serial_src` varchar(32) DEFAULT '' COMMENT '流水来源',
  `money` decimal(10,0) DEFAULT '0' COMMENT '金额',
  `data_flag` int(2) DEFAULT '0' COMMENT '是否有效',
  `remark` varchar(256) DEFAULT '' COMMENT '资金流水备注',
  `pay_terminal` varchar(16) DEFAULT '' COMMENT '支付终端：PC/mobile',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '流水表';

-- ----------------------------
-- Table structure for sofastack_cloud_user
-- ----------------------------
DROP TABLE IF EXISTS `sofastack_cloud_user`;
CREATE TABLE `sofastack_cloud_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID，主键',
  `user_name` varchar(64) DEFAULT '' COMMENT '用户名，真实姓名',
  `nick_name` varchar(128) DEFAULT '' COMMENT '用户昵称',
  `id_card` varchar(20) DEFAULT '' COMMENT '身份证号',
  `password` varchar(32) DEFAULT '' COMMENT 'MD5加密之后的账户密码',
  `email` varchar(100) DEFAULT '' COMMENT '邮箱',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `old_password` varchar(32) DEFAULT '' COMMENT '旧密码',
  `role_type` varchar(12) DEFAULT '' COMMENT '角色类型',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '用户表';

-- ----------------------------
-- Records of sofastack_cloud_user
-- ----------------------------
BEGIN;
INSERT INTO `sofastack_cloud_user`(id,user_name,nick_name,id_card,password,email,create_time,old_password,role_type,user_id) VALUES (2, 'sofa', 'sofa', '1234564321234564', 'sofa', 'sofa@sofa.com', '2019-03-05 19:25:20', 'oldpwd', 'admin', 1000);
INSERT INTO `sofastack_cloud_user`(id,user_name,nick_name,id_card,password,email,create_time,old_password,role_type,user_id) VALUES (3, 'cloud', 'cloud', '0980989012312312', 'cloud', 'cloud@cloud.com', '2019-03-11 21:59:35', 'oldpwd', 'admin', 1001);
COMMIT;
