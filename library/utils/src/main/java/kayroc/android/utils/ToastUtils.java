package kayroc.android.utils;

import android.widget.Toast;

import kayroc.android.ToolKitUtils;

/**
 * 简易的吐司工具类
 *
 * @author kayroc
 */
@SuppressWarnings("unused")
public final class ToastUtils {

    private ToastUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static Toast sToast;

    public static boolean isShow = true;

    /**
     * 显示 Toast
     */
    private static void show(
            CharSequence message,
            int time
    ) {
        if (isShow) {
            if (sToast != null) {
                sToast.cancel();
                sToast = null;
            }

            sToast = Toast.makeText(ToolKitUtils.getContext(), null, time);
            sToast.setText(message);
            sToast.show();
        }
    }

    /**
     * 短时间显示Toast
     */
    public static void showShort(CharSequence message) {
        show(message, Toast.LENGTH_SHORT);
    }

    /**
     * 长时间显示Toast
     */
    public static void showLong(CharSequence message) {
        show(message, Toast.LENGTH_LONG);
    }

    /**
     * 自定义显示Toast时间
     */
    public static void showCustom(
            CharSequence message,
            int duration
    ) {
        show(message, duration);
    }
}
