package kayroc.java.utils.encrypt;

import android.annotation.SuppressLint;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import kayroc.android.utils.LogUtils;


/**
 * AES 对称加密工具类
 *
 * @author kayroc
 * <pre>
 *     Advanced Encryption Standard 高级数据加密标准 ( 对称加密算法 )
 *     AES 算法可以有效抵制针对 DES 的攻击算法
 * </pre>
 */
@SuppressWarnings({"AlibabaClassNamingShouldBeCamel", "unused"})
public final class AESUtils {

    private AESUtils() {
    }

    /**
     * 生成密钥
     *
     * @return 密钥 byte[]
     */
    public static byte[] generateKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            // 192 256
            keyGen.init(256);
            SecretKey secretKey = keyGen.generateKey();
            return secretKey.getEncoded();
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * AES 加密
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
            SecretKey secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * AES 解密
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
            SecretKey secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }
}