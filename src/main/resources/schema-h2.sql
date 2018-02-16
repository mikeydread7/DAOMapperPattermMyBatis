
drop sequence user_id_seq;

ALTER TABLE USER_ORDERS disable constraint fk_user_profile_pk;

drop table  USER_PROFILES;

create table USER_PROFILES  
(  
 	userId  	number(4) not null,  
 	userName 	VARCHAR2(32) not null,
 	eyeColor 	VARCHAR2(16) not null,
 	height 		number(4) not null,
 	weight 		number(4) not null,
 	birthday 	date not null,
 	userAge 	number(4) not null ,
 	CONSTRAINT user_profile_pk PRIMARY KEY (userId)
);  

create sequence user_id_seq increment by 1 start with 1;

insert into USER_PROFILES values(user_id_seq.nextval,'Michael','brown',73,140,sysdate-16900,47);  
insert into USER_PROFILES values(user_id_seq.nextval,'Steffan','brown',63,89,sysdate-5000,13);  
insert into USER_PROFILES values(user_id_seq.nextval,'Louis','brown',70,170,sysdate-16990,48);  
insert into USER_PROFILES values(user_id_seq.nextval,'Jeff','brown',68,190,sysdate-16800,43); 
insert into USER_PROFILES values(user_id_seq.nextval,'Sean','brown',58,170,sysdate-16780,30); 
