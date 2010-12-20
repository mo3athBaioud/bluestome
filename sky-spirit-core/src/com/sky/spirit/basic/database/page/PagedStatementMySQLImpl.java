package com.sky.spirit.basic.database.page;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.RowSet;

import org.apache.log4j.Logger;

import com.sun.rowset.CachedRowSetImpl;

public class PagedStatementMySQLImpl extends PagedStatement {
	
	private static Logger log = Logger.getLogger("org.common.page.PagedStatementMySQLImpl");

	  /**
     * ����һ��ѯ���������ݵ�PageStatement
     * @param sql  query sql
     */
    public PagedStatementMySQLImpl(String sql){
        super(sql);
    }


    /**
     * ����һ��ѯ����ҳ���ݵ�PageStatement
     * @param sql  query sql
     * @param pageNo  ҳ��
     */
    public PagedStatementMySQLImpl(String sql, int pageNo){
        super(sql, pageNo);
    }

    /**
     * ����һ��ѯ����ҳ���ݵ�PageStatement����ָ��ÿҳ��ʾ��¼����
     * @param sql query sql
     * @param pageNo ҳ��
     * @param pageSize ÿҳ����
     */
    public PagedStatementMySQLImpl(String sql, int pageNo, int pageSize){
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
        	querySQL.append("select * from (") 
            .append(  sql) 
            .append(")my_tableA limit " + (startIndex-1) +", "+ size);

        } else {
        	querySQL.append("select * from (") 
                    .append(  sql) 
                    .append(")my_tableA  "); 
        }
        
        log.debug(querySQL.toString());
        return querySQL.toString();
    }

    /**
     *��ResultSet��������CachedRowSet 
     */
    protected  RowSet populate(ResultSet rs) throws SQLException{
        CachedRowSetImpl cri = new CachedRowSetImpl();
        cri.populate(rs);
        return cri;
    }
}
