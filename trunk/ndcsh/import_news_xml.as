// create a new XML object
var pageXML = new XML();

// create a new array to store XML node order
var pageOrder = new Array();	
var oriOrder = new Array();	
var textOrder = new Array();	

// set the ignoreWhite property to true (default value is false)
pageXML.ignoreWhite = true;

// After loading is complete, trace the XML object
pageXML.onLoad = function(success) {
	if (success) {
		var i = 0;
		var z = 0;		

		for (var thisNode = pageXML.firstChild.firstChild; thisNode != null; thisNode = thisNode.nextSibling) {
			if (thisNode.nodeName == "pic") {
//				trace("ppppppppppppppppppppp");
				pageOrder[i] = thisNode.attributes.src;
				oriOrder[i] = thisNode.attributes.ori_src;				
				textOrder[i] = thisNode.attributes.txt;				
				i++;			
			} 
		} // for
		// move playhead forward
//		play();
trace("bbbbbbbbbbbbbbbbbbbbbbb");
//	ifFrameLoaded (3) {
		gotoAndStop(3);
		
//	}
	} else {
		trace("Error loading XML");
	}
};

// load the XML into the flooring object
pageXML.load(_level0.xmlFile);
