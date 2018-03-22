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
