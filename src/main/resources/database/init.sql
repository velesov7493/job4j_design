/* Создание БД */

CREATE DATABASE tracker_db WITH
    OWNER = root
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE tracker_db
    IS 'БД трекера заявок';