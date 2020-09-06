CREATE TABLE IF NOT EXISTS post
(
    id   SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE IF NOT EXISTS candidate
(
    id       SERIAL PRIMARY KEY,
    name     TEXT,
    image_id VARCHAR(5)
);

CREATE TABLE IF NOT EXISTS photo
(
    id SERIAL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(256) UNIQUE,
    email    VARCHAR(256) UNIQUE,
    password VARCHAR(256)
)
