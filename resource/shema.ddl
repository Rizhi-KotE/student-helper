create table groups
(
	group_number int not null
		primary key,
	avg_mark double not null,
	constraint groups_group_number_uindex
		unique (group_number)
)
;

create table marks
(
	id bigint not null auto_increment
		primary key,
	study_id bigint not null,
	student_id bigint not null,
	date date not null,
	professor_id bigint not null,
	mark int not null,
	comments varchar(256) not null,
	constraint marks_id_uindex
		unique (id)
)
;

create index marks_students_id_fk
	on marks (student_id)
;

create index marks_professors_id_fk
	on marks (professor_id)
;

create index marks_studies_id_fk
	on marks (study_id)
;

create table professors
(
	id bigint not null auto_increment
		primary key,
	first_name varchar(30) not null,
	second_name varchar(30) not null,
	father_name varchar(30) not null,
	birth_date date not null,
	avg_name double not null,
	constraint professors_id_uindex
		unique (id)
)
;

alter table marks
	add constraint marks_professors_id_fk
		foreign key (professor_id) references professors (id)
;

create table students
(
	id bigint not null auto_increment
		primary key,
	first_name varchar(30) not null,
	second_name varchar(30) not null,
	group_number int not null,
	constraint students_id_uindex
		unique (id),
	constraint students_groups_group_number_fk
		foreign key (group_number) references groups (group_number)
)
;

create index students_groups_group_number_fk
	on students (group_number)
;

alter table marks
	add constraint marks_students_id_fk
		foreign key (student_id) references students (id)
;

create table studies
(
	id bigint not null auto_increment
		primary key,
	name varchar(256) not null,
	hours double not null,
	professor_id bigint not null,
	avg_mark double not null,
	constraint studies_id_uindex
		unique (id),
	constraint studies_professors_id_fk
		foreign key (professor_id) references professors (id)
)
;

create index studies_professors_id_fk
	on studies (professor_id)
;

alter table marks
	add constraint marks_studies_id_fk
		foreign key (study_id) references studies (id)
;

create table users
(
	user varchar(30) not null,
	password varchar(100) not null,
	role enum('ADMIN', 'STUDENT', 'PROFESSOR') null
)
;

