package kayroc.android.widget.shape

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.view.View
import kayroc.android.widget.R

/**
 * ShapeView 通用协议
 * @author : kayroc
 */
interface IShapeBase<V: View> {

    /** Shape 形状 推荐默认值：[GradientDrawable.RECTANGLE] */
    var shape: Int
    /** shape 宽度 推荐默认值：-1 */
    var shapeWidth: Int
    /** shape 高度 推荐默认值：-1 */
    var shapeHeight: Int

    /** 正常状态填充色 推荐默认值：Color.TRANSPARENT */
    var solidNormalColor: Int
    /** 按下状态填充色 推荐默认值：solidNormalColor */
    var solidPressedColor: Int
    /** 禁用状态填充色 推荐默认值：solidNormalColor */
    var solidDisabledColor: Int
    /** 焦点状态填充色 推荐默认值：solidNormalColor */
    var solidFocusedColor: Int
    /** 选择状态填充色 推荐默认值：solidNormalColor */
    var solidSelectedColor: Int

    /** 左上圆角大小 推荐默认值：0f */
    var cornerRadiusTopLeft: Float
    /** 右上圆角大小 推荐默认值：0f */
    var cornerRadiusTopRight: Float
    /** 左下圆角大小 推荐默认值：0f */
    var cornerRadiusBottomLeft: Float
    /** 左下圆角大小 推荐默认值：0f */
    var cornerRadiusBottomRight: Float

    /** 渐变起始颜色 推荐默认值：Color.TRANSPARENT */
    var gradientStartColor: Int
    /** 渐变中间颜色 推荐默认值：Color.TRANSPARENT */
    var gradientCenterColor: Int
    /** 渐变结束颜色 推荐默认值：Color.TRANSPARENT */
    var gradientEndColor: Int
    /** 渐变方向 推荐默认值：GradientDrawable.Orientation.LEFT_RIGHT */
    var gradientOrientation: GradientDrawable.Orientation
    /** 渐变类型 推荐默认值：GradientDrawable.LINEAR_GRADIENT */
    var gradientType: Int
    /** 渐变中心x 推荐默认值：0.5f */
    var gradientCenterX: Float
    /** 渐变中心y 推荐默认值：0.5f */
    var gradientCenterY: Float
    /** 渐变半径 0f */
    var gradientRadius: Float

    /** 正常状态边框颜色 推荐默认值：Color.TRANSPARENT */
    var borderNormalColor: Int
    /** 按下状态边框颜色 推荐默认值：borderNormalColor */
    var borderPressedColor: Int
    /** 禁用状态边框颜色 推荐默认值：borderNormalColor */
    var borderDisabledColor: Int
    /** 焦点状态边框颜色 推荐默认值：borderNormalColor */
    var borderFocusedColor: Int
    /** 选择状态边框颜色 推荐默认值：borderNormalColor */
    var borderSelectedColor: Int

    /** 正常状态边框宽度 推荐默认值：0 */
    var borderNormalWidth: Int
    /** 按下状态边框宽度 推荐默认值：borderNormalWidth */
    var borderPressedWidth: Int
    /** 禁用状态边框宽度 推荐默认值：borderNormalWidth */
    var borderDisabledWidth: Int
    /** 焦点状态边框宽度 推荐默认值：borderNormalWidth */
    var borderFocusedWidth: Int
    /** 选择状态边框宽度 推荐默认值：borderNormalWidth */
    var borderSelectedWidth: Int

    /** 边框虚线宽度 推荐默认值：0f */
    var borderDashWidth: Float
    /** 边框虚线间隔 推荐默认值：0f */
    var borderDashGap: Float

    /**
     * 初始化自定义属性
     * @param context [Context]
     * @param attributeSet [AttributeSet]
     */
    fun initCustomAttr(context: Context, attributeSet: AttributeSet?) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ShapeView)
        shape = typedArray.getInt(R.styleable.ShapeView_shape, shape)
        shapeWidth = typedArray.getDimensionPixelSize(R.styleable.ShapeView_shapeWidth, shapeWidth)
        shapeHeight = typedArray.getDimensionPixelSize(R.styleable.ShapeView_shapeHeight, shapeHeight)

        solidNormalColor = typedArray.getColor(R.styleable.ShapeView_solidNormalColor, solidNormalColor)
        solidPressedColor = typedArray.getColor(R.styleable.ShapeView_solidPressedColor, solidNormalColor)
        solidDisabledColor = typedArray.getColor(R.styleable.ShapeView_solidDisabledColor, solidNormalColor)
        solidFocusedColor = typedArray.getColor(R.styleable.ShapeView_solidFocusedColor, solidNormalColor)
        solidSelectedColor = typedArray.getColor(R.styleable.ShapeView_solidSelectedColor, solidNormalColor)

        val radius = typedArray.getDimension(R.styleable.ShapeView_cornerRadius, 0f)
        cornerRadiusTopLeft = typedArray.getDimension(R.styleable.ShapeView_cornerRadiusTopLeft, radius)
        cornerRadiusTopRight = typedArray.getDimension(R.styleable.ShapeView_cornerRadiusTopRight, radius)
        cornerRadiusBottomLeft = typedArray.getDimension(R.styleable.ShapeView_cornerRadiusBottomLeft, radius)
        cornerRadiusBottomRight = typedArray.getDimension(R.styleable.ShapeView_cornerRadiusBottomRight, radius)

        gradientStartColor = typedArray.getColor(R.styleable.ShapeView_gradientStartColor, gradientStartColor)
        gradientCenterColor = typedArray.getColor(R.styleable.ShapeView_gradientCenterColor, gradientCenterColor)
        gradientEndColor = typedArray.getColor(R.styleable.ShapeView_gradientEndColor, gradientEndColor)
        val orientation = typedArray.getInt(R.styleable.ShapeView_gradientOrientation, GradientDrawable.Orientation.LEFT_RIGHT.ordinal)
        gradientOrientation = when (orientation) {
            0 -> GradientDrawable.Orientation.TOP_BOTTOM
            1 -> GradientDrawable.Orientation.TR_BL
            2 -> GradientDrawable.Orientation.RIGHT_LEFT
            3 -> GradientDrawable.Orientation.BR_TL
            4 -> GradientDrawable.Orientation.BOTTOM_TOP
            5 -> GradientDrawable.Orientation.BL_TR
            6 -> GradientDrawable.Orientation.LEFT_RIGHT
            else -> GradientDrawable.Orientation.TL_BR
        }
        gradientType = typedArray.getInt(R.styleable.ShapeView_gradientType, gradientType)
        gradientCenterX = typedArray.getFloat(R.styleable.ShapeView_gradientCenterX, gradientCenterX)
        gradientCenterY = typedArray.getFloat(R.styleable.ShapeView_gradientCenterY, gradientCenterY)
        gradientRadius = typedArray.getDimension(R.styleable.ShapeView_gradientRadius, gradientRadius)

        borderNormalColor = typedArray.getColor(R.styleable.ShapeView_borderNormalColor, borderNormalColor)
        borderPressedColor = typedArray.getColor(R.styleable.ShapeView_borderPressedColor, borderNormalColor)
        borderDisabledColor = typedArray.getColor(R.styleable.ShapeView_borderDisabledColor, borderNormalColor)
        borderFocusedColor = typedArray.getColor(R.styleable.ShapeView_borderFocusedColor, borderNormalColor)
        borderSelectedColor = typedArray.getColor(R.styleable.ShapeView_borderSelectedColor, borderNormalColor)

        borderNormalWidth = typedArray.getColor(R.styleable.ShapeView_borderNormalWidth, borderNormalWidth)
        borderPressedWidth = typedArray.getColor(R.styleable.ShapeView_borderPressedWidth, borderNormalWidth)
        borderDisabledWidth = typedArray.getColor(R.styleable.ShapeView_borderDisabledWidth, borderNormalWidth)
        borderFocusedWidth = typedArray.getColor(R.styleable.ShapeView_borderFocusedWidth, borderNormalWidth)
        borderSelectedWidth = typedArray.getColor(R.styleable.ShapeView_borderSelectedWidth, borderNormalWidth)

        borderDashWidth = typedArray.getDimension(R.styleable.ShapeView_borderDashWidth, borderDashWidth)
        borderDashGap = typedArray.getDimension(R.styleable.ShapeView_borderDashGap, borderDashGap)

        typedArray.recycle()
    }

    /**
     * 构建正常状态的 Drawable
     * @return Drawable
     */
    fun buildNormalDrawable(): Drawable {
        return GradientDrawable().apply {
            shape = this@IShapeBase.shape
            setSize(shapeWidth, shapeHeight)
            setColor(solidNormalColor)
            cornerRadii = floatArrayOf(
                cornerRadiusTopLeft, cornerRadiusTopLeft,
                cornerRadiusTopRight, cornerRadiusTopRight,
                cornerRadiusBottomRight, cornerRadiusBottomRight,
                cornerRadiusBottomLeft, cornerRadiusBottomLeft
            )
            setStroke(borderNormalWidth, borderNormalColor, borderDashWidth, borderDashGap)

            if (gradientStartColor != Color.TRANSPARENT || gradientCenterColor != Color.TRANSPARENT || gradientEndColor != Color.TRANSPARENT) {
                gradientType = this@IShapeBase.gradientType
                colors = if (gradientCenterColor == Color.TRANSPARENT) {
                    intArrayOf(gradientStartColor, gradientEndColor)
                } else {
                    intArrayOf(gradientStartColor, gradientCenterColor, gradientEndColor)
                }
                orientation = gradientOrientation
                gradientRadius = gradientRadius
                setGradientCenter(this@IShapeBase.gradientCenterX, this@IShapeBase.gradientCenterY)
            }
        }
    }

    /**
     * 构建按下状态的 Drawable
     * @return Drawable
     */
    fun buildPressedDrawable(): Drawable {
        return GradientDrawable().apply {
            shape = this@IShapeBase.shape
            setSize(shapeWidth, shapeHeight)
            setColor(solidPressedColor)
            cornerRadii = floatArrayOf(
                cornerRadiusTopLeft, cornerRadiusTopLeft,
                cornerRadiusTopRight, cornerRadiusTopRight,
                cornerRadiusBottomRight, cornerRadiusBottomRight,
                cornerRadiusBottomLeft, cornerRadiusBottomLeft
            )
            setStroke(borderPressedWidth, borderPressedColor, borderDashWidth, borderDashGap)

            if (gradientStartColor != Color.TRANSPARENT || gradientCenterColor != Color.TRANSPARENT || gradientEndColor != Color.TRANSPARENT) {
                gradientType = this@IShapeBase.gradientType
                colors = if (gradientCenterColor == Color.TRANSPARENT) {
                    intArrayOf(gradientStartColor, gradientEndColor)
                } else {
                    intArrayOf(gradientStartColor, gradientCenterColor, gradientEndColor)
                }
                orientation = gradientOrientation
                gradientRadius = gradientRadius
                setGradientCenter(this@IShapeBase.gradientCenterX, this@IShapeBase.gradientCenterY)
            }
        }
    }

    /**
     * 构建禁用状态的 Drawable
     * @return Drawable
     */
    fun buildDisabledDrawable(): Drawable {
        return GradientDrawable().apply {
            shape = this@IShapeBase.shape
            setSize(shapeWidth, shapeHeight)
            setColor(solidDisabledColor)
            cornerRadii = floatArrayOf(
                cornerRadiusTopLeft, cornerRadiusTopLeft,
                cornerRadiusTopRight, cornerRadiusTopRight,
                cornerRadiusBottomRight, cornerRadiusBottomRight,
                cornerRadiusBottomLeft, cornerRadiusBottomLeft
            )
            setStroke(borderDisabledWidth, borderDisabledColor, borderDashWidth, borderDashGap)

            if (gradientStartColor != Color.TRANSPARENT || gradientCenterColor != Color.TRANSPARENT || gradientEndColor != Color.TRANSPARENT) {
                gradientType = this@IShapeBase.gradientType
                colors = if (gradientCenterColor == Color.TRANSPARENT) {
                    intArrayOf(gradientStartColor, gradientEndColor)
                } else {
                    intArrayOf(gradientStartColor, gradientCenterColor, gradientEndColor)
                }
                orientation = gradientOrientation
                gradientRadius = gradientRadius
                setGradientCenter(this@IShapeBase.gradientCenterX, this@IShapeBase.gradientCenterY)
            }
        }
    }

    /**
     * 构建焦点状态的 Drawable
     * @return Drawable
     */
    fun buildFocusedDrawable(): Drawable {
        return GradientDrawable().apply {
            shape = this@IShapeBase.shape
            setSize(shapeWidth, shapeHeight)
            setColor(solidFocusedColor)
            cornerRadii = floatArrayOf(
                cornerRadiusTopLeft, cornerRadiusTopLeft,
                cornerRadiusTopRight, cornerRadiusTopRight,
                cornerRadiusBottomRight, cornerRadiusBottomRight,
                cornerRadiusBottomLeft, cornerRadiusBottomLeft
            )
            setStroke(borderFocusedWidth, borderFocusedColor, borderDashWidth, borderDashGap)

            if (gradientStartColor != Color.TRANSPARENT || gradientCenterColor != Color.TRANSPARENT || gradientEndColor != Color.TRANSPARENT) {
                gradientType = this@IShapeBase.gradientType
                colors = if (gradientCenterColor == Color.TRANSPARENT) {
                    intArrayOf(gradientStartColor, gradientEndColor)
                } else {
                    intArrayOf(gradientStartColor, gradientCenterColor, gradientEndColor)
                }
                orientation = gradientOrientation
                gradientRadius = gradientRadius
                setGradientCenter(this@IShapeBase.gradientCenterX, this@IShapeBase.gradientCenterY)
            }
        }
    }

    /**
     * 构建选择状态的 Drawable
     * @return Drawable
     */
    fun buildSelectedDrawable(): Drawable {
        return GradientDrawable().apply {
            shape = this@IShapeBase.shape
            setSize(shapeWidth, shapeHeight)
            setColor(solidSelectedColor)
            cornerRadii = floatArrayOf(
                cornerRadiusTopLeft, cornerRadiusTopLeft,
                cornerRadiusTopRight, cornerRadiusTopRight,
                cornerRadiusBottomRight, cornerRadiusBottomRight,
                cornerRadiusBottomLeft, cornerRadiusBottomLeft
            )
            setStroke(borderSelectedWidth, borderSelectedColor, borderDashWidth, borderDashGap)

            if (gradientStartColor != Color.TRANSPARENT || gradientCenterColor != Color.TRANSPARENT || gradientEndColor != Color.TRANSPARENT) {
                gradientType = this@IShapeBase.gradientType
                colors = if (gradientCenterColor == Color.TRANSPARENT) {
                    intArrayOf(gradientStartColor, gradientEndColor)
                } else {
                    intArrayOf(gradientStartColor, gradientCenterColor, gradientEndColor)
                }
                orientation = gradientOrientation
                gradientRadius = gradientRadius
                setGradientCenter(this@IShapeBase.gradientCenterX, this@IShapeBase.gradientCenterY)
            }
        }
    }

    /**
     * 构建用于水波纹的ContentDrawable
     * @return Drawable
     */
    fun buildContentDrawable(): Drawable? {
        return if (
            solidNormalColor != solidPressedColor || borderNormalColor != borderPressedColor ||
            solidNormalColor != solidDisabledColor || borderNormalColor != borderDisabledColor ||
            solidNormalColor != solidFocusedColor || borderNormalColor != borderFocusedColor ||
            solidNormalColor != solidSelectedColor || borderNormalColor != borderSelectedColor
        ) {
            // 如果选择器状态的颜色属性发生改变，则构建 StateListDrawable

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
            // selected
            if (solidNormalColor != solidSelectedColor || borderNormalColor != borderSelectedColor) {
                stateListDrawable.addState(intArrayOf(android.R.attr.state_selected), buildSelectedDrawable())
            }
            // normal
            stateListDrawable.addState(intArrayOf(), buildNormalDrawable())

            stateListDrawable
        } else if (solidNormalColor != Color.TRANSPARENT || borderNormalColor != Color.TRANSPARENT ||
            gradientStartColor != Color.TRANSPARENT || gradientCenterColor != Color.TRANSPARENT || gradientEndColor != Color.TRANSPARENT) {
            // 如果默认状态的颜色发生改变，则构建正常状态的 GradientDrawable
            buildNormalDrawable()
        } else {
            // 如果没有颜色变化，则认为没有自定义属性，什么都不做
            null
        }
    }

    /**
     * 应用属性
     * 为防止频繁创建 Drawable，请在更改自定义属性后，再次调用此方法
     * @param view View
     */
    fun applyCustomAttr() {
        val contentDrawable = buildContentDrawable()
        if (contentDrawable != null) {
            getShapeView().background = contentDrawable
        }
    }

    fun getShapeView(): V
}