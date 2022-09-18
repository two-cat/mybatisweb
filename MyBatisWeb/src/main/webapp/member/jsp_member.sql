drop table jsp_member;

create table jsp_member(
    idx number(8) constraint jsp_member_pk primary key,
    name varchar2(30) not null,
    userid varchar2(20) constraint jsp_member_userid_uk unique,
    pwd varchar2(20) not null,
    hp1 char(3) not null,
    hp2 char(4) not null,
    hp3 char(4) not null,
    zipcode char(5),
    addr1 varchar2(200),
    addr2 varchar2(200),
    mileage number(10) default 1000,
    indate date default sysdate
);
/*시퀀스명: jsp_member_seq
시작: 1
증가치: 1
캐시 사용 안함*/

drop sequence jsp_member_seq;

create sequence jsp_member_seq
start with 1
increment by 1
nocache;