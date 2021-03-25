USE spotitube_db;
INSERT INTO user (username, password)
VALUES ('john', 'doe');
INSERT INTO track (
        title,
        performer,
        duration,
        album,
        play_count,
        publication_date,
        description
    )
VALUES (
        'ocean eyes',
        'Billie Eilish',
        200,
        'dont smile at me',
        10,
        '01-01-2000',
        'description...'
    );
INSERT INTO track (
        title,
        performer,
        duration,
        album,
        play_count,
        publication_date,
        description
    )
VALUES (
        'Kleine Jongen',
        'Andre Hazes',
        200,
        'Best Hits',
        10,
        '01-01-2000',
        'description...'
    );
INSERT INTO track (
        title,
        performer,
        duration,
        album,
        play_count,
        publication_date,
        description
    )
VALUES (
        'Zeg maar niets meer',
        'Andre Hazes',
        200,
        'Best Hits',
        10,
        '01-01-2000',
        'description...'
    );
INSERT INTO track (
        title,
        performer,
        duration,
        album,
        play_count,
        publication_date,
        description
    )
VALUES (
        'De Vlieger',
        'Andre Hazes',
        200,
        'Best Hits',
        10,
        '01-01-2000',
        'description...'
    );
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