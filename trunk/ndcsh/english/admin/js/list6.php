<? 
header('Content-type: text/html; charset=UTF-8');
		if	(isset($_POST['memid'])) {
			 $memid =  $_POST['memid']; 
		} else if	(isset($_GET['memid'])) {
			 $memid =  $_GET['memid']; 
		}	 
		if (file_exists("../conn.php")) {
			include('../conn.php');
		}else{
			if (file_exists("conn.php")) {
				include('conn.php');		
			}
		}	
//		$memid = $_POST['memid'];
//		echo $_POST['memid'];
		
?>



<?php 

include("../js/tool_windows.php") ;

?>
<link href="css/siteMap.css" rel="stylesheet" type="text/css">
<link href="css/font.css" rel="stylesheet" type="text/css">
<link href="css/bookroll.css" rel="stylesheet" type="text/css">
<link href="css/sddm.css" rel="stylesheet" type="text/css">
<link href="css/subContent.css" rel="stylesheet" type="text/css">


																									 <div id=linkList>
		<DIV id=linkList2>
		
		<DIV id=lselect>

		  <DIV id=profile>

		  <SPAN id="mprofile">

				  <img class="img_me" width=50 height=50 src ='

				  <?php

		  		     $dSQL = "";
					$dSQL = "SELECT * FROM member m  WHERE m.email = '" . $memid . "'"; 

					$rs = $conn->Execute(trim($dSQL)); 
    				$row = $rs->fields; 		
				

					//$query_Com = odbc_exec($conn, $dSQL);
					
					//$row_Com = odbc_fetch_row($query_Com);
					//echo $SHOME . "home/" . $memid . "/" . odbc_result($query_Com,"pic") . "'>";
					echo $SHOME . "home/" . $memid . "/" . $row["pic"]. "'>";					
					echo "</SPAN>";
					echo "		&nbsp;	<SPAN id='me_nickname_normal'>" . $row["nickname"] . "'s&nbsp;bookroll</SPAN>";		
					?>


			
		</DIV>	

<div class="mainDiv" state="0">
	<div id="top_group" class="topItem" classOut="topItem" classOver="topItemOver" onmouseover="Init(this);"> 
	 my group 
	</div>
	<div class="dropMenu">
		<div class="submenu" id="igroup" state="0" callprocess="<? echo $SHOME ?>mybookroll/getmygroup.php" argu="memid=<?PHP echo $memid; ?>"> 	
		</div>
	</div>
</div>

<div class="mainDiv" state="0">
	<div id = "top_favorite" class="topItem" classOut="topItem" classOver="topItemOver" onmouseover="Init(this);"> my favorite </div>
<div class="dropMenu">
	<div class="submenu" id = "ifavorite" state="0" callprocess="<? echo $SHOME ?>mybookroll/getmyfavorite.php" argu="memid=<?PHP echo $memid; ?>"> 	

</div>
</div>
</div>

<div class="mainDiv" state="0">
	<div id="top_account" class="topItem" classOut="topItem" classOver="topItemOver" onmouseover="Init(this);"> 
	 my account 
	</div>
	<div class="dropMenu">
		<div class="submenu" id="iaccount" state="0" callprocess="<? echo $SHOME ?>mybookroll/getAccountManage.php" argu="memid=<?PHP echo $memid; ?>"> 	
		</div>
	</div>
</div>

</div>
</div>
</div>
