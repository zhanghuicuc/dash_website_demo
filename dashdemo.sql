/*
Navicat MySQL Data Transfer

Source Server         : 本机Mysql
Source Server Version : 56210
Source Host           : localhost:3306
Source Database       : dashdemo

Target Server Type    : MYSQL
Target Server Version : 56210
File Encoding         : 65001

Date: 2015-02-18 10:57:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `parentid` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', 'vod', '0', null);
INSERT INTO `category` VALUES ('2', 'live', '0', null);

-- ----------------------------
-- Table structure for `configure`
-- ----------------------------
DROP TABLE IF EXISTS `configure`;
CREATE TABLE `configure` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `val` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of configure
-- ----------------------------
INSERT INTO `configure` VALUES ('1', 'vcodec', 'libx264', 'Video encoder');
INSERT INTO `configure` VALUES ('2', 'vbitrate', '500', 'Video bitrates, in kbps, comma separated');
INSERT INTO `configure` VALUES ('3', 'vfps', '25', 'Video frame rate');
INSERT INTO `configure` VALUES ('4', 'vreso', '640x360', 'Video resolutions, WxH, comma separated');
INSERT INTO `configure` VALUES ('5', 'keepaspectratio', 'true', 'Keep original aspect ratio');
INSERT INTO `configure` VALUES ('6', 'acodec', 'aac', 'Audio encoder');
INSERT INTO `configure` VALUES ('7', 'abitrate', '64', 'Audio bitrates, in kbps, comma separated');
INSERT INTO `configure` VALUES ('8', 'dash_mpdname', 'test', 'MPD file name');
INSERT INTO `configure` VALUES ('9', 'dash_segmentsize', '4000', 'Segment Size (milliseconds)');
INSERT INTO `configure` VALUES ('10', 'dash_tisi', 'true', 'Add tisi info into mpd');
INSERT INTO `configure` VALUES ('11', 'watermarkuse', 'true', 'Use Watermark');
INSERT INTO `configure` VALUES ('12', 'watermark_url', 'watermark/logo.png', 'Watermark file\'s URL');
INSERT INTO `configure` VALUES ('13', 'watermark_cor', '5:5', 'Watermark\'s location (W:H)');
INSERT INTO `configure` VALUES ('14', 'thumbnail_ss', '5', 'When to get thumbnail (from start)');
INSERT INTO `configure` VALUES ('15', 'folder_videoori', 'videoori', 'Folder to save Upload Video');
INSERT INTO `configure` VALUES ('16', 'folder_dashfiles', 'videodash', 'Folder to save DASH Contents');
INSERT INTO `configure` VALUES ('17', 'folder_logs', 'videolog', 'Folder to save logs');
INSERT INTO `configure` VALUES ('18', 'folder_thumbnail', 'videothumbnail', 'Folder to save Thumbnail of Video');
-- ----------------------------
-- Table structure for `video`
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `intro` varchar(8192) DEFAULT NULL,
  `edittime` datetime DEFAULT NULL,
  `categoryid` int(11) DEFAULT NULL,
  `islive` int(11) DEFAULT NULL,
  `mpdurl` varchar(255) DEFAULT NULL,
  `dashfileurl` varchar(255) DEFAULT NULL,
  `oriurl` varchar(255) DEFAULT NULL,
  `thumbnailurl` varchar(255) DEFAULT NULL,
  `logurl` varchar(255) DEFAULT NULL,
  `videostateid` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_CATEGORY` (`categoryid`),
  KEY `FK_VIDEOSTATE` (`videostateid`),
  CONSTRAINT `FK_CATEGORY` FOREIGN KEY (`categoryid`) REFERENCES `category` (`id`),
  CONSTRAINT `FK_VIDEOSTATE` FOREIGN KEY (`videostateid`) REFERENCES `videostate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of video
-- ----------------------------

-- ----------------------------
-- Table structure for `videostate`
-- ----------------------------
DROP TABLE IF EXISTS `videostate`;
CREATE TABLE `videostate` (
  `id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  `order` int(11) DEFAULT NULL,
  `cssstyle` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of videostate
-- ----------------------------
INSERT INTO `videostate` VALUES ('1', '等待上传', '1', 'background:#CCFFFF', null);
INSERT INTO `videostate` VALUES ('2', '等待截图', '2', 'background:#00FF99', null);
INSERT INTO `videostate` VALUES ('3', '等待转码', '3', 'background:#00FF00', null);
INSERT INTO `videostate` VALUES ('4', '完成', '4', 'background:#FFFFFF', null);
