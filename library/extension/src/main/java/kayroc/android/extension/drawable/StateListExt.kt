package kayroc.android.extension.drawable

import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import kayroc.android.extension.*

/**
 * StateListDrawable 相关
 *
 * @author : kayroc
 * @date   : 2021/2/13
 */

// ===============================================================================
// =                              StateListDrawable                              =
// ===============================================================================

/**
 * 激活状态
 * @receiver StateListDrawable
 * @param drawable Drawable?
 * @param reversed Boolean
 * @return StateListDrawable
 */
fun StateListDrawable.active(
    drawable: Drawable? = null,
    reversed: Boolean = true
): StateListDrawable {
    if (reversed) {
        addState(intArrayOf(android.R.attr.state_active), drawable)
    } else {
        addState(intArrayOf(-android.R.attr.state_active), drawable)
    }
    return this
}

/**
 * 勾选状态
 * @receiver StateListDrawable
 * @param drawable Drawable?
 * @param reversed Boolean
 * @return StateListDrawable
 */
fun StateListDrawable.checked(
    drawable: Drawable? = null,
    reversed: Boolean = true
): StateListDrawable {
    if (reversed) {
        addState(intArrayOf(android.R.attr.state_checked), drawable)
    } else {
        addState(intArrayOf(-android.R.attr.state_checked), drawable)
    }
    return this
}

/**
 * 可用状态
 * @receiver StateListDrawable
 * @param drawable Drawable?
 * @param reversed Boolean
 * @return StateListDrawable
 */
fun StateListDrawable.enable(
    drawable: Drawable? = null,
    reversed: Boolean = true
): StateListDrawable {
    if (reversed) {
        addState(intArrayOf(android.R.attr.state_enabled), drawable)
    } else {
        addState(intArrayOf(-android.R.attr.state_enabled), drawable)
    }
    return this
}

/**
 * 焦点状态
 * @receiver StateListDrawable
 * @param drawable Drawable?
 * @param reversed Boolean
 * @return StateListDrawable
 */
fun StateListDrawable.focused(
    drawable: Drawable? = null,
    reversed: Boolean = true
): StateListDrawable {
    if (reversed) {
        addState(intArrayOf(android.R.attr.state_focused), drawable)
    } else {
        addState(intArrayOf(-android.R.attr.state_focused), drawable)
    }
    return this
}

/**
 * 按下状态
 * @receiver StateListDrawable
 * @param drawable Drawable?
 * @param reversed Boolean
 * @return StateListDrawable
 */
fun StateListDrawable.pressed(
    drawable: Drawable? = null,
    reversed: Boolean = true
): StateListDrawable {
    if (reversed) {
        addState(intArrayOf(android.R.attr.state_pressed), drawable)
    } else {
        addState(intArrayOf(-android.R.attr.state_pressed), drawable)
    }
    return this
}

/**
 * 选中状态
 * @receiver StateListDrawable
 * @param drawable Drawable?
 * @param reversed Boolean
 * @return StateListDrawable
 */
fun StateListDrawable.selected(
    drawable: Drawable? = null,
    reversed: Boolean = true
): StateListDrawable {
    if (reversed) {
        addState(intArrayOf(android.R.attr.state_selected), drawable)
    } else {
        addState(intArrayOf(-android.R.attr.state_selected), drawable)
    }
    return this
}

/**
 * 正常状态
 * @receiver StateListDrawable
 * @param drawable Drawable?
 * @return StateListDrawable
 */
fun StateListDrawable.normal(drawable: Drawable? = null): StateListDrawable {
    addState(intArrayOf(), drawable)
    return this
}

// ========================================================================
// =                              点击状态背景                              =
// ========================================================================

fun View.pressedBg(
    pressedDrawable: Drawable? = null,
    normalDrawable: Drawable? = null,
    disableDrawable: Drawable? = null
) {
    background = StateListDrawable()
        .enable(disableDrawable, false)
        .pressed(pressedDrawable)
        .normal(normalDrawable)
}

fun View.pressedBg(
    @DrawableRes pressedRes: Int,
    @DrawableRes normalRes: Int,
    @DrawableRes disableRes: Int
) {
    pressedBg(findDrawable(pressedRes), findDrawable(normalRes), findDrawable(disableRes))
}

fun ImageView.pressedSrc(
    pressedDrawable: Drawable? = null,
    normalDrawable: Drawable? = null,
    disableDrawable: Drawable? = null
) {
    setImageDrawable(
        StateListDrawable()
            .enable(disableDrawable, false)
            .pressed(pressedDrawable)
            .normal(normalDrawable)
    )
}

fun ImageView.pressedSrc(
    @DrawableRes pressedRes: Int,
    @DrawableRes normalRes: Int,
    @DrawableRes disableRes: Int
) {
    pressedSrc(findDrawable(pressedRes), findDrawable(normalRes), findDrawable(disableRes))
}

fun TextView.setDrawableLeftPressed(
    pressedDrawable: Drawable? = null,
    normalDrawable: Drawable? = null,
    disableDrawable: Drawable? = null
) {
    setDrawableLeft(
        StateListDrawable()
            .enable(disableDrawable, false)
            .pressed(pressedDrawable)
            .normal(normalDrawable)
    )
}

fun TextView.setDrawableLeftPressed(
    @DrawableRes pressedRes: Int,
    @DrawableRes normalRes: Int,
    @DrawableRes disableRes: Int
) {
    setDrawableLeftPressed(
        findDrawable(pressedRes),
        findDrawable(normalRes),
        findDrawable(disableRes)
    )
}

fun TextView.setDrawableRightPressed(
    pressedDrawable: Drawable? = null,
    normalDrawable: Drawable? = null,
    disableDrawable: Drawable? = null
) {
    setDrawableRight(
        StateListDrawable()
            .enable(disableDrawable, false)
            .pressed(pressedDrawable)
            .normal(normalDrawable)
    )
}

fun TextView.setDrawableRightPressed(
    @DrawableRes pressedRes: Int,
    @DrawableRes normalRes: Int,
    @DrawableRes disableRes: Int
) {
    setDrawableRightPressed(
        findDrawable(pressedRes),
        findDrawable(normalRes),
        findDrawable(disableRes)
    )
}

fun TextView.setDrawableTopPressed(
    pressedDrawable: Drawable? = null,
    normalDrawable: Drawable? = null,
    disableDrawable: Drawable? = null
) {
    setDrawableTop(
        StateListDrawable()
            .enable(disableDrawable, false)
            .pressed(pressedDrawable)
            .normal(normalDrawable)
    )
}

fun TextView.setDrawableTopPressed(
    @DrawableRes pressedRes: Int,
    @DrawableRes normalRes: Int,
    @DrawableRes disableRes: Int
) {
    setDrawableTopPressed(
        findDrawable(pressedRes),
        findDrawable(normalRes),
        findDrawable(disableRes)
    )
}

fun TextView.setDrawableBottomPressed(
    pressedDrawable: Drawable? = null,
    normalDrawable: Drawable? = null,
    disableDrawable: Drawable? = null
) {
    setDrawableBottom(
        StateListDrawable()
            .enable(disableDrawable, false)
            .pressed(pressedDrawable)
            .normal(normalDrawable)
    )
}

fun TextView.setDrawableBottomPressed(
    @DrawableRes pressedRes: Int,
    @DrawableRes normalRes: Int,
    @DrawableRes disableRes: Int
) {
    setDrawableBottomPressed(
        findDrawable(pressedRes),
        findDrawable(normalRes),
        findDrawable(disableRes)
    )
}

// ========================================================================
// =                              勾选状态背景                              =
// ========================================================================

fun View.checkedBg(
    checkedDrawable: Drawable? = null,
    normalDrawable: Drawable? = null,
    disableDrawable: Drawable? = null
) {
    background = StateListDrawable()
        .enable(disableDrawable, false)
        .checked(checkedDrawable)
        .normal(normalDrawable)
}

fun View.checkedBg(
    @DrawableRes checkedRes: Int,
    @DrawableRes normalRes: Int,
    @DrawableRes disableRes: Int
) {
    checkedBg(findDrawable(checkedRes), findDrawable(normalRes), findDrawable(disableRes))
}

fun ImageView.checkedSrc(
    checkedDrawable: Drawable? = null,
    normalDrawable: Drawable? = null,
    disableDrawable: Drawable? = null
) {
    setImageDrawable(
        StateListDrawable()
            .enable(disableDrawable, false)
            .checked(checkedDrawable)
            .normal(normalDrawable)
    )
}

fun ImageView.checkedSrc(
    @DrawableRes checkedRes: Int,
    @DrawableRes normalRes: Int,
    @DrawableRes disableRes: Int
) {
    checkedSrc(findDrawable(checkedRes), findDrawable(normalRes), findDrawable(disableRes))
}

fun TextView.setDrawableLeftChecked(
    checkedDrawable: Drawable? = null,
    normalDrawable: Drawable? = null,
    disableDrawable: Drawable? = null
) {
    setDrawableLeft(
        StateListDrawable()
            .enable(disableDrawable, false)
            .pressed(checkedDrawable)
            .normal(normalDrawable)
    )
}

fun TextView.setDrawableLeftChecked(
    @DrawableRes checkedRes: Int,
    @DrawableRes normalRes: Int,
    @DrawableRes disableRes: Int
) {
    setDrawableLeftChecked(
        findDrawable(checkedRes),
        findDrawable(normalRes),
        findDrawable(disableRes)
    )
}

fun TextView.setDrawableRightChecked(
    checkedDrawable: Drawable? = null,
    normalDrawable: Drawable? = null,
    disableDrawable: Drawable? = null
) {
    setDrawableRight(
        StateListDrawable()
            .enable(disableDrawable, false)
            .checked(checkedDrawable)
            .normal(normalDrawable)
    )
}

fun TextView.setDrawableRightChecked(
    @DrawableRes checkedRes: Int,
    @DrawableRes normalRes: Int,
    @DrawableRes disableRes: Int
) {
    setDrawableRightChecked(
        findDrawable(checkedRes),
        findDrawable(normalRes),
        findDrawable(disableRes)
    )
}

fun TextView.setDrawableTopChecked(
    checkedDrawable: Drawable? = null,
    normalDrawable: Drawable? = null,
    disableDrawable: Drawable? = null
) {
    setDrawableTop(
        StateListDrawable()
            .enable(disableDrawable, false)
            .checked(checkedDrawable)
            .normal(normalDrawable)
    )
}

fun TextView.setDrawableTopChecked(
    @DrawableRes checkedRes: Int,
    @DrawableRes normalRes: Int,
    @DrawableRes disableRes: Int
) {
    setDrawableTopChecked(
        findDrawable(checkedRes),
        findDrawable(normalRes),
        findDrawable(disableRes)
    )
}

fun TextView.setDrawableBottomChecked(
    checkedDrawable: Drawable? = null,
    normalDrawable: Drawable? = null,
    disableDrawable: Drawable? = null
) {
    setDrawableBottom(
        StateListDrawable()
            .enable(disableDrawable, false)
            .checked(checkedDrawable)
            .normal(normalDrawable)
    )
}

fun TextView.setDrawableBottomChecked(
    @DrawableRes checkedRes: Int,
    @DrawableRes normalRes: Int,
    @DrawableRes disableRes: Int
) {
    setDrawableBottomChecked(
        findDrawable(checkedRes),
        findDrawable(normalRes),
        findDrawable(disableRes)
    )
}

// ========================================================================
// =                              选中状态背景                              =
// ========================================================================

fun View.selectedBg(
    selectedDrawable: Drawable? = null,
    normalDrawable: Drawable? = null,
    disableDrawable: Drawable? = null
) {
    background = StateListDrawable()
        .enable(disableDrawable, false)
        .selected(selectedDrawable)
        .normal(normalDrawable)
}

fun View.selectedBg(
    @DrawableRes selectedRes: Int,
    @DrawableRes normalRes: Int,
    @DrawableRes disableRes: Int
) {
    selectedBg(findDrawable(selectedRes), findDrawable(normalRes), findDrawable(disableRes))
}

fun ImageView.selectedSrc(
    selectedDrawable: Drawable? = null,
    normalDrawable: Drawable? = null,
    disableDrawable: Drawable? = null
) {
    setImageDrawable(
        StateListDrawable()
            .enable(disableDrawable, false)
            .selected(selectedDrawable)
            .normal(normalDrawable)
    )
}

fun ImageView.selectedSrc(
    @DrawableRes selectedRes: Int,
    @DrawableRes normalRes: Int,
    @DrawableRes disableRes: Int
) {
    selectedSrc(findDrawable(selectedRes), findDrawable(normalRes), findDrawable(disableRes))
}

fun TextView.setDrawableLeftSelected(
    selectedDrawable: Drawable? = null,
    normalDrawable: Drawable? = null,
    disableDrawable: Drawable? = null
) {
    setDrawableLeft(
        StateListDrawable()
            .enable(disableDrawable, false)
            .selected(selectedDrawable)
            .normal(normalDrawable)
    )
}

fun TextView.setDrawableLeftSelected(
    @DrawableRes selectedRes: Int,
    @DrawableRes normalRes: Int,
    @DrawableRes disableRes: Int
) {
    setDrawableLeftSelected(
        findDrawable(selectedRes),
        findDrawable(normalRes),
        findDrawable(disableRes)
    )
}

fun TextView.setDrawableRightSelected(
    selectedDrawable: Drawable? = null,
    normalDrawable: Drawable? = null,
    disableDrawable: Drawable? = null
) {
    setDrawableRight(
        StateListDrawable()
            .enable(disableDrawable, false)
            .selected(selectedDrawable)
            .normal(normalDrawable)
    )
}

fun TextView.setDrawableRightSelected(
    @DrawableRes selectedRes: Int,
    @DrawableRes normalRes: Int,
    @DrawableRes disableRes: Int
) {
    setDrawableRightSelected(
        findDrawable(selectedRes),
        findDrawable(normalRes),
        findDrawable(disableRes)
    )
}

fun TextView.setDrawableTopSelected(
    selectedDrawable: Drawable? = null,
    normalDrawable: Drawable? = null,
    disableDrawable: Drawable? = null
) {
    setDrawableTop(
        StateListDrawable()
            .enable(disableDrawable, false)
            .selected(selectedDrawable)
            .normal(normalDrawable)
    )
}

fun TextView.setDrawableTopSelected(
    @DrawableRes selectedRes: Int,
    @DrawableRes normalRes: Int,
    @DrawableRes disableRes: Int
) {
    setDrawableTopSelected(
        findDrawable(selectedRes),
        findDrawable(normalRes),
        findDrawable(disableRes)
    )
}

fun TextView.setDrawableBottomSelected(
    selectedDrawable: Drawable? = null,
    normalDrawable: Drawable? = null,
    disableDrawable: Drawable? = null
) {
    setDrawableBottom(
        StateListDrawable()
            .enable(disableDrawable, false)
            .selected(selectedDrawable)
            .normal(normalDrawable)
    )
}

fun TextView.setDrawableBottomSelected(
    @DrawableRes selectedRes: Int,
    @DrawableRes normalRes: Int,
    @DrawableRes disableRes: Int
) {
    setDrawableBottomSelected(
        findDrawable(selectedRes),
        findDrawable(normalRes),
        findDrawable(disableRes)
    )
}