
insert into table_demo_jpa_two(name) values ('B_1');
insert into table_demo_jpa_two(name) values ('B_2');
insert into table_demo_jpa_two(name) values ('B_3');

insert into table_demo_jpa_one(name,tid) values ('A_1',1);
insert into table_demo_jpa_one(name,tid) values ('A_2',2);
insert into table_demo_jpa_one(name,tid) values ('A_3',3);


select * from table_demo_jpa_one;
select * from table_demo_jpa_two;