package kayroc.android.extension.drawable

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import android.os.Build
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import kayroc.android.extension.findColor
import kayroc.android.extension.findDrawable
import kayroc.android.kotlin.R

/**
 * Ripple 相关
 *
 * @author kayroc
 */

/** 给 View 设置水波纹 */
fun View.ripple(
    rippleColor: Int = findColor(R.color.white20),
    contentDrawable: Drawable? = null,
    maskDrawable: Drawable? = null
) {
    background = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val colorStateList = ColorStateList(arrayOf(intArrayOf()), intArrayOf(rippleColor))
        val rippleDrawable = RippleDrawable(colorStateList, contentDrawable, maskDrawable)
        rippleDrawable
    } else {
        contentDrawable
    }
}

/** 给 View 设置水波纹 */
fun View.ripple(
    rippleColor: Int = findColor(R.color.white20),
    contentFillColor: Int = -1,
    contentCornerRadius: Float = 0f,
    contentStrokeWidth: Int = 0,
    contentStrokeColor: Int = Color.TRANSPARENT,
    maskFillColor: Int = -1,
    maskCornerRadius: Float = 0f,
    maskStrokeWidth: Int = 0,
    maskStrokeColor: Int = Color.TRANSPARENT
) {
    val contentDrawable = if (contentFillColor != -1) {
        createRectangleBg(
            fillColor = contentFillColor,
            cornerRadius = contentCornerRadius,
            strokeWidth = contentStrokeWidth,
            strokeColor = contentStrokeColor
        )
    } else null
    val maskDrawable = if (maskFillColor != -1) {
        createRectangleBg(
            fillColor = maskFillColor,
            cornerRadius = maskCornerRadius,
            strokeWidth = maskStrokeWidth,
            strokeColor = maskStrokeColor
        )
    } else null
    ripple(rippleColor, contentDrawable, maskDrawable)
}

/** 给 View 设置水波纹 */
fun View.rippleRes(
    rippleColor: Int = findColor(R.color.white20),
    @DrawableRes contentDrawableRes: Int = -1,
    @DrawableRes maskDrawableRes: Int = -1
) {
    val contentDrawable = if (contentDrawableRes != -1) {
        findDrawable(contentDrawableRes)
    } else null
    val maskDrawable = if (maskDrawableRes != -1) {
        findDrawable(maskDrawableRes)
    } else null
    ripple(rippleColor, contentDrawable, maskDrawable)
}

/** 给 ImageView 设置水波纹 */
fun ImageView.rippleSrc(
    rippleColor: Int = findColor(R.color.white20),
    @DrawableRes contentDrawableRes: Int = -1,
    @DrawableRes maskDrawableRes: Int = -1
) {
    val contentDrawable = if (contentDrawableRes != -1) {
        findDrawable(contentDrawableRes)
    } else null
    val maskDrawable = if (maskDrawableRes != -1) {
        findDrawable(maskDrawableRes)
    } else null
    rippleSrc(rippleColor, contentDrawable, maskDrawable)
}

/** 给 ImageView 设置水波纹 */
fun ImageView.rippleSrc(
    rippleColor: Int = findColor(R.color.white20),
    contentDrawable: Drawable? = null,
    maskDrawable: Drawable? = null
) {
    val colorStateList = ColorStateList(arrayOf(intArrayOf()), intArrayOf(rippleColor))
    val rippleDrawable = RippleDrawable(colorStateList, contentDrawable, maskDrawable)
    setImageDrawable(rippleDrawable)
}
