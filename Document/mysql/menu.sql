/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 5.7.16-log 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `menu` (
	`id` int (11),
	`description` varchar (765),
	`entry_datetime` datetime ,
	`name` varchar (60),
	`entry_id` int (11)
); 
insert into `menu` (`id`, `description`, `entry_datetime`, `name`, `entry_id`) values('1','floor',NULL,'floor',NULL);
insert into `menu` (`id`, `description`, `entry_datetime`, `name`, `entry_id`) values('2','room',NULL,'room',NULL);
insert into `menu` (`id`, `description`, `entry_datetime`, `name`, `entry_id`) values('3','materiel',NULL,'materiel',NULL);
insert into `menu` (`id`, `description`, `entry_datetime`, `name`, `entry_id`) values('4','inventory',NULL,'inventory',NULL);
insert into `menu` (`id`, `description`, `entry_datetime`, `name`, `entry_id`) values('5','role',NULL,'role',NULL);
insert into `menu` (`id`, `description`, `entry_datetime`, `name`, `entry_id`) values('6','staff',NULL,'staff',NULL);
insert into `menu` (`id`, `description`, `entry_datetime`, `name`, `entry_id`) values('7','leave',NULL,'leave',NULL);
insert into `menu` (`id`, `description`, `entry_datetime`, `name`, `entry_id`) values('8','finance',NULL,'finance',NULL);
insert into `menu` (`id`, `description`, `entry_datetime`, `name`, `entry_id`) values('9','report',NULL,'report',NULL);
insert into `menu` (`id`, `description`, `entry_datetime`, `name`, `entry_id`) values('10','notify',NULL,'notify',NULL);
insert into `menu` (`id`, `description`, `entry_datetime`, `name`, `entry_id`) values('11','hotel_info',NULL,'hotel_info',NULL);
insert into `menu` (`id`, `description`, `entry_datetime`, `name`, `entry_id`) values('12','settings',NULL,'settings',NULL);
