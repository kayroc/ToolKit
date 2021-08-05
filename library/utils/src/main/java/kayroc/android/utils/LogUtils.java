package kayroc.android.utils;

import android.util.Log;

/**
 * 简易的日志工具类 ( 主要打印 Android 日志 )
 *
 * @author kayroc
 */
@SuppressWarnings("unused")
public final class LogUtils {

    private LogUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 是否为 Debug 模式, true 打印日志, false 关闭日志
     */
    public static boolean isDebug = true;
    /**
     * 默认日志 TAG
     */
    private static final String TAG = LogUtils.class.getSimpleName();

    /**
     * 打印 Verbose 级别日志
     *
     * @param msg 日志内容
     */
    public static void v(String msg) {
        if (isDebug) {
            Log.v(TAG, getMsgFormat(msg));
        }
    }

    /**
     * 打印 Verbose 级别日志
     *
     * @param tag 日志TAG
     * @param msg 日志内容
     */
    public static void v(
        String tag,
        String msg
    ) {
        if (isDebug) {
            Log.v(tag, getMsgFormat(msg));
        }
    }

    /**
     * 打印 Debug 级别日志
     *
     * @param msg 日志内容
     */
    public static void d(String msg) {
        if (isDebug) {
            Log.d(TAG, getMsgFormat(msg));
        }
    }

    /**
     * 打印 Debug 级别日志
     *
     * @param tag 日志TAG
     * @param msg 日志内容
     */
    public static void d(
        String tag,
        String msg
    ) {
        if (isDebug) {
            Log.d(tag, getMsgFormat(msg));
        }
    }

    /**
     * 打印 Info 级别日志
     *
     * @param msg 日志内容
     */
    public static void i(String msg) {
        if (isDebug) {
            Log.i(TAG, getMsgFormat(msg));
        }
    }

    /**
     * 打印 Info 级别日志
     *
     * @param tag 日志TAG
     * @param msg 日志内容
     */
    public static void i(
        String tag,
        String msg
    ) {
        if (isDebug) {
            Log.i(tag, getMsgFormat(msg));
        }
    }

    /**
     * 打印 Warn 级别日志
     *
     * @param msg 日志内容
     */
    public static void w(String msg) {
        if (isDebug) {
            Log.w(TAG, getMsgFormat(msg));
        }
    }

    /**
     * 打印 Warn 级别日志
     *
     * @param tag 日志TAG
     * @param msg 日志内容
     */
    public static void w(
        String tag,
        String msg
    ) {
        if (isDebug) {
            Log.w(tag, getMsgFormat(msg));
        }
    }

    /**
     * 打印 Error 级别日志
     *
     * @param msg 日志内容
     */
    public static void e(String msg) {
        if (isDebug) {
            Log.e(TAG, getMsgFormat(msg));
        }
    }

    /**
     * 打印 Error 级别日志
     *
     * @param tag 日志TAG
     * @param msg 日志内容
     */
    public static void e(
        String tag,
        String msg
    ) {
        if (isDebug) {
            Log.e(tag, getMsgFormat(msg));
        }
    }

    /**
     * 获取相关数据:类名,方法名,行号等.用来定位行<br>
     * at cn.utils.MainActivity.onCreate(MainActivity.java:17) 就是用來定位行的代碼<br>
     *
     * @return [ Thread:main, at
     * cn.utils.MainActivity.onCreate(MainActivity.java:17)]
     */
    private static String getFunctionName() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }
            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }
            if (st.getClassName().equals(LogUtils.class.getName())) {
                continue;
            }
            return "[ Thread:" + Thread.currentThread().getName() + ", at " + st.getClassName() + "." + st.getMethodName()
                + "(" + st.getFileName() + ":" + st.getLineNumber() + ")" + " ]";
        }
        return "";
    }

    /**
     * 输出格式定义
     */
    private static String getMsgFormat(String msg) {
        return getFunctionName() + ":" + msg;
    }
}
