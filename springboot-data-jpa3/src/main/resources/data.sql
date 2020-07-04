
insert into table_demo_jpa_two(name) values ('B_1');
insert into table_demo_jpa_two(name) values ('B_2');
insert into table_demo_jpa_two(name) values ('B_3');

insert into table_demo_jpa_one(name,tid) values ('A_1',1);
insert into table_demo_jpa_one(name,tid) values ('A_2',2);
insert into table_demo_jpa_one(name,tid) values ('A_3',3);
insert into table_demo_jpa_one(name,tid) values ('A_4',1);
insert into table_demo_jpa_one(name,tid) values ('A_5',2);
insert into table_demo_jpa_one(name,tid) values ('A_6',3);


select * from table_demo_jpa_one;
select * from table_demo_jpa_two;


insert into table_demo_jpa_o1po(name) values ('o1A');
insert into table_demo_jpa_o1po(name) values ('o1B');
insert into table_demo_jpa_o1po(name) values ('o1C');


insert into table_demo_jpa_o2po(name , o1id_o1id) values ('02A' , 1);
insert into table_demo_jpa_o2po(name , o1id_o1id) values ('02B' , 2);
insert into table_demo_jpa_o2po(name , o1id_o1id) values ('02C' , 3);
insert into table_demo_jpa_o2po(name , o1id_o1id) values ('02D' , 1);
insert into table_demo_jpa_o2po(name , o1id_o1id) values ('02E' , 1);
insert into table_demo_jpa_o2po(name , o1id_o1id) values ('02F' , 2);


select * from table_demo_jpa_o1po;
select * from table_demo_jpa_o2po;