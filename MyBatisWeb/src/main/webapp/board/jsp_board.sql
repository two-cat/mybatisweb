drop table jsp_board;
--단순형 게시판
create table jsp_board(
 idx number(8) constraint jsp_board_idx_pk primary key, --글번호
 name varchar2(30) not null, --작성자
 subject varchar2(200) not null, --제목
 content varchar2(2000), --내용
 wdate date default sysdate, --작성일
 readnum number(8) default 0, --조회수
 filename varchar2(200), --첨부파일명
 filesize number(8) --첨부파일크기
);

drop sequence jsp_board_seq;

create sequence jsp_board_seq nocache;

drop table jsp_reply;
--댓글 테이블
create table jsp_reply(
  re_idx number(8) primary key, --댓글 글번호
  re_name varchar2(30) not null, --댓글 작성자 아이디
  re_content varchar2(500), --댓글 내용
  re_readnum number(8) default 0, --댓글 조회수
  re_date date default sysdate, --댓글 작성일
  idx_fk number(8) references jsp_board(idx) 
  --on delete cascade--부모글 글번호 
);

drop sequence jsp_reply_seq;

create sequence jsp_reply_seq nocache;

desc jsp_reply;
