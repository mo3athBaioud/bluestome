

<style type="text/css">
#profile {
	position:absolute;
	left:60px;
	top:37px;
	width:585px;
	height:321px;
	z-index:1;
}
DIV.books {
	display : inline;
	border: 1px solid #ddd;
	overflow :auto;
	position:relative;
	left:1px;
	top:1px;
	width:auto;
	height:auto;
	z-index:30;
  	background:  #ffffff no-repeat 1px 1px;
	margin-top : 5px;	
	margin-bottom : 5px;		
	padding-left : 20px;
	padding-right : 20px; 

  	
}
.L_comments {
	display : none;
	overflow :auto;
	position:static;
	left:1000px;
	top:900px;
	width:auto;
	height:auto;
	z-index:20;
  	
}

.reader_bar {
	display:none;
}
</style>

<?PHP 

	if (file_exists("../conn.php")) {
		require_once('../conn.php');
	}else{
		if (file_exists("conn.php")) {
			require_once('conn.php');		
		}
	}	

 ?>
	<script type='text/javascript'>
<?	
	echo "var shome = '" . $SHOME . "';"; 
?>	
	
var GRB_MEMID="none";
var FontColor = "#666666";
var nFontColor = "#ffffcc";
var BackColor = "#ffcc99";
var nBackColor = "#0099cc"
function addComments(url,argu,div_name) {
	var ajax = InitAjax();
	var argu2 = argu + "&message=" + document.frmcomment.message.value;
	ajax.open("POST", url, true); 
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send(argu2);
	ajax.onreadystatechange = function() { 
		if (ajax.readyState == 4 && ajax.status == 200) { 
			loadContent(document.frmcomment.redir.value,argu,div_name);
	
		}			
	   
	} 	

}

function InitAjax()
{
	var ajax=false; 
	try { 
		ajax = new ActiveXObject("Msxml2.XMLHTTP"); 
	} catch (e) { 
		try { 
			ajax = new ActiveXObject("Microsoft.XMLHTTP"); 
		} catch (E) { 
			ajax = false; 
		} 
	}

	if (!ajax && typeof XMLHttpRequest!='undefined') { 
		ajax = new XMLHttpRequest(); 
	} 
	return ajax;
} 



function showBook(bookno){
	var pageObj;
	var menu=document.getElementById("menubar");
	var main = document.getElementById("mainpage");			
	var mainF = document.getElementById("mainFrame");				
//	var i;
	var sbookid = "book" + bookno;
//	alert(bookno);
	for (i=0; i<menu.childNodes.length; i++){
		//if (menu.childNodes[i].nodeName=="LI")

		if (menu.childNodes[i].nodeName=="SPAN") {

			if (menu.childNodes[i].id == bookno) {
				pageObj = document.getElementById(menu.childNodes[i].getAttribute("relid"));			
				pageObj.style.display = 'inline';
				menu.childNodes[i].style.background = BackColor;
				menu.childNodes[i].style.color = FontColor;				
			} else {
				if (menu.childNodes[i].id == "bmainpage") {				
					pageObj = document.getElementById('mainpage');				
					pageObj.style.display = 'none';					
					menu.childNodes[i].style.background = nBackColor;
					menu.childNodes[i].style.color = nFontColor;					
				} else {
					pageObj = document.getElementById(menu.childNodes[i].getAttribute("relid"));				
					pageObj.style.display = 'none';
					menu.childNodes[i].style.background = nBackColor;
					menu.childNodes[i].style.color = nFontColor;										
//					mainF.removeChild(pageObj);				
				}				
			}	
		}		
				
	}
	

}




function showBook2(){
	var pageObj;
	
  	var ns4 = document.layers;
  	var ns6 = document.getElementById && !document.all;
  	var ie4 = document.all;
  	
	var menu=document.getElementById("menubar");
	var main = document.getElementById("mainpage");
	var mainF = document.getElementById("mainFrame");				
	var bookno;

  	if(ns4) {
			bookno = event.target.id;	
  	} else if(ns6) {
			bookno = event.target.id;
  	} else if(ie4) {
			bookno = event.srcElement.id;
  	} else {
	
	}	
	var sbookid = "book" + bookno;
	for (i=0; i<menu.childNodes.length; i++){
		//if (menu.childNodes[i].nodeName=="LI")

		if (menu.childNodes[i].nodeName=="SPAN") {
			if (menu.childNodes[i].id == bookno) {
				pageObj = document.getElementById(menu.childNodes[i].getAttribute("relid"));			
//				alert(menu.childNodes[i].getAttribute("relid") + 'aaa');
				if (pageObj) {
					pageObj.style.display = 'inline';
					menu.childNodes[i].style.background = BackColor;
					menu.childNodes[i].style.color = FontColor;					
				} else {
					openpage(menu.childNodes[i].getAttribute('bid'),menu.childNodes[i].getAttribute('memid'));				
				}	
			} else {
				if (menu.childNodes[i].id == "bmainpage") {				
					pageObj = document.getElementById('mainpage');				
					pageObj.style.display = 'none';
					menu.childNodes[i].style.background = nBackColor;
					menu.childNodes[i].style.color = nFontColor;										
				} else {
					pageObj = document.getElementById(menu.childNodes[i].getAttribute("relid"));				
					pageObj.style.display = 'none';
					menu.childNodes[i].style.background = nBackColor;
					menu.childNodes[i].style.color = nFontColor;										
//					mainF.removeChild(pageObj);				
				}				
			}	
		}		
				
	}
	

}



function openpage(bookno,memid)
{
//	alert(bookno);
	var menu = document.getElementById("menubar");
	
	var books = document.createElement("DIV");
	var main = document.getElementById("mainpage");	
	var mainF = document.getElementById("mainFrame");
	var menux = document.getElementById(bookno);	

  	books.id = "book" + bookno+memid;		   
	books.className="books";
	mainF.appendChild(books);	

	var postStr = "bookno=" + bookno + "&memid=" + memid + "&pageid=" + books.id;
	var url = "<?PHP echo $SHOME ?>reader?" + postStr;
	var url2 = "<?PHP echo $SHOME ?>reader/get_comments.php?" + postStr;	
	var ajax = InitAjax();
	ajax.open("POST", url, true); 
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send(postStr);
	ajax.onreadystatechange = function() { 
		if (ajax.readyState == 4 && ajax.status == 200) { 
		   //books.innerHTML="<iframe src='http://192.168.16.81/br/reader/breader.php?bookno=" + bookno + "&memid=" + memid + "'  width=1000 height=600 frameborder='0' scrolling=no >";
			  books.style.display = 'inline';
			  menux.style.background = BackColor;
			  menux.style.color = FontColor;			  
		   	  books.innerHTML=ajax.responseText;

//				var comment = document.createElement("DIV");								  
//				comment.id = "L_comments" + bookno;
//				comment.className = "L_comments";
//				books.appendChild(comment);
				
//				var ajax2 = InitAjax();				
//				ajax2.open("POST", url2, true); 
//				ajax2.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
//				ajax2.send(postStr);
//				ajax2.onreadystatechange = function() { 				  
//					if (ajax2.readyState == 4 && ajax2.status == 200) { 

//					   	  comment.innerHTML=ajax2.responseText;
//						  comment.style.display = 'inline';			  
//	    			}
//				}			
		}			
	   
	} 
}


function openbook(bookno,memid,booktitle,w,h)
{
	var property = "";
	property = "location=yes,status=yes,scrollbars=yes,toolbar=yes,menubar=yes,directories=yes,resizable=yes,width="+w+",height="+h;
	window.open(shome+"reader?bookno=" + bookno + "&bmemid=" + memid + "&shome=" + shome , bookno , property);
}


function openwindow(url,title,w,h)
{
//alert('aaa');
	var property = "";
	property = "location=yes,status=yes,scrollbars=yes,toolbar=yes,menubar=yes,directories=yes,resizable=yes";
//	alert(property);
	window.open(url , title , property);
}

function openbook2(shome,bookno,memid,booktitle,w,h)
{
	var property = "";
	property = "location=yes,status=yes,scrollbars=yes,toolbar=yes,menubar=yes,directories=yes,resizable=yes,width="+w+",height="+h;
	window.open(shome+"reader?bookno=" + bookno + "&bmemid=" + memid + "&shome=" + shome , bookno , property);
}





function closereader(bookid,memid)
{
	
	var books = document.getElementById("book"+bookid+memid);
	var bookmenu = document.getElementById(bookid+memid);
	var menu = document.getElementById("menubar");
	var main = document.getElementById("mainpage");			
	var mainF = document.getElementById("mainFrame");
	
	var mainmenu=document.getElementById("bmainpage");				
	mainmenu.style.background = BackColor;
	mainmenu.style.color =  FontColor;					
	
	  books.style.display = 'none';
	  bookmenu.style.display = 'none';
	  mainF.removeChild(books);
	  menu.removeChild(bookmenu);
	  main.style.display = 'inline';
}


function showMain(){
	var pageObj;
	var menu=document.getElementById("menubar");
	var main = document.getElementById("mainpage");		
	var mainF = document.getElementById("mainFrame");			
//	var i;
	for (i=0; i<menu.childNodes.length; i++){
		//if (menu.childNodes[i].nodeName=="LI")
		if (menu.childNodes[i].nodeName=="SPAN") {
		
//			pageObj = document.getElementById(menu.childNodes[i].getAttribute("relid"));

			if (menu.childNodes[i].id=="bmainpage") {
				pageObj = document.getElementById('mainpage');			
				pageObj.style.display = 'inline';
				
				menu.childNodes[i].style.background = BackColor;
				menu.childNodes[i].style.color = FontColor;
				
			} else {
				pageObj = document.getElementById("book" + menu.childNodes[i].id);			
				if (pageObj) {
					pageObj.style.display = 'none';
					
					menu.childNodes[i].style.background = nBackColor;
					menu.childNodes[i].style.color =  nFontColor;
					
//					mainF.removeChild(pageObj);
				}	
			}	
		}		
				
	}
//alert(menu.childNodes.length);	

}
//-->
</script>
<? //echo "																									 <div class = 'books' id='books'></div>" ?>


