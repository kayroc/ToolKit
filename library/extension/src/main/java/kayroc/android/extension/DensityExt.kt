package kayroc.android.toolkit.extension

import android.content.res.Resources
import android.util.TypedValue

/**
 * 密度相关
 *
 * @author : kayroc
 * @date   : 2021/1/1
 */

private val metrics = Resources.getSystem().displayMetrics

/** dp */
val Float.dp: Float
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, metrics)

/** dp */
val Int.dp: Float
    get() = toFloat().dp

/** sp */
val Float.sp: Float
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this, metrics)

/** sp */
val Int.sp: Float
    get() = toFloat().sp

/** px */
val Number.px: Number
    get() = this

/** px 转 dp */
val Number.px2dp: Number
    get() = (this.toFloat() / metrics.density + 0.5f).toInt()

/** px 转 sp */
val Number.px2sp: Number
    get() = (this.toFloat() / metrics.scaledDensity + 0.5f).toInt()