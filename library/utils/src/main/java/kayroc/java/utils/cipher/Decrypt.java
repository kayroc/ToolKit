package kayroc.java.utils.cipher;

/**
 * 解密 ( 解码 ) 接口
 *
 * @author kayroc
 */
public interface Decrypt {

    /**
     * 解密 ( 解码 ) 方法
     *
     * @param data 待解码数据
     *
     * @return 解码后的 byte[]
     */
    byte[] decrypt(byte[] data);
}