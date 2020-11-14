package com.paidang.domain.qo;

import com.paidang.dao.model.Order;

import java.util.List;

/**
 * @author: xww
 * @create: 2020-11-08 21:08
 * @description:
 * @modified By:
 * @version:
 **/
public class OrderQo extends Order {

    private List<Integer> states;

    private List<Integer> refStates;



    public List<Integer> getStates() {
        return states;
    }

    public void setStates(List<Integer> states) {
        this.states = states;
    }

    public List<Integer> getRefStates() {
        return refStates;
    }

    public void setRefStates(List<Integer> refStates) {
        this.refStates = refStates;
    }
}
