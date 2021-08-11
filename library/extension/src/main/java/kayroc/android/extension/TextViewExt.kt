package kayroc.android.extension

import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.widget.TextView

/**
 * 检查 TextView 中是否有内容
 * @receiver TextView
 * @return Boolean
 */
fun TextView.hasContent(): Boolean {
    return !TextUtils.isEmpty(text) || compoundDrawables.isNotEmpty()
}

// ====================================================================================
// =                              设置 ComponentDrawables                              =
// ====================================================================================

/**
 * 设置文本左侧图片
 * @receiver TextView
 * @param resId Int
 */
fun TextView.setDrawableLeft(resId: Int) {
    // val drawable = findDrawable(resId)
    // drawable?.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
    // this.setCompoundDrawables(drawable, null, null, null)

    setDrawableLeft(findDrawable(resId))
}

/**
 * 设置文本左侧图片
 * @receiver TextView
 * @param drawable Drawable?
 */
fun TextView.setDrawableLeft(drawable: Drawable?) {
    setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
}

/**
 * 设置文本右侧图片
 * @receiver TextView
 * @param resId Int
 */
fun TextView.setDrawableRight(resId: Int) {
    // val drawable = findDrawable(resId)
    // drawable?.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
    // setCompoundDrawables(null, null, drawable, null)

    setDrawableRight(findDrawable(resId))
}

/**
 * 设置文本右侧图片
 * @receiver TextView
 * @param drawable Drawable?
 */
fun TextView.setDrawableRight(drawable: Drawable?) {
    setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
}

/**
 * 设置文本上方图片
 * @receiver TextView
 * @param resId Int
 */
fun TextView.setDrawableTop(resId: Int) {
    // val drawable = findDrawable(resId)
    // drawable?.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
    // this.setCompoundDrawables(null, drawable, null, null)

    setDrawableTop(findDrawable(resId))
}

/**
 * 设置文本上方图片
 * @receiver TextView
 * @param drawable Drawable?
 */
fun TextView.setDrawableTop(drawable: Drawable?) {
    setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null)
}

/**
 * 设置文本下方图片
 * @receiver TextView
 * @param resId Int
 */
fun TextView.setDrawableBottom(resId: Int) {
    // val drawable = findDrawable(resId)
    // drawable?.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
    // setCompoundDrawables(null, null, null, drawable)

    setDrawableBottom(findDrawable(resId))
}

/**
 * 设置文本下方图片
 * @receiver TextView
 * @param drawable Drawable?
 */
fun TextView.setDrawableBottom(drawable: Drawable?) {
    setCompoundDrawablesWithIntrinsicBounds(null, null, null, drawable)
}

// =========================================================================================
// =                              修改 ComponentDrawables 大小                              =
// =========================================================================================


/**
 * 给TextView的drawable设置大小，Drawable如果不传的话会尝试使用TextView自己的Drawable
 * @receiver TextView
 * @param width Int
 * @param height Int
 * @param leftDrawable Int
 * @param topDrawable Int
 * @param rightDrawable Int
 * @param bottomDrawable Int
 * @return TextView
 */
fun TextView.sizeDrawable(
    width: Int, height: Int,
    leftDrawable: Int = 0, topDrawable: Int = 0,
    rightDrawable: Int = 0, bottomDrawable: Int = 0
): TextView {
    val rect = Rect(0, 0, width, height)
    setCompoundDrawables(
        findComponentDrawables(leftDrawable, 0, this)?.apply { bounds = rect },
        findComponentDrawables(topDrawable, 1, this)?.apply { bounds = rect },
        findComponentDrawables(rightDrawable, 2, this)?.apply { bounds = rect },
        findComponentDrawables(bottomDrawable, 3, this)?.apply { bounds = rect }
    )
    return this
}

/**
 * 给TextView的drawable设置大小，Drawable如果不传的话会尝试使用TextView自己的Drawable
 * @receiver TextView
 * @param size Int 同时作用于Drawable宽高
 * @param leftDrawable Int
 * @param topDrawable Int
 * @param rightDrawable Int
 * @param bottomDrawable Int
 * @return TextView
 */
fun TextView.sizeDrawable(
    size: Int,
    leftDrawable: Int = 0, topDrawable: Int = 0,
    rightDrawable: Int = 0, bottomDrawable: Int = 0
): TextView {
    sizeDrawable(size, size, leftDrawable, topDrawable, rightDrawable, bottomDrawable)
    return this
}

private fun findComponentDrawables(
    drawableRes: Int,
    index: Int,
    textView: TextView
): Drawable? {
    if (drawableRes != 0) return textView.findDrawable(drawableRes)
    if (textView.compoundDrawables.isNotEmpty()) return textView.compoundDrawables[index]
    return null
}