package kayroc.android.widget.shape

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.TextView
import kayroc.android.widget.R

/**
 * TextView 协议
 * @author : kayroc
 */
interface IShapeText<out V : TextView> : IShapeBase<V> {

    /** 正常状态字体颜色 推荐默认值：textColors.defaultColor */
    var textNormalColor: Int
    /** 按下状态字体颜色 推荐默认值：textNormalColor */
    var textPressedColor: Int
    /** 禁用状态字体颜色 推荐默认值：textNormalColor */
    var textDisabledColor: Int
    /** 焦点状态字体颜色 推荐默认值：textNormalColor */
    var textFocusedColor: Int
    /** 选择状态字体颜色 推荐默认值：textNormalColor */
    var textSelectedColor: Int

    override fun initCustomAttr(context: Context, attributeSet: AttributeSet?) {
        super.initCustomAttr(context, attributeSet)

        val typedArray: TypedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ShapeTextView)

        textNormalColor = typedArray.getColor(R.styleable.ShapeTextView_textNormalColor, textNormalColor)
        textPressedColor = typedArray.getColor(R.styleable.ShapeTextView_textPressedColor, textNormalColor)
        textDisabledColor = typedArray.getColor(R.styleable.ShapeTextView_textDisabledColor, textNormalColor)
        textFocusedColor = typedArray.getColor(R.styleable.ShapeTextView_textFocusedColor, textNormalColor)
        textSelectedColor = typedArray.getColor(R.styleable.ShapeTextView_textSelectedColor, textNormalColor)

        typedArray.recycle()
    }

    /**
     * 构建文字颜色
     * @return ColorStateList
     */
    fun buildTextColor(): ColorStateList? {
        return ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_enabled),
                intArrayOf(android.R.attr.state_focused),
                intArrayOf(android.R.attr.state_pressed),
                intArrayOf(android.R.attr.state_selected),
                intArrayOf()
            ), intArrayOf(
                textDisabledColor,
                textFocusedColor,
                textPressedColor,
                textSelectedColor,
                textNormalColor
            )
        )
    }

    override fun applyCustomAttr() {
        super.applyCustomAttr()
        getShapeView().setTextColor(buildTextColor())
    }

}
