package kayroc.android.widget.shape.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import kayroc.android.widget.shape.IShapeBase

/**
 * 支持 xml 设置 shape 属性的 View
 * @author : kayroc
 */
open class ShapeView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attributeSet, defStyleAttr),
    IShapeBase<ShapeView> {

    override var shape = GradientDrawable.RECTANGLE
    override var shapeWidth = -1
    override var shapeHeight = -1

    override var solidNormalColor = Color.TRANSPARENT
    override var solidPressedColor = Color.TRANSPARENT
    override var solidDisabledColor = Color.TRANSPARENT
    override var solidFocusedColor = Color.TRANSPARENT
    override var solidSelectedColor = Color.TRANSPARENT

    override var cornerRadiusTopLeft = 0f
    override var cornerRadiusTopRight = 0f
    override var cornerRadiusBottomLeft = 0f
    override var cornerRadiusBottomRight = 0f

    override var gradientStartColor = Color.TRANSPARENT
    override var gradientCenterColor = Color.TRANSPARENT
    override var gradientEndColor = Color.TRANSPARENT
    override var gradientOrientation = GradientDrawable.Orientation.LEFT_RIGHT
    override var gradientType = GradientDrawable.LINEAR_GRADIENT
    override var gradientCenterX = 0.5f
    override var gradientCenterY = 0.5f
    override var gradientRadius = 0f

    override var borderNormalColor = Color.TRANSPARENT
    override var borderPressedColor = Color.TRANSPARENT
    override var borderDisabledColor = Color.TRANSPARENT
    override var borderFocusedColor = Color.TRANSPARENT
    override var borderSelectedColor = Color.TRANSPARENT

    override var borderNormalWidth = 0
    override var borderPressedWidth = 0
    override var borderDisabledWidth = 0
    override var borderFocusedWidth = 0
    override var borderSelectedWidth = 0

    override var borderDashWidth = 0f
    override var borderDashGap = 0f

    init {
        initView(context, attributeSet)
    }

    private fun initView(context: Context, attributeSet: AttributeSet?) {
        initCustomAttr(context, attributeSet)
        applyCustomAttr()
    }

    override fun getShapeView(): ShapeView = this
}