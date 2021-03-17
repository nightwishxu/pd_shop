package com.paidang.domain.qo;

import com.paidang.dao.model.Goods;

import java.util.List;

/**
 * @author: xww
 * @create: 2020-11-16 20:42
 * @description:
 * @modified By:
 * @version:
 **/
public class GoodsQo extends Goods {

    private List<Integer> sources;

    private Integer startTotal;

    private Integer isOnlineCnt;

    private Integer limitNum;

    private Integer orderType;

    private Integer orgState;

    private Integer pageSize;

    private Integer pageNum;

    public List<Integer> getSources() {
        return sources;
    }

    public void setSources(List<Integer> sources) {
        this.sources = sources;
    }

    public Integer getStartTotal() {
        return startTotal;
    }

    public void setStartTotal(Integer startTotal) {
        this.startTotal = startTotal;
    }


    public Integer getIsOnlineCnt() {
        return isOnlineCnt;
    }

    public void setIsOnlineCnt(Integer isOnlineCnt) {
        this.isOnlineCnt = isOnlineCnt;
    }

    public Integer getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getOrgState() {
        return orgState;
    }

    public void setOrgState(Integer orgState) {
        this.orgState = orgState;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
