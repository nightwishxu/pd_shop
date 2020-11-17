package com.ruoyi.common.core.page;

import com.ruoyi.common.utils.StringUtils;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

/**
 * 分页数据
 * 
 * @author ruoyi
 */
public class PageDomain
{
    /** 当前记录起始索引 */
    @ApiModelProperty(name="pageNum",value="页数")
    private Integer pageNum;

    /** 每页显示记录数 */
    @ApiModelProperty(name="pageSize",value="每页大小")
    private Integer pageSize;

    /** 排序列 */
    @ApiParam(hidden = true)
    private String orderByColumn;

    /** 排序的方向 "desc" 或者 "asc". */
    @ApiParam(hidden = true)
    private String isAsc;

    @ApiParam(hidden = true)
    public String getOrderBy()
    {
        if (StringUtils.isEmpty(orderByColumn))
        {
            return "";
        }
        return StringUtils.toUnderScoreCase(orderByColumn) + " " + isAsc;
    }

    public Integer getPageNum()
    {
        return pageNum;
    }

    public void setPageNum(Integer pageNum)
    {
        this.pageNum = pageNum;
    }

    public Integer getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    public String getOrderByColumn()
    {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn)
    {
        this.orderByColumn = orderByColumn;
    }

    public String getIsAsc()
    {
        return isAsc;
    }

    public void setIsAsc(String isAsc)
    {
        this.isAsc = isAsc;
    }
}
