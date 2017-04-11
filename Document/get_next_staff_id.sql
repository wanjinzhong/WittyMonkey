DROP PROCEDURE IF EXISTS get_next_staff_id;
DELIMITER //
CREATE PROCEDURE get_next_staff_id (IN hotel_id INT, OUT staff_no VARCHAR(20))
BEGIN
DECLARE curr_no INT;
IF (hotel_id IS NULL) THEN
SET staff_no = 0;
ELSE
UPDATE odom SET sequence = sequence + 1 WHERE hotel_id = hotel_id;
SELECT curr_no = sequence FROM odom WHERE hotel_id = hotel_id;
END IF;
SELECT staff_no;
END//
DELIMITER ;