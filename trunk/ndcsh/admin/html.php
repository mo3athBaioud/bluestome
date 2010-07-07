<?
//--标题,名字等字段入库处理(去首尾空格)
function trans_string_trim($str) {
 $str=trim($str);
 $str=eregi_replace("'","''",$str);
 $str=stripslashes($str);
 return $str;
}

//--文章入库处理，即textarea字段；
function trans_string($str) {
 $str=eregi_replace("'","''",$str);
 $str=stripslashes($str);
 return $str;
}

//--从库中显示在表单中；在text中以trans转换,在textarea中,无需转换,直接显示

//--显示在WEB页面，过滤HTML代码；包括链接地址
function trans($string) {
 $string=htmlspecialchars($string);
 $string=ereg_replace(chr(10),"<br>",$string);
 $string=ereg_replace(chr(32)," ",$string);
 return $string;   
}

//--显示在WEB页面，不过滤HTML代码；
function trans_web($string) {
 $string=ereg_replace(chr(10),"<br>",$string);
 $string=ereg_replace(chr(32)," ",$string);
 return $string;   
}

//--显示在WEB页面，过滤HTML代码及头尾空格,主要用于显示用户昵称
function trans_trim($string) {
 $string=trim($string);
 $string=htmlspecialchars($string);
 $string=ereg_replace(chr(10),"<br>",$string);
 $string=ereg_replace(chr(32)," ",$string);
 return $string;   
}

//--显示在span中；
#function trans_span($string) {
# $string=ereg_replace(chr(10),"\n",$string);
# $string=ereg_replace(chr(32)," ",$string);
# $string=ereg_replace('"',""",$string);
# return $string;   
#}

//在WEB上显示cookie,过滤html
function trans_cookie($str) {
 $str=trans($str);
 $str=stripslashes($str);
 $str=eregi_replace("''","'",$str);
 return $str;
}
?>
