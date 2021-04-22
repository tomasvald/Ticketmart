DELIMITER //
CREATE PROCEDURE update_database
(a INT(5), b INT(5))
BEGIN
   DECLARE i INT;
   DECLARE j INT;
   CREATE TABLE init_tickets
      (idTicket BIGINT(10), seatNumber VARCHAR(10), idStatus INT(10), idSection INT(10), idEvent INT(11));
   SET i=1;
   WHILE i <= 3 DO
      SET j=1;
      WHILE j <= 10 DO
         INSERT INTO init_tickets
         SELECT *
         FROM ticket
         WHERE idEvent = i
         AND idSection = j
         AND idStatus = 1
         AND LEFT (seatNumber, CHAR_LENGTH(seatNumber)-2) BETWEEN a AND b;
         SET j=j+1;
      END WHILE;
      SET i=i+1;
   END WHILE;
   SELECT * FROM init_tickets;
   DROP TABLE init_tickets;
END; //
DELIMITER ;

