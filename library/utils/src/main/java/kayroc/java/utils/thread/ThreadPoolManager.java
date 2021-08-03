package kayroc.java.utils.thread;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 线程池管理工具类
 *
 * @author kayroc
 */
public final class ThreadPoolManager {

    private ThreadPoolManager() {
    }

    /** 默认通用线程池 ( 通过 CPU 自动处理 ) */
    private static final ThreadPool DEV_THREAD_POOL = new ThreadPool(ThreadPool.ThreadPoolType.CALC_CPU);
    /** 线程池数据 */
    private static final Map<String, ThreadPool> THREAD_POOL_MAP = new LinkedHashMap<>();
    /** 配置数据 */
    private static final Map<String, Object> CONFIG_MAPS = new HashMap<>();

    /**
     * 获取 ThreadManager 实例
     *
     * @param threadNumber 线程数量
     *
     * @return {@link ThreadPool}
     */
    public static synchronized ThreadPool getInstance(final int threadNumber) {
        // 初始化 key
        String key = "n_" + threadNumber;
        // 如果不为 null, 则直接返回
        ThreadPool threadPool = THREAD_POOL_MAP.get(key);
        if (threadPool != null) {
            return threadPool;
        }
        threadPool = new ThreadPool(threadNumber);
        THREAD_POOL_MAP.put(key, threadPool);
        return threadPool;
    }

    /**
     * 获取 ThreadManager 实例
     *
     * @param key 线程配置 key {@link ThreadPool.ThreadPoolType} or int-Integer
     *
     * @return {@link ThreadPool}
     */
    public static synchronized ThreadPool getInstance(final String key) {
        // 如果不为 null, 则直接返回
        ThreadPool threadPool = THREAD_POOL_MAP.get(key);
        if (threadPool != null) {
            return threadPool;
        }
        Object object = CONFIG_MAPS.get(key);
        if (object != null) {
            try {
                // 判断是否属于线程池类型
                if (object instanceof ThreadPool.ThreadPoolType) {
                    threadPool = new ThreadPool((ThreadPool.ThreadPoolType) object);
                } else if (object instanceof Integer) {
                    threadPool = new ThreadPool((Integer) object);
                } else { // 其他类型, 统一转换 Integer
                    threadPool = new ThreadPool(Integer.parseInt((String) object));
                }
                THREAD_POOL_MAP.put(key, threadPool);
                return threadPool;
            } catch (Exception e) {
                return DEV_THREAD_POOL;
            }
        }
        return DEV_THREAD_POOL;
    }

    // =

    /**
     * 初始化配置信息
     *
     * @param mapConfigs 线程配置信息 Map
     */
    public static void initConfig(final Map<String, Object> mapConfigs) {
        if (mapConfigs != null) {
            CONFIG_MAPS.putAll(mapConfigs);
        }
    }

    /**
     * 添加配置信息
     *
     * @param key   线程配置 key
     * @param value 线程配置 value
     */
    public static void putConfig(
        final String key,
        final Object value
    ) {
        CONFIG_MAPS.put(key, value);
    }

    /**
     * 移除配置信息
     *
     * @param key 线程配置 key
     */
    public static void removeConfig(final String key) {
        CONFIG_MAPS.remove(key);
    }
}