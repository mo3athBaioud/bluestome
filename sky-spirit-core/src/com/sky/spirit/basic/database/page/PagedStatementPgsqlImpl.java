package com.sky.spirit.basic.database.page;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.RowSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sun.rowset.CachedRowSetImpl;

public class PagedStatementPgsqlImpl extends PagedStatement {

	private static Log log = LogFactory.getLog(PagedStatementPgsqlImpl.class.getName());
	  /**
     * ����һ��ѯ���������ݵ�PageStatement
     * @param sql  query sql
     */
    public PagedStatementPgsqlImpl(String sql){
        super(sql);
    }


    /**
     * ����һ��ѯ����ҳ���ݵ�PageStatement
     * @param sql  query sql
     * @param pageNo  ҳ��
     */
    public PagedStatementPgsqlImpl(String sql, int pageNo){
        super(sql, pageNo);
    }

    /**
     * ����һ��ѯ����ҳ���ݵ�PageStatement����ָ��ÿҳ��ʾ��¼����
     * @param sql query sql
     * @param pageNo ҳ��
     * @param pageSize ÿҳ����
     */
    public PagedStatementPgsqlImpl(String sql, int pageNo, int pageSize){
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
        	querySQL.append("select top " + size +" * from " +
        			"("+sql+")my_tableA " +
        			"where id not in (select top " + 
        			(startIndex - 1) + 
        			" id from ("+sql+")my_tableB order by id desc) order by id desc");

        } else {
        	querySQL.append("select * from " +
        			"("+sql+")my_tableA " +
        			"where id not in (select top " + 
        			(startIndex -1) + 
        			" id from ("+sql+")my_tableB order by id desc) order by id desc");  
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
