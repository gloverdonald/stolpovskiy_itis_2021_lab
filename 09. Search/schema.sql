drop table if exists student;

create table student
(
    id         serial primary key,
    first_name varchar(100),
    last_name  varchar(100),
    group_name varchar(50),
    age        integer
);