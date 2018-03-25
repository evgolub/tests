CREATE OR REPLACE PACKAGE BODY test_pkg_3 IS

FUNCTION GET_NAME(in_sex string) RETURN CURSOR2 PIPELINED IS

BEGIN

FOR cur2 IN (
                select p.people_name, p.age, p.sex, c.city_name, s.soc_name, ca.car_name 
                from people p, city c, social s, car ca 
                where p.cit_id = c.id and p.soc_id = s.id and p.car_id = ca.id
                and p.sex = in_sex
)
    LOOP
        PIPE ROW(cur2);
    END LOOP;
RETURN;
END GET_NAME;

END test_pkg_3;
