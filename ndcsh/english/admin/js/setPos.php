<SCRIPT LANGUAGE="JAVASCRIPT1.2" >

var target="_blank";  // 開啟指定網頁所使用的 Frame
var MAIN_bgcolor = "#64D0FE";   // 主目錄背景色彩
var SUB_bgcolor = "#CCEEFF";   // 子目錄背景色彩
var width = "200";   // 目錄寬度
var newx=25,newy=100;	// 目錄左上端起始位置


var layerName,l,currmenu=0;
var iMAIN = 0;
var newMainItem = new initArray();
var newSubItem = new initArray();
/*
newSubItem[0] = new initArray();
newSubItem[0][0] = "Jack";
*/
var HTMLstr="";

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
	HTMLstr += "	<DIV id=lselect>";
	HTMLstr += "		<H3 class=select><SPAN>Select a Design:</SPAN></H3>	";
	
	HTMLstr += "	<UL>";
	HTMLstr += "	  <img class=\"img_me\" src = '";
	<?PHP 
		  		     $dSQL = "";
					$dSQL = "SELECT * " . 
					" FROM member m  WHERE m.email = '" . $memid . "'"; 

			

					$query_Com = odbc_exec($conn, $dSQL);
					$row_Com = odbc_fetch_row($query_Com);
					echo "../home/" . $memid . "/" . odbc_result($query_Com,"pic") . "'>";
					echo "		&nbsp;	<SPAN class='me_nickname_normal'>" . odbc_result($query_Com,"nickname") . "</SPAN>";		
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

		<UL>
		  <img class="img_me" src = '<?PHP 
		  		     $dSQL = "";
					$dSQL = "SELECT * " . 
					" FROM member m  WHERE m.email = '" . $memid . "'"; 

			

					$query_Com = odbc_exec($conn, $dSQL);
					$row_Com = odbc_fetch_row($query_Com);
					echo "../home/" . $memid . "/" . odbc_result($query_Com,"pic") . "'>";
					echo "		&nbsp;	<SPAN class='me_nickname_normal'>" . odbc_result($query_Com,"nickname") . "</SPAN>";		
					?>


		  <LI><A title="My Favorite" accessKey=e 
		  href="myfavorite.php">My Favorite
		  </A></LI> 
		  <LI><A title="My Group" accessKey=f 
		  href="javascript: opengroup('<?PHP 

				echo $memid;
		  ?>
		  ')">My Group 
		  </A> 
		  </LI>
		</UL></DIV></DIV></DIV>

function makeFirst() {
	MENUstr = "";
	MENUstr += "<div id=\"menu0\">\n";
	for(var i=0;i<iMAIN;i++) {
		MENUstr += "																									 <table border=0 width=";
		MENUstr += width;
		MENUstr += " cellspacing=2>\n";
		MENUstr += "<tr bgcolor=\"";
		MENUstr += MAIN_bgcolor;
		MENUstr += "\">\n";
		MENUstr += "<td>　\n";

		temp_use = i+1;

		if(newMainItem[i].loc == null) {
			MENUstr += "<a href=\"javascript://\" class=\"multimenu\" onClick=\"hideall(";
			MENUstr += temp_use
			MENUstr += ")\">";
		}
		else {
			MENUstr += "<a href=\"";
			MENUstr += newMainItem[i].loc;
			MENUstr += "\" class=\"multimenu\" target=";
			MENUstr += target;
			MENUstr += ">";
		}
		MENUstr += newMainItem[i].name;
		MENUstr += "</a>";

		MENUstr += "</td>\n";
		MENUstr += "</tr>\n";
		MENUstr += "</table>\n\n";
	}
	MENUstr += "</div>";

	HTMLstr = HTMLstr.replace("<!-- MAIN_MENU -->\n", MENUstr);
	makeSubArray();	//建立雙層的陣列
}

function makeSecond() {
	MENUstr = "";
	for(var temp_j=0 ; temp_j < iMAIN ; temp_j++) {
		DIVnum = temp_j+1;
		MENUstr += "<div id=\"menu";
		MENUstr += DIVnum;
		MENUstr += "\">\n";

		for(var temp_i=0 ; temp_i < iMAIN ; temp_i++) {
			if(temp_j != temp_i) {
				MENUstr += "<table border=0 width=";
				MENUstr += width;
				MENUstr += " cellspacing=2>\n";
				MENUstr += "<tr>\n";
				MENUstr += "<td bgcolor=\"";
				MENUstr += MAIN_bgcolor;
				MENUstr += "\">　";

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
				MENUstr += "</td>";
				MENUstr += "</tr>";
				MENUstr += "</table>";
			}
			else {
				iLIMIT = newMainItem[temp_j].iCount;

				MENUstr += "<table border=0 width=";
				MENUstr += width;
				MENUstr += " cellspacing=2>\n";

				MENUstr += "<tr>\n";

				MENUstr += "<td bgcolor=\"";
				MENUstr += MAIN_bgcolor;
				MENUstr += "\">　";

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

				MENUstr += "</td>\n";
				MENUstr += "</tr>\n";

				for(var temp_k=0 ; temp_k < iLIMIT ; temp_k++) {

					MENUstr += "<tr>\n";
					MENUstr += "<td bgcolor=\"";
					MENUstr += SUB_bgcolor;
					MENUstr += "\">　";

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

					MENUstr += "</td>\n";
					MENUstr += "</tr>\n";
				}
				MENUstr += "</table>\n";
			}
		}

		MENUstr += "</div>\n";
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
		moveLayerto(layer,newy,newx)

	}
}
</SCRIPT>
