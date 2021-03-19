DROP DATABASE IF EXISTS spotitube_db;
CREATE DATABASE spotitube_db;
USE spotitube_db;

DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (username)
);


DROP TABLE IF EXISTS token;
CREATE TABLE token
(
    username    VARCHAR(255) NOT NULL,
    token       VARCHAR(255) NOT NULL,
    expiry_date DATETIME     NOT NULL,
    PRIMARY KEY (username),
    UNIQUE (token),
    CONSTRAINT FK_username
        FOREIGN KEY (username) REFERENCES spotitube_db.user (username)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

DROP TABLE IF EXISTS track;
CREATE TABLE track
(
    id                INT          NOT NULL AUTO_INCREMENT,
    title             VARCHAR(255) NOT NULL,
    performer         VARCHAR(255) NOT NULL,
    duration          INT          NOT NULL,
    album             VARCHAR(255) NOT NULL,
    play_count        INT,
    publication_date  VARCHAR(255),
    description       VARCHAR(255),
    offline_available BOOLEAN,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS playlist;
CREATE TABLE playlist
(
    id    INT          NOT NULL AUTO_INCREMENT,
    name  VARCHAR(255) NOT NULL,
    owner VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_owner
        FOREIGN KEY (owner) REFERENCES spotitube_db.user (username)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

DROP TABLE IF EXISTS playlist_track;
CREATE TABLE playlist_track
(
    playlist_id INT NOT NULL,
    track_id    INT NOT NULL,
    PRIMARY KEY (track_id, playlist_id),
    CONSTRAINT FK_track
        FOREIGN KEY (track_id) REFERENCES spotitube_db.track (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT FK_playlist
        FOREIGN KEY (playlist_id) REFERENCES spotitube_db.playlist (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

INSERT INTO user (username, password)
VALUES ('john', 'doe');

INSERT INTO track (title, performer, duration, album, play_count, publication_date, description)
VALUES ('oceaen eyes', 'Billie Eilish', 200, 'dont smile at me', 10, '01-01-2000', 'description...');

INSERT INTO track (title, performer, duration, album, play_count, publication_date, description)
VALUES ('Kleine Jongen', 'Andre Hazes', 200, 'Best Hits', 10, '01-01-2000', 'description...');

INSERT INTO track (title, performer, duration, album, play_count, publication_date, description)
VALUES ('Zeg maar niets meer', 'Andre Hazes', 200, 'Best Hits', 10, '01-01-2000', 'description...');

INSERT INTO track (title, performer, duration, album, play_count, publication_date, description)
VALUES ('De Vlieger', 'Andre Hazes', 200, 'Best Hits', 10, '01-01-2000', 'description...');

INSERT INTO playlist (name, owner)
VALUES ('Toppertjes', 'john');

INSERT INTO playlist (name, owner)
VALUES ('Summer 2020', 'john');

INSERT INTO playlist (name, owner)
VALUES ('Lege playlist', 'john');

INSERT INTO playlist_track (playlist_id, track_id)
VALUES (1, 2);

INSERT INTO playlist_track (playlist_id, track_id)
VALUES (1, 3);

INSERT INTO playlist_track (playlist_id, track_id)
VALUES (1, 4);

INSERT INTO playlist_track (playlist_id, track_id)
VALUES (2, 1);