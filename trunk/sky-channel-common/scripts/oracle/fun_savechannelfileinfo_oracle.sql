--------------------------------------------
-- Export file for user MUSICTEST         --
-- Created by John on 2010/1/14, 16:11:06 --
--------------------------------------------

spool function.log

prompt
prompt Creating function FUN_SAVECHANNELFILEINFO
prompt =========================================
prompt
create or replace function fun_saveChannelFileInfo(
provincecode varchar2,
fileName varchar2,
startPrice number,
endPrice number,
starttime       date,
endtime       date
)return INTEGER
is
resultcode   INTEGER;
v_provincecode varchar2(10);
begin
resultcode := 200;
v_provincecode:=provincecode;
   --move old info to  TBL_PROVINCE_FILE_INFO_EXPIRED
   INSERT INTO  TBL_PROVINCE_FILE_INFO_EXPIRED
               (PROVINCECODE, FILENAME, STARTPRICE, ENDPRICE, STARTTIME,ENDTIME)
   SELECT PROVINCECODE, FILENAME, STARTPRICE, ENDPRICE, STARTTIME,ENDTIME FROM TBL_PROVINCE_FILE_INFO T
   WHERE  provincecode =  v_provincecode;
   --delete old info
   DELETE FROM  tbl_province_file_info t where t.provincecode =  v_provincecode;
   --insert new info
   INSERT INTO TBL_PROVINCE_FILE_INFO
               (PROVINCECODE, FILENAME, STARTPRICE, ENDPRICE, STARTTIME,ENDTIME)
        VALUES (v_provincecode,fileName,startPrice,endPrice,starttime,endtime
               );
commit;
return resultcode;
exception
WHEN OTHERS
   THEN
      RAISE;
end;
/


spool off
