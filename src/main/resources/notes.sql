SELECT * FROM MVC.NOTES;
SELECT * FROM MVC.USER;
SELECT * FROM MVC.VOTES;

SELECT count(*) FROM MVC.NOTES;
SELECT count(*) FROM MVC.USER;
SELECT count(*) FROM MVC.VOTES;

SET SESSION autocommit = 0;

SELECT * FROM MVC.VOTES where notes_id = 9;
insert into MVC.VOTES 
(id, create_date, vote, notes_id)
values (FLOOR(RAND() * 999999), now(),8, 9);
COMMIT;

show global variables;

select @@sql_log_off;

SET GLOBAL sql_log_off=true;

use MVC;

delete N, U, V 
FROM NOTES N 
inner join USER U
on N.USER_ID = U.ID
inner join VOTES V
on V.NOTES_ID = N.ID
and N.ID > 150; 
