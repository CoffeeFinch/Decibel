BEGIN TRANSACTION;
ROLLBACK;

DROP TABLE IF EXISTS song_mood;
DROP TABLE IF EXISTS song_genre;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS song;
DROP TABLE IF EXISTS mood;
DROP TABLE IF EXISTS genre;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);


CREATE TABLE song (
	song_id SERIAL,
	title varchar(200) NOT NULL,
	artist varchar(50) NOT NULL,
	CONSTRAINT PK_song PRIMARY KEY (song_id)
	
	
);

CREATE TABLE genre (
	genre_id SERIAL,
	genre_name varchar(200) NOT NULL,
	CONSTRAINT PK_genre PRIMARY KEY (genre_id)
	
);

CREATE TABLE mood (
	mood_id SERIAL,
	mood_name varchar(200) NOT NULL,
	CONSTRAINT PK_mood PRIMARY KEY (mood_id)
);

CREATE TABLE song_genre (
	song_id bigint,
	genre_id bigint,
	
	CONSTRAINT pk_song_genre primary key (song_id, genre_id),
	CONSTRAINT fk_song_genre_song_id FOREIGN KEY (song_id) REFERENCES song(song_id),
	CONSTRAINT fk_song_genre_genre_id FOREIGN KEY (genre_id) REFERENCES genre(genre_id)
);

CREATE TABLE song_mood (
	song_id bigint,
	mood_id bigint,
	
	CONSTRAINT pk_song_mood primary key (song_id, mood_id),
	CONSTRAINT fk_song_mood_song_id FOREIGN KEY (song_id) REFERENCES song(song_id),
	CONSTRAINT fk_song_mood_mood_id FOREIGN KEY (mood_id) REFERENCES mood(mood_id)
);