CREATE TABLE IF NOT EXISTS post
(
    id   SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE IF NOT EXISTS candidate
(
    id   SERIAL PRIMARY KEY,
    name TEXT,
    image_id VARCHAR(5)
);

--ALTER TABLE candidate ADD COLUMN image_id VARCHAR(5);

CREATE TABLE IF NOT EXISTS photo
(
    id   SERIAL PRIMARY KEY
);
