/*Разделить надо туловище от спеки*/
CREATE OR REPLACE PACKAGE test_pkg_3 IS

TYPE CURSOR_REC2 IS RECORD (id int, name varchar2(200), city_id int);

TYPE CURSOR2 IS TABLE OF CURSOR_REC2;

FUNCTION GET_NAME RETURN CURSOR2 PIPELINED;

END test_pkg_3;
/
CREATE OR REPLACE PACKAGE BODY test_pkg_3 IS
FUNCTION GET_NAME RETURN CURSOR2 PIPELINED IS

BEGIN

FOR cur2 IN (select id, name, city_id from people)
    LOOP
        PIPE ROW(cur2);
    END LOOP;
RETURN;
END GET_NAME;

END test_pkg_3;
/
