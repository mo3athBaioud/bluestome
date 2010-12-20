package com.sky.spirit.basic.database.page;

import javax.sql.RowSet;


/**
 * <p>Title: RowSetPage</p>
 * <p>Description: ʹ��RowSet��װ���ݵķ�ҳ����</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * @author silence_wang
 * @version 1.0
 */

public class RowSetPage extends Page {
	
	static final long serialVersionUID = 7098821887120003L;
	
    private javax.sql.RowSet rs;

    /**
     *��ҳ
     */
    public static final RowSetPage EMPTY_PAGE = new RowSetPage();

    /**
     *Ĭ�Ϲ��췽����������ҳ
     */
    public RowSetPage(){
      this(null, 0,0);
    }

    /**
     *�����ҳ����
     *@param crs ����һҳ���ݵ�OracleCachedRowSet
     *@param start ��ҳ���������ݿ��е���ʼλ��
     *@param totalSize ���ݿ��а����ļ�¼����
     */
    public RowSetPage(RowSet crs, int start, int totalSize) {
        this(crs,start,totalSize,Page.DEFAULT_PAGE_SIZE);
    }

    /**
     *�����ҳ����
     *@param crs ����һҳ���ݵ�OracleCachedRowSet
     *@param start ��ҳ���������ݿ��е���ʼλ��
     *@param totalSize ���ݿ��а����ļ�¼����
     *@pageSize ��ҳ�����ɵļ�¼��
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
     *ȡ��ҳ�����еļ�¼����
     */
    public javax.sql.RowSet getRowSet(){
        return rs;
    }


}


