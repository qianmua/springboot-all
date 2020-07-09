

/* 文档*/
create table title_1(
  tid BIGINT primary key AUTO_INCREMENT,
  title_name varchar(100) not null ,
  title_value text,
  cid BIGINT
);

/* 一级*/
create table commit_1(
    cid BIGINT primary key AUTO_INCREMENT,
    uid bigint,
    comm_value varchar(255)
);

/* 二级 */
create table commit_2(
    c2id BIGINT primary key AUTO_INCREMENT,
    comm_value varchar(255),
    cid BIGINT,
    from_uid BIGINT,
    to_uid BIGINT
);

insert into commit_1(cid, uid, comm_value) values (1,1,'你好啊');
insert into commit_1(cid, uid, comm_value) values (2,2,'你好啊');
insert into commit_1(cid, uid, comm_value) values (3,3,'你好啊');

insert into commit_2( comm_value, cid, from_uid, to_uid) VALUES ('是啊',1,3,1);
insert into commit_2( comm_value, cid, from_uid, to_uid) VALUES ('是啊',1,1,3);
insert into commit_2( comm_value, cid, from_uid, to_uid) VALUES ('是啊',2,3,1);



select * from commit_1;
select * from commit_2;

select * from test.commit_1 c1 , test.commit_2 c2 where c1.cid = c2.cid;

alter table commit_2 add constraint FK_COMM2 foreign key (cid) references commit_1(cid);


