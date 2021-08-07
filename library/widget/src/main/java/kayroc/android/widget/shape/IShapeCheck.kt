package kayroc.android.widget.shape

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.widget.CompoundButton
import kayroc.android.widget.R

/**
 * CompoundButton 协议
 * @author : kayroc
 */
interface IShapeCheck<out V : CompoundButton> : IShapeText<V> {

    /** 选中状态填充色 推荐默认值：solidNormalColor */
    var solidCheckedColor: Int

    /** 选中状态边框颜色 推荐默认值：borderNormalColor */
    var borderCheckedColor: Int

    /** 选中状态边框宽度 推荐默认值：borderNormalWidth */
    var borderCheckedWidth: Int

    /** 选中状态字体颜色 推荐默认值：textNormalColor */
    var textCheckedColor: Int

    override fun initCustomAttr(context: Context, attributeSet: AttributeSet?) {
        super.initCustomAttr(context, attributeSet)
        val typedArray: TypedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ShapeRadioButton)

        solidCheckedColor = typedArray.getColor(R.styleable.ShapeRadioButton_solidCheckedColor, solidNormalColor)

        borderCheckedColor = typedArray.getColor(R.styleable.ShapeRadioButton_borderCheckedColor, borderNormalColor)

        borderCheckedWidth = typedArray.getColor(R.styleable.ShapeRadioButton_borderCheckedWidth, borderNormalWidth)

        textCheckedColor = typedArray.getColor(R.styleable.ShapeRadioButton_textCheckedColor, textNormalColor)

        typedArray.recycle()
    }

    fun buildCheckedDrawable(): Drawable {
        return GradientDrawable().apply {
            shape = this@IShapeCheck.shape
            setSize(shapeWidth, shapeHeight)
            setColor(solidCheckedColor)
            cornerRadii = floatArrayOf(
                cornerRadiusTopLeft, cornerRadiusTopLeft,
                cornerRadiusTopRight, cornerRadiusTopRight,
                cornerRadiusBottomRight, cornerRadiusBottomRight,
                cornerRadiusBottomLeft, cornerRadiusBottomLeft
            )
            setStroke(borderCheckedWidth, borderCheckedColor, borderDashWidth, borderDashGap)

            if (gradientStartColor != Color.TRANSPARENT || gradientCenterColor != Color.TRANSPARENT || gradientEndColor != Color.TRANSPARENT) {
                gradientType = this@IShapeCheck.gradientType
                colors = if (gradientCenterColor == Color.TRANSPARENT) {
                    intArrayOf(gradientStartColor, gradientEndColor)
                } else {
                    intArrayOf(gradientStartColor, gradientCenterColor, gradientEndColor)
                }
                orientation = gradientOrientation
                gradientRadius = gradientRadius
                setGradientCenter(this@IShapeCheck.gradientCenterX, this@IShapeCheck.gradientCenterY)
            }
        }
    }

    override fun buildContentDrawable(): Drawable {
        return if (
            solidNormalColor != solidPressedColor || borderNormalColor != borderPressedColor ||
            solidNormalColor != solidDisabledColor || borderNormalColor != borderDisabledColor ||
            solidNormalColor != solidFocusedColor || borderNormalColor != borderFocusedColor ||
            solidNormalColor != solidSelectedColor || borderNormalColor != borderSelectedColor ||
            solidNormalColor != solidCheckedColor || borderNormalColor != borderCheckedColor
        ) {

            val stateListDrawable = StateListDrawable()
            // disable
            if (solidNormalColor != solidDisabledColor || borderNormalColor != borderDisabledColor) {
                stateListDrawable.addState(intArrayOf(-android.R.attr.state_enabled), buildDisabledDrawable())
            }
            // focused
            if (solidNormalColor != solidFocusedColor || borderNormalColor != borderFocusedColor) {
                stateListDrawable.addState(intArrayOf(android.R.attr.state_focused), buildFocusedDrawable())
            }
            // pressed
            if (solidNormalColor != solidPressedColor || borderNormalColor != borderPressedColor) {
                stateListDrawable.addState(intArrayOf(android.R.attr.state_pressed), buildPressedDrawable())
            }
            // checked
            if (solidNormalColor != solidCheckedColor || borderNormalColor != solidCheckedColor) {
                stateListDrawable.addState(intArrayOf(android.R.attr.state_checked), buildCheckedDrawable())
            }
            // selected
            if (solidNormalColor != solidSelectedColor || borderNormalColor != borderSelectedColor) {
                stateListDrawable.addState(intArrayOf(android.R.attr.state_selected), buildSelectedDrawable())
            }
            // normal
            stateListDrawable.addState(intArrayOf(), buildNormalDrawable())

            stateListDrawable
        } else {
            buildNormalDrawable()
        }
    }

    override fun buildTextColor(): ColorStateList {
        return ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_enabled),
                intArrayOf(android.R.attr.state_focused),
                intArrayOf(android.R.attr.state_pressed),
                intArrayOf(android.R.attr.state_checked),
                intArrayOf(android.R.attr.state_selected),
                intArrayOf()
            ), intArrayOf(
                textDisabledColor,
                textFocusedColor,
                textPressedColor,
                textCheckedColor,
                textSelectedColor,
                textNormalColor
            )
        )
    }
}