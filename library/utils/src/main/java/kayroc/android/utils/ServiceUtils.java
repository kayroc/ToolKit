package kayroc.android.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kayroc.android.ToolKitUtils;

/**
 * 服务相关工具类
 *
 * @author kayroc
 */
@SuppressWarnings("unused")
public final class ServiceUtils {

    private ServiceUtils() {
    }

    // =================
    // = 判断服务是否运行 =
    // =================

    /**
     * 判断服务是否运行
     *
     * @param clazz {@link Class}
     *
     * @return {@code true} yes, {@code false} no
     */
    public static boolean isServiceRunning(final Class<?> clazz) {
        return (clazz != null) && isServiceRunning(clazz.getName());
    }

    /**
     * 判断服务是否运行
     *
     * @param className package.ServiceClassName ( class.getName() )
     *
     * @return {@code true} yes, {@code false} no
     */
    public static boolean isServiceRunning(final String className) {
        try {
            ActivityManager activityManager = AppUtils.getActivityManager();
            List<RunningServiceInfo> lists = activityManager.getRunningServices(Integer.MAX_VALUE);
            for (RunningServiceInfo info : lists) {
                if (className.equals(info.service.getClassName())) {
                    return true;
                }
            }
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return false;
    }

    // =

    /**
     * 获取所有运行的服务
     *
     * @return 服务名集合
     */
    public static Set<String> getAllRunningService() {
        try {
            Set<String> names = new HashSet<>();
            ActivityManager activityManager = AppUtils.getActivityManager();
            List<RunningServiceInfo> lists = activityManager.getRunningServices(Integer.MAX_VALUE);
            for (RunningServiceInfo info : lists) {
                names.add(info.service.getClassName());
            }
            return names;
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return Collections.emptySet();
    }

    // ==========
    // = 启动服务 =
    // ==========

    /**
     * 启动服务
     *
     * @param className package.ServiceClassName ( class.getName() )
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean startService(final String className) {
        try {
            return startService(Class.forName(className));
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return false;
    }

    /**
     * 启动服务
     *
     * @param clazz {@link Class}
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean startService(final Class<?> clazz) {
        try {
            return AppUtils.startService(new Intent(ToolKitUtils.getContext(), clazz));
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return false;
    }

    // ==========
    // = 停止服务 =
    // ==========

    /**
     * 停止服务
     *
     * @param className package.ServiceClassName ( class.getName() )
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean stopService(final String className) {
        try {
            return stopService(Class.forName(className));
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
            return false;
        }
    }

    /**
     * 停止服务
     *
     * @param clazz {@link Class}
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean stopService(final Class<?> clazz) {
        try {
            return AppUtils.stopService(new Intent(ToolKitUtils.getContext(), clazz));
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
            return false;
        }
    }

    // ==========
    // = 绑定服务 =
    // ==========

    /**
     * 绑定服务
     *
     * @param className package.ServiceClassName ( class.getName() )
     * @param conn      {@link ServiceConnection}
     * @param flags     绑定选项
     *                  {@link Context#BIND_AUTO_CREATE}
     *                  {@link Context#BIND_DEBUG_UNBIND}
     *                  {@link Context#BIND_NOT_FOREGROUND}
     *                  {@link Context#BIND_ABOVE_CLIENT}
     *                  {@link Context#BIND_ALLOW_OOM_MANAGEMENT}
     *                  {@link Context#BIND_WAIVE_PRIORITY}
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean bindService(
        final String className,
        final ServiceConnection conn,
        final int flags
    ) {
        try {
            return bindService(Class.forName(className), conn, flags);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return false;
    }

    /**
     * 绑定服务
     *
     * @param clazz {@link Class}
     * @param conn  {@link ServiceConnection}
     * @param flags 绑定选项
     *              {@link Context#BIND_AUTO_CREATE}
     *              {@link Context#BIND_DEBUG_UNBIND}
     *              {@link Context#BIND_NOT_FOREGROUND}
     *              {@link Context#BIND_ABOVE_CLIENT}
     *              {@link Context#BIND_ALLOW_OOM_MANAGEMENT}
     *              {@link Context#BIND_WAIVE_PRIORITY}
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean bindService(
        final Class<?> clazz,
        final ServiceConnection conn,
        final int flags
    ) {
        try {
            Intent intent = new Intent(ToolKitUtils.getContext(), clazz);
            ToolKitUtils.getContext().bindService(intent, conn, flags);
            return true;
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return false;
    }

    // ==========
    // = 解绑服务 =
    // ==========

    /**
     * 解绑服务
     *
     * @param conn {@link ServiceConnection}
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean unbindService(final ServiceConnection conn) {
        try {
            ToolKitUtils.getContext().unbindService(conn);
            return true;
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return false;
    }
}