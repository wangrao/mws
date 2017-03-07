
create table User (
	phoneNumber varchar(20) not null,
	userName varchar(20) not null,
	idNumber varchar(40),
	age integer,
	gendar boolean,
	weChatId varchar(40),
	qqId varchar(40),
	constraint User_PK
		primary key (phoneNumber) 
)   ENGINE=InnoDB DEFAULT CHARSET=utf8;
create index
	User_IdNumber on User (idNumber) ;
create index
	User_UserName on User (userName) ;
create index
	User_WeChatId on User (weChatId) ;