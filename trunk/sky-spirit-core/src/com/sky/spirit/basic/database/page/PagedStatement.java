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
 * <p>Title: 分页查询</p>
 * <p>Description: 根据查询语句和页码查询出当页数据</p>
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
     * 构造一查询出所有数据的PageStatement
     * @param sql  query sql
     */
    public PagedStatement(String sql){
        this(sql,1,MAX_PAGE_SIZE);
    }


    /**
     * 构造一查询出当页数据的PageStatement
     * @param sql  query sql
     * @param pageNo  页码
     */
    public PagedStatement(String sql, int pageNo){
        this(sql, pageNo, Page.DEFAULT_PAGE_SIZE);
    }

    /**
     * 构造一查询出当页数据的PageStatement，并指定每页显示记录条数
     * @param sql query sql
     * @param pageNo 页码
     * @param pageSize 每页容量
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
     *生成查询一页数据的sql语句
     *@param sql 原查询语句
     *@startIndex 开始记录位置
     *@size 需要获取的记录数
     */
    protected abstract  String intiQuerySQL(String sql, int startIndex, int size);


    /**
     *使用给出的对象设置指定参数的值
     *@param index 第一个参数为1，第二个为2，。。。
     *@param obj 包含参数值的对象
     */
    public void setObject(int index, Object obj) throws SQLException{
        BoundParam bp = new BoundParam(index, obj);
        boundParams.remove(bp);
        boundParams.add(bp);
    }

    /**
     *使用给出的对象设置指定参数的值
     *@param index 第一个参数为1，第二个为2，。。。
     *@param obj 包含参数值的对象
     *@param targetSqlType 参数的数据库类型
     */
    public void setObject(int index, Object obj, int targetSqlType) throws SQLException{
        BoundParam bp = new BoundParam(index, obj, targetSqlType);
        boundParams.remove(bp);
        boundParams.add(bp );
    }

    /**
     *使用给出的对象设置指定参数的值
     *@param index 第一个参数为1，第二个为2，。。。
     *@param obj 包含参数值的对象
     *@param targetSqlType 参数的数据库类型(常量定义在java.sql.Types中)
     *@param scale 精度，小数点后的位数
     * （只对targetSqlType是Types.NUMBER或Types.DECIMAL有效，其它类型则忽略）
     */
    public void setObject(int index, Object obj, int targetSqlType, int scale) throws SQLException{
        BoundParam bp = new BoundParam(index, obj, targetSqlType, scale) ;
        boundParams.remove(bp);
        boundParams.add(bp);
    }

    /**
     *使用给出的字符串设置指定参数的值
     *@param index 第一个参数为1，第二个为2，。。。
     *@param str 包含参数值的字符串
     */
    public void setString(int index, String str)throws SQLException{
        BoundParam bp = new BoundParam(index, str)  ;
        boundParams.remove(bp);
        boundParams.add(bp);
    }

    /**
     *使用给出的字符串设置指定参数的值
     *@param index 第一个参数为1，第二个为2，。。。
     *@param timestamp 包含参数值的时间戳
     */
    public void setTimestamp(int index, Timestamp timestamp)throws SQLException{
        BoundParam bp = new BoundParam(index, timestamp)  ;
        boundParams.remove(bp);
        boundParams.add( bp );
    }

    /**
     *使用给出的整数设置指定参数的值
     *@param index 第一个参数为1，第二个为2，。。。
     *@param value 包含参数值的整数
     */
    public void setInt(int index, int value)throws SQLException{
        BoundParam bp =  new BoundParam(index, new Integer(value))  ;
        boundParams.remove(bp);
        boundParams.add( bp );
    }

    /**
     *使用给出的长整数设置指定参数的值
     *@param index 第一个参数为1，第二个为2，。。。
     *@param value 包含参数值的长整数
     */
    public void setLong(int index, long value)throws SQLException{
        BoundParam bp =  new BoundParam(index, new Long(value))  ;
        boundParams.remove(bp);
        boundParams.add( bp );
    }

    /**
     *使用给出的双精度浮点数设置指定参数的值
     *@param index 第一个参数为1，第二个为2，。。。
     *@param value 包含参数值的双精度浮点数
     */
    public void setDouble(int index, double value)throws SQLException{
        BoundParam bp =  new BoundParam(index, new Double(value))   ;
        boundParams.remove(bp);
        boundParams.add( bp);
    }

    /**
     *使用给出的BigDecimal设置指定参数的值
     *@param index 第一个参数为1，第二个为2，。。。
     *@param bd 包含参数值的BigDecimal
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
     * 执行查询取得一页数据，执行结束后关闭数据库连接
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
     *将ResultSet数据填充进CachedRowSet
     */
    protected abstract RowSet populate(ResultSet rs) throws SQLException;

    /**
     *取封装成RowSet查询结果
     *@return RowSet
     */
    public javax.sql.RowSet getRowSet(){
        return this.rowSet;
    }


    /**
     *取封装成RowSetPage的查询结果
     *@return RowSetPage
     */
    public RowSetPage getRowSetPage() {
        return this.rowSetPage;
    }



    /**
     *关闭数据库连接
     */
    public void close(){
        //因为数据库连接在查询结束或发生异常时即关闭，此处不做任何事情
        //留待扩充。
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

