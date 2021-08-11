package kayroc.android.extension.drawable

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.annotation.ColorInt

/**
 * Shape 相关
 * @author kayroc
 */

fun createGradientDrawable(
    shape: Int = GradientDrawable.RECTANGLE,
    width: Int = -1,
    height: Int = -1,
    @ColorInt fillColor: Int = Color.TRANSPARENT,
    cornerRadius: Float = 0f,
    cornerRadii: FloatArray = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f),
    strokeWidth: Int = -1,
    @ColorInt strokeColor: Int = Color.TRANSPARENT,
    dashWidth: Float = 0f,
    dashGap: Float = 0f,
    gradientColors: IntArray? = null,
    gradientOrientation: GradientDrawable.Orientation = GradientDrawable.Orientation.LEFT_RIGHT,
    gradientRadius: Float =  0.5f,
    gradientType: Int = GradientDrawable.LINEAR_GRADIENT,
    gradientCenterX: Float = 0.5f,
    gradientCenterY: Float = 0.5f
): GradientDrawable {
    return GradientDrawable().apply {
        setShape(shape)
        setSize(width, height)
        setColor(fillColor)
        if (cornerRadius != 0f) {
            setCornerRadius(cornerRadius)
        } else {
            setCornerRadii(cornerRadii)
        }
        setStroke(strokeWidth, strokeColor, dashWidth, dashGap)
        if (gradientColors != null) {
            colors = gradientColors
            orientation = gradientOrientation
            setGradientRadius(gradientRadius)
            setGradientType(gradientType)
            setGradientCenter(gradientCenterX, gradientCenterY)
        }
    }
}

fun createLineBg(
    strokeWidth: Int = -1,
    @ColorInt strokeColor: Int = Color.TRANSPARENT,
    dashWidth: Float = 0f,
    dashGap: Float = 0f
): GradientDrawable {
    return GradientDrawable().apply {
        shape = GradientDrawable.LINE
        setStroke(strokeWidth, strokeColor, dashWidth, dashGap)
    }
}

fun createRectangleBg(
    @ColorInt fillColor: Int = Color.TRANSPARENT,
    cornerRadius: Float = 0f,
    cornerRadii: FloatArray = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f),
    strokeWidth: Int = -1,
    @ColorInt strokeColor: Int = Color.TRANSPARENT,
    dashWidth: Float = 0f,
    dashGap: Float = 0f,
    width: Int = -1,
    height: Int = -1,
    gradientColors: IntArray? = null,
    gradientOrientation: GradientDrawable.Orientation = GradientDrawable.Orientation.LEFT_RIGHT,
    gradientRadius: Float =  0.5f,
    gradientType: Int = GradientDrawable.LINEAR_GRADIENT,
    gradientCenterX: Float = 0.5f,
    gradientCenterY: Float = 0.5f
): GradientDrawable {
    return GradientDrawable().apply {
        if (cornerRadius != 0f) {
            setCornerRadius(cornerRadius)
        } else {
            setCornerRadii(cornerRadii)
        }
        setColor(fillColor)
        setStroke(strokeWidth, strokeColor, dashWidth, dashGap)
        setSize(width, height)
        if (gradientColors != null) {
            colors = gradientColors
            orientation = gradientOrientation
            setGradientRadius(gradientRadius)
            setGradientType(gradientType)
            setGradientCenter(gradientCenterX, gradientCenterY)
        }
    }
}

fun createOvalBg(
    @ColorInt fillColor: Int = Color.TRANSPARENT,
    strokeWidth: Int = -1,
    @ColorInt strokeColor: Int = Color.TRANSPARENT,
    dashWidth: Float = 0f,
    dashGap: Float = 0f,
    width: Int = -1,
    height: Int = -1,
    gradientColors: IntArray? = null,
    gradientOrientation: GradientDrawable.Orientation = GradientDrawable.Orientation.LEFT_RIGHT,
    gradientRadius: Float =  0.5f,
    gradientType: Int = GradientDrawable.LINEAR_GRADIENT,
    gradientCenterX: Float = 0.5f,
    gradientCenterY: Float = 0.5f
): GradientDrawable {
    return GradientDrawable().apply {
        shape = GradientDrawable.OVAL
        setColor(fillColor)
        setStroke(strokeWidth, strokeColor, dashWidth, dashGap)
        setSize(width, height)
        if (gradientColors != null) {
            colors = gradientColors
            useLevel = false
            orientation = gradientOrientation
            setGradientRadius(gradientRadius)
            setGradientType(gradientType)
            setGradientCenter(gradientCenterX, gradientCenterY)
        }
    }
}