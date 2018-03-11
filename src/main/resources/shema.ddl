CREATE TABLE groups
(
  group_number INT PRIMARY KEY,
  avg_mark     DECIMAL NOT NULL,
  CONSTRAINT groups_group_number_index
  UNIQUE (group_number)
);

CREATE TABLE marks
(
  id           SERIAL PRIMARY KEY,
  study_id     BIGINT       NOT NULL,
  student_id   BIGINT       NOT NULL,
  date         DATE         NOT NULL,
  professor_id BIGINT       NOT NULL,
  mark         INT          NOT NULL,
  comments     VARCHAR(256) NOT NULL,
  CONSTRAINT marks_id_index
  UNIQUE (id)
);

CREATE INDEX marks_students_id_fk
  ON marks (student_id);

CREATE INDEX marks_professors_id_fk
  ON marks (professor_id);

CREATE INDEX marks_studies_id_fk
  ON marks (study_id);

CREATE TABLE professors
(
  id          SERIAL PRIMARY KEY,
  first_name  VARCHAR(30) NOT NULL,
  second_name VARCHAR(30) NOT NULL,
  father_name VARCHAR(30) NOT NULL,
  birth_date  DATE        NOT NULL,
  avg_name    DECIMAL     NOT NULL,
  CONSTRAINT professors_id_index
  UNIQUE (id)
);

ALTER TABLE marks
  ADD CONSTRAINT marks_professors_id_fk
FOREIGN KEY (professor_id) REFERENCES professors (id);

CREATE TABLE students
(
  id           SERIAL      NOT NULL  PRIMARY KEY,
  first_name   VARCHAR(30) NOT NULL,
  second_name  VARCHAR(30) NOT NULL,
  group_number INT         NOT NULL,
  CONSTRAINT students_id_index
  UNIQUE (id),
  CONSTRAINT students_groups_group_number_fk
  FOREIGN KEY (group_number) REFERENCES groups (group_number)
);

CREATE INDEX students_groups_group_number_fk
  ON students (group_number);

ALTER TABLE marks
  ADD CONSTRAINT marks_students_id_fk
FOREIGN KEY (student_id) REFERENCES students (id);

CREATE TABLE studies
(
  id           SERIAL       NOT NULL  PRIMARY KEY,
  name         VARCHAR(256) NOT NULL,
  hours        DECIMAL      NOT NULL,
  professor_id BIGINT       NOT NULL,
  avg_mark     DECIMAL      NOT NULL,
  CONSTRAINT studies_id_index
  UNIQUE (id),
  CONSTRAINT studies_professors_id_fk
  FOREIGN KEY (professor_id) REFERENCES professors (id)
);

CREATE INDEX studies_professors_id_fk
  ON studies (professor_id);

ALTER TABLE marks
  ADD CONSTRAINT marks_studies_id_fk
FOREIGN KEY (study_id) REFERENCES studies (id);

CREATE TYPE user_role AS ENUM ('ADMIN', 'STUDENT', 'PROFESSOR');

CREATE TABLE users
(
  username     VARCHAR(30)                           NOT NULL,
  password VARCHAR(100)                          NOT NULL,
  role     user_role NULL
);

