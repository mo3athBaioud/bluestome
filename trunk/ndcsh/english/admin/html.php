<?
//--����,���ֵ��ֶ���⴦��(ȥ��β�ո�)
function trans_string_trim($str) {
 $str=trim($str);
 $str=eregi_replace("'","''",$str);
 $str=stripslashes($str);
 return $str;
}

//--������⴦����textarea�ֶΣ�
function trans_string($str) {
 $str=eregi_replace("'","''",$str);
 $str=stripslashes($str);
 return $str;
}

//--�ӿ�����ʾ�ڱ��У���text����transת��,��textarea��,����ת��,ֱ����ʾ

//--��ʾ��WEBҳ�棬����HTML���룻�������ӵ�ַ
function trans($string) {
 $string=htmlspecialchars($string);
 $string=ereg_replace(chr(10),"<br>",$string);
 $string=ereg_replace(chr(32)," ",$string);
 return $string;   
}

//--��ʾ��WEBҳ�棬������HTML���룻
function trans_web($string) {
 $string=ereg_replace(chr(10),"<br>",$string);
 $string=ereg_replace(chr(32)," ",$string);
 return $string;   
}

//--��ʾ��WEBҳ�棬����HTML���뼰ͷβ�ո�,��Ҫ������ʾ�û��ǳ�
function trans_trim($string) {
 $string=trim($string);
 $string=htmlspecialchars($string);
 $string=ereg_replace(chr(10),"<br>",$string);
 $string=ereg_replace(chr(32)," ",$string);
 return $string;   
}

//--��ʾ��span�У�
#function trans_span($string) {
# $string=ereg_replace(chr(10),"\n",$string);
# $string=ereg_replace(chr(32)," ",$string);
# $string=ereg_replace('"',""",$string);
# return $string;   
#}

//��WEB����ʾcookie,����html
function trans_cookie($str) {
 $str=trans($str);
 $str=stripslashes($str);
 $str=eregi_replace("''","'",$str);
 return $str;
}
?>
