package kayroc.android.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;

import kayroc.android.ToolKitUtils;

/**
 * 剪贴板相关工具类
 *
 * @author kayroc
 */
@SuppressWarnings("unused")
public final class ClipboardUtils {

    private ClipboardUtils() {
    }

    /**
     * 复制文本到剪贴板
     *
     * @param text 文本
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean copyText(final CharSequence text) {
        try {
            ClipboardManager clipManager = AppUtils.getClipboardManager();
            // 复制的数据
            ClipData clipData = ClipData.newPlainText("text", text);
            // 设置复制的数据
            clipManager.setPrimaryClip(clipData);
            return true;
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return false;
    }

    /**
     * 获取剪贴板文本
     *
     * @return 剪贴板文本
     */
    public static CharSequence getText() {
        try {
            ClipboardManager clipManager = AppUtils.getClipboardManager();
            ClipData clipData = clipManager.getPrimaryClip();
            if (clipData != null && clipData.getItemCount() > 0) {
                return clipData.getItemAt(0).coerceToText(ToolKitUtils.getContext());
            }
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * 复制 URI 到剪贴板
     *
     * @param uri {@link Uri}
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean copyUri(final Uri uri) {
        try {
            ClipboardManager clipManager = AppUtils.getClipboardManager();
            // 复制的数据
            ClipData clipData = ClipData.newUri(
                ResourceUtils.getContentResolver(), "", uri
            );
            // 设置复制的数据
            clipManager.setPrimaryClip(clipData);
            return true;
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return false;
    }

    /**
     * 获取剪贴板 URI
     *
     * @return 剪贴板 URI
     */
    public static Uri getUri() {
        try {
            ClipboardManager clipManager = AppUtils.getClipboardManager();
            ClipData clipData = clipManager.getPrimaryClip();
            if (clipData != null && clipData.getItemCount() > 0) {
                return clipData.getItemAt(0).getUri();
            }
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * 复制意图到剪贴板
     *
     * @param intent {@link Intent}
     *
     * @return {@code true} success, {@code false} fail
     */
    public static boolean copyIntent(final Intent intent) {
        try {
            ClipboardManager clipManager = AppUtils.getClipboardManager();
            // 复制的数据
            ClipData clipData = ClipData.newIntent("intent", intent);
            // 设置复制的数据
            clipManager.setPrimaryClip(clipData);
            return true;
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return false;
    }

    /**
     * 获取剪贴板意图
     *
     * @return 剪贴板意图
     */
    public static Intent getIntent() {
        try {
            ClipboardManager clipManager = AppUtils.getClipboardManager();
            ClipData clipData = clipManager.getPrimaryClip();
            if (clipData != null && clipData.getItemCount() > 0) {
                return clipData.getItemAt(0).getIntent();
            }
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }
}