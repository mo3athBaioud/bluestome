package com.sky.spirit.basic.database.page;

import javax.sql.RowSet;


/**
 * <p>Title: RowSetPage</p>
 * <p>Description: 使用RowSet封装数据的分页对象</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * @author silence_wang
 * @version 1.0
 */

public class RowSetPage extends Page {
	
	static final long serialVersionUID = 7098821887120003L;
	
    private javax.sql.RowSet rs;

    /**
     *空页
     */
    public static final RowSetPage EMPTY_PAGE = new RowSetPage();

    /**
     *默认构造方法，创建空页
     */
    public RowSetPage(){
      this(null, 0,0);
    }

    /**
     *构造分页对象
     *@param crs 包含一页数据的OracleCachedRowSet
     *@param start 该页数据在数据库中的起始位置
     *@param totalSize 数据库中包含的记录总数
     */
    public RowSetPage(RowSet crs, int start, int totalSize) {
        this(crs,start,totalSize,Page.DEFAULT_PAGE_SIZE);
    }

    /**
     *构造分页对象
     *@param crs 包含一页数据的OracleCachedRowSet
     *@param start 该页数据在数据库中的起始位置
     *@param totalSize 数据库中包含的记录总数
     *@pageSize 本页能容纳的记录数
     */
    public RowSetPage(RowSet crs, int start, int totalSize, int pageSize) {
        try{
            int avaCount=0;
            if (crs!=null) {
                crs.beforeFirst();
                if (crs.next()){
                    crs.last();
                    avaCount = crs.getRow();
                }
                crs.beforeFirst();
            }
            rs = crs;
            super.init(start,avaCount,totalSize,pageSize,rs);
        }catch(java.sql.SQLException sqle){
            throw new RuntimeException(sqle.toString());
        }
    }

    /**
     *取分页对象中的记录数据
     */
    public javax.sql.RowSet getRowSet(){
        return rs;
    }


}


