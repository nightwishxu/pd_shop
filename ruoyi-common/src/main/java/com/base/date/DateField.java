//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.base.date;

public enum DateField {
    YEAR(1),
    MONTH(2),
    WEEK_OF_YEAR(3),
    WEEK_OF_MONTH(4),
    DAY_OF_MONTH(5),
    DAY_OF_YEAR(6),
    DAY_OF_WEEK(7),
    DAY_OF_WEEK_IN_MONTH(8),
    AM_PM(9),
    HOUR(10),
    HOUR_OF_DAY(11),
    MINUTE(12),
    SECOND(13),
    MILLISECOND(14);

    private int value;

    private DateField(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static DateField of(int calendarPartIntValue) {
        switch(calendarPartIntValue) {
            case 1:
                return YEAR;
            case 2:
                return MONTH;
            case 3:
                return WEEK_OF_YEAR;
            case 4:
                return WEEK_OF_MONTH;
            case 5:
                return DAY_OF_MONTH;
            case 6:
                return DAY_OF_YEAR;
            case 7:
                return DAY_OF_WEEK;
            case 8:
                return DAY_OF_WEEK_IN_MONTH;
            case 9:
            case 10:
            case 11:
            default:
                return null;
            case 12:
                return MINUTE;
            case 13:
                return SECOND;
            case 14:
                return MILLISECOND;
        }
    }
}
