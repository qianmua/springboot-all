

create table emp_types(
  t_id bigint primary key ,
  t_Values varchar(100)
);

insert into emp_types (t_id, t_Values)
values (1 , '事假');

insert into emp_types (t_id, t_Values)
values (2 , '病假');

insert into emp_types (t_id, t_Values)
values (3 , '年假');

insert into emp_types (t_id, t_Values)
values (4 , '产假');

insert into emp_types (t_id, t_Values)
values (5 , '陪产假');
