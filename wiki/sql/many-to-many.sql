/* Связь многие ко многим */

DROP TABLE IF EXISTS tz_authors;
DROP TABLE IF EXISTS tz_books;
DROP TABLE IF EXISTS tr_authors_books;

CREATE TABLE tz_authors (
    id BIGSERIAL PRIMARY KEY,
    isDeleted SMALLINT DEFAULT 0,
    aName VARCHAR(120) NOT NULL,
    aRating NUMERIC(5,2) DEFAULT 0.00
);

CREATE TABLE tz_books (
    id BIGSERIAL PRIMARY KEY,
    isDeleted SMALLINT DEFAULT 0,
    bName VARCHAR(250) NOT NULL,
    bTotalPages INTEGER NOT NULL
);

CREATE TABLE tr_authors_books (
    id BIGSERIAL PRIMARY KEY,
    id_author BIGINT REFERENCES tz_authors (id) ON DELETE CASCADE,
    id_book BIGINT REFERENCES tz_books (id) ON DELETE CASCADE
);