
drop table if exists emp;
create table emp(

    e_id bigint primary key ,
    e_name varchar(255) ,
    title varchar(255) ,
    start_time datetime ,
    end_time datetime,
    context text,
    dept varchar(255),
    p1 varchar(255),
    create_time datetime,
    update_time datetime,
    auth_id int, -- 1 -- 2 -- 3
    status int , -- 0 审批中 1 已归档
    suc int     -- 0 不同意 1 同意

);

insert into emp(e_id, e_name, title, start_time, end_time, context, dept, p1, create_time, update_time,auth_id , status, suc) values
(1, '王五', '事假' , now() ,now() , '世界这么大我想去看看' , '开发部' , '神秘人' , now() , now() ,1, 0 , 1);

insert into emp(e_id, e_name, title, start_time, end_time, context, dept, p1, create_time, update_time,auth_id ,  status, suc) values
(2, '王五', '事假' , now() ,now() , '世界这么大我想去看看' , '开发部' , '神秘人' , now() , now() ,1, 0 , 0);

insert into emp(e_id, e_name, title, start_time, end_time, context, dept, p1, create_time, update_time,auth_id ,  status, suc) values
(3, '王五', '事假' , now() ,now() , '世界这么大我想去看看' , '开发部' , '神秘人' , now() , now() ,1, 0 , 1);

insert into emp(e_id, e_name, title, start_time, end_time, context, dept, p1, create_time, update_time,auth_id ,  status, suc) values
(4, '王五', '事假' , now() ,now() , '世界这么大我想去看看' , '开发部' , '神秘人' , now() , now() ,1, 1 , 1);

insert into emp(e_id, e_name, title, start_time, end_time, context, dept, p1, create_time, update_time,auth_id ,  status, suc) values
(5, '王五', '事假' , now() ,now() , '世界这么大我想去看看' , '开发部' , '神秘人' , now() , now() ,1, 0 , 1);

insert into emp(e_id, e_name, title, start_time, end_time, context, dept, p1, create_time, update_time,auth_id ,  status, suc) values
(6, '王五', '事假' , now() ,now() , '世界这么大我想去看看' , '开发部' , '神秘人' , now() , now() ,1, 0 , 1);

insert into emp(e_id, e_name, title, start_time, end_time, context, dept, p1, create_time, update_time,auth_id ,  status, suc) values
(7, '王五', '事假' , now() ,now() , '世界这么大我想去看看' , '开发部' , '神秘人' , now() , now() ,1, 1 , 1);

insert into emp(e_id, e_name, title, start_time, end_time, context, dept, p1, create_time, update_time,auth_id ,  status, suc) values
(8, '王五', '事假' , now() ,now() , '世界这么大我想去看看' , '开发部' , '神秘人' , now() , now() ,1, 0 , 1);

insert into emp(e_id, e_name, title, start_time, end_time, context, dept, p1, create_time, update_time,auth_id ,  status, suc) values
(9, '王五', '事假' , now() ,now() , '世界这么大我想去看看' , '开发部' , '神秘人' , now() , now() ,1, 0 , 1);

insert into emp(e_id, e_name, title, start_time, end_time, context, dept, p1, create_time, update_time,auth_id ,  status, suc) values
(10, '王五', '事假' , now() ,now() , '世界这么大我想去看看' , '开发部' , '神秘人' , now() , now() , 1,0 , 1);

insert into emp(e_id, e_name, title, start_time, end_time, context, dept, p1, create_time, update_time,auth_id ,  status, suc) values
(11, '王五', '事假' , now() ,now() , '世界这么大我想去看看' , '开发部' , '神秘人' , now() , now() ,1, 0 , 1);

insert into emp(e_id, e_name, title, start_time, end_time, context, dept, p1, create_time, update_time,auth_id ,  status, suc) values
(12, '王五', '事假' , now() ,now() , '世界这么大我想去看看' , '开发部' , '神秘人' , now() , now() ,1, 0 , 1);

insert into emp(e_id, e_name, title, start_time, end_time, context, dept, p1, create_time, update_time,auth_id ,  status, suc) values
(13, '王五', '事假' , now() ,now() , '世界这么大我想去看看' , '开发部' , '神秘人' , now() , now() , 1,1 , 1);

insert into emp(e_id, e_name, title, start_time, end_time, context, dept, p1, create_time, update_time,auth_id ,  status, suc) values
(14, '王五', '事假' , now() ,now() , '世界这么大我想去看看' , '开发部' , '神秘人' , now() , now() ,1, 0 , 1);

insert into emp(e_id, e_name, title, start_time, end_time, context, dept, p1, create_time, update_time,auth_id ,  status, suc) values
(15, 'bilibili', '事假' , now() ,now() , '世界这么大我想去看看' , '开发部' , '神秘人' , now() , now() ,2, 0 , 1);
insert into emp(e_id, e_name, title, start_time, end_time, context, dept, p1, create_time, update_time,auth_id ,  status, suc) values
(16, 'bilibili', '事假' , now() ,now() , '世界这么大我想去看看' , '开发部' , '神秘人' , now() , now() ,2, 0 , 1);
insert into emp(e_id, e_name, title, start_time, end_time, context, dept, p1, create_time, update_time,auth_id ,  status, suc) values
(17, 'bilibili', '事假' , now() ,now() , '世界这么大我想去看看' , '开发部' , '神秘人' , now() , now() ,2, 0 , 1);


