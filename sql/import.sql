DROP DATABASE IF EXISTS fandango;
CREATE DATABASE fandango;
USE fandango;

CREATE TABLE USER
(
  user_id     INT PRIMARY KEY AUTO_INCREMENT,
  username    VARCHAR(20) UNIQUE NOT NULL,
  password    VARCHAR(100) NOT NULL,
  first_name  VARCHAR(30),
  last_name   VARCHAR(30),
  street     VARCHAR(100),
  city        VARCHAR(30),
  state       CHAR(2),
  zipcode     CHAR(5),
  phone       CHAR(15),
  email       VARCHAR(50) NOT NULL,
  image_url   VARCHAR(100),
  credit_card VARCHAR(20)
);

INSERT INTO USER
(username, password, first_name, last_name, street, city, state, zipcode, email,phone)
VALUES
('user1', '$2a$10$bNqsANQaxojDrovhLCF2DeaSxXKMA6l1iss/nzzBkS/SdhhtWCPT6','Daisy','Saulsberry','7769 Windfall St.','Cartersville', 'GA', '30120','kspiteri@verizon.net','(875)283-6533'),
('user2', '$2a$10$bNqsANQaxojDrovhLCF2DeaSxXKMA6l1iss/nzzBkS/SdhhtWCPT6','Tasia','Uhrig','433 Jones Court', 'Youngstown', 'OH', '44512','syrinx@me.com','(714)449-6291'),
('user3', '$2a$10$bNqsANQaxojDrovhLCF2DeaSxXKMA6l1iss/nzzBkS/SdhhtWCPT6', 'Albert','Blaschke','91 Hill Field Avenue', 'Cincinnati', 'OH', '45211','solomon@yahoo.ca','(970)156-4910'),
('user4', '$2a$10$bNqsANQaxojDrovhLCF2DeaSxXKMA6l1iss/nzzBkS/SdhhtWCPT6', 'Marquis','Reinhold','392 Yukon Dr.', 'Murfreesboro', 'TN', '37128','quantaman@comcast.net','(185) 782-9454');


-- test database

DROP DATABASE IF EXISTS fandango_test;
CREATE DATABASE fandango_test;
USE fandango_test;

CREATE TABLE USER LIKE fandango.USER;