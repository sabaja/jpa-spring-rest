CREATE TABLE HIBERNATE.NOTES 
( 
	ID INTEGER NOT NULL AUTO_INCREMENT,
	TITLE VARCHAR(100) NOT NULL,
	CONTENT VARCHAR(100),
	CREATEDAT 	timestamp,
    UPDATEDAT 	timestamp,
	PRIMARY KEY (ID) 
)
 ENGINE = InnoDB;
 
 commit;
 
 use HIBERNATE;
 
 select * from Notes;
