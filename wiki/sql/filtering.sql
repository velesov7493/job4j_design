/* Создание таблиц */

DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS type;

CREATE TABLE type (
    id SERIAL PRIMARY KEY,
    name VARCHAR(60) NOT NULL
);

CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    type_id INT NOT NULL REFERENCES type (id) ON DELETE CASCADE,
    name VARCHAR(250) NOT NULL,
    expiried_date DATE NOT NULL,
    price NUMERIC(10,2)
);

/* Заполнение таблиц */

INSERT INTO type (name) VALUES
('СЫР'), ('МОЛОКО'), ('МОРОЖЕННОЕ'), ('ХЛЕБ'), ('КОЛБАСА'), ('РЫБА'), ('МЯСО');

INSERT INTO product (type_id, name, expiried_date, price) VALUES
(1, 'Сыр плавленный', '2021-08-01', 120.00),
(1, 'Сыр моцарелла', '2021-08-01', 290.00),
(1, 'Сыр плавленный шоколадный', '2021-08-01', 150.00),
(1, 'Сыр леадонни', '2021-06-01', 500.00),
(2, 'Молоко "Вологодское"', '2021-06-23', 84.00),
(2, 'Молоко "Мишкино детство"', '2021-06-23', 50.00),
(3, 'Мороженное "Пломбир"', '2021-06-25', 40.00),
(3, 'Мороженное "Эскимо"', '2021-06-25', 32.00),
(4, 'Хлеб ржаной (г. Родники)', '2021-06-25', 35.00),
(4, 'Хлеб ржаной "Дарницкий" (г. Шуя)', '2021-06-25', 30.00),
(4, 'Хлеб ржаной (г. Владимир)', '2021-06-25', 39.00),
(4, 'Хлеб ржаной Каравай (г. Владимир)', '2021-06-25', 40.00),
(4, 'Хлеб ржаной Каравай с тмином (г. Владимир)', '2021-06-25', 45.00),
(4, 'Хлеб пшеничный чайный батон', '2021-06-25', 35.00),
(4, 'Хлеб пшеничный нарезной батон', '2021-06-25', 30.00),
(4, 'Хлеб пшеничный буханка', '2021-06-25', 39.00),
(4, 'Хлеб из отрубей', '2021-06-25', 31.00),
(4, 'Хлеб из отрубей нарезной', '2021-06-25', 35.00),
(5, 'Колбаса докторская', '2021-07-25', 120.00),
(5, 'Колбаса копченая', '2021-07-25', 130.00),
(5, 'Колбаса полукопченая', '2021-07-25', 130.00),
(6, 'Минтай', '2021-07-25', 50.00),
(6, 'Сельдь копченая', '2021-07-25', 84.00),
(6, 'Сельдь соленая', '2021-07-25', 60.00),
(7, 'Говядина', '2021-07-25', 90.00),
(7, 'Баранина', '2021-07-25', 110.00),
(7, 'Свинина', '2021-07-25', 120.00);

/* Получение всех продуктов с типом СЫР */

SELECT * FROM product WHERE type_id IN (SELECT id FROM type WHERE name='СЫР');

/* Получение всех продуктов, у которых в имени есть слово "мороженное" без учета регистра символов */

SELECT * FROM product WHERE LOWER(name) LIKE '%мороженное%';

/* Продукты с истекшим сроком годности */

SELECT * FROM product WHERE expiried_date < current_date;

/* Самый дорогой продукт */

SELECT type_id, name, expiried_date, price FROM product
ORDER BY price DESC
LIMIT 1;

/* Количества продуктов по типам */

SELECT t.name, COUNT(p.id)
FROM product AS p
    INNER JOIN type AS t ON p.type_id=t.id
GROUP BY t.name;

/* Получение всех продуктов с типом СЫР и МОЛОКО */

SELECT * FROM product WHERE type_id IN (SELECT id FROM type WHERE name='СЫР' OR name='МОЛОКО');

/* Количества продуктов по типам, выбирая только группы, где количечество продуктов меньше 10 */

SELECT t.name, COUNT(p.id)
FROM product AS p
    INNER JOIN type AS t ON p.type_id=t.id
GROUP BY t.name
HAVING COUNT(p.id) < 10;

/* Вывести все продукты и их тип */

SELECT p.id, p.type_id, t.name, p.name, p.expiried_date, p.price
FROM product AS p
    INNER JOIN type AS t ON p.type_id=t.id
ORDER BY 3,4;