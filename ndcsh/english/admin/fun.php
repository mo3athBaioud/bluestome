<?

function formatdatetime($d){

	$dd = substr($d,0,4) . "-" . substr($d,4,2) . "-" . substr($d,6,2) . " " . substr($d,8,2) . ":" . substr($d,10,2) . ":" . substr($d,12,2);
	return $dd;

}

function formatdate($d){

	$dd = substr($d,0,4) . "-" . substr($d,4,2) . "-" . substr($d,6,2) ;
	return $dd;

}

function formattime($d){

	$dd = substr($d,0,2) . ":" . substr($d,2,2) . ":" . substr($d,4,2);
	return $dd;

}

?>