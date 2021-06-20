CREATE DATABASE test1_db WITH OWNER=root ENCODING=UTF8;

CREATE TABLE IF NOT EXISTS tz_actors (
    id BIGSERIAL PRIMARY KEY,
    id_avatar BIGINT DEFAULT 0,
    aLogin VARCHAR(40) NOT NULL UNIQUE,
    aPass VARCHAR(40) NOT NULL,
    aName VARCHAR(90) NOT NULL,
    aBirthDate DATE NOT NULL,
    aSex CHAR(1) NOT NULL,
    aPhone VARCHAR(20) NOT NULL,
    aEmail VARCHAR(60) NOT NULL UNIQUE,
    aActivated SMALLINT NOT NULL,
    aFcmToken VARCHAR(250)
);

INSERT INTO tz_actors (aLogin, aPass, aName, aBirthDate, aSex, aPhone, aEmail, aActivated, aFcmToken) VALUES
('dba', 'AB4154A7C451F56E9B7FF1537758DDD0C619F8BE', 'Администратор системы', '1984-10-15', 'M', '+79621671681', 'velesov7493@yandex.ru', 1, null);

UPDATE tz_actors SET aLogin = 'sysdba' WHERE id=1;

DELETE FROM tz_actors WHERE id=1;