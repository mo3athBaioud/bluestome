function addComments(url,argu,div_name) {
	alert(url);
	alert(argu);
	alert(div_name);
	alert(document.frmcomment.message.value);
	var ajax = InitAjax();
	var argu2 = argu + "&" + document.frmcomment.message.value;
	ajax.open("POST", url, true); 
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send(argu2);
	ajax.onreadystatechange = function() { 
		if (ajax.readyState == 4 && ajax.status == 200) { 
		   //books.innerHTML="<iframe src='http://192.168.16.81/br/reader/breader.php?bookno=" + bookno + "&memid=" + memid + "'  width=1000 height=600 frameborder='0' scrolling=no >";
//		   	  books.innerHTML=ajax.responseText;
//			  books.style.display = 'inline';
			loadContent(document.frmcomment.redir.value,argu,div_name);
	
		}			
	   
	} 	

}


function captureKeys(evt) {
evt = evt || window.event;
skip = 0;
		 if (IE) { // grab the x-y pos.s if browser is IE
			var keyCode = event.keyCode ? event.keyCode :
			event.charCode ? event.charCode : event.which;
			var who=event.srcElement || event.currentTarget || event.target;
			ctrlPressed=event.ctrlKey;			
			shiftPressed=event.shiftKey;				
				
		 } else {
			var keyCode = evt.keyCode ? evt.keyCode :
			evt.charCode ? evt.charCode : evt.which;	 
			var who =window.document.body;	
			
			ctrlPressed=evt.ctrlKey;			
			shiftPressed=evt.shiftKey;						
		 }
		 if ((keyCode == 33) || (keyCode == 34)) {
							if (evt.cancelable){
								evt.preventDefault();
								return false; 							
							}			 
							evt.returnValue = false;							
							return false; 														
							
		 }
		 return;

}

function captureKeys2(evt) {
evt = evt || window.event;
skip = 0;
		 if (IE) { // grab the x-y pos.s if browser is IE
			var keyCode = event.keyCode ? event.keyCode :
			event.charCode ? event.charCode : event.which;
			var who=event.srcElement || event.currentTarget || event.target;
			ctrlPressed=event.ctrlKey;			
			shiftPressed=event.shiftKey;						
				
		 } else {
			var keyCode = evt.keyCode ? evt.keyCode :
			evt.charCode ? evt.charCode : evt.which;	 
			var who =window.document.body;	
			
			ctrlPressed=evt.ctrlKey;			
			shiftPressed=evt.shiftKey;						
		 }
		 
		 if ((keyCode == 33) || (keyCode == 34)) {
			 if (keyCode == 33) { //PageUP
				 goPrevious();
				 
			 } else {
				 goNext();
				 
			 }
			if (evt.cancelable){
				evt.preventDefault();
				return false; 							
			}			 
			evt.returnValue = false;							
			return false; 										
							
		 }
		 return;		 

}


function goFirst(){
	
	thisMovie("mybook").gofirst();
	
}

function goNext(){
	
	thisMovie("mybook").gonext();
	
}

function goPrevious(){
	thisMovie("mybook").goprevious();
	
}

function goPage(i){
	
	thisMovie("mybook").gopage(i);
	
}

function goLast(){
	
	thisMovie("mybook").golast();
	
}




