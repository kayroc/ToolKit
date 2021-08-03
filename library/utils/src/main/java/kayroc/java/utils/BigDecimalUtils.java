package kayroc.java.utils;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.math.BigInteger;

import kayroc.android.utils.LogUtils;

/**
 * 资金运算工具类
 *
 * @author kayroc
 * <pre>
 *     @see <a href="https://www.cnblogs.com/liqforstudy/p/5652517.html"/>
 *     @see <a href="https://blog.csdn.net/mo_cold_rain/article/details/81366310"/>
 * </pre>
 */
@SuppressWarnings("unused")
public final class BigDecimalUtils {

    private BigDecimalUtils() {
    }

    /** 小数点位数 */
    private static int NEW_SCALE = 10;
    /** 舍入模式 ( 默认向下取 ) */
    private static int ROUNDING_MODE = BigDecimal.ROUND_DOWN;

    /**
     * 设置全局小数点保留位数、舍入模式
     *
     * @param scale        小数点保留位数
     * @param roundingMode 舍入模式
     */
    public static void setScale(
        final int scale,
        final int roundingMode
    ) {
        NEW_SCALE = scale;
        ROUNDING_MODE = roundingMode;
    }

    /**
     * 获取 BigDecimal
     *
     * @param value Value
     *
     * @return {@link BigDecimal}
     */
    public static BigDecimal getBigDecimal(final Object value) {
        if (value == null) {
            return null;
        }
        try {
            if (value instanceof String) {
                return new BigDecimal((String) value);
            } else if (value instanceof Double) {
                return BigDecimal.valueOf((Double) value);
            } else if (value instanceof Long) {
                return new BigDecimal((Long) value);
            } else if (value instanceof Float) {
                return BigDecimal.valueOf((Float) value);
            } else if (value instanceof Integer) {
                return new BigDecimal((Integer) value);
            } else if (value instanceof BigInteger) {
                return new BigDecimal((BigInteger) value);
            } else if (value instanceof BigDecimal) {
                return (BigDecimal) value;
            }
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * 获取 Operation
     *
     * @param value Value
     *
     * @return {@link Operation}
     */
    public static Operation operation(final Object value) {
        return new Operation(value);
    }

    /**
     * 获取 Operation
     *
     * @param value  Value
     * @param config {@link Config}
     *
     * @return {@link Operation}
     */
    public static Operation operation(
        final Object value,
        final Config config
    ) {
        return new Operation(value, config);
    }

    // ========
    // = 包装类 =
    // ========

    /**
     * 计算异常
     *
     * @author kayroc
     */
    public static class CalculateException
        extends RuntimeException {
        public CalculateException() {
        }
    }

    /**
     * 配置信息
     *
     * @author kayroc
     */
    public static final class Config {

        // 小数点位数
        private final int mScale;
        // 舍入模式
        private final int mRoundingMode;

        public Config() {
            this(NEW_SCALE, ROUNDING_MODE);
        }

        /**
         * 初始化小数点保留位数、舍入模式
         *
         * @param scale        小数点保留位数
         * @param roundingMode 舍入模式
         */
        public Config(
            final int scale,
            final int roundingMode
        ) {
            this.mScale = scale;
            this.mRoundingMode = roundingMode;
        }

        /**
         * 获取小数点保留位数
         *
         * @return 小数点保留位数
         */
        public int getScale() {
            return mScale;
        }

        /**
         * 获取舍入模式
         *
         * @return 舍入模式
         */
        public int getRoundingMode() {
            return mRoundingMode;
        }
    }

    /**
     * BigDecimal 操作包装类
     *
     * @author kayroc
     */
    public static final class Operation {

        // 计算数值
        private BigDecimal mValue;
        // 配置信息
        private Config mConfig;
        // 是否抛出异常
        private boolean mThrowError = false;

        public Operation(final Object value) {
            this(value, null);
        }

        public Operation(
            final Object value,
            final Config config
        ) {
            this.mValue = BigDecimalUtils.getBigDecimal(value);
            this.mConfig = config;
        }

        // =

        /**
         * 检查 Value 是否为 null, 为 null 则抛出异常
         *
         * @return {@link Operation}
         *
         * @throws NullPointerException null 异常
         */
        public Operation requireNonNull() {
            if (mValue != null) {
                return this;
            }
            throw new NullPointerException("mValue is null");
        }

        /**
         * 内部抛出异常方法
         */
        private void throwException() {
            if (mThrowError) {
                throw new CalculateException();
            }
        }

        // ===========
        // = get/set =
        // ===========

        /**
         * 获取 Value
         *
         * @return {@link BigDecimal}
         */
        public BigDecimal getBigDecimal() {
            return mValue;
        }

        /**
         * 设置 Value
         *
         * @param value {@link BigDecimal}
         *
         * @return {@link Operation}
         */
        public Operation setBigDecimal(final Object value) {
            this.mValue = BigDecimalUtils.getBigDecimal(value);
            return this;
        }

        /**
         * 获取配置信息
         *
         * @return {@link Config}
         */
        public Config getConfig() {
            return mConfig;
        }

        /**
         * 设置配置信息
         *
         * @param config {@link Config}
         *
         * @return {@link Operation}
         */
        public Operation setConfig(final Config config) {
            return setConfig(config, true);
        }

        /**
         * 设置配置信息
         *
         * @param config {@link Config}
         * @param set    是否进行设置
         *
         * @return {@link Operation}
         */
        public Operation setConfig(
            final Config config,
            final boolean set
        ) {
            this.mConfig = config;
            if (set) {
                setScaleByConfig();
            }
            return this;
        }

        /**
         * 移除配置信息
         *
         * @return {@link Operation}
         */
        public Operation removeConfig() {
            return setConfig(null, false);
        }

        // =

        /**
         * 设置小数点保留位数、舍入模式
         *
         * @param scale        小数点保留位数
         * @param roundingMode 舍入模式
         *
         * @return {@link Operation}
         */
        public Operation setScale(
            final int scale,
            final int roundingMode
        ) {
            return setScale(new Config(scale, roundingMode));
        }

        /**
         * 设置小数点保留位数、舍入模式
         *
         * @param config {@link Config}
         *
         * @return {@link Operation}
         */
        public Operation setScale(final Config config) {
            if (mValue != null && config != null) {
                try {
                    mValue = mValue.setScale(
                        config.getScale(),
                        config.getRoundingMode()
                    );
                } catch (Exception e) {
                    LogUtils.e(e.getMessage());
                    throwException();
                }
            } else {
                throwException();
            }
            return this;
        }

        /**
         * 设置小数点保留位数、舍入模式
         *
         * @return {@link Operation}
         */
        @SuppressWarnings("UnusedReturnValue")
        public Operation setScaleByConfig() {
            return setScale(mConfig);
        }

        // =

        /**
         * 是否抛出异常
         *
         * @return {@code true} yes, {@code false} no
         */
        public boolean isThrowError() {
            return mThrowError;
        }

        /**
         * 设置是否抛出异常
         *
         * @param throwError 是否抛出异常
         *
         * @return {@link Operation}
         */
        public Operation setThrowError(final boolean throwError) {
            this.mThrowError = throwError;
            return this;
        }

        // ==========
        // = 获取方法 =
        // ==========

        /**
         * 克隆对象
         *
         * @return {@link Operation}
         */
        @NonNull
        @Override
        public Operation clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e) {
                LogUtils.e(e.getMessage());
            }
            return new Operation(mValue, mConfig);
        }

        /**
         * 获取此 BigDecimal 的字符串表示形式科学记数法
         *
         * @return 此 BigDecimal 的字符串表示形式科学记数法
         */
        @Override
        public String toString() {
            return mValue != null ? mValue.toString() : "";
        }

        /**
         * 获取此 BigDecimal 的字符串表示形式不带指数字段
         *
         * @return 此 BigDecimal 的字符串表示形式不带指数字段
         */
        public String toPlainString() {
            return (mValue != null) ? mValue.toPlainString() : null;
        }

        /**
         * 获取此 BigDecimal 的字符串表示形式工程计数法
         *
         * @return 此 BigDecimal 的字符串表示形式工程计数法
         */
        public String toEngineeringString() {
            return (mValue != null) ? mValue.toEngineeringString() : null;
        }

        /**
         * 获取指定类型值
         *
         * @return 指定类型值
         */
        public int intValue() {
            return (mValue != null) ? mValue.intValue() : 0;
        }

        /**
         * 获取指定类型值
         *
         * @return 指定类型值
         */
        public float floatValue() {
            return (mValue != null) ? mValue.floatValue() : 0F;
        }

        /**
         * 获取指定类型值
         *
         * @return 指定类型值
         */
        public long longValue() {
            return (mValue != null) ? mValue.longValue() : 0L;
        }

        /**
         * 获取指定类型值
         *
         * @return 指定类型值
         */
        public double doubleValue() {
            return (mValue != null) ? mValue.doubleValue() : 0D;
        }

        // =====
        // = 加 =
        // =====

        /**
         * 提供精确的加法运算
         *
         * @param value 加数
         *
         * @return {@link Operation}
         */
        public Operation add(final Object value) {
            BigDecimal bigDecimal = BigDecimalUtils.getBigDecimal(value);
            if (mValue != null && bigDecimal != null) {
                mValue = mValue.add(bigDecimal);
            } else {
                throwException();
            }
            return this;
        }

        // =====
        // = 减 =
        // =====

        /**
         * 提供精确的减法运算
         *
         * @param value 减数
         *
         * @return {@link Operation}
         */
        public Operation subtract(final Object value) {
            BigDecimal bigDecimal = BigDecimalUtils.getBigDecimal(value);
            if (mValue != null && bigDecimal != null) {
                mValue = mValue.subtract(bigDecimal);
            } else {
                throwException();
            }
            return this;
        }

        // =====
        // = 乘 =
        // =====

        /**
         * 提供精确的乘法运算
         *
         * @param value 乘数
         *
         * @return {@link Operation}
         */
        public Operation multiply(final Object value) {
            BigDecimal bigDecimal = BigDecimalUtils.getBigDecimal(value);
            if (mValue != null && bigDecimal != null) {
                mValue = mValue.multiply(bigDecimal);
            } else {
                throwException();
            }
            return this;
        }

        // =====
        // = 除 =
        // =====

        /**
         * 提供精确的除法运算
         *
         * @param value 除数
         *
         * @return {@link Operation}
         */
        public Operation divide(final Object value) {
            return divide(value, mConfig);
        }

        /**
         * 提供精确的除法运算
         *
         * @param value  除数
         * @param config {@link Config}
         *
         * @return {@link Operation}
         */
        public Operation divide(
            final Object value,
            final Config config
        ) {
            if (config != null) {
                return divide(value, config.getScale(), config.getRoundingMode());
            } else {
                return divide(value, NEW_SCALE, ROUNDING_MODE);
            }
        }

        /**
         * 提供精确的除法运算
         *
         * @param value        除数
         * @param scale        保留 scale 位小数
         * @param roundingMode 舍入模式
         *
         * @return {@link Operation}
         */
        public Operation divide(
            final Object value,
            final int scale,
            final int roundingMode
        ) {
            BigDecimal bigDecimal = BigDecimalUtils.getBigDecimal(value);
            if (mValue != null && bigDecimal != null) {
                try {
                    mValue = mValue.divide(bigDecimal, scale, roundingMode);
                } catch (Exception e) {
                    LogUtils.e(e.getMessage());
                    throwException();
                }
            } else {
                throwException();
            }
            return this;
        }

        // =======
        // = 取余 =
        // =======

        /**
         * 提供精确的取余运算
         *
         * @param value 除数
         *
         * @return {@link Operation}
         */
        public Operation remainder(final Object value) {
            BigDecimal bigDecimal = BigDecimalUtils.getBigDecimal(value);
            if (mValue != null && bigDecimal != null) {
                mValue = mValue.remainder(bigDecimal);
            } else {
                throwException();
            }
            return this;
        }

        // ==========
        // = 四舍五入 =
        // ==========

        /**
         * 提供精确的小数位四舍五入处理
         *
         * @return {@link Operation}
         */
        public Operation round() {
            return round(mConfig);
        }

        /**
         * 提供精确的小数位四舍五入处理
         *
         * @param config {@link Config}
         *
         * @return {@link Operation}
         */
        public Operation round(final Config config) {
            return divide(new BigDecimal("1"), config);
        }

        /**
         * 提供精确的小数位四舍五入处理
         *
         * @param scale        保留 scale 位小数
         * @param roundingMode 舍入模式
         *
         * @return {@link Operation}
         */
        public Operation round(
            final int scale,
            final int roundingMode
        ) {
            return divide(new BigDecimal("1"), scale, roundingMode);
        }

        // ==========
        // = 比较大小 =
        // ==========

        /**
         * 比较大小
         *
         * @param value 被比较的数字
         *
         * @return [1 = v1 > v2]、[-1 = v1 < v2]、[0 = v1 = v2]、[-2 = error]
         */
        public int compareTo(final Object value) {
            BigDecimal bigDecimal = BigDecimalUtils.getBigDecimal(value);
            if (mValue != null && bigDecimal != null) {
                try {
                    return mValue.compareTo(bigDecimal);
                } catch (Exception e) {
                    LogUtils.e(e.getMessage());
                    throwException();
                }
            } else {
                throwException();
            }
            return -2;
        }

        // ==========
        // = 金额分割 =
        // ==========

        /**
         * 金额分割, 四舍五入金额
         *
         * @return 指定格式处理的字符串
         */
        public String formatMoney() {
            return formatMoney(mConfig);
        }

        /**
         * 金额分割, 四舍五入金额
         *
         * @param splitNumber 拆分位数
         * @param splitSymbol 拆分符号
         *
         * @return 指定格式处理的字符串
         */
        public String formatMoney(
            final int splitNumber,
            final String splitSymbol
        ) {
            return formatMoney(mConfig, splitNumber, splitSymbol);
        }

        // =

        /**
         * 金额分割, 四舍五入金额
         *
         * @param config {@link Config}
         *
         * @return 指定格式处理的字符串
         */
        public String formatMoney(final Config config) {
            return formatMoney(config, 3, ",");
        }

        /**
         * 金额分割, 四舍五入金额
         *
         * @param config      {@link Config}
         * @param splitNumber 拆分位数
         * @param splitSymbol 拆分符号
         *
         * @return 指定格式处理的字符串
         */
        public String formatMoney(
            final Config config,
            final int splitNumber,
            final String splitSymbol
        ) {
            if (config != null) {
                return formatMoney(config.getScale(), config.getRoundingMode(), splitNumber, splitSymbol);
            } else {
                return formatMoney(NEW_SCALE, ROUNDING_MODE, splitNumber, splitSymbol);
            }
        }

        /**
         * 金额分割, 四舍五入金额
         *
         * @param scale       小数点后保留几位
         * @param mode        处理模式
         * @param splitNumber 拆分位数
         * @param splitSymbol 拆分符号
         *
         * @return 指定格式处理的字符串
         */
        public String formatMoney(
            final int scale,
            final int mode,
            final int splitNumber,
            final String splitSymbol
        ) {
            if (mValue == null) {
                return null;
            }
            try {
                // 如果等于 0, 直接返回
                if (mValue.doubleValue() == 0) {
                    return mValue.setScale(scale, mode).toPlainString();
                }
                // 获取原始值字符串 ( 非科学计数法 )
                String valuePlain = mValue.toPlainString();
                // 判断是否负数
                boolean isNegative = valuePlain.startsWith("-");
                // 处理后的数据
                BigDecimal bigDecimal = new BigDecimal(isNegative ? valuePlain.substring(1) : valuePlain);
                // 范围处理
                valuePlain = bigDecimal.setScale(scale, mode).toPlainString();
                // 进行拆分小数点处理
                String[] values = valuePlain.split("\\.");
                // 判断是否存在小数点
                boolean isDecimal = (values.length == 2);

                // 拼接符号
                String symbol = (splitSymbol != null) ? splitSymbol : "";
                // 防止出现负数
                int number = Math.max(splitNumber, 0);
                // 格式化数据 ( 拼接处理 )
                StringBuilder builder = new StringBuilder();
                // 进行处理小数点前的数值
                for (int len = values[0].length() - 1, i = len, splitPos = 1; i >= 0; i--) {
                    char ch = values[0].charAt(i);
                    builder.append(ch);
                    // 判断是否需要追加符号
                    if (number > 0 && splitPos % number == 0 && i != 0) {
                        builder.append(symbol);
                    }
                    splitPos++;
                }
                // 倒序处理
                builder.reverse();
                // 存在小数点, 则进行拼接
                if (isDecimal) {
                    builder.append(".").append(values[1]);
                }
                // 判断是否负数
                return isNegative ? "-" + builder.toString() : builder.toString();
            } catch (Exception e) {
                LogUtils.e(e.getMessage());
            }
            return null;
        }
    }

    // ==========
    // = 快捷方法 =
    // ==========

    // 异常值
    public static final double ERROR_VALUE = -123456.0D;

    // =====
    // = 加 =
    // =====

    /**
     * 提供精确的加法运算
     *
     * @param v1 被加数
     * @param v2 加数
     *
     * @return 两个参数的和
     */
    public static double add(
        final Object v1,
        final Object v2
    ) {
        return add(v1, v2, NEW_SCALE, ROUNDING_MODE);
    }

    /**
     * 提供精确的加法运算
     *
     * @param v1    被加数
     * @param v2    加数
     * @param scale 保留 scale 位小数
     *
     * @return 两个参数的和
     */
    public static double add(
        final Object v1,
        final Object v2,
        final int scale
    ) {
        return add(v1, v2, scale, ROUNDING_MODE);
    }

    /**
     * 提供精确的加法运算
     *
     * @param v1     被加数
     * @param v2     加数
     * @param config {@link Config}
     *
     * @return 两个参数的和
     */
    public static double add(
        final Object v1,
        final Object v2,
        final Config config
    ) {
        if (config != null) {
            return add(v1, v2, config.getScale(), config.getRoundingMode());
        } else {
            return add(v1, v2, NEW_SCALE, ROUNDING_MODE);
        }
    }

    /**
     * 提供精确的加法运算
     *
     * @param v1           被加数
     * @param v2           加数
     * @param scale        保留 scale 位小数
     * @param roundingMode 舍入模式
     *
     * @return 两个参数的和
     */
    public static double add(
        final Object v1,
        final Object v2,
        final int scale,
        final int roundingMode
    ) {
        try {
            return operation(v1).setThrowError(true)
                .add(v2).setScale(scale, roundingMode)
                .requireNonNull().doubleValue();
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return ERROR_VALUE;
    }

    // =====
    // = 减 =
    // =====

    /**
     * 提供精确的减法运算
     *
     * @param v1 被减数
     * @param v2 减数
     *
     * @return 两个参数的差
     */
    public static double subtract(
        final Object v1,
        final Object v2
    ) {
        return subtract(v1, v2, NEW_SCALE, ROUNDING_MODE);
    }

    /**
     * 提供精确的减法运算
     *
     * @param v1    被减数
     * @param v2    减数
     * @param scale 保留 scale 位小数
     *
     * @return 两个参数的差
     */
    public static double subtract(
        final Object v1,
        final Object v2,
        final int scale
    ) {
        return subtract(v1, v2, scale, ROUNDING_MODE);
    }

    /**
     * 提供精确的减法运算
     *
     * @param v1     被减数
     * @param v2     减数
     * @param config {@link Config}
     *
     * @return 两个参数的差
     */
    public static double subtract(
        final Object v1,
        final Object v2,
        final Config config
    ) {
        if (config != null) {
            return subtract(v1, v2, config.getScale(), config.getRoundingMode());
        } else {
            return subtract(v1, v2, NEW_SCALE, ROUNDING_MODE);
        }
    }

    /**
     * 提供精确的减法运算
     *
     * @param v1           被减数
     * @param v2           减数
     * @param scale        保留 scale 位小数
     * @param roundingMode 舍入模式
     *
     * @return 两个参数的差
     */
    public static double subtract(
        final Object v1,
        final Object v2,
        final int scale,
        final int roundingMode
    ) {
        try {
            return operation(v1).setThrowError(true)
                .subtract(v2).setScale(scale, roundingMode)
                .requireNonNull().doubleValue();
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return ERROR_VALUE;
    }

    // =====
    // = 乘 =
    // =====

    /**
     * 提供精确的乘法运算
     *
     * @param v1 被乘数
     * @param v2 乘数
     *
     * @return 两个参数的积
     */
    public static double multiply(
        final Object v1,
        final Object v2
    ) {
        return multiply(v1, v2, NEW_SCALE, ROUNDING_MODE);
    }

    /**
     * 提供精确的乘法运算
     *
     * @param v1    被乘数
     * @param v2    乘数
     * @param scale 保留 scale 位小数
     *
     * @return 两个参数的积
     */
    public static double multiply(
        final Object v1,
        final Object v2,
        final int scale
    ) {
        return multiply(v1, v2, scale, ROUNDING_MODE);
    }

    /**
     * 提供精确的乘法运算
     *
     * @param v1     被乘数
     * @param v2     乘数
     * @param config {@link Config}
     *
     * @return 两个参数的积
     */
    public static double multiply(
        final Object v1,
        final Object v2,
        final Config config
    ) {
        if (config != null) {
            return multiply(v1, v2, config.getScale(), config.getRoundingMode());
        } else {
            return multiply(v1, v2, NEW_SCALE, ROUNDING_MODE);
        }
    }

    /**
     * 提供精确的乘法运算
     *
     * @param v1           被乘数
     * @param v2           乘数
     * @param scale        保留 scale 位小数
     * @param roundingMode 舍入模式
     *
     * @return 两个参数的积
     */
    public static double multiply(
        final Object v1,
        final Object v2,
        final int scale,
        final int roundingMode
    ) {
        try {
            return operation(v1).setThrowError(true)
                .multiply(v2).setScale(scale, roundingMode)
                .requireNonNull().doubleValue();
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return ERROR_VALUE;
    }

    // =====
    // = 除 =
    // =====

    /**
     * 提供精确的除法运算
     *
     * @param v1 被除数
     * @param v2 除数
     *
     * @return 两个参数的商
     */
    public static double divide(
        final Object v1,
        final Object v2
    ) {
        return divide(v1, v2, NEW_SCALE, ROUNDING_MODE);
    }

    /**
     * 提供精确的除法运算
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 保留 scale 位小数
     *
     * @return 两个参数的商
     */
    public static double divide(
        final Object v1,
        final Object v2,
        final int scale
    ) {
        return divide(v1, v2, scale, ROUNDING_MODE);
    }

    /**
     * 提供精确的除法运算
     *
     * @param v1     被除数
     * @param v2     除数
     * @param config {@link Config}
     *
     * @return 两个参数的商
     */
    public static double divide(
        final Object v1,
        final Object v2,
        final Config config
    ) {
        if (config != null) {
            return divide(v1, v2, config.getScale(), config.getRoundingMode());
        } else {
            return divide(v1, v2, NEW_SCALE, ROUNDING_MODE);
        }
    }

    /**
     * 提供精确的除法运算
     *
     * @param v1           被除数
     * @param v2           除数
     * @param scale        保留 scale 位小数
     * @param roundingMode 舍入模式
     *
     * @return 两个参数的商
     */
    public static double divide(
        final Object v1,
        final Object v2,
        final int scale,
        final int roundingMode
    ) {
        try {
            return operation(v1).setThrowError(true)
                .divide(v2, scale, roundingMode)
                .requireNonNull().doubleValue();
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return ERROR_VALUE;
    }

    // =======
    // = 取余 =
    // =======

    /**
     * 提供精确的取余运算
     *
     * @param v1 被除数
     * @param v2 除数
     *
     * @return 两个参数的余数
     */
    public static double remainder(
        final Object v1,
        final Object v2
    ) {
        return remainder(v1, v2, NEW_SCALE, ROUNDING_MODE);
    }

    /**
     * 提供精确的取余运算
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 保留 scale 位小数
     *
     * @return 两个参数的余数
     */
    public static double remainder(
        final Object v1,
        final Object v2,
        final int scale
    ) {
        return remainder(v1, v2, scale, ROUNDING_MODE);
    }

    /**
     * 提供精确的取余运算
     *
     * @param v1     被除数
     * @param v2     除数
     * @param config {@link Config}
     *
     * @return 两个参数的余数
     */
    public static double remainder(
        final Object v1,
        final Object v2,
        final Config config
    ) {
        if (config != null) {
            return remainder(v1, v2, config.getScale(), config.getRoundingMode());
        } else {
            return remainder(v1, v2, NEW_SCALE, ROUNDING_MODE);
        }
    }

    /**
     * 提供精确的取余运算
     *
     * @param v1           被除数
     * @param v2           除数
     * @param scale        保留 scale 位小数
     * @param roundingMode 舍入模式
     *
     * @return 两个参数的余数
     */
    public static double remainder(
        final Object v1,
        final Object v2,
        final int scale,
        final int roundingMode
    ) {
        try {
            return operation(v1).setThrowError(true)
                .remainder(v2).setScale(scale, roundingMode)
                .requireNonNull().doubleValue();
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return ERROR_VALUE;
    }

    // ==========
    // = 四舍五入 =
    // ==========

    /**
     * 提供精确的小数位四舍五入处理
     *
     * @param v1 需要四舍五入的数值
     *
     * @return 四舍五入后的结果
     */
    public static double round(final Object v1) {
        return round(v1, NEW_SCALE, ROUNDING_MODE);
    }

    /**
     * 提供精确的小数位四舍五入处理
     *
     * @param v1    需要四舍五入的数值
     * @param scale 保留 scale 位小数
     *
     * @return 四舍五入后的结果
     */
    public static double round(
        final Object v1,
        final int scale
    ) {
        return round(v1, scale, ROUNDING_MODE);
    }

    /**
     * 提供精确的小数位四舍五入处理
     *
     * @param v1     需要四舍五入的数值
     * @param config {@link Config}
     *
     * @return 四舍五入后的结果
     */
    public static double round(
        final Object v1,
        final Config config
    ) {
        if (config != null) {
            return round(v1, config.getScale(), config.getRoundingMode());
        } else {
            return round(v1, NEW_SCALE, ROUNDING_MODE);
        }
    }

    /**
     * 提供精确的小数位四舍五入处理
     *
     * @param v1           需要四舍五入的数值
     * @param scale        保留 scale 位小数
     * @param roundingMode 舍入模式
     *
     * @return 四舍五入后的结果
     */
    public static double round(
        final Object v1,
        final int scale,
        final int roundingMode
    ) {
        try {
            return operation(v1).setThrowError(true)
                .round(scale, roundingMode)
                .requireNonNull().doubleValue();
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return ERROR_VALUE;
    }

    // ==========
    // = 比较大小 =
    // ==========

    /**
     * 比较大小
     *
     * @param v1 输入的数值
     * @param v2 被比较的数字
     *
     * @return [1 = v1 > v2]、[-1 = v1 < v2]、[0 = v1 = v2]、[-2 = error]
     */
    public static int compareTo(
        final Object v1,
        final Object v2
    ) {
        try {
            return operation(v1).setThrowError(true).compareTo(v2);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return -2;
    }
}