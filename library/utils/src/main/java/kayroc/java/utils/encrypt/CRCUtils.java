package kayroc.java.utils.encrypt;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.zip.CRC32;

import kayroc.android.utils.LogUtils;
import kayroc.java.utils.CloseUtils;


/**
 * CRC 工具类
 *
 * @author kayroc
 * <pre>
 *     Cyclic Redundancy Check 循环冗余校验
 *     CRC 是一种根据网络数据包或电脑文件等数据产生简短固定位数校验码的一种散列函数
 * </pre>
 */
@SuppressWarnings({"AlibabaLowerCamelCaseVariableNaming", "unused", "AlibabaClassNamingShouldBeCamel"})
public final class CRCUtils {

    private CRCUtils() {
    }

    /**
     * 获取 CRC32 值
     *
     * @param data 字符串数据
     *
     * @return CRC32 long 值
     */
    public static long getCRC32(final String data) {
        if (data == null) {
            return -1L;
        }
        try {
            CRC32 crc32 = new CRC32();
            crc32.update(data.getBytes());
            return crc32.getValue();
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return -1L;
    }

    /**
     * 获取 CRC32 值
     *
     * @param data 字符串数据
     *
     * @return CRC32 字符串
     */
    public static String getCRC32ToHexString(final String data) {
        if (data == null) {
            return null;
        }
        try {
            CRC32 crc32 = new CRC32();
            crc32.update(data.getBytes());
            return Long.toHexString(crc32.getValue());
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * 获取文件 CRC32 值
     *
     * @param filePath 文件路径
     *
     * @return 文件 CRC32 值
     */
    public static String getFileCRC32(final String filePath) {
        if (filePath == null) {
            return null;
        }
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            byte[] buffer = new byte[1024];
            CRC32 crc32 = new CRC32();
            int numRead;
            while ((numRead = is.read(buffer)) > 0) {
                crc32.update(buffer, 0, numRead);
            }
            return Long.toHexString(crc32.getValue());
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        } finally {
            CloseUtils.closeIOQuietly(is);
        }
        return null;
    }
}