package kayroc.java.utils.encrypt;

import android.annotation.SuppressLint;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import kayroc.android.utils.LogUtils;

/**
 * 3DES 对称加密工具类
 *
 * @author kayroc
 * <pre>
 *     Triple DES、DESede 进行了三重 DES 加密的算法 ( 对称加密算法 )
 * </pre>
 */
@SuppressWarnings({"unused", "AlibabaClassNamingShouldBeCamel", "SpellCheckingInspection"})
public final class TripleDESUtils {

    private TripleDESUtils() {
    }

    /**
     * 生成密钥
     *
     * @return 密钥 byte[]
     */
    public static byte[] initKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("DESede");
            // 112 168
            keyGen.init(168);
            SecretKey secretKey = keyGen.generateKey();
            return secretKey.getEncoded();
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * 3DES 加密
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
            SecretKey secretKey = new SecretKeySpec(key, "DESede");
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * 3DES 解密
     *
     * @param data 待加密数据
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
            SecretKey secretKey = new SecretKeySpec(key, "DESede");
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }
}