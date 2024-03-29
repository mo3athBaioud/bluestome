/**
 *
 * Magnolia and its source-code is licensed under the LGPL.
 * You may copy, adapt, and redistribute this file for commercial or non-commercial use.
 * When copying, adapting, or redistributing this document in keeping with the guidelines above,
 * you are required to provide proper attribution to obinary.
 * If you reproduce or distribute the document without making any substantive modifications to its content,
 * please use the following attribution line:
 *
 * Copyright 1993-2006 obinary Ltd. (http://www.obinary.com) All rights reserved.
 *
 */

classDef("mgnl.controls.List", function(name, form){
    this.name = name;
    this.form = form;

    this.selected = -1;
    
    this.mainDiv = $(name + "Div");
    this.sortGroupDiv = $(name + "SortGroupDiv");
    this.contentDiv = $(name + "ContentDiv");
    this.innerContentDiv = $(name + "InnerContentDiv");
    this.resizerLine = $(name + "ColumnResizerLine"); 
    
    // css classes used
    this.css = {
        row:{
            selected: 'mgnlListRowSelected',
            hover: 'mgnlListRowHover',
             normal: 'mgnlListRow'
        }
    };
    
    /**
     * Array of list columns
     */
    this.columns = new Array();
    
    this.addColumn = function(column){
        this.columns.push(column);
    }
    
    this.select = function(id){
        this.unselect();
        this.selected = id;
        var row = $(this.name + 'Row' + this.selected);
        row.className = this.css.row.selected;
        
        // refresh function bar if any
        if(window["mgnlFunctionBar"]){
            window["mgnlFunctionBar"].refresh();
        }
    }
    
    this.unselect = function(){
        if(this.selected == -1)
            return;
        var row = $(this.name + 'Row' + this.selected);
        row.className= this.css.row.normal;
        this.selected = -1;
    }
    
    this.startResizeColumn =  function(index){
        this.resizerLine.style.visibility="visible";
        this.resizerLine.style.left = this.columns[index].left + 5;
        this.resizerLine.style.height = this.height;
        var list = this;
        
        this.contentDiv.onmousemove = function(event){
                list.onResizeColumn(event, index);
        };
        
        this.contentDiv.onmouseup = function(event){
                list.stopResizeColumn(event, index);
        };
        
        mgnl.util.Debug.debug("start column resizing");
    }

    this.onResizeColumn =  function(event, index){
        var pos = mgnl.util.DHTMLUtil.getMousePos(event);
        this.resizerLine.style.left = pos.x-1;
    }

    this.stopResizeColumn =  function(event, index){
        var newLeft = mgnl.util.DHTMLUtil.getMousePos(event).x -6;
        var column = this.columns[index];
        
        column.resize(newLeft, column.width + (column.left - newLeft));
        // resize also column to the left
        if(index >= 1){
            column = this.columns[index-1];
            column.resize(column.left, newLeft -column.left);
        }
        
        this.resizerLine.style.visibility="hidden";
        this.contentDiv.onmousemove = null;
        this.contentDiv.onmouseup = null;
        
        mgnl.util.Debug.debug("stop column resizing");
    }
    
    this.mouseover = function(id){
        if(id != this.selected){
            var row = $(this.name + 'Row' + id);
            row.className= this.css.row.hover;
        }
    }

    this.mouseout = function(id){
        if(id != this.selected){
            var row = $(this.name + 'Row' + id);
            row.className= this.css.row.normal;
        }
    }
    
    this.resize = function(){
        this.height = MgnlDHTMLUtil.getHeight(this.mainDiv);
        this.width = MgnlDHTMLUtil.getWidth(this.mainDiv);
        
        if(this.sortGroupDiv){
            var sortWidth = MgnlDHTMLUtil.getWidth(this.sortGroupDiv);
            this.sortGroupDiv.style.left = this.width - sortWidth + "px";        
            this.sortGroupDiv.style.height = this.height -1 + "px";
            this.sortGroupDiv.style.visibility = "visible";
            this.width -= sortWidth
        }
        
        MgnlDebug.debug("new total widht:" + this.width, this);
        

        this.contentDiv.style.width = this.width;
        this.contentDiv.style.height = this.height;
        this.innerContentDiv.style.height = this.height -20;
        
        // columns
        var factor = this.getWidthFactor(this.width);
        MgnlDebug.debug("factor is:" +factor, this);
        left=0;
        for(i=0; i< this.columns.length;i++){
            var column = this.columns[i];
            var newColumnWidth;
            if(column.fixed){
                newColumnWidth  = column.width;            
            }
            else{
                newColumnWidth  = factor * column.width;
            }
            column.resize(left, newColumnWidth)
            left += newColumnWidth; // next start
        }
    }
    
    this.getWidthFactor = function(){
        var sum = 0;
        for(i=0; i<this.columns.length; i++){
            sum += this.columns[i].width;
        }
        MgnlDebug.debug("sum of columns widht:" + sum, this);
        return this.width / sum;
    }
    
    this.sort = function(name, direction){
        this.form.sortBy.value = name;
        this.form.sortByOrder.value = direction;
        this.form.submit();
    }

    this.group = function(name, direction){
        this.form.groupBy.value = name;
        this.form.groupByOrder.value = direction;
        this.form.submit();
    }
});