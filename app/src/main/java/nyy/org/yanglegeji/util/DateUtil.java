package nyy.org.yanglegeji.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日期工具类
 * @author niuyy
 * @date 2018/1/24
 */
public class DateUtil {

    public static final String DEFAULT_DATE_PATTEN = "yyyy-MM-dd";
    public static final String DEFAULT_DATE_TIME_PATTEN = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_TIME_PATTEN = "HH:mm:ss";
    public static final String NO_SYMBOL_DATE_TIME_PATTEN = "yyyyMMddHHmmss";


    public static final Locale DEFAULT_LOCAL = Locale.CHINA;

    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(DEFAULT_DATE_PATTEN, DEFAULT_LOCAL);
    public static final SimpleDateFormat DEFAULT_DATE_TIME_FORMAT = new SimpleDateFormat(DEFAULT_DATE_TIME_PATTEN, DEFAULT_LOCAL);
    public static final SimpleDateFormat DEFAULT_TIME_FORMAT = new SimpleDateFormat(DEFAULT_TIME_PATTEN, DEFAULT_LOCAL);
    public static final SimpleDateFormat NO_SYMBOL_DATE_TIME_FORMAT = new SimpleDateFormat(NO_SYMBOL_DATE_TIME_PATTEN, DEFAULT_LOCAL);


    /**
     * 格式化日期, 采用默认设置
     * @param date 时间
     * @return eg: 23:30:00
     */
    public static String formatTime(Date date){
       return DEFAULT_TIME_FORMAT.format(date);
    }
    /**
     * 格式化日期, 采用默认设置
     * @param date 时间
     * @return eg: 2018-02-02 23:30:00
     */
    public static String formatDateTime(Date date){
       return DEFAULT_DATE_TIME_FORMAT.format(date);
    }
}
