create table jsptest.new_member (
	id varchar(15) not null primary key,
	password varchar(10) not null,
	name varchar(15) not null,
	age int,
	gender varchar(5),
	email varchar(30)
);