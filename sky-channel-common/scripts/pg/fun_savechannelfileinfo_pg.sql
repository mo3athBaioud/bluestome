CREATE OR REPLACE FUNCTION fun_savechannelfileinfo 
(in provincecode varchar, in filename varchar, in filebytes bytea, in startprice numeric, in endprice numeric, in starttime timestamp, in endtime timestamp) RETURNS integer AS
$BODY$
DECLARE
    	resultcode   integer;
		v_provincecode varchar(10);
BEGIN

	resultcode := 200;
	v_provincecode:=provincecode;
      
   insert into  TBL_channel_FILE_INFO_EXPIRED              
   SELECT PROVINCECODE, FILENAME, fileBytes,STARTPRICE, ENDPRICE, STARTTIME,ENDTIME FROM TBL_channel_FILE_INFO t
   WHERE  t.provincecode =  v_provincecode;
   
      
   DELETE FROM  TBL_channel_FILE_INFO t where t.provincecode =  v_provincecode;
      INSERT INTO TBL_channel_FILE_INFO
                       VALUES (v_provincecode,fileName,fileBytes,startPrice,endPrice,starttime,endtime);
               
return resultcode;
EXCEPTION
WHEN OTHERS THEN
    raise notice 'ERROR';
     return 400;
END;
$BODY$
LANGUAGE 'plpgsql'