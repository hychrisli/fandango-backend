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


CREATE TABLE GENRE
(
  genre_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  genre_name VARCHAR(30)
);

CREATE TABLE MPAA_RATING
(
  mpaa_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  mpaa_name VARCHAR(10)
);

CREATE TABLE FORMAT
(
  format_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  format_name VARCHAR(10)
);

CREATE TABLE MOVIE
(
  movie_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  movie_title VARCHAR(100) NOT NULL,
  movie_desc VARCHAR(500),
  trailer_url VARCHAR(200),
  cover_image_url VARCHAR(200),
  release_date DATE,
  mpaa_id INTEGER,
  mpaa_rating VARCHAR(10),
  length INTEGER,
  stars DECIMAL(2,1) DEFAULT 5.0,
  FOREIGN KEY (mpaa_id) REFERENCES MPAA_RATING(mpaa_id)
);


CREATE TABLE MOVIE_IMAGE
(
  image_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  movie_id INTEGER,
  image_title VARCHAR(50),
  image_desc VARCHAR(500),
  image_url VARCHAR(200),
  FOREIGN KEY (movie_id) REFERENCES MOVIE(movie_id)
);

CREATE TABLE MOVIE_CHARACTER
(
  character_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  movie_id INTEGER,
  character_name VARCHAR(50),
  FOREIGN KEY (movie_id) REFERENCES MOVIE(movie_id)
);

CREATE TABLE MOVIE_GENRE
(
  movie_genre_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  movie_id INTEGER,
  genre_id INTEGER,
  FOREIGN KEY (genre_id) REFERENCES GENRE(genre_id),
  FOREIGN KEY (movie_id) REFERENCES MOVIE(movie_id),
  UNIQUE movie_genre_uniq (genre_id, movie_id)
);

CREATE TABLE MOVIE_FORMAT
(
  movie_format_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  format_id INTEGER,
  movie_id INTEGER,
  FOREIGN KEY (format_id) REFERENCES FORMAT(format_id),
  FOREIGN KEY (movie_id) REFERENCES MOVIE(movie_id),
  UNIQUE movie_format_uniq (format_id, movie_id)
);


-- test database

DROP DATABASE IF EXISTS fandango_test;
CREATE DATABASE fandango_test;
USE fandango_test;

CREATE TABLE USER LIKE fandango.USER;