<? include("../conn.php");
		  			if	(session_is_registered ("bookroll_memid"))  	{
						$memid =  $_SESSION["bookroll_memid"] ;
					}


		$memid = "allen@cvl.com.tw";
?>

<style type="text/css">

</style>

<script type="text/javascript">



/************************************************************************ 
Author: Eric Simmons
Contact: info@jswitch.com
Website: http://www.jswitch.com
Version: 1.0 4/2005       
Browser Target: Mozilla 6+/FireFox Netscape 6+, IE 5.0+
Type : XP style sliding dropdown menus (aka Switch Menu II on Dynamicdrive.com)
Note: Modification by Dynamicdrive.com to dynamically determine sub menus widths

DISCLAIMER:
THIS SOFTWARE IS PROVIDED "AS IS" AND WITHOUT
ANY EXPRESS OR IMPLIED WARRANTIES, JSWITCH.COM
IS NOT RESPONSIBLE FOR ANY ADVERSE AFFECTS TO
YOUR COMPUTER OR SYSTEMS RUNNING THIS SCRIPT.

LICENSE:
YOU ARE GRANTED THE RIGHT TO USE THE SCRIPT
PERSONALLY OR COMMERCIALLY. THE AUTHOR, WEBSITE LINKS 
AND LICENSE INFORMATION IN THE HEADER OF THIS SCRIPT
MUST NOT BE MODIFIED OR REMOVED. 

v 1.0
XP style sliding Menu Bar
***********************************************************************/

var TIMER_SLIDE = null;
var OBJ_SLIDE;
var OBJ_VIEW;
var PIX_SLIDE = 10; //this is the amount of slide/DELAY_SLIDE
var NEW_PIX_VAL;
var DELAY_SLIDE = 30; //this is the time between each call to slide
var DIV_HEIGHT = 22; //value irrelevant
var SUB_MENU_NUM =0;
var RE_INIT_OBJ = null;
var bMenu = document.getElementById("curMenu");
var MainDiv,SubDiv

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


//DD added code
document.write('																									 <div id="tempcontainer" class="mainDiv" style="visibility: hidden; position: absolute"></div>')

function Init(objDiv)
{

    if (TIMER_SLIDE == null)
    {

        SUB_MENU_NUM = 0;
        MainDiv = objDiv.parentNode;
        SubDiv =  MainDiv.getElementsByTagName("DIV").item(0);  //topItem
        SubDiv.onclick = SetSlide;
        
        OBJ_SLIDE = MainDiv.getElementsByTagName("DIV").item(1);  //dropMenu
        OBJ_VIEW = OBJ_SLIDE.getElementsByTagName("DIV").item(0);  //subMenu
				document.getElementById("tempcontainer").innerHTML=MainDiv.getElementsByTagName("DIV").item(2).innerHTML; //DD added code		

		DIV_HEIGHT=document.getElementById("tempcontainer").offsetHeight; //DD added code		
		alert(DIV_HEIGHT);
        for (i=0;i<OBJ_VIEW.childNodes.length;i++)
        {
            if (OBJ_VIEW.childNodes.item(i).tagName == "SPAN")
            {
                SUB_MENU_NUM ++;
                OBJ_VIEW.childNodes.item(i).onmouseover= ChangeStyle;
                OBJ_VIEW.childNodes.item(i).onmouseout= ChangeStyle;
            }
        }   
        
              NEW_PIX_VAL = parseInt(MainDiv.getAttribute("state")); 
    }

}
function SetSlide()
{   
	  if (window.TIMER_SLIDE) clearInterval(TIMER_SLIDE) //DD added code
      if (TIMER_SLIDE == null && this.parentNode == MainDiv){

            TIMER_SLIDE = setInterval('RunSlide()', DELAY_SLIDE);
	  }					
      else
      {
          RE_INIT_OBJ = this;
          setTimeout('ReInit()', 200);
      }
}

function ReInit(obj)
{
    Init(RE_INIT_OBJ);
    TIMER_SLIDE = setInterval('RunSlide()', DELAY_SLIDE);
    RE_INIT_OBJ = null;
}

function RunSlide()
{

    if (OBJ_VIEW.getAttribute("state") == 0)
    {

						
        NEW_PIX_VAL += PIX_SLIDE;
        OBJ_SLIDE.style.height = NEW_PIX_VAL;

        if (NEW_PIX_VAL >= DIV_HEIGHT) //DD modified code
        {
            clearInterval(TIMER_SLIDE);
            TIMER_SLIDE = null;
            OBJ_VIEW.style.display = 'inline';
            OBJ_VIEW.setAttribute("state","1")
            MainDiv.setAttribute("state",NEW_PIX_VAL);
        }
    } else
    {
        OBJ_VIEW.style.display = 'none';
        NEW_PIX_VAL -= PIX_SLIDE;
        if(NEW_PIX_VAL > 0)OBJ_SLIDE.style.height = NEW_PIX_VAL;
        if (NEW_PIX_VAL <= 0)
        {
            NEW_PIX_VAL = 0;
            OBJ_SLIDE.style.height = NEW_PIX_VAL
            clearInterval(TIMER_SLIDE);
            TIMER_SLIDE = null;
            OBJ_VIEW.setAttribute("state","0")
            MainDiv.setAttribute("state",NEW_PIX_VAL);
        }
    }
}

function ChangeStyle()
{
    if (this.className == this.getAttribute("classOut"))
        this.className = this.getAttribute("classOver");
    else
        this.className = this.getAttribute("classOut");
}
/*---------------------------------------------------------------------------*/












</script>

<DIV id=linkList>
		<DIV id=linkList2>
		<DIV id=lselect>
		  <DIV id=profile>
		  <SPAN id="mprofile"><img class="img_me" src = '<?PHP 
		  		     $dSQL = "";
					$dSQL = "SELECT * " . 
					" FROM member m  WHERE m.email = '" . $memid . "'"; 

			

					$query_Com = odbc_exec($conn, $dSQL);
					$row_Com = odbc_fetch_row($query_Com);
					echo "../home/" . $memid . "/" . odbc_result($query_Com,"pic") . "'></SPAN>";
					echo "		&nbsp;	<SPAN id='me_nickname_normal'>" . odbc_result($query_Com,"nickname") . "'s&nbsp;bookroll</SPAN>";		
					?>
					
			
			
		</DIV>	

<div class="mainDiv" state="0">

	<div class="topItem" classOut="topItem" classOver="topItemOver" onMouseOver="Init(this);"> my group </div>
<div class="dropMenu">
	<div class="submenu" state="0" callprocess="http://192.168.16.81/br/mybookroll/getMybookroll.php" argu=""> 	
		<span class='subItem' classOut='subItem' classOver='subItemOver'>
	 <a href='notice.htm'>Usage Terms</a><br>
</span>
<span class='subItem' classOut='subItem' classOver='subItemOver'>
 <a href='faqs.htm'>DHTML FAQs</a><br>
</span>		 
<span class='subItem' classOut='subItem' classOver='subItemOver'>
 <a href='help.htm'>Scripts FAQs</a>
</span>	
	</div>
	</div>
</div>

<div class="mainDiv" state="0">
	<div class="topItem" classOut="topItem" classOver="topItemOver" onMouseOver="Init(this);"> my favorite </div>
<div class="dropMenu">
	<div class="submenu" state="0" callprocess="getMybookroll.php" argu=""> 	
		<span class='subItem' classOut='subItem' classOver='subItemOver'>
	 <a href='notice.htm'>Usage Terms</a><br>
</span>
<span class='subItem' classOut='subItem' classOver='subItemOver'>
 <a href='faqs.htm'>DHTML FAQs</a><br>
</span>		 
<span class='subItem' classOut='subItem' classOver='subItemOver'>
 <a href='help.htm'>Scripts FAQs</a>
</span>
</div>
</div>
</div>



</div>
</div>
</div>
