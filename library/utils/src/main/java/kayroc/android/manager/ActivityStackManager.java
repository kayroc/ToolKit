package kayroc.android.manager;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Iterator;
import java.util.Stack;

import kayroc.android.utils.KeyBoardUtils;
import kayroc.android.utils.LogUtils;

/**
 * Activity 管理
 *
 * @author kayroc
 */
@SuppressWarnings({"unused", "SpellCheckingInspection"})
public final class ActivityStackManager implements Application.ActivityLifecycleCallbacks {

    private static volatile ActivityStackManager sInstance;

    public static ActivityStackManager getInstance() {
        if (sInstance == null) {
            synchronized (ActivityStackManager.class) {
                if (sInstance == null) {
                    sInstance = new ActivityStackManager();
                }
            }
        }
        return sInstance;
    }

    @Override
    public void onActivityCreated(
        @NonNull Activity activity,
        @Nullable Bundle savedInstanceState
    ) {
        LogUtils.i(activity.getClass().getSimpleName() + " onActivityCreated");
        addActivity(activity);
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        LogUtils.i(activity.getClass().getSimpleName() + " onActivityStarted");
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        LogUtils.i(activity.getClass().getSimpleName() + " onActivityResumed");
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        LogUtils.i(activity.getClass().getSimpleName() + " onActivityPaused");
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        LogUtils.i(activity.getClass().getSimpleName() + " onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(
        @NonNull Activity activity,
        @NonNull Bundle outState
    ) {
        LogUtils.i(activity.getClass().getSimpleName() + " onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        LogUtils.i(activity.getClass().getSimpleName() + " onActivityDestroyed");
        removeActivity(activity);
        KeyBoardUtils.fixSoftInputLeaks(activity);
    }

    /** Activity 栈 ( 后进先出 ) */
    private final Stack<Activity> mActivityStacks = new Stack<>();

    /**
     * 获取 Activity 栈
     *
     * @return {@link Stack<Activity>}
     */
    public Stack<Activity> getActivityStacks() {
        return mActivityStacks;
    }

    /**
     * 添加 Activity
     *
     * @param activity {@link Activity}
     *
     * @return {@link ActivityStackManager}
     */
    @SuppressWarnings("UnusedReturnValue")
    public ActivityStackManager addActivity(final Activity activity) {
        if (activity != null) {
            synchronized (mActivityStacks) {
                if (mActivityStacks.contains(activity)) {
                    return this;
                }
                mActivityStacks.add(activity);
            }
        }
        return this;
    }

    /**
     * 移除 Activity
     *
     * @param activity {@link Activity}
     *
     * @return {@link ActivityStackManager}
     */
    @SuppressWarnings("UnusedReturnValue")
    public ActivityStackManager removeActivity(final Activity activity) {
        if (activity != null) {
            synchronized (mActivityStacks) {
                int index = mActivityStacks.indexOf(activity);
                if (index == -1) {
                    return this;
                }
                try {
                    mActivityStacks.remove(index);
                } catch (Exception e) {
                    LogUtils.e(e.getMessage());
                }
            }
        }
        return this;
    }

    /**
     * 移除多个 Activity
     *
     * @param activitys Activity[]
     *
     * @return {@link ActivityStackManager}
     */
    public ActivityStackManager removeActivity(final Activity... activitys) {
        if (activitys != null && activitys.length != 0) {
            for (Activity activity : activitys) {
                removeActivity(activity);
            }
        }
        return this;
    }

    /**
     * 获取最后一个 ( 当前 ) Activity
     *
     * @return 最后一个 ( 当前 ) {@link Activity}
     */
    public Activity currentActivity() {
        return mActivityStacks.lastElement();
    }

    /**
     * 关闭最后一个 ( 当前 ) Activity
     *
     * @return {@link ActivityStackManager}
     */
    public ActivityStackManager finishActivity() {
        return finishActivity(mActivityStacks.lastElement());
    }

    /**
     * 检测是否包含指定的 Activity
     *
     * @param clazzs Class(Activity)[]
     *
     * @return {@code true} yes, {@code false} no
     */
    public boolean existActivitys(final Class<?>... clazzs) {
        if (clazzs != null && clazzs.length != 0) {
            synchronized (mActivityStacks) {
                // 保存新的堆栈, 防止出现同步问题
                Stack<Activity> stack = new Stack<>();
                stack.addAll(mActivityStacks);
                try {
                    // 进行遍历判断
                    for (Activity activity : stack) {
                        if (activity != null && !activity.isFinishing()) {
                            for (Class<?> clazz : clazzs) {
                                if (clazz != null && activity.getClass().getName().equals(
                                    clazz.getName())
                                ) {
                                    return true;
                                }
                            }
                        }
                    }
                } finally {
                    // 移除数据, 并且清空内存
                    stack.clear();
                }
            }
        }
        return false;
    }

    /**
     * 关闭指定 Activity
     *
     * @param activity {@link Activity}
     *
     * @return {@link ActivityStackManager}
     */
    public ActivityStackManager finishActivity(final Activity activity) {
        // 先移除 Activity
        removeActivity(activity);
        // Activity 不为 null, 并且属于未销毁状态
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
        }
        return this;
    }

    /**
     * 关闭多个 Activity
     *
     * @param activitys Activity[]
     *
     * @return {@link ActivityStackManager}
     */
    public ActivityStackManager finishActivity(final Activity... activitys) {
        if (activitys != null && activitys.length != 0) {
            for (Activity activity : activitys) {
                finishActivity(activity);
            }
        }
        return this;
    }

    /**
     * 关闭指定类名 Activity
     *
     * @param clazz Activity.class
     *
     * @return {@link ActivityStackManager}
     */
    public ActivityStackManager finishActivity(final Class<?> clazz) {
        if (clazz != null) {
            synchronized (mActivityStacks) {
                // 保存新的堆栈, 防止出现同步问题
                Stack<Activity> stack = new Stack<>();
                stack.addAll(mActivityStacks);
                // 清空全部, 便于后续操作处理
                mActivityStacks.clear();
                // 进行遍历移除
                Iterator<Activity> iterator = stack.iterator();
                while (iterator.hasNext()) {
                    Activity activity = iterator.next();
                    // 判断是否想要关闭的 Activity
                    if (activity != null) {
                        if (activity.getClass() == clazz) {
                            // 如果 Activity 没有 finish 则进行 finish
                            if (!activity.isFinishing()) {
                                activity.finish();
                            }
                            // 删除对应的 Item
                            iterator.remove();
                        }
                    } else {
                        // 删除对应的 Item
                        iterator.remove();
                    }
                }
                // 把不符合条件的保存回去
                mActivityStacks.addAll(stack);
                // 移除数据, 并且清空内存
                stack.clear();
            }
        }
        return this;
    }

    /**
     * 结束多个类名 Activity
     *
     * @param clazzs Class(Activity)[]
     *
     * @return {@link ActivityStackManager}
     */
    public ActivityStackManager finishActivity(final Class<?>... clazzs) {
        if (clazzs != null && clazzs.length != 0) {
            synchronized (mActivityStacks) {
                // 保存新的堆栈, 防止出现同步问题
                Stack<Activity> stack = new Stack<>();
                stack.addAll(mActivityStacks);
                // 清空全部, 便于后续操作处理
                mActivityStacks.clear();
                // 判断是否销毁
                boolean isRemove;
                // 进行遍历移除
                Iterator<Activity> iterator = stack.iterator();
                while (iterator.hasNext()) {
                    Activity activity = iterator.next();
                    // 判断是否想要关闭的 Activity
                    if (activity != null) {
                        // 默认不需要销毁
                        isRemove = false;
                        // 循环判断
                        for (Class<?> clazz : clazzs) {
                            // 判断是否相同
                            if (activity.getClass() == clazz) {
                                isRemove = true;
                                break;
                            }
                        }
                        // 判断是否销毁
                        if (isRemove) {
                            // 如果 Activity 没有 finish 则进行 finish
                            if (!activity.isFinishing()) {
                                activity.finish();
                            }
                            // 删除对应的 Item
                            iterator.remove();
                        }
                    } else {
                        // 删除对应的 Item
                        iterator.remove();
                    }
                }
                // 把不符合条件的保存回去
                mActivityStacks.addAll(stack);
                // 移除数据, 并且清空内存
                stack.clear();
            }
        }
        return this;
    }

    /**
     * 结束全部 Activity 除忽略的 Activity 外
     *
     * @param clazz Activity.class
     *
     * @return {@link ActivityStackManager}
     */
    public ActivityStackManager finishAllActivityToIgnore(final Class<?> clazz) {
        if (clazz != null) {
            synchronized (mActivityStacks) {
                // 保存新的堆栈, 防止出现同步问题
                Stack<Activity> stack = new Stack<>();
                stack.addAll(mActivityStacks);
                // 清空全部, 便于后续操作处理
                mActivityStacks.clear();
                // 进行遍历移除
                Iterator<Activity> iterator = stack.iterator();
                while (iterator.hasNext()) {
                    Activity activity = iterator.next();
                    // 判断是否想要关闭的 Activity
                    if (activity != null) {
                        if (activity.getClass() != clazz) {
                            // 如果 Activity 没有 finish 则进行 finish
                            if (!activity.isFinishing()) {
                                activity.finish();
                            }
                            // 删除对应的 Item
                            iterator.remove();
                        }
                    } else {
                        // 删除对应的 Item
                        iterator.remove();
                    }
                }
                // 把不符合条件的保存回去
                mActivityStacks.addAll(stack);
                // 移除数据, 并且清空内存
                stack.clear();
            }
        }
        return this;
    }

    /**
     * 结束全部 Activity 除忽略的 Activity 外
     *
     * @param clazzs Class(Activity)[]
     *
     * @return {@link ActivityStackManager}
     */
    public ActivityStackManager finishAllActivityToIgnore(final Class<?>... clazzs) {
        if (clazzs != null && clazzs.length != 0) {
            synchronized (mActivityStacks) {
                // 保存新的堆栈, 防止出现同步问题
                Stack<Activity> stack = new Stack<>();
                stack.addAll(mActivityStacks);
                // 清空全部, 便于后续操作处理
                mActivityStacks.clear();
                // 判断是否销毁
                boolean isRemove;
                // 进行遍历移除
                Iterator<Activity> iterator = stack.iterator();
                while (iterator.hasNext()) {
                    Activity activity = iterator.next();
                    // 判断是否想要关闭的 Activity
                    if (activity != null) {
                        // 默认需要销毁
                        isRemove = true;
                        // 循环判断
                        for (Class<?> clazz : clazzs) {
                            // 判断是否相同
                            if (activity.getClass() == clazz) {
                                isRemove = false;
                                break;
                            }
                        }
                        // 判断是否销毁
                        if (isRemove) {
                            // 如果 Activity 没有 finish 则进行 finish
                            if (!activity.isFinishing()) {
                                activity.finish();
                            }
                            // 删除对应的 Item
                            iterator.remove();
                        }
                    } else {
                        // 删除对应的 Item
                        iterator.remove();
                    }
                }
                // 把不符合条件的保存回去
                mActivityStacks.addAll(stack);
                // 移除数据, 并且清空内存
                stack.clear();
            }
        }
        return this;
    }

    /**
     * 结束所有 Activity
     *
     * @return {@link ActivityStackManager}
     */
    @SuppressWarnings("UnusedReturnValue")
    public ActivityStackManager finishAllActivity() {
        synchronized (mActivityStacks) {
            // 保存新的堆栈, 防止出现同步问题
            Stack<Activity> stack = new Stack<>();
            stack.addAll(mActivityStacks);
            // 清空全部, 便于后续操作处理
            mActivityStacks.clear();
            // 进行遍历移除
            Iterator<Activity> iterator = stack.iterator();
            while (iterator.hasNext()) {
                Activity activity = iterator.next();
                if (activity != null && !activity.isFinishing()) {
                    activity.finish();
                    // 删除对应的 Item
                    iterator.remove();
                }
            }
            // 移除数据, 并且清空内存
            stack.clear();
        }
        return this;
    }
}
