CREATE OR REPLACE PACKAGE BODY test_pkg_3 IS
FUNCTION GET_NAME RETURN CURSOR2 PIPELINED IS

BEGIN

FOR cur2 IN (    
                select p.name, c.name cname, f.fr_name, v.ve_name 
                    from people p, city c, fruits f, vegetables v 
                         where c.id = p.city_id 
                               and f.id = p.id
                                   and v.id = p.id
)
    LOOP
        PIPE ROW(cur2);
    END LOOP;
RETURN;
END GET_NAME;

END test_pkg_3;
