DROP TABLE IF EXISTS tz_users;

CREATE TABLE tz_users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);