package kayroc.android.bug;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import kayroc.android.utils.BarUtils;

/**
 * 解决全屏Activity的键盘档住输入框
 * 来自：https://blog.csdn.net/passerby_b/article/details/82686662
 * 注意：
 * 1.要在setContentView之后调用 assistActivity(activity)！
 * 2.要是横屏输入法不是满屏的，就需要自己适配了！
 * 3.自测没有发现问题，但无法100%保证兼容性~
 * 4.分屏模式下的处理，不知道会不会有其他问题，如果不是刚需，建议还是通过setSoftInputMode尝试调整~~~
 * <p>
 * 更新 Cooper 2018-9-13 13:27:59
 * 1.解决虚拟导航栏隐藏显示布局不自动适配的问题（三星Note8 8.0实测横屏竖屏都没问题，Vivo没有虚拟按键的机器6.0测试没有问题）
 * 2.解决分屏模式下不适配的问题（三星Note8 8.0实测横屏竖屏都没问题）
 * 3.优化代码
 * <p>
 * 参考：https://blog.csdn.net/smileiam/article/details/69055963
 * 参考：https://blog.csdn.net/auccy/article/details/80632429
 * 参考：https://github.com/yy1300326388/AndroidBarUtils/blob/master/app/src/main/java/cn/zsl/androidbarutils/utils/AndroidBarUtils.java
 * 其实最初的原版就是 AndroidBug5497Workaround ，但是原版考虑的不够全面，尤其是虚拟导航栏的问题，没有考虑进去
 * 参考：https://www.jianshu.com/p/a95a1b84da11
 *
 * @author kayroc
 */
@SuppressWarnings({"CommentedOutCode", "AlibabaRemoveCommentedCode", "unused"})
public final class AndroidBug5497Workaround {
    public static void assistActivity(Activity activity) {
        new AndroidBug5497Workaround(activity);
    }

    /** ContentView */
    private final View mContentView;
    /** contentView 的 layoutParams */
    private final FrameLayout.LayoutParams mFrameLayoutParams;

    // private boolean isNavigationShowing = false;
    // private boolean isFullscreenMode = false;
    // /**虚拟导航栏高度*/
    // private int barNavigationHeight = 0;
    // /**虚拟导航栏宽度*/
    // private int barNavigationWidth = 0;

    private int barStatusHeight = 0;
    /** 上一次的可用高度 */
    private int lastUsableHeight = 0;
    // /** 上一次的可用宽度 */
    // private int lastUsableWidth = 0;

    private AndroidBug5497Workaround(final Activity activity) {
        //region 本来是想通过这个监听虚拟按键，结果发现这个回调比布局回调要晚，所以不用了。放在这里是为了给以后提供一些思路。
        // //1.为DecorView添加系统组件的可见变更事件
        // View decorView = activity.getWindow().getDecorView();
        // isNavigationShowing = ((decorView.getSystemUiVisibility() & View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) == 0);
        // isFullscreenMode = ((decorView.getSystemUiVisibility() & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0);//api 16以上
        // decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {//参考：https://blog.csdn.net/auccy/article/details/80632429
        //     @Override
        //     public void onSystemUiVisibilityChange(int visibility) {
        //         if ((visibility & View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) == 0) {
        //             isNavigationShowing = true;
        //         } else {
        //             isNavigationShowing = false;
        //         }
        //         if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
        //             isFullscreenMode = true;
        //         } else {
        //             isFullscreenMode = true;
        //         }
        //     }
        // });
        //endregion

        // 1.获取 状态栏 高度，获取 导航栏 高度、宽度（横屏用到的，可是横屏在手机上输入法会满屏，不知道不满屏的情况，所以不处理了，要是你遇到了，自行按照横屏的方式解决吧）
        barStatusHeight = BarUtils.getStatusBarHeight();
        // barNavigationHeight = getNavigationBarHeight(activity);
        // barNavigationWidth = getNavigationBarWidth(activity);
        // 2.找到Activity的最外层布局控件，它其实是一个DecorView,它所用的控件就是FrameLayout
        final FrameLayout content = activity.findViewById(android.R.id.content);
        // 3.获取到setContentView放进去的View
        mContentView = content.getChildAt(0);
        // 4.拿到我们设置的View的布局参数，主要是调整该参数来实现软键盘弹出上移
        mFrameLayoutParams = (FrameLayout.LayoutParams) mContentView.getLayoutParams();
        // 5.给我们设置的View添加布局变动的监听，来实现布局动作（虚拟导航栏的弹出收起也会触发该监听！）
        mContentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            // 软键盘弹出、系统导航栏隐藏显示均会触发这里
            @SuppressWarnings("AlibabaUndefineMagicConstant")
            @SuppressLint("ObsoleteSdkInt")
            @Override
            public void onGlobalLayout() {
                // 包含虚拟按键的高度（如果有的话）
                int heightRoot = content.getRootView().getHeight();
                // 不含虚拟按键的高度，貌似不包含状态栏高度
                int heightDecor = content.getHeight();

                // 我们 setContentView 设置的 view 的可用高度
                int usableHeight = computeUsableHeight();

                if (usableHeight != lastUsableHeight) {
                    lastUsableHeight = usableHeight;//防止重复变动

                    int heightDifference = heightDecor - usableHeight;

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && activity.isInMultiWindowMode()) {
                        // 如果是分屏模式
                        if (heightDifference > 0) {
                            // 分屏模式，只要变动了就人为弹出键盘，因为分屏可能该Activity是在手机屏幕的上方，弹出输入法只是遮盖了一丁点~如果不合适，需要你自己适配了！
                            // 这里不能加状态栏高度哟~
                            setHeight(heightDecor - heightDifference);
                        } else {
                            // 还原默认高度，不能用计算的值，因为虚拟导航栏显示或者隐藏的时候也会改变高度
                            setHeight(FrameLayout.LayoutParams.MATCH_PARENT);
                        }
                    } else {
                        if (heightDifference > (heightDecor / 4)) {
                            // 高度变动超过decor的四分之一则认为是软键盘弹出事件，为什么不用屏幕高度呢？开始以为这样在分屏模式下也可以监听，但是实测不行。
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                // 这里为什么要添加状态栏高度？
                                setHeight(heightDecor - heightDifference + barStatusHeight);
                            } else {
                                // 这里不添加状态栏高度？不懂为什么，原版如此，就先这样吧。遇到再说~
                                setHeight(heightDecor - heightDifference);
                            }
                        } else {
                            // 还原默认高度，不能用计算的值，因为虚拟导航栏显示或者隐藏的时候也会改变高度
                            setHeight(FrameLayout.LayoutParams.MATCH_PARENT);
                        }
                    }
                }
            }
        });
    }

    private void setHeight(int height) {
        if (mFrameLayoutParams.height != height) {
            // 不必要的更新就不要了
            mFrameLayoutParams.height = height;
            // 触发布局更新
            mContentView.requestLayout();
        }
    }

    private int computeUsableHeight() {
        Rect r = new Rect();
        mContentView.getWindowVisibleDisplayFrame(r);
        // 全屏模式下：直接返回r.bottom，r.top其实是状态栏的高度
        return (r.bottom - r.top);
    }

    private int computeUsableWidth() {
        Rect r = new Rect();
        mContentView.getWindowVisibleDisplayFrame(r);
        // 全屏模式下：直接返回r.bottom，r.top其实是状态栏的高度//横屏就是宽度
        return (r.right - r.left);
    }
}
