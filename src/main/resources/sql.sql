CREATE TABLE students (
  id int(10) NOT NULL AUTO_INCREMENT,
  first_name varchar(50) DEFAULT NULL,
  last_name varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE subjects (
  id int(10) NOT NULL AUTO_INCREMENT,
  subject varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE marks (
  mark_id int(100) NOT NULL AUTO_INCREMENT,
  student_id int(10) DEFAULT NULL,
  subject_id int(10) DEFAULT NULL,
  mark int(2) DEFAULT NULL,
  PRIMARY KEY (mark_id),
  INDEX student_id (student_id),
  INDEX subject_id (subject_id),
  CONSTRAINT marks_ibfk_1 FOREIGN KEY (student_id)
  REFERENCES students (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT marks_ibfk_2 FOREIGN KEY (subject_id)
  REFERENCES subjects (id) ON DELETE RESTRICT ON UPDATE RESTRICT
);
INSERT INTO students(id, first_name, last_name) VALUES (1, 'Alexander', 'Grechny');
INSERT INTO students(id, first_name, last_name) VALUES (2, 'Natallia', 'Kosyak');
INSERT INTO students(id, first_name, last_name) VALUES (3, 'Dmitry', 'Rogozin');
INSERT INTO students(id, first_name, last_name) VALUES (4, 'Eugeni', 'Glukhotorenko');
INSERT INTO students(id, first_name, last_name) VALUES (5, 'Maksim', 'Lipovsky');
INSERT INTO students(id, first_name, last_name) VALUES (6, 'Andrey', 'Kirushchanka');
INSERT INTO students(id, first_name, last_name) VALUES (7, 'Nickolay', 'Vlasov');
INSERT INTO students(id, first_name, last_name) VALUES (8, 'Vasiliy', 'Pupkin');
INSERT INTO students(id, first_name, last_name) VALUES (9, 'Vasiliy', 'Pupkin');
INSERT INTO students(id, first_name, last_name) VALUES (10, 'Alina', 'Laptenok');

INSERT INTO subjects(id, subject) VALUES(1, 'Mathematics');
INSERT INTO subjects(id, subject) VALUES(2, 'Literature');
INSERT INTO subjects(id, subject) VALUES(3, 'History');
INSERT INTO subjects(id, subject) VALUES(4, 'Geography');
INSERT INTO subjects(id, subject) VALUES(5, 'Physics');
INSERT INTO subjects(id, subject) VALUES(6, 'Chemistry');
INSERT INTO subjects(id, subject) VALUES(7, 'Biology');
INSERT INTO subjects(id, subject) VALUES(8, 'Economics');
INSERT INTO subjects(id, subject) VALUES(9, 'Art');
INSERT INTO subjects(id, subject) VALUES(10, 'Science');

INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(10, 10, 4, 10);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(11, 4, 6, 8);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(12, 6, 7, 10);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(13, 1, 8, 1);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(14, 2, 9, 4);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(15, 3, 10, 7);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(16, 5, 2, 9);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(17, 6, 2, 9);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(18, 7, 4, 6);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(19, 1, 4, 5);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(20, 3, 5, 5);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(21, 3, 6, 3);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(22, 6, 9, 4);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(1, 1, 1, 5);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(2, 2, 3, 7);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(3, 3, 5, 8);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(4, 4, 5, 9);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(5, 5, 7, 9);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(6, 6, 10, 2);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(7, 7, 1, 6);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(8, 1, 2, 9);
INSERT INTO marks(mark_id, student_id, subject_id, mark) VALUES(9, 2, 3, 7);
