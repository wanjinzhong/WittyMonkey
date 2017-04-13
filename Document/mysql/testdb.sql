/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 5.7.17-log : Database - wittymonkey
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`wittymonkey` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `wittymonkey`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `entry_datetime` datetime DEFAULT NULL,
  `idcard_no` varchar(18) DEFAULT NULL,
  `login_name` varchar(20) DEFAULT NULL,
  `note` longtext,
  `password` varchar(24) DEFAULT NULL,
  `real_name` varchar(20) DEFAULT NULL,
  `regist_date` datetime DEFAULT NULL,
  `tel` varchar(15) DEFAULT NULL,
  `entry_id` int(11) DEFAULT NULL,
  `hotel_id` int(11) DEFAULT NULL,
  `setting_id` int(11) DEFAULT NULL,
  `dimission_date` datetime DEFAULT NULL,
  `dimission_note` longtext,
  `staff_no` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_f8h0du7wv1dynten8q0m8llgq` (`entry_id`),
  KEY `FK_5yd3ufcs396rbon7ypo62g5re` (`hotel_id`),
  KEY `FK_2tsc95h8mknd1ufnsyymmd6ny` (`setting_id`),
  CONSTRAINT `FK_2tsc95h8mknd1ufnsyymmd6ny` FOREIGN KEY (`setting_id`) REFERENCES `setting` (`id`),
  CONSTRAINT `FK_5yd3ufcs396rbon7ypo62g5re` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`),
  CONSTRAINT `FK_f8h0du7wv1dynten8q0m8llgq` FOREIGN KEY (`entry_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`email`,`entry_datetime`,`idcard_no`,`login_name`,`note`,`password`,`real_name`,`regist_date`,`tel`,`entry_id`,`hotel_id`,`setting_id`,`dimission_date`,`dimission_note`,`staff_no`) values (0,NULL,NULL,NULL,NULL,NULL,NULL,'System',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'1051750377@qq.com','2017-03-07 09:03:53',NULL,'',NULL,'6ZoYxCjLONXyYIU2eJIuAw==','李云飞','2017-03-07 09:03:53',NULL,0,1,1,NULL,NULL,'10001'),(3,'1141257374@qq.com','2017-03-07 09:05:17',NULL,'bst',NULL,'6ZoYxCjLONXyYIU2eJIuAw==','佰思特','2017-03-07 09:05:17',NULL,0,2,2,NULL,NULL,'20001'),(4,'105505@QQ.COM','2017-04-12 14:49:10','511181199409084218','10016',NULL,'witty','fds','2017-04-12 14:49:14','55',2,1,3,NULL,NULL,'10016'),(5,'1051@qq.com','2017-04-12 15:37:04','511181199409084218','10017',NULL,'ZwsUcorZkCrsujLiL6T2vQ==','dvn d','2017-04-12 15:37:04','158855',2,1,4,NULL,NULL,'10017'),(6,'100@qq.com','2017-04-12 16:03:47','511181199409084218',NULL,NULL,'ZwsUcorZkCrsujLiL6T2vQ==','dfs','2017-04-12 16:03:47','1221',2,1,5,NULL,NULL,'10018'),(7,'','2017-04-12 16:21:38','511181199409084218',NULL,NULL,'ZwsUcorZkCrsujLiL6T2vQ==','fsd','2017-04-12 16:21:38','',2,1,6,NULL,NULL,'10019'),(8,'1051750377@qq.com','2017-04-13 15:38:06','511181199409084218',NULL,NULL,'ZwsUcorZkCrsujLiL6T2vQ==','万进忠','2017-04-13 15:38:06','15881193175',2,1,7,NULL,NULL,'10020'),(9,'105@abc.com','2017-04-13 23:12:59','511181199409084218',NULL,NULL,'ZwsUcorZkCrsujLiL6T2vQ==','abcde','2017-04-13 22:29:20','15881193175',2,1,8,'2017-04-14 00:30:01','工作失误被开除了','10021');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `FK_it77eq964jhfqtu54081ebtio` (`role_id`),
  KEY `FK_apcc8lxk2xnug8377fatvbn04` (`user_id`),
  CONSTRAINT `FK_apcc8lxk2xnug8377fatvbn04` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_it77eq964jhfqtu54081ebtio` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`user_id`,`role_id`) values (2,1),(3,2),(8,8),(9,7),(9,10);

/* Procedure structure for procedure `get_next_staff_id` */

/*!50003 DROP PROCEDURE IF EXISTS  `get_next_staff_id` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `get_next_staff_id`(
		IN hotelId INT)
BEGIN
DECLARE curr_no INT;
DECLARE next_no INT;
declare staffNo VARCHAR(20);
IF (hotelId IS NULL) THEN
	SET curr_no = -1;
ELSE
	SET curr_no = (SELECT sequence
	FROM odom
	WHERE hotel_id = hotelId);
	IF (curr_no IS NULL) THEN
		SET curr_no = -1;
	ELSE
		UPDATE odom
		SET sequence = sequence + 1
		WHERE hotel_id = hotelId;
	
		SET next_no = (SELECT sequence
		FROM odom
		WHERE hotel_id = hotelId);
	END IF;
END IF;
IF (curr_no < 0) THEN
	SET staffNo = '-1';
ELSE
	SET staffNo = hotelId;
	IF (next_no < 10) THEN
		SET staffNo = CONCAT(staffNo,'000', next_no);
	ELSEIF (next_no < 100) THEN
		SET staffNo = CONCAT(staffNo,'00', next_no);
	ELSEIF (next_no < 1000) THEN
		SET staffNo = CONCAT(staffNo,'0', next_no);
	ELSE
		SET staffNo = CONCAT(staffNo, next_no);
	END IF;
END IF;
SELECT staffNo AS staff_no;
END */$$
DELIMITER ;

/* Procedure structure for procedure `ju` */

/*!50003 DROP PROCEDURE IF EXISTS  `ju` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `ju`()
BEGIN
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
