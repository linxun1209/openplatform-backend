/*
 Navicat Premium Data Transfer

 Source Server         : xinxin
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : 110.40.211.224:3306
 Source Schema         : platform

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 10/12/2022 20:27:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for interface_info
-- ----------------------------
DROP TABLE IF EXISTS `interface_info`;
CREATE TABLE `interface_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `description` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接口地址',
  `requestHeader` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '请求头',
  `responseHeader` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '响应头',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '接口状态（0-关闭，1-开启）',
  `method` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '请求类型',
  `userId` bigint(20) NOT NULL COMMENT '创建人',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除(0-未删, 1-已删)',
  `requestParams` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '请求参数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '接口信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of interface_info
-- ----------------------------
INSERT INTO `interface_info` VALUES (1, 'getUsernameByPost', '获取用户名', 'http://localhost:8123/name/user', '{\"content-{”Content-Type“: ”application/json“}', '{\"content-{”Content-Type“: ”application/json“}', 1, 'POST', 3, '2022-11-26 06:10:02', '2022-12-10 07:11:29', 0, '[\n{\"name\":\"username\",\"type:string\"}\n]');
INSERT INTO `interface_info` VALUES (2, '许擎宇', '薛聪健', 'www.cary-king.net', '潘博涛', '谭聪健', 0, '石炫明', 9500534531, '2022-11-03 14:40:55', '2022-11-12 04:33:02', 1, NULL);
INSERT INTO `interface_info` VALUES (3, '陆弘文', '白志强', 'www.leslee-kuhn.net', '潘懿轩', '马鸿涛', 0, '陈峻熙', 3982575846, '2022-11-03 14:40:55', '2022-11-15 01:09:47', 1, NULL);
INSERT INTO `interface_info` VALUES (4, '毛建辉', '罗文', 'www.rosaria-kilback.io', '冯子默', '彭哲瀚', 1, '赵远航', 121776355, '2022-11-03 14:40:55', '2022-11-26 05:46:33', 0, NULL);
INSERT INTO `interface_info` VALUES (5, '彭雨泽', '蔡煜祺', 'www.norris-bergstrom.biz', '董思源', '田晓博', 0, '潘擎宇', 740, '2022-11-03 14:40:55', '2022-11-03 14:40:55', 0, NULL);
INSERT INTO `interface_info` VALUES (6, '傅志强', '陈梓晨', 'www.jordan-reinger.com', '金志强', '熊锦程', 0, '邓睿渊', 35542559, '2022-11-03 14:40:55', '2022-11-03 14:40:55', 0, NULL);
INSERT INTO `interface_info` VALUES (7, '吕黎昕', '孔越彬', 'www.fe-okon.info', '万伟宸', '林昊然', 0, '孟荣轩', 1445, '2022-11-03 14:40:55', '2022-11-03 14:40:55', 0, NULL);
INSERT INTO `interface_info` VALUES (8, '夏雪松', '许子骞', 'www.lashawna-legros.co', '蔡昊然', '胡鹏涛', 0, '钟立辉', 34075514, '2022-11-03 14:40:55', '2022-11-03 14:40:55', 0, NULL);
INSERT INTO `interface_info` VALUES (9, '严钰轩', '阎志泽', 'www.kay-funk.biz', '莫皓轩', '郭黎昕', 0, '龚天宇', 70956, '2022-11-03 14:40:55', '2022-11-03 14:40:55', 0, NULL);
INSERT INTO `interface_info` VALUES (10, '萧嘉懿', '曹熠彤', 'www.margarette-lindgren.biz', '田泽洋', '邓睿渊', 0, '梁志强', 98, '2022-11-03 14:40:56', '2022-11-03 14:40:56', 0, NULL);
INSERT INTO `interface_info` VALUES (11, '杜驰', '冯思源', 'www.vashti-auer.org', '黎健柏', '武博文', 0, '李伟宸', 9, '2022-11-03 14:40:56', '2022-11-03 14:40:56', 0, NULL);
INSERT INTO `interface_info` VALUES (12, '史金鑫', '蔡鹏涛', 'www.diann-keebler.org', '徐烨霖', '阎建辉', 0, '李烨伟', 125, '2022-11-03 14:40:56', '2022-11-03 14:40:56', 0, NULL);
INSERT INTO `interface_info` VALUES (13, '林炫明', '贾旭尧', 'www.dotty-kuvalis.io', '梁雨泽', '龙伟泽', 0, '许智渊', 79998, '2022-11-03 14:40:56', '2022-11-03 14:40:56', 0, NULL);
INSERT INTO `interface_info` VALUES (14, '何钰轩', '赖智宸', 'www.andy-adams.net', '崔思淼', '白鸿煊', 0, '邵振家', 7167482751, '2022-11-03 14:40:56', '2022-11-03 14:40:56', 0, NULL);
INSERT INTO `interface_info` VALUES (15, '魏志强', '于立诚', 'www.ione-aufderhar.biz', '朱懿轩', '万智渊', 0, '唐昊强', 741098, '2022-11-03 14:40:56', '2022-11-03 14:40:56', 0, NULL);
INSERT INTO `interface_info` VALUES (16, '严君浩', '金胤祥', 'www.duane-boyle.org', '雷昊焱', '侯思聪', 0, '郝思', 580514, '2022-11-03 14:40:56', '2022-11-03 14:40:56', 0, NULL);
INSERT INTO `interface_info` VALUES (17, '姚皓轩', '金鹏', 'www.lyda-klein.biz', '杜昊强', '邵志泽', 0, '冯鸿涛', 6546, '2022-11-03 14:40:56', '2022-11-03 14:40:56', 0, NULL);
INSERT INTO `interface_info` VALUES (18, '廖驰', '沈泽洋', 'www.consuelo-sipes.info', '彭昊然', '邓耀杰', 0, '周彬', 7761037, '2022-11-03 14:40:56', '2022-11-03 14:40:56', 0, NULL);
INSERT INTO `interface_info` VALUES (19, '赖智渊', '邓志泽', 'www.emerson-mann.co', '熊明哲', '贺哲瀚', 0, '田鹏', 381422, '2022-11-03 14:40:56', '2022-11-23 15:18:56', 0, NULL);
INSERT INTO `interface_info` VALUES (20, '许涛', '陆致远', 'www.vella-ankunding.name', '贾哲瀚', '莫昊焱', 0, '袁越彬', 4218096, '2022-11-03 14:40:56', '2022-11-26 04:36:51', 0, NULL);
INSERT INTO `interface_info` VALUES (21, '吕峻熙', '沈鹏飞', 'www.shari-reichel.org', '郭鸿煊', '覃烨霖', 0, '熊黎昕', 493, '2022-11-03 14:40:56', '2022-11-23 15:20:28', 0, NULL);
INSERT INTO `interface_info` VALUES (23, '1', '1', '111', '1', '1', 0, '11', 2, '2022-11-23 14:25:58', '2022-11-23 14:29:02', 1, '1');
INSERT INTO `interface_info` VALUES (24, '名字呢', '名字', 'http://localhost:8081/api/name/', '名字', '名字', 0, '名字', 2, '2022-11-23 15:23:29', '2022-12-09 14:22:10', 0, '名字');
INSERT INTO `interface_info` VALUES (25, '许擎宇', '薛聪健', 'www.cary-king.net', '潘博涛', '谭聪健', 0, '石炫明', 9500534531, '2022-11-03 14:39:46', '2022-11-26 06:12:12', 1, NULL);

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `gender` tinyint(4) NOT NULL DEFAULT 0 COMMENT '性别（0-男, 1-女）',
  `education` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学历',
  `place` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地点',
  `job` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职业',
  `contact` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `loveExp` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '感情经历',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容（个人介绍）',
  `photo` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '照片地址',
  `reviewStatus` int(11) NOT NULL DEFAULT 0 COMMENT '状态（0-待审核, 1-通过, 2-拒绝）',
  `reviewMessage` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核信息',
  `viewNum` int(11) NOT NULL DEFAULT 0 COMMENT '浏览数',
  `thumbNum` int(11) NOT NULL DEFAULT 0 COMMENT '点赞数',
  `userId` bigint(20) NOT NULL COMMENT '创建用户 id',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '帖子' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of post
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userName` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `userAccount` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `userAvatar` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `gender` tinyint(4) NULL DEFAULT NULL COMMENT '性别',
  `userRole` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'user' COMMENT '用户角色：user / admin',
  `userPassword` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `accessKey` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'accessKey',
  `secretKey` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'secretKey',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_userAccount`(`userAccount`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (3, NULL, 'xingchen0419', NULL, NULL, 'admin', '40beb09e055678db3d3053e4ea6818d6', '7e6541c5206a088a5d38dfd930108ff4', '107cd40866e70cabe435e3ee593c67e4', '2022-11-26 04:35:24', '2022-11-26 04:35:59', 0);

-- ----------------------------
-- Table structure for user_interface_info
-- ----------------------------
DROP TABLE IF EXISTS `user_interface_info`;
CREATE TABLE `user_interface_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userId` bigint(20) NOT NULL COMMENT '调用用户Id',
  `interfaceInfo` bigint(20) NOT NULL COMMENT '接口Id',
  `totalNum` int(11) NOT NULL DEFAULT 0 COMMENT '总调用次数',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '0-正常,1-禁用',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户调用接口关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_interface_info
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
