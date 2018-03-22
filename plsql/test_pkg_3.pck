CREATE OR REPLACE PACKAGE test_pkg_3 IS

TYPE CURSOR_REC2 IS RECORD (PeopleName varchar2(200), CityName varchar2(200));

TYPE CURSOR2 IS TABLE OF CURSOR_REC2;

FUNCTION GET_NAME(in_name string) RETURN CURSOR2 PIPELINED;

END test_pkg_3;
/
CREATE OR REPLACE PACKAGE BODY test_pkg_3 IS

FUNCTION GET_NAME(in_name string) RETURN CURSOR2 PIPELINED IS

BEGIN

FOR cur2 IN (
                select p.name, c.name cit_name from people p, city c
			where c.id = p.cit_id and c.name = in_name
)
    LOOP
        PIPE ROW(cur2);
    END LOOP;
RETURN;
END GET_NAME;

END test_pkg_3;
/
