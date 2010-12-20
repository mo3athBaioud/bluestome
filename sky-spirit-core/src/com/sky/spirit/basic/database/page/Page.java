package com.sky.spirit.basic.database.page;

import org.apache.log4j.Logger;



/**
 * Title: ��ҳ����<br>
 * Description:  ���ڰ������ݼ���ҳ��Ϣ�Ķ���<br>
 *               Page��ʵ����������ʾ��ҳ��Ϣ�Ļ�����������δָ���������ݵ����ͣ�
 *               �ɸ�����Ҫʵ�����ض���ʽ��֯���ݵ����࣬<br>
 *               ��RowSetPage��RowSet��װ���ݣ�ListPage��List��װ����<br>
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
     * Ĭ�Ϲ��췽����ֻ�����ҳ
     */
    protected Page(){
        this.init(0,0,0,DEFAULT_PAGE_SIZE,new Object());
    }

    /**
     * ��ҳ���ݳ�ʼ���������������
     * @param start ��ҳ���������ݿ��е���ʼλ��
     * @param avaCount ��ҳ��������������
     * @param totalSize ���ݿ����ܼ�¼����
     * @param pageSize ��ҳ����
     * @param data ��ҳ����������
     */
    protected void init(int start, int avaCount, int totalSize, int pageSize, Object data){

        this.avaCount =avaCount;
        this.myPageSize = pageSize;

        this.start = start;
        this.totalSize = totalSize;

        this.data=data;

        if (avaCount>totalSize) {
            //throw new RuntimeException("��¼������������������");
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
     * ȡ��ҳ������������ҳ�ܰ����ļ�¼����
     * @return ��ҳ�ܰ����ļ�¼��
     */
    public int getPageSize(){
        return this.myPageSize;
    }

    /**
     * �Ƿ�����һҳ
     * @return �Ƿ�����һҳ
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
     * �Ƿ�����һҳ
     * @return  �Ƿ�����һҳ
     */
    public boolean hasPreviousPage() {
      /*
        return start > 1;
       */
      return (this.getCurrentPageNo()>1);
    }

    /**
     * ��ȡ��ǰҳ��һ�����������ݿ��е�λ��
     * @return
     */
    public int getStart(){
        return start;
    }

    /**
     * ��ȡ��ǰҳ���һ�����������ݿ��е�λ��
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
     * ��ȡ��һҳ��һ�����������ݿ��е�λ��
     * @return ��¼��Ӧ��rownum
     */
    public int getStartOfPreviousPage() {
        return Math.max(start-myPageSize, 1);
    }


    /**
     * ��ȡ��һҳ��һ�����������ݿ��е�λ��
     * @return ��¼��Ӧ��rownum
     */
    public int getStartOfNextPage() {
        return start + avaCount;
    }

    /**
     * ��ȡ��һҳ��һ�����������ݿ��е�λ�ã�ÿҳ����ʹ��Ĭ��ֵ
     * @param pageNo ҳ��
     * @return ��¼��Ӧ��rownum
     */
    public static int getStartOfAnyPage(int pageNo){
        return getStartOfAnyPage(pageNo, DEFAULT_PAGE_SIZE);
    }

    /**
     * ��ȡ��һҳ��һ�����������ݿ��е�λ��
     * @param pageNo ҳ��
     * @param pageSize ÿҳ�����ļ�¼��
     * @return ��¼��Ӧ��rownum
     */
    public static int getStartOfAnyPage(int pageNo, int pageSize){
        int startIndex = (pageNo-1) * pageSize + 1;
        if ( startIndex < 1) startIndex = 1;
        //System.out.println("Page No to Start Index: " + pageNo + "-" + startIndex);
        return startIndex;
    }

    /**
     * ȡ��ҳ�����ļ�¼��
     * @return ��ҳ�����ļ�¼��
     */
    public int getSize() {
        return avaCount;
    }

    /**
     * ȡ���ݿ��а������ܼ�¼��
     * @return ���ݿ��а������ܼ�¼��
     */
    public int getTotalSize() {
        return this.totalSize;
    }

    /**
     * ȡ��ǰҳ��
     * @return ��ǰҳ��
     */
    public int getCurrentPageNo(){
    	  	
        return  this.currentPageno;
    }

    /**
     * ȡ��ҳ��
     * @return ��ҳ��
     */
    public int getTotalPageCount(){
        return this.totalPageCount;
    }
    
    /**
    *
    * @param queryJSFunctionName  ʵ�ַ�ҳ��ѯ��JS�ű����֣�ҳ��䶯ʱ���Զ����ø÷���
    * @return
    */
   public String getHTML(String queryJSFunctionName){
       return getHTML(queryJSFunctionName, "pageno");
   }

    /**
     *
     * @param queryJSFunctionName ʵ�ַ�ҳ��JS�ű����֣�ҳ��䶯ʱ���Զ��ص��÷���
     * @param pageNoParamName ҳ���������
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
             .append(  "          alert('��δ�����ѯ������function ")
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
        html.append(  "       ��" ).append( getTotalPageCount() ).append( "ҳ")
             .append(  "       [") .append(getStart()).append("..").append(getEnd())
             .append("/").append(this.getTotalSize()).append("]  \n")
             .append( "    </td>  \n")
             .append( "    <td align=right>  \n");
        if (hasPreviousPage()){
             html.append( "<a href='javascript:").append(gotoPage)
             .append("(") .append(getCurrentPageNo()-1) 
             .append( ")'>[��һҳ]</a>   \n");
        }
        
        composePages(html,gotoPage); 
        
        if (hasNextPage()){
            html.append( "    <a href='javascript:").append(gotoPage)
              .append("(").append((getCurrentPageNo()+1)) 
              .append( ")'>[��һҳ]</a>   \n");
       }
        
        html.append("       ת��")
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
        html.append( "    </select>ҳ  \n");
       
        html.append( "</td></tr></table>  \n");

        return html.toString();

    }
    
    /**
     * ���ҳ������� 
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
     * ��ӽ�����ҳ������ 
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
     * �����ʼ��ҳ������
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

