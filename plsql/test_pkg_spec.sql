CREATE OR REPLACE PACKAGE test_pkg_3 IS

TYPE CURSOR_REC2 IS RECORD (PeopleName varchar2(200), Age int, Sex varchar2(200), CityName varchar2(200), SocialStatus varchar2(200), Car varchar2(200));

TYPE CURSOR2 IS TABLE OF CURSOR_REC2;

FUNCTION GET_NAME(in_sex string) RETURN CURSOR2 PIPELINED;

END test_pkg_3;
