package com.sky.spirit.basic.database.page;

import org.apache.log4j.Logger;



/**
 * Title: 分页对象<br>
 * Description:  用于包含数据及分页信息的对象<br>
 *               Page类实现了用于显示分页信息的基本方法，但未指定所含数据的类型，
 *               可根据需要实现以特定方式组织数据的子类，<br>
 *               如RowSetPage以RowSet封装数据，ListPage以List封装数据<br>
 * Copyright:    Copyright (c) 2008 <br>
 * @author silence_wang <br>
 * @version 1.0
 */
public  class Page implements java.io.Serializable {
	
	static final long serialVersionUID = 7098821887120002L;
	
	private static Logger log = Logger.getLogger("org.common.page.Page");
	
    public static final Page EMPTY_PAGE = new Page();
    
    public static final int  DEFAULT_PAGE_SIZE = 20;
    
    public static final  int MAX_PAGE_SIZE = 9999;

    private int myPageSize = DEFAULT_PAGE_SIZE;

    private int start;
    private int avaCount,totalSize;
    private Object data;

    private int currentPageno;
    private int totalPageCount;

    /**
     * 默认构造方法，只构造空页
     */
    protected Page(){
        this.init(0,0,0,DEFAULT_PAGE_SIZE,new Object());
    }

    /**
     * 分页数据初始方法，由子类调用
     * @param start 本页数据在数据库中的起始位置
     * @param avaCount 本页包含的数据条数
     * @param totalSize 数据库中总记录条数
     * @param pageSize 本页容量
     * @param data 本页包含的数据
     */
    protected void init(int start, int avaCount, int totalSize, int pageSize, Object data){

        this.avaCount =avaCount;
        this.myPageSize = pageSize;

        this.start = start;
        this.totalSize = totalSize;

        this.data=data;

        if (avaCount>totalSize) {
            //throw new RuntimeException("记录条数大于总条数？！");
        }

        this.currentPageno = (start -1)/pageSize +1;
        this.totalPageCount = (totalSize + pageSize -1) / pageSize;

        if (totalSize==0 && avaCount==0){
            this.currentPageno = 1;
            this.totalPageCount = 1;
        }
        
        log.debug("Start Index to Page No: " + start + "-" + currentPageno);
    }

    public  Object getData(){
        return this.data;
    }

    /**
     * 取本页数据容量（本页能包含的记录数）
     * @return 本页能包含的记录数
     */
    public int getPageSize(){
        return this.myPageSize;
    }

    /**
     * 是否有下一页
     * @return 是否有下一页
     */
    public boolean hasNextPage() {
      /*
        if (avaCount==0 && totalSize==0){
            return false;
        }
        return (start + avaCount -1) < totalSize;
       */
      return (this.getCurrentPageNo()<this.getTotalPageCount());
    }

    /**
     * 是否有上一页
     * @return  是否有上一页
     */
    public boolean hasPreviousPage() {
      /*
        return start > 1;
       */
      return (this.getCurrentPageNo()>1);
    }

    /**
     * 获取当前页第一条数据在数据库中的位置
     * @return
     */
    public int getStart(){
        return start;
    }

    /**
     * 获取当前页最后一条数据在数据库中的位置
     * @return
     */
    public int getEnd(){
        int end = this.getStart() + this.getSize() -1;
        if (end<0) {
            end = 0;
        }
        return end;
    }

    /**
     * 获取上一页第一条数据在数据库中的位置
     * @return 记录对应的rownum
     */
    public int getStartOfPreviousPage() {
        return Math.max(start-myPageSize, 1);
    }


    /**
     * 获取下一页第一条数据在数据库中的位置
     * @return 记录对应的rownum
     */
    public int getStartOfNextPage() {
        return start + avaCount;
    }

    /**
     * 获取任一页第一条数据在数据库中的位置，每页条数使用默认值
     * @param pageNo 页号
     * @return 记录对应的rownum
     */
    public static int getStartOfAnyPage(int pageNo){
        return getStartOfAnyPage(pageNo, DEFAULT_PAGE_SIZE);
    }

    /**
     * 获取任一页第一条数据在数据库中的位置
     * @param pageNo 页号
     * @param pageSize 每页包含的记录数
     * @return 记录对应的rownum
     */
    public static int getStartOfAnyPage(int pageNo, int pageSize){
        int startIndex = (pageNo-1) * pageSize + 1;
        if ( startIndex < 1) startIndex = 1;
        //System.out.println("Page No to Start Index: " + pageNo + "-" + startIndex);
        return startIndex;
    }

    /**
     * 取本页包含的记录数
     * @return 本页包含的记录数
     */
    public int getSize() {
        return avaCount;
    }

    /**
     * 取数据库中包含的总记录数
     * @return 数据库中包含的总记录数
     */
    public int getTotalSize() {
        return this.totalSize;
    }

    /**
     * 取当前页码
     * @return 当前页码
     */
    public int getCurrentPageNo(){
    	  	
        return  this.currentPageno;
    }

    /**
     * 取总页码
     * @return 总页码
     */
    public int getTotalPageCount(){
        return this.totalPageCount;
    }
    
    /**
    *
    * @param queryJSFunctionName  实现分页查询的JS脚本名字，页码变动时会自动调用该方法
    * @return
    */
   public String getHTML(String queryJSFunctionName){
       return getHTML(queryJSFunctionName, "pageno");
   }

    /**
     *
     * @param queryJSFunctionName 实现分页的JS脚本名字，页码变动时会自动回调该方法
     * @param pageNoParamName 页码参数名称
     * @return
     */
    public String getHTML(String queryJSFunctionName, String pageNoParamName){
        if (getTotalPageCount()<1){
            return "<input type='hidden' name='"+pageNoParamName+"' value='1' >";
        }
        if (queryJSFunctionName == null || queryJSFunctionName.trim().length()<1) {
            queryJSFunctionName = "gotoPage";
        }
        if (pageNoParamName == null || pageNoParamName.trim().length()<1){
            pageNoParamName = "pageno";
        }

        String gotoPage = "_"+queryJSFunctionName;

        StringBuffer html = new StringBuffer("\n");
        html.append("<script language=\"Javascript1.2\">\n")
             .append("function ").append(gotoPage).append("(pageNo){  \n")
             .append(  "   var curPage=1;  \n")
             .append(  "   try{ curPage = document.all[\"")
             .append(pageNoParamName).append("\"].value;  \n")
             .append(  "        document.all[\"").append(pageNoParamName)
             .append("\"].value = pageNo;  \n")
             .append(  "        ").append(queryJSFunctionName).append("(pageNo); \n")
             .append(  "        return true;  \n")
             .append(  "   }catch(e){ \n")
             //.append(  "      try{ \n")
             //.append(  "           document.forms[0].submit();  \n")
             //.append(  "      }catch(e){   \n")
             .append(  "          alert('尚未定义查询方法：function ")
             .append(queryJSFunctionName).append("()'); \n")
             .append(  "          document.all[\"").append(pageNoParamName)
             .append("\"].value = curPage;  \n")
             .append(  "          return false;  \n")
             //.append(  "      }  \n")
             .append(  "   }  \n")
             .append(  "}")
             .append(  "</script>  \n")
             .append(  "");
        html.append( "<table  class=paginator border=0 cellspacing=0 cellpadding=0 align=center width=100%>  \n") 
             .append( "  <tr>  \n") 
             .append( "    <td align=left>  \n");
        html.append(  "       共" ).append( getTotalPageCount() ).append( "页")
             .append(  "       [") .append(getStart()).append("..").append(getEnd())
             .append("/").append(this.getTotalSize()).append("]  \n")
             .append( "    </td>  \n")
             .append( "    <td align=right>  \n");
        if (hasPreviousPage()){
             html.append( "<a href='javascript:").append(gotoPage)
             .append("(") .append(getCurrentPageNo()-1) 
             .append( ")'>[上一页]</a>   \n");
        }
        
        composePages(html,gotoPage); 
        
        if (hasNextPage()){
            html.append( "    <a href='javascript:").append(gotoPage)
              .append("(").append((getCurrentPageNo()+1)) 
              .append( ")'>[下一页]</a>   \n");
       }
        
        html.append("       转到")
            .append("       <select name='")
            .append(pageNoParamName).append("' onChange='javascript:")
            .append(gotoPage).append("(this.value)'>\n");
        String selected = "selected";
        for(int i=1;i<=getTotalPageCount();i++){
            if( i == getCurrentPageNo() )
                 selected = "selected";
            else selected = "";
            html.append( "      <option value='").append(i).append("' ")
              .append(selected).append(">").append(i).append("</option>  \n");
        }
        if (getCurrentPageNo()>getTotalPageCount()){
            html.append( "      <option value='").append(getCurrentPageNo())
            .append("' selected>").append(getCurrentPageNo())
            .append("</option>  \n");
        }
        html.append( "    </select>页  \n");
       
        html.append( "</td></tr></table>  \n");

        return html.toString();

    }
    
    /**
     * 组合页面的链接 
     * @param html      
     * @param gotoPage  
     */
    private void composePages(StringBuffer html,String gotoPage){
    	    	
    	int total = getTotalPageCount(); 
    	if( total <= 10 ){
    		for( int i=1; i<=total; i++ ){
    			if( i == getCurrentPageNo() ){
    				html.append( "<a href='javascript:").append(gotoPage)
                    .append("(") .append(i) 
                    .append( ")' ><font color='#293c9c'>"+i+"</font></a>   \n");    	            
    			}
    	        else {    			
    			html.append( "<a href='javascript:").append(gotoPage)
                .append("(") .append(i) 
                .append( ")'>["+i+"]</a>   \n");
    	        }
    		}
    		
    	}
    	else {
    		int currentPage = getCurrentPageNo();
    		if( currentPage <= 3 ){
    			for( int i=1; i<=5; i++ ){
    				if( i == getCurrentPageNo() ){
        				html.append( "<a href='javascript:").append(gotoPage)
                        .append("(") .append(i) 
                        .append( ")' ><font color='#293c9c'>"+i+"</font></a>   \n");    	            
        			}
        	        else {    			
        			html.append( "<a href='javascript:").append(gotoPage)
                    .append("(") .append(i) 
                    .append( ")'>["+i+"]</a>   \n");
        	        }
        		}
    			html.append(" .. ");
    			appendEndPages(html,gotoPage);
    			
    		}
    		
    		else if( currentPage == 4 || currentPage == 5 ){
    			for( int i= 1; i<=currentPage+2; i++ ){
    				if( i == getCurrentPageNo() ){
        				html.append( "<a href='javascript:").append(gotoPage)
                        .append("(") .append(i) 
                        .append( ")' ><font color='#293c9c'>"+i+"</font></a>   \n");    	            
        			}
        	        else {    			
        			html.append( "<a href='javascript:").append(gotoPage)
                    .append("(") .append(i) 
                    .append( ")'>["+i+"]</a>   \n");
        	        }
        		}    	
    			html.append(" .. ");
    			appendEndPages(html,gotoPage);
    			
    		}
    		
    		else if( currentPage > 5 && currentPage < total-4 ){
    			appendBeginPages(html,gotoPage);
    			html.append(" .. ");
    			
    			int begin = currentPage-2;
    			int end   = currentPage+2;
    			for( int i=begin; i <= end; i++ ){
    				if( i == getCurrentPageNo() ){
        				html.append( "<a href='javascript:").append(gotoPage)
                        .append("(") .append(i) 
                        .append( ")' ><font color='#293c9c'>"+i+"</font></a>   \n");    	            
        			}
        	        else {    			
        			html.append( "<a href='javascript:").append(gotoPage)
                    .append("(") .append(i) 
                    .append( ")'>["+i+"]</a>   \n");
        	        }
        		} 
    			html.append(" .. ");
    			appendEndPages(html,gotoPage);
    			
    		}
    		
    		else if( currentPage == total-4  || currentPage == total-3 ){
    			
    			appendBeginPages(html,gotoPage);
    			html.append(" .. ");
    			for( int i=currentPage-2; i<=total; i++ ){
    				if( i == getCurrentPageNo() ){
        				html.append( "<a href='javascript:").append(gotoPage)
                        .append("(") .append(i) 
                        .append( ")' ><font color='#293c9c'>"+i+"</font></a>   \n");    	            
        			}
        	        else {    			
        			html.append( "<a href='javascript:").append(gotoPage)
                    .append("(") .append(i) 
                    .append( ")'>["+i+"]</a>   \n");
        	        }
        		}    			
    		}
    		
    		else if( currentPage > total-3 ){
    			
    			appendBeginPages(html,gotoPage);
    			html.append(" .. ");
    			for( int i=total-4; i <= total; i++ ){
    				if( i == getCurrentPageNo() ){
        				html.append( "<a href='javascript:").append(gotoPage)
                        .append("(") .append(i) 
                        .append( ")' ><font color='#293c9c'>"+i+"</font></a>   \n");    	            
        			}
        	        else {    			
        			html.append( "<a href='javascript:").append(gotoPage)
                    .append("(") .append(i) 
                    .append( ")'>["+i+"]</a>   \n");
        	        }
        		}    			
    		}
    	}
    }
    
    /**
     * 添加结束两页的链接 
     * @param html
     * @param gotoPage
     */
    private void appendEndPages(StringBuffer html,String gotoPage){
    	int total = getTotalPageCount();
    	for( int i= total-1; i <= total; i++ ){
    	html.append( "<a href='javascript:").append(gotoPage)
        .append("(") .append(i) 
        .append( ")'>["+i+"]</a>   \n");
    	}
    }
    
    /**
     * 添加起始两页的链接
     * @param html  
     * @param gotoPage  
     */
    private void appendBeginPages(StringBuffer html,String gotoPage){
    	
    	for( int i= 1; i <= 2 ; i++ ){
    	html.append( "<a href='javascript:").append(gotoPage)
        .append("(") .append(i) 
        .append( ")'>["+i+"]</a>   \n");
    	}
    }

}

