DROP DATABASE IF EXISTS spotitube_db;
CREATE DATABASE spotitube_db;

USE spotitube_db;

DROP TABLE IF EXISTS user;
CREATE TABLE user
(
  id INT NOT NULL auto_increment PRIMARY KEY,
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(50) NOT NULL,
  token VARCHAR(50) UNIQUE
);

INSERT INTO user (username, password) VALUES ('john', 'doe');

