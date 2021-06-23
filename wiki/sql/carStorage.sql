/* Создание таблиц */

DROP TABLE IF EXISTS tr_cars;
DROP TABLE IF EXISTS tz_bodies;
DROP TABLE IF EXISTS tz_engines;
DROP TABLE IF EXISTS tz_transmissions;

CREATE TABLE tz_bodies (
    id SERIAL PRIMARY KEY,
    name VARCHAR(250) NOT NULL
);

CREATE TABLE tz_engines (
    id SERIAL PRIMARY KEY,
    name VARCHAR(250) NOT NULL
);

CREATE TABLE tz_transmissions (
    id SERIAL PRIMARY KEY,
    name VARCHAR(250) NOT NULL
);

CREATE TABLE tr_cars (
    id SERIAL PRIMARY KEY,
    id_body INTEGER NOT NULL REFERENCES tz_bodies (id) ON DELETE RESTRICT,
    id_engine INTEGER NOT NULL REFERENCES tz_engines (id) ON DELETE RESTRICT,
    id_transmission INTEGER NOT NULL REFERENCES tz_transmissions (id) ON DELETE RESTRICT,
    name VARCHAR(250) NOT NULL
);

/* Заполнение таблиц */

INSERT INTO tz_bodies (name) VALUES
('Седан'), ('Купе'), ('Хардтоп-седан'), ('Хардтоп-купе'),
('Фастбэк'), ('Комби'), ('Универсал'), ('Лимузин'),
('Бескапотный кузов'), ('Фургон'), ('Фаэтон'), ('Фаэтон-универсал'),
('Карбиолет'), ('Кабриолет-хардтоп'), ('Родстер'), ('Брогам'),
('Ландо'), ('Тага'), ('Пикап'), ('Хэтчбек'), ('Минивэн');

INSERT INTO tz_engines (name) VALUES
('VAZ 11194'), ('VAZ 21124'), ('VAZ 21126'), ('VAZ 21127'),
('VAZ 21129'), ('VAZ 21179'), ('ЗМЗ-51432'), ('ЗМЗ-40905'),
('ЗМЗ-40906'), ('УМЗ-451'), ('ЗМЗ-4021.10'), ('УМЗ-4178'),
('УМЗ-4218'), ('УМЗ-414'), ('УМЗ-4213'), ('ЗМЗ-4091');

INSERT INTO tz_transmissions (name) VALUES
('VAZ 2180'), ('VAZ 2181'), ('VAZ 21807'), ('VAZ 21809'),
('VAZ 1118'), ('VAZ 2170'), ('VAZ 2190'), ('VAZ 2110'),
('VAZ 2111'), ('УАЗ 452 С/О 5ст'), ('УАЗ 452 Н/О'), ('УАЗ 3163 DYMOS'),
('УАЗ 3151-95'), ('УАЗ 469 С/О 4ст'), ('ГАЗ 53 3307'), ('УАЗ 469 Н/О 5ст');

INSERT INTO tr_cars (id_body, id_engine, id_transmission, name) VALUES
(9, 14, 10, 'УАЗ 452'), (12, 11, 16, 'УАЗ 469'), (1, 2, 3, 'ВАЗ 2110'), (20, 5, 5, 'ВАЗ 2118'),
(2, 3, 9, 'ВАЗ 21213'), (1, 4, 4, 'ВАЗ 2107'), (1, 3, 3, 'ВАЗ 2105'), (1, 7, 8, 'ВАЗ 2101'),
(7, 9, 12, 'УАЗ Патриот'), (2, 2, 8, 'ВАЗ 2121'), (1, 4, 7, 'ВАЗ 2190'), (1, 1, 5, 'ВАЗ 21099'),
(21, 6, 2, 'ВАЗ 2120'), (1, 3, 6, 'ВАЗ 2106'), (1, 4, 5, 'ВАЗ 21063'), (2, 4, 4, 'ВАЗ 2122');

/* Список всех машин с привязанными к ним деталями */

SELECT c.name, b.name, e.name, t.name
FROM tr_cars AS c
    LEFT JOIN tz_bodies AS b ON c.id_body=b.id
    LEFT JOIN tz_engines AS e ON c.id_engine=e.id
    LEFT JOIN tz_transmissions AS t ON c.id_transmission=t.id
ORDER BY 1,2,3,4;

/* Неиспользуемые детали */

SELECT 'Кузов' AS uType, b.name AS unit FROM tz_bodies AS b
WHERE b.id NOT IN (SELECT DISTINCT id_body FROM tr_cars)

UNION SELECT 'Двигатель' AS uType, e.name AS unit FROM tz_engines AS e
WHERE e.id NOT IN (SELECT DISTINCT id_engine FROM tr_cars)

UNION SELECT 'Трансмиссия' AS uType, t.name AS unit FROM tz_transmissions AS t
WHERE t.id NOT IN (SELECT DISTINCT id_transmission FROM tr_cars)
ORDER BY 1,2;