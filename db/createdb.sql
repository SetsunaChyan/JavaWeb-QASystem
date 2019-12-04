use QASystemDB;

drop table teach,curriculum,teacher,department,users;

create table users
(
    u_name     nchar(40),
    u_password nchar(40),
    u_type     nchar(40),
    primary key (u_name)
);

insert into users(u_name, u_password, u_type)
values ('Setsuna', 'admin', 'admin');
insert into users(u_name, u_password, u_type)
values ('stu1', 'stu1', 'student');
insert into users(u_name, u_password, u_type)
values ('Azusa', 'kon', 'teacher');

create table department
(
    dept_name nchar(40),
    inf       nchar(1023),
    primary key (dept_name)
);

create table teacher
(
    te_name nchar(40),
    inf     nchar(1023),
    title   nchar(40),
    primary key (te_name),
    foreign key (te_name) references users (u_name)
);

create table curriculum
(
    cur_name  nchar(40),
    inf       nchar(1023),
    dept_name nchar(40),
    primary key (cur_name),
    foreign key (dept_name) references department (dept_name)
);

create table teach
(
    te_name  nchar(40),
    cur_name nchar(40),
    primary key (te_name, cur_name),
    foreign key (cur_name) references curriculum (cur_name),
    foreign key (te_name) references teacher (te_name)
);