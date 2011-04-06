// 扩展extjs的验证！

Ext.apply(Ext.form.VTypes, {
     // 固定电话、传真
     phone: function(v) {
         var r = /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/;
         return r.test(v);
     },
     phoneText : '请输入正确的电话或传真号码!<br />格式如：0000-XXXXXXXX',
     // 移动电话
     mobile : function(v) {
         var r = /^((\(\d{2,3}\))|(\d{3}\-))?1?[3,5,8]\d{9}$/;
//         alert('mobile:\t'+r.test(v));
         return r.test(v);
     },
     mobileText : '请输入正确的移动电话号码!<br />格式如：13{5,8}XXXXXXXXX',
     // 邮政编码
     zip : function(v) {
         var r = /^[1-9]\d{5}$/;
         return r.test(v);
     },
     zipText : '请输入正确的邮政编码!',
     // 搜索关键字过滤
     search : function(v) {
         var r = /^[^`~!@#$%^&*()+=|\\\][\]\{\}:;'\,.<>/; //?]*$/
         return r.test(v);
     },
     searchText : '请不要输入非法的搜索字符!',
     // 简体中文
     chinese : function(v) {
         var r = /^[\u0391-\uFFE5]+$/;
         return r.test(v);
     },
     chineseText : '您只能在这里输入中文字符!',
     // 非中文
     'noChinese' : function(v) {
         var r = /^^[\u0391-\uFFE5]+$/;
         return r.test(v);
     },
    noChineseText : '您不能在这里输入中文字符!',
     // 货币
     currency : function(v) {
         var r = /^\d+(\.\d+)?$/;
         return r.test(v);
     },
     currencyText : '请输入货币值!<br />格式如：1.00',
     // QQ
     qq : function(v) {
         var r = /^[1-9]\d{4,8}$/;
         return r.test(v);
     },
     qqText : '请输入合法的QQ号码!',
     // 安全密码
     safe : function(v) {
         var r = /^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/;
         return !r.test(v);
     },
     safeText : '请输入足够安全的字符,包含英文和数字货其他字符!',
     numeric: function(v){
	  	var objRegExp  =  /(^-?\d\d*\.\d*$)|(^-?\d\d*$)|(^-?\.\d\d*$)/;
	  	return function(v){
			  //check for numeric characters
		  	return objRegExp.test(v);
	  	}
	},
	 numericText: '只允许输入数字',
	ssn: function(v){
        var re = /^([0-6]\d{2}|7[0-6]\d|77[0-2])([ \-]?)(\d{2})\2(\d{4})$/;
        return function(v){
                return re.test(v);
        }
    },
    ssnText : 'SSN format: xxx-xx-xxxx',
	password : function(val, field) {
        if (field.initialPassField) {
            var pwd = Ext.getCmp(field.initialPassField);
            return (val == pwd.getValue());
        }
        return true;
    },
    passwordText : '密码不匹配',
    url : function(v){
    	///(http[s]?|ftp):\/\/[^\/\.]+?\..+\w$/i
    	var r = /\/[^\/\.]+?\..+\w$/i;
		return r.test(v);
    },
   	urlText:'请输入正确格式的URL!',
 	integer:function(val,field)  {  
	         try  
	         {  
	             if(/^[-+]?[\d]+$/.test(val))  
	                 return true;  
	             return false;  
	         }  
	         catch(e)  
	         {  
	             return false;  
	         }  
	},  
    integerText:'请输入正确的整型数字',
 	ip:function(val,field){  
         try  
         {  
             if((/^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(val)))  
                 return true;  
             return false;  
         }  
         catch(e)  
         {  
             return false;  
         }  
   },  
   ipText:'请输入正确的IP地址',
   alpha:function(val,field)  
   {  
         try  
         {  
             if( /^[a-zA-Z]+$/.test(val))  
                 return true;  
             return false;  
         }  
         catch(e)  
         {  
             return false;  
         }  
   },  
   alphaText:'请输入英文字母',  
   beta:function(val,field)  
   {  
         try  
         {  
             if( /^([a-zA-Z]([\_]?))+$/.test(val))  
                 return true;  
             return false;  
         }  
         catch(e)  
         {  
             return false;  
         }  
   },  
   betaText:'请输入英文字母，下划线组合',  
   money:function(val,field){  
         try  
         {  
             if(/^\d+\.\d{2}$/.test(val))          
                 return true;  
         return false;     
         }  
         catch(e)  
         {  
             return false;     
         }  
   
   },  
   moneyText:'请输入正确的金额',
   minlength:function(val,field)  
   {  
         try  
         {  
             if(val.length >= parseInt(field.minlen))  
                 return true;  
             return false  
         }  
         catch(e)  
         {  
             return false;  
         }  
   },  
   minlengthText:'长度过小',  
   maxlength:function(val,field)  
   {  
      try  
      {  
         if(val.length <= parseInt(field.maxlen))  
             return true;  
         return false;  
      }  
      catch(e)  
      {  
         return false;  
      }  
   },  
   maxlengthText:'长度过大',  
   datecn:function(val,field)  
   {  
         try  
         {  
             var regex = /^(\d{4})-(\d{2})-(\d{2})$/;  
             if(!regex.test(val)) return false;  
             var d = new Date(val.replace(regex, '$1/$2/$3'));  
             return (parseInt(RegExp.$2, 10) == (1+d.getMonth())) && (parseInt(RegExp.$3, 10) == d.getDate())&&(parseInt(RegExp.$1, 10) == d.getFullYear());  
         }  
         catch(e)  
         {  
             return false;  
         }  
   },  
   datecnText:'请使用这样的日期格式: yyyy-mm-dd. 例如:2008-06-20.',
   max:function(val,field)  
   {  
         try  
         {  
             if(parseFloat(val) <= parseFloat(field.max))  
                 return true;  
             return false;  
         }  
         catch(e)  
         {  
             return false;  
         }  
   },  
   maxText:'超过最大值',
  url1:function(val,field)  
   {  
         try  
         {  
             if(/^(http|https|ftp):\/\/(([A-Z0-9][A-Z0-9_-]*)(\.[A-Z0-9][A-Z0-9_-]*)+)(:(\d+))?\/?/i.test(val))  
                 return true;  
             return false;  
         }  
         catch(e)  
         {  
             return false;  
         }  
   },  
   url1Text:'请输入有效的URL地址.',  
   age:function(val,field)  
   {  
         try  
         {  
             if(parseInt(val) >= 18 && parseInt(val) <= 100)  
                 return true;  
             return false;  
         }  
         catch(err)   
         {  
             return false;  
         }  
   },  
   ageText:'年龄输入有误',
   alphanum:function(val,field)  
   {  
         try  
         {  
             if(!/\W/.test(val))  
                 return true;  
             return false;  
         }  
         catch(e)  
         {  
             return false;  
         }  
   },  
   alphanumText:'请输入英文字母或是数字,其它字符是不允许的.'       
})
//Ext.form.VTypes["phone"] = /^(\d{3}[-]?){1,2}(\d{4})$/;
//Ext.form.VTypes["phoneMask"] = /[\d-]/;
//Ext.form.VTypes["phoneText"] = 'Not a valid phone number.  Must be in the format 123-4567 or 123-456-7890 (dashes optional)';
