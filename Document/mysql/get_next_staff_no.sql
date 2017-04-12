/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 5.7.16-log : Database - wittymonkey
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

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
