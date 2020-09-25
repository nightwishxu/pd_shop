package com.util.WxBank;

public class BankCardBin {
    private String id; //卡binID
    private String cardTy; //卡类型
    private String cardName; //卡名称
    private String binStat; //其他信息
    private String bankName; //银行名称
    private String bankId; //银行ID

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardTy() {
        return cardTy;
    }

    public void setCardTy(String cardTy) {
        this.cardTy = cardTy;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getBinStat() {
        return binStat;
    }

    public void setBinStat(String binStat) {
        this.binStat = binStat;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }


}
