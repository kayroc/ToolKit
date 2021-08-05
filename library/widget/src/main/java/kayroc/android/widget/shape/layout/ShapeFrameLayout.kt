package kayroc.android.widget.shape.layout

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.widget.FrameLayout
import kayroc.android.widget.shape.IShapeBase

/**
 * 支持 xml 设置 shape 属性的 FrameLayout
 * @author : kayroc
 */
open class ShapeFrameLayout @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr),
    IShapeBase<ShapeFrameLayout> {

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
        initAttr(context, attributeSet)
    }

    private fun initAttr(context: Context, attributeSet: AttributeSet?) {
        initCustomAttr(context, attributeSet)
        applyCustomAttr()
    }

    override fun getShapeView(): ShapeFrameLayout = this
}