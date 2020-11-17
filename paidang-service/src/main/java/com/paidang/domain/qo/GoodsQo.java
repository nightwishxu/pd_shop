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
}
