package com.paidang.domain.enums;

import java.math.BigDecimal;

/**
 * @author: xww
 * @create: 2020-11-17 00:04
 * @description:
 * @modified By:
 * @version:
 **/
public enum UserIntegralEnum {

    /**

     *  
     *  
     * 等级：
     * o        V0
     * 1-50       V1
     * 21-40        V2
     * 41-80        V3
     * 81-160        V4
     * 161-500       V5
     * 501-900       V6    
     */


    V0(new BigDecimal(0),new BigDecimal(0),null,2,"V0"),
    V1(new BigDecimal(0),new BigDecimal(50),null,2,"V1"),
    V2(new BigDecimal(51),new BigDecimal(100),null,2,"V2"),
    V3(new BigDecimal(101),new BigDecimal(200),null,2,"V3"),
    V4(new BigDecimal(201),new BigDecimal(400),null,2,"V4"),
    V5(new BigDecimal(401),new BigDecimal(1600),null,2,"V5"),
    V6(new BigDecimal(1601),new BigDecimal(6400),null,2,"V6"),
    V7(new BigDecimal(6401),new BigDecimal(25600),null,2,"V7"),
    V8(new BigDecimal(25601),new BigDecimal(102400),null,2,"V8"),
    V9(new BigDecimal(102401),new BigDecimal(512000),null,2,"V9"),
    V10(new BigDecimal(512001),new BigDecimal(1099000),null,2,"V10"),
    V11(new BigDecimal(1099001),new BigDecimal(1824000),null,2,"V11"),
    V12(new BigDecimal(1824001),new BigDecimal(294000),null,2,"V12"),
    V13(new BigDecimal(294001),new BigDecimal(999999999),null,2,"V13"),
    ;


//    public static UserIntegralEnum getIntegral(BigDecimal price) {
//        if (price == null) {
//            return null;
//        }
//
//        UserIntegralEnum result = null;
//        for (UserIntegralEnum tmp : UserIntegralEnum.values()) {
//            if (tmp.type==1 && price.compareTo(tmp.getStart())>= 0 && price.compareTo(tmp.getEnd())<0) {
//                result = tmp;
//                break;
//            }
//        }
//        return result;
//    }


    public static UserIntegralEnum getLevel(BigDecimal integral) {
        if (integral == null) {
            return null;
        }

        UserIntegralEnum result = null;
        for (UserIntegralEnum tmp : UserIntegralEnum.values()) {
            if (tmp.type==2 && integral.compareTo(tmp.getStart())>= 0 && integral.compareTo(tmp.getEnd())<0) {
                result = tmp;
                break;
            }
        }
        return result;
    }

    UserIntegralEnum(BigDecimal start, BigDecimal end, BigDecimal integral, Integer type, String desc) {
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
