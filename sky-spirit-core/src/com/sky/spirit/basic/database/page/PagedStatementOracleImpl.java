package com.sky.spirit.basic.database.page;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.RowSet;

import oracle.jdbc.rowset.OracleCachedRowSet;

import org.apache.log4j.Logger;

/**
 * <p>Title: ��ҳ��ѯOracle���ݿ�ʵ��</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * @author silence_wang
 * @version 1.1
 */
public class PagedStatementOracleImpl extends PagedStatement {
	
	private static Logger log = Logger.getLogger("org.common.page.PagedStatementOracleImpl");

    /**
     * ����һ��ѯ���������ݵ�PageStatement
     * @param sql  query sql
     */
    public PagedStatementOracleImpl(String sql){
        super(sql);
    }


    /**
     * ����һ��ѯ����ҳ���ݵ�PageStatement
     * @param sql  query sql
     * @param pageNo  ҳ��
     */
    public PagedStatementOracleImpl(String sql, int pageNo){
        super(sql, pageNo);
    }

    /**
     * ����һ��ѯ����ҳ���ݵ�PageStatement����ָ��ÿҳ��ʾ��¼����
     * @param sql query sql
     * @param pageNo ҳ��
     * @param pageSize ÿҳ����
     */
    public PagedStatementOracleImpl(String sql, int pageNo, int pageSize){
        super(sql, pageNo, pageSize);
    }


    /**
     *���ɲ�ѯһҳ���ݵ�sql���
     *@param sql ԭ��ѯ���
     *@startIndex ��ʼ��¼λ��
     *@size ��Ҫ��ȡ�ļ�¼��
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
     *��ResultSet��������CachedRowSet
     */
    protected  RowSet populate(ResultSet rs) throws SQLException{
        OracleCachedRowSet ocrs = new OracleCachedRowSet();
        ocrs.populate(rs);
        return ocrs;
    }

}

