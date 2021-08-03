package kayroc.android.utils;

import android.net.Uri;

import androidx.core.content.FileProvider;

import java.io.File;

import kayroc.android.ToolKitUtils;

/**
 * Android 7.0 File Provider 适配相关
 *
 * @author kayroc
 */
public final class FileProviderUtils {

    public FileProviderUtils() {
    }

    public static final String FILE_PROVIDER = "file.provider";

    /**
     * Android N 以上获取文件 Uri (通过 FileProvider)
     */
    public static Uri getUriForFile(File file) {
        return ToolKitFileProvider.getUriForFile(ToolKitUtils.getContext(), getAuthority(), file);
    }

    /**
     * 获取本应用 FileProvider 授权
     * <p> UE 会自动以 {@code PackageName + ".file.provider"} 形式为 FileProvider 获取授权</p>
     *
     * @return 包名.file.provider
     */
    public static String getAuthority() {
        return AppUtils.getPackageName() + "." + FILE_PROVIDER;
    }

    public static class ToolKitFileProvider extends FileProvider {
    }
}