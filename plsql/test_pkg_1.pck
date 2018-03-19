CREATE OR REPLACE PACKAGE test_pkg_1 IS

PROCEDURE get_name;

END test_pkg_1;
/
CREATE OR REPLACE PACKAGE BODY test_pkg_1 IS

PROCEDURE get_name IS

empname VARCHAR2(200);
    CURSOR c1 IS
        SELECT people.name
            FROM people;

BEGIN

OPEN c1;
        LOOP
            FETCH c1 INTO empname;
            EXIT WHEN c1%NOTFOUND;
            DBMS_OUTPUT.put_line(empname);
        END LOOP;
CLOSE c1;

END get_name;

END test_pkg_1;
/
