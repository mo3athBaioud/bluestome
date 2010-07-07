// JavaScript Document

function main_delete(id,myurl){
if (confirm('您確定要刪除?')) {			
	location.href="delete_item.php?hotid="+id+"&url2="+myurl;
	
}
}

function jobs_delete(id,myurl){
if (confirm('您確定要刪除?')) {			
	location.href="delete_jobs.php?hotid="+id+"&url2="+myurl;
	
}
}