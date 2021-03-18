DROP DATABASE IF EXISTS spotitube_db;
CREATE DATABASE spotitube_db;

USE spotitube_db;

DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    PRIMARY KEY (username)
);


DROP TABLE IF EXISTS token;
CREATE TABLE token
(
    username    VARCHAR(50) NOT NULL,
    token       VARCHAR(50) NOT NULL,
    expiry_date DATETIME    NOT NULL,
    PRIMARY KEY (username),
    UNIQUE (token),
    CONSTRAINT FK_username
        FOREIGN KEY (username)
            REFERENCES spotitube_db.user (username)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

INSERT INTO user (username, password)
VALUES ('john', 'doe');