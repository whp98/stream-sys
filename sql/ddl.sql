CREATE DATABASE `stream_share` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;


CREATE TABLE `user` (
                        `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
                        `user_nikename` varchar(45) COLLATE utf8_unicode_ci DEFAULT '默认昵称' COMMENT '用户昵称',
                        `user_pwd` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户密码',
                        `user_email` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '邮箱，用于恢复密码，登录，营销等',
                        PRIMARY KEY (`user_id`),
                        UNIQUE KEY `user_email_UNIQUE` (`user_email`)
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户表';



CREATE TABLE `stream` (
                          `stream_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用于区分每一个stream的标识',
                          `stream_url` varchar(8200) COLLATE utf8_unicode_ci NOT NULL COMMENT 'stream的直播源链接',
                          `stream_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '直播源名称',
                          `stream_interduce` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '直播源简单介绍',
                          `stream_fail` tinyint(4) NOT NULL COMMENT '表示直播源是否失效',
                          `stream_crate` float DEFAULT NULL COMMENT '表示直播源的内容平均分',
                          `stream_qrate` float DEFAULT NULL COMMENT '直播源流畅性评分',
                          PRIMARY KEY (`stream_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;




CREATE TABLE `rate` (
                        `stream_id` bigint(20) NOT NULL COMMENT '视频流id',
                        `user_id` bigint(20) NOT NULL COMMENT '评分人id',
                        `c_rate` float DEFAULT NULL COMMENT '直播流内容评分',
                        `q_rate` float DEFAULT NULL COMMENT '直播流播放质量评分',
                        PRIMARY KEY (`stream_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='评分表';

CREATE DEFINER=`root`@`%` TRIGGER `rate_AFTER_INSERT` AFTER INSERT ON `rate` FOR EACH ROW BEGIN
    update stream set stream_crate=(select avg(c_rate) from rate where stream_id = new.stream_id),stream_qrate=(select avg(q_rate) from rate where stream_id = new.stream_id) where stream_id = new.stream_id;
END

CREATE DEFINER=`root`@`%` TRIGGER `rate_AFTER_UPDATE` AFTER UPDATE ON `rate` FOR EACH ROW BEGIN
    update stream set stream_crate=(select avg(c_rate) from rate where stream_id = new.stream_id),stream_qrate=(select avg(q_rate) from rate where stream_id = new.stream_id) where stream_id = new.stream_id;
END