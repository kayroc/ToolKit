package kayroc.android.extension.drawable

import android.content.res.ColorStateList
import android.widget.TextView
import androidx.annotation.ColorInt
import kayroc.android.extension.findColor
import kayroc.android.kotlin.R

/**
 * ColorStateList 相关
 *
 * @author kayroc
 */

// ============================================================================
// =                              ColorStateList                              =
// ============================================================================

class ColorStateBuilder {

    private val map = mutableMapOf<IntArray, Int>()

    fun addStateColor(state: IntArray, @ColorInt color: Int): ColorStateBuilder {
        map[state] = color
        return this
    }

    fun normalTextColor(@ColorInt color: Int): ColorStateBuilder {
        map[intArrayOf()] = color
        return this
    }

    fun pressedTextColor(@ColorInt color: Int, reversed: Boolean = true): ColorStateBuilder {
        if (reversed) {
            map[intArrayOf(android.R.attr.state_pressed)] = color
        } else {
            map[intArrayOf(-android.R.attr.state_pressed)] = color
        }
        return this
    }

    fun enableTextColor(@ColorInt color: Int, reversed: Boolean = true): ColorStateBuilder {
        if (reversed) {
            map[intArrayOf(android.R.attr.state_enabled)] = color
        } else {
            map[intArrayOf(-android.R.attr.state_enabled)] = color
        }
        return this
    }


    fun checkedTextColor(@ColorInt color: Int, reversed: Boolean = true): ColorStateBuilder {
        if (reversed) {
            map[intArrayOf(android.R.attr.state_checked)] = color
        } else {
            map[intArrayOf(-android.R.attr.state_checked)] = color
        }
        return this
    }


    fun selectedTextColor(@ColorInt color: Int, reversed: Boolean = true): ColorStateBuilder {
        if (reversed) {
            map[intArrayOf(android.R.attr.state_selected)] = color
        } else {
            map[intArrayOf(-android.R.attr.state_selected)] = color
        }
        return this
    }


    fun focusedTextColor(@ColorInt color: Int, reversed: Boolean = true): ColorStateBuilder {
        if (reversed) {
            map[intArrayOf(android.R.attr.state_focused)] = color
        } else {
            map[intArrayOf(-android.R.attr.state_focused)] = color
        }
        return this
    }

    fun build(): ColorStateList {
        val states = mutableListOf<IntArray>()
        val colors = mutableListOf<Int>()
        map.forEach {
            val key = it.key
            val value = it.value
            states.add(key)
            colors.add(value)
        }
        val statesArray = states.toTypedArray()
        val colorsArray: IntArray = colors.toIntArray()
        return ColorStateList(statesArray, colorsArray)
    }

}

// ==========================================================================
// =                              TextView 使用                              =
// ==========================================================================

fun TextView.setColorStateList(
    @ColorInt pressedColor: Int = findColor(R.color.black50),
    @ColorInt normalColor: Int = findColor(R.color.color_33),
    @ColorInt disableColor: Int = findColor(R.color.color_bb),
    @ColorInt selectorColor: Int = -1,
    @ColorInt checkedColor: Int = -1,
    @ColorInt focusColor: Int = -1
) {
    val colorStateBuilder = ColorStateBuilder()
    if (disableColor != -1) colorStateBuilder.enableTextColor(disableColor, false)
    if (selectorColor != -1) colorStateBuilder.selectedTextColor(selectorColor)
    if (checkedColor != -1) colorStateBuilder.checkedTextColor(checkedColor)
    if (focusColor != -1) colorStateBuilder.focusedTextColor(focusColor)
    colorStateBuilder.pressedTextColor(pressedColor)
    colorStateBuilder.normalTextColor(normalColor)
    val textColor = colorStateBuilder.build()
    setTextColor(textColor)
}