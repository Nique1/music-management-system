CREATE TABLE IF NOT EXISTS songs(
 song_id INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
 uuid UUID NOT NULL,
 artist_name VARCHAR(255) NOT NULL,
 track_name VARCHAR(255) NOT NULL,
 track_length INTEGER NOT NULL,
 year INTEGER NOT NULL
);
