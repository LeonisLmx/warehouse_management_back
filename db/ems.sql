/*
 Navicat Premium Data Transfer
 Date: 04/07/2023 10:05:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for client
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `name` varchar(64) DEFAULT NULL,
                          `companyName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                          `landlinePhone` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '座机',
                          `phone` varchar(64) DEFAULT NULL COMMENT '运单号',
                          `cardNumber` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '身份证号码',
                          `address` varchar(128) DEFAULT NULL,
                          `postCode` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮政编码',
                          `email` varchar(100) DEFAULT NULL COMMENT 'email',
                          `note` varchar(128) DEFAULT NULL,
                          `enabled` tinyint(1) DEFAULT '1',
                          `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
                          `updateTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of client
-- ----------------------------
BEGIN;
INSERT INTO `client` VALUES (1, '庞老板', '百度', '086-12345', '17863671111', '321231243252341231', '123', '224000', '123@email.com', '职业老赖', 1, '2023-06-20 11:10:56', '2023-06-20 11:35:36');
INSERT INTO `client` VALUES (2, '于老板', NULL, NULL, '17863671111', NULL, '456', NULL, NULL, '职业老赖', 1, '2023-06-20 11:10:56', '2023-06-20 11:11:10');
INSERT INTO `client` VALUES (3, '张老板', NULL, NULL, '17863671111', NULL, '黑龙江省双鸭山市集贤县偶或', NULL, NULL, '信誉不错', 0, '2023-06-20 11:10:56', '2023-06-20 11:11:10');
INSERT INTO `client` VALUES (4, '史老板', NULL, NULL, '17863671111', NULL, '123', NULL, NULL, '职业老赖', 1, '2023-06-20 11:10:56', '2023-06-20 11:11:10');
INSERT INTO `client` VALUES (5, '朱老板', NULL, NULL, '17863671111', NULL, '123', NULL, NULL, '职业老赖...', 1, '2023-06-20 11:10:56', '2023-06-20 11:11:10');
INSERT INTO `client` VALUES (6, '测试角色', NULL, NULL, '17863671111', NULL, '123', NULL, NULL, '信誉挺好', 0, '2023-06-20 11:10:56', '2023-06-20 11:11:10');
INSERT INTO `client` VALUES (7, '明雨哥', NULL, NULL, '17863671111', NULL, '123', NULL, NULL, '很讲诚信', 1, '2023-06-20 11:10:56', '2023-06-20 11:11:10');
INSERT INTO `client` VALUES (8, '谢老板', NULL, NULL, '17863671111', NULL, '123', NULL, NULL, '123', 1, '2023-06-20 11:10:56', '2023-06-20 11:11:10');
INSERT INTO `client` VALUES (9, '张三', NULL, NULL, '17863671111', NULL, '123', '224001', NULL, '', 1, '2023-06-20 11:10:56', '2023-06-21 18:04:10');
INSERT INTO `client` VALUES (10, '小麻', NULL, NULL, '17863671111', NULL, '山东省聊城市茌平县哈哈哈哈', NULL, NULL, '哈哈哈哈', 1, '2023-06-20 11:10:56', '2023-06-20 11:11:10');
INSERT INTO `client` VALUES (11, '王二', '阿里巴巴', '021-123456', '18911111111', '319241188310283984', '副书记的地方卡瓦就', '224000', '123456@mail.com', '对的撒多哇', 0, '2023-06-20 11:42:50', '2023-06-20 11:42:50');
INSERT INTO `client` VALUES (12, '王二', '阿里巴巴', '021-123456', '18911111111', '319241188310283984', '12345', '224000', '123456@mail.com', '对的撒多哇', 1, '2023-06-20 11:42:50', '2023-06-20 11:42:50');
COMMIT;

-- ----------------------------
-- Table structure for client_order
-- ----------------------------
DROP TABLE IF EXISTS `client_order`;
CREATE TABLE `client_order` (
                                `id` int NOT NULL AUTO_INCREMENT,
                                `cid` int DEFAULT NULL,
                                `oid` int DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                KEY `deleo` (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of client_order
-- ----------------------------
BEGIN;
INSERT INTO `client_order` VALUES (3, 2, 7);
INSERT INTO `client_order` VALUES (4, 2, 8);
INSERT INTO `client_order` VALUES (5, 1, 9);
INSERT INTO `client_order` VALUES (6, 7, 10);
INSERT INTO `client_order` VALUES (7, 5, 11);
INSERT INTO `client_order` VALUES (8, 5, 12);
INSERT INTO `client_order` VALUES (9, 4, 13);
INSERT INTO `client_order` VALUES (10, 8, 14);
INSERT INTO `client_order` VALUES (11, 9, 15);
INSERT INTO `client_order` VALUES (12, 1, 16);
INSERT INTO `client_order` VALUES (13, 1, 17);
INSERT INTO `client_order` VALUES (14, 2, 18);
INSERT INTO `client_order` VALUES (15, 5, 19);
INSERT INTO `client_order` VALUES (16, 9, 24);
INSERT INTO `client_order` VALUES (17, 9, 25);
INSERT INTO `client_order` VALUES (18, 10, 27);
COMMIT;

-- ----------------------------
-- Table structure for finance_detail
-- ----------------------------
DROP TABLE IF EXISTS `finance_detail`;
CREATE TABLE `finance_detail` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `action` int DEFAULT NULL COMMENT '财务操作',
                                  `amount` bigint DEFAULT NULL,
                                  `role` int DEFAULT NULL COMMENT '关联角色',
                                  `createTime` date DEFAULT NULL,
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of finance_detail
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for good_category
-- ----------------------------
DROP TABLE IF EXISTS `good_category`;
CREATE TABLE `good_category` (
                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                 `parentId` bigint DEFAULT NULL COMMENT '父级id',
                                 `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of good_category
-- ----------------------------
BEGIN;
INSERT INTO `good_category` VALUES (1, 0, '酒类');
INSERT INTO `good_category` VALUES (2, 0, '化妆品类');
INSERT INTO `good_category` VALUES (3, 0, '车');
INSERT INTO `good_category` VALUES (4, 3, '比亚迪');
INSERT INTO `good_category` VALUES (5, 3, '小鹏');
INSERT INTO `good_category` VALUES (6, 1, '茅台');
INSERT INTO `good_category` VALUES (7, 1, '五粮液');
COMMIT;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `num` varchar(64) DEFAULT NULL,
                         `orderNum` varchar(64) DEFAULT NULL,
                         `name` varchar(64) DEFAULT NULL,
                         `codeName` varchar(10) DEFAULT NULL,
                         `count` double(64,0) DEFAULT NULL,
  `type` int DEFAULT NULL,
  `clientId` int DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `operator` varchar(64) DEFAULT NULL,
  `expCode` varchar(64) DEFAULT NULL,
  `expNo` varchar(64) DEFAULT NULL,
  `ship` int DEFAULT '0',
  `remainCount` double(64,0) DEFAULT NULL,
  `substationId` bigint DEFAULT NULL,
  `supplierId` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of goods
-- ----------------------------
BEGIN;
INSERT INTO `goods` VALUES (27, 'G202003312112410001', 'N202003181540180001', 'N95口罩', 'N95口罩', 65, 1, 1, '2020-03-31 21:12:41', '庞庆舟', 'N95', 'YT4347941418575', 1, 65, 7, 2);
INSERT INTO `goods` VALUES (26, 'G202003312035570001', '', '茶叶', '茶叶', 990, 1, 0, '2020-03-31 20:35:57', '庞庆舟', 'TEA', NULL, 0, 490, 8, 2);
INSERT INTO `goods` VALUES (28, 'G202003312141550001', '', '茶叶', '茶叶', 90, 1, 0, '2020-03-31 21:41:55', '庞庆舟', 'TEA', NULL, 0, 90, 7, 1);
INSERT INTO `goods` VALUES (29, 'G202003312211550001', '', '茶叶', '茶叶', 3, 1, 0, '2020-03-31 22:11:55', '庞庆舟', 'TEA', NULL, 0, 3, 8, 2);
INSERT INTO `goods` VALUES (30, 'G202003312224300001', 'N202003201455260001', '护目镜', '护目镜', 90, 1, 4, '2020-03-31 22:24:30', '庞庆舟', 'YTO', 'YT2037026334999', 1, 90, 7, 2);
INSERT INTO `goods` VALUES (31, 'G202003312227490001', 'N202003201624230001', '小矮胖猫', '小矮胖猫', 88, 1, 8, '2020-03-31 22:27:49', '庞庆舟', 'YTO', 'YT2047017381987', 0, 88, 8, 1);
INSERT INTO `goods` VALUES (32, 'G202003312235030001', 'N202003181349100001', '消毒水', '消毒水', 1450, 1, 2, '2020-03-31 22:35:03', '庞庆舟', 'WAT', NULL, 0, 1450, 2, 1);
INSERT INTO `goods` VALUES (33, 'G202003312239250001', 'N202003181546440001', '青岛啤酒', '青岛啤酒', 90, 1, 5, '2020-03-31 22:39:25', '庞庆舟', 'BEA', NULL, 0, 90, 2, 1);
INSERT INTO `goods` VALUES (34, 'G202003312243360001', 'N202003181546050001', '普通医用口罩', '普通医用口罩', 55, 1, 5, '2020-03-31 22:43:36', '庞庆舟', 'COM', NULL, 0, 55, 7, 2);
INSERT INTO `goods` VALUES (35, 'G202004011030230001', '', '哈默', '哈默', 340, 1, 0, '2020-04-01 10:30:23', '庞庆舟', 'TEST', NULL, 0, 340, 8, 1);
INSERT INTO `goods` VALUES (36, 'G202004071020550001', 'N202004052106310001', '百世可乐', '百世可乐', 99, 1, 1, '2020-04-07 10:20:55', '庞庆舟', 'COCO', NULL, 0, 99, 1, 2);
INSERT INTO `goods` VALUES (37, 'G202004092003550001', '', '朱明轩', '朱明轩', 9, 1, 0, '2020-04-09 20:03:55', '庞庆舟', 'NAME', NULL, 0, 9, 2, 1);
INSERT INTO `goods` VALUES (38, 'G202004151811400001', 'N202003181423460001', '消毒液', '消毒液', 490, 1, 2, '2020-04-15 18:11:40', '庞庆舟', 'DROG', NULL, 0, 490, 4, 1);
INSERT INTO `goods` VALUES (39, NULL, 'N212938124810239', '比亚迪', 'BYD', 10, NULL, NULL, '2023-06-25 13:27:00', 'admin', NULL, NULL, 0, 10, 5, 1);
INSERT INTO `goods` VALUES (40, NULL, 'N2381234012930', '小鹏', 'XP', 100, NULL, NULL, '2023-06-25 13:29:55', 'admin', NULL, NULL, 0, 100, 8, 1);
INSERT INTO `goods` VALUES (41, NULL, 'N2054132165212313', '林肯', 'LINKO', 10, 1, NULL, '2023-07-01 12:59:31', 'admin', NULL, NULL, 0, 10, 7, 1);
INSERT INTO `goods` VALUES (42, NULL, 'N001', '电脑', 'CUM', 100, 1, NULL, '2023-07-01 13:27:20', 'admin', NULL, NULL, 0, 100, 2, 2);
INSERT INTO `goods` VALUES (43, NULL, 'N002', '键盘', 'KEY', 200, 1, NULL, '2023-07-01 13:30:10', 'admin', NULL, NULL, 0, 200, 3, 1);
INSERT INTO `goods` VALUES (44, NULL, 'N003', '矿泉水', 'WATER', 10000, 1, NULL, '2023-07-01 13:31:06', 'admin', NULL, NULL, 0, 10000, 5, 2);
INSERT INTO `goods` VALUES (45, NULL, 'N004', '面膜', 'FACE', 500, 1, NULL, '2023-07-01 13:32:13', 'admin', NULL, NULL, 0, 500, 4, 1);
INSERT INTO `goods` VALUES (48, NULL, 'N202307011817060001', '飞天茅台', '飞天', 100, 1, NULL, '2023-07-01 17:15:32', '庞庆舟', NULL, NULL, 0, 100, 9, 2);
COMMIT;

-- ----------------------------
-- Table structure for invoice_manage
-- ----------------------------
DROP TABLE IF EXISTS `invoice_manage`;
CREATE TABLE `invoice_manage` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                  `price` decimal(10,0) DEFAULT NULL,
                                  `description` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                  `applyTime` datetime DEFAULT NULL,
                                  `status` int DEFAULT NULL,
                                  `date` datetime DEFAULT CURRENT_TIMESTAMP,
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of invoice_manage
-- ----------------------------
BEGIN;
INSERT INTO `invoice_manage` VALUES (1, '张三', 1000, '新购：茅台-100瓶', '2023-03-10 10:00:01', 2, '2023-07-03 16:39:02');
INSERT INTO `invoice_manage` VALUES (2, '李四', 100, '退货：比亚迪-5台', '2023-05-01 14:00:00', 0, '2023-07-03 16:41:28');
INSERT INTO `invoice_manage` VALUES (3, '特朗普', 500, '换货：东风4号-1枚', '2023-06-07 01:00:00', 2, '2023-07-03 16:42:03');
INSERT INTO `invoice_manage` VALUES (4, '印度', 1000, '新购：航母-1个', '2023-05-12 12:00:00', -1, '2023-07-03 16:49:27');
INSERT INTO `invoice_manage` VALUES (5, '贵州茅台酒厂', 10000, '供应商供货：飞天茅台-1999瓶', '2023-01-01 01:00:00', 1, '2023-07-03 16:50:18');
COMMIT;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `pattern` varchar(64) DEFAULT NULL,
                        `path` varchar(64) DEFAULT NULL,
                        `level` int DEFAULT NULL,
                        `name` varchar(32) DEFAULT NULL,
                        `icon` varchar(64) DEFAULT NULL,
                        `parentId` int DEFAULT NULL,
                        `enable` tinyint(1) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of menu
-- ----------------------------
BEGIN;
INSERT INTO `menu` VALUES (2, '/', '/home', 0, '用户管理', 'el-icon-user-solid', 1, 1);
INSERT INTO `menu` VALUES (3, '/', '/home', 0, '权限管理', 'el-icon-s-platform', 1, 1);
INSERT INTO `menu` VALUES (6, '/employee/userList/**', '/userList', 1, '用户列表', 'el-icon-menu', 2, 1);
INSERT INTO `menu` VALUES (7, '/staff/roleList/**', '/roleList', 1, '角色列表', 'el-icon-menu', 3, 1);
INSERT INTO `menu` VALUES (8, '/staff/powList/**', '/powList', 1, '权限列表', 'el-icon-menu', 3, 1);
INSERT INTO `menu` VALUES (11, '/employee/updateEnabled/**', '/userList', 2, '更改用户状态', NULL, 6, 0);
INSERT INTO `menu` VALUES (12, '/employee/addUser/**', '/userList', 2, '添加用户', NULL, 6, 0);
INSERT INTO `menu` VALUES (13, '/employee/updateUser/**', '/userList', 2, '更改用户', NULL, 6, 0);
INSERT INTO `menu` VALUES (14, '/employee/deleteUser/**', '/userList', 2, '删除用户', NULL, 6, 0);
INSERT INTO `menu` VALUES (15, '/staff/addRole/**', '/roleList', 2, '添加角色', NULL, 7, 0);
INSERT INTO `menu` VALUES (16, '/staff/editRole/**', '/roleList', 2, '更改角色', NULL, 7, 0);
INSERT INTO `menu` VALUES (17, '/staff/deleteRole/**', '/roleList', 2, '删除角色', NULL, 7, 0);
INSERT INTO `menu` VALUES (18, '/employee/allRole/**', '/userList', 2, '分配角色', NULL, 6, 0);
INSERT INTO `menu` VALUES (19, '/staff/deletePow/**', '/roleList', 2, '删除角色权限', NULL, 7, 0);
INSERT INTO `menu` VALUES (20, '/staff/assPow/**', '/roleList', 2, '分配权限', NULL, 7, 0);
INSERT INTO `menu` VALUES (21, '/staff/changeMenuEnable/**', '/powList', 2, '菜单状态', NULL, 8, 0);
INSERT INTO `menu` VALUES (22, '/staff/addPow/**', '/powList', 2, '添加权限（菜单）', NULL, 8, 0);
INSERT INTO `menu` VALUES (25, '/staff/editPow/**', '/powList', 2, '修改权限', NULL, 8, 0);
INSERT INTO `menu` VALUES (27, '/staff/deletePow/**', '/powList', 2, '删除权限', NULL, 8, 0);
INSERT INTO `menu` VALUES (30, '/system/druidSql/**', '/systemSql', 2, '日志记录', NULL, 29, 0);
INSERT INTO `menu` VALUES (31, '/', '/home', 0, '配送中心', 'el-icon-s-order', 1, 1);
INSERT INTO `menu` VALUES (32, '/order/orderList/**', '/orderList', 1, '订单列表', 'el-icon-menu', 41, 1);
INSERT INTO `menu` VALUES (33, '/', '/home', 0, '调度中心', 'el-icon-s-claim', 1, 1);
INSERT INTO `menu` VALUES (35, '/', '/home', 0, '库房管理', 'el-icon-s-home', 1, 1);
INSERT INTO `menu` VALUES (36, '/goods/enterList/**', '/enter', 1, '入库管理', 'el-icon-menu', 35, 1);
INSERT INTO `menu` VALUES (37, '/goods/enterList/**', '/out', 1, '出库管理', 'el-icon-menu', 35, 1);
INSERT INTO `menu` VALUES (39, '/', '/home', 0, '财务管理', 'el-icon-s-data', 1, 1);
INSERT INTO `menu` VALUES (41, '/', '/home', 0, '客户服务中心', 'el-icon-s-custom', 1, 1);
INSERT INTO `menu` VALUES (42, '/client/clientList/**', '/clientList', 1, '客户管理', 'el-icon-menu', 41, 1);
INSERT INTO `menu` VALUES (43, '/client/addClient/**', '/clientList', 2, '添加客户', '', 42, 0);
INSERT INTO `menu` VALUES (44, '/client/getOrderByCid/**', '/clientList', 2, '查看订单', '', 42, 0);
INSERT INTO `menu` VALUES (45, '/client/editClient/**', '/clientList', 2, '修改客户信息', '', 42, 0);
INSERT INTO `menu` VALUES (46, '/client/enterBlacklist/**', '/clientList', 2, '加入黑名单', '', 42, 0);
INSERT INTO `menu` VALUES (47, '/client/getBlacklist/**', '/clientList', 2, '黑名单列表', '', 42, 0);
INSERT INTO `menu` VALUES (48, '/order/addOrder/**', '/orderList', 2, '添加订单', '', 32, 0);
INSERT INTO `menu` VALUES (49, '/order/editOrder/**', '/orderList', 2, '修改订单', '', 32, 0);
INSERT INTO `menu` VALUES (50, '/order/deleteOrder/**', '/orderList', 2, '删除订单', '', 32, 0);
INSERT INTO `menu` VALUES (51, '/output/addOutput/**', '/outputList', 2, '新建生产线', '', 34, 0);
INSERT INTO `menu` VALUES (52, '/output/updateOutputState/**', '/outputList', 2, '更改生产状态', '', 34, 0);
INSERT INTO `menu` VALUES (53, '/output/updateOutput/**', '/output', 2, '更新生产线信息', '', 34, 0);
INSERT INTO `menu` VALUES (54, '/output/deleteOutput/**', '/outputList', 2, '删除生产线', '', 34, 0);
INSERT INTO `menu` VALUES (55, '/output/insertOutput/**', '/outputList', 2, '新建生产', '', 34, 0);
INSERT INTO `menu` VALUES (56, '/order/orders/**', '/outputList', 2, '远程订单列表', '', 34, 0);
INSERT INTO `menu` VALUES (57, '/output/outputLog/**', '/outputList', 2, '工作日志', '', 34, 0);
INSERT INTO `menu` VALUES (58, '/output/workOutput/**', '/outputList', 2, '工作进度', '', 34, 0);
INSERT INTO `menu` VALUES (59, '/output/achieve/**', '/outputList', 2, '完成生产', '', 34, 0);
INSERT INTO `menu` VALUES (64, '/warehouse/myRegion/**', '/region', 2, '库区管理', '', 9, 0);
INSERT INTO `menu` VALUES (65, '/warehouse/myShelf/**', '/shelf', 2, '我的货架', '', 9, 0);
INSERT INTO `menu` VALUES (66, '/warehouse/addShelf/**', '/shelf', 2, '新建货架', '', 9, 0);
INSERT INTO `menu` VALUES (67, '/warehouse/transitions/**', '/myWarehouse', 2, '远程查询完成生产产品', '', 9, 0);
INSERT INTO `menu` VALUES (68, '/warehouse/position/**', '/myWarehouse', 2, '存储位置（级联选择器）', '', 9, 0);
INSERT INTO `menu` VALUES (69, '/warehouse/store/**', '/myWarehouse', 2, '待检库暂存', '', 9, 0);
INSERT INTO `menu` VALUES (70, '/transition/myTransition/**', '/transition', 1, '自动调度', 'el-icon-menu', 33, 1);
INSERT INTO `menu` VALUES (71, '/warehouse/getWarehouseId/**', '/enter', 2, '选择仓库', '', 36, 0);
INSERT INTO `menu` VALUES (72, '/warehouse/enter/**', '/enter', 2, '入库', '', 36, 0);
INSERT INTO `menu` VALUES (73, '/transition/transitionPosition/**', '/transition', 2, '存放位置（待检库）', '', 70, 0);
INSERT INTO `menu` VALUES (74, '/goods/goodsPosition/**', '/enter', 2, '存储位置（仓库）', '', 36, 0);
INSERT INTO `menu` VALUES (75, '/goods/ship/**', '/out', 2, '物流信息', '', 37, 0);
INSERT INTO `menu` VALUES (76, '/goods/out/**', '/out', 2, '出库', '', 37, 0);
INSERT INTO `menu` VALUES (78, '/transition/deleteTransition/**', '/transition', 2, '删除生产数据', '', 70, 0);
INSERT INTO `menu` VALUES (79, '/', '/home', 0, '分站管理', 'el-icon-menu', 1, 1);
INSERT INTO `menu` VALUES (80, '/substation/list/**', '/substationList', 1, '分站分布情况', 'el-icon-menu', 79, 1);
INSERT INTO `menu` VALUES (82, '/order/orderDetail/**', '/orderDetail', 1, '订单管理查询', 'el-icon-menu', 41, 1);
INSERT INTO `menu` VALUES (83, '/order/ManageOrderTable/**', '/manageOrderTable', 1, '员工订单情况', 'el-icon-menu', 41, 1);
INSERT INTO `menu` VALUES (84, '/schedule/HandSchedule/**', '/handSchedule', 1, '手动调度', 'el-icon-menu', 33, 1);
INSERT INTO `menu` VALUES (85, '/goods/enterList/**', '/check', 1, '仓库盘点', 'el-icon-menu', 35, 1);
INSERT INTO `menu` VALUES (86, '/substation/list/**', '/substationOrderManage', 1, '任务分配', 'el-icon-menu', 79, 1);
INSERT INTO `menu` VALUES (87, '/substation/list/**', '/printExpress', 1, '配送单打印', 'el-icon-menu', 79, 1);
INSERT INTO `menu` VALUES (88, '/substation/list/**', '/receipt', 1, '回执录入', 'el-icon-menu', 79, 1);
INSERT INTO `menu` VALUES (89, '/', '/home', 0, '配送中心管理', 'el-icon-s-platform', 1, 1);
INSERT INTO `menu` VALUES (90, '/express/list/**', '/goodManage', 1, '商品管理', 'el-icon-menu', 89, 1);
INSERT INTO `menu` VALUES (91, '/goods/enterList/**', '/centerWarehouse', 1, '中心库房', 'el-icon-menu', 35, 1);
INSERT INTO `menu` VALUES (92, '/express/list/**', '/supplierManage', 1, '供应商管理', 'el-icon-menu', 89, 1);
INSERT INTO `menu` VALUES (93, '/express/list/**', '/explainSubstation', 1, '分站配送分析', 'el-icon-menu', 89, 1);
INSERT INTO `menu` VALUES (94, '/express/list/**', '/explainSupplierOrders', 1, '订购分析', 'el-icon-menu', 89, 1);
INSERT INTO `menu` VALUES (95, '/data/report/**', '/settlementSupplier', 1, '供应商结算', 'el-icon-menu', 39, 1);
INSERT INTO `menu` VALUES (96, '/data/report/**', '/invoiceManage', 1, '发票管理', 'el-icon-menu', 39, 1);
INSERT INTO `menu` VALUES (97, '/data/report/**', '/substationSettlement', 1, '分站结算', 'el-icon-menu', 39, 1);
COMMIT;

-- ----------------------------
-- Table structure for menu_role
-- ----------------------------
DROP TABLE IF EXISTS `menu_role`;
CREATE TABLE `menu_role` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `mid` int DEFAULT NULL,
                             `rid` int DEFAULT NULL,
                             PRIMARY KEY (`id`),
                             KEY `dm` (`mid`),
                             KEY `dr` (`rid`),
                             CONSTRAINT `dm` FOREIGN KEY (`mid`) REFERENCES `menu` (`id`) ON DELETE CASCADE,
                             CONSTRAINT `dr` FOREIGN KEY (`rid`) REFERENCES `role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=773 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of menu_role
-- ----------------------------
BEGIN;
INSERT INTO `menu_role` VALUES (508, 41, 12);
INSERT INTO `menu_role` VALUES (509, 42, 12);
INSERT INTO `menu_role` VALUES (510, 47, 12);
INSERT INTO `menu_role` VALUES (511, 46, 12);
INSERT INTO `menu_role` VALUES (512, 45, 12);
INSERT INTO `menu_role` VALUES (513, 43, 12);
INSERT INTO `menu_role` VALUES (514, 44, 12);
INSERT INTO `menu_role` VALUES (515, 33, 12);
INSERT INTO `menu_role` VALUES (517, 51, 12);
INSERT INTO `menu_role` VALUES (518, 59, 12);
INSERT INTO `menu_role` VALUES (519, 58, 12);
INSERT INTO `menu_role` VALUES (520, 57, 12);
INSERT INTO `menu_role` VALUES (521, 56, 12);
INSERT INTO `menu_role` VALUES (522, 55, 12);
INSERT INTO `menu_role` VALUES (523, 54, 12);
INSERT INTO `menu_role` VALUES (524, 53, 12);
INSERT INTO `menu_role` VALUES (525, 52, 12);
INSERT INTO `menu_role` VALUES (526, 31, 12);
INSERT INTO `menu_role` VALUES (527, 32, 12);
INSERT INTO `menu_role` VALUES (528, 50, 12);
INSERT INTO `menu_role` VALUES (529, 49, 12);
INSERT INTO `menu_role` VALUES (530, 48, 12);
INSERT INTO `menu_role` VALUES (646, 36, 2);
INSERT INTO `menu_role` VALUES (647, 71, 2);
INSERT INTO `menu_role` VALUES (648, 72, 2);
INSERT INTO `menu_role` VALUES (649, 74, 2);
INSERT INTO `menu_role` VALUES (650, 37, 2);
INSERT INTO `menu_role` VALUES (651, 76, 2);
INSERT INTO `menu_role` VALUES (652, 75, 2);
INSERT INTO `menu_role` VALUES (654, 68, 2);
INSERT INTO `menu_role` VALUES (655, 69, 2);
INSERT INTO `menu_role` VALUES (656, 66, 2);
INSERT INTO `menu_role` VALUES (657, 65, 2);
INSERT INTO `menu_role` VALUES (658, 64, 2);
INSERT INTO `menu_role` VALUES (659, 67, 2);
INSERT INTO `menu_role` VALUES (660, 31, 2);
INSERT INTO `menu_role` VALUES (661, 32, 2);
INSERT INTO `menu_role` VALUES (662, 50, 2);
INSERT INTO `menu_role` VALUES (663, 49, 2);
INSERT INTO `menu_role` VALUES (664, 48, 2);
INSERT INTO `menu_role` VALUES (665, 33, 2);
INSERT INTO `menu_role` VALUES (667, 58, 2);
INSERT INTO `menu_role` VALUES (668, 57, 2);
INSERT INTO `menu_role` VALUES (669, 56, 2);
INSERT INTO `menu_role` VALUES (670, 55, 2);
INSERT INTO `menu_role` VALUES (671, 54, 2);
INSERT INTO `menu_role` VALUES (672, 53, 2);
INSERT INTO `menu_role` VALUES (673, 52, 2);
INSERT INTO `menu_role` VALUES (674, 59, 2);
INSERT INTO `menu_role` VALUES (675, 51, 2);
INSERT INTO `menu_role` VALUES (676, 70, 2);
INSERT INTO `menu_role` VALUES (677, 73, 2);
INSERT INTO `menu_role` VALUES (678, 41, 2);
INSERT INTO `menu_role` VALUES (679, 42, 2);
INSERT INTO `menu_role` VALUES (680, 47, 2);
INSERT INTO `menu_role` VALUES (681, 46, 2);
INSERT INTO `menu_role` VALUES (682, 45, 2);
INSERT INTO `menu_role` VALUES (683, 44, 2);
INSERT INTO `menu_role` VALUES (684, 43, 2);
INSERT INTO `menu_role` VALUES (685, 35, 2);
INSERT INTO `menu_role` VALUES (686, 36, 3);
INSERT INTO `menu_role` VALUES (687, 71, 3);
INSERT INTO `menu_role` VALUES (688, 72, 3);
INSERT INTO `menu_role` VALUES (689, 74, 3);
INSERT INTO `menu_role` VALUES (690, 37, 3);
INSERT INTO `menu_role` VALUES (691, 76, 3);
INSERT INTO `menu_role` VALUES (692, 75, 3);
INSERT INTO `menu_role` VALUES (694, 68, 3);
INSERT INTO `menu_role` VALUES (695, 69, 3);
INSERT INTO `menu_role` VALUES (696, 66, 3);
INSERT INTO `menu_role` VALUES (697, 65, 3);
INSERT INTO `menu_role` VALUES (698, 64, 3);
INSERT INTO `menu_role` VALUES (699, 67, 3);
INSERT INTO `menu_role` VALUES (700, 70, 3);
INSERT INTO `menu_role` VALUES (701, 73, 3);
INSERT INTO `menu_role` VALUES (702, 35, 3);
INSERT INTO `menu_role` VALUES (703, 33, 3);
INSERT INTO `menu_role` VALUES (704, 3, 1);
INSERT INTO `menu_role` VALUES (705, 7, 1);
INSERT INTO `menu_role` VALUES (706, 20, 1);
INSERT INTO `menu_role` VALUES (707, 19, 1);
INSERT INTO `menu_role` VALUES (708, 17, 1);
INSERT INTO `menu_role` VALUES (709, 16, 1);
INSERT INTO `menu_role` VALUES (710, 15, 1);
INSERT INTO `menu_role` VALUES (711, 8, 1);
INSERT INTO `menu_role` VALUES (712, 25, 1);
INSERT INTO `menu_role` VALUES (713, 22, 1);
INSERT INTO `menu_role` VALUES (714, 21, 1);
INSERT INTO `menu_role` VALUES (715, 27, 1);
INSERT INTO `menu_role` VALUES (718, 30, 1);
INSERT INTO `menu_role` VALUES (719, 31, 1);
INSERT INTO `menu_role` VALUES (720, 32, 1);
INSERT INTO `menu_role` VALUES (721, 48, 1);
INSERT INTO `menu_role` VALUES (722, 49, 1);
INSERT INTO `menu_role` VALUES (723, 50, 1);
INSERT INTO `menu_role` VALUES (724, 33, 1);
INSERT INTO `menu_role` VALUES (725, 70, 1);
INSERT INTO `menu_role` VALUES (726, 73, 1);
INSERT INTO `menu_role` VALUES (727, 78, 1);
INSERT INTO `menu_role` VALUES (729, 53, 1);
INSERT INTO `menu_role` VALUES (730, 52, 1);
INSERT INTO `menu_role` VALUES (731, 54, 1);
INSERT INTO `menu_role` VALUES (732, 55, 1);
INSERT INTO `menu_role` VALUES (733, 56, 1);
INSERT INTO `menu_role` VALUES (734, 57, 1);
INSERT INTO `menu_role` VALUES (735, 58, 1);
INSERT INTO `menu_role` VALUES (736, 59, 1);
INSERT INTO `menu_role` VALUES (737, 51, 1);
INSERT INTO `menu_role` VALUES (738, 35, 1);
INSERT INTO `menu_role` VALUES (740, 37, 1);
INSERT INTO `menu_role` VALUES (741, 75, 1);
INSERT INTO `menu_role` VALUES (742, 76, 1);
INSERT INTO `menu_role` VALUES (743, 36, 1);
INSERT INTO `menu_role` VALUES (744, 71, 1);
INSERT INTO `menu_role` VALUES (745, 72, 1);
INSERT INTO `menu_role` VALUES (746, 74, 1);
INSERT INTO `menu_role` VALUES (748, 64, 1);
INSERT INTO `menu_role` VALUES (749, 65, 1);
INSERT INTO `menu_role` VALUES (750, 66, 1);
INSERT INTO `menu_role` VALUES (751, 67, 1);
INSERT INTO `menu_role` VALUES (752, 68, 1);
INSERT INTO `menu_role` VALUES (753, 69, 1);
INSERT INTO `menu_role` VALUES (754, 39, 1);
INSERT INTO `menu_role` VALUES (756, 41, 1);
INSERT INTO `menu_role` VALUES (757, 42, 1);
INSERT INTO `menu_role` VALUES (758, 43, 1);
INSERT INTO `menu_role` VALUES (759, 44, 1);
INSERT INTO `menu_role` VALUES (760, 45, 1);
INSERT INTO `menu_role` VALUES (761, 46, 1);
INSERT INTO `menu_role` VALUES (762, 47, 1);
INSERT INTO `menu_role` VALUES (764, 79, 1);
INSERT INTO `menu_role` VALUES (765, 80, 1);
INSERT INTO `menu_role` VALUES (766, 2, 1);
INSERT INTO `menu_role` VALUES (767, 6, 1);
INSERT INTO `menu_role` VALUES (768, 13, 1);
INSERT INTO `menu_role` VALUES (769, 18, 1);
INSERT INTO `menu_role` VALUES (770, 14, 1);
INSERT INTO `menu_role` VALUES (771, 12, 1);
INSERT INTO `menu_role` VALUES (772, 11, 1);
COMMIT;

-- ----------------------------
-- Table structure for order_list
-- ----------------------------
DROP TABLE IF EXISTS `order_list`;
CREATE TABLE `order_list` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `goodsId` bigint DEFAULT NULL COMMENT '商品名称',
                              `orderNum` varchar(128) DEFAULT NULL COMMENT '订单编号',
                              `price` double(64,0) DEFAULT NULL COMMENT '商品价格',
  `count` int DEFAULT NULL COMMENT '商品数量',
  `pay` tinyint(1) DEFAULT NULL COMMENT '是否支付',
  `date` datetime DEFAULT NULL COMMENT '下单时间',
  `transport` tinyint(1) DEFAULT NULL COMMENT '是否发货',
  `orderState` int DEFAULT '1' COMMENT '物流信息',
  `util` varchar(20) DEFAULT NULL COMMENT '计量单位',
  `invoiceEnabled` tinyint(1) DEFAULT '0' COMMENT '发票信息',
  `relationOrderId` varchar(128) DEFAULT NULL COMMENT '关联订单Id',
  `orderType` int DEFAULT NULL COMMENT '订单类型',
  `clientId` bigint DEFAULT NULL COMMENT '客户id',
  `deliverTime` datetime DEFAULT NULL COMMENT '送货日期',
  `deliverySubstation` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '投递分站',
  `goodsContent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品说明',
  `content` varchar(255) DEFAULT NULL COMMENT '备注',
  `reason` varchar(255) DEFAULT NULL COMMENT '理由',
  `operateId` int DEFAULT NULL COMMENT '操作客服id',
  `substationId` bigint DEFAULT NULL COMMENT '所属分站',
  `expressName` varchar(50) DEFAULT NULL COMMENT '快递员',
  `customerSatisfaction` bigint DEFAULT NULL COMMENT '客户满意度',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of order_list
-- ----------------------------
BEGIN;
INSERT INTO `order_list` VALUES (7, 32, 'N202003181349100001', 4500, 1500, 1, '2020-03-18 13:49:10', 0, 9, '个', 0, NULL, 1, 9, NULL, NULL, NULL, NULL, NULL, 1, 7, '拜登', 99, '2023-07-02 19:00:16');
INSERT INTO `order_list` VALUES (8, 38, 'N202003181423460001', 2000, 500, 1, '2020-03-18 14:23:46', 0, 10, '个', 0, NULL, 2, 9, NULL, NULL, NULL, NULL, NULL, 34, 8, '特朗普', 60, '2023-07-02 19:00:16');
INSERT INTO `order_list` VALUES (9, 27, 'N202003181540180001', 666, 66, 1, '2020-03-18 15:40:18', 1, 8, '个', 0, NULL, 3, 9, NULL, NULL, NULL, NULL, NULL, 1, 7, '普京大帝', NULL, '2023-07-02 19:00:16');
INSERT INTO `order_list` VALUES (10, 28, 'N202003181545330001', 20000, 1000, 0, '2020-03-18 15:45:33', 0, 7, '个', 0, NULL, 4, 9, NULL, NULL, NULL, NULL, NULL, 1, 8, '花花', NULL, '2023-07-02 19:00:16');
INSERT INTO `order_list` VALUES (11, 34, 'N202003181546050001', 300, 100, 0, '2020-03-18 15:46:05', 0, 8, '个', 0, NULL, 1, 9, NULL, NULL, NULL, NULL, NULL, 1, 7, '慢慢', NULL, '2023-07-02 19:00:16');
INSERT INTO `order_list` VALUES (12, 33, 'N202003181546440001', 600, 99, 0, '2020-03-18 15:46:44', 0, 3, '个', 0, NULL, 1, 9, NULL, NULL, NULL, NULL, NULL, 34, 7, NULL, NULL, '2023-07-02 19:00:16');
INSERT INTO `order_list` VALUES (13, 30, 'N202003201455260001', 999, 99, 1, '2020-03-20 14:55:26', 1, 2, '个', 0, NULL, 3, 9, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, '2023-07-02 19:00:16');
INSERT INTO `order_list` VALUES (14, 31, 'N202003201624230001', 999, 88, 1, '2020-03-20 16:24:23', 1, 2, '个', 0, NULL, 1, 9, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, '2023-07-02 19:00:16');
INSERT INTO `order_list` VALUES (15, 42, 'N202003222027190001', 12000, 100, 0, '2020-03-22 20:27:19', 0, 2, '个', 0, NULL, 4, 9, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, '2023-07-02 19:00:16');
INSERT INTO `order_list` VALUES (16, 48, 'N202003291419150001', 3000, 66, 0, '2020-03-29 14:19:15', 0, 0, '个', 0, NULL, 1, 9, NULL, NULL, NULL, NULL, NULL, 35, NULL, NULL, NULL, '2023-07-02 19:00:17');
INSERT INTO `order_list` VALUES (17, 44, 'N202004052106310001', 1000, 100, 0, '2020-04-05 21:06:31', 0, 2, '个', 0, NULL, 2, 9, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, '2023-07-02 19:00:17');
INSERT INTO `order_list` VALUES (18, 44, 'N202004091218100001', 200, 99, 0, '2020-04-09 12:18:10', 0, 2, '个', 0, NULL, 3, 9, NULL, NULL, NULL, NULL, NULL, 35, NULL, NULL, NULL, '2023-07-02 19:00:17');
INSERT INTO `order_list` VALUES (19, 44, 'N202004091226140001', 200, 100, 0, '2020-04-09 12:26:14', 0, 2, '个', 0, NULL, 4, 9, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, '2023-07-02 19:00:17');
INSERT INTO `order_list` VALUES (22, 48, 'N202306201728350001', 100, 20, 1, '2023-06-20 17:28:36', 0, 2, '瓶', 0, NULL, 3, 9, NULL, NULL, NULL, NULL, NULL, 35, NULL, NULL, NULL, '2023-07-02 19:00:17');
INSERT INTO `order_list` VALUES (23, 48, 'N202306201730500001', 3000, 2, 0, '2023-06-20 17:30:51', 0, 2, '瓶', 0, NULL, 1, 9, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, '2023-07-02 19:00:17');
INSERT INTO `order_list` VALUES (25, 38, 'N202306210011380001', 2000, 500, 0, '2023-06-30 00:00:00', 0, 2, '个', 0, 'N202003181423460001', 2, 9, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, '2023-07-02 19:00:17');
INSERT INTO `order_list` VALUES (27, 39, 'N202306251556170001', 200000, 1, 0, '2023-06-25 15:56:18', 0, 2, '台', 0, NULL, 1, 10, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, '2023-07-02 19:00:17');
COMMIT;

-- ----------------------------
-- Table structure for output
-- ----------------------------
DROP TABLE IF EXISTS `output`;
CREATE TABLE `output` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `name` varchar(32) DEFAULT NULL,
                          `total` int DEFAULT '0',
                          `complete` double(64,2) DEFAULT '0.00',
  `percentage` double(64,2) DEFAULT '0.00',
  `note` varchar(128) DEFAULT NULL,
  `state` tinyint(1) DEFAULT '1',
  `orderNum` varchar(64) DEFAULT NULL,
  `orderName` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of output
-- ----------------------------
BEGIN;
INSERT INTO `output` VALUES (1, '1号生产线', 100, 66.00, 66.00, '该生产线位于山东省济南市商河县以北。\n生产线采用最新技术，高效便捷，用于生产大批量货物。', 1, 'N202004091226140001', '雀巢咖啡');
INSERT INTO `output` VALUES (2, '2号生产线', 100, 5.00, 5.00, '磨磨唧唧，效率很低', 1, '', '蒙牛牛奶');
INSERT INTO `output` VALUES (3, '3号生产线', 100, 0.00, 0.00, '生产效率一般情况', 1, '', '山楂树下');
COMMIT;

-- ----------------------------
-- Table structure for output_goods_log
-- ----------------------------
DROP TABLE IF EXISTS `output_goods_log`;
CREATE TABLE `output_goods_log` (
                                    `id` bigint NOT NULL AUTO_INCREMENT,
                                    `goodsId` bigint NOT NULL,
                                    `orderNum` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                    `createTime` datetime DEFAULT NULL,
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of output_goods_log
-- ----------------------------
BEGIN;
INSERT INTO `output_goods_log` VALUES (1, 26, 'N202306210011380001', '2023-06-25 15:01:36');
COMMIT;

-- ----------------------------
-- Table structure for output_log
-- ----------------------------
DROP TABLE IF EXISTS `output_log`;
CREATE TABLE `output_log` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `orderNum` varchar(64) DEFAULT NULL,
                              `orderName` varchar(64) DEFAULT NULL,
                              `startTime` datetime DEFAULT NULL,
                              `endTime` datetime DEFAULT NULL,
                              `oid` int DEFAULT NULL,
                              `operator` int DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of output_log
-- ----------------------------
BEGIN;
INSERT INTO `output_log` VALUES (1, 'test', '测试', '2020-03-18 19:54:39', '2020-03-20 15:41:56', NULL, NULL);
INSERT INTO `output_log` VALUES (4, 'N202003181349100001', '消毒水', '2020-03-19 20:24:58', '2020-03-20 20:25:10', 1, 1);
INSERT INTO `output_log` VALUES (5, 'N202003181540180001', 'N95口罩', '2020-03-19 21:02:35', '2020-03-20 14:48:22', 2, 1);
INSERT INTO `output_log` VALUES (6, '', '茶叶', '2020-03-19 22:30:44', '2020-03-20 14:26:12', 3, 1);
INSERT INTO `output_log` VALUES (8, '', '茶叶', '2020-03-20 14:37:55', '2020-03-20 14:38:27', 3, 1);
INSERT INTO `output_log` VALUES (9, '', '茶叶', '2020-03-20 14:43:14', '2020-03-20 14:44:59', 3, 1);
INSERT INTO `output_log` VALUES (10, 'N202003201455260001', '护目镜', '2020-03-20 15:41:37', '2020-03-20 15:41:56', 2, 1);
INSERT INTO `output_log` VALUES (11, '', '哈默', '2020-03-20 15:43:14', '2020-03-25 13:45:14', 3, 1);
INSERT INTO `output_log` VALUES (12, 'N202003201624230001', '小矮胖猫', '2020-03-20 16:24:49', '2020-03-20 20:20:34', 2, 1);
INSERT INTO `output_log` VALUES (13, 'N202003181546440001', '青岛啤酒', '2020-03-20 20:25:24', '2020-03-22 20:34:46', 1, 1);
INSERT INTO `output_log` VALUES (14, 'N202003181546050001', '普通医用口罩', '2020-03-20 20:25:50', '2020-03-24 14:47:01', 2, 1);
INSERT INTO `output_log` VALUES (15, 'N202003222027190001', '电脑', '2020-03-22 23:09:01', '2020-03-29 11:03:44', 1, 1);
INSERT INTO `output_log` VALUES (16, '', '水杯', '2020-03-24 14:50:13', '2020-03-25 13:45:14', 2, 1);
INSERT INTO `output_log` VALUES (17, 'N202003181423460001', '消毒液', '2020-03-25 22:27:01', '2020-03-25 22:27:13', 3, 2);
INSERT INTO `output_log` VALUES (18, 'N202003181545330001', '防护服', '2020-03-29 12:32:29', '2020-03-29 12:32:49', 1, 1);
INSERT INTO `output_log` VALUES (19, 'N202003291419150001', '牛奶', '2020-03-29 14:19:32', '2020-03-29 14:19:41', 1, 1);
INSERT INTO `output_log` VALUES (20, '', '朱明轩', '2020-04-01 10:31:03', '2020-04-01 10:31:11', 1, 1);
INSERT INTO `output_log` VALUES (21, 'N202004052106310001', '百世可乐', '2020-04-05 21:06:50', '2020-04-07 10:18:27', 1, 1);
INSERT INTO `output_log` VALUES (22, 'N202004091218100001', '可口可乐', '2020-04-09 12:18:25', '2020-04-09 12:25:14', 1, 1);
INSERT INTO `output_log` VALUES (23, '', '蒙牛牛奶', '2020-04-09 12:18:47', NULL, 2, 1);
INSERT INTO `output_log` VALUES (24, '', '山楂树下', '2020-04-09 12:23:45', NULL, 3, 1);
INSERT INTO `output_log` VALUES (25, 'N202004091226140001', '雀巢咖啡', '2020-04-09 12:26:37', NULL, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
                                     `username` varchar(64) NOT NULL,
                                     `series` varchar(64) NOT NULL,
                                     `token` varchar(64) NOT NULL,
                                     `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                     PRIMARY KEY (`series`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------
BEGIN;
INSERT INTO `persistent_logins` VALUES ('admin', '8kk98PgbTmz4QAuxyk0sTg==', 'CDmO5kN/DEshEPJkBk4jNA==', '2023-07-03 22:55:40');
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `name` varchar(32) DEFAULT NULL,
                        `nameZh` varchar(32) DEFAULT NULL,
                        `remark` varchar(128) DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        KEY `nameZh` (`nameZh`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, 'ROLE_admin', '系统管理员', '拥有管理系统的权力');
INSERT INTO `role` VALUES (2, 'ROLE_manager', '总经理', '管理仓库的权力');
INSERT INTO `role` VALUES (3, 'ROLE_warehouse', '仓库管理员', '对仓库的管理');
INSERT INTO `role` VALUES (12, 'ROLE_workshop', '车间生产员', '客户订单生产模块的管理');
COMMIT;

-- ----------------------------
-- Table structure for substation
-- ----------------------------
DROP TABLE IF EXISTS `substation`;
CREATE TABLE `substation` (
                              `id` bigint NOT NULL AUTO_INCREMENT,
                              `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
                              `parentId` bigint DEFAULT NULL,
                              `date` datetime DEFAULT CURRENT_TIMESTAMP,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of substation
-- ----------------------------
BEGIN;
INSERT INTO `substation` VALUES (1, '上海站', 0, '2023-07-01 11:16:02');
INSERT INTO `substation` VALUES (2, '北京站', 0, '2023-07-01 11:16:02');
INSERT INTO `substation` VALUES (3, '武汉站', 0, '2023-07-01 11:16:02');
INSERT INTO `substation` VALUES (4, '广东站', 0, '2023-07-01 11:16:02');
INSERT INTO `substation` VALUES (5, '重庆站', 0, '2023-07-01 11:16:02');
INSERT INTO `substation` VALUES (6, '江苏站', 0, '2023-07-01 11:27:37');
INSERT INTO `substation` VALUES (7, '海淀分站', 2, '2023-07-01 11:49:56');
INSERT INTO `substation` VALUES (8, '朝阳分站', 2, '2023-07-01 11:51:09');
INSERT INTO `substation` VALUES (9, '普陀区', 1, '2023-07-01 13:33:17');
INSERT INTO `substation` VALUES (10, '黄浦区', 1, '2023-07-01 13:33:24');
INSERT INTO `substation` VALUES (11, '浦东新区', 1, '2023-07-01 13:33:31');
INSERT INTO `substation` VALUES (12, '江北区', 5, '2023-07-01 13:33:47');
INSERT INTO `substation` VALUES (13, '苏州站', 6, '2023-07-01 13:33:57');
COMMIT;

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                            `address` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                            `contactUser` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                            `contactPhone` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                            `bankName` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                            `bankNumber` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                            `fax` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                            `postcode` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                            `legalPerson` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                            `content` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                            `date` datetime DEFAULT CURRENT_TIMESTAMP,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of supplier
-- ----------------------------
BEGIN;
INSERT INTO `supplier` VALUES (1, '贵州酒厂', '贵州市遵义路', '曹操', '00000000', '贵州银行', NULL, '020-11111111', '222000', '夏侯惇', '酒厂备注', '2023-07-02 11:10:04');
INSERT INTO `supplier` VALUES (3, '智飞生物', '安徽', '老大', '12345678', '安徽银行', NULL, '000-52368754', '223000', '老二', '生物医疗', '2023-07-02 11:13:41');
COMMIT;

-- ----------------------------
-- Table structure for supplier_goods
-- ----------------------------
DROP TABLE IF EXISTS `supplier_goods`;
CREATE TABLE `supplier_goods` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `code` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                  `goodsName` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                  `firstCategory` int DEFAULT NULL,
                                  `secondCategory` int DEFAULT NULL,
                                  `measurement` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                  `count` int DEFAULT NULL,
                                  `originPrice` decimal(10,0) DEFAULT NULL,
                                  `discount` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                  `price` decimal(10,0) DEFAULT NULL,
                                  `model` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                  `supplierId` bigint DEFAULT NULL,
                                  `factoryName` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                  `shelfLife` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                  `enabledReturn` int DEFAULT NULL,
                                  `content` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                  `date` datetime DEFAULT CURRENT_TIMESTAMP,
                                  `goodsId` bigint DEFAULT NULL,
                                  `operateId` bigint DEFAULT NULL,
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of supplier_goods
-- ----------------------------
BEGIN;
INSERT INTO `supplier_goods` VALUES (1, '飞天', '飞天茅台', 1, 6, '瓶', 100, 1499, '0', 99, '53', 1, '贵州茅台', '100', 0, '限量供应', '2023-07-01 17:15:32', 48, 1);
INSERT INTO `supplier_goods` VALUES (2, '兔茅', '兔年茅台', 1, 6, '瓶', 200, 2499, '0', 99, '53', 2, '上海酒厂', '100', NULL, '每天申购', '2023-07-01 17:38:12', NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for transition
-- ----------------------------
DROP TABLE IF EXISTS `transition`;
CREATE TABLE `transition` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `orderNum` varchar(128) DEFAULT NULL COMMENT '订单id',
                              `name` varchar(64) DEFAULT NULL,
                              `plan` int DEFAULT NULL,
                              `complete` double(64,2) DEFAULT NULL,
  `outputId` int DEFAULT NULL,
  `state` int DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of transition
-- ----------------------------
BEGIN;
INSERT INTO `transition` VALUES (22, 'N202004091218100001', '可口可乐', 99, 99.00, 1, 1);
INSERT INTO `transition` VALUES (21, 'N202004052106310001', '百世可乐', 100, 100.00, 1, 3);
INSERT INTO `transition` VALUES (8, 'N202003181540180001', 'N95口罩', 66, 66.00, 2, 3);
INSERT INTO `transition` VALUES (9, 'N202003201455260001', '护目镜', 99, 99.00, 2, 3);
INSERT INTO `transition` VALUES (10, 'N202003201624230001', '小矮胖猫', 88, 88.00, 2, 3);
INSERT INTO `transition` VALUES (11, 'N202003181349100001', '消毒水', 1500, 1500.00, 1, 3);
INSERT INTO `transition` VALUES (12, 'N202003181546440001', '青岛啤酒', 99, 99.00, 1, 3);
INSERT INTO `transition` VALUES (13, 'N202003181546050001', '普通医用口罩', 100, 60.00, 2, 3);
INSERT INTO `transition` VALUES (14, '', '哈默', 345, 345.00, 3, 3);
INSERT INTO `transition` VALUES (15, 'N202003181423460001', '消毒液', 500, 500.00, 3, 3);
INSERT INTO `transition` VALUES (16, '', '水杯', 20, 20.00, 2, 2);
INSERT INTO `transition` VALUES (17, 'N202003222027190001', '电脑', 100, 100.00, 1, 2);
INSERT INTO `transition` VALUES (18, 'N202003181545330001', '防护服', 1000, 1000.00, 1, 2);
INSERT INTO `transition` VALUES (19, 'N202003291419150001', '牛奶', 66, 66.00, 1, 2);
INSERT INTO `transition` VALUES (20, '', '朱明轩', 10, 10.00, 1, 3);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT COMMENT 'hrID',
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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, '庞庆舟', '17863671817', 'pqz@163.com', '超级管理员', 1, 'admin', '$2a$10$o2NUWiYOX3Iiz/Cgj9nhweSm20ubCtVet9YIKa3teE.RFso5.PwsS', 'http://localhost:8181/uploadFile/2020/04/29/22543b71-a7ff-4571-b0a5-65e630e14c1e.JPG', NULL);
INSERT INTO `user` VALUES (34, '肉肉', '17863671788', '1204216126@qq.com', '总经理', 1, 'rourou', '$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm', NULL, '');
INSERT INTO `user` VALUES (35, '丢丢', '17863671816', 'haha@163.com', '仓库管理员', 1, 'diudiu', '$2a$10$fJq9a2tiHDeAa0pRSoT3ieF.hnlLEuirrbHrzaX5LvZ5YT151Oe1m', NULL, '');
COMMIT;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `uid` int DEFAULT NULL,
                             `rid` int DEFAULT NULL,
                             PRIMARY KEY (`id`),
                             KEY `deu` (`uid`),
                             KEY `der` (`rid`),
                             CONSTRAINT `der` FOREIGN KEY (`rid`) REFERENCES `role` (`id`) ON DELETE CASCADE,
                             CONSTRAINT `deu` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `user_role` VALUES (1, 1, 1);
INSERT INTO `user_role` VALUES (2, 1, 2);
INSERT INTO `user_role` VALUES (26, 35, 3);
INSERT INTO `user_role` VALUES (32, 34, 2);
COMMIT;

-- ----------------------------
-- Table structure for warehouse
-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `num` varchar(64) DEFAULT NULL,
                             `name` varchar(64) DEFAULT NULL,
                             `address` varchar(128) DEFAULT NULL,
                             `area` double(64,2) DEFAULT NULL,
  `capacity` double(64,2) DEFAULT '0.00',
  `used` double(64,2) DEFAULT '0.00',
  `percentage` double(64,2) DEFAULT '0.00',
  `parentId` int DEFAULT NULL,
  `type` int DEFAULT NULL,
  `layer` int DEFAULT '1',
  `state` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=149 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of warehouse
-- ----------------------------
BEGIN;
INSERT INTO `warehouse` VALUES (2, 'T1', '待检库', '山东省济南市商河县以北', 1000.00, 240.00, 56.00, 23.33, 1, 1, 1, 1);
INSERT INTO `warehouse` VALUES (3, 'R1', '库区', NULL, 200.00, 120.00, 56.00, 46.67, 2, 2, 1, 1);
INSERT INTO `warehouse` VALUES (4, 'S1', '货架', NULL, 10.00, 30.00, 16.00, 53.33, 3, 3, 3, 1);
INSERT INTO `warehouse` VALUES (5, 'L1', '层', NULL, 10.00, 10.00, 0.00, 0.00, 4, 4, 1, 1);
INSERT INTO `warehouse` VALUES (6, 'L2', '层', NULL, 10.00, 10.00, 10.00, 100.00, 4, 4, 1, 1);
INSERT INTO `warehouse` VALUES (7, 'L3', '层', NULL, 10.00, 10.00, 6.00, 60.00, 4, 4, 1, 1);
INSERT INTO `warehouse` VALUES (8, 'W1', '普通仓库', '山东省济南市商河县以北', 1000.00, 360.00, 139.00, 38.61, 1, 1, 1, 1);
INSERT INTO `warehouse` VALUES (9, 'R2', '库区', NULL, 200.00, 90.00, 0.00, 0.00, 2, 2, 1, 1);
INSERT INTO `warehouse` VALUES (23, 'S2', '货架', NULL, 10.00, 30.00, 10.00, 33.33, 3, 3, 3, 1);
INSERT INTO `warehouse` VALUES (24, 'L1', '层', NULL, 10.00, 10.00, 10.00, 100.00, 23, 4, 1, 1);
INSERT INTO `warehouse` VALUES (25, 'L2', '层', NULL, 10.00, 10.00, 0.00, 0.00, 23, 4, 1, 1);
INSERT INTO `warehouse` VALUES (26, 'L3', '层', NULL, 10.00, 10.00, 0.00, 0.00, 23, 4, 1, 1);
INSERT INTO `warehouse` VALUES (27, 'S3', '货架', NULL, 10.00, 30.00, 30.00, 100.00, 3, 3, 3, 1);
INSERT INTO `warehouse` VALUES (28, 'L1', '层', NULL, 10.00, 10.00, 10.00, 100.00, 27, 4, 1, 0);
INSERT INTO `warehouse` VALUES (29, 'L2', '层', NULL, 10.00, 10.00, 10.00, 100.00, 27, 4, 1, 1);
INSERT INTO `warehouse` VALUES (30, 'L3', '层', NULL, 10.00, 10.00, 10.00, 100.00, 27, 4, 1, 1);
INSERT INTO `warehouse` VALUES (31, 'S1', '货架', NULL, 10.00, 30.00, 0.00, 0.00, 9, 3, 3, 1);
INSERT INTO `warehouse` VALUES (32, 'L1', '层', NULL, 10.00, 10.00, 0.00, 0.00, 31, 4, 1, 1);
INSERT INTO `warehouse` VALUES (33, 'L2', '层', NULL, 10.00, 10.00, 0.00, 0.00, 31, 4, 1, 1);
INSERT INTO `warehouse` VALUES (34, 'L3', '层', NULL, 10.00, 10.00, 0.00, 0.00, 31, 4, 1, 1);
INSERT INTO `warehouse` VALUES (35, 'S2', '货架', NULL, 10.00, 30.00, 0.00, 0.00, 9, 3, 3, 1);
INSERT INTO `warehouse` VALUES (36, 'L1', '层', NULL, 10.00, 10.00, 0.00, 0.00, 35, 4, 1, 1);
INSERT INTO `warehouse` VALUES (37, 'L2', '层', NULL, 10.00, 10.00, 0.00, 0.00, 35, 4, 1, 1);
INSERT INTO `warehouse` VALUES (38, 'L3', '层', NULL, 10.00, 10.00, 0.00, 0.00, 35, 4, 1, 1);
INSERT INTO `warehouse` VALUES (39, 'S3', '货架', NULL, 10.00, 30.00, 0.00, 0.00, 9, 3, 3, 1);
INSERT INTO `warehouse` VALUES (40, 'L1', '层', NULL, 10.00, 10.00, 0.00, 0.00, 39, 4, 1, 1);
INSERT INTO `warehouse` VALUES (41, 'L2', '层', NULL, 10.00, 10.00, 0.00, 0.00, 39, 4, 1, 1);
INSERT INTO `warehouse` VALUES (42, 'L3', '层', NULL, 10.00, 10.00, 0.00, 0.00, 39, 4, 1, 1);
INSERT INTO `warehouse` VALUES (43, 'S4', '货架', NULL, 10.00, 30.00, 0.00, 0.00, 3, 3, 3, 1);
INSERT INTO `warehouse` VALUES (44, 'L1', '层', NULL, 10.00, 10.00, 0.00, 0.00, 43, 4, 1, 1);
INSERT INTO `warehouse` VALUES (45, 'L2', '层', NULL, 10.00, 10.00, 0.00, 0.00, 43, 4, 1, 1);
INSERT INTO `warehouse` VALUES (46, 'L3', '层', NULL, 10.00, 10.00, 0.00, 0.00, 43, 4, 1, 1);
INSERT INTO `warehouse` VALUES (47, 'R3', '库区', '', 200.00, 0.00, 0.00, 0.00, 2, 2, 1, 1);
INSERT INTO `warehouse` VALUES (48, 'R4', '库区', '', 200.00, 30.00, 0.00, 0.00, 2, 2, 1, 1);
INSERT INTO `warehouse` VALUES (1, NULL, '所有', NULL, NULL, NULL, 0.00, 0.00, 0, NULL, 1, 1);
INSERT INTO `warehouse` VALUES (49, 'S1', '货架', NULL, 10.00, 30.00, 0.00, 0.00, 47, 3, 3, 1);
INSERT INTO `warehouse` VALUES (50, 'L1', '层', NULL, 10.00, 10.00, 0.00, 0.00, 49, 4, 1, 1);
INSERT INTO `warehouse` VALUES (51, 'L2', '层', NULL, 10.00, 10.00, 0.00, 0.00, 49, 4, 1, 1);
INSERT INTO `warehouse` VALUES (52, 'L3', '层', NULL, 10.00, 10.00, 0.00, 0.00, 49, 4, 1, 1);
INSERT INTO `warehouse` VALUES (53, 'S1', '货架', NULL, 10.00, 30.00, 0.00, 0.00, 48, 3, 3, 1);
INSERT INTO `warehouse` VALUES (54, 'L1', '层', NULL, 10.00, 10.00, 0.00, 0.00, 53, 4, 1, 1);
INSERT INTO `warehouse` VALUES (55, 'L2', '层', NULL, 10.00, 10.00, 0.00, 0.00, 53, 4, 1, 1);
INSERT INTO `warehouse` VALUES (56, 'L3', '层', NULL, 10.00, 10.00, 0.00, 0.00, 53, 4, 1, 1);
INSERT INTO `warehouse` VALUES (126, 'L1', '层', NULL, 10.00, 10.00, 0.00, 0.00, 125, 4, 1, 1);
INSERT INTO `warehouse` VALUES (127, 'L2', '层', NULL, 10.00, 10.00, 0.00, 0.00, 125, 4, 1, 1);
INSERT INTO `warehouse` VALUES (57, 'W2', '普通仓库', '山东省济南市商河县怀仁镇', 1000.00, 240.00, 27.00, 11.25, 1, 1, 1, 1);
INSERT INTO `warehouse` VALUES (58, 'G1', '废品库', '山东省济南市商河县怀仁镇', 500.00, 500.00, 66.00, 13.20, 1, 1, 1, 1);
INSERT INTO `warehouse` VALUES (59, 'R1', '库区', '', 200.00, 150.00, 103.00, 68.67, 8, 2, 1, 1);
INSERT INTO `warehouse` VALUES (60, 'R2', '库区', '', 200.00, 180.00, 36.00, 20.00, 8, 2, 1, 1);
INSERT INTO `warehouse` VALUES (61, 'R3', '库区', '', 200.00, 30.00, 0.00, 0.00, 8, 2, 1, 1);
INSERT INTO `warehouse` VALUES (62, 'S1', '货架', NULL, 10.00, 30.00, 30.00, 100.00, 59, 3, 3, 1);
INSERT INTO `warehouse` VALUES (63, 'L1', '层', NULL, 10.00, 10.00, 10.00, 100.00, 62, 4, 1, 1);
INSERT INTO `warehouse` VALUES (64, 'L2', '层', NULL, 10.00, 10.00, 10.00, 100.00, 62, 4, 1, 1);
INSERT INTO `warehouse` VALUES (65, 'L3', '层', NULL, 10.00, 10.00, 10.00, 100.00, 62, 4, 1, 1);
INSERT INTO `warehouse` VALUES (66, 'S1', '货架', NULL, 10.00, 30.00, 27.00, 90.00, 60, 3, 3, 1);
INSERT INTO `warehouse` VALUES (67, 'L1', '层', NULL, 10.00, 10.00, 9.00, 90.00, 66, 4, 1, 1);
INSERT INTO `warehouse` VALUES (68, 'L2', '层', NULL, 10.00, 10.00, 10.00, 100.00, 66, 4, 1, 1);
INSERT INTO `warehouse` VALUES (69, 'L3', '层', NULL, 10.00, 10.00, 8.00, 80.00, 66, 4, 1, 1);
INSERT INTO `warehouse` VALUES (70, 'S1', '货架', NULL, 10.00, 30.00, 0.00, 0.00, 61, 3, 3, 1);
INSERT INTO `warehouse` VALUES (71, 'L1', '层', NULL, 10.00, 10.00, 0.00, 0.00, 70, 4, 1, 1);
INSERT INTO `warehouse` VALUES (72, 'L2', '层', NULL, 10.00, 10.00, 0.00, 0.00, 70, 4, 1, 1);
INSERT INTO `warehouse` VALUES (73, 'L3', '层', NULL, 10.00, 10.00, 0.00, 0.00, 70, 4, 1, 1);
INSERT INTO `warehouse` VALUES (74, 'R1', '库区', '', 200.00, 120.00, 9.00, 7.50, 57, 2, 1, 1);
INSERT INTO `warehouse` VALUES (75, 'S1', '货架', NULL, 10.00, 30.00, 9.00, 30.00, 74, 3, 3, 1);
INSERT INTO `warehouse` VALUES (76, 'L1', '层', NULL, 10.00, 10.00, 9.00, 90.00, 75, 4, 1, 1);
INSERT INTO `warehouse` VALUES (77, 'L2', '层', NULL, 10.00, 10.00, 0.00, 0.00, 75, 4, 1, 1);
INSERT INTO `warehouse` VALUES (78, 'L3', '层', NULL, 10.00, 10.00, 0.00, 0.00, 75, 4, 1, 1);
INSERT INTO `warehouse` VALUES (124, 'L3', '层', NULL, 10.00, 10.00, 3.00, 30.00, 121, 4, 1, 1);
INSERT INTO `warehouse` VALUES (125, 'S5', '货架', NULL, 10.00, 30.00, 10.00, 33.33, 59, 3, 3, 1);
INSERT INTO `warehouse` VALUES (123, 'L2', '层', NULL, 10.00, 10.00, 10.00, 100.00, 121, 4, 1, 1);
INSERT INTO `warehouse` VALUES (122, 'L1', '层', NULL, 10.00, 10.00, 10.00, 100.00, 121, 4, 1, 1);
INSERT INTO `warehouse` VALUES (121, 'S4', '货架', NULL, 10.00, 30.00, 23.00, 76.67, 59, 3, 3, 1);
INSERT INTO `warehouse` VALUES (120, 'L3', '层', NULL, 10.00, 10.00, 10.00, 100.00, 117, 4, 1, 1);
INSERT INTO `warehouse` VALUES (119, 'L2', '层', NULL, 10.00, 10.00, 10.00, 100.00, 117, 4, 1, 1);
INSERT INTO `warehouse` VALUES (118, 'L1', '层', NULL, 10.00, 10.00, 10.00, 100.00, 117, 4, 1, 1);
INSERT INTO `warehouse` VALUES (117, 'S3', '货架', NULL, 10.00, 30.00, 30.00, 100.00, 59, 3, 3, 1);
INSERT INTO `warehouse` VALUES (84, 'R2', '库区', '', 200.00, 120.00, 18.00, 15.00, 57, 2, 1, 1);
INSERT INTO `warehouse` VALUES (85, 'S1', '货架', NULL, 10.00, 30.00, 18.00, 60.00, 84, 3, 3, 1);
INSERT INTO `warehouse` VALUES (86, 'L1', '层', NULL, 10.00, 10.00, 10.00, 100.00, 85, 4, 1, 1);
INSERT INTO `warehouse` VALUES (87, 'L2', '层', NULL, 10.00, 10.00, 8.00, 80.00, 85, 4, 1, 1);
INSERT INTO `warehouse` VALUES (88, 'L3', '层', NULL, 10.00, 10.00, 0.00, 0.00, 85, 4, 1, 1);
INSERT INTO `warehouse` VALUES (89, 'S2', '货架', NULL, 10.00, 30.00, 0.00, 0.00, 84, 3, 3, 1);
INSERT INTO `warehouse` VALUES (90, 'L1', '层', NULL, 10.00, 10.00, 0.00, 0.00, 89, 4, 1, 1);
INSERT INTO `warehouse` VALUES (91, 'L2', '层', NULL, 10.00, 10.00, 0.00, 0.00, 89, 4, 1, 1);
INSERT INTO `warehouse` VALUES (92, 'L3', '层', NULL, 10.00, 10.00, 0.00, 0.00, 89, 4, 1, 1);
INSERT INTO `warehouse` VALUES (93, 'S3', '货架', NULL, 10.00, 30.00, 0.00, 0.00, 84, 3, 3, 1);
INSERT INTO `warehouse` VALUES (94, 'L1', '层', NULL, 10.00, 10.00, 0.00, 0.00, 93, 4, 1, 1);
INSERT INTO `warehouse` VALUES (95, 'L2', '层', NULL, 10.00, 10.00, 0.00, 0.00, 93, 4, 1, 1);
INSERT INTO `warehouse` VALUES (96, 'L3', '层', NULL, 10.00, 10.00, 0.00, 0.00, 93, 4, 1, 1);
INSERT INTO `warehouse` VALUES (97, 'S4', '货架', NULL, 10.00, 30.00, 0.00, 0.00, 84, 3, 3, 1);
INSERT INTO `warehouse` VALUES (98, 'L1', '层', NULL, 10.00, 10.00, 0.00, 0.00, 97, 4, 1, 1);
INSERT INTO `warehouse` VALUES (99, 'L2', '层', NULL, 10.00, 10.00, 0.00, 0.00, 97, 4, 1, 1);
INSERT INTO `warehouse` VALUES (100, 'L3', '层', NULL, 10.00, 10.00, 0.00, 0.00, 97, 4, 1, 1);
INSERT INTO `warehouse` VALUES (101, 'S2', '货架', NULL, 10.00, 30.00, 0.00, 0.00, 74, 3, 3, 1);
INSERT INTO `warehouse` VALUES (102, 'L1', '层', NULL, 10.00, 10.00, 0.00, 0.00, 101, 4, 1, 1);
INSERT INTO `warehouse` VALUES (103, 'L2', '层', NULL, 10.00, 10.00, 0.00, 0.00, 101, 4, 1, 1);
INSERT INTO `warehouse` VALUES (104, 'L3', '层', NULL, 10.00, 10.00, 0.00, 0.00, 101, 4, 1, 1);
INSERT INTO `warehouse` VALUES (105, 'S3', '货架', NULL, 10.00, 30.00, 0.00, 0.00, 74, 3, 3, 1);
INSERT INTO `warehouse` VALUES (106, 'L1', '层', NULL, 10.00, 10.00, 0.00, 0.00, 105, 4, 1, 1);
INSERT INTO `warehouse` VALUES (107, 'L2', '层', NULL, 10.00, 10.00, 0.00, 0.00, 105, 4, 1, 1);
INSERT INTO `warehouse` VALUES (108, 'L3', '层', NULL, 10.00, 10.00, 0.00, 0.00, 105, 4, 1, 1);
INSERT INTO `warehouse` VALUES (109, 'S4', '货架', NULL, 10.00, 30.00, 0.00, 0.00, 74, 3, 3, 1);
INSERT INTO `warehouse` VALUES (110, 'L1', '层', NULL, 10.00, 10.00, 0.00, 0.00, 109, 4, 1, 1);
INSERT INTO `warehouse` VALUES (111, 'L2', '层', NULL, 10.00, 10.00, 0.00, 0.00, 109, 4, 1, 1);
INSERT INTO `warehouse` VALUES (112, 'L3', '层', NULL, 10.00, 10.00, 0.00, 0.00, 109, 4, 1, 1);
INSERT INTO `warehouse` VALUES (116, 'L3', '层', NULL, 10.00, 10.00, 10.00, 100.00, 113, 4, 1, 1);
INSERT INTO `warehouse` VALUES (115, 'L2', '层', NULL, 10.00, 10.00, 0.00, 0.00, 113, 4, 1, 1);
INSERT INTO `warehouse` VALUES (114, 'L1', '层', NULL, 10.00, 10.00, 0.00, 0.00, 113, 4, 1, 1);
INSERT INTO `warehouse` VALUES (113, 'S2', '货架', NULL, 10.00, 30.00, 10.00, 33.33, 59, 3, 3, 1);
INSERT INTO `warehouse` VALUES (128, 'L3', '层', NULL, 10.00, 10.00, 10.00, 100.00, 125, 4, 1, 1);
INSERT INTO `warehouse` VALUES (129, 'S2', '货架', NULL, 10.00, 30.00, 9.00, 30.00, 60, 3, 3, 1);
INSERT INTO `warehouse` VALUES (130, 'L1', '层', NULL, 10.00, 10.00, 9.00, 90.00, 129, 4, 1, 1);
INSERT INTO `warehouse` VALUES (131, 'L2', '层', NULL, 10.00, 10.00, 0.00, 0.00, 129, 4, 1, 1);
INSERT INTO `warehouse` VALUES (132, 'L3', '层', NULL, 10.00, 10.00, 0.00, 0.00, 129, 4, 1, 1);
INSERT INTO `warehouse` VALUES (133, 'S4', '货架', NULL, 10.00, 30.00, 0.00, 0.00, 60, 3, 3, 1);
INSERT INTO `warehouse` VALUES (134, 'L1', '层', NULL, 10.00, 10.00, 0.00, 0.00, 133, 4, 1, 1);
INSERT INTO `warehouse` VALUES (135, 'L2', '层', NULL, 10.00, 10.00, 0.00, 0.00, 133, 4, 1, 1);
INSERT INTO `warehouse` VALUES (136, 'L3', '层', NULL, 10.00, 10.00, 0.00, 0.00, 133, 4, 1, 1);
INSERT INTO `warehouse` VALUES (137, 'S5', '货架', NULL, 10.00, 30.00, 0.00, 0.00, 60, 3, 3, 1);
INSERT INTO `warehouse` VALUES (138, 'L1', '层', NULL, 10.00, 10.00, 0.00, 0.00, 137, 4, 1, 1);
INSERT INTO `warehouse` VALUES (139, 'L2', '层', NULL, 10.00, 10.00, 0.00, 0.00, 137, 4, 1, 1);
INSERT INTO `warehouse` VALUES (140, 'L3', '层', NULL, 10.00, 10.00, 0.00, 0.00, 137, 4, 1, 1);
INSERT INTO `warehouse` VALUES (141, 'S3', '货架', NULL, 10.00, 30.00, 0.00, 0.00, 60, 3, 3, 1);
INSERT INTO `warehouse` VALUES (142, 'L1', '层', NULL, 10.00, 10.00, 0.00, 0.00, 141, 4, 1, 1);
INSERT INTO `warehouse` VALUES (143, 'L2', '层', NULL, 10.00, 10.00, 0.00, 0.00, 141, 4, 1, 1);
INSERT INTO `warehouse` VALUES (144, 'L3', '层', NULL, 10.00, 10.00, 0.00, 0.00, 141, 4, 1, 1);
INSERT INTO `warehouse` VALUES (145, 'S6', '货架', NULL, 10.00, 30.00, 0.00, 0.00, 60, 3, 3, 1);
INSERT INTO `warehouse` VALUES (146, 'L1', '层', NULL, 10.00, 10.00, 0.00, 0.00, 145, 4, 1, 1);
INSERT INTO `warehouse` VALUES (147, 'L2', '层', NULL, 10.00, 10.00, 0.00, 0.00, 145, 4, 1, 1);
INSERT INTO `warehouse` VALUES (148, 'L3', '层', NULL, 10.00, 10.00, 0.00, 0.00, 145, 4, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for warehouse_goods
-- ----------------------------
DROP TABLE IF EXISTS `warehouse_goods`;
CREATE TABLE `warehouse_goods` (
                                   `id` int NOT NULL AUTO_INCREMENT,
                                   `wid` int DEFAULT NULL,
                                   `gid` int DEFAULT NULL,
                                   `used` double(64,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of warehouse_goods
-- ----------------------------
BEGIN;
INSERT INTO `warehouse_goods` VALUES (42, 87, 35, 8);
INSERT INTO `warehouse_goods` VALUES (41, 86, 35, 10);
INSERT INTO `warehouse_goods` VALUES (40, 69, 34, 8);
INSERT INTO `warehouse_goods` VALUES (39, 68, 34, 10);
INSERT INTO `warehouse_goods` VALUES (38, 67, 33, 9);
INSERT INTO `warehouse_goods` VALUES (37, 116, 32, 10);
INSERT INTO `warehouse_goods` VALUES (36, 118, 32, 10);
INSERT INTO `warehouse_goods` VALUES (35, 119, 32, 10);
INSERT INTO `warehouse_goods` VALUES (34, 120, 32, 10);
INSERT INTO `warehouse_goods` VALUES (44, 130, 37, 9);
INSERT INTO `warehouse_goods` VALUES (43, 76, 36, 9);
INSERT INTO `warehouse_goods` VALUES (46, 122, 38, 10);
INSERT INTO `warehouse_goods` VALUES (45, 123, 38, 10);
INSERT INTO `warehouse_goods` VALUES (29, 124, 29, 3);
INSERT INTO `warehouse_goods` VALUES (28, 128, 28, 10);
INSERT INTO `warehouse_goods` VALUES (25, 65, 26, 10);
INSERT INTO `warehouse_goods` VALUES (24, 64, 26, 10);
INSERT INTO `warehouse_goods` VALUES (23, 63, 26, 10);
COMMIT;

-- ----------------------------
-- Table structure for warehouse_transition
-- ----------------------------
DROP TABLE IF EXISTS `warehouse_transition`;
CREATE TABLE `warehouse_transition` (
                                        `id` int NOT NULL AUTO_INCREMENT,
                                        `lid` int DEFAULT NULL,
                                        `tid` int DEFAULT NULL,
                                        `used` double(64,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of warehouse_transition
-- ----------------------------
BEGIN;
INSERT INTO `warehouse_transition` VALUES (73, 7, 19, 6.00);
INSERT INTO `warehouse_transition` VALUES (72, 30, 18, 10.00);
INSERT INTO `warehouse_transition` VALUES (71, 29, 18, 10.00);
INSERT INTO `warehouse_transition` VALUES (70, 28, 18, 10.00);
INSERT INTO `warehouse_transition` VALUES (69, 24, 17, 10.00);
INSERT INTO `warehouse_transition` VALUES (68, 6, 16, 10.00);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
