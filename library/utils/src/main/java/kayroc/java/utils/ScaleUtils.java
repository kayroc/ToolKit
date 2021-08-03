package kayroc.java.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kayroc.android.utils.LogUtils;

/**
 * 计算比例工具类
 *
 * @author kayroc
 */
@SuppressWarnings("unused")
public final class ScaleUtils {

    private ScaleUtils() {
    }

    /**
     * 计算比例 ( 商 )
     *
     * @param dividend 被除数
     * @param divisor  除数
     *
     * @return 商
     */
    public static double calcScale(
        final double dividend,
        final double divisor
    ) {
        try {
            return dividend / divisor;
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return -1d;
    }

    /**
     * 计算比例 ( 被除数 ( 最大值 ) / 除数 ( 最小值 ) )
     *
     * @param value1 第一个值
     * @param value2 第二个值
     *
     * @return 被除数 ( 最大值 ) / 除数 ( 最小值 ) = 商
     */
    public static double calcScaleToMath(
        final double value1,
        final double value2
    ) {
        try {
            return Math.max(value1, value2) / Math.min(value1, value2);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return -1d;
    }

    // =======
    // = int =
    // =======

    /**
     * 计算缩放比例 ( 根据宽度比例转换高度 )
     *
     * @param targetWidth   需要的最终宽度
     * @param currentWidth  当前宽度
     * @param currentHeight 当前高度
     *
     * @return int[] { 宽度, 高度 }
     */
    public static int[] calcScaleToWidth(
        final int targetWidth,
        final int currentWidth,
        final int currentHeight
    ) {
        try {
            if (currentWidth == 0) {
                return new int[]{0, 0};
            }
            // 计算比例
            float scale = ((float) targetWidth) / ((float) currentWidth);
            // 计算缩放后的高度
            int scaleHeight = (int) (scale * (float) currentHeight);
            // 返回对应的数据
            return new int[]{targetWidth, scaleHeight};
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * 计算缩放比例 ( 根据高度比例转换宽度 )
     *
     * @param targetHeight  需要的最终高度
     * @param currentWidth  当前宽度
     * @param currentHeight 当前高度
     *
     * @return int[] { 宽度, 高度 }
     */
    public static int[] calcScaleToHeight(
        final int targetHeight,
        final int currentWidth,
        final int currentHeight
    ) {
        try {
            if (currentHeight == 0) {
                return new int[]{0, 0};
            }
            // 计算比例
            float scale = ((float) targetHeight) / ((float) currentHeight);
            // 计算缩放后的宽度
            int scaleWidth = (int) (scale * (float) currentWidth);
            // 返回对应的数据
            return new int[]{scaleWidth, targetHeight};
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * 通过宽度、高度根据对应的比例, 转换成对应的比例宽度高度 ( 智能转换 )
     *
     * @param width       宽度
     * @param height      高度
     * @param widthScale  宽度比例
     * @param heightScale 高度比例
     *
     * @return int[] { 宽度, 高度 }
     */
    public static int[] calcWidthHeightToScale(
        final int width,
        final int height,
        final float widthScale,
        final float heightScale
    ) {
        try {
            // 如果宽度的比例, 大于等于高度比例
            if (widthScale >= heightScale) {
                // 以宽度为基准, 计算高度
                int scaleHeight = (int) (((float) width) * (heightScale / widthScale));
                // 返回对应的比例
                return new int[]{width, scaleHeight};
            } else { // 以高度为基准
                // 以宽度为基准, 计算宽度
                int scaleWidth = (int) (((float) height) * (widthScale / heightScale));
                // 返回对应的比例
                return new int[]{scaleWidth, height};
            }
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * 以宽度为基准, 转换对应比例的高度
     *
     * @param width       宽度
     * @param widthScale  宽度比例
     * @param heightScale 高度比例
     *
     * @return int[] { 宽度, 高度 }
     */
    public static int[] calcWidthToScale(
        final int width,
        final float widthScale,
        final float heightScale
    ) {
        try {
            // 以宽度为基准，计算高度
            int scaleHeight = (int) (((float) width) * (heightScale / widthScale));
            // 返回对应的比例
            return new int[]{width, scaleHeight};
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * 以高度为基准, 转换对应比例的宽度
     *
     * @param height      高度
     * @param widthScale  宽度比例
     * @param heightScale 高度比例
     *
     * @return int[] { 宽度, 高度 }
     */
    public static int[] calcHeightToScale(
        final int height,
        final float widthScale,
        final float heightScale
    ) {
        try {
            // 以高度为基准，计算宽度
            int scaleWidth = (int) (((float) height) * (widthScale / heightScale));
            // 返回对应的比例
            return new int[]{scaleWidth, height};
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    // ==========
    // = double =
    // ==========

    /**
     * 计算缩放比例 ( 根据宽度比例转换高度 )
     *
     * @param targetWidth   需要的最终宽度
     * @param currentWidth  当前宽度
     * @param currentHeight 当前高度
     *
     * @return double[] { 宽度, 高度 }
     */
    public static double[] calcScaleToWidth(
        final double targetWidth,
        final double currentWidth,
        final double currentHeight
    ) {
        try {
            if (currentWidth == 0d) {
                return new double[]{0d, 0d};
            }
            // 计算比例
            double scale = targetWidth / currentWidth;
            // 计算缩放后的高度
            double scaleHeight = scale * currentHeight;
            // 返回对应的数据
            return new double[]{targetWidth, scaleHeight};
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * 计算缩放比例 ( 根据高度比例转换宽度 )
     *
     * @param targetHeight  需要的最终高度
     * @param currentWidth  当前宽度
     * @param currentHeight 当前高度
     *
     * @return double[] { 宽度, 高度 }
     */
    public static double[] calcScaleToHeight(
        final double targetHeight,
        final double currentWidth,
        final double currentHeight
    ) {
        try {
            if (currentHeight == 0d) {
                return new double[]{0d, 0d};
            }
            // 计算比例
            double scale = targetHeight / currentHeight;
            // 计算缩放后的宽度
            double scaleWidth = scale * currentWidth;
            // 返回对应的数据
            return new double[]{scaleWidth, targetHeight};
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * 通过宽度、高度根据对应的比例, 转换成对应的比例宽度高度 ( 智能转换 )
     *
     * @param width       宽度
     * @param height      高度
     * @param widthScale  宽度比例
     * @param heightScale 高度比例
     *
     * @return double[] { 宽度, 高度 }
     */
    public static double[] calcWidthHeightToScale(
        final double width,
        final double height,
        final double widthScale,
        final double heightScale
    ) {
        try {
            // 如果宽度的比例, 大于等于高度比例
            if (widthScale >= heightScale) {
                // 以宽度为基准，计算高度
                double scaleHeight = width * (heightScale / widthScale);
                // 返回对应的比例
                return new double[]{width, scaleHeight};
            } else {
                // 以高度为基准，计算宽度
                double scaleWidth = height * (widthScale / heightScale);
                // 返回对应的比例
                return new double[]{scaleWidth, height};
            }
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * 以宽度为基准, 转换对应比例的高度
     *
     * @param width       宽度
     * @param widthScale  宽度比例
     * @param heightScale 高度比例
     *
     * @return double[] { 宽度, 高度 }
     */
    public static double[] calcWidthToScale(
        final double width,
        final double widthScale,
        final double heightScale
    ) {
        try {
            // 以宽度为基准，计算高度
            double scaleHeight = width * (heightScale / widthScale);
            // 返回对应的比例
            return new double[]{width, scaleHeight};
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * 以高度为基准, 转换对应比例的宽度
     *
     * @param height      高度
     * @param widthScale  宽度比例
     * @param heightScale 高度比例
     *
     * @return double[] { 宽度, 高度 }
     */
    public static double[] calcHeightToScale(
        final double height,
        final double widthScale,
        final double heightScale
    ) {
        try {
            // 以高度为基准，计算宽度
            double scaleWidth = height * (widthScale / heightScale);
            // 返回对应的比例
            return new double[]{scaleWidth, height};
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    // ========
    // = long =
    // ========

    /**
     * 计算缩放比例 ( 根据宽度比例转换高度 )
     *
     * @param targetWidth   需要的最终宽度
     * @param currentWidth  当前宽度
     * @param currentHeight 当前高度
     *
     * @return long[] { 宽度, 高度 }
     */
    public static long[] calcScaleToWidth(
        final long targetWidth,
        final long currentWidth,
        final long currentHeight
    ) {
        try {
            if (currentWidth == 0L) {
                return new long[]{0L, 0L};
            }
            // 计算比例
            long scale = targetWidth / currentWidth;
            // 计算缩放后的高度
            long scaleHeight = scale * currentHeight;
            // 返回对应的数据
            return new long[]{targetWidth, scaleHeight};
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * 计算缩放比例 ( 根据高度比例转换宽度 )
     *
     * @param targetHeight  需要的最终高度
     * @param currentWidth  当前宽度
     * @param currentHeight 当前高度
     *
     * @return long[] { 宽度, 高度 }
     */
    public static long[] calcScaleToHeight(
        final long targetHeight,
        final long currentWidth,
        final long currentHeight
    ) {
        try {
            if (currentHeight == 0L) {
                return new long[]{0L, 0L};
            }
            // 计算比例
            long scale = targetHeight / currentHeight;
            // 计算缩放后的宽度
            long scaleWidth = scale * currentWidth;
            // 返回对应的数据
            return new long[]{scaleWidth, targetHeight};
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * 通过宽度、高度根据对应的比例, 转换成对应的比例宽度高度 ( 智能转换 )
     *
     * @param width       宽度
     * @param height      高度
     * @param widthScale  宽度比例
     * @param heightScale 高度比例
     *
     * @return long[] { 宽度, 高度 }
     */
    public static long[] calcWidthHeightToScale(
        final long width,
        final long height,
        final long widthScale,
        final long heightScale
    ) {
        try {
            // 如果宽度的比例, 大于等于高度比例
            if (widthScale >= heightScale) {
                // 以宽度为基准，计算高度
                long scaleHeight = width * (heightScale / widthScale);
                // 返回对应的比例
                return new long[]{width, scaleHeight};
            } else {
                // 以高度为基准，计算宽度
                long scaleWidth = height * (widthScale / heightScale);
                // 返回对应的比例
                return new long[]{scaleWidth, height};
            }
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * 以宽度为基准, 转换对应比例的高度
     *
     * @param width       宽度
     * @param widthScale  宽度比例
     * @param heightScale 高度比例
     *
     * @return long[] { 宽度, 高度 }
     */
    public static long[] calcWidthToScale(
        final long width,
        final long widthScale,
        final long heightScale
    ) {
        try {
            // 以宽度为基准，计算高度
            long scaleHeight = width * (heightScale / widthScale);
            // 返回对应的比例
            return new long[]{width, scaleHeight};
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * 以高度为基准, 转换对应比例的宽度
     *
     * @param height      高度
     * @param widthScale  宽度比例
     * @param heightScale 高度比例
     *
     * @return long[] { 宽度, 高度 }
     */
    public static long[] calcHeightToScale(
        final long height,
        final long widthScale,
        final long heightScale
    ) {
        try {
            // 以高度为基准，计算宽度
            long scaleWidth = height * (widthScale / heightScale);
            // 返回对应的比例
            return new long[]{scaleWidth, height};
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    // =========
    // = float =
    // =========

    /**
     * 计算缩放比例 ( 根据宽度比例转换高度 )
     *
     * @param targetWidth   需要的最终宽度
     * @param currentWidth  当前宽度
     * @param currentHeight 当前高度
     *
     * @return float[] { 宽度, 高度 }
     */
    public static float[] calcScaleToWidth(
        final float targetWidth,
        final float currentWidth,
        final float currentHeight
    ) {
        try {
            if (currentWidth == 0f) {
                return new float[]{0f, 0f};
            }
            // 计算比例
            float scale = targetWidth / currentWidth;
            // 计算缩放后的高度
            float scaleHeight = scale * currentHeight;
            // 返回对应的数据
            return new float[]{targetWidth, scaleHeight};
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * 计算缩放比例 ( 根据高度比例转换宽度 )
     *
     * @param targetHeight  需要的最终高度
     * @param currentWidth  当前宽度
     * @param currentHeight 当前高度
     *
     * @return float[] { 宽度, 高度 }
     */
    public static float[] calcScaleToHeight(
        final float targetHeight,
        final float currentWidth,
        final float currentHeight
    ) {
        try {
            if (currentHeight == 0f) {
                return new float[]{0f, 0f};
            }
            // 计算比例
            float scale = targetHeight / currentHeight;
            // 计算缩放后的宽度
            float scaleWidth = scale * currentWidth;
            // 返回对应的数据
            return new float[]{scaleWidth, targetHeight};
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * 通过宽度、高度根据对应的比例, 转换成对应的比例宽度高度 ( 智能转换 )
     *
     * @param width       宽度
     * @param height      高度
     * @param widthScale  宽度比例
     * @param heightScale 高度比例
     *
     * @return float[] { 宽度, 高度 }
     */
    public static float[] calcWidthHeightToScale(
        final float width,
        final float height,
        final float widthScale,
        final float heightScale
    ) {
        try {
            // 如果宽度的比例, 大于等于高度比例
            if (widthScale >= heightScale) {
                // 以宽度为基准，计算高度
                float scaleHeight = width * (heightScale / widthScale);
                // 返回对应的比例
                return new float[]{width, scaleHeight};
            } else {
                // 以高度为基准，计算宽度
                float scaleWidth = height * (widthScale / heightScale);
                // 返回对应的比例
                return new float[]{scaleWidth, height};
            }
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * 以宽度为基准, 转换对应比例的高度
     *
     * @param width       宽度
     * @param widthScale  宽度比例
     * @param heightScale 高度比例
     *
     * @return float[] { 宽度, 高度 }
     */
    public static float[] calcWidthToScale(
        final float width,
        final float widthScale,
        final float heightScale
    ) {
        try {
            // 以宽度为基准，计算高度
            float scaleHeight = width * (heightScale / widthScale);
            // 返回对应的比例
            return new float[]{width, scaleHeight};
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * 以高度为基准, 转换对应比例的宽度
     *
     * @param height      高度
     * @param widthScale  宽度比例
     * @param heightScale 高度比例
     *
     * @return float[] { 宽度, 高度 }
     */
    public static float[] calcHeightToScale(
        final float height,
        final float widthScale,
        final float heightScale
    ) {
        try {
            // 以高度为基准，计算宽度
            float scaleWidth = height * (widthScale / heightScale);
            // 返回对应的比例
            return new float[]{scaleWidth, height};
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    // ========
    // = XY 比 =
    // ========

    /**
     * 计算 XY 比
     *
     * @param x X 值
     * @param y Y 值
     *
     * @return XY 比实体类
     */
    public static XY calcXY(
        final int x,
        final int y
    ) {
        return calcXY(XY_LIST, x, y);
    }

    /**
     * 计算 XY 比
     *
     * @param xyLists XY 比集合
     * @param x       X 值
     * @param y       Y 值
     *
     * @return XY 比实体类
     */
    public static XY calcXY(
        final List<XY> xyLists,
        final int x,
        final int y
    ) {
        if (xyLists != null && xyLists.size() != 0) {
            List<XY> lists = new ArrayList<>(xyLists);
            Collections.sort(lists);
            double scale = calcScale(x, y);
            for (int i = 0, len = lists.size(); i < len; i++) {
                XY xy = lists.get(i);
                if (scale >= xy.scale) {
                    return xy;
                }
            }
        }
        return null;
    }

    // ========
    // = 实体类 =
    // ========

    public static final List<XY> XY_LIST;

    static {
        List<XY> xys = new ArrayList<>();
        xys.add(new XY(16, 9));
        xys.add(new XY(17, 10));
        xys.add(new XY(15, 9));
        xys.add(new XY(16, 10));
        xys.add(new XY(3, 2));
        xys.add(new XY(4, 3));
        xys.add(new XY(5, 4));
        xys.add(new XY(1, 1));
        XY_LIST = Collections.unmodifiableList(xys);
    }

    /**
     * XY 比实体类
     *
     * @author kayroc
     */
    @SuppressWarnings("AlibabaClassNamingShouldBeCamel")
    public static class XY
        implements Comparable<XY> {

        public XY(
            final int x,
            final int y
        ) {
            this(x, y, 0);
        }

        public XY(
            final int x,
            final int y,
            final int type
        ) {
            this.x = x;
            this.y = y;
            this.scale = calcScale(x, y);
            this.type = type;
        }

        public final int x;
        public final int y;
        public final double scale;
        public final int type;

        public String getXYx() {
            return getXY("x");
        }

        public String getXY() {
            return getXY(":");
        }

        public String getXY(String joint) {
            return x + joint + y;
        }

        @Override
        public int compareTo(XY xy) {
            return Double.compare(xy.scale, this.scale);
        }
    }
}