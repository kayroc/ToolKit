package kayroc.android;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import kayroc.android.manager.ActivityStackManager;
import kayroc.android.utils.LogUtils;

/**
 * ToolKit 工具类（用于初始化 utils）
 *
 * @author kayroc
 */
public final class ToolKitUtils {

    private ToolKitUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 全局 Application 对象
     */
    private static Application sApplication;

    /**
     * 初始化
     *
     * @param application {@link Application}
     */
    public static void init(final Application application) {
        if (application == null) {
            throw new NullPointerException("application cannot be null");
        }

        initApplication(application);

        registerActivityLifecycleCallbacks(application);
    }

    // ======================================================================================
    // =                              全局 Application、Context                              =
    // ======================================================================================

    /**
     * 初始化 Application
     *
     * @param application {@link Application}
     */
    private static void initApplication(Application application) {
        if (sApplication == null && application != null) {
            sApplication = application;
        }
    }

    /**
     * 获取全局 Application
     *
     * @return {@link Application}
     */
    public static Application getApplication() {
        if (sApplication != null) {
            return sApplication;
        }
        try {
            Application application = getApplicationByReflect();
            init(application);
            return application;
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        throw new NullPointerException("u should init first");
    }

    /**
     * 反射获取 Application
     *
     * @return {@link Application}
     */
    private static Application getApplicationByReflect() {
        try {
            @SuppressLint("PrivateApi")
            Class<?> activityThread = Class.forName("android.app.ActivityThread");
            Object thread = activityThread.getMethod("currentActivityThread").invoke(null);
            Object app = activityThread.getMethod("getApplication").invoke(thread);
            if (app == null) {
                throw new NullPointerException("u should init first");
            }
            return (Application) app;
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        throw new NullPointerException("u should init first");
    }

    /**
     * 获取全局 Context
     *
     * @return {@link Context}
     */
    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    // ==================================================================================
    // =                              监听 Activity 生命周期                              =
    // ==================================================================================

    /**
     * 注册绑定 Activity 生命周期事件处理
     *
     * @param application {@link Application}
     */
    private static void registerActivityLifecycleCallbacks(final Application application) {
        // 先移除监听
        unregisterActivityLifecycleCallbacks(application);
        // 防止为 null
        if (application != null) {
            application.registerActivityLifecycleCallbacks(ActivityStackManager.getInstance());
        }
    }

    /**
     * 解除注册 Activity 生命周期事件处理
     *
     * @param application {@link Application}
     */
    private static void unregisterActivityLifecycleCallbacks(final Application application) {
        if (application != null) {
            application.unregisterActivityLifecycleCallbacks(ActivityStackManager.getInstance());
        }
    }

}
