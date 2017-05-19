DELIMITER $$

USE `wittymonkey`$$

DROP PROCEDURE IF EXISTS `get_next_staff_no`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `get_next_staff_no`(
		IN hotelId INT)
BEGIN
DECLARE curr_no INT;
DECLARE next_no INT;
DECLARE staffNo VARCHAR(20);
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
END$$

DELIMITER ;