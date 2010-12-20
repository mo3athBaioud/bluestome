package com.sky.spirit.basic.database.page;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.RowSet;

import oracle.jdbc.rowset.OracleCachedRowSet;

import org.apache.log4j.Logger;

/**
 * <p>Title: 分页查询Oracle数据库实现</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * @author silence_wang
 * @version 1.1
 */
public class PagedStatementOracleImpl extends PagedStatement {
	
	private static Logger log = Logger.getLogger("org.common.page.PagedStatementOracleImpl");

    /**
     * 构造一查询出所有数据的PageStatement
     * @param sql  query sql
     */
    public PagedStatementOracleImpl(String sql){
        super(sql);
    }


    /**
     * 构造一查询出当页数据的PageStatement
     * @param sql  query sql
     * @param pageNo  页码
     */
    public PagedStatementOracleImpl(String sql, int pageNo){
        super(sql, pageNo);
    }

    /**
     * 构造一查询出当页数据的PageStatement，并指定每页显示记录条数
     * @param sql query sql
     * @param pageNo 页码
     * @param pageSize 每页容量
     */
    public PagedStatementOracleImpl(String sql, int pageNo, int pageSize){
        super(sql, pageNo, pageSize);
    }


    /**
     *生成查询一页数据的sql语句
     *@param sql 原查询语句
     *@startIndex 开始记录位置
     *@size 需要获取的记录数
     */
    protected String intiQuerySQL(String sql, int startIndex, int size){
        StringBuffer querySQL = new StringBuffer();
        if (size != PagedStatement.MAX_PAGE_SIZE) {
            querySQL.append("select * from (select my_table.*,rownum as my_rownum from(")
                    .append( sql )
                    .append(") my_table where rownum<").append(startIndex + size)
                    .append(") where my_rownum>=").append(startIndex);
        } else {
            querySQL.append("select * from (select my_table.*,rownum as my_rownum from(")
                    .append(sql)
                    .append(") my_table ")
                    .append(") where my_rownum>=").append(startIndex);
        }
        
        log.debug(querySQL.toString());
        
        return querySQL.toString();
    }

    /**
     *将ResultSet数据填充进CachedRowSet
     */
    protected  RowSet populate(ResultSet rs) throws SQLException{
        OracleCachedRowSet ocrs = new OracleCachedRowSet();
        ocrs.populate(rs);
        return ocrs;
    }

}

