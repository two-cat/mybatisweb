drop table jsp_board;
--�ܼ��� �Խ���
create table jsp_board(
 idx number(8) constraint jsp_board_idx_pk primary key, --�۹�ȣ
 name varchar2(30) not null, --�ۼ���
 subject varchar2(200) not null, --����
 content varchar2(2000), --����
 wdate date default sysdate, --�ۼ���
 readnum number(8) default 0, --��ȸ��
 filename varchar2(200), --÷�����ϸ�
 filesize number(8) --÷������ũ��
);

drop sequence jsp_board_seq;

create sequence jsp_board_seq nocache;

drop table jsp_reply;
--��� ���̺�
create table jsp_reply(
  re_idx number(8) primary key, --��� �۹�ȣ
  re_name varchar2(30) not null, --��� �ۼ��� ���̵�
  re_content varchar2(500), --��� ����
  re_readnum number(8) default 0, --��� ��ȸ��
  re_date date default sysdate, --��� �ۼ���
  idx_fk number(8) references jsp_board(idx) 
  --on delete cascade--�θ�� �۹�ȣ 
);

drop sequence jsp_reply_seq;

create sequence jsp_reply_seq nocache;

desc jsp_reply;
