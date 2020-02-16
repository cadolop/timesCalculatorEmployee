drop table if exists EMPLOYEE;

create table EMPLOYEE(
  ID int not null AUTO_INCREMENT,
  FIRST_NAME varchar(100) not null,
  LAST_NAME varchar(100) not null,
  PRIMARY KEY ( ID )
);