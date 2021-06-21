/* Очистка базы */

DROP VIEW IF EXISTS vt_students;
DROP VIEW IF EXISTS vt_employees;
DROP VIEW IF EXISTS vt_teachers;
DROP VIEW IF EXISTS vt_istudents;
DROP VIEW IF EXISTS vt_iemployees;
DROP VIEW IF EXISTS vt_iteachers;
DROP VIEW IF EXISTS vt_iparents;
DROP VIEW IF EXISTS vt_iguests;
DROP VIEW IF EXISTS vt_student_ntotals;
DROP VIEW IF EXISTS vt_student_ytotals;
DROP VIEW IF EXISTS vt_student_totals;
DROP VIEW IF EXISTS vt_classparents;
DROP VIEW IF EXISTS vt_classstudents;
DROP VIEW IF EXISTS vt_trimesters;
DROP TABLE IF EXISTS tz_trimesters;
DROP TABLE IF EXISTS tz_years;
DROP TABLE IF EXISTS tz_settings;
DROP TABLE IF EXISTS tr_chatmessages;
DROP TABLE IF EXISTS tr_receptiondays;
DROP TABLE IF EXISTS tj_tasks;
DROP TABLE IF EXISTS tr_attendance;
DROP TABLE IF EXISTS tr_requirements;
DROP TABLE IF EXISTS tz_lessons;
DROP TABLE IF EXISTS tz_news;
DROP TABLE IF EXISTS tr_actorstats;
DROP TABLE IF EXISTS tz_classes;
DROP TABLE IF EXISTS tz_chatrooms;
DROP TABLE IF EXISTS tz_actors;
DROP TABLE IF EXISTS tz_media;
DROP TABLE IF EXISTS tz_worktypes;
DROP TABLE IF EXISTS tz_disciplines;
DROP TABLE IF EXISTS tz_professions;
DROP TABLE IF EXISTS tz_roles;

DROP SEQUENCE IF EXISTS hibernate_sequence;
DROP SEQUENCE IF EXISTS tj_tasks_id_seq;
DROP SEQUENCE IF EXISTS tr_actorstats_id_seq;
DROP SEQUENCE IF EXISTS tr_attendance_id_seq;
DROP SEQUENCE IF EXISTS tr_chatmessages_id_seq;
DROP SEQUENCE IF EXISTS tr_receptiondays_id_seq;
DROP SEQUENCE IF EXISTS tr_requirements_id_seq;
DROP SEQUENCE IF EXISTS tz_actors_id_seq;
DROP SEQUENCE IF EXISTS tz_chatrooms_id_seq;
DROP SEQUENCE IF EXISTS tz_classes_id_seq;
DROP SEQUENCE IF EXISTS tz_disciplines_id_seq;
DROP SEQUENCE IF EXISTS tz_lessons_id_seq;
DROP SEQUENCE IF EXISTS tz_media_id_seq;
DROP SEQUENCE IF EXISTS tz_news_id_seq;
DROP SEQUENCE IF EXISTS tz_professions_id_seq;
DROP SEQUENCE IF EXISTS tz_trimesters_id_seq;
DROP SEQUENCE IF EXISTS tz_worktypes_id_seq;

/* Таблицы БД */

CREATE TABLE IF NOT EXISTS tz_roles (
    id INTEGER PRIMARY KEY,
    rName VARCHAR(90) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS tz_professions (
    id SERIAL PRIMARY KEY,
    pName VARCHAR(60) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS tz_disciplines (
    id SERIAL PRIMARY KEY,
    dName VARCHAR(90) NOT NULL UNIQUE,
    dMinClassNum SMALLINT NOT NULL,
    dMaxClassNum SMALLINT NOT NULL
);

CREATE TABLE IF NOT EXISTS tz_worktypes (
    id SERIAL PRIMARY KEY,
    wtName VARCHAR(60) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS tz_media (
    id BIGSERIAL PRIMARY KEY,
    id_owner BIGINT DEFAULT NULL,
    id_poster BIGINT DEFAULT NULL REFERENCES tz_media (id) ON DELETE SET NULL,
    accessRing INTEGER DEFAULT 8,
    mMimeType VARCHAR(60) NOT NULL,
    mIsVideo SMALLINT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS tz_chatrooms (
    id BIGSERIAL PRIMARY KEY,
    is_public SMALLINT DEFAULT 0,
    access_ring INTEGER,
    id_class BIGINT,
    id_actor1 BIGINT,
    id_actor2 BIGINT,
    ctName VARCHAR(60) NOT NULL,
    ctLastMessage TIMESTAMPTZ
);

CREATE TABLE IF NOT EXISTS tz_actors (
    id BIGSERIAL PRIMARY KEY,
    id_avatar BIGINT DEFAULT 0 REFERENCES tz_media (id) ON DELETE SET DEFAULT,
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

CREATE TABLE IF NOT EXISTS tz_classes (
    id BIGSERIAL PRIMARY KEY,
    id_leader BIGINT NOT NULL REFERENCES tz_actors (id),
    id_pchat BIGINT NOT NULL UNIQUE REFERENCES tz_chatrooms (id),
    id_schat BIGINT NOT NULL UNIQUE REFERENCES tz_chatrooms (id),
    cStudentsCount INTEGER NOT NULL,
    cNumber SMALLINT NOT NULL,
    cLiter VARCHAR(10) NOT NULL,
    cCreated DATE NOT NULL,
    cIssued DATE
);

CREATE TABLE IF NOT EXISTS tr_actorstats (
    id BIGSERIAL PRIMARY KEY,
    id_actor BIGINT NOT NULL REFERENCES tz_actors (id) ON DELETE CASCADE,
    id_role INTEGER NOT NULL REFERENCES tz_roles (id) ON DELETE CASCADE,
    id_profession INTEGER REFERENCES tz_professions (id) ON DELETE SET NULL,
    id_discipline INTEGER REFERENCES tz_disciplines (id) ON DELETE SET NULL,
    id_class BIGINT REFERENCES tz_classes (id) ON DELETE SET NULL,
    id_father BIGINT REFERENCES tz_actors (id) ON DELETE SET NULL,
    id_mother BIGINT REFERENCES tz_actors (id) ON DELETE SET NULL,
    id_child BIGINT REFERENCES tz_actors (id) ON DELETE SET NULL,
    sName VARCHAR(120)
);

CREATE TABLE IF NOT EXISTS tz_news (
    id BIGSERIAL PRIMARY KEY,
    id_media BIGINT REFERENCES tz_media (id) ON DELETE SET NULL,
    nTime TIMESTAMPTZ NOT NULL,
    nCaption VARCHAR(120) NOT NULL,
    nText TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS tz_lessons (
    id BIGSERIAL PRIMARY KEY,
    id_teacher BIGINT REFERENCES tz_actors (id) ON DELETE SET NULL,
    id_class BIGINT NOT NULL REFERENCES tz_classes (id) ON DELETE CASCADE,
    id_discipline INTEGER NOT NULL REFERENCES tz_disciplines (id),
    lDescription TEXT,
    lDate DATE NOT NULL,
    lStarts TIME NOT NULL,
    lEnds TIME NOT NULL,
    lCabinetNum INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS tr_requirements (
    id BIGSERIAL PRIMARY KEY,
    id_lesson BIGINT NOT NULL REFERENCES tz_lessons (id) ON DELETE CASCADE,
    id_attach BIGINT REFERENCES tz_media (id) ON DELETE SET NULL,
    rqDescription TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS tr_attendance (
    id BIGSERIAL PRIMARY KEY,
    id_lesson BIGINT NOT NULL REFERENCES tz_lessons (id) ON DELETE CASCADE,
    id_student BIGINT NOT NULL REFERENCES tz_actors (id) ON DELETE CASCADE,
    is_attend SMALLINT DEFAULT 1,
    CONSTRAINT uk_attendance UNIQUE (id_lesson,id_student)
);

/*
    Статусы заданий:

    0 - задано,
    1 - сдано на проверку,
    2 - возвращено на доработку,
    3 - принято и оценено
*/

CREATE TABLE IF NOT EXISTS tj_tasks (
    id BIGSERIAL PRIMARY KEY,
    id_teacher BIGINT REFERENCES tz_actors (id) ON DELETE SET NULL,
    id_student BIGINT REFERENCES tz_actors (id) ON DELETE CASCADE,
    id_discipline INTEGER NOT NULL REFERENCES tz_disciplines (id),
    id_class BIGINT NOT NULL REFERENCES tz_classes (id) ON DELETE CASCADE,
    id_lesson BIGINT NOT NULL REFERENCES tz_lessons (id) ON DELETE CASCADE,
    id_worktype INTEGER NOT NULL REFERENCES tz_worktypes (id) ON DELETE CASCADE,
    id_studentattach BIGINT REFERENCES tz_media (id) ON DELETE SET NULL,
    id_teacherattach BIGINT REFERENCES tz_media (id) ON DELETE SET NULL,
    tsWorkNum INTEGER NOT NULL,
    tsDescription TEXT NOT NULL,
    tsIssued DATE NOT NULL,
    tsDeadLine DATE NOT NULL,
    tsDone DATE,
    tsStatus SMALLINT DEFAULT 0,
    tsMark NUMERIC(4,2),
    tsStudentComment TEXT,
    tsTeacherComment TEXT
);

CREATE TABLE IF NOT EXISTS tr_receptiondays (
    id BIGSERIAL PRIMARY KEY,
    id_state BIGINT NOT NULL REFERENCES tr_actorstats (id) ON DELETE CASCADE,
    rcDay SMALLINT NOT NULL,
    rcStarts TIME NOT NULL,
    rcEnds TIME NOT NULL
);

CREATE TABLE IF NOT EXISTS tr_chatmessages (
    id BIGSERIAL PRIMARY KEY,
    id_chat BIGINT NOT NULL REFERENCES tz_chatrooms (id) ON DELETE CASCADE,
    id_actor BIGINT REFERENCES tz_actors (id) ON DELETE SET NULL,
    id_attach BIGINT REFERENCES tz_media (id) ON DELETE SET NULL,
    msgTime TIMESTAMPTZ NOT NULL,
    msgText TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS tz_settings (
    id INTEGER NOT NULL,
    ssDayStarts TIME NOT NULL,
    ssDayEnds TIME NOT NULL,
    ssLessonLength INTEGER NOT NULL,
    ssBreakLength INTEGER NOT NULL,
    ssBigBreakLength INTEGER NOT NULL,
    ssOperatorName VARCHAR(160) NOT NULL,
    ssOperatorEmail VARCHAR(90) NOT NULL,
    ssThisHost VARCHAR(160) NOT NULL,
    CHECK (id=1)
);

CREATE TABLE IF NOT EXISTS tz_years (
    year1 INTEGER PRIMARY KEY,
    year2 INTEGER NOT NULL,
    yStarts DATE NOT NULL,
    yEnds DATE NOT NULL,
    CHECK ((year2-year1)=1)
);

CREATE TABLE IF NOT EXISTS tz_trimesters (
    id BIGSERIAL PRIMARY KEY,
    id_year INTEGER NOT NULL REFERENCES tz_years (year1) ON DELETE CASCADE,
    trNumber SMALLINT NOT NULL,
    trStarts DATE NOT NULL,
    trEnds DATE NOT NULL
);

/* Виртуальные таблицы (просмотры) */

/* Триместры по годам */

CREATE OR REPLACE VIEW vt_trimesters AS SELECT
    id_year AS trYear, trNumber, trStarts, trEnds
FROM tz_trimesters
ORDER BY 1,2;

/* Ученики по классам */

CREATE OR REPLACE VIEW vt_classstudents AS SELECT
    s.id_class, s.id_actor AS id_student,
    a.id, a.id_avatar, a.aLogin, a.aPass, a.aName, a.aBirthDate, a.aSex, a.aPhone, a.aEmail, a.aActivated, a.aFcmToken
FROM tr_actorstats AS s
    INNER JOIN tz_actors AS a ON s.id_actor=a.id AND s.id_role=5
ORDER BY 1,3;

/* Родители по классам */

CREATE OR REPLACE VIEW vt_classparents AS SELECT
    s.id_class, s.id_father AS id_parent,
    a.id, a.id_avatar, a.aLogin, a.aPass, a.aName, a.aBirthDate, a.aSex, a.aPhone, a.aEmail, a.aActivated, a.aFcmToken
FROM tr_actorstats AS s
INNER JOIN tz_actors AS a ON s.id_father=a.id AND s.id_role=5
UNION SELECT
    s.id_class, s.id_mother AS id_parent,
    a.id, a.id_avatar, a.aLogin, a.aPass, a.aName, a.aBirthDate, a.aSex, a.aPhone, a.aEmail, a.aActivated, a.aFcmToken
FROM tr_actorstats AS s
INNER JOIN tz_actors AS a ON s.id_mother=a.id AND s.id_role=5
ORDER BY 1,7;

/* Неименованные итоги по триместрам */

CREATE OR REPLACE VIEW vt_student_totals AS SELECT
    vt_trimesters.trYear, vt_trimesters.trNumber, id_student, id_class, id_discipline, AVG(tsMark) AS avg_mark
FROM tj_tasks
    RIGHT JOIN vt_trimesters ON tj_tasks.tsDone BETWEEN vt_trimesters.trStarts AND vt_trimesters.trEnds
WHERE tsStatus=3
GROUP BY vt_trimesters.trYear, vt_trimesters.trNumber, id_class, id_student, id_discipline;

/* Годовые итоги */

CREATE OR REPLACE VIEW vt_student_ytotals AS SELECT
    trYear, id_student, id_class, id_discipline, AVG(avg_mark) AS avg_mark
FROM vt_student_totals
GROUP BY trYear, id_class, id_student, id_discipline;

/* Именованные итоги по триместрам и годовые */

CREATE OR REPLACE VIEW vt_student_ntotals AS SELECT
    vt_student_totals.id_student, vt_student_totals.id_class, vt_student_totals.id_discipline,
    tz_actors.aName AS stName, tz_disciplines.dName AS dName, vt_student_totals.trYear, vt_student_totals.trNumber AS trNumber, vt_student_totals.avg_mark
FROM vt_student_totals
    INNER JOIN tz_actors ON vt_student_totals.id_student=tz_actors.id
    INNER JOIN tz_disciplines ON vt_student_totals.id_discipline=tz_disciplines.id
UNION SELECT
    vt_student_ytotals.id_student, vt_student_ytotals.id_class, vt_student_ytotals.id_discipline,
    tz_actors.aName AS stName, tz_disciplines.dName AS dName, vt_student_ytotals.trYear, 4 AS trNumber, vt_student_ytotals.avg_mark
FROM vt_student_ytotals
    INNER JOIN tz_actors ON vt_student_ytotals.id_student=tz_actors.id
    INNER JOIN tz_disciplines ON vt_student_ytotals.id_discipline=tz_disciplines.id
ORDER BY 6,7,4,5;

CREATE OR REPLACE VIEW vt_iteachers AS
    SELECT DISTINCT id_actor FROM tr_actorstats WHERE id_role=4;
CREATE OR REPLACE VIEW vt_iemployees AS
    SELECT DISTINCT id_actor FROM tr_actorstats WHERE id_role<4;
CREATE OR REPLACE VIEW vt_istudents AS
    SELECT DISTINCT id_actor FROM tr_actorstats WHERE id_role=5;
CREATE OR REPLACE VIEW vt_iparents AS
    SELECT DISTINCT id_actor FROM tr_actorstats WHERE id_role=6;
CREATE OR REPLACE VIEW vt_iguests AS
    SELECT DISTINCT id_actor FROM tr_actorstats WHERE id_role=7;

/* Учителя */

CREATE OR REPLACE VIEW vt_teachers AS SELECT
    a.id, a.id_avatar, a.aLogin, a.aPass, a.aName, a.aBirthDate, a.aSex, a.aPhone, a.aEmail, a.aActivated, a.aFcmToken
FROM tz_actors AS a
INNER JOIN vt_iteachers AS it ON a.id=it.id_actor;

/* Сотрудники */

CREATE OR REPLACE VIEW vt_employees AS SELECT
    a.id, a.id_avatar, a.aLogin, a.aPass, a.aName, a.aBirthDate, a.aSex, a.aPhone, a.aEmail, a.aActivated, a.aFcmToken
FROM tz_actors AS a
INNER JOIN vt_iemployees AS ie ON a.id=ie.id_actor;

/* Ученики */

CREATE OR REPLACE VIEW vt_students AS SELECT
    a.id, a.id_avatar, a.aLogin, a.aPass, a.aName, a.aBirthDate, a.aSex, a.aPhone, a.aEmail, a.aActivated, a.aFcmToken
FROM tz_actors AS a
INNER JOIN vt_istudents AS ist ON a.id=ist.id_actor;