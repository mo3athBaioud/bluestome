<? session_start();
	require_once("conn.php");
	session_unset();
	
		echo "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" .
			"<response callloop='1' msgloop='0'>\n" .
		"  <call area='' url2='".$SHOME."' argu2='a=1'>6</call>\n" ;									
		echo "<message method='1' area='iname'>&amp;nbsp;</message>\n";											
		echo "</response>";	
?>