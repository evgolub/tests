CREATE OR REPLACE PACKAGE BODY test_pkg_3 IS

FUNCTION GET_NAME(in_name string) RETURN CURSOR2 PIPELINED IS

BEGIN

<<<<<<< HEAD
FOR cur2 IN (
                select p.name, c.name cit_name from people p, city c
			where c.id = p.cit_id and c.name = in_name
=======
FOR cur2 IN (    
                select p.name, c.name cname, f.fr_name, v.ve_name 
                    from people p, city c, fruits f, vegetables v 
                         where c.id = p.city_id 
                               and f.id = p.id
                                   and v.id = p.id
				/*очень странный запрос, так не делается
				у каждой таблицы есть primary_key и foreign_key, через которые они связываются если нужна связка между ними
				в итоге у таблицы people должен быть свой ID, например peop_id, а у таблицы city например cit_id.
				И тогда, чтобы эти таблицы были связаны между собой надо в таблицу people ввести поле cit_cit_id, которое будет являться 
				foreign ключам для таблицы city.
				Запрос тогда сработает правильно и будет выглядеть так:
				select p.name from people p, city c*
				where c.cit_id = p.cit_cit_id and c.name = 'Иркутск'
				Т.е. отбираем имена людей, у которых название города = Иркутск
				Или можно по православному:
				select p.name from people p
				join city c on c.cit_id = p.cit_cit_id
				where c.name = 'Иркутск' */
>>>>>>> 11ec9b13635b07716bc24e1667b1554d3bf5987d
)
    LOOP
        PIPE ROW(cur2);
    END LOOP;
RETURN;
END GET_NAME;

END test_pkg_3;
