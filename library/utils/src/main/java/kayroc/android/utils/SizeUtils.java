package kayroc.android.utils;

import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

/**
 * 大小工具类 ( dp, px, sp 转换、View 获取宽高等 )
 *
 * @author kayroc
 */
@SuppressWarnings("unused")
public final class SizeUtils {

    private SizeUtils() {
    }

    /**
     * dp 转 px
     *
     * @param dpValue 待转换值
     *
     * @return 转换后的值
     */
    public static int dp2px(final float dpValue) {
        return dp2px(dpValue, ResourceUtils.getDisplayMetrics());
    }

    /**
     * dp 转 px (float)
     *
     * @param dpValue 待转换值
     *
     * @return 转换后的值
     */
    public static float dp2pxf(final float dpValue) {
        return dp2pxf(dpValue, ResourceUtils.getDisplayMetrics());
    }

    /**
     * px 转 dp
     *
     * @param pxValue 待转换值
     *
     * @return 转换后的值
     */
    public static int px2dp(final float pxValue) {
        return px2dp(pxValue, ResourceUtils.getDisplayMetrics());
    }

    /**
     * px 转 dp (float)
     *
     * @param pxValue 待转换值
     *
     * @return 转换后的值
     */
    public static float px2dpf(final float pxValue) {
        return px2dpf(pxValue, ResourceUtils.getDisplayMetrics());
    }

    /**
     * sp 转 px
     *
     * @param spValue 待转换值
     *
     * @return 转换后的值
     */
    public static int sp2px(final float spValue) {
        return sp2px(spValue, ResourceUtils.getDisplayMetrics());
    }

    /**
     * sp 转 px (float)
     *
     * @param spValue 待转换值
     *
     * @return 转换后的值
     */
    public static float sp2pxf(final float spValue) {
        return sp2pxf(spValue, ResourceUtils.getDisplayMetrics());
    }

    /**
     * px 转 sp
     *
     * @param pxValue 待转换值
     *
     * @return 转换后的值
     */
    public static int px2sp(final float pxValue) {
        return px2sp(pxValue, ResourceUtils.getDisplayMetrics());
    }

    /**
     * px 转 sp (float)
     *
     * @param pxValue 待转换值
     *
     * @return 转换后的值
     */
    public static float px2spf(final float pxValue) {
        return px2spf(pxValue, ResourceUtils.getDisplayMetrics());
    }

    // ==================
    // = DisplayMetrics =
    // ==================

    /**
     * dp 转 px
     *
     * @param dpValue 待转换值
     * @param metrics {@link DisplayMetrics}
     *
     * @return 转换后的值
     */
    public static int dp2px(
        final float dpValue,
        final DisplayMetrics metrics
    ) {
        return (int) dp2pxf(dpValue, metrics);
    }

    /**
     * dp 转 px (float)
     *
     * @param dpValue 待转换值
     * @param metrics {@link DisplayMetrics}
     *
     * @return 转换后的值
     */
    public static float dp2pxf(
        final float dpValue,
        final DisplayMetrics metrics
    ) {
        return applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, metrics);
    }

    /**
     * px 转 dp
     *
     * @param pxValue 待转换值
     * @param metrics {@link DisplayMetrics}
     *
     * @return 转换后的值
     */
    public static int px2dp(
        final float pxValue,
        final DisplayMetrics metrics
    ) {
        return (int) px2dpf(pxValue, metrics);
    }

    /**
     * px 转 dp (float)
     *
     * @param pxValue 待转换值
     * @param metrics {@link DisplayMetrics}
     *
     * @return 转换后的值
     */
    public static float px2dpf(
        final float pxValue,
        final DisplayMetrics metrics
    ) {
        if (metrics != null) {
            try {
                float scale = metrics.density;
                return (pxValue / scale);
            } catch (Exception e) {
                LogUtils.e(e.getMessage());
            }
        }
        return 0f;
    }

    /**
     * sp 转 px
     *
     * @param spValue 待转换值
     * @param metrics {@link DisplayMetrics}
     *
     * @return 转换后的值
     */
    public static int sp2px(
        final float spValue,
        final DisplayMetrics metrics
    ) {
        return (int) sp2pxf(spValue, metrics);
    }

    /**
     * sp 转 px (float)
     *
     * @param spValue 待转换值
     * @param metrics {@link DisplayMetrics}
     *
     * @return 转换后的值
     */
    public static float sp2pxf(
        final float spValue,
        final DisplayMetrics metrics
    ) {
        return applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, metrics);
    }

    /**
     * px 转 sp
     *
     * @param pxValue 待转换值
     * @param metrics {@link DisplayMetrics}
     *
     * @return 转换后的值
     */
    public static int px2sp(
        final float pxValue,
        final DisplayMetrics metrics
    ) {
        return (int) px2spf(pxValue, metrics);
    }

    /**
     * px 转 sp (float)
     *
     * @param pxValue 待转换值
     * @param metrics {@link DisplayMetrics}
     *
     * @return 转换后的值
     */
    public static float px2spf(
        final float pxValue,
        final DisplayMetrics metrics
    ) {
        if (metrics != null) {
            try {
                float scale = metrics.scaledDensity;
                return (pxValue / scale);
            } catch (Exception e) {
                LogUtils.e(e.getMessage());
            }
        }
        return 0f;
    }

    // ==================
    // = applyDimension =
    // ==================

    /**
     * 各种单位转换 ( 该方法存在于 TypedValue.applyDimension )
     *
     * @param unit  单位
     * @param value 值
     *
     * @return 转换后的值
     */
    public static float applyDimension(
        final int unit,
        final float value
    ) {
        return applyDimension(unit, value, ResourceUtils.getDisplayMetrics());
    }

    /**
     * 各种单位转换 ( 该方法存在于 TypedValue.applyDimension )
     *
     * @param unit    单位
     * @param value   值
     * @param metrics {@link DisplayMetrics}
     *
     * @return 转换后的值
     */
    public static float applyDimension(
        final int unit,
        final float value,
        final DisplayMetrics metrics
    ) {
        if (metrics != null) {
            return TypedValue.applyDimension(unit, value, metrics);
        }
        return 0f;
    }

    // =

    /**
     * 在 onCreate 中获取视图的尺寸 ( 需回调 onGetSizeListener 接口, 在 onGetSize 中获取 View 宽高 )
     * <pre>
     *     用法示例如下所示
     *     <p></p>
     *     SizeUtils.forceGetViewSize(view, new SizeUtils.onGetSizeListener() {
     *          Override
     *          public void onGetSize(final View view) {
     *              view.getWidth();
     *          }
     *     });
     * </pre>
     *
     * @param view     {@link View}
     * @param listener {@link OnGetSizeListener}
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean forceGetViewSize(
        final View view,
        final OnGetSizeListener listener
    ) {
        if (view != null) {
            view.post(() -> {
                if (listener != null) {
                    listener.onGetSize(view);
                }
            });
            return true;
        }
        return false;
    }

    /**
     * 获取 View 宽高监听
     *
     * @author kayroc
     */
    public interface OnGetSizeListener {

        /**
         * 获取到 View 宽高监听通知
         *
         * @param view {@link View}
         */
        void onGetSize(View view);
    }

    // =============
    // = ViewUtils =
    // =============

    /**
     * 测量 View
     *
     * @param view {@link View}
     *
     * @return int[] 0 = 宽度, 1 = 高度
     */
    public static int[] measureView(final View view) {
        return WidgetUtils.measureView(view);
    }

    /**
     * 获取 View 的宽度
     *
     * @param view {@link View}
     *
     * @return View 的宽度
     */
    public static int getMeasuredWidth(final View view) {
        return WidgetUtils.getMeasuredWidth(view);
    }

    /**
     * 获取 View 的高度
     *
     * @param view {@link View}
     *
     * @return View 的高度
     */
    public static int getMeasuredHeight(final View view) {
        return WidgetUtils.getMeasuredHeight(view);
    }
}