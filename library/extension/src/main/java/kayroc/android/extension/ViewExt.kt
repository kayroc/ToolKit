package kayroc.android.extension

import android.view.View
import android.view.ViewGroup

/**
 * View 扩展
 *
 * @author : kayroc
 * @date   : 2021/1/27
 */

/** 所有子 View */
inline val ViewGroup.children
    get() = (0 until childCount).map { getChildAt(it) }

// =====================================================================
// =                              点击事件                              =
// =====================================================================

/** 设置点击监听, 并实现事件节流 */
var _viewClickFlag = false
var _clickRunnable = Runnable { _viewClickFlag = false }
fun View.click(action: (view: View) -> Unit) {
    setOnClickListener {
        if (!_viewClickFlag) {
            _viewClickFlag = true
            action(it)
        }
        removeCallbacks(_clickRunnable)
        postDelayed(_clickRunnable, 350)
    }
}

/** 设置长按监听 */
fun View.longClick(action: (view: View) -> Boolean) {
    setOnLongClickListener {
        action(it)
    }
}

// ====================================================================
// =                              内外边距                              =
// ====================================================================

/**
 * 设置 View 的 padding
 *
 * @param leftPadding 左内边距
 * @param topPadding 上内边距
 * @param rightPadding 右内边距
 * @param bottomPadding 下内边距
 */
fun View.padding(
    leftPadding: Int = Int.MAX_VALUE,
    topPadding: Int = Int.MAX_VALUE,
    rightPadding: Int = Int.MAX_VALUE,
    bottomPadding: Int = Int.MAX_VALUE
): View {
    if (leftPadding != Int.MAX_VALUE)
        setPadding(leftPadding, paddingTop, paddingRight, paddingBottom)
    if (topPadding != Int.MAX_VALUE)
        setPadding(paddingLeft, topPadding, paddingRight, paddingBottom)
    if (rightPadding != Int.MAX_VALUE)
        setPadding(paddingLeft, paddingTop, rightPadding, paddingBottom)
    if (bottomPadding != Int.MAX_VALUE)
        setPadding(paddingLeft, paddingTop, paddingRight, bottomPadding)
    return this
}

/**
 * 设置 View 的 padding
 *
 * @param startPadding 左内边距
 * @param topPadding 上内边距
 * @param endPadding 右内边距
 * @param bottomPadding 下内边距
 */
fun View.paddingRelative(
    startPadding: Int = Int.MAX_VALUE,
    topPadding: Int = Int.MAX_VALUE,
    endPadding: Int = Int.MAX_VALUE,
    bottomPadding: Int = Int.MAX_VALUE
): View {
    if (startPadding != Int.MAX_VALUE)
        setPaddingRelative(startPadding, paddingTop, paddingRight, paddingBottom)
    if (topPadding != Int.MAX_VALUE)
        setPaddingRelative(paddingLeft, topPadding, paddingRight, paddingBottom)
    if (endPadding != Int.MAX_VALUE)
        setPaddingRelative(paddingLeft, paddingTop, endPadding, paddingBottom)
    if (bottomPadding != Int.MAX_VALUE)
        setPaddingRelative(paddingLeft, paddingTop, paddingRight, bottomPadding)
    return this
}


/**
 * 设置 View 的 margin
 *
 * @param margin 外边距
 */
fun View.margin(
    margin: Int = Int.MAX_VALUE
): View {
    val params = layoutParams as ViewGroup.MarginLayoutParams
    if (margin != Int.MAX_VALUE) {
        params.leftMargin = margin
        params.topMargin = margin
        params.rightMargin = margin
        params.bottomMargin = margin
    }
    layoutParams = params
    return this
}

/**
 * 设置 View 的横向外边距
 *
 */
fun View.horizontalMargin(
    margin: Int = Int.MAX_VALUE
): View {
    val params = layoutParams as ViewGroup.MarginLayoutParams
    if (margin != Int.MAX_VALUE) {
        params.leftMargin = margin
        params.rightMargin = margin
    }
    layoutParams = params
    return this
}

/**
 * 设置 View 的纵向外边距
 *
 */
fun View.verticalMargin(
    margin: Int = Int.MAX_VALUE
): View {
    val params = layoutParams as ViewGroup.MarginLayoutParams
    if (margin != Int.MAX_VALUE) {
        params.topMargin = margin
        params.bottomMargin = margin
    }
    layoutParams = params
    return this
}


/** 上外边距 */
var View.topMargin: Int
    get():Int {
        return (layoutParams as ViewGroup.MarginLayoutParams).topMargin
    }
    set(value) {
        (layoutParams as ViewGroup.MarginLayoutParams).topMargin = value
    }

/** 下外边距 */
var View.bottomMargin: Int
    get():Int = (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin
    set(value) {
        (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin = value
    }

/** 左外边距 */
var View.leftMargin: Int
    get():Int {
        return (layoutParams as ViewGroup.MarginLayoutParams).leftMargin
    }
    set(value) {
        (layoutParams as ViewGroup.MarginLayoutParams).leftMargin = value
    }

/** 右外边距 */
var View.rightMargin: Int
    get():Int {
        return (layoutParams as ViewGroup.MarginLayoutParams).rightMargin
    }
    set(value) {
        (layoutParams as ViewGroup.MarginLayoutParams).rightMargin = value
    }

// ===================================================================
// =                              可见性                              =
// ===================================================================

/** View 可见 */
fun View.visible() {
    visibility = View.VISIBLE
}

/** View 不可见(不占位置) */
fun View.gone() {
    visibility = View.GONE
}

/** View 不可见(占位置) */
fun View.invisible() {
    visibility = View.INVISIBLE
}

/** View 是否可见 */
val View.isVisible: Boolean
    get() {
        return visibility == View.VISIBLE
    }

/** View 是否不可见(不占位置) */
val View.isGone: Boolean
    get() {
        return visibility == View.GONE
    }

/** View 是否不可见(占位置) */
val View.isInvisible: Boolean
    get() {
        return visibility == View.INVISIBLE
    }

/** 切换 View 的可见性 */
fun View.toggleVisibility() {
    visibility = if (visibility == View.GONE) View.VISIBLE else View.GONE
}

// ===================================================================
// =                              可用性                              =
// ===================================================================

/** 设置 View 不可用 */
fun View.disable(value: Float = 0.5f) {
    isEnabled = false
    alpha = value
}

/** 设置所有子 View 不可用 */
fun View.disableAll() {
    isEnabled = false
    alpha = 0.55f
    if (this is ViewGroup) {
        children.forEach {
            it.disableAll()
        }
    }
}

/** 设置 View 可用 */
fun View.enable() {
    isEnabled = true
    alpha = 1f
}

/** 设置所有子 View 可用 */
fun View.enableAll() {
    isEnabled = true
    alpha = 1f
    if (this is ViewGroup) {
        children.forEach {
            it.enableAll()
        }
    }
}

// =================================================================
// =                              宽高                              =
// =================================================================

/** 设置View的宽度 */
fun View.width(width: Int): View {
    val params = layoutParams ?: ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT)
    params.width = width
    layoutParams = params
    return this
}

/** 设置View的高度 */
fun View.height(height: Int): View {
    val params = layoutParams ?: ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT)
    params.height = height
    layoutParams = params
    return this
}

/** 设置View的宽和高 */
fun View.widthAndHeight(width: Int, height: Int): View {
    val params = layoutParams ?: ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    params.width = width
    params.height = height
    layoutParams = params
    return this
}