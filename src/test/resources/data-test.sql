USE fandango_test;

DELETE FROM USER;
ALTER TABLE USER AUTO_INCREMENT = 1;
INSERT INTO USER
(username, password, email)
VALUES
('user1', '$2a$10$bNqsANQaxojDrovhLCF2DeaSxXKMA6l1iss/nzzBkS/SdhhtWCPT6','kspiteri@verizon.net'),
('user2', '$2a$10$bNqsANQaxojDrovhLCF2DeaSxXKMA6l1iss/nzzBkS/SdhhtWCPT6','hello@gmail.com');
