
/* Drop Tables */

DROP TABLE JIBUN_ADDR CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE JIBUN_ADDR
(
	code varchar2(75) NOT NULL,
	code_num number(3,0) NOT NULL,
	dong_code varchar2(30) NOT NULL,
	sido_name varchar2(60) NOT NULL,
	gugun_name varchar2(60),
	dong_name varchar2(60),
	lee_name varchar2(60),
	san char(1),
	jibun number(4,0),
	jibun_sub number(4,0),
	major char(1),
	PRIMARY KEY (code, code_num)
);



