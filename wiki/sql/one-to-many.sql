/* Связь один ко многим */

DROP TABLE IF EXISTS tz_actors;
DROP TABLE IF EXISTS tr_actorstats;

CREATE TABLE tz_actors (
    id BIGSERIAL PRIMARY KEY,
    aName VARCHAR(120) NOT NULL,
    aEmail VARCHAR(120) NOT NULL UNIQUE
);

CREATE TABLE tr_actorstats (
    id BIGSERIAL PRIMARY KEY,
    id_actor BIGINT NOT NULL REFERENCES tz_actors (id) ON DELETE CASCADE,
    sName VARCHAR(120) NOT NULL
);