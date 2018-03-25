CREATE OR REPLACE PACKAGE test_pkg_3 IS

TYPE CURSOR_REC2 IS RECORD (PeopleName varchar2(200), Age int, Sex varchar2(200), CityName varchar2(200), SocialStatus varchar2(200), Car varchar2(200));

TYPE CURSOR2 IS TABLE OF CURSOR_REC2;

FUNCTION GET_NAME(in_sex string) RETURN CURSOR2 PIPELINED;

END test_pkg_3;
/
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
/
