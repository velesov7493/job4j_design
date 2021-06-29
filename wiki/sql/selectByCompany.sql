CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

/*
1. В одном запросе получить:
- имена всех person, которые не состоят в компании с id = 5;
- название компании для каждого человека.
*/

SELECT p.name, c.name
FROM person AS p INNER JOIN company AS c ON
    p.company_id=c.id AND
    c.id <> 5
ORDER BY p.name, c.name;

/*
2. Необходимо выбрать название компании с максимальным количеством человек
+ количество человек в этой компании.
*/

SELECT c.name, COUNT(p.id) AS personCount
FROM person AS p INNER JOIN company AS c ON
    p.company_id=c.id
GROUP BY c.name
ORDER BY personCount DESC
LIMIT 1;