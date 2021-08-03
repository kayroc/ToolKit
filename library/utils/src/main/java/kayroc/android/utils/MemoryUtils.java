package kayroc.android.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.os.Build;
import android.text.format.Formatter;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.FileReader;

import kayroc.android.ToolKitUtils;
import kayroc.java.utils.CloseUtils;

/**
 * 内存信息工具类
 *
 * @author kayroc
 */
@SuppressWarnings({"unused", "SpellCheckingInspection"})
public final class MemoryUtils {

    private MemoryUtils() {
    }

    /** 内存信息文件地址 */
    private static final String MEM_INFO_PATH = "/proc/meminfo";
    /** 获取内存总大小 */
    private static final String MEMTOTAL = "MemTotal";
    /** 获取可用内存 */
    private static final String MEMAVAILABLE = "MemAvailable";

    /**
     * 获取内存信息
     *
     * @return 内存信息
     */
    public static String printMemoryInfo() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(MEM_INFO_PATH), 4 * 1024);
            StringBuilder builder = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                builder.append(str);
            }
            return builder.toString();
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        } finally {
            CloseUtils.closeIOQuietly(br);
        }
        return null;
    }

    /**
     * 获取内存信息
     *
     * @return 内存信息
     */
    @SuppressLint("ObsoleteSdkInt")
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public static String printMemoryInfo2() {
        try {
            ActivityManager.MemoryInfo memoryInfo = getMemoryInfo();
            if (memoryInfo == null) {
                return null;
            }
            StringBuilder builder = new StringBuilder();
            builder.append("Memory: ");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                builder.append("\ntotalMem: ").append(memoryInfo.totalMem);
            }
            builder.append("\navailMem: ").append(memoryInfo.availMem);
            builder.append("\nlowMemory: ").append(memoryInfo.lowMemory);
            builder.append("\nthreshold: ").append(memoryInfo.threshold);
            return builder.toString();
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * 获取内存信息
     *
     * @return 内存信息
     */
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public static ActivityManager.MemoryInfo getMemoryInfo() {
        try {
            ActivityManager activityManager = AppUtils.getActivityManager();
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            return memoryInfo;
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    // =

    /**
     * 获取可用内存信息
     *
     * @return 可用内存信息
     */
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public static long getAvailMemory() {
        try {
            // 获取 android 当前可用内存大小
            ActivityManager activityManager = AppUtils.getActivityManager();
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            // 当前系统的可用内存
            return memoryInfo.availMem;
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return 0L;
    }

    /**
     * 获取可用内存信息 ( 格式化 )
     *
     * @return 可用内存信息
     */
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public static String getAvailMemoryFormat() {
        return Formatter.formatFileSize(ToolKitUtils.getContext(), getAvailMemory());
    }

    // =

    /**
     * 获取总内存大小
     *
     * @return 总内存大小
     */
    public static long getTotalMemory() {
        return getMemInfoType(MEMTOTAL);
    }

    /**
     * 获取总内存大小 ( 格式化 )
     *
     * @return 总内存大小
     */
    public static String getTotalMemoryFormat() {
        return Formatter.formatFileSize(ToolKitUtils.getContext(), getTotalMemory());
    }

    // =

    /**
     * 获取可用内存大小
     *
     * @return 可用内存大小
     */
    public static long getMemoryAvailable() {
        return getMemInfoType(MEMAVAILABLE);
    }

    /**
     * 获取可用内存大小 ( 格式化 )
     *
     * @return 可用内存大小
     */
    public static String getMemoryAvailableFormat() {
        return Formatter.formatFileSize(ToolKitUtils.getContext(), getMemoryAvailable());
    }

    /**
     * 通过不同 type 获取对应的内存信息
     *
     * @param type 内存类型
     *
     * @return 对应 type 内存信息
     */
    @SuppressWarnings("ConstantConditions")
    public static long getMemInfoType(final String type) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(MEM_INFO_PATH), 4 * 1024);
            String str;
            while ((str = br.readLine()) != null) {
                if (str.contains(type)) {
                    break;
                }
            }
            // 拆分空格、回车、换行等空白符
            String[] array = str.split("\\s+");
            // 获取系统总内存, 单位是 KB, 乘以 1024 转换为 Byte
            return Long.parseLong(array[1]) * 1024;
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        } finally {
            CloseUtils.closeIOQuietly(br);
        }
        return 0L;
    }
}