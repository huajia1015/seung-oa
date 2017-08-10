package com.oa.page;

import java.io.Serializable;
import java.util.List;

import com.oa.util.StringUtils;
/**
 * 
 * 分页VO
 * @author Dwen
 * @version v 0.1 2013-8-21 下午11:03:09
 */
public class Page<T> implements Serializable{
	
	/**  */
	private static final long serialVersionUID = -4542794864612494151L;
	
	protected int             current             = 1;     // 当前页码
    protected int             pageSize         = 20;    // 每页显示大小
    protected int             start            = 0;     // 分页开始位置
    protected int             end              = 20;    // 分页结果位置
    protected int             totalCount       = 0;     // 总记录数
    protected int             totalPage        = 0;     // 总页数
    protected List<T>         list;                     // 列表
    /** 是否分页*/
    protected boolean		hasPage 			=	Boolean.TRUE;

    /**
     * 获取总数
     * @return
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 分页大小
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }

    public Page<T> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public long getNextPage() {
        return Math.min(current==0?2:current + 1, totalPage);
    }

    public long getPrevPage() {
        return Math.max(current - 1, 1);
    }

    public boolean getHasNextPage() {
        return current == 0 ? current + 2 == totalPage : current + 1 == totalPage;
    }

    public boolean hasNextPage() {
        return getHasNextPage();
    }

    public boolean getHasPrevPage() {
        return current - 1 == 0 ? false : true;
    }

    public boolean hasPrevPage() {
        return getHasPrevPage();
    }

    /**
     * 获取当前页
     * 
     * @return
     */
    public int getCurrent() {
    	current = Math.max(current, 1);
    	current = Math.min(current, totalPage);
        return current;
    }
    
    public int getCurrentPage() {
        return current == 0 ? 1 : current;
    }

    public void setCurrent(String current) {
        this.current = StringUtils.parseInt(current,1);
    }

    public void setCurrentPage(int current) {
        this.current = Math.max(current, 1);
    }

    /**
     * 跳转到XX页
     * 
     * @param page
     */
    public void gotoPage(int current) {
        if (current <= 0) {
            this.current = 1;
        } else {
            this.current = Math.min(current, totalPage);
        }
    }

    /**
     * 总页数
     * 
     * @return
     */
    public int getTotalPage() {
        return totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    /**
     * 重置分页
     * 
     * @param numTotalHits
     */
    public void repaginate(int numTotalHits) {
        totalPage = numTotalHits % pageSize == 0 ? numTotalHits / pageSize : numTotalHits
                                                                             / pageSize + 1;// 总页数
        totalCount = numTotalHits;
        start = Math.min(Math.max(0, current - 1) * pageSize, Math.max((totalPage - 1), 0) * pageSize);// 需要返回结果的开始位置
        end = pageSize;
    }

    /**
     * 重置分页,设置偏移量
     * @param page
     * @param offset
     */
    public void repaginateWithoutFirstPage(int numTotalHits, int firstPageSize, int pageSize) {
        if (numTotalHits > firstPageSize) {
            int withoutFirstPage = numTotalHits - firstPageSize;
            int withoutFirstPageCount = withoutFirstPage % pageSize == 0 ? withoutFirstPage
                                                                           / pageSize
                : withoutFirstPage / pageSize + 1;
            totalPage = withoutFirstPageCount + 1;
        } else {
            totalPage = numTotalHits % pageSize == 0 ? numTotalHits / pageSize : numTotalHits
                                                                                 / pageSize + 1;// 总页数
        }
        totalCount = numTotalHits;

        if (current == 1) {
            start = 0;
            end = firstPageSize;
        } else if (totalPage <= 1) {
            start = 0;
            end = totalCount;
        } else if (totalPage > 1) {
            start = Math.max(0, firstPageSize + (current - 2) * pageSize);
            end = Math.min(start + pageSize, numTotalHits);// 结束位置
            end = end == 0 ? start + pageSize : end;
        }
    }

    /**
     * 分页开始位置
     * 
     * @return
     */
    public int getStart() {
    		return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    /**
     * 分页结束位置
     * 
     * @return
     */
    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

	public boolean isHasPage() {
		return hasPage;
	}

	public void setHasPage(boolean hasPage) {
		this.hasPage = hasPage;
	}

    
}