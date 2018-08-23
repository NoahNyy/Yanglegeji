package nyy.org.yanglegeji.util;

import android.util.Log;

import java.util.Collection;
import java.util.Date;

/**
 * 日志工具类
 * @author niuyy
 */
public class LogUtil {

    public static final String TAG = "Yanlegeji";
    private static final String CLZ_NAME = LogUtil.class.getSimpleName();


    public static Boolean FLAG = Boolean.TRUE;
    public static Boolean WRITE_LOG_FLAG = Boolean.FALSE;

    public enum Level{
        I,  // 一般信息
        V,  // 未使用
        D,  // 调试用
        W,  // 不正常, 但可运行
        E;  // 功能异常
    }

    public static void assertTrue(String msg, boolean condition) {
        if (!condition){
            fail(msg);
        }
    }

    public static void assertNotNull(String msg, Object obj) {
        if (obj == null){
            fail(msg);
        }
    }

    public static void assertStrIsNotBlank(String msg, String str) {
        if (StringUtil.isBlank(str)){
            fail(msg);
        }
    }

    public static void assertCollectionIsNotEmpty(String msg, Collection collection) {
        if (CollectionUtil.isEmpty(collection)){
            fail(msg);
        }
    }

    static public void fail(String message) {
        if (message == null) {
            throw new RuntimeException("断言错误信息为空!");
        }
        throw new RuntimeException(message);
    }


    public static void i(String str) {
        String msg = "[" + DateUtil.formatDateTime(new Date()) + "]: " + str;
        if (FLAG) {
            Log.i(TAG, msg);
        }
    }

    public static void i(String format, Object... args) {
        String msg = "[" + DateUtil.formatDateTime(new Date()) + "]: " + String.format(format, args);
        if (FLAG) {
            Log.i(TAG, msg);
        }
    }

    public static void i(String str, Throwable t) {
        String msg = "[" + DateUtil.formatDateTime(new Date()) + "]: " + str;
        if (FLAG) {
            Log.i(TAG, msg, t);
        }
    }

    public static void w(String str) {
        String msg = "[" + DateUtil.formatDateTime(new Date()) + "]: " + str;
        if (FLAG) {
            Log.w(TAG, msg);
        }
    }

    public static void w(String format, Object... args) {
        String msg = "[" + DateUtil.formatDateTime(new Date()) + "]: " + String.format(format, args);
        if (FLAG) {
            Log.w(TAG, msg);
        }
    }

    public static void w(String str, Throwable t) {
        String msg = "[" + DateUtil.formatDateTime(new Date()) + "]: " + str;
        if (FLAG) {
            Log.w(TAG, msg, t);
        }
    }

    public static void e(String str) {
        String msg = "[" + DateUtil.formatDateTime(new Date()) + "]: " + str;
        if (FLAG) {
            Log.e(TAG, msg);
        }
    }

    public static void e(String format, Object... args) {
        String msg = "[" + DateUtil.formatDateTime(new Date()) + "]: " + String.format(format, args);
        if (FLAG) {
            Log.e(TAG, msg);
        }
    }

    public static void e(String str, Throwable t) {
        String msg = "[" + DateUtil.formatDateTime(new Date()) + "]: " + str;
        if (FLAG) {
            Log.e(TAG, msg, t);
        }
    }

    public static void d(String str) {
        String msg = "[" + DateUtil.formatDateTime(new Date()) + "]: " + str;
        if (FLAG) {
            Log.d(TAG, msg);
        }
    }

    public static void d(String tag, String str) {
        String msg = "[" + DateUtil.formatDateTime(new Date()) + "]: " + str;
        if (FLAG) {
            Log.d(tag, msg);
        }
    }

    public static void d(String format, Object... args) {
        String msg = "[" + DateUtil.formatDateTime(new Date()) + "]: " + String.format(format, args);
        if (FLAG) {
            Log.d(TAG, msg);
        }
    }

    public static void d(String str, Throwable t) {
        String msg = "[" + DateUtil.formatDateTime(new Date()) + "]: " + str;
        if (FLAG) {
            Log.d(TAG, msg, t);
        }
    }
}
