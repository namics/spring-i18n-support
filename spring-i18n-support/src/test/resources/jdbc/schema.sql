DROP TABLE MESSAGES IF EXISTS;
CREATE TABLE MESSAGES (
	CODEID VARCHAR(255) NOT NULL ,
	LANGID VARCHAR(10) NOT NULL,
	MESSAGE VARCHAR(4000),
	TYPE VARCHAR(255)
);
ALTER TABLE MESSAGES ADD CONSTRAINT pk PRIMARY KEY (CODEID,LANGID);
	