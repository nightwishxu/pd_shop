package com.paidang.domain.enums;

import java.math.BigDecimal;

/**
 * @author: xww
 * @create: 2020-11-17 00:04
 * @description:
 * @modified By:
 * @version:
 **/
public enum  OrgIntegralEnum {

    /**
     * 订单价格在
     * 10-250          2分
     * 251-500        4分
     * 501-2000       6分
     * 2001-16000       8分
     * 16001            10分
     *  
     *  
     *  
     * 等级：
     * 0-20        V1
     * 21-40        V2
     * 41-80        V3
     * 81-160        V4
     * 161-500       V5
     * 501-900       V6    
     */

    L1(new BigDecimal(10),new BigDecimal(251),new BigDecimal(2),1,""),
    L2(new BigDecimal(251),new BigDecimal(501),new BigDecimal(4),1,""),
    L3(new BigDecimal(501),new BigDecimal(2000),new BigDecimal(6),1,""),
    L4(new BigDecimal(2001),new BigDecimal(16000),new BigDecimal(8),1,""),
    L5(new BigDecimal(16001),new BigDecimal(99999999),new BigDecimal(10),1,""),

    V1(new BigDecimal(0),new BigDecimal(21),null,2,"V1"),
    V2(new BigDecimal(21),new BigDecimal(41),null,2,"V2"),
    V3(new BigDecimal(41),new BigDecimal(80),null,2,"V3"),
    V4(new BigDecimal(81),new BigDecimal(161),null,2,"V4"),
    V5(new BigDecimal(161),new BigDecimal(501),null,2,"V5"),
    V6(new BigDecimal(501),new BigDecimal(901),null,2,"V6"),
    ;


    public static OrgIntegralEnum getIntegral(BigDecimal price) {
        if (price == null) {
            return null;
        }

        OrgIntegralEnum result = null;
        for (OrgIntegralEnum tmp : OrgIntegralEnum.values()) {
            if (tmp.type==1 && price.compareTo(tmp.getStart())>= 0 && price.compareTo(tmp.getEnd())<0) {
                result = tmp;
                break;
            }
        }
        return result;
    }


    public static OrgIntegralEnum getLevel(BigDecimal integral) {
        if (integral == null) {
            return null;
        }

        OrgIntegralEnum result = null;
        for (OrgIntegralEnum tmp : OrgIntegralEnum.values()) {
            if (tmp.type==2 && integral.compareTo(tmp.getStart())>= 0 && integral.compareTo(tmp.getEnd())<0) {
                result = tmp;
                break;
            }
        }
        return result;
    }

    OrgIntegralEnum(BigDecimal start, BigDecimal end, BigDecimal integral, Integer type, String desc) {
        this.start = start;
        this.end = end;
        this.integral = integral;
        this.type = type;
        this.desc = desc;
    }

    private BigDecimal start;

    private BigDecimal end;

    private BigDecimal integral;

    private Integer type;

    private String desc;


    public BigDecimal getStart() {
        return start;
    }

    public BigDecimal getEnd() {
        return end;
    }

    public BigDecimal getIntegral() {
        return integral;
    }

    public String getDesc() {
        return desc;
    }

    public Integer getType() {
        return type;
    }
}
