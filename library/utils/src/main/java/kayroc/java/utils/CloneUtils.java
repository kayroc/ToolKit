package kayroc.java.utils;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import kayroc.android.utils.LogUtils;

/**
 * 克隆工具类
 *
 * @author kayroc
 */
@SuppressWarnings("unused")
public final class CloneUtils {

    private CloneUtils() {
    }

    /**
     * 进行克隆
     *
     * @param data Object implements {@link Serializable}
     * @param <T>  泛型
     *
     * @return 克隆后的对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T deepClone(final Serializable data) {
        if (data == null) {
            return null;
        }
        return (T) ConvertUtils.bytesToObject(serializableToBytes(data));
    }

    /**
     * 通过序列化实体类, 获取对应的 byte[] 数据
     *
     * @param serializable Object implements {@link Serializable}
     *
     * @return 克隆后 byte[]
     */
    @SuppressWarnings("SpellCheckingInspection")
    public static byte[] serializableToBytes(final Serializable serializable) {
        if (serializable == null) {
            return null;
        }
        ObjectOutputStream oos = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(serializable);
            return baos.toByteArray();
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
            return null;
        } finally {
            CloseUtils.closeIOQuietly(oos);
        }
    }

    // =

    /**
     * 进行克隆
     *
     * @param map   存储集合
     * @param datas 需要克隆的数据源
     * @param <K>   key
     * @param <V>   value
     *
     * @return {@code true} success, {@code false} fail
     */
    @SuppressWarnings({"unchecked", "SpellCheckingInspection"})
    public static <K, V> boolean deepClone(
        final Map<K, V> map,
        final Map<K, V> datas
    ) {
        if (map != null && datas != null && datas.size() > 0) {
            Iterator<Map.Entry<K, V>> iterator = datas.entrySet().iterator();
            while (iterator.hasNext()) {
                try {
                    Map.Entry<K, V> entry = iterator.next();
                    // 获取 key
                    K key = entry.getKey();
                    // 克隆对象
                    V cloneObj = (V) ConvertUtils.bytesToObject(serializableToBytes((Serializable) entry.getValue()));
                    if (cloneObj != null) {
                        // 保存到集合
                        map.put(key, cloneObj);
                    }
                } catch (Exception e) {
                    LogUtils.e(e.getMessage());
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 进行克隆
     *
     * @param collection 存储集合
     * @param datas      需要克隆的数据源
     * @param <T>        泛型
     *
     * @return {@code true} success, {@code false} fail
     */
    @SuppressWarnings({"unchecked", "SpellCheckingInspection"})
    public static <T> boolean deepClone(
        final Collection<T> collection,
        final Collection<T> datas
    ) {
        if (collection != null && datas != null && datas.size() > 0) {
            Iterator<T> iterator = datas.iterator();
            while (iterator.hasNext()) {
                try {
                    // 克隆对象
                    T cloneObj = (T) ConvertUtils.bytesToObject(serializableToBytes((Serializable) iterator.next()));
                    if (cloneObj != null) {
                        collection.add(cloneObj);
                    }
                } catch (Exception e) {
                    LogUtils.e(e.getMessage());
                }
            }
            return true;
        }
        return false;
    }
}