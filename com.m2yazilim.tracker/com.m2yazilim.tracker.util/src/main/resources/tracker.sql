-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Anamakine: localhost
-- Üretim Zamanı: 03 Kasım 2013 saat 23:13:47
-- Sunucu sürümü: 5.1.41
-- PHP Sürümü: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Veritabanı: `tracker`
--

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_admin_requests`
--

CREATE TABLE IF NOT EXISTS `tracker_admin_requests` (
  `request_id` int(5) NOT NULL AUTO_INCREMENT,
  `project_id` int(5) NOT NULL DEFAULT '0',
  `task_id` int(5) NOT NULL DEFAULT '0',
  `submitted_by` int(5) NOT NULL DEFAULT '0',
  `request_type` int(2) NOT NULL DEFAULT '0',
  `reason_given` text COLLATE utf8_turkish_ci,
  `time_submitted` int(11) NOT NULL DEFAULT '0',
  `resolved_by` int(5) NOT NULL DEFAULT '0',
  `time_resolved` int(11) NOT NULL DEFAULT '0',
  `deny_reason` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `version` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`request_id`),
  KEY `project_id` (`project_id`),
  KEY `task_id` (`task_id`),
  KEY `submitted_by` (`submitted_by`),
  KEY `request_type` (`request_type`),
  KEY `resolved_by` (`resolved_by`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=8 ;

--
-- Tablo döküm verisi `tracker_admin_requests`
--

INSERT INTO `tracker_admin_requests` (`request_id`, `project_id`, `task_id`, `submitted_by`, `request_type`, `reason_given`, `time_submitted`, `resolved_by`, `time_resolved`, `deny_reason`, `version`) VALUES
(3, 1, 2, 2, 1, 'sorun giderildi', 1352035057, 1, 1352039710, '', 0),
(4, 1, 2, 2, 1, '', 1352035484, 1, 1352039710, '', 1),
(5, 1, 3, 2, 1, 'işlem tamamlandı', 1359234364, 1, 1359234936, 'eksik var', 1),
(6, 1, 3, 2, 1, 'tekrar tamam', 1359235171, 1, 1359235293, 'tamam değil', 1),
(7, 1, 3, 2, 1, 'tekrar tamam', 1359239072, 1, 1362602596, '', 1);

--
-- Tetiklemeler `tracker_admin_requests`
--
DROP TRIGGER IF EXISTS `admin_requests_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `admin_requests_version_inc_trigger` BEFORE UPDATE ON `tracker_admin_requests`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_assigned`
--

CREATE TABLE IF NOT EXISTS `tracker_assigned` (
  `assigned_id` int(10) NOT NULL AUTO_INCREMENT,
  `task_id` int(10) NOT NULL DEFAULT '0',
  `user_id` int(5) NOT NULL DEFAULT '0',
  `version` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`assigned_id`),
  UNIQUE KEY `tracker_task_user` (`task_id`,`user_id`),
  KEY `tracker_task_id_assigned` (`task_id`,`user_id`),
  KEY `user_id` (`user_id`),
  KEY `task_id` (`task_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=10 ;

--
-- Tablo döküm verisi `tracker_assigned`
--

INSERT INTO `tracker_assigned` (`assigned_id`, `task_id`, `user_id`, `version`) VALUES
(0, 1, 2, 1),
(1, 3, 2, 3),
(2, 1, 1, 1),
(3, 3, 3, 1),
(4, 0, 0, 1),
(5, 4, 4, 0),
(6, 5, 4, 0),
(8, 6, 1, 0),
(9, 6, 4, 0);

--
-- Tetiklemeler `tracker_assigned`
--
DROP TRIGGER IF EXISTS `assigned_insert_log_trigger`;
DELIMITER //
CREATE TRIGGER `assigned_insert_log_trigger` AFTER INSERT ON `tracker_assigned`
 FOR EACH ROW BEGIN INSERT INTO tracker_table_change_log (table_name, table_row_id) VALUES ('tracker_assigned',NEW.assigned_id);

END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `assigned_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `assigned_version_inc_trigger` BEFORE UPDATE ON `tracker_assigned`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `assigned_update_log_trigger`;
DELIMITER //
CREATE TRIGGER `assigned_update_log_trigger` AFTER UPDATE ON `tracker_assigned`
 FOR EACH ROW BEGIN 

INSERT INTO tracker_table_change_log (table_name, table_row_id) VALUES ('tracker_assigned',NEW.assigned_id);
INSERT INTO tracker_assigned_old (task_id, old_user_id,assigned_id) VALUES (OLD.task_id,OLD.user_id,OLD.assigned_id);

END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_assigned_old`
--

CREATE TABLE IF NOT EXISTS `tracker_assigned_old` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `task_id` int(10) NOT NULL,
  `old_user_id` int(10) NOT NULL,
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `is_sent` int(1) DEFAULT '0',
  `sent_date` timestamp NULL DEFAULT NULL,
  `assigned_id` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `task_id` (`task_id`,`old_user_id`),
  KEY `old_user_id` (`old_user_id`),
  KEY `assigned_id` (`assigned_id`),
  KEY `assigned_id_2` (`assigned_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci COMMENT='task bir kullanicidan digerine assign edilirse eski kullanic' AUTO_INCREMENT=8 ;

--
-- Tablo döküm verisi `tracker_assigned_old`
--

INSERT INTO `tracker_assigned_old` (`id`, `task_id`, `old_user_id`, `create_date`, `is_sent`, `sent_date`, `assigned_id`) VALUES
(1, 2, 1, '2013-01-12 11:45:27', 0, NULL, 0),
(2, 1, 1, '2013-01-12 11:46:21', 0, NULL, 0),
(3, 1, 2, '2013-01-12 11:46:46', 0, NULL, 0),
(4, 2, 2, '2013-01-12 11:49:18', 0, NULL, 0),
(5, 1, 2, '2013-01-12 13:47:49', 0, NULL, 0),
(6, 0, 0, '2013-01-23 12:51:35', 0, NULL, 4),
(7, 3, 1, '2013-02-09 23:43:04', 0, NULL, 3);

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_attachments`
--

CREATE TABLE IF NOT EXISTS `tracker_attachments` (
  `attachment_id` int(5) NOT NULL AUTO_INCREMENT,
  `task_id` int(10) NOT NULL DEFAULT '0',
  `comment_id` int(10) NOT NULL DEFAULT '0',
  `orig_name` varchar(255) COLLATE utf8_turkish_ci NOT NULL,
  `file_name` varchar(30) COLLATE utf8_turkish_ci NOT NULL,
  `file_type` varchar(255) COLLATE utf8_turkish_ci NOT NULL,
  `file_size` int(20) NOT NULL DEFAULT '0',
  `added_by` int(3) NOT NULL DEFAULT '0',
  `date_added` int(11) NOT NULL DEFAULT '0',
  `version` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`attachment_id`),
  KEY `tracker_task_id_attachments` (`task_id`,`comment_id`),
  KEY `task_id` (`task_id`),
  KEY `comment_id` (`comment_id`),
  KEY `added_by` (`added_by`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=3 ;

--
-- Tablo döküm verisi `tracker_attachments`
--

INSERT INTO `tracker_attachments` (`attachment_id`, `task_id`, `comment_id`, `orig_name`, `file_name`, `file_type`, `file_size`, `added_by`, `date_added`, `version`) VALUES
(1, 1, 1, '383038_10150502088123360_290539813359_8593009_131924931_n.jpg', '1_38cabf427db2392b11562a39f575', 'image/jpeg; charset=binary', 53080, 1, 1351982125, 0),
(2, 1, 1, 'Ales_sinava_giris_belgesi.pdf', '1_2cab4f56bc4f8f047435e1cc5eb7', 'application/pdf; charset=binary', 266462, 1, 1351982124, 1);

--
-- Tetiklemeler `tracker_attachments`
--
DROP TRIGGER IF EXISTS `attachments_insert_log_trigger`;
DELIMITER //
CREATE TRIGGER `attachments_insert_log_trigger` AFTER INSERT ON `tracker_attachments`
 FOR EACH ROW BEGIN INSERT INTO tracker_table_change_log (table_name, table_row_id) VALUES ('tracker_attachments',NEW.attachment_id);

END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `attachments_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `attachments_version_inc_trigger` BEFORE UPDATE ON `tracker_attachments`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `attachments_update_log_trigger`;
DELIMITER //
CREATE TRIGGER `attachments_update_log_trigger` AFTER UPDATE ON `tracker_attachments`
 FOR EACH ROW BEGIN INSERT INTO tracker_table_change_log (table_name, table_row_id) VALUES ('tracker_attachments',NEW.attachment_id);

END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_cache`
--

CREATE TABLE IF NOT EXISTS `tracker_cache` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `type` varchar(4) COLLATE utf8_turkish_ci NOT NULL,
  `content` longtext COLLATE utf8_turkish_ci NOT NULL,
  `topic` int(11) NOT NULL,
  `last_updated` int(11) NOT NULL DEFAULT '0',
  `project_id` int(11) NOT NULL DEFAULT '0',
  `max_items` int(11) NOT NULL DEFAULT '0',
  `version` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tracker_cache_type` (`type`,`topic`,`project_id`,`max_items`),
  KEY `tracker_cache_type_topic` (`type`,`topic`),
  KEY `project_id` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=1 ;

--
-- Tablo döküm verisi `tracker_cache`
--


--
-- Tetiklemeler `tracker_cache`
--
DROP TRIGGER IF EXISTS `cache_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `cache_version_inc_trigger` BEFORE UPDATE ON `tracker_cache`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_city`
--

CREATE TABLE IF NOT EXISTS `tracker_city` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `city_name` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `version` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `city_name` (`city_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=1 ;

--
-- Tablo döküm verisi `tracker_city`
--


--
-- Tetiklemeler `tracker_city`
--
DROP TRIGGER IF EXISTS `city_insert_log_trigger`;
DELIMITER //
CREATE TRIGGER `city_insert_log_trigger` AFTER INSERT ON `tracker_city`
 FOR EACH ROW BEGIN INSERT INTO tracker_table_change_log (table_name, table_row_id) VALUES ('tracker_city',NEW.id);

END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `city_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `city_version_inc_trigger` BEFORE UPDATE ON `tracker_city`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `city_update_log_trigger`;
DELIMITER //
CREATE TRIGGER `city_update_log_trigger` AFTER UPDATE ON `tracker_city`
 FOR EACH ROW BEGIN INSERT INTO tracker_table_change_log (table_name, table_row_id) VALUES ('tracker_city',NEW.id);

END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_comments`
--

CREATE TABLE IF NOT EXISTS `tracker_comments` (
  `comment_id` int(10) NOT NULL AUTO_INCREMENT,
  `task_id` int(10) NOT NULL DEFAULT '0',
  `date_added` int(11) NOT NULL DEFAULT '0',
  `user_id` int(3) NOT NULL DEFAULT '0',
  `comment_text` text COLLATE utf8_turkish_ci,
  `last_edited_time` int(11) NOT NULL DEFAULT '0',
  `version` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`comment_id`),
  KEY `tracker_task_id_comments` (`task_id`),
  KEY `user_id` (`user_id`),
  KEY `task_id` (`task_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=14 ;

--
-- Tablo döküm verisi `tracker_comments`
--

INSERT INTO `tracker_comments` (`comment_id`, `task_id`, `date_added`, `user_id`, `comment_text`, `last_edited_time`, `version`) VALUES
(1, 2, 1351982125, 1, 'multi atcment', 1351982125, 0),
(2, 0, 1218152775, 1, 'Deneme45', 1218152775, 1),
(3, 1, 1218155650, 1, 'Deneme', 1218155650, 0),
(4, 0, 1218192306, 1, 'Deneme', 1218192306, 0),
(5, 1, 1218194072, 1, 'Deneme', 1218194072, 0),
(6, 1, 1218289509, 1, 'Deneme', 1218289509, 0),
(7, 1, 2102096869, 1, 'Deneme', 2102096869, 0),
(8, 1, 2102204025, 1, 'Deneme', 2102204025, 0),
(9, 1, 2102685822, 1, 'Deneme', 2102685822, 0),
(10, 1, 2102686962, 1, 'Deneme', 2102686962, 0),
(11, 1, 2102687744, 1, 'Deneme', 2102687744, 0),
(12, 1, 2102688447, 1, 'Deneme', 2102688447, 0),
(13, 1, 0, 1, 'dfgdfgdg', 0, 0);

--
-- Tetiklemeler `tracker_comments`
--
DROP TRIGGER IF EXISTS `comments_insert_log_trigger`;
DELIMITER //
CREATE TRIGGER `comments_insert_log_trigger` AFTER INSERT ON `tracker_comments`
 FOR EACH ROW BEGIN INSERT INTO tracker_table_change_log (table_name, table_row_id) VALUES ('tracker_comments',NEW.comment_id);

END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `comments_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `comments_version_inc_trigger` BEFORE UPDATE ON `tracker_comments`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `comments_update_log_trigger`;
DELIMITER //
CREATE TRIGGER `comments_update_log_trigger` AFTER UPDATE ON `tracker_comments`
 FOR EACH ROW BEGIN INSERT INTO tracker_table_change_log (table_name, table_row_id) VALUES ('tracker_comments',NEW.comment_id);

END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_dependencies`
--

CREATE TABLE IF NOT EXISTS `tracker_dependencies` (
  `depend_id` int(10) NOT NULL AUTO_INCREMENT,
  `task_id` int(10) NOT NULL DEFAULT '0',
  `dep_task_id` int(10) NOT NULL DEFAULT '0',
  `version` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`depend_id`),
  UNIQUE KEY `tracker_task_id_deps` (`task_id`,`dep_task_id`),
  KEY `task_id` (`task_id`),
  KEY `dep_task_id` (`dep_task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=1 ;

--
-- Tablo döküm verisi `tracker_dependencies`
--


--
-- Tetiklemeler `tracker_dependencies`
--
DROP TRIGGER IF EXISTS `dependencies_insert_log_trigger`;
DELIMITER //
CREATE TRIGGER `dependencies_insert_log_trigger` AFTER INSERT ON `tracker_dependencies`
 FOR EACH ROW BEGIN INSERT INTO tracker_table_change_log (table_name, table_row_id) VALUES ('tracker_dependencies',NEW.depend_id);

END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `dependencies_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `dependencies_version_inc_trigger` BEFORE UPDATE ON `tracker_dependencies`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `dependencies_update_log_trigger`;
DELIMITER //
CREATE TRIGGER `dependencies_update_log_trigger` AFTER UPDATE ON `tracker_dependencies`
 FOR EACH ROW BEGIN INSERT INTO tracker_table_change_log (table_name, table_row_id) VALUES ('tracker_dependencies',NEW.depend_id);

END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_groups`
--

CREATE TABLE IF NOT EXISTS `tracker_groups` (
  `group_id` int(3) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(20) COLLATE utf8_turkish_ci NOT NULL,
  `group_desc` varchar(150) COLLATE utf8_turkish_ci NOT NULL,
  `project_id` int(3) NOT NULL DEFAULT '0',
  `is_admin` int(1) NOT NULL DEFAULT '0',
  `manage_project` int(1) NOT NULL DEFAULT '0',
  `view_tasks` int(1) NOT NULL DEFAULT '0',
  `open_new_tasks` int(1) NOT NULL DEFAULT '0',
  `modify_own_tasks` int(1) NOT NULL DEFAULT '0',
  `modify_all_tasks` int(1) NOT NULL DEFAULT '0',
  `view_comments` int(1) NOT NULL DEFAULT '0',
  `add_comments` int(1) NOT NULL DEFAULT '0',
  `edit_comments` int(1) NOT NULL DEFAULT '0',
  `edit_own_comments` int(1) NOT NULL DEFAULT '0',
  `delete_comments` int(1) NOT NULL DEFAULT '0',
  `create_attachments` int(1) NOT NULL DEFAULT '0',
  `delete_attachments` int(1) NOT NULL DEFAULT '0',
  `view_history` int(1) NOT NULL DEFAULT '0',
  `close_own_tasks` int(1) NOT NULL DEFAULT '0',
  `close_other_tasks` int(1) NOT NULL DEFAULT '0',
  `assign_to_self` int(1) NOT NULL DEFAULT '0',
  `assign_others_to_self` int(1) NOT NULL DEFAULT '0',
  `add_to_assignees` int(1) NOT NULL DEFAULT '0',
  `view_reports` int(1) NOT NULL DEFAULT '0',
  `add_votes` int(1) NOT NULL DEFAULT '0',
  `edit_assignments` int(1) NOT NULL DEFAULT '0',
  `show_as_assignees` int(1) NOT NULL DEFAULT '0',
  `group_open` int(1) NOT NULL DEFAULT '0',
  `version` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`group_id`),
  UNIQUE KEY `tracker_group_name` (`group_name`,`project_id`),
  KEY `tracker_belongs_to_project` (`project_id`),
  KEY `project_id` (`project_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=10 ;

--
-- Tablo döküm verisi `tracker_groups`
--

INSERT INTO `tracker_groups` (`group_id`, `group_name`, `group_desc`, `project_id`, `is_admin`, `manage_project`, `view_tasks`, `open_new_tasks`, `modify_own_tasks`, `modify_all_tasks`, `view_comments`, `add_comments`, `edit_comments`, `edit_own_comments`, `delete_comments`, `create_attachments`, `delete_attachments`, `view_history`, `close_own_tasks`, `close_other_tasks`, `assign_to_self`, `assign_others_to_self`, `add_to_assignees`, `view_reports`, `add_votes`, `edit_assignments`, `show_as_assignees`, `group_open`, `version`) VALUES
(1, 'Admin', 'Members have unlimited access to all functionality.', 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0),
(2, 'Developers', 'Global Developers for all projects', 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0),
(3, 'Reporters', 'Open new tasks / add comments in all projects', 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0),
(4, 'Basic', 'Members can login, relying upon Project permissions only', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0),
(5, 'Pending', 'Users who are awaiting approval of their accounts.', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(6, 'Project Managers', 'Permission to do anything related to the Default Project.', 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0),
(7, 'Project Managers', 'Permission to do anything related to this project.', 2, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0),
(8, 'Saha Ekipleri', 'Sahadi arızaları takip eden ekiplerdir', 2, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1),
(9, 'Project Managers', 'Permission to do anything related to this project.', 3, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0);

--
-- Tetiklemeler `tracker_groups`
--
DROP TRIGGER IF EXISTS `groups_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `groups_version_inc_trigger` BEFORE UPDATE ON `tracker_groups`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_history`
--

CREATE TABLE IF NOT EXISTS `tracker_history` (
  `history_id` int(10) NOT NULL AUTO_INCREMENT,
  `task_id` int(10) NOT NULL DEFAULT '0',
  `user_id` int(3) NOT NULL DEFAULT '0',
  `event_date` int(11) NOT NULL DEFAULT '0',
  `event_type` int(2) NOT NULL DEFAULT '0',
  `field_changed` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `old_value` text COLLATE utf8_turkish_ci,
  `new_value` text COLLATE utf8_turkish_ci,
  `version` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`history_id`),
  KEY `tracker_idx_task_id` (`task_id`),
  KEY `task_id` (`task_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=41 ;

--
-- Tablo döküm verisi `tracker_history`
--

INSERT INTO `tracker_history` (`history_id`, `task_id`, `user_id`, `event_date`, `event_type`, `field_changed`, `old_value`, `new_value`, `version`) VALUES
(1, 1, 1, 1130024797, 1, '', '', '', 0),
(2, 2, 1, 1351463454, 14, '', '', '1', 0),
(3, 2, 1, 1351463454, 1, '', '', '', 0),
(4, 2, 1, 1351463454, 9, '', '', '1', 0),
(5, 1, 1, 1351982125, 4, '', '', '1', 0),
(6, 1, 1, 1351982125, 7, '', '383038_10150502088123360_290539813359_8593009_131924931_n.jpg', '1', 0),
(7, 1, 1, 1351982125, 7, '', 'Ales_sinava_giris_belgesi.pdf', '2', 0),
(8, 1, 1, 1351982125, 9, '', '', '1', 0),
(9, 0, 1, 1352034716, 30, '', '', 'a:34:{i:0;s:1:"2";s:7:"user_id";s:1:"2";i:1;s:5:"kemal";s:9:"user_name";s:5:"kemal";i:2;s:32:"883a22bb3013d335245eb5ca17178b03";s:9:"user_pass";s:32:"883a22bb3013d335245eb5ca17178b03";i:3;s:5:"kemal";s:9:"real_name";s:5:"kemal";i:4;s:0:"";s:9:"jabber_id";s:0:"";i:5;s:22:"mehmet2aktas@yahoo.com";s:13:"email_address";s:22:"mehmet2aktas@yahoo.com";i:6;s:1:"1";s:11:"notify_type";s:1:"1";i:7;s:1:"0";s:10:"notify_own";s:1:"0";i:8;s:1:"1";s:15:"account_enabled";s:1:"1";i:9;s:0:"";s:10:"dateformat";s:0:"";i:10;s:0:"";s:19:"dateformat_extended";s:0:"";i:11;s:0:"";s:9:"magic_url";s:0:"";i:12;s:2:"25";s:13:"tasks_perpage";s:2:"25";i:13;s:10:"1352034716";s:13:"register_date";s:10:"1352034716";i:14;s:1:"0";s:9:"time_zone";s:1:"0";i:15;s:1:"0";s:14:"login_attempts";s:1:"0";i:16;s:1:"0";s:10:"lock_until";s:1:"0";}', 0),
(10, 2, 2, 1352035057, 20, '', '', 'sorun giderildi', 0),
(11, 2, 1, 1352035454, 28, '', '', '', 0),
(12, 2, 2, 1352035482, 20, '', '', '', 0),
(13, 2, 2, 1352035724, 29, '', '1 2', '2', 0),
(14, 2, 1, 1352036059, 11, '', '', '1', 0),
(15, 1, 1, 1352036059, 11, '', '', '2', 0),
(16, 2, 1, 1352039709, 3, 'percent_complete', '0', '100', 0),
(17, 2, 1, 1352039710, 2, '', '', '5', 0),
(18, 3, 1, 1353527234, 14, '', '', '1', 0),
(19, 3, 1, 1353527234, 1, '', '', '', 0),
(20, 3, 1, 1353527234, 9, '', '', '1', 0),
(21, 3, 2, 1359234364, 20, '', '', 'işlem tamamlandı', 0),
(22, 3, 1, 1359234936, 28, '', '', 'eksik var', 0),
(23, 3, 2, 1359235171, 20, '', '', 'tekrar tamam', 0),
(24, 3, 1, 1359235293, 28, '', '', 'tamam değil', 0),
(25, 3, 2, 1359239073, 20, '', '', 'tekrar tamam', 0),
(26, 0, 1, 1359239240, 30, '', '', 'a:36:{i:0;s:1:"3";s:7:"user_id";s:1:"3";i:1;s:6:"maktas";s:9:"user_name";s:6:"maktas";i:2;s:32:"883a22bb3013d335245eb5ca17178b03";s:9:"user_pass";s:32:"883a22bb3013d335245eb5ca17178b03";i:3;s:6:"maktas";s:9:"real_name";s:6:"maktas";i:4;s:0:"";s:9:"jabber_id";s:0:"";i:5;s:20:"maktas@yahoo.com@yah";s:13:"email_address";s:20:"maktas@yahoo.com@yah";i:6;s:1:"0";s:11:"notify_type";s:1:"0";i:7;s:1:"0";s:10:"notify_own";s:1:"0";i:8;s:1:"1";s:15:"account_enabled";s:1:"1";i:9;s:0:"";s:10:"dateformat";s:0:"";i:10;s:0:"";s:19:"dateformat_extended";s:0:"";i:11;s:0:"";s:9:"magic_url";s:0:"";i:12;s:2:"25";s:13:"tasks_perpage";s:2:"25";i:13;s:10:"1359239239";s:13:"register_date";s:10:"1359239239";i:14;s:1:"0";s:9:"time_zone";s:1:"0";i:15;s:1:"0";s:14:"login_attempts";s:1:"0";i:16;s:1:"0";s:10:"lock_until";s:1:"0";i:17;s:1:"0";s:7:"version";s:1:"0";}', 0),
(27, 3, 1, 1362602596, 28, '', '', '', 0),
(28, 0, 1, 1362607912, 30, '', '', 'a:36:{i:0;s:1:"4";s:7:"user_id";s:1:"4";i:1;s:8:"enerjisa";s:9:"user_name";s:8:"enerjisa";i:2;s:32:"827ccb0eea8a706c4c34a16891f84e7b";s:9:"user_pass";s:32:"827ccb0eea8a706c4c34a16891f84e7b";i:3;s:21:"enerjisa çalışanı";s:9:"real_name";s:21:"enerjisa çalışanı";i:4;s:0:"";s:9:"jabber_id";s:0:"";i:5;s:24:"enerjisa@enerjisa.com.tr";s:13:"email_address";s:24:"enerjisa@enerjisa.com.tr";i:6;s:1:"3";s:11:"notify_type";s:1:"3";i:7;s:1:"0";s:10:"notify_own";s:1:"0";i:8;s:1:"1";s:15:"account_enabled";s:1:"1";i:9;s:0:"";s:10:"dateformat";s:0:"";i:10;s:0:"";s:19:"dateformat_extended";s:0:"";i:11;s:0:"";s:9:"magic_url";s:0:"";i:12;s:2:"25";s:13:"tasks_perpage";s:2:"25";i:13;s:10:"1362607912";s:13:"register_date";s:10:"1362607912";i:14;s:1:"2";s:9:"time_zone";s:1:"2";i:15;s:1:"0";s:14:"login_attempts";s:1:"0";i:16;s:1:"0";s:10:"lock_until";s:1:"0";i:17;s:1:"0";s:7:"version";s:1:"0";}', 0),
(29, 0, 1, 1362608733, 30, '', '', 'a:36:{i:0;s:1:"5";s:7:"user_id";s:1:"5";i:1;s:5:"basic";s:9:"user_name";s:5:"basic";i:2;s:32:"827ccb0eea8a706c4c34a16891f84e7b";s:9:"user_pass";s:32:"827ccb0eea8a706c4c34a16891f84e7b";i:3;s:21:"enerjisa çalışanı";s:9:"real_name";s:21:"enerjisa çalışanı";i:4;s:0:"";s:9:"jabber_id";s:0:"";i:5;s:24:"enerjisa@enerjisa.com.tr";s:13:"email_address";s:24:"enerjisa@enerjisa.com.tr";i:6;s:1:"0";s:11:"notify_type";s:1:"0";i:7;s:1:"0";s:10:"notify_own";s:1:"0";i:8;s:1:"1";s:15:"account_enabled";s:1:"1";i:9;s:0:"";s:10:"dateformat";s:0:"";i:10;s:0:"";s:19:"dateformat_extended";s:0:"";i:11;s:0:"";s:9:"magic_url";s:0:"";i:12;s:2:"25";s:13:"tasks_perpage";s:2:"25";i:13;s:10:"1362608733";s:13:"register_date";s:10:"1362608733";i:14;s:1:"0";s:9:"time_zone";s:1:"0";i:15;s:1:"0";s:14:"login_attempts";s:1:"0";i:16;s:1:"0";s:10:"lock_until";s:1:"0";i:17;s:1:"0";s:7:"version";s:1:"0";}', 0),
(30, 4, 1, 1363484851, 14, '', '', '4', 0),
(31, 4, 1, 1363484854, 1, '', '', '', 0),
(32, 4, 1, 1363484856, 9, '', '', '1', 0),
(33, 5, 1, 1363485295, 14, '', '', '4', 0),
(34, 5, 1, 1363485296, 1, '', '', '', 0),
(35, 5, 1, 1363485298, 9, '', '', '1', 0),
(36, 6, 1, 1363646161, 14, '', '', '1', 0),
(37, 6, 1, 1363646162, 1, '', '', '', 0),
(38, 6, 1, 1363646162, 9, '', '', '1', 0),
(39, 6, 1, 1363646340, 3, 'version', '2', '1', 0),
(40, 6, 1, 1363646340, 14, '', '1', '1 4', 0);

--
-- Tetiklemeler `tracker_history`
--
DROP TRIGGER IF EXISTS `history_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `history_version_inc_trigger` BEFORE UPDATE ON `tracker_history`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_list_category`
--

CREATE TABLE IF NOT EXISTS `tracker_list_category` (
  `category_id` int(3) NOT NULL AUTO_INCREMENT,
  `project_id` int(3) NOT NULL DEFAULT '0',
  `category_name` varchar(30) COLLATE utf8_turkish_ci NOT NULL,
  `show_in_list` int(1) NOT NULL DEFAULT '0',
  `category_owner` int(3) NOT NULL DEFAULT '0',
  `lft` int(10) unsigned NOT NULL DEFAULT '0',
  `rgt` int(10) unsigned NOT NULL DEFAULT '0',
  `version` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`category_id`),
  KEY `tracker_project_id_cat` (`project_id`),
  KEY `project_id` (`project_id`,`category_owner`),
  KEY `category_owner` (`category_owner`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=10 ;

--
-- Tablo döküm verisi `tracker_list_category`
--

INSERT INTO `tracker_list_category` (`category_id`, `project_id`, `category_name`, `show_in_list`, `category_owner`, `lft`, `rgt`, `version`) VALUES
(1, 1, 'Backend / Core', 1, 0, 2, 3, 0),
(2, 0, 'root', 0, 0, 1, 2, 0),
(3, 1, 'root', 0, 0, 1, 6, 0),
(4, 1, 'Arıza', 1, 0, 4, 5, 0),
(5, 2, 'root', 1, 0, 1, 6, 1),
(6, 2, 'Backend / Core', 0, 0, 2, 3, 1),
(7, 2, 'Bakım', 1, 0, 4, 5, 1),
(8, 3, 'root', 1, 0, 1, 4, 0),
(9, 3, 'Backend / Core', 1, 0, 2, 3, 0);

--
-- Tetiklemeler `tracker_list_category`
--
DROP TRIGGER IF EXISTS `list_category_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `list_category_version_inc_trigger` BEFORE UPDATE ON `tracker_list_category`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_list_os`
--

CREATE TABLE IF NOT EXISTS `tracker_list_os` (
  `os_id` int(3) NOT NULL AUTO_INCREMENT,
  `project_id` int(3) NOT NULL DEFAULT '0',
  `os_name` varchar(40) COLLATE utf8_turkish_ci NOT NULL,
  `list_position` int(3) NOT NULL DEFAULT '0',
  `show_in_list` int(1) NOT NULL DEFAULT '0',
  `version` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`os_id`),
  KEY `tracker_project_id_os` (`project_id`),
  KEY `project_id` (`project_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=7 ;

--
-- Tablo döküm verisi `tracker_list_os`
--

INSERT INTO `tracker_list_os` (`os_id`, `project_id`, `os_name`, `list_position`, `show_in_list`, `version`) VALUES
(1, 1, 'All', 1, 1, 0),
(2, 1, 'Windows', 2, 1, 0),
(3, 1, 'Linux', 3, 1, 0),
(4, 1, 'Mac OS', 4, 1, 0),
(5, 2, 'All', 1, 1, 0),
(6, 3, 'All', 1, 1, 0);

--
-- Tetiklemeler `tracker_list_os`
--
DROP TRIGGER IF EXISTS `list_os_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `list_os_version_inc_trigger` BEFORE UPDATE ON `tracker_list_os`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_list_resolution`
--

CREATE TABLE IF NOT EXISTS `tracker_list_resolution` (
  `resolution_id` int(3) NOT NULL AUTO_INCREMENT,
  `resolution_name` varchar(30) COLLATE utf8_turkish_ci NOT NULL,
  `list_position` int(3) NOT NULL DEFAULT '0',
  `show_in_list` int(1) NOT NULL DEFAULT '0',
  `project_id` int(3) NOT NULL DEFAULT '0',
  `version` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`resolution_id`),
  KEY `tracker_project_id_res` (`project_id`),
  KEY `project_id` (`project_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=11 ;

--
-- Tablo döküm verisi `tracker_list_resolution`
--

INSERT INTO `tracker_list_resolution` (`resolution_id`, `resolution_name`, `list_position`, `show_in_list`, `project_id`, `version`) VALUES
(1, 'Not a bug', 1, 1, 0, 0),
(2, 'Won''t fix', 2, 1, 0, 0),
(3, 'Won''t implement', 3, 1, 0, 0),
(4, 'Works for me', 4, 1, 0, 0),
(5, 'Deferred', 5, 1, 0, 0),
(6, 'Duplicate', 6, 1, 0, 0),
(7, 'Fixed', 7, 1, 0, 0),
(8, 'Implemented', 8, 1, 0, 0),
(9, 'Görev Bitirildi', 0, 1, 2, 0),
(10, 'Başlandı', 1, 1, 2, 0);

--
-- Tetiklemeler `tracker_list_resolution`
--
DROP TRIGGER IF EXISTS `list_resolution_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `list_resolution_version_inc_trigger` BEFORE UPDATE ON `tracker_list_resolution`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_list_status`
--

CREATE TABLE IF NOT EXISTS `tracker_list_status` (
  `status_id` int(3) NOT NULL AUTO_INCREMENT,
  `status_name` varchar(40) COLLATE utf8_turkish_ci NOT NULL,
  `list_position` int(3) NOT NULL DEFAULT '0',
  `show_in_list` int(1) NOT NULL DEFAULT '0',
  `project_id` int(3) NOT NULL DEFAULT '0',
  `version` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`status_id`),
  KEY `tracker_project_id_status` (`project_id`),
  KEY `project_id` (`project_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=10 ;

--
-- Tablo döküm verisi `tracker_list_status`
--

INSERT INTO `tracker_list_status` (`status_id`, `status_name`, `list_position`, `show_in_list`, `project_id`, `version`) VALUES
(1, 'Unconfirmed', 1, 1, 0, 0),
(2, 'New', 2, 1, 0, 0),
(3, 'Assigned', 3, 1, 0, 0),
(4, 'Researching', 4, 1, 0, 0),
(5, 'Waiting on Customer', 5, 1, 0, 0),
(6, 'Requires testing', 6, 1, 0, 0),
(7, 'Göreve Başlandı', 0, 1, 2, 0),
(8, 'Görev Bitirildi', 1, 1, 2, 0),
(9, 'Arıza Tespit Edildi', 0, 1, 3, 0);

--
-- Tetiklemeler `tracker_list_status`
--
DROP TRIGGER IF EXISTS `list_status_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `list_status_version_inc_trigger` BEFORE UPDATE ON `tracker_list_status`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_list_tasktype`
--

CREATE TABLE IF NOT EXISTS `tracker_list_tasktype` (
  `tasktype_id` int(3) NOT NULL AUTO_INCREMENT,
  `tasktype_name` varchar(40) COLLATE utf8_turkish_ci NOT NULL,
  `list_position` int(3) NOT NULL DEFAULT '0',
  `show_in_list` int(1) NOT NULL DEFAULT '0',
  `project_id` int(3) NOT NULL DEFAULT '0',
  `version` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`tasktype_id`),
  KEY `tracker_project_id_tt` (`project_id`),
  KEY `project_id` (`project_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=5 ;

--
-- Tablo döküm verisi `tracker_list_tasktype`
--

INSERT INTO `tracker_list_tasktype` (`tasktype_id`, `tasktype_name`, `list_position`, `show_in_list`, `project_id`, `version`) VALUES
(1, 'Bug Report', 1, 1, 0, 0),
(2, 'Feature Request', 2, 1, 0, 0),
(3, 'Arıza Bildirimi', 0, 1, 2, 1),
(4, 'Bakım', 1, 1, 2, 1);

--
-- Tetiklemeler `tracker_list_tasktype`
--
DROP TRIGGER IF EXISTS `list_tasktype_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `list_tasktype_version_inc_trigger` BEFORE UPDATE ON `tracker_list_tasktype`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_list_version`
--

CREATE TABLE IF NOT EXISTS `tracker_list_version` (
  `version_id` int(3) NOT NULL AUTO_INCREMENT,
  `project_id` int(3) NOT NULL DEFAULT '0',
  `version_name` varchar(40) COLLATE utf8_turkish_ci NOT NULL,
  `list_position` int(3) NOT NULL DEFAULT '0',
  `show_in_list` int(1) NOT NULL DEFAULT '0',
  `version_tense` int(1) NOT NULL DEFAULT '0',
  `version` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`version_id`),
  KEY `tracker_project_id_version` (`project_id`,`version_tense`),
  KEY `project_id` (`project_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=4 ;

--
-- Tablo döküm verisi `tracker_list_version`
--

INSERT INTO `tracker_list_version` (`version_id`, `project_id`, `version_name`, `list_position`, `show_in_list`, `version_tense`, `version`) VALUES
(0, 0, 'Default', 1, 0, 2, 0),
(1, 1, 'Development', 1, 1, 2, 0),
(2, 2, '1.0', 1, 1, 2, 0),
(3, 3, '1.0', 1, 1, 2, 0);

--
-- Tetiklemeler `tracker_list_version`
--
DROP TRIGGER IF EXISTS `list_version_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `list_version_version_inc_trigger` BEFORE UPDATE ON `tracker_list_version`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_location_get_type`
--

CREATE TABLE IF NOT EXISTS `tracker_location_get_type` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_turkish_ci NOT NULL,
  `description` varchar(200) COLLATE utf8_turkish_ci NOT NULL,
  `version` int(15) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=5 ;

--
-- Tablo döküm verisi `tracker_location_get_type`
--

INSERT INTO `tracker_location_get_type` (`id`, `name`, `description`, `version`) VALUES
(1, 'gps', 'gps den veri alınacaktır l', 3),
(2, 'network', '', 0),
(3, 'operator', '', 0),
(4, 'best', 'en iyi lokasyon alinacaktir', 0);

--
-- Tetiklemeler `tracker_location_get_type`
--
DROP TRIGGER IF EXISTS `location_get_type_insert_log_trigger`;
DELIMITER //
CREATE TRIGGER `location_get_type_insert_log_trigger` AFTER INSERT ON `tracker_location_get_type`
 FOR EACH ROW BEGIN INSERT INTO tracker_table_change_log (table_name, table_row_id) VALUES ('tracker_location_get_type',NEW.id);

END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `location_get_type_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `location_get_type_version_inc_trigger` BEFORE UPDATE ON `tracker_location_get_type`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `location_get_type_update_log_trigger`;
DELIMITER //
CREATE TRIGGER `location_get_type_update_log_trigger` AFTER UPDATE ON `tracker_location_get_type`
 FOR EACH ROW BEGIN INSERT INTO tracker_table_change_log (table_name, table_row_id) VALUES ('tracker_location_get_type',NEW.id);

END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_notifications`
--

CREATE TABLE IF NOT EXISTS `tracker_notifications` (
  `notify_id` int(10) NOT NULL AUTO_INCREMENT,
  `task_id` int(10) NOT NULL DEFAULT '0',
  `user_id` int(5) NOT NULL DEFAULT '0',
  `version` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`notify_id`),
  UNIQUE KEY `tracker_task_id_notifs` (`task_id`,`user_id`),
  KEY `task_id` (`task_id`,`user_id`),
  KEY `task_id_2` (`task_id`,`user_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=7 ;

--
-- Tablo döküm verisi `tracker_notifications`
--

INSERT INTO `tracker_notifications` (`notify_id`, `task_id`, `user_id`, `version`) VALUES
(1, 2, 1, 0),
(2, 1, 1, 0),
(3, 3, 1, 0),
(4, 4, 1, 0),
(5, 5, 1, 0),
(6, 6, 1, 0);

--
-- Tetiklemeler `tracker_notifications`
--
DROP TRIGGER IF EXISTS `notifications_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `notifications_version_inc_trigger` BEFORE UPDATE ON `tracker_notifications`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_notification_messages`
--

CREATE TABLE IF NOT EXISTS `tracker_notification_messages` (
  `message_id` int(10) NOT NULL AUTO_INCREMENT,
  `message_subject` text COLLATE utf8_turkish_ci,
  `message_body` text COLLATE utf8_turkish_ci,
  `time_created` int(11) NOT NULL DEFAULT '0',
  `version` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=1 ;

--
-- Tablo döküm verisi `tracker_notification_messages`
--


--
-- Tetiklemeler `tracker_notification_messages`
--
DROP TRIGGER IF EXISTS `notification_messages_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `notification_messages_version_inc_trigger` BEFORE UPDATE ON `tracker_notification_messages`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_notification_recipients`
--

CREATE TABLE IF NOT EXISTS `tracker_notification_recipients` (
  `recipient_id` int(10) NOT NULL AUTO_INCREMENT,
  `message_id` int(10) NOT NULL DEFAULT '0',
  `notify_method` varchar(1) COLLATE utf8_turkish_ci NOT NULL,
  `notify_address` varchar(100) COLLATE utf8_turkish_ci NOT NULL,
  `version` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`recipient_id`),
  KEY `message_id` (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=1 ;

--
-- Tablo döküm verisi `tracker_notification_recipients`
--


--
-- Tetiklemeler `tracker_notification_recipients`
--
DROP TRIGGER IF EXISTS `notification_recipients_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `notification_recipients_version_inc_trigger` BEFORE UPDATE ON `tracker_notification_recipients`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_prefs`
--

CREATE TABLE IF NOT EXISTS `tracker_prefs` (
  `pref_id` int(1) NOT NULL AUTO_INCREMENT,
  `pref_name` varchar(20) COLLATE utf8_turkish_ci NOT NULL,
  `pref_value` varchar(250) COLLATE utf8_turkish_ci NOT NULL DEFAULT '0',
  `version` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`pref_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=30 ;

--
-- Tablo döküm verisi `tracker_prefs`
--

INSERT INTO `tracker_prefs` (`pref_id`, `pref_name`, `pref_value`, `version`) VALUES
(1, 'fs_ver', '0.9.9.7', 0),
(2, 'jabber_server', '', 1),
(3, 'jabber_port', '5222', 1),
(4, 'jabber_username', '', 1),
(5, 'jabber_password', '', 1),
(6, 'anon_group', '4', 1),
(7, 'user_notify', '1', 1),
(8, 'admin_email', 'flyspray@example.com', 1),
(9, 'lang_code', 'tr', 1),
(10, 'spam_proof', '1', 1),
(11, 'default_project', '1', 1),
(12, 'dateformat', '', 1),
(13, 'dateformat_extended', '', 1),
(14, 'anon_reg', '1', 1),
(15, 'global_theme', 'CleanFS', 1),
(16, 'visible_columns', 'id project category tasktype severity summary status progress', 1),
(17, 'smtp_server', '', 1),
(18, 'smtp_user', '', 1),
(19, 'smtp_pass', '', 1),
(20, 'page_title', 'Arıza Takip::', 1),
(21, 'notify_registration', '0', 1),
(22, 'jabber_ssl', '0', 1),
(23, 'last_update_check', '1363645466', 4),
(24, 'cache_feeds', '1', 1),
(25, 'lock_for', '5', 0),
(26, 'email_ssl', '0', 1),
(27, 'email_tls', '0', 1),
(28, 'default_timezone', '0', 0),
(29, 'attachment_folder', 'attachments', 1);

--
-- Tetiklemeler `tracker_prefs`
--
DROP TRIGGER IF EXISTS `prefs_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `prefs_version_inc_trigger` BEFORE UPDATE ON `tracker_prefs`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_projects`
--

CREATE TABLE IF NOT EXISTS `tracker_projects` (
  `project_id` int(3) NOT NULL AUTO_INCREMENT,
  `project_title` varchar(100) COLLATE utf8_turkish_ci NOT NULL,
  `theme_style` varchar(20) COLLATE utf8_turkish_ci NOT NULL DEFAULT '0',
  `default_cat_owner` int(3) NOT NULL DEFAULT '0',
  `intro_message` text COLLATE utf8_turkish_ci,
  `project_is_active` int(1) NOT NULL DEFAULT '0',
  `visible_columns` varchar(255) COLLATE utf8_turkish_ci NOT NULL,
  `others_view` int(1) NOT NULL DEFAULT '0',
  `anon_open` int(1) NOT NULL DEFAULT '0',
  `notify_email` text COLLATE utf8_turkish_ci,
  `notify_jabber` text COLLATE utf8_turkish_ci,
  `notify_reply` text COLLATE utf8_turkish_ci,
  `notify_types` varchar(100) COLLATE utf8_turkish_ci NOT NULL DEFAULT '0',
  `feed_img_url` text COLLATE utf8_turkish_ci,
  `feed_description` text COLLATE utf8_turkish_ci,
  `notify_subject` varchar(100) COLLATE utf8_turkish_ci NOT NULL DEFAULT '',
  `lang_code` varchar(10) COLLATE utf8_turkish_ci NOT NULL,
  `comment_closed` int(1) NOT NULL DEFAULT '0',
  `auto_assign` int(1) NOT NULL DEFAULT '0',
  `last_updated` int(11) NOT NULL DEFAULT '0',
  `default_task` text COLLATE utf8_turkish_ci,
  `default_entry` varchar(8) COLLATE utf8_turkish_ci NOT NULL DEFAULT 'index',
  `version` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`project_id`),
  KEY `default_cat_owner` (`default_cat_owner`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=4 ;

--
-- Tablo döküm verisi `tracker_projects`
--

INSERT INTO `tracker_projects` (`project_id`, `project_title`, `theme_style`, `default_cat_owner`, `intro_message`, `project_is_active`, `visible_columns`, `others_view`, `anon_open`, `notify_email`, `notify_jabber`, `notify_reply`, `notify_types`, `feed_img_url`, `feed_description`, `notify_subject`, `lang_code`, `comment_closed`, `auto_assign`, `last_updated`, `default_task`, `default_entry`, `version`) VALUES
(0, 'default', 'CleanFS', 0, NULL, 0, 'id category tasktype severity summary status ', 0, 0, NULL, NULL, NULL, '0', NULL, NULL, '', '', 0, 0, 0, NULL, 'index', 0),
(1, 'Ana Proje', 'CleanFS', 0, 'Welcome to your first Flyspray project!  We hope that Flyspray provides you with many hours of increased productivity.  If you have any issues, go to http://flyspray.org/support.                   You can customise this message by clicking the **Manage Project** link in the menu above...', 1, 'id category tasktype severity summary status progress', 1, 0, '', '', '', '0', '', '', '', 'tr', 0, 0, 1362602436, '', 'index', 2),
(2, 'Enerjisa', 'CleanFS', 4, '', 1, 'id project category tasktype severity summary status progress', 0, 0, '', '', '', '0', '', '', '', 'tr', 0, 1, 1362607976, '', 'index', 2),
(3, 'Su işletmesi', 'CleanFS', 0, '', 1, 'id project category tasktype severity summary status progress', 1, 0, '', '', NULL, '0', NULL, NULL, '', 'tr', 0, 0, 0, NULL, 'index', 0);

--
-- Tetiklemeler `tracker_projects`
--
DROP TRIGGER IF EXISTS `projects_insert_log_trigger`;
DELIMITER //
CREATE TRIGGER `projects_insert_log_trigger` AFTER INSERT ON `tracker_projects`
 FOR EACH ROW BEGIN INSERT INTO tracker_table_change_log (table_name, table_row_id) VALUES ('tracker_projects',NEW.project_id);

END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `projects_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `projects_version_inc_trigger` BEFORE UPDATE ON `tracker_projects`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `projects_update_log_trigger`;
DELIMITER //
CREATE TRIGGER `projects_update_log_trigger` AFTER UPDATE ON `tracker_projects`
 FOR EACH ROW BEGIN INSERT INTO tracker_table_change_log (table_name, table_row_id) VALUES ('tracker_projects',NEW.project_id);

END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_registrations`
--

CREATE TABLE IF NOT EXISTS `tracker_registrations` (
  `reg_id` int(10) NOT NULL AUTO_INCREMENT,
  `reg_time` int(11) NOT NULL DEFAULT '0',
  `confirm_code` varchar(20) COLLATE utf8_turkish_ci NOT NULL,
  `user_name` varchar(32) COLLATE utf8_turkish_ci NOT NULL,
  `real_name` varchar(100) COLLATE utf8_turkish_ci NOT NULL,
  `email_address` varchar(100) COLLATE utf8_turkish_ci NOT NULL,
  `jabber_id` varchar(100) COLLATE utf8_turkish_ci NOT NULL,
  `notify_type` int(1) NOT NULL DEFAULT '0',
  `magic_url` varchar(40) COLLATE utf8_turkish_ci NOT NULL,
  `time_zone` int(6) NOT NULL DEFAULT '0',
  `version` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`reg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=1 ;

--
-- Tablo döküm verisi `tracker_registrations`
--


--
-- Tetiklemeler `tracker_registrations`
--
DROP TRIGGER IF EXISTS `registrations_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `registrations_version_inc_trigger` BEFORE UPDATE ON `tracker_registrations`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_related`
--

CREATE TABLE IF NOT EXISTS `tracker_related` (
  `related_id` int(10) NOT NULL AUTO_INCREMENT,
  `this_task` int(10) NOT NULL DEFAULT '0',
  `related_task` int(10) NOT NULL DEFAULT '0',
  `is_duplicate` int(1) NOT NULL DEFAULT '0',
  `version` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`related_id`),
  UNIQUE KEY `tracker_this_task` (`this_task`,`related_task`,`is_duplicate`),
  KEY `this_task` (`this_task`),
  KEY `related_task` (`related_task`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=2 ;

--
-- Tablo döküm verisi `tracker_related`
--

INSERT INTO `tracker_related` (`related_id`, `this_task`, `related_task`, `is_duplicate`, `version`) VALUES
(1, 2, 1, 0, 0);

--
-- Tetiklemeler `tracker_related`
--
DROP TRIGGER IF EXISTS `related_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `related_version_inc_trigger` BEFORE UPDATE ON `tracker_related`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_reminders`
--

CREATE TABLE IF NOT EXISTS `tracker_reminders` (
  `reminder_id` int(10) NOT NULL AUTO_INCREMENT,
  `task_id` int(10) NOT NULL DEFAULT '0',
  `to_user_id` int(3) NOT NULL DEFAULT '0',
  `from_user_id` int(3) NOT NULL DEFAULT '0',
  `start_time` int(11) NOT NULL DEFAULT '0',
  `how_often` int(12) NOT NULL DEFAULT '0',
  `last_sent` int(11) NOT NULL DEFAULT '0',
  `reminder_message` text COLLATE utf8_turkish_ci,
  `version` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`reminder_id`),
  KEY `task_id` (`task_id`,`to_user_id`,`from_user_id`),
  KEY `to_user_id` (`to_user_id`),
  KEY `from_user_id` (`from_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=1 ;

--
-- Tablo döküm verisi `tracker_reminders`
--


--
-- Tetiklemeler `tracker_reminders`
--
DROP TRIGGER IF EXISTS `reminders_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `reminders_version_inc_trigger` BEFORE UPDATE ON `tracker_reminders`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_searches`
--

CREATE TABLE IF NOT EXISTS `tracker_searches` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `search_string` text COLLATE utf8_turkish_ci,
  `time` int(11) NOT NULL DEFAULT '0',
  `version` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=13 ;

--
-- Tablo döküm verisi `tracker_searches`
--

INSERT INTO `tracker_searches` (`id`, `user_id`, `name`, `search_string`, `time`, `version`) VALUES
(1, 2, 'Tasks I watch', 'a:16:{s:12:"only_watched";s:1:"1";s:6:"string";N;s:4:"type";a:1:{i:0;s:0:"";}s:3:"sev";a:1:{i:0;s:0:"";}s:3:"due";a:1:{i:0;s:0:"";}s:3:"dev";N;s:3:"cat";a:1:{i:0;s:0:"";}s:6:"status";a:1:{i:0;s:4:"open";}s:5:"order";N;s:4:"sort";N;s:7:"percent";a:1:{i:0;s:0:"";}s:6:"opened";N;s:18:"search_in_comments";N;s:14:"search_for_all";N;s:8:"reported";a:1:{i:0;s:0:"";}s:12:"only_primary";N;}', 1352034716, 0),
(2, 2, 'Assigned to myself', 'a:16:{s:3:"dev";i:2;s:6:"string";N;s:4:"type";a:1:{i:0;s:0:"";}s:3:"sev";a:1:{i:0;s:0:"";}s:3:"due";a:1:{i:0;s:0:"";}s:3:"cat";a:1:{i:0;s:0:"";}s:6:"status";a:1:{i:0;s:4:"open";}s:5:"order";N;s:4:"sort";N;s:7:"percent";a:1:{i:0;s:0:"";}s:6:"opened";N;s:18:"search_in_comments";N;s:14:"search_for_all";N;s:8:"reported";a:1:{i:0;s:0:"";}s:12:"only_primary";N;s:12:"only_watched";N;}', 1352034716, 0),
(3, 2, 'Tasks I reported', 'a:16:{s:6:"opened";i:2;s:6:"string";N;s:4:"type";a:1:{i:0;s:0:"";}s:3:"sev";a:1:{i:0;s:0:"";}s:3:"due";a:1:{i:0;s:0:"";}s:3:"dev";N;s:3:"cat";a:1:{i:0;s:0:"";}s:6:"status";a:1:{i:0;s:4:"open";}s:5:"order";N;s:4:"sort";N;s:7:"percent";a:1:{i:0;s:0:"";}s:18:"search_in_comments";N;s:14:"search_for_all";N;s:8:"reported";a:1:{i:0;s:0:"";}s:12:"only_primary";N;s:12:"only_watched";N;}', 1352034716, 0),
(4, 3, 'Tasks I watch', 'a:16:{s:12:"only_watched";s:1:"1";s:6:"string";N;s:4:"type";a:1:{i:0;s:0:"";}s:3:"sev";a:1:{i:0;s:0:"";}s:3:"due";a:1:{i:0;s:0:"";}s:3:"dev";N;s:3:"cat";a:1:{i:0;s:0:"";}s:6:"status";a:1:{i:0;s:4:"open";}s:5:"order";N;s:4:"sort";N;s:7:"percent";a:1:{i:0;s:0:"";}s:6:"opened";N;s:18:"search_in_comments";N;s:14:"search_for_all";N;s:8:"reported";a:1:{i:0;s:0:"";}s:12:"only_primary";N;}', 1359239240, 0),
(5, 3, 'Assigned to myself', 'a:16:{s:3:"dev";i:3;s:6:"string";N;s:4:"type";a:1:{i:0;s:0:"";}s:3:"sev";a:1:{i:0;s:0:"";}s:3:"due";a:1:{i:0;s:0:"";}s:3:"cat";a:1:{i:0;s:0:"";}s:6:"status";a:1:{i:0;s:4:"open";}s:5:"order";N;s:4:"sort";N;s:7:"percent";a:1:{i:0;s:0:"";}s:6:"opened";N;s:18:"search_in_comments";N;s:14:"search_for_all";N;s:8:"reported";a:1:{i:0;s:0:"";}s:12:"only_primary";N;s:12:"only_watched";N;}', 1359239240, 0),
(6, 3, 'Tasks I reported', 'a:16:{s:6:"opened";i:3;s:6:"string";N;s:4:"type";a:1:{i:0;s:0:"";}s:3:"sev";a:1:{i:0;s:0:"";}s:3:"due";a:1:{i:0;s:0:"";}s:3:"dev";N;s:3:"cat";a:1:{i:0;s:0:"";}s:6:"status";a:1:{i:0;s:4:"open";}s:5:"order";N;s:4:"sort";N;s:7:"percent";a:1:{i:0;s:0:"";}s:18:"search_in_comments";N;s:14:"search_for_all";N;s:8:"reported";a:1:{i:0;s:0:"";}s:12:"only_primary";N;s:12:"only_watched";N;}', 1359239240, 0),
(7, 4, 'Tasks I watch', 'a:16:{s:12:"only_watched";s:1:"1";s:6:"string";N;s:4:"type";a:1:{i:0;s:0:"";}s:3:"sev";a:1:{i:0;s:0:"";}s:3:"due";a:1:{i:0;s:0:"";}s:3:"dev";N;s:3:"cat";a:1:{i:0;s:0:"";}s:6:"status";a:1:{i:0;s:4:"open";}s:5:"order";N;s:4:"sort";N;s:7:"percent";a:1:{i:0;s:0:"";}s:6:"opened";N;s:18:"search_in_comments";N;s:14:"search_for_all";N;s:8:"reported";a:1:{i:0;s:0:"";}s:12:"only_primary";N;}', 1362607913, 0),
(8, 4, 'Assigned to myself', 'a:16:{s:3:"dev";i:4;s:6:"string";N;s:4:"type";a:1:{i:0;s:0:"";}s:3:"sev";a:1:{i:0;s:0:"";}s:3:"due";a:1:{i:0;s:0:"";}s:3:"cat";a:1:{i:0;s:0:"";}s:6:"status";a:1:{i:0;s:4:"open";}s:5:"order";N;s:4:"sort";N;s:7:"percent";a:1:{i:0;s:0:"";}s:6:"opened";N;s:18:"search_in_comments";N;s:14:"search_for_all";N;s:8:"reported";a:1:{i:0;s:0:"";}s:12:"only_primary";N;s:12:"only_watched";N;}', 1362607913, 0),
(9, 4, 'Tasks I reported', 'a:16:{s:6:"opened";i:4;s:6:"string";N;s:4:"type";a:1:{i:0;s:0:"";}s:3:"sev";a:1:{i:0;s:0:"";}s:3:"due";a:1:{i:0;s:0:"";}s:3:"dev";N;s:3:"cat";a:1:{i:0;s:0:"";}s:6:"status";a:1:{i:0;s:4:"open";}s:5:"order";N;s:4:"sort";N;s:7:"percent";a:1:{i:0;s:0:"";}s:18:"search_in_comments";N;s:14:"search_for_all";N;s:8:"reported";a:1:{i:0;s:0:"";}s:12:"only_primary";N;s:12:"only_watched";N;}', 1362607913, 0),
(10, 5, 'Tasks I watch', 'a:16:{s:12:"only_watched";s:1:"1";s:6:"string";N;s:4:"type";a:1:{i:0;s:0:"";}s:3:"sev";a:1:{i:0;s:0:"";}s:3:"due";a:1:{i:0;s:0:"";}s:3:"dev";N;s:3:"cat";a:1:{i:0;s:0:"";}s:6:"status";a:1:{i:0;s:4:"open";}s:5:"order";N;s:4:"sort";N;s:7:"percent";a:1:{i:0;s:0:"";}s:6:"opened";N;s:18:"search_in_comments";N;s:14:"search_for_all";N;s:8:"reported";a:1:{i:0;s:0:"";}s:12:"only_primary";N;}', 1362608733, 0),
(11, 5, 'Assigned to myself', 'a:16:{s:3:"dev";i:5;s:6:"string";N;s:4:"type";a:1:{i:0;s:0:"";}s:3:"sev";a:1:{i:0;s:0:"";}s:3:"due";a:1:{i:0;s:0:"";}s:3:"cat";a:1:{i:0;s:0:"";}s:6:"status";a:1:{i:0;s:4:"open";}s:5:"order";N;s:4:"sort";N;s:7:"percent";a:1:{i:0;s:0:"";}s:6:"opened";N;s:18:"search_in_comments";N;s:14:"search_for_all";N;s:8:"reported";a:1:{i:0;s:0:"";}s:12:"only_primary";N;s:12:"only_watched";N;}', 1362608733, 0),
(12, 5, 'Tasks I reported', 'a:16:{s:6:"opened";i:5;s:6:"string";N;s:4:"type";a:1:{i:0;s:0:"";}s:3:"sev";a:1:{i:0;s:0:"";}s:3:"due";a:1:{i:0;s:0:"";}s:3:"dev";N;s:3:"cat";a:1:{i:0;s:0:"";}s:6:"status";a:1:{i:0;s:4:"open";}s:5:"order";N;s:4:"sort";N;s:7:"percent";a:1:{i:0;s:0:"";}s:18:"search_in_comments";N;s:14:"search_for_all";N;s:8:"reported";a:1:{i:0;s:0:"";}s:12:"only_primary";N;s:12:"only_watched";N;}', 1362608733, 0);

--
-- Tetiklemeler `tracker_searches`
--
DROP TRIGGER IF EXISTS `searches_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `searches_version_inc_trigger` BEFORE UPDATE ON `tracker_searches`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_table_change_log`
--

CREATE TABLE IF NOT EXISTS `tracker_table_change_log` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `table_row_id` int(10) NOT NULL,
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `is_sent` int(1) DEFAULT '0',
  `sent_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=40 ;

--
-- Tablo döküm verisi `tracker_table_change_log`
--

INSERT INTO `tracker_table_change_log` (`id`, `table_name`, `table_row_id`, `create_date`, `is_sent`, `sent_date`) VALUES
(1, 'deneme', 2, '2012-12-22 09:22:30', 1, '2013-01-12 08:21:45'),
(2, 'tracker_comments', 2, '2012-12-22 09:28:42', 1, '2013-01-12 08:21:47'),
(3, 'tracker_comments', 13, '2012-12-22 09:30:42', 1, '2013-01-12 08:21:48'),
(4, 'tracker_votes', 1, '2012-12-22 10:13:59', 1, '2013-01-12 08:21:49'),
(5, 'tracker_attachments', 2, '2012-12-23 10:32:47', 1, '2013-01-12 08:21:50'),
(6, 'tracker_tasks', 3, '2013-01-02 13:58:02', 1, '2013-01-27 05:02:09'),
(7, 'tracker_tasks', 2, '2013-01-02 13:58:39', 1, '2013-01-27 04:59:57'),
(8, 'tracker_user_mobile_setting', 1, '2013-01-12 09:04:16', 1, '2013-01-21 12:44:14'),
(9, 'tracker_location_get_type', 1, '2013-01-12 09:04:54', 1, '2013-01-21 12:44:14'),
(10, 'tracker_assigned', 1, '2013-01-12 11:45:27', 1, '2013-01-21 12:44:14'),
(11, 'tracker_assigned', 1, '2013-01-12 11:46:21', 1, '2013-01-21 12:44:14'),
(12, 'tracker_assigned', 1, '2013-01-12 11:46:46', 1, '2013-01-21 12:44:14'),
(13, 'tracker_assigned', 2, '2013-01-12 11:49:18', 1, '2013-01-21 12:44:14'),
(14, 'tracker_assigned', 4, '2013-01-12 13:45:38', 1, '2013-01-21 12:44:14'),
(16, 'tracker_assigned', 0, '2013-01-12 13:47:49', 1, '2013-01-21 12:44:14'),
(17, 'tracker_assigned', 4, '2013-01-23 12:51:35', 1, '2013-01-23 12:58:27'),
(18, 'tracker_assigned', 3, '2013-02-09 23:43:04', 1, '2013-03-16 18:45:11'),
(19, 'tracker_projects', 1, '2013-03-06 12:40:36', 1, '2013-03-16 18:45:11'),
(20, 'tracker_projects', 1, '2013-03-06 12:40:36', 1, '2013-03-16 18:45:11'),
(21, 'tracker_projects', 2, '2013-03-06 14:08:50', 1, '2013-03-16 18:45:11'),
(22, 'tracker_projects', 2, '2013-03-06 14:12:56', 1, '2013-03-16 18:45:11'),
(23, 'tracker_projects', 2, '2013-03-06 14:12:56', 1, '2013-03-16 18:45:11'),
(24, 'tracker_projects', 3, '2013-03-06 14:21:32', 1, '2013-03-16 18:45:11'),
(25, 'tracker_tasks', 4, '2013-03-16 18:47:31', 1, '2013-03-16 18:47:33'),
(26, 'tracker_assigned', 5, '2013-03-16 18:47:31', 1, '2013-03-16 18:47:33'),
(27, 'tracker_tasks', 5, '2013-03-16 18:54:55', 1, '2013-03-16 18:54:58'),
(28, 'tracker_assigned', 6, '2013-03-16 18:54:55', 1, '2013-03-16 18:54:58'),
(29, 'tracker_tasks', 4, '2013-03-18 13:08:37', 1, '2013-03-18 13:08:41'),
(30, 'tracker_tasks', 6, '2013-03-18 15:36:01', 1, '2013-03-18 15:38:38'),
(31, 'tracker_assigned', 7, '2013-03-18 15:36:01', 1, '2013-03-18 15:38:38'),
(32, 'tracker_tasks', 6, '2013-03-18 15:39:00', 1, '2013-03-18 15:39:03'),
(33, 'tracker_assigned', 8, '2013-03-18 15:39:01', 1, '2013-03-18 15:39:03'),
(34, 'tracker_assigned', 9, '2013-03-18 15:39:01', 1, '2013-03-18 15:39:03'),
(35, 'tracker_tasks', 6, '2013-03-18 18:11:06', 1, '2013-03-18 18:11:09'),
(36, 'tracker_tasks', 6, '2013-03-18 18:13:16', 1, '2013-03-18 18:13:18'),
(37, 'tracker_tasks', 6, '2013-03-18 18:13:20', 1, '2013-03-18 18:13:21'),
(38, 'tracker_tasks', 6, '2013-03-18 18:13:52', 1, '2013-03-18 18:13:54'),
(39, 'tracker_tasks', 6, '2013-03-18 18:25:32', 1, '2013-03-18 18:25:34');

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_tasks`
--

CREATE TABLE IF NOT EXISTS `tracker_tasks` (
  `task_id` int(10) NOT NULL AUTO_INCREMENT,
  `project_id` int(3) NOT NULL DEFAULT '0',
  `task_type` int(3) NOT NULL DEFAULT '0',
  `date_opened` int(11) NOT NULL DEFAULT '0',
  `opened_by` int(3) NOT NULL DEFAULT '0',
  `is_closed` int(1) NOT NULL DEFAULT '0',
  `date_closed` int(11) NOT NULL DEFAULT '0',
  `closed_by` int(3) NOT NULL DEFAULT '0',
  `closure_comment` text COLLATE utf8_turkish_ci,
  `item_summary` varchar(100) COLLATE utf8_turkish_ci NOT NULL,
  `detailed_desc` text COLLATE utf8_turkish_ci,
  `item_status` int(3) NOT NULL DEFAULT '0',
  `resolution_reason` int(3) NOT NULL DEFAULT '1',
  `product_category` int(3) NOT NULL DEFAULT '0',
  `product_version` int(3) NOT NULL DEFAULT '0',
  `closedby_version` int(3) NOT NULL DEFAULT '0',
  `operating_system` int(3) NOT NULL DEFAULT '0',
  `task_severity` int(3) NOT NULL DEFAULT '0',
  `task_priority` int(3) NOT NULL DEFAULT '0',
  `last_edited_by` int(3) NOT NULL DEFAULT '0',
  `last_edited_time` int(11) NOT NULL DEFAULT '0',
  `percent_complete` int(3) NOT NULL DEFAULT '0',
  `mark_private` int(1) NOT NULL DEFAULT '0',
  `due_date` int(11) NOT NULL DEFAULT '0',
  `anon_email` varchar(100) COLLATE utf8_turkish_ci NOT NULL DEFAULT '',
  `task_token` varchar(32) COLLATE utf8_turkish_ci NOT NULL DEFAULT '0',
  `version` int(15) NOT NULL DEFAULT '0',
  `address` text COLLATE utf8_turkish_ci,
  `city_id` int(10) DEFAULT NULL,
  `latitude` varchar(200) COLLATE utf8_turkish_ci DEFAULT NULL,
  `longitude` varchar(200) COLLATE utf8_turkish_ci DEFAULT NULL,
  PRIMARY KEY (`task_id`),
  KEY `tracker_attached_to_project` (`project_id`),
  KEY `tracker_task_severity` (`task_severity`),
  KEY `tracker_task_type` (`task_type`),
  KEY `tracker_product_category` (`product_category`),
  KEY `tracker_item_status` (`item_status`),
  KEY `tracker_is_closed` (`is_closed`),
  KEY `tracker_closedby_version` (`closedby_version`),
  KEY `tracker_due_date` (`due_date`),
  KEY `project_id` (`project_id`),
  KEY `task_type` (`task_type`),
  KEY `opened_by` (`opened_by`),
  KEY `closed_by` (`closed_by`),
  KEY `item_status` (`item_status`),
  KEY `resolution_reason` (`resolution_reason`),
  KEY `product_category` (`product_category`),
  KEY `product_version` (`product_version`),
  KEY `closedby_version` (`closedby_version`),
  KEY `operating_system` (`operating_system`),
  KEY `task_severity` (`task_severity`),
  KEY `task_priority` (`task_priority`),
  KEY `last_edited_by` (`last_edited_by`),
  KEY `percent_complete` (`percent_complete`),
  KEY `city_id` (`city_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=7 ;

--
-- Tablo döküm verisi `tracker_tasks`
--

INSERT INTO `tracker_tasks` (`task_id`, `project_id`, `task_type`, `date_opened`, `opened_by`, `is_closed`, `date_closed`, `closed_by`, `closure_comment`, `item_summary`, `detailed_desc`, `item_status`, `resolution_reason`, `product_category`, `product_version`, `closedby_version`, `operating_system`, `task_severity`, `task_priority`, `last_edited_by`, `last_edited_time`, `percent_complete`, `mark_private`, `due_date`, `anon_email`, `task_token`, `version`, `address`, `city_id`, `latitude`, `longitude`) VALUES
(0, 0, 1, 1130024797, 1, 1, 0, 0, ' ', 'Sample Task', 'This isn''t a real task.  You should close it and start opening some real tasks.', 2, 1, 1, 1, 0, 1, 1, 2, 0, 0, 0, 0, 0, '', '0', 0, NULL, NULL, NULL, NULL),
(1, 1, 1, 1130024797, 1, 0, 0, 0, ' ', 'Sample Task', 'This isn''t a real task.  You should close it and start opening some real tasks.', 2, 1, 1, 1, 0, 1, 1, 2, 0, 0, 0, 0, 0, '', '0', 0, NULL, NULL, NULL, NULL),
(2, 1, 2, 1351463454, 1, 1, 1352039709, 1, '', 'Koray sokakta arıza oluştu', 'deneme bir arıza oluştu 2', 5, 5, 1, 1, 0, 1, 2, 2, 1, 1352039709, 100, 0, 0, '', '0', 1, NULL, NULL, NULL, NULL),
(3, 1, 1, 1353527231, 1, 0, 0, 0, '', 'Su tesisat güncellemsi', 'Su tesisat güncellemsi yapıldılan', 2, 1, 1, 1, 0, 1, 2, 2, 0, 1353527233, 0, 0, 0, '', '0', 3, NULL, NULL, NULL, NULL),
(4, 3, 1, 1363484851, 1, 0, 0, 0, '', 'elektrik kesintisi', 'elektrik kesintisi', 5, 3, 9, 3, 0, 6, 2, 2, 0, 1363484851, 0, 0, 0, '', '0', 1, NULL, NULL, NULL, NULL),
(5, 3, 1, 1363485295, 1, 0, 0, 0, '', 'ankara etimesgut elektrik kesintisi', 'ankara etimesgut elektrik kesintisi', 2, 1, 9, 3, 0, 6, 2, 2, 0, 1363485295, 0, 0, 0, '', '0', 0, NULL, NULL, NULL, NULL),
(6, 1, 1, 1363646161, 1, 0, 0, 0, '', 'Ankara''n?n Etimesgut ilçesine  elektrik kesintisi', 'Ankara''n?n Etimesgut ilçesine  elektrik kesintisi', 3, 3, 1, 1, 0, 1, 2, 2, 1, 1363646340, 0, 0, 0, '', '0', 6, NULL, NULL, NULL, NULL);

--
-- Tetiklemeler `tracker_tasks`
--
DROP TRIGGER IF EXISTS `tasks_insert_log_trigger`;
DELIMITER //
CREATE TRIGGER `tasks_insert_log_trigger` AFTER INSERT ON `tracker_tasks`
 FOR EACH ROW BEGIN INSERT INTO tracker_table_change_log (table_name, table_row_id) VALUES ('tracker_tasks',NEW.task_id);

END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `tasks_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `tasks_version_inc_trigger` BEFORE UPDATE ON `tracker_tasks`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 

END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `tasks_update_log_trigger`;
DELIMITER //
CREATE TRIGGER `tasks_update_log_trigger` AFTER UPDATE ON `tracker_tasks`
 FOR EACH ROW BEGIN INSERT INTO tracker_table_change_log (table_name, table_row_id) VALUES ('tracker_tasks',NEW.task_id);

END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_users`
--

CREATE TABLE IF NOT EXISTS `tracker_users` (
  `user_id` int(3) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) COLLATE utf8_turkish_ci NOT NULL,
  `user_pass` varchar(40) COLLATE utf8_turkish_ci DEFAULT NULL,
  `real_name` varchar(100) COLLATE utf8_turkish_ci NOT NULL,
  `jabber_id` varchar(100) COLLATE utf8_turkish_ci NOT NULL,
  `email_address` varchar(100) COLLATE utf8_turkish_ci NOT NULL,
  `notify_type` int(1) NOT NULL DEFAULT '0',
  `notify_own` int(6) NOT NULL DEFAULT '0',
  `account_enabled` int(1) NOT NULL DEFAULT '0',
  `dateformat` varchar(30) COLLATE utf8_turkish_ci NOT NULL DEFAULT '',
  `dateformat_extended` varchar(30) COLLATE utf8_turkish_ci NOT NULL DEFAULT '',
  `magic_url` varchar(40) COLLATE utf8_turkish_ci NOT NULL DEFAULT '',
  `tasks_perpage` int(3) NOT NULL DEFAULT '0',
  `register_date` int(11) NOT NULL DEFAULT '0',
  `time_zone` int(6) NOT NULL DEFAULT '0',
  `login_attempts` int(11) NOT NULL DEFAULT '0',
  `lock_until` int(11) NOT NULL DEFAULT '0',
  `version` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `tracker_user_name` (`user_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=6 ;

--
-- Tablo döküm verisi `tracker_users`
--

INSERT INTO `tracker_users` (`user_id`, `user_name`, `user_pass`, `real_name`, `jabber_id`, `email_address`, `notify_type`, `notify_own`, `account_enabled`, `dateformat`, `dateformat_extended`, `magic_url`, `tasks_perpage`, `register_date`, `time_zone`, `login_attempts`, `lock_until`, `version`) VALUES
(0, 'default', '1cb3c10b73358bdb8c7c5548da688de1', 'default', '', 'mehmet2aktas@yahoo.com', 1, 0, 0, '', '', '', 0, 1352034716, 0, 0, 0, 1),
(1, 'mehmet2aktas', '1cb3c10b73358bdb8c7c5548da688de1', 'Mr Super User', 'super@example.com', 'mehmet2aktas@gmail.com', 0, 1, 1, '', '', '', 0, 0, 0, 0, 0, 15),
(2, 'kemal', '1cb3c10b73358bdb8c7c5548da688de1', 'kemal', '', 'mehmet2aktas@yahoo.com', 1, 0, 1, '', '', '', 25, 1352034716, 0, 0, 0, 7),
(3, 'maktas', '1cb3c10b73358bdb8c7c5548da688de1', 'maktas', '', 'maktas@yahoo.com@yah', 0, 0, 1, '', '', '', 25, 1359239239, 0, 0, 0, 5),
(4, 'enerjisa', '1cb3c10b73358bdb8c7c5548da688de1', 'enerjisa çalışanı', '', 'enerjisa@enerjisa.com.tr', 3, 0, 1, '', '', '', 25, 1362607912, 2, 0, 0, 2),
(5, 'basic', '1cb3c10b73358bdb8c7c5548da688de1', 'enerjisa çalışanı', '', 'enerjisa@enerjisa.com.tr', 0, 0, 1, '', '', '', 25, 1362608733, 0, 0, 0, 3);

--
-- Tetiklemeler `tracker_users`
--
DROP TRIGGER IF EXISTS `users_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `users_version_inc_trigger` BEFORE UPDATE ON `tracker_users`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_users_in_groups`
--

CREATE TABLE IF NOT EXISTS `tracker_users_in_groups` (
  `record_id` int(5) NOT NULL AUTO_INCREMENT,
  `user_id` int(5) NOT NULL DEFAULT '0',
  `group_id` int(3) NOT NULL DEFAULT '0',
  `version` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`record_id`),
  UNIQUE KEY `tracker_group_id_uig` (`group_id`,`user_id`),
  KEY `tracker_user_id_uig` (`user_id`),
  KEY `user_id` (`user_id`),
  KEY `group_id` (`group_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=7 ;

--
-- Tablo döküm verisi `tracker_users_in_groups`
--

INSERT INTO `tracker_users_in_groups` (`record_id`, `user_id`, `group_id`, `version`) VALUES
(1, 1, 1, 0),
(2, 2, 4, 0),
(3, 3, 4, 1),
(4, 4, 2, 0),
(5, 4, 8, 0),
(6, 5, 4, 0);

--
-- Tetiklemeler `tracker_users_in_groups`
--
DROP TRIGGER IF EXISTS `users_in_groups_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `users_in_groups_version_inc_trigger` BEFORE UPDATE ON `tracker_users_in_groups`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_user_location_log`
--

CREATE TABLE IF NOT EXISTS `tracker_user_location_log` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `longitude` varchar(200) COLLATE utf8_turkish_ci NOT NULL,
  `latitude` varchar(200) COLLATE utf8_turkish_ci NOT NULL,
  `create_date` int(11) NOT NULL,
  `location_get_type_id` int(10) NOT NULL,
  `deviation_share` varchar(100) COLLATE utf8_turkish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`,`location_get_type_id`),
  KEY `location_get_type_id` (`location_get_type_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=7 ;

--
-- Tablo döküm verisi `tracker_user_location_log`
--

INSERT INTO `tracker_user_location_log` (`id`, `user_id`, `longitude`, `latitude`, `create_date`, `location_get_type_id`, `deviation_share`) VALUES
(5, 1, '345345435', '546546', 1357427050, 3, '546546'),
(6, 1, '345345435', '546546', 1357427184, 3, '546546');

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_user_mobile_setting`
--

CREATE TABLE IF NOT EXISTS `tracker_user_mobile_setting` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `location_get_type_id` int(10) NOT NULL,
  `coordinat_post_interval` int(20) NOT NULL,
  `is_sent_location` int(1) NOT NULL,
  `version` int(15) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`,`location_get_type_id`),
  KEY `location_get_type_id` (`location_get_type_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=2 ;

--
-- Tablo döküm verisi `tracker_user_mobile_setting`
--

INSERT INTO `tracker_user_mobile_setting` (`id`, `user_id`, `location_get_type_id`, `coordinat_post_interval`, `is_sent_location`, `version`) VALUES
(1, 1, 1, 3003, 1, 2);

--
-- Tetiklemeler `tracker_user_mobile_setting`
--
DROP TRIGGER IF EXISTS `user_mobile_setting_insert_log_trigger`;
DELIMITER //
CREATE TRIGGER `user_mobile_setting_insert_log_trigger` AFTER INSERT ON `tracker_user_mobile_setting`
 FOR EACH ROW BEGIN INSERT INTO tracker_table_change_log (table_name, table_row_id) VALUES ('tracker_user_mobile_setting',NEW.id);

END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `user_mobile_setting_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `user_mobile_setting_version_inc_trigger` BEFORE UPDATE ON `tracker_user_mobile_setting`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `user_mobile_setting_update_log_trigger`;
DELIMITER //
CREATE TRIGGER `user_mobile_setting_update_log_trigger` AFTER UPDATE ON `tracker_user_mobile_setting`
 FOR EACH ROW BEGIN INSERT INTO tracker_table_change_log (table_name, table_row_id) VALUES ('tracker_user_mobile_setting',NEW.id);

END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_user_session`
--

CREATE TABLE IF NOT EXISTS `tracker_user_session` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `token` varchar(200) COLLATE utf8_turkish_ci NOT NULL,
  `create_date` int(11) NOT NULL,
  `update_date` int(11) DEFAULT NULL,
  `expire_date` int(11) NOT NULL,
  `device_register_key` varchar(200) COLLATE utf8_turkish_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=57 ;

--
-- Tablo döküm verisi `tracker_user_session`
--

INSERT INTO `tracker_user_session` (`id`, `user_id`, `token`, `create_date`, `update_date`, `expire_date`, `device_register_key`) VALUES
(47, 1, '02716612-f16f-4e58-b50d-d91b54414ce3', 1363484469, 1363484473, 1393484473, 'APA91bHlMVf1UG7pZHgpvjPm896tLRV_JrjAU5udX_6B7Jz2f-Z6RQy0oH1-ha4i7NECAflxRnauS2YnIK0tYVAHuI7PFtl6Fv_A75ZY2PVDmzF7OjNdDHM0UrVf30LuMGmYRBO5dqpoqJUceXffYOJztxy2jQv93w'),
(48, 2, 'f04c69d0-c31b-47e3-9e11-04ae16d31c84', 1363484549, 1363484712, 1393484712, 'APA91bHlMVf1UG7pZHgpvjPm896tLRV_JrjAU5udX_6B7Jz2f-Z6RQy0oH1-ha4i7NECAflxRnauS2YnIK0tYVAHuI7PFtl6Fv_A75ZY2PVDmzF7OjNdDHM0UrVf30LuMGmYRBO5dqpoqJUceXffYOJztxy2jQv93w'),
(49, 4, 'd31e7553-bfd3-40c6-bad1-d3b532411ce8', 1363485243, 1363485301, 1393485301, 'APA91bHlMVf1UG7pZHgpvjPm896tLRV_JrjAU5udX_6B7Jz2f-Z6RQy0oH1-ha4i7NECAflxRnauS2YnIK0tYVAHuI7PFtl6Fv_A75ZY2PVDmzF7OjNdDHM0UrVf30LuMGmYRBO5dqpoqJUceXffYOJztxy2jQv93w'),
(50, 1, 'dd433870-5b63-4575-8186-69f8050527eb', 1363485329, 1363485408, 1393485408, 'APA91bHlMVf1UG7pZHgpvjPm896tLRV_JrjAU5udX_6B7Jz2f-Z6RQy0oH1-ha4i7NECAflxRnauS2YnIK0tYVAHuI7PFtl6Fv_A75ZY2PVDmzF7OjNdDHM0UrVf30LuMGmYRBO5dqpoqJUceXffYOJztxy2jQv93w'),
(51, 1, '7d420603-4a67-4ff5-8434-2a009d4e6a2f', 1363485428, 1363485433, 1393485433, 'APA91bHlMVf1UG7pZHgpvjPm896tLRV_JrjAU5udX_6B7Jz2f-Z6RQy0oH1-ha4i7NECAflxRnauS2YnIK0tYVAHuI7PFtl6Fv_A75ZY2PVDmzF7OjNdDHM0UrVf30LuMGmYRBO5dqpoqJUceXffYOJztxy2jQv93w'),
(52, 1, '40829e1c-5f3a-4921-aa04-c55b04f74741', 1363637166, 1363637170, 1393637170, ''),
(53, 4, '36b61374-6d89-40f3-b91d-36acee58ebdf', 1363637255, 1363646107, 1393646107, 'APA91bHlMVf1UG7pZHgpvjPm896tLRV_JrjAU5udX_6B7Jz2f-Z6RQy0oH1-ha4i7NECAflxRnauS2YnIK0tYVAHuI7PFtl6Fv_A75ZY2PVDmzF7OjNdDHM0UrVf30LuMGmYRBO5dqpoqJUceXffYOJztxy2jQv93w'),
(54, 1, 'ecea0424-fad5-405d-95e2-e3551dde4df6', 1363646239, 1363646246, 1393646246, 'APA91bHlMVf1UG7pZHgpvjPm896tLRV_JrjAU5udX_6B7Jz2f-Z6RQy0oH1-ha4i7NECAflxRnauS2YnIK0tYVAHuI7PFtl6Fv_A75ZY2PVDmzF7OjNdDHM0UrVf30LuMGmYRBO5dqpoqJUceXffYOJztxy2jQv93w'),
(55, 4, '5491a15f-f408-49d7-a6bb-fd8eaad23341', 1363646365, 1363655632, 1393655632, 'APA91bHlMVf1UG7pZHgpvjPm896tLRV_JrjAU5udX_6B7Jz2f-Z6RQy0oH1-ha4i7NECAflxRnauS2YnIK0tYVAHuI7PFtl6Fv_A75ZY2PVDmzF7OjNdDHM0UrVf30LuMGmYRBO5dqpoqJUceXffYOJztxy2jQv93w'),
(56, 1, '5fdadde1-984e-4d90-8fb4-b6169988caf5', 1363655647, 1363656881, 1393656881, 'APA91bHlMVf1UG7pZHgpvjPm896tLRV_JrjAU5udX_6B7Jz2f-Z6RQy0oH1-ha4i7NECAflxRnauS2YnIK0tYVAHuI7PFtl6Fv_A75ZY2PVDmzF7OjNdDHM0UrVf30LuMGmYRBO5dqpoqJUceXffYOJztxy2jQv93w');

-- --------------------------------------------------------

--
-- Tablo yapısı: `tracker_votes`
--

CREATE TABLE IF NOT EXISTS `tracker_votes` (
  `vote_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `task_id` int(11) NOT NULL DEFAULT '0',
  `date_time` int(11) NOT NULL DEFAULT '0',
  `version` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`vote_id`),
  KEY `tracker_task_id_votes` (`task_id`),
  KEY `user_id` (`user_id`),
  KEY `task_id` (`task_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci AUTO_INCREMENT=2 ;

--
-- Tablo döküm verisi `tracker_votes`
--

INSERT INTO `tracker_votes` (`vote_id`, `user_id`, `task_id`, `date_time`, `version`) VALUES
(1, 1, 3, 0, 0);

--
-- Tetiklemeler `tracker_votes`
--
DROP TRIGGER IF EXISTS `votes_insert_log_trigger`;
DELIMITER //
CREATE TRIGGER `votes_insert_log_trigger` AFTER INSERT ON `tracker_votes`
 FOR EACH ROW BEGIN INSERT INTO tracker_table_change_log (table_name, table_row_id) VALUES ('tracker_votes',NEW.vote_id);

END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `votes_version_inc_trigger`;
DELIMITER //
CREATE TRIGGER `votes_version_inc_trigger` BEFORE UPDATE ON `tracker_votes`
 FOR EACH ROW BEGIN
set  NEW.version = ( OLD.version +1 ); 
END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `votes_update_log_trigger`;
DELIMITER //
CREATE TRIGGER `votes_update_log_trigger` AFTER UPDATE ON `tracker_votes`
 FOR EACH ROW BEGIN INSERT INTO tracker_table_change_log (table_name, table_row_id) VALUES ('tracker_votes',NEW.vote_id);

END
//
DELIMITER ;

--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `tracker_admin_requests`
--
ALTER TABLE `tracker_admin_requests`
  ADD CONSTRAINT `tracker_admin_requests_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `tracker_projects` (`project_id`),
  ADD CONSTRAINT `tracker_admin_requests_ibfk_2` FOREIGN KEY (`task_id`) REFERENCES `tracker_tasks` (`task_id`),
  ADD CONSTRAINT `tracker_admin_requests_ibfk_3` FOREIGN KEY (`submitted_by`) REFERENCES `tracker_users` (`user_id`),
  ADD CONSTRAINT `tracker_admin_requests_ibfk_4` FOREIGN KEY (`resolved_by`) REFERENCES `tracker_users` (`user_id`);

--
-- Tablo kısıtlamaları `tracker_assigned`
--
ALTER TABLE `tracker_assigned`
  ADD CONSTRAINT `tracker_assigned_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `tracker_tasks` (`task_id`),
  ADD CONSTRAINT `tracker_assigned_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `tracker_users` (`user_id`);

--
-- Tablo kısıtlamaları `tracker_assigned_old`
--
ALTER TABLE `tracker_assigned_old`
  ADD CONSTRAINT `tracker_assigned_old_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `tracker_tasks` (`task_id`),
  ADD CONSTRAINT `tracker_assigned_old_ibfk_2` FOREIGN KEY (`old_user_id`) REFERENCES `tracker_users` (`user_id`),
  ADD CONSTRAINT `tracker_assigned_old_ibfk_3` FOREIGN KEY (`assigned_id`) REFERENCES `tracker_assigned` (`assigned_id`);

--
-- Tablo kısıtlamaları `tracker_attachments`
--
ALTER TABLE `tracker_attachments`
  ADD CONSTRAINT `tracker_attachments_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `tracker_tasks` (`task_id`),
  ADD CONSTRAINT `tracker_attachments_ibfk_2` FOREIGN KEY (`comment_id`) REFERENCES `tracker_comments` (`comment_id`),
  ADD CONSTRAINT `tracker_attachments_ibfk_3` FOREIGN KEY (`added_by`) REFERENCES `tracker_users` (`user_id`);

--
-- Tablo kısıtlamaları `tracker_cache`
--
ALTER TABLE `tracker_cache`
  ADD CONSTRAINT `tracker_cache_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `tracker_list_version` (`project_id`);

--
-- Tablo kısıtlamaları `tracker_comments`
--
ALTER TABLE `tracker_comments`
  ADD CONSTRAINT `tracker_comments_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `tracker_tasks` (`task_id`),
  ADD CONSTRAINT `tracker_comments_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `tracker_users` (`user_id`);

--
-- Tablo kısıtlamaları `tracker_dependencies`
--
ALTER TABLE `tracker_dependencies`
  ADD CONSTRAINT `tracker_dependencies_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `tracker_tasks` (`task_id`),
  ADD CONSTRAINT `tracker_dependencies_ibfk_2` FOREIGN KEY (`dep_task_id`) REFERENCES `tracker_tasks` (`task_id`);

--
-- Tablo kısıtlamaları `tracker_groups`
--
ALTER TABLE `tracker_groups`
  ADD CONSTRAINT `tracker_groups_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `tracker_projects` (`project_id`);

--
-- Tablo kısıtlamaları `tracker_history`
--
ALTER TABLE `tracker_history`
  ADD CONSTRAINT `tracker_history_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tracker_users` (`user_id`),
  ADD CONSTRAINT `tracker_history_ibfk_2` FOREIGN KEY (`task_id`) REFERENCES `tracker_tasks` (`task_id`);

--
-- Tablo kısıtlamaları `tracker_list_category`
--
ALTER TABLE `tracker_list_category`
  ADD CONSTRAINT `tracker_list_category_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `tracker_projects` (`project_id`),
  ADD CONSTRAINT `tracker_list_category_ibfk_2` FOREIGN KEY (`category_owner`) REFERENCES `tracker_users` (`user_id`);

--
-- Tablo kısıtlamaları `tracker_list_os`
--
ALTER TABLE `tracker_list_os`
  ADD CONSTRAINT `tracker_list_os_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `tracker_projects` (`project_id`);

--
-- Tablo kısıtlamaları `tracker_list_resolution`
--
ALTER TABLE `tracker_list_resolution`
  ADD CONSTRAINT `tracker_list_resolution_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `tracker_projects` (`project_id`);

--
-- Tablo kısıtlamaları `tracker_list_status`
--
ALTER TABLE `tracker_list_status`
  ADD CONSTRAINT `tracker_list_status_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `tracker_projects` (`project_id`);

--
-- Tablo kısıtlamaları `tracker_list_tasktype`
--
ALTER TABLE `tracker_list_tasktype`
  ADD CONSTRAINT `tracker_list_tasktype_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `tracker_projects` (`project_id`);

--
-- Tablo kısıtlamaları `tracker_list_version`
--
ALTER TABLE `tracker_list_version`
  ADD CONSTRAINT `tracker_list_version_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `tracker_projects` (`project_id`);

--
-- Tablo kısıtlamaları `tracker_notifications`
--
ALTER TABLE `tracker_notifications`
  ADD CONSTRAINT `tracker_notifications_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `tracker_tasks` (`task_id`),
  ADD CONSTRAINT `tracker_notifications_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `tracker_users` (`user_id`);

--
-- Tablo kısıtlamaları `tracker_notification_recipients`
--
ALTER TABLE `tracker_notification_recipients`
  ADD CONSTRAINT `tracker_notification_recipients_ibfk_1` FOREIGN KEY (`message_id`) REFERENCES `tracker_notification_messages` (`message_id`);

--
-- Tablo kısıtlamaları `tracker_projects`
--
ALTER TABLE `tracker_projects`
  ADD CONSTRAINT `tracker_projects_ibfk_1` FOREIGN KEY (`default_cat_owner`) REFERENCES `tracker_users` (`user_id`);

--
-- Tablo kısıtlamaları `tracker_related`
--
ALTER TABLE `tracker_related`
  ADD CONSTRAINT `tracker_related_ibfk_1` FOREIGN KEY (`this_task`) REFERENCES `tracker_tasks` (`task_id`),
  ADD CONSTRAINT `tracker_related_ibfk_2` FOREIGN KEY (`related_task`) REFERENCES `tracker_tasks` (`task_id`);

--
-- Tablo kısıtlamaları `tracker_reminders`
--
ALTER TABLE `tracker_reminders`
  ADD CONSTRAINT `tracker_reminders_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `tracker_tasks` (`task_id`),
  ADD CONSTRAINT `tracker_reminders_ibfk_2` FOREIGN KEY (`to_user_id`) REFERENCES `tracker_users` (`user_id`),
  ADD CONSTRAINT `tracker_reminders_ibfk_3` FOREIGN KEY (`from_user_id`) REFERENCES `tracker_users` (`user_id`);

--
-- Tablo kısıtlamaları `tracker_searches`
--
ALTER TABLE `tracker_searches`
  ADD CONSTRAINT `tracker_searches_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tracker_users` (`user_id`);

--
-- Tablo kısıtlamaları `tracker_tasks`
--
ALTER TABLE `tracker_tasks`
  ADD CONSTRAINT `tracker_tasks_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `tracker_projects` (`project_id`),
  ADD CONSTRAINT `tracker_tasks_ibfk_10` FOREIGN KEY (`last_edited_by`) REFERENCES `tracker_users` (`user_id`),
  ADD CONSTRAINT `tracker_tasks_ibfk_11` FOREIGN KEY (`city_id`) REFERENCES `tracker_city` (`id`),
  ADD CONSTRAINT `tracker_tasks_ibfk_2` FOREIGN KEY (`task_type`) REFERENCES `tracker_list_tasktype` (`tasktype_id`),
  ADD CONSTRAINT `tracker_tasks_ibfk_3` FOREIGN KEY (`opened_by`) REFERENCES `tracker_users` (`user_id`),
  ADD CONSTRAINT `tracker_tasks_ibfk_4` FOREIGN KEY (`closed_by`) REFERENCES `tracker_users` (`user_id`),
  ADD CONSTRAINT `tracker_tasks_ibfk_5` FOREIGN KEY (`item_status`) REFERENCES `tracker_list_status` (`status_id`),
  ADD CONSTRAINT `tracker_tasks_ibfk_6` FOREIGN KEY (`resolution_reason`) REFERENCES `tracker_list_resolution` (`resolution_id`),
  ADD CONSTRAINT `tracker_tasks_ibfk_7` FOREIGN KEY (`product_category`) REFERENCES `tracker_list_category` (`category_id`),
  ADD CONSTRAINT `tracker_tasks_ibfk_8` FOREIGN KEY (`product_version`) REFERENCES `tracker_list_version` (`version_id`),
  ADD CONSTRAINT `tracker_tasks_ibfk_9` FOREIGN KEY (`operating_system`) REFERENCES `tracker_list_os` (`os_id`);

--
-- Tablo kısıtlamaları `tracker_users_in_groups`
--
ALTER TABLE `tracker_users_in_groups`
  ADD CONSTRAINT `tracker_users_in_groups_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tracker_users` (`user_id`),
  ADD CONSTRAINT `tracker_users_in_groups_ibfk_2` FOREIGN KEY (`group_id`) REFERENCES `tracker_groups` (`group_id`);

--
-- Tablo kısıtlamaları `tracker_user_location_log`
--
ALTER TABLE `tracker_user_location_log`
  ADD CONSTRAINT `tracker_user_location_log_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tracker_users` (`user_id`),
  ADD CONSTRAINT `tracker_user_location_log_ibfk_2` FOREIGN KEY (`location_get_type_id`) REFERENCES `tracker_location_get_type` (`id`);

--
-- Tablo kısıtlamaları `tracker_user_mobile_setting`
--
ALTER TABLE `tracker_user_mobile_setting`
  ADD CONSTRAINT `tracker_user_mobile_setting_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tracker_users` (`user_id`),
  ADD CONSTRAINT `tracker_user_mobile_setting_ibfk_2` FOREIGN KEY (`location_get_type_id`) REFERENCES `tracker_location_get_type` (`id`);

--
-- Tablo kısıtlamaları `tracker_user_session`
--
ALTER TABLE `tracker_user_session`
  ADD CONSTRAINT `tracker_user_session_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tracker_users` (`user_id`);

--
-- Tablo kısıtlamaları `tracker_votes`
--
ALTER TABLE `tracker_votes`
  ADD CONSTRAINT `tracker_votes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tracker_users` (`user_id`),
  ADD CONSTRAINT `tracker_votes_ibfk_2` FOREIGN KEY (`task_id`) REFERENCES `tracker_tasks` (`task_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
