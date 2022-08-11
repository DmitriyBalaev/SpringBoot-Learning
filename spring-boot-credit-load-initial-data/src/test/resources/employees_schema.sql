drop table employees if exists;

create table employees(
  ID int not null AUTO_INCREMENT,
  NAME varchar(100) not null,
  TITLE varchar(100),
  PRIMARY KEY ( ID )
);