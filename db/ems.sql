
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `client`
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `phone` varchar(64) DEFAULT NULL COMMENT '运单号',
  `address` varchar(128) DEFAULT NULL,
  `note` varchar(128) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of client
-- ----------------------------
INSERT INTO `client` VALUES ('1', '庞老板', '17863671111', '123', '职业老赖', '1');
INSERT INTO `client` VALUES ('2', '于老板', '17863671111', '456', '职业老赖', '1');
INSERT INTO `client` VALUES ('3', '张老板', '17863671111', '黑龙江省双鸭山市集贤县偶或', '信誉不错', '0');
INSERT INTO `client` VALUES ('4', '史老板', '17863671111', '123', '职业老赖', '1');
INSERT INTO `client` VALUES ('5', '朱老板', '17863671111', '123', '职业老赖...', '1');
INSERT INTO `client` VALUES ('6', '测试角色', '17863671111', '123', '信誉挺好', '0');
INSERT INTO `client` VALUES ('7', '明雨哥', '17863671111', '123', '很讲诚信', '1');
INSERT INTO `client` VALUES ('8', '谢老板', '17863671111', '123', '123', '1');
INSERT INTO `client` VALUES ('9', '张三', '17863671111', '123', '', '1');
INSERT INTO `client` VALUES ('10', '小麻', '17863671111', '山东省聊城市茌平县哈哈哈哈', '哈哈哈哈', '1');

-- ----------------------------
-- Table structure for `client_order`
-- ----------------------------
DROP TABLE IF EXISTS `client_order`;
CREATE TABLE `client_order` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `cid` int(32) DEFAULT NULL,
  `oid` int(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `deleo` (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of client_order
-- ----------------------------
INSERT INTO `client_order` VALUES ('3', '2', '7');
INSERT INTO `client_order` VALUES ('4', '2', '8');
INSERT INTO `client_order` VALUES ('5', '1', '9');
INSERT INTO `client_order` VALUES ('6', '7', '10');
INSERT INTO `client_order` VALUES ('7', '5', '11');
INSERT INTO `client_order` VALUES ('8', '5', '12');
INSERT INTO `client_order` VALUES ('9', '4', '13');
INSERT INTO `client_order` VALUES ('10', '8', '14');
INSERT INTO `client_order` VALUES ('11', '9', '15');
INSERT INTO `client_order` VALUES ('12', '1', '16');
INSERT INTO `client_order` VALUES ('13', '1', '17');
INSERT INTO `client_order` VALUES ('14', '2', '18');
INSERT INTO `client_order` VALUES ('15', '5', '19');

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `num` varchar(64) DEFAULT NULL,
  `orderNum` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `count` double(64,0) DEFAULT NULL,
  `type` int(32) DEFAULT NULL,
  `clientId` int(32) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `operator` varchar(64) DEFAULT NULL,
  `expCode` varchar(64) DEFAULT NULL,
  `expNo` varchar(64) DEFAULT NULL,
  `ship` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('27', 'G202003312112410001', 'N202003181540180001', 'N95口罩', '65', '1', '1', '2020-03-31 21:12:41', '庞庆舟', 'YTO', 'YT4347941418575', '1');
INSERT INTO `goods` VALUES ('26', 'G202003312035570001', '', '茶叶', '990', '1', '0', '2020-03-31 20:35:57', '庞庆舟', null, null, '0');
INSERT INTO `goods` VALUES ('28', 'G202003312141550001', '', '茶叶', '90', '1', '0', '2020-03-31 21:41:55', '庞庆舟', null, null, '0');
INSERT INTO `goods` VALUES ('29', 'G202003312211550001', '', '茶叶', '3', '1', '0', '2020-03-31 22:11:55', '庞庆舟', null, null, '0');
INSERT INTO `goods` VALUES ('30', 'G202003312224300001', 'N202003201455260001', '护目镜', '90', '1', '4', '2020-03-31 22:24:30', '庞庆舟', 'YTO', 'YT2037026334999', '1');
INSERT INTO `goods` VALUES ('31', 'G202003312227490001', 'N202003201624230001', '小矮胖猫', '88', '1', '8', '2020-03-31 22:27:49', '庞庆舟', 'YTO', 'YT2047017381987', '0');
INSERT INTO `goods` VALUES ('32', 'G202003312235030001', 'N202003181349100001', '消毒水', '1450', '1', '2', '2020-03-31 22:35:03', '庞庆舟', null, null, '0');
INSERT INTO `goods` VALUES ('33', 'G202003312239250001', 'N202003181546440001', '青岛啤酒', '90', '1', '5', '2020-03-31 22:39:25', '庞庆舟', null, null, '0');
INSERT INTO `goods` VALUES ('34', 'G202003312243360001', 'N202003181546050001', '普通医用口罩', '55', '1', '5', '2020-03-31 22:43:36', '庞庆舟', null, null, '0');
INSERT INTO `goods` VALUES ('35', 'G202004011030230001', '', '哈默', '340', '1', '0', '2020-04-01 10:30:23', '庞庆舟', null, null, '0');
INSERT INTO `goods` VALUES ('36', 'G202004071020550001', 'N202004052106310001', '百世可乐', '99', '1', '1', '2020-04-07 10:20:55', '庞庆舟', null, null, '0');
INSERT INTO `goods` VALUES ('37', 'G202004092003550001', '', '朱明轩', '9', '1', '0', '2020-04-09 20:03:55', '庞庆舟', null, null, '0');
INSERT INTO `goods` VALUES ('38', 'G202004151811400001', 'N202003181423460001', '消毒液', '490', '1', '2', '2020-04-15 18:11:40', '庞庆舟', null, null, '0');

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pattern` varchar(64) DEFAULT NULL,
  `path` varchar(64) DEFAULT NULL,
  `level` int(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `icon` varchar(64) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `enable` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('2', '/', '/home', '0', '用户管理', 'el-icon-user-solid', '1', '1');
INSERT INTO `menu` VALUES ('3', '/', '/home', '0', '权限管理', 'el-icon-s-platform', '1', '1');
INSERT INTO `menu` VALUES ('6', '/employee/userList/**', '/userList', '1', '用户列表', 'el-icon-menu', '2', '1');
INSERT INTO `menu` VALUES ('7', '/staff/roleList/**', '/roleList', '1', '角色列表', 'el-icon-menu', '3', '1');
INSERT INTO `menu` VALUES ('8', '/staff/powList/**', '/powList', '1', '权限列表', 'el-icon-menu', '3', '1');
INSERT INTO `menu` VALUES ('9', '/warehouse/myWarehouse/**', '/myWarehouse', '1', '我的仓库', 'el-icon-menu', '35', '1');
INSERT INTO `menu` VALUES ('11', '/employee/updateEnabled/**', '/userList', '2', '更改用户状态', null, '6', '0');
INSERT INTO `menu` VALUES ('12', '/employee/addUser/**', '/userList', '2', '添加用户', null, '6', '0');
INSERT INTO `menu` VALUES ('13', '/employee/updateUser/**', '/userList', '2', '更改用户', null, '6', '0');
INSERT INTO `menu` VALUES ('14', '/employee/deleteUser/**', '/userList', '2', '删除用户', null, '6', '0');
INSERT INTO `menu` VALUES ('15', '/staff/addRole/**', '/roleList', '2', '添加角色', null, '7', '0');
INSERT INTO `menu` VALUES ('16', '/staff/editRole/**', '/roleList', '2', '更改角色', null, '7', '0');
INSERT INTO `menu` VALUES ('17', '/staff/deleteRole/**', '/roleList', '2', '删除角色', null, '7', '0');
INSERT INTO `menu` VALUES ('18', '/employee/allRole/**', '/userList', '2', '分配角色', null, '6', '0');
INSERT INTO `menu` VALUES ('19', '/staff/deletePow/**', '/roleList', '2', '删除角色权限', null, '7', '0');
INSERT INTO `menu` VALUES ('20', '/staff/assPow/**', '/roleList', '2', '分配权限', null, '7', '0');
INSERT INTO `menu` VALUES ('21', '/staff/changeMenuEnable/**', '/powList', '2', '菜单状态', null, '8', '0');
INSERT INTO `menu` VALUES ('22', '/staff/addPow/**', '/powList', '2', '添加权限（菜单）', null, '8', '0');
INSERT INTO `menu` VALUES ('25', '/staff/editPow/**', '/powList', '2', '修改权限', null, '8', '0');
INSERT INTO `menu` VALUES ('27', '/staff/deletePow/**', '/powList', '2', '删除权限', null, '8', '0');
INSERT INTO `menu` VALUES ('28', '/', '/home', '0', '系统监控', 'el-icon-odometer', '1', '1');
INSERT INTO `menu` VALUES ('29', '/system/sql/**', '/systemSql', '1', 'SQL监控', 'el-icon-menu', '28', '1');
INSERT INTO `menu` VALUES ('30', '/system/druidSql/**', '/systemSql', '2', '日志记录', null, '29', '0');
INSERT INTO `menu` VALUES ('31', '/', '/home', '0', '订单管理', 'el-icon-s-order', '1', '1');
INSERT INTO `menu` VALUES ('32', '/order/orderList/**', '/orderList', '1', '订单列表', 'el-icon-menu', '31', '1');
INSERT INTO `menu` VALUES ('33', '/', '/home', '0', '生产管理', 'el-icon-s-claim', '1', '1');
INSERT INTO `menu` VALUES ('34', '/output/findOutputs/**', '/outputList', '1', '我的生产', 'el-icon-menu', '33', '1');
INSERT INTO `menu` VALUES ('35', '/', '/home', '0', '仓库管理', 'el-icon-s-home', '1', '1');
INSERT INTO `menu` VALUES ('36', '/goods/enterList/**', '/enter', '1', '入库管理', 'el-icon-menu', '35', '1');
INSERT INTO `menu` VALUES ('37', '/goods/enterList/**', '/out', '1', '出库管理', 'el-icon-menu', '35', '1');
INSERT INTO `menu` VALUES ('38', '/warehouse/check/**', '/check', '1', '仓库盘点', 'el-icon-menu', '35', '1');
INSERT INTO `menu` VALUES ('39', '/', '/home', '0', '数据统计', 'el-icon-s-data', '1', '1');
INSERT INTO `menu` VALUES ('40', '/data/report/**', '/report', '1', '数据报表', 'el-icon-menu', '39', '1');
INSERT INTO `menu` VALUES ('41', '/', '/home', '0', '客户管理', 'el-icon-s-custom', '1', '1');
INSERT INTO `menu` VALUES ('42', '/client/clientList/**', '/clientList', '1', '客户列表', 'el-icon-menu', '41', '1');
INSERT INTO `menu` VALUES ('43', '/client/addClient/**', '/clientList', '2', '添加客户', '', '42', '0');
INSERT INTO `menu` VALUES ('44', '/client/getOrderByCid/**', '/clientList', '2', '查看订单', '', '42', '0');
INSERT INTO `menu` VALUES ('45', '/client/editClient/**', '/clientList', '2', '修改客户信息', '', '42', '0');
INSERT INTO `menu` VALUES ('46', '/client/enterBlacklist/**', '/clientList', '2', '加入黑名单', '', '42', '0');
INSERT INTO `menu` VALUES ('47', '/client/getBlacklist/**', '/clientList', '2', '黑名单列表', '', '42', '0');
INSERT INTO `menu` VALUES ('48', '/order/addOrder/**', '/orderList', '2', '添加订单', '', '32', '0');
INSERT INTO `menu` VALUES ('49', '/order/editOrder/**', '/orderList', '2', '修改订单', '', '32', '0');
INSERT INTO `menu` VALUES ('50', '/order/deleteOrder/**', '/orderList', '2', '删除订单', '', '32', '0');
INSERT INTO `menu` VALUES ('51', '/output/addOutput/**', '/outputList', '2', '新建生产线', '', '34', '0');
INSERT INTO `menu` VALUES ('52', '/output/updateOutputState/**', '/outputList', '2', '更改生产状态', '', '34', '0');
INSERT INTO `menu` VALUES ('53', '/output/updateOutput/**', '/output', '2', '更新生产线信息', '', '34', '0');
INSERT INTO `menu` VALUES ('54', '/output/deleteOutput/**', '/outputList', '2', '删除生产线', '', '34', '0');
INSERT INTO `menu` VALUES ('55', '/output/insertOutput/**', '/outputList', '2', '新建生产', '', '34', '0');
INSERT INTO `menu` VALUES ('56', '/order/orders/**', '/outputList', '2', '远程订单列表', '', '34', '0');
INSERT INTO `menu` VALUES ('57', '/output/outputLog/**', '/outputList', '2', '工作日志', '', '34', '0');
INSERT INTO `menu` VALUES ('58', '/output/workOutput/**', '/outputList', '2', '工作进度', '', '34', '0');
INSERT INTO `menu` VALUES ('59', '/output/achieve/**', '/outputList', '2', '完成生产', '', '34', '0');
INSERT INTO `menu` VALUES ('64', '/warehouse/myRegion/**', '/region', '2', '库区管理', '', '9', '0');
INSERT INTO `menu` VALUES ('65', '/warehouse/myShelf/**', '/shelf', '2', '我的货架', '', '9', '0');
INSERT INTO `menu` VALUES ('66', '/warehouse/addShelf/**', '/shelf', '2', '新建货架', '', '9', '0');
INSERT INTO `menu` VALUES ('67', '/warehouse/transitions/**', '/myWarehouse', '2', '远程查询完成生产产品', '', '9', '0');
INSERT INTO `menu` VALUES ('68', '/warehouse/position/**', '/myWarehouse', '2', '存储位置（级联选择器）', '', '9', '0');
INSERT INTO `menu` VALUES ('69', '/warehouse/store/**', '/myWarehouse', '2', '待检库暂存', '', '9', '0');
INSERT INTO `menu` VALUES ('70', '/transition/myTransition/**', '/transition', '1', '生产档案', 'el-icon-menu', '33', '1');
INSERT INTO `menu` VALUES ('71', '/warehouse/getWarehouseId/**', '/enter', '2', '选择仓库', '', '36', '0');
INSERT INTO `menu` VALUES ('72', '/warehouse/enter/**', '/enter', '2', '入库', '', '36', '0');
INSERT INTO `menu` VALUES ('73', '/transition/transitionPosition/**', '/transition', '2', '存放位置（待检库）', '', '70', '0');
INSERT INTO `menu` VALUES ('74', '/goods/goodsPosition/**', '/enter', '2', '存储位置（仓库）', '', '36', '0');
INSERT INTO `menu` VALUES ('75', '/goods/ship/**', '/out', '2', '物流信息', '', '37', '0');
INSERT INTO `menu` VALUES ('76', '/goods/out/**', '/out', '2', '出库', '', '37', '0');
INSERT INTO `menu` VALUES ('78', '/transition/deleteTransition/**', '/transition', '2', '删除生产数据', '', '70', '0');

-- ----------------------------
-- Table structure for `menu_role`
-- ----------------------------
DROP TABLE IF EXISTS `menu_role`;
CREATE TABLE `menu_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dm` (`mid`),
  KEY `dr` (`rid`),
  CONSTRAINT `dm` FOREIGN KEY (`mid`) REFERENCES `menu` (`id`) ON DELETE CASCADE,
  CONSTRAINT `dr` FOREIGN KEY (`rid`) REFERENCES `role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=704 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of menu_role
-- ----------------------------
INSERT INTO `menu_role` VALUES ('182', '2', '1');
INSERT INTO `menu_role` VALUES ('183', '6', '1');
INSERT INTO `menu_role` VALUES ('184', '11', '1');
INSERT INTO `menu_role` VALUES ('185', '12', '1');
INSERT INTO `menu_role` VALUES ('186', '13', '1');
INSERT INTO `menu_role` VALUES ('187', '14', '1');
INSERT INTO `menu_role` VALUES ('188', '18', '1');
INSERT INTO `menu_role` VALUES ('189', '3', '1');
INSERT INTO `menu_role` VALUES ('190', '8', '1');
INSERT INTO `menu_role` VALUES ('191', '27', '1');
INSERT INTO `menu_role` VALUES ('192', '25', '1');
INSERT INTO `menu_role` VALUES ('193', '22', '1');
INSERT INTO `menu_role` VALUES ('194', '21', '1');
INSERT INTO `menu_role` VALUES ('195', '7', '1');
INSERT INTO `menu_role` VALUES ('196', '20', '1');
INSERT INTO `menu_role` VALUES ('197', '19', '1');
INSERT INTO `menu_role` VALUES ('198', '15', '1');
INSERT INTO `menu_role` VALUES ('199', '16', '1');
INSERT INTO `menu_role` VALUES ('200', '17', '1');
INSERT INTO `menu_role` VALUES ('201', '28', '1');
INSERT INTO `menu_role` VALUES ('202', '29', '1');
INSERT INTO `menu_role` VALUES ('203', '30', '1');
INSERT INTO `menu_role` VALUES ('508', '41', '12');
INSERT INTO `menu_role` VALUES ('509', '42', '12');
INSERT INTO `menu_role` VALUES ('510', '47', '12');
INSERT INTO `menu_role` VALUES ('511', '46', '12');
INSERT INTO `menu_role` VALUES ('512', '45', '12');
INSERT INTO `menu_role` VALUES ('513', '43', '12');
INSERT INTO `menu_role` VALUES ('514', '44', '12');
INSERT INTO `menu_role` VALUES ('515', '33', '12');
INSERT INTO `menu_role` VALUES ('516', '34', '12');
INSERT INTO `menu_role` VALUES ('517', '51', '12');
INSERT INTO `menu_role` VALUES ('518', '59', '12');
INSERT INTO `menu_role` VALUES ('519', '58', '12');
INSERT INTO `menu_role` VALUES ('520', '57', '12');
INSERT INTO `menu_role` VALUES ('521', '56', '12');
INSERT INTO `menu_role` VALUES ('522', '55', '12');
INSERT INTO `menu_role` VALUES ('523', '54', '12');
INSERT INTO `menu_role` VALUES ('524', '53', '12');
INSERT INTO `menu_role` VALUES ('525', '52', '12');
INSERT INTO `menu_role` VALUES ('526', '31', '12');
INSERT INTO `menu_role` VALUES ('527', '32', '12');
INSERT INTO `menu_role` VALUES ('528', '50', '12');
INSERT INTO `menu_role` VALUES ('529', '49', '12');
INSERT INTO `menu_role` VALUES ('530', '48', '12');
INSERT INTO `menu_role` VALUES ('646', '36', '2');
INSERT INTO `menu_role` VALUES ('647', '71', '2');
INSERT INTO `menu_role` VALUES ('648', '72', '2');
INSERT INTO `menu_role` VALUES ('649', '74', '2');
INSERT INTO `menu_role` VALUES ('650', '37', '2');
INSERT INTO `menu_role` VALUES ('651', '76', '2');
INSERT INTO `menu_role` VALUES ('652', '75', '2');
INSERT INTO `menu_role` VALUES ('653', '9', '2');
INSERT INTO `menu_role` VALUES ('654', '68', '2');
INSERT INTO `menu_role` VALUES ('655', '69', '2');
INSERT INTO `menu_role` VALUES ('656', '66', '2');
INSERT INTO `menu_role` VALUES ('657', '65', '2');
INSERT INTO `menu_role` VALUES ('658', '64', '2');
INSERT INTO `menu_role` VALUES ('659', '67', '2');
INSERT INTO `menu_role` VALUES ('660', '31', '2');
INSERT INTO `menu_role` VALUES ('661', '32', '2');
INSERT INTO `menu_role` VALUES ('662', '50', '2');
INSERT INTO `menu_role` VALUES ('663', '49', '2');
INSERT INTO `menu_role` VALUES ('664', '48', '2');
INSERT INTO `menu_role` VALUES ('665', '33', '2');
INSERT INTO `menu_role` VALUES ('666', '34', '2');
INSERT INTO `menu_role` VALUES ('667', '58', '2');
INSERT INTO `menu_role` VALUES ('668', '57', '2');
INSERT INTO `menu_role` VALUES ('669', '56', '2');
INSERT INTO `menu_role` VALUES ('670', '55', '2');
INSERT INTO `menu_role` VALUES ('671', '54', '2');
INSERT INTO `menu_role` VALUES ('672', '53', '2');
INSERT INTO `menu_role` VALUES ('673', '52', '2');
INSERT INTO `menu_role` VALUES ('674', '59', '2');
INSERT INTO `menu_role` VALUES ('675', '51', '2');
INSERT INTO `menu_role` VALUES ('676', '70', '2');
INSERT INTO `menu_role` VALUES ('677', '73', '2');
INSERT INTO `menu_role` VALUES ('678', '41', '2');
INSERT INTO `menu_role` VALUES ('679', '42', '2');
INSERT INTO `menu_role` VALUES ('680', '47', '2');
INSERT INTO `menu_role` VALUES ('681', '46', '2');
INSERT INTO `menu_role` VALUES ('682', '45', '2');
INSERT INTO `menu_role` VALUES ('683', '44', '2');
INSERT INTO `menu_role` VALUES ('684', '43', '2');
INSERT INTO `menu_role` VALUES ('685', '35', '2');
INSERT INTO `menu_role` VALUES ('686', '36', '3');
INSERT INTO `menu_role` VALUES ('687', '71', '3');
INSERT INTO `menu_role` VALUES ('688', '72', '3');
INSERT INTO `menu_role` VALUES ('689', '74', '3');
INSERT INTO `menu_role` VALUES ('690', '37', '3');
INSERT INTO `menu_role` VALUES ('691', '76', '3');
INSERT INTO `menu_role` VALUES ('692', '75', '3');
INSERT INTO `menu_role` VALUES ('693', '9', '3');
INSERT INTO `menu_role` VALUES ('694', '68', '3');
INSERT INTO `menu_role` VALUES ('695', '69', '3');
INSERT INTO `menu_role` VALUES ('696', '66', '3');
INSERT INTO `menu_role` VALUES ('697', '65', '3');
INSERT INTO `menu_role` VALUES ('698', '64', '3');
INSERT INTO `menu_role` VALUES ('699', '67', '3');
INSERT INTO `menu_role` VALUES ('700', '70', '3');
INSERT INTO `menu_role` VALUES ('701', '73', '3');
INSERT INTO `menu_role` VALUES ('702', '35', '3');
INSERT INTO `menu_role` VALUES ('703', '33', '3');

-- ----------------------------
-- Table structure for `order_list`
-- ----------------------------
DROP TABLE IF EXISTS `order_list`;
CREATE TABLE `order_list` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '商品名称',
  `orderNum` varchar(128) DEFAULT NULL COMMENT '订单编号',
  `price` double(64,0) DEFAULT NULL COMMENT '商品价格',
  `count` int(64) DEFAULT NULL COMMENT '商品数量',
  `pay` tinyint(1) DEFAULT NULL COMMENT '是否支付',
  `date` datetime DEFAULT NULL COMMENT '下单时间',
  `transport` tinyint(1) DEFAULT NULL COMMENT '是否发货',
  `orderState` int(32) DEFAULT '1' COMMENT '物流信息',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_list
-- ----------------------------
INSERT INTO `order_list` VALUES ('7', '消毒水', 'N202003181349100001', '4500', '1500', '1', '2020-03-18 13:49:10', '0', '5');
INSERT INTO `order_list` VALUES ('8', '消毒液', 'N202003181423460001', '2000', '500', '1', '2020-03-18 14:23:46', '0', '5');
INSERT INTO `order_list` VALUES ('9', 'N95口罩', 'N202003181540180001', '666', '66', '1', '2020-03-18 15:40:18', '1', '7');
INSERT INTO `order_list` VALUES ('10', '防护服', 'N202003181545330001', '20000', '1000', '0', '2020-03-18 15:45:33', '0', '4');
INSERT INTO `order_list` VALUES ('11', '普通医用口罩', 'N202003181546050001', '300', '100', '0', '2020-03-18 15:46:05', '0', '5');
INSERT INTO `order_list` VALUES ('12', '青岛啤酒', 'N202003181546440001', '600', '99', '0', '2020-03-18 15:46:44', '0', '5');
INSERT INTO `order_list` VALUES ('13', '护目镜', 'N202003201455260001', '999', '99', '1', '2020-03-20 14:55:26', '1', '7');
INSERT INTO `order_list` VALUES ('14', '小矮胖猫', 'N202003201624230001', '999', '88', '1', '2020-03-20 16:24:23', '1', '7');
INSERT INTO `order_list` VALUES ('15', '电脑', 'N202003222027190001', '12000', '100', '0', '2020-03-22 20:27:19', '0', '4');
INSERT INTO `order_list` VALUES ('16', '牛奶', 'N202003291419150001', '3000', '66', '0', '2020-03-29 14:19:15', '0', '4');
INSERT INTO `order_list` VALUES ('17', '百世可乐', 'N202004052106310001', '1000', '100', '0', '2020-04-05 21:06:31', '0', '5');
INSERT INTO `order_list` VALUES ('18', '可口可乐', 'N202004091218100001', '200', '99', '0', '2020-04-09 12:18:10', '0', '3');
INSERT INTO `order_list` VALUES ('19', '雀巢咖啡', 'N202004091226140001', '200', '100', '0', '2020-04-09 12:26:14', '0', '2');

-- ----------------------------
-- Table structure for `output`
-- ----------------------------
DROP TABLE IF EXISTS `output`;
CREATE TABLE `output` (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `total` int(64) DEFAULT '0',
  `complete` double(64,2) DEFAULT '0.00',
  `percentage` double(64,2) DEFAULT '0.00',
  `note` varchar(128) DEFAULT NULL,
  `state` tinyint(1) DEFAULT '1',
  `orderNum` varchar(64) DEFAULT NULL,
  `orderName` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of output
-- ----------------------------
INSERT INTO `output` VALUES ('1', '1号生产线', '100', '66.00', '66.00', '该生产线位于山东省济南市商河县以北。\n生产线采用最新技术，高效便捷，用于生产大批量货物。', '1', 'N202004091226140001', '雀巢咖啡');
INSERT INTO `output` VALUES ('2', '2号生产线', '100', '5.00', '5.00', '磨磨唧唧，效率很低', '1', '', '蒙牛牛奶');
INSERT INTO `output` VALUES ('3', '3号生产线', '100', '0.00', '0.00', '生产效率一般情况', '1', '', '山楂树下');

-- ----------------------------
-- Table structure for `output_log`
-- ----------------------------
DROP TABLE IF EXISTS `output_log`;
CREATE TABLE `output_log` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `orderNum` varchar(64) DEFAULT NULL,
  `orderName` varchar(64) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `oid` int(32) DEFAULT NULL,
  `operator` int(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of output_log
-- ----------------------------
INSERT INTO `output_log` VALUES ('1', 'test', '测试', '2020-03-18 19:54:39', '0000-00-00 00:00:00', null, null);
INSERT INTO `output_log` VALUES ('4', 'N202003181349100001', '消毒水', '2020-03-19 20:24:58', '2020-03-20 20:25:10', '1', '1');
INSERT INTO `output_log` VALUES ('5', 'N202003181540180001', 'N95口罩', '2020-03-19 21:02:35', '2020-03-20 14:48:22', '2', '1');
INSERT INTO `output_log` VALUES ('6', '', '茶叶', '2020-03-19 22:30:44', '2020-03-20 14:26:12', '3', '1');
INSERT INTO `output_log` VALUES ('8', '', '茶叶', '2020-03-20 14:37:55', '2020-03-20 14:38:27', '3', '1');
INSERT INTO `output_log` VALUES ('9', '', '茶叶', '2020-03-20 14:43:14', '2020-03-20 14:44:59', '3', '1');
INSERT INTO `output_log` VALUES ('10', 'N202003201455260001', '护目镜', '2020-03-20 15:41:37', '2020-03-20 15:41:56', '2', '1');
INSERT INTO `output_log` VALUES ('11', '', '哈默', '2020-03-20 15:43:14', '2020-03-25 13:45:14', '3', '1');
INSERT INTO `output_log` VALUES ('12', 'N202003201624230001', '小矮胖猫', '2020-03-20 16:24:49', '2020-03-20 20:20:34', '2', '1');
INSERT INTO `output_log` VALUES ('13', 'N202003181546440001', '青岛啤酒', '2020-03-20 20:25:24', '2020-03-22 20:34:46', '1', '1');
INSERT INTO `output_log` VALUES ('14', 'N202003181546050001', '普通医用口罩', '2020-03-20 20:25:50', '2020-03-24 14:47:01', '2', '1');
INSERT INTO `output_log` VALUES ('15', 'N202003222027190001', '电脑', '2020-03-22 23:09:01', '2020-03-29 11:03:44', '1', '1');
INSERT INTO `output_log` VALUES ('16', '', '水杯', '2020-03-24 14:50:13', '2020-03-25 13:45:14', '2', '1');
INSERT INTO `output_log` VALUES ('17', 'N202003181423460001', '消毒液', '2020-03-25 22:27:01', '2020-03-25 22:27:13', '3', '2');
INSERT INTO `output_log` VALUES ('18', 'N202003181545330001', '防护服', '2020-03-29 12:32:29', '2020-03-29 12:32:49', '1', '1');
INSERT INTO `output_log` VALUES ('19', 'N202003291419150001', '牛奶', '2020-03-29 14:19:32', '2020-03-29 14:19:41', '1', '1');
INSERT INTO `output_log` VALUES ('20', '', '朱明轩', '2020-04-01 10:31:03', '2020-04-01 10:31:11', '1', '1');
INSERT INTO `output_log` VALUES ('21', 'N202004052106310001', '百世可乐', '2020-04-05 21:06:50', '2020-04-07 10:18:27', '1', '1');
INSERT INTO `output_log` VALUES ('22', 'N202004091218100001', '可口可乐', '2020-04-09 12:18:25', '2020-04-09 12:25:14', '1', '1');
INSERT INTO `output_log` VALUES ('23', '', '蒙牛牛奶', '2020-04-09 12:18:47', null, '2', '1');
INSERT INTO `output_log` VALUES ('24', '', '山楂树下', '2020-04-09 12:23:45', null, '3', '1');
INSERT INTO `output_log` VALUES ('25', 'N202004091226140001', '雀巢咖啡', '2020-04-09 12:26:37', null, '1', '1');

-- ----------------------------
-- Table structure for `persistent_logins`
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `nameZh` varchar(32) DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `nameZh` (`nameZh`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_admin', '系统管理员', '拥有管理系统的权力');
INSERT INTO `role` VALUES ('2', 'ROLE_manager', '总经理', '管理仓库的权力');
INSERT INTO `role` VALUES ('3', 'ROLE_warehouse', '仓库管理员', '对仓库的管理');
INSERT INTO `role` VALUES ('12', 'ROLE_workshop', '车间生产员', '客户订单生产模块的管理');

-- ----------------------------
-- Table structure for `transition`
-- ----------------------------
DROP TABLE IF EXISTS `transition`;
CREATE TABLE `transition` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `orderNum` varchar(128) DEFAULT NULL COMMENT '订单id',
  `name` varchar(64) DEFAULT NULL,
  `plan` int(64) DEFAULT NULL,
  `complete` double(64,2) DEFAULT NULL,
  `outputId` int(32) DEFAULT NULL,
  `state` int(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of transition
-- ----------------------------
INSERT INTO `transition` VALUES ('22', 'N202004091218100001', '可口可乐', '99', '99.00', '1', '1');
INSERT INTO `transition` VALUES ('21', 'N202004052106310001', '百世可乐', '100', '100.00', '1', '3');
INSERT INTO `transition` VALUES ('8', 'N202003181540180001', 'N95口罩', '66', '66.00', '2', '3');
INSERT INTO `transition` VALUES ('9', 'N202003201455260001', '护目镜', '99', '99.00', '2', '3');
INSERT INTO `transition` VALUES ('10', 'N202003201624230001', '小矮胖猫', '88', '88.00', '2', '3');
INSERT INTO `transition` VALUES ('11', 'N202003181349100001', '消毒水', '1500', '1500.00', '1', '3');
INSERT INTO `transition` VALUES ('12', 'N202003181546440001', '青岛啤酒', '99', '99.00', '1', '3');
INSERT INTO `transition` VALUES ('13', 'N202003181546050001', '普通医用口罩', '100', '60.00', '2', '3');
INSERT INTO `transition` VALUES ('14', '', '哈默', '345', '345.00', '3', '3');
INSERT INTO `transition` VALUES ('15', 'N202003181423460001', '消毒液', '500', '500.00', '3', '3');
INSERT INTO `transition` VALUES ('16', '', '水杯', '20', '20.00', '2', '2');
INSERT INTO `transition` VALUES ('17', 'N202003222027190001', '电脑', '100', '100.00', '1', '2');
INSERT INTO `transition` VALUES ('18', 'N202003181545330001', '防护服', '1000', '1000.00', '1', '2');
INSERT INTO `transition` VALUES ('19', 'N202003291419150001', '牛奶', '66', '66.00', '1', '2');
INSERT INTO `transition` VALUES ('20', '', '朱明轩', '10', '10.00', '1', '3');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'hrID',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `phone` char(11) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `roleName` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `enabled` tinyint(1) DEFAULT '1',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `userface` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '庞庆舟', '17863671817', 'pqz@163.com', '超级管理员', '1', 'admin', '$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm', 'http://localhost:8181/uploadFile/2020/04/29/22543b71-a7ff-4571-b0a5-65e630e14c1e.JPG', null);
INSERT INTO `user` VALUES ('34', '肉肉', '17863671788', '1204216126@qq.com', '总经理', '1', 'rourou', '$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm', null, '');
INSERT INTO `user` VALUES ('35', '丢丢', '17863671816', 'haha@163.com', '仓库管理员', '1', 'diudiu', '$2a$10$fJq9a2tiHDeAa0pRSoT3ieF.hnlLEuirrbHrzaX5LvZ5YT151Oe1m', null, '');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `deu` (`uid`),
  KEY `der` (`rid`),
  CONSTRAINT `der` FOREIGN KEY (`rid`) REFERENCES `role` (`id`) ON DELETE CASCADE,
  CONSTRAINT `deu` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '1', '2');
INSERT INTO `user_role` VALUES ('26', '35', '3');
INSERT INTO `user_role` VALUES ('32', '34', '2');

-- ----------------------------
-- Table structure for `warehouse`
-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `num` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL,
  `area` double(64,2) DEFAULT NULL,
  `capacity` double(64,2) DEFAULT '0.00',
  `used` double(64,2) DEFAULT '0.00',
  `percentage` double(64,2) DEFAULT '0.00',
  `parentId` int(32) DEFAULT NULL,
  `type` int(32) DEFAULT NULL,
  `layer` int(32) DEFAULT '1',
  `state` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=149 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of warehouse
-- ----------------------------
INSERT INTO `warehouse` VALUES ('2', 'T1', '待检库', '山东省济南市商河县以北', '1000.00', '240.00', '56.00', '23.33', '1', '1', '1', '1');
INSERT INTO `warehouse` VALUES ('3', 'R1', '库区', null, '200.00', '120.00', '56.00', '46.67', '2', '2', '1', '1');
INSERT INTO `warehouse` VALUES ('4', 'S1', '货架', null, '10.00', '30.00', '16.00', '53.33', '3', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('5', 'L1', '层', null, '10.00', '10.00', '0.00', '0.00', '4', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('6', 'L2', '层', null, '10.00', '10.00', '10.00', '100.00', '4', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('7', 'L3', '层', null, '10.00', '10.00', '6.00', '60.00', '4', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('8', 'W1', '普通仓库', '山东省济南市商河县以北', '1000.00', '360.00', '139.00', '38.61', '1', '1', '1', '1');
INSERT INTO `warehouse` VALUES ('9', 'R2', '库区', null, '200.00', '90.00', '0.00', '0.00', '2', '2', '1', '1');
INSERT INTO `warehouse` VALUES ('23', 'S2', '货架', null, '10.00', '30.00', '10.00', '33.33', '3', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('24', 'L1', '层', null, '10.00', '10.00', '10.00', '100.00', '23', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('25', 'L2', '层', null, '10.00', '10.00', '0.00', '0.00', '23', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('26', 'L3', '层', null, '10.00', '10.00', '0.00', '0.00', '23', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('27', 'S3', '货架', null, '10.00', '30.00', '30.00', '100.00', '3', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('28', 'L1', '层', null, '10.00', '10.00', '10.00', '100.00', '27', '4', '1', '0');
INSERT INTO `warehouse` VALUES ('29', 'L2', '层', null, '10.00', '10.00', '10.00', '100.00', '27', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('30', 'L3', '层', null, '10.00', '10.00', '10.00', '100.00', '27', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('31', 'S1', '货架', null, '10.00', '30.00', '0.00', '0.00', '9', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('32', 'L1', '层', null, '10.00', '10.00', '0.00', '0.00', '31', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('33', 'L2', '层', null, '10.00', '10.00', '0.00', '0.00', '31', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('34', 'L3', '层', null, '10.00', '10.00', '0.00', '0.00', '31', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('35', 'S2', '货架', null, '10.00', '30.00', '0.00', '0.00', '9', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('36', 'L1', '层', null, '10.00', '10.00', '0.00', '0.00', '35', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('37', 'L2', '层', null, '10.00', '10.00', '0.00', '0.00', '35', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('38', 'L3', '层', null, '10.00', '10.00', '0.00', '0.00', '35', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('39', 'S3', '货架', null, '10.00', '30.00', '0.00', '0.00', '9', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('40', 'L1', '层', null, '10.00', '10.00', '0.00', '0.00', '39', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('41', 'L2', '层', null, '10.00', '10.00', '0.00', '0.00', '39', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('42', 'L3', '层', null, '10.00', '10.00', '0.00', '0.00', '39', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('43', 'S4', '货架', null, '10.00', '30.00', '0.00', '0.00', '3', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('44', 'L1', '层', null, '10.00', '10.00', '0.00', '0.00', '43', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('45', 'L2', '层', null, '10.00', '10.00', '0.00', '0.00', '43', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('46', 'L3', '层', null, '10.00', '10.00', '0.00', '0.00', '43', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('47', 'R3', '库区', '', '200.00', '0.00', '0.00', '0.00', '2', '2', '1', '1');
INSERT INTO `warehouse` VALUES ('48', 'R4', '库区', '', '200.00', '30.00', '0.00', '0.00', '2', '2', '1', '1');
INSERT INTO `warehouse` VALUES ('1', null, '所有', null, null, null, '0.00', '0.00', '0', null, '1', '1');
INSERT INTO `warehouse` VALUES ('49', 'S1', '货架', null, '10.00', '30.00', '0.00', '0.00', '47', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('50', 'L1', '层', null, '10.00', '10.00', '0.00', '0.00', '49', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('51', 'L2', '层', null, '10.00', '10.00', '0.00', '0.00', '49', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('52', 'L3', '层', null, '10.00', '10.00', '0.00', '0.00', '49', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('53', 'S1', '货架', null, '10.00', '30.00', '0.00', '0.00', '48', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('54', 'L1', '层', null, '10.00', '10.00', '0.00', '0.00', '53', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('55', 'L2', '层', null, '10.00', '10.00', '0.00', '0.00', '53', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('56', 'L3', '层', null, '10.00', '10.00', '0.00', '0.00', '53', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('126', 'L1', '层', null, '10.00', '10.00', '0.00', '0.00', '125', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('127', 'L2', '层', null, '10.00', '10.00', '0.00', '0.00', '125', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('57', 'W2', '普通仓库', '山东省济南市商河县怀仁镇', '1000.00', '240.00', '27.00', '11.25', '1', '1', '1', '1');
INSERT INTO `warehouse` VALUES ('58', 'G1', '废品库', '山东省济南市商河县怀仁镇', '500.00', '500.00', '66.00', '13.20', '1', '1', '1', '1');
INSERT INTO `warehouse` VALUES ('59', 'R1', '库区', '', '200.00', '150.00', '103.00', '68.67', '8', '2', '1', '1');
INSERT INTO `warehouse` VALUES ('60', 'R2', '库区', '', '200.00', '180.00', '36.00', '20.00', '8', '2', '1', '1');
INSERT INTO `warehouse` VALUES ('61', 'R3', '库区', '', '200.00', '30.00', '0.00', '0.00', '8', '2', '1', '1');
INSERT INTO `warehouse` VALUES ('62', 'S1', '货架', null, '10.00', '30.00', '30.00', '100.00', '59', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('63', 'L1', '层', null, '10.00', '10.00', '10.00', '100.00', '62', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('64', 'L2', '层', null, '10.00', '10.00', '10.00', '100.00', '62', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('65', 'L3', '层', null, '10.00', '10.00', '10.00', '100.00', '62', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('66', 'S1', '货架', null, '10.00', '30.00', '27.00', '90.00', '60', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('67', 'L1', '层', null, '10.00', '10.00', '9.00', '90.00', '66', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('68', 'L2', '层', null, '10.00', '10.00', '10.00', '100.00', '66', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('69', 'L3', '层', null, '10.00', '10.00', '8.00', '80.00', '66', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('70', 'S1', '货架', null, '10.00', '30.00', '0.00', '0.00', '61', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('71', 'L1', '层', null, '10.00', '10.00', '0.00', '0.00', '70', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('72', 'L2', '层', null, '10.00', '10.00', '0.00', '0.00', '70', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('73', 'L3', '层', null, '10.00', '10.00', '0.00', '0.00', '70', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('74', 'R1', '库区', '', '200.00', '120.00', '9.00', '7.50', '57', '2', '1', '1');
INSERT INTO `warehouse` VALUES ('75', 'S1', '货架', null, '10.00', '30.00', '9.00', '30.00', '74', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('76', 'L1', '层', null, '10.00', '10.00', '9.00', '90.00', '75', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('77', 'L2', '层', null, '10.00', '10.00', '0.00', '0.00', '75', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('78', 'L3', '层', null, '10.00', '10.00', '0.00', '0.00', '75', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('124', 'L3', '层', null, '10.00', '10.00', '3.00', '30.00', '121', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('125', 'S5', '货架', null, '10.00', '30.00', '10.00', '33.33', '59', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('123', 'L2', '层', null, '10.00', '10.00', '10.00', '100.00', '121', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('122', 'L1', '层', null, '10.00', '10.00', '10.00', '100.00', '121', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('121', 'S4', '货架', null, '10.00', '30.00', '23.00', '76.67', '59', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('120', 'L3', '层', null, '10.00', '10.00', '10.00', '100.00', '117', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('119', 'L2', '层', null, '10.00', '10.00', '10.00', '100.00', '117', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('118', 'L1', '层', null, '10.00', '10.00', '10.00', '100.00', '117', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('117', 'S3', '货架', null, '10.00', '30.00', '30.00', '100.00', '59', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('84', 'R2', '库区', '', '200.00', '120.00', '18.00', '15.00', '57', '2', '1', '1');
INSERT INTO `warehouse` VALUES ('85', 'S1', '货架', null, '10.00', '30.00', '18.00', '60.00', '84', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('86', 'L1', '层', null, '10.00', '10.00', '10.00', '100.00', '85', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('87', 'L2', '层', null, '10.00', '10.00', '8.00', '80.00', '85', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('88', 'L3', '层', null, '10.00', '10.00', '0.00', '0.00', '85', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('89', 'S2', '货架', null, '10.00', '30.00', '0.00', '0.00', '84', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('90', 'L1', '层', null, '10.00', '10.00', '0.00', '0.00', '89', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('91', 'L2', '层', null, '10.00', '10.00', '0.00', '0.00', '89', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('92', 'L3', '层', null, '10.00', '10.00', '0.00', '0.00', '89', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('93', 'S3', '货架', null, '10.00', '30.00', '0.00', '0.00', '84', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('94', 'L1', '层', null, '10.00', '10.00', '0.00', '0.00', '93', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('95', 'L2', '层', null, '10.00', '10.00', '0.00', '0.00', '93', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('96', 'L3', '层', null, '10.00', '10.00', '0.00', '0.00', '93', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('97', 'S4', '货架', null, '10.00', '30.00', '0.00', '0.00', '84', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('98', 'L1', '层', null, '10.00', '10.00', '0.00', '0.00', '97', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('99', 'L2', '层', null, '10.00', '10.00', '0.00', '0.00', '97', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('100', 'L3', '层', null, '10.00', '10.00', '0.00', '0.00', '97', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('101', 'S2', '货架', null, '10.00', '30.00', '0.00', '0.00', '74', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('102', 'L1', '层', null, '10.00', '10.00', '0.00', '0.00', '101', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('103', 'L2', '层', null, '10.00', '10.00', '0.00', '0.00', '101', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('104', 'L3', '层', null, '10.00', '10.00', '0.00', '0.00', '101', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('105', 'S3', '货架', null, '10.00', '30.00', '0.00', '0.00', '74', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('106', 'L1', '层', null, '10.00', '10.00', '0.00', '0.00', '105', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('107', 'L2', '层', null, '10.00', '10.00', '0.00', '0.00', '105', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('108', 'L3', '层', null, '10.00', '10.00', '0.00', '0.00', '105', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('109', 'S4', '货架', null, '10.00', '30.00', '0.00', '0.00', '74', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('110', 'L1', '层', null, '10.00', '10.00', '0.00', '0.00', '109', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('111', 'L2', '层', null, '10.00', '10.00', '0.00', '0.00', '109', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('112', 'L3', '层', null, '10.00', '10.00', '0.00', '0.00', '109', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('116', 'L3', '层', null, '10.00', '10.00', '10.00', '100.00', '113', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('115', 'L2', '层', null, '10.00', '10.00', '0.00', '0.00', '113', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('114', 'L1', '层', null, '10.00', '10.00', '0.00', '0.00', '113', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('113', 'S2', '货架', null, '10.00', '30.00', '10.00', '33.33', '59', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('128', 'L3', '层', null, '10.00', '10.00', '10.00', '100.00', '125', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('129', 'S2', '货架', null, '10.00', '30.00', '9.00', '30.00', '60', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('130', 'L1', '层', null, '10.00', '10.00', '9.00', '90.00', '129', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('131', 'L2', '层', null, '10.00', '10.00', '0.00', '0.00', '129', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('132', 'L3', '层', null, '10.00', '10.00', '0.00', '0.00', '129', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('133', 'S4', '货架', null, '10.00', '30.00', '0.00', '0.00', '60', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('134', 'L1', '层', null, '10.00', '10.00', '0.00', '0.00', '133', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('135', 'L2', '层', null, '10.00', '10.00', '0.00', '0.00', '133', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('136', 'L3', '层', null, '10.00', '10.00', '0.00', '0.00', '133', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('137', 'S5', '货架', null, '10.00', '30.00', '0.00', '0.00', '60', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('138', 'L1', '层', null, '10.00', '10.00', '0.00', '0.00', '137', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('139', 'L2', '层', null, '10.00', '10.00', '0.00', '0.00', '137', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('140', 'L3', '层', null, '10.00', '10.00', '0.00', '0.00', '137', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('141', 'S3', '货架', null, '10.00', '30.00', '0.00', '0.00', '60', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('142', 'L1', '层', null, '10.00', '10.00', '0.00', '0.00', '141', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('143', 'L2', '层', null, '10.00', '10.00', '0.00', '0.00', '141', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('144', 'L3', '层', null, '10.00', '10.00', '0.00', '0.00', '141', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('145', 'S6', '货架', null, '10.00', '30.00', '0.00', '0.00', '60', '3', '3', '1');
INSERT INTO `warehouse` VALUES ('146', 'L1', '层', null, '10.00', '10.00', '0.00', '0.00', '145', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('147', 'L2', '层', null, '10.00', '10.00', '0.00', '0.00', '145', '4', '1', '1');
INSERT INTO `warehouse` VALUES ('148', 'L3', '层', null, '10.00', '10.00', '0.00', '0.00', '145', '4', '1', '1');

-- ----------------------------
-- Table structure for `warehouse_goods`
-- ----------------------------
DROP TABLE IF EXISTS `warehouse_goods`;
CREATE TABLE `warehouse_goods` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `wid` int(32) DEFAULT NULL,
  `gid` int(32) DEFAULT NULL,
  `used` double(64,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of warehouse_goods
-- ----------------------------
INSERT INTO `warehouse_goods` VALUES ('42', '87', '35', '8');
INSERT INTO `warehouse_goods` VALUES ('41', '86', '35', '10');
INSERT INTO `warehouse_goods` VALUES ('40', '69', '34', '8');
INSERT INTO `warehouse_goods` VALUES ('39', '68', '34', '10');
INSERT INTO `warehouse_goods` VALUES ('38', '67', '33', '9');
INSERT INTO `warehouse_goods` VALUES ('37', '116', '32', '10');
INSERT INTO `warehouse_goods` VALUES ('36', '118', '32', '10');
INSERT INTO `warehouse_goods` VALUES ('35', '119', '32', '10');
INSERT INTO `warehouse_goods` VALUES ('34', '120', '32', '10');
INSERT INTO `warehouse_goods` VALUES ('44', '130', '37', '9');
INSERT INTO `warehouse_goods` VALUES ('43', '76', '36', '9');
INSERT INTO `warehouse_goods` VALUES ('46', '122', '38', '10');
INSERT INTO `warehouse_goods` VALUES ('45', '123', '38', '10');
INSERT INTO `warehouse_goods` VALUES ('29', '124', '29', '3');
INSERT INTO `warehouse_goods` VALUES ('28', '128', '28', '10');
INSERT INTO `warehouse_goods` VALUES ('25', '65', '26', '10');
INSERT INTO `warehouse_goods` VALUES ('24', '64', '26', '10');
INSERT INTO `warehouse_goods` VALUES ('23', '63', '26', '10');

-- ----------------------------
-- Table structure for `warehouse_transition`
-- ----------------------------
DROP TABLE IF EXISTS `warehouse_transition`;
CREATE TABLE `warehouse_transition` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `lid` int(32) DEFAULT NULL,
  `tid` int(32) DEFAULT NULL,
  `used` double(64,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of warehouse_transition
-- ----------------------------
INSERT INTO `warehouse_transition` VALUES ('73', '7', '19', '6.00');
INSERT INTO `warehouse_transition` VALUES ('72', '30', '18', '10.00');
INSERT INTO `warehouse_transition` VALUES ('71', '29', '18', '10.00');
INSERT INTO `warehouse_transition` VALUES ('70', '28', '18', '10.00');
INSERT INTO `warehouse_transition` VALUES ('69', '24', '17', '10.00');
INSERT INTO `warehouse_transition` VALUES ('68', '6', '16', '10.00');
