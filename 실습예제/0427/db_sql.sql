SELECT * FROM jsptest.board;


CREATE TABLE jsptest.testboard (
 bId int not null primary key auto_increment,
 bName varchar(20),
 bTitle varchar(100),
 bContent varchar(300),
 bDate datetime,
 bHit int DEFAULT 0,
 bGroup int ,
 bStep int ,
 bIndent int 
 );


ALTER TABLE jsptest.members 
CHANGE COLUMN password password VARCHAR(60) NOT NULL ;