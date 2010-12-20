package com.sky.spirit.basic.database.page;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.sql.RowSet;

import org.apache.log4j.Logger;

import com.sky.spirit.basic.database.util.DBUtil;

/**
 * <p>Title: ��ҳ��ѯ</p>
 * <p>Description: ���ݲ�ѯ����ҳ���ѯ����ҳ����</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * @author silence_wang
 * @version 1.1
 */
public abstract class PagedStatement {
	
    public final static int MAX_PAGE_SIZE = Page.MAX_PAGE_SIZE;
    
    private static Logger log = Logger.getLogger("org.common.page.PagedStatement");

    protected String countSQL, querySQL;
    protected int pageNo,pageSize,startIndex,totalCount;
    protected javax.sql.RowSet rowSet;
    protected RowSetPage rowSetPage;

    private List<BoundParam> boundParams;

    /**
     * ����һ��ѯ���������ݵ�PageStatement
     * @param sql  query sql
     */
    public PagedStatement(String sql){
        this(sql,1,MAX_PAGE_SIZE);
    }


    /**
     * ����һ��ѯ����ҳ���ݵ�PageStatement
     * @param sql  query sql
     * @param pageNo  ҳ��
     */
    public PagedStatement(String sql, int pageNo){
        this(sql, pageNo, Page.DEFAULT_PAGE_SIZE);
    }

    /**
     * ����һ��ѯ����ҳ���ݵ�PageStatement����ָ��ÿҳ��ʾ��¼����
     * @param sql query sql
     * @param pageNo ҳ��
     * @param pageSize ÿҳ����
     */
    public PagedStatement(String sql, int pageNo, int pageSize){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.startIndex = Page.getStartOfAnyPage(pageNo, pageSize);
        this.boundParams = Collections.synchronizedList(new java.util.LinkedList<BoundParam>()); 

        this.countSQL = "select count(1) from ( " + sql +")my_tableA ";
        this.querySQL = intiQuerySQL(sql, this.startIndex, pageSize);
    }


    /**
     *���ɲ�ѯһҳ���ݵ�sql���
     *@param sql ԭ��ѯ���
     *@startIndex ��ʼ��¼λ��
     *@size ��Ҫ��ȡ�ļ�¼��
     */
    protected abstract  String intiQuerySQL(String sql, int startIndex, int size);


    /**
     *ʹ�ø����Ķ�������ָ��������ֵ
     *@param index ��һ������Ϊ1���ڶ���Ϊ2��������
     *@param obj ��������ֵ�Ķ���
     */
    public void setObject(int index, Object obj) throws SQLException{
        BoundParam bp = new BoundParam(index, obj);
        boundParams.remove(bp);
        boundParams.add(bp);
    }

    /**
     *ʹ�ø����Ķ�������ָ��������ֵ
     *@param index ��һ������Ϊ1���ڶ���Ϊ2��������
     *@param obj ��������ֵ�Ķ���
     *@param targetSqlType ���������ݿ�����
     */
    public void setObject(int index, Object obj, int targetSqlType) throws SQLException{
        BoundParam bp = new BoundParam(index, obj, targetSqlType);
        boundParams.remove(bp);
        boundParams.add(bp );
    }

    /**
     *ʹ�ø����Ķ�������ָ��������ֵ
     *@param index ��һ������Ϊ1���ڶ���Ϊ2��������
     *@param obj ��������ֵ�Ķ���
     *@param targetSqlType ���������ݿ�����(����������java.sql.Types��)
     *@param scale ���ȣ�С������λ��
     * ��ֻ��targetSqlType��Types.NUMBER��Types.DECIMAL��Ч��������������ԣ�
     */
    public void setObject(int index, Object obj, int targetSqlType, int scale) throws SQLException{
        BoundParam bp = new BoundParam(index, obj, targetSqlType, scale) ;
        boundParams.remove(bp);
        boundParams.add(bp);
    }

    /**
     *ʹ�ø������ַ�������ָ��������ֵ
     *@param index ��һ������Ϊ1���ڶ���Ϊ2��������
     *@param str ��������ֵ���ַ���
     */
    public void setString(int index, String str)throws SQLException{
        BoundParam bp = new BoundParam(index, str)  ;
        boundParams.remove(bp);
        boundParams.add(bp);
    }

    /**
     *ʹ�ø������ַ�������ָ��������ֵ
     *@param index ��һ������Ϊ1���ڶ���Ϊ2��������
     *@param timestamp ��������ֵ��ʱ���
     */
    public void setTimestamp(int index, Timestamp timestamp)throws SQLException{
        BoundParam bp = new BoundParam(index, timestamp)  ;
        boundParams.remove(bp);
        boundParams.add( bp );
    }

    /**
     *ʹ�ø�������������ָ��������ֵ
     *@param index ��һ������Ϊ1���ڶ���Ϊ2��������
     *@param value ��������ֵ������
     */
    public void setInt(int index, int value)throws SQLException{
        BoundParam bp =  new BoundParam(index, new Integer(value))  ;
        boundParams.remove(bp);
        boundParams.add( bp );
    }

    /**
     *ʹ�ø����ĳ���������ָ��������ֵ
     *@param index ��һ������Ϊ1���ڶ���Ϊ2��������
     *@param value ��������ֵ�ĳ�����
     */
    public void setLong(int index, long value)throws SQLException{
        BoundParam bp =  new BoundParam(index, new Long(value))  ;
        boundParams.remove(bp);
        boundParams.add( bp );
    }

    /**
     *ʹ�ø�����˫���ȸ���������ָ��������ֵ
     *@param index ��һ������Ϊ1���ڶ���Ϊ2��������
     *@param value ��������ֵ��˫���ȸ�����
     */
    public void setDouble(int index, double value)throws SQLException{
        BoundParam bp =  new BoundParam(index, new Double(value))   ;
        boundParams.remove(bp);
        boundParams.add( bp);
    }

    /**
     *ʹ�ø�����BigDecimal����ָ��������ֵ
     *@param index ��һ������Ϊ1���ڶ���Ϊ2��������
     *@param bd ��������ֵ��BigDecimal
     */
    public void setBigDecimal(int index, BigDecimal bd)throws SQLException{
        BoundParam bp =   new BoundParam(index, bd )   ;
        boundParams.remove(bp);
        boundParams.add( bp);
    }

    private  void setParams(PreparedStatement pst) throws SQLException{
        if (pst==null || this.boundParams==null || this.boundParams.size()==0 ) return ;
        BoundParam param;
        for (Iterator itr = this.boundParams.iterator();itr.hasNext();){
            param = (BoundParam) itr.next();
            if  (param==null) continue;
            if (param.sqlType == java.sql.Types.OTHER){
                pst.setObject(param.index, param.value);
            }else{
                pst.setObject(param.index, param.value, param.sqlType, param.scale);
            }
        }
    }



    /**
     * ִ�в�ѯȡ��һҳ���ݣ�ִ�н�����ر����ݿ�����
     * @return RowSetPage
     * @throws SQLException
     */
    public  RowSetPage executeQuery() throws SQLException{
    	log.debug("executeQueryUsingPreparedStatement");
    	
        Connection conn = DBUtil.getConnection();        
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            pst = conn.prepareStatement(this.countSQL);
            setParams(pst);
            rs =pst.executeQuery();
            if (rs.next()){
                totalCount = rs.getInt(1);
            } else {
                totalCount = 0;
            }

            rs.close();
            pst.close();

            if (totalCount < 1 ) return RowSetPage.EMPTY_PAGE;

            pst = conn.prepareStatement(this.querySQL);
            log.debug(querySQL);
            pst.setFetchSize(this.pageSize);
            setParams(pst);
            rs =pst.executeQuery();
            //rs.setFetchSize(pageSize);

            this.rowSet = populate(rs);

            rs.close();
            rs = null;
            pst.close();
            pst = null;

            this.rowSetPage = new RowSetPage(this.rowSet,startIndex,totalCount,pageSize);
            
            return this.rowSetPage;
            
        }catch(SQLException sqle){            
            sqle.printStackTrace();
            log.error(sqle);
            throw sqle;
        }finally{            
            DBUtil.close(rs, pst, conn);   
        }
    }

    /**
     *��ResultSet��������CachedRowSet
     */
    protected abstract RowSet populate(ResultSet rs) throws SQLException;

    /**
     *ȡ��װ��RowSet��ѯ���
     *@return RowSet
     */
    public javax.sql.RowSet getRowSet(){
        return this.rowSet;
    }


    /**
     *ȡ��װ��RowSetPage�Ĳ�ѯ���
     *@return RowSetPage
     */
    public RowSetPage getRowSetPage() {
        return this.rowSetPage;
    }



    /**
     *�ر����ݿ�����
     */
    public void close(){
        //��Ϊ���ݿ������ڲ�ѯ���������쳣ʱ���رգ��˴������κ�����
        //�������䡣
    }



    private class BoundParam {
        int index;
        Object value;
        int sqlType;
        int scale;

        public BoundParam(int index, Object value) {
            this(index, value, java.sql.Types.OTHER);
        }

        public BoundParam(int index, Object value, int sqlType) {
            this(index, value, sqlType, 0);
        }

        public BoundParam(int index, Object value, int sqlType, int scale) {
            this.index = index;
            this.value = value;
            this.sqlType = sqlType;
            this.scale = scale;
        }

        public boolean equals(Object obj){
            if (obj!=null && this.getClass().isInstance(obj)){
                BoundParam bp = (BoundParam)obj;
                if (this.index==bp.index) return true;
            }
            return false;
        }
    }

}

