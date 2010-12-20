/**
 * 文件com.sky.spirit.basic.hibernate.support.Page.java 创建于2008 2008-9-4 下午09:38:57
 * 版权所属： 斯凯网络 SkySpirit项目
 * 创建者: 全佳营
 * 创建时间: 2008 2008-9-4 下午09:38:57
 * Email:jonny_quan@hotmail.com
 * 备注：
 * 免费阅读参考及拷贝，拷贝时附带版权信息，谢谢合作！
 */


package com.sky.spirit.basic.hibernate.support;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * 注释
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 上午02:17:48
 * @version 1.0.0<br>
 * 更新记录备注 更新人，更新时间，更新内容，及版本号
 *
 */
public class Page implements Serializable {
	public Page()
    {
        this(0L, 0L, DEFAULT_PAGE_SIZE, new ArrayList());
    }

    public Page(long start, long totalSize, int pageSize, Object data)
    {
        this.pageSize = DEFAULT_PAGE_SIZE;
        this.pageSize = pageSize;
        this.start = start;
        totalCount = totalSize;
        this.data = data;
    }

    public long getTotalCount()
    {
        return totalCount;
    }

    public long getTotalPageCount()
    {
        if(totalCount % (long)pageSize == 0L)
            return totalCount / (long)pageSize;
        else
            return totalCount / (long)pageSize + 1L;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public Object getResult()
    {
        return data;
    }

    public long getCurrentPageNo()
    {
        return start / (long)pageSize + 1L;
    }

    public boolean hasNextPage()
    {
        return getCurrentPageNo() < getTotalPageCount() - 1L;
    }

    public boolean hasPreviousPage()
    {
        return getCurrentPageNo() > 1L;
    }

    protected static int getStartOfPage(int pageNo)
    {
        return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
    }

    public static int getStartOfPage(int pageNo, int pageSize)
    {
        return (pageNo - 1) * pageSize;
    }

    private static int DEFAULT_PAGE_SIZE = 20;
    private int pageSize;
    private long start;
    private Object data;
    private long totalCount;
}
