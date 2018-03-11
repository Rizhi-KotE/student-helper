alter SEQUENCE professors_id_seq RESTART;
ALTER SEQUENCE marks_id_seq RESTART ;
ALTER SEQUENCE students_id_seq RESTART ;
ALTER SEQUENCE studies_id_seq RESTART ;

DELETE FROM marks;
delete FROM studies;
DELETE FROM professors;
DELETE FROM students;
DELETE FROM groups;
