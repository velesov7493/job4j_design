/* Создание таблиц */

DROP TABLE IF EXISTS tz_users;
DROP TABLE IF EXISTS tz_roles;
DROP TABLE IF EXISTS tz_rules;
DROP TABLE IF EXISTS tr_roles_rules;
DROP TABLE IF EXISTS tr_items;
DROP TABLE IF EXISTS tj_comments;
DROP TABLE IF EXISTS tj_attachments;
DROP TABLE IF EXISTS tz_categories;
DROP TABLE IF EXISTS tz_stats;

DROP SEQUENCE IF EXISTS tz_users_id_seq;
DROP SEQUENCE IF EXISTS tz_roles_id_seq;
DROP SEQUENCE IF EXISTS tz_rules_id_seq;
DROP SEQUENCE IF EXISTS tr_items_id_seq;
DROP SEQUENCE IF EXISTS tj_comments_id_seq;
DROP SEQUENCE IF EXISTS tj_attachments_id_seq;
DROP SEQUENCE IF EXISTS tz_categories_id_seq;
DROP SEQUENCE IF EXISTS tz_stats_id_seq;

CREATE TABLE tz_roles (
    id SERIAL PRIMARY KEY,
    roName VARCHAR(60) NOT NULL
);

CREATE TABLE tz_rules (
    id SERIAL PRIMARY KEY,
    ruName VARCHAR(250) NOT NULL
);

CREATE TABLE tr_roles_rules (
    id_role INTEGER NOT NULL REFERENCES tz_roles (id) ON DELETE CASCADE,
    id_rule INTEGER NOT NULL REFERENCES tz_rules (id) ON DELETE CASCADE,
    CONSTRAINT pk_roles_rules PRIMARY KEY (id_role, id_rule)
);

CREATE TABLE tz_users (
    id SERIAL PRIMARY KEY,
    id_role INTEGER REFERENCES tz_roles (id) ON DELETE SET NULL,
    uName VARCHAR(60) NOT NULL,
    uLogin VARCHAR(15) NOT NULL UNIQUE,
    uMail VARCHAR(40) NOT NULL,
    uPass VARCHAR(40) NOT NULL,
    uActivated SMALLINT DEFAULT 0
);

CREATE TABLE tz_categories (
    id SERIAL PRIMARY KEY,
    id_parent INTEGER REFERENCES tz_categories (id) ON DELETE SET NULL,
    cName VARCHAR(250) NOT NULL
);

CREATE TABLE tz_stats (
    id SERIAL PRIMARY KEY,
    stName VARCHAR(250) NOT NULL
);

CREATE TABLE tz_items (
    id SERIAL PRIMARY KEY,
    id_user INTEGER NOT NULL REFERENCES tz_users (id) ON DELETE CASCADE,
    id_category INTEGER NOT NULL REFERENCES tz_categories (id) ON DELETE CASCADE,
    id_state INTEGER DEFAULT 0 REFERENCES tz_stats (id) ON DELETE SET DEFAULT,
    iName VARCHAR(250) NOT NULL
);

CREATE TABLE tj_comments (
    id SERIAL PRIMARY KEY,
    id_item INTEGER NOT NULL REFERENCES tz_items (id) ON DELETE CASCADE,
    cmText TEXT
);

CREATE TABLE tj_attachments (
    id SERIAL PRIMARY KEY,
    id_item INTEGER NOT NULL REFERENCES tz_items (id) ON DELETE CASCADE,
    atMimeType VARCHAR(120) NOT NULL
);