package kayroc.android.widget.shape.layout

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.widget.RelativeLayout
import kayroc.android.widget.shape.IShapeBase

/**
 * 支持 xml 设置 shape 属性的 RelativeLayout
 * @author : kayroc
 */
class ShapeRelativeLayout @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attributeSet, defStyleAttr),
    IShapeBase<ShapeRelativeLayout> {

    override var shape = GradientDrawable.RECTANGLE
    override var shapeWidth = -1
    override var shapeHeight = -1

    override var solidNormalColor = Color.TRANSPARENT
    override var solidPressedColor = solidNormalColor
    override var solidDisabledColor = solidNormalColor
    override var solidFocusedColor = solidNormalColor
    override var solidSelectedColor = solidNormalColor

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
    override var borderPressedColor = borderNormalColor
    override var borderDisabledColor = borderNormalColor
    override var borderFocusedColor = borderNormalColor
    override var borderSelectedColor = borderNormalColor

    override var borderNormalWidth = 0
    override var borderPressedWidth = borderNormalWidth
    override var borderDisabledWidth = borderNormalWidth
    override var borderFocusedWidth = borderNormalWidth
    override var borderSelectedWidth = borderNormalWidth

    override var borderDashWidth = 0f
    override var borderDashGap = 0f

    init {
        initCustomAttr(context, attributeSet)
        applyCustomAttr()
    }

    override fun getShapeView(): ShapeRelativeLayout = this
}