/* 1. Задание */

drop table if exists devices_people;
drop table if exists devices;
drop table if exists people;

drop sequence if exists devices_id_seq;
drop sequence if exists people_id_seq;
drop sequence if exists devices_people_id_seq;

create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

/* 2. Заполнение таблиц данными */

insert into devices (name, price) values
('Asus ZenBook 13', 817.12),
('Huawei Matebook D 15 2021', 851.63),
('Apple Macbook pro 13', 2313.45),
('Apple Macbook pro 16', 3280.79),
('Aser Aspire 5', 646.07),
('Сервер 2288H/8-2R10S V5 550WR 2XG6132/128G/R10/7T/DVD HUAWEI 02311XBK-SET12', 13297.68),
('Смартфон Samsung A31', 218.73),
('ASUS TUF Gaming A17', 1229.12),
('Ноутбук Lenovo V17 IIL', 874.83),
('Компьютер Apple Mac mini (2018 года)', 1578.69);

insert into people (name) values
('Петров Валерий Николаевич'), ('Баширов Василий Семенович'),
('Потапов Михаил Федерович'), ('Субботин Сергей Дмитриевич');

insert into devices_people (people_id, device_id) values
(1, 4), (1, 6), (2, 1), (2, 3), (2, 7),
(3, 2), (3, 5), (4, 8), (4, 9), (4, 10);

/* Средняя цена по всем устройствам */

SELECT AVG(price) FROM devices;

/* Средняя цена устройств у каждого человека */

SELECT dp.people_id, ppl.name, AVG(dev.price)
FROM devices_people AS dp
	INNER JOIN people AS ppl ON dp.people_id=ppl.id
	INNER JOIN devices AS dev ON dp.device_id=dev.id
GROUP BY dp.people_id, ppl.name
ORDER BY ppl.name;

/* У кого средняя цена устройств больше 5000 */

SELECT dp.people_id, ppl.name, AVG(dev.price)
FROM devices_people AS dp
	INNER JOIN people AS ppl ON dp.people_id=ppl.id
	INNER JOIN devices AS dev ON dp.device_id=dev.id
GROUP BY dp.people_id, ppl.name
HAVING AVG(dev.price) > 5000
ORDER BY ppl.name;