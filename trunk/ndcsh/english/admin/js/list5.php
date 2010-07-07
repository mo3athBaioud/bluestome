<? 
		session_start(); 
		if	(isset($_GET['memid'])) {
			 $memid =  $_GET['memid']; 
		}
//		echo $memid . "aaa";	 		
//	    if(session_is_registered ('bookroll_memid')) {
//				$memid =  $_SESSION['bookroll_memid']; 
//		}		
//		$memid = "allen@cvl.com.tw";
		if (file_exists("../conn.php")) {
			include('../conn.php');
		}else{
			if (file_exists("conn.php")) {
				include('conn.php');		
			}
		}	
?>



<?php 

include("../js/tool_windows.php") ;

?>
<link href="<? echo $SHOME ?>css/siteMap.css" rel="stylesheet" type="text/css">
<link href="<? echo $SHOME ?>css/font.css" rel="stylesheet" type="text/css">
<link href="<? echo $SHOME ?>css/bookroll.css" rel="stylesheet" type="text/css">
<link href="<? echo $SHOME ?>css/sddm.css" rel="stylesheet" type="text/css">
<link href="<? echo $SHOME ?>css/subContent.css" rel="stylesheet" type="text/css">

																									 <div id=linkList>
		<DIV id=linkList2>
		
		<DIV id=lselect>

		  <DIV id=profile>

		  <SPAN id="mprofile">

				  <img class="img_me" width=50 height=50 src ='

				  <?php

		  		     $dSQL = "";
					$dSQL = "SELECT * FROM member m  WHERE m.email = '" . $memid . "'"; 

			

					$query_Com = odbc_exec($conn, $dSQL);
					$row_Com = odbc_fetch_row($query_Com);
					echo $SHOME . "home/" . $memid . "/" . odbc_result($query_Com,"pic") . "'>";
					echo "</SPAN>";
										
					echo "		&nbsp;	<SPAN id='me_nickname_normal'>" . odbc_result($query_Com,"nickname") . "'s&nbsp;bookroll</SPAN>";		
					?>


			
		</DIV>	

<div class="mainDiv" state="0">

	<div class="topItem" classOut="topItem" classOver="topItemOver" onactivate="Init(this);"> 
	 my group </div>
<div class="dropMenu">
	<div class="submenu" state="0" callprocess="<? echo $SHOME ?>mybookroll/getmygroup.php" argu="memid=<?PHP echo $memid; ?>"> 	
	</div>
	</div>
</div>

<div class="mainDiv" state="0">
	<div class="topItem" classOut="topItem" classOver="topItemOver" onactivate="Init(this);"> my favorite </div>
<div class="dropMenu">
	<div class="submenu" state="0" callprocess="<? echo $SHOME ?>mybookroll/getmyfavorite.php" argu="memid=<?PHP echo $memid; ?>"> 	

</div>
</div>
</div>



</div>
</div>
</div>
