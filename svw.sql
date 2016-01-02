/*
Navicat MySQL Data Transfer

Source Server         : 本机Mysql
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : svw

Target Server Type    : MYSQL
Target Server Version : 50524
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
INSERT INTO `configure` VALUES ('1', 'transcoder_vcodec', 'libx264', 'Video encoder');
INSERT INTO `configure` VALUES ('2', 'transcoder_bv', '500000', 'Video bitrate');
INSERT INTO `configure` VALUES ('3', 'transcoder_framerate', '25', 'Video frame rate');
INSERT INTO `configure` VALUES ('4', 'transcoder_acodec', 'libmp3lame', 'Audio encoder');
INSERT INTO `configure` VALUES ('5', 'transcoder_ar', '22050', 'Audio sample rate');
INSERT INTO `configure` VALUES ('6', 'transcoder_ba', '64000', 'Audio bitrate');
INSERT INTO `configure` VALUES ('7', 'transcoder_scale_w', '640', 'Video width');
INSERT INTO `configure` VALUES ('8', 'transcoder_scale_h', '360', 'Video height');
INSERT INTO `configure` VALUES ('9', 'transcoder_watermarkuse', 'true', 'Use Watermark');
INSERT INTO `configure` VALUES ('10', 'transcoder_watermark_url', 'watermark/svw.png', 'Watermark file\'s URL');
INSERT INTO `configure` VALUES ('11', 'transcoder_watermark_x', '5', 'Watermark\'s location (x)');
INSERT INTO `configure` VALUES ('12', 'transcoder_watermark_y', '5', 'Watermark\'s location (y)');
INSERT INTO `configure` VALUES ('13', 'transcoder_keepaspectratio', 'true', 'Keep original aspect ratio');
INSERT INTO `configure` VALUES ('14', 'transcoder_outfmt', 'flv', 'Output file format');
INSERT INTO `configure` VALUES ('15', 'thumbnail_ss', '5', 'When to get thumbnail (from start)');
INSERT INTO `configure` VALUES ('16', 'folder_videoori', 'videoori', 'Folder to save Upload Video');
INSERT INTO `configure` VALUES ('17', 'folder_video', 'video', 'Folder to save Transcode Video');
INSERT INTO `configure` VALUES ('18', 'folder_thumbnail', 'videothumbnail', 'Folder to save Thumbnail of Video');
INSERT INTO `configure` VALUES ('19', 'dash_v_bitrate_settings', '250@480x270|500@640x360', 'Bitrate and resolution settings for DASH video (bitrate@wxh|bitrate@wxh)');
INSERT INTO `configure` VALUES ('20', 'dash_a_bitrate_settings', '2,44100,48|2,44100,128', 'Bitrate, channels, and sample rate settings for DASH audio (ch,sr,br|ch,sr,br)');
INSERT INTO `configure` VALUES ('21', 'dash_segment_size', '4000', 'Segment Size (milliseconds)');
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
  `url` varchar(255) DEFAULT NULL,
  `oriurl` varchar(255) DEFAULT NULL,
  `thumbnailurl` varchar(255) DEFAULT NULL,
  `mpdurl` varchar(255) DEFAULT NULL,
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
