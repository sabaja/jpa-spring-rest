SELECT * FROM MVC.NOTES;
SELECT count(*) FROM MVC.USER;
SELECT count(*) FROM MVC.VOTES;

SET SESSION autocommit = 0;

SELECT * FROM MVC.VOTES where notes_id = 9;
insert into MVC.VOTES 
(id, create_date, vote, notes_id)
values (FLOOR(RAND() * 999999), now(),6, 9);
COMMIT;

show global variables;

select @@sql_log_off;

SET GLOBAL sql_log_off=true;