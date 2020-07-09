

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


