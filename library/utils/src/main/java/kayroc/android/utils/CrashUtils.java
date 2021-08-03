package kayroc.android.utils;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * UncaughtException 处理工具类
 *
 * @author kayroc
 * <pre>
 *     当程序发生 Uncaught 异常的时候, 由该类来接管程序, 并记录发送错误报告
 *
 *     CrashUtils.getInstance().init(application, new CrashUtils.CrashCatchListener() {
 *         @Override
 *         public void handleException(Throwable ex) {
 *             // 保存日志信息
 *         }
 *
 *         @Override
 *         public void uncaughtException(
 *             Context context,
 *             Thread thread,
 *             Throwable ex
 *         ) {
 *             // // 退出 JVM (Java 虚拟机 ) 释放所占内存资源, 0 表示正常退出、非 0 的都为异常退出
 *             // System.exit(-1);
 *             // // 从操作系统中结束掉当前程序的进程
 *             // android.os.Process.killProcess(android.os.Process.myPid());
 *             // 关闭 APP
 *             AppUtils.exitApplication();
 *             // 可开启定时任务, 延迟几秒启动 APP
 *         }
 *     });
 * </pre>
 */
@SuppressWarnings("unused")
public final class CrashUtils
    implements UncaughtExceptionHandler {

    private CrashUtils() {
    }

    /** Application */
    private Application mApplication;
    /** 系统默认的 UncaughtException 处理器 */
    private UncaughtExceptionHandler mDefaultHandler;
    /** 捕获异常事件处理 */
    private CrashCatchListener mCrashCatchListener;
    /** CrashUtils 实例 */
    private static volatile CrashUtils sInstance;

    /**
     * 获取 CrashUtils 实例
     *
     * @return {@link CrashUtils}
     */
    public static CrashUtils getInstance() {
        if (sInstance == null) {
            synchronized (CrashUtils.class) {
                if (sInstance == null) {
                    sInstance = new CrashUtils();
                }
            }
        }
        return sInstance;
    }

    /**
     * 初始化方法
     *
     * @param application        {@link Application}
     * @param crashCatchListener {@link CrashCatchListener}
     */
    public void init(
        Application application,
        CrashCatchListener crashCatchListener
    ) {
        this.mApplication = application;
        this.mCrashCatchListener = crashCatchListener;
        // 获取系统默认的 UncaughtException 处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该 CrashUtils 为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当 UncaughtException 发生时会转入该函数来处理
     *
     * @param thread {@link Thread}
     * @param ex     {@link Throwable}
     */
    @Override
    public void uncaughtException(
        @NonNull Thread thread,
        @NonNull Throwable ex
    ) {
        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            if (mCrashCatchListener != null) {
                mCrashCatchListener.uncaughtException(mApplication, thread, ex);
            }
        }
    }

    /**
     * 自定义错误处理 ( 收集错误信息、发送错误报告等操作均在此完成 )
     *
     * @param ex {@link Throwable}
     *
     * @return {@code true} 处理该异常信息, {@code false} 未处理该异常信息
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        // 触发回调
        if (mCrashCatchListener != null) {
            mCrashCatchListener.handleException(ex);
        }
        return true;
    }

    /**
     * 异常捕获处理
     *
     * @author kayroc
     */
    public interface CrashCatchListener {

        /**
         * 处理异常
         *
         * @param ex {@link Throwable}
         */
        void handleException(Throwable ex);

        /**
         * 处理未捕获的异常
         *
         * @param context {@link Context}
         * @param thread  {@link Thread}
         * @param ex      {@link Throwable}
         */
        void uncaughtException(
            Context context,
            Thread thread,
            Throwable ex
        );
    }
}