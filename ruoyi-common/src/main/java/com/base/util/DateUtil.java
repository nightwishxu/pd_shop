package com.base.util;

import cn.hutool.core.date.DateBetween;
import cn.hutool.core.date.DateException;
import cn.hutool.core.date.DateUnit;
import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static cn.hutool.core.date.DateUtil.parseDateTime;
import static cn.hutool.core.date.DateUtil.parseTime;

/**
 * 日期工具类
 * Created by macro on 2019/1/29.
 */
public class DateUtil {


    public static final String YYSS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYMMMM = "yyyy-MM-dd HH:mm";
    public static final String MMMM = "MM-dd HH:mm";
    public static final String YYYY = "yyyy";
    public static final String YYMM = "yyyy-MM";
    public static final String YYMMDD = "yyyy-MM-dd";
    public static final String HHMM = "HH:mm";
    public static final String YYSSSS = "yyyyMMddHHmmsssss";
    public static final String MMDD = "MM-dd";
    public static final String NORM_DATE_PATTERN = "yyyy-MM-dd";
    public static final String NORM_TIME_PATTERN = "HH:mm:ss";
    public static final String NORM_DATETIME_MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String NORM_DATETIME_MS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String HTTP_DATETIME_PATTERN = "EEE, dd MMM yyyy HH:mm:ss z";

    /**
     * 从Date类型的时间中提取日期部分
     */
    public static Date getDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 从Date类型的时间中提取时间部分
     */
    public static Date getTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, 1970);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static String getCurrentTime(String format) {
        SimpleDateFormat formatObj = new SimpleDateFormat(format);
        String date = formatObj.format(new Date());
        return date;
    }

    public static String getDate(int num, String format) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(5, num);
        Date time = gc.getTime();
        SimpleDateFormat formatObj = new SimpleDateFormat(format);
        String date = formatObj.format(time);
        return date;
    }

    public static Date add(Date date, int n) {
        if (date == null) {
            return null;
        } else {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(date);
            gc.add(5, n);
            return gc.getTime();
        }
    }

    public static Date addMonth(Date date, int n) {
        if (date == null) {
            return null;
        } else {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(date);
            gc.add(2, n);
            return gc.getTime();
        }
    }

    public static Date addHour(Date date, int n) {
        if (date == null) {
            return null;
        } else {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(date);
            gc.add(10, n);
            return gc.getTime();
        }
    }

    public static Date addMinute(Date date, int n) {
        if (date == null) {
            return null;
        } else {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(date);
            gc.add(12, n);
            return gc.getTime();
        }
    }

//    public static long timeDiff(String data1, String data2, DateUtil.TimeType timeType) {
//        Calendar c1 = new GregorianCalendar(Integer.parseInt(data1.substring(0, 4)), Integer.parseInt(data1.substring(5, 7)), Integer.parseInt(data1.substring(8, 10)), Integer.parseInt(data1.substring(11, 13)), Integer.parseInt(data1.substring(14, 16)));
//        Calendar c2 = new GregorianCalendar(Integer.parseInt(data2.substring(0, 4)), Integer.parseInt(data2.substring(5, 7)), Integer.parseInt(data2.substring(8, 10)), Integer.parseInt(data2.substring(11, 13)), Integer.parseInt(data2.substring(14, 16)));
//        long diffMillis = 0L;
//        if (c1.after(c2)) {
//            diffMillis = c1.getTimeInMillis() - c2.getTimeInMillis();
//        } else {
//            diffMillis = c2.getTimeInMillis() - c1.getTimeInMillis();
//        }
//
//        if (timeType == DateUtil.TimeType.diffMillis) {
//            return diffMillis;
//        } else if (timeType == DateUtil.TimeType.diffSecs) {
//            return diffMillis / 1000L;
//        } else if (timeType == DateUtil.TimeType.diffMins) {
//            return diffMillis / 60000L;
//        } else {
//            return timeType == DateUtil.TimeType.diffHours ? diffMillis / 3600000L : diffMillis / 86400000L;
//        }
//    }

    public static String toIndexDate(String date) {
        String indexDate = "19700101";
        if (date != null && date.trim().length() >= 10) {
            int i = date.indexOf(" ");
            if (i != -1) {
                indexDate = date.substring(0, i).replaceAll("-", "").replace("/", "");
            } else {
                indexDate = date.replaceAll("-", "").replace("/", "");
            }
        }

        return indexDate;
    }

    public static String toDate(String indexDate) {
        StringBuilder sb = new StringBuilder("1970-01-01");
        if (indexDate != null && indexDate.trim().length() == 8) {
            sb = new StringBuilder(indexDate);
            sb.insert(4, '-');
            sb.insert(7, '-');
        }

        return sb.toString();
    }

    public static Date parseDate(String dateStr, String pattern) {
        if (dateStr != null && dateStr.length() != 0 && pattern != null && pattern.length() != 0) {
            DateFormat fmt = new SimpleDateFormat(pattern);
            Date result = null;

            try {
                result = fmt.parse(dateStr);
            } catch (ParseException var5) {
                var5.printStackTrace();
            }

            return result;
        } else {
            return null;
        }
    }

    public static String dateToStr(Date date) {
        if (date == null) {
            return null;
        } else {
            SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return newFormat.format(date);
        }
    }

    public static String dateToStr(Date date, String sFormat) {
        if (date == null) {
            return null;
        } else {
            SimpleDateFormat newFormat = new SimpleDateFormat(sFormat);
            return newFormat.format(date);
        }
    }

    public static Date strToDate(String strDate) {
        if (!StringUtil.isEmpty(strDate) && !"".equals(strDate.trim())) {
            SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            try {
                return newFormat.parse(strDate);
            } catch (ParseException var3) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static Date strToDate(String strDate, String sFormat) {
        if (!StringUtil.isBlank(strDate) && !"".equals(strDate.trim())) {
            SimpleDateFormat newFormat = new SimpleDateFormat(sFormat);

            try {
                return newFormat.parse(strDate);
            } catch (ParseException var4) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static long between(Date beginDate, Date endDate, DateUnit unit) {
        return (new DateBetween(beginDate, endDate)).between(unit);
    }

    public static String getAddDaysDate(Date date, int days) {
        long das = date.getTime() / 86400000L + (long)days + 1L;
        return dateToStr(new Date(das * 86400000L));
    }

    public static String format(Date date, String format) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        }
    }

    public static int secondsAfter(Date a, Date b) {
        return (int)((a.getTime() - b.getTime()) / 1000L);
    }


    public static Date parse(String time,String format) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(time);
    }

    public static Date parse(String dateStr) {
        if (null == dateStr) {
            return null;
        } else {
            dateStr = dateStr.trim();
            int length = dateStr.length();

            try {
                if (length == "yyyy-MM-dd HH:mm:ss".length()) {
                    return parseDateTime(dateStr);
                }

                if (length == "yyyy-MM-dd".length()) {
                    return parseDate(dateStr,"yyyy-MM-dd");
                }

                if (length == "HH:mm:ss".length()) {
                    return parseTime(dateStr);
                }

                if (length == "yyyy-MM-dd HH:mm".length()) {
                    return parse(dateStr, "yyyy-MM-dd HH:mm");
                }

                if (length >= "yyyy-MM-dd HH:mm:ss.SSS".length() - 2) {
                    return parse(dateStr, "yyyy-MM-dd HH:mm:ss.SSS");
                }
            } catch (Exception var3) {
                throw new DateException(StringUtil.format("Parse [{}] with format normal error!", new Object[]{dateStr}));
            }

            throw new DateException(StringUtil.format(" [{}] format is not fit for date pattern!", new Object[]{dateStr}));
        }
    }


    public static String dateAsQQ(Date date) {
        String str = null;
        if (date == null) {
            return str;
        } else {
            Date today = new Date();
            if (isSameYear(date, today)) {
                if (isSameDay(date, today)) {
                    str = dateToStr(date, "HH:mm");
                } else if (getXcDays(date, today) == 1L) {
                    str = "昨天";
                } else if (getXcDays(date, today) <= 7L) {
                    str = getChineseWeek(date);
                } else {
                    str = dateToStr(date, "MM-dd HH:mm");
                }
            } else {
                str = dateToStr(date, "yyyy-MM-dd");
            }

            return str;
        }
    }

    public static boolean isSameYear(Date a, Date b) {
        return isSameDate(a, b, "yyyy");
    }

    public static boolean isSameDay(Date a, Date b) {
        return isSameDate(a, b, "yyyy-MM-dd");
    }

    public static boolean isSameDay(Date a) {
        return isSameDate(a, new Date(), "yyyy-MM-dd");
    }

    public static boolean isSameDate(Date a, Date b, String format) {
        return dateToStr(a, format).equals(dateToStr(b, format));
    }

    public static long getXcDays(Date sd, Date ed) {
        return (ed.getTime() - sd.getTime()) / 86400000L;
    }

    public static String getChineseWeek(Date date) {
        Calendar d1 = Calendar.getInstance();
        d1.setTime(date);
        String[] dayNames = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        int dayOfWeek = d1.get(7);
        return dayNames[dayOfWeek - 1];
    }
}
