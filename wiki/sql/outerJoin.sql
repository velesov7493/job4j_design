/* Создание таблиц */

DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS departments;
DROP TABLE IF EXISTS teens;

CREATE TABLE departments (
    id SERIAL PRIMARY KEY,
    name VARCHAR(250) NOT NULL
);

CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    id_department INTEGER NOT NULL REFERENCES departments (id) ON DELETE CASCADE,
    name VARCHAR(250) NOT NULL
);

CREATE TABLE teens (
    name VARCHAR(250) NOT NULL,
    gender SMALLINT NOT NULL
);

/* Заполнение таблиц */

INSERT INTO departments (name) VALUES
('Управление'), ('Бухгалтерия'), ('Электроотдел'), ('Транспортный отдел'),
('Ткацкий цех'), ('Приготовительный отдел'), ('Механическая мастерская'),
('Медпункт'), ('Коммерческий отдел');

INSERT INTO employees (id_department, name) VALUES
(2, 'Орешина Елена Михайловна'), (1, 'Власов Александр Сергеевич'), (1, 'Лопатин Александр Витальевич'),
(1, 'Денисов Денис Константинович'), (1, 'Денисова Анна Михайловна'), (1, 'Климова Анна Дмитриевна'),
(3, 'Удалов Сергей Федорович'), (4, 'Грошков Владимир Николаевич'), (4, 'Грошков Сергей Николаевич'),
(5, 'Корулина Мария Александровна'), (8, 'Живетьева Катерина Сергеевна'), (8, 'Абадаева Юлия Викторовна'),
(7, 'Егоров Денис Николаевич'), (1, 'Румянцева Мария Сергеевна'), (1, 'Власов Евгений Геннадьевич'),
(1, 'Леонова Яна Павловна');

INSERT INTO teens (name, gender) VALUES
('Орешина Елена Михайловна', 0), ('Денисова Анна Михайловна', 0), ('Климова Анна Дмитриевна', 0), ('Корулина Мария Александровна', 0),
('Живетьева Катерина Сергеевна', 0), ('Абадаева Юлия Викторовна', 0), ('Румянцева Мария Сергеевна', 0), ('Леонова Яна Павловна', 0),
('Власов Александр Сергеевич', 1), ('Лопатин Александр Витальевич', 1), ('Денисов Денис Константинович', 1), ('Удалов Сергей Федорович', 1),
('Грошков Владимир Николаевич', 1), ('Грошков Сергей Николаевич', 1), ('Егоров Денис Николаевич', 1), ('Власов Евгений Геннадьевич', 1);

/* LEFT, RIGHT, FULL, CROSS */

SELECT d.name, e.name
FROM departments AS d LEFT JOIN employees AS e ON d.id=e.id_department;

SELECT d.name, e.name
FROM departments AS d RIGHT JOIN employees AS e ON d.id=e.id_department;

SELECT d.name, e.name
FROM departments AS d FULL JOIN employees AS e ON d.id=e.id_department;

SELECT d.name, e.name
FROM departments AS d CROSS JOIN employees AS e;

/* Отделы без работников */

SELECT d.name
FROM departments AS d LEFT JOIN employees AS e ON d.id=e.id_department
WHERE e.id_department IS NULL;

/* LEFT и RIGHT JOIN с одинаковым результатом */

SELECT d.name, e.name
FROM departments AS d LEFT JOIN employees AS e ON d.id=e.id_department;

SELECT d.name, e.name
FROM employees AS e RIGHT JOIN departments AS d ON d.id=e.id_department;

/* Все возможные разнополые пары */

SELECT t1.name, t2.name FROM teens AS t1 CROSS JOIN teens AS t2
WHERE t1.gender=0 AND t2.gender=1;