drop table if exists student;
create table student
(
    id         serial primary key,
    first_name varchar(100),
    last_name  varchar(100),
    group_name varchar(50),
    age        integer
);

insert into student (first_name, last_name, group_name, age)
values ('Ogden', 'Molyneaux', 'zo3-456', 25);
insert into student (first_name, last_name, group_name, age)
values ('Giovanni', 'Fuggle', 'zEF-733', 21);
insert into student (first_name, last_name, group_name, age)
values ('Mable', 'Braxton', 'zo3-456', 28);
insert into student (first_name, last_name, group_name, age)
values ('Hermann', 'Craine', 'zRjJ-670', 29);
insert into student (first_name, last_name, group_name, age)
values ('Danika', 'Herley', 'zo3-456', 23);
insert into student (first_name, last_name, group_name, age)
values ('Melinde', 'Fluit', 'zEF-733', 29);
insert into student (first_name, last_name, group_name, age)
values ('Bastian', 'Minor', 'Wyi-597', 18);
insert into student (first_name, last_name, group_name, age)
values ('Kara', 'Gerraty', 'zo3-456', 26);
insert into student (first_name, last_name, group_name, age)
values ('Raddy', 'Cosens', 'Wyi-597', 26);
insert into student (first_name, last_name, group_name, age)
values ('Celestine', 'MacIlriach', 'Wyi-597', 24);
insert into student (first_name, last_name, group_name, age)
values ('Maurie', 'Freegard', 'Wyi-597', 25);
insert into student (first_name, last_name, group_name, age)
values ('Maisey', 'Brussels', 'sE6-491', 22);
insert into student (first_name, last_name, group_name, age)
values ('Riley', 'Grimwad', 'Wyi-597', 18);
insert into student (first_name, last_name, group_name, age)
values ('Tommy', 'Rigler', 'sE6-491', 25);
insert into student (first_name, last_name, group_name, age)
values ('Pearla', 'Valeri', 'jvx-834', 27);
insert into student (first_name, last_name, group_name, age)
values ('Willdon', 'Cavan', 'Wyi-597', 20);
insert into student (first_name, last_name, group_name, age)
values ('Sandi', 'Osanne', 'sE6-491', 23);
insert into student (first_name, last_name, group_name, age)
values ('Laughton', 'Crockley', 'Wyi-597', 24);
insert into student (first_name, last_name, group_name, age)
values ('Connor', 'Ashburne', 'Wyi-597', 25);
insert into student (first_name, last_name, group_name, age)
values ('Johann', 'Puckinghorne', 'IL3-411', 23);
insert into student (first_name, last_name, group_name, age)
values ('Leigha', 'Lawerence', 'RjJ-670', 27);
insert into student (first_name, last_name, group_name, age)
values ('Viviana', 'Beaman', 'RjJ-670', 29);
insert into student (first_name, last_name, group_name, age)
values ('Karry', 'Beine', 'IRjJ-670', 25);

