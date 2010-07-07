<? include("../conn.php");
		  			if	(session_is_registered ("bookroll_memid"))  	{
						$memid =  $_SESSION["bookroll_memid"] ;
					}


		$memid = "allen@cvl.com.tw";
echo "        <script>";
?>




var target="_blank";  // 開啟指定網頁所使用的 Frame
var MAIN_bgcolor = "#64D0FE";   // 主目錄背景色彩
var SUB_bgcolor = "#CCEEFF";   // 子目錄背景色彩
var width = "200";   // 目錄寬度
var newx=1,newy=1;	// 目錄左上端起始位置


var layerName,l,currmenu=0;
var iMAIN = 0;
var newMainItem = new initArray();
var newSubItem = new initArray();
/*
newSubItem[0] = new initArray();
newSubItem[0][0] = "Jack";
*/
var HTMLstr="";

// Example: obj = findObj("image1");
function findObj(theObj, theDoc)
{
  var p, i, foundObj;
  
  if(!theDoc) theDoc = document;
  if( (p = theObj.indexOf("?")) > 0 && parent.frames.length)
  {
    theDoc = parent.frames[theObj.substring(p+1)].document;
    theObj = theObj.substring(0,p);
  }
  if(!(foundObj = theDoc[theObj]) && theDoc.all) foundObj = theDoc.all[theObj];
  for (i=0; !foundObj && i < theDoc.forms.length; i++) 
    foundObj = theDoc.forms[i][theObj];
  for(i=0; !foundObj && theDoc.layers && i < theDoc.layers.length; i++) 
    foundObj = findObj(theObj,theDoc.layers[i].document);
  if(!foundObj && document.getElementById) foundObj = document.getElementById(theObj);
  
  return foundObj;
}

	alert(newy);

function initArray() {
	this.length = initArray.arguments.length;
	for (var i = 0; i < this.length; i++) {
		this[i] = initArray.arguments[i];
	}
}

function MainItem(Mclass,name,loc,iCount) {
        this.Mclass = Mclass;
        this.name = name;
        this.loc = loc;
        this.iCount = iCount;
        return this;
}

function SubItem(Mclass,name,loc) {
        this.Mclass = Mclass;
        this.name = name;
        this.loc = loc;
        return this;
}

function addMainItem(name,loc) {
alert(name);
	newMainItem[iMAIN] = new MainItem(iMAIN,name,loc,0);
	iMAIN++;
}

function addSubItem(Mclass,name,loc) {
	newSubItem[Mclass][newMainItem[Mclass].iCount] = new SubItem(Mclass,name,loc);
	newMainItem[Mclass].iCount++;
}

function makemenu() {

	HTMLstr = "";
	HTMLstr += "<!-- MENU MAKE BEGINS -->\n";
	HTMLstr += "\n";
	HTMLstr += "<DIV id=linkList>\n";
	HTMLstr += "	<DIV id=linkList2>";
	HTMLstr += "	<DIV id=lselect >";
	HTMLstr += "		<H3 class=select><SPAN>Select a Design:</SPAN></H3>	";
	
	HTMLstr += "	  <img class=\"img_me\" src = '";
	<?PHP
		  		    $dSQL = "";
					$dSQL = "SELECT * " . 
					" FROM member m  WHERE m.email = '" . $memid . "'"; 

					$query_Com = odbc_exec($conn, $dSQL);
					$row_Com = odbc_fetch_row($query_Com);
					echo "HTMLstr += \"../home/" . $memid . "/" . odbc_result($query_Com,"pic") . "'>\";\n";
					echo "HTMLstr +=  \"		&nbsp;	<SPAN class='me_nickname_normal'>" . odbc_result($query_Com,"nickname") . "</SPAN>\";";		
	?>		

	HTMLstr += "<!-- MAIN MENU STARTS -->\n";
	HTMLstr += "<!-- MAIN_MENU -->\n";
	HTMLstr += "<!-- MAIN MENU ENDS -->\n";

	HTMLstr += "\n";
	HTMLstr += "<!-- SUB MENU STARTS -->\n";
	HTMLstr += "<!-- SUB_MENU -->\n";
	HTMLstr += "<!-- SUB MENU ENDS -->\n";
	HTMLstr += "\n";
	HTMLstr +=  "</DIV></DIV></DIV>";
	HTMLstr += "<!-- MENU MAKE ENDS -->\n";
}


function makeSubArray() {
	for(var temp_j = 0; temp_j < iMAIN ; temp_j++)
		newSubItem[temp_j] = new initArray();
}




function makeFirst() {
	MENUstr = "";
//	MENUstr += "<UL id=\"menu0\">\n";
	MENUstr += "<span id=\"menu0\">\n";
//	MENUstr += "<Li> \n";
	for(var i=0;i<iMAIN;i++) {
//		MENUstr += "																									 <table border=0 width=";
//		MENUstr += width;
//		MENUstr += " cellspacing=2>\n";
//		MENUstr += "<tr bgcolor=\"";
//		MENUstr += MAIN_bgcolor;
//		MENUstr += "\">\n";
//		MENUstr += "<td>　\n";
		MENUstr += "<Li> \n";
		temp_use = i+1;

		if(newMainItem[i].loc == null) {
			MENUstr += "<a href=\"javascript://\" onClick=\"hideall(";
			MENUstr += temp_use
			MENUstr += ")\">";
		}
		else {
			MENUstr += "<a href=\"";
			MENUstr += newMainItem[i].loc;
			MENUstr += "\" target=";
			MENUstr += target;
			MENUstr += ">";
		}
		MENUstr += newMainItem[i].name;
		MENUstr += "</a>";


//		MENUstr += "</td>\n";
//		MENUstr += "</tr>\n";
//		MENUstr += "</table>\n\n";
		MENUstr += "</Li>";				
	}
	MENUstr += "</span>";
	//MENUstr += "</UL>";		
	HTMLstr = HTMLstr.replace("<!-- MAIN_MENU -->\n", MENUstr);
	makeSubArray();	//建立雙層的陣列
}

function makeSecond() {
	MENUstr = "";
	for(var temp_j=0 ; temp_j < iMAIN ; temp_j++) {
		DIVnum = temp_j+1;
//		MENUstr += "<Li>";				
		MENUstr += "<span id=\"menu";
//		MENUstr += "<UL id=\"menu";
		MENUstr += DIVnum;
		MENUstr += "\">\n";

		for(var temp_i=0 ; temp_i < iMAIN ; temp_i++) {
			if(temp_j != temp_i) {
//				MENUstr += "<table border=0 width=";
//				MENUstr += width;
//				MENUstr += " cellspacing=2>\n";
//				MENUstr += "<tr>\n";
//				MENUstr += "<td bgcolor=\"";
//				MENUstr += MAIN_bgcolor;
//				MENUstr += "\">　";
				MENUstr += "<Li>";				
				MENUstr += "<a href=\"";

				if(newMainItem[temp_i].loc == null)	MENUstr += "javascript://";
				else								MENUstr += newMainItem[temp_i].loc;

				MENUstr += "\" class=\"multimenu\" ";
				
				if(newMainItem[temp_i].loc != null)	{
					MENUstr += "target=";
					MENUstr += target;
				}

				MENUstr += " onClick=\"hideall(";
				temp_use = temp_i+1;
				MENUstr += temp_use;
				MENUstr += ")\"";
				MENUstr += ">";

				MENUstr += newMainItem[temp_i].name;

				MENUstr += "</a>";
				MENUstr += "</Li>";				
//				MENUstr += "</td>";
//				MENUstr += "</tr>";
//				MENUstr += "</table>";
			}
			else {
				iLIMIT = newMainItem[temp_j].iCount;

//				MENUstr += "<table border=0 width=";
//				MENUstr += width;
//				MENUstr += " cellspacing=2>\n";

//				MENUstr += "<tr>\n";

//				MENUstr += "<td bgcolor=\"";
//				MENUstr += MAIN_bgcolor;
//				MENUstr += "\">　";
				MENUstr += "<Li>";				
				MENUstr += "<a href=\"";

				if(newMainItem[temp_i].loc == null)	MENUstr += "javascript://";
				else								MENUstr += newMainItem[temp_i].loc;

				MENUstr += "\" class=\"multimenu\" ";
				if(newMainItem[temp_i].loc != null)	{
					MENUstr += "target=";
					MENUstr += target;
				}

				MENUstr += " onClick=\"showorigin();\">";

				MENUstr += newMainItem[temp_i].name;

				MENUstr += "</a>";

//				MENUstr += "</td>\n";
//				MENUstr += "</tr>\n";
				MENUstr += "</Li>";				

				for(var temp_k=0 ; temp_k < iLIMIT ; temp_k++) {

//					MENUstr += "<tr>\n";
//					MENUstr += "<td bgcolor=\"";
//					MENUstr += SUB_bgcolor;
//					MENUstr += "\">　";
					MENUstr += "<Li>";				
					MENUstr += " <a href=\"";

					if(newSubItem[temp_j][temp_k].loc == null)	MENUstr += "javascript://";
					else								MENUstr += newSubItem[temp_j][temp_k].loc;

					MENUstr += "\" class=\"multimenu\" ";
					if(newSubItem[temp_j][temp_k].loc != null)	{
						MENUstr += "target=";
						MENUstr += target;
					}

					MENUstr += ">";

					MENUstr += newSubItem[temp_j][temp_k].name;


					MENUstr += "</a>";

//					MENUstr += "</td>\n";
//					MENUstr += "</tr>\n";
					MENUstr += "</Li>";				
				}
				//MENUstr += "</table>\n";
			}
		}

		MENUstr += "</span>\n";
//		MENUstr += "</UL>";				
	}
	HTMLstr = HTMLstr.replace("<!-- SUB_MENU -->\n", MENUstr);
	write2page();
}

function write2page() {
	document.write(HTMLstr);
}


function hideall(c){
	hideLayer("menu0");
	hideLayer(eval('"menu'+currmenu+'"'));
	showLayer(eval('"menu'+c+'"'));	
	currmenu=c;
}

function showorigin(){
	hideLayer(eval('"menu'+currmenu+'"'));	
	showLayer(eval('"menu0"'));	
}


var layerRef="null",layerStyleRef="null",styleSwitch="null";

if (navigator.appName == "Netscape") {
	layerStyleRef="layer.";
	layerRef="document.layers";
	styleSwitch="";
	ns=1;
}
else {
	layerStyleRef="layer.style.";
	layerRef="document.all";
	styleSwitch=".style";
	ie=1;
}

function showLayer(layerName) {
	eval(layerRef+'["'+layerName+'"]'+styleSwitch+'.visibility="visible"');
}

function hideLayer(layerName){
	eval(layerRef+'["'+layerName+'"]'+styleSwitch+'.visibility="hidden"');
}

function moveLayerto(layerName,top,left){
	eval(layerRef+'["'+layerName+'"]'+styleSwitch+'.top=top');
	eval(layerRef+'["'+layerName+'"]'+styleSwitch+'.left=left');
}




function startmove() {

	for(i=0;i<=iMAIN;i++) {

		var layer="menu"+i;
		//var linklist = "LinkList";
 		 if ((findObj("tb"))!=null)
  		{
		    if (document.layers)  //NS
		    {
		      obj = findObj("tb");
		      alert(obj.left);
		      alert(obj.top);
		    }
		    else if (document.all)//IE
		    {
		      obj = findObj("tb");
			  //obj = document.all["tb"].style;
			  
			  alert("allen");
		      alert(obj.width);
			  alert(document.body.offsetWidth);
			  alert(document.body.offsetHeight);
		    }
		 }
		var i = (obj.width);		
		var s = document.body.offsetWidth;
		var h = document.body.offsetHeight; 
		var d = (1024-i)/2;

		var j = i * 75 / 100;
		//alert(eval(layer));		
		moveLayerto(layer,newy,d+j );

	}
}


makemenu();

addMainItem("My Favorite",null);
addMainItem("My Group",null);

makeFirst();

addSubItem(0,"跟隨游標的字(單色)","http://easylearn.bhes.tpc.edu.tw/java/demo_s1_1.htm");
addSubItem(0,"跟隨游標的字(多彩)","http://easylearn.bhes.tpc.edu.tw/java/demo_s1_2.htm");
addSubItem(0,"跟隨游標的 3D 環繞字形","http://easylearn.bhes.tpc.edu.tw/java/demo_s1_4.htm");

addSubItem(1,"逐字出現式跑馬燈","http://easylearn.bhes.tpc.edu.tw/java/demo_s3_1.htm");
addSubItem(1,"逐字滑入式跑馬燈","http://easylearn.bhes.tpc.edu.tw/java/demo_s3_2.htm");
addSubItem(1,"左右來回式跑馬燈","http://easylearn.bhes.tpc.edu.tw/java/demo_s3_3.htm");

makeSecond();
</SCRIPT>



