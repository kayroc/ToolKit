package kayroc.java.utils.encrypt;

import android.annotation.SuppressLint;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import kayroc.android.utils.LogUtils;

/**
 * DES 对称加密工具类
 *
 * @author kayroc
 * <pre>
 *     Data Encryption Standard 数据加密标准 ( 对称加密算法 )
 * </pre>
 */
@SuppressWarnings({"AlibabaLowerCamelCaseVariableNaming", "unused", "SpellCheckingInspection", "AlibabaClassNamingShouldBeCamel"})
public final class DESUtils {

    private DESUtils() {
    }

    /**
     * 获取可逆算法 DES 的密钥
     *
     * @param key 前八个字节将被用来生成密钥
     *
     * @return 可逆算法 DES 的密钥
     */
    public static Key getDESKey(final byte[] key) {
        if (key == null) {
            return null;
        }
        try {
            DESKeySpec desKey = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            return keyFactory.generateSecret(desKey);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * DES 加密
     *
     * @param data 待加密数据
     * @param key  密钥
     *
     * @return 加密后的 byte[]
     */
    @SuppressLint("GetInstance")
    public static byte[] encrypt(
        final byte[] data,
        final byte[] key
    ) {
        if (data == null || key == null) {
            return null;
        }
        try {
            SecretKey secretKey = new SecretKeySpec(key, "DES");
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * DES 解密
     *
     * @param data 待解密数据
     * @param key  密钥
     *
     * @return 解密后的 byte[]
     */
    @SuppressLint("GetInstance")
    public static byte[] decrypt(
        final byte[] data,
        final byte[] key
    ) {
        if (data == null || key == null) {
            return null;
        }
        try {
            SecretKey secretKey = new SecretKeySpec(key, "DES");
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }
}