package com.paidang.daoEx.model;

import com.paidang.dao.model.OrgWithdrawApply;

import java.math.BigDecimal;

/**
@author sun
*/
public class OrgWithdrawApplyEx extends OrgWithdrawApply {

    private String orgName;
    private String account;

    private BigDecimal balance;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}