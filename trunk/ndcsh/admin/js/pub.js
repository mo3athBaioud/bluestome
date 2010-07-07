<script language="JavaScript">
<!--

function WM_moveTo(daObject, endLeft, endTop, numSteps, delay, endFunction) {

/*

WM_moveTo()

Moves an object from its current location to a new location and optionally fires a function when it's done.

Source: Webmonkey Code Library
(http://www.hotwired.com/webmonkey/javascript/code_library/)

Author: Nadav Savio
Author Email: nadav@wired.com

Usage: WM_moveTo('objectName', endingLeft, endingTop, numberOfSteps, delayBetweenSteps, 'functionToFire()'); 

*/

  // Declare variables.
  var leftInc, topInc, daObj = new Object;
  // The first time through, create document.WM.WM_moveTo
  if (typeof document.WM == 'undefined'){
    document.WM = new Object;
    document.WM.WM_moveTo = new Object;
  } else if (typeof document.WM.WM_moveTo == 'undefined') {
    document.WM.WM_moveTo = new Object;
  }
  // Store endFunction to execute when the move is finished.
  if(endFunction) document.WM.WM_moveTo.endFunction = endFunction;
  // Get a good object reference (call it daObj) from WM_checkIn().
  // But if we've already done so, don't check it in again.
    if (daObject == "sameObj") {
      daObj = document.WM.WM_moveTo.daObj;
    } else {
      daObj = WM_checkIn(daObject);
      document.WM.WM_moveTo.daObj = daObj;
    }
  // If this is the last step, go to the end point and run endFunction.
  if (numSteps == 1) {
    daObj.left = endLeft;
    daObj.top = endTop;
    // If an endFunction was set, execute it and then delete it.
    if(document.WM.WM_moveTo.endFunction) {
      daFunction = document.WM.WM_moveTo.endFunction;
      document.WM.WM_moveTo.endFunction = '';
      eval(daFunction);
    }
  } else {
    // Otherwise, figure out how far to move.
    leftInc = (endLeft - parseInt(daObj.left)) / numSteps;
    topInc = (endTop - parseInt(daObj.top)) / numSteps;
    // Then move, decrement numSteps, and do it all again.
    daObj.left = parseInt(daObj.left) + leftInc;
    daObj.top = parseInt(daObj.top) + topInc;
    numSteps--;
    setTimeout ('WM_moveTo(\'sameObj\', ' + endLeft + ', ' + endTop + ', ' + numSteps + ', ' + delay + ')', delay);
  }
}





// -->

</script>



<script language="JavaScript">
<!--

function WM_checkIn(WM_id) { 

/*
WM_checkIn()
Takes the ID of a positioned HTML element and returns an object reference.

Source: Webmonkey Code Library
(http://www.hotwired.com/webmonkey/javascript/code_library/)

Author: Taylor
Author Email: taylor@wired.com
Author URL: http://www.taylor.org/

Usage: WM_checkIn('id')
*/

  // First we initialize all the variables.
  var theObj,ss,sr,i,j,WM_layers=new Array();
  // This chunk handles the IE portion of the checkIn code.
  if (document.all) {
    // This checks to see if the inline style declaration has 
    // a position property associated with it. If not, it will 
    // scan the global stylesheets for the ID.
    if((document.all[WM_id].style.position != 'absolute') && (document.all[WM_id].style.position != 'relative')){
      // This little loop I'm very proud of, because it's kinda 
      // slick and I wrote it all myself. It loops through all 
      // global stylesheets and all the rules in each stylesheet, 
      // tests for the selected ID, then returns that as the object.
      for (ss=0 ; ss < document.styleSheets.length; ss++) {
        for (sr=0 ; sr < document.styleSheets(ss).rules.length; sr++) { 
          if (document.styleSheets(ss).rules(sr).selectorText == '#' + WM_id) {
            theObj = document.styleSheets(ss).rules(sr).style;
            break;
          }
        }
      }
    } else {
      // This works the same as in the light version, so you can 
      // use inline styles.
      theObj = document.all[WM_id].style;
    }
  } else if(document.layers) {
    // Now we're in Netscapeland. The main problem here 
    // is finding the object in a maze of hierarchy.
    // I wish I could say that I'm proud of this code, 
    // because it's really slick. Unfortunately, I ripped 
    // it off from Macromedia Dreamweaver's drag layer code 
    // (with permission, of course :-) 
    // Dreamweaver/Configuration/Behaviors/Actions/Drag Layer.htm 
    // It works wonderfully and solves the problem.
    WM_layers = new Array();
    with (document) {
      for (i=0; i<layers.length; i++) WM_layers[i]=layers[i]; {
        for (i=0; i<WM_layers.length; i++) {
          if (WM_layers[i].document && WM_layers[i].document.layers) {
            for (j=0; j<WM_layers[i].document.layers.length; j++) {
              WM_layers[WM_layers.length] = WM_layers[i].document.layers[j];
            }
            if(WM_layers[i].name == WM_id){
              // So if the code matches the name of the layer, 
              // return the reference. 
              theObj = WM_layers[i];
            }
          }
        }
      }
    }
  }
  return theObj;
}


// -->

</script>



<script language="JavaScript">
<!--

function WM_position2element() {
/*
WM_position2element()
Author: Taylor
Author Email: taylor@wired.com
Author URL: http://www.taylor.org/
URL: http://www.hotwired.com/webmonkey/code_library/dhtml/wm_position2element/
This action positions one DHTML element a specified number of 
pixels away from another, based on the left, top, right, or 
bottom of the bounding box of each element. To use it, you must 
choose the element to position and the element to position against;
specify which side of each element's bounding box to base the 
positioning on; and supply the number of pixels difference between 
the two. Optionally, you can choose the browser window as the 
element to position against by using the string "window".

You can only position either the left or the top of an element 
on each function call, not both at once, so multiple calls will 
be necessary if you want to offset the positioned element in two 
dimensions at once. This also only updates the element once, so 
if you want elements to follow each other in formation you'll 
need to call the function multiple times.

Usage: WM_position2element(elementPositioned, 
left|top|right|bottom, differenceInPixels, 
elementPositionedAgainst|window, left|top|right|bottom);
*/

  // First we set up our variables. posE is short for positionEE 
  // and posR is short for positionER.
  var posE,posR,mod;
  with(WM_position2element);
  posE = WM_checkIn(arguments[0]);
  // This block of code takes the string 'window' and makes it mean 
  // the browser window. This is handled in very different ways by 
  // Netscape and IE, so this block of code is rather long.
  if (arguments[3] == 'window') {
    if (document.all){
      posR = document.body;;
      posR.left = 0;
      posR.top = 0;
      // For some reason IE was adding four pixels. 
      // I subtracted it here.
      posR.width = document.body.offsetWidth - 4;
      posR.height = document.body.offsetHeight - 4;
    } else if (document.layers) {
      posR = document;
      posR.left = 0;
      posR.top = 0;
      posR.width = this.window.innerWidth;
      posR.height = this.window.innerHeight;
      // You need to set the width and height manually for Netscape. 
      // You can do this based on its clip.
      posE.width = posE.clip.width;
      posE.height = posE.clip.height;
    } 
  } else {
    // This is for positioning your element based on another element.
    // First, the standard checkIn procedure to conditionalize around 
    // the differences in the DOMs. You can replace this with any 
    // function that returns an object reference to a DHTML object.
    posR = WM_checkIn(arguments[3]);
    // Netscape doesn't know the object's width, only its 
    // clip.width, so I construct all that here.
    if (document.layers) {
      posE.width = posE.clip.width;
      posE.height = posE.clip.height;
      posR.width = posR.clip.width;
      posR.height = posR.clip.height;
    }
  }
  // This is where the faux properties are constructed. Right and 
  // bottom are equal to width and height, but I still use them, 
  // because it's easier to construct references to them based on 
  // the arguments later on.
  posE.right = parseInt(posE.width);
  posE.bottom = parseInt(posE.height);
  posR.right = parseInt(posR.left) + parseInt(posR.width);
  posR.bottom = parseInt(posR.top) + parseInt(posR.height);
  // This is where all that conditional work comes into play - the 
  // algorithm for the actual positioning. This is also where the 
  // difference between left and right or top and bottom is handled, 
  // through the setting of the mod[ifier] variable.
  if((arguments[1] == 'left') || (arguments[1] == 'right')) {
    if(arguments[1] == 'left') mod = 0;
    if(arguments[1] == 'right') mod = posE.right * -1;
    posE.left = parseInt(posR[arguments[4]]) + parseInt(arguments[2]) + mod;
  }
  if((arguments[1] == 'top') || (arguments[1] == 'bottom')) {
    if(arguments[1] == 'top') mod = 0;
    if(arguments[1] == 'bottom') mod = posE.bottom * -1;
    posE.top = parseInt(posR[arguments[4]]) + parseInt(arguments[2]) + mod;
  }
}


// -->

</script>

